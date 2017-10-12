package com.kalbenutritionals.app.kalbespgmobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import bl.clsHelperBL;
import bl.clsMainBL;
import bl.mCounterNumberBL;
import bl.mEmployeeSalesProductBL;
import bl.tPlanogramMobileBL;
import bl.tSalesProductHeaderBL;
import bl.tStockInHandDetailBL;
import bl.tStockInHandHeaderBL;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.OnXScrollListener;
import library.spgmobile.common.ModelListview;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.mEmployeeSalesProductData;
import library.spgmobile.common.tPlanogramMobileData;
import library.spgmobile.common.tStockInHandDetailData;
import library.spgmobile.common.tStockInHandHeaderData;
import library.spgmobile.common.visitplanAbsenData;
import library.spgmobile.dal.enumCounterData;
import library.spgmobile.dal.tSalesProductDetailDA;
import library.spgmobile.dal.tStockInHandDetailDA;

/**
 * Created by aan.junianto on 18/08/2017.
 */

public class FragmentAddStockInHand extends Fragment implements View.OnClickListener{

    TextView tv_date;
    TextView tv_noso;

    private EditText edketerangan, searchProduct;
    private String noso;
    private ArrayList<ModelListview> modelItems;
    private ArrayList<ModelListview> modelItemsLoadMore;
    private ArrayList<ModelListview> arrdataPriv;
    MyAdapter dataAdapter;
    ListView listView;
    int selectedId;
    public static ArrayList<ModelListview> arr = new ArrayList<>();

    ProgressDialog progressDialog;
    View v;
    FloatingActionButton fab;
    clsMainActivity _clsMainActivity;
    String idTrCustomer = null;
    String param = null;
    tStockInHandHeaderData dt;
    boolean validViewEdit = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        selectedId = 0;
        v = inflater.inflate(R.layout.coordinator_layout, container, false);

        Bundle mBundle = getArguments();
        if (mBundle != null) {
            idTrCustomer = mBundle.getString("id");
            param = mBundle.getString("param");
        }

        tv_noso = (TextView) v.findViewById(R.id.txtNoreso);
        tv_date = (TextView) v.findViewById(R.id.txtviewDate);
        edketerangan = (EditText) v.findViewById(R.id.etKeterangan);
        searchProduct = (EditText) v.findViewById(R.id.searchProduct);
        fab = (FloatingActionButton) v.findViewById(R.id.fab);

        _clsMainActivity = new clsMainActivity();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edketerangan.getWindowToken(), 0);

//        edketerangan.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        edketerangan.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        edketerangan.setSingleLine(false);

        InputFilter inputFilter_edketerangan1 = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                if (charSequence.equals("")) { // for backspace
                    return charSequence;
                }
                if (charSequence.toString().matches("[a-zA-Z0-9.,\\- ]+")) {
                    return charSequence;
                }
                return "";
            }
        };

        InputFilter inputFilter_edketerangan2 = new InputFilter.AllCaps();

        edketerangan.setFilters(new InputFilter[]{inputFilter_edketerangan1, inputFilter_edketerangan2});

        List<tStockInHandHeaderData> dtLast = new tStockInHandHeaderBL().getLastData();
        if (dtLast == null || dtLast.size() == 0) {
            noso = new mCounterNumberBL().getData(enumCounterData.NoSIH);

        } else {
            noso = new mCounterNumberBL().getData(enumCounterData.NoSIH);
            List<tStockInHandHeaderData> dataFirstIsExist = new tStockInHandHeaderBL().getDataByNoSO(noso);
            if (dataFirstIsExist.size() == 1) {
                clsHelper _clsHelper = new clsHelper();
                String oldVersion = dtLast.get(0).get_txtNoSo();
                noso = _clsHelper.generateNewId(oldVersion, "-", "4");
            } else {
                noso = new mCounterNumberBL().getData(enumCounterData.NoSIH);
            }
        }
        tv_noso.setText(noso);

//        String timeStamp = new SimpleDateFormat("dd/MM/yyyy",
//                Locale.getDefault()).format(new Date());
//        tv_date.setText(timeStamp);

        //adding date and outlet di menu add
        TextView txtDate = (TextView) v.findViewById(R.id.txtDate);
        txtDate.setText("Outlet-Date");
        tv_date.setVisibility(View.VISIBLE);

        visitplanAbsenData dtAbsensVisitplan = new clsHelperBL().getDataCheckInActive();
        String outlet = "-";

        if(dtAbsensVisitplan!=null){
            outlet = dtAbsensVisitplan.get_txtOutletName();
            if(dtAbsensVisitplan.get_txtOutletName().toString().equals("null")){
                outlet = "No Outlet";
            }
        }

        // add date & outlet
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy",
                Locale.getDefault()).format(new Date());
        tv_date.setText(outlet + "-" + timeStamp);

        Button btn_preview = (Button) v.findViewById(R.id.btnPreviewReso);
        btn_preview.setOnClickListener(this);

        listView = (ListView) v.findViewById(R.id.lvProduk);

        final ScrollView scrollView = (ScrollView) v.findViewById(R.id.scroll_reso);

        dt = new tStockInHandHeaderData();

        if(mBundle != null && !idTrCustomer.equals("null")){
            dt = new tStockInHandHeaderData();
            dt = new tStockInHandHeaderBL().getDataByNoSOOne(idTrCustomer);
            if(dt.get_txtNoSo()!=null){
                validViewEdit = true;
            }
        }

        if(validViewEdit){
            tv_noso.setText(dt.get_txtNoSo());
            edketerangan.setText(dt.get_txtKeterangan());
        }

        new GetAllemployeeSalesProductDataList().execute();
//        loadListData();

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                LinearLayout lnLayout = (LinearLayout) v.findViewById(R.id.lnReso);

                int coords[] = {0, 0};
                lnLayout.getLocationInWindow(coords);

                int absoluteTop = coords[1];

                if (absoluteTop < 90) {
//                    fab.setVisibility(View.VISIBLE);
                    fab.show();
                } else {
//                    fab.setVisibility(View.INVISIBLE);
                    fab.hide();
                }
            }
        });

        /*scrollView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View vi, MotionEvent event) {
                LinearLayout lnLayout = (LinearLayout) v.findViewById(R.id.lnReso);

                int coords[]={0,0};
                lnLayout.getLocationInWindow(coords);

                int absoluteTop = coords[1];

                if(absoluteTop < 90){
//                    fab.setVisibility(View.VISIBLE);
                    fab.show();
                }
                else{
//                    fab.setVisibility(View.INVISIBLE);
                    fab.hide();
                }

                return false;
            }
        });
*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
//                fab.setVisibility(View.INVISIBLE);
                fab.hide();
            }
        });

        searchProduct.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    dataAdapter.getFilter().filter(s.toString());
                    Collections.sort(modelItems, ModelListview.StuRollno);
                } else if (s.length() > 0) {
                    dataAdapter.getFilter().filter(s.toString());

                } else {
                    Collections.sort(modelItems, ModelListview.StuRollno);
                }
            }
        });

        searchProduct.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(searchProduct) {
            @Override
            public boolean onDrawableClick() {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchProduct.getWindowToken(), 0);
                CharSequence s = searchProduct.getText();
                if (s.length() > 0) {
                    searchProduct.getText().clear();

                }
//                else {
//                    Collections.sort(modelItems, ModelListview.StuRollno);
//                }


                return true;
            }
        });

//        listView.setOnScrollListener(new OnXScrollListener() {
//            @Override
//            public void onXScrolling(View view) {
//
//            }
//
//            @Override
//            public void onScrollStateChanged(AbsListView absListView, int i) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//                final int lastItem = i + i1;
//                if (lastItem == i2) {
//                    if (moreData) {
//                        footer.setVisibility(View.VISIBLE);
//                        footer.setPadding(0, 0, 0, 0);
//                        Handler handler = new Handler(); // hear is the handler for testing purpose
//                        handler.postDelayed(new Runnable() { // make some delay for check load more view
//                            @Override
//                            public void run() {
////                                getListData();
//                            }
//                        }, 10000);
//
//                    }
//
//                }
//            }
//        });

        return v;
    }

    public void viewResoFragment() {
        Intent myIntent = new Intent(getContext(), MainMenu.class);
        myIntent.putExtra("key_view", "View Stock On Hand");
        getActivity().finish();
        startActivity(myIntent);
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId()) {
            case R.id.btnPreviewReso:
                final List<tStockInHandDetailData> dataPrevList = new ArrayList<>();
                tStockInHandDetailData dataPrev = new tStockInHandDetailData();
                double qntySum = 0;
                double qntyNum;
                double value;
                double price;
                String result = "0";

                arrdataPriv = new ArrayList<>();
                for (int i = 0; i < modelItems.size(); i++) {
                    if (modelItems.get(i).get_value() > 0) {
                        ModelListview data = new ModelListview();
                        data.set_id(modelItems.get(i).get_id());
                        data.set_price(modelItems.get(i).get_price());
                        data.set_name(modelItems.get(i).get_name());
                        data.set_value(modelItems.get(i).get_value());
                        price = Double.parseDouble(modelItems.get(i).get_price());
                        value = Double.parseDouble(String.valueOf(modelItems.get(i).get_value()));
                        qntyNum = price * value;
                        qntySum += qntyNum;
                        result = new clsMainActivity().convertNumberDec2(qntySum);
                        arrdataPriv.add(data);

                        double prc = Double.valueOf(modelItems.get(i).get_price());
                        double itm = (double) modelItems.get(i).get_value();
                        dataPrev = new tStockInHandDetailData();
                        dataPrev.set_intId(_clsMainActivity.GenerateGuid());
                        dataPrev.set_intActive("1");
                        dataPrev.set_intPrice(modelItems.get(i).get_price());
                        dataPrev.set_intQty(String.valueOf(modelItems.get(i).get_value()));
                        dataPrev.set_txtCodeProduct(modelItems.get(i).get_id());
                        dataPrev.set_txtNameProduct(String.valueOf(modelItems.get(i).get_name()));
                        dataPrev.set_intTotal(new clsMainActivity().convertNumberDec2(prc * itm));
                        dataPrevList.add(dataPrev);
                    }
                }
                if (edketerangan.getText().toString().equals("")) {
                    _clsMainActivity.showCustomToast(getActivity(), "Please fill Description...", false);

                } else
                if (arrdataPriv.size()==0){
                    _clsMainActivity.showCustomToast(getActivity(), "Please fill Quantity Product...", false);

                } else if (arrdataPriv.size()>0){
                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    final View promptView = layoutInflater.inflate(R.layout.activity_preview_so, null);

                    final TextView _tvNoSO = (TextView) promptView.findViewById(R.id.tvnoSOtbl);
                    final TextView _tvKet = (TextView) promptView.findViewById(R.id.tvkettbl);
                    _tvNoSO.setText(String.format(": %s", tv_noso.getText().toString()));
                    _tvKet.setText(String.format(": %s", edketerangan.getText().toString()));

                    arr = new ArrayList<>();
                    arr = modelItems;

                    TableLayout tlb = (TableLayout) promptView.findViewById(R.id.tlProduct);
                    tlb.removeAllViews();

                    TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                    params.setMargins(1, 1, 1, 1);

                    TableRow tr = new TableRow(getContext());

                    TableLayout tl = new TableLayout(getContext());

                    String[] colTextHeader = {"Name", "Qty"};

                    for (String text : colTextHeader) {
                        TextView tv = new TextView(getContext());
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

                        tv.setTextSize(14);
                        tv.setPadding(10, 10, 10, 10);
                        tv.setText(text);
                        tv.setGravity(Gravity.CENTER);
                        tv.setBackgroundColor(Color.parseColor("#4CAF50"));

                        tv.setTextColor(Color.WHITE);
                        tr.addView(tv,params);
                    }
                    tl.addView(tr);

                    double qtySum = 0;
                    double qtyNum;
                    for (ModelListview dt : arrdataPriv) {

                        tr = new TableRow(getContext());

                        tr.setGravity(Gravity.CENTER_HORIZONTAL);
                        TableLayout.LayoutParams tableRowParams =
                                new TableLayout.LayoutParams
                                        (TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);

                        int leftMargin = 0;
                        int topMargin = 0;
                        int rightMargin = 0;
                        int bottomMargin = 0;
                        tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

                        tr.setLayoutParams(tableRowParams);

                        TextView product = new TextView(getContext());
                        product.setTextSize(12);
                        product.setWidth(400);
                        product.setPadding(10, 10, 10, 10);
                        product.setBackgroundColor(Color.parseColor("#f0f0f0"));
                        product.setTextColor(Color.BLACK);
                        product.setText(dt.get_name());
                        tr.addView(product,params);

                        TextView qty = new TextView(getContext());
                        qty.setTextSize(12);
                        qty.setPadding(10, 10, 10, 10);
                        qty.setBackgroundColor(Color.parseColor("#f0f0f0"));
                        qty.setTextColor(Color.BLACK);
                        qty.setGravity(Gravity.RIGHT);
                        qty.setText(String.valueOf(dt.get_value()+" pcs"));
                        tr.addView(qty,params);

//                        TextView price = new TextView(getContext());
//                        price.setTextSize(12);
//                        price.setPadding(10, 10, 10, 10);
//                        price.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                        price.setTextColor(Color.BLACK);
//                        price.setGravity(Gravity.RIGHT);
//                        price.setText(new clsMainActivity().convertNumberDec(Double.valueOf(dt.get_price())));
//                        tr.addView(price,params);
//
//                        TextView amount = new TextView(getContext());
//                        amount.setTextSize(12);
//                        amount.setWidth(200);
//                        amount.setPadding(10, 10, 10, 10);
//                        amount.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                        amount.setTextColor(Color.BLACK);
//                        amount.setGravity(Gravity.RIGHT);
//                        double prc = Double.valueOf(dt.get_price());
//                        double itm = (double) dt.get_value();
//                        qtyNum = prc * itm;
//                        qtySum += qtyNum;
//                        amount.setText(new clsMainActivity().convertNumberDec(qtyNum));
//                        tr.addView(amount,params);

                        tl.addView(tr, tableRowParams);
                    }
                    tlb.addView(tl);

                    final TextView tv_item = (TextView) promptView.findViewById(R.id.tvItemtbl);
                    tv_item.setTypeface(null, Typeface.BOLD);
                    int sumItem = arrdataPriv.size();
                    tv_item.setText(String.format(": %s", String.valueOf(sumItem)));

                    final TextView tv_amount = (TextView) promptView.findViewById(R.id.tvSumAmount);
                    tv_amount.setTypeface(null, Typeface.BOLD);
                    String nilai = new clsMainActivity().convertNumberDec(qtySum);
                    tv_amount.setText(String.format(": %s", String.valueOf(nilai)));

                    final TextView tv_status = (TextView) promptView.findViewById(R.id.tvStatus);
                    tv_status.setTypeface(null, Typeface.BOLD);
                    tv_status.setText(": Open");

                    final TableRow tr_amount = (TableRow) promptView.findViewById(R.id.tr_amount);
                    tr_amount.setVisibility(View.GONE);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setView(promptView);
                    final String intSumAmount = result;
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("Save",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                                            alertDialog.setTitle("Confirm");
                                            alertDialog.setMessage("Are you sure?");
                                            alertDialog.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    saveReso(dataPrevList, intSumAmount, arrdataPriv.size());
                                                    viewResoFragment();
                                                }
                                            });
                                            alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();

                                                }
                                            });

                                            alertDialog.show();
                                        }
                                    })
                            .setNegativeButton("Close",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            if (searchProduct.getText().length()>0){
                                                Collections.sort(modelItems, ModelListview.StuRollno);
                                            } else {
                                                Collections.sort(modelItems, ModelListview.StuRollno);
                                                dataAdapter.notifyDataSetChanged();
                                            }

                                            dialog.cancel();
                                        }
                                    });
                    final AlertDialog alertD = alertDialogBuilder.create();
                    alertD.show();
                    break;
                }
        }
    }

    private void saveReso(List<tStockInHandDetailData> listdatafromPrev,String intSumAmount, int intSumItem) {
        clsMainBL _clsMainBL = new clsMainBL();
        SQLiteDatabase _db = _clsMainBL.getDb();

        tStockInHandHeaderData dttStockInHandHeaderData = new tStockInHandHeaderData();

        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        clsMainActivity _clsMainActivity = new clsMainActivity();
//        tAbsenUserData absenUserData = new tAbsenUserBL().getDataCheckInActive();
        visitplanAbsenData _viAbsenData = new visitplanAbsenData();
        _viAbsenData = new clsHelperBL().getDataCheckInActive();

        dttStockInHandHeaderData.set_intId(new clsMainActivity().GenerateGuid());
        dttStockInHandHeaderData.set_txtNoSo(tv_noso.getText().toString());
        dttStockInHandHeaderData.set_dtDate(dateFormat.format(cal.getTime()));
        dttStockInHandHeaderData.set_OutletCode(_viAbsenData.get_txtOutletCode());
        dttStockInHandHeaderData.set_OutletName(_viAbsenData.get_txtOutletName());
        dttStockInHandHeaderData.set_txtKeterangan(edketerangan.getText().toString());
        dttStockInHandHeaderData.set_intSumItem(String.valueOf(intSumItem));
        dttStockInHandHeaderData.set_intSumAmount(String.valueOf(intSumAmount));
        dttStockInHandHeaderData.set_UserId(_viAbsenData.get_txtUserId());
        dttStockInHandHeaderData.set_txtRoleId(_viAbsenData.get_txtRoleId());
        dttStockInHandHeaderData.set_intSubmit("0");
        dttStockInHandHeaderData.set_intSync("0");
        dttStockInHandHeaderData.set_txtBranchCode(_viAbsenData.get_txtBranchCode());
        dttStockInHandHeaderData.set_txtBranchName(_viAbsenData.get_txtBranchName());
        dttStockInHandHeaderData.set_intIdAbsenUser(_viAbsenData.get_intId());
        dttStockInHandHeaderData.set_txtNIK(_viAbsenData.get_txtUserId());

        List<tStockInHandDetailData> tStockInHandDetailDataList = null;
        if(validViewEdit){
            dttStockInHandHeaderData.set_intId(dt.get_intId());
            dttStockInHandHeaderData.set_txtNoSo(dt.get_txtNoSo());
            tStockInHandDetailDataList = new tStockInHandDetailBL().GetDataByNoSO(dt.get_txtNoSo());
        }

        if(tStockInHandDetailDataList!=null&&tStockInHandDetailDataList.size()>0){
            for(tStockInHandDetailData dtDetail : tStockInHandDetailDataList){
                new tStockInHandDetailDA(_db).deleteContact(_db, dtDetail.get_intId());
            }
        }

        new tStockInHandHeaderBL().SaveData(dttStockInHandHeaderData);

        for(tStockInHandDetailData data : listdatafromPrev){
            data.set_txtNoSo(tv_noso.getText().toString());
            data.set_txtNIK(_viAbsenData.get_txtUserId());
            data.set_dtDate(dateFormat.format(cal.getTime()));
            data.set_txtKeterangan(edketerangan.getText().toString());
            new tStockInHandDetailDA(_db).SaveDatatStockInHandDetailData(_db, data);
        }

        _clsMainActivity.showCustomToast(getActivity(), "Saved", true);
        _db.close();
    }


    private class MyAdapter extends BaseAdapter implements Filterable {

        ArrayList<ModelListview> mOriginalValues; // Original Values
        ArrayList<ModelListview> mDisplayedValues;    // Values to be displayed


        MyAdapter(Context context, ArrayList<ModelListview> mProductArrayList) {
            this.mOriginalValues = mProductArrayList;
            this.mDisplayedValues = mProductArrayList;
        }

        @Override
        public int getCount() {
            return mDisplayedValues.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {
            EditText code;
            TextView name;
            //CheckBox name;

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {

                LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                convertView = vi.inflate(R.layout.activity_listview, null);

                holder = new ViewHolder();

                holder.name = (TextView) convertView.findViewById(R.id.textViewproduk);
                holder.code = (EditText) convertView.findViewById(R.id.editTextQty);
//                holder.code.setKeyListener(DigitsKeyListener.getInstance("0123456789"));

                convertView.setTag(holder);

                final ViewHolder finalHolder = holder;
                holder.code.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        //Collections.sort(mDisplayedValues, ModelListview.StuRollno);
                        EditText et = finalHolder.code;
                        for (int i = 0; i < mOriginalValues.size(); i++) {
                            if (finalHolder.name.getText().equals(mOriginalValues.get(i).get_id() + "\n" + mOriginalValues.get(i).get_name())) {
                                if (s.length() == 0) {
                                    mOriginalValues.get(i).set_value(0);
                                    break;

                                } else {
                                    mOriginalValues.get(i).set_value(Integer.parseInt(et.getText().toString()));
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                holder.code.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        for (int i = 0; i < mOriginalValues.size(); i++) {
                            if (finalHolder.name.getText().equals(mOriginalValues.get(i).get_id() + "\n" + mOriginalValues.get(i).get_name())) {
                                if (hasFocus) {
                                    if (mOriginalValues.get(i).get_value() == 0)
                                        finalHolder.code.setText("");
                                    else {
                                        finalHolder.code.setText(String.valueOf(mOriginalValues.get(i).get_value()));
                                        mOriginalValues.get(i).set_value(mOriginalValues.get(i).get_value());
                                        break;
                                    }
                                }
                            }

                        }

                    }

                });

            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            final ModelListview state = mDisplayedValues.get(position);

//            if (state.get_value()==0){
//                holder.name.setText(state.get_id()+"\n"+state.get_name());
//                holder.code.setText("");
//            } else {
            holder.name.setText(state.get_id() + "\n" + state.get_name());
            holder.code.setText(String.valueOf(state.get_value()));
//            final ViewHolder finalHolder1 = holder;

//            }

            holder.name.setTag(state);

            return convertView;
        }

        @Override
        public Filter getFilter() {
            return new Filter() {

                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    mDisplayedValues = (ArrayList<ModelListview>) results.values; // has the filtered values
                    notifyDataSetChanged();  // notifies the data with new filtered values
                    setListViewHeightBasedOnItems(listView);
                }

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                    ArrayList<ModelListview> FilteredArrList = new ArrayList<>();

                    if (mOriginalValues == null) {
                        mOriginalValues = new ArrayList<>(mDisplayedValues); // saves the original data in mOriginalValues
                    }

                    if (constraint == null || constraint.length() == 0) {

                        // set the Original result to return
                        results.count = mOriginalValues.size();
                        results.values = mOriginalValues;
                    } else {
                        constraint = constraint.toString().toLowerCase();
                        for (int i = 0; i < mOriginalValues.size(); i++) {
                            String data = mOriginalValues.get(i).get_name();
                            if (data.toLowerCase().startsWith(constraint.toString())) {
                                ModelListview dt = new ModelListview();
                                dt.set_id(mOriginalValues.get(i).get_id());
                                dt.set_name(mOriginalValues.get(i).get_name());
                                dt.set_value(mOriginalValues.get(i).get_value());
                                dt.set_selected(mOriginalValues.get(i).isSelected());
                                FilteredArrList.add(dt);
                            }
                        }
                        // set the Filtered result to return
                        results.count = FilteredArrList.size();
                        results.values = FilteredArrList;
                    }
                    return results;
                }
            };
        }
    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }

    private class GetAllemployeeSalesProductDataList extends AsyncTask<ArrayList<ModelListview>, Void, ArrayList<ModelListview>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //bikin progres dialognya
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading Data... Please Wait");
            progressDialog.setIndeterminate(true); //ukur berapa persen, false maka not do
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @SafeVarargs
        @Override
        protected final ArrayList<ModelListview> doInBackground(ArrayList<ModelListview>... params) {
            //android.os.Debug.waitForDebugger();

            List<mEmployeeSalesProductData> employeeSalesProductDataList = new mEmployeeSalesProductBL().getAllDataNotWhere();
            List<tStockInHandDetailData> tStockInHandDetailDataList = null;

            if(dt!=null&&dt.get_txtNoSo()!=null){
                tStockInHandDetailDataList = new tStockInHandDetailBL().GetDataByNoSO(dt.get_txtNoSo());
            }

            modelItems = new ArrayList<>();

            if (employeeSalesProductDataList.size() > 0) {
                for (int i = 0; i < employeeSalesProductDataList.size(); i++) {
                    ModelListview dt = new ModelListview();
                    dt.set_id(employeeSalesProductDataList.get(i).get_txtBrandDetailGramCode());
                    dt.set_name(employeeSalesProductDataList.get(i).get_txtProductBrandDetailGramName());
                    dt.set_price((employeeSalesProductDataList.get(i).get_decHJD()));
                    dt.set_NIK(employeeSalesProductDataList.get(i).get_txtNIK());
                    if(tStockInHandDetailDataList!=null&&tStockInHandDetailDataList.size()>0){
                        for(tStockInHandDetailData dataSaveDetail : tStockInHandDetailDataList){
                            if(dt.get_id().equals(dataSaveDetail.get_txtCodeProduct())){
                                dt.set_value(Integer.valueOf(dataSaveDetail.get_intQty()));
                            }
                        }
                    }
                    modelItems.add(dt);
                }
            }

            return modelItems;
        }

        @Override
        protected void onPostExecute(ArrayList<ModelListview> s) {
//            footer = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_layout, null, false);
//            listView.addFooterView(footer);
            Collections.sort(modelItems, ModelListview.StuRollno);
            dataAdapter = new MyAdapter(getContext(), modelItems);
            listView.setAdapter(dataAdapter);
            listView.setTextFilterEnabled(true);

            setListViewHeightBasedOnItems(listView);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            }, 5000);
        }
    }
    private View footer;
    private boolean moreData = true;
    private static Timer timer = new Timer();

    private void loadListData(){
        List<mEmployeeSalesProductData> employeeSalesProductDataList = new mEmployeeSalesProductBL().getAllDataNotWhere();
        List<tStockInHandDetailData> tStockInHandDetailDataList = null;

        if(dt!=null&&dt.get_txtNoSo()!=null){
            tStockInHandDetailDataList = new tStockInHandDetailBL().GetDataByNoSO(dt.get_txtNoSo());
        }

        modelItemsLoadMore = new ArrayList<>();

        if (employeeSalesProductDataList.size() > 0) {
            for (int i = 0; i < employeeSalesProductDataList.size(); i++) {
                ModelListview dt = new ModelListview();
                dt.set_id(employeeSalesProductDataList.get(i).get_txtBrandDetailGramCode());
                dt.set_name(employeeSalesProductDataList.get(i).get_txtProductBrandDetailGramName());
                dt.set_price((employeeSalesProductDataList.get(i).get_decHJD()));
                dt.set_NIK(employeeSalesProductDataList.get(i).get_txtNIK());
                if(tStockInHandDetailDataList!=null&&tStockInHandDetailDataList.size()>0){
                    for(tStockInHandDetailData dataSaveDetail : tStockInHandDetailDataList){
                        if(dt.get_id().equals(dataSaveDetail.get_txtCodeProduct())){
                            dt.set_value(Integer.valueOf(dataSaveDetail.get_intQty()));
                        }
                    }
                }
                modelItemsLoadMore.add(dt);
            }
        }

        modelItems = new ArrayList<>();

//        int count = modelItemsLoadMore.size()/4;

        for (int i = 0; i < 50; i++) {
            modelItems.add(modelItemsLoadMore.get(i));
        }

        footer = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_layout, null, false);
        listView.addFooterView(footer);
        Collections.sort(modelItems, ModelListview.StuRollno);
        dataAdapter = new MyAdapter(getContext(), modelItems);
        listView.setAdapter(dataAdapter);
        listView.setTextFilterEnabled(true);

        setListViewHeightBasedOnItems(listView);

        timer = new Timer();
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        loadMore(modelItems);
                    }
                }, 3000,3000);
    }

    private void loadMore(List<ModelListview> modelListview){
        int target = modelListview.size()+50;
        for(int i = modelListview.size()+1; i < target; i++){
            modelItems.add(modelItemsLoadMore.get(i));
        }
        if(modelItems.size()>modelItemsLoadMore.size()){
            timer.cancel();
        }
        dataAdapter.notifyDataSetChanged();
    }
}
