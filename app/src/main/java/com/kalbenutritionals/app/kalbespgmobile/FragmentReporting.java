package com.kalbenutritionals.app.kalbespgmobile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import addons.tableview.ReportComparators;
import addons.tableview.ReportTableDataAdapter;
import addons.tableview.SortableReportTableView;
import bl.mEmployeeAreaBL;
import bl.mTypeSubmissionMobileBL;
import bl.tCustomerBasedMobileDetailBL;
import bl.tCustomerBasedMobileDetailProductBL;
import bl.tCustomerBasedMobileHeaderBL;
import bl.tSalesProductDetailBL;
import bl.tSalesProductHeaderBL;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import library.salesforce.common.ReportTable;
import library.salesforce.common.mEmployeeAreaData;
import library.salesforce.common.mTypeSubmissionMobile;
import library.salesforce.common.tCustomerBasedMobileDetailData;
import library.salesforce.common.tCustomerBasedMobileDetailProductData;
import library.salesforce.common.tCustomerBasedMobileHeaderData;
import library.salesforce.common.tSalesProductDetailData;
import library.salesforce.common.tSalesProductHeaderData;

public class FragmentReporting extends Fragment {

    private SortableReportTableView ReportTableView;
    private Spinner spnTypeReport, spnOutlet;
    private Button btnHide, btnSearch;
    private RelativeLayout rlSearch;
    HashMap<String, String> arrOutlet;

    String spinnerSelected = null;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_report, container, false);

        ReportTableView = (SortableReportTableView) v.findViewById(R.id.tableView);
        spnTypeReport = (Spinner) v.findViewById(R.id.spnType);
        spnOutlet = (Spinner) v.findViewById(R.id.spnOutlet);
        btnHide = (Button) v.findViewById(R.id.btnHide);
        btnSearch = (Button) v.findViewById(R.id.btnsearch);
        rlSearch = (RelativeLayout) v.findViewById(R.id.rlSearch);

        btnHide.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public void onClick(View v) {
                if(btnHide.getText().equals("Hide")){
                    rlSearch.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            rlSearch.setVisibility(View.GONE);
                        }
                    });
                    btnHide.setText("Show");
                    btnSearch.setEnabled(false);
                }
                else{
                    rlSearch.setVisibility(View.VISIBLE);
                    rlSearch.animate().alpha(1.0f).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }
                    });
                    btnHide.setText("Hide");
                    btnSearch.setEnabled(true);
                }
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public void onClick(View v) {
                if(btnHide.getText().equals("Hide")){
                    rlSearch.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            rlSearch.setVisibility(View.GONE);
                        }
                    });
                    btnHide.setText("Show");
                    btnSearch.setEnabled(false);
                }
                else{
                    rlSearch.setVisibility(View.VISIBLE);
                    rlSearch.animate().alpha(1.0f).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }
                    });
                    btnHide.setText("Hide");
                    btnSearch.setEnabled(true);
                }
                spinnerSelected = spnTypeReport.getSelectedItem().toString();
                String outletcode = arrOutlet.get(spnOutlet.getSelectedItem().toString());
                generateReport(spinnerSelected, outletcode);
            }
        });

        List<mEmployeeAreaData> listOutlet = new ArrayList<>();

        listOutlet = new mEmployeeAreaBL().GetAllData();
        List<String> arrData=new ArrayList<>();
        List<String> arrDataOutlet=new ArrayList<>();

        arrOutlet = new HashMap<>();
        arrData.add(0, "Reso");
        arrData.add(1, "Customer Base");

        arrDataOutlet.add(0, "ALL OUTLET");
        int i = 1;

        for (mEmployeeAreaData outlet: listOutlet) {
            arrOutlet.put(outlet.get_txtOutletName(), outlet.get_txtOutletCode());
            arrDataOutlet.add(i, outlet.get_txtOutletName());
            i++;
        }

        arrOutlet.put("ALL OUTLET","ALLOUTLET");

        spnTypeReport.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
        spnOutlet.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrDataOutlet));

        return v;
    }

    private void generateReport(String spinnerSelected, String outletcode) {
        String[] header;
        SimpleTableHeaderAdapter simpleTableHeaderAdapter;
        List<ReportTable> reportList;
        int i;

        switch (spinnerSelected){
            case "Reso":
                header = new String[6];
                header[1] = "SO";
                header[2] = "Tot. Prd";
                header[3] = "Tot. Qty";
                header[4] = "Tot. Price";
                header[5] = "Outlet";

                ReportTableView.setColumnCount(header.length);

                simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
                simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
                simpleTableHeaderAdapter.setTextSize(14);
                simpleTableHeaderAdapter.setPaddingBottom(20);
                simpleTableHeaderAdapter.setPaddingTop(20);

                ReportTableView.setColumnComparator(1, ReportComparators.getNoSoComparator());
                ReportTableView.setColumnComparator(2, ReportComparators.getTotalProductComparator());
                ReportTableView.setColumnComparator(3, ReportComparators.getTotalItemComparator());
                ReportTableView.setColumnComparator(4, ReportComparators.getTotalPriceComparator());
                ReportTableView.setColumnComparator(5, ReportComparators.getStatusComparator());

                ReportTableView.setColumnWeight(1, 2);
                ReportTableView.setColumnWeight(2, 1);
                ReportTableView.setColumnWeight(3, 1);
                ReportTableView.setColumnWeight(4, 2);
                ReportTableView.setColumnWeight(5, 1);

                ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

                List<tSalesProductHeaderData> dt_so = new tSalesProductHeaderBL().getAllSalesProductHeaderByOutletCode(outletcode);
                reportList = new ArrayList<>();

                if(dt_so != null&&dt_so.size()>0){
                    for(tSalesProductHeaderData datas : dt_so ){
                        ReportTable rt = new ReportTable();

                        rt.set_report_type("Reso");
                        rt.set_no_so(datas.get_txtNoSo());
                        rt.set_total_product(datas.get_intSumItem());
                        rt.set_total_price(new clsMainActivity().convertNumberDec(Double.valueOf(datas.get_intSumAmount())));
                        rt.set_status(datas.get_OutletName());

                        List<tSalesProductDetailData> dt_detail = new tSalesProductDetailBL().GetDataByNoSO(datas.get_txtNoSo());

                        int total_item = 0;

                        for(i = 0; i < dt_detail.size(); i++){
                            total_item = total_item + Integer.parseInt(dt_detail.get(i).get_intQty());
                        }

                        rt.set_total_item(String.valueOf(total_item));
                        rt.set_total_product(String.valueOf(dt_detail.size()));

                        reportList.add(rt);
                    }
                } else {
                    new clsMainActivity().showCustomToast(getContext(), "No Data to Show", false);
                }

                ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));
                break;

            case "Customer Base":
                header = new String[7];
                header[1] = "Type";
                header[2] = "Name";
                header[3] = "Phone";
                header[4] = "Csmr";
                header[5] = "Prod";
                header[6] = "Qty";

                ReportTableView.setColumnCount(header.length);

                simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
                simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
                simpleTableHeaderAdapter.setTextSize(14);
                simpleTableHeaderAdapter.setPaddingBottom(20);
                simpleTableHeaderAdapter.setPaddingTop(20);

                ReportTableView.setColumnComparator(1, ReportComparators.getNoCbComparator());
                ReportTableView.setColumnComparator(2, ReportComparators.getCustomerNameComparator());
                ReportTableView.setColumnComparator(3, ReportComparators.getNoTelpComparator());
                ReportTableView.setColumnComparator(4, ReportComparators.getTotalMemberComparator());
                ReportTableView.setColumnComparator(5, ReportComparators.getTotalProductComparator());
                ReportTableView.setColumnComparator(6, ReportComparators.getTotalItemComparator());

                ReportTableView.setColumnWeight(1, 2);
                ReportTableView.setColumnWeight(2, 2);
                ReportTableView.setColumnWeight(3, 2);
                ReportTableView.setColumnWeight(4, 1);
                ReportTableView.setColumnWeight(5, 1);
                ReportTableView.setColumnWeight(6, 1);

                ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

                List<tCustomerBasedMobileHeaderData> data_cb = new tCustomerBasedMobileHeaderBL().getAllCustomerBasedMobileHeaderByOutletCodeReporting(outletcode);

                reportList = new ArrayList<>();

                if (data_cb!=null&&data_cb.size()>0){

                    for(tCustomerBasedMobileHeaderData datas : data_cb){

                        ReportTable rt = new ReportTable();

                        mTypeSubmissionMobile mtTypeSubmissionMobile = new mTypeSubmissionMobile();
                        mtTypeSubmissionMobile = new mTypeSubmissionMobileBL().getDataBySubmissionCode(datas.get_txtSubmissionCode());

                        rt.set_report_type("Customer Base");
                        rt.set_no_cb(mtTypeSubmissionMobile.get_txtNamaMasterData());
                        rt.set_customer_name(datas.get_txtNamaDepan());
                        rt.set_no_tlp(datas.get_txtTelp());

                        final List<tCustomerBasedMobileDetailData> dtListDetail = new tCustomerBasedMobileDetailBL().getAllDataByHeaderId(datas.get_intTrCustomerId());
                        rt.set_total_member(String.valueOf(dtListDetail.size()));


                        int totProduct = new tCustomerBasedMobileHeaderBL().getCountProductAllCustomerBased(datas.get_intTrCustomerId(), outletcode);
                        rt.set_total_product(String.valueOf(totProduct));


                        int count = 0;
                        for(int j=0;j<dtListDetail.size();j++){
                            final List<tCustomerBasedMobileDetailProductData> list = new tCustomerBasedMobileDetailProductBL().getDataByCustomerDetailId(dtListDetail.get(j).get_intTrCustomerIdDetail());
                            for(i=0 ; i < list.size(); i++){
                                int count2 = Integer.valueOf(list.get(i).get_txtProductBrandQty());
                                count+=count2;
                            }
                        }


                        rt.set_total_item(String.valueOf(count));

                        reportList.add(rt);
                    }
                } else {
                    new clsMainActivity().showCustomToast(getContext(), "No Data to Show", false);
                }

                ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));

                break;

            default:
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public class MyAdapter extends ArrayAdapter<String>
    {
        private List<String> arrayDataAdapyter;
        private Context Ctx;

        public List<String> getArrayDataAdapyter() {
            return arrayDataAdapyter;
        }
        public void setArrayDataAdapyter(List<String> arrayDataAdapyter) {
            this.arrayDataAdapyter = arrayDataAdapyter;
        }
        public Context getCtx() {
            return Ctx;
        }
        public void setCtx(Context ctx) {
            Ctx = ctx;
        }
        public MyAdapter(Context context, int textViewResourceId, List<String> objects)
        {
            super(context, textViewResourceId, objects);
            setCtx(context);
            setArrayDataAdapyter(objects);
            // TODO Auto-generated constructor stub
        }
        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent)
        {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater=getActivity().getLayoutInflater();
            View row=inflater.inflate(R.layout.custom_spinner, parent, false);
            if(getArrayDataAdapyter().size()>0){
                TextView label=(TextView)row.findViewById(R.id.tvTitle);
                //label.setText(arrData.get(position));
                label.setText(getArrayDataAdapyter().get(position));
                label.setTextColor(new Color().parseColor("#000000"));
                TextView sub=(TextView)row.findViewById(R.id.tvDesc);
                sub.setVisibility(View.INVISIBLE);
                sub.setVisibility(View.GONE);
                row.setBackgroundColor(new Color().parseColor("#FFFFFF"));
            }
            //sub.setText(mydata2[position]);
            return row;
        }

    }

}







