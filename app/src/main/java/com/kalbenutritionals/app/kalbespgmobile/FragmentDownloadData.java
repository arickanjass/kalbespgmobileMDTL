package com.kalbenutritionals.app.kalbespgmobile;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PowerManager;
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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kalbenutritionals.app.kalbespgmobile.Utils.ImagePick;
import com.kalbenutritionals.app.kalbespgmobile.MainMenu;
import com.kalbenutritionals.app.kalbespgmobile.R;
import com.kalbenutritionals.app.kalbespgmobile.clsMainActivity;

import org.apache.http.util.ByteArrayBuffer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bl.KoordinasiOutletBL;
import bl.clsHelperBL;
import bl.clsMainBL;
import bl.mCategoryPOPStandardBL;
import bl.mCategoryVisitPlanBL;
import bl.mCountConsumerMTDBL;
import bl.mDownloadMasterData_mobileBL;
import bl.mEmployeeAreaBL;
import bl.mEmployeeBranchBL;
import bl.mEmployeeSalesProductBL;
import bl.mKategoriBL;
import bl.mListJawabanBL;
import bl.mMenuBL;
import bl.mParentBL;
import bl.mPertanyaanBL;
import bl.mProductBarcodeBL;
import bl.mProductBrandHeaderBL;
import bl.mProductCompetitorBL;
import bl.mProductPICBL;
import bl.mProductSPGBL;
import bl.mReasonPOPStandardBL;
import bl.mTypeLeaveBL;
import bl.mTypePOPStandardBL;
import bl.mTypePertanyaanBL;
import bl.mTypeSubmissionMobileBL;
import bl.mUserLOBBL;
import bl.mUserRoleBL;
import bl.tAbsenUserBL;
import bl.tActivityBL;
import bl.tActivityMobileBL;
import bl.tAttendanceUserBL;
import bl.tCustomerBasedMobileDetailBL;
import bl.tCustomerBasedMobileDetailProductBL;
import bl.tCustomerBasedMobileHeaderBL;
import bl.tDeviceInfoUserBL;
import bl.tDisplayPictureBL;
import bl.tGroupQuestionMappingBL;
import bl.tHirarkiBISBL;
import bl.tJawabanUserBL;
import bl.tJawabanUserHeaderBL;
import bl.tKategoryPlanogramMobileBL;
import bl.tKemasanRusakHeaderBL;
import bl.tLeaveMobileBL;
import bl.tLogDownloadBL;
import bl.tOverStockHeaderBL;
import bl.tPOPStandardDetailBL;
import bl.tPOPStandardHeaderBL;
import bl.tPlanogramMobileBL;
import bl.tPurchaseOrderHeaderBL;
import bl.tSalesProductHeaderBL;
import bl.tSalesProductQuantityHeaderBL;
import bl.tStockInHandHeaderBL;
import bl.tStockOutHeaderBL;
import bl.tSubTypeActivityBL;
import bl.tTidakSesuaiPesananHeaderBL;
import bl.tUserLoginBL;
import bl.tVisitPlanHeader_MobileBL;
import bl.tVisitPlanRealisasiBL;
import bl.trackingLocationBL;
import library.spgmobile.common.APIData;
import library.spgmobile.common.KoordinasiOutletData;
import library.spgmobile.common.KoordinasiOutletImageData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.clsLogReceiverDetail_mobile;
import library.spgmobile.common.clsLogReceiverHeader_mobile;
import library.spgmobile.common.clsPushData;
import library.spgmobile.common.dataJson;
import library.spgmobile.common.mCategoryKoordinasiOutletData;
import library.spgmobile.common.mCategoryPOPStandardData;
import library.spgmobile.common.mCategoryVisitPlanData;
import library.spgmobile.common.mCountConsumerMTDData;
import library.spgmobile.common.mCounterNumberData;
import library.spgmobile.common.mDownloadMasterData_mobileData;
import library.spgmobile.common.mEmployeeAreaData;
import library.spgmobile.common.mEmployeeBranchData;
import library.spgmobile.common.mEmployeeSalesProductData;
import library.spgmobile.common.mKategoriData;
import library.spgmobile.common.mListJawabanData;
import library.spgmobile.common.mMenuData;
import library.spgmobile.common.mParentData;
import library.spgmobile.common.mPertanyaanData;
import library.spgmobile.common.mProductBarcodeData;
import library.spgmobile.common.mProductBrandHeaderData;
import library.spgmobile.common.mProductCompetitorData;
import library.spgmobile.common.mProductPICData;
import library.spgmobile.common.mProductSPGData;
import library.spgmobile.common.mReasonPOPStandardData;
import library.spgmobile.common.mTypeLeaveMobileData;
import library.spgmobile.common.mTypePOPStandardData;
import library.spgmobile.common.mTypePertanyaanData;
import library.spgmobile.common.mTypeSubmissionMobile;
import library.spgmobile.common.mUserLOBData;
import library.spgmobile.common.mUserRoleData;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tActivityMobileData;
import library.spgmobile.common.tAttendanceUserData;
import library.spgmobile.common.tCustomerBasedMobileDetailData;
import library.spgmobile.common.tCustomerBasedMobileDetailProductData;
import library.spgmobile.common.tCustomerBasedMobileHeaderData;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.common.tDisplayPictureData;
import library.spgmobile.common.tGroupQuestionMappingData;
import library.spgmobile.common.tHirarkiBIS;
import library.spgmobile.common.tJawabanUserData;
import library.spgmobile.common.tJawabanUserHeaderData;
import library.spgmobile.common.tKategoryPlanogramMobileData;
import library.spgmobile.common.tKemasanRusakDetailData;
import library.spgmobile.common.tKemasanRusakHeaderData;
import library.spgmobile.common.tKemasanRusakImageData;
import library.spgmobile.common.tLeaveMobileData;
import library.spgmobile.common.tLogDownloadData;
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
import library.spgmobile.common.tSalesProductQuantityImageData;
import library.spgmobile.common.tStockInHandDetailData;
import library.spgmobile.common.tStockInHandHeaderData;
import library.spgmobile.common.tStockOutDetailData;
import library.spgmobile.common.tStockOutHeaderData;
import library.spgmobile.common.tSubTypeActivityData;
import library.spgmobile.common.tTidakSesuaiPesananHeaderData;
import library.spgmobile.common.tTidakSesuaiPesananImageData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.tVisitPlanHeader_MobileData;
import library.spgmobile.common.tVisitPlanRealisasiData;
import library.spgmobile.common.trackingLocationData;
import library.spgmobile.dal.KoordinasiOutletDA;
import library.spgmobile.dal.KoordinasiOutletImageDA;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.clsLogReceiverDetail_mobileDA;
import library.spgmobile.dal.clsLogReceiverHeader_mobileDA;
import library.spgmobile.dal.enumCounterData;
import library.spgmobile.dal.mCategoryKoordinasiOutletDA;
import library.spgmobile.dal.mCategoryVisitPlanDA;
import library.spgmobile.dal.mCounterNumberDA;
import library.spgmobile.dal.mEmployeeAreaDA;
import library.spgmobile.dal.mEmployeeBranchDA;
import library.spgmobile.dal.mEmployeeSalesProductDA;
import library.spgmobile.dal.mProductBrandHeaderDA;
import library.spgmobile.dal.mProductCompetitorDA;
import library.spgmobile.dal.mProductPICDA;
import library.spgmobile.dal.mProductSPGDA;
import library.spgmobile.dal.mTypeLeaveMobileDA;
import library.spgmobile.dal.mTypeSubmissionMobileDA;
import library.spgmobile.dal.mUserRoleDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tAbsenUserDA;
import library.spgmobile.dal.tActivityDA;
import library.spgmobile.dal.tActivityMobileDA;
import library.spgmobile.dal.tCustomerBasedMobileDetailDA;
import library.spgmobile.dal.tCustomerBasedMobileDetailProductDA;
import library.spgmobile.dal.tCustomerBasedMobileHeaderDA;
import library.spgmobile.dal.tHirarkiBISDA;
import library.spgmobile.dal.tJawabanUserDA;
import library.spgmobile.dal.tJawabanUserHeaderDA;
import library.spgmobile.dal.tKategoryPlanogramMobileDA;
import library.spgmobile.dal.tKemasanRusakDetailDA;
import library.spgmobile.dal.tKemasanRusakHeaderDA;
import library.spgmobile.dal.tKemasanRusakImageDA;
import library.spgmobile.dal.tLeaveMobileDA;
import library.spgmobile.dal.tOverStockDetailDA;
import library.spgmobile.dal.tOverStockHeaderDA;
import library.spgmobile.dal.tPOPStandardDetailDA;
import library.spgmobile.dal.tPOPStandardHeaderDA;
import library.spgmobile.dal.tPlanogramImageDA;
import library.spgmobile.dal.tPlanogramMobileDA;
import library.spgmobile.dal.tPurchaseOrderDetailDA;
import library.spgmobile.dal.tPurchaseOrderHeaderDA;
import library.spgmobile.dal.tSalesProductDetailDA;
import library.spgmobile.dal.tSalesProductHeaderDA;
import library.spgmobile.dal.tSalesProductQuantityDetailDA;
import library.spgmobile.dal.tSalesProductQuantityHeaderDA;
import library.spgmobile.dal.tSalesProductQuantityImageDA;
import library.spgmobile.dal.tStockInHandDetailDA;
import library.spgmobile.dal.tStockInHandHeaderDA;
import library.spgmobile.dal.tStockOutDetailDA;
import library.spgmobile.dal.tStockOutHeaderDA;
import library.spgmobile.dal.tSubTypeActivityDA;
import library.spgmobile.dal.tTidakSesuaiPesananHeaderDA;
import library.spgmobile.dal.tTidakSesuaiPesananImageDA;
import library.spgmobile.dal.tUserLoginDA;
import library.spgmobile.dal.tVisitPlanHeader_MobileDA;
import library.spgmobile.dal.tVisitPlanRealisasiDA;
import library.spgmobile.dal.trackingLocationDA;
import service.MyServiceNative;
import service.MyTrackingLocationService;

public class FragmentDownloadData extends Fragment {
    View v;
    private Spinner spnKordinasiOutlet;
    private Spinner spnCategoryKordinasiOutlet;
    private Spinner spnBranch;
    private Spinner spnVisitPlan, spnTrVisitPlan;
    private Spinner spnOutlet;
    private Spinner spnProduct;
    private Spinner spnLeave;
    private Spinner spnBrand;
    private Spinner spnReso, spnPOP;
    private Spinner spnActivity, spnActivityV2, spnStockIH, spnDataOverStock,spnDataStockOut, spnaddDisplay, spnDatatidaksesuaipesanan, spnDatakemasanrusak;
    private Spinner spnCustomerBase;
    private Spinner spnAbsen, spnQuiz, spnSPG;
    private Spinner spnkategoryPlanogram, spnAttendanceFpe, spnDataPlanogram, spnDataLeave, spnSubTypeActivity, spnDataPO, spnDataQuantityStock, spnProductComp, spnTypeSubmission, spnProdSPGCusBased, spnProdPICCusBased;
    private LinearLayout ll_subtypeactivity;
    private LinearLayout ll_branch;
    private LinearLayout ll_product;
    private LinearLayout ll_brand;
    private LinearLayout ll_type_leave;
    private LinearLayout ll_reso;
    private LinearLayout ll_data_activity, ll_dataPOP_Standard;
    private LinearLayout ll_data_activityV2;
    private LinearLayout ll_data_customerbased;
    private LinearLayout ll_absen, ll_data_attendance;
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
    private LinearLayout ll_dataCategoryKordinasiOutlet, ll_data_addDisplay;
    private LinearLayout ll_dataQuesioner, ll_data_planogram, ll_kategoryPlanogram, ll_data_stockIH, ll_outlet, ll_data_overStock,ll_data_StockOut, ll_dataSPG, ll_data_kemasanrusak, ll_data_tidaksesuaipesanan;

    private ProgressDialog mProgressDialog;

    private PackageInfo pInfo = null;
    private String intRoleId = "";
    private List<String> arrData;
    private String[] strip = new String[]{"-"};
    int intProcesscancel = 0;
    tUserLoginData loginData;
    Handler mHandler = new Handler();

    clsMainActivity _clsMainActivity;

    private String strMessage = "";
    ProgressBar mProgressBar;

    List<mUserLOBData> mUserLOBDataList;

    private TextView txtBranch, txtBranchLastDownload;
    private TextView txtOutlet, txtOutletLastDownload;
    private TextView textView7, textView7LastDownload;
    private TextView txtProduct, txtProductLastDownload;
    private TextView txtVwProductSPGCusBase, txtProductSPGCusBaseLastDownload;
    private TextView txtVwProductPICCusBase, txtProductPICCusBaseLastDownload;
    private TextView txtVwProductComp, txtProductCompLastDownload;
    private TextView txtVwTypeSubm, txtTypeSubmLastDownload;
    private TextView txtVwLeave, txtLeaveLastDownload;
    private TextView txtCategoryKoordinasi, txtCategoryKoordinasiLastDownload;
    private TextView txtVwVisitPlan, txtVisitPlanLastDownload;
    private TextView txtsubTypeActivity, txtsubTypeActivityLastDownload;
    private TextView txtkategoryPlanogram, txtkategoryPlanogramLastDownload;
    private TextView tv_SPG, txtSPGLastDownload;
    private TextView tv_quis, txtquisLastDownload;
    private TextView tv_POP, txtPOPLastDownload;
    private TextView txtTrVisitPlan, txtTrVisitPlanLastDownload;
    private TextView txtVwAbsen, txtAbsenLastDownload;
    private TextView txtVwAbsenFPE, txtAbsenFPELastDownload;
    private TextView txtVwDataLeave, txtDataLeaveLastDownload;
    private TextView txtVwReso, txtResoLastDownload;
    private TextView txtVwstockIH, txtstockIHLastDownload;
    private TextView txtVwActivity, txtActivityLastDownload;
    private TextView txtVwActivityV2, txtActivityV2LastDownload;
    private TextView txtVwaddDisplayV2, txtaddDisplayV2LastDownload;
    private TextView txtVwCustomerBase, txtCustomerBaseLastDownload;
    private TextView txtTrPO, txtTrPOLastDownload;
    private TextView txtTrQuantityStock, txtTrQuantityStockLastDownload;
    private TextView txtTrPlanogram, txtPlanogramLastDownload;
    private TextView txtVwKordinasiOutlet, txtKordinasiOutletLastDownload;
    private TextView txtVwDataOverStock, txtOverStockLastDownload;
    private TextView txtVwDataStockOut, txtStockOutLastDownload;
    private TextView txtVwDatakemasanrusak, txtkemasanrusakLastDownload;
    private TextView txtVwDatatidaksesuaipesanan, txttidaksesuaipesananLastDownload;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_download_data, container, false);
        Button btnAllDownload = (Button) v.findViewById(R.id.btnAllDownload);

        mUserLOBDataList = new ArrayList<>();
        mUserLOBDataList = new mUserLOBBL().GetAllData();

        Button btnKordinasiOutlet = (Button) v.findViewById(R.id.btnKordinasiOutlet);
        spnKordinasiOutlet = (Spinner) v.findViewById(R.id.spnKordinasiOutlet);
        Button btnCategoryKordinasiOutlet = (Button) v.findViewById(R.id.btnCategoryKoordinasiOutlet);
        spnCategoryKordinasiOutlet = (Spinner) v.findViewById(R.id.spnCategoryKoordinasiOutlet);
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
        spnSPG = (Spinner) v.findViewById(R.id.spnSPG);
//        Button btnSPG = (Button) v.findViewById(R.id.btnSPG);
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
        spnkategoryPlanogram = (Spinner) v.findViewById(R.id.spnkategoryPlanogram);
        Button btnkategoryPlanogram = (Button) v.findViewById(R.id.btnkategoryPlanogram);
        spnDataPlanogram = (Spinner) v.findViewById(R.id.spnDataPlanogram);
        Button btnDlDataPlanogram = (Button) v.findViewById(R.id.btnDlDataPlanogram);
        spnAttendanceFpe = (Spinner) v.findViewById(R.id.spnAttendanceFpe);
        Button btnDlAttendanceFpe = (Button) v.findViewById(R.id.btnDlAttendanceFpe);
        spnStockIH = (Spinner) v.findViewById(R.id.spnStockIH);
        Button btnDlStockIH = (Button) v.findViewById(R.id.btnDlStockIH);
        spnDataOverStock = (Spinner) v.findViewById(R.id.spnDataOverStock);
        Button btnDataOverStock = (Button) v.findViewById(R.id.btnDataOverStock);
        spnDataStockOut  = (Spinner) v.findViewById(R.id.spnDataStockOut);
        Button btnDataStockOut = (Button) v.findViewById(R.id.btnDataStockOut);
        spnaddDisplay = (Spinner) v.findViewById(R.id.spnaddDisplay);
        Button btnDladdDisplay = (Button) v.findViewById(R.id.btnDladdDisplay);
        spnDatakemasanrusak = (Spinner) v.findViewById(R.id.spnDatakemasanrusak);
        Button btnDatakemasanrusak = (Button) v.findViewById(R.id.btnDatakemasanrusak);
        spnDatatidaksesuaipesanan = (Spinner) v.findViewById(R.id.spnDatatidaksesuaipesanan);
        Button btnDatatidaksesuaipesanan = (Button) v.findViewById(R.id.btnDatatidaksesuaipesanan);
        spnPOP = (Spinner) v.findViewById(R.id.spnDPOP);
        Button btnPOP = (Button) v.findViewById(R.id.btnPOPStandard);

        ll_dataPOP_Standard = (LinearLayout) v.findViewById(R.id.ll_dataPOP_Standard);
        ll_branch = (LinearLayout) v.findViewById(R.id.ll_branch);
        ll_outlet = (LinearLayout) v.findViewById(R.id.ll_outlet);
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
        ll_dataCategoryKordinasiOutlet = (LinearLayout) v.findViewById(R.id.ll_CategoryKoordinasiOutlet);
        ll_dataQuesioner = (LinearLayout) v.findViewById(R.id.ll_dataQuesioner);
        ll_dataSPG = (LinearLayout) v.findViewById(R.id.ll_dataSPG);
        ll_subtypeactivity = (LinearLayout) v.findViewById(R.id.ll_subtypeactivity);
        ll_kategoryPlanogram = (LinearLayout) v.findViewById(R.id.ll_kategoryPlanogram);
        ll_data_planogram = (LinearLayout) v.findViewById(R.id.ll_data_planogram);
        ll_data_attendance = (LinearLayout) v.findViewById(R.id.ll_data_attendance);
        ll_data_overStock = (LinearLayout) v.findViewById(R.id.ll_data_overStock);
        ll_data_StockOut = (LinearLayout) v.findViewById(R.id.ll_data_StockOut);
        ll_data_stockIH = (LinearLayout) v.findViewById(R.id.ll_data_stockIH);
        ll_data_addDisplay = (LinearLayout) v.findViewById(R.id.ll_data_addDisplay);
        ll_data_kemasanrusak = (LinearLayout) v.findViewById(R.id.ll_data_kemasanrusak);
        ll_data_tidaksesuaipesanan = (LinearLayout) v.findViewById(R.id.ll_data_tidaksesuaipesanan);

        txtBranch = (TextView) v.findViewById(R.id.txtBranch);
        txtBranchLastDownload = (TextView) v.findViewById(R.id.txtBranchLastDownload);
        txtOutlet = (TextView) v.findViewById(R.id.txtOutlet);
        txtOutletLastDownload = (TextView) v.findViewById(R.id.txtOutletLastDownload);
        textView7 = (TextView) v.findViewById(R.id.textView7);
        textView7LastDownload = (TextView) v.findViewById(R.id.textView7LastDownload);
        txtProduct = (TextView) v.findViewById(R.id.txtProduct);
        txtProductLastDownload = (TextView) v.findViewById(R.id.txtProductLastDownload);
        txtVwProductSPGCusBase = (TextView) v.findViewById(R.id.txtVwProductSPGCusBase);
        txtProductSPGCusBaseLastDownload = (TextView) v.findViewById(R.id.txtProductSPGCusBaseLastDownload);
        txtVwProductPICCusBase = (TextView) v.findViewById(R.id.txtVwProductPICCusBase);
        txtProductPICCusBaseLastDownload = (TextView) v.findViewById(R.id.txtProductPICCusBaseLastDownload);
        txtVwProductComp = (TextView) v.findViewById(R.id.txtVwProductComp);
        txtProductCompLastDownload = (TextView) v.findViewById(R.id.txtProductCompLastDownload);
        txtVwTypeSubm = (TextView) v.findViewById(R.id.txtVwTypeSubm);
        txtTypeSubmLastDownload = (TextView) v.findViewById(R.id.txtTypeSubmLastDownload);
        txtVwLeave = (TextView) v.findViewById(R.id.txtVwLeave);
        txtLeaveLastDownload = (TextView) v.findViewById(R.id.txtLeaveLastDownload);
        txtCategoryKoordinasi = (TextView) v.findViewById(R.id.txtCategoryKoordinasi);
        txtCategoryKoordinasiLastDownload = (TextView) v.findViewById(R.id.txtCategoryKoordinasiLastDownload);
        txtVwVisitPlan = (TextView) v.findViewById(R.id.txtVwVisitPlan);
        txtVisitPlanLastDownload = (TextView) v.findViewById(R.id.txtVisitPlanLastDownload);
        txtsubTypeActivity = (TextView) v.findViewById(R.id.txtsubTypeActivity);
        txtsubTypeActivityLastDownload = (TextView) v.findViewById(R.id.txtsubTypeActivityLastDownload);
        txtkategoryPlanogram = (TextView) v.findViewById(R.id.txtkategoryPlanogram);
        txtkategoryPlanogramLastDownload = (TextView) v.findViewById(R.id.txtkategoryPlanogramLastDownload);
        tv_SPG = (TextView) v.findViewById(R.id.tv_SPG);
        txtSPGLastDownload = (TextView) v.findViewById(R.id.txtSPGLastDownload);
        tv_quis = (TextView) v.findViewById(R.id.tv_quis);
        txtquisLastDownload = (TextView) v.findViewById(R.id.txtquisLastDownload);
        tv_POP = (TextView) v.findViewById(R.id.tv_POP);
        txtPOPLastDownload = (TextView) v.findViewById(R.id.txtPOPLastDownload);
        txtTrVisitPlan = (TextView) v.findViewById(R.id.txtTrVisitPlan);
        txtTrVisitPlanLastDownload = (TextView) v.findViewById(R.id.txtTrVisitPlanLastDownload);
        txtVwAbsen = (TextView) v.findViewById(R.id.txtVwAbsen);
        txtAbsenLastDownload = (TextView) v.findViewById(R.id.txtAbsenLastDownload);
        txtVwAbsenFPE = (TextView) v.findViewById(R.id.txtVwAbsenFPE);
        txtAbsenFPELastDownload = (TextView) v.findViewById(R.id.txtAbsenFPELastDownload);
        txtVwDataLeave = (TextView) v.findViewById(R.id.txtVwDataLeave);
        txtDataLeaveLastDownload = (TextView) v.findViewById(R.id.txtDataLeaveLastDownload);
        txtVwReso = (TextView) v.findViewById(R.id.txtVwReso);
        txtResoLastDownload = (TextView) v.findViewById(R.id.txtResoLastDownload);
        txtVwstockIH = (TextView) v.findViewById(R.id.txtVwstockIH);
        txtstockIHLastDownload = (TextView) v.findViewById(R.id.txtstockIHLastDownload);
        txtVwActivity = (TextView) v.findViewById(R.id.txtVwActivity);
        txtActivityLastDownload = (TextView) v.findViewById(R.id.txtActivityLastDownload);
        txtVwActivityV2 = (TextView) v.findViewById(R.id.txtVwActivityV2);
        txtActivityV2LastDownload = (TextView) v.findViewById(R.id.txtActivityV2LastDownload);
        txtVwaddDisplayV2 = (TextView) v.findViewById(R.id.txtVwaddDisplayV2);
        txtaddDisplayV2LastDownload = (TextView) v.findViewById(R.id.txtaddDisplayV2LastDownload);
        txtVwCustomerBase = (TextView) v.findViewById(R.id.txtVwCustomerBase);
        txtCustomerBaseLastDownload = (TextView) v.findViewById(R.id.txtCustomerBaseLastDownload);
        txtTrPO = (TextView) v.findViewById(R.id.txtTrPO);
        txtTrPOLastDownload = (TextView) v.findViewById(R.id.txtTrPOLastDownload);
        txtTrQuantityStock = (TextView) v.findViewById(R.id.txtTrQuantityStock);
        txtTrQuantityStockLastDownload = (TextView) v.findViewById(R.id.txtTrQuantityStockLastDownload);
        txtTrPlanogram = (TextView) v.findViewById(R.id.txtTrPlanogram);
        txtPlanogramLastDownload = (TextView) v.findViewById(R.id.txtPlanogramLastDownload);
        txtVwKordinasiOutlet = (TextView) v.findViewById(R.id.txtVwKordinasiOutlet);
        txtKordinasiOutletLastDownload = (TextView) v.findViewById(R.id.txtKordinasiOutletLastDownload);
        txtVwDataOverStock = (TextView) v.findViewById(R.id.txtVwDataOverStock);
        txtOverStockLastDownload = (TextView) v.findViewById(R.id.txtOverStockLastDownload);
        txtVwDataStockOut = (TextView) v.findViewById(R.id.txtVwDataStockOut);
        txtStockOutLastDownload = (TextView) v.findViewById(R.id.txtStockOutLastDownload);
        txtVwDatakemasanrusak = (TextView) v.findViewById(R.id.txtVwDatakemasanrusak);
        txtkemasanrusakLastDownload = (TextView) v.findViewById(R.id.txtkemasanrusakLastDownload);
        txtVwDatatidaksesuaipesanan = (TextView) v.findViewById(R.id.txtVwDatatidaksesuaipesanan);
        txttidaksesuaipesananLastDownload = (TextView) v.findViewById(R.id.txttidaksesuaipesananLastDownload);
        tv_quis = (TextView) v.findViewById(R.id.tv_quis);
        txtquisLastDownload = (TextView) v.findViewById(R.id.txtquisLastDownload);


        spnVisitPlan = (Spinner) v.findViewById(R.id.spnVisitPlan);
        spnTrVisitPlan = (Spinner) v.findViewById(R.id.spnTrVisitPlan);

        Button btnVisitPlan = (Button) v.findViewById(R.id.btnVisitPlan);
        Button btnTrVisitPlan = (Button) v.findViewById(R.id.btnTrVisitPlan);


        loginData = new tUserLoginData();
        loginData = new tUserLoginBL().getUserActive();

        generateLayout();

        loadData();

        setViewTextLastDownload();

        btnPOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intProcesscancel = 0;
                AsyncCallPOPStandard task = new AsyncCallPOPStandard();
                task.execute();
            }
        });

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

        btnDatatidaksesuaipesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intProcesscancel = 0;
                AsyncCallDataTidakSesuaiPesanan task = new AsyncCallDataTidakSesuaiPesanan();
                task.execute();
            }
        });

        btnCategoryKordinasiOutlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intProcesscancel = 0;
                AsyncCallDataCategoryKoordinasiOutlet task = new AsyncCallDataCategoryKoordinasiOutlet();
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
        btnkategoryPlanogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intProcesscancel = 0;
                AsyncCallKategoryPlanogram task = new AsyncCallKategoryPlanogram();
                task.execute();
            }
        });
        btnAllDownload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
//                AsyncCallDownloadAll task = new AsyncCallDownloadAll();
                tUserLoginData dtLogin = new tUserLoginBL().getUserLogin();
                boolean validMandatoryProdComp = false;

                if(dtLogin!=null&&dtLogin.get_TxtEmpId()!=null){
                    if (ll_product != null && checkVisibility(ll_product)) {
                        int count = new mEmployeeSalesProductBL().getContactsCountByKN(mUserLOBDataList,dtLogin.get_TxtEmpId());
                        if (count == 0) {
                            validDownloadProduct = true;
                        }
                    }
                    if (ll_product_competitor != null && checkVisibility(ll_product_competitor)) {
                        int count = new mProductCompetitorBL().getContactsCountByKN(mUserLOBDataList,dtLogin.get_TxtEmpId());
                        if (count == 0) {
                            validDownloadProductComp = true;
                        }
                    }
                    if (ll_product_spg != null && checkVisibility(ll_product_spg)) {
                        int count = new mProductSPGBL().getContactCountByKN(mUserLOBDataList,dtLogin.get_TxtEmpId());
                        if (count == 0) {
                            validDownloadProductSPG = true;
                        }
                    }
                    if (ll_product_pic != null && checkVisibility(ll_product_pic)) {
                        int count = new mProductPICBL().getContactCountByKN(mUserLOBDataList,dtLogin.get_TxtEmpId());
                        if (count == 0) {
                            validDownloadProductPIC = true;
                        }
                    }

//                    if(ll_product_competitor != null && checkVisibility(ll_product_competitor)){
//                        int count = new mTypeSubmissionMobileBL().getContactCountTypeSUbProdComp();
//                        if(count>0){
//                            validMandatoryProdComp = true;
//                        }
//                    }

                    if(validDownloadProductPIC||validDownloadProductSPG||validDownloadProductComp||validDownloadProduct&&checkVisibility(ll_product_pic)&&checkVisibility(ll_product_spg)&&checkVisibility(ll_product_competitor)){
                        AsyncCallGenerateSQLite task = new AsyncCallGenerateSQLite();
                        task.execute();
                    } else if(validDownloadProduct&& !checkVisibility(ll_product_pic)&&!checkVisibility(ll_product_spg)&&!checkVisibility(ll_product_competitor)){

                        AsyncCallGenerateSQLite task = new AsyncCallGenerateSQLite();
                        task.execute();
                    } else {
                        AsyncCallDownloadAllBundleData task = new AsyncCallDownloadAllBundleData();
                        task.execute();
                    }
                }

//                task.execute();

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
        btnDlStockIH.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
                AsyncCallStockIH task = new AsyncCallStockIH();
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

        btnDladdDisplay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
                AsyncCallAddDisPlay task = new AsyncCallAddDisPlay();
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
        btnDlAttendanceFpe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncCallAttendanceFpe task = new AsyncCallAttendanceFpe();
                task.execute();
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
//                AsyncCallQuis1 task1 = new AsyncCallQuis1();
//                task1.execute();
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
        btnDatakemasanrusak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intProcesscancel = 0;
                AsyncCallDataKemasanRusak task = new AsyncCallDataKemasanRusak();
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
        btnDataOverStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intProcesscancel = 0;
                AsyncCallDataOverStock task = new AsyncCallDataOverStock();
                task.execute();
            }
        });
        btnDataStockOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intProcesscancel = 0;
                AsyncCallDataStockOut task = new AsyncCallDataStockOut();
                task.execute();
            }
        });
        btnProductComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intProcesscancel = 0;
//                AsyncCallDataProdComp task = new AsyncCallDataProdComp();
//                task.execute();
                AsyncCallGenerateSQLite task = new AsyncCallGenerateSQLite();
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

    private Resources res;

    private void generateLayout() {
        List<mDownloadMasterData_mobileData> mDownloadMasterData_mobileDataList;

        mDownloadMasterData_mobileDataList = new mDownloadMasterData_mobileBL().GetAllData();

        res = getResources();

        for (int i = 0; i < mDownloadMasterData_mobileDataList.size(); i++) {
            String txt_id = mDownloadMasterData_mobileDataList.get(i).get_txtMasterData().replaceAll(" ", "");

            SQLiteDatabase db = new clsMainBL().getDb();

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
                ll_dataSPG.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_dataPOP_Standard.getId()))) {
                ll_dataPOP_Standard.setVisibility(View.VISIBLE);
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
            } else if (txt_id.equals(res.getResourceEntryName(ll_dataCategoryKordinasiOutlet.getId()))) {
                ll_dataCategoryKordinasiOutlet.setVisibility(View.VISIBLE);
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
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_attendance.getId()))) {
                ll_data_attendance.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_stockIH.getId()))) {
                ll_data_stockIH.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_kategoryPlanogram.getId()))) {
                ll_kategoryPlanogram.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_overStock.getId()))) {
                ll_data_overStock.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_StockOut.getId()))) {
                ll_data_StockOut.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_addDisplay.getId()))) {
                ll_data_addDisplay.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_kemasanrusak.getId()))) {
                ll_data_kemasanrusak.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_tidaksesuaipesanan.getId()))) {
                ll_data_tidaksesuaipesanan.setVisibility(View.VISIBLE);
            }
        }
    }

    private void loadData() {
//        mProgressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
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
        List<mTypeLeaveMobileData> listDataLeave = new mTypeLeaveBL().GetAllData();
        List<mProductBrandHeaderData> listmProductBrandData = new mProductBrandHeaderBL().getData("");
        List<tSalesProductHeaderData> listtSalesProductHeaderData = new tSalesProductHeaderBL().getAllSalesProductHeader();
        List<tStockInHandHeaderData> listtStockInHandHeaderData = new tStockInHandHeaderBL().getAllSalesProductHeader();
        List<tCustomerBasedMobileHeaderData> listtCustomerBasedHeaderData = new tCustomerBasedMobileHeaderBL().getAllData();
        List<tActivityData> listtActivityData = new tActivityBL().getAllData();
        List<tActivityMobileData> listtActivityMobileData = new tActivityMobileBL().getAllData();
        List<tAbsenUserData> listtAbsenUserData = new tAbsenUserBL().getAllDataActive();
        List<tAttendanceUserData> listtAttendanceUserData = new tAttendanceUserBL().getAllDataActive();
        List<tLeaveMobileData> listtLeaveData = new tLeaveMobileBL().getData("");
        List<mParentData> parentDataList = new mParentBL().GetAllData();
        List<mPertanyaanData> pertanyaanDataList = new mPertanyaanBL().GetAllData();
        List<mListJawabanData> jawabanDataList = new mListJawabanBL().GetAllData();
        List<mTypePertanyaanData> typePertanyaanDataList = new mTypePertanyaanBL().GetAllData();
        List<tGroupQuestionMappingData> tGroupQuestionMappingDataList = new tGroupQuestionMappingBL().GetAllData();
        List<mKategoriData> kategoriDataList = new mKategoriBL().GetAllData();
        List<tHirarkiBIS> listSPG = new tHirarkiBISBL().GetAllData();
        List<mTypePOPStandardData> listTypePOP = new mTypePOPStandardBL().GetAllData();
        List<mReasonPOPStandardData> listReasonPOP = new mReasonPOPStandardBL().GetAllData();
        List<mCategoryPOPStandardData> listCategoryPOP = new mCategoryPOPStandardBL().GetAllData();
        List<tPurchaseOrderHeaderData> listPurchaseOrderHeaderData = new tPurchaseOrderHeaderBL().getAllPurchaseOrderHeader();
        List<tSalesProductQuantityHeaderData> listQuantityStockHeaderData = new tSalesProductQuantityHeaderBL().getAllSalesQuantityHeader();
        List<tOverStockHeaderData> listOverStockHeaderData = new tOverStockHeaderBL().getAllOverStockHeader();
        List<tStockOutHeaderData> listStockOutHeaderData = new tStockOutHeaderBL().getAllOverStockHeader();
//        List<trackingLocationData> listtrackingLocationData = new trackingLocationBL().getAllDataTrackingLocation();
        List<KoordinasiOutletData> listKoordinasiOutletData = new KoordinasiOutletBL().getAllKoordinasiOutletData();
        List<mCategoryKoordinasiOutletData> lisCategoryKoordinasiOutletData = new KoordinasiOutletBL().GetAllCategoryKoordinasiOutletData();
        List<tSubTypeActivityData> tSubTypeActivityDataList = new tSubTypeActivityBL().getAllData();
        List<tPlanogramMobileData> tPlanogramMobileDataList = new tPlanogramMobileBL().getAllData();
        List<tKategoryPlanogramMobileData> tKategoryPlanogramDataList = new tKategoryPlanogramMobileBL().getAllData();
        List<tKemasanRusakHeaderData> tKemasanRusakHeaderDataList = new tKemasanRusakHeaderBL().getAllHeader();
        List<tTidakSesuaiPesananHeaderData> tTidakSesuaiPesananHeaderDataList = new tTidakSesuaiPesananHeaderBL().getAllData();
        List<mTypeSubmissionMobile> typeSubmissionDataList = new mTypeSubmissionMobileBL().GetAllData();

        tUserLoginData dtLogin = new tUserLoginBL().getUserLogin();

        List<mEmployeeSalesProductData> listDataProduct = new mEmployeeSalesProductBL().GetAllDataByKN(mUserLOBDataList, dtLogin.get_TxtEmpId());
        List<mProductCompetitorData> productCompetitorDataList = new mProductCompetitorBL().GetAllDataByKN(mUserLOBDataList, dtLogin.get_TxtEmpId());
        List<mProductSPGData> mProductSPGDataList = new mProductSPGBL().GetAllDataByKN(mUserLOBDataList, dtLogin.get_TxtEmpId());
        List<mProductPICData> mProductPICDataList = new mProductPICBL().GetAllDataByKN(mUserLOBDataList, dtLogin.get_TxtEmpId());

        arrData = new ArrayList<>();
        if (listTypePOP.size() > 0 && listReasonPOP.size() > 0 && listCategoryPOP.size() > 0) {
//            for (tKemasanRusakHeaderData dt : tKemasanRusakHeaderDataList) {
//                arrData.add(dt.get_txtKemasanRusak());
//            }
            arrData.add("POP Standard Ready");
            spnPOP.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnPOP.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnPOP.setAdapter(adapterspn);
            spnPOP.setEnabled(false);
        }

        arrData = new ArrayList<>();
        if (tKemasanRusakHeaderDataList != null) {
            for (tKemasanRusakHeaderData dt : tKemasanRusakHeaderDataList) {
                arrData.add(dt.get_txtKemasanRusak());
            }
            spnDatakemasanrusak.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnDatakemasanrusak.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnDatakemasanrusak.setAdapter(adapterspn);
            spnDatakemasanrusak.setEnabled(false);
        }

        arrData = new ArrayList<>();
        if (tTidakSesuaiPesananHeaderDataList != null && tTidakSesuaiPesananHeaderDataList.size() > 0) {
            for (tTidakSesuaiPesananHeaderData dt : tTidakSesuaiPesananHeaderDataList) {
                arrData.add(dt.get_txtOutletName());
            }
            spnDatatidaksesuaipesanan.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnDatatidaksesuaipesanan.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnDatatidaksesuaipesanan.setAdapter(adapterspn);
            spnDatatidaksesuaipesanan.setEnabled(false);
        }

        arrData = new ArrayList<>();
        if (tPlanogramMobileDataList.size() > 0) {
            for (tPlanogramMobileData dt : tPlanogramMobileDataList) {
                arrData.add(dt.get_txtCategoryName());
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
        if (tKategoryPlanogramDataList.size() > 0) {
            for (tKategoryPlanogramMobileData dt : tKategoryPlanogramDataList) {
                arrData.add(dt.get_txtName());
            }
            spnkategoryPlanogram.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnkategoryPlanogram.setEnabled(true);
        } else if (tKategoryPlanogramDataList.size() == 0) {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnkategoryPlanogram.setAdapter(adapterspn);
            spnkategoryPlanogram.setEnabled(false);
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
        if (parentDataList.size() > 0 && kategoriDataList.size() > 0 && typePertanyaanDataList.size() > 0 && pertanyaanDataList.size() > 0 && tGroupQuestionMappingDataList.size() > 0) {
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
        if (listSPG.size() > 0) {
            for (tHirarkiBIS dt : listSPG) {
                arrData.add(dt.get_txtName());
            }
            spnSPG.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnSPG.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnSPG.setAdapter(adapterspn);
            spnSPG.setEnabled(false);
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
                arrData.add(dt.get_txtTipeLeaveName());
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
        if (listtStockInHandHeaderData != null) {
            for (tStockInHandHeaderData dt : listtStockInHandHeaderData) {
                arrData.add(dt.get_txtNoSo());
            }
            spnStockIH.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnStockIH.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnStockIH.setAdapter(adapterspn);
            spnStockIH.setEnabled(false);
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
        if (listtActivityMobileData != null && listtActivityMobileData.size() != 0) {
            for (tActivityMobileData dt : listtActivityMobileData) {
                arrData.add(dt.get_intFlag() + "-" + dt.get_txtDesc());
            }
            spnaddDisplay.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnaddDisplay.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnaddDisplay.setAdapter(adapterspn);
            spnaddDisplay.setEnabled(false);
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
        if (listtAttendanceUserData != null) {
            for (tAttendanceUserData dt : listtAttendanceUserData) {
                arrData.add(dt.get_dtDateCheckIn() + " - " + dt.get_dtDateCheckOut());
            }
            spnAttendanceFpe.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnAttendanceFpe.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnAttendanceFpe.setAdapter(adapterspn);
            spnAttendanceFpe.setEnabled(false);
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
        if (listOverStockHeaderData != null) {
            for (tOverStockHeaderData dt : listOverStockHeaderData) {
                arrData.add(dt.get_txtOverStock());
            }
            spnDataOverStock.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnDataOverStock.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnDataOverStock.setAdapter(adapterspn);
            spnDataOverStock.setEnabled(false);
        }

        arrData = new ArrayList<>();
        if (listStockOutHeaderData != null) {
            for (tStockOutHeaderData dt : listStockOutHeaderData) {
                arrData.add(dt.get_txtOverStock());
            }
            spnDataStockOut.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnDataStockOut.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnDataStockOut.setAdapter(adapterspn);
            spnDataStockOut.setEnabled(false);
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
        if (lisCategoryKoordinasiOutletData != null && lisCategoryKoordinasiOutletData.size() != 0) {
            for (mCategoryKoordinasiOutletData dt : lisCategoryKoordinasiOutletData) {
                arrData.add(dt.get_txtCategory());
            }
            spnCategoryKordinasiOutlet.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
            spnCategoryKordinasiOutlet.setEnabled(true);
        } else {
            ArrayAdapter<String> adapterspn = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, strip);
            spnCategoryKordinasiOutlet.setAdapter(adapterspn);
            spnCategoryKordinasiOutlet.setEnabled(false);
        }

        arrData = new ArrayList<>();
        if (listtLeaveData.size() > 0) {
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

//        mProgressBar.setVisibility(View.GONE);
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

//    private class AsyncCallDownloadAll extends AsyncTask<JSONArray, Void, List<dataJson>> {
//        @Override
//        protected List<dataJson> doInBackground(JSONArray... params) {
//            //android.os.Debug.waitForDebugger();
//            JSONArray Json;
//            List<dataJson> listDataJson = new ArrayList<>();
//            dataJson dtdataJson = new dataJson();
//            try {
////                new mPriceInOutletBL().DownloadmPriceInOutlet(pInfo.versionName);
//
//                if (ll_subtypeactivity != null && checkVisibility(ll_subtypeactivity)) {
//                    Json = new tSubTypeActivityBL().DownloadtSubTypeActivity(pInfo.versionName, loginData.get_txtRoleId());
//                    SaveDatatSubTypeActivityData(Json);
//                }
//
//                if (ll_kategoryPlanogram != null && checkVisibility(ll_kategoryPlanogram)) {
//                    Json = new tKategoryPlanogramMobileBL().DownloadKategoryPlanogram(pInfo.versionName, loginData.get_txtRoleId());
//                    SaveDataKategoryPlanogram(Json);
//                }
//
//                if (ll_branch != null && checkVisibility(ll_branch)) {
//                    Json = new mEmployeeBranchBL().DownloadEmployeeBranch2(pInfo.versionName);
//                    SaveDatamEmployeeBranchData(Json);
//                }
//                if (ll_type_leave != null && checkVisibility(ll_type_leave)) {
//                    Json = new mTypeLeaveBL().DownloadTypeLeave2(pInfo.versionName);
//                    SaveDatamTypeLeaveMobileData(Json);
//                }
//                if (ll_product != null && checkVisibility(ll_product)) {
//                    Json = new mEmployeeSalesProductBL().DownloadEmployeeSalesProduct(pInfo.versionName);
////                    SaveDatamProductBarcodeData(Json);
//                }
//                if (ll_brand != null && checkVisibility(ll_brand)) {
//                    Json = new mProductBrandHeaderBL().DownloadBrandHeader(pInfo.versionName);
//                    SaveDatamProductBrandHeaderData(Json);
//                }
//
//                if (ll_kategoriVisitPlan != null && checkVisibility(ll_kategoriVisitPlan)) {
//                    Json = new mCategoryVisitPlanBL().DownloadCategoryVisitPlanData(pInfo.versionName);
//                    SaveDatamCategoryVisitPlanData(Json);
//                }
//
//                if (ll_dataVisitPlan != null && checkVisibility(ll_dataVisitPlan)) {
//                    Json = new tVisitPlanRealisasiBL().DownloadRealisasiVisitPlan(pInfo.versionName);
//                    SaveDatatTransaksiVisitPlanHeaderData(Json);
//                    SaveDatatTransaksiVisitPlanData(Json);
//                }
//
//                if (ll_data_attendance != null && checkVisibility(ll_data_attendance )){
//                    Json = new tAttendanceUserBL() .DownloadAttendance(pInfo.versionName);
//                    SaveDatatAttendanceUserData(Json);
//                }
//
//                if (ll_outlet != null && checkVisibility(ll_outlet )){
//                    Json = new mEmployeeAreaBL().DownloadEmployeeArea2(pInfo.versionName);
//                    SaveDatamEmployeeAreaData(Json);
//                }
//
//                if (ll_reso != null && checkVisibility(ll_reso)) {
//                    Json = new tSalesProductHeaderBL().DownloadReso(pInfo.versionName);
//                    Iterator i = Json.iterator();
//                    org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
//                    int boolValid = Integer.valueOf(String.valueOf(innerObj.get("_pboolValid")));
//                    if (boolValid == 1) SaveDatatSalesProductData(Json);
//                }
//
//                if (ll_purchase_order != null && checkVisibility(ll_purchase_order)) {
//                    new tPurchaseOrderHeaderBL().DownloadNOPO(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
//                    Json = new tPurchaseOrderHeaderBL().DownloadTransactionPO(pInfo.versionName);
//                    Iterator j = Json.iterator();
//                    org.json.simple.JSONObject innerObj_po = (org.json.simple.JSONObject) j.next();
//                    int boolValid_po = Integer.valueOf(String.valueOf(innerObj_po.get("_pboolValid")));
//                    if (boolValid_po == 1) SaveDatatPurchaseOrderData(Json);
//                }
//
//                if (ll_data_stockIH != null && checkVisibility(ll_data_stockIH)) {
//                    new tStockInHandHeaderBL().DownloadNOSIH(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
//                    Json = new tStockInHandHeaderBL().DownloadSIH(pInfo.versionName);
//                    Iterator j = Json.iterator();
//                    org.json.simple.JSONObject innerObj_po = (org.json.simple.JSONObject) j.next();
//                    int boolValid_po = Integer.valueOf(String.valueOf(innerObj_po.get("_pboolValid")));
//                    if (boolValid_po == 1) SaveDatatStockInHandData(Json);
//                }
//
//                if (ll_dataQuesioner != null && checkVisibility(ll_dataQuesioner)) {
//                    Json = new mParentBL().DownlaodDataQuesioner(pInfo.versionName);
//                    Iterator x = Json.iterator();
//                    org.json.simple.JSONObject innerObj_Quiz = (org.json.simple.JSONObject) x.next();
//                    int boolValid_po = Integer.valueOf(String.valueOf(innerObj_Quiz.get("_pboolValid")));
//                    if (boolValid_po == 1) SaveDataQuesioner(Json);
//
////                    Json = new mParentBL().DownlaodDataSPGfromTL(pInfo.versionName);
////                    Iterator y = Json.iterator();
////                    JSONObject innnerObj_SPGFromTL = (JSONObject) y.next();
////                    int boolValid_SPG = Integer.valueOf(String.valueOf(innnerObj_SPGFromTL.get("_pboolValid")));
////                    if (boolValid_SPG == 1) SaveDataSPGFromTL(Json);
//                }
//
//                if (ll_dataQuantityStock != null && checkVisibility(ll_dataQuantityStock)) {
//                    new tSalesProductQuantityHeaderBL().DownloadNOQuantityStock(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
//                    Json = new tSalesProductQuantityHeaderBL().DownloadTransactionQuantityStock(pInfo.versionName);
//                    Iterator k = Json.iterator();
//                    org.json.simple.JSONObject innerObj_quantityStock = (org.json.simple.JSONObject) k.next();
//                    int boolValid_quantityStock = Integer.valueOf(String.valueOf(innerObj_quantityStock.get("_pboolValid")));
//                    if (boolValid_quantityStock == 1) SaveDatatSalesProductQuantityData(Json);
//                }
//
//                if (ll_data_overStock != null && checkVisibility(ll_data_overStock)) {
//                    new tOverStockHeaderBL().DownloadNOOverStock(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
//                    Json = new tOverStockHeaderBL().DownloadTransactionOverStock(pInfo.versionName);
//                    Iterator k = Json.iterator();
//                    org.json.simple.JSONObject innerObj_quantityStock = (org.json.simple.JSONObject) k.next();
//                    int boolValid_overStock = Integer.valueOf(String.valueOf(innerObj_quantityStock.get("_pboolValid")));
//                    if (boolValid_overStock == 1) SaveDatatOverStockData(Json);
//                }
//
//                Json = new trackingLocationBL().DownloadTrackingLocation(pInfo.versionName);
//                Iterator l = Json.iterator();
//                org.json.simple.JSONObject innerObj_trackingLocation = (org.json.simple.JSONObject) l.next();
//                int boolValid_trackingLocation = Integer.valueOf(String.valueOf(innerObj_trackingLocation.get("_pboolValid")));
//                if (boolValid_trackingLocation == 1) SaveDataTrackingLocationData(Json);
//
//                if (ll_dataKordinasiOutlet != null && checkVisibility(ll_dataKordinasiOutlet)) {
//                    Json = new KoordinasiOutletBL().DownloadDataKoordinasiOutlet(pInfo.versionName);
//                    Iterator m = Json.iterator();
//                    org.json.simple.JSONObject innerObj_koordinasiOutlet = (org.json.simple.JSONObject) m.next();
//                    int boolValid_koordinasiOutlet = Integer.valueOf(String.valueOf(innerObj_koordinasiOutlet.get("_pboolValid")));
//                    if (boolValid_koordinasiOutlet == 1) SaveDataKoordinasiOutletData(Json);
//
//                    Json = new KoordinasiOutletBL().DownloadDataCategoryKoordinasiOutlet(pInfo.versionName);
//                    Iterator c = Json.iterator();
//                    org.json.simple.JSONObject innerObj_categoryKoordinasiOutlet = (org.json.simple.JSONObject) c.next();
//                    int boolValid_categoryKoordinasiOutlet = Integer.valueOf(String.valueOf(innerObj_categoryKoordinasiOutlet.get("_pboolValid")));
//                    if (boolValid_categoryKoordinasiOutlet == 1) SaveDataCategoryKoordinasiOutletData(Json);
//                }
//
//                if (ll_data_planogram != null && checkVisibility(ll_data_planogram)) {
//                    Json = new tPlanogramMobileBL().DownloadTransactionPlanogram(pInfo.versionName);
//                    Iterator m = Json.iterator();
//                    org.json.simple.JSONObject innerObj_koordinasiOutlet = (org.json.simple.JSONObject) m.next();
//                    int boolValid_Planogram = Integer.valueOf(String.valueOf(innerObj_koordinasiOutlet.get("_pboolValid")));
//                    if (boolValid_Planogram == 1) SaveDatatPlamogramData(Json);
//                }
//
//                if (ll_data_activity != null && checkVisibility(ll_data_activity)) {
//                    Json = new tActivityBL().DownloadActivity(pInfo.versionName);
//                    SaveDatatActivityData(Json);
//                }
//                if (ll_data_activityV2 != null && checkVisibility(ll_data_activityV2)) {
//                    Json = new tActivityMobileBL().DownloadActivityV2(pInfo.versionName);
//                    SaveDatatActivityDataV2(Json);
//                }
//                if (ll_data_customerbased != null && checkVisibility(ll_data_customerbased)) {
//                    Json = new tCustomerBasedMobileHeaderBL().DownloadCustomerBase(pInfo.versionName);
//                    SaveDatatCustomerBasedData(Json);
//                }
//                if (ll_absen != null && checkVisibility(ll_absen)) {
//                    Json = new tAbsenUserBL().DownloadAbsen(pInfo.versionName);
//                    SaveDatatAbsenUserData(Json);
//                }
//                if (ll_data_leave != null && checkVisibility(ll_data_leave)) {
//                    Json = new tLeaveMobileBL().DownloadDataLeave(pInfo.versionName);
//                    SaveDatatLeaveData(Json);
//                }
//                if (ll_product_competitor != null && checkVisibility(ll_product_competitor)) {
//                    Json = new mProductCompetitorBL().DownloadProdctCompetitor(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
//                    SaveDatammProductCompetitorData(Json);
//                }
//                if (ll_product_spg != null && checkVisibility(ll_product_spg)) {
//                    Json = new mProductSPGBL().DownloadProductSPG(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
//                    SaveDatammProductSPGData(Json);
//                }
//                if (ll_product_pic != null && checkVisibility(ll_product_pic)) {
//                    Json = new mProductPICBL().DownloadProductPIC(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
//                    SaveDatammProductPICData(Json);
//                }
//                if (ll_type_submission != null && checkVisibility(ll_type_submission)) {
//                    Json = new mTypeSubmissionMobileBL().DownloadTypeSubmission(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
//                    SaveDatamTypeSubmissionMobile(Json);
//                }
//
//                dtdataJson.setIntResult("1");
//            } catch (Exception e) {
//                dtdataJson.setIntResult("0");
//                //dtdataJson.setTxtMessage(e.getMessage().toString());
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            listDataJson.add(dtdataJson);
//            return listDataJson;
//        }
//
//        @Override
//        protected void onCancelled() {
//            Dialog.dismiss();
//            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
//        }
//
//        private ProgressDialog Dialog = new ProgressDialog(getContext());
//
//        @Override
//        protected void onPostExecute(List<dataJson> listdataJson) {
//            if (listdataJson.get(0).getIntResult().equals("0")) {
//                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessUnableToConnect, false);
//                Dialog.dismiss();
//
//            } else {
//                loadData();
//                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
//                Dialog.dismiss();
//                checkingDataTable("ALL");
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            // Make ProgressBar invisible
//            // pg.setVisibility(View.VISIBLE);
//            Dialog.setMessage(new clsHardCode().txtMessGetAllData);
//            Dialog.setCancelable(false);
////            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
////                @Override
////                public void onClick(DialogInterface dialog, int which) {
////                    intProcesscancel = 1;
////                }
////            });
//            Dialog.show();
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            Dialog.dismiss();
//        }
//
//    }

    private String eMessage = "";
    private String pstrArgumet = "";
    private boolean validDownloadProduct = false;
    private boolean validDownloadProductComp = false;
    private boolean validDownloadProductPIC = false;
    private boolean validDownloadProductSPG = false;

    private class AsyncCallDownloadAllBundleData extends AsyncTask<JSONArray, Void, List<dataJson>> {
        @Override
        protected List<dataJson> doInBackground(JSONArray... params) {
            //android.os.Debug.waitForDebugger();
            JSONArray Json;
            List<dataJson> listDataJson = new ArrayList<>();
            dataJson dtdataJson = new dataJson();
            clsPushData dtJson = new clsHelperBL().downloadData(pInfo.versionName);
            JSONArray JsonArrayResult = null;

            //master
            if (ll_subtypeactivity != null && checkVisibility(ll_subtypeactivity)) {
                dtJson.getDtdataJson().getDttSubTypeActivityData().setBoolValid("1");
            }
            if (ll_kategoryPlanogram != null && checkVisibility(ll_kategoryPlanogram)) {
                dtJson.getDtdataJson().getDttKategoryPlanogramMobileData().setBoolValid("1");
            }
            if (ll_outlet != null && checkVisibility(ll_outlet)) {
                dtJson.getDtdataJson().getDtmEmployeeAreaData().setBoolValid("1");
            }
            if (ll_branch != null && checkVisibility(ll_branch)) {
                dtJson.getDtdataJson().getDtmEmployeeBranchData().setBoolValid("1");
            }
            if (ll_type_leave != null && checkVisibility(ll_type_leave)) {
                dtJson.getDtdataJson().getDtmTypeLeaveMobileData().setBoolValid("1");
            }
            if (ll_product != null && checkVisibility(ll_product)) {
                int count = new mEmployeeSalesProductBL().getContactsCountByKN(mUserLOBDataList);
                if (count == 0) {
                    dtJson.getDtdataJson().getDtmEmployeeSalesProductData().setBoolValid("1");
                    validDownloadProduct = true;
                }
            }
            if (ll_brand != null && checkVisibility(ll_brand)) {
                dtJson.getDtdataJson().getDtmProductBrandHeaderData().setBoolValid("1");
            }
            if (ll_kategoriVisitPlan != null && checkVisibility(ll_kategoriVisitPlan)) {
                dtJson.getDtdataJson().getDtmCategoryVisitPlanData().setBoolValid("1");
            }
            if (ll_product_competitor != null && checkVisibility(ll_product_competitor)) {
                int count = new mProductCompetitorBL().getContactsCountByKN(mUserLOBDataList);
                if (count == 0) {
                    dtJson.getDtdataJson().getDtmProductCompetitorData().setBoolValid("1");
                    validDownloadProductComp = true;
                }
            }
            if (ll_product_spg != null && checkVisibility(ll_product_spg)) {
                int count = new mProductSPGBL().getContactCountByKN(mUserLOBDataList);
                if (count == 0) {
                    dtJson.getDtdataJson().getDtmProductSPGData().setBoolValid("1");
                    validDownloadProductSPG = true;
                }
            }
            if (ll_product_pic != null && checkVisibility(ll_product_pic)) {
                int count = new mProductPICBL().getContactCountByKN(mUserLOBDataList);
                if (count == 0) {
                    dtJson.getDtdataJson().getDtmProductPICData().setBoolValid("1");
                    validDownloadProductPIC = true;
                }
            }
            if (ll_type_submission != null && checkVisibility(ll_type_submission)) {
                dtJson.getDtdataJson().getDtmTypeSubmissionMobile().setBoolValid("1");
            }
            if (ll_dataCategoryKordinasiOutlet != null && checkVisibility(ll_dataCategoryKordinasiOutlet)) {
                dtJson.getDtdataJson().getDtmCategoryKoordinasiOutletData().setBoolValid("1");
            }
            if (ll_dataQuesioner != null && checkVisibility(ll_dataQuesioner)) {
                dtJson.getDtdataJson().getDtmParentData().setBoolValid("1");
//                dtJson.getDtdataJson().getDttHirarkiBIS().setBoolValid("1");
            }
            //transaksi
            if (ll_dataVisitPlan != null && checkVisibility(ll_dataVisitPlan)) {
                dtJson.getDtdataJson().getDttVisitPlanRealisasiData().setBoolValid("1");
            }
            if (ll_data_attendance != null && checkVisibility(ll_data_attendance)) {
                dtJson.getDtdataJson().getDttAttendanceUserData().setBoolValid("1");
            }
            if (ll_reso != null && checkVisibility(ll_reso)) {
                dtJson.getDtdataJson().getDttSalesProductHeaderData().setBoolValid("1");
            }
            if (ll_purchase_order != null && checkVisibility(ll_purchase_order)) {
                dtJson.getDtdataJson().getDttPurchaseOrderHeaderData().setBoolValid("1");
            }
            if (ll_data_stockIH != null && checkVisibility(ll_data_stockIH)) {
                dtJson.getDtdataJson().getDttStockInHandHeaderData().setBoolValid("1");
            }
            if (ll_dataQuantityStock != null && checkVisibility(ll_dataQuantityStock)) {
                dtJson.getDtdataJson().getDttSalesProductQuantityHeaderData().setBoolValid("1");
            }
            if (ll_data_overStock != null && checkVisibility(ll_data_overStock)) {
                dtJson.getDtdataJson().getDttOverStockHeaderData().setBoolValid("1");
            }
            if (ll_data_StockOut != null && checkVisibility(ll_data_StockOut)) {
                dtJson.getDtdataJson().getDttStockOutHeaderData().setBoolValid("1");
            }
            //tracking location
            dtJson.getDtdataJson().getDttrackingLocationData().setBoolValid("1");
            if (ll_dataKordinasiOutlet != null && checkVisibility(ll_dataKordinasiOutlet)) {
                dtJson.getDtdataJson().getDtKoordinasiOutletData().setBoolValid("1");
            }
            if (ll_data_planogram != null && checkVisibility(ll_data_planogram)) {
                dtJson.getDtdataJson().getDttPlanogramMobileData().setBoolValid("1");
            }
            if (ll_data_activity != null && checkVisibility(ll_data_activity)) {
                dtJson.getDtdataJson().getDttActivityData().setBoolValid("1");
            }
            if (ll_data_activityV2 != null && checkVisibility(ll_data_activityV2)) {
                dtJson.getDtdataJson().getDttActivityMobileData().setBoolValid("1");
            }
            if (ll_data_addDisplay != null && checkVisibility(ll_data_addDisplay)) {
                dtJson.getDtdataJson().getDttActivityMobileData().setBoolValid("1");
            }
            if (ll_data_customerbased != null && checkVisibility(ll_data_customerbased)) {
                dtJson.getDtdataJson().getDttCustomerBasedMobileHeaderData().setBoolValid("1");
            }
            if (ll_absen != null && checkVisibility(ll_absen)) {
                dtJson.getDtdataJson().getDttAbsenUserData().setBoolValid("1");
            }
            if (ll_data_leave != null && checkVisibility(ll_data_leave)) {
                dtJson.getDtdataJson().getDttLeaveMobileData().setBoolValid("1");
            }
            if (ll_data_kemasanrusak != null && checkVisibility(ll_data_kemasanrusak)) {
                dtJson.getDtdataJson().getDttKemasanRusakHeaderData().setBoolValid("1");
            }
            if (ll_data_tidaksesuaipesanan != null && checkVisibility(ll_data_tidaksesuaipesanan)) {
                dtJson.getDtdataJson().getDttTidakSesuaiPesananHeaderData().setBoolValid("1");
            }
            if (ll_dataPOP_Standard != null && checkVisibility(ll_dataPOP_Standard)) {
                dtJson.getDtdataJson().getDtmTypePOPStandardData().setBoolValid("1");
            }
            String txtValid = "0";
            String txtMess = "";
            String jsonData = "";
            try {
                jsonData = new clsHelperBL().downloadAllData(pInfo.versionName, dtJson.getDtdataJson().generateJsonReqDownloadAllData().toString());

                clsHelper _help = new clsHelper();
                SQLiteDatabase _db = new clsMainBL().getDb();
                JsonArrayResult = _help.ResultJsonArray(jsonData);
                mCounterNumberDA _mCounterNumberDA = new mCounterNumberDA(_db);

                if (JsonArrayResult != null) {
                    Iterator i = JsonArrayResult.iterator();
                    while (i.hasNext()) {
                        org.json.simple.JSONObject innerObjTop = (org.json.simple.JSONObject) i.next();
                        txtValid = String.valueOf(innerObjTop.get("_pboolValid"));
                        txtMess = String.valueOf(innerObjTop.get("_pstrMessage"));
                        pstrArgumet = String.valueOf(innerObjTop.get(new APIData().strArgument));

                        if (txtValid.equals("1")) {
                            for (Object aJsonArray : JsonArrayResult) {
                                JSONObject innerObj = (JSONObject) aJsonArray;
                                try {
//                                    JSONArray jsonArray_tSubTypeActivityData = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOftSubTypeActivity")));
                                    JSONArray jsonArray_tSubTypeActivityData = (JSONArray) innerObj.get("ListOftSubTypeActivity");
                                    if (jsonArray_tSubTypeActivityData != null) {
                                        for (Object aJsonArray_header : jsonArray_tSubTypeActivityData) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatatSubTypeActivityData(jsonArray_tSubTypeActivityData);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }
//                                    JSONArray jsonArray_KategoryPlanogram = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOftKategoryPlanogram")));
                                    JSONArray jsonArray_KategoryPlanogram = (JSONArray) innerObj.get("ListOftKategoryPlanogram");
                                    if (jsonArray_KategoryPlanogram != null) {
                                        for (Object aJsonArray_header : jsonArray_KategoryPlanogram) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDataKategoryPlanogram(jsonArray_KategoryPlanogram);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }
//                                    JSONArray jsonArray_EmployeeBranchData = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfEmployeeBranchData")));
                                    JSONArray jsonArray_EmployeeBranchData = (JSONArray) innerObj.get("ListOfEmployeeBranchData");
                                    if (jsonArray_EmployeeBranchData != null) {
                                        for (Object aJsonArray_header : jsonArray_EmployeeBranchData) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatamEmployeeBranchData(jsonArray_EmployeeBranchData);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }
//                                    JSONArray jsonArray_TypeLeaveMobileData = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfmTypeLeaveMobile")));
                                    JSONArray jsonArray_TypeLeaveMobileData = (JSONArray) innerObj.get("ListOfmTypeLeaveMobile");
                                    if (jsonArray_TypeLeaveMobileData != null) {
                                        for (Object aJsonArray_header : jsonArray_TypeLeaveMobileData) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatamTypeLeaveMobileData(jsonArray_TypeLeaveMobileData);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }
//                                    JSONArray jsonArray_SalesProductData = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfvw_SalesInsentive_EmployeeSalesProductDetailData")));
                                    JSONArray jsonArray_SalesProductData = (JSONArray) innerObj.get("ListOfvw_SalesInsentive_EmployeeSalesProductDetailData");
                                    if (jsonArray_SalesProductData != null) {
                                        for (Object aJsonArray_header : jsonArray_SalesProductData) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatamEmployeeSalesProductData(jsonArray_SalesProductData);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }
//                                    JSONArray jsonArray_ProductBrandHeaderData = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfmProductBrandHeader")));
                                    JSONArray jsonArray_ProductBrandHeaderData = (JSONArray) innerObj.get("ListOfmProductBrandHeader");
                                    if (jsonArray_ProductBrandHeaderData != null) {
                                        for (Object aJsonArray_header : jsonArray_ProductBrandHeaderData) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatamProductBrandHeaderData(jsonArray_ProductBrandHeaderData);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }
//                                    JSONArray jsonArray_CategoryVisitPlanData = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfmCategoryVisitPlanData")));
                                    JSONArray jsonArray_CategoryVisitPlanData = (JSONArray) innerObj.get("ListOfmCategoryVisitPlanData");
                                    if (jsonArray_CategoryVisitPlanData != null) {
                                        for (Object aJsonArray_header : jsonArray_CategoryVisitPlanData) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatamCategoryVisitPlanData(jsonArray_CategoryVisitPlanData);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }
//                                    JSONArray jsonArray_EmployeeAreaData = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfvw_SalesInsentive_EmployeeAreaData")));
                                    JSONArray jsonArray_EmployeeAreaData = (JSONArray) innerObj.get("ListOfvw_SalesInsentive_EmployeeAreaData");
                                    if (jsonArray_EmployeeAreaData != null) {
                                        for (Object aJsonArray_header : jsonArray_EmployeeAreaData) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatamEmployeeAreaData(jsonArray_EmployeeAreaData);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }
//                                    JSONArray jsonArray_ProductCompetitorData = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfvw_SalesInsentive_EmployeeSalesProductCompetitorData")));
                                    JSONArray jsonArray_ProductCompetitorData = (JSONArray) innerObj.get("ListOfvw_SalesInsentive_EmployeeSalesProductCompetitorData");
                                    if (jsonArray_ProductCompetitorData != null) {
                                        for (Object aJsonArray_header : jsonArray_ProductCompetitorData) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatammProductCompetitorData(jsonArray_ProductCompetitorData);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }
//                                    JSONArray jsonArray_ProductSPGData = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfvw_SalesInsentive_EmployeeSalesProductSPGData")));
                                    JSONArray jsonArray_ProductSPGData = (JSONArray) innerObj.get("ListOfvw_SalesInsentive_EmployeeSalesProductSPGData");
                                    if (jsonArray_ProductSPGData != null) {
                                        for (Object aJsonArray_header : jsonArray_ProductSPGData) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatammProductSPGData(jsonArray_ProductSPGData);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }
//                                    JSONArray jsonArray_ProductPICData = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfvw_SalesInsentive_EmployeeSalesProductPICData")));
                                    JSONArray jsonArray_ProductPICData = (JSONArray) innerObj.get("ListOfvw_SalesInsentive_EmployeeSalesProductPICData");
                                    if (jsonArray_ProductPICData != null) {
                                        for (Object aJsonArray_header : jsonArray_ProductPICData) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatammProductPICData(jsonArray_ProductPICData);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }
//                                    JSONArray jsonArray_TypeSubmissionMobile = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfmTypeSubmissionMobile")));
                                    JSONArray jsonArray_TypeSubmissionMobile = (JSONArray) innerObj.get("ListOfmTypeSubmissionMobile");
                                    if (jsonArray_TypeSubmissionMobile != null) {
                                        for (Object aJsonArray_header : jsonArray_TypeSubmissionMobile) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatamTypeSubmissionMobile(jsonArray_TypeSubmissionMobile);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }
                                    JSONArray jsonArray_CategoryKoordinasiOutlet = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfmCategoryKoordinasiOutlet_mobile")));
                                    if (jsonArray_CategoryKoordinasiOutlet != null) {
                                        for (Object aJsonArray_header : jsonArray_CategoryKoordinasiOutlet) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDataCategoryKoordinasiOutletData(jsonArray_CategoryKoordinasiOutlet);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_Quesioner = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfclsDataQuesionerAPI")));
                                    if (jsonArray_Quesioner != null) {
                                        for (Object aJsonArray_header : jsonArray_Quesioner) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDataQuesioner(jsonArray_Quesioner);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_POPStandard = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfclsDataPOPStandardAPI")));
                                    if (jsonArray_POPStandard != null) {
                                        for (Object aJsonArray_header : jsonArray_POPStandard) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDataPOPStandard(jsonArray_POPStandard);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

//                                    //belum bener list json Array
//                                    JSONArray jsonArray_HirarkiBIS = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOftHirartkiBis_mobile")));
//                                    if (jsonArray_HirarkiBIS != null) {
//                                        for (Object aJsonArray_header : jsonArray_HirarkiBIS) {
//                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
//                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
//                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
//                                            if(boolValid.equals("1")){
//                                                SaveDataSPGFromTL(jsonArray_HirarkiBIS);
//                                            } else if(boolValid.equals("0")){
//                                            }
//                                            break;
//                                        }
//                                    }

                                    //belum bener list json Array
                                    JSONArray jsonArray_TransaksiVisitPlanAll = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOftTransaksiVisitPlanAll")));
                                    if (jsonArray_TransaksiVisitPlanAll != null) {
                                        for (Object aJsonArray_header : jsonArray_TransaksiVisitPlanAll) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatatTransaksiVisitPlanHeaderData(jsonArray_TransaksiVisitPlanAll);
                                                SaveDatatTransaksiVisitPlanData(jsonArray_TransaksiVisitPlanAll);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }
                                    JSONArray jsonArray_Attendace = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOftAttendanceUser_mobile")));
                                    if (jsonArray_Attendace != null) {
                                        for (Object aJsonArray_header : jsonArray_Attendace) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatatAttendanceUserData(jsonArray_Attendace);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    //belum bener list json Array
                                    JSONArray jsonArray_Reso = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfclsdataResoAPI")));
                                    if (jsonArray_Reso != null) {
                                        for (Object aJsonArray_header : jsonArray_Reso) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            String txtNoSo = String.valueOf(innerObj_header.get("_pstrArgument"));

                                            mCounterNumberData _data = new mCounterNumberData();
                                            _db = new clsMainBL().getDb();
                                            _data.set_intId(enumCounterData.NoDataSO.getidCounterData());
                                            _data.set_txtDeskripsi((String) innerObj_header.get("_pstrMethodRequest"));
                                            _data.set_txtName((String) innerObj_header.get("_pstrMethodRequest"));
                                            _data.set_txtValue((String) innerObj_header.get("_pstrArgument"));
                                            _mCounterNumberDA.SaveDataMConfig(_db, _data);

                                            if (boolValid.equals("1")) {
                                                SaveDatatSalesProductData(jsonArray_Reso, 0);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_Po = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfclsDatatPurchaseOrderAPI")));
                                    if (jsonArray_Po != null) {
                                        for (Object aJsonArray_header : jsonArray_Po) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            String txtNoPo = String.valueOf(innerObj_header.get("_pstrArgument"));

                                            mCounterNumberData _data = new mCounterNumberData();
                                            _db = new clsMainBL().getDb();
                                            _data.set_intId(enumCounterData.NoPurchaseOrder.getidCounterData());
                                            _data.set_txtDeskripsi((String) innerObj_header.get("_pstrMethodRequest"));
                                            _data.set_txtName((String) innerObj_header.get("_pstrMethodRequest"));
                                            _data.set_txtValue((String) innerObj_header.get("_pstrArgument"));
                                            _mCounterNumberDA.SaveDataMConfig(_db, _data);

                                            if (boolValid.equals("1")) {
                                                SaveDatatPurchaseOrderData(jsonArray_Po);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_StockOnHandHeader = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfclsDataStockInHandAPI")));
                                    if (jsonArray_StockOnHandHeader != null) {
                                        for (Object aJsonArray_header : jsonArray_StockOnHandHeader) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            String txtNoSOH = String.valueOf(innerObj_header.get("_pstrArgument"));

                                            mCounterNumberData _data = new mCounterNumberData();
                                            _data.set_intId(enumCounterData.NoSIH.getidCounterData());
                                            _data.set_txtDeskripsi((String) innerObj_header.get("_pstrMethodRequest"));
                                            _data.set_txtName((String) innerObj_header.get("_pstrMethodRequest"));
                                            _data.set_txtValue((String) innerObj_header.get("_pstrArgument"));
                                            _mCounterNumberDA.SaveDataMConfig(_db, _data);

                                            if (boolValid.equals("1")) {
                                                SaveDatatStockInHandData(jsonArray_StockOnHandHeader);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_NearEd = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfclstQuantityStockHeader")));
                                    if (jsonArray_NearEd != null) {
                                        for (Object aJsonArray_header : jsonArray_NearEd) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            String txtNoQTS = String.valueOf(innerObj_header.get("_pstrArgument"));

                                            _db = new clsMainBL().getDb();
                                            mCounterNumberData _data = new mCounterNumberData();
                                            _data.set_intId(enumCounterData.NoQuantityStock.getidCounterData());
                                            _data.set_txtDeskripsi((String) innerObj_header.get("_pstrMethodRequest"));
                                            _data.set_txtName((String) innerObj_header.get("_pstrMethodRequest"));
                                            _data.set_txtValue((String) innerObj_header.get("_pstrArgument"));
                                            _mCounterNumberDA.SaveDataMConfig(_db, _data);

                                            if (boolValid.equals("1")) {
                                                SaveDatatSalesProductQuantityData(jsonArray_NearEd);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_KmsnRusak = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfclsKemasanRusakHeader")));
                                    if (jsonArray_KmsnRusak != null) {
                                        for (Object aJsonArray_header : jsonArray_KmsnRusak) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            String txtNoKRS = String.valueOf(innerObj_header.get("_pstrArgument"));

                                            _db = new clsMainBL().getDb();
                                            mCounterNumberData _data = new mCounterNumberData();
                                            _data.set_intId(enumCounterData.NoKRS.getidCounterData());
                                            _data.set_txtDeskripsi((String) innerObj_header.get("_pstrMethodRequest"));
                                            _data.set_txtName((String) innerObj_header.get("_pstrMethodRequest"));
                                            _data.set_txtValue((String) innerObj_header.get("_pstrArgument"));
                                            _mCounterNumberDA.SaveDataMConfig(_db, _data);

                                            if (boolValid.equals("1")) {
                                                SaveDatatKemasanRusakData(jsonArray_KmsnRusak);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_OverStock = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfclstOverStockHeader")));
                                    if (jsonArray_OverStock != null) {
                                        for (Object aJsonArray_header : jsonArray_OverStock) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            String txtOVS = String.valueOf(innerObj_header.get("_pstrArgument"));

                                            _db = new clsMainBL().getDb();
                                            mCounterNumberData _data = new mCounterNumberData();
                                            _data.set_intId(enumCounterData.NoOS.getidCounterData());
                                            _data.set_txtDeskripsi((String) innerObj_header.get("_pstrMethodRequest"));
                                            _data.set_txtName((String) innerObj_header.get("_pstrMethodRequest"));
                                            _data.set_txtValue((String) innerObj_header.get("_pstrArgument"));
                                            _mCounterNumberDA.SaveDataMConfig(_db, _data);

                                            if (boolValid.equals("1")) {
                                                SaveDatatOverStockData(jsonArray_OverStock);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_StockOut = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfclstStockOutHeader")));
                                    if (jsonArray_StockOut != null) {
                                        for (Object aJsonArray_header : jsonArray_StockOut) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            String txtOVS = String.valueOf(innerObj_header.get("_pstrArgument"));

//                                            _db = new clsMainBL().getDb();
//                                            mCounterNumberData _data = new mCounterNumberData();
//                                            _data.set_intId(enumCounterData.NoOS.getidCounterData());
//                                            _data.set_txtDeskripsi((String) innerObj_header.get("_pstrMethodRequest"));
//                                            _data.set_txtName((String) innerObj_header.get("_pstrMethodRequest"));
//                                            _data.set_txtValue((String) innerObj_header.get("_pstrArgument"));
//                                            _mCounterNumberDA.SaveDataMConfig(_db, _data);

                                            if (boolValid.equals("1")) {
                                                SaveDatatStockOutData(jsonArray_StockOut);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_TrackingLocation = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfclsTrackingLocation_mobile")));
                                    if (jsonArray_TrackingLocation != null) {
                                        for (Object aJsonArray_header : jsonArray_TrackingLocation) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDataTrackingLocationData(jsonArray_TrackingLocation);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_KoordinasiOutlet = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfclsKoordinasiOutlet_mobile")));
                                    if (jsonArray_KoordinasiOutlet != null) {
                                        for (Object aJsonArray_header : jsonArray_KoordinasiOutlet) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDataKoordinasiOutletData(jsonArray_KoordinasiOutlet);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_TSesuaiPesanan = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfclsclsTidakSesuaiPesanan")));
                                    if (jsonArray_TSesuaiPesanan != null) {
                                        for (Object aJsonArray_header : jsonArray_TSesuaiPesanan) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDataTidakSesuaiPesanan(jsonArray_TSesuaiPesanan);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_Planogram = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfclsPlanogramMobile")));
                                    if (jsonArray_Planogram != null) {
                                        for (Object aJsonArray_header : jsonArray_Planogram) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatatPlamogramData(jsonArray_Planogram);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_Activity = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOftActivity_mobile")));
                                    if (jsonArray_Activity != null) {
                                        for (Object aJsonArray_header : jsonArray_Activity) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatatActivityData(jsonArray_Activity);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_ActivityV2 = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOftActivity_mobileNew")));
                                    if (jsonArray_ActivityV2 != null) {
                                        for (Object aJsonArray_header : jsonArray_ActivityV2) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatatActivityDataV2(jsonArray_ActivityV2);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_CustomerBased = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOfclsdataCustomerBasedAPI")));
                                    if (jsonArray_CustomerBased != null) {
                                        for (Object aJsonArray_header : jsonArray_CustomerBased) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatatCustomerBasedData(jsonArray_CustomerBased);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                    JSONArray jsonArray_AbsenUser = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOftAbsenUser_mobile")));
                                    if (jsonArray_AbsenUser != null) {
                                        for (Object aJsonArray_header : jsonArray_AbsenUser) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatatAbsenUserData(jsonArray_AbsenUser);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }
                                    JSONArray jsonArray_ListOftLeaveMobile = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListOftLeaveMobile")));
                                    if (jsonArray_ListOftLeaveMobile != null) {
                                        for (Object aJsonArray_header : jsonArray_ListOftLeaveMobile) {
                                            JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                                            String boolValid = String.valueOf(innerObj_header.get("_pboolValid"));
                                            String pstrMess = String.valueOf(innerObj_header.get("_pstrMessage"));
                                            if (boolValid.equals("1")) {
                                                SaveDatatLeaveData(jsonArray_ListOftLeaveMobile);
                                            } else if (boolValid.equals("0")) {
                                            }
                                            break;
                                        }
                                    }

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else if (txtValid.equals("0")) {
                            dtdataJson.setIntResult(txtValid);
                            dtdataJson.setTxtMessage(txtMess);
                        }

                    }
                }

                dtdataJson.setIntResult(txtValid);
                dtdataJson.setTxtMessage(txtMess);
                _db.close();
            } catch (Exception e) {
                dtdataJson.setIntResult("0");
                dtdataJson.setTxtMessage(e.toString() + "\n" + jsonData);
                eMessage = e.toString();
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
                new clsMainActivity().showCustomToast(getContext(), listdataJson.get(0).getTxtMessage(), false);
                Dialog.dismiss();

            } else if (listdataJson.get(0).getIntResult().equals("1")) {
                loadData();
//                setViewTextLastDownload();
                new clsMainActivity().showCustomToast(getContext(), listdataJson.get(0).getTxtMessage(), true);
                Dialog.dismiss();
                checkingDataTable("ALL");
            }
            saveLastDownload(pstrArgumet);
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage(new clsHardCode().txtMessGetAllData);
            Dialog.setCancelable(false);
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }

    private void saveLastDownload(String pstrArgumet) {
        if (!pstrArgumet.equals("")) {
            //master
            if (ll_subtypeactivity != null && checkVisibility(ll_subtypeactivity)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_subtypeactivity.getId()).toString());
            }
            if (ll_kategoryPlanogram != null && checkVisibility(ll_kategoryPlanogram)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_kategoryPlanogram.getId()).toString());
            }
            if (ll_outlet != null && checkVisibility(ll_outlet)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_outlet.getId()).toString());
            }
            if (ll_branch != null && checkVisibility(ll_branch)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_branch.getId()).toString());
            }
            if (ll_type_leave != null && checkVisibility(ll_type_leave)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_type_leave.getId()).toString());
            }
            if (ll_product != null && checkVisibility(ll_product)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_product.getId()).toString());
            }
            if (ll_brand != null && checkVisibility(ll_brand)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_brand.getId()).toString());
            }
            if (ll_kategoriVisitPlan != null && checkVisibility(ll_kategoriVisitPlan)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_kategoriVisitPlan.getId()).toString());
            }
            if (ll_product_competitor != null && checkVisibility(ll_product_competitor)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_product_competitor.getId()).toString());
            }
            if (ll_product_spg != null && checkVisibility(ll_product_spg)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_product_spg.getId()).toString());
            }
            if (ll_product_pic != null && checkVisibility(ll_product_pic)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_product_pic.getId()).toString());
            }
            if (ll_type_submission != null && checkVisibility(ll_type_submission)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_type_submission.getId()).toString());
            }
            if (ll_dataCategoryKordinasiOutlet != null && checkVisibility(ll_dataCategoryKordinasiOutlet)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_dataCategoryKordinasiOutlet.getId()).toString());
            }
            if (ll_dataQuesioner != null && checkVisibility(ll_dataQuesioner)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_dataQuesioner.getId()).toString());
            }
            //transaksi
            if (ll_dataVisitPlan != null && checkVisibility(ll_dataVisitPlan)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_dataVisitPlan.getId()).toString());
            }
            if (ll_data_attendance != null && checkVisibility(ll_data_attendance)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_data_attendance.getId()).toString());
            }
            if (ll_reso != null && checkVisibility(ll_reso)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_reso.getId()).toString());
            }
            if (ll_purchase_order != null && checkVisibility(ll_purchase_order)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_purchase_order.getId()).toString());
            }
            if (ll_data_stockIH != null && checkVisibility(ll_data_stockIH)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_data_stockIH.getId()).toString());
            }
            if (ll_dataQuantityStock != null && checkVisibility(ll_dataQuantityStock)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_dataQuantityStock.getId()).toString());
            }
            if (ll_data_overStock != null && checkVisibility(ll_data_overStock)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_data_overStock.getId()).toString());
            }
            if (ll_data_StockOut != null && checkVisibility(ll_data_StockOut)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_data_StockOut.getId()).toString());
            }
            if (ll_dataKordinasiOutlet != null && checkVisibility(ll_dataKordinasiOutlet)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_dataKordinasiOutlet.getId()).toString());
            }
            if (ll_data_planogram != null && checkVisibility(ll_data_planogram)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_data_planogram.getId()).toString());
            }
            if (ll_data_activity != null && checkVisibility(ll_data_activity)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_data_activity.getId()).toString());
            }
            if (ll_data_activityV2 != null && checkVisibility(ll_data_activityV2)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_data_activityV2.getId()).toString());
            }
            if (ll_data_addDisplay != null && checkVisibility(ll_data_addDisplay)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_data_addDisplay.getId()).toString());
            }
            if (ll_data_customerbased != null && checkVisibility(ll_data_customerbased)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_data_customerbased.getId()).toString());
            }
            if (ll_absen != null && checkVisibility(ll_absen)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_absen.getId()).toString());
            }
            if (ll_data_leave != null && checkVisibility(ll_data_leave)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_data_leave.getId()).toString());
            }
            if (ll_data_kemasanrusak != null && checkVisibility(ll_data_kemasanrusak)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_data_kemasanrusak.getId()).toString());
            }
            if (ll_data_tidaksesuaipesanan != null && checkVisibility(ll_data_tidaksesuaipesanan)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_data_tidaksesuaipesanan.getId()).toString());
            }
            if (ll_dataPOP_Standard != null && checkVisibility(ll_dataPOP_Standard)) {
                saveDatatLogDownloadData(pstrArgumet, res.getResourceEntryName(ll_dataPOP_Standard.getId()).toString());
            }

            setViewTextLastDownload();
        }
    }

    private void setViewTextLastDownload() {
        tUserLoginData dtLogin = new tUserLoginBL().getUserLogin();
        SQLiteDatabase db = new clsMainBL().getDb();
        String pstrArgument = "";
        clsMainActivity _clsMainActivity = new clsMainActivity();
        //master
        if (ll_subtypeactivity != null && checkVisibility(ll_subtypeactivity)) {
            txtsubTypeActivity.setText("Sub Type Activity (" + String.valueOf(new tSubTypeActivityDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_subtypeactivity.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtsubTypeActivityLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_kategoryPlanogram != null && checkVisibility(ll_kategoryPlanogram)) {
            txtkategoryPlanogram.setText("Category Planogram (" + String.valueOf(new tKategoryPlanogramMobileDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_kategoryPlanogram.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtkategoryPlanogramLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_outlet != null && checkVisibility(ll_outlet)) {
            txtOutlet.setText("Outlet (" + String.valueOf(new mEmployeeAreaDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_outlet.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtOutletLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_branch != null && checkVisibility(ll_branch)) {
            txtBranch.setText("Branch (" + String.valueOf(new mEmployeeBranchDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_branch.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtBranchLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_type_leave != null && checkVisibility(ll_type_leave)) {
            txtVwLeave.setText("Type Leave (" + String.valueOf(new mTypeLeaveMobileDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_type_leave.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtLeaveLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_product != null && checkVisibility(ll_product)) {
            txtProduct.setText("Product (" + String.valueOf(new mEmployeeSalesProductDA(db).getContactsCountByKN(db, mUserLOBDataList, dtLogin.get_TxtEmpId())) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_product.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtProductLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_brand != null && checkVisibility(ll_brand)) {
            textView7.setText("Brand (" + String.valueOf(new mProductBrandHeaderDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_brand.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                textView7LastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_kategoriVisitPlan != null && checkVisibility(ll_kategoriVisitPlan)) {
            txtVwVisitPlan.setText("Kategori visit plan (" + String.valueOf(new mCategoryVisitPlanDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_kategoriVisitPlan.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtVisitPlanLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_product_competitor != null && checkVisibility(ll_product_competitor)) {
            txtVwProductComp.setText("Product Competitor (" + String.valueOf(new mProductCompetitorDA(db).getContactsCountByKN(db, mUserLOBDataList, dtLogin.get_TxtEmpId())) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_product_competitor.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtProductCompLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_product_spg != null && checkVisibility(ll_product_spg)) {
            txtVwProductSPGCusBase.setText("Product SPG CustomerBased (" + String.valueOf(new mProductSPGDA(db).getContactsCountByKN(db, mUserLOBDataList, dtLogin.get_TxtEmpId())) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_product_spg.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtProductSPGCusBaseLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_product_pic != null && checkVisibility(ll_product_pic)) {
            txtVwProductPICCusBase.setText("Product PIC CustomerBased (" + String.valueOf(new mProductPICDA(db).getContactsCountByKN(db, mUserLOBDataList, dtLogin.get_TxtEmpId())) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_product_pic.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtProductPICCusBaseLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_type_submission != null && checkVisibility(ll_type_submission)) {
            txtVwTypeSubm.setText("Type Submission (" + String.valueOf(new mTypeSubmissionMobileDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_type_submission.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtTypeSubmLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_dataCategoryKordinasiOutlet != null && checkVisibility(ll_dataCategoryKordinasiOutlet)) {
            txtCategoryKoordinasi.setText("Kategori Koordinasi Outlet (" + String.valueOf(new mCategoryKoordinasiOutletDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_dataCategoryKordinasiOutlet.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtCategoryKoordinasiLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_dataQuesioner != null && checkVisibility(ll_dataQuesioner)) {
            tv_SPG.setText("List SPG (" + String.valueOf(new tHirarkiBISDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_dataQuesioner.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtSPGLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
                txtquisLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        //transaksi
        if (ll_dataVisitPlan != null && checkVisibility(ll_dataVisitPlan)) {
            txtTrVisitPlan.setText("Transaksi visit plan (" + String.valueOf(new tVisitPlanRealisasiDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_dataVisitPlan.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtTrVisitPlanLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_data_attendance != null && checkVisibility(ll_data_attendance)) {
            txtVwAbsenFPE.setText("Attendance FPE (" + String.valueOf(new tAbsenUserDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_data_attendance.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtAbsenFPELastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_reso != null && checkVisibility(ll_reso)) {
            txtVwReso.setText("Reso (" + String.valueOf(new tSalesProductHeaderDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_reso.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtResoLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_purchase_order != null && checkVisibility(ll_purchase_order)) {
            txtTrPO.setText("Purchase Order (" + String.valueOf(new tPurchaseOrderHeaderDA(db).getContactCountPO(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_purchase_order.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtTrPOLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_data_stockIH != null && checkVisibility(ll_data_stockIH)) {
            txtVwstockIH.setText("Stock On Hand (" + String.valueOf(new tStockInHandHeaderDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_data_stockIH.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtstockIHLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_dataQuantityStock != null && checkVisibility(ll_dataQuantityStock)) {
            txtTrQuantityStock.setText("Near ED (" + String.valueOf(new tSalesProductQuantityHeaderDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_dataQuantityStock.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtTrQuantityStockLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_data_overStock != null && checkVisibility(ll_data_overStock)) {
            txtVwDataOverStock.setText("Over Stock (" + String.valueOf(new tOverStockHeaderDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_data_overStock.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtOverStockLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_data_StockOut != null && checkVisibility(ll_data_StockOut)) {
            txtVwDataStockOut.setText("Stock Out (" + String.valueOf(new tStockOutHeaderDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_data_StockOut.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtStockOutLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_dataKordinasiOutlet != null && checkVisibility(ll_dataKordinasiOutlet)) {
            txtVwKordinasiOutlet.setText("Koordinasi Outlet (" + String.valueOf(new KoordinasiOutletDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_dataKordinasiOutlet.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtKordinasiOutletLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_data_planogram != null && checkVisibility(ll_data_planogram)) {
            txtTrPlanogram.setText("Planogram (" + String.valueOf(new tPlanogramMobileDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_data_planogram.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtPlanogramLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_data_activity != null && checkVisibility(ll_data_activity)) {
            txtVwActivity.setText("Activity (" + String.valueOf(new tActivityDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_data_activity.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtActivityLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_data_activityV2 != null && checkVisibility(ll_data_activityV2)) {
            txtVwActivityV2.setText("Aktivitas Promosi (" + String.valueOf(new tActivityMobileDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_data_activityV2.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtActivityV2LastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_data_addDisplay != null && checkVisibility(ll_data_addDisplay)) {
            txtVwaddDisplayV2.setText("Additional Display (" + String.valueOf(new tActivityMobileDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_data_addDisplay.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtaddDisplayV2LastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_data_customerbased != null && checkVisibility(ll_data_customerbased)) {
            txtVwCustomerBase.setText("Customer base (" + String.valueOf(new tCustomerBasedMobileHeaderDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_data_customerbased.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtCustomerBaseLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_absen != null && checkVisibility(ll_absen)) {
            txtVwAbsen.setText("Absen (" + String.valueOf(new tAbsenUserDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_absen.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtAbsenLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_data_leave != null && checkVisibility(ll_data_leave)) {
            txtVwDataLeave.setText("Leave (" + String.valueOf(new tLeaveMobileDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_data_leave.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtDataLeaveLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_data_kemasanrusak != null && checkVisibility(ll_data_kemasanrusak)) {
            txtVwDatakemasanrusak.setText("Kemasan Rusak (" + String.valueOf(new tKemasanRusakHeaderDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_data_kemasanrusak.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtkemasanrusakLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_data_tidaksesuaipesanan != null && checkVisibility(ll_data_tidaksesuaipesanan)) {
            txtVwDatatidaksesuaipesanan.setText("Tidak Sesuai Pesanan (" + String.valueOf(new tTidakSesuaiPesananHeaderDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_data_tidaksesuaipesanan.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txttidaksesuaipesananLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        if (ll_dataPOP_Standard != null && checkVisibility(ll_dataPOP_Standard)) {
            tv_POP.setText("POP Standard (" + String.valueOf(new tPOPStandardHeaderDA(db).getContactsCount(db)) + ")");
            tLogDownloadData _tLogDownloadData;
            _tLogDownloadData = new tLogDownloadBL().getDataById(res.getResourceEntryName(ll_dataPOP_Standard.getId()).toString());
            if (_tLogDownloadData != null && _tLogDownloadData.get_dtLastDownload().toString() != "null") {
                txtPOPLastDownload.setText("Last Download : " + _clsMainActivity.giveFormatDateWithTime(_tLogDownloadData.get_dtLastDownload().toString()));
            }
        }
        db.close();
    }

    private void saveDatatLogDownloadData(String pstrArgumet, String s) {
        tLogDownloadData _tLogDownloadData = new tLogDownloadData();
        List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

        _tLogDownloadData.set_txtModuleName(s);
        _tLogDownloadData.set_dtLastDownload(pstrArgumet);

        tLogDownloadDataList.add(_tLogDownloadData);
        new tLogDownloadBL().SaveData(tLogDownloadDataList);
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

            String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_kategoriVisitPlan.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);
            new tLogDownloadBL().SaveData(tLogDownloadDataList);

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
                            _data.set_txtBranchName((String) innerObjListoftTransaksiRealisasiVisitPlanData.get("txtBranchName"));
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

    private List<String> SaveDatatTransaksiVisitPlanDataV2(JSONArray JData) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        Iterator i = JData.iterator();
        List<tVisitPlanRealisasiData> _Listdata = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                tVisitPlanRealisasiData _data = new tVisitPlanRealisasiData();
                _data.set_txtDataIDRealisasi((String) innerObj.get("txtDataIdRealisasi"));
                _data.set_intCategoryVisitPlan((String) innerObj.get("intCategoryVisitPlan"));
                _data.set_intDetailID((String) innerObj.get("intDetailId"));
                _data.set_intHeaderID((String) innerObj.get("intHeaderId"));
                _data.set_intUserID((String) innerObj.get("intUserId"));
                _data.set_txtOutletCode((String) innerObj.get("txtOutletCode"));
                _data.set_txtOutletName((String) innerObj.get("txtOutletName"));
                _data.set_txtBranchCode((String) innerObj.get("txtBranchCode"));
                _data.set_txtBranchName((String) innerObj.get("txtBranchName"));
                _data.set_dtDate((String) innerObj.get("dtDate"));
                _data.set_intBobot((String) innerObj.get("intBobot"));
                _data.set_txtDesc((String) innerObj.get("txtDesc"));
                _data.set_bitActive((String) innerObj.get("bitActive"));
                _data.set_txtLongSource((String) innerObj.get("txtLong"));
                _data.set_txtLatSource((String) innerObj.get("txtLat"));
                _data.set_txtAcc((String) innerObj.get("txtAcc"));
                _data.set_intSubmit("0");
                _data.set_intPush("0");
                _data.set_intCheckout("0");
                _array.add(_data.get_txtOutletName() + "-" + _data.get_txtDesc());
                _Listdata.add(_data);
            }
        }
        new tVisitPlanRealisasiBL().downloadData(_Listdata);
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

    private void SaveDatatTransaksiVisitPlanHeaderDataV2(JSONArray JData) {
        APIData dtAPIDATA = new APIData();
        Iterator i = JData.iterator();
        List<tVisitPlanHeader_MobileData> _Listdata = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                tVisitPlanHeader_MobileData _data = new tVisitPlanHeader_MobileData();
                _data.set_intHeaderId((String) innerObj.get("intHeaderId"));
                _data.set_txtUserId((String) innerObj.get("txtUserId"));
                _data.set_txtPeriode((String) innerObj.get("txtPeriode"));
                _data.set_txtGuidUnplan((String) innerObj.get("txtGuidIdUnplan"));
                _data.set_intUnplan((String) innerObj.get("intUnplan"));
                _data.set_txtBranchCode((String) innerObj.get("txtBranchCode"));
                _data.set_bitActive((String) innerObj.get("bitActive"));
                _data.set_dtStart((String) innerObj.get("dtStart"));
                _data.set_dtEnd((String) innerObj.get("dtEnd"));
                _data.set_intSumBobot((String) innerObj.get("intSumBobot"));
                _data.set_intRealisasi((String) innerObj.get("intRealisasi"));
//                            _array.add(_data.get_txtOutletName()+"-"+_data.get_txtDesc());
                _data.set_intSubmit("0");
                _data.set_intPush("0");
                _Listdata.add(_data);
            }
        }
        new tVisitPlanHeader_MobileBL().saveData(_Listdata);
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

            String pstrArgumet = String.valueOf(innerObj.get(dtAPIDATA.getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_branch.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);
            new tLogDownloadBL().SaveData(tLogDownloadDataList);

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

    private List<String> SaveDatatSalesProductData(JSONArray JData, int intSource) {
        List<String> _array;
        _array = new ArrayList<>();
        for (Object aJData : JData) {
            JSONObject innerObj = (JSONObject) aJData;
            try {
                String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
                tLogDownloadData _tLogDownloadData = new tLogDownloadData();
                List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

                _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_reso.getId()).toString());
                _tLogDownloadData.set_dtLastDownload(pstrArgumet);

                tLogDownloadDataList.add(_tLogDownloadData);
                new tLogDownloadBL().SaveData(tLogDownloadDataList);

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

                        SQLiteDatabase db = new clsMainBL().getDb();
                        if(_data!=null){
                            tSalesProductHeaderData _tSalesProductHeaderData = new tSalesProductHeaderDA(db).getDataExist(db, _data.get_txtNoSo());
                                if(_tSalesProductHeaderData!=null){
                                    if(_tSalesProductHeaderData.get_txtNoSo()!=null){
                                        new tSalesProductHeaderDA(db).UpdateData(db, _data.get_txtNoSo(), _data.get_intId());
                                    }
                                }
                        }
                        db.close();

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

                        SQLiteDatabase db = new clsMainBL().getDb();
                        if(_data!=null){
                            new tSalesProductDetailDA(db).deleteByNOSO(db, _data.get_txtNoSo());
                        }
                        db.close();
                    }

                    Iterator l = JsonArray_detail.iterator();

                    while (l.hasNext()) {
                        tSalesProductDetailData _data = new tSalesProductDetailData();
                        JSONObject innerObj_detail = (JSONObject) l.next();
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
                    _db.close();
                } else {
                    //Jika dari button single
                    if(intSource == 1){
                        new clsMainActivity().showCustomToast(getContext(), "Data not found", false);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return _array;
    }

    private List<String> SaveDatatStockInHandData(JSONArray JData) {
        List<String> _array;
        _array = new ArrayList<>();
        for (Object aJData : JData) {
            JSONObject innerObj = (JSONObject) aJData;

            try {

                String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
                tLogDownloadData _tLogDownloadData = new tLogDownloadData();
                List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

                _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_data_stockIH.getId()).toString());
                _tLogDownloadData.set_dtLastDownload(pstrArgumet);

                tLogDownloadDataList.add(_tLogDownloadData);
                new tLogDownloadBL().SaveData(tLogDownloadDataList);

                JSONArray JsonArray_header = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDatatStockInHandHeader_mobile")));
                if (JsonArray_header != null) {

                    for (Object aJsonArray_header : JsonArray_header) {
                        tStockInHandHeaderData _data = new tStockInHandHeaderData();
                        JSONObject innerObj_detail = (JSONObject) aJsonArray_header;
                        _data.set_txtNoSo(String.valueOf(innerObj_detail.get("TxtNoSIH")));
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

                        SQLiteDatabase db = new clsMainBL().getDb();
                        if(_data!=null){
                            tStockInHandHeaderData _tStockInHandHeaderData = new tStockInHandHeaderDA(db).getDataExist(db, _data.get_txtNoSo());
                            if(_tStockInHandHeaderData!=null){
                                if(_tStockInHandHeaderData.get_txtNoSo()!=null){
                                    new tStockInHandHeaderDA(db).UpdateData(db, _data.get_txtNoSo(), _data.get_intId());
                                }
                            }
                        }

                        db.close();

                        new tStockInHandHeaderBL().SaveData(_data);
                    }

                    JSONArray JsonArray_detail = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDatatStockInHandDetail_mobile")));
                    Iterator k = JsonArray_detail.iterator();

                    clsMainBL _clsMainBL = new clsMainBL();
                    SQLiteDatabase _db = _clsMainBL.getDb();

                    while (k.hasNext()) {
                        tStockInHandDetailData _data = new tStockInHandDetailData();
                        JSONObject innerObj_detail = (JSONObject) k.next();
                        _data.set_txtNoSo(String.valueOf(innerObj_detail.get("TxtNoSIH")));

                        SQLiteDatabase db = new clsMainBL().getDb();
                        if(_data!=null){
                            new tStockInHandDetailDA(db).deleteByNOSO(db, _data.get_txtNoSo());
                        }
                        db.close();
                    }

                    Iterator l = JsonArray_detail.iterator();

                    while (l.hasNext()) {
                        tStockInHandDetailData _data = new tStockInHandDetailData();
                        JSONObject innerObj_detail = (JSONObject) l.next();
                        _data.set_txtNoSo(String.valueOf(innerObj_detail.get("TxtNoSIH")));
                        _data.set_txtNameProduct(String.valueOf(innerObj_detail.get("TxtNameProduct")));
                        _data.set_txtNoSo(String.valueOf(innerObj_detail.get("TxtNoSIH")));
                        _data.set_txtCodeProduct(String.valueOf(innerObj_detail.get("TxtCodeProduct")));
                        _data.set_intTotal(String.valueOf(innerObj_detail.get("IntTotal")));
                        _data.set_intPrice(String.valueOf(innerObj_detail.get("IntPrice")));
                        _data.set_intQty(String.valueOf(innerObj_detail.get("IntQty")));
                        _data.set_intId(String.valueOf(innerObj_detail.get("TxtDataId")));
                        new tStockInHandDetailDA(_db).SaveDatatStockInHandDetailData(_db, _data);
                    }
                    _db.close();
                } else {
//                    new clsMainActivity().showCustomToast(getContext(), "Data not found", false);
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

            String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_data_activity.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);
            new tLogDownloadBL().SaveData(tLogDownloadDataList);

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

            String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_data_activityV2.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);

            _tLogDownloadData = new tLogDownloadData();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_data_addDisplay.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);

            new tLogDownloadBL().SaveData(tLogDownloadDataList);

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
            _data.set_intIsValid(String.valueOf(innerObj.get("IntIsValid")));

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

            String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_subtypeactivity.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);
            new tLogDownloadBL().SaveData(tLogDownloadDataList);

            if (txtValid.equals("1")) {
                _data.set_bitActive(String.valueOf(innerObj.get("BitActive")));
                _data.set_txtType(String.valueOf(innerObj.get("TxtType")));
                _data.set_txtName(String.valueOf(innerObj.get("TxtName")));
                _data.set_intSubTypeActivity(String.valueOf(innerObj.get("IntSubTypeActivity")));
                ListDatatSubTypeActivityData.add(_data);
            } else {
//                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
                break;
            }

        }
        if (ListDatatSubTypeActivityData.size() > 0) {
            new tSubTypeActivityBL().saveData(ListDatatSubTypeActivityData);
        }
        return _array;
    }

    private List<String> SaveDataKategoryPlanogram(JSONArray JData) {
        List<String> _array;
        List<tKategoryPlanogramMobileData> ListDatatKategoryPlanogramData;
        _array = new ArrayList<>();
        Iterator i = JData.iterator();
        ListDatatKategoryPlanogramData = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            tKategoryPlanogramMobileData _data = new tKategoryPlanogramMobileData();
            String txtValid = String.valueOf(innerObj.get("_pboolValid"));

            String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_kategoryPlanogram.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);
            new tLogDownloadBL().SaveData(tLogDownloadDataList);

            if (txtValid.equals("1")) {
                _data.set_bitActive(String.valueOf(innerObj.get("BitActive")));
                _data.set_txtName(String.valueOf(innerObj.get("TxtName")));
                _data.set_intKategoryPlanogram(String.valueOf(innerObj.get("IntKategoryPlanogram")));
                _data.set_intIsCheckValid(String.valueOf(innerObj.get("IntIsCheckValid")));
                ListDatatKategoryPlanogramData.add(_data);
            } else {
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
                break;
            }

        }
        if (ListDatatKategoryPlanogramData.size() > 0) {
            new tKategoryPlanogramMobileBL().saveData(ListDatatKategoryPlanogramData);
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

            String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_absen.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);
            new tLogDownloadBL().SaveData(tLogDownloadDataList);

            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                tAbsenUserData _data = new tAbsenUserData();
                _data.set_txtAbsen(String.valueOf(innerObj.get("TxtDataId")));
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
        SQLiteDatabase db = new clsMainBL().getDb();

        if(ListdataAbsen!=null){
            for(tAbsenUserData dt : ListdataAbsen){
                tAbsenUserData _tAbsenUserData = new tAbsenUserDA(db).getDataExist(db, dt.get_txtAbsen());
                if(_tAbsenUserData!=null){
                    if(_tAbsenUserData.get_txtAbsen()!=null){
//                        dt.set_intId(dt.get_txtAbsen());
                        new tAbsenUserDA(db).UpdateDatatxtAbsen(db, dt.get_txtAbsen());
                    }
                }
            }
        }
        db.close();
        new tAbsenUserBL().saveData(ListdataAbsen);
        return _array;
    }

    private List<String> SaveDatatAttendanceUserData(JSONArray JData) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        Iterator i = JData.iterator();
        List<tAttendanceUserData> ListtAttendance = new ArrayList<>();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();

            String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_data_attendance.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);
            new tLogDownloadBL().SaveData(tLogDownloadDataList);

            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                tAttendanceUserData _data = new tAttendanceUserData();
                _data.set_dtDateCheckIn(String.valueOf(innerObj.get("DtCheckIn")));
                _data.set_dtDateCheckOut(String.valueOf(innerObj.get("DtCheckOut")));
                _data.set_intSubmit("1");
                _data.set_intSync("1");
                _data.set_txtAbsen(String.valueOf(innerObj.get("TxtDataId")));
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
                _data.set_txtDesc(String.valueOf(innerObj.get("TxtDesc")));

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

                new tAttendanceUserBL().saveData(_data);

            } else {
                continue;
            }
        }
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

            String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_data_leave.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);
            new tLogDownloadBL().SaveData(tLogDownloadDataList);

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

    private byte[] getImageAndFile(String url, String namaFile) {
        OutputStream out = null;
        InputStream in = null;
        File mediaStorageDir = new File(new clsHardCode().txtFolderQuiz + File.separator);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("File", "Failed create directory");
                return null;
            }
        }
        // Create a media file name
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + namaFile);
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL imageUrl = new URL(url);
            URLConnection ucon = imageUrl.openConnection();
            String contentType = ucon.getHeaderField("Content-Type");
            boolean image = contentType.startsWith("image/");
            boolean text = contentType.startsWith("application/");

            if (image) {
                InputStream is = ucon.getInputStream();
                in = new BufferedInputStream(is, 1024);
                out = new FileOutputStream(mediaFile);

                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                is.close();
                out.close();
                BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                Bitmap bm = BitmapFactory.decodeFile(mediaFile.getPath().toString(), bitmapOptions);
                byte[] byteQuiz = ImagePick.byteQuiz(bm);
                return byteQuiz;
            } else if (text) {
                InputStream is = ucon.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                out = new FileOutputStream(mediaFile);
                ByteArrayBuffer baf = new ByteArrayBuffer(500);
                int current;
                while ((current = bis.read()) != -1) {
                    baf.append((byte) current);
                }

                out.write(baf.toByteArray());
                // Transfer bytes from in to out
                out.close();
                byte[] byteFile = null;
                try {
                    byteFile = ImagePick.getFile(Uri.parse(mediaFile.getPath().toString()), getContext());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
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

                String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
                tLogDownloadData _tLogDownloadData = new tLogDownloadData();
                List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

                _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_data_customerbased.getId()).toString());
                _tLogDownloadData.set_dtLastDownload(pstrArgumet);

                tLogDownloadDataList.add(_tLogDownloadData);
                new tLogDownloadBL().SaveData(tLogDownloadDataList);

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

    private List<String> SaveDatamProductBrandHeaderData(JSONArray JsonArray) {
        List<String> _array = new ArrayList<>();
        APIData dtAPIDATA = new APIData();
        Iterator i = JsonArray.iterator();
        Boolean flag = true;
        String ErrorMess = "";
        new mProductBrandHeaderBL().DeleteAllData();
        int intsum = new mProductBrandHeaderBL().getContactsCount();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                intsum += 1;
                mProductBrandHeaderData _data = new mProductBrandHeaderData();
                //mEmployeeSalesProductData _dataProperty =new mEmployeeSalesProductData();
                _data.set_intmProductUmbrandId((String) innerObj.get("IntmProductUmbrandId"));
                _data.set_txtAliasName((String) innerObj.get("TxtAliasName"));
                _data.set_txtProductBrandCode((String) innerObj.get("TxtProductBrandCode"));
                _data.set_txtProductBrandName((String) innerObj.get("TxtProductBrandName"));
                new mProductBrandHeaderBL().saveData(_data);
            } else {

            }
        }
        return _array;
    }

    private List<String> SaveDatamEmployeeSalesProductData(JSONArray JData) {
        List<String> _array = new ArrayList<>();
        APIData dtAPIDATA = new APIData();
        Iterator i = JData.iterator();
        Boolean flag = true;
        String ErrorMess = "";
        new mEmployeeSalesProductBL().DeleteAllData();
        int intsum = new mEmployeeSalesProductBL().getContactsCount();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                intsum += 1;
                mEmployeeSalesProductData _data = new mEmployeeSalesProductData();
                //mEmployeeSalesProductData _dataProperty =new mEmployeeSalesProductData();
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
                new mEmployeeSalesProductBL().saveData(_data);
            } else {

            }
        }
        return _array;
    }

    private List<String> SaveDatamEmployeeSalesProductDataV2(JSONArray JData) {
        List<String> _array = new ArrayList<>();
        APIData dtAPIDATA = new APIData();
        Iterator i = JData.iterator();
        Boolean flag = true;
        String ErrorMess = "";
        new mEmployeeSalesProductBL().DeleteAllData();
        int intsum = new mEmployeeSalesProductBL().getContactsCount();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                intsum += 1;
                mEmployeeSalesProductData _data = new mEmployeeSalesProductData();
                //mEmployeeSalesProductData _dataProperty =new mEmployeeSalesProductData();
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
                new mEmployeeSalesProductBL().saveData(_data);
            } else {

            }
        }
        return _array;
    }

    private class AsyncCallPOPStandard extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new mTypePOPStandardBL().DownlaodDataPOPStandard(pInfo.versionName);
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
                arrData = SaveDataPOPStandard(jsonArray);
                loadData();
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Getting POP Standard");
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

    private class AsyncCallQuis extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new mParentBL().DownlaodDataQuesioner(pInfo.versionName);
//                Json = new mParentBL().DownlaodDataSPGfromTL(pInfo.versionName);
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
//                arrData = SaveDataSPGFromTL(jsonArray);
                loadData();
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
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
                Json = new mEmployeeSalesProductBL().DownloadEmployeeSalesProduct(pInfo.versionName, mUserLOBDataList, res.getResourceEntryName(ll_product.getId()).toString());
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
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
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
                Json = new mProductBrandHeaderBL().DownloadBrandHeader(pInfo.versionName, res.getResourceEntryName(ll_brand.getId()).toString());
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
                new clsMainActivity().showCustomToast(getContext(), "Saving Data", true);
                loadData();
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
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
                arrData = SaveDatatSalesProductData(roledata, 1);
                //spnBranch.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                setViewTextLastDownload();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
//            checkingDataTable("");
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

    private class AsyncCallStockIH extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new tStockInHandHeaderBL().DownloadSIH(pInfo.versionName);
                new tStockInHandHeaderBL().DownloadNOSIH(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
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
                arrData = SaveDatatStockInHandData(roledata);
                //spnBranch.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                setViewTextLastDownload();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
//            checkingDataTable("");
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Stock On Hand");
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
                setViewTextLastDownload();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
//            checkingDataTable("");
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
                setViewTextLastDownload();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
//            checkingDataTable("");
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Aktivitas Promosi");
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

    private class AsyncCallAddDisPlay extends AsyncTask<JSONArray, Void, JSONArray> {
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
                setViewTextLastDownload();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
//            checkingDataTable("");
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Add Display");
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
                setViewTextLastDownload();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
//            checkingDataTable("");
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
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
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

    private class AsyncCallAttendanceFpe extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new tAttendanceUserBL().DownloadAttendance(pInfo.versionName);
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
                arrData = SaveDatatAttendanceUserData(roledata);
                loadData();
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Getting Attendance FPE");
            Dialog.setCancelable(false);
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
                setViewTextLastDownload();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
//            checkingDataTable("");
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            dialog.dismiss();
        }
    }

    private class AsyncCallDataOverStock extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new tOverStockHeaderBL().DownloadTransactionOverStock(pInfo.versionName);
                new tOverStockHeaderBL().DownloadNOOverStock(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
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
            dialog.setMessage("Getting Data Over Stock");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray != null && jsonArray.size() > 0) {
                arrData = SaveDatatOverStockData(jsonArray);
                loadData();
                setViewTextLastDownload();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
//            checkingDataTable("");
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            dialog.dismiss();
        }
    }

    private class AsyncCallDataStockOut extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new tStockOutHeaderBL().DownloadTransactionStockOut(pInfo.versionName);
//                new tOverStockHeaderBL().DownloadNOOverStock(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
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
            dialog.setMessage("Getting Data Stock Out");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray != null && jsonArray.size() > 0) {
                arrData = SaveDatatStockOutData(jsonArray);
                loadData();
                setViewTextLastDownload();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
//            checkingDataTable("");
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
            dialog.setMessage("Getting Data Near ED");
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
                setViewTextLastDownload();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
//            checkingDataTable("");
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            dialog.dismiss();
        }
    }

    private class AsyncCallDataKemasanRusak extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new tKemasanRusakHeaderBL().DownloadTransactionKemasanRusak(pInfo.versionName);
                new tKemasanRusakHeaderBL().DownloadNOKemasanRusak(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
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
            dialog.setMessage("Getting Data Kemasan Rusak");
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
                arrData = SaveDatatKemasanRusakData(jsonArray);
                loadData();
                setViewTextLastDownload();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
//            checkingDataTable("");
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
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
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
//            checkingDataTable("");
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            dialog.dismiss();
        }
    }

    private class AsyncCallDataTidakSesuaiPesanan extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new tTidakSesuaiPesananHeaderBL().DownloadDataTidakSesuaiPesanan(pInfo.versionName);
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
            dialog.setMessage("Getting Data");
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
                arrData = SaveDataTidakSesuaiPesanan(jsonArray);
                loadData();
                setViewTextLastDownload();
//                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
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
                setViewTextLastDownload();
//                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            dialog.dismiss();
        }
    }

    private class AsyncCallDataCategoryKoordinasiOutlet extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new KoordinasiOutletBL().DownloadDataCategoryKoordinasiOutlet(pInfo.versionName);
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
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray != null && jsonArray.size() > 0) {
                arrData = SaveDataCategoryKoordinasiOutletData(jsonArray);
                loadData();
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
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
                setViewTextLastDownload();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
//            checkingDataTable("");
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

            String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_outlet.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);
            new tLogDownloadBL().SaveData(tLogDownloadDataList);

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
                Dialog.setMessage("Saving Data");
                new clsMainActivity().showCustomToast(getContext(), "Saving Data", true);
                arrData = SaveDatamEmployeeAreaData(roledata);
                loadData();
                setViewTextLastDownload();
                checkingDataTable("");
                //spnOutlet.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
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
                Dialog.setTitle("Saving Data");
                new clsMainActivity().showCustomToast(getContext(), "Saving Data", true);
                arrData = SaveDatamEmployeeBranchData(roledata);
                //spnBranch.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
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
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
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
                Json = new tSubTypeActivityBL().DownloadtSubTypeActivity(pInfo.versionName, loginData.get_txtRoleId());
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
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
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

    private class AsyncCallKategoryPlanogram extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new tKategoryPlanogramMobileBL().DownloadKategoryPlanogram(pInfo.versionName, loginData.get_txtRoleId());
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
                arrData = SaveDataKategoryPlanogram(roledata);
                //spnBranch.setAdapter(new MyAdapter(getApplicationContext(), R.layout.custom_spinner, arrData));
                loadData();
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Kategory Planogram");
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
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
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

    private void checkingDataTable(String flag) {
        List<mEmployeeBranchData> mEmployeeBranchDataList;
        List<mEmployeeSalesProductData> employeeSalesProductDataList;
        List<mEmployeeAreaData> mEmployeeAreaDataList;
        List<mProductBrandHeaderData> mProductBrandHeaderDataList;
        List<mTypeLeaveMobileData> mTypeLeaveMobileDataList;
        List<mProductSPGData> mProductSPGDataList = new ArrayList<>();
        List<mProductPICData> mProductPICDataList = new ArrayList<>();
        List<mProductCompetitorData> mProductCompetitorDataList = new ArrayList<>();
        List<mTypeSubmissionMobile> mTypeSubmissionMobileList = new ArrayList<>();
        List<tSubTypeActivityData> tSubTypeActivityDataList = new ArrayList<>();
        List<tKategoryPlanogramMobileData> tKategoryPlanogramMobileDataList = new ArrayList<>();

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
        mProductSPGDataList = new mProductSPGBL().GetAllData();
        mProductPICDataList = new mProductPICBL().GetAllData();
        mProductCompetitorDataList = new mProductCompetitorBL().GetAllData();
        mTypeSubmissionMobileList = new mTypeSubmissionMobileBL().GetAllData();
        tSubTypeActivityDataList = new tSubTypeActivityBL().getAllData();
        tKategoryPlanogramMobileDataList = new tKategoryPlanogramMobileBL().getAllData();

//        List<mEmployeeAreaData> data = _mEmployeeAreaBL.GetAllData();

//        int validate = 0;

//        if (mEmployeeBranchDataList.size() > 0
//                && employeeSalesProductDataList.size() > 0
//                && mEmployeeAreaDataList.size() > 0
//                && mProductBrandHeaderDataList.size() > 0
//                && mTypeLeaveMobileDataList.size() > 0
////                && mCategoryVisitPlanList.size()>0
//                ) {
//
////            goToMainMenu();
//            //validate = 1;
//
////            for(mEmployeeAreaData dt : data){
////                if(dt.get_txtLatitude()==""||dt.get_txtLatitude()==null&&dt.get_txtLongitude()==""||dt.get_txtLongitude()==null){
////                    validate = 0;
////                }
////            }
//        }
//        goToMainMenu();

//        if(validate==1){
//            goToMainMenu();
//        }
        if(flag.equals("ALL")){
            boolean validToMainMenu=true;

            if(ll_branch.getVisibility()==View.VISIBLE){
                if(mEmployeeBranchDataList.size() == 0){
                    validToMainMenu=false;
                }
            }
            if(ll_outlet.getVisibility()==View.VISIBLE){
                if(mEmployeeAreaDataList.size() == 0){
                    validToMainMenu=false;
                }
            }
//            if(ll_product.getVisibility()==View.VISIBLE){
//                if(employeeSalesProductDataList.size() == 0){
//                    validToMainMenu=false;
//                }
//            }
            if(ll_brand.getVisibility()==View.VISIBLE){
                if(mProductBrandHeaderDataList.size() == 0){
                    validToMainMenu=false;
                }
            }
            if(ll_type_leave.getVisibility()==View.VISIBLE){
                if(mTypeLeaveMobileDataList.size() == 0){
                    validToMainMenu=false;
                }
            }
//            if(ll_product_spg.getVisibility()==View.VISIBLE){
//                if(mProductSPGDataList.size() == 0){
//                    validToMainMenu=false;
//                }
//            }
//            if(ll_product_pic.getVisibility()==View.VISIBLE){
//                if(mProductPICDataList.size() == 0){
//                    validToMainMenu=false;
//                }
////            }
//            if(ll_product_competitor.getVisibility()==View.VISIBLE){
//                if(mProductCompetitorDataList.size() == 0){
//                    validToMainMenu=false;
//                }
//            }
            if(ll_type_submission.getVisibility()==View.VISIBLE){
                if(mTypeSubmissionMobileList.size() == 0){
                    validToMainMenu=false;
                }
            }
            if(ll_subtypeactivity.getVisibility()==View.VISIBLE){
                if(tSubTypeActivityDataList.size() == 0){
                    validToMainMenu=false;
                }
            }
            if(ll_kategoryPlanogram.getVisibility()==View.VISIBLE){
                if(tKategoryPlanogramMobileDataList.size() == 0){
                    validToMainMenu=false;
                }
            }
            if(ll_reso.getVisibility()==View.VISIBLE){

            }
            if(ll_dataQuantityStock.getVisibility()==View.VISIBLE){

            }
            if(ll_data_stockIH.getVisibility()==View.VISIBLE){

            }
            if(validToMainMenu){
                goToMainMenu();
            }
        } else {
            boolean validToMainMenu=true;

            if(ll_branch.getVisibility()==View.VISIBLE){
                if(mEmployeeBranchDataList.size() == 0){
                    validToMainMenu=false;
                }
            }
            if(ll_outlet.getVisibility()==View.VISIBLE){
                if(mEmployeeAreaDataList.size() == 0){
                    validToMainMenu=false;
                }
            }
//            if(ll_product.getVisibility()==View.VISIBLE){
//                if(employeeSalesProductDataList.size() == 0){
//                    validToMainMenu=false;
//                }
//            }
            if(ll_brand.getVisibility()==View.VISIBLE){
                if(mProductBrandHeaderDataList.size() == 0){
                    validToMainMenu=false;
                }
            }
            if(ll_type_leave.getVisibility()==View.VISIBLE){
                if(mTypeLeaveMobileDataList.size() == 0){
                    validToMainMenu=false;
                }
            }
//            if(ll_product_spg.getVisibility()==View.VISIBLE){
//                if(mProductSPGDataList.size() == 0){
//                    validToMainMenu=false;
//                }
//            }
//            if(ll_product_pic.getVisibility()==View.VISIBLE){
//                if(mProductPICDataList.size() == 0){
//                    validToMainMenu=false;
//                }
//            }
//            if(ll_product_competitor.getVisibility()==View.VISIBLE){
//                if(mProductCompetitorDataList.size() == 0){
//                    validToMainMenu=false;
//                }
//            }
            if(ll_type_submission.getVisibility()==View.VISIBLE){
                if(mTypeSubmissionMobileList.size() == 0){
                    validToMainMenu=false;
                }
            }
            if(ll_subtypeactivity.getVisibility()==View.VISIBLE){
                if(tSubTypeActivityDataList.size() == 0){
                    validToMainMenu=false;
                }
            }
            if(ll_kategoryPlanogram.getVisibility()==View.VISIBLE){
                if(tKategoryPlanogramMobileDataList.size() == 0){
                    validToMainMenu=false;
                }
            }
            if(ll_reso.getVisibility()==View.VISIBLE){

            }
            if(ll_dataQuantityStock.getVisibility()==View.VISIBLE){

            }
            if(ll_data_stockIH.getVisibility()==View.VISIBLE){

            }
            if(validToMainMenu){
                goToMainMenu();
            }
        }
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

            String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_type_leave.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);
            new tLogDownloadBL().SaveData(tLogDownloadDataList);

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
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
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
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), strMessage, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
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

            String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_product_competitor.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);
            new tLogDownloadBL().SaveData(tLogDownloadDataList);

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
        if (_Listdata.size() > 0) {
            new mProductCompetitorBL().deleteAllDataByKN(mUserLOBDataList);
        }
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
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), strMessage, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
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
//        new mProductSPGBL().deleteAllData();
        int intsum = new mProductSPGBL().getContactCount();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();

            String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_product_spg.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);
            new tLogDownloadBL().SaveData(tLogDownloadDataList);

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
        if (_Listdata.size() > 0) {
            new mProductSPGBL().deleteAllDataByKN(mUserLOBDataList);
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
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), strMessage, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
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
//        new mProductPICBL().deleteAllData();
        int intsum = new mProductPICBL().getContactCount();
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();

            String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_product_pic.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);
            new tLogDownloadBL().SaveData(tLogDownloadDataList);

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
        if (_Listdata.size() > 0) {
            new mProductPICBL().deleteAllDataByKN(mUserLOBDataList);
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
                setViewTextLastDownload();
                checkingDataTable("");
                new clsMainActivity().showCustomToast(getContext(), strMessage, true);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }

            }
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

            String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
            tLogDownloadData _tLogDownloadData = new tLogDownloadData();
            List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

            _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_type_submission.getId()).toString());
            _tLogDownloadData.set_dtLastDownload(pstrArgumet);

            tLogDownloadDataList.add(_tLogDownloadData);
            new tLogDownloadBL().SaveData(tLogDownloadDataList);

            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                strMessage = (String) innerObj.get(dtAPIDATA.strMessage);
                mTypeSubmissionMobile _data = new mTypeSubmissionMobile();
                _data.set_txtMasterID(String.valueOf(innerObj.get("TxtMasterID")));
                _data.set_txtGrupMasterID(String.valueOf(innerObj.get("TxtGrupMasterID")));
                _data.set_txtKeterangan(String.valueOf(innerObj.get("TxtKeterangan")));
                _data.set_txtNamaMasterData(String.valueOf(innerObj.get("TxtNamaMasterData")));
                _data.set_intLastActiveSelection("0");
                _data.set_BitMandatoryProductCompetitor(String.valueOf(innerObj.get("BitMandatoryProductCompetitor")));
//                _data.set_BitMandatoryProductCompetitor("0");
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
                String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
                tLogDownloadData _tLogDownloadData = new tLogDownloadData();
                List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

                _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_dataQuesioner.getId()).toString());
                _tLogDownloadData.set_dtLastDownload(pstrArgumet);

                tLogDownloadDataList.add(_tLogDownloadData);
                new tLogDownloadBL().SaveData(tLogDownloadDataList);
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
                        _data.set_dtEnd(String.valueOf(innerObj_GroupQuestion.get("DtDateEnd")));
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

                    JSONArray jsonArray_listSPG = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDataSpGFromTl_mobile")));
                    int id = 0;
                    if (jsonArray_listSPG != null) {
                        for (Object aJsonArray_listSPG : jsonArray_listSPG) {
                            JSONObject innerObj_listSPG = (JSONObject) aJsonArray_listSPG;
                            tHirarkiBIS _data = new tHirarkiBIS();
                            id += 1;
                            _data.set_intId(String.valueOf(id));
                            _data.set_txtNik(String.valueOf(innerObj_listSPG.get("IntNIK")));
                            _data.set_txtName(String.valueOf(innerObj_listSPG.get("TxtName")));
                            _data.set_txtLOB(String.valueOf(innerObj_listSPG.get("TxtLOB")));
                            _data.set_intBranchId(String.valueOf(innerObj_listSPG.get("IntBranchId")));
                            _data.set_txtBranchCode(String.valueOf(innerObj_listSPG.get("TxtBranchCode")));
                            _data.set_txtBranchName(String.valueOf(innerObj_listSPG.get("TXtBranchName")));
                            _data.set_intOutletId(String.valueOf(innerObj_listSPG.get("IntOutletId")));
                            _data.set_txtOutletCode(String.valueOf(innerObj_listSPG.get("TxtOutletCode")));
                            _data.set_txtOutletName(String.valueOf(innerObj_listSPG.get("TxtOutletName")));
                            new tHirarkiBISBL().SaveDataSPGFromTL(_data);
                        }
                    }

                    JSONArray jsonArray_jawabanSPG = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtJawabanSPG_mobile")));
                    for (Object ajsonArray_jawabanSPG : jsonArray_jawabanSPG) {
                        tJawabanUserData _data = new tJawabanUserData();
                        JSONObject innerObj_JawabanSPG = (JSONObject) ajsonArray_jawabanSPG;
                        _data.set_intUserAnswer(String.valueOf(innerObj_JawabanSPG.get("IntJawabanSPG")));
                        _data.set_intHeaderId(String.valueOf(innerObj_JawabanSPG.get("IntHeaderId")));
                        _data.set_intUserId(String.valueOf(innerObj_JawabanSPG.get("IntUserId")));
                        _data.set_intNik(String.valueOf(innerObj_JawabanSPG.get("IntNik")));
                        _data.set_dtDate(String.valueOf(innerObj_JawabanSPG.get("DtDate")).replace("/", "-"));
                        _data.set_dtDatetime(String.valueOf(innerObj_JawabanSPG.get("DtDatetime")).replace("/", "-"));
                        _data.set_intRoleId(String.valueOf(innerObj_JawabanSPG.get("IntRoleId")));
                        _data.set_intQuestionId(String.valueOf(innerObj_JawabanSPG.get("IntQuestionId")));
                        _data.set_intTypeQuestionId(String.valueOf(innerObj_JawabanSPG.get("IntTypeQuestionId")));
                        _data.set_bolHaveAnswerList(String.valueOf(innerObj_JawabanSPG.get("BolHaveAnswerList")));
                        _data.set_intAnswerId(String.valueOf(innerObj_JawabanSPG.get("IntListAnswerId")));
                        _data.set_txtValue(String.valueOf(innerObj_JawabanSPG.get("TxtValueSPG")));
                        _data.set_decBobot(String.valueOf(innerObj_JawabanSPG.get("DecBobot")));
                        String urlImg = String.valueOf(innerObj_JawabanSPG.get("TxtLinkImg"));
                        String urlFile = String.valueOf(innerObj_JawabanSPG.get("TxtLinkFile"));
                        String value = String.valueOf(innerObj_JawabanSPG.get("TxtValueSPG"));
                        byte[] Img = null;
                        byte[] filedata = null;
                        if (urlImg != "null") {
                            Img = getImageAndFile(urlImg, value);
                        } else if (urlFile != "null") {
                            filedata = getImageAndFile(urlFile, value);
                        }

                        if (Img != null) {
                            _data.set_ptQuiz(Img);
                        }
                        if (filedata != null) {
                            _data.set_txtFileQuiz(filedata);
                        }
                        _data.set_intSubmit("1");
                        _data.set_intSync("1");
                        new tJawabanUserBL().SaveDatatJawabanUser(_data);
                    }
                    JSONArray jsonArray_jawabanSPGHeader = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtJawabanHeaderSPG_mobile")));
                    for (Object aJsonArray_jawabanSPGHeader : jsonArray_jawabanSPGHeader) {
                        tJawabanUserHeaderData _data = new tJawabanUserHeaderData();
                        JSONObject innerObj_JawabanSPGHeader = (JSONObject) aJsonArray_jawabanSPGHeader;
                        _data.set_intHeaderId(String.valueOf(innerObj_JawabanSPGHeader.get("IntHeaderId")));
                        _data.set_intGroupQuestionId(String.valueOf(innerObj_JawabanSPGHeader.get("IntGroupQuestionId")));
                        _data.set_intNik(String.valueOf(innerObj_JawabanSPGHeader.get("IntNik")));
                        _data.set_txtUserName(String.valueOf(innerObj_JawabanSPGHeader.get("TxtUserName")));
                        _data.set_txtOutletName(String.valueOf(innerObj_JawabanSPGHeader.get("TxtOutletName")));
                        _data.set_txtOutletCode(String.valueOf(innerObj_JawabanSPGHeader.get("TxtOutletCode")));
                        _data.set_dtDate(String.valueOf(innerObj_JawabanSPGHeader.get("DtDate")).replace("/", "-"));
                        _data.set_dtDatetime(String.valueOf(innerObj_JawabanSPGHeader.get("DtDatetime")).replace("/", "-"));
                        _data.set_intRoleId(String.valueOf(innerObj_JawabanSPGHeader.get("IntRoleId")));
                        _data.set_intSum(String.valueOf(innerObj_JawabanSPGHeader.get("IntSum")));
                        _data.set_intAverage(String.valueOf(innerObj_JawabanSPGHeader.get("IntAverage")));
                        _data.set_intSubmit("1");
                        _data.set_intSync("1");
                        new tJawabanUserHeaderBL().SaveDatatJawabanHeaderUser(_data);
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

    private List<String> SaveDataPOPStandard(JSONArray jsonArray) {
        List<String> _array;
        APIData dtAPIDATA = new APIData();
        _array = new ArrayList<>();
        for (Object aJsonArray : jsonArray) {
            JSONObject innerObj = (JSONObject) aJsonArray;
            try {
                int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
                String pstrArgumet = String.valueOf(innerObj.get(dtAPIDATA.getStrArgument()));
                tLogDownloadData _tLogDownloadData = new tLogDownloadData();
                List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

                _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_dataPOP_Standard.getId()).toString());
                _tLogDownloadData.set_dtLastDownload(pstrArgumet);

                tLogDownloadDataList.add(_tLogDownloadData);
                new tLogDownloadBL().SaveData(tLogDownloadDataList);

                if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                    JSONArray jsonArray_Type = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDataTypePOPStandard_mobile")));
                    for (Object aJsonArray_Type : jsonArray_Type) {
                        mTypePOPStandardData _data = new mTypePOPStandardData();
                        JSONObject innerObj_parent = (JSONObject) aJsonArray_Type;
                        _data.set_intId(String.valueOf(innerObj_parent.get("IntId")));
                        _data.set_txtType(String.valueOf(innerObj_parent.get("TxtType")));
                        _data.set_intFlagMandatori(String.valueOf(innerObj_parent.get("IntFlagMandatori")));
                        new mTypePOPStandardBL().SaveData(_data);
                    }

                    JSONArray jsonArray_reason = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDataReasonPOPStandard_mobile")));
                    for (Object aJsonArray_reason : jsonArray_reason) {
                        mReasonPOPStandardData _data = new mReasonPOPStandardData();
                        JSONObject innerObj_kategori = (JSONObject) aJsonArray_reason;
                        _data.set_intId(String.valueOf(innerObj_kategori.get("IntId")));
                        _data.set_txtReason(String.valueOf(innerObj_kategori.get("TxtReason")));
                        new mReasonPOPStandardBL().SaveData(_data);
                    }

                    JSONArray jsonArray_kategori = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDataCategoryPOPStandard_mobile")));
                    for (Object aJsonArray_kategori : jsonArray_kategori) {
                        mCategoryPOPStandardData _data = new mCategoryPOPStandardData();
                        JSONObject innerObj_kategori = (JSONObject) aJsonArray_kategori;
                        _data.set_intId(String.valueOf(innerObj_kategori.get("IntId")));
                        _data.set_txtCategoryCode(String.valueOf(innerObj_kategori.get("TxtCategoryCode")));
                        _data.set_txtCategoryName(String.valueOf(innerObj_kategori.get("TxtCategoryName")));
                        new mCategoryPOPStandardBL().SaveData(_data);
                    }

                    JSONArray jsonArray_POPHeader = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDataPOPStandardHeader_mobile")));
                    for (Object aJsonArray_POPHeader : jsonArray_POPHeader) {
                        tPOPStandardHeaderData _data = new tPOPStandardHeaderData();
                        JSONObject innerObj_POPHeader = (JSONObject) aJsonArray_POPHeader;
                        _data.set_intId(String.valueOf(innerObj_POPHeader.get("IntId")));
                        _data.set_txtType(String.valueOf(innerObj_POPHeader.get("TxtType")));
                        _data.set_bolHavePOP(String.valueOf(innerObj_POPHeader.get("BolHavePOP")));
                        _data.set_txtCategory(String.valueOf(innerObj_POPHeader.get("TxtCategory")));
                        _data.set_txtReason(String.valueOf(innerObj_POPHeader.get("TxtReason")));
                        _data.set_intRoleId(String.valueOf(innerObj_POPHeader.get("IntRoleId")));
                        _data.set_txtUserName(String.valueOf(innerObj_POPHeader.get("TxtUserName")));
                        _data.set_txtNIK(String.valueOf(innerObj_POPHeader.get("TxtNIK")));
                        _data.set_txtOutletName(String.valueOf(innerObj_POPHeader.get("TxtOutletName")));
                        _data.set_txtOutletCode(String.valueOf(innerObj_POPHeader.get("TxtOutletCode")));
                        _data.set_txtBranchName(String.valueOf(innerObj_POPHeader.get("TxtBranchName")));
                        _data.set_txtBranchCode(String.valueOf(innerObj_POPHeader.get("TxtBranchCode")));
                        _data.set_DtDatetime(String.valueOf(innerObj_POPHeader.get("")));
                        _data.set_intSync("1");
                        _data.set_intSubmit("1");
                        new tPOPStandardHeaderBL().SaveData(_data);
                    }

                    JSONArray jsonArray_POPDetail = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListDataPOPStandardDetail_mobile")));
                    for (Object aJsonArray_POPDetail : jsonArray_POPDetail) {
                        tPOPStandardDetailData _data = new tPOPStandardDetailData();
                        JSONObject innerObj_POPDetail = (JSONObject) aJsonArray_POPDetail;
                        _data.set_intId(String.valueOf(innerObj_POPDetail.get("IntId")));
                        _data.set_intHeaderId(String.valueOf(innerObj_POPDetail.get("IntHeaderId")));
                        _data.set_dtDatetime(String.valueOf(innerObj_POPDetail.get("DtDatetime")));
                        String urlImg1 = String.valueOf(innerObj_POPDetail.get("TxtLinkImg1"));
                        String urlImg2 = String.valueOf(innerObj_POPDetail.get("TxtLinkImg2"));
                        byte[] Img1 = null;
                        byte[] img2 = null;
                        if (urlImg1 != "null") {
                            Img1 = getLogoImage(urlImg1);
                        }
                        if (urlImg2 != "null") {
                            img2 = getLogoImage(urlImg2);
                        }

                        if (Img1 != null) {
                            _data.set_txtImg1(Img1);
                        }
                        if (img2 != null) {
                            _data.set_txtImg2(img2);
                        }
                        _data.set_intSync("1");
                        _data.set_intSubmit("1");
                        new tPOPStandardDetailBL().SaveData(_data);
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
                String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
                tLogDownloadData _tLogDownloadData = new tLogDownloadData();
                List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

                _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_purchase_order.getId()).toString());
                _tLogDownloadData.set_dtLastDownload(pstrArgumet);

                tLogDownloadDataList.add(_tLogDownloadData);
                new tLogDownloadBL().SaveData(tLogDownloadDataList);

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
                    _db.close();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), "Data Not Found", false);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return _array;
    }

    private List<String> SaveDatatStockOutData(JSONArray jsonArray) {
        List<String> _array;
        _array = new ArrayList<>();
        for (Object aJsonArray : jsonArray) {
            JSONObject innerObj = (JSONObject) aJsonArray;
            try {
                String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
                tLogDownloadData _tLogDownloadData = new tLogDownloadData();
                List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

                _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_data_StockOut.getId()).toString());
                _tLogDownloadData.set_dtLastDownload(pstrArgumet);

                tLogDownloadDataList.add(_tLogDownloadData);
                new tLogDownloadBL().SaveData(tLogDownloadDataList);

                JSONArray jsonArray_header = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtStockOutHeader_mobile")));
                if (jsonArray_header != null) {
                    for (Object aJsonArray_header : jsonArray_header) {
                        tStockOutHeaderData _data = new tStockOutHeaderData();
                        JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                        _data.set_intId(String.valueOf(innerObj_header.get("txtDataId")));
                        _data.set_txtOverStock(String.valueOf(innerObj_header.get("TxtNoStockOut")));
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
                        new tStockOutHeaderBL().SaveData(_data);
                    }

                    JSONArray jsonArray_Detail = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtStockOutDetail_mobile")));
                    Iterator k = jsonArray_Detail.iterator();
                    clsMainBL _clsMainBL = new clsMainBL();
                    SQLiteDatabase _db = _clsMainBL.getDb();
                    while (k.hasNext()) {
                        tStockOutDetailData _data = new tStockOutDetailData();
                        JSONObject innerObj_detail = (JSONObject) k.next();
                        _data.setIntId(String.valueOf(innerObj_detail.get("TxtTrStockOutDetail")));
                        _data.set_txtOverStock(String.valueOf(innerObj_detail.get("TxtNoStockOut")));
                        _data.set_dtDate(String.valueOf(innerObj_detail.get("DtDate")));
                        _data.set_intPrice(String.valueOf(innerObj_detail.get("IntPrice")));
                        _data.set_txtCodeProduct(String.valueOf(innerObj_detail.get("TxtCodeProduct")));
                        _data.set_txtKeterangan(String.valueOf(innerObj_detail.get("TxtKeterangan")));
                        _data.setTxtProduct(String.valueOf(innerObj_detail.get("TxtProduct")));
                        _data.setTxtExpireDate(String.valueOf(innerObj_detail.get("TxtExpireDate")));
                        _data.setTxtQuantity(String.valueOf(innerObj_detail.get("TxtQuantity")));
                        _data.set_intTotal(String.valueOf(innerObj_detail.get("IntTotal")));
                        _data.set_txtNIK(String.valueOf(innerObj_detail.get("TxtUserId")));
                        new tStockOutDetailDA(_db).SaveDatatOverStockDetailData(_db, _data);
                    }
                    _db.close();
                } else {
//                    new clsMainActivity().showCustomToast(getContext(), "Data Not Found", false);
                    _array.add("Data Stock Out Not Found");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return _array;
    }

    private List<String> SaveDatatOverStockData(JSONArray jsonArray) {
        List<String> _array;
        _array = new ArrayList<>();
        for (Object aJsonArray : jsonArray) {
            JSONObject innerObj = (JSONObject) aJsonArray;
            try {
                String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
                tLogDownloadData _tLogDownloadData = new tLogDownloadData();
                List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

                _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_data_overStock.getId()).toString());
                _tLogDownloadData.set_dtLastDownload(pstrArgumet);

                tLogDownloadDataList.add(_tLogDownloadData);
                new tLogDownloadBL().SaveData(tLogDownloadDataList);

                JSONArray jsonArray_header = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtOverStockHeader_mobile")));
                if (jsonArray_header != null) {
                    for (Object aJsonArray_header : jsonArray_header) {
                        tOverStockHeaderData _data = new tOverStockHeaderData();
                        JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                        _data.set_intId(String.valueOf(innerObj_header.get("txtDataId")));
                        _data.set_txtOverStock(String.valueOf(innerObj_header.get("TxtNoOverStock")));
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
                        new tOverStockHeaderBL().SaveData(_data);
                    }

                    JSONArray jsonArray_Detail = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtOverStockDetail_mobile")));
                    Iterator k = jsonArray_Detail.iterator();
                    clsMainBL _clsMainBL = new clsMainBL();
                    SQLiteDatabase _db = _clsMainBL.getDb();
                    while (k.hasNext()) {
                        tOverStockDetailData _data = new tOverStockDetailData();
                        JSONObject innerObj_detail = (JSONObject) k.next();
                        _data.setIntId(String.valueOf(innerObj_detail.get("TxtTrOverStockDetail")));
                        _data.set_txtOverStock(String.valueOf(innerObj_detail.get("TxtNoOverStock")));
                        _data.set_dtDate(String.valueOf(innerObj_detail.get("DtDate")));
                        _data.set_intPrice(String.valueOf(innerObj_detail.get("IntPrice")));
                        _data.set_txtCodeProduct(String.valueOf(innerObj_detail.get("TxtCodeProduct")));
                        _data.set_txtKeterangan(String.valueOf(innerObj_detail.get("TxtKeterangan")));
                        _data.setTxtProduct(String.valueOf(innerObj_detail.get("TxtProduct")));
                        _data.setTxtExpireDate(String.valueOf(innerObj_detail.get("TxtExpireDate")));
                        _data.setTxtQuantity(String.valueOf(innerObj_detail.get("TxtQuantity")));
                        _data.set_intTotal(String.valueOf(innerObj_detail.get("IntTotal")));
                        _data.set_txtNIK(String.valueOf(innerObj_detail.get("TxtUserId")));
                        new tOverStockDetailDA(_db).SaveDatatOverStockDetailData(_db, _data);
                    }
                    _db.close();
                } else {
//                    new clsMainActivity().showCustomToast(getContext(), "Data Not Found", false);
                    _array.add("Data Near ED Not Found");
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
                String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
                tLogDownloadData _tLogDownloadData = new tLogDownloadData();
                List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

                _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_dataQuantityStock.getId()).toString());
                _tLogDownloadData.set_dtLastDownload(pstrArgumet);

                tLogDownloadDataList.add(_tLogDownloadData);
                new tLogDownloadBL().SaveData(tLogDownloadDataList);

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
                    if(jsonArray_Detail!=null){
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
                        _db.close();
                    }

                    JSONArray jsonArray_Image = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtSalesProductQuantityImage_mobile")));
                    if (jsonArray_Image != null) {
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
                        _db_image.close();
                    }
                } else {
//                    new clsMainActivity().showCustomToast(getContext(), "Data Not Found", false);
                    _array.add("Data Near ED Not Found");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return _array;
    }

    private List<String> SaveDatatKemasanRusakData(JSONArray jsonArray) {
        List<String> _array;
        _array = new ArrayList<>();
        for (Object aJsonArray : jsonArray) {
            JSONObject innerObj = (JSONObject) aJsonArray;
            try {
                String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
                tLogDownloadData _tLogDownloadData = new tLogDownloadData();
                List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

                _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_data_kemasanrusak.getId()).toString());
                _tLogDownloadData.set_dtLastDownload(pstrArgumet);

                tLogDownloadDataList.add(_tLogDownloadData);
                new tLogDownloadBL().SaveData(tLogDownloadDataList);

                JSONArray jsonArray_header = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtKemasanRusakHeader_mobile")));
                if (jsonArray_header != null) {
                    for (Object aJsonArray_header : jsonArray_header) {
                        tKemasanRusakHeaderData _data = new tKemasanRusakHeaderData();
                        JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                        _data.set_intId(String.valueOf(innerObj_header.get("txtDataId")));
                        _data.set_txtKemasanRusak(String.valueOf(innerObj_header.get("TxtNoKemasanRusak")));
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
                        new tKemasanRusakHeaderBL().SaveData(_data);
                    }

                    JSONArray jsonArray_Detail = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtKemasanRusakDetail_mobile")));
                    Iterator k = jsonArray_Detail.iterator();
                    clsMainBL _clsMainBL = new clsMainBL();
                    SQLiteDatabase _db = _clsMainBL.getDb();
                    while (k.hasNext()) {
                        tKemasanRusakDetailData _data = new tKemasanRusakDetailData();
                        JSONObject innerObj_detail = (JSONObject) k.next();
                        _data.setIntId(String.valueOf(innerObj_detail.get("TxtTrKemasanRusakDetail")));
                        _data.set_txtKemasanRusak(String.valueOf(innerObj_detail.get("TxtNoKemasanRusak")));
                        _data.set_dtDate(String.valueOf(innerObj_detail.get("DtDate")));
                        _data.set_intPrice(String.valueOf(innerObj_detail.get("IntPrice")));
                        _data.set_txtCodeProduct(String.valueOf(innerObj_detail.get("TxtCodeProduct")));
                        _data.set_txtKeterangan(String.valueOf(innerObj_detail.get("TxtKeterangan")));
                        _data.setTxtProduct(String.valueOf(innerObj_detail.get("TxtProduct")));
                        _data.setTxtExpireDate(String.valueOf(innerObj_detail.get("TxtExpireDate")));
                        _data.setTxtQuantity(String.valueOf(innerObj_detail.get("TxtQuantity")));
                        _data.set_intTotal(String.valueOf(innerObj_detail.get("IntTotal")));
                        _data.set_txtNIK(String.valueOf(innerObj_detail.get("TxtUserId")));
                        new tKemasanRusakDetailDA(_db).SaveData(_db, _data);
                    }
                    _db.close();

                    JSONArray jsonArray_Image = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtKemasanRusakImage_mobile")));
                    if (jsonArray_Image != null) {
                        Iterator l = jsonArray_Image.iterator();
                        clsMainBL _clsMainBL_image = new clsMainBL();
                        SQLiteDatabase _db_image = _clsMainBL_image.getDb();
                        while (l.hasNext()) {
                            tKemasanRusakImageData _data = new tKemasanRusakImageData();
                            JSONObject innerObj_image = (JSONObject) l.next();
                            _data.set_txtId(String.valueOf(innerObj_image.get("TxtTrKemasanRusakImage")));
                            _data.set_txtHeaderId(String.valueOf(innerObj_image.get("TxtKemasanRusak")));
                            _data.set_intPosition(String.valueOf(innerObj_image.get("IntPosition")));
                            _data.set_txtType(String.valueOf(innerObj_image.get("TxtType")));

                            String url = String.valueOf(innerObj_image.get("TxtImage"));

                            byte[] logoImage = getLogoImage(url);

                            if (logoImage != null) {
                                _data.set_txtImage(logoImage);
                            }

                            new tKemasanRusakImageDA(_db_image).SaveDataImage(_db_image, _data);
                        }
                        _db_image.close();
                    }
                } else {
//                    new clsMainActivity().showCustomToast(getContext(), "Data Not Found", false);
                    _array.add("Data Kemasan Rusak Not Found");
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
                String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
                tLogDownloadData _tLogDownloadData = new tLogDownloadData();
                List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

                _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_data_planogram.getId()).toString());
                _tLogDownloadData.set_dtLastDownload(pstrArgumet);

                tLogDownloadDataList.add(_tLogDownloadData);
                new tLogDownloadBL().SaveData(tLogDownloadDataList);

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
                        _data.set_txtIdCategory(String.valueOf(innerObj_header.get("IntKategoryPlanogram")));
                        _data.set_txtCategoryName(String.valueOf(innerObj_header.get("TxtCategoryName")));
                        _data.set_intIsValid(String.valueOf(innerObj_header.get("IntIsValid")));
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
                    _db_image.close();
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

                String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
                tLogDownloadData _tLogDownloadData = new tLogDownloadData();
                List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

                _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_dataKordinasiOutlet.getId()).toString());
                _tLogDownloadData.set_dtLastDownload(pstrArgumet);

                tLogDownloadDataList.add(_tLogDownloadData);
                new tLogDownloadBL().SaveData(tLogDownloadDataList);

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
                        _data.set_intCategoriId(String.valueOf(innerObj_header.get("IntCategoryId")));
                        _data.set_txtCategory(String.valueOf(innerObj_header.get("TxtCategory")));
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
                    _db_image.close();
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

    private List<String> SaveDataTidakSesuaiPesanan(JSONArray jsonArray) {
        List<String> _array;
        _array = new ArrayList<>();
        for (Object aJsonArray : jsonArray) {
            JSONObject innerObj = (JSONObject) aJsonArray;
            try {

                String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
                tLogDownloadData _tLogDownloadData = new tLogDownloadData();
                List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

                _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_data_tidaksesuaipesanan.getId()).toString());
                _tLogDownloadData.set_dtLastDownload(pstrArgumet);

                tLogDownloadDataList.add(_tLogDownloadData);
                new tLogDownloadBL().SaveData(tLogDownloadDataList);

                JSONArray jsonArray_header = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtTidakSesuaiPesananOutlet_mobile")));
                if (jsonArray_header != null) {
                    for (Object aJsonArray_header : jsonArray_header) {
                        tTidakSesuaiPesananHeaderData _data = new tTidakSesuaiPesananHeaderData();
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
                        _data.set_intCategoriId(String.valueOf(innerObj_header.get("IntCategoryId")));
                        _data.set_txtCategory(String.valueOf(innerObj_header.get("TxtCategory")));
                        _data.set_intSubmit("1");
                        _data.set_intSync("1");
                        new tTidakSesuaiPesananHeaderBL().SaveData(_data);
                    }

                    JSONArray jsonArray_Image = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListtTidakSesuaiPesananOutletImage_mobile")));
                    Iterator l = jsonArray_Image.iterator();
                    clsMainBL _clsMainBL_image = new clsMainBL();
                    SQLiteDatabase _db_image = _clsMainBL_image.getDb();
                    while (l.hasNext()) {
                        tTidakSesuaiPesananImageData _data = new tTidakSesuaiPesananImageData();
                        JSONObject innerObj_image = (JSONObject) l.next();
                        _data.set_txtId(String.valueOf(innerObj_image.get("TxtId")));
                        _data.set_txtHeaderId(String.valueOf(innerObj_image.get("TxtHeaderId")));
                        _data.set_intPosition(String.valueOf(innerObj_image.get("IntPosition")));

                        String url = String.valueOf(innerObj_image.get("TxtImage"));

                        byte[] logoImage = getLogoImage(url);

                        if (logoImage != null) {
                            _data.set_txtImage(logoImage);
                        }

                        new tTidakSesuaiPesananImageDA(_db_image).SaveDataImage(_db_image, _data);
                    }
                    _db_image.close();
                } else {
//                    new clsMainActivity().showCustomToast(getContext(), "Data Not Found", false);
                    _array.add("Data not Found");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return _array;
    }

    private List<String> SaveDataCategoryKoordinasiOutletData(JSONArray jsonArray) {
        List<String> _array;
        _array = new ArrayList<>();
        for (Object aJsonArray : jsonArray) {
            JSONObject innerObj = (JSONObject) aJsonArray;
            try {

                String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
                tLogDownloadData _tLogDownloadData = new tLogDownloadData();
                List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

                _tLogDownloadData.set_txtModuleName(res.getResourceEntryName(ll_dataCategoryKordinasiOutlet.getId()).toString());
                _tLogDownloadData.set_dtLastDownload(pstrArgumet);

                tLogDownloadDataList.add(_tLogDownloadData);
                new tLogDownloadBL().SaveData(tLogDownloadDataList);

                JSONArray jsonArray_header = new clsHelper().ResultJsonArray(String.valueOf(innerObj.get("ListCategoryKoordinasiOutlet_mobile")));
                if (jsonArray_header != null) {
                    for (Object aJsonArray_header : jsonArray_header) {
                        mCategoryKoordinasiOutletData _data = new mCategoryKoordinasiOutletData();
                        JSONObject innerObj_header = (JSONObject) aJsonArray_header;
                        _data.set_intId(String.valueOf(innerObj_header.get("IntId")));
                        _data.set_txtCategory(String.valueOf(innerObj_header.get("TxtCategory")));
                        new KoordinasiOutletBL().SaveDataCategoryKoordinasiOutlet(_data);
                    }
                } else {
//                    new clsMainActivity().showCustomToast(getContext(), "Data Not Found", false);
                    _array.add("Data Category Koordinasi Outlet not Found");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return _array;
    }

    private boolean successDownload = false;
    private class AsyncCallGenerateSQLite extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new clsHelperBL().SQLiteGenerateRequest(pInfo.versionName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }



            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());
        CountDownTimer CDT;
        int i =1;

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null && roledata.size() > 0) {
                Iterator i = roledata.iterator();
                String txtLink = "";
                while (i.hasNext()) {
                    org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
                    txtLink = String.valueOf(innerObj.get("txtLink"));
                    pstrArgumet = String.valueOf(innerObj.get(new APIData().strArgument));
                }
                Dialog.dismiss();
                mProgressDialog = new ProgressDialog(getContext());
                mProgressDialog.setMessage("Progress Download");
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(true);

                Intent serviceIntentMyServiceNative = new Intent(getContext(), MyServiceNative.class);
                    getContext().stopService(serviceIntentMyServiceNative);
                getContext().stopService(new Intent(getActivity(), MyTrackingLocationService.class));
//                MyTrackingLocationService service = new MyTrackingLocationService();
//                    service.shutdownService();
                NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancelAll();

                final DownloadTask downloadTask = new DownloadTask(getContext());
                downloadTask.execute(txtLink);

                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        downloadTask.cancel(true);
                    }
                });
            }
//            checkingDataTable("");
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Generating Data");
            Dialog.setCancelable(false);
            Dialog.setProgress(i);

//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel = 1;
//                }
//            });
            Dialog.show();

            CDT = new CountDownTimer(1000 * 60 * 30, 1000)
            {
                public void onTick(long millisUntilFinished)
                {
                    Dialog.setMessage("Please wait... " + getFormattedTime(i));
                    i++;
                }

                public void onFinish()
                {
                    Dialog.dismiss();
                    //Your Code ...
                }
            }.start();
        }


        @Override
        protected void onProgressUpdate(Void... progress) {
            Dialog.dismiss();
        }
    }

    private static String getFormattedTime(int secs) {
        // int secs = (int) Math.round((double) milliseconds / 1000); // for millisecs arg instead of secs
        if (secs < 60)
            return secs + "s";
        else {
            int mins = (int) secs / 60;
            int remainderSecs = secs - (mins * 60);
            if (mins < 60) {
                return (mins < 10 ? "0" : "") + mins + "m "
                        + (remainderSecs < 10 ? "0" : "") + remainderSecs + "s";
            }
            else {
                int hours = (int) mins / 60;
                int remainderMins = mins - (hours * 60);
                return (hours < 10 ? "0" : "") + hours + "h "
                        + (remainderMins < 10 ? "0" : "") + remainderMins + "m "
                        + (remainderSecs < 10 ? "0" : "") + remainderSecs + "s";
            }
        }
    }

    private class DownloadTask extends AsyncTask<String, Integer, String> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;

        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                SQLiteDatabase _db = new clsHelperBL().getDb();
                tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
                tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
                List<mCountConsumerMTDData> _dataCountConsumerMTDData = new mCountConsumerMTDBL().getAllmCountConsumerMTDDA("ALLOUTLET");
                List<mCounterNumberData> _dataCountNumberData = new mCounterNumberDA(_db).getAllData(_db);
                List<mDownloadMasterData_mobileData> _dataDownloadMasterData = new mDownloadMasterData_mobileBL().GetAllData();
                List<mMenuData> _dataMenuData = new mMenuBL().GetAllData();
                List<mUserLOBData> _dataUserLOBData = new mUserLOBBL().GetAllData();
                List<mUserRoleData> _dataUserRoleData = new mUserRoleBL().getAllData();
                List<tDeviceInfoUserData> _dataDeviceInfoUser = new tDeviceInfoUserBL().getData(0);
                tDisplayPictureData _pictureData = new tDisplayPictureBL().getData();
                List<mconfigData> _mConfigData = new mconfigDA(_db).getAllData(_db);

                List<tSalesProductHeaderData> _tSalesProductHeaderData = new tSalesProductHeaderDA(_db).getAllDataToPushData(_db);
                List<tSalesProductDetailData> _tSalesProductDetailData = new tSalesProductDetailDA(_db).getAllDataToPushData(_db, _tSalesProductHeaderData);
                List<tActivityData> _tActivityData = new tActivityDA(_db).getAllDataToPushData(_db);

                // customer base submit
                List<tCustomerBasedMobileHeaderData> _tCustomerBasedMobileHeaderData = new tCustomerBasedMobileHeaderDA(_db).getPushData(_db);
                List<tCustomerBasedMobileDetailData> _tCustomerBasedMobileDetailData = new tCustomerBasedMobileDetailDA(_db).getPushData(_db, _tCustomerBasedMobileHeaderData);
                List<tCustomerBasedMobileDetailProductData> _tCustomerBasedMobileDetailProductData = new tCustomerBasedMobileDetailProductDA(_db).getPushData(_db, _tCustomerBasedMobileDetailData);

                // cus base save
                List<tCustomerBasedMobileHeaderData> _tCustomerBasedMobileHeaderDataSave = new tCustomerBasedMobileHeaderBL().getAllDataToSubmit();
                List<tCustomerBasedMobileDetailData> _tCustomerBasedMobileDetailDataSave = new tCustomerBasedMobileDetailDA(_db).getPushData(_db, _tCustomerBasedMobileHeaderDataSave);
                List<tCustomerBasedMobileDetailProductData> _tCustomerBasedMobileDetailProductDataSave = new tCustomerBasedMobileDetailProductDA(_db).getPushData(_db, _tCustomerBasedMobileDetailDataSave);

                List<tAbsenUserData> _tAbsenUserData = new tAbsenUserDA(_db).getAllDataToPushData(_db);

                List<tVisitPlanHeader_MobileData> _tVisitPlanHeader_MobileData = new tVisitPlanHeader_MobileDA(_db).getAllData(_db);
                List<tVisitPlanRealisasiData> _tVisitPlanRealisasiData = new tVisitPlanRealisasiDA(_db).getAllData(_db);

                List<tSalesProductQuantityHeaderData> _tSalesProductQuantityHeaderData = new tSalesProductQuantityHeaderDA(_db).getAllDataToPushData(_db);
                List<tSalesProductQuantityDetailData> _tSalesProductQuantityDetailData = new tSalesProductQuantityDetailDA(_db).getAllDataKeep(_db, _tSalesProductQuantityHeaderData);

                List<tKemasanRusakHeaderData> _tKemasanRusakHeaderData = new tKemasanRusakHeaderDA(_db).getAllDataToPushData(_db);
                List<tKemasanRusakDetailData> _tKemasanRusakDetailData = new tKemasanRusakDetailDA(_db).getAllDataKeep(_db, _tKemasanRusakHeaderData);
                List<tKemasanRusakImageData> _tKemasanRusakImageData = new tKemasanRusakImageDA(_db).getAllDataToPushData(_db, _tKemasanRusakHeaderData);

                List<tOverStockHeaderData> _tOverStockHeaderData = new tOverStockHeaderDA(_db).getAllDataToPushData(_db);
                List<tOverStockDetailData> _tOverStockDetailData = new tOverStockDetailDA(_db).getAllDataKeep(_db, _tOverStockHeaderData);

                List<tStockOutHeaderData> _tStockOutHeaderData = new tStockOutHeaderDA(_db).getAllDataToPushData(_db);
                List<tStockOutDetailData> _tStockOutDetailData = new tStockOutDetailDA(_db).getAllDataKeep(_db, _tStockOutHeaderData);

                List<tPlanogramMobileData> _tPlanogramMobileData = new tPlanogramMobileDA(_db).getAllDataToPushData(_db);
                List<tPlanogramImageData> _tPlanogramImageData = new tPlanogramImageDA(_db).getAllDataToPushData(_db, _tPlanogramMobileData);

                List<tPlanogramMobileData> _tPlanogramMobileDataSave = new tPlanogramMobileDA(_db).getAllDataSelectImageNotNullUnsubmit(_db);
                List<tPlanogramImageData> _tPlanogramImageDataSave = new tPlanogramImageDA(_db).getAllDataToPushData(_db, _tPlanogramMobileDataSave);

                List<tStockInHandHeaderData> _tStockInHandHeaderData = new tStockInHandHeaderDA(_db).getAllDataToPushData(_db);
                List<tStockInHandDetailData> _tStockInHandDetailData = new tStockInHandDetailDA(_db).getAllDataToPushData(_db, _tStockInHandHeaderData);

                List<tStockInHandHeaderData> _tStockInHandHeaderDataSave = new tStockInHandHeaderDA(_db).getAllDataSave(_db);
                List<tStockInHandDetailData> _tStockInHandDetailDataSave = new tStockInHandDetailDA(_db).getAllDataToPushData(_db, _tStockInHandHeaderDataSave);

                List<tActivityMobileData> _tActivityMobileData = new tActivityMobileDA(_db).getAllDataToPushData(_db);

                List<tTidakSesuaiPesananHeaderData> _tTidakSesuaiPesananHeaderData = new tTidakSesuaiPesananHeaderDA(_db).getAllDataToPushData(_db);
                List<tTidakSesuaiPesananImageData> _tTidakSesuaiPesananImageData = new tTidakSesuaiPesananImageDA(_db).getAllDataToPushData(_db, _tTidakSesuaiPesananHeaderData);

                List<trackingLocationData> _trackingLocationData = new trackingLocationDA(_db).getAllData(_db);

                List<tPurchaseOrderHeaderData> _tPurchaseOrderHeaderData = new tPurchaseOrderHeaderDA(_db).getAllDataToPushData(_db);
                List<tPurchaseOrderDetailData> _tPurchaseOrderDetailData = new tPurchaseOrderDetailDA(_db).getAllDataToPushDataPO(_db, _tPurchaseOrderHeaderData);

                List<tLeaveMobileData> _tLeaveMobileData=new tLeaveMobileDA(_db).getAllDataPushData(_db);

                List<clsLogReceiverHeader_mobile> _clsLogReceiverHeader_mobile = new clsLogReceiverHeader_mobileDA(_db).getAllDataToPushData(_db);
                List<clsLogReceiverDetail_mobile> _clsLogReceiverDetail_mobile = new clsLogReceiverDetail_mobileDA(_db).getAllDataToPushData(_db);

                //tambahan buat tl
                List<tJawabanUserData> _tJawabanUserData = new tJawabanUserDA(_db).GetDataToPushAnswer(_db);
                List<tJawabanUserHeaderData> _tJawabanUserHeaderData = new tJawabanUserHeaderDA(_db).GetDataToPushAnswer(_db);
                List<tPOPStandardHeaderData> _tPOPStandardHeaderData = new tPOPStandardHeaderDA(_db).GetDataToPush(_db);
                List<tPOPStandardDetailData> _tPOPStandardDetailData = new tPOPStandardDetailDA(_db).GetDataToPush(_db);
                List<KoordinasiOutletData> _KoordinasiOutletData = new KoordinasiOutletDA(_db).getAllDataToPushData(_db);
                List<KoordinasiOutletImageData> _KoordinasiOutletImageData = new KoordinasiOutletImageDA(_db).getAllDataToPushData(_db, _KoordinasiOutletData);


                _db.close();

                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream("/sdcard/db_new_mobile.db3");

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }

                input = new FileInputStream("/sdcard/db_new_mobile.db3");
                output = new FileOutputStream(new clsHardCode().txtDatabaseName);

                byte data2[] = new byte[4096];
                total = 0;
                count = 0;

                while ((count = input.read(data2)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data2, 0, count);
                }

                _db = new clsHelperBL().getDb();
                new tUserLoginBL().saveData(_dataUserLogin);
                new mCountConsumerMTDBL().SaveData(_dataCountConsumerMTDData);
                for (mCounterNumberData dt : _dataCountNumberData) {
                    new mCounterNumberDA(_db).SaveDataMConfig(_db, dt);
                }

                new mDownloadMasterData_mobileBL().SaveData(_dataDownloadMasterData);
                new mMenuBL().SaveData(_dataMenuData);
                new mUserLOBBL().saveData(_dataUserLOBData);

                for (mUserRoleData dt : _dataUserRoleData) {
                    new mUserRoleDA(_db).SaveDataMConfig(_db, dt);
                }

                for (tDeviceInfoUserData dt : _dataDeviceInfoUser) {
                    new tDeviceInfoUserBL().SaveInfoDevice(dt.get_txtUserId(), dt.get_txtDeviceId(), dt.get_txtImei());
                }

                List<tDisplayPictureData> dataProfile = new ArrayList<>();
                dataProfile.add((_pictureData));

                new tDisplayPictureBL().saveData(dataProfile);

                for (mconfigData dt : _mConfigData) {
                    new mconfigDA(_db).SaveDataMConfig(_db, dt);
                }

                if(_tAbsenUserData!=null){
                    for(tAbsenUserData dt : _tAbsenUserData){
                        new tAbsenUserDA(_db).SaveDatatAbsenUserData(_db, dt);
                    }
                }

                if(_tSalesProductHeaderData!=null){
                    for(tSalesProductHeaderData dt : _tSalesProductHeaderData){
                        new tSalesProductHeaderDA(_db).SaveDatatSalesProductHeaderData(_db, dt);
                    }
                }

                if(_tSalesProductDetailData!=null){
                    for(tSalesProductDetailData dt : _tSalesProductDetailData){
                        new tSalesProductDetailDA(_db).SaveDatatSalesProductDetailData(_db, dt);
                    }
                }

                if(_tActivityData!=null){
                    for(tActivityData dt : _tActivityData){
                        new tActivityDA(_db).SaveDatatActivityData(_db, dt);
                    }
                }

                if(_tCustomerBasedMobileHeaderData!=null){
                    for(tCustomerBasedMobileHeaderData dt : _tCustomerBasedMobileHeaderData){
                        new tCustomerBasedMobileHeaderDA(_db).SaveDatatCustomerBasedMobileHeaderData(_db, dt);
                    }
                }

                if(_tCustomerBasedMobileDetailData!=null){
                    for(tCustomerBasedMobileDetailData dt : _tCustomerBasedMobileDetailData){
                        new tCustomerBasedMobileDetailDA(_db).SaveDatatCustomerBasedMobileDetailData(_db, dt);
                    }
                }

                if(_tCustomerBasedMobileDetailProductData!=null){
                    for(tCustomerBasedMobileDetailProductData dt : _tCustomerBasedMobileDetailProductData){
                        new tCustomerBasedMobileDetailProductDA(_db).SaveDatatCustomerBasedMobileDetailProductData(_db, dt);
                    }
                }

                if(_tCustomerBasedMobileHeaderDataSave!=null){
                    for(tCustomerBasedMobileHeaderData dt : _tCustomerBasedMobileHeaderDataSave){
                        new tCustomerBasedMobileHeaderDA(_db).SaveDatatCustomerBasedMobileHeaderData(_db, dt);
                    }
                }

                if(_tCustomerBasedMobileDetailDataSave!=null){
                    for(tCustomerBasedMobileDetailData dt : _tCustomerBasedMobileDetailDataSave){
                        new tCustomerBasedMobileDetailDA(_db).SaveDatatCustomerBasedMobileDetailData(_db, dt);
                    }
                }

                if(_tCustomerBasedMobileDetailProductDataSave!=null){
                    for(tCustomerBasedMobileDetailProductData dt : _tCustomerBasedMobileDetailProductDataSave){
                        new tCustomerBasedMobileDetailProductDA(_db).SaveDatatCustomerBasedMobileDetailProductData(_db, dt);
                    }
                }

                if(_tVisitPlanHeader_MobileData!=null){
                    for(tVisitPlanHeader_MobileData dt : _tVisitPlanHeader_MobileData){
                        new tVisitPlanHeader_MobileDA(_db).SaveDatatVisitPlanHeader_MobileData(_db, dt);
                    }
                }

                if(_tVisitPlanRealisasiData!=null){
                    for(tVisitPlanRealisasiData dt : _tVisitPlanRealisasiData){
                        new tVisitPlanRealisasiDA(_db).SaveDatatVisitPlan(_db, dt);
                    }
                }

                if(_tSalesProductQuantityHeaderData!=null){
                    for(tSalesProductQuantityHeaderData dt : _tSalesProductQuantityHeaderData){
                        new tSalesProductQuantityHeaderDA(_db).SaveDataSalesProductQuantityData(_db, dt);
                    }
                }

                if(_tSalesProductQuantityDetailData!=null){
                    for(tSalesProductQuantityDetailData dt : _tSalesProductQuantityDetailData){
                        new tSalesProductQuantityDetailDA(_db).SaveDatatSalesProductQuantityDetailData(_db, dt);
                    }
                }

                if(_tKemasanRusakHeaderData!=null){
                    for(tKemasanRusakHeaderData dt : _tKemasanRusakHeaderData){
                        new tKemasanRusakHeaderDA(_db).SaveData(_db, dt);
                    }
                }

                if(_tKemasanRusakDetailData!=null){
                    for(tKemasanRusakDetailData dt : _tKemasanRusakDetailData){
                        new tKemasanRusakDetailDA(_db).SaveData(_db, dt);
                    }
                }

                if(_tKemasanRusakImageData!=null){
                    for(tKemasanRusakImageData dt : _tKemasanRusakImageData){
                        new tKemasanRusakImageDA(_db).SaveDataImage(_db,dt);
                    }
                }

                if(_tOverStockHeaderData!=null){
                    for(tOverStockHeaderData dt : _tOverStockHeaderData){
                        new tOverStockHeaderDA(_db).SaveDataOverStockData(_db, dt);
                    }
                }

                if(_tOverStockDetailData!=null){
                    for(tOverStockDetailData dt : _tOverStockDetailData){
                        new tOverStockDetailDA(_db).SaveDatatOverStockDetailData(_db,dt);
                    }
                }

                if(_tStockOutHeaderData!=null){
                    for(tStockOutHeaderData dt : _tStockOutHeaderData){
                        new tStockOutHeaderDA(_db).SaveDataOverStockData(_db, dt);
                    }
                }

                if(_tStockOutDetailData!=null){
                    for(tStockOutDetailData dt : _tStockOutDetailData){
                        new tStockOutDetailDA(_db).SaveDatatOverStockDetailData(_db,dt);
                    }
                }

                if(_tPlanogramMobileData!=null){
                    for(tPlanogramMobileData dt : _tPlanogramMobileData){
                        new tPlanogramMobileDA(_db).SaveDataPlanogram(_db,dt);
                    }
                }

                if(_tPlanogramImageData!=null){
                    for(tPlanogramImageData dt : _tPlanogramImageData){
                        new tPlanogramImageDA(_db).SaveDataImage(_db,dt);
                    }
                }

                if(_tPlanogramMobileDataSave!=null){
                    for(tPlanogramMobileData dt : _tPlanogramMobileDataSave){
                        new tPlanogramMobileDA(_db).SaveDataPlanogram(_db,dt);
                    }
                }

                if(_tPlanogramImageDataSave!=null){
                    for(tPlanogramImageData dt : _tPlanogramImageDataSave){
                        new tPlanogramImageDA(_db).SaveDataImage(_db,dt);
                    }
                }

                if(_tStockInHandHeaderData!=null){
                    for(tStockInHandHeaderData dt : _tStockInHandHeaderData){
                        new tStockInHandHeaderDA(_db).SaveDatatStockInHandHeaderData(_db,dt);
                    }
                }

                if(_tStockInHandDetailData!=null){
                    for(tStockInHandDetailData dt : _tStockInHandDetailData){
                        new tStockInHandDetailDA(_db).SaveDatatStockInHandDetailData(_db, dt);
                    }
                }

                if(_tStockInHandHeaderDataSave!=null){
                    for(tStockInHandHeaderData dt : _tStockInHandHeaderDataSave){
                        new tStockInHandHeaderDA(_db).SaveDatatStockInHandHeaderData(_db,dt);
                    }
                }

                if(_tStockInHandDetailDataSave!=null){
                    for(tStockInHandDetailData dt : _tStockInHandDetailDataSave){
                        new tStockInHandDetailDA(_db).SaveDatatStockInHandDetailData(_db, dt);
                    }
                }

                if(_tPurchaseOrderHeaderData!=null){
                    for(tPurchaseOrderHeaderData dt : _tPurchaseOrderHeaderData){
                        new tPurchaseOrderHeaderDA(_db).SaveDatatPurchaseOrderHeaderData(_db,dt);
                    }
                }

                if(_tPurchaseOrderDetailData!=null){
                    for(tPurchaseOrderDetailData dt : _tPurchaseOrderDetailData){
                        new tPurchaseOrderDetailDA(_db).SaveDatatPurchaseOrderDetailData(_db, dt);
                    }
                }

                if(_tActivityMobileData!=null){
                    for(tActivityMobileData dt : _tActivityMobileData){
                        new tActivityMobileDA(_db).SaveDatatActivityData(_db,dt);
                    }
                }

                if(_tTidakSesuaiPesananHeaderData!=null){
                    for(tTidakSesuaiPesananHeaderData dt : _tTidakSesuaiPesananHeaderData){
                        new tTidakSesuaiPesananHeaderDA(_db).SaveData(_db,dt);
                    }
                }

                if(_tTidakSesuaiPesananImageData!=null){
                    for(tTidakSesuaiPesananImageData dt : _tTidakSesuaiPesananImageData){
                        new tTidakSesuaiPesananImageDA(_db).SaveDataImage(_db,dt);
                    }
                }

                if(_trackingLocationData!=null){
                    for(trackingLocationData dt : _trackingLocationData){
                        new trackingLocationDA(_db).SaveDataDownloadTrackingLocation(_db, dt);
                    }
                }

                if(_tLeaveMobileData!=null){
                    for(tLeaveMobileData dt : _tLeaveMobileData){
                        new tLeaveMobileDA(_db).SaveDataMConfig(_db, dt);
                    }
                }

                if(_clsLogReceiverHeader_mobile!=null){
                    for(clsLogReceiverHeader_mobile dt : _clsLogReceiverHeader_mobile){
                        new clsLogReceiverHeader_mobileDA(_db).SaveDataMConfig(_db, dt);
                    }
                }

                if(_clsLogReceiverDetail_mobile!=null){
                    for(clsLogReceiverDetail_mobile dt : _clsLogReceiverDetail_mobile){
                        new clsLogReceiverDetail_mobileDA(_db).SaveDataMConfig(_db, dt);
                    }
                }

                if (_tJawabanUserHeaderData!=null){
                    for (tJawabanUserHeaderData dt : _tJawabanUserHeaderData){
                        new tJawabanUserHeaderDA(_db).SaveDatatJawabanUserHeader(_db, dt);
                    }
                }

                if (_tJawabanUserData!=null) {
                    for (tJawabanUserData dt : _tJawabanUserData){
                        new tJawabanUserDA(_db).SaveDatatJawabanUser(_db, dt);
                    }
                }

                if (_KoordinasiOutletData!=null) {
                    for (KoordinasiOutletData dt : _KoordinasiOutletData) {
                        new KoordinasiOutletDA(_db).SaveDataKoordinasiOutlet(_db, dt);
                    }
                }

                if (_KoordinasiOutletImageData!=null){
                    for (KoordinasiOutletImageData dt : _KoordinasiOutletImageData){
                        new KoordinasiOutletImageDA(_db).SaveDataImage(_db, dt);
                    }
                }

                if(_tPOPStandardHeaderData!=null){
                    for (tPOPStandardHeaderData dt : _tPOPStandardHeaderData){
                        new tPOPStandardHeaderDA(_db).SaveDatatPOPStandardHeader(_db,dt);
                    }
                }

                if(_tPOPStandardDetailData!=null){
                    for (tPOPStandardDetailData dt : _tPOPStandardDetailData){
                        new tPOPStandardDetailDA(_db).SaveDatatPOPStandardDetail(_db, dt);
                    }
                }

                _db.close();

            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();

                    Intent serviceIntentMyServiceNative = new Intent(getContext(), MyServiceNative.class);
                    if (!isMyServiceRunning(MyServiceNative.class)) {
                        getActivity().startService(serviceIntentMyServiceNative);
                    }

                    Intent serviceIntentMyTrackingLocationService = new Intent(getActivity(), MyTrackingLocationService.class);
                    if (!isMyServiceRunning(MyTrackingLocationService.class)) {
                        getActivity().startService(serviceIntentMyTrackingLocationService);
                    }

                File file = new File("/sdcard/db_new_mobile.db3");
                    if(file.exists()){
                        file.delete();
                    }

            }


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
            mProgressDialog.show();
            mProgressDialog.setCancelable(false);
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
            mProgressDialog.dismiss();
            if (result != null)
                Toast.makeText(context, "Download error: " + result, Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(context, "File downloaded", Toast.LENGTH_SHORT).show();
            }

            saveLastDownload(pstrArgumet);

            AsyncCallDownloadTransaction task = new AsyncCallDownloadTransaction();
            task.execute();
//            Intent intent = new Intent(getContext(), MainMenu.class);
//            startActivity(intent);
//            getActivity().finish();
        }
    }

    private class AsyncCallDownloadTransaction extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                if(loginData.get_txtRoleName().equals("SPG Mobile V2")){
                    Json = new tSalesProductHeaderBL().DownloadReso(pInfo.versionName);
                    arrData = SaveDatatSalesProductData(Json, 0);

                    Json = new tActivityBL().DownloadActivity(pInfo.versionName);
                    arrData = SaveDatatActivityData(Json);

                    Json = new tCustomerBasedMobileHeaderBL().DownloadCustomerBase(pInfo.versionName);
                    arrData = SaveDatatCustomerBasedData(Json);

                    Json = new tAbsenUserBL().DownloadAbsen(pInfo.versionName);
                    arrData = SaveDatatAbsenUserData(Json);

                    Json = new tLeaveMobileBL().DownloadDataLeave(pInfo.versionName);
                    arrData = SaveDatatLeaveData(Json);
                }
                else if(loginData.get_txtRoleName().equals("MD Mobile")){
                    Json = new tVisitPlanRealisasiBL().DownloadRealisasiVisitPlan(pInfo.versionName);
                    SaveDatatTransaksiVisitPlanData(Json);
                    SaveDatatTransaksiVisitPlanHeaderData(Json);

                    Json = new tLeaveMobileBL().DownloadDataLeave(pInfo.versionName);
                    arrData = SaveDatatLeaveData(Json);

                    Json = new tStockInHandHeaderBL().DownloadSIH(pInfo.versionName);
                    new tStockInHandHeaderBL().DownloadNOSIH(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
                    arrData = SaveDatatStockInHandData(Json);

                    Json = new tActivityMobileBL().DownloadActivityV2(pInfo.versionName);
                    arrData = SaveDatatActivityDataV2(Json);

                    Json = new tSalesProductQuantityHeaderBL().DownloadTransactionQuantityStock(pInfo.versionName);
                    new tSalesProductQuantityHeaderBL().DownloadNOQuantityStock(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
                    arrData = SaveDatatSalesProductQuantityData(Json);

                    Json = new tPlanogramMobileBL().DownloadTransactionPlanogram(pInfo.versionName);
                    arrData = SaveDatatPlamogramData(Json);

                    Json = new tOverStockHeaderBL().DownloadTransactionOverStock(pInfo.versionName);
                    new tOverStockHeaderBL().DownloadNOOverStock(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
                    arrData = SaveDatatOverStockData(Json);

                    Json = new tStockOutHeaderBL().DownloadTransactionStockOut(pInfo.versionName);
//                    new tOverStockHeaderBL().DownloadNOOverStock(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
                    arrData = SaveDatatStockOutData(Json);

                    Json = new tKemasanRusakHeaderBL().DownloadTransactionKemasanRusak(pInfo.versionName);
                    new tKemasanRusakHeaderBL().DownloadNOKemasanRusak(pInfo.versionName, loginData.get_txtUserId(), loginData.get_TxtEmpId());
                    arrData = SaveDatatKemasanRusakData(Json);

                    Json = new tTidakSesuaiPesananHeaderBL().DownloadDataTidakSesuaiPesanan(pInfo.versionName);
                    arrData = SaveDataTidakSesuaiPesanan(Json);
                } else if(loginData.get_txtRoleName().equals("TL Mobile")){
                    Json = new tAbsenUserBL().DownloadAbsen(pInfo.versionName);
                    arrData = SaveDatatAbsenUserData(Json);

                    Json = new tLeaveMobileBL().DownloadDataLeave(pInfo.versionName);
                    arrData = SaveDatatLeaveData(Json);
                }
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
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessSuccessDownload, true);

            Intent intent = new Intent(getContext(), MainMenu.class);
            startActivity(intent);
            getActivity().finish();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Geting today transaction");
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
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
