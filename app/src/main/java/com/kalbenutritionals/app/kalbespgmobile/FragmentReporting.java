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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import bl.KoordinasiOutletImageBL;
import bl.mEmployeeSalesProductBL;
import bl.mKategoriBL;
import bl.mListJawabanBL;
import bl.mPertanyaanBL;
import bl.tHirarkiBISBL;
import bl.tKemasanRusakImageBL;
import bl.tPOPStandardDetailBL;
import bl.tPOPStandardHeaderBL;
import bl.tPlanogramImageBL;
import bl.tTidakSesuaiPesananImageBL;
import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
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
import library.spgmobile.common.KoordinasiOutletImageData;
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
import library.spgmobile.common.tKemasanRusakImageData;
import library.spgmobile.common.tOverStockDetailData;
import library.spgmobile.common.tOverStockHeaderData;
import library.spgmobile.common.tPOPStandardDetailData;
import library.spgmobile.common.tPOPStandardHeaderData;
import library.spgmobile.common.tPlanogramImageData;
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
import library.spgmobile.common.tTidakSesuaiPesananImageData;
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
    List<tPOPStandardHeaderData> listPOP = null;
    List<tStockInHandHeaderData> dt_so = null;
    List<tSalesProductQuantityHeaderData> dt_qs = null;
    List<tSalesProductHeaderData> dt_reso = null;
    List<tActivityMobileData> dt_actV2 = null;
    List<tActivityData> dt_act= null;
    List<KoordinasiOutletData> dt_koorOutlet = null;
    List<tCustomerBasedMobileHeaderData> data_cb = null;
    List<mCountConsumerMTDData> dt_mCountConsumerMTDData = null;
    List<tKemasanRusakHeaderData> dt_krs= null;
    List<tOverStockHeaderData> dt_OVS = null;
    List<tPlanogramMobileData> dt_planogram = null;
    List<tTidakSesuaiPesananHeaderData> listTSP = null;
    List<mMenuData> menu;
    File files = null;
    String writeException = "";
    String iOException = "";
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
                listQuis= null; listPOP = null; dt_so = null; dt_qs = null; dt_reso = null; dt_actV2 = null;
                dt_act= null; dt_koorOutlet = null; data_cb = null; dt_mCountConsumerMTDData = null;
                dt_krs= null; dt_OVS = null; dt_planogram = null; listTSP = null;
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
//                final List<tJawabanUserHeaderData> dt_quiz = new tJawabanUserHeaderBL().GetDataByOutletCode(outletcode, dataUserActive.get_dtLastLogin());
                outlet = spnOutlet.getSelectedItem().toString();
                if(dt_so!=null&&dt_so.size()>0){
//                    generatefileXls(spinnerSelected, dt_so);
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//                                generatefileXls(spinnerSelected, outletName, dt_so);
                                AsyncSOH task = new AsyncSOH();
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
//                        generatefileXls(spinnerSelected, outletName, dt_so);
                        AsyncSOH task = new AsyncSOH();
                        task.execute();
                    }

                } else if (listQuis!=null&&listQuis.size()>0){
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AsyncQuiz task = new AsyncQuiz();
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
                        AsyncQuiz task = new AsyncQuiz();
                        task.execute();
                    }
                } else if (listPOP!=null&&listPOP.size()>0){
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AsyncPOP task = new AsyncPOP();
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
                        AsyncPOP task = new AsyncPOP();
                        task.execute();
                    }
                }else if (dt_qs!=null&&dt_qs.size()>0){
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AsyncNearED task = new AsyncNearED();
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
                        AsyncNearED task = new AsyncNearED();
                        task.execute();
                    }
                }else if ((dt_actV2!=null&&dt_actV2.size()>0) || (dt_act!=null&&dt_act.size()>0)){
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AsyncActivityTL task = new AsyncActivityTL();
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
                        AsyncActivityTL task = new AsyncActivityTL();
                        task.execute();
                    }
                }else if (dt_koorOutlet!=null&&dt_koorOutlet.size()>0){
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AsyncKoorOutlet task = new AsyncKoorOutlet();
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
                        AsyncKoorOutlet task = new AsyncKoorOutlet();
                        task.execute();
                    }
                }else if (dt_reso!=null&&dt_reso.size()>0){
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AsyncReso task = new AsyncReso();
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
                        AsyncReso task = new AsyncReso();
                        task.execute();
                    }
                }else if (data_cb!=null&&data_cb.size()>0){
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AsyncCB task = new AsyncCB();
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
                        AsyncCB task = new AsyncCB();
                        task.execute();
                    }
                } else if (dt_mCountConsumerMTDData!=null&&dt_mCountConsumerMTDData.size()>0){
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AsyncCBMTD task = new AsyncCBMTD();
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
                        AsyncCBMTD task = new AsyncCBMTD();
                        task.execute();
                    }
                } else if (dt_krs!=null&&dt_krs.size()>0){
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AsyncKRS task = new AsyncKRS();
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
                        AsyncKRS task = new AsyncKRS();
                        task.execute();
                    }
                } else if (dt_OVS!=null&&dt_OVS.size()>0){
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AsyncOVS task = new AsyncOVS();
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
                        AsyncOVS task = new AsyncOVS();
                        task.execute();
                    }
                } else if (dt_planogram!=null&&dt_planogram.size()>0){
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AsyncPlano task = new AsyncPlano();
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
                        AsyncPlano task = new AsyncPlano();
                        task.execute();
                    }
                } else if (listTSP!=null&&listTSP.size()>0){
                    if(!outletName.equals("ALL OUTLET")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirm");
                        builder.setMessage("Hanya akan export Exel data transaksi di Outlet " + outletName + "\n" + "Untuk export semua outlet silahkan pilih 'ALL OUTLET'");

                        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AsyncTSP task = new AsyncTSP();
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
                        AsyncTSP task = new AsyncTSP();
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

                dt_reso = new tSalesProductHeaderBL().getAllSalesProductHeaderByOutletCode(outletcode);
                reportList = new ArrayList<>();

                if(dt_reso != null&&dt_reso.size()>0){
                    for(tSalesProductHeaderData datas : dt_reso ){
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
                    btnExport.setVisibility(View.VISIBLE);
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

            dt_so = new tStockInHandHeaderBL().getAllSalesProductHeaderByOutletCodeReport(outletcode);
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

               data_cb = new tCustomerBasedMobileHeaderBL().getAllCustomerBasedMobileHeaderByOutletCodeReporting(outletcode);

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
                    btnExport.setVisibility(View.VISIBLE);
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

             dt_mCountConsumerMTDData = new mCountConsumerMTDBL().getAllmCountConsumerMTDDA(outletcode);
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
                btnExport.setVisibility(View.VISIBLE);
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

            dt_act = new tActivityBL().getAllDataByOutletCode(outletcode);
            dt_actV2 = new tActivityMobileBL().getAllDataByOutletCode(outletcode);
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
                btnExport.setVisibility(View.VISIBLE);
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
                btnExport.setVisibility(View.VISIBLE);
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
            listPOP = new tPOPStandardHeaderBL().GetDataByOutletCodeReport(outletcode);
            reportList = new ArrayList<>();
            int iterator = 0;
            if(listPOP != null&&listPOP.size()>0){
                for(tPOPStandardHeaderData data : listPOP ){
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
                    btnExport.setVisibility(View.VISIBLE);
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
            dt_qs = new tSalesProductQuantityHeaderBL().getAllSalesProductHeaderByOutletCode(outletcode);
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
                btnExport.setVisibility(View.VISIBLE);
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

             dt_krs = new tKemasanRusakHeaderBL().getAllHeaderByOutletCode(outletcode);
            reportList = new ArrayList<>();

            if(dt_krs != null&&dt_krs.size()>0){
                for(tKemasanRusakHeaderData datas : dt_krs ){
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
                btnExport.setVisibility(View.VISIBLE);
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

             dt_OVS = new tOverStockHeaderBL().getAllOverStockHeaderByOutletCode(outletcode);
            reportList = new ArrayList<>();

            if(dt_OVS != null&&dt_OVS.size()>0){
                for(tOverStockHeaderData datas : dt_OVS ){
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
                btnExport.setVisibility(View.VISIBLE);
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

            dt_planogram = new tPlanogramMobileBL().getAllPlanogramByOutletCode(outletcode);
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
                btnExport.setVisibility(View.VISIBLE);
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
            listQuis = new tJawabanUserHeaderBL().GetDataByOutletCode(outletcode, dataUserActive.get_dtLastLogin());
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

            dt_koorOutlet = new KoordinasiOutletBL().getAllDataByOutletCodeandSync(outletcode);
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
                btnExport.setVisibility(View.VISIBLE);
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

            listTSP = new tTidakSesuaiPesananHeaderBL().getAllDataByOutletCodeReport(outletcode);

            reportList = new ArrayList<>();
            int iterator = 0;
            if(listTSP != null&&listTSP.size()>0){
                for(tTidakSesuaiPesananHeaderData data : listTSP ){
                    iterator +=1;
                    ReportTable rt = new ReportTable();

                    rt.set_report_type("Tidak Sesuai Pesanan");
                    rt.set_RepeatQuiz( String.valueOf(iterator));
                    rt.set_txtOutletName(data.get_txtOutletName());
                    rt.set_txtDesc(data.get_txtKeterangan());
//                    rt.set_view_detail("View Detail");
                    reportList.add(rt);
                }
                btnExport.setVisibility(View.VISIBLE);
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

    private class AsyncQuiz extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            generatefileXlsQuiz(spinnerSelected, outlet, listQuis);
            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!writeException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), writeException, false);
            }else if (!iOException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), iOException, false);
            } else {
                new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
                showfileExel(files);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Exporting Your Report...");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private class AsyncPOP extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
           generatefileXlsPOP(spinnerSelected, outlet, listPOP);
            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!writeException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), writeException, false);
            }else if (!iOException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), iOException, false);
            } else {
                new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
                showfileExel(files);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Exporting Your Report...");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private class AsyncSOH extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            generatefileXlsSOH(spinnerSelected, outlet, dt_so);
            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!writeException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), writeException, false);
            }else if (!iOException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), iOException, false);
            } else {
                new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
                showfileExel(files);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Exporting Your Report...");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private class AsyncNearED extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            generatefileXlsNearED(spinnerSelected, outlet, dt_qs);
            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!writeException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), writeException, false);
            }else if (!iOException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), iOException, false);
            } else {
                new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
                showfileExel(files);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Exporting Your Report...");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private class AsyncActivityTL extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            generatefileXlsActivityTL(spinnerSelected, outlet);
            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!writeException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), writeException, false);
            }else if (!iOException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), iOException, false);
            } else {
                new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
                showfileExel(files);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Exporting Your Report...");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private class AsyncKoorOutlet extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            generatefileXlsKoordinasiOutlet(spinnerSelected, outlet, dt_koorOutlet);
            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!writeException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), writeException, false);
            }else if (!iOException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), iOException, false);
            } else {
                new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
                showfileExel(files);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Exporting Your Report...");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private class AsyncReso extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            generatefileXlsReso(spinnerSelected, outlet, dt_reso);
            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!writeException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), writeException, false);
            }else if (!iOException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), iOException, false);
            } else {
                new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
                showfileExel(files);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Exporting Your Report...");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private class AsyncCB extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            generatefileXlsCB(spinnerSelected, outlet, data_cb);
            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!writeException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), writeException, false);
            }else if (!iOException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), iOException, false);
            } else {
                new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
                showfileExel(files);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Exporting Your Report...");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private class AsyncCBMTD extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            generatefileXlsCBMTD(spinnerSelected, outlet, dt_mCountConsumerMTDData);
            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!writeException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), writeException, false);
            }else if (!iOException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), iOException, false);
            } else {
                new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
                showfileExel(files);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Exporting Your Report...");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private class AsyncKRS extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            generatefileXlsKRS(spinnerSelected, outlet, dt_krs);
            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!writeException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), writeException, false);
            }else if (!iOException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), iOException, false);
            } else {
                new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
                showfileExel(files);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Exporting Your Report...");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private class AsyncOVS extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            generatefileXlsOVS(spinnerSelected, outlet, dt_OVS);
            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!writeException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), writeException, false);
            }else if (!iOException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), iOException, false);
            } else {
                new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
                showfileExel(files);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Exporting Your Report...");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private class AsyncPlano extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            generatefileXlsPlano(spinnerSelected, outlet, dt_planogram);
            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!writeException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), writeException, false);
            }else if (!iOException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), iOException, false);
            } else {
                new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
                showfileExel(files);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Exporting Your Report...");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private class AsyncTSP extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            generatefileXlsTSP(spinnerSelected, outlet, listTSP);
            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!writeException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), writeException, false);
            }else if (!iOException.equals("")){
                new clsMainActivity().showCustomToast(getActivity(), iOException, false);
            } else {
                new clsMainActivity().showCustomToast(getActivity(), "Data Exported in a Excel Sheet\n" + "Location file in Internal Storage", true);
                showfileExel(files);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Exporting Your Report...");
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

                    WritableCellFormat cellFormat = new WritableCellFormat();
                    cellFormat.setWrap(true);
                    cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
                    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

                    WritableFont cellFont = new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD);
                    WritableCellFormat formatFont = new WritableCellFormat(cellFont);
                    formatFont.setBorder(Border.ALL, BorderLineStyle.THIN);
                    formatFont.setAlignment(Alignment.CENTRE);

                    // column and row detail
                    sheet.addCell(new Label(0, k, "No Question", formatFont));
                    sheet.addCell(new Label(1, k, "Category", formatFont));
                    sheet.addCell(new Label(2, k, "Question", formatFont));
                    sheet.addCell(new Label(3, k, "Answer", formatFont));

                    for (int z = 0; z <= 3; z++){
                        CellView cellData2 = sheet.getColumnView(z);
                        cellData2.setAutosize(true);
                        sheet.setColumnView(z, cellData2);
                    }

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
                        }else {
                            answer = dataAnswer.get(r).get_txtValue();
                        }
                        k = k+1;
                        sheet.addCell(new Label(0, k, noQ, cellFormat));
                        sheet.addCell(new Label(1, k, kategori, cellFormat));
                        sheet.addCell(new Label(2, k, question, cellFormat));

                        if (dataAnswer.get(r).get_intTypeQuestionId().equals("7")){
                            WritableImage img1 = new WritableImage(3, k,1, 1, dataAnswer.get(r).get_ptQuiz());
                            sheet.addImage(img1);
                            int heightInPoints = 38*20;
                            sheet.setColumnView(3, heightInPoints);
                            sheet.setRowView(k, heightInPoints);
                        } else {
                            sheet.addCell(new Label(3, k, answer, cellFormat));
                        }
                    }
                }
            }

            //closing cursor
            workbook.write();
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
            writeException = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            iOException = e.getMessage().toString();
        }
    }

    private void generatefileXlsPOP(String fileName, String outletName, List<tPOPStandardHeaderData> _tPOPStandardHeaderData){
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
            int y = 0;
            tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;
            try {
                date = sdfDate.parse(dataUserActive.get_dtLastLogin());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dateNow = dateFormat.format(date);
            List<tPOPStandardHeaderData> listOutlet = new tPOPStandardHeaderBL().GetOutletCodeReport(outletName);
            if (listOutlet.size()>0&&listOutlet!=null){
                for (tPOPStandardHeaderData dt : listOutlet){
                    List<tPOPStandardHeaderData> listType = new tPOPStandardHeaderBL().GetTypePOPByOutlet(dt.get_txtOutletName());
                    if (listType!=null&&listType.size()>0){
                        for (int x = 0; x < listType.size(); x++){
                            String nama = listType.get(x).get_txtOutletName() + "_" + listType.get(x).get_txtType();
                            //Excel sheet name. 0 represents first sheet
                            WritableSheet sheet = workbook.createSheet(nama, y);
                            y+=1;
                            String outlet = listType.get(x).get_txtOutletName();
                            String branch = listType.get(x).get_txtBranchName();
                            String typepop = listType.get(x).get_txtType();
                            String name = listType.get(x).get_txtUserName();

                            // column and row header
                            sheet.addCell(new Label(0, 0, "Name"));
                            sheet.addCell(new Label(0, 1, "Branch"));
                            sheet.addCell(new Label(0, 2, "Outlet"));
                            sheet.addCell(new Label(0, 3, "Date"));
                            sheet.addCell(new Label(0, 4, "Type"));

                            sheet.addCell(new Label(1, 0, name));
                            sheet.addCell(new Label(1, 1, branch));
                            sheet.addCell(new Label(1, 2, outlet));
                            sheet.addCell(new Label(1, 3, dateNow));
                            sheet.addCell(new Label(1, 4, typepop));

                            int z = 0;
                            k = 7;
                            List<tPOPStandardHeaderData> listPOP = new tPOPStandardHeaderBL().GetDataByOutletCode(listType.get(x).get_txtOutletCode(), listType.get(x).get_txtType());
                            if (listPOP!=null&&listPOP.size()>0){
                                for (tPOPStandardHeaderData pop : listPOP){


                                    WritableCellFormat cellFormat = new WritableCellFormat();
                                    cellFormat.setWrap(true);
                                    cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
                                    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

                                    WritableFont cellFont = new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD);
                                    WritableCellFormat formatFont = new WritableCellFormat(cellFont);
                                    formatFont.setBorder(Border.ALL, BorderLineStyle.THIN);
                                    formatFont.setAlignment(Alignment.CENTRE);

                                    z++;
                                    // column and row detail
                                    sheet.addCell(new Label(0, 6, "No.", formatFont));
                                    sheet.addCell(new Label(1, 6, "Category", formatFont));
                                    sheet.addCell(new Label(2, 6, "Have POP", formatFont));
                                    sheet.addCell(new Label(3, 6, "Reason", formatFont));
                                    sheet.addCell(new Label(4, 6, "Image 1", formatFont));
                                    sheet.addCell(new Label(5, 6, "Image 2", formatFont));

                                    for (int e = 0; e <= 5; e++){
                                        CellView cellData2 = sheet.getColumnView(e);
                                        cellData2.setAutosize(true);
                                        sheet.setColumnView(e, cellData2);
                                    }

                                    String reason = "";
                                    if (pop.get_txtReason().equals("null")){
                                        reason = "-";
                                    } else {
                                        reason = pop.get_txtReason();
                                    }
                                    String no = String.valueOf(z);
                                    String category = pop.get_txtCategory();
                                    String bolHave = "";
                                    if (pop.get_bolHavePOP().equals("1")){
                                        bolHave = "Yes";
                                    } else {
                                        bolHave = "No";
                                    }
                                    tPOPStandardDetailData data = new tPOPStandardDetailBL().GetDataById(pop.get_intId());
                                    if (data.get_txtImg1()!=null){
                                        WritableImage img1 = new WritableImage(4, k,1, 1, data.get_txtImg1());
                                        sheet.addImage(img1);
                                    } else {
                                        sheet.addCell(new Label(4, k, "-", cellFormat));
                                    }
                                    if (data.get_txtImg2()!=null){
                                        WritableImage img2 = new WritableImage(5, k,1, 1, data.get_txtImg2());
                                        sheet.addImage(img2);
                                    } else {
                                        sheet.addCell(new Label(5, k, "-", cellFormat));
                                    }
                                    if (data.get_txtImg1()!=null||data.get_txtImg2()!=null){
                                        CellView cellData4 = sheet.getRowView(k);
                                        int heightInPoints = 38*20;
                                        cellData4.setAutosize(true);
                                        sheet.setRowView(k, heightInPoints);
                                    }

                                    sheet.addCell(new Label(0, k, no, cellFormat));
                                    sheet.addCell(new Label(1, k, category, cellFormat));
                                    sheet.addCell(new Label(2, k, bolHave, cellFormat));
                                    sheet.addCell(new Label(3, k, reason, cellFormat));
                                    k++;
                                }
                            }
                        }
                    }
                }
            }
            //closing cursor
            workbook.write();
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
            writeException = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            iOException = e.getMessage().toString();
        }
    }

    private void generatefileXlsSOH(String fileName, String outletName, List<tStockInHandHeaderData> _tStockInHandHeaderData){
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
            files = new File(directory, csvFile);
//            if(file.exists()){
//
//            }
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(files, wbSettings);


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

                    String totProd = _tStockInHandHeaderData.get(i).get_intSumItem();

                    List<tStockInHandDetailData> dt_detail = new tStockInHandDetailBL().GetDataByNoSO(_tStockInHandHeaderData.get(i).get_txtNoSo());

                    int total_item = 0;

                    for(int j = 0; j < dt_detail.size(); j++){
                        total_item = total_item + Integer.parseInt(dt_detail.get(j).get_intQty());
                    }

                    String totQty = String.valueOf(total_item)+ " pcs";
                    String outlet = _tStockInHandHeaderData.get(i).get_OutletName();
                    tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date date = null;
                    try {
                        date = sdfDate.parse(dataUserActive.get_dtLastLogin());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String dateNow = dateFormat.format(date);
                    sheet.addCell(new Label(1, 0, no));
                    sheet.addCell(new Label(1, 1, totProd));
                    sheet.addCell(new Label(1, 2, totQty));
                    sheet.addCell(new Label(1, 3, outlet));
                    sheet.addCell(new Label(1, 4, dateNow));

                    WritableCellFormat cellFormat = new WritableCellFormat();
                    cellFormat.setWrap(true);
                    cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
                    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

                    WritableFont cellFont = new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD);
                    WritableCellFormat formatFont = new WritableCellFormat(cellFont);
                    formatFont.setBorder(Border.ALL, BorderLineStyle.THIN);
                    formatFont.setAlignment(Alignment.CENTRE);

                    // column and row detail
                    sheet.addCell(new Label(0, 6, "Name Product", formatFont));
                    sheet.addCell(new Label(1, 6, "Qty", formatFont));
                    for (int z = 0; z <= 1; z++){
                        CellView cellData2 = sheet.getColumnView(z);
                        cellData2.setAutosize(true);
                        sheet.setColumnView(z, cellData2);
                    }

                    int r = 6;


                    for(int k = 0; k < dt_detail.size(); k++){
                        String nameProd = dt_detail.get(k).get_txtNameProduct();
                        String pcs = dt_detail.get(k).get_intQty();
                        r = r+1;

                        sheet.addCell(new Label(0, r, nameProd, cellFormat));
                        sheet.addCell(new Label(1, r, pcs, cellFormat));
                    }
                }
            }

            //closing cursor
            workbook.write();
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
            writeException = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            iOException = e.getMessage().toString();
        }
    }

    private void generatefileXlsNearED(String fileName, String outletName, List<tSalesProductQuantityHeaderData> _tSalesProductQuantityHeaderData ){
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
            files = new File(directory, csvFile);
//            if(file.exists()){
//
//            }
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(files, wbSettings);
            if (_tSalesProductQuantityHeaderData!=null&&_tSalesProductQuantityHeaderData.size()>0) {
                for(int i=0; i<_tSalesProductQuantityHeaderData.size(); i++){

                    String no = _tSalesProductQuantityHeaderData.get(i).get_txtQuantityStock();

                    //Excel sheet name. 0 represents first sheet
                    WritableSheet sheet = workbook.createSheet(no, i);

                    // column and row header
                    sheet.addCell(new Label(0, 0, "No"));
                    sheet.addCell(new Label(0, 1, "Total Produk"));
                    sheet.addCell(new Label(0, 2, "Qty"));
                    sheet.addCell(new Label(0, 3, "Outlet"));
                    sheet.addCell(new Label(0, 4, "Date"));

                    String totProd = _tSalesProductQuantityHeaderData.get(i).get_intSumItem();

                    List<tSalesProductQuantityDetailData> dt_detail = new tSalesProductQuantityDetailBL().GetDataByNoQuantityStock(_tSalesProductQuantityHeaderData.get(i).get_txtQuantityStock());

                    int total_item = 0;

                    for(int j = 0; j < dt_detail.size(); j++){
                        total_item = total_item + Integer.parseInt(dt_detail.get(j).getTxtQuantity());
                    }

                    String totQty = String.valueOf(total_item)+ " pcs";
                    String outlet = _tSalesProductQuantityHeaderData.get(i).get_OutletName();

                    tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date date = null;
                    try {
                        date = sdfDate.parse(dataUserActive.get_dtLastLogin());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String dateNow = dateFormat.format(date);
                    SimpleDateFormat sdfDated = new SimpleDateFormat("yyyy/MM/dd");

                    sheet.addCell(new Label(1, 0, no));
                    sheet.addCell(new Label(1, 1, totProd));
                    sheet.addCell(new Label(1, 2, totQty));
                    sheet.addCell(new Label(1, 3, outlet));
                    sheet.addCell(new Label(1, 4, dateNow));

                    WritableCellFormat cellFormat = new WritableCellFormat();
                    cellFormat.setWrap(true);
                    cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
                    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

                    WritableFont cellFont = new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD);
                    WritableCellFormat formatFont = new WritableCellFormat(cellFont);
                    formatFont.setBorder(Border.ALL, BorderLineStyle.THIN);
                    formatFont.setAlignment(Alignment.CENTRE);

                    // column and row detail
                    sheet.addCell(new Label(0, 6, "Name Product", formatFont));
                    sheet.addCell(new Label(1, 6, "Qty", formatFont));
                    sheet.addCell(new Label(2, 6, "Expired Date", formatFont));

                    for (int z = 0; z <= 2; z++){
                        CellView cellData2 = sheet.getColumnView(z);
                        cellData2.setAutosize(true);
                        sheet.setColumnView(z, cellData2);
                    }

                    int r = 6;

                    for(int k = 0; k < dt_detail.size(); k++){
                        String nameProd = dt_detail.get(k).getTxtProduct();
                        String pcs = dt_detail.get(k).getTxtQuantity();

                        try {
                            date = sdfDated.parse(dt_detail.get(k).getTxtExpireDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String expr = dateFormat.format(date);
                        r = r+1;

                        sheet.addCell(new Label(0, r, nameProd, cellFormat));
                        sheet.addCell(new Label(1, r, pcs, cellFormat));
                        sheet.addCell(new Label(2, r, expr, cellFormat));
                    }
                }
            }
            //closing cursor
            workbook.write();
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
            writeException = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            iOException = e.getMessage().toString();
        }
    }

    private void generatefileXlsActivityTL(String fileName, String outletName ){
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
            files = new File(directory, csvFile);
//            if(file.exists()){
//
//            }
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(files, wbSettings);

            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setWrap(true);
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableFont cellFont = new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD);
            WritableCellFormat formatFont = new WritableCellFormat(cellFont);
            formatFont.setBorder(Border.ALL, BorderLineStyle.THIN);
            formatFont.setAlignment(Alignment.CENTRE);

            List<tActivityMobileData> listActivity = new tActivityMobileBL().getAllOutletCode(outletName);
            tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
            String branch = dataUserActive.get_txtCab();
            String name = dataUserActive.get_txtUserName();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;
            try {
                date = sdfDate.parse(dataUserActive.get_dtLastLogin());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dateNow = dateFormat.format(date);

            if (listActivity!=null&&listActivity.size()>0){
             for (int x = 0; x<listActivity.size(); x++){
                 String outlet = listActivity.get(x).get_txtOutletName();
                 //Excel sheet name. 0 represents first sheet
                 WritableSheet sheet = workbook.createSheet(outlet, 0);

                 // column and row header
                 sheet.addCell(new Label(0, 0, "Branch"));
                 sheet.addCell(new Label(0, 1, "Outlet"));
                 sheet.addCell(new Label(0, 2, "Name"));
                 sheet.addCell(new Label(0, 3, "Date"));

                 sheet.addCell(new Label(1, 0, branch));
                 sheet.addCell(new Label(1, 1, outlet));
                 sheet.addCell(new Label(1, 2, name));
                 sheet.addCell(new Label(1, 3, dateNow));

                 int k = 6;
                 // column and row detail
                 sheet.addCell(new Label(0, 5, "Type", formatFont));
                 sheet.addCell(new Label(1, 5, "Category", formatFont));
                 sheet.addCell(new Label(2, 5, "Description", formatFont));
                 sheet.addCell(new Label(3, 5, "Image 1", formatFont));
                 sheet.addCell(new Label(4, 5, "Image 2", formatFont));

                 for (int z = 0; z <= 5; z++){
                     CellView cellData2 = sheet.getColumnView(z);
                     cellData2.setAutosize(true);
                     sheet.setColumnView(z, cellData2);
                 }

                 List<tActivityMobileData> dt_actV2 = new tActivityMobileBL().getAllDataByOutletCode(listActivity.get(x).get_txtOutletCode());
                 if (dt_actV2!=null&&dt_actV2.size()>0) {
                     for(int i=0; i<dt_actV2.size(); i++){

                         String desc = dt_actV2.get(i).get_txtDesc();
                         String  flag = dt_actV2.get(i).get_intFlag();
                         String typeActivity = dt_actV2.get(i).get_txtTypeActivity();

                         if (dt_actV2.get(i).get_txtImg1()!=null){
                             WritableImage img1 = new WritableImage(3, k,1, 1, dt_actV2.get(i).get_txtImg1());
                             sheet.addImage(img1);
                         } else {
                             sheet.addCell(new Label(3, k, "-", cellFormat));
                         }
                         if (dt_actV2.get(i).get_txtImg2()!=null){
                             WritableImage img2 = new WritableImage(4, k,1, 1, dt_actV2.get(i).get_txtImg2());
                             sheet.addImage(img2);
                         } else {
                             sheet.addCell(new Label(4, k, "-", cellFormat));
                         }
                         if (dt_actV2.get(i).get_txtImg1()!=null||dt_actV2.get(i).get_txtImg2()!=null){
                             CellView cellData4 = sheet.getRowView(k);
                             int heightInPoints = 38*20;
                             cellData4.setAutosize(true);
                             sheet.setRowView(k, heightInPoints);
                         }

                             sheet.addCell(new Label(0, k, flag, cellFormat));
                             sheet.addCell(new Label(1, k, typeActivity, cellFormat));
                             sheet.addCell(new Label(2, k, desc, cellFormat));
                         k++;
                     }
                 }
             }
            }

            List<tActivityData> list_Act = new tActivityBL().getAllOutlet(outletName);
            if (list_Act!=null&&list_Act.size()>0){
                for (int i = 0; i < list_Act.size(); i++){
                    String outlet = list_Act.get(i).get_txtOutletName();
                    //Excel sheet name. 0 represents first sheet
                    WritableSheet sheet = workbook.createSheet(outlet, 0);

                    // column and row header
                    sheet.addCell(new Label(0, 0, "Branch"));
                    sheet.addCell(new Label(0, 1, "Outlet"));
                    sheet.addCell(new Label(0, 2, "Name"));
                    sheet.addCell(new Label(0, 3, "Date"));

                    sheet.addCell(new Label(1, 0, branch));
                    sheet.addCell(new Label(1, 1, outlet));
                    sheet.addCell(new Label(1, 2, name));
                    sheet.addCell(new Label(1, 3, dateNow));
                    for (int z = 0; z <=3; z++){
                        CellView cellData2 = sheet.getColumnView(z);
                        cellData2.setAutosize(true);
                        sheet.setColumnView(z, cellData2);
                    }

                    int k = 6;

                    // column and row detail
                    sheet.addCell(new Label(0, 5, "Type", formatFont));
                    sheet.addCell(new Label(1, 5, "Description", formatFont));
                    sheet.addCell(new Label(2, 5, "Image 1", formatFont));
                    sheet.addCell(new Label(3, 5, "Image 2", formatFont));

                   List<tActivityData> detail = new tActivityBL().getAllDataByOutletCode(list_Act.get(i).get_txtOutletCode());
                    if (detail!=null&&detail.size()>0) {
                        for(int j=0; j<detail.size(); j++){

                            String desc = detail.get(j).get_txtDesc();
                            String  flag = detail.get(j).get_intFlag();

                            if (detail.get(j).get_txtImg1()!=null){
                                WritableImage img1 = new WritableImage(2, k,1, 1, detail.get(j).get_txtImg1());
                                sheet.addImage(img1);
                            } else {
                                sheet.addCell(new Label(2, k, "-", cellFormat));
                            }
                            if (detail.get(j).get_txtImg2()!=null){
                                WritableImage img2 = new WritableImage(3, k,1, 1, detail.get(j).get_txtImg2());
                                sheet.addImage(img2);
                            } else {
                                sheet.addCell(new Label(3, k, "-", cellFormat));
                            }
                            if (detail.get(j).get_txtImg1()!=null||detail.get(j).get_txtImg2()!=null){
                                CellView cellData4 = sheet.getRowView(k);
                                int heightInPoints = 38*20;
                                cellData4.setAutosize(true);
                                sheet.setRowView(k, heightInPoints);
                            }

                            sheet.addCell(new Label(0, k, flag, cellFormat));
                            sheet.addCell(new Label(1, k, desc, cellFormat));
                            k++;
                        }
                    }
                }
            }
            //closing cursor
            workbook.write();
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
            writeException = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            iOException = e.getMessage().toString();
        }
    }

    private void generatefileXlsKoordinasiOutlet(String fileName, String outletName, List<KoordinasiOutletData> _KoordinasiOutletData ){
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
            files = new File(directory, csvFile);
//            if(file.exists()){
//
//            }
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(files, wbSettings);

            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet(outlet, 0);
            // column and row header
            sheet.addCell(new Label(0, 0, "Branch"));
            sheet.addCell(new Label(0, 1, "Name"));
            sheet.addCell(new Label(0, 2, "Date"));

            tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
            String branch = dataUserActive.get_txtCab();
            String name = dataUserActive.get_txtUserName();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;
            try {
                date = sdfDate.parse(dataUserActive.get_dtLastLogin());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dateNow = dateFormat.format(date);

            sheet.addCell(new Label(1, 0, branch));
            sheet.addCell(new Label(1, 1, name));
            sheet.addCell(new Label(1, 2, dateNow));

            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setWrap(true);
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableFont cellFont = new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD);
            WritableCellFormat formatFont = new WritableCellFormat(cellFont);
            formatFont.setBorder(Border.ALL, BorderLineStyle.THIN);
            formatFont.setAlignment(Alignment.CENTRE);

                    int k = 5;
                    // column and row detail
                    sheet.addCell(new Label(0, 4, "No.", formatFont));
                    sheet.addCell(new Label(1, 4, "Outlet", formatFont));
                    sheet.addCell(new Label(2, 4, "Category", formatFont));
                    sheet.addCell(new Label(3, 4, "Description", formatFont));
                    sheet.addCell(new Label(4, 4, "Image 1", formatFont));
                    sheet.addCell(new Label(5, 4, "Image 2", formatFont));

            for (int z = 0; z <= 5; z++){
                CellView cellData2 = sheet.getColumnView(z);
                cellData2.setAutosize(true);
                sheet.setColumnView(z, cellData2);
            }

                    if (_KoordinasiOutletData!=null&&_KoordinasiOutletData.size()>0) {
                        for(int i=0; i<_KoordinasiOutletData.size(); i++){

                            String desc = _KoordinasiOutletData.get(i).get_txtKeterangan();
                            String category = _KoordinasiOutletData.get(i).get_txtCategory();
                            String outlet = _KoordinasiOutletData.get(i).get_txtOutletName();

                            List<KoordinasiOutletImageData> listImg = new KoordinasiOutletImageBL().getDataHeaderId(_KoordinasiOutletData.get(i).get_intId());
                            int a = 0;
                            int b = 0;
                            if (listImg!=null&&listImg.size()>0){
                                CellView cellData4 = sheet.getRowView(k);
                                int heightInPoints = 38*20;
                                cellData4.setAutosize(true);
                                sheet.setRowView(k, heightInPoints);
                                for (int x= 0; x<listImg.size(); x++){
                                    if (listImg.get(x).get_txtImage()!=null){
                                        if (listImg.get(x).get_intPosition().equals("1")){
                                            a = 4;
                                            WritableImage img1 = new WritableImage(a, k,1, 1, listImg.get(x).get_txtImage());
                                            sheet.addImage(img1);
                                        } else if (listImg.get(x).get_intPosition().equals("2")){
                                            b = 5;
                                            WritableImage img1 = new WritableImage(b, k,1, 1, listImg.get(x).get_txtImage());
                                            sheet.addImage(img1);
                                        }
                                    }
                                }
                            }
                            if (a==0){
                                sheet.addCell(new Label(4, k, "-", cellFormat));
                            }
                            if (b==0){
                                sheet.addCell(new Label(5, k, "-", cellFormat));
                            }

                            sheet.addCell(new Label(0, k, String.valueOf(i+1), cellFormat));
                            sheet.addCell(new Label(1, k, outlet, cellFormat));
                            sheet.addCell(new Label(2, k, category, cellFormat));
                            sheet.addCell(new Label(3, k, desc, cellFormat));
                            k++;
                        }
                    }
            //closing cursor
            workbook.write();
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
            writeException = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            iOException = e.getMessage().toString();
        }
    }

    private String convertNumberDec(double dec) {
        double harga = dec;
        DecimalFormat df = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol("");
        dfs.setMonetaryDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        String hsl = df.format(harga);

        return hsl;
    }
    private void generatefileXlsReso(String fileName, String outletName, List<tSalesProductHeaderData> _tSalesProductHeaderData ){
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
            files = new File(directory, csvFile);
//            if(file.exists()){
//
//            }
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(files, wbSettings);


            tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
            String name = dataUserActive.get_txtUserName();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            Date date = null;
            if (_tSalesProductHeaderData!=null&&_tSalesProductHeaderData.size()>0) {
                for(int i=0; i<_tSalesProductHeaderData.size(); i++){
                    String no = _tSalesProductHeaderData.get(i).get_txtNoSo();
                    //Excel sheet name. 0 represents first sheet
                    WritableSheet sheet = workbook.createSheet(no, 0);
                    // column and row header

                    sheet.addCell(new Label(0, 0, "Name"));
                    sheet.addCell(new Label(0, 1, "Branch"));
                    sheet.addCell(new Label(0, 2, "Outlet"));
                    sheet.addCell(new Label(0, 3, "Date"));
                    sheet.addCell(new Label(0, 4, "No. SO"));
                    sheet.addCell(new Label(0, 5, "Sum Item"));

                    try {
                        date = sdfDate.parse(_tSalesProductHeaderData.get(i).get_dtDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String dateNow = dateFormat.format(date);
                    String branch = _tSalesProductHeaderData.get(i).get_txtBranchName();
                    String outlet = _tSalesProductHeaderData.get(i).get_OutletName();
                    String sumItem = _tSalesProductHeaderData.get(i).get_intSumItem();
                    String sumAmount = convertNumberDec(Double.valueOf(_tSalesProductHeaderData.get(i).get_intSumAmount()));

                    sheet.addCell(new Label(1, 0, name));
                    sheet.addCell(new Label(1, 1, branch));
                    sheet.addCell(new Label(1, 2, outlet));
                    sheet.addCell(new Label(1, 3, dateNow));
                    sheet.addCell(new Label(1, 4, no));
                    sheet.addCell(new Label(1, 5, sumItem));

                    WritableCellFormat cellFormat = new WritableCellFormat();
                    cellFormat.setWrap(true);
                    cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
                    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

                    int k = 7;

                    WritableFont cellFont = new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD);
                    WritableCellFormat formatFont = new WritableCellFormat(cellFont);
                    formatFont.setBorder(Border.ALL, BorderLineStyle.THIN);
                    formatFont.setAlignment(Alignment.CENTRE);

                    // column and row detail
                    sheet.addCell(new Label(0, k, "No.", formatFont));
                    sheet.addCell(new Label(1, k, "Product", formatFont));
                    sheet.addCell(new Label(2, k, "Description", formatFont));
                    sheet.addCell(new Label(3, k, "Price", formatFont));
                    sheet.addCell(new Label(4, k, "Qty", formatFont));
                    sheet.addCell(new Label(5, k, "Total", formatFont));

                    int totalQty = 0;
                    List<tSalesProductDetailData> detail = new tSalesProductDetailBL().GetDataByNoSO(_tSalesProductHeaderData.get(i).get_txtNoSo());
                    if (detail!=null&&detail.size()>0){
                        for (int x= 0; x<detail.size(); x++){
                            k++;
                            String product = detail.get(x).get_txtNameProduct();
                            String desc = detail.get(x).get_txtKeterangan();
                            String price = detail.get(x).get_intPrice();
                            String qty = detail.get(x).get_intQty();
                            String total = detail.get(x).get_intTotal();
                            totalQty += Integer.parseInt(qty);

                            sheet.addCell(new Label(0, k, String.valueOf(x+1), cellFormat));
                            sheet.addCell(new Label(1, k, product, cellFormat));
                            sheet.addCell(new Label(2, k, desc, cellFormat));
                            sheet.addCell(new Label(3, k, price, cellFormat));
                            sheet.addCell(new Label(4, k, qty, cellFormat));
                            sheet.addCell(new Label(5, k, total, cellFormat));
                        }
                    }
                    int j = k+1;
                    sheet.addCell(new Label(0, j, "Sum Amount", formatFont));
                    sheet.mergeCells(0,j,3,j);
                    sheet.addCell(new Label(4, j, String.valueOf(totalQty), formatFont));
                    sheet.addCell(new Label(5, j, "Rp. " + sumAmount, formatFont));

                    for (int z = 0; z <= 5; z++){
                        CellView cellData2 = sheet.getColumnView(z);
                        cellData2.setAutosize(true);
                        sheet.setColumnView(z, cellData2);
                    }
                }
            }
            //closing cursor
            workbook.write();
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
            writeException = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            iOException = e.getMessage().toString();
        }
    }

    private void generatefileXlsCB(String fileName, String outletName, List<tCustomerBasedMobileHeaderData> _tCustomerBasedMobileHeaderData ){
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
            files = new File(directory, csvFile);
//            if(file.exists()){
//
//            }
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(files, wbSettings);


            tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
            String name = dataUserActive.get_txtUserName();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;
            try {
                date = sdfDate.parse(dataUserActive.get_dtLastLogin());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dateNow = dateFormat.format(date);
            String branch = dataUserActive.get_txtCab();
            String sheetName = "Customer Base";

            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet(sheetName, 0);
            // column and row header

            sheet.addCell(new Label(0, 0, "Name"));
            sheet.addCell(new Label(0, 1, "Branch"));
            sheet.addCell(new Label(0, 2, "Date"));

            sheet.addCell(new Label(1, 0, name));
            sheet.addCell(new Label(1, 1, branch));
            sheet.addCell(new Label(1, 2, dateNow));

            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setWrap(true);
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            int k = 5;

            WritableFont cellFont = new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD);
            WritableCellFormat formatFont = new WritableCellFormat(cellFont);
            formatFont.setBorder(Border.ALL, BorderLineStyle.THIN);
            formatFont.setAlignment(Alignment.CENTRE);

            // column and row detail
            sheet.addCell(new Label(0, k, "No.", formatFont));
            sheet.addCell(new Label(1, k, "Type", formatFont));
            sheet.addCell(new Label(2, k, "PIC Name", formatFont));
            sheet.addCell(new Label(3, k, "Phone", formatFont));
            sheet.addCell(new Label(4, k, "Total Consumer", formatFont));
            sheet.addCell(new Label(5, k, "Total Product", formatFont));
            sheet.addCell(new Label(6, k, "Total Quantity", formatFont));

            for (int z = 0; z <= 6; z++){
                CellView cellData2 = sheet.getColumnView(z);
                cellData2.setAutosize(true);
                sheet.setColumnView(z, cellData2);
            }

            if (_tCustomerBasedMobileHeaderData!=null&&_tCustomerBasedMobileHeaderData.size()>0) {
                for(int i=0; i<_tCustomerBasedMobileHeaderData.size(); i++){
                            k++;
                    mTypeSubmissionMobile mtTypeSubmissionMobile = new mTypeSubmissionMobileBL().getDataBySubmissionCode( _tCustomerBasedMobileHeaderData.get(i).get_txtSubmissionCode());

                    String type = mtTypeSubmissionMobile.get_txtNamaMasterData();
                    String pic = _tCustomerBasedMobileHeaderData.get(i).get_txtNamaDepan();
                    String phone = _tCustomerBasedMobileHeaderData.get(i).get_txtTelp();
                    final List<tCustomerBasedMobileDetailData> dtListDetail = new tCustomerBasedMobileDetailBL().getAllDataByHeaderId(_tCustomerBasedMobileHeaderData.get(i).get_intTrCustomerId());
                    String sumCons = String.valueOf(dtListDetail.size());

                    int totProduct = new tCustomerBasedMobileHeaderBL().getCountProductAllCustomerBased(_tCustomerBasedMobileHeaderData.get(i).get_intTrCustomerId(), _tCustomerBasedMobileHeaderData.get(i).get_txtSumberData());
                    String sumProd = String.valueOf(totProduct);

                    int count = 0;
                    for(int j=0;j<dtListDetail.size();j++){
                        final List<tCustomerBasedMobileDetailProductData> list = new tCustomerBasedMobileDetailProductBL().getDataByCustomerDetailId(dtListDetail.get(j).get_intTrCustomerIdDetail());
                        for(int x=0 ; x < list.size(); x++){
                            int count2 = Integer.valueOf(list.get(x).get_txtProductBrandQty());
                            count+=count2;
                        }
                    }
                            sheet.addCell(new Label(0, k, String.valueOf(i+1), cellFormat));
                            sheet.addCell(new Label(1, k, type, cellFormat));
                            sheet.addCell(new Label(2, k, pic, cellFormat));
                            sheet.addCell(new Label(3, k, phone, cellFormat));
                            sheet.addCell(new Label(4, k, sumCons, cellFormat));
                            sheet.addCell(new Label(5, k, sumProd, cellFormat));
                            sheet.addCell(new Label(6, k, String.valueOf(count), cellFormat));
                }
            }
            //closing cursor
            workbook.write();
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
            writeException = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            iOException = e.getMessage().toString();
        }
    }

    private void generatefileXlsCBMTD(String fileName, String outletName, List<mCountConsumerMTDData> _mCountConsumerMTDData ){
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
            files = new File(directory, csvFile);
//            if(file.exists()){
//
//            }
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(files, wbSettings);


            tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
            String name = dataUserActive.get_txtUserName();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;
            try {
                date = sdfDate.parse(dataUserActive.get_dtLastLogin());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String dateNow = dateFormat.format(date);
            String branch = dataUserActive.get_txtCab();
            String sheetName = "Customer Base MTD";

            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet(sheetName, 0);
            // column and row header

            sheet.addCell(new Label(0, 0, "Name"));
            sheet.addCell(new Label(0, 1, "Branch"));
            sheet.addCell(new Label(0, 2, "Date"));

            sheet.addCell(new Label(1, 0, name));
            sheet.addCell(new Label(1, 1, branch));
            sheet.addCell(new Label(1, 2, dateNow));

            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setWrap(true);
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            int k = 5;

            WritableFont cellFont = new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD);
            WritableCellFormat formatFont = new WritableCellFormat(cellFont);
            formatFont.setBorder(Border.ALL, BorderLineStyle.THIN);
            formatFont.setAlignment(Alignment.CENTRE);

            // column and row detail
            sheet.addCell(new Label(0, k, "No.", formatFont));
            sheet.addCell(new Label(1, k, "Outlet Code", formatFont));
            sheet.addCell(new Label(2, k, "Outlet Name", formatFont));
            sheet.addCell(new Label(3, k, "Daily", formatFont));
            sheet.addCell(new Label(4, k, "MTD", formatFont));

            for (int z = 0; z <= 4; z++){
                CellView cellData2 = sheet.getColumnView(z);
                cellData2.setAutosize(true);
                sheet.setColumnView(z, cellData2);
            }

            if (_mCountConsumerMTDData!=null&&_mCountConsumerMTDData.size()>0) {
                for(int i=0; i<_mCountConsumerMTDData.size(); i++){
                    k++;
                    String outletCode = _mCountConsumerMTDData.get(i).getTxtOutletCode();
                    String outletname = _mCountConsumerMTDData.get(i).getTxtOutletName();
                    int CustomerBasedDaily = new tCustomerBasedMobileHeaderBL().getCountAllCustomerBasedAbsen(outletCode);
                    String dialy = convertNumberDec(Double.valueOf(CustomerBasedDaily));
                    String mtd = convertNumberDec(Double.valueOf(_mCountConsumerMTDData.get(i).getJumlah())+ Double.valueOf(CustomerBasedDaily));

                    sheet.addCell(new Label(0, k, String.valueOf(i+1), cellFormat));
                    sheet.addCell(new Label(1, k, outletCode, cellFormat));
                    sheet.addCell(new Label(2, k, outletname, cellFormat));
                    sheet.addCell(new Label(3, k, dialy, cellFormat));
                    sheet.addCell(new Label(4, k, mtd, cellFormat));
                }
            }
            //closing cursor
            workbook.write();
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
            writeException = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            iOException = e.getMessage().toString();
        }
    }

    private void generatefileXlsKRS(String fileName, String outletName, List<tKemasanRusakHeaderData> _tKemasanRusakHeaderData ){
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
            files = new File(directory, csvFile);
//            if(file.exists()){
//
//            }
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(files, wbSettings);


            tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
            String name = dataUserActive.get_txtUserName();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;
            try {
                date = sdfDate.parse(dataUserActive.get_dtLastLogin());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dateNow = dateFormat.format(date);
            String branch = dataUserActive.get_txtCab();

            if (_tKemasanRusakHeaderData!=null&&_tKemasanRusakHeaderData.size()>0) {
                for(int i=0; i<_tKemasanRusakHeaderData.size(); i++){
                    String no = _tKemasanRusakHeaderData.get(i).get_txtKemasanRusak();

                    //Excel sheet name. 0 represents first sheet
                    WritableSheet sheet = workbook.createSheet(no, i);

                    // column and row header
                    sheet.addCell(new Label(0, 0, "Name"));
                    sheet.addCell(new Label(0, 1, "Branch"));
                    sheet.addCell(new Label(0, 2, "Date"));
                    sheet.addCell(new Label(0, 3, "No."));
                    sheet.addCell(new Label(0, 4, "Outlet"));
                    sheet.addCell(new Label(0, 5, "Total Product"));
                    sheet.addCell(new Label(0, 6, "Image 1"));
                    sheet.addCell(new Label(0, 7, "Image 2"));

                    String outletname  = _tKemasanRusakHeaderData.get(i).get_OutletName();
                    String sumItem = _tKemasanRusakHeaderData.get(i).get_intSumItem();
                    List<tKemasanRusakImageData> dataImage = new tKemasanRusakImageBL().getDataByHeaderId(_tKemasanRusakHeaderData.get(i).get_txtKemasanRusak());

                    sheet.addCell(new Label(1, 0, name));
                    sheet.addCell(new Label(1, 1, branch));
                    sheet.addCell(new Label(1, 2, dateNow));
                    sheet.addCell(new Label(1, 3, no));
                    sheet.addCell(new Label(1, 4, outletname));
                    sheet.addCell(new Label(1, 5, sumItem));

                    int a=0;
                    int b=0;
                    if (dataImage!=null&&dataImage.size()>0){
                        for (tKemasanRusakImageData dt : dataImage){
                            if (dt.get_intPosition().equals("1")){
                                a=6;
                                WritableImage img1 = new WritableImage(1,a,1, 1, dt.get_txtImage());
                                int heightInPoints = 38*60;
                                sheet.setRowView(a, heightInPoints);
                                sheet.addImage(img1);
                            } else if (dt.get_intPosition().equals("2")){
                                b=7;
                                WritableImage img1 = new WritableImage(1,b,1, 1, dt.get_txtImage());
                                int heightInPoints = 38*60;
                                sheet.setRowView(b, heightInPoints);
                                sheet.addImage(img1);
                            }
                        }
                    }
                    if (a==0){
                        sheet.addCell(new Label(1, 6, "-"));
                    }
                    if (b==0){
                        sheet.addCell(new Label(1, 7, "-"));
                    }

                    WritableCellFormat cellFormat = new WritableCellFormat();
                    cellFormat.setWrap(true);
                    cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
                    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

                    int k = 9;

                    WritableFont cellFont = new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD);
                    WritableCellFormat formatFont = new WritableCellFormat(cellFont);
                    formatFont.setBorder(Border.ALL, BorderLineStyle.THIN);
                    formatFont.setAlignment(Alignment.CENTRE);

                    // column and row detail
                    sheet.addCell(new Label(0, k, "No.", formatFont));
                    sheet.addCell(new Label(1, k, "Product", formatFont));
                    sheet.addCell(new Label(2, k, "Description", formatFont));
                    sheet.addCell(new Label(3, k, "Expired Date", formatFont));
                    sheet.addCell(new Label(4, k, "Qty", formatFont));

                    for (int z = 0; z <= 4; z++){
                        CellView cellData2 = sheet.getColumnView(z);
                        cellData2.setAutosize(true);
                        sheet.setColumnView(z, cellData2);
                    }

                    int sumQty = 0;
                    List<tKemasanRusakDetailData> dt_detail = new tKemasanRusakDetailBL().GetDataByNoKemasanRusak(no);
                    if (dt_detail!=null&&dt_detail.size()>0){
                        for (int x =0; x < dt_detail.size(); x++){
                            k++;
                            String product = dt_detail.get(x).getTxtProduct();
                            String desc = dt_detail.get(x).get_txtKeterangan();
                            try {
                                date = sdfDate.parse(dt_detail.get(x).getTxtExpireDate());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String ed = dateFormat.format(date);
                            String qty = dt_detail.get(x).getTxtQuantity();
                            sumQty += Integer.parseInt(qty);

                            sheet.addCell(new Label(0, k, String.valueOf(x+1), cellFormat));
                            sheet.addCell(new Label(1, k, product, cellFormat));
                            sheet.addCell(new Label(2, k, desc, cellFormat));
                            sheet.addCell(new Label(3, k, ed, cellFormat));
                            sheet.addCell(new Label(4, k, qty, cellFormat));
                        }
                    }
                    String totalQty = String.valueOf(sumQty);
                    int j = k+1;
                    sheet.addCell(new Label(0, j, "Total", formatFont));
                    sheet.addCell(new Label(4, j, totalQty, cellFormat));
                    sheet.mergeCells(0,j,3,j);
                }
            }
            //closing cursor
            workbook.write();
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
            writeException = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            iOException = e.getMessage().toString();
        }
    }

    private void generatefileXlsOVS(String fileName, String outletName, List<tOverStockHeaderData> _tOverStockHeaderData ){
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
            files = new File(directory, csvFile);
//            if(file.exists()){
//
//            }
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(files, wbSettings);


            tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
            String name = dataUserActive.get_txtUserName();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            SimpleDateFormat sdfDated = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdfDate.parse(dataUserActive.get_dtLastLogin());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dateNow = dateFormat.format(date);
            String branch = dataUserActive.get_txtCab();

            if (_tOverStockHeaderData!=null&&_tOverStockHeaderData.size()>0) {
                for(int i=0; i<_tOverStockHeaderData.size(); i++){
                    String no = _tOverStockHeaderData.get(i).get_txtOverStock();

                    //Excel sheet name. 0 represents first sheet
                    WritableSheet sheet = workbook.createSheet(no, i);

                    // column and row header
                    sheet.addCell(new Label(0, 0, "Name"));
                    sheet.addCell(new Label(0, 1, "Branch"));
                    sheet.addCell(new Label(0, 2, "Date"));
                    sheet.addCell(new Label(0, 3, "No."));
                    sheet.addCell(new Label(0, 4, "Outlet"));
                    sheet.addCell(new Label(0, 5, "Total Product"));
                    sheet.addCell(new Label(0, 6, "Description"));

                    String outletname  = _tOverStockHeaderData.get(i).get_OutletName();
                    String sumItem = _tOverStockHeaderData.get(i).get_intSumItem();
                    String description = _tOverStockHeaderData.get(i).get_txtKeterangan();

                    sheet.addCell(new Label(1, 0, name));
                    sheet.addCell(new Label(1, 1, branch));
                    sheet.addCell(new Label(1, 2, dateNow));
                    sheet.addCell(new Label(1, 3, no));
                    sheet.addCell(new Label(1, 4, outletname));
                    sheet.addCell(new Label(1, 5, sumItem));
                    sheet.addCell(new Label(1, 6, description));

                    WritableCellFormat cellFormat = new WritableCellFormat();
                    cellFormat.setWrap(true);
                    cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
                    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

                    int k = 9;

                    WritableFont cellFont = new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD);
                    WritableCellFormat formatFont = new WritableCellFormat(cellFont);
                    formatFont.setBorder(Border.ALL, BorderLineStyle.THIN);
                    formatFont.setAlignment(Alignment.CENTRE);

                    // column and row detail
                    sheet.addCell(new Label(0, k, "No.", formatFont));
                    sheet.addCell(new Label(1, k, "Product", formatFont));
                    sheet.addCell(new Label(2, k, "Description", formatFont));
                    sheet.addCell(new Label(3, k, "Expired Date", formatFont));
                    sheet.addCell(new Label(4, k, "Qty", formatFont));

                    for (int z = 0; z <= 4; z++){
                        CellView cellData2 = sheet.getColumnView(z);
                        cellData2.setAutosize(true);
                        sheet.setColumnView(z, cellData2);
                    }

                    int sumQty = 0;
                    List<tOverStockDetailData> dt_detail = new tOverStockDetailBL().GetDataByNoOverStock(_tOverStockHeaderData.get(i).get_txtOverStock());
                    if (dt_detail!=null&&dt_detail.size()>0){
                        for (int x =0; x < dt_detail.size(); x++){
                            k++;
                            String product = dt_detail.get(x).getTxtProduct();
                            String desc = dt_detail.get(x).get_txtKeterangan();
                            try {
                                date = sdfDate.parse(dt_detail.get(x).getTxtExpireDate());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String ed = dateFormat.format(date);
                            String qty = dt_detail.get(x).getTxtQuantity();
                            sumQty += Integer.parseInt(qty);

                            sheet.addCell(new Label(0, k, String.valueOf(x+1), cellFormat));
                            sheet.addCell(new Label(1, k, product, cellFormat));
                            sheet.addCell(new Label(2, k, desc, cellFormat));
                            sheet.addCell(new Label(3, k, ed, cellFormat));
                            sheet.addCell(new Label(4, k, qty, cellFormat));
                        }
                    }

                    String totalQty = String.valueOf(sumQty);
                    int j = k+1;
                    sheet.addCell(new Label(3, j, "Total", formatFont));
                    sheet.addCell(new Label(4, j, totalQty, cellFormat));
//                    sheet.mergeCells(0,j,3,j);
                }
            }
            //closing cursor
            workbook.write();
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
            writeException = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            iOException = e.getMessage().toString();
        }
    }

    private void generatefileXlsPlano(String fileName, String outletName, List<tPlanogramMobileData> _tPlanogramMobileData ){
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
            files = new File(directory, csvFile);
//            if(file.exists()){
//
//            }
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(files, wbSettings);


            tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
            String name = dataUserActive.get_txtUserName();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;
            try {
                date = sdfDate.parse(dataUserActive.get_dtLastLogin());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dateNow = dateFormat.format(date);
            String branch = dataUserActive.get_txtCab();
            String sheetName = "Report Planogram";

            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet(sheetName, 0);

            // column and row header
            sheet.addCell(new Label(0, 0, "Name"));
            sheet.addCell(new Label(0, 1, "Branch"));
            sheet.addCell(new Label(0, 2, "Date"));

            sheet.addCell(new Label(1, 0, name));
            sheet.addCell(new Label(1, 1, branch));
            sheet.addCell(new Label(1, 2, dateNow));

            int k = 4;

            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setWrap(true);
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableFont cellFont = new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD);
            WritableCellFormat formatFont = new WritableCellFormat(cellFont);
            formatFont.setBorder(Border.ALL, BorderLineStyle.THIN);
            formatFont.setAlignment(Alignment.CENTRE);

            // column and row detail
            sheet.addCell(new Label(0, k, "No.", formatFont));
            sheet.addCell(new Label(1, k, "Outlet", formatFont));
            sheet.addCell(new Label(2, k, "Category", formatFont));
            sheet.addCell(new Label(3, k, "Kesesuaian", formatFont));
            sheet.addCell(new Label(4, k, "Description", formatFont));
            sheet.addCell(new Label(5, k, "Image Before 1", formatFont));
            sheet.addCell(new Label(6, k, "Image Before 2", formatFont));
            sheet.addCell(new Label(7, k, "Image After 1", formatFont));
            sheet.addCell(new Label(8, k, "Image After 2", formatFont));

            for (int z = 0; z <= 8; z++){
                CellView cellData2 = sheet.getColumnView(z);
                cellData2.setAutosize(true);
                sheet.setColumnView(z, cellData2);
            }

            if (_tPlanogramMobileData!=null&&_tPlanogramMobileData.size()>0) {
                for(int i=0; i<_tPlanogramMobileData.size(); i++){
                    k++;
                    String outlet = _tPlanogramMobileData.get(i).get_OutletName();
                    String category = _tPlanogramMobileData.get(i).get_txtCategoryName();
                    String kesesuaian = _tPlanogramMobileData.get(i).get_intIsValid().toString().equals("1")?"Sesuai":"Tidak Sesuai";
                    String desc = _tPlanogramMobileData.get(i).get_txtKeterangan();
                    sheet.addCell(new Label(0, k, String.valueOf(i+1), cellFormat));
                    sheet.addCell(new Label(1, k, outlet, cellFormat));
                    sheet.addCell(new Label(2, k, category, cellFormat));
                    sheet.addCell(new Label(3, k, kesesuaian, cellFormat));
                    sheet.addCell(new Label(4, k, desc, cellFormat));
                    List<tPlanogramImageData>dataImage = new tPlanogramImageBL().getDataHeaderId(_tPlanogramMobileData.get(i).get_txtIdPlanogram());
                    int a=0, b=0, c=0, d=0;
                    if (dataImage!=null&&dataImage.size()>0){
                        int heightInPoints = 38*40;
                        sheet.setRowView(k, heightInPoints);
                        for (tPlanogramImageData dt : dataImage){
                            if (dt.get_txtType().equals("BEFORE")){
                                if (dt.get_intPosition().equals("1")){
                                    a=5;
                                    WritableImage img1 = new WritableImage(a,k,1, 1, dt.get_txtImage());
                                    sheet.addImage(img1);
                                } else if (dt.get_intPosition().equals("2")){
                                    b=6;
                                    WritableImage img1 = new WritableImage(b,k,1, 1, dt.get_txtImage());
                                    sheet.addImage(img1);
                                }
                            }else if (dt.get_txtType().equals("AFTER")){
                                if (dt.get_intPosition().equals("1")){
                                    c=7;
                                    WritableImage img1 = new WritableImage(c,k,1, 1, dt.get_txtImage());
                                    sheet.addImage(img1);
                                } else if (dt.get_intPosition().equals("2")){
                                    d=8;
                                    WritableImage img1 = new WritableImage(d,k,1, 1, dt.get_txtImage());
                                    sheet.addImage(img1);
                                }
                            }

                        }
                    }
                    if (a==0){
                        sheet.addCell(new Label(5, k, "-", cellFormat));
                    }
                    if (b==0){
                        sheet.addCell(new Label(6, k, "-", cellFormat));
                    }
                    if (c==0){
                        sheet.addCell(new Label(7, k, "-", cellFormat));
                    }
                    if (d==0){
                        sheet.addCell(new Label(8, k, "-", cellFormat));
                    }
                }
            }
            //closing cursor
            workbook.write();
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
            writeException = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            iOException = e.getMessage().toString();
        }
    }

    private void generatefileXlsTSP(String fileName, String outletName, List<tTidakSesuaiPesananHeaderData> _tTidakSesuaiPesananHeaderData ){
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
            files = new File(directory, csvFile);
//            if(file.exists()){
//
//            }
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(files, wbSettings);

            tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
            String name = dataUserActive.get_txtUserName();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;

            try {
                date = sdfDate.parse(dataUserActive.get_dtLastLogin());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dateNow = dateFormat.format(date);
            String branch = dataUserActive.get_txtCab();
            String sheetName = "Tidak Sesuai Pesanan";

            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet(sheetName, 0);

            // column and row header
            sheet.addCell(new Label(0, 0, "Name"));
            sheet.addCell(new Label(0, 1, "Branch"));
            sheet.addCell(new Label(0, 2, "Date"));

            sheet.addCell(new Label(1, 0, name));
            sheet.addCell(new Label(1, 1, branch));
            sheet.addCell(new Label(1, 2, dateNow));
            int k = 4;
            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setWrap(true);
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableFont cellFont = new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD);
            WritableCellFormat formatFont = new WritableCellFormat(cellFont);
            formatFont.setBorder(Border.ALL, BorderLineStyle.THIN);
            formatFont.setAlignment(Alignment.CENTRE);

            // column and row detail
            sheet.addCell(new Label(0, k, "No.", formatFont));
            sheet.addCell(new Label(1, k, "Outlet", formatFont));
            sheet.addCell(new Label(2, k, "Description", formatFont));
            sheet.addCell(new Label(3, k, "Image 1", formatFont));
            sheet.addCell(new Label(4, k, "Image 2", formatFont));

            for (int z = 0; z <= 4; z++){
                CellView cellData2 = sheet.getColumnView(z);
                cellData2.setAutosize(true);
                sheet.setColumnView(z, cellData2);
            }

            if (_tTidakSesuaiPesananHeaderData!=null&&_tTidakSesuaiPesananHeaderData.size()>0) {
                for(int i=0; i<_tTidakSesuaiPesananHeaderData.size(); i++){
                    k++;
                    String outlet = _tTidakSesuaiPesananHeaderData.get(i).get_txtOutletName();
                    String desc = _tTidakSesuaiPesananHeaderData.get(i).get_txtKeterangan();
                    sheet.addCell(new Label(0, k, String.valueOf(i+1), cellFormat));
                    sheet.addCell(new Label(1, k, outlet, cellFormat));
                    sheet.addCell(new Label(2, k, desc, cellFormat));

                    List<tTidakSesuaiPesananImageData> dataImage = new tTidakSesuaiPesananImageBL().getDataByHeaderId(_tTidakSesuaiPesananHeaderData.get(i).get_intId());
                    int a=0, b=0;
                    if (dataImage!=null&&dataImage.size()>0){
                        int heightInPoints = 38*20;
                        sheet.setRowView(k, heightInPoints);
                        for (tTidakSesuaiPesananImageData dt : dataImage){
                                if (dt.get_intPosition().equals("1")){
                                    a=3;
                                    WritableImage img1 = new WritableImage(a,k,1, 1, dt.get_txtImage());
                                    sheet.addImage(img1);
                                } else if (dt.get_intPosition().equals("2")){
                                    b=4;
                                    WritableImage img1 = new WritableImage(b,k,1, 1, dt.get_txtImage());
                                    sheet.addImage(img1);
                                }
                        }
                    }
                    if (a==0){
                        sheet.addCell(new Label(3, k, "-", cellFormat));
                    }
                    if (b==0){
                        sheet.addCell(new Label(4, k, "-", cellFormat));
                    }
                }
            }
            //closing cursor
            workbook.write();
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
            writeException = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            iOException = e.getMessage().toString();
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