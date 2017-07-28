package com.kalbenutritionals.app.kalbespgmobile;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bl.clsHelperBL;
import bl.mDownloadMasterData_mobileBL;
import bl.tAbsenUserBL;
import bl.tNotificationBL;
import bl.tUserLoginBL;
import come.example.viewbadger.ShortcutBadger;
import library.spgmobile.common.APIData;
import library.spgmobile.common.KoordinasiOutletData;
import library.spgmobile.common.clsPushData;
import library.spgmobile.common.dataJson;
import library.spgmobile.common.mDownloadMasterData_mobileData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tCustomerBasedMobileHeaderData;
import library.spgmobile.common.tJawabanUserData;
import library.spgmobile.common.tLeaveMobileData;
import library.spgmobile.common.tNotificationData;
import library.spgmobile.common.tPurchaseOrderHeaderData;
import library.spgmobile.common.tSalesProductHeaderData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tVisitPlanRealisasiData;
import library.spgmobile.dal.clsHardCode;
import service.MyServiceNative;

public class FragmentPushData extends Fragment {


    private TableLayout tlSOHeader;
    private TableLayout tlsPOHeader;
    private TableLayout tlsQuis;
    private TableLayout tlSODetail;
    private TableLayout tlActivity;
    private TableLayout tlCustomerBase;
    private TableLayout tlsQuantityStock;
    private TableLayout tlAbsen;
    private TableLayout tlLeave;
    private TableLayout tlKoordinasi;
    private TableLayout tlVisitPlan;
    private Button btnPush;
    private String myValue;
    private LinearLayout ll_reso, ll_data_activity, ll_data_customerbased, ll_purchase_order, ll_dataQuantityStock, ll_absen, ll_dataVisitPlan, ll_data_leave, ll_data_koordinasi, ll_dataQuesioner;

    View v;

    clsMainActivity _clsMainActivity = new clsMainActivity();

    PackageInfo pInfo = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        try {
            pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if(this.getArguments()!=null){
            myValue = this.getArguments().getString("message");
        }

        v = inflater.inflate(R.layout.activity_push_data, container, false);

        Intent serviceIntentMyServiceNative = new Intent(getContext(), MyServiceNative.class);
        getContext().stopService(serviceIntentMyServiceNative);

        tlSOHeader = (TableLayout) v.findViewById(R.id.tlSOHeader);
        tlsPOHeader = (TableLayout)v.findViewById(R.id.tlPO);
        tlsQuis = (TableLayout) v.findViewById(R.id.tl_quiz);
        tlActivity = (TableLayout) v.findViewById(R.id.tlActivity);
        tlCustomerBase = (TableLayout) v.findViewById(R.id.tl_cb);
        tlsQuantityStock = (TableLayout) v.findViewById(R.id.tl_quantity_stock);
        tlAbsen = (TableLayout) v.findViewById(R.id.tl_absen);
        tlLeave = (TableLayout) v.findViewById(R.id.tl_leave);
        tlKoordinasi = (TableLayout) v.findViewById(R.id.tl_koordinasi);
        tlVisitPlan = (TableLayout) v.findViewById(R.id.tl_visit_plan);
        btnPush = (Button) v.findViewById(R.id.btnPush);

        ll_reso = (LinearLayout) v.findViewById(R.id.ll_reso);
        ll_data_activity = (LinearLayout) v.findViewById(R.id.ll_data_activity);
        ll_data_customerbased = (LinearLayout) v.findViewById(R.id.ll_data_customerbased);
        ll_purchase_order = (LinearLayout) v.findViewById(R.id.ll_purchase_order);
        ll_dataQuantityStock = (LinearLayout) v.findViewById(R.id.ll_dataQuantityStock);
        ll_absen = (LinearLayout) v.findViewById(R.id.ll_absen);
        ll_dataVisitPlan = (LinearLayout) v.findViewById(R.id.ll_dataVisitPlan);
        ll_data_leave = (LinearLayout) v.findViewById(R.id.ll_data_leave);
        ll_data_koordinasi = (LinearLayout) v.findViewById(R.id.ll_data_koordinasi);
        ll_dataQuesioner = (LinearLayout) v.findViewById(R.id.ll_dataQuesioner);

        List<mDownloadMasterData_mobileData> mDownloadMasterData_mobileDataList = new ArrayList<>();

        mDownloadMasterData_mobileDataList = new mDownloadMasterData_mobileBL().GetAllData();

        Resources res = getResources();

        for(mDownloadMasterData_mobileData data : mDownloadMasterData_mobileDataList){
            String txt_id = data.get_txtMasterData().replaceAll(" ","");

            if(txt_id.equals(res.getResourceEntryName(ll_reso.getId()))){
                ll_reso.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_activity.getId()))){
                ll_data_activity.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_customerbased.getId()))){
                ll_data_customerbased.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_purchase_order.getId()))){
                ll_purchase_order.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_dataQuantityStock.getId()))){
                ll_dataQuantityStock.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_absen.getId()))){
                ll_absen.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_dataVisitPlan.getId()))){
                ll_dataVisitPlan.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_leave.getId()))){
                ll_data_leave.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_data_koordinasi.getId()))){
                ll_data_koordinasi.setVisibility(View.VISIBLE);
            } else if (txt_id.equals(res.getResourceEntryName(ll_dataQuesioner.getId()))){
                ll_dataQuesioner.setVisibility(View.VISIBLE);
            }
        }

        btnPush.setTextColor(Color.parseColor("#FFFFFF"));

        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncCallRole task=new AsyncCallRole();
                task.execute();
//                AsyncCallLogOut task2= new AsyncCallLogOut();
//                task.execute();
            }
        });

        loadData();

        return  v;
    }

    private void loadData() {
        String versionName="";
        try {
            versionName = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
        } catch (NameNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        clsPushData dtclsPushData=new clsHelperBL().pushData(versionName);

        if(dtclsPushData!=null){
            dataJson dtJson =dtclsPushData.getDtdataJson();

            if(dtJson.getListOftSalesProductHeaderData()!=null){
                initSOHeader(getContext(),dtJson.getListOftSalesProductHeaderData());
            } else {
                initSOHeader(getContext(),null);
            }

            if(dtJson.getListOftPurchaseOrderHeaderData() !=null){
                initPOHeader(getContext(),dtJson.getListOftPurchaseOrderHeaderData());
            } else {
                initPOHeader(getContext(),null);
            }

            if (dtJson.getListOftJawabanUserData() != null){
                initQuis(getContext(), dtJson.getListOftJawabanUserData());
            }
            else {
                initQuis(getContext(), null);
            }
            if (dtJson.getListOftVisitPlanRealisasiData() != null) {
                initVisitPlanRealisasiData(getContext(), dtJson.getListOftVisitPlanRealisasiData());
            } else {
                initVisitPlanRealisasiData(getContext(), null);
            }

            if (dtJson.getListOftSalesProductQuantityHeaderData() != null){
                initQuantityStockHeader(getContext(),dtJson.getListOftSalesProductQuantityHeaderData());
            } else {
                initQuantityStockHeader(getContext(), null);
            }

            if (dtJson.getListOfKoordinasiOutletImageData() != null){
                initKoordinasiOutlet(getContext(),dtJson.getListOfKoordinasiOutletData());
            } else {
                initKoordinasiOutlet(getContext(), null);
            }

            if(dtJson.getListOftActivityData()!=null){
                initActivity(getContext(),dtJson.getListOftActivityData());
            } else {
                initActivity(getContext(),null);
            }

            if(dtJson.get_ListOftCustomerBasedMobileHeaderData()!=null){
                initCustomerBase(getContext(),dtJson.get_ListOftCustomerBasedMobileHeaderData());
            } else {
                initCustomerBase(getContext(),null);
            }

            if(dtJson.getListOftAbsenUserData()!=null){
                inittAbsen(getContext(),dtJson.getListOftAbsenUserData());
            } else {
                inittAbsen(getContext(),null);
            }

            if(dtJson.getListOftLeaveMobileData()!=null){
                inittLeave(getContext(),dtJson.getListOftLeaveMobileData());
            } else {
                inittLeave(getContext(),null);
            }

        }
    }

    private void inittLeave(Context context, List<tLeaveMobileData> listOftLeaveMobileData) {
        tlLeave = (TableLayout) v.findViewById(R.id.tl_leave);

        tlLeave.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        String[] colTextHeader = {"No.", "Reason", "Type", "Date"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tv.setLayoutParams(params);

            tr.addView(tv);
        }
        tlLeave.addView(tr);

        if(listOftLeaveMobileData!=null){
            int index = 1;
            for(tLeaveMobileData dat : listOftLeaveMobileData){
                tr = new TableRow(getContext());

                TextView tv_index = new TextView(getContext());
                tv_index.setTextSize(12);
                tv_index.setPadding(10, 10, 10, 10);
                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv_index.setTextColor(Color.BLACK);
                tv_index.setGravity(Gravity.CENTER);
                tv_index.setText(String.valueOf(index + "."));
                tv_index.setLayoutParams(params);

                tr.addView(tv_index);

                TextView outlet_code = new TextView(getContext());
                outlet_code.setTextSize(12);
                outlet_code.setPadding(10, 10, 10, 10);
                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_code.setTextColor(Color.BLACK);
                outlet_code.setGravity(Gravity.CENTER);
                outlet_code.setText(dat.get_txtAlasan());
                outlet_code.setLayoutParams(params);

                tr.addView(outlet_code);

                TextView outlet_name = new TextView(getContext());
                outlet_name.setTextSize(12);
                outlet_name.setPadding(10, 10, 10, 10);
                outlet_name.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_name.setTextColor(Color.BLACK);
                outlet_name.setGravity(Gravity.CENTER);
                outlet_name.setText(dat.get_txtTypeAlasanName());
                outlet_name.setLayoutParams(params);

                tr.addView(outlet_name);

                TextView date = new TextView(getContext());
                date.setTextSize(12);
                date.setPadding(10, 10, 10, 10);
                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
                date.setTextColor(Color.BLACK);
                date.setGravity(Gravity.CENTER);
                date.setText(new clsMainActivity().giveFormatDate2(dat.get_dtLeave()));
                date.setLayoutParams(params);

                tr.addView(date);

                tlLeave.addView(tr,index++);
            }
        }
    }

    private void initCustomerBase(Context context, List<tCustomerBasedMobileHeaderData> listOftCustomerBasedMobileHeaderData) {

        tlCustomerBase.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        String[] colTextHeader = {"No.", "Code", "Date", "Branch Code"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tv.setLayoutParams(params);

            tr.addView(tv);
        }
        tlCustomerBase.addView(tr,0);

        if(listOftCustomerBasedMobileHeaderData!=null){
            int index = 1;
            for(tCustomerBasedMobileHeaderData dat : listOftCustomerBasedMobileHeaderData){
                tr = new TableRow(getContext());

                TextView tv_index = new TextView(getContext());
                tv_index.setTextSize(12);
                tv_index.setPadding(10, 10, 10, 10);
                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv_index.setTextColor(Color.BLACK);
                tv_index.setGravity(Gravity.CENTER);
                tv_index.setText(String.valueOf(index + "."));
                tv_index.setLayoutParams(params);

                tr.addView(tv_index);

                TextView outlet_code = new TextView(getContext());
                outlet_code.setTextSize(12);
                outlet_code.setPadding(10, 10, 10, 10);
                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_code.setTextColor(Color.BLACK);
                outlet_code.setGravity(Gravity.CENTER);
                outlet_code.setText(dat.get_txtSubmissionId());
                outlet_code.setLayoutParams(params);

                tr.addView(outlet_code);

                TextView outlet_name = new TextView(getContext());
                outlet_name.setTextSize(12);
                outlet_name.setPadding(10, 10, 10, 10);
                outlet_name.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_name.setTextColor(Color.BLACK);
                outlet_name.setGravity(Gravity.CENTER);
                outlet_name.setText(new clsMainActivity().giveFormatDate(dat.get_dtDate()));
                outlet_name.setLayoutParams(params);

                tr.addView(outlet_name);

                TextView date = new TextView(getContext());
                date.setTextSize(12);
                date.setPadding(10, 10, 10, 10);
                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
                date.setTextColor(Color.BLACK);
                date.setGravity(Gravity.CENTER);
                date.setText(dat.get_txtBranchCode());
                date.setLayoutParams(params);

                tr.addView(date);

                tlCustomerBase.addView(tr,index++);
            }
        }


    }

    private void initActivity(Context context, List<tActivityData> listOftActivityData) {

        tlActivity.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        String[] colTextHeader = {"No.", "Desc.", "Date", "Outlet Code"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tv.setLayoutParams(params);

            tr.addView(tv);
        }
        tlActivity.addView(tr,0);

        if(listOftActivityData!=null){
            int index = 1;
            for(tActivityData dat : listOftActivityData){
                tr = new TableRow(getContext());

                TextView tv_index = new TextView(getContext());
                tv_index.setTextSize(12);
                tv_index.setPadding(10, 10, 10, 10);
                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv_index.setTextColor(Color.BLACK);
                tv_index.setGravity(Gravity.CENTER);
                tv_index.setText(String.valueOf(index + "."));
                tv_index.setLayoutParams(params);

                tr.addView(tv_index);

                TextView outlet_code = new TextView(getContext());
                outlet_code.setTextSize(12);
                outlet_code.setPadding(10, 10, 10, 10);
                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_code.setTextColor(Color.BLACK);
                outlet_code.setGravity(Gravity.CENTER);
                outlet_code.setText(dat.get_txtDesc());
                outlet_code.setLayoutParams(params);

                tr.addView(outlet_code);

                TextView outlet_name = new TextView(getContext());
                outlet_name.setTextSize(12);
                outlet_name.setPadding(10, 10, 10, 10);
                outlet_name.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_name.setTextColor(Color.BLACK);
                outlet_name.setGravity(Gravity.CENTER);
                outlet_name.setText(new clsMainActivity().giveFormatDate(dat.get_dtActivity()));
                outlet_name.setLayoutParams(params);

                tr.addView(outlet_name);

                TextView date = new TextView(getContext());
                date.setTextSize(12);
                date.setPadding(10, 10, 10, 10);
                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
                date.setTextColor(Color.BLACK);
                date.setGravity(Gravity.CENTER);
                date.setText(dat.get_txtOutletCode());
                date.setLayoutParams(params);

                tr.addView(date);

                tlActivity.addView(tr,index++);
            }
        }
    }
    private void initVisitPlanRealisasiData(Context context, List<tVisitPlanRealisasiData> listOftVisitPlanRealisasiData) {
        tlVisitPlan.removeAllViews();
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);
        TableRow tr = new TableRow(getContext());

        String[] colTextHeader = {"No.", "Outlet Code", "Outlet Name", "Date"};
        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tv.setLayoutParams(params);

            tr.addView(tv);
        }
        tlVisitPlan.addView(tr, 0);

        if (listOftVisitPlanRealisasiData != null) {
            int index = 1;
            for (tVisitPlanRealisasiData dat : listOftVisitPlanRealisasiData) {
                tr = new TableRow(getContext());

                TextView tv_index = new TextView(getContext());
                tv_index.setTextSize(12);
                tv_index.setPadding(10, 10, 10, 10);
                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv_index.setTextColor(Color.BLACK);
                tv_index.setGravity(Gravity.CENTER);
                tv_index.setText(String.valueOf(index + "."));
                tv_index.setLayoutParams(params);

                tr.addView(tv_index);

                TextView outlet_code = new TextView(getContext());
                outlet_code.setTextSize(12);
                outlet_code.setPadding(10, 10, 10, 10);
                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_code.setTextColor(Color.BLACK);
                outlet_code.setGravity(Gravity.CENTER);
                outlet_code.setText(dat.get_txtOutletCode());
                outlet_code.setLayoutParams(params);

                tr.addView(outlet_code);

                TextView outlet_name = new TextView(getContext());
                outlet_name.setTextSize(12);
                outlet_name.setPadding(10, 10, 10, 10);
                outlet_name.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_name.setTextColor(Color.BLACK);
                outlet_name.setGravity(Gravity.CENTER);
                outlet_name.setText(dat.get_txtOutletName());
                outlet_name.setLayoutParams(params);

                tr.addView(outlet_name);

                TextView date = new TextView(getContext());
                date.setTextSize(12);
                date.setPadding(10, 10, 10, 10);
                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
                date.setTextColor(Color.BLACK);
                date.setGravity(Gravity.CENTER);
                date.setText(new clsMainActivity().giveFormatDate(dat.get_dtDateRealisasi()));
                date.setLayoutParams(params);

                tr.addView(date);

                tlVisitPlan.addView(tr, index++);
            }
        }
    }

    private void initSOHeader(Context context, List<tSalesProductHeaderData> listOftSalesProductHeaderData) {
        tlSOHeader = (TableLayout) v.findViewById(R.id.tlSOHeader);
        tlSOHeader.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        String[] colTextHeader = {"No.", "NO SO", "Date", "Outlet Code"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tv.setLayoutParams(params);

            tr.addView(tv);
        }
        tlSOHeader.addView(tr,0);

        if(listOftSalesProductHeaderData!=null){
            int index = 1;
            for(tSalesProductHeaderData dat : listOftSalesProductHeaderData){
                tr = new TableRow(getContext());

                TextView tv_index = new TextView(getContext());
                tv_index.setTextSize(12);
                tv_index.setPadding(10, 10, 10, 10);
                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv_index.setTextColor(Color.BLACK);
                tv_index.setGravity(Gravity.CENTER);
                tv_index.setText(String.valueOf(index + "."));
                tv_index.setLayoutParams(params);

                tr.addView(tv_index);

                TextView outlet_code = new TextView(getContext());
                outlet_code.setTextSize(12);
                outlet_code.setPadding(10, 10, 10, 10);
                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_code.setTextColor(Color.BLACK);
                outlet_code.setGravity(Gravity.CENTER);
                outlet_code.setText(dat.get_txtNoSo());
                outlet_code.setLayoutParams(params);

                tr.addView(outlet_code);

                TextView date = new TextView(getContext());
                date.setTextSize(12);
                date.setPadding(10, 10, 10, 10);
                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
                date.setTextColor(Color.BLACK);
                date.setGravity(Gravity.CENTER);
                date.setText(new clsMainActivity().giveFormatDate(dat.get_dtDate()));
                date.setLayoutParams(params);

                tr.addView(date);

                TextView outlet_name = new TextView(getContext());
                outlet_name.setTextSize(12);
                outlet_name.setPadding(10, 10, 10, 10);
                outlet_name.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_name.setTextColor(Color.BLACK);
                outlet_name.setGravity(Gravity.CENTER);
                outlet_name.setText(dat.get_OutletCode());
                outlet_name.setLayoutParams(params);

                tr.addView(outlet_name);

                tlSOHeader.addView(tr,index++);
            }
        }
    }

    private void initPOHeader(Context context, List<tPurchaseOrderHeaderData> listOftPurchaseOrderHeaderData) {
        tlsPOHeader = (TableLayout) v.findViewById(R.id.tlPO);
        tlsPOHeader.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        String[] colTextHeader = {"No.", "No Order", "Date", "Outlet Code"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());
           // tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tv.setLayoutParams(params);

            tr.addView(tv);
        }
        tlsPOHeader.addView(tr);

        if(listOftPurchaseOrderHeaderData!=null){
            int index = 1;
            for(tPurchaseOrderHeaderData dat : listOftPurchaseOrderHeaderData){
                tr = new TableRow(getContext());
//                TableLayout.LayoutParams tableRowParams=
//                        new TableLayout.LayoutParams
//                                (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
//
//                int leftMargin=0;
//                int topMargin=0;
//                int rightMargin=0;
//                int bottomMargin=0;
//                tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
//
//                tr.setLayoutParams(tableRowParams);

                TextView tv_index = new TextView(getContext());
                tv_index.setTextSize(12);
                tv_index.setPadding(10, 10, 10, 10);
                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv_index.setGravity(Gravity.CENTER);
                tv_index.setTextColor(Color.BLACK);
                tv_index.setText(String.valueOf(index + "."));
                tv_index.setLayoutParams(params);
                tr.addView(tv_index);

                TextView no_order = new TextView(getContext());
                no_order.setTextSize(12);
                no_order.setPadding(10, 10, 10, 10);
                no_order.setBackgroundColor(Color.parseColor("#f0f0f0"));
                no_order.setTextColor(Color.BLACK);
                no_order.setGravity(Gravity.CENTER);
                no_order.setText(dat.get_txtNoOrder());
                no_order.setLayoutParams(params);

                tr.addView(no_order);

                TextView date = new TextView(getContext());
                date.setTextSize(12);
                date.setPadding(10, 10, 10, 10);
                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
                date.setTextColor(Color.BLACK);
                date.setGravity(Gravity.CENTER);
                date.setText(new clsMainActivity().giveFormatDate2(dat.get_dtDate()));
                date.setLayoutParams(params);

                tr.addView(date);

                TextView outlet_code = new TextView(getContext());
                outlet_code.setTextSize(12);
                outlet_code.setPadding(10, 10, 10, 10);
                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_code.setTextColor(Color.BLACK);
                outlet_code.setGravity(Gravity.CENTER);
                outlet_code.setText(dat.get_OutletCode());
                outlet_code.setLayoutParams(params);

                tr.addView(outlet_code);

                tlsPOHeader.addView(tr, index++);
            }
        }

    }

    //table for view quis
    private void initQuis(Context context, List<tJawabanUserData> listOftJawabanUserData) {
        tlsQuis = (TableLayout) v.findViewById(R.id.tl_quiz);
        tlsQuis.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        String[] colTextHeader = {"No. Question", "Answer"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());
            // tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tv.setLayoutParams(params);

            tr.addView(tv);
        }
        tlsQuis.addView(tr);

        if(listOftJawabanUserData!=null){
            int index = 1;
            for(tJawabanUserData dat : listOftJawabanUserData){

                tr = new TableRow(getContext());
                TextView tv_no_quis = new TextView(getContext());
                tv_no_quis.setTextSize(12);
                tv_no_quis.setPadding(10, 10, 10, 10);
                tv_no_quis.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv_no_quis.setGravity(Gravity.CENTER);
                tv_no_quis.setTextColor(Color.BLACK);
                tv_no_quis.setText(String.valueOf(index + "."));
                tv_no_quis.setLayoutParams(params);
                tr.addView(tv_no_quis);

                TextView answer = new TextView(getContext());
                answer.setTextSize(12);
                answer.setPadding(10, 10, 10, 10);
                answer.setBackgroundColor(Color.parseColor("#f0f0f0"));
                answer.setTextColor(Color.BLACK);
                answer.setGravity(Gravity.CENTER);
                answer.setText("Done Question no. " + index);
                answer.setLayoutParams(params);

                tr.addView(answer);

                tlsQuis.addView(tr, index ++);
            }
        }

    }

    private void initQuantityStockHeader(Context context, List<tSalesProductQuantityHeaderData> listOftSalesProductQuantityHeaderData) {
        tlsQuantityStock = (TableLayout) v.findViewById(R.id.tl_quantity_stock);
        tlsQuantityStock.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        String[] colTextHeader = {"No.", "No Quantity", "Date", "Outlet Code"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());
            // tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tv.setLayoutParams(params);

            tr.addView(tv);
        }
        tlsQuantityStock.addView(tr);

        if (listOftSalesProductQuantityHeaderData != null){
            int index = 1;
            for (tSalesProductQuantityHeaderData dat : listOftSalesProductQuantityHeaderData){
                tr = new TableRow(getContext());

                TextView tv_index = new TextView(getContext());
                tv_index.setTextSize(12);
                tv_index.setPadding(10, 10, 10, 10);
                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv_index.setGravity(Gravity.CENTER);
                tv_index.setTextColor(Color.BLACK);
                tv_index.setText(String.valueOf(index + "."));
                tv_index.setLayoutParams(params);
                tr.addView(tv_index);

                TextView no_quantity_stock = new TextView(getContext());
                no_quantity_stock.setTextSize(12);
                no_quantity_stock.setPadding(10, 10, 10, 10);
                no_quantity_stock.setBackgroundColor(Color.parseColor("#f0f0f0"));
                no_quantity_stock.setTextColor(Color.BLACK);
                no_quantity_stock.setGravity(Gravity.CENTER);
                no_quantity_stock.setText(dat.get_txtQuantityStock());
                no_quantity_stock.setLayoutParams(params);

                tr.addView(no_quantity_stock);

                TextView date = new TextView(getContext());
                date.setTextSize(12);
                date.setPadding(10, 10, 10, 10);
                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
                date.setTextColor(Color.BLACK);
                date.setGravity(Gravity.CENTER);
                date.setText(new clsMainActivity().giveFormatDate2(dat.get_dtDate()));
                date.setLayoutParams(params);

                tr.addView(date);

                TextView outlet_code = new TextView(getContext());
                outlet_code.setTextSize(12);
                outlet_code.setPadding(10, 10, 10, 10);
                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_code.setTextColor(Color.BLACK);
                outlet_code.setGravity(Gravity.CENTER);
                outlet_code.setText(dat.get_OutletCode());
                outlet_code.setLayoutParams(params);

                tr.addView(outlet_code);

                tlsQuantityStock.addView(tr, index++);
            }
        }
    }

    private void initKoordinasiOutlet(Context context, List<KoordinasiOutletData> listOfKoordinasiOutletData) {
        tlKoordinasi = (TableLayout) v.findViewById(R.id.tl_koordinasi);
        tlKoordinasi.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        String[] colTextHeader = {"No.", "Keterangan", "Date", "Outlet Code"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());
            // tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tv.setLayoutParams(params);

            tr.addView(tv);
        }
        tlKoordinasi.addView(tr);

        if (listOfKoordinasiOutletData != null){
            int index = 1;
            for (KoordinasiOutletData dat : listOfKoordinasiOutletData){
                tr = new TableRow(getContext());

                TextView tv_index = new TextView(getContext());
                tv_index.setTextSize(12);
                tv_index.setPadding(10, 10, 10, 10);
                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv_index.setGravity(Gravity.CENTER);
                tv_index.setTextColor(Color.BLACK);
                tv_index.setText(String.valueOf(index + "."));
                tv_index.setLayoutParams(params);
                tr.addView(tv_index);

                TextView keterangan = new TextView(getContext());
                keterangan.setTextSize(12);
                keterangan.setPadding(10, 10, 10, 10);
                keterangan.setBackgroundColor(Color.parseColor("#f0f0f0"));
                keterangan.setTextColor(Color.BLACK);
                keterangan.setGravity(Gravity.CENTER);
                keterangan.setText(dat.get_txtKeterangan());
                keterangan.setLayoutParams(params);
                tr.addView(keterangan);

                TextView date = new TextView(getContext());
                date.setTextSize(12);
                date.setPadding(10, 10, 10, 10);
                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
                date.setTextColor(Color.BLACK);
                date.setGravity(Gravity.CENTER);
                date.setText(new clsMainActivity().giveFormatDate2(dat.get_dtDate()));
                date.setLayoutParams(params);
                tr.addView(date);

                TextView outlet_code = new TextView(getContext());
                outlet_code.setTextSize(12);
                outlet_code.setPadding(10, 10, 10, 10);
                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_code.setTextColor(Color.BLACK);
                outlet_code.setGravity(Gravity.CENTER);
                outlet_code.setText(dat.get_txtOutletCode());
                outlet_code.setLayoutParams(params);
                tr.addView(outlet_code);

                tlKoordinasi.addView(tr, index++);
            }
        }
    }

    private class AsyncCallRole extends AsyncTask<List<dataJson>, Void, List<dataJson>> {
        @Override
        protected List<dataJson> doInBackground(List<dataJson>... params) {
            String versionName="";
            try {
                versionName = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
//                    versionName = new clsMainActivity().getApplicationContext().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
            } catch (NameNotFoundException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
//            android.os.Debug.waitForDebugger();
            List<dataJson> roledata=new ArrayList<dataJson>();
            clsPushData dtJson= new clsHelperBL().pushData(versionName);
            dataJson dtdataJson=new dataJson();
            if(dtJson!=null){

                try {
                    JSONArray Jresult= new clsHelperBL().callPushDataReturnJson(versionName,dtJson.getDtdataJson().txtJSON().toString(),dtJson.getFileUpload());
                    new clsHelperBL().saveDataPush(dtJson.getDtdataJson(),Jresult);
                    dtdataJson.setIntResult("1");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    dtdataJson.setIntResult("0");
                }
            }
            else
            {
                dtdataJson.setIntResult("0");
                dtdataJson.setTxtMessage("No Data");
            }
            roledata.add(dtdataJson);
            return roledata ;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());
        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            Toast toast = Toast.makeText(getContext(),
                    new clsHardCode().txtMessCancelRequest, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 25, 400);
            toast.show();
        }
        @Override
        protected void onPostExecute(List<dataJson> roledata) {
            boolean result = false;
            if(roledata.get(0).getIntResult().equals("1")){
                new clsMainActivity().showCustomToast(getContext(), "Success Push Data", true);
                Intent myIntent = new Intent(getContext(), MainMenu.class);
                if(myValue!=null&&myValue.equals("notMainMenu")){
                    AsyncCallLogOut task = new AsyncCallLogOut();
                    task.execute();
                } else {
                    startActivity(myIntent);
                }
            }else{
                new clsMainActivity().showToast(getContext(), roledata.get(0).getTxtMessage());
            }
            Dialog.dismiss();
        }
        int intProcesscancel=0;
        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Syncronize Data!!");
            Dialog.setCancelable(false);
            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intProcesscancel=1;
                }
            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            AsyncCallLogOut task = new AsyncCallLogOut();
            task.execute();
            Dialog.dismiss();
        }

    }

    int intProcesscancel = 0;
    private class AsyncCallLogOut extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            String versionName = "";
            try {
                versionName = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            JSONArray Json = null;

            try {
                List<tAbsenUserData> listAbsenData = new ArrayList<tAbsenUserData>();
                tAbsenUserData dtTabsenData = new tAbsenUserBL().getDataCheckInActive();
                if (dtTabsenData != null) {
                    dtTabsenData.set_dtDateCheckOut(_clsMainActivity.FormatDateComplete().toString());
                    dtTabsenData.set_intSubmit("1");
                    dtTabsenData.set_intSync("0");
                    listAbsenData.add(dtTabsenData);
                    new tAbsenUserBL().saveData(listAbsenData);
                }
                clsPushData dtJson = new clsHelperBL().pushData(versionName);
                if (dtJson != null) {
                    try {
                        JSONArray Jresult = null;
                        if (dtJson.getDtdataJson().getListOftAbsenUserData() != null) {
                            List<tAbsenUserData> listAbsen = dtJson.getDtdataJson().getListOftAbsenUserData();
                            if (listAbsen.get(0).get_txtAbsen().equals("0")) {
                                Jresult = new clsHelperBL().callPushDataReturnJson(versionName, dtJson.getDtdataJson().txtJSON().toString(), dtJson.getFileUpload());
                            } else {
                                Jresult = new clsHelperBL().callPushDataReturnJson(versionName, dtJson.getDtdataJson().txtJSON().toString(), null);
                            }
//                            new clsHelperBL().saveDataPush(dtJson.getDtdataJson(), Jresult);
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

                try {
                    pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }

                Json = new tUserLoginBL().LogoutFromPushData(pInfo.versionName);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            Toast toast = Toast.makeText(getContext(), "cancel", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 25, 400);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null) {
                Iterator i = roledata.iterator();
                while (i.hasNext()) {
                    JSONObject innerObj = (JSONObject) i.next();
                    Long IntResult = (Long) innerObj.get("_pboolValid");
                    String PstrMessage = (String) innerObj.get("_pstrMessage");

                    if (IntResult == 1) {
                        tNotificationBL _tNotificationBL = new tNotificationBL();
                        List<tNotificationData> ListData = _tNotificationBL.getAllDataWillAlert("1");
                        if (ListData != null) {
                            for (tNotificationData dttNotificationData : ListData) {
                                NotificationManager tnotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                                tnotificationManager.cancelAll();
                                ShortcutBadger.applyCount(getContext(), 0);
                                System.gc();
                            }
                        }
                        new clsHelperBL().DeleteAllDB();
                        Intent nextScreen = new Intent(getContext(), Splash.class);
                        startActivity(nextScreen);
                        getActivity().finish();
                    } else {
                        Toast toast = Toast.makeText(getContext(),
                                PstrMessage, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 25, 400);
                        toast.show();
                    }
                }

            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    _clsMainActivity.showCustomToast(getContext(), "Offline", false);
                }

            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage(new clsHardCode().txtMessLogOut);
            Dialog.setCancelable(false);
            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intProcesscancel = 1;
                }
            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }


    public void inittAbsen(Context _ctx,List<tAbsenUserData> ListData){
        tlAbsen = (TableLayout) v.findViewById(R.id.tl_absen);
        tlAbsen.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        String[] colTextHeader = {"No.", "Outlet Code", "Outlet Name", "Date"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tv.setLayoutParams(params);

            tr.addView(tv);
        }
        tlAbsen.addView(tr);

        if(ListData!=null){
            int index = 1;
            for(tAbsenUserData dat : ListData){
                tr = new TableRow(getContext());

                TextView tv_index = new TextView(getContext());
                tv_index.setTextSize(12);
                tv_index.setPadding(10, 10, 10, 10);
                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv_index.setTextColor(Color.BLACK);
                tv_index.setGravity(Gravity.CENTER);
                tv_index.setText(String.valueOf(index + "."));
                tv_index.setLayoutParams(params);

                tr.addView(tv_index);

                TextView outlet_code = new TextView(getContext());
                outlet_code.setTextSize(12);
                outlet_code.setPadding(10, 10, 10, 10);
                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_code.setTextColor(Color.BLACK);
                outlet_code.setGravity(Gravity.CENTER);
                outlet_code.setText(dat.get_txtOutletCode());
                outlet_code.setLayoutParams(params);

                tr.addView(outlet_code);

                TextView outlet_name = new TextView(getContext());
                outlet_name.setTextSize(12);
                outlet_name.setPadding(10, 10, 10, 10);
                outlet_name.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_name.setTextColor(Color.BLACK);
                outlet_name.setGravity(Gravity.CENTER);
                outlet_name.setText(dat.get_txtOutletName());
                outlet_name.setLayoutParams(params);

                tr.addView(outlet_name);

                TextView date = new TextView(getContext());
                date.setTextSize(12);
                date.setPadding(10, 10, 10, 10);
                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
                date.setTextColor(Color.BLACK);
                date.setGravity(Gravity.CENTER);
                date.setText(new clsMainActivity().giveFormatDate2(dat.get_dtDateCheckIn()));
                date.setLayoutParams(params);

                tr.addView(date);

                tlAbsen.addView(tr,index++);
            }
        }
    }
}
