package com.kalbenutritionals.app.kalbespgmobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.fingerprint.FingerprintManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import bl.clsMainBL;
import bl.mCounterNumberBL;
import bl.mEmployeeSalesProductBL;
import bl.tAbsenUserBL;
import bl.tPurchaseOrderHeaderBL;
import bl.tSalesProductHeaderBL;
import library.salesforce.common.ModelListview;
import library.salesforce.common.clsHelper;
import library.salesforce.common.mEmployeeSalesProductData;
import library.salesforce.common.tAbsenUserData;
import library.salesforce.common.tPurchaseOrderDetailData;
import library.salesforce.common.tPurchaseOrderHeaderData;
import library.salesforce.common.tSalesProductHeaderData;
import library.salesforce.dal.enumCounterData;
import library.salesforce.dal.tPurchaseOrderDetailDA;

/**
 * Created by XSIS on 21/03/2017.
 */

public class FragmentAddPO extends FragmentPO implements View.OnClickListener {
TextView tv_tgl, tv_noPO;
    private EditText desc, searchProduct;
    private String noPO;
    private ArrayList<ModelListview> modelItems;
    private ArrayList<ModelListview> arrDataProd;
    ListView listView;
    int selectedId ;
    MyAdapter dataAdapter;
    public static ArrayList<ModelListview> arr = new ArrayList<>();
    FloatingActionButton fab;
    clsMainActivity _clsMainActivity;
    ProgressDialog progressDialog;

    View v;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        selectedId = 0;
        v = inflater.inflate(R.layout.view_po, container, false);

        tv_noPO = (TextView)v.findViewById(R.id.noOrder);
        tv_tgl =(TextView)v.findViewById(R.id.tglPO);
        desc = (EditText)v.findViewById(R.id.descOrder);
        searchProduct = (EditText)v.findViewById(R.id.searchPO);
        fab = (FloatingActionButton)v.findViewById(R.id.fabPO);

        listView = (ListView)v.findViewById(R.id.lvPO);
        _clsMainActivity = new clsMainActivity();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(desc.getWindowToken(),0);
        List<tPurchaseOrderHeaderData> dtLast = new tPurchaseOrderHeaderBL().getLastData();
        //List<tSalesProductHeaderData> dtLast = new tSalesProductHeaderBL().getLastData();
        if (dtLast == null || dtLast.size() == 0){
            noPO = new mCounterNumberBL().getData(enumCounterData.NoDataSO);

        }else {
            noPO = new mCounterNumberBL().getData(enumCounterData.NoDataSO);
            //List<tSalesProductHeaderData> dataFirstIsExist = new tSalesProductHeaderBL().getDataByNoSO(noPO);
            List<tPurchaseOrderHeaderData> dataFirstIsExist = new tPurchaseOrderHeaderBL().getDataByNoOrder(noPO);
            if(dataFirstIsExist.size() == 1){
                clsHelper _clsHelper = new clsHelper();
                String oldVersion = dtLast.get(0).get_txtNoOrder();
                noPO = _clsHelper.generateNewId(oldVersion, "-", "5");
            }else {
                noPO = new mCounterNumberBL().getData(enumCounterData.NoDataSO);
            }
        }
        tv_noPO.setText(noPO);
        String sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        tv_tgl.setText(sdf);
        Button btn = (Button)v.findViewById(R.id.btn_previewPO);
        btn.setOnClickListener(this);
        new GetAllemployeeSalesProductDataList().execute();
        final ScrollView scrollView = (ScrollView) v.findViewById(R.id.scroll_po);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                LinearLayout ly = (LinearLayout)v.findViewById(R.id.ln_PO);
                int coords[] = {0,0};
                ly.getLocationInWindow(coords);
                int absoluteTop = coords[1];
                if (absoluteTop < 90){
                    fab.setVisibility(View.VISIBLE);
                } else {
                    fab.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
                fab.setVisibility(View.INVISIBLE);
            }
        });
        searchProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            if (charSequence.length() == 0){
                dataAdapter.getFilter().filter(charSequence.toString());
                Collections.sort(modelItems, ModelListview.StuRollno);
            }else if (charSequence.length() > 0){
                dataAdapter.getFilter().filter(charSequence.toString());
            }else {
                Collections.sort(modelItems, ModelListview.StuRollno);
            }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchProduct.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(searchProduct) {

            @Override
            public boolean onDrawableClick() {
                InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(searchProduct.getWindowToken(),0);
                CharSequence s = searchProduct.getText();
                if (s.length()>0){
                    searchProduct.getText().clear();
                }
                return true;
            }
        });

        return v;
    }

    public void viewResoFragment(){
        Bundle bundle = new Bundle();
        bundle.putString("key_view", "View_PO");
        FragmentAddPO fragmentAddPO = new FragmentAddPO();
        fragmentAddPO.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, new FragmentPO()).commit();
//        Intent myIntent = new Intent(getContext(), MainMenu.class);
//        myIntent.putExtra("key_view","View PO");
//        getActivity().finish();
//        startActivity(myIntent);
//        return;
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId()) {
            case R.id.btn_previewPO:
                arrDataProd = new ArrayList<ModelListview>();
                for (int i = 0; i < modelItems.size(); i++) {
                    if (modelItems.get(i).get_value() > 0) {
                        ModelListview data = new ModelListview();
                        data.set_id(modelItems.get(i).get_id());
                        data.set_price(modelItems.get(i).get_price());
                        data.set_name(modelItems.get(i).get_name());
                        data.set_value(modelItems.get(i).get_value());
                        arrDataProd.add(data);
                    }
                }
                if (desc.getText().toString().equals("")) {
                    _clsMainActivity.showCustomToast(getActivity(), "Please fill Description...", false);

                } else if (arrDataProd.size()==0){
                    _clsMainActivity.showCustomToast(getActivity(), "Please fill Quantity Product...", false);

                } else if (arrDataProd.size()>0){
                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    final View promptView = layoutInflater.inflate(R.layout.activity_preview_po, null);

                    final TextView _tvNoSO = (TextView) promptView.findViewById(R.id.tvnoSOtbl);
                    final TextView _tvKet = (TextView) promptView.findViewById(R.id.tvkettbl);
                    _tvNoSO.setText(noPO);
                    _tvKet.setText(": " + desc.getText().toString());

                    arr = new ArrayList<ModelListview>();
                    arr = modelItems;

                    TableLayout tlb = (TableLayout) promptView.findViewById(R.id.tlProduct);
                    tlb.removeAllViews();

                    TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                    params.setMargins(1, 1, 1, 1);

                    TableRow tr = new TableRow(getContext());

                    TableLayout tl = new TableLayout(getContext());

                    String[] colTextHeader = {"Nama", "Qty", "Price", "Amount"};

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
                    for (ModelListview dt : arrDataProd) {

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
                        product.setText(dt.get_name());
                        tr.addView(product,params);

                        TextView qty = new TextView(getContext());
                        qty.setTextSize(12);
                        qty.setPadding(10, 10, 10, 10);
                        qty.setBackgroundColor(Color.parseColor("#f0f0f0"));
                        qty.setTextColor(Color.BLACK);
                        qty.setGravity(Gravity.RIGHT);
                        qty.setText(String.valueOf(dt.get_value()));
                        tr.addView(qty,params);

                        TextView price = new TextView(getContext());
                        price.setTextSize(12);
                        price.setPadding(10, 10, 10, 10);
                        price.setBackgroundColor(Color.parseColor("#f0f0f0"));
                        price.setTextColor(Color.BLACK);
                        price.setGravity(Gravity.RIGHT);
                        price.setText(new clsMainActivity().convertNumberDec(Double.valueOf(dt.get_price())));
                        tr.addView(price,params);

                        TextView amount = new TextView(getContext());
                        amount.setTextSize(12);
                        amount.setWidth(200);
                        amount.setPadding(10, 10, 10, 10);
                        amount.setBackgroundColor(Color.parseColor("#f0f0f0"));
                        amount.setTextColor(Color.BLACK);
                        amount.setGravity(Gravity.RIGHT);
                        double prc = Double.valueOf(dt.get_price());
                        double itm = Double.valueOf(dt.get_value());
                        qtyNum = prc * itm;
                        qtySum += qtyNum;
                        amount.setText(new clsMainActivity().convertNumberDec(qtyNum));
                        tr.addView(amount,params);

                        tl.addView(tr, tableRowParams);
                    }
                    tlb.addView(tl);

                    final TextView tv_item = (TextView) promptView.findViewById(R.id.tvItemtbl);
                    tv_item.setTypeface(null, Typeface.BOLD);
                    int sumItem = arrDataProd.size();
                    tv_item.setText(": " + String.valueOf(sumItem));

                    final TextView tv_amount = (TextView) promptView.findViewById(R.id.tvSumAmount);
                    tv_amount.setTypeface(null, Typeface.BOLD);
                    String nilai = new clsMainActivity().convertNumberDec(qtySum);
                    tv_amount.setText(": " + String.valueOf(nilai));

                    final TextView tv_status = (TextView) promptView.findViewById(R.id.tvStatus);
                    tv_status.setTypeface(null, Typeface.BOLD);
                    tv_status.setText(": Open");

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setView(promptView);
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
                                                    saveReso();
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

    private void saveReso() {
        int a = listView.getCount();
        String nik = null;
        List<String> item = new ArrayList<>();
        arrDataProd = new ArrayList<ModelListview>();
        double qntySum=0;
        double qntyNum;
        double value;
        double price;
        String result = "0";

        for (int i = 0; i < modelItems.size(); i++) {
            if (modelItems.get(i).get_value() > 0) {
                ModelListview data = new ModelListview();
                data.set_id(modelItems.get(i).get_id());
                data.set_name(modelItems.get(i).get_name());
                data.set_value(modelItems.get(i).get_value());
                nik = data.set_NIK(String.valueOf(modelItems.get(i).get_NIK()));
                price = Double.parseDouble(modelItems.get(i).get_price());
                value = Double.parseDouble(String.valueOf(modelItems.get(i).get_value()));
                qntyNum =  price * value;
                qntySum += qntyNum;
                result = new clsMainActivity().convertNumberDec2(qntySum);
                arrDataProd.add(data);
            }
        }

        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();

        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        clsMainActivity _clsMainActivity = new clsMainActivity();
        mEmployeeSalesProductData _mEmployeeSalesProductData = new mEmployeeSalesProductData();
        tAbsenUserData absenUserData = new tAbsenUserBL().getDataCheckInActive();

        dt.set_intId(new clsMainActivity().GenerateGuid());
        dt.set_txtNoOrder(tv_noPO.getText().toString());
        dt.set_dtDate(dateFormat.format(cal.getTime()));
        dt.set_OutletCode(absenUserData.get_txtOutletCode());
        dt.set_OutletName(absenUserData.get_txtOutletName());
        dt.set_txtKeterangan(desc.getText().toString());
        dt.set_intSumItem(String.valueOf(arrDataProd.size()));
        dt.set_intSumAmount(String.valueOf(result));
        dt.set_UserId(absenUserData.get_txtUserId());
        dt.set_txtRoleId(absenUserData.get_txtRoleId());
        dt.set_intSubmit("1");
        dt.set_intSync("0");
        dt.set_txtBranchCode(absenUserData.get_txtBranchCode());
        dt.set_txtBranchName(absenUserData.get_txtBranchName());
        dt.set_intIdAbsenUser(absenUserData.get_intId());
        dt.set_txtNIK(nik);

        new tPurchaseOrderHeaderBL().SaveData(dt);

        clsMainBL _clsMainBL = new clsMainBL();

        SQLiteDatabase _db=_clsMainBL.getDb();
        for (int i = 0; i < modelItems.size(); i++) {
            if (modelItems.get(i).get_value() > 0) {
                double prc = Double.valueOf(modelItems.get(i).get_price());
                double itm = Double.valueOf(modelItems.get(i).get_value());
                tPurchaseOrderDetailData dtDetail = new tPurchaseOrderDetailData();
                dtDetail.set_intId(_clsMainActivity.GenerateGuid());
                dtDetail.set_dtDate(dateFormat.format(cal.getTime()));
                dtDetail.set_intPrice(modelItems.get(i).get_price());
                dtDetail.set_intQty(String.valueOf(modelItems.get(i).get_value()));
                dtDetail.set_txtCodeProduct(modelItems.get(i).get_id());
                dtDetail.set_txtKeterangan(desc.getText().toString());
                dtDetail.set_txtNameProduct(String.valueOf(modelItems.get(i).get_name()));
                dtDetail.set_intTotal(new clsMainActivity().convertNumberDec2(prc*itm));
                dtDetail.set_txtNoOrder(tv_noPO.getText().toString());
                dtDetail.set_intActive("1");
                dtDetail.set_txtNIK(modelItems.get(i).get_NIK());
                new tPurchaseOrderDetailDA(_db).SaveDatatPurchaseOrderDetailData(_db, dtDetail);
            }

        }

        _clsMainActivity.showCustomToast(getActivity(), "Saved", true);
    }

    public class MyAdapter extends BaseAdapter implements Filterable {

        public ArrayList<ModelListview> mOriginalValues; // Original Values
        public ArrayList<ModelListview> mDisplayedValues;    // Values to be displayed


        public MyAdapter(Context context, ArrayList<ModelListview> mProductArrayList) {
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

        private class ViewHolder{
            EditText code;
            TextView name;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

           FragmentAddPO.MyAdapter.ViewHolder holder = null;

            android.util.Log.v("ConvertView", String.valueOf(position));

            if (convertView == null)
            {

                LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                convertView = vi.inflate(R.layout.activity_listview, null);

                holder = new FragmentAddPO.MyAdapter.ViewHolder();

                holder.name = (TextView) convertView.findViewById(R.id.textViewproduk);
                holder.code = (EditText) convertView.findViewById(R.id.editText4);

                convertView.setTag(holder);

                final FragmentAddPO.MyAdapter.ViewHolder finalHolder = holder;
                holder.code.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        //Collections.sort(mDisplayedValues, ModelListview.StuRollno);
                        EditText et = finalHolder.code;
                        for(int i = 0; i < mOriginalValues.size(); i++) {
                            if(finalHolder.name.getText().equals(mOriginalValues.get(i).get_id() + "\n" + mOriginalValues.get(i).get_name())){
                                if(s.length()==0){
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
                        //Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                        EditText etx = finalHolder.code;
                        for(int i=0;i< mOriginalValues.size();i++){
                            if(finalHolder.name.getText().equals(mOriginalValues.get(i).get_id() + "\n" + mOriginalValues.get(i).get_name())) {
                                if(hasFocus){
                                    if (mOriginalValues.get(i).get_value()==0)
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

            }
            else
            {
                holder = (FragmentAddPO.MyAdapter.ViewHolder) convertView.getTag();
            }


            final ModelListview state = mDisplayedValues.get(position);
            final ModelListview state2 = mOriginalValues.get(position);

//            if (state.get_value()==0){
//                holder.name.setText(state.get_id()+"\n"+state.get_name());
//                holder.code.setText("");
//            } else {
            holder.name.setText(state.get_id()+"\n"+state.get_name());
            holder.code.setText(String.valueOf(state.get_value()));
//            final ViewHolder finalHolder1 = holder;

//            }

            holder.name.setTag(state);

            return convertView;
        }

        public void Sortings(){
            Collections.sort(mDisplayedValues, ModelListview.StuRollno);
            dataAdapter.notifyDataSetChanged();
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {

                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence constraint,FilterResults results) {

                    mDisplayedValues = (ArrayList<ModelListview>) results.values; // has the filtered values
                    notifyDataSetChanged();  // notifies the data with new filtered values
                }

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                    ArrayList<ModelListview> FilteredArrList = new ArrayList<ModelListview>();

                    if (mOriginalValues == null) {
                        mOriginalValues = new ArrayList<ModelListview>(mDisplayedValues); // saves the original data in mOriginalValues
                    }

                    /********
                     *
                     *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                     *  else does the Filtering and returns FilteredArrList(Filtered)
                     *
                     ********/
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
                                FilteredArrList.add(dt);}
                        }
                        // set the Filtered result to return
                        results.count = FilteredArrList.size();
                        results.values = FilteredArrList;
                    }
                    return results;
                }
            };
            return filter;
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

        @Override
        protected ArrayList<ModelListview> doInBackground(ArrayList<ModelListview>... params) {
            //android.os.Debug.waitForDebugger();

            List<mEmployeeSalesProductData> employeeSalesProductDataList = new mEmployeeSalesProductBL().GetAllData();

            modelItems = new ArrayList<ModelListview>();

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

            dataAdapter = new FragmentAddPO.MyAdapter(getContext(), modelItems);
            listView.setAdapter(dataAdapter);
            listView.setTextFilterEnabled(true);

            setListViewHeightBasedOnItems(listView);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            }, 4000);
        }
    }
}

