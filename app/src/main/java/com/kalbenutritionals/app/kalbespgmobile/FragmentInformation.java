package com.kalbenutritionals.app.kalbespgmobile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.owater.library.CircleTextView;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.List;

import bl.clsHelperBL;
import bl.mCountConsumerMTDBL;
import bl.tAbsenUserBL;
import bl.tActivityBL;
import bl.tCustomerBasedMobileHeaderBL;
import bl.tDisplayPictureBL;
import bl.tLeaveMobileBL;
import bl.tSalesProductHeaderBL;
import bl.tUserLoginBL;
import de.hdodenhof.circleimageview.CircleImageView;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tDisplayPictureData;
import library.spgmobile.common.tLeaveMobileData;
import library.spgmobile.common.tSalesProductHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.visitplanAbsenData;

public class FragmentInformation extends Fragment implements View.OnClickListener {

    View v;
    TextView tvUsername, tvBranchOutlet, tvEmail, tv_reso0, tv_reso1, tv_reso2, tv_act0, tv_act1, tv_act2, tv_cb0, tv_cb1, tv_cb2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home_new, container, false);

        tDisplayPictureData tDisplayPictureData = new tDisplayPictureBL().getData();

        CircleImageView ivProfile = (CircleImageView) v.findViewById(R.id.profile_image);
        CircleTextView tvTotalReso = (CircleTextView) v.findViewById(R.id.tvTotalReso);
        CircleTextView tvTotalActivity = (CircleTextView) v.findViewById(R.id.tvTotalActivity);
        CircleTextView tvTotalCustomerBase = (CircleTextView) v.findViewById(R.id.tvTotalCustomerBase);
        CircleTextView tvTotalCustomerBaseMTD = (CircleTextView) v.findViewById(R.id.tvTotalConsumerMTD);
        tvUsername = (TextView) v.findViewById(R.id.tvUsername);
        tvBranchOutlet = (TextView) v.findViewById(R.id.tvBranchOutlet);
        tvEmail = (TextView) v.findViewById(R.id.tvEmail);
        tv_reso0 = (TextView) v.findViewById(R.id.tv_reso0);
        tv_reso1 = (TextView) v.findViewById(R.id.tv_reso1);
        tv_reso2 = (TextView) v.findViewById(R.id.tv_reso2);
        tv_act0 = (TextView) v.findViewById(R.id.tv_act0);
        tv_act1 = (TextView) v.findViewById(R.id.tv_act1);
        tv_act2 = (TextView) v.findViewById(R.id.tv_act2);
        tv_cb0 = (TextView) v.findViewById(R.id.tv_cb0);
        tv_cb1 = (TextView) v.findViewById(R.id.tv_cb1);
        tv_cb2 = (TextView) v.findViewById(R.id.tv_cb2);

        tUserLoginData dt = new tUserLoginBL().getUserActive();
//        final tAbsenUserData dtAbsen = new tAbsenUserBL().getDataCheckInActive();
        final visitplanAbsenData dtAbsen = new clsHelperBL().getDataCheckInActive();
        List<tLeaveMobileData> dtLeave = new tLeaveMobileBL().getData("");

        List<tSalesProductHeaderData> dtReso;
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

        tvUsername.setText(dt.get_txtUserName());

        if (dtLeave.size() > 0) {
            tvBranchOutlet.setText(dtLeave.get(0).get_txtTypeAlasanName() + " - " + dtLeave.get(0).get_txtAlasan());
            tvTotalReso.setText("0");
            tvTotalActivity.setText("0");
            tvTotalCustomerBase.setText("0");
            tvTotalCustomerBaseMTD.setText("0");
            tv_reso0.setText("0");
            tv_reso1.setText("0");
            tv_reso2.setText("0");
            tv_act0.setText("0");
            tv_act1.setText("0");
            tv_act2.setText("0");
            tv_cb0.setText("0");
            tv_cb1.setText("0");
            tv_cb2.setText("0");
        } else if (dtAbsen != null) {
            tvBranchOutlet.setText(dtAbsen.get_txtBranchCode() + " - " + dtAbsen.get_txtOutletName());
            dtReso = new tSalesProductHeaderBL().getAllSalesProductHeaderByOutletCode(dtAbsen.get_txtOutletCode());
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
            tv_reso0.setText("0");
            tv_reso1.setText("0");
            tv_reso2.setText("0");
            tv_act0.setText("0");
            tv_act1.setText("0");
            tv_act2.setText("0");
            tv_cb0.setText("0");
            tv_cb1.setText("0");
            tv_cb2.setText("0");
        }

        tvEmail.setText("(NIK : " + dt.get_TxtEmpId() + ")");

        final NavigationView nv = (NavigationView) getActivity().findViewById(R.id.navigation_view);
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        tvTotalReso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dtAbsen != null) {
                    toolbar.setTitle("View Reso");
                    nv.setCheckedItem(0);
                    FragmentViewResoSPG fragmentViewResoSPG = new FragmentViewResoSPG();
                    fragmentTransaction.replace(R.id.frame, fragmentViewResoSPG);
                    fragmentTransaction.commit();
                }
            }
        });

        tvTotalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dtAbsen != null) {
                    toolbar.setTitle("View Actvity");
                    nv.setCheckedItem(1);
                    FragmentViewActvitySPG fragmentViewActvitySPG = new FragmentViewActvitySPG();
                    fragmentTransaction.replace(R.id.frame, fragmentViewActvitySPG);
                    fragmentTransaction.commit();
                }
            }
        });

        tvTotalCustomerBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dtAbsen != null) {
                    toolbar.setTitle("View Customer Base");
                    nv.setCheckedItem(2);
                    FragmentViewCustomerBaseSPG fragmentViewCustomerBaseSPG = new FragmentViewCustomerBaseSPG();
                    fragmentTransaction.replace(R.id.frame, fragmentViewCustomerBaseSPG);
                    fragmentTransaction.commit();
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
