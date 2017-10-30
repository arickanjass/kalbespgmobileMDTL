package com.kalbenutritionals.app.kalbespgmobile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import bl.mEmployeeSalesProductBL;
import bl.mKategoriBL;
import bl.mListJawabanBL;
import bl.mPertanyaanBL;
import bl.tHirarkiBISBL;
import bl.tPOPStandardHeaderBL;
import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import library.spgmobile.common.KoordinasiOutletData;
import library.spgmobile.common.ReportTable;
import library.spgmobile.common.mCountConsumerMTDData;
import library.spgmobile.common.mDownloadMasterData_mobileData;
import library.spgmobile.common.mEmployeeAreaData;
import library.spgmobile.common.mEmployeeSalesProductData;
import library.spgmobile.common.mKategoriData;
import library.spgmobile.common.mListJawabanData;
import library.spgmobile.common.mMenuData;
import library.spgmobile.common.mPertanyaanData;
import library.spgmobile.common.mTypeSubmissionMobile;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tActivityMobileData;
import library.spgmobile.common.tCustomerBasedMobileDetailData;
import library.spgmobile.common.tCustomerBasedMobileDetailProductData;
import library.spgmobile.common.tCustomerBasedMobileHeaderData;
import library.spgmobile.common.tGroupQuestionMappingData;
import library.spgmobile.common.tHirarkiBIS;
import library.spgmobile.common.tJawabanUserData;
import library.spgmobile.common.tJawabanUserHeaderData;
import library.spgmobile.common.tKemasanRusakDetailData;
import library.spgmobile.common.tKemasanRusakHeaderData;
import library.spgmobile.common.tOverStockDetailData;
import library.spgmobile.common.tOverStockHeaderData;
import library.spgmobile.common.tPOPStandardHeaderData;
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
    private Button btnHide, btnSearch, btnExport;
    private RelativeLayout rlSearch;
    HashMap<String, String> arrOutlet;

    String spinnerSelected = null;
    String outlet = null;
    List<tJawabanUserHeaderData> listQuis= null;
    List<mMenuData> menu;
    File files = null;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_report, container, false);

        ReportTableView = (SortableReportTableView) v.findViewById(R.id.tableView);
        spnTypeReport = (Spinner) v.findViewById(R.id.spnType);
        spnOutlet = (Spinner) v.findViewById(R.id.spnOutlet);
        btnHide = (Button) v.findViewById(R.id.btnHide);
        btnSearch = (Button) v.findViewById(R.id.btnsearch);
        btnExport = (Button) v.findViewById(R.id.btnExport);
        rlSearch = (RelativeLayout) v.findViewById(R.id.rlSearch);

        btnExport.setVisibility(View.GONE);

        btnHide.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public void onClick(View v) {
                btnExport.setVisibility(View.GONE);
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

        btnExport.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public void onClick(View v) {
                String outletcode = arrOutlet.get(spnOutlet.getSelectedItem().toString());
                final String outletName = spnOutlet.getSelectedItem().toString();
                tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
                final List<tStockInHandHeaderData> dt_so = new tStockInHandHeaderBL().getAllSalesProductHeaderByOutletCodeReport(outletcode);
//                final List<tJawabanUserHeaderData> dt_quiz = new tJawabanUserHeaderBL().GetDataByOutletCode(outletcode, dataUserActive.get_dtLastLogin());
               listQuis = new tJawabanUserHeaderBL().GetDataByOutletCode(outletcode, dataUserActive.get_dtLastLogin());
                outlet = spnOutlet.getSelectedItem().toString();
                if(dt_so!=null&&dt_so.size()>0){
//                    generatefileXls(spinnerSelected, dt_so);
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                generatefileXls(spinnerSelected, outletName, dt_so);
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        generatefileXls(spinnerSelected, outletName, dt_so);
                    }

                } else if (listQuis!=null&&listQuis.size()>0){
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//                                generatefileXlsQuiz(spinnerSelected, outletName, dt_quiz);
                                AsyncSQuiz task = new AsyncSQuiz();
                                task.execute();
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
//                        generatefileXlsQuiz(spinnerSelected, outletName, dt_quiz);
                        AsyncSQuiz task = new AsyncSQuiz();
                        task.execute();
                    }
                }
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
        menu = new ArrayList<>();

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

            List<tStockInHandHeaderData> dt_so = new tStockInHandHeaderBL().getAllSalesProductHeaderByOutletCodeReport(outletcode);
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
            if(dt_so!=null&&dt_so.size()>0){
                btnExport.setVisibility(View.VISIBLE);
            }
            ReportTableView.setDataAdapter(new ReportTableDataAdapter(getContext(), reportList));
        } else if (spinnerSelected.equals("Customer Base SPG")){
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
        } else if(spinnerSelected.equals("Customer Base MTD")){
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

        } else if (spinnerSelected.contains("POP Standard TL")){
            header = new String[6];
            header[1] = "No.";
            header[2] = "Type POP";
            header[3] = "Category";
            header[4] = "";

            ReportTableView.setColumnCount(header.length);

            simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getContext(), header);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
            simpleTableHeaderAdapter.setTextSize(14);
            simpleTableHeaderAdapter.setPaddingBottom(20);
            simpleTableHeaderAdapter.setPaddingTop(20);

            ReportTableView.setColumnComparator(1, ReportComparators.getRepeatComparator());
            ReportTableView.setColumnComparator(2, ReportComparators.getTypeComparator());
            ReportTableView.setColumnComparator(3, ReportComparators.getCategoryComparator());
            ReportTableView.setColumnComparator(4, ReportComparators.getviewDetailComparator());


            ReportTableView.setColumnWeight(1, 1);
            ReportTableView.setColumnWeight(2, 2);
            ReportTableView.setColumnWeight(3, 2);
            ReportTableView.setColumnWeight(4, 2);

            ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

            List<tPOPStandardHeaderData> dt_header = new tPOPStandardHeaderBL().GetDataByOutletCodeReport(outletcode);
            reportList = new ArrayList<>();
            int iterator = 0;
            if(dt_header != null&&dt_header.size()>0){
                for(tPOPStandardHeaderData data : dt_header ){
                    iterator +=1;
                    ReportTable rt = new ReportTable();

                    rt.set_report_type("POP Standard TL");
                    rt.set_type(data.get_txtType());
                    rt.set_Category(data.get_txtCategory());
                    rt.set_RepeatQuiz( String.valueOf(iterator));
                    rt.set_txtOutletName(data.get_txtOutletName());
                    rt.set_dummy(data.get_intId());
                    rt.set_view_detail("View Detail");
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
                    btnExport.setVisibility(View.VISIBLE);
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

    private void generatefileXls(String fileName, String outletName, List<tStockInHandHeaderData> _tStockInHandHeaderData){
        File sd = Environment.getExternalStorageDirectory();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDateandTime = sdf.format(new Date());
        String csvFile = fileName + "_" + outletName + "_" + currentDateandTime + ".xls";

        File directory = new File(sd.getAbsolutePath());
        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        try {

            //file path
            File file = new File(directory, csvFile);
//            if(file.exists()){
//
//            }
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(file, wbSettings);


            if (_tStockInHandHeaderData!=null&&_tStockInHandHeaderData.size()>0) {
                for(int i=0; i<_tStockInHandHeaderData.size(); i++){

                    String no = _tStockInHandHeaderData.get(i).get_txtNoSo();

                    //Excel sheet name. 0 represents first sheet
                    WritableSheet sheet = workbook.createSheet(no, i);

                    // column and row header
                    sheet.addCell(new Label(0, 0, "No"));
                    sheet.addCell(new Label(0, 1, "Total Produk"));
                    sheet.addCell(new Label(0, 2, "Qty"));
                    sheet.addCell(new Label(0, 3, "Outlet"));
                    sheet.addCell(new Label(0, 4, "Date"));

                    CellView cell0 = sheet.getColumnView(0);
                    cell0.setAutosize(true);
                    sheet.setColumnView(0, cell0);

                    String totProd = _tStockInHandHeaderData.get(i).get_intSumItem();

                    List<tStockInHandDetailData> dt_detail = new tStockInHandDetailBL().GetDataByNoSO(_tStockInHandHeaderData.get(i).get_txtNoSo());

                    int total_item = 0;

                    for(int j = 0; j < dt_detail.size(); j++){
                        total_item = total_item + Integer.parseInt(dt_detail.get(j).get_intQty());
                    }

                    String totQty = String.valueOf(total_item)+ " pcs";
                    String outlet = _tStockInHandHeaderData.get(i).get_OutletName();
                    String date = _tStockInHandHeaderData.get(i).get_dtDate();

                    sheet.addCell(new Label(1, 0, no));
                    sheet.addCell(new Label(1, 1, totProd));
                    sheet.addCell(new Label(1, 2, totQty));
                    sheet.addCell(new Label(1, 3, outlet));
                    sheet.addCell(new Label(1, 4, date));

                    CellView cellData1 = sheet.getColumnView(1);
                    cellData1.setAutosize(true);
                    sheet.setColumnView(1, cellData1);

                    // column and row detail
                    sheet.addCell(new Label(0, 6, "Name Product"));
                    sheet.addCell(new Label(1, 6, "Qty"));

                    int r = 6;


                    for(int k = 0; k < dt_detail.size(); k++){
                        String nameProd = dt_detail.get(k).get_txtNameProduct();
                        String pcs = dt_detail.get(k).get_intQty();
                        r = r+1;

                        sheet.addCell(new Label(0, r, nameProd));
                        sheet.addCell(new Label(1, r, pcs));
                    }


                }
            }

            //closing cursor
            workbook.write();
            workbook.close();
            new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
            showfileExel(file);
//            showNotification(file);
//            Toast.makeText(getActivity(),
//                    "Data Exported in a Excel Sheet", Toast.LENGTH_SHORT).show();
        } catch (WriteException e) {
            e.printStackTrace();
            new clsMainActivity().showCustomToast(getActivity(), e.toString(), false);
        } catch (IOException e) {
            e.printStackTrace();
            new clsMainActivity().showCustomToast(getActivity(), e.toString(), false);
        }
    }
    private class AsyncSQuiz extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            generatefileXlsQuiz(spinnerSelected, outlet, listQuis);
            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
//            new clsMainActivity().showCustomToast(getContext(), "Export Successfull...", true);
            new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
            showfileExel(files);
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Export Your Reporting...");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private void generatefileXlsQuiz(String fileName, String outletName, List<tJawabanUserHeaderData> _tJawabanUserHeaderData){
        File sd = Environment.getExternalStorageDirectory();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDateandTime = sdf.format(new Date());
        String csvFile = fileName + "_" + outletName + "_" + currentDateandTime + ".xls";
        int k = 0;
        File directory = new File(sd.getAbsolutePath());
        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        try {

            //file path
           files = new File(directory, csvFile);
//            if(file.exists()){
//
//            }
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(files, wbSettings);


            if (_tJawabanUserHeaderData!=null&&_tJawabanUserHeaderData.size()>0) {
                for(int i=0; i<_tJawabanUserHeaderData.size(); i++){

                    String name = _tJawabanUserHeaderData.get(i).get_txtUserName();
                    String outlet = _tJawabanUserHeaderData.get(i).get_txtOutletName();
                    List<tGroupQuestionMappingData> dt_group = new tGroupQuestionMappingBL().GetDataById(Integer.parseInt(_tJawabanUserHeaderData.get(i).get_intGroupQuestionId()));
                    String group = dt_group.get(0).get_txtGroupQuestion();
                    String time = _tJawabanUserHeaderData.get(i).get_dtDatetime();
                    String no = _tJawabanUserHeaderData.get(i).get_intHeaderId();

                    //Excel sheet name. 0 represents first sheet
                    WritableSheet sheet = workbook.createSheet(no, i);

                    // column and row header
                    sheet.addCell(new Label(0, 0, "Name"));
                    sheet.addCell(new Label(0, 1, "Outlet"));
                    sheet.addCell(new Label(0, 2, "Group Question"));
                    sheet.addCell(new Label(0, 3, "Answered At"));

                    List<tJawabanUserData> dataAnswer = new tJawabanUserBL().GetDataByHeaderIdOrderBySoalId(_tJawabanUserHeaderData.get(i).get_intHeaderId());
                    List<mPertanyaanData> dt_Question  = new mPertanyaanBL().GetDataByQuestionId(dataAnswer.get(0).get_intQuestionId());
                    mKategoriData kategoriData = new mKategoriBL().GetCategoryById(dt_Question.get(0).get_intCategoryId());
                    if(kategoriData.get_intParentId().equals("2")) {
                        sheet.addCell(new Label(0, 4, "Sum"));
                        sheet.addCell(new Label(0, 5, "Average"));
                        sheet.addCell(new Label(1, 4, _tJawabanUserHeaderData.get(i).get_intSum()));
                        sheet.addCell(new Label(1, 5, _tJawabanUserHeaderData.get(i).get_intAverage()));
                        k = 7;
                    } else {
                        k = 5;
                    }
                    sheet.addCell(new Label(1, 0, name));
                    sheet.addCell(new Label(1, 1, outlet));
                    sheet.addCell(new Label(1, 2, group));
                    sheet.addCell(new Label(1, 3, time));

                    CellView cell0 = sheet.getColumnView(0);
                    cell0.setAutosize(true);
                    sheet.setColumnView(0, cell0);


                    CellView cellData1 = sheet.getColumnView(1);
                    cellData1.setAutosize(true);
                    sheet.setColumnView(1, cellData1);

                    // column and row detail
                    sheet.addCell(new Label(0, k, "No Question"));
                    sheet.addCell(new Label(1, k, "Category"));
                    sheet.addCell(new Label(2, k, "Question"));
                    sheet.addCell(new Label(3, k, "Answer"));

                    for(int r = 0; r < dataAnswer.size(); r++){
                        List<tJawabanUserData> data_Answer = new tJawabanUserBL().GetDataByQuestionId(dataAnswer.get(r).get_intQuestionId(), _tJawabanUserHeaderData.get(i).get_intHeaderId());
                        List<mPertanyaanData> dtQuestion  = new mPertanyaanBL().GetDataByQuestionId(dataAnswer.get(r).get_intQuestionId());
                        mKategoriData kategori_Data = new mKategoriBL().GetCategoryById(dtQuestion.get(0).get_intCategoryId());
                        String noQ = dtQuestion.get(0).get_intSoalId();
                        String kategori = kategori_Data.get_txtCategoryName();
                        String question = dtQuestion.get(0).get_txtQuestionDesc();
                        String answer = "";
                        if (dataAnswer.get(r).get_intTypeQuestionId().equals("1") || dataAnswer.get(r).get_intTypeQuestionId().equals("2") || dataAnswer.get(r).get_intTypeQuestionId().equals("6")){
                            String jawab = null;
                            if (dataAnswer != null && dataAnswer.size()>0){
                                for (tJawabanUserData dt : data_Answer){
                                    mListJawabanData answerData = new mListJawabanBL().GetDataById(dt.get_intAnswerId());
                                    final HashMap<String, String> HMProduct = new HashMap<String, String>();
                                    List<String> dataJawaban = new ArrayList<>();
                                    if (answerData.get_txtValue().equals("SPG01")){

                                        List<tHirarkiBIS> listSPG = new tHirarkiBISBL().GetDataByOutlet(_tJawabanUserHeaderData.get(i).get_txtOutletCode());
                                        if (listSPG.size() > 0) {
                                            for (tHirarkiBIS dat : listSPG) {
                                                dataJawaban.add(dat.get_txtNik());
                                                HMProduct.put(dat.get_txtNik(), dat.get_txtName());
                                            }
                                        }
                                        String jawaban = HMProduct.get(dataAnswer.get(r).get_txtValue());
                                        answer = jawaban;
                                    } else if (answerData.get_txtValue().equals("CUS01")){
                                        List<mEmployeeSalesProductData> listDataProductKalbe = new mEmployeeSalesProductBL().GetAllData();
                                        if (listDataProductKalbe.size() > 0) {
                                            for (mEmployeeSalesProductData dat : listDataProductKalbe) {
                                                dataJawaban.add(dat.get_txtBrandDetailGramCode());
                                                HMProduct.put(dat.get_txtBrandDetailGramCode(), dat.get_txtProductBrandDetailGramName());
                                            }
                                        }
                                        String jawaban = HMProduct.get(dataAnswer.get(r).get_txtValue());
                                        answer = jawaban;
                                    }else {
                                        if (jawab != null){
                                            jawab += answerData.get_txtKey() + " ,";
                                        }else {
                                            jawab = answerData.get_txtKey() + " ,";
                                        }
                                        String jawabFinal = jawab.substring(0, jawab.lastIndexOf(',')).trim();
                                        answer = jawabFinal;
                                    }
                                }
                            }
                        } else {
                            answer = dataAnswer.get(r).get_txtValue();
                        }
                        k = k+1;

                        sheet.addCell(new Label(0, k, noQ));
                        sheet.addCell(new Label(1, k, kategori));
                        sheet.addCell(new Label(2, k, question));
                        sheet.addCell(new Label(3, k, answer));
                    }


                }
            }

            //closing cursor
            workbook.write();
            workbook.close();
//            new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
//            showfileExel(file);
//            showNotification(file);
//            Toast.makeText(getActivity(),
//                    "Data Exported in a Excel Sheet", Toast.LENGTH_SHORT).show();
        } catch (WriteException e) {
            e.printStackTrace();
            new clsMainActivity().showCustomToast(getActivity(), e.toString(), false);
        } catch (IOException e) {
            e.printStackTrace();
            new clsMainActivity().showCustomToast(getActivity(), e.toString(), false);
        }
    }

    NotificationManager notificationManager;
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    int nid = 1;
    private void showNotification(File file){
        if(file.exists()){
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "excel/*");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent pIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);

            Notification noti = new NotificationCompat.Builder(getActivity())
                    .setContentTitle("Export Successfull...")
                    .setContentText(file.getName().toString())
                    .setSmallIcon(R.drawable.ic_xls_file)
                    .setContentIntent(pIntent).build();

            noti.flags |= Notification.FLAG_AUTO_CANCEL;

            notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, noti);
        }
    }

    public void showfileExel(final File file){
        File sd = Environment.getExternalStorageDirectory();

        File directory = new File(sd.getAbsolutePath());

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Confirm");
        builder.setMessage("Data Exported to Internal stroage location: \n" + directory.toString() + "/" + file.getName().toString());

        builder.setPositiveButton("Show", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                intent.setDataAndType(Uri.fromFile(file),"application/vnd.ms-excel");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
//                    Toast.makeText(getActivity(),"No Application available to viewExcel", Toast.LENGTH_SHORT).show();
                    new clsMainActivity().showCustomToast(getActivity(), "No Application available to view Excel\n" + "Please Instal first...", true);
                    final String appPackageName = "com.google.android.apps.docs.editors.sheets";
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    }
                    catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }
            }
        });

        builder.setNegativeButton("Later", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

}







