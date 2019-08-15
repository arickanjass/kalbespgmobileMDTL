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
import android.support.design.widget.TextInputLayout;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.kalbenutritionals.app.kalbespgmobile.DrawableClickListener;
import com.kalbenutritionals.app.kalbespgmobile.MainMenu;
import com.kalbenutritionals.app.kalbespgmobile.R;
import com.kalbenutritionals.app.kalbespgmobile.clsMainActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import addons.adapter.AdapterListProductCustomerBased;
import addons.adapter.AdapterListProductReso;
import bl.clsHelperBL;
import bl.clsMainBL;
import bl.mCounterNumberBL;
import bl.mEmployeeSalesProductBL;
import bl.mProductCompetitorBL;
import bl.mProductPICBL;
import bl.mProductSPGBL;
import bl.mUserLOBBL;
import bl.tCustomerBasedMobileDetailProductBL;
import bl.tSalesProductDetailBL;
import bl.tSalesProductHeaderBL;
import bl.tUserLoginBL;
import library.spgmobile.common.ModelListview;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.clsSwipeList;
import library.spgmobile.common.mEmployeeSalesProductData;
import library.spgmobile.common.mProductCompetitorData;
import library.spgmobile.common.mProductPICData;
import library.spgmobile.common.mProductSPGData;
import library.spgmobile.common.mUserLOBData;
import library.spgmobile.common.tCustomerBasedMobileDetailData;
import library.spgmobile.common.tCustomerBasedMobileDetailProductData;
import library.spgmobile.common.tSalesProductDetailData;
import library.spgmobile.common.tSalesProductHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.visitplanAbsenData;
import library.spgmobile.dal.enumCounterData;
import library.spgmobile.dal.tSalesProductDetailDA;

public class FragmentAddResoSPGEnhance extends Fragment implements View.OnClickListener {
    TextView tv_date;
    TextView tv_noso;

    private EditText edketerangan, searchProduct;
    private String noso;
    private ArrayList<ModelListview> modelItems;
    private ArrayList<ModelListview> arrdataPriv;
    MyAdapter dataAdapter;
    ListView listView;
    int selectedId;
    public static ArrayList<ModelListview> arr = new ArrayList<>();

    ProgressDialog progressDialog;
    View v;
    FloatingActionButton fab;
    clsMainActivity _clsMainActivity;
    List<mUserLOBData> mUserLOBDataList;
    List<tSalesProductDetailData> dtListDetailProduct;
    List<tSalesProductDetailData> dtListDetailProductSave;
    private ArrayList<clsSwipeList> swipeListProduct = new ArrayList<>();
    AdapterListProductReso AdapterProduct;
    ListView lvProduct;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        selectedId = 0;
        v = inflater.inflate(R.layout.coordinator_layout_enhance, container, false);
        tv_noso = (TextView) v.findViewById(R.id.txtNoreso);
        tv_date = (TextView) v.findViewById(R.id.txtviewDate);
        edketerangan = (EditText) v.findViewById(R.id.etKeterangan);
        searchProduct = (EditText) v.findViewById(R.id.searchProduct);
        fab = (FloatingActionButton) v.findViewById(R.id.fab);

        mUserLOBDataList = new ArrayList<>();
        mUserLOBDataList = new mUserLOBBL().GetAllData();

        dtListDetailProduct = new ArrayList<>();
        dtListDetailProductSave = new ArrayList<>();

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
                if (charSequence.toString().matches("[a-zA-Z0-9.\\- ]+")) {
                    return charSequence;
                }
                return "";
            }
        };

        InputFilter inputFilter_edketerangan2 = new InputFilter.AllCaps();

        edketerangan.setFilters(new InputFilter[]{inputFilter_edketerangan1, inputFilter_edketerangan2});

        List<tSalesProductHeaderData> dtLast = new tSalesProductHeaderBL().getLastData();
        if (dtLast == null || dtLast.size() == 0) {
            noso = new mCounterNumberBL().getData(enumCounterData.NoDataSO);

        } else {
            noso = new mCounterNumberBL().getData(enumCounterData.NoDataSO);
            List<tSalesProductHeaderData> dataFirstIsExist = new tSalesProductHeaderBL().getDataByNoSO(noso);
            if (dataFirstIsExist.size() == 1) {
                clsHelper _clsHelper = new clsHelper();
                String oldVersion = dtLast.get(0).get_txtNoSo();
                noso = _clsHelper.generateNewId(oldVersion, "-", "5");
            } else {
                noso = new mCounterNumberBL().getData(enumCounterData.NoDataSO);
            }
        }
        tv_noso.setText(noso);

        String timeStamp = new SimpleDateFormat("dd/MM/yyyy",
                Locale.getDefault()).format(new Date());
        tv_date.setText(timeStamp);
        Button btn_preview = (Button) v.findViewById(R.id.btnPreviewReso);
        Button btn_add = (Button) v.findViewById(R.id.btnAddProduct);
        btn_add.setOnClickListener(this);
        btn_preview.setOnClickListener(this);

        listView = (ListView) v.findViewById(R.id.lvProduk);

//        new GetAllemployeeSalesProductDataList().execute();

        final ScrollView scrollView = (ScrollView) v.findViewById(R.id.scroll_reso);


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

        searchProduct.setVisibility(View.GONE);

        return v;
    }

    public void viewResoFragment() {
        Intent myIntent = new Intent(getContext(), MainMenu.class);
        myIntent.putExtra("key_view", "View Reso SPG Enhance");
        getActivity().finish();
        startActivity(myIntent);
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId()) {
            case R.id.btnPreviewReso:
//                int jumlah_data = new tSalesProductDetailBL().getContactCount(tv_noso.getText().toString());
                final List<tSalesProductDetailData> _tSalesProductDetailData = new tSalesProductDetailBL().GetDataByNoSO(tv_noso.getText().toString());


                if (edketerangan.getText().toString().equals("")) {
                    _clsMainActivity.showCustomToast(getActivity(), "Please fill Description...", false);

                } else
                if (_tSalesProductDetailData!=null&&_tSalesProductDetailData.size()==0){
                    _clsMainActivity.showCustomToast(getActivity(), "Please fill Quantity Product...", false);

                } else if (_tSalesProductDetailData!=null&&_tSalesProductDetailData.size()>0){
                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    final View promptView = layoutInflater.inflate(R.layout.activity_preview_so, null);

                    final TextView _tvNoSO = (TextView) promptView.findViewById(R.id.tvnoSOtbl);
                    final TextView _tvKet = (TextView) promptView.findViewById(R.id.tvkettbl);
                    _tvNoSO.setText(String.format(": %s", noso));
                    _tvKet.setText(String.format(": %s", edketerangan.getText().toString()));

                    arr = new ArrayList<>();
                    arr = modelItems;

                    TableLayout tlb = (TableLayout) promptView.findViewById(R.id.tlProduct);
                    tlb.removeAllViews();

                    TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                    params.setMargins(1, 1, 1, 1);

                    TableRow tr = new TableRow(getContext());

                    TableLayout tl = new TableLayout(getContext());

                    String[] colTextHeader = {"Name", "Qty", "Price", "Amount"};

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
                    for (tSalesProductDetailData dt : _tSalesProductDetailData) {

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
                        product.setWidth(200);
                        product.setPadding(10, 10, 10, 10);
                        product.setBackgroundColor(Color.parseColor("#f0f0f0"));
                        product.setTextColor(Color.BLACK);
                        product.setText(dt.get_txtNameProduct());
                        tr.addView(product,params);

                        TextView qty = new TextView(getContext());
                        qty.setTextSize(12);
                        qty.setPadding(10, 10, 10, 10);
                        qty.setBackgroundColor(Color.parseColor("#f0f0f0"));
                        qty.setTextColor(Color.BLACK);
                        qty.setGravity(Gravity.RIGHT);
                        qty.setText(String.valueOf(dt.get_intQty()));
                        tr.addView(qty,params);

                        TextView price = new TextView(getContext());
                        price.setTextSize(12);
                        price.setPadding(10, 10, 10, 10);
                        price.setBackgroundColor(Color.parseColor("#f0f0f0"));
                        price.setTextColor(Color.BLACK);
                        price.setGravity(Gravity.RIGHT);
                        price.setText(new clsMainActivity().convertNumberDec(Double.valueOf(dt.get_intPrice())));
                        tr.addView(price,params);

                        TextView amount = new TextView(getContext());
                        amount.setTextSize(12);
                        amount.setWidth(200);
                        amount.setPadding(10, 10, 10, 10);
                        amount.setBackgroundColor(Color.parseColor("#f0f0f0"));
                        amount.setTextColor(Color.BLACK);
                        amount.setGravity(Gravity.RIGHT);
                        double prc = Double.valueOf(dt.get_intPrice());
                        double itm = Double.valueOf(dt.get_intQty());
                        qtyNum = prc * itm;
                        qtySum += qtyNum;
                        amount.setText(new clsMainActivity().convertNumberDec(qtyNum));
                        tr.addView(amount,params);

                        tl.addView(tr, tableRowParams);
                    }
                    tlb.addView(tl);

                    final TextView tv_item = (TextView) promptView.findViewById(R.id.tvItemtbl);
                    tv_item.setTypeface(null, Typeface.BOLD);
                    int sumItem = _tSalesProductDetailData.size();
                    tv_item.setText(String.format(": %s", String.valueOf(sumItem)));

                    final TextView tv_amount = (TextView) promptView.findViewById(R.id.tvSumAmount);
                    tv_amount.setTypeface(null, Typeface.BOLD);
                    final String nilai = new clsMainActivity().convertNumberDec(qtySum);
                    tv_amount.setText(String.format(": %s", String.valueOf(nilai)));

                    final TextView tv_status = (TextView) promptView.findViewById(R.id.tvStatus);
                    tv_status.setTypeface(null, Typeface.BOLD);
                    tv_status.setText(": Open");

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setView(promptView);
                    final double finalQtySum = qtySum;
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("Submit",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                                            alertDialog.setTitle("Confirm");
                                            alertDialog.setMessage("Are you sure?");
                                            alertDialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    saveReso(_tSalesProductDetailData, finalQtySum);
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
//                                            if (searchProduct.getText().length()>0){
//                                                Collections.sort(modelItems, ModelListview.StuRollno);
//                                            } else {
//                                                Collections.sort(modelItems, ModelListview.StuRollno);
//                                                dataAdapter.notifyDataSetChanged();
//                                            }

                                            dialog.cancel();
                                        }
                                    });
                    final AlertDialog alertD = alertDialogBuilder.create();
                    alertD.show();
                    break;
                }
                break;
            case R.id.btnAddProduct:
//                new clsMainActivity().showCustomToast(getActivity(),"Tes", true);
                tSalesProductDetailData data = new tSalesProductDetailData();
                if (edketerangan.getText().toString().equals("")) {
                    _clsMainActivity.showCustomToast(getActivity(), "Please fill Description...", false);
                } else {
                    popUpAddProduct(data);
                }
                break;
        }
    }

    private void saveReso(List<tSalesProductDetailData> arrdataPriv, double qntySum ) {
//        String nik = null;
//        arrdataPriv = new ArrayList<>();
//        double qntySum = 0;
//        double qntyNum;
//        double value;
//        double price;
//        String result = "0";
//
//        for (int i = 0; i < modelItems.size(); i++) {
//            if (modelItems.get(i).get_value() > 0) {
//                ModelListview data = new ModelListview();
//                data.set_id(modelItems.get(i).get_id());
//                data.set_name(modelItems.get(i).get_name());
//                data.set_value(modelItems.get(i).get_value());
//                nik = data.set_NIK(String.valueOf(modelItems.get(i).get_NIK()));
//                price = Double.parseDouble(modelItems.get(i).get_price());
//                value = Double.parseDouble(String.valueOf(modelItems.get(i).get_value()));
//                qntyNum = price * value;
//                qntySum += qntyNum;
//                result = new clsMainActivity().convertNumberDec2(qntySum);
//                arrdataPriv.add(data);
//            }
//        }

        String result = "0";

        result = new clsMainActivity().convertNumberDec2(qntySum);

        tSalesProductHeaderData dt = new tSalesProductHeaderData();

        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        clsMainActivity _clsMainActivity = new clsMainActivity();
//        tAbsenUserData absenUserData = new tAbsenUserBL().getDataCheckInActive();
        visitplanAbsenData _viAbsenData = new visitplanAbsenData();
        _viAbsenData = new clsHelperBL().getDataCheckInActive();

        dt.set_intId(new clsMainActivity().GenerateGuid());
        dt.set_txtNoSo(tv_noso.getText().toString());
        dt.set_dtDate(dateFormat.format(cal.getTime()));
        dt.set_OutletCode(_viAbsenData.get_txtOutletCode());
        dt.set_OutletName(_viAbsenData.get_txtOutletName());
        dt.set_txtKeterangan(edketerangan.getText().toString());
        dt.set_intSumItem(String.valueOf(arrdataPriv.size()));
        dt.set_intSumAmount(String.valueOf(result));
        dt.set_UserId(_viAbsenData.get_txtUserId());
        dt.set_txtRoleId(_viAbsenData.get_txtRoleId());
        dt.set_intSubmit("1");
        dt.set_intSync("0");
        dt.set_txtBranchCode(_viAbsenData.get_txtBranchCode());
        dt.set_txtBranchName(_viAbsenData.get_txtBranchName());
        dt.set_intIdAbsenUser(_viAbsenData.get_intId());
        dt.set_txtNIK(arrdataPriv.get(0).get_txtNIK());

        new tSalesProductHeaderBL().SaveData(dt);
        _clsMainActivity.showCustomToast(getActivity(), "Saved", true);
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
            progressDialog.setIndeterminate(false); //ukur berapa persen, false maka not do
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @SafeVarargs
        @Override
        protected final ArrayList<ModelListview> doInBackground(ArrayList<ModelListview>... params) {
            //android.os.Debug.waitForDebugger();

            List<mEmployeeSalesProductData> employeeSalesProductDataList = new mEmployeeSalesProductBL().GetAllDataByKN(mUserLOBDataList);

            modelItems = new ArrayList<>();

            if (employeeSalesProductDataList.size() > 0) {
                for (int i = 0; i < employeeSalesProductDataList.size(); i++) {
                    ModelListview dt = new ModelListview();
                    dt.set_id(employeeSalesProductDataList.get(i).get_txtBrandDetailGramCode());
                    dt.set_name(employeeSalesProductDataList.get(i).get_txtProductBrandDetailGramName());
                    dt.set_price((employeeSalesProductDataList.get(i).get_decHJD()));
                    dt.set_NIK(employeeSalesProductDataList.get(i).get_txtNIK());
                    modelItems.add(dt);
                }
            }

            return modelItems;
        }

        @Override
        protected void onPostExecute(ArrayList<ModelListview> s) {

            if(modelItems.size()>0){
                dataAdapter = new MyAdapter(getContext(), modelItems);
                listView.setAdapter(dataAdapter);
                listView.setTextFilterEnabled(true);

                setListViewHeightBasedOnItems(listView);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 4000);
            } else {
                new clsMainActivity().showCustomToast(getContext(),"Please re-donwload Product First",false);
                progressDialog.dismiss();
            }
        }
    }

    private String txtNoSO;


    private void popUpAddProduct(final tSalesProductDetailData dataDetail) {
        final tUserLoginData _tUserLoginData = new tUserLoginBL().getUserActive();
        dtListDetailProduct = new ArrayList<>();
        dtListDetailProduct = new tSalesProductDetailBL().GetDataByNoSO(tv_noso.getText().toString());

        if(dtListDetailProduct==null){
            dtListDetailProduct = new ArrayList<>();
        }

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.popup_addproductreso, null);
        final Button btnAddProduct = (Button) promptView.findViewById(R.id.btn_add_product);
        final EditText qty = (EditText) promptView.findViewById(R.id.qty);
        final HashMap<String, String> HMProduct = new HashMap<>();
        final HashMap<String, String> HMProductHjd = new HashMap<>();
        final TextInputLayout txtInputLayoutQty = (TextInputLayout) promptView.findViewById(R.id.input_layout_qty);


        final Spinner spnKalbeProduct = (Spinner) promptView.findViewById(R.id.spn_kn_product);

        List<String> dataProductKalbe = new ArrayList<>();
        final List<mEmployeeSalesProductData> mProductSPGDataList = new mEmployeeSalesProductBL().GetAllDataByKN(mUserLOBDataList);

        final java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final Calendar cal = Calendar.getInstance();

            if (mProductSPGDataList.size() > 0) {
                dataProductKalbe.add("Select Product");
                for (mEmployeeSalesProductData dt : mProductSPGDataList) {
                    dataProductKalbe.add(dt.get_txtProductBrandDetailGramName());
                    HMProduct.put(dt.get_txtProductBrandDetailGramName(), dt.get_txtBrandDetailGramCode());
                    HMProduct.put(dt.get_txtBrandDetailGramCode(), dt.get_txtProductDetailCode());
//                    HMProduct.put(dt.get_txtProductDetailCode(), dt.get_decHJD());
                }
                for (mEmployeeSalesProductData dt : mProductSPGDataList) {
//                    dataProductKalbe.add(dt.get_txtProductBrandDetailGramName());
                    HMProductHjd.put(dt.get_txtProductBrandDetailGramName(), dt.get_decHJD());
                    HMProductHjd.put(dt.get_txtBrandDetailGramCode(), dt.get_txtProductDetailCode());
//                    HMProduct.put(dt.get_txtProductDetailCode(), dt.get_decHJD());
                }
            }

        ArrayAdapter<String> adapterKalbeProduct = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, dataProductKalbe);
        spnKalbeProduct.setAdapter(adapterKalbeProduct);
        spnKalbeProduct.setSelection(0);
        spnKalbeProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean val = true;
                if(dtListDetailProduct!=null){
                    for (int i = 0; i < dtListDetailProduct.size(); i++) {
                        if (dtListDetailProduct.get(i).get_txtNameProduct().equals(spnKalbeProduct.getSelectedItem().toString())) {
                            val = false;
                        }

                    }
                }

                if (!spnKalbeProduct.getSelectedItem().equals("Select Product")) {
                    if (val) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(qty.getWindowToken(), 0);
                        String selectedOneKNProduct = spnKalbeProduct.getSelectedItem().toString();
                        String qtyProduct;
                        qtyProduct = qty.getText().toString();
                        new clsMainActivity().removeErrorMessage(txtInputLayoutQty);
                        if (qtyProduct.length() == 0) {
                            new clsMainActivity().setErrorMessage(getContext(), txtInputLayoutQty, qty, "Qty cannot empty");
                            spnKalbeProduct.setSelection(spnKalbeProduct.getSelectedItemPosition());
                        } else if (Integer.parseInt(qtyProduct) == 0) {
                            new clsMainActivity().setErrorMessage(getContext(), txtInputLayoutQty, qty, "Qty cannot 0");
                            spnKalbeProduct.setSelection(spnKalbeProduct.getSelectedItemPosition());
                        } else {
                            new clsMainActivity().removeErrorMessage(txtInputLayoutQty);
                            tSalesProductDetailData data = new tSalesProductDetailData();

                            clsMainBL _clsMainBL = new clsMainBL();

                            SQLiteDatabase _db = _clsMainBL.getDb();

                            String price = HMProductHjd.get(selectedOneKNProduct);

                            double prc = Double.valueOf(HMProductHjd.get(selectedOneKNProduct));
                            double itm = Double.valueOf(qtyProduct);

                            data.set_intId(_clsMainActivity.GenerateGuid());
                            data.set_dtDate(dateFormat.format(cal.getTime()));
                            data.set_intPrice(price);
                            data.set_intQty(qtyProduct);
                            data.set_txtCodeProduct(HMProduct.get(selectedOneKNProduct));
                            data.set_txtKeterangan(edketerangan.getText().toString());
                            data.set_txtNameProduct(selectedOneKNProduct);
                            data.set_intTotal(new clsMainActivity().convertNumberDec2(prc * itm));
                            data.set_txtNoSo(tv_noso.getText().toString());
                            data.set_intActive("1");
                            data.set_txtNIK(_tUserLoginData.get_TxtEmpId());

                            new tSalesProductDetailDA(_db).SaveDatatSalesProductDetailData(_db, data);

                            dtListDetailProduct.add(data);

                            _db.close();

                            if (spnKalbeProduct.getSelectedItemPosition() > 0) {
                                spnKalbeProduct.setSelection(0);
                            }
                            qty.setText("");
                            spnKalbeProduct.setSelection(0);
                            setTableProduct(dtListDetailProduct,promptView);
                        }
                    } else {
                        new clsMainActivity().showCustomToast(getContext(), "Sorry, you cannot add same product", false);
                    }
                } else {
                    new clsMainActivity().showCustomToast(getContext(), "Please Choose Product first", false);
                }

            }
        });

        setTableProduct(dtListDetailProduct, promptView);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();

    }

    private void setTableProduct(List<tSalesProductDetailData> dtDetail,final View v) {
        clsSwipeList swplist;

        swipeListProduct.clear();

        if(dtListDetailProduct!=null){
            for (int i = 0; i < dtListDetailProduct.size(); i++) {
                swplist = new clsSwipeList();
                swplist.set_txtId(dtListDetailProduct.get(i).get_intId());
                swplist.set_txtTitle(dtListDetailProduct.get(i).get_txtNameProduct());
                swplist.set_intPIC(dtListDetailProduct.get(i).get_intQty());
                swipeListProduct.add(swplist);
            }
        }

        AdapterProduct = clsMainActivity.setListProductReso(getActivity().getApplicationContext(), swipeListProduct);

        lvProduct = (ListView) v.findViewById(R.id.listViewProduct);
        lvProduct.setAdapter(AdapterProduct);
        lvProduct.setEmptyView(v.findViewById(R.id.LayoutEmpty));
    }
}

