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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import addons.tableview.ReportComparators;
import addons.tableview.ReportTableDataAdapter;
import addons.tableview.SortableReportTableView;
import bl.KoordinasiOutletBL;
import bl.mCountConsumerMTDBL;
import bl.mDownloadMasterData_mobileBL;
import bl.mEmployeeAreaBL;
import bl.mMenuBL;
import bl.mTypeSubmissionMobileBL;
import bl.tAbsenUserBL;
import bl.tActivityBL;
import bl.tActivityMobileBL;
import bl.tCustomerBasedMobileDetailBL;
import bl.tCustomerBasedMobileDetailProductBL;
import bl.tCustomerBasedMobileHeaderBL;
import bl.tGroupQuestionMappingBL;
import bl.tJawabanUserBL;
import bl.tJawabanUserHeaderBL;
import bl.tKemasanRusakDetailBL;
import bl.tKemasanRusakHeaderBL;
import bl.tOverStockDetailBL;
import bl.tOverStockHeaderBL;
import bl.tPlanogramMobileBL;
import bl.tPurchaseOrderDetailBL;
import bl.tPurchaseOrderHeaderBL;
import bl.tSalesProductDetailBL;
import bl.tSalesProductHeaderBL;
import bl.tSalesProductQuantityDetailBL;
import bl.tSalesProductQuantityHeaderBL;
import bl.tStockInHandDetailBL;
import bl.tStockInHandHeaderBL;
import bl.tTidakSesuaiPesananHeaderBL;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import library.spgmobile.common.KoordinasiOutletData;
import library.spgmobile.common.ReportTable;
import library.spgmobile.common.mCountConsumerMTDData;
import library.spgmobile.common.mDownloadMasterData_mobileData;
import library.spgmobile.common.mEmployeeAreaData;
import library.spgmobile.common.mMenuData;
import library.spgmobile.common.mPertanyaanData;
import library.spgmobile.common.mTypeSubmissionMobile;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tActivityMobileData;
import library.spgmobile.common.tCustomerBasedMobileDetailData;
import library.spgmobile.common.tCustomerBasedMobileDetailProductData;
import library.spgmobile.common.tCustomerBasedMobileHeaderData;
import library.spgmobile.common.tGroupQuestionMappingData;
import library.spgmobile.common.tJawabanUserData;
import library.spgmobile.common.tJawabanUserHeaderData;
import library.spgmobile.common.tKemasanRusakDetailData;
import library.spgmobile.common.tKemasanRusakHeaderData;
import library.spgmobile.common.tOverStockDetailData;
import library.spgmobile.common.tOverStockHeaderData;
import library.spgmobile.common.tPlanogramMobileData;
import library.spgmobile.common.tPurchaseOrderDetailData;
import library.spgmobile.common.tPurchaseOrderHeaderData;
import library.spgmobile.common.tSalesProductDetailData;
import library.spgmobile.common.tSalesProductHeaderData;
import library.spgmobile.common.tSalesProductQuantityDetailData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tStockInHandDetailData;
import library.spgmobile.common.tStockInHandHeaderData;
import library.spgmobile.common.tTidakSesuaiPesananHeaderData;
import library.spgmobile.common.tUserLoginData;

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
        List<String> arrDataOutlet=new ArrayList<>();

        List<mDownloadMasterData_mobileData> mDownloadMasterData_mobileDataList = new ArrayList<>();

        mDownloadMasterData_mobileDataList = new mDownloadMasterData_mobileBL().GetAllData();

        List<String> arrData=new ArrayList<>();
//        arrData.add(0, "Reso");
//        arrData.add(1, "Customer Base");

// add elements to al, including duplicates
        Set<String> hs = new HashSet<>();
        hs.addAll(arrData);
        arrData.clear();
        arrData.addAll(hs);

        String intParentID = new mMenuBL().getIntParentID();
        List<mMenuData> menu = new ArrayList<>();

        if(intParentID != null){
            menu = new mMenuBL().getDatabyParentId(intParentID);
        }

        if(menu.size()>0){
            for(mMenuData _mMenuData : menu){
                arrData.add(_mMenuData.get_TxtMenuName());
                if(_mMenuData.get_TxtMenuName().contains("Customer Base")){
                    arrData.add("Customer Base MTD");
                }
            }
        }

        arrOutlet = new HashMap<>();

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

        if(spinnerSelected.contains("Reso")){
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
                        if(datas.get_OutletName().equals("null")){
                            rt.set_status("-");
                        }

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
        } else if(spinnerSelected.contains("Stock on Hand")){
            header = new String[6];
            header[1] = "NO";
            header[2] = "Tot. Prd";
            header[3] = "Tot. Qty";
//            header[4] = "Tot. Price";
            header[4] = "Outlet";
            header[5] = "";

            ReportTableView.setColumnCount(header.length);

            simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
            simpleTableHeaderAdapter.setTextSize(14);
            simpleTableHeaderAdapter.setPaddingBottom(20);
            simpleTableHeaderAdapter.setPaddingTop(20);

            ReportTableView.setColumnComparator(1, ReportComparators.getNoSoComparator());
            ReportTableView.setColumnComparator(2, ReportComparators.getTotalProductComparator());
            ReportTableView.setColumnComparator(3, ReportComparators.getTotalItemComparator());
//            ReportTableView.setColumnComparator(4, ReportComparators.getTotalPriceComparator());
            ReportTableView.setColumnComparator(4, ReportComparators.getStatusComparator());
            ReportTableView.setColumnComparator(5, ReportComparators.getviewDetailComparator());

            ReportTableView.setColumnWeight(1, 2);
            ReportTableView.setColumnWeight(2, 1);
            ReportTableView.setColumnWeight(3, 1);
            ReportTableView.setColumnWeight(4, 1);
            ReportTableView.setColumnWeight(5, 1);

            ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

            List<tStockInHandHeaderData> dt_so = new tStockInHandHeaderBL().getAllSalesProductHeaderByOutletCode(outletcode);
            reportList = new ArrayList<>();

            if(dt_so != null&&dt_so.size()>0){
                for(tStockInHandHeaderData datas : dt_so ){
                    ReportTable rt = new ReportTable();

                    rt.set_report_type("Stock In Hand");
                    rt.set_no_so(datas.get_txtNoSo());
                    rt.set_total_product(datas.get_intSumItem());
                    rt.set_total_price(new clsMainActivity().convertNumberDec(Double.valueOf(datas.get_intSumAmount())));
                    rt.set_status(datas.get_OutletName());
                    rt.set_view_detail("View Detail");
                    rt.set_dummy("Stock on Hand");

                    List<tStockInHandDetailData> dt_detail = new tStockInHandDetailBL().GetDataByNoSO(datas.get_txtNoSo());

                    int total_item = 0;

                    for(i = 0; i < dt_detail.size(); i++){
                        total_item = total_item + Integer.parseInt(dt_detail.get(i).get_intQty());
                    }

                    rt.set_total_item(String.valueOf(total_item)+ " pcs");
                    rt.set_total_product(String.valueOf(dt_detail.size()));

                    reportList.add(rt);
                }
            } else {
                new clsMainActivity().showCustomToast(getContext(), "No Data to Show", false);
            }

            ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));
        } else if (spinnerSelected.contains("Customer Base")){
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
        } else if(spinnerSelected.contains("Customer Base MTD")){
            header = new String[6];
            header[1] = "Outlet Code";
            header[2] = "Outlet Name";
            header[3] = "Daily";
            header[4] = "MTD";

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

            ReportTableView.setColumnWeight(1, 1);
            ReportTableView.setColumnWeight(2, 2);
            ReportTableView.setColumnWeight(3, 1);
            ReportTableView.setColumnWeight(4, 1);

            ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

            List<mCountConsumerMTDData> dt_mCountConsumerMTDData = new mCountConsumerMTDBL().getAllmCountConsumerMTDDA(outletcode);
            reportList = new ArrayList<>();

            if(dt_mCountConsumerMTDData != null&&dt_mCountConsumerMTDData.size()>0){
                for(mCountConsumerMTDData datas : dt_mCountConsumerMTDData ){
                    ReportTable rt = new ReportTable();

                    rt.set_report_type("Customer Base MTD");
                    rt.set_outlet_code(datas.getTxtOutletCode());
                    rt.set_outlet_name(datas.getTxtOutletName());
                    int CustomerBasedDaily = new tCustomerBasedMobileHeaderBL().getCountAllCustomerBasedAbsen(datas.getTxtOutletCode());
                    rt.set_sum_daily(new clsMainActivity().convertNumberDec(Double.valueOf(CustomerBasedDaily)));
                    rt.set_sum_MTD(new clsMainActivity().convertNumberDec(Double.valueOf(datas.getJumlah())+Double.valueOf(CustomerBasedDaily)));
                    reportList.add(rt);
                }
            } else {
                new clsMainActivity().showCustomToast(getContext(), "No Data to Show", false);
            }

            ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));

        } else if (spinnerSelected.contains("Actvity")||spinnerSelected.contains("Activity")||spinnerSelected.contains("Additional Display")){
//            Toast.makeText(getContext(), "Actvity", Toast.LENGTH_SHORT).show();
            header = new String[6];
            header[1] = "Outlet";
            header[2] = "Desc.";

            ReportTableView.setColumnCount(header.length);

            simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
            simpleTableHeaderAdapter.setTextSize(14);
            simpleTableHeaderAdapter.setPaddingBottom(20);
            simpleTableHeaderAdapter.setPaddingTop(20);

            ReportTableView.setColumnComparator(1, ReportComparators.getOutletActivityComparator());
            ReportTableView.setColumnComparator(2, ReportComparators.getDescActivityComparator());

            ReportTableView.setColumnWeight(1, 2);
            ReportTableView.setColumnWeight(2, 2);

            ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

            List<tActivityData> dt_act = new tActivityBL().getAllDataByOutletCode(outletcode);
            List<tActivityMobileData> dt_actV2 = new tActivityMobileBL().getAllDataByOutletCode(outletcode);
            reportList = new ArrayList<>();

            if(dt_act != null&&dt_act.size()>0){
                for(tActivityData datas : dt_act ){
                    ReportTable rt = new ReportTable();

                    rt.set_report_type("Activity");
                    rt.set_txtDesc(datas.get_txtDesc());
                    rt.set_txtOutletName(datas.get_txtOutletName());
                    if(datas.get_txtOutletName().equals("null")){
                        rt.set_txtOutletName("-");
                    }
                    reportList.add(rt);
                }
            } else if(dt_actV2 != null&&dt_actV2.size()>0){
                ReportTableView = (SortableReportTableView) v.findViewById(R.id.tableView);

                header = new String[6];
                header[1] = "Outlet";
                header[2] = "Type";
                header[3] = "Category";
                header[4] = "Desc.";

                ReportTableView.setColumnCount(header.length);

                simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
                simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
                simpleTableHeaderAdapter.setTextSize(14);
                simpleTableHeaderAdapter.setPaddingBottom(20);
                simpleTableHeaderAdapter.setPaddingTop(20);

                ReportTableView.setColumnComparator(1, ReportComparators.getOutletActivityComparator());
                ReportTableView.setColumnComparator(2, ReportComparators.getStatusComparator());
                ReportTableView.setColumnComparator(3, ReportComparators.getCategoryComparator());
                ReportTableView.setColumnComparator(4, ReportComparators.getDescActivityComparator());

                ReportTableView.setColumnWeight(1, 2);
                ReportTableView.setColumnWeight(2, 2);
                ReportTableView.setColumnWeight(3, 2);
                ReportTableView.setColumnWeight(4, 2);

                ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

                for(tActivityMobileData datas : dt_actV2 ){
                    ReportTable rt = new ReportTable();

                    rt.set_report_type("ActivityV2");
                    rt.set_status(datas.get_intFlag());
                    rt.set_Category(datas.get_txtTypeActivity());
                    rt.set_txtDesc(datas.get_txtDesc());
                    rt.set_txtOutletName(datas.get_txtOutletName());
                    if(datas.get_txtOutletName().equals("null")){
                        rt.set_txtOutletName("-");
                    }

                    reportList.add(rt);
                }
            } else {
                new clsMainActivity().showCustomToast(getContext(), "No Data to Show", false);
            }

            ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));

        } else if (spinnerSelected.contains("PO")){
//            Toast.makeText(getContext(), "PO", Toast.LENGTH_SHORT).show();
            header = new String[6];
            header[1] = "NO";
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

            ReportTableView.setColumnComparator(1, ReportComparators.getNoPoComparator());
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

            List<tPurchaseOrderHeaderData> dt_po = new tPurchaseOrderHeaderBL().getAllPurchaseOrderHeaderByOutletCode(outletcode);
            reportList = new ArrayList<>();

            if(dt_po != null&&dt_po.size()>0){
                for(tPurchaseOrderHeaderData datas : dt_po ){
                    ReportTable rt = new ReportTable();

                    rt.set_report_type("Po");
                    rt.set_no_po(datas.get_txtNoOrder());
                    rt.set_total_product(datas.get_intSumItem());
                    rt.set_total_price(new clsMainActivity().convertNumberDec(Double.valueOf(datas.get_intSumAmount())));
                    rt.set_status(datas.get_OutletName());

                    List<tPurchaseOrderDetailData> dt_detail = new tPurchaseOrderDetailBL().getDataByNoPO(datas.get_txtNoOrder());

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

        } else if (spinnerSelected.contains("Near ED")){
//            Toast.makeText(getContext(), "Quantity Stock", Toast.LENGTH_SHORT).show();
            header = new String[6];
            header[1] = "NO";
            header[2] = "Tot. Prd";
            header[3] = "Tot. Qty";
//            header[4] = "Tot. Price";
            header[4] = "Outlet";
            header[5] = "";

            ReportTableView.setColumnCount(header.length);

            simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
            simpleTableHeaderAdapter.setTextSize(14);
            simpleTableHeaderAdapter.setPaddingBottom(20);
            simpleTableHeaderAdapter.setPaddingTop(20);

            ReportTableView.setColumnComparator(1, ReportComparators.getNoQStockComparator());
            ReportTableView.setColumnComparator(2, ReportComparators.getTotalProductComparator());
            ReportTableView.setColumnComparator(3, ReportComparators.getTotalItemComparator());
//            ReportTableView.setColumnComparator(4, ReportComparators.getTotalPriceComparator());
            ReportTableView.setColumnComparator(4, ReportComparators.getStatusComparator());
            ReportTableView.setColumnComparator(5, ReportComparators.getviewDetailComparator());

            ReportTableView.setColumnWeight(1, 2);
            ReportTableView.setColumnWeight(2, 1);
            ReportTableView.setColumnWeight(3, 1);
            ReportTableView.setColumnWeight(4, 1);
            ReportTableView.setColumnWeight(5, 1);

            ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

            List<tSalesProductQuantityHeaderData> dt_qs = new tSalesProductQuantityHeaderBL().getAllSalesProductHeaderByOutletCode(outletcode);
            reportList = new ArrayList<>();

            if(dt_qs != null&&dt_qs.size()>0){
                for(tSalesProductQuantityHeaderData datas : dt_qs ){
                    ReportTable rt = new ReportTable();

                    rt.set_report_type("QStock");
                    rt.set_txtQuantityStock(datas.get_txtQuantityStock());
                    rt.set_total_product(datas.get_intSumItem());
                    rt.set_total_price(new clsMainActivity().convertNumberDec(Double.valueOf(datas.get_intSumAmount())));
                    rt.set_status(datas.get_OutletName());
                    rt.set_dummy("Near ED");
                    rt.set_view_detail("View Detail");

                    List<tSalesProductQuantityDetailData> dt_detail = new tSalesProductQuantityDetailBL().GetDataByNoQuantityStock(datas.get_txtQuantityStock());

                    int total_item = 0;

                    for(i = 0; i < dt_detail.size(); i++){
                        total_item = total_item + Integer.parseInt(dt_detail.get(i).getTxtQuantity());
                    }

                    rt.set_total_item(String.valueOf(total_item)+ " pcs");
                    rt.set_total_product(String.valueOf(dt_detail.size()));

                    reportList.add(rt);
                }
            } else {
                new clsMainActivity().showCustomToast(getContext(), "No Data to Show", false);
            }

            ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));
        } else if (spinnerSelected.contains("Kemasan Rusak")){
//            Toast.makeText(getContext(), "Quantity Stock", Toast.LENGTH_SHORT).show();
            header = new String[6];
            header[1] = "NO";
            header[2] = "Tot. Prd";
            header[3] = "Tot. Qty";
//            header[4] = "Tot. Price";
            header[4] = "Outlet";
            header[5] = "";

            ReportTableView.setColumnCount(header.length);

            simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
            simpleTableHeaderAdapter.setTextSize(14);
            simpleTableHeaderAdapter.setPaddingBottom(20);
            simpleTableHeaderAdapter.setPaddingTop(20);

            ReportTableView.setColumnComparator(1, ReportComparators.getNoQStockComparator());
            ReportTableView.setColumnComparator(2, ReportComparators.getTotalProductComparator());
            ReportTableView.setColumnComparator(3, ReportComparators.getTotalItemComparator());
//            ReportTableView.setColumnComparator(4, ReportComparators.getTotalPriceComparator());
            ReportTableView.setColumnComparator(4, ReportComparators.getStatusComparator());
            ReportTableView.setColumnComparator(5, ReportComparators.getviewDetailComparator());

            ReportTableView.setColumnWeight(1, 2);
            ReportTableView.setColumnWeight(2, 1);
            ReportTableView.setColumnWeight(3, 1);
            ReportTableView.setColumnWeight(4, 1);
            ReportTableView.setColumnWeight(5, 1);

            ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

            List<tKemasanRusakHeaderData> dt_qs = new tKemasanRusakHeaderBL().getAllHeaderByOutletCode(outletcode);
            reportList = new ArrayList<>();

            if(dt_qs != null&&dt_qs.size()>0){
                for(tKemasanRusakHeaderData datas : dt_qs ){
                    ReportTable rt = new ReportTable();

                    rt.set_report_type("KemasanRusak");
                    rt.set_txtQuantityStock(datas.get_txtKemasanRusak());
                    rt.set_total_product(datas.get_intSumItem());
                    rt.set_total_price(new clsMainActivity().convertNumberDec(Double.valueOf(datas.get_intSumAmount())));
                    rt.set_status(datas.get_OutletName());
                    rt.set_dummy("Kemasan Rusak");
                    rt.set_view_detail("View Detail");

                    List<tKemasanRusakDetailData> dt_detail = new tKemasanRusakDetailBL().GetDataByNo(datas.get_txtKemasanRusak());

                    int total_item = 0;

                    for(i = 0; i < dt_detail.size(); i++){
                        total_item = total_item + Integer.parseInt(dt_detail.get(i).getTxtQuantity());
                    }

                    rt.set_total_item(String.valueOf(total_item)+ " pcs");
                    rt.set_total_product(String.valueOf(dt_detail.size()));

                    reportList.add(rt);
                }
            } else {
                new clsMainActivity().showCustomToast(getContext(), "No Data to Show", false);
            }

            ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));
        } else if (spinnerSelected.contains("Over Stock")){
//            Toast.makeText(getContext(), "Quantity Stock", Toast.LENGTH_SHORT).show();
            header = new String[6];
            header[1] = "NO";
            header[2] = "Tot. Prd";
            header[3] = "Tot. Qty";
//            header[4] = "Tot. Price";
            header[4] = "Outlet";
            header[5] = "";

            ReportTableView.setColumnCount(header.length);

            simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
            simpleTableHeaderAdapter.setTextSize(14);
            simpleTableHeaderAdapter.setPaddingBottom(20);
            simpleTableHeaderAdapter.setPaddingTop(20);

            ReportTableView.setColumnComparator(1, ReportComparators.getNoQStockComparator());
            ReportTableView.setColumnComparator(2, ReportComparators.getTotalProductComparator());
            ReportTableView.setColumnComparator(3, ReportComparators.getTotalItemComparator());
            ReportTableView.setColumnComparator(4, ReportComparators.getStatusComparator());
            ReportTableView.setColumnComparator(5, ReportComparators.getviewDetailComparator());

            ReportTableView.setColumnWeight(1, 2);
            ReportTableView.setColumnWeight(2, 1);
            ReportTableView.setColumnWeight(3, 1);
            ReportTableView.setColumnWeight(4, 1);
            ReportTableView.setColumnWeight(5, 1);

            ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

            List<tOverStockHeaderData> dt_qs = new tOverStockHeaderBL().getAllOverStockHeaderByOutletCode(outletcode);
            reportList = new ArrayList<>();

            if(dt_qs != null&&dt_qs.size()>0){
                for(tOverStockHeaderData datas : dt_qs ){
                    ReportTable rt = new ReportTable();

                    rt.set_report_type("QStock");
                    rt.set_txtQuantityStock(datas.get_txtOverStock());
                    rt.set_total_product(datas.get_intSumItem());
                    rt.set_total_price(new clsMainActivity().convertNumberDec(Double.valueOf(datas.get_intSumAmount())));
                    rt.set_status(datas.get_OutletName());
                    rt.set_dummy("Over Stock");
                    rt.set_view_detail("View Detail");

                    List<tOverStockDetailData> dt_detail = new tOverStockDetailBL().GetDataByNoOverStock(datas.get_txtOverStock());

                    int total_item = 0;

                    for(i = 0; i < dt_detail.size(); i++){
                        total_item = total_item + Integer.parseInt(dt_detail.get(i).getTxtQuantity());
                    }

                    rt.set_total_item(String.valueOf(total_item)+ " pcs");
                    rt.set_total_product(String.valueOf(dt_detail.size()));

                    reportList.add(rt);
                }
            } else {
                new clsMainActivity().showCustomToast(getContext(), "No Data to Show", false);
            }

            ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));
        } else if (spinnerSelected.contains("Planogram")){
//            Toast.makeText(getContext(), "Actvity", Toast.LENGTH_SHORT).show();
            header = new String[6];
            header[1] = "Outlet";
            header[2] = "Category";
            header[3] = "Sesuai/Tidak";
            header[4] = "Desc.";

            ReportTableView.setColumnCount(header.length);

            simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
            simpleTableHeaderAdapter.setTextSize(14);
            simpleTableHeaderAdapter.setPaddingBottom(20);
            simpleTableHeaderAdapter.setPaddingTop(20);

            ReportTableView.setColumnComparator(1, ReportComparators.getOutletActivityComparator());
            ReportTableView.setColumnComparator(2, ReportComparators.getCategoryComparator());
            ReportTableView.setColumnComparator(3, ReportComparators.getStatusComparator());
            ReportTableView.setColumnComparator(4, ReportComparators.getDescActivityComparator());

            ReportTableView.setColumnWeight(1, 2);
            ReportTableView.setColumnWeight(2, 2);
            ReportTableView.setColumnWeight(3, 2);
            ReportTableView.setColumnWeight(4, 2);

            ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

            List<tPlanogramMobileData> dt_planogram = new tPlanogramMobileBL().getAllPlanogramByOutletCode(outletcode);
            reportList = new ArrayList<>();

            if(dt_planogram != null&&dt_planogram.size()>0){
                for(tPlanogramMobileData datas : dt_planogram ){
                    ReportTable rt = new ReportTable();

                    rt.set_report_type("Planogram");
                    rt.set_txtDesc(datas.get_txtKeterangan());
                    rt.set_txtOutletName(datas.get_OutletName());
                    rt.set_Category(datas.get_txtCategoryName());
                    String validPlano = "";
                    validPlano = datas.get_intIsValid().toString().equals("1")?"Sesuai":"Tidak Sesuai";
                    rt.set_status(validPlano);

                    reportList.add(rt);
                }
            } else {
                new clsMainActivity().showCustomToast(getContext(), "No Data to Show", false);
            }

            ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));

            ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));
        } else if (spinnerSelected.contains("Kuesioner")){
            header = new String[6];
            header[1] = "No.";
            header[2] = "Group Question";
            header[3] = "Outlet";
            header[4] = "Answered at -";
            header[5] = "";

            ReportTableView.setColumnCount(header.length);

            simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
            simpleTableHeaderAdapter.setTextSize(14);
            simpleTableHeaderAdapter.setPaddingBottom(20);
            simpleTableHeaderAdapter.setPaddingTop(20);

            ReportTableView.setColumnComparator(1, ReportComparators.getRepeatComparator());
            ReportTableView.setColumnComparator(2, ReportComparators.getGroupQuestionComparator());
            ReportTableView.setColumnComparator(3, ReportComparators.getOutletActivityComparator());
            ReportTableView.setColumnComparator(4, ReportComparators.getDatetimeComparator());
            ReportTableView.setColumnComparator(5, ReportComparators.getviewDetailComparator());


            ReportTableView.setColumnWeight(1, 1);
            ReportTableView.setColumnWeight(2, 2);
            ReportTableView.setColumnWeight(3, 2);
            ReportTableView.setColumnWeight(4, 2);
            ReportTableView.setColumnWeight(5, 2);


            ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

            tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
            List<tJawabanUserHeaderData> dt_HeaderAnswer = new tJawabanUserHeaderBL().GetDataByOutletCode(outletcode, dataUserActive.get_dtLastLogin());
            reportList = new ArrayList<>();
            int iterator = 0;
            if(dt_HeaderAnswer != null&&dt_HeaderAnswer.size()>0){
                for(tJawabanUserHeaderData data : dt_HeaderAnswer ){
                    iterator +=1;
                    ReportTable rt = new ReportTable();
                    List<tGroupQuestionMappingData> dt_group = new tGroupQuestionMappingBL().GetDataById(Integer.parseInt(data.get_intGroupQuestionId()));

                    rt.set_report_type("Kuesioner");
                    rt.set_Group_Question(dt_group.get(0).get_txtGroupQuestion());
                    rt.set_RepeatQuiz( String.valueOf(iterator));
                    rt.set_txtOutletName(data.get_txtOutletName());
                    rt.set_dummy(data.get_intHeaderId());
                    rt.set_view_detail("View Detail");
//                    rt.set_type(String.valueOf(iterator));
                    rt.set_dateTime(data.get_dtDatetime());
                    reportList.add(rt);
                }
            } else {
                new clsMainActivity().showCustomToast(getContext(), "No Data to Show", false);
            }

            ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));
        }else if (spinnerSelected.contains("Koordinasi Outlet")){
            header = new String[6];
            header[1] = "No.";
            header[2] = "Outlet";
            header[3] = "Category";
            header[4] = "Desc";
//            header[5] = "";

            ReportTableView.setColumnCount(header.length);

            simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
            simpleTableHeaderAdapter.setTextSize(14);
            simpleTableHeaderAdapter.setPaddingBottom(20);
            simpleTableHeaderAdapter.setPaddingTop(20);

            ReportTableView.setColumnComparator(1, ReportComparators.getRepeatComparator());
            ReportTableView.setColumnComparator(2, ReportComparators.getOutletActivityComparator());
            ReportTableView.setColumnComparator(3, ReportComparators.getCategoryComparator());
            ReportTableView.setColumnComparator(4, ReportComparators.getDescActivityComparator());
//            ReportTableView.setColumnComparator(5, ReportComparators.getviewDetailComparator());


            ReportTableView.setColumnWeight(1, 1);
            ReportTableView.setColumnWeight(2, 2);
            ReportTableView.setColumnWeight(3, 2);
            ReportTableView.setColumnWeight(4, 2);
//            ReportTableView.setColumnWeight(5, 2);


            ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

            List<KoordinasiOutletData> list = new KoordinasiOutletBL().getAllDataByOutletCodeandSync(outletcode);

            reportList = new ArrayList<>();
            int iterator = 0;
            if(list != null&&list.size()>0){
                for(KoordinasiOutletData data : list ){
                    iterator +=1;
                    ReportTable rt = new ReportTable();

                    rt.set_report_type("Koordinasi Outlet");
                    rt.set_Category(data.get_txtCategory());
                    rt.set_RepeatQuiz( String.valueOf(iterator));
                    rt.set_txtOutletName(data.get_txtOutletName());
                    rt.set_txtDesc(data.get_txtKeterangan());
//                    rt.set_view_detail("View Detail");
                    reportList.add(rt);
                }
            } else {
                new clsMainActivity().showCustomToast(getContext(), "No Data to Show", false);
            }

            ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));
        } else if (spinnerSelected.contains("Tidak Sesuai Pesanan")){
            header = new String[6];
            header[1] = "No.";
            header[2] = "Outlet";
            header[3] = "Desc";
//            header[5] = "";

            ReportTableView.setColumnCount(header.length);

            simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
            simpleTableHeaderAdapter.setTextSize(14);
            simpleTableHeaderAdapter.setPaddingBottom(20);
            simpleTableHeaderAdapter.setPaddingTop(20);

            ReportTableView.setColumnComparator(1, ReportComparators.getRepeatComparator());
            ReportTableView.setColumnComparator(2, ReportComparators.getOutletActivityComparator());
            ReportTableView.setColumnComparator(4, ReportComparators.getDescActivityComparator());
//            ReportTableView.setColumnComparator(5, ReportComparators.getviewDetailComparator());


            ReportTableView.setColumnWeight(1, 1);
            ReportTableView.setColumnWeight(2, 2);
            ReportTableView.setColumnWeight(3, 2);
//            ReportTableView.setColumnWeight(5, 2);


            ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

            List<tTidakSesuaiPesananHeaderData> list = new tTidakSesuaiPesananHeaderBL().getAllDataByOutletCodeReport(outletcode);

            reportList = new ArrayList<>();
            int iterator = 0;
            if(list != null&&list.size()>0){
                for(tTidakSesuaiPesananHeaderData data : list ){
                    iterator +=1;
                    ReportTable rt = new ReportTable();

                    rt.set_report_type("Tidak Sesuai Pesanan");
                    rt.set_RepeatQuiz( String.valueOf(iterator));
                    rt.set_txtOutletName(data.get_txtOutletName());
                    rt.set_txtDesc(data.get_txtKeterangan());
//                    rt.set_view_detail("View Detail");
                    reportList.add(rt);
                }
            } else {
                new clsMainActivity().showCustomToast(getContext(), "No Data to Show", false);
            }

            ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));
        }
        else {
//                Toast.makeText(getContext(), "No Data to Show", Toast.LENGTH_SHORT).show();
                header = new String[6];

                ReportTableView.setColumnCount(header.length);

                simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
                simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
                simpleTableHeaderAdapter.setTextSize(14);
                simpleTableHeaderAdapter.setPaddingBottom(20);
                simpleTableHeaderAdapter.setPaddingTop(20);

                ReportTableView.setColumnWeight(1, 2);

                ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

                reportList = new ArrayList<>();

                ReportTable rt = new ReportTable();

                rt.set_no_po("No Data");
                rt.set_report_type("no data");

                reportList.add(rt);

                ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));
        }

//        switch (spinnerSelected){
//            case "Reso":
//                header = new String[6];
//                header[1] = "SO";
//                header[2] = "Tot. Prd";
//                header[3] = "Tot. Qty";
//                header[4] = "Tot. Price";
//                header[5] = "Outlet";
//
//                ReportTableView.setColumnCount(header.length);
//
//                simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
//                simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
//                simpleTableHeaderAdapter.setTextSize(14);
//                simpleTableHeaderAdapter.setPaddingBottom(20);
//                simpleTableHeaderAdapter.setPaddingTop(20);
//
//                ReportTableView.setColumnComparator(1, ReportComparators.getNoSoComparator());
//                ReportTableView.setColumnComparator(2, ReportComparators.getTotalProductComparator());
//                ReportTableView.setColumnComparator(3, ReportComparators.getTotalItemComparator());
//                ReportTableView.setColumnComparator(4, ReportComparators.getTotalPriceComparator());
//                ReportTableView.setColumnComparator(5, ReportComparators.getStatusComparator());
//
//                ReportTableView.setColumnWeight(1, 2);
//                ReportTableView.setColumnWeight(2, 1);
//                ReportTableView.setColumnWeight(3, 1);
//                ReportTableView.setColumnWeight(4, 2);
//                ReportTableView.setColumnWeight(5, 1);
//
//                ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);
//
//                List<tSalesProductHeaderData> dt_so = new tSalesProductHeaderBL().getAllSalesProductHeaderByOutletCode(outletcode);
//                reportList = new ArrayList<>();
//
//                if(dt_so != null&&dt_so.size()>0){
//                    for(tSalesProductHeaderData datas : dt_so ){
//                        ReportTable rt = new ReportTable();
//
//                        rt.set_report_type("Reso");
//                        rt.set_no_so(datas.get_txtNoSo());
//                        rt.set_total_product(datas.get_intSumItem());
//                        rt.set_total_price(new clsMainActivity().convertNumberDec(Double.valueOf(datas.get_intSumAmount())));
//                        rt.set_status(datas.get_OutletName());
//
//                        List<tSalesProductDetailData> dt_detail = new tSalesProductDetailBL().GetDataByNoSO(datas.get_txtNoSo());
//
//                        int total_item = 0;
//
//                        for(i = 0; i < dt_detail.size(); i++){
//                            total_item = total_item + Integer.parseInt(dt_detail.get(i).get_intQty());
//                        }
//
//                        rt.set_total_item(String.valueOf(total_item));
//                        rt.set_total_product(String.valueOf(dt_detail.size()));
//
//                        reportList.add(rt);
//                    }
//                } else {
//                    new clsMainActivity().showCustomToast(getContext(), "No Data to Show", false);
//                }
//
//                ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));
//                break;
//
//            case "Customer Base":
//                header = new String[7];
//                header[1] = "Type";
//                header[2] = "Name";
//                header[3] = "Phone";
//                header[4] = "Csmr";
//                header[5] = "Prod";
//                header[6] = "Qty";
//
//                ReportTableView.setColumnCount(header.length);
//
//                simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
//                simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
//                simpleTableHeaderAdapter.setTextSize(14);
//                simpleTableHeaderAdapter.setPaddingBottom(20);
//                simpleTableHeaderAdapter.setPaddingTop(20);
//
//                ReportTableView.setColumnComparator(1, ReportComparators.getNoCbComparator());
//                ReportTableView.setColumnComparator(2, ReportComparators.getCustomerNameComparator());
//                ReportTableView.setColumnComparator(3, ReportComparators.getNoTelpComparator());
//                ReportTableView.setColumnComparator(4, ReportComparators.getTotalMemberComparator());
//                ReportTableView.setColumnComparator(5, ReportComparators.getTotalProductComparator());
//                ReportTableView.setColumnComparator(6, ReportComparators.getTotalItemComparator());
//
//                ReportTableView.setColumnWeight(1, 2);
//                ReportTableView.setColumnWeight(2, 2);
//                ReportTableView.setColumnWeight(3, 2);
//                ReportTableView.setColumnWeight(4, 1);
//                ReportTableView.setColumnWeight(5, 1);
//                ReportTableView.setColumnWeight(6, 1);
//
//                ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);
//
//                List<tCustomerBasedMobileHeaderData> data_cb = new tCustomerBasedMobileHeaderBL().getAllCustomerBasedMobileHeaderByOutletCodeReporting(outletcode);
//
//                reportList = new ArrayList<>();
//
//                if (data_cb!=null&&data_cb.size()>0){
//
//                    for(tCustomerBasedMobileHeaderData datas : data_cb){
//
//                        ReportTable rt = new ReportTable();
//
//                        mTypeSubmissionMobile mtTypeSubmissionMobile = new mTypeSubmissionMobile();
//                        mtTypeSubmissionMobile = new mTypeSubmissionMobileBL().getDataBySubmissionCode(datas.get_txtSubmissionCode());
//
//                        rt.set_report_type("Customer Base");
//                        rt.set_no_cb(mtTypeSubmissionMobile.get_txtNamaMasterData());
//                        rt.set_customer_name(datas.get_txtNamaDepan());
//                        rt.set_no_tlp(datas.get_txtTelp());
//
//                        final List<tCustomerBasedMobileDetailData> dtListDetail = new tCustomerBasedMobileDetailBL().getAllDataByHeaderId(datas.get_intTrCustomerId());
//                        rt.set_total_member(String.valueOf(dtListDetail.size()));
//
//
//                        int totProduct = new tCustomerBasedMobileHeaderBL().getCountProductAllCustomerBased(datas.get_intTrCustomerId(), outletcode);
//                        rt.set_total_product(String.valueOf(totProduct));
//
//
//                        int count = 0;
//                        for(int j=0;j<dtListDetail.size();j++){
//                            final List<tCustomerBasedMobileDetailProductData> list = new tCustomerBasedMobileDetailProductBL().getDataByCustomerDetailId(dtListDetail.get(j).get_intTrCustomerIdDetail());
//                            for(i=0 ; i < list.size(); i++){
//                                int count2 = Integer.valueOf(list.get(i).get_txtProductBrandQty());
//                                count+=count2;
//                            }
//                        }
//
//
//                        rt.set_total_item(String.valueOf(count));
//
//                        reportList.add(rt);
//                    }
//                } else {
//                    new clsMainActivity().showCustomToast(getContext(), "No Data to Show", false);
//                }
//
//                ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));
//
//                break;
//
//            default:
//                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
//                break;
//        }
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







