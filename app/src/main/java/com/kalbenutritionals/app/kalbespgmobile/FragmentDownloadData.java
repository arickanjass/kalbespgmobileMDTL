package com.kalbenutritionals.app.kalbespgmobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.util.ByteArrayBuffer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bl.KoordinasiOutletBL;
import bl.clsMainBL;
import bl.mCategoryVisitPlanBL;
import bl.mDownloadMasterData_mobileBL;
import bl.mEmployeeAreaBL;
import bl.mEmployeeBranchBL;
import bl.mEmployeeSalesProductBL;
import bl.mKategoriBL;
import bl.mListJawabanBL;
import bl.mParentBL;
import bl.mPertanyaanBL;
import bl.mPriceInOutletBL;
import bl.mProductBarcodeBL;
import bl.mProductBrandHeaderBL;
import bl.mProductCompetitorBL;
import bl.mProductPICBL;
import bl.mProductSPGBL;
import bl.mTypeLeaveBL;
import bl.mTypePertanyaanBL;
import bl.mTypeSubmissionMobileBL;
import bl.tAbsenUserBL;
import bl.tActivityBL;
import bl.tActivityMobileBL;
import bl.tCustomerBasedMobileDetailBL;
import bl.tCustomerBasedMobileDetailProductBL;
import bl.tCustomerBasedMobileHeaderBL;
import bl.tGroupQuestionMappingBL;
import bl.tJawabanUserBL;
import bl.tLeaveMobileBL;
import bl.tPlanogramMobileBL;
import bl.tPurchaseOrderHeaderBL;
import bl.tSalesProductHeaderBL;
import bl.tSalesProductQuantityHeaderBL;
import bl.tSubTypeActivityBL;
import bl.tUserLoginBL;
import bl.tVisitPlanHeader_MobileBL;
import bl.tVisitPlanRealisasiBL;
import bl.trackingLocationBL;
import library.spgmobile.common.APIData;
import library.spgmobile.common.KoordinasiOutletData;
import library.spgmobile.common.KoordinasiOutletImageData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.dataJson;
import library.spgmobile.common.mCategoryVisitPlanData;
import library.spgmobile.common.mDownloadMasterData_mobileData;
import library.spgmobile.common.mEmployeeAreaData;
import library.spgmobile.common.mEmployeeBranchData;
import library.spgmobile.common.mEmployeeSalesProductData;
import library.spgmobile.common.mKategoriData;
import library.spgmobile.common.mListJawabanData;
import library.spgmobile.common.mParentData;
import library.spgmobile.common.mPertanyaanData;
import library.spgmobile.common.mProductBarcodeData;
import library.spgmobile.common.mProductBrandHeaderData;
import library.spgmobile.common.mProductCompetitorData;
import library.spgmobile.common.mProductPICData;
import library.spgmobile.common.mProductSPGData;
import library.spgmobile.common.mTypeLeaveMobileData;
import library.spgmobile.common.mTypePertanyaanData;
import library.spgmobile.common.mTypeSubmissionMobile;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tActivityMobileData;
import library.spgmobile.common.tCustomerBasedMobileDetailData;
import library.spgmobile.common.tCustomerBasedMobileDetailProductData;
import library.spgmobile.common.tCustomerBasedMobileHeaderData;
import library.spgmobile.common.tGroupQuestionMappingData;
import library.spgmobile.common.tJawabanUserData;
import library.spgmobile.common.tLeaveMobileData;
import library.spgmobile.common.tPlanogramImageData;
import library.spgmobile.common.tPlanogramMobileData;
import library.spgmobile.common.tPurchaseOrderDetailData;
import library.spgmobile.common.tPurchaseOrderHeaderData;
import library.spgmobile.common.tSalesProductDetailData;
import library.spgmobile.common.tSalesProductHeaderData;
import library.spgmobile.common.tSalesProductQuantityDetailData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tSalesProductQuantityImageData;
import library.spgmobile.common.tSubTypeActivityData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.tVisitPlanHeader_MobileData;
import library.spgmobile.common.tVisitPlanRealisasiData;
import library.spgmobile.common.trackingLocationData;
import library.spgmobile.dal.KoordinasiOutletImageDA;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.tPlanogramImageDA;
import library.spgmobile.dal.tPurchaseOrderDetailDA;
import library.spgmobile.dal.tSalesProductDetailDA;
import library.spgmobile.dal.tSalesProductQuantityDetailDA;
import library.spgmobile.dal.tSalesProductQuantityImageDA;

public class FragmentDownloadData extends Fragment {
    View v;
    private Spinner spnKordinasiOutlet;
    private Spinner spnBranch;
    private Spinner spnVisitPlan, spnTrVisitPlan;
    private Spinner spnOutlet;
    private Spinner spnProduct;
    private Spinner spnLeave;
    private Spinner spnBrand;
    private Spinner spnReso;
    private Spinner spnActivity, spnActivityV2;
    private Spinner spnCustomerBase;
    private Spinner spnAbsen, spnQuiz;
    private Spinner spnDataPlanogram, spnDataLeave, spnSubTypeActivity, spnDataPO, spnDataQuantityStock, spnProductComp, spnTypeSubmission, spnProdSPGCusBased, spnProdPICCusBased;
    private LinearLayout ll_subtypeactivity;
    private LinearLayout ll_branch;
    private LinearLayout ll_product;
    private LinearLayout ll_brand;
    private LinearLayout ll_type_leave;
    private LinearLayout ll_reso;
    private LinearLayout ll_data_activity;
    private LinearLayout ll_data_activityV2;
    private LinearLayout ll_data_customerbased;
    private LinearLayout ll_absen;
    private LinearLayout ll_purchase_order;
    private LinearLayout ll_data_leave;
    private LinearLayout ll_product_spg;
    private LinearLayout ll_product_pic;
    private LinearLayout ll_product_competitor;
    private LinearLayout ll_type_submission;
    private LinearLayout ll_kategoriVisitPlan;
    private LinearLayout ll_dataVisitPlan;
    private LinearLayout ll_dataQuantityStock;
    private LinearLayout ll_dataKordinasiOutlet;
    private LinearLayout ll_dataQuesioner,ll_data_planogram;

    private PackageInfo pInfo = null;
    private List<String> arrData;
    private String[] strip = new String[]{"-"};
    int intProcesscancel = 0;
    tUserLoginData loginData;
    Handler mHandler = new Handler();

    clsMainActivity _clsMainActivity;

    private String strMessage = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_download_data, container, false);
        Button btnAllDownload = (Button) v.findViewById(R.id.btnAllDownload);

        Button btnKordinasiOutlet = (Button) v.findViewById(R.id.btnKordinasiOutlet);
        spnKordinasiOutlet = (Spinner) v.findViewById(R.id.spnKordinasiOutlet);
        Button btnBranch = (Button) v.findViewById(R.id.btnBranch);
        spnBranch = (Spinner) v.findViewById(R.id.spnType);
        Button btnOutlet = (Button) v.findViewById(R.id.btnOutlet);
        spnOutlet = (Spinner) v.findViewById(R.id.spnOutlet);
        Button btnProduct = (Button) v.findViewById(R.id.btnProduct);
        spnProduct = (Spinner) v.findViewById(R.id.spnProduct);
        Button btnLeave = (Button) v.findViewById(R.id.btnLeave);
        spnLeave = (Spinner) v.findViewById(R.id.spnLeave);
        spnBrand = (Spinner) v.findViewById(R.id.spnBrand);
        Button btnBrand = (Button) v.findViewById(R.id.btnDlBrand);
        spnReso = (Spinner) v.findViewById(R.id.spnReso);
        Button btnReso = (Button) v.findViewById(R.id.btnDlReso);
        spnActivity = (Spinner) v.findViewById(R.id.spnActivity);
        Button btnActivity = (Button) v.findViewById(R.id.btnDlActivity);
        spnActivityV2 = (Spinner) v.findViewById(R.id.spnActivityV2);
        Button btnActivityV2 = (Button) v.findViewById(R.id.btnDlActivityV2);
        spnCustomerBase = (Spinner) v.findViewById(R.id.spnCustomerBase);
        Button btnCustomerBase = (Button) v.findViewById(R.id.btnDlCustomerBase);
        spnAbsen = (Spinner) v.findViewById(R.id.spnAbsen);
        Button btnAbsen = (Button) v.findViewById(R.id.btnDlAbsen);
        spnDataLeave = (Spinner) v.findViewById(R.id.spnDataLeave);
        Button btnDataLeave = (Button) v.findViewById(R.id.btnDlDataLeave);
        spnDataPO = (Spinner) v.findViewById(R.id.spnDataPO);
        Button btnDataPO = (Button) v.findViewById(R.id.btnDlDataPO);
        spnDataQuantityStock = (Spinner) v.findViewById(R.id.spnDataQuantityStock);
        Button btnDataQuantityStock = (Button) v.findViewById(R.id.btnDlDataQuantityStock);
        spnQuiz = (Spinner) v.findViewById(R.id.spnQuiz);
        Button btnQuiz = (Button) v.findViewById(R.id.btnQuiz);
        spnProductComp = (Spinner) v.findViewById(R.id.spnProdComp);
        Button btnProductComp = (Button) v.findViewById(R.id.btnProdComp);
        spnTypeSubmission = (Spinner) v.findViewById(R.id.spnTypeSubm);
        Button btnTypeSubmission = (Button) v.findViewById(R.id.btnSumbisson);
        spnProdSPGCusBased = (Spinner) v.findViewById(R.id.spnProdSPGCusBase);
        Button btnProdSPGCusBased = (Button) v.findViewById(R.id.btnProdSPGCusBase);
        spnProdPICCusBased = (Spinner) v.findViewById(R.id.spnProdPICCusBase);
        Button btnProdPICCusBased = (Button) v.findViewById(R.id.btnProdPICCusBase);
        spnSubTypeActivity = (Spinner) v.findViewById(R.id.spnSubTypeActivity);
        Button btnSubTypeActivity = (Button) v.findViewById(R.id.btnSubTypeActivity);
        spnDataPlanogram = (Spinner) v.findViewById(R.id.spnDataPlanogram);
        Button btnDlDataPlanogram = (Button) v.findViewById(R.id.btnDlDataPlanogram);

        ll_branch = (LinearLayout) v.findViewById(R.id.ll_branch);
        LinearLayout ll_outlet = (LinearLayout) v.findViewById(R.id.ll_outlet);
        ll_product = (LinearLayout) v.findViewById(R.id.ll_product);
        ll_brand = (LinearLayout) v.findViewById(R.id.ll_brand);
        ll_type_leave = (LinearLayout) v.findViewById(R.id.ll_type_leave);
        ll_reso = (LinearLayout) v.findViewById(R.id.ll_reso);
        ll_data_activity = (LinearLayout) v.findViewById(R.id.ll_data_activity);
        ll_data_activityV2 = (LinearLayout) v.findViewById(R.id.ll_data_activityV2);
        ll_data_customerbased = (LinearLayout) v.findViewById(R.id.ll_data_customerbased);
        ll_absen = (LinearLayout) v.findViewById(R.id.ll_absen);
        ll_purchase_order = (LinearLayout) v.findViewById(R.id.ll_purchase_order);
        ll_data_leave = (LinearLayout) v.findViewById(R.id.ll_data_leave);
        ll_product_spg = (LinearLayout) v.findViewById(R.id.ll_product_spg);
        ll_product_pic = (LinearLayout) v.findViewById(R.id.ll_product_pic);
        ll_product_competitor = (LinearLayout) v.findViewById(R.id.ll_product_competitor);
        ll_type_submission = (LinearLayout) v.findViewById(R.id.ll_type_submission);
        ll_kategoriVisitPlan = (LinearLayout) v.findViewById(R.id.ll_kategoriVisitPlan);
        ll_dataVisitPlan = (LinearLayout) v.findViewById(R.id.ll_dataVisitPlan);
        ll_dataQuantityStock = (LinearLayout) v.findViewById(R.id.ll_dataQuantityStock);
        ll_dataKordinasiOutlet = (LinearLayout) v.findViewById(R.id.ll_dataKordinasiOutlet);
        ll_dataQuesioner = (LinearLayout) v.findViewById(R.id.ll_dataQuesioner);
        ll_subtypeactivity = (LinearLayout) v.findViewById(R.id.ll_subtypeactivity);
        ll_data_planogram = (LinearLayout) v.findViewById(R.id.ll_data_planogram);

        spnVisitPlan = (Spinner) v.findViewById(R.id.spnVisitPlan);
        spnTrVisitPlan = (Spinner) v.findViewById(R.id.spnTrVisitPlan);

        Button btnVisitPlan = (Button) v.findViewById(R.id.btnVisitPlan);
        Button btnTrVisitPlan = (Button) v.findViewById(R.id.btnTrVisitPlan);


        loginData = new tUserLoginData();
        loginData = new tUserLoginBL().getUserActive();

        List<mDownloadMasterData_mobileData> mDownloadMasterData_mobileDataList;

        mDownloadMasterData_mobileDataList = new mDownloadMasterData_mobileBL().GetAllData();

        Resources res = getResources();

//        for (mDownloadMasterData_mobileData data : mDownloadMasterData_mobileDataList) {
//            String txt_id = data.get_txtMasterData().replaceAll(" ", "");
//
//            //show master data
//            if (txt_id.equals(res.getResourceEntryName(ll_branch.getId()))) {
//                ll_branch.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_outlet.getId()))) {
//                ll_outlet.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_product.getId()))) {
//                ll_product.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_brand.getId()))) {
//                ll_brand.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_type_leave.getId()))) {
//                ll_type_leave.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_product_spg.getId()))) {
//                ll_product_spg.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_product_pic.getId()))) {
//                ll_product_pic.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_product_competitor.getId()))) {
//                ll_product_competitor.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_type_submission.getId()))) {
//                ll_type_submission.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_kategoriVisitPlan.getId()))) {
//                ll_kategoriVisitPlan.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_dataQuesioner.getId()))) {
//                ll_dataQuesioner.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_subtypeactivity.getId()))) {
//                ll_subtypeactivity.setVisibility(View.VISIBLE);
//            }
//            // show data transaksi
//            else if (txt_id.equals(res.getResourceEntryName(ll_dataVisitPlan.getId()))) {
//                ll_dataVisitPlan.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_dataQuantityStock.getId()))) {
//                ll_dataQuantityStock.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_dataKordinasiOutlet.getId()))) {
//                ll_dataKordinasiOutlet.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_reso.getId()))) {
//                ll_reso.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_data_activity.getId()))) {
//                ll_data_activity.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_data_activityV2.getId()))) {
//                ll_data_activityV2.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_data_customerbased.getId()))) {
//                ll_data_customerbased.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_absen.getId()))) {
//                ll_absen.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_purchase_order.getId()))) {
//                ll_purchase_order.setVisibility(View.VISIBLE);
//            } else if (txt_id.equals(res.getResourceEntryName(ll_data_leave.getId()))) {
//                ll_data_leave.setVisibility(View.VISIBLE);
//            }
//        }

        for(int i = 0; i < mDownloadMasterData_mobileDataList.size(); i++){
            String txt_id = mDownloadMasterData_mobileDataList.get(i).get_txtMasterData().replaceAll(" ", "");

            //show master data
            if (txt_id.equals(res.getResourceEntryName(ll_branch.getId()))) {
                ll_branch.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_outlet.getId()))) {
                ll_outlet.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_product.getId()))) {
                ll_product.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_brand.getId()))) {
                ll_brand.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_type_leave.getId()))) {
                ll_type_leave.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_product_spg.getId()))) {
                ll_product_spg.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_product_pic.getId()))) {
                ll_product_pic.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_product_competitor.getId()))) {
                ll_product_competitor.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_type_submission.getId()))) {
                ll_type_submission.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_kategoriVisitPlan.getId()))) {
                ll_kategoriVisitPlan.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_dataQuesioner.getId()))) {
                ll_dataQuesioner.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_subtypeactivity.getId()))) {
                ll_subtypeactivity.setVisibility(View.VISIBLE);
            }
            // show data transaksi
            else if (txt_id.equals(res.getResourceEntryName(ll_dataVisitPlan.getId()))) {
                ll_dataVisitPlan.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_dataQuantityStock.getId()))) {
                ll_dataQuantityStock.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_dataKordinasiOutlet.getId()))) {
                ll_dataKordinasiOutlet.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_reso.getId()))) {
                ll_reso.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_activity.getId()))) {
                ll_data_activity.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_activityV2.getId()))) {
                ll_data_activityV2.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_customerbased.getId()))) {
                ll_data_customerbased.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_absen.getId()))) {
                ll_absen.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_purchase_order.getId()))) {
                ll_purchase_order.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_leave.getId()))) {
                ll_data_leave.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_planogram.getId()))) {
                ll_data_planogram.setVisibility(View.VISIBLE);
            }
        }

        loadData();

        btnDlDataPlanogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intProcesscancel = 0;
                AsyncCallDataPlanogram task = new AsyncCallDataPlanogram();
                task.execute();
            }
        });

        btnKordinasiOutlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intProcesscancel = 0;
                AsyncCallDataKoordinasiOutlet task = new AsyncCallDataKoordinasiOutlet();
                task.execute();
            }
        });

        btnVisitPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intProcesscancel = 0;
                AsyncCallCategoryVisitPlan task = new AsyncCallCategoryVisitPlan();
                task.execute();
            }
        });

        btnTrVisitPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intProcesscancel = 0;
                AsyncCalltTransaksiVisitPlan task = new AsyncCalltTransaksiVisitPlan();
                task.execute();
            }
        });
        btnSubTypeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intProcesscancel = 0;
                AsyncCallSubTypeActivity task = new AsyncCallSubTypeActivity();
                task.execute();
            }
        });
        btnAllDownload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
                AsyncCallDownloadAll task = new AsyncCallDownloadAll();
                task.execute();
            }
        });
        btnLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intProcesscancel = 0;
                AsyncCallLeave task = new AsyncCallLeave();
                task.execute();
            }
        });
        btnBranch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
                AsyncCallBranch task = new AsyncCallBranch();
                task.execute();
            }
        });
        btnOutlet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
                AsyncCallOutlet task = new AsyncCallOutlet();
                task.execute();
            }
        });
        btnProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
                AsyncCallProduct task = new AsyncCallProduct();
                task.execute();
            }
        });
        btnBrand.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
                AsyncCallProductBrand task = new AsyncCallProductBrand();
                task.execute();
            }
        });
        btnReso.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
                AsyncCallReso task = new AsyncCallReso();
                task.execute();
            }
        });
        btnActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
                AsyncCallActivity task = new AsyncCallActivity();
                task.execute();
            }
        });

        btnActivityV2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
                AsyncCallActivityV2 task = new AsyncCallActivityV2();
                task.execute();
            }
        });

        btnCustomerBase.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
                AsyncCallCustomerBase task = new AsyncCallCustomerBase();
                task.execute();
            }
        });
        btnAbsen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
                AsyncCallAbsen task = new AsyncCallAbsen();
                AsyncCallDataTrackingLocation task2 = new AsyncCallDataTrackingLocation();
                task.execute();
                task2.execute();
//                _clsMainActivity.startService(new Intent(getContext(), MyTrackingLocationService.class));
            }
        });
        btnDataLeave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
                AsyncCallDataLeave task = new AsyncCallDataLeave();
                task.execute();
            }
        });
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intProcesscancel = 0;
                AsyncCallQuis task = new AsyncCallQuis();
                task.execute();
            }
        });
        btnDataPO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intProcesscancel = 0;
                AsyncCallDataPO task = new AsyncCallDataPO();
                task.execute();
            }
        });
        btnDataQuantityStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intProcesscancel = 0;
                AsyncCallDataQuantityStock task = new AsyncCallDataQuantityStock();
                task.execute();
            }
        });
        btnProductComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intProcesscancel = 0;
                AsyncCallDataProdComp task = new AsyncCallDataProdComp();
                task.execute();
            }
        });

        btnTypeSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncCallTypeSubmission task = new AsyncCallTypeSubmission();
                task.execute();
            }
        });
        btnProdSPGCusBased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncCallDataProdSPGCusBased task = new AsyncCallDataProdSPGCusBased();
                task.execute();
            }
        });
        btnProdPICCusBased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncCallDataProdPICCusBased task = new AsyncCallDataProdPICCusBased();
                task.execute();
            }
        });


        return v;
    }

    private void loadData() {
        _clsMainActivity = new clsMainActivity();
        try {
            pInfo = getContext().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        List<mCategoryVisitPlanData> listDataCategoryVisitPlan = new mCategoryVisitPlanBL().GetAllData();
        List<tVisitPlanRealisasiData> listVisitPlanRealisasi = new tVisitPlanRealisasiBL().GetAllData();
        List<mEmployeeBranchData> listDataBranch = new mEmployeeBranchBL().GetAllData();
        List<mEmployeeAreaData> listDataArea = new mEmployeeAreaBL().GetAllData();
        List<mEmployeeSalesProductData> listDataProduct = new mEmployeeSalesProductBL().GetAllData();
        List<mTypeLeaveMobileData> listDataLeave = new mTypeLeaveBL().GetAllData();
        List<mProductBrandHeaderData> listmProductBrandData = new mProductBrandHeaderBL().getData("");
        List<tSalesProductHeaderData> listtSalesProductHeaderData = new tSalesProductHeaderBL().getAllSalesProductHeader();
        List<tCustomerBasedMobileHeaderData> listtCustomerBasedHeaderData = new tCustomerBasedMobileHeaderBL().getAllData();
        List<tActivityData> listtActivityData = new tActivityBL().getAllData();
        List<tActivityMobileData> listtActivityMobileData = new tActivityMobileBL().getAllData();
        List<tAbsenUserData> listtAbsenUserData = new tAbsenUserBL().getAllDataActive();
        List<tLeaveMobileData> listtLeaveData = new tLeaveMobileBL().getData("");
        List<mParentData> parentDataList = new mParentBL().GetAllData();
        List<mPertanyaanData> pertanyaanDataList = new mPertanyaanBL().GetAllData();
        List<mListJawabanData> jawabanDataList = new mListJawabanBL().GetAllData();
        List<mTypePertanyaanData> typePertanyaanDataList = new mTypePertanyaanBL().GetAllData();
        List<tGroupQuestionMappingData> tGroupQuestionMappingDataList = new tGroupQuestionMappingBL().GetAllData();
        List<mKategoriData> kategoriDataList = new mKategoriBL().GetAllData();
        List<tPurchaseOrderHeaderData> listPurchaseOrderHeaderData = new tPurchaseOrderHeaderBL().getAllPurchaseOrderHeader();
        List<tSalesProductQuantityHeaderData> listQuantityStockHeaderData = new tSalesProductQuantityHeaderBL().getAllSalesQuantityHeader();
//        List<trackingLocationData> listtrackingLocationData = new trackingLocationBL().getAllDataTrackingLocation();
        List<KoordinasiOutletData> listKoordinasiOutletData = new KoordinasiOutletBL().getAllKoordinasiOutletData();
        List<mProductCompetitorData> productCompetitorDataList = new mProductCompetitorBL().GetAllData();
        List<mTypeSubmissionMobile> typeSubmissionDataList = new mTypeSubmissionMobileBL().GetAllData();
        List<mProductSPGData> mProductSPGDataList = new mProductSPGBL().GetAllData();
        List<mProductPICData> mProductPICDataList = new mProductPICBL().GetAllData();
        List<tSubTypeActivityData> tSubTypeActivityDataList = new tSubTypeActivityBL().getAllData();
        List<tPlanogramMobileData> tPlanogramMobileDataList = new tPlanogramMobileBL().getAllData();

        arrData = new ArrayList<>();
        if (tPlanogramMobileDataList.size() > 0) {
            for (tPlanogramMobileData dt : tPlanogramMobileDataList) {
                arrData.add(dt.get_OutletName());
            }
            spnDataPlanogram.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnDataPlanogram.setEnabled(true);
        } else if (tPlanogramMobileDataList.size() == 0) {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnDataPlanogram.setAdapter(adapterspn);
            spnDataPlanogram.setEnabled(false);
        }

        arrData = new ArrayList<>();
        if (tSubTypeActivityDataList.size() > 0) {
            for (tSubTypeActivityData dt : tSubTypeActivityDataList) {
                arrData.add(dt.get_txtType() + "-" + dt.get_txtName());
            }
            spnSubTypeActivity.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnSubTypeActivity.setEnabled(true);
        } else if (tSubTypeActivityDataList.size() == 0) {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnSubTypeActivity.setAdapter(adapterspn);
            spnSubTypeActivity.setEnabled(false);
        }

        arrData = new ArrayList<>();
        if (typeSubmissionDataList.size() > 0) {
            for (mTypeSubmissionMobile dt : typeSubmissionDataList) {
                arrData.add(dt.get_txtMasterID() + "-" + dt.get_txtNamaMasterData());
            }
            spnTypeSubmission.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnTypeSubmission.setEnabled(true);
        } else if (typeSubmissionDataList.size() == 0) {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnTypeSubmission.setAdapter(adapterspn);
            spnTypeSubmission.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (mProductSPGDataList.size() > 0) {
            for (mProductSPGData dt : mProductSPGDataList) {
                arrData.add(dt.get_txtMasterId() + " - " + dt.get_txtProductBrandDetailGramName());
            }
            spnProdSPGCusBased.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnProdSPGCusBased.setEnabled(true);
        } else if (mProductSPGDataList.size() == 0) {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnProdSPGCusBased.setAdapter(adapterspn);
            spnProdSPGCusBased.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (mProductPICDataList.size() > 0) {
            for (mProductPICData dt : mProductPICDataList) {
                arrData.add(dt.get_txtMasterId() + " - " + dt.get_txtProductBrandDetailGramName());
            }
            spnProdPICCusBased.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnProdPICCusBased.setEnabled(true);
        } else if (mProductPICDataList.size() == 0) {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnProdPICCusBased.setAdapter(adapterspn);
            spnProdPICCusBased.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (productCompetitorDataList.size() > 0) {
            for (mProductCompetitorData dt : productCompetitorDataList) {
                arrData.add(dt.get_txtProductDetailCode() + "(" + dt.get_txtProdukKompetitorID() + ")");
            }
            spnProductComp.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnProductComp.setEnabled(true);
        } else if (productCompetitorDataList.size() == 0) {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnProductComp.setAdapter(adapterspn);
            spnProductComp.setEnabled(false);
        }


        arrData = new ArrayList<>();
        if (listDataBranch.size() > 0) {
            for (mEmployeeBranchData dt : listDataBranch) {
                arrData.add(dt.get_txtBranchCode() + " - " + dt.get_txtBranchName());
            }
            spnBranch.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnBranch.setEnabled(true);
        } else if (listDataBranch.size() == 0) {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnBranch.setAdapter(adapterspn);
            spnBranch.setEnabled(false);
        }

        arrData = new ArrayList<>();
        if (parentDataList.size() > 0 && kategoriDataList.size() > 0 && jawabanDataList.size() > 0 && typePertanyaanDataList.size() > 0 && pertanyaanDataList.size() > 0 && tGroupQuestionMappingDataList.size() > 0) {
            arrData.add("Quesioner  Ready");
            spnQuiz.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnQuiz.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnQuiz.setAdapter(adapterspn);
            spnQuiz.setEnabled(false);
        }

        arrData = new ArrayList<>();
        if (listVisitPlanRealisasi.size() > 0) {
            for (tVisitPlanRealisasiData dt : listVisitPlanRealisasi) {
                arrData.add(dt.get_txtOutletName() + " - " + dt.get_txtDesc() + " - " + dt.get_dtDate());
            }
            spnTrVisitPlan.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnTrVisitPlan.setEnabled(true);
        } else if (listVisitPlanRealisasi.size() == 0) {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, strip);
            spnTrVisitPlan.setAdapter(adapterspn);
            spnTrVisitPlan.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (listDataCategoryVisitPlan.size() > 0) {
            for (mCategoryVisitPlanData dt : listDataCategoryVisitPlan) {
                arrData.add(dt.getIntCategoryVisitPlan() + " - " + dt.getTxtCatVisitPlan());
            }
            spnVisitPlan.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnVisitPlan.setEnabled(true);
        } else if (listDataCategoryVisitPlan.size() == 0) {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnVisitPlan.setAdapter(adapterspn);
            spnVisitPlan.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (listDataLeave.size() > 0) {
            for (mTypeLeaveMobileData dt : listDataLeave) {
                arrData.add(dt.get_intTipeLeave() + " - " + dt.get_txtTipeLeaveName());
            }
            spnLeave.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnLeave.setEnabled(true);
        } else if (listDataLeave.size() == 0) {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnLeave.setAdapter(adapterspn);
            spnLeave.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (listDataArea.size() > 0) {
            for (mEmployeeAreaData dt : listDataArea) {
                arrData.add(dt.get_txtOutletCode() + " - " + dt.get_txtOutletName());
            }
            spnOutlet.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnOutlet.setEnabled(true);
        } else if (listDataArea.size() == 0) {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnOutlet.setAdapter(adapterspn);
            spnOutlet.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (listDataProduct.size() > 0) {
            for (mEmployeeSalesProductData dt : listDataProduct) {
                arrData.add(dt.get_txtProductBrandDetailGramName());
            }
            spnProduct.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnProduct.setEnabled(true);
        } else if (listDataProduct.size() == 0) {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnProduct.setAdapter(adapterspn);
            spnProduct.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (listmProductBrandData.size() > 0) {
            for (mProductBrandHeaderData dt : listmProductBrandData) {
                arrData.add(dt.get_txtProductBrandName());
            }
            spnBrand.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnBrand.setEnabled(true);
        } else if (listmProductBrandData.size() == 0) {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnBrand.setAdapter(adapterspn);
            spnBrand.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (listtSalesProductHeaderData != null) {
            for (tSalesProductHeaderData dt : listtSalesProductHeaderData) {
                arrData.add(dt.get_txtNoSo());
            }
            spnReso.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnReso.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnReso.setAdapter(adapterspn);
            spnReso.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (listtCustomerBasedHeaderData != null && listtCustomerBasedHeaderData.size() != 0) {
            for (tCustomerBasedMobileHeaderData dt : listtCustomerBasedHeaderData) {
                arrData.add(dt.get_txtSubmissionId());
            }
            spnCustomerBase.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnCustomerBase.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnCustomerBase.setAdapter(adapterspn);
            spnCustomerBase.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (listtActivityData != null && listtActivityData.size() != 0) {
            for (tActivityData dt : listtActivityData) {
                arrData.add(dt.get_intFlag() + "-" + dt.get_txtDesc());
            }
            spnActivity.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnActivity.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnActivity.setAdapter(adapterspn);
            spnActivity.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (listtActivityMobileData != null && listtActivityMobileData.size() != 0) {
            for (tActivityMobileData dt : listtActivityMobileData) {
                arrData.add(dt.get_intFlag() + "-" + dt.get_txtDesc());
            }
            spnActivityV2.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnActivityV2.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnActivityV2.setAdapter(adapterspn);
            spnActivityV2.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (listtAbsenUserData != null) {
            for (tAbsenUserData dt : listtAbsenUserData) {
                arrData.add(dt.get_txtBranchName() + " - " + dt.get_txtOutletName());
            }
            spnAbsen.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnAbsen.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnAbsen.setAdapter(adapterspn);
            spnAbsen.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (listPurchaseOrderHeaderData != null) {
            for (tPurchaseOrderHeaderData dt : listPurchaseOrderHeaderData) {
                arrData.add(dt.get_txtNoOrder());
            }
            spnDataPO.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnDataPO.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnDataPO.setAdapter(adapterspn);
            spnDataPO.setEnabled(false);
        }
        arrData = new ArrayList<>();
        if (listQuantityStockHeaderData != null) {
            for (tSalesProductQuantityHeaderData dt : listQuantityStockHeaderData) {
                arrData.add(dt.get_txtQuantityStock());
            }
            spnDataQuantityStock.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnDataQuantityStock.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnDataQuantityStock.setAdapter(adapterspn);
            spnDataQuantityStock.setEnabled(false);
        }

        arrData = new ArrayList<>();
        if (listKoordinasiOutletData != null && listKoordinasiOutletData.size() != 0) {
            for (KoordinasiOutletData dt : listKoordinasiOutletData) {
                arrData.add(dt.get_txtKeterangan());
            }
            spnKordinasiOutlet.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnKordinasiOutlet.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnKordinasiOutlet.setAdapter(adapterspn);
            spnKordinasiOutlet.setEnabled(false);
        }

        arrData = new ArrayList<>();
        if (listtAbsenUserData != null) {
            for (tLeaveMobileData dt : listtLeaveData) {
                arrData.add(dt.get_txtTypeAlasanName());
            }
            spnDataLeave.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnDataLeave.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnDataLeave.setAdapter(adapterspn);
            spnDataLeave.setEnabled(false);
        }
    }

    private class MyAdapter extends ArrayAdapter<String> {
        private List<String> arrayDataAdapyter;
        private Context Ctx;

        List<String> getArrayDataAdapyter() {
            return arrayDataAdapyter;
        }

        void setArrayDataAdapyter(List<String> arrayDataAdapyter) {
            this.arrayDataAdapyter = arrayDataAdapyter;
        }

        public Context getCtx() {
            return Ctx;
        }

        public void setCtx(Context ctx) {
            Ctx = ctx;
        }

        MyAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            setCtx(context);
            setArrayDataAdapyter(objects);
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, parent);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, parent);
        }

        View getCustomView(int position, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row = inflater.inflate(R.layout.custom_spinner, parent, false);
            if (getArrayDataAdapyter().size() > 0) {
                TextView label = (TextView) row.findViewById(R.id.tvTitle);
                //label.setText(arrData.get(position));
                label.setText(getArrayDataAdapyter().get(position));
                label.setTextColor(Color.parseColor("#000000"));
                TextView sub = (TextView) row.findViewById(R.id.tvDesc);
                sub.setVisibility(View.INVISIBLE);
                sub.setVisibility(View.GONE);
                row.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            //sub.setText(mydata2[position]);
            return row;
        }

    }

    private Boolean checkVisibility(LinearLayout view) {
        return view.getVisibility() == View.VISIBLE;
    }

    private class AsyncCallDownloadAll extends AsyncTask<JSONArray, Void, List<dataJson>> {
        @Override
        protected List<dataJson> doInBackground(JSONArray... params) {
            //android.os.Debug.waitForDebugger();
            JSONArray Json;
            List<dataJson> listDataJson = new ArrayList<>();
            dataJson dtdataJson = new dataJson();
            try {
//                new mPriceInOutletBL().DownloadmPriceInOutlet(pInfo.versionName);

                if (ll_subtypeactivity != null && checkVisibility(ll_subtypeactivity)) {
                    Json = new tSubTypeActivityBL().DownloadtSubTypeActivity(pInfo.versionName);
                    SaveDatatSubTypeActivityData(Json);
                }

                if (ll_branch != null && checkVisibility(ll_branch)) {
                    Json = new mEmployeeBranchBL().DownloadEmployeeBranch2(pInfo.versionName);
                    SaveDatamEmployeeBranchData(Json);
                }
                if (ll_type_leave != null && checkVisibility(ll_type_leave)) {
                    Json = new mTypeLeaveBL().DownloadTypeLeave2(pInfo.versionName);
                    SaveDatamTypeLeaveMobileData(Json);
                }
                if (ll_product != null && checkVisibility(ll_product)) {
                    Json = new mEmployeeSalesProductBL().DownloadEmployeeSalesProduct(pInfo.versionName);
                    SaveDatamProductBarcodeData(Json);
                }
                if (ll_brand != null && checkVisibility(ll_brand)) {
                    Json = new mProductBrandHeaderBL().DownloadBrandHeader(pInfo.versionName);
                    SaveDatamProductBarcodeData(Json);
                }

                if (ll_kategoriVisitPlan != null && checkVisibility(ll_kategoriVisitPlan)) {
                    Json = new mCategoryVisitPlanBL().DownloadCategoryVisitPlanData(pInfo.versionName);
                    SaveDatamCategoryVisitPlanData(Json);
                }

                if (ll_dataVisitPlan != null && checkVisibility(ll_dataVisitPlan)) {
                    Json = new tVisitPlanRealisasiBL().DownloadRealisasiVisitPlan(pInfo.versionName);
                    SaveDatatTransaksiVisitPlanHeaderData(Json);
                    SaveDatatTransaksiVisitPlanData(Json);
                }

                Json = new mEmployeeAreaBL().DownloadEmployeeArea2(pInfo.versionName);
                SaveDatamEmployeeAreaData(Json);

                if (ll_reso != null && checkVisibility(ll_reso)) {
                    Json = new tSalesProductHeaderBL().DownloadReso(pInfo.versionName);
                    Iterator i = Json.iterator();
                    org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
                    int boolValid = Integer.valueOf(String.valueOf(innerObj.get("_pboolValid")));
                    if (boolValid == 1) SaveDatatSalesProductData(Json);
                }

                if (ll_purchase_order != null && checkVisibility(ll_purchase_order)) {
                    new tPurchaseOrderHeaderBL().DownloadNOPO(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
                    Json = new tPurchaseOrderHeaderBL().DownloadTransactionPO(pInfo.versionName);
                    Iterator j = Json.iterator();
                    org.json.simple.JSONObject innerObj_po = (org.json.simple.JSONObject) j.next();
                    int boolValid_po = Integer.valueOf(String.valueOf(innerObj_po.get("_pboolValid")));
                    if (boolValid_po == 1) SaveDatatPurchaseOrderData(Json);
                }

                if (ll_dataQuesioner != null && checkVisibility(ll_dataQuesioner)) {
                    Json = new mParentBL().DownlaodDataQuesioner(pInfo.versionName);
                    Iterator x = Json.iterator();
                    org.json.simple.JSONObject innerObj_Quiz = (org.json.simple.JSONObject) x.next();
                    int boolValid_po = Integer.valueOf(String.valueOf(innerObj_Quiz.get("_pboolValid")));
                    if (boolValid_po == 1) SaveDataQuesioner(Json);
                }

                if (ll_dataQuantityStock != null && checkVisibility(ll_dataQuantityStock)) {
                    new tSalesProductQuantityHeaderBL().DownloadNOQuantityStock(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
                    Json = new tSalesProductQuantityHeaderBL().DownloadTransactionQuantityStock(pInfo.versionName);
                    Iterator k = Json.iterator();
                    org.json.simple.JSONObject innerObj_quantityStock = (org.json.simple.JSONObject) k.next();
                    int boolValid_quantityStock = Integer.valueOf(String.valueOf(innerObj_quantityStock.get("_pboolValid")));
                    if (boolValid_quantityStock == 1) SaveDatatSalesProductQuantityData(Json);
                }

                Json = new trackingLocationBL().DownloadTrackingLocation(pInfo.versionName);
                Iterator l = Json.iterator();
                org.json.simple.JSONObject innerObj_trackingLocation = (org.json.simple.JSONObject) l.next();
                int boolValid_trackingLocation = Integer.valueOf(String.valueOf(innerObj_trackingLocation.get("_pboolValid")));
                if (boolValid_trackingLocation == 1) SaveDataTrackingLocationData(Json);

                if (ll_dataKordinasiOutlet != null && checkVisibility(ll_dataKordinasiOutlet)) {
                    Json = new KoordinasiOutletBL().DownloadDataKoordinasiOutlet(pInfo.versionName);
                    Iterator m = Json.iterator();
                    org.json.simple.JSONObject innerObj_koordinasiOutlet = (org.json.simple.JSONObject) m.next();
                    int boolValid_koordinasiOutlet = Integer.valueOf(String.valueOf(innerObj_koordinasiOutlet.get("_pboolValid")));
                    if (boolValid_koordinasiOutlet == 1) SaveDataKoordinasiOutletData(Json);
                }

                if (ll_data_activity != null && checkVisibility(ll_data_activity)) {
                    Json = new tActivityBL().DownloadActivity(pInfo.versionName);
                    SaveDatatActivityData(Json);
                }
                if (ll_data_activityV2 != null && checkVisibility(ll_data_activityV2)) {
                    Json = new tActivityMobileBL().DownloadActivityV2(pInfo.versionName);
                    SaveDatatActivityDataV2(Json);
                }
                if (ll_data_customerbased != null && checkVisibility(ll_data_customerbased)) {
                    Json = new tCustomerBasedMobileHeaderBL().DownloadCustomerBase(pInfo.versionName);
                    SaveDatatCustomerBasedData(Json);
                }
                if (ll_absen != null && checkVisibility(ll_absen)) {
                    Json = new tAbsenUserBL().DownloadAbsen(pInfo.versionName);
                    SaveDatatAbsenUserData(Json);
                }
                if (ll_data_leave != null && checkVisibility(ll_data_leave)) {
                    Json = new tLeaveMobileBL().DownloadDataLeave(pInfo.versionName);
                    SaveDatatLeaveData(Json);
                }
                if (ll_product_competitor != null && checkVisibility(ll_product_competitor)) {
                    Json = new mProductCompetitorBL().DownloadProdctCompetitor(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
                    SaveDatammProductCompetitorData(Json);
                }
                if (ll_product_spg != null && checkVisibility(ll_product_spg)) {
                    Json = new mProductSPGBL().DownloadProductSPG(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
                    SaveDatammProductSPGData(Json);
                }
                if (ll_product_pic != null && checkVisibility(ll_product_pic)) {
                    Json = new mProductPICBL().DownloadProductPIC(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
                    SaveDatammProductPICData(Json);
                }
                if (ll_type_submission != null && checkVisibility(ll_type_submission)) {
                    Json = new mTypeSubmissionMobileBL().DownloadTypeSubmission(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
                    SaveDatamTypeSubmissionMobile(Json);
                }

                dtdataJson.setIntResult("1");
            } catch (Exception e) {
                dtdataJson.setIntResult("0");
                //dtdataJson.setTxtMessage(e.getMessage().toString());
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            listDataJson.add(dtdataJson);
            return listDataJson;
        }

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onPostExecute(List<dataJson> listdataJson) {
            if (listdataJson.get(0).getIntResult().equals("0")) {
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessUnableToConnect, false);
                Dialog.dismiss();

            } else {
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
                Dialog.dismiss();
                checkingDataTable();
            }
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(new clsHardCode().txtMessGetAllData);
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }


    private class AsyncCallDownloadAllBundleData extends AsyncTask<JSONArray, Void, List<dataJson>> {
        @Override
        protected List<dataJson> doInBackground(JSONArray... params) {
            //android.os.Debug.waitForDebugger();
            JSONArray Json;
            List<dataJson> listDataJson = new ArrayList<>();
            dataJson dtdataJson = new dataJson();
            try {
                new mDownloadMasterData_mobileBL().GetBundleMasterAndTransactionAll(pInfo.versionName);

                dtdataJson.setIntResult("1");
            } catch (Exception e) {
                dtdataJson.setIntResult("0");
                //dtdataJson.setTxtMessage(e.getMessage().toString());
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            listDataJson.add(dtdataJson);
            return listDataJson;
        }

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onPostExecute(List<dataJson> listdataJson) {
            if (listdataJson.get(0).getIntResult().equals("0")) {
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessUnableToConnect, false);
                Dialog.dismiss();

            } else {
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
                Dialog.dismiss();
                checkingDataTable();
            }
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(new clsHardCode().txtMessGetAllData);
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }


    private List<String> SaveDatamCategoryVisitPlanData(JSONArray JData) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        Iterator i = JData.iterator();
        List<mCategoryVisitPlanData> _Listdata = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                mCategoryVisitPlanData _data = new mCategoryVisitPlanData();
                _data.setIntCategoryVisitPlan((String) innerObj.get("intCategoryVisitPlan"));
                _data.setTxtCatVisitPlan((String) innerObj.get("txtCatVisitPlan"));
                _data.setBitActive((String) innerObj.get("bitActive"));
                _array.add(_data.getIntCategoryVisitPlan());
                _Listdata.add(_data);
            } else {
                break;
            }
        }
        new mCategoryVisitPlanBL().saveData(_Listdata);
        return _array;
    }

    private List<String> SaveDatatTransaksiVisitPlanData(JSONArray JData) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        Iterator i = JData.iterator();
        List<tVisitPlanRealisasiData> _Listdata = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            String ListoftTransaksiRealisasiVisitPlanData = String.valueOf(String.valueOf(innerObj.get("ListoftTransaksiRealisasiVisitPlanData")));
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            clsHelper _help = new clsHelper();
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                try {
                    JSONArray JsonArrayListoftTransaksiRealisasiVisitPlanData = _help.ResultJsonArray(ListoftTransaksiRealisasiVisitPlanData);
                    //                    List<tVisitPlanRealisasiData> datadetail = new tVisitPlanRealisasiBL().GetAllData();
                    for (Object aJsonArrayListoftTransaksiRealisasiVisitPlanData : JsonArrayListoftTransaksiRealisasiVisitPlanData) {
                        JSONObject innerObjListoftTransaksiRealisasiVisitPlanData = (JSONObject) aJsonArrayListoftTransaksiRealisasiVisitPlanData;
                        int boolValid2 = Integer.valueOf(String.valueOf(innerObjListoftTransaksiRealisasiVisitPlanData.get(dtAPIDATA.boolValid)));
                        if (boolValid2 == Integer.valueOf(new clsHardCode().intSuccess)) {
                            tVisitPlanRealisasiData _data = new tVisitPlanRealisasiData();
                            _data.set_txtDataIDRealisasi((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("txtDataIdRealisasi"));
                            _data.set_intCategoryVisitPlan((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("intCategoryVisitPlan"));
                            _data.set_intDetailID((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("intDetailId"));
                            _data.set_intHeaderID((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("intHeaderId"));
                            _data.set_intUserID((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("intUserId"));
                            _data.set_txtOutletCode((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("txtOutletCode"));
                            _data.set_txtOutletName((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("txtOutletName"));
                            _data.set_txtBranchCode((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("txtBranchCode"));
                            _data.set_txtBranchCode((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("txtBranchName"));
                            _data.set_dtDate((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("dtDate"));
                            _data.set_intBobot((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("intBobot"));
                            _data.set_txtDesc((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("txtDesc"));
                            _data.set_bitActive((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("bitActive"));
                            _data.set_txtLongSource((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("txtLong"));
                            _data.set_txtLatSource((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("txtLat"));
                            _data.set_txtAcc((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("txtAcc"));
                            _data.set_intSubmit("0");
                            _data.set_intPush("0");
                            _data.set_intCheckout("0");
                            _array.add(_data.get_txtOutletName() + "-" + _data.get_txtDesc());
                            _Listdata.add(_data);
                        } else {
                            break;
                        }
                    }
                    new tVisitPlanRealisasiBL().downloadData(_Listdata);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }

        }

        return _array;
    }

    private void SaveDatatTransaksiVisitPlanHeaderData(JSONArray JData) {
        APIData dtAPIDATA = new APIData();
        Iterator i = JData.iterator();
        List<tVisitPlanHeader_MobileData> _Listdata = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            String ListoftTransaksiRealisasiVisitPlanHeaderData = String.valueOf(String.valueOf(innerObj.get("ListoftTransaksiRealisasiVisitPlanHeaderData")));
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            clsHelper _help = new clsHelper();
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                try {
                    JSONArray JsonArrayListoftTransaksiRealisasiVisitPlanHeaderData = _help.ResultJsonArray(ListoftTransaksiRealisasiVisitPlanHeaderData);
                    for (Object aJsonArrayListoftTransaksiRealisasiVisitPlanHeaderData : JsonArrayListoftTransaksiRealisasiVisitPlanHeaderData) {
                        JSONObject innerObjListoftTransaksiRealisasiVisitPlanHeaderData = (JSONObject) aJsonArrayListoftTransaksiRealisasiVisitPlanHeaderData;
                        int boolValid2 = Integer.valueOf(String.valueOf(innerObjListoftTransaksiRealisasiVisitPlanHeaderData.get(dtAPIDATA.boolValid)));
                        if (boolValid2 == Integer.valueOf(new clsHardCode().intSuccess)) {
                            tVisitPlanHeader_MobileData _data = new tVisitPlanHeader_MobileData();
                            _data.set_intHeaderId((String) innerObjListoftTransaksiRealisasiVisitPlanHeaderData.get("intHeaderId"));
                            _data.set_txtUserId((String) innerObjListoftTransaksiRealisasiVisitPlanHeaderData.get("txtUserId"));
                            _data.set_txtPeriode((String) innerObjListoftTransaksiRealisasiVisitPlanHeaderData.get("txtPeriode"));
                            _data.set_txtGuidUnplan((String) innerObjListoftTransaksiRealisasiVisitPlanHeaderData.get("txtGuidIdUnplan"));
                            _data.set_intUnplan((String) innerObjListoftTransaksiRealisasiVisitPlanHeaderData.get("intUnplan"));
                            _data.set_txtBranchCode((String) innerObjListoftTransaksiRealisasiVisitPlanHeaderData.get("txtBranchCode"));
                            _data.set_bitActive((String) innerObjListoftTransaksiRealisasiVisitPlanHeaderData.get("bitActive"));
                            _data.set_dtStart((String) innerObjListoftTransaksiRealisasiVisitPlanHeaderData.get("dtStart"));
                            _data.set_dtEnd((String) innerObjListoftTransaksiRealisasiVisitPlanHeaderData.get("dtEnd"));
                            _data.set_intSumBobot((String) innerObjListoftTransaksiRealisasiVisitPlanHeaderData.get("intSumBobot"));
                            _data.set_intRealisasi((String) innerObjListoftTransaksiRealisasiVisitPlanHeaderData.get("intRealisasi"));
//                            _array.add(_data.get_txtOutletName()+"-"+_data.get_txtDesc());
                            _data.set_intSubmit("0");
                            _data.set_intPush("0");
                            _Listdata.add(_data);
                        } else {
                            break;
                        }

                    }
                    new tVisitPlanHeader_MobileBL().saveData(_Listdata);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }

        }

//        return _array;
    }


    private List<String> SaveDatamEmployeeBranchData(JSONArray JData) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        Iterator i = JData.iterator();
        List<mEmployeeBranchData> _Listdata = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                mEmployeeBranchData _data = new mEmployeeBranchData();

                _data.set_EmpId((String) innerObj.get("EmpId"));
                _data.set_txtBranchCode((String) innerObj.get("IntBranchId"));
                _data.set_intID((String) innerObj.get("IntID"));
                _data.set_txtBranchCode((String) innerObj.get("TxtBranchCode"));
                _data.set_txtBranchName((String) innerObj.get("TxtBranchName"));
                _data.set_txtNIK((String) innerObj.get("TxtNIK"));
                _data.set_txtName((String) innerObj.get("TxtName"));
                _array.add(_data.get_txtBranchCode() + " - " + _data.get_txtBranchName());
                _Listdata.add(_data);
            } else {
                break;
            }
        }
        new mEmployeeBranchBL().saveData(_Listdata);
        return _array;
    }


    private List<String> SaveDatatSalesProductData(JSONArray JData) {
        List<String> _array;
        _array = new ArrayList<>();
        for (Object aJData : JData) {
            JSONObject innerObj = (JSONObject) aJData;

            try {
                JSONArray JsonArray_header = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDatatSalesProductHeader_mobile")));
                if (JsonArray_header != null) {

                    for (Object aJsonArray_header : JsonArray_header) {
                        tSalesProductHeaderData _data = new tSalesProductHeaderData();
                        JSONObject innerObj_detail = (JSONObject) aJsonArray_header;
                        _data.set_txtNoSo(String.valueOf(innerObj_detail.get("TxtNoSO")));
                        _data.set_txtBranchCode(String.valueOf(innerObj_detail.get("TxtBranchCode")));
                        _data.set_intSubmit("1");
                        _data.set_intSync("1");
                        _data.set_intSumAmount(String.valueOf(innerObj_detail.get("IntSumAmount")));
                        _data.set_UserId(String.valueOf(innerObj_detail.get("txtUserId")));
                        _data.set_intId(String.valueOf(innerObj_detail.get("TxtDataId")));
                        _data.set_txtKeterangan(String.valueOf(innerObj_detail.get("TxtKeterangan")));
                        _data.set_intSumItem(String.valueOf(innerObj_detail.get("IntSumItem")));
                        _data.set_txtBranchName(String.valueOf(innerObj_detail.get("TxtBranchName")));
                        _data.set_txtNIK(String.valueOf(innerObj_detail.get("TxtNik")));
                        _data.set_OutletCode(String.valueOf(innerObj_detail.get("TxtOutletCode")));
                        _data.set_OutletName(String.valueOf(innerObj_detail.get("TxtOutletName")));
                        _data.set_dtDate(String.valueOf(innerObj_detail.get("DtDate")));
                        new tSalesProductHeaderBL().SaveData(_data);
                    }

                    JSONArray JsonArray_detail = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDatatSalesProductDetail_mobile")));
                    Iterator k = JsonArray_detail.iterator();

                    clsMainBL _clsMainBL = new clsMainBL();
                    SQLiteDatabase _db = _clsMainBL.getDb();

                    while (k.hasNext()) {
                        tSalesProductDetailData _data = new tSalesProductDetailData();
                        JSONObject innerObj_detail = (JSONObject) k.next();
                        _data.set_txtNoSo(String.valueOf(innerObj_detail.get("TxtNoSO")));
                        _data.set_txtNameProduct(String.valueOf(innerObj_detail.get("TxtNameProduct")));
                        _data.set_txtNoSo(String.valueOf(innerObj_detail.get("TxtNoSO")));
                        _data.set_txtCodeProduct(String.valueOf(innerObj_detail.get("TxtCodeProduct")));
                        _data.set_intTotal(String.valueOf(innerObj_detail.get("IntTotal")));
                        _data.set_intPrice(String.valueOf(innerObj_detail.get("IntPrice")));
                        _data.set_intQty(String.valueOf(innerObj_detail.get("IntQty")));
                        _data.set_intId(String.valueOf(innerObj_detail.get("TxtDataId")));
                        new tSalesProductDetailDA(_db).SaveDatatSalesProductDetailData(_db, _data);
                    }
                } else {
                    new clsMainActivity().showCustomToast(getContext(), "Data not found", false);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return _array;
    }

    private List<String> SaveDatatActivityData(JSONArray JData) {
        List<String> _array;
        _array = new ArrayList<>();
        Iterator i = JData.iterator();
        List<tActivityData> ListdataActivity = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();

            tActivityData _data = new tActivityData();
            _data.set_dtActivity(String.valueOf(innerObj.get("DtActivity")));
            _data.set_intActive(String.valueOf(innerObj.get("IntActive")));
            _data.set_intSubmit("1");
            _data.set_intIdSyn("1");
            _data.set_intId(String.valueOf(innerObj.get("IntIdData")));
            _data.set_txtBranch(String.valueOf(innerObj.get("TxtCabId")));
            _data.set_txtDesc(String.valueOf(innerObj.get("TxtDesc")));
            _data.set_txtDeviceId(String.valueOf(innerObj.get("TxtDeviceId")));
            _data.set_txtOutletCode(String.valueOf(innerObj.get("TxtOutletCode")));
            _data.set_txtOutletName(String.valueOf(innerObj.get("TxtOutletName")));
            _data.set_txtUserId(String.valueOf(innerObj.get("TxtUserId")));
            _data.set_intFlag(String.valueOf(innerObj.get("TxtType")));
            _data.set_intActive(String.valueOf(innerObj.get("IntActive")));

            String url1 = String.valueOf(innerObj.get("TxtLinkImg1"));
            String url2 = String.valueOf(innerObj.get("TxtLinkImg2"));

            byte[] logoImage1 = getLogoImage(url1);
            byte[] logoImage2 = getLogoImage(url2);

            if (logoImage1 != null) {
                _data.set_txtImg1(logoImage1);
            }

            if (logoImage2 != null) {
                _data.set_txtImg2(logoImage2);
            }

            ListdataActivity.add(_data);
        }

        if (ListdataActivity.size() > 0) {
            new tActivityBL().saveData(ListdataActivity);
        }

        return _array;
    }

    private List<String> SaveDatatActivityDataV2(JSONArray JData) {
        List<String> _array;
        _array = new ArrayList<>();
        Iterator i = JData.iterator();
        List<tActivityMobileData> ListdataActivity = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();

            tActivityMobileData _data = new tActivityMobileData();
            _data.set_dtActivity(String.valueOf(innerObj.get("DtActivity")));
            _data.set_intActive(String.valueOf(innerObj.get("IntActive")));
            _data.set_intSubmit("1");
            _data.set_intIdSyn("1");
            _data.set_intId(String.valueOf(innerObj.get("IntIdData")));
            _data.set_txtBranch(String.valueOf(innerObj.get("TxtCabId")));
            _data.set_txtDesc(String.valueOf(innerObj.get("TxtDesc")));
            _data.set_txtDeviceId(String.valueOf(innerObj.get("TxtDeviceId")));
            _data.set_txtOutletCode(String.valueOf(innerObj.get("TxtOutletCode")));
            _data.set_txtOutletName(String.valueOf(innerObj.get("TxtOutletName")));
            _data.set_txtUserId(String.valueOf(innerObj.get("TxtUserId")));
            _data.set_intFlag(String.valueOf(innerObj.get("TxtType")));
            _data.set_intActive(String.valueOf(innerObj.get("IntActive")));
            _data.set_intSubTypeActivity(String.valueOf(innerObj.get("TxtSubTypeId")));
            _data.set_txtTypeActivity(String.valueOf(innerObj.get("TxtSubTypeName")));

            String url1 = String.valueOf(innerObj.get("TxtLinkImg1"));
            String url2 = String.valueOf(innerObj.get("TxtLinkImg2"));

            byte[] logoImage1 = getLogoImage(url1);
            byte[] logoImage2 = getLogoImage(url2);

            if (logoImage1 != null) {
                _data.set_txtImg1(logoImage1);
            }

            if (logoImage2 != null) {
                _data.set_txtImg2(logoImage2);
            }

            ListdataActivity.add(_data);
        }

        if (ListdataActivity.size() > 0) {
            new tActivityMobileBL().saveData(ListdataActivity);
        }

        return _array;
    }

    private List<String> SaveDatatSubTypeActivityData(JSONArray JData) {
        List<String> _array;
        List<tSubTypeActivityData> ListDatatSubTypeActivityData;
        _array = new ArrayList<>();
        Iterator i = JData.iterator();
        ListDatatSubTypeActivityData = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            tSubTypeActivityData _data = new tSubTypeActivityData();
            String txtValid = String.valueOf(innerObj.get("_pboolValid"));
            if (txtValid.equals("1")) {
                _data.set_bitActive(String.valueOf(innerObj.get("BitActive")));
                _data.set_txtType(String.valueOf(innerObj.get("TxtType")));
                _data.set_txtName(String.valueOf(innerObj.get("TxtName")));
                _data.set_intSubTypeActivity(String.valueOf(innerObj.get("IntSubTypeActivity")));
                ListDatatSubTypeActivityData.add(_data);
            } else {
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
                break;
            }

        }
        if (ListDatatSubTypeActivityData.size() > 0) {
            new tSubTypeActivityBL().saveData(ListDatatSubTypeActivityData);
        }
        return _array;
    }

    private List<String> SaveDatatAbsenUserData(JSONArray JData) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        Iterator i = JData.iterator();
        List<tAbsenUserData> ListdataAbsen = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();

            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                tAbsenUserData _data = new tAbsenUserData();
                _data.set_dtDateCheckIn(String.valueOf(innerObj.get("DtCheckIn")));
                _data.set_dtDateCheckOut(String.valueOf(innerObj.get("DtCheckOut")));
                _data.set_intSubmit("1");
                _data.set_intSync("1");
                _data.set_txtAccuracy(String.valueOf(innerObj.get("TxtAccuracy")));
                _data.set_txtBranchCode(String.valueOf(innerObj.get("TxtBranchCode")));
                _data.set_txtBranchName(String.valueOf(innerObj.get("TxtBranchName")));
                _data.set_txtDeviceId(String.valueOf(innerObj.get("TxtDeviceId")));
                _data.set_txtOutletCode(String.valueOf(innerObj.get("TxtOutletCode")));
                _data.set_txtOutletName(String.valueOf(innerObj.get("TxtOutletName")));
                _data.set_txtUserId(String.valueOf(innerObj.get("TxtUserId")));
                _data.set_intId(String.valueOf(innerObj.get("TxtDataId")));
                _data.set_txtLatitude(String.valueOf(innerObj.get("TxtLatitude")));
                _data.set_txtLongitude(String.valueOf(innerObj.get("TxtLongitude")));
                _data.set_txtUserId(String.valueOf(innerObj.get("TxtUserId")));

                String url1 = String.valueOf(innerObj.get("TxtLinkImg1"));
                String url2 = String.valueOf(innerObj.get("TxtLinkImg2"));

                byte[] logoImage1 = getLogoImage(url1);
                byte[] logoImage2 = getLogoImage(url2);

                if (logoImage1 != null) {
                    _data.set_txtImg1(logoImage1);
                }

                if (logoImage2 != null) {
                    _data.set_txtImg2(logoImage2);
                }

                ListdataAbsen.add(_data);

            } else {
                break;
            }
        }
        new tAbsenUserBL().saveData(ListdataAbsen);
        return _array;
    }

    private List<String> SaveDatatLeaveData(JSONArray JData) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        Iterator i = JData.iterator();
        List<tLeaveMobileData> ListdataLeave = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();

            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                tLeaveMobileData _data = new tLeaveMobileData();
                _data.set_dtLeave(String.valueOf(innerObj.get("DtLeave")));
                _data.set_intLeaveId(String.valueOf(innerObj.get("IntLeaveId")));
                _data.set_intSubmit("1");
                _data.set_intLeaveIdSync("1");
                _data.set_txtAlasan(String.valueOf(innerObj.get("TxtAlasan")));
                _data.set_txtDeviceId(String.valueOf(innerObj.get("TxtDeviceId")));
                _data.set_txtTypeAlasan(String.valueOf(innerObj.get("TxtTypeAlasan")));
                _data.set_txtDeviceId(String.valueOf(innerObj.get("TxtDeviceId")));
                _data.set_txtUserId(String.valueOf(innerObj.get("TxtUserId")));
                _data.set_txtTypeAlasanName(new mTypeLeaveBL().GetDataByintTypeLeave(_data.get_txtTypeAlasan()).get_txtTipeLeaveName());
                ListdataLeave.add(_data);
            } else {
                break;
            }
        }
        new tLeaveMobileBL().saveData(ListdataLeave);
        return _array;
    }

    private byte[] getLogoImage(String url) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL imageUrl = new URL(url);
            URLConnection ucon = imageUrl.openConnection();
            String contentType = ucon.getHeaderField("Content-Type");
            boolean image = contentType.startsWith("image/");

            if (image) {
                InputStream is = ucon.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                ByteArrayBuffer baf = new ByteArrayBuffer(500);
                int current;
                while ((current = bis.read()) != -1) {
                    baf.append((byte) current);
                }

                return baf.toByteArray();
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.d("ImageManager", "Error: " + e.toString());
        }
        return null;
    }

    private List<String> SaveDatatCustomerBasedData(JSONArray JData) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        for (Object aJData : JData) {
            JSONObject innerObj = (JSONObject) aJData;

            try {
                JSONArray JsonArray_header = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDatatCustomerBasedHeader_mobile")));
                if (JsonArray_header != null) {

                    for (Object aJsonArray_header : JsonArray_header) {
                        tCustomerBasedMobileHeaderData _data = new tCustomerBasedMobileHeaderData();
                        JSONObject innerObj_detail = (JSONObject) aJsonArray_header;
                        _data.set_bitActive(String.valueOf(innerObj_detail.get("_bitActive")));
                        _data.set_dtDate(String.valueOf(innerObj_detail.get("_dtDate")));
                        _data.set_intSubmit("1");
                        _data.set_intSync("1");
                        _data.set_intPIC(String.valueOf(innerObj_detail.get("_intPIC")));
                        _data.set_intAge(String.valueOf(innerObj_detail.get("_intAge")));
                        _data.set_intAgeTypeFlag(String.valueOf(innerObj_detail.get("_intAgeTypeFlag")));
                        _data.set_txtLOB(String.valueOf(innerObj_detail.get("_txtLOB")));
                        _data.set_txtTglLahir(String.valueOf(innerObj_detail.get("_txtTglLahir")));
                        _data.set_txtALamat(String.valueOf(innerObj_detail.get("_txtALamat")));
                        _data.set_txtBranchCode(String.valueOf(innerObj_detail.get("_txtBranchCode")));
                        _data.set_txtDeviceId(String.valueOf(innerObj_detail.get("_txtDeviceId")));
                        _data.set_txtEmail(String.valueOf(innerObj_detail.get("_txtEmail")));
                        _data.set_txtGender(String.valueOf(innerObj_detail.get("_txtGender")));
                        _data.set_txtNamaDepan(String.valueOf(innerObj_detail.get("_txtNamaDepan")));
                        _data.set_txtNamaSumberData(String.valueOf(innerObj_detail.get("_txtNamaSumberData")));
                        _data.set_txtPINBBM(String.valueOf(innerObj_detail.get("_txtPINBBM")));
                        _data.set_txtSubmissionCode(String.valueOf(innerObj_detail.get("_txtSubmissionCode")));
                        _data.set_txtSubmissionId(String.valueOf(innerObj_detail.get("_txtSubmissionId")));
                        _data.set_txtSumberData(String.valueOf(innerObj_detail.get("_txtSumberData")));
                        _data.set_txtTelp(String.valueOf(innerObj_detail.get("_txtTelp")));
                        _data.set_txtTelpKantor(String.valueOf(innerObj_detail.get("_txtTelpKantor")));
                        _data.set_intTrCustomerId(String.valueOf(innerObj_detail.get("_txtTrCustomerId")));
                        _data.set_txtUserId(String.valueOf(innerObj_detail.get("_txtUserId")));
                        _data.set_txtTelp2(String.valueOf(innerObj_detail.get("_txtTelp2")));
                        new tCustomerBasedMobileHeaderBL().saveData(_data);
                    }

                    JSONArray JsonArray_detail = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDatatCustomerBasedDetail_mobile")));

                    for (Object aJsonArray_detail : JsonArray_detail) {
                        tCustomerBasedMobileDetailData _data = new tCustomerBasedMobileDetailData();
                        JSONObject innerObj_detail = (JSONObject) aJsonArray_detail;
                        _data.set_bitActive(String.valueOf(innerObj_detail.get("_bitActive")));
                        _data.set_dtInserted(String.valueOf(innerObj_detail.get("_dtInserted")));
                        _data.set_dtUpdated(String.valueOf(innerObj_detail.get("_dtUpdated")));
                        _data.set_intNo(String.valueOf(innerObj_detail.get("_intNo")));
                        _data.set_intPIC(String.valueOf(innerObj_detail.get("_intPIC")));
                        _data.set_txtGender(String.valueOf(innerObj_detail.get("_txtGender")));
                        _data.set_txtInsertedBy(String.valueOf(innerObj_detail.get("_txtInsertedBy")));
                        _data.set_txtNamaDepan(String.valueOf(innerObj_detail.get("_txtNamaDepan")));
                        _data.set_intTrCustomerId(String.valueOf(innerObj_detail.get("_txtTrCustomerId")));
                        _data.set_intTrCustomerIdDetail(String.valueOf(innerObj_detail.get("_txtTrCustomerIdDetail")));
                        _data.set_txtUpdatedBy(String.valueOf(innerObj_detail.get("_txtUpdatedBy")));
                        _data.set_txtUsiaKehamilan(String.valueOf(innerObj_detail.get("_intUsiaKehamilan")));
                        _data.set_txtTglLahir(String.valueOf(innerObj_detail.get("_txtDateOfBirth")));
                        _data.set_intAge(String.valueOf(innerObj_detail.get("_intUmur")));
                        _data.set_intAgeTypeFlag(String.valueOf(innerObj_detail.get("_intAgeTypeFlag")));
                        new tCustomerBasedMobileDetailBL().saveData(_data);
                    }

                    JSONArray JsonArray_detailProduct = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDatatCustomerBasedDetailProduct_mobile")));

                    for (Object aJsonArray_detailProduct : JsonArray_detailProduct) {
                        tCustomerBasedMobileDetailProductData _data = new tCustomerBasedMobileDetailProductData();
                        JSONObject innerObj_detail = (JSONObject) aJsonArray_detailProduct;
                        _data.set_bitActive(String.valueOf(innerObj_detail.get("_bitActive")));
                        _data.set_dtInserted(String.valueOf(innerObj_detail.get("_dtInserted")));
                        _data.set_dtUpdated(String.valueOf(innerObj_detail.get("_dtUpdated")));
                        _data.set_txtProductBrandCode(String.valueOf(innerObj_detail.get("_txtProductBrandCode")));
                        _data.set_txtProductBrandName(String.valueOf(innerObj_detail.get("_txtProductBrandName")));
                        _data.set_intTrCustomerIdDetailProduct(String.valueOf(innerObj_detail.get("_txtTrCustomerIdDetailProduct")));
                        _data.set_txtInsertedBy(String.valueOf(innerObj_detail.get("_txtInsertedBy")));
                        _data.set_intTrCustomerIdDetail(String.valueOf(innerObj_detail.get("_txtTrCustomerIdDetail")));
                        _data.set_txtUpdatedBy(String.valueOf(innerObj_detail.get("_txtUpdatedBy")));
                        _data.set_txtProductBrandQty(String.valueOf(innerObj_detail.get("_intProductBrandQty")));
                        _data.set_txtProductCompetitorCode(String.valueOf(innerObj_detail.get("_txtProductCodeCompetitor")));
                        _data.set_txtProductCompetitorName(String.valueOf(innerObj_detail.get("_txtProductNameCompetitor")));
                        _data.set_txtProductBrandCodeCRM(String.valueOf(innerObj_detail.get("_txtProductBrandCodeCRM")));
                        new tCustomerBasedMobileDetailProductBL().saveData(_data);
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                mEmployeeBranchData _data = new mEmployeeBranchData();

                _data.set_EmpId((String) innerObj.get("EmpId"));
                _data.set_txtBranchCode((String) innerObj.get("IntBranchId"));
                _data.set_intID((String) innerObj.get("IntID"));
                _data.set_txtBranchCode((String) innerObj.get("TxtBranchCode"));
                _data.set_txtBranchName((String) innerObj.get("TxtBranchName"));
                _data.set_txtNIK((String) innerObj.get("TxtNIK"));
                _data.set_txtName((String) innerObj.get("TxtName"));
                _array.add(_data.get_txtBranchCode() + " - " + _data.get_txtBranchName());
//                _Listdata.add(_data);
            } else {
                break;
            }
        }
//        new mEmployeeBranchBL().saveData(_Listdata);
        return _array;
    }

    private List<String> SaveDatamProductBarcodeData(JSONArray JData) {
        List<String> _array = new ArrayList<>();
        APIData dtAPIDATA = new APIData();
        Iterator i = JData.iterator();
        List<mProductBarcodeData> _Listdata = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                mProductBarcodeData _data = new mProductBarcodeData();

                _data.set_intProductCode(String.valueOf(innerObj.get("_intProductCode")));
                _data.set_txtProductCode((String) innerObj.get("_txtProductCode"));
                _data.set_txtBarcode((String) innerObj.get("_txtBarcode"));
                _data.set_txtProductName((String) innerObj.get("_txtProductName"));
                _data.set_intSubmit("1");
                _data.set_intSync("0");
                _array.add(_data.get_txtProductCode() + " - " + _data.get_txtProductName());
                _Listdata.add(_data);
            } else {
                break;
            }
        }
        new mProductBarcodeBL().saveData(_Listdata);
        return _array;
    }

    private class AsyncCallQuis extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new mParentBL().DownlaodDataQuesioner(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray != null && jsonArray.size() > 0) {
                arrData = SaveDataQuesioner(jsonArray);
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Getting Question");
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }
    }

    private class AsyncCallProduct extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
//                Json = new mProductBarcodeBL().DownloadmProductBarcode2(pInfo.versionName);
                Json = new mEmployeeSalesProductBL().DownloadEmployeeSalesProduct(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null && roledata.size() > 0) {
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(new clsHardCode().txtMessGetProduct);
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }
    }

    private class AsyncCallProductBrand extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new mProductBrandHeaderBL().DownloadBrandHeader(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null && roledata.size() > 0) {
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(new clsHardCode().txtMessGetProduct);
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }
    }

    private class AsyncCallReso extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new tSalesProductHeaderBL().DownloadReso(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null && roledata.size() > 0) {
                arrData = SaveDatatSalesProductData(roledata);
                //spnBranch.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Reso");
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }
    }

    private class AsyncCallActivity extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new tActivityBL().DownloadActivity(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null && roledata.size() > 0) {
                arrData = SaveDatatActivityData(roledata);
                //spnBranch.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Activity");
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }
    }

    private class AsyncCallActivityV2 extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new tActivityMobileBL().DownloadActivityV2(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null && roledata.size() > 0) {
                arrData = SaveDatatActivityDataV2(roledata);
                //spnBranch.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting ActivityV2");
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }
    }

    private class AsyncCallCustomerBase extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new tCustomerBasedMobileHeaderBL().DownloadCustomerBase(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null) {
                arrData = SaveDatatCustomerBasedData(roledata);
                //spnBranch.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Customer Base");
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }
    }

    private class AsyncCallAbsen extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new tAbsenUserBL().DownloadAbsen(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null && roledata.size() > 0) {
                arrData = SaveDatatAbsenUserData(roledata);
                //spnBranch.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Absen");
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }
    }

    private class AsyncCallDataPO extends AsyncTask<JSONArray, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new tPurchaseOrderHeaderBL().DownloadTransactionPO(pInfo.versionName);
                new tPurchaseOrderHeaderBL().DownloadNOPO(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Getting Data Purchase Order");
            dialog.setCancelable(false);
//            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray != null && jsonArray.size() > 0) {
                arrData = SaveDatatPurchaseOrderData(jsonArray);
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
            checkingDataTable();
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            dialog.dismiss();
        }
    }

    private class AsyncCallDataQuantityStock extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new tSalesProductQuantityHeaderBL().DownloadTransactionQuantityStock(pInfo.versionName);
                new tSalesProductQuantityHeaderBL().DownloadNOQuantityStock(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
//                new tSalesProductQuantityHeaderBL().DownloadNOQuantityStock(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Getting Data Quantity Stock");
            dialog.setCancelable(false);
//            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray != null && jsonArray.size() > 0) {
                arrData = SaveDatatSalesProductQuantityData(jsonArray);
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
            checkingDataTable();
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            dialog.dismiss();
        }
    }

    private class AsyncCallDataPlanogram extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new tPlanogramMobileBL().DownloadTransactionPlanogram(pInfo.versionName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Getting Data Planogram");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray != null && jsonArray.size() > 0) {
                arrData = SaveDatatPlamogramData(jsonArray);
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
            checkingDataTable();
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            dialog.dismiss();
        }
    }

    private class AsyncCallDataTrackingLocation extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new trackingLocationBL().DownloadTrackingLocation(pInfo.versionName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray != null && jsonArray.size() > 0) {
                arrData = SaveDataTrackingLocationData(jsonArray);
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
            checkingDataTable();
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            dialog.dismiss();
        }
    }

    private class AsyncCallDataKoordinasiOutlet extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new KoordinasiOutletBL().DownloadDataKoordinasiOutlet(pInfo.versionName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Getting Data Koordinasi Outlet");
            dialog.setCancelable(false);
//            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray != null && jsonArray.size() > 0) {
                arrData = SaveDataKoordinasiOutletData(jsonArray);
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
            checkingDataTable();
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            dialog.dismiss();
        }
    }

    private class AsyncCallDataLeave extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new tLeaveMobileBL().DownloadDataLeave(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null && roledata.size() > 0) {
                arrData = SaveDatatLeaveData(roledata);
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Absen");
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }
    }

    private List<String> SaveDatamEmployeeAreaData(JSONArray JData) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        Iterator i = JData.iterator();
        List<mEmployeeAreaData> _Listdata = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
//                String latitude = (String) innerObj.get("txtLatitude");
//                String longitude = (String) innerObj.get("txtLongitude");
//                if ((latitude.equals("") || latitude == null
//                        || longitude.equals("") || longitude == null)) {
//
//                    getActivity().runOnUiThread(new Runnable() {
//                        public void run() {
//                            Toast toast = Toast.makeText(getContext(), "Location Outlet Can't be Found...",
//                                    Toast.LENGTH_SHORT);
//                            toast.setGravity(Gravity.TOP, 25, 400);
//                            toast.show();
//                        }
//                    });
//
//
//                }
                mEmployeeAreaData _data = new mEmployeeAreaData();
                _data.set_intBranchId((String) innerObj.get("IntBranchId"));
                _data.set_intChannelId((String) innerObj.get("IntChannelId"));
                _data.set_intEmployeeId((String) innerObj.get("IntEmployeeId"));
                _data.set_intID((String) innerObj.get("IntID"));
                _data.set_intOutletId((String) innerObj.get("IntOutletId"));
                _data.set_intChannelId((String) innerObj.get("IntChannelId"));
                _data.set_intRayonId((String) innerObj.get("IntRayonId"));
                _data.set_intRegionId((String) innerObj.get("IntRegionId"));
                _data.set_txtBranchCode((String) innerObj.get("TxtBranchCode"));
                _data.set_txtBranchName((String) innerObj.get("TxtBranchName"));
                _data.set_txtNIK((String) innerObj.get("TxtNIK"));
                _data.set_txtName((String) innerObj.get("TxtName"));
                _data.set_txtOutletCode((String) innerObj.get("TxtOutletCode"));
                _data.set_txtOutletName((String) innerObj.get("TxtOutletName"));
                _data.set_txtRayonCode((String) innerObj.get("TxtRayonCode"));
                _data.set_txtRayonName((String) innerObj.get("TxtRayonName"));
                _data.set_txtRegionName((String) innerObj.get("TxtRegionName"));
                _data.set_txtLatitude((String) innerObj.get("txtLatitude"));
                _data.set_txtLongitude((String) innerObj.get("txtLongitude"));

                //hardcode cui..
//                _data.set_txtLatitude("-6.150721");
//                _data.set_txtLongitude("106.887543");

                _array.add(_data.get_txtOutletCode() + " - " + _data.get_txtOutletName());
                _Listdata.add(_data);
            } else {
                break;
            }
        }
        new mEmployeeAreaBL().saveData(_Listdata);
        return _array;
    }

    private class AsyncCallOutlet extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            //android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new mEmployeeAreaBL().DownloadEmployeeArea2(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null && roledata.size() > 0) {
                arrData = SaveDatamEmployeeAreaData(roledata);
                loadData();
                //spnOutlet.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(new clsHardCode().txtMessGetOutlet);
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }

    private class AsyncCallBranch extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            //android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new mEmployeeBranchBL().DownloadEmployeeBranch2(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onPostExecute(JSONArray roledata) {

            if (roledata != null && roledata.size() > 0) {
                arrData = SaveDatamEmployeeBranchData(roledata);
                //spnBranch.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(new clsHardCode().txtMessGetBranch);
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }


    private class AsyncCallCategoryVisitPlan extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            //android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new mCategoryVisitPlanBL().DownloadCategoryVisitPlanData(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onPostExecute(JSONArray roledata) {

            if (roledata != null && roledata.size() > 0) {
                arrData = SaveDatamCategoryVisitPlanData(roledata);
                //spnBranch.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(new clsHardCode().txtMessGetVisitPLan);
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }

    private class AsyncCallSubTypeActivity extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new tSubTypeActivityBL().DownloadtSubTypeActivity(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null && roledata.size() > 0) {
                arrData = SaveDatatSubTypeActivityData(roledata);
                //spnBranch.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Sub Type Activity");
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }
    }

    private class AsyncCalltTransaksiVisitPlan extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            //android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new tVisitPlanRealisasiBL().DownloadRealisasiVisitPlan(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onPostExecute(JSONArray roledata) {

            if (roledata != null && roledata.size() > 0) {
                arrData = SaveDatatTransaksiVisitPlanData(roledata);
                //spnBranch.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                SaveDatatTransaksiVisitPlanHeaderData(roledata);
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(new clsHardCode().txtMessGetVisitPlanTr);
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }

    private void checkingDataTable() {
        List<mEmployeeBranchData> mEmployeeBranchDataList;
        List<mEmployeeSalesProductData> employeeSalesProductDataList;
        List<mEmployeeAreaData> mEmployeeAreaDataList;
        List<mProductBrandHeaderData> mProductBrandHeaderDataList;
        List<mTypeLeaveMobileData> mTypeLeaveMobileDataList;
//        List<mCategoryVisitPlanData> mCategoryVisitPlanList = new ArrayList<>();

        mCategoryVisitPlanBL _mCategoryVisitPlanBL = new mCategoryVisitPlanBL();

//        mCategoryVisitPlanList = _mCategoryVisitPlanBL.GetAllData();

        mEmployeeBranchBL _mEmployeeBranchBL = new mEmployeeBranchBL();
        mEmployeeSalesProductBL _mEmployeeSalesProductBL = new mEmployeeSalesProductBL();
        mEmployeeAreaBL _mEmployeeAreaBL = new mEmployeeAreaBL();
        mProductBrandHeaderBL _mProductBrandHeaderBL = new mProductBrandHeaderBL();
        mTypeLeaveBL _mTypeLeaveBL = new mTypeLeaveBL();


        employeeSalesProductDataList = _mEmployeeSalesProductBL.GetAllData();
        mEmployeeBranchDataList = _mEmployeeBranchBL.GetAllData();
        mEmployeeAreaDataList = _mEmployeeAreaBL.GetAllData();
        mProductBrandHeaderDataList = _mProductBrandHeaderBL.GetAllData();
        mTypeLeaveMobileDataList = _mTypeLeaveBL.GetAllData();

//        List<mEmployeeAreaData> data = _mEmployeeAreaBL.GetAllData();

//        int validate = 0;

        if (mEmployeeBranchDataList.size() > 0
                && employeeSalesProductDataList.size() > 0
                && mEmployeeAreaDataList.size() > 0
                && mProductBrandHeaderDataList.size() > 0
                && mTypeLeaveMobileDataList.size() > 0
//                && mCategoryVisitPlanList.size()>0
                ) {

//            goToMainMenu();
            //validate = 1;

//            for(mEmployeeAreaData dt : data){
//                if(dt.get_txtLatitude()==""||dt.get_txtLatitude()==null&&dt.get_txtLongitude()==""||dt.get_txtLongitude()==null){
//                    validate = 0;
//                }
//            }
        }
        goToMainMenu();

//        if(validate==1){
//            goToMainMenu();
//        }

    }

    private void goToMainMenu() {
        Intent myIntent = new Intent(getContext(), MainMenu.class);
        getActivity().finish();
        startActivity(myIntent);
    }

    private List<String> SaveDatamTypeLeaveMobileData(JSONArray JData) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        Iterator i = JData.iterator();
        List<mTypeLeaveMobileData> _Listdata = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                mTypeLeaveMobileData _data = new mTypeLeaveMobileData();
                _data.set_intTipeLeave(String.valueOf(innerObj.get("IntTipeLeave")));
                _data.set_txtTipeLeaveName(String.valueOf(innerObj.get("TxtTipeLeaveName")));
                _array.add(_data.get_intTipeLeave() + " - " + _data.get_txtTipeLeaveName());
                _Listdata.add(_data);
            } else {
                break;
            }
        }
        new mTypeLeaveBL().saveData(_Listdata);
        return _array;
    }

    private class AsyncCallLeave extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new mTypeLeaveBL().DownloadTypeLeave2(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null && roledata.size() > 0) {
                arrData = SaveDatamTypeLeaveMobileData(roledata);
                //spnLeave.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(new clsHardCode().txtMessGetUserRole);
            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }
    }

    private class AsyncCallDataProdComp extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new mProductCompetitorBL().DownloadProdctCompetitor(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled(JSONArray jsonArray) {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray != null && jsonArray.size() > 0) {
                arrData = SaveDatammProductCompetitorData(jsonArray);
                //spnLeave.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                new clsMainActivity().showCustomToast(getContext(), strMessage, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Product Competitor");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private List<String> SaveDatammProductCompetitorData(JSONArray jsonArray) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        Iterator i = jsonArray.iterator();
        List<mProductCompetitorData> _Listdata = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                strMessage = (String) innerObj.get(dtAPIDATA.strMessage);
                mProductCompetitorData _data = new mProductCompetitorData();
                _data.set_txtID(new clsHelper().GenerateGuid());
                _data.set_txtCRMCode(String.valueOf(innerObj.get("TxtBranchCRMCode")));
                _data.set_GroupProduct(String.valueOf(innerObj.get("TxtGroupProduct")));
                _data.set_txtLobName(String.valueOf(innerObj.get("TxtLobName")));
                _data.set_txtNIK(String.valueOf(innerObj.get("TxtNIK")));
                _data.set_txtName(String.valueOf(innerObj.get("TxtName")));
                _data.set_txtProductDetailCode(String.valueOf(innerObj.get("TxtProductDetailCode")));
                _data.set_txtProdukKompetitorID(String.valueOf(innerObj.get("TxtProdukKompetitorID")));
                _data.set_txtProdukid(String.valueOf(innerObj.get("TxtProdukid")));
                _array.add(_data.get_txtProdukKompetitorID());
                _Listdata.add(_data);
            } else {
                strMessage = (String) innerObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
        new mProductCompetitorBL().deleteAllData();
        new mProductCompetitorBL().saveData(_Listdata);
        return _array;
    }

    private class AsyncCallDataProdSPGCusBased extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new mProductSPGBL().DownloadProductSPG(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled(JSONArray jsonArray) {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray != null && jsonArray.size() > 0) {
                arrData = SaveDatammProductSPGData(jsonArray);
                //spnLeave.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                new clsMainActivity().showCustomToast(getContext(), strMessage, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Product SPG");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private List<String> SaveDatammProductSPGData(JSONArray jsonArray) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        Iterator i = jsonArray.iterator();
        List<mProductSPGData> _Listdata = new ArrayList<>();
        new mProductSPGBL().deleteAllData();
        int intsum = new mProductSPGBL().getContactCount();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                strMessage = (String) innerObj.get(dtAPIDATA.strMessage);
                intsum += 1;
                mProductSPGData _data = new mProductSPGData();
                _data.set_intId(String.valueOf(intsum));
                _data.set_decBobot((String) innerObj.get("DecBobot"));
                _data.set_decHJD((String) innerObj.get("DecHJD"));
                _data.set_txtBrandDetailGramCode((String) innerObj.get("TxtBrandDetailGramCode"));
                _data.set_txtNIK((String) innerObj.get("TxtNIK"));
                _data.set_txtName((String) innerObj.get("TxtName"));
                _data.set_txtProductBrandDetailGramName((String) innerObj.get("TxtProductBrandDetailGramName"));
                _data.set_txtProductDetailCode((String) innerObj.get("TxtProductDetailCode"));
                _data.set_txtProductDetailName((String) innerObj.get("TxtProductDetailName"));
                _data.set_txtLobName((String) innerObj.get("TxtLobName"));
                _data.set_txtMasterId((String) innerObj.get("TxtMasterId"));
                _data.set_txtNamaMasterData((String) innerObj.get("TxtNamaMasterData"));
                _data.set_txtKeterangan((String) innerObj.get("TxtKeterangan"));
                _Listdata.add(_data);
            } else {
                strMessage = (String) innerObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
        new mProductSPGBL().saveData(_Listdata);
        return _array;
    }

    private class AsyncCallDataProdPICCusBased extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new mProductPICBL().DownloadProductPIC(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled(JSONArray jsonArray) {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray != null && jsonArray.size() > 0) {
                arrData = SaveDatammProductPICData(jsonArray);
                //spnLeave.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                new clsMainActivity().showCustomToast(getContext(), strMessage, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Getting Product PIC");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private List<String> SaveDatammProductPICData(JSONArray jsonArray) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        Iterator i = jsonArray.iterator();
        List<mProductPICData> _Listdata = new ArrayList<>();
        new mProductPICBL().deleteAllData();
        int intsum = new mProductPICBL().getContactCount();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                strMessage = (String) innerObj.get(dtAPIDATA.strMessage);
                intsum += 1;
                mProductPICData _data = new mProductPICData();
                _data.set_intId(String.valueOf(intsum));
                _data.set_decBobot((String) innerObj.get("DecBobot"));
                _data.set_decHJD((String) innerObj.get("DecHJD"));
                _data.set_txtBrandDetailGramCode((String) innerObj.get("TxtBrandDetailGramCode"));
                _data.set_txtNIK((String) innerObj.get("TxtNIK"));
                _data.set_txtName((String) innerObj.get("TxtName"));
                _data.set_txtProductBrandDetailGramName((String) innerObj.get("TxtProductBrandDetailGramName"));
                _data.set_txtProductDetailCode((String) innerObj.get("TxtProductDetailCode"));
                _data.set_txtProductDetailName((String) innerObj.get("TxtProductDetailName"));
                _data.set_txtLobName((String) innerObj.get("TxtLobName"));
                _data.set_txtMasterId((String) innerObj.get("TxtMasterId"));
                _data.set_txtNamaMasterData((String) innerObj.get("TxtNamaMasterData"));
                _data.set_txtKeterangan((String) innerObj.get("TxtKeterangan"));
                _Listdata.add(_data);
            } else {
                strMessage = (String) innerObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
        new mProductPICBL().saveData(_Listdata);
        return _array;
    }

    private class AsyncCallTypeSubmission extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new mTypeSubmissionMobileBL().DownloadTypeSubmission(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled(JSONArray jsonArray) {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray != null && jsonArray.size() > 0) {
                arrData = SaveDatamTypeSubmissionMobile(jsonArray);
                loadData();
                new clsMainActivity().showCustomToast(getContext(), strMessage, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            checkingDataTable();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Type Submission");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private List<String> SaveDatamTypeSubmissionMobile(JSONArray jsonArray) {
        List<String> _array = new ArrayList<>();
        APIData dtAPIDATA = new APIData();
        Iterator i = jsonArray.iterator();
        List<mTypeSubmissionMobile> _Listdata = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                strMessage = (String) innerObj.get(dtAPIDATA.strMessage);
                mTypeSubmissionMobile _data = new mTypeSubmissionMobile();
                _data.set_txtMasterID(String.valueOf(innerObj.get("TxtMasterID")));
                _data.set_txtGrupMasterID(String.valueOf(innerObj.get("TxtGrupMasterID")));
                _data.set_txtKeterangan(String.valueOf(innerObj.get("TxtKeterangan")));
                _data.set_txtNamaMasterData(String.valueOf(innerObj.get("TxtNamaMasterData")));
                _data.set_intLastActiveSelection("0");
                _array.add(_data.get_txtMasterID());
                _Listdata.add(_data);
            } else {
                strMessage = (String) innerObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
        new mTypeSubmissionMobileBL().saveData(_Listdata);
        return _array;
    }

    private List<String> SaveDataQuesioner(JSONArray jsonArray) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        for (Object aJsonArray : jsonArray) {
            JSONObject innerObj = (JSONObject) aJsonArray;
            try {
                int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
                if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                    new tGroupQuestionMappingBL().deleteAllDatatGroupQuestionMapping();
                    new mKategoriBL().DeletemKategori();
                    new mPertanyaanBL().DeletemPertanyaan();
                    new mParentBL().DeletemParent();
                    new mTypePertanyaanBL().DeletemTypePertanyaan();
                    new mListJawabanBL().DeletemListJawaban();
                    JSONArray jsonArray_parent = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDatamParent_mobile")));
                    for (Object aJsonArray_parent : jsonArray_parent) {
                        mParentData _data = new mParentData();
                        JSONObject innerObj_parent = (JSONObject) aJsonArray_parent;
                        _data.set_intParentId(String.valueOf(innerObj_parent.get("IntParentId")));
                        _data.set_txtParentName(String.valueOf(innerObj_parent.get("TxtParentName")));
                        new mParentBL().SaveDatamParent(_data);
                    }

                    JSONArray jsonArray_kategori = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDatamKategori_mobile")));
                    for (Object aJsonArray_kategori : jsonArray_kategori) {
                        mKategoriData _data = new mKategoriData();
                        JSONObject innerObj_kategori = (JSONObject) aJsonArray_kategori;
                        _data.set_intCategoryId(String.valueOf(innerObj_kategori.get("IntCategoryId")));
                        _data.set_intParentId(String.valueOf(innerObj_kategori.get("IntParentId")));
                        _data.set_txtCategoryName(String.valueOf(innerObj_kategori.get("TxtCategoryName")));
                        new mKategoriBL().SaveData(_data);
                    }

                    JSONArray jsonArray_groupQuestion = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtGroupQuestion_mobile")));
                    for (Object aJsonArray_groupQuestion : jsonArray_groupQuestion) {
                        tGroupQuestionMappingData _data = new tGroupQuestionMappingData();
                        JSONObject innerObj_GroupQuestion = (JSONObject) aJsonArray_groupQuestion;
                        _data.set_intId(String.valueOf(innerObj_GroupQuestion.get("IntQuestionGroupId")));
                        _data.set_txtGroupQuestion(String.valueOf(innerObj_GroupQuestion.get("TxtQuestionGroupName")));
                        _data.set_intRoleId(String.valueOf(innerObj_GroupQuestion.get("TxtRoleName")));
                        _data.set_txtRepeatQuestion(String.valueOf(innerObj_GroupQuestion.get("TxtRepeatQuestion")));
                        _data.set_dtStart(String.valueOf(innerObj_GroupQuestion.get("DtDateStart")));
                        _data.set_dtEnd(String.valueOf(innerObj_GroupQuestion.get("DtDateStart")));
                        new tGroupQuestionMappingBL().saveDatatGroupQuestionMapping(_data);
                    }

                    JSONArray jsonArray_Pertanyaan = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDatamPertanyaan_mobile")));
                    for (Object aJsonArray_Pertanyaan : jsonArray_Pertanyaan) {
                        mPertanyaanData _data = new mPertanyaanData();
                        JSONObject innerObj_Pertanyaan = (JSONObject) aJsonArray_Pertanyaan;
                        _data.set_intQuestionId(String.valueOf(innerObj_Pertanyaan.get("IntQuestionId")));
                        _data.set_intSoalId(String.valueOf(innerObj_Pertanyaan.get("IntSoalId")));
                        _data.set_intCategoryId(String.valueOf(innerObj_Pertanyaan.get("IntCategoryId")));
                        _data.set_txtQuestionDesc(String.valueOf(innerObj_Pertanyaan.get("TxtQuestionDesc")));
                        _data.set_intTypeQuestionId(String.valueOf(innerObj_Pertanyaan.get("IntTypeQuestionId")));
                        _data.set_decBobot(String.valueOf(innerObj_Pertanyaan.get("DecBobot")));
                        _data.set_bolHaveAnswerList(String.valueOf(innerObj_Pertanyaan.get("BolHaveAnswerList")));
                        _data.set_inttGroupQuestionMapping(String.valueOf(innerObj_Pertanyaan.get("InttGroupQuestionMapping")));
                        new mPertanyaanBL().SaveData(_data);
                    }


                    JSONArray jsonArray_listJawaban = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListmListJawaban_mobile")));
                    for (Object aJsonArray_listJawaban : jsonArray_listJawaban) {
                        mListJawabanData _data = new mListJawabanData();
                        JSONObject innerObj_listJawaban = (JSONObject) aJsonArray_listJawaban;
                        _data.set_intListAnswerId(String.valueOf(innerObj_listJawaban.get("IntListAnswerId")));
                        _data.set_intQuestionId(String.valueOf(innerObj_listJawaban.get("IntQuestionId")));
                        _data.set_intTypeQuestionId(String.valueOf(innerObj_listJawaban.get("IntTypeQuestionId")));
                        _data.set_txtKey(String.valueOf(innerObj_listJawaban.get("TxtKey")));
                        _data.set_txtValue(String.valueOf(innerObj_listJawaban.get("TxtValue")));
                        new mListJawabanBL().SaveData(_data);
                    }


                    JSONArray jsonArray_typePertanyaan = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDatamTypePertanyaan_mobile")));

                    for (Object aJsonArray_typePertanyaan : jsonArray_typePertanyaan) {
                        mTypePertanyaanData _data = new mTypePertanyaanData();
                        JSONObject innerObj_TypePertanyaan = (JSONObject) aJsonArray_typePertanyaan;
                        _data.set_intTypeQuestionId(String.valueOf(innerObj_TypePertanyaan.get("IntTypeQuestionId")));
                        _data.set_txtTypeQuestion(String.valueOf(innerObj_TypePertanyaan.get("TxtTypeQuestion")));
                        new mTypePertanyaanBL().SaveData(_data);
                    }
                    
                } else {
                    new clsMainActivity().showCustomToast(getContext(), "Data Not Found", false);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return _array;
    }


    private List<String> SaveDatatPurchaseOrderData(JSONArray jsonArray) {
        List<String> _array;
        _array = new ArrayList<>();
        for (Object aJsonArray : jsonArray) {
            JSONObject innerObj = (JSONObject) aJsonArray;
            try {
                JSONArray jsonArray_header = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDatatPurchaseOrderHeader_mobile")));
                if (jsonArray_header != null) {
                    for (Object aJsonArray_header : jsonArray_header) {
                        tPurchaseOrderHeaderData _data = new tPurchaseOrderHeaderData();
                        JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                        _data.set_intId(String.valueOf(innerObj_header.get("TxtDataId")));
                        _data.set_txtKeterangan(String.valueOf(innerObj_header.get("TxtKeterangan")));
                        _data.set_dtDate(String.valueOf(innerObj_header.get("DtDate")));
                        _data.set_intSumAmount(String.valueOf(innerObj_header.get("IntSumAmount")));
                        _data.set_intSumItem(String.valueOf(innerObj_header.get("IntSumItem")));
                        _data.set_intIdAbsenUser(String.valueOf(innerObj_header.get("IntIdAbsenUser")));
                        _data.set_txtNIK(String.valueOf(innerObj_header.get("TxtNIK")));
                        _data.set_txtNoOrder(String.valueOf(innerObj_header.get("TxtNoPB")));
                        _data.set_OutletCode(String.valueOf(innerObj_header.get("TxtOutletCode")));
                        _data.set_OutletName(String.valueOf(innerObj_header.get("TxtOutletName")));
                        _data.set_txtBranchCode(String.valueOf(innerObj_header.get("TxtBranchCode")));
                        _data.set_txtBranchName(String.valueOf(innerObj_header.get("TxtBranchName")));
                        _data.set_UserId(String.valueOf(innerObj_header.get("TxtUserId")));
                        _data.set_txtRoleId(String.valueOf(innerObj_header.get("TxtRoleId")));
                        _data.set_intSubmit("1");
                        _data.set_intSync("1");
                        new tPurchaseOrderHeaderBL().SaveData(_data);
                    }

                    JSONArray jsonArray_Detail = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDatatPurchaseOrderDetail_mobile")));
                    Iterator k = jsonArray_Detail.iterator();
                    clsMainBL _clsMainBL = new clsMainBL();
                    SQLiteDatabase _db = _clsMainBL.getDb();
                    while (k.hasNext()) {
                        tPurchaseOrderDetailData _data = new tPurchaseOrderDetailData();
                        JSONObject innerObj_detail = (JSONObject) k.next();
                        _data.set_intId(String.valueOf(innerObj_detail.get("TxtTrPurchaseOrderDetail")));
                        _data.set_dtDate(String.valueOf(innerObj_detail.get("DtDate")));
                        _data.set_intPrice(String.valueOf(innerObj_detail.get("IntPrice")));
                        _data.set_intQty(String.valueOf(innerObj_detail.get("IntQty")));
                        _data.set_intTotal(String.valueOf(innerObj_detail.get("IntTotal")));
                        _data.set_txtCodeProduct(String.valueOf(innerObj_detail.get("TxtCodeProduct")));
                        _data.set_txtNameProduct(String.valueOf(innerObj_detail.get("TxtNameProduct")));
                        _data.set_txtNoOrder(String.valueOf(innerObj_detail.get("TxtNoPB")));
                        _data.set_txtKeterangan(String.valueOf(innerObj_detail.get("TxtKeterangan")));
                        _data.set_intActive(String.valueOf(innerObj_detail.get("BitActive")));
                        new tPurchaseOrderDetailDA(_db).SaveDatatPurchaseOrderDetailData(_db, _data);
                    }
                } else {
                    new clsMainActivity().showCustomToast(getContext(), "Data Not Found", false);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return _array;
    }

    private List<String> SaveDatatSalesProductQuantityData(JSONArray jsonArray) {
        List<String> _array;
        _array = new ArrayList<>();
        for (Object aJsonArray : jsonArray) {
            JSONObject innerObj = (JSONObject) aJsonArray;
            try {
                JSONArray jsonArray_header = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtSalesProductQuantityHeader_mobile")));
                if (jsonArray_header != null) {
                    for (Object aJsonArray_header : jsonArray_header) {
                        tSalesProductQuantityHeaderData _data = new tSalesProductQuantityHeaderData();
                        JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                        _data.set_intId(String.valueOf(innerObj_header.get("txtDataId")));
                        _data.set_txtQuantityStock(String.valueOf(innerObj_header.get("TxtNoQuantityStock")));
                        _data.set_dtDate(String.valueOf(innerObj_header.get("DtDate")));
                        _data.set_OutletCode(String.valueOf(innerObj_header.get("TxtOutletCode")));
                        _data.set_OutletName(String.valueOf(innerObj_header.get("TxtOutletName")));
                        _data.set_txtKeterangan(String.valueOf(innerObj_header.get("TxtKeterangan")));
                        _data.set_intSumItem(String.valueOf(innerObj_header.get("IntSumItem")));
                        _data.set_intSumAmount(String.valueOf(innerObj_header.get("IntSumAmount")));
                        _data.set_UserId(String.valueOf(innerObj_header.get("TxtUserId")));
                        _data.set_txtBranchCode(String.valueOf(innerObj_header.get("TxtBranchCode")));
                        _data.set_txtBranchName(String.valueOf(innerObj_header.get("TxtBranchName")));
                        _data.set_intIdAbsenUser(String.valueOf(innerObj_header.get("IntIdAbsenUser")));
                        _data.set_txtRoleId(String.valueOf(innerObj_header.get("TxtRoleId")));
                        _data.set_txtNIK(String.valueOf(innerObj_header.get("TxtNIK")));
                        _data.set_intSubmit("1");
                        _data.set_intSync("1");
                        new tSalesProductQuantityHeaderBL().SaveData(_data);
                    }

                    JSONArray jsonArray_Detail = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtSalesProductQuantityDetail_mobile")));
                    Iterator k = jsonArray_Detail.iterator();
                    clsMainBL _clsMainBL = new clsMainBL();
                    SQLiteDatabase _db = _clsMainBL.getDb();
                    while (k.hasNext()) {
                        tSalesProductQuantityDetailData _data = new tSalesProductQuantityDetailData();
                        JSONObject innerObj_detail = (JSONObject) k.next();
                        _data.setIntId(String.valueOf(innerObj_detail.get("TxtTrSalesProductQuantityDetail")));
                        _data.set_txtQuantityStock(String.valueOf(innerObj_detail.get("TxtNoQuantityStock")));
                        _data.set_dtDate(String.valueOf(innerObj_detail.get("DtDate")));
                        _data.set_intPrice(String.valueOf(innerObj_detail.get("IntPrice")));
                        _data.set_txtCodeProduct(String.valueOf(innerObj_detail.get("TxtCodeProduct")));
                        _data.set_txtKeterangan(String.valueOf(innerObj_detail.get("TxtKeterangan")));
                        _data.setTxtProduct(String.valueOf(innerObj_detail.get("TxtProduct")));
                        _data.setTxtExpireDate(String.valueOf(innerObj_detail.get("TxtExpireDate")));
                        _data.setTxtQuantity(String.valueOf(innerObj_detail.get("TxtQuantity")));
                        _data.set_intTotal(String.valueOf(innerObj_detail.get("IntTotal")));
                        _data.set_txtNIK(String.valueOf(innerObj_detail.get("TxtUserId")));
                        new tSalesProductQuantityDetailDA(_db).SaveDatatSalesProductQuantityDetailData(_db, _data);
                    }

                    JSONArray jsonArray_Image = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtSalesProductQuantityImage_mobile")));
                    Iterator l = jsonArray_Image.iterator();
                    clsMainBL _clsMainBL_image = new clsMainBL();
                    SQLiteDatabase _db_image = _clsMainBL_image.getDb();
                    while (l.hasNext()) {
                        tSalesProductQuantityImageData _data = new tSalesProductQuantityImageData();
                        JSONObject innerObj_image = (JSONObject) l.next();
                        _data.set_txtId(String.valueOf(innerObj_image.get("TxtTrSalesProductQuantityImage")));
                        _data.set_txtHeaderId(String.valueOf(innerObj_image.get("TxtQuantityStock")));
                        _data.set_intPosition(String.valueOf(innerObj_image.get("IntPosition")));
                        _data.set_txtType(String.valueOf(innerObj_image.get("TxtType")));

                        String url = String.valueOf(innerObj_image.get("TxtImage"));

                        byte[] logoImage = getLogoImage(url);

                        if (logoImage != null) {
                            _data.set_txtImage(logoImage);
                        }

                        new tSalesProductQuantityImageDA(_db_image).SaveDataImage(_db_image, _data);
                    }
                } else {
//                    new clsMainActivity().showCustomToast(getContext(), "Data Not Found", false);
                    _array.add("Data Quantity Stock Not Found");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return _array;
    }

    private List<String> SaveDatatPlamogramData(JSONArray jsonArray) {
        List<String> _array;
        _array = new ArrayList<>();
        for (Object aJsonArray : jsonArray) {
            JSONObject innerObj = (JSONObject) aJsonArray;
            try {
                JSONArray jsonArray_header = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtPlanogram_mobile")));
                if (jsonArray_header != null) {
                    for (Object aJsonArray_header : jsonArray_header) {
                        tPlanogramMobileData _data = new tPlanogramMobileData();
                        JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                        _data.set_txtIdPlanogram(String.valueOf(innerObj_header.get("TxtIdPlanogram")));
                        _data.set_txtNIK(String.valueOf(innerObj_header.get("TxtNIK")));
                        _data.set_txtKeterangan(String.valueOf(innerObj_header.get("TxtDesc")));
                        _data.set_dtDate(String.valueOf(innerObj_header.get("DtActivity")));
                        _data.set_OutletCode(String.valueOf(innerObj_header.get("TxtOutletCode")));
                        _data.set_OutletName(String.valueOf(innerObj_header.get("TxtOutletName")));
                        _data.set_UserId(String.valueOf(innerObj_header.get("TxtUserId")));
                        _data.set_intSubmit("1");
                        _data.set_intSync("1");
                        _data.set_bitActive("0");
                        _data.set_txtBranchCode(String.valueOf(innerObj_header.get("TxtBranchCode")));
                        _data.set_txtBranchName(String.valueOf(innerObj_header.get("TxtBranchName")));
                        _data.set_txtRoleId(String.valueOf(innerObj_header.get("TxtRoleId")));
                        new tPlanogramMobileBL().saveData(_data);
                    }
                    JSONArray jsonArray_Image = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtPlanogramImage_mobile")));
                    Iterator l = jsonArray_Image.iterator();
                    clsMainBL _clsMainBL_image = new clsMainBL();
                    SQLiteDatabase _db_image = _clsMainBL_image.getDb();
                    while (l.hasNext()) {
                        tPlanogramImageData _data = new tPlanogramImageData();
                        JSONObject innerObj_image = (JSONObject) l.next();
                        _data.set_txtId(String.valueOf(innerObj_image.get("TxtTrPlanogramImage")));
                        _data.set_txtHeaderId(String.valueOf(innerObj_image.get("TxtHeaderId")));
                        _data.set_intPosition(String.valueOf(innerObj_image.get("IntPosition")));
                        _data.set_txtType(String.valueOf(innerObj_image.get("TxtType")));

                        String url = String.valueOf(innerObj_image.get("TxtImage"));

                        byte[] logoImage = getLogoImage(url);

                        if (logoImage != null) {
                            _data.set_txtImage(logoImage);
                        }

                        new tPlanogramImageDA(_db_image).SaveDataImage(_db_image, _data);
                    }
                } else {
//                    new clsMainActivity().showCustomToast(getContext(), "Data Not Found", false);
                    _array.add("Data Planogram Not Found");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return _array;
    }

    private List<String> SaveDataTrackingLocationData(JSONArray jsonArray) {
        List<String> _array;
        _array = new ArrayList<>();
        for (Object aJsonArray : jsonArray) {
            JSONObject innerObj = (JSONObject) aJsonArray;
            try {
                JSONArray jsonArray_header = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListTrackingLocation_mobile")));
                if (jsonArray_header != null) {
                    for (Object aJsonArray_header : jsonArray_header) {
                        trackingLocationData _data = new trackingLocationData();
                        JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                        _data.set_intId(String.valueOf(innerObj_header.get("IntId")));
                        _data.set_txtLongitude(String.valueOf(innerObj_header.get("TxtLongitude")));
                        _data.set_txtLatitude(String.valueOf(innerObj_header.get("TxtLatitude")));
                        _data.set_txtAccuracy(String.valueOf(innerObj_header.get("TxtAccuracy")));
                        _data.set_txtTime(String.valueOf(innerObj_header.get("Time")));
                        _data.set_txtUserId(String.valueOf(innerObj_header.get("TxtUserId")));
                        _data.set_txtUsername(String.valueOf(innerObj_header.get("TxtUsername")));
                        _data.set_txtRoleId(String.valueOf(innerObj_header.get("TxtRoleId")));
                        _data.set_txtDeviceId(String.valueOf(innerObj_header.get("TxtDeviceId")));
                        _data.set_txtBranchCode(String.valueOf(innerObj_header.get("TxtBranchCode")));
                        _data.set_txtOutletCode(String.valueOf(innerObj_header.get("TxtOutletCode")));
                        _data.set_txtNIK(String.valueOf(innerObj_header.get("TxtNIK")));
                        _data.set_intSequence(String.valueOf(innerObj_header.get("IntSequence")));
                        _data.set_intSubmit("1");
                        _data.set_intSync("1");
                        new trackingLocationBL().SaveDataTrackingLocation(_data);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return _array;
    }

    private List<String> SaveDataKoordinasiOutletData(JSONArray jsonArray) {
        List<String> _array;
        _array = new ArrayList<>();
        for (Object aJsonArray : jsonArray) {
            JSONObject innerObj = (JSONObject) aJsonArray;
            try {
                JSONArray jsonArray_header = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListKoordinasiOutlet_mobile")));
                if (jsonArray_header != null) {
                    for (Object aJsonArray_header : jsonArray_header) {
                        KoordinasiOutletData _data = new KoordinasiOutletData();
                        JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                        _data.set_intId(String.valueOf(innerObj_header.get("IntId")));
                        _data.set_dtDate(String.valueOf(innerObj_header.get("DtDate")));
                        _data.set_txtKeterangan(String.valueOf(innerObj_header.get("TxtKeterangan")));
                        _data.set_txtUserId(String.valueOf(innerObj_header.get("TxtUserId")));
                        _data.set_txtUsername(String.valueOf(innerObj_header.get("TxtUsername")));
                        _data.set_txtRoleId(String.valueOf(innerObj_header.get("TxtRoleId")));
                        _data.set_txtOutletCode(String.valueOf(innerObj_header.get("TxtOutletCode")));
                        _data.set_txtOutletName(String.valueOf(innerObj_header.get("TxtOutletName")));
                        _data.set_txtBranchCode(String.valueOf(innerObj_header.get("TxtBranchCode")));
                        _data.set_txtBranchName(String.valueOf(innerObj_header.get("TxtBranchName")));
                        _data.set_txtNIK(String.valueOf(innerObj_header.get("TxtNIK")));
                        _data.set_intSubmit("1");
                        _data.set_intSync("1");
                        new KoordinasiOutletBL().SaveDataKoordinasiOutlet(_data);
                    }

                    JSONArray jsonArray_Image = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListKoordinasiOutletImage_mobile")));
                    Iterator l = jsonArray_Image.iterator();
                    clsMainBL _clsMainBL_image = new clsMainBL();
                    SQLiteDatabase _db_image = _clsMainBL_image.getDb();
                    while (l.hasNext()) {
                        KoordinasiOutletImageData _data = new KoordinasiOutletImageData();
                        JSONObject innerObj_image = (JSONObject) l.next();
                        _data.set_txtId(String.valueOf(innerObj_image.get("TxtId")));
                        _data.set_txtHeaderId(String.valueOf(innerObj_image.get("TxtHeaderId")));
                        _data.set_intPosition(String.valueOf(innerObj_image.get("IntPosition")));

                        String url = String.valueOf(innerObj_image.get("TxtImage"));

                        byte[] logoImage = getLogoImage(url);

                        if (logoImage != null) {
                            _data.set_txtImage(logoImage);
                        }

                        new KoordinasiOutletImageDA(_db_image).SaveDataImage(_db_image, _data);
                    }
                } else {
//                    new clsMainActivity().showCustomToast(getContext(), "Data Not Found", false);
                    _array.add("Data Koordinasi Outlet not Found");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return _array;
    }
}
