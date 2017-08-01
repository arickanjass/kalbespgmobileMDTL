package com.kalbenutritionals.app.kalbespgmobile;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import bl.tCustomerBasedMobileHeaderBL;
import bl.tDisplayPictureBL;
import bl.tLeaveMobileBL;
import bl.tPurchaseOrderHeaderBL;
import bl.tSalesProductHeaderBL;
import bl.tSalesProductQuantityHeaderBL;
import bl.tUserLoginBL;
import de.hdodenhof.circleimageview.CircleImageView;
import library.spgmobile.common.mDownloadMasterData_mobileData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tDisplayPictureData;
import library.spgmobile.common.tLeaveMobileData;
import library.spgmobile.common.tPurchaseOrderHeaderData;
import library.spgmobile.common.tSalesProductHeaderData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.visitplanAbsenData;

public class FragmentInformation extends Fragment implements View.OnClickListener {

    View v;
    TextView tvUsername, tvBranchOutlet, tvEmail, tv_reso0, tv_reso1, tv_reso2, tv_act0, tv_act1, tv_act2, tv_cb0, tv_cb1, tv_cb2,tv_actV20, tv_actV21, tv_actV22,tv_po0,tv_po1, tv_po2,tv_qs0,tv_qs1, tv_qs2;
    private LinearLayout ll_reso, ll_data_activity, ll_data_activityV2 , ll_data_customerbased, ll_data_customerbasedMTD, ll_purchase_order, ll_dataQuantityStock;
    private TableRow tr_reso, tr_activity, tr_activityV2, tr_cunsomer, tr_po, tr_qStock;

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
        CircleTextView tvTotalCustomerBase = (CircleTextView) v.findViewById(R.id.tvTotalCustomerBase);
        CircleTextView tvTotalCustomerBaseMTD = (CircleTextView) v.findViewById(R.id.tvTotalConsumerMTD);
        CircleTextView tvTotalPO = (CircleTextView) v.findViewById(R.id.tvTotalPO);
        CircleTextView tvTotalQStock = (CircleTextView) v.findViewById(R.id.tvTotalQStock);
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

        ll_reso = (LinearLayout) v.findViewById(R.id.ll_reso);
        ll_data_activity = (LinearLayout) v.findViewById(R.id.ll_data_activity);
        ll_data_activityV2 = (LinearLayout) v.findViewById(R.id.ll_data_activityV2);
        ll_data_customerbased = (LinearLayout) v.findViewById(R.id.ll_data_customerbased);
        ll_data_customerbasedMTD = (LinearLayout) v.findViewById(R.id.ll_data_customerbasedMTD);
        ll_purchase_order = (LinearLayout) v.findViewById(R.id.ll_purchase_order);
        ll_dataQuantityStock = (LinearLayout) v.findViewById(R.id.ll_dataQuantityStock);

        tr_reso = (TableRow) v.findViewById(R.id.tr_reso);
        tr_activity = (TableRow) v.findViewById(R.id.tr_activity);
        tr_activityV2 = (TableRow) v.findViewById(R.id.tr_activityV2);
        tr_cunsomer = (TableRow) v.findViewById(R.id.tr_cunsomer);
        tr_po = (TableRow) v.findViewById(R.id.tr_po);
        tr_qStock = (TableRow) v.findViewById(R.id.tr_qStock);

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
                }
            }
        }

        tUserLoginData dt = new tUserLoginBL().getUserActive();
//        final tAbsenUserData dtAbsen = new tAbsenUserBL().getDataCheckInActive();
        final visitplanAbsenData dtAbsen = new clsHelperBL().getDataCheckInActive();
        List<tLeaveMobileData> dtLeave = new tLeaveMobileBL().getData("");

        List<tSalesProductHeaderData> dtReso;
        List<tPurchaseOrderHeaderData> dtPo;
        List<tSalesProductQuantityHeaderData> dtQStock;
        int dtCbase;
        int dtCbaseMTD;
        List<tActivityData> dtActivity;
        List<tSalesProductHeaderData> dt_reso_unpush;
        List<tSalesProductHeaderData> dt_reso_push;
        List<tActivityData> dt_act_unpush;
        List<tActivityData> dt_act_push;
        int dt_cb_save;
        int dt_cb_unpush;
        int dt_cb_push;

        tvUsername.setText(dt.get_txtUserName().toUpperCase());

        if (dtLeave.size() > 0) {
            tvBranchOutlet.setText(dtLeave.get(0).get_txtTypeAlasanName() + " - " + dtLeave.get(0).get_txtAlasan());
            tvTotalReso.setText("0");
            tvTotalActivity.setText("0");
            tvTotalCustomerBase.setText("0");
            tvTotalCustomerBaseMTD.setText("0");
            tvTotalPO.setText("0");
            tvTotalQStock.setText("0");
            tv_reso0.setText("0");
            tv_reso1.setText("0");
            tv_reso2.setText("0");
            tv_act0.setText("0");
            tv_act1.setText("0");
            tv_act2.setText("0");
            tv_actV20.setText("0");
            tv_actV21.setText("0");
            tv_actV22.setText("0");
            tv_cb0.setText("0");
            tv_cb1.setText("0");
            tv_cb2.setText("0");
            tv_po0.setText("0");
            tv_po1.setText("0");
            tv_po2.setText("0");
            tv_qs0.setText("0");
            tv_qs1.setText("0");
            tv_qs2.setText("0");
        } else if (dtAbsen != null) {
            tvBranchOutlet.setText(dtAbsen.get_txtBranchCode() + " - " + dtAbsen.get_txtOutletName());
            dtReso = new tSalesProductHeaderBL().getAllSalesProductHeaderByOutletCode(dtAbsen.get_txtOutletCode());
            dtPo = new tPurchaseOrderHeaderBL().getAllPurchaseOrderHeaderByOutletCode(dtAbsen.get_txtOutletCode());
            dtQStock = new tSalesProductQuantityHeaderBL().getAllSalesProductHeaderByOutletCode(dtAbsen.get_txtOutletCode());
            dtActivity = new tActivityBL().getAllDataByOutletCode(dtAbsen.get_txtOutletCode());
            dtCbase = new tCustomerBasedMobileHeaderBL().getCountAllCustomerBasedAbsen(dtAbsen.get_txtOutletCode());
            dtCbaseMTD = new mCountConsumerMTDBL().getCountConsumerMTD(dtAbsen.get_txtOutletCode());
            dt_reso_unpush = new tSalesProductHeaderBL().getAllDataByIntSycAndOutlet("0", dtAbsen.get_txtOutletCode());
            dt_reso_push = new tSalesProductHeaderBL().getAllDataByIntSycAndOutlet("1", dtAbsen.get_txtOutletCode());
            dt_act_unpush = new tActivityBL().getAllDataByIntSycAndOutlet("0", dtAbsen.get_txtOutletCode());
            dt_act_push = new tActivityBL().getAllDataByIntSycAndOutlet("1", dtAbsen.get_txtOutletCode());
            dt_cb_unpush = new tCustomerBasedMobileHeaderBL().getCountAllCustomerBasedAbsenUnpush(dtAbsen.get_txtOutletCode());
            dt_cb_push = new tCustomerBasedMobileHeaderBL().countCustomerBaseHomeAbsenPush(dtAbsen.get_txtOutletCode());
            dt_cb_save = new tCustomerBasedMobileHeaderBL().getCountAllCustomerBasedAbsenByStatusSave(dtAbsen.get_txtOutletCode());

            tvTotalReso.setText(dtReso != null ? String.valueOf(dtReso.size()) : "0");
            tvTotalActivity.setText(dtActivity != null ? String.valueOf(dtActivity.size()) : "0");
            tvTotalCustomerBase.setText(String.valueOf(dtCbase));
            tvTotalCustomerBaseMTD.setText(String.valueOf(dtCbaseMTD + dtCbase));
            tvTotalPO.setText(dtPo !=null ? String.valueOf(dtPo.size()) : "0");
            tvTotalQStock.setText(dtQStock !=null ? String.valueOf(dtQStock.size()) : "0");

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

            tv_reso0.setText("0");
            tv_act0.setText("0");
            tv_cb0.setText(String.valueOf(dt_cb_save));


        } else {
            tvBranchOutlet.setText("Inactive");
            tvTotalReso.setText("0");
            tvTotalActivity.setText("0");
            tvTotalCustomerBase.setText("0");
            tvTotalCustomerBaseMTD.setText("0");
            tvTotalPO.setText("0");
            tvTotalQStock.setText("0");
            tv_reso0.setText("0");
            tv_reso1.setText("0");
            tv_reso2.setText("0");
            tv_act0.setText("0");
            tv_act1.setText("0");
            tv_act2.setText("0");
            tv_actV20.setText("0");
            tv_actV21.setText("0");
            tv_actV22.setText("0");
            tv_cb0.setText("0");
            tv_cb1.setText("0");
            tv_cb2.setText("0");
            tv_po0.setText("0");
            tv_po1.setText("0");
            tv_po2.setText("0");
            tv_qs0.setText("0");
            tv_qs1.setText("0");
            tv_qs2.setText("0");
        }

        tvEmail.setText("(NIK : " + dt.get_TxtEmpId() + ")");

        final NavigationView nv = (NavigationView) getActivity().findViewById(R.id.navigation_view);
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        tvTotalReso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ll_reso.getVisibility() == View.VISIBLE){
                    if (dtAbsen != null) {
                        toolbar.setTitle("View Reso");
                        nv.setCheckedItem(0);
                        FragmentViewResoSPG fragmentViewResoSPG = new FragmentViewResoSPG();
                        fragmentTransaction.replace(R.id.frame, fragmentViewResoSPG);
                        fragmentTransaction.commit();
                    }
                }
            }
        });

        tvTotalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll_data_activity.getVisibility() == View.VISIBLE){
                    if (dtAbsen != null) {
                        toolbar.setTitle("View Actvity");
                        nv.setCheckedItem(0);
                        FragmentViewActvitySPG fragmentViewActvitySPG = new FragmentViewActvitySPG();
                        fragmentTransaction.replace(R.id.frame, fragmentViewActvitySPG);
                        fragmentTransaction.commit();
                    }
                }
            }
        });

        tvTotalActivityV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll_data_activityV2.getVisibility() == View.VISIBLE){
                    if (dtAbsen != null) {
                        toolbar.setTitle("View Actvity");
                        nv.setCheckedItem(0);
                        FragmentViewActivityMD fragmentViewActivityMD = new FragmentViewActivityMD();
                        fragmentTransaction.replace(R.id.frame, fragmentViewActivityMD);
                        fragmentTransaction.commit();
                    }
                }
            }
        });

        tvTotalCustomerBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll_data_customerbased.getVisibility() == View.VISIBLE){
                    if (dtAbsen != null) {
                        toolbar.setTitle("View Customer Base");
                        nv.setCheckedItem(0);
                        FragmentViewCustomerBaseSPG fragmentViewCustomerBaseSPG = new FragmentViewCustomerBaseSPG();
                        fragmentTransaction.replace(R.id.frame, fragmentViewCustomerBaseSPG);
                        fragmentTransaction.commit();
                    }
                }
            }
        });

        tvTotalPO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ll_purchase_order.getVisibility() == View.VISIBLE){
                    if (dtAbsen != null) {
                        toolbar.setTitle("View PO Mobile");
                        nv.setCheckedItem(0);
                        FragmentPO fragmentPO = new FragmentPO();
                        fragmentTransaction.replace(R.id.frame, fragmentPO);
                        fragmentTransaction.commit();
                    }
                }
            }
        });

        tvTotalQStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ll_dataQuantityStock.getVisibility() == View.VISIBLE){
                    if (dtAbsen != null) {
                        toolbar.setTitle("View Quantity Stock");
                        nv.setCheckedItem(0);
                        FragmentViewQuantityStock fragmentViewQuantityStock = new FragmentViewQuantityStock();
                        fragmentTransaction.replace(R.id.frame, fragmentViewQuantityStock);
                        fragmentTransaction.commit();
                    }
                }
            }
        });

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
