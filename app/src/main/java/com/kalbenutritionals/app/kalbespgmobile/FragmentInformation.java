package com.kalbenutritionals.app.kalbespgmobile;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.owater.library.CircleTextView;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;
import java.util.List;

import bl.clsHelperBL;
import bl.mCountConsumerMTDBL;
import bl.mDownloadMasterData_mobileBL;
import bl.tAbsenUserBL;
import bl.tActivityBL;
import bl.tActivityMobileBL;
import bl.tCustomerBasedMobileHeaderBL;
import bl.tDisplayPictureBL;
import bl.tJawabanUserHeaderBL;
import bl.tKemasanRusakHeaderBL;
import bl.tLeaveMobileBL;
import bl.tOverStockHeaderBL;
import bl.tPOPStandardHeaderBL;
import bl.tPlanogramMobileBL;
import bl.tPurchaseOrderHeaderBL;
import bl.tSalesProductHeaderBL;
import bl.tSalesProductQuantityHeaderBL;
import bl.tStockInHandHeaderBL;
import bl.tTidakSesuaiPesananHeaderBL;
import bl.tUserLoginBL;
import bl.tVisitPlanRealisasiBL;
import de.hdodenhof.circleimageview.CircleImageView;
import library.spgmobile.common.mDownloadMasterData_mobileData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tActivityMobileData;
import library.spgmobile.common.tDisplayPictureData;
import library.spgmobile.common.tJawabanUserHeaderData;
import library.spgmobile.common.tKemasanRusakHeaderData;
import library.spgmobile.common.tLeaveMobileData;
import library.spgmobile.common.tOverStockHeaderData;
import library.spgmobile.common.tPOPStandardHeaderData;
import library.spgmobile.common.tPlanogramMobileData;
import library.spgmobile.common.tPurchaseOrderHeaderData;
import library.spgmobile.common.tSalesProductHeaderData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tStockInHandHeaderData;
import library.spgmobile.common.tTidakSesuaiPesananHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.tVisitPlanRealisasiData;
import library.spgmobile.common.visitplanAbsenData;

public class FragmentInformation extends Fragment implements View.OnClickListener {

    View v;
    TextView tvUsername, tvBranchOutlet, tvEmail, tv_reso0, tv_reso1, tv_reso2, tv_act0, tv_act1, tv_act2, tv_cb0, tv_cb1, tv_cb2,tv_actV20, tv_actV21, tv_actV22,tv_po0,tv_po1, tv_po2,tv_qs0,tv_qs1, tv_qs2,tv_StockINhand2,tv_StockINhand1,tv_StockINhand0,tv_planogram2, tv_planogram1,tv_planogram0, tv_koordinasi2,tv_koordinasi1,tv_koordinasi0,tv_quesioner2,tv_quesioner1,tv_quesioner0;
    TextView tv_pop_standard0, tv_pop_standard1, tv_pop_standard2;
    private LinearLayout ll_data_tidaksesuaipesanan, ll_data_kemasanrusak, ll_data_addDisplay, ll_data_overStock, ll_dataVisitPlan, ll_dataVisitPlanDone, ll_reso, ll_data_activity, ll_data_activityV2 , ll_data_customerbased, ll_data_customerbasedMTD, ll_purchase_order, ll_dataQuantityStock,ll_dataQuesioner, ll_dataKoordinasi, ll_data_planogram, ll_data_stockIH, ll_dataPOP_Standard;
    private TableRow tr_kemasanRusak,tr_tidaksesuai,tr_addDisplay, tr_overStock, tr_oustVisit, tr_doneVisit, tr_reso, tr_activity, tr_activityV2, tr_cunsomer, tr_po, tr_qStock,tr_StockINhand,tr_koordinasi,tr_planogram,tr_quesioner, tr_POPStandard;
    LocationManager locationManager;
    TextView tv_kemasanRusak0,tv_kemasanRusak1,tv_kemasanRusak2,tv_tidaksesuai0,tv_tidaksesuai1,tv_tidaksesuai2,tv_oustVisit0, tv_oustVisit1, tv_oustVisit2, tv_doneVisit0, tv_doneVisit1, tv_doneVisit2, tv_os0, tv_os1, tv_os2, tv_addDisplay0, tv_addDisplay1, tv_addDisplay2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home_new, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tDisplayPictureData tDisplayPictureData = new tDisplayPictureBL().getData();

        CircleImageView ivProfile = (CircleImageView) v.findViewById(R.id.profile_image);
        CircleTextView tvTotalReso = (CircleTextView) v.findViewById(R.id.tvTotalReso);
        CircleTextView tvTotalActivity = (CircleTextView) v.findViewById(R.id.tvTotalActivity);
        CircleTextView tvTotalActivityV2 = (CircleTextView) v.findViewById(R.id.tvTotalActivityV2);
        CircleTextView tvTotaladdDisplay = (CircleTextView) v.findViewById(R.id.tvTotaladdDisplay);
        CircleTextView tvTotalCustomerBase = (CircleTextView) v.findViewById(R.id.tvTotalCustomerBase);
        CircleTextView tvTotalCustomerBaseMTD = (CircleTextView) v.findViewById(R.id.tvTotalConsumerMTD);
        CircleTextView tvTotalPO = (CircleTextView) v.findViewById(R.id.tvTotalPO);
        CircleTextView tvTotalQStock = (CircleTextView) v.findViewById(R.id.tvTotalQStock);
        CircleTextView tvTotalStockIH = (CircleTextView) v.findViewById(R.id.tvTotalStockIH);
        CircleTextView tvTotalPlanogram = (CircleTextView) v.findViewById(R.id.tvTotalPlanogram);
        CircleTextView tvTotalKoordinasi = (CircleTextView) v.findViewById(R.id.tvTotalKoordinasi);
        CircleTextView tvTotalKuesioner = (CircleTextView) v.findViewById(R.id.tvTotalKuesioner);
        CircleTextView tvTotalPOPStandard = (CircleTextView) v.findViewById(R.id.tvTotalPOPStandard);
        CircleTextView tvTotalVisitPlan = (CircleTextView) v.findViewById(R.id.tvTotalVisitPlan);
        CircleTextView tvTotalVisitPlanDone = (CircleTextView) v.findViewById(R.id.tvTotalVisitPlanDone);
        CircleTextView tvTotaloverStock = (CircleTextView) v.findViewById(R.id.tvTotaloverStock);
        CircleTextView tvTotaltidaksesuaipesanan = (CircleTextView) v.findViewById(R.id.tvTotaltidaksesuaipesanan);
        CircleTextView tvTotalkemasanrusak = (CircleTextView) v.findViewById(R.id.tvTotalkemasanrusak);
        tvUsername = (TextView) v.findViewById(R.id.tvUsername);
        tvBranchOutlet = (TextView) v.findViewById(R.id.tvBranchOutlet);
        tvEmail = (TextView) v.findViewById(R.id.tvEmail);
        tv_reso0 = (TextView) v.findViewById(R.id.tv_reso0);
        tv_reso1 = (TextView) v.findViewById(R.id.tv_reso1);
        tv_reso2 = (TextView) v.findViewById(R.id.tv_reso2);
        tv_act0 = (TextView) v.findViewById(R.id.tv_act0);
        tv_act1 = (TextView) v.findViewById(R.id.tv_act1);
        tv_act2 = (TextView) v.findViewById(R.id.tv_act2);
        tv_actV20 = (TextView) v.findViewById(R.id.tv_actV20);
        tv_actV21 = (TextView) v.findViewById(R.id.tv_actV21);
        tv_actV22 = (TextView) v.findViewById(R.id.tv_actV22);
        tv_cb0 = (TextView) v.findViewById(R.id.tv_cb0);
        tv_cb1 = (TextView) v.findViewById(R.id.tv_cb1);
        tv_cb2 = (TextView) v.findViewById(R.id.tv_cb2);
        tv_po0 = (TextView) v.findViewById(R.id.tv_po0);
        tv_po1 = (TextView) v.findViewById(R.id.tv_po1);
        tv_po2 = (TextView) v.findViewById(R.id.tv_po2);
        tv_qs0 = (TextView) v.findViewById(R.id.tv_qs0);
        tv_qs1 = (TextView) v.findViewById(R.id.tv_qs1);
        tv_qs2 = (TextView) v.findViewById(R.id.tv_qs2);
        tv_oustVisit0 = (TextView) v.findViewById(R.id.tv_oustVisit0);
        tv_oustVisit1 = (TextView) v.findViewById(R.id.tv_oustVisit1);
        tv_oustVisit2 = (TextView) v.findViewById(R.id.tv_oustVisit2);
        tv_doneVisit0 = (TextView) v.findViewById(R.id.tv_doneVisit0);
        tv_doneVisit1 = (TextView) v.findViewById(R.id.tv_doneVisit1);
        tv_doneVisit2 = (TextView) v.findViewById(R.id.tv_doneVisit2);
        tv_os0 = (TextView) v.findViewById(R.id.tv_os0);
        tv_os1 = (TextView) v.findViewById(R.id.tv_os1);
        tv_os2 = (TextView) v.findViewById(R.id.tv_os2);

        tv_StockINhand0 = (TextView) v.findViewById(R.id.tv_StockINhand0);
        tv_StockINhand1 = (TextView) v.findViewById(R.id.tv_StockINhand1);
        tv_StockINhand2 = (TextView) v.findViewById(R.id.tv_StockINhand2);

        tv_planogram0 = (TextView) v.findViewById(R.id.tv_planogram0);
        tv_planogram1 = (TextView) v.findViewById(R.id.tv_planogram1);
        tv_planogram2 = (TextView) v.findViewById(R.id.tv_planogram2);

        tv_koordinasi0 = (TextView) v.findViewById(R.id.tv_koordinasi0);
        tv_koordinasi1 = (TextView) v.findViewById(R.id.tv_koordinasi1);
        tv_koordinasi2 = (TextView) v.findViewById(R.id.tv_koordinasi2);

        tv_quesioner0 = (TextView) v.findViewById(R.id.tv_quesioner0);
        tv_quesioner1 = (TextView) v.findViewById(R.id.tv_quesioner1);
        tv_quesioner2 = (TextView) v.findViewById(R.id.tv_quesioner2);

        tv_pop_standard0 = (TextView) v.findViewById(R.id.tv_pop_standard0);
        tv_pop_standard1 = (TextView) v.findViewById(R.id.tv_pop_standard1);
        tv_pop_standard2 = (TextView) v.findViewById(R.id.tv_pop_standard2);

        tv_addDisplay0 = (TextView) v.findViewById(R.id.tv_addDisplay0);
        tv_addDisplay1 = (TextView) v.findViewById(R.id.tv_addDisplay1);
        tv_addDisplay2 = (TextView) v.findViewById(R.id.tv_addDisplay2);

        tv_kemasanRusak0 = (TextView) v.findViewById(R.id.tv_kemasanRusak0);
        tv_kemasanRusak1 = (TextView) v.findViewById(R.id.tv_kemasanRusak1);
        tv_kemasanRusak2 = (TextView) v.findViewById(R.id.tv_kemasanRusak2);

        tv_tidaksesuai0 = (TextView) v.findViewById(R.id.tv_tidaksesuai0);
        tv_tidaksesuai1 = (TextView) v.findViewById(R.id.tv_tidaksesuai1);
        tv_tidaksesuai2 = (TextView) v.findViewById(R.id.tv_tidaksesuai2);

        ll_reso = (LinearLayout) v.findViewById(R.id.ll_reso);
        ll_data_activity = (LinearLayout) v.findViewById(R.id.ll_data_activity);
        ll_data_activityV2 = (LinearLayout) v.findViewById(R.id.ll_data_activityV2);
        ll_data_customerbased = (LinearLayout) v.findViewById(R.id.ll_data_customerbased);
        ll_data_customerbasedMTD = (LinearLayout) v.findViewById(R.id.ll_data_customerbasedMTD);
        ll_purchase_order = (LinearLayout) v.findViewById(R.id.ll_purchase_order);
        ll_dataQuantityStock = (LinearLayout) v.findViewById(R.id.ll_dataQuantityStock);
        ll_dataQuesioner = (LinearLayout) v.findViewById(R.id.ll_dataQuesioner);
        ll_dataPOP_Standard =(LinearLayout) v.findViewById(R.id.ll_dataPOP_Standard);
        ll_dataKoordinasi = (LinearLayout) v.findViewById(R.id.ll_dataKoordinasi);
        ll_data_planogram = (LinearLayout) v.findViewById(R.id.ll_data_planogram);
        ll_data_stockIH = (LinearLayout) v.findViewById(R.id.ll_data_stockIH);
        ll_dataVisitPlan = (LinearLayout) v.findViewById(R.id.ll_dataVisitPlan);
        ll_dataVisitPlanDone = (LinearLayout) v.findViewById(R.id.ll_dataVisitPlanDone);
        ll_data_overStock = (LinearLayout) v.findViewById(R.id.ll_data_overStock);
        ll_data_addDisplay = (LinearLayout) v.findViewById(R.id.ll_data_addDisplay);
        ll_data_kemasanrusak = (LinearLayout) v.findViewById(R.id.ll_data_kemasanrusak);
        ll_data_tidaksesuaipesanan = (LinearLayout) v.findViewById(R.id.ll_data_tidaksesuaipesanan);

        tr_reso = (TableRow) v.findViewById(R.id.tr_reso);
        tr_activity = (TableRow) v.findViewById(R.id.tr_activity);
        tr_activityV2 = (TableRow) v.findViewById(R.id.tr_activityV2);
        tr_addDisplay = (TableRow) v.findViewById(R.id.tr_addDisplay);
        tr_cunsomer = (TableRow) v.findViewById(R.id.tr_cunsomer);
        tr_po = (TableRow) v.findViewById(R.id.tr_po);
        tr_qStock = (TableRow) v.findViewById(R.id.tr_qStock);
        tr_StockINhand = (TableRow) v.findViewById(R.id.tr_StockINhand);
        tr_koordinasi = (TableRow) v.findViewById(R.id.tr_koordinasi);
        tr_planogram = (TableRow) v.findViewById(R.id.tr_planogram);
        tr_quesioner = (TableRow) v.findViewById(R.id.tr_quesioner);
        tr_POPStandard = (TableRow) v.findViewById(R.id.tr_pop_standard);
        tr_overStock = (TableRow) v.findViewById(R.id.tr_overStock);
        tr_oustVisit = (TableRow) v.findViewById(R.id.tr_oustVisit);
        tr_doneVisit = (TableRow) v.findViewById(R.id.tr_doneVisit);
        tr_kemasanRusak = (TableRow) v.findViewById(R.id.tr_kemasanRusak);
        tr_tidaksesuai = (TableRow) v.findViewById(R.id.tr_tidaksesuai);


        List<mDownloadMasterData_mobileData> mDownloadMasterData_mobileDataList = new ArrayList<>();

        mDownloadMasterData_mobileDataList = new mDownloadMasterData_mobileBL().GetAllData();

        Resources res = getResources();

        if(mDownloadMasterData_mobileDataList.size()!=0){
            for(mDownloadMasterData_mobileData data : mDownloadMasterData_mobileDataList){
                String txt_id = data.get_txtMasterData().replaceAll(" ","");

                // show data transaksi
                if (txt_id.equals(res.getResourceEntryName(ll_reso.getId()))){
                    ll_reso.setVisibility(View.VISIBLE);
                    tr_reso.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_data_activity.getId()))){
                    ll_data_activity.setVisibility(View.VISIBLE);
                    tr_activity.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_data_activityV2.getId()))){
                    ll_data_activityV2.setVisibility(View.VISIBLE);
                    tr_activityV2.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_data_addDisplay.getId()))){
                    ll_data_addDisplay.setVisibility(View.VISIBLE);
                    tr_addDisplay.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_data_customerbased.getId()))){
                    ll_data_customerbased.setVisibility(View.VISIBLE);
                    ll_data_customerbasedMTD.setVisibility(View.VISIBLE);
                    tr_cunsomer.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_purchase_order.getId()))){
                    ll_purchase_order.setVisibility(View.VISIBLE);
                    tr_po.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_dataQuantityStock.getId()))){
                    ll_dataQuantityStock.setVisibility(View.VISIBLE);
                    tr_qStock.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_dataQuesioner.getId()))){
                    ll_dataQuesioner.setVisibility(View.VISIBLE);
                    tr_quesioner.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_dataPOP_Standard.getId()))){
                  ll_dataPOP_Standard.setVisibility(View.VISIBLE);
                    tr_POPStandard.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_dataKoordinasi.getId()))){
                    ll_dataKoordinasi.setVisibility(View.VISIBLE);
                    tr_koordinasi.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_data_planogram.getId()))){
                    ll_data_planogram.setVisibility(View.VISIBLE);
                    tr_planogram.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_data_stockIH.getId()))){
                    ll_data_stockIH.setVisibility(View.VISIBLE);
                    tr_StockINhand.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_dataVisitPlan.getId()))){
                    ll_dataVisitPlan.setVisibility(View.VISIBLE);
                    ll_dataVisitPlanDone.setVisibility(View.VISIBLE);
//                    tr_doneVisit.setVisibility(View.VISIBLE);
                    tr_oustVisit.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_data_overStock.getId()))){
                    ll_data_overStock.setVisibility(View.VISIBLE);
                    tr_overStock.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_data_kemasanrusak.getId()))){
                    ll_data_kemasanrusak.setVisibility(View.VISIBLE);
                    tr_kemasanRusak.setVisibility(View.VISIBLE);
                } else if (txt_id.equals(res.getResourceEntryName(ll_data_tidaksesuaipesanan.getId()))){
                    ll_data_tidaksesuaipesanan.setVisibility(View.VISIBLE);
                    tr_tidaksesuai.setVisibility(View.VISIBLE);
                }
            }
        }

        tUserLoginData dt = new tUserLoginBL().getUserActive();
//        final tAbsenUserData dtAbsen = new tAbsenUserBL().getDataCheckInActive();
        final visitplanAbsenData dtAbsen = new clsHelperBL().getDataCheckInActive();
        List<tLeaveMobileData> dtLeave = new tLeaveMobileBL().getData("");

        List<tSalesProductHeaderData> dtReso;
        List<tStockInHandHeaderData> dtSIH;
        List<tPurchaseOrderHeaderData> dtPo;
        List<tSalesProductQuantityHeaderData> dtQStock;
        List<tKemasanRusakHeaderData> dtKRusak;
        List<tTidakSesuaiPesananHeaderData> dtTSesuaiPesanan;
        List<tOverStockHeaderData> dtOverStock;
        int dtCbase;
        int dtCbaseMTD;
        List<tActivityData> dtActivity;
        List<tActivityMobileData> dtActivityV2;
        List<tPlanogramMobileData> dtPlanogram;
        List<tJawabanUserHeaderData> dtQuis = null;
        List<tJawabanUserHeaderData> dtQuis_push = null;
        List<tJawabanUserHeaderData> dtQuis_unpush = null;
        List<tPOPStandardHeaderData> dtPOP_push = null;
        List<tPOPStandardHeaderData> dtPOP_unPush = null;
        List<tPOPStandardHeaderData> dtPOP = null;
        tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
        List<tSalesProductHeaderData> dt_reso_unpush;
        List<tSalesProductHeaderData> dt_reso_push;
        int dt_sih_unpush;
        int dt_sih_push;
        int dt_sih_save;
        List<tActivityData> dt_act_unpush;
        List<tActivityData> dt_act_push;
        int dt_cb_save;
        int dt_cb_unpush;
        int dt_cb_push;
        int dt_actV2_save;
        int dt_actV2_unpush;
        int dt_actV2_push;
        int dt_planogram_save;
        int dt_planogram_unpush;
        int dt_planogram_push;
        int dt_po_save;
        int dt_po_unpush;
        int dt_po_push;
        int dt_qStock_save;
        int dt_qStock_unpush;
        int dt_qStock_push;
        int dt_kRusak_save;
        int dt_kRusak_unpush;
        int dt_kRusak_push;
        int dt_tSesuai_save;
        int dt_tSesuai_unpush;
        int dt_tSesuai_push;
        int dt_quis_push = 0;
        int dt_quis_unpush = 0;
        int dt_quis_save;
        int dt_pop_push = 0;
        int dt_pop_unpush = 0;
        int dt_pop_save = 0;

        tvUsername.setText(dt.get_txtUserName().toUpperCase());

        if (dtLeave.size() > 0) {
            tvBranchOutlet.setText(dtLeave.get(0).get_txtTypeAlasanName() + " - " + dtLeave.get(0).get_txtAlasan());
            tvTotalReso.setText("0");
            tvTotalStockIH.setText("0");
            tvTotalActivity.setText("0");
            tvTotalActivityV2.setText("0");
            tvTotaladdDisplay.setText("0");
            tvTotalCustomerBase.setText("0");
            tvTotalCustomerBaseMTD.setText("0");
            tvTotalPO.setText("0");
            tvTotalPlanogram.setText("0");
            tvTotalQStock.setText("0");
            tvTotaloverStock.setText("0");
            tvTotalVisitPlan.setText("0");
            tvTotalVisitPlanDone.setText("0");
            tvTotalKuesioner.setText("0");
            tvTotalPOPStandard.setText("0");
            tvTotalkemasanrusak.setText("0");
            tvTotaltidaksesuaipesanan.setText("0");
            tv_reso0.setText("0");
            tv_reso1.setText("0");
            tv_reso2.setText("0");
            tv_act0.setText("0");
            tv_act1.setText("0");
            tv_act2.setText("0");
            tv_actV20.setText("0");
            tv_actV21.setText("0");
            tv_actV22.setText("0");

            tv_addDisplay0.setText("0");
            tv_addDisplay1.setText("0");
            tv_addDisplay2.setText("0");

            tv_planogram0.setText("0");
            tv_planogram1.setText("0");
            tv_planogram2.setText("0");

            tv_cb0.setText("0");
            tv_cb1.setText("0");
            tv_cb2.setText("0");
            tv_po0.setText("0");
            tv_po1.setText("0");
            tv_po2.setText("0");
            tv_qs0.setText("0");
            tv_qs1.setText("0");
            tv_qs2.setText("0");

            tv_StockINhand0.setText("0");
            tv_StockINhand1.setText("0");
            tv_StockINhand2.setText("0");

            tv_planogram0.setText("0");
            tv_planogram1.setText("0");
            tv_planogram2.setText("0");

            tv_qs0.setText("0");
            tv_qs1.setText("0");
            tv_qs2.setText("0");

            tv_qs0.setText("0");
            tv_qs1.setText("0");
            tv_qs2.setText("0");

            tv_oustVisit0.setText("0");
            tv_oustVisit1.setText("0");
            tv_oustVisit2.setText("0");

            tv_doneVisit0.setText("0");
            tv_doneVisit1.setText("0");
            tv_doneVisit2.setText("0");

            tv_os0.setText("0");
            tv_os1.setText("0");
            tv_os2.setText("0");

            tv_quesioner0.setText("0");
            tv_quesioner1.setText("0");
            tv_quesioner2.setText("0");

            tv_pop_standard0.setText("0");
            tv_pop_standard1.setText("0");
            tv_pop_standard2.setText("0");

            tv_kemasanRusak0.setText("0");
            tv_kemasanRusak1.setText("0");
            tv_kemasanRusak2.setText("0");

            tv_tidaksesuai0.setText("0");
            tv_tidaksesuai1.setText("0");
            tv_tidaksesuai2.setText("0");

            List<tVisitPlanRealisasiData> dtVisitPlan = new tVisitPlanRealisasiBL().getAllDataByIntSubmit("0");
            List<tVisitPlanRealisasiData> dtVisitPlanDone = new tVisitPlanRealisasiBL().getAllDataByIntSubmit("1");

            tvTotalVisitPlan.setText(dtVisitPlan !=null ? String.valueOf(dtVisitPlan.size()) : "0");
            tvTotalVisitPlanDone.setText(dtVisitPlanDone !=null ? String.valueOf(dtVisitPlanDone.size()) : "0");

        } else if (dtAbsen != null) {
            String txtBranchOutlet = dtAbsen.get_txtBranchCode() + " - " + dtAbsen.get_txtOutletName();
            tvBranchOutlet.setText(dtAbsen.get_txtOutletName().toString().equals("null") ? "-" : txtBranchOutlet);
            dtReso = new tSalesProductHeaderBL().getAllSalesProductHeaderByOutletCode(dtAbsen.get_txtOutletCode());
            dtSIH = new tStockInHandHeaderBL().getAllSalesProductHeaderByOutletCode(dtAbsen.get_txtOutletCode());
            dtPo = new tPurchaseOrderHeaderBL().getAllPurchaseOrderHeaderByOutletCode(dtAbsen.get_txtOutletCode());
            dtQStock = new tSalesProductQuantityHeaderBL().getAllSalesProductHeaderByOutletCode(dtAbsen.get_txtOutletCode());
            dtTSesuaiPesanan = new tTidakSesuaiPesananHeaderBL().getAllDataByOutletCodeReport(dtAbsen.get_txtOutletCode());
            dtKRusak = new tKemasanRusakHeaderBL().getAllHeaderByOutletCode(dtAbsen.get_txtOutletCode());
            dtOverStock = new tOverStockHeaderBL().getAllOverStockHeaderByOutletCode(dtAbsen.get_txtOutletCode());
            dtActivity = new tActivityBL().getAllDataByOutletCode(dtAbsen.get_txtOutletCode());
            dtActivityV2 = new tActivityMobileBL().getAllDataByOutletCode(dtAbsen.get_txtOutletCode());
            dtPlanogram = new tPlanogramMobileBL().getAllPlanogramByOutletCode(dtAbsen.get_txtOutletCode());
            if (dtAbsen.getType().equals("absen") || dtAbsen.getType().equals("absenFPE")){
                dtQuis = new tJawabanUserHeaderBL().GetDataByOutletCode(dtAbsen.get_txtOutletCode(), dtAbsen.get_dtDateCheckIn());
                dtQuis_push = new tJawabanUserHeaderBL().GetDataByOutletCodeAndSync(dtAbsen.get_txtOutletCode(), dtAbsen.get_dtDateCheckIn(), "1");
                dtQuis_unpush = new tJawabanUserHeaderBL().GetDataByOutletCodeAndSync(dtAbsen.get_txtOutletCode(), dtAbsen.get_dtDateCheckIn(), "0");
            }
            dtPOP = new tPOPStandardHeaderBL().GetDataByOutletCodeReport(dtAbsen.get_txtOutletCode());
            dtPOP_push = new tPOPStandardHeaderBL().GetDataByOutletCodeAndSync(dtAbsen.get_txtOutletCode(), "1");
            dtPOP_unPush = new tPOPStandardHeaderBL().GetDataByOutletCodeAndSync(dtAbsen.get_txtOutletCode(), "0");
            dtCbase = new tCustomerBasedMobileHeaderBL().getCountAllCustomerBasedAbsen(dtAbsen.get_txtOutletCode());
            dtCbaseMTD = new mCountConsumerMTDBL().getCountConsumerMTD(dtAbsen.get_txtOutletCode());
            dt_reso_unpush = new tSalesProductHeaderBL().getAllDataByIntSycAndOutlet("0", dtAbsen.get_txtOutletCode());
            dt_reso_push = new tSalesProductHeaderBL().getAllDataByIntSycAndOutlet("1", dtAbsen.get_txtOutletCode());
            dt_sih_unpush = new tStockInHandHeaderBL().getCountAllPlanogramByStatusSubmit(dtAbsen.get_txtOutletCode());
            dt_sih_push = new tStockInHandHeaderBL().countPlanogramHomeAbsenPush(dtAbsen.get_txtOutletCode());
            dt_sih_save = new tStockInHandHeaderBL().getCountAllStatusSave(dtAbsen.get_txtOutletCode());
            dt_act_unpush = new tActivityBL().getAllDataByIntSycAndOutlet("0", dtAbsen.get_txtOutletCode());
            dt_act_push = new tActivityBL().getAllDataByIntSycAndOutlet("1", dtAbsen.get_txtOutletCode());
            dt_cb_unpush = new tCustomerBasedMobileHeaderBL().getCountAllCustomerBasedAbsenUnpush(dtAbsen.get_txtOutletCode());
            dt_cb_push = new tCustomerBasedMobileHeaderBL().countCustomerBaseHomeAbsenPush(dtAbsen.get_txtOutletCode());
            dt_cb_save = new tCustomerBasedMobileHeaderBL().getCountAllCustomerBasedAbsenByStatusSave(dtAbsen.get_txtOutletCode());
            dt_actV2_save = 0;
            dt_actV2_unpush = new tActivityMobileBL().getCountAllActivityV2ByStatusSubmit(dtAbsen.get_txtOutletCode());
            dt_actV2_push = new tActivityMobileBL().countAcivityV2HomeAbsenPush(dtAbsen.get_txtOutletCode());
            dt_planogram_save = new tPlanogramMobileBL().getCountAllStatusSave(dtAbsen.get_txtOutletCode());
            dt_planogram_unpush = new tPlanogramMobileBL().getCountAllPlanogramByStatusSubmit(dtAbsen.get_txtOutletCode());
            dt_planogram_push = new tPlanogramMobileBL().countPlanogramHomeAbsenPush(dtAbsen.get_txtOutletCode());
            dt_po_save = 0;
            dt_po_unpush = new tPurchaseOrderHeaderBL().getCountPOStatusSubmit(dtAbsen.get_txtOutletCode());
            dt_po_push = new tPurchaseOrderHeaderBL().countPoPush(dtAbsen.get_txtOutletCode());
            dt_qStock_save = 0;
            dt_qStock_unpush = new tSalesProductQuantityHeaderBL().getCountQStockStatusSubmit(dtAbsen.get_txtOutletCode());
            dt_qStock_push = new tSalesProductQuantityHeaderBL().countQStockPush(dtAbsen.get_txtOutletCode());
            dt_kRusak_save = 0;
            dt_kRusak_unpush = new tKemasanRusakHeaderBL().getCounStatusSubmit(dtAbsen.get_txtOutletCode());
            dt_kRusak_push = new tKemasanRusakHeaderBL().countPush(dtAbsen.get_txtOutletCode());
            dt_tSesuai_save = 0;
            dt_tSesuai_unpush = new tTidakSesuaiPesananHeaderBL().getCounStatusSubmit(dtAbsen.get_txtOutletCode());
            dt_tSesuai_push = new tTidakSesuaiPesananHeaderBL().countPush(dtAbsen.get_txtOutletCode());
            int dt_oStock_save = 0;
            int dt_oStock_unpush= new tOverStockHeaderBL().getCountOStockStatusSubmit(dtAbsen.get_txtOutletCode());
            int dt_oStock_push = new tOverStockHeaderBL().countOStockPush(dtAbsen.get_txtOutletCode());

            int dt_outVisit_save = 0;
            int dt_outVisit_unpush= new tVisitPlanRealisasiBL().getCountOutVisitStatusUnPush(dtAbsen.get_txtOutletCode());
            int dt_outVisit_push = new tVisitPlanRealisasiBL().countOutVisitPush(dtAbsen.get_txtOutletCode());

            int dt_outVisit_unsubmit= new tVisitPlanRealisasiBL().getCountOutVisitStatusUnPush(dtAbsen.get_txtOutletCode());

            int dt_doneVisit_save = 0;
            int dt_doneVisit_unpush= new tOverStockHeaderBL().getCountOStockStatusSubmit(dtAbsen.get_txtOutletCode());
            int dt_doneVisit_push = new tOverStockHeaderBL().countOStockPush(dtAbsen.get_txtOutletCode());

            dt_quis_save = 0;
            if (dtQuis_push != null){
                dt_quis_push = dtQuis_push.size();
            }
            if (dtQuis_unpush != null){
                dt_quis_unpush = dtQuis_unpush.size();

            }
            dt_pop_save = 0;
            if (dtPOP_push != null){
                dt_pop_push = dtPOP_push.size();
            }
            if (dtPOP_unPush != null){
                dt_pop_unpush = dtPOP_unPush.size();
            }

            List<tVisitPlanRealisasiData> dtVisitPlan = new tVisitPlanRealisasiBL().getAllDataByIntSubmit("0");
            List<tVisitPlanRealisasiData> dtVisitPlanDone = new tVisitPlanRealisasiBL().getAllDataByIntSubmit("1");

            tvTotalReso.setText(dtReso != null ? String.valueOf(dtReso.size()) : "0");
            tvTotalStockIH.setText(dtSIH != null ? String.valueOf(dtSIH.size()) : "0");
            tvTotalActivity.setText(dtActivity != null ? String.valueOf(dtActivity.size()) : "0");
            tvTotalActivityV2.setText(dtActivityV2 != null ? String.valueOf(dtActivityV2.size()) : "0");
            tvTotaladdDisplay.setText(dtActivityV2 != null ? String.valueOf(dtActivityV2.size()) : "0");
            tvTotalKuesioner.setText(dtQuis != null ? String.valueOf(dtQuis.size()) : "0");
            tvTotalPOPStandard.setText(dtPOP != null ? String.valueOf(dtPOP.size()) : "0");
            tvTotalCustomerBase.setText(String.valueOf(dtCbase));
            tvTotalCustomerBaseMTD.setText(String.valueOf(dtCbaseMTD + dtCbase));
            tvTotalPO.setText(dtPo !=null ? String.valueOf(dtPo.size()) : "0");
            tvTotalQStock.setText(dtQStock !=null ? String.valueOf(dtQStock.size()) : "0");
            tvTotalkemasanrusak.setText(dtKRusak !=null ? String.valueOf(dtKRusak.size()) : "0");
            tvTotaltidaksesuaipesanan.setText(dtTSesuaiPesanan !=null ? String.valueOf(dtTSesuaiPesanan.size()) : "0");
            tvTotalPlanogram.setText(dtPlanogram !=null ? String.valueOf(dtPlanogram.size()) : "0");
            tvTotalVisitPlan.setText(dtVisitPlan !=null ? String.valueOf(dtVisitPlan.size()) : "0");
            tvTotalVisitPlanDone.setText(dtVisitPlanDone !=null ? String.valueOf(dtVisitPlanDone.size()) : "0");
            tvTotaloverStock.setText(dtOverStock !=null ? String.valueOf(dtOverStock.size()) : "0");

            if (dt_reso_unpush != null) {
                tv_reso1.setText(String.valueOf(dt_reso_unpush.size()));
            }
            if (dt_reso_push != null) {
                tv_reso2.setText(String.valueOf(dt_reso_push.size()));
            }
            if (dt_act_unpush != null) {
                tv_act1.setText(String.valueOf(dt_act_unpush.size()));
            }
            if (dt_act_push != null) {
                tv_act2.setText(String.valueOf(dt_act_push.size()));
            }
            tv_cb1.setText(String.valueOf(dt_cb_unpush));
            tv_cb2.setText(String.valueOf(dt_cb_push));
            tv_actV20.setText(String.valueOf(dt_actV2_save));
            tv_actV21.setText(String.valueOf(dt_actV2_unpush));
            tv_actV22.setText(String.valueOf(dt_actV2_push));
            tv_addDisplay0.setText(String.valueOf(dt_actV2_save));
            tv_addDisplay1.setText(String.valueOf(dt_actV2_unpush));
            tv_addDisplay2.setText(String.valueOf(dt_actV2_push));
            tv_planogram0.setText(String.valueOf(dt_planogram_save));
            tv_planogram1.setText(String.valueOf(dt_planogram_unpush));
            tv_planogram2.setText(String.valueOf(dt_planogram_push));
            tv_StockINhand0.setText(String.valueOf(dt_sih_save));
            tv_StockINhand1.setText(String.valueOf(dt_sih_unpush));
            tv_StockINhand2.setText(String.valueOf(dt_sih_push));
            tv_po0.setText(String.valueOf(dt_po_save));
            tv_po1.setText(String.valueOf(dt_po_unpush));
            tv_po2.setText(String.valueOf(dt_po_push));
            tv_qs0.setText(String.valueOf(dt_qStock_save));
            tv_qs1.setText(String.valueOf(dt_qStock_unpush));
            tv_qs2.setText(String.valueOf(dt_qStock_push));
            tv_os0.setText(String.valueOf(dt_oStock_save));
            tv_os1.setText(String.valueOf(dt_oStock_unpush));
            tv_os2.setText(String.valueOf(dt_oStock_push));
            tv_oustVisit0.setText(String.valueOf(dt_outVisit_save));
            tv_oustVisit1.setText(String.valueOf(dt_outVisit_unpush));
            tv_oustVisit2.setText(String.valueOf(dt_outVisit_push));
            tv_quesioner0.setText(String.valueOf(dt_quis_save));
            tv_quesioner1.setText(String.valueOf(dt_quis_unpush));
            tv_quesioner2.setText(String.valueOf(dt_quis_push));

            tv_pop_standard0.setText(String.valueOf(dt_pop_save));
            tv_pop_standard1.setText(String.valueOf(dt_pop_unpush));
            tv_pop_standard2.setText(String.valueOf(dt_pop_push));

            tv_kemasanRusak0.setText(String.valueOf(dt_kRusak_save));
            tv_kemasanRusak1.setText(String.valueOf(dt_kRusak_unpush));
            tv_kemasanRusak2.setText(String.valueOf(dt_kRusak_push));

            tv_tidaksesuai0.setText(String.valueOf(dt_tSesuai_save));
            tv_tidaksesuai1.setText(String.valueOf(dt_tSesuai_unpush));
            tv_tidaksesuai2.setText(String.valueOf(dt_tSesuai_push));


            tv_reso0.setText("0");
            tv_act0.setText("0");
            tv_cb0.setText(String.valueOf(dt_cb_save));


        } else {
            tvBranchOutlet.setText("Inactive");
            tvTotalReso.setText("0");
            tvTotalStockIH.setText("0");
            tvTotalActivity.setText("0");
            tvTotalActivityV2.setText("0");
            tvTotaladdDisplay.setText("0");
            tvTotalPlanogram.setText("0");
            tvTotalCustomerBase.setText("0");
            tvTotalCustomerBaseMTD.setText("0");
            tvTotalPO.setText("0");
            tvTotalQStock.setText("0");
            tvTotalkemasanrusak.setText("0");
            tvTotaltidaksesuaipesanan.setText("0");
            tvTotalVisitPlan.setText("0");
            tvTotalVisitPlanDone.setText("0");
            tvTotaloverStock.setText("0");
            tv_reso0.setText("0");
            tv_reso1.setText("0");
            tv_reso2.setText("0");
            tv_act0.setText("0");
            tv_act1.setText("0");
            tv_act2.setText("0");
            tv_actV20.setText("0");
            tv_actV21.setText("0");
            tv_actV22.setText("0");
            tv_addDisplay0.setText("0");
            tv_addDisplay1.setText("0");
            tv_addDisplay2.setText("0");
            tv_planogram0.setText("0");
            tv_planogram1.setText("0");
            tv_planogram2.setText("0");
            tv_cb0.setText("0");
            tv_cb1.setText("0");
            tv_cb2.setText("0");
            tv_po0.setText("0");
            tv_po1.setText("0");
            tv_po2.setText("0");
            tv_qs0.setText("0");
            tv_qs1.setText("0");
            tv_qs2.setText("0");
            tv_kemasanRusak0.setText("0");
            tv_kemasanRusak1.setText("0");
            tv_kemasanRusak2.setText("0");
            tv_tidaksesuai0.setText("0");
            tv_tidaksesuai1.setText("0");
            tv_tidaksesuai2.setText("0");
            tv_os0.setText("0");
            tv_os1.setText("0");
            tv_os2.setText("0");
            tv_oustVisit0.setText("0");
            tv_oustVisit1.setText("0");
            tv_oustVisit2.setText("0");

            tv_StockINhand0.setText("0");
            tv_StockINhand1.setText("0");
            tv_StockINhand2.setText("0");

            tv_quesioner0.setText("0");
            tv_quesioner1.setText("0");
            tv_quesioner2.setText("0");

            tv_pop_standard0.setText("0");
            tv_pop_standard1.setText("0");
            tv_pop_standard2.setText("0");

            List<tVisitPlanRealisasiData> dtVisitPlan = new tVisitPlanRealisasiBL().getAllDataByIntSubmit("0");
            List<tVisitPlanRealisasiData> dtVisitPlanDone = new tVisitPlanRealisasiBL().getAllDataByIntSubmit("1");

            tvTotalVisitPlan.setText(dtVisitPlan !=null ? String.valueOf(dtVisitPlan.size()) : "0");
            tvTotalVisitPlanDone.setText(dtVisitPlanDone !=null ? String.valueOf(dtVisitPlanDone.size()) : "0");
        }

        tvEmail.setText("(NIK : " + dt.get_TxtEmpId() + ")");

        final NavigationView nv = (NavigationView) getActivity().findViewById(R.id.navigation_view);
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

//        tvTotalReso.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(ll_reso.getVisibility() == View.VISIBLE){
//                    if (dtAbsen != null) {
//                        toolbar.setTitle("View Reso");
//                        nv.setCheckedItem(0);
//                        FragmentViewResoSPG fragmentViewResoSPG = new FragmentViewResoSPG();
//                        fragmentTransaction.replace(R.id.frame, fragmentViewResoSPG);
//                        fragmentTransaction.commit();
//                    }
//                }
//            }
//        });
//
//        tvTotalActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(ll_data_activity.getVisibility() == View.VISIBLE){
//                    if (dtAbsen != null) {
//                        toolbar.setTitle("View Actvity");
//                        nv.setCheckedItem(0);
//                        FragmentViewActvitySPG fragmentViewActvitySPG = new FragmentViewActvitySPG();
//                        fragmentTransaction.replace(R.id.frame, fragmentViewActvitySPG);
//                        fragmentTransaction.commit();
//                    }
//                }
//            }
//        });
//
//        tvTotalActivityV2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(ll_data_activityV2.getVisibility() == View.VISIBLE){
//                    if (dtAbsen != null) {
//                        toolbar.setTitle("View Actvity");
//                        nv.setCheckedItem(0);
//                        FragmentViewAddDisplay fragmentViewActivityMD = new FragmentViewAddDisplay();
//                        fragmentTransaction.replace(R.id.frame, fragmentViewActivityMD);
//                        fragmentTransaction.commit();
//                    }
//                }
//            }
//        });
//
//        tvTotalCustomerBase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(ll_data_customerbased.getVisibility() == View.VISIBLE){
//                    if (dtAbsen != null) {
//                        toolbar.setTitle("View Customer Base");
//                        nv.setCheckedItem(0);
//                        FragmentViewCustomerBaseSPG fragmentViewCustomerBaseSPG = new FragmentViewCustomerBaseSPG();
//                        fragmentTransaction.replace(R.id.frame, fragmentViewCustomerBaseSPG);
//                        fragmentTransaction.commit();
//                    }
//                }
//            }
//        });
//
//        tvTotalPO.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(ll_purchase_order.getVisibility() == View.VISIBLE){
//                    if (dtAbsen != null) {
//                        toolbar.setTitle("View PO Mobile");
//                        nv.setCheckedItem(0);
//                        FragmentPO fragmentPO = new FragmentPO();
//                        fragmentTransaction.replace(R.id.frame, fragmentPO);
//                        fragmentTransaction.commit();
//                    }
//                }
//            }
//        });
//
//        tvTotalQStock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(ll_dataQuantityStock.getVisibility() == View.VISIBLE){
//                    if (dtAbsen != null) {
//                        toolbar.setTitle("View Quantity Stock");
//                        nv.setCheckedItem(0);
//                        FragmentViewQuantityStock fragmentViewQuantityStock = new FragmentViewQuantityStock();
//                        fragmentTransaction.replace(R.id.frame, fragmentViewQuantityStock);
//                        fragmentTransaction.commit();
//                    }
//                }
//            }
//        });

        if (tDisplayPictureData.get_image() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(tDisplayPictureData.get_image(), 0, tDisplayPictureData.get_image().length);
            ivProfile.setImageBitmap(bitmap);
        } else {
            ivProfile.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                    R.drawable.profile));
        }

        ivProfile.setOnClickListener(this);

        return v;
    }

    private boolean isGPSEnabled() {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        // getting GPS status
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isGPSEnabled;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!isGPSEnabled()){
            final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_image:
                pickImage2();
                break;
        }
    }

    public void pickImage2() {
        CropImage.startPickImageActivity(getActivity());
    }
}
