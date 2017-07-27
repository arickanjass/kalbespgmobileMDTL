package com.kalbenutritionals.app.kalbespgmobile;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import bl.clsHelperBL;
import bl.clsMainBL;
import bl.mCountConsumerMTDBL;
import bl.mCounterNumberBL;
import bl.mDownloadMasterData_mobileBL;
import bl.mMenuBL;
import bl.mUserRoleBL;
import bl.tDeviceInfoUserBL;
import bl.tLogErrorBL;
import bl.tUserLoginBL;
import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.mCountConsumerMTDData;
import library.spgmobile.common.mDownloadMasterData_mobileData;
import library.spgmobile.common.mEmployeeAreaData;
import library.spgmobile.common.mMenuData;
import library.spgmobile.common.mUserRoleData;
import library.spgmobile.common.tLogErrorData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mEmployeeAreaDA;
import library.spgmobile.dal.mUserRoleDA;
import library.spgmobile.dal.mconfigDA;
import service.MyServiceNative;
import service.MyTrackingLocationService;

import static junit.framework.Assert.assertEquals;

public class Login extends clsMainActivity {
    private EditText txtLoginEmail;
    private EditText txtLoginPassword;
    private Button btnLogin;
    private PackageInfo pInfo = null;
    private List<String> arrrole;
    private HashMap<String, String> HMRole = new HashMap<>();
    private Spinner spnRole;
    private int intSet = 1;
    private String selectedRole;
    private String txtEmail1;
    private String txtPassword1;
    private String[] arrdefaultBranch = new String[]{"-"};
    private String userName = "";
    private LinearLayout llContentWarning;
    private LinearLayout llContent;
    private TextView tvWarning;
    private Button btnCheckVersion;
    private android.support.v7.app.AlertDialog alertDialog;
    private int intProcesscancel = 0;
    ProgressDialog mProgressDialog;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit");
        builder.setMessage("Do you want to exit?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private clsHardCode clsHardcode = new clsHardCode();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        if (isMyServiceRunning(MyTrackingLocationService.class)) {
            stopService(new Intent(Login.this, MyTrackingLocationService.class));
        }

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imeiNumber = tm.getDeviceId();
        llContentWarning = (LinearLayout) findViewById(R.id.llContentWarning);
        llContent = (LinearLayout) findViewById(R.id.llContent);
        tvWarning = (TextView) findViewById(R.id.tvWarning);
        btnCheckVersion = (Button) findViewById(R.id.btnCheckVersion);
        new tDeviceInfoUserBL().SaveInfoDevice("", "", imeiNumber);
        ImageView imgBanner = (ImageView) findViewById(R.id.ivBannerLogin);
        imgBanner.setAdjustViewBounds(true);
        imgBanner.setScaleType(ImageView.ScaleType.CENTER_CROP);
        txtLoginEmail = (EditText) findViewById(R.id.txtLoginEmail);
        txtLoginEmail.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        txtLoginPassword = (EditText) findViewById(R.id.editTextPass);
        txtLoginEmail.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    intProcesscancel = 0;
                    txtEmail1 = txtLoginEmail.getText().toString();
                    txtPassword1 = txtLoginPassword.getText().toString();
                    if (!txtEmail1.equals("")) {
                        AsyncCallRole task = new AsyncCallRole();
                        task.execute();
                    } else {
                        txtLoginEmail.requestFocus();
                        showCustomToast(Login.this, "Please input username", false);
                    }
                    return true;
                }
                return false;
            }
        });

//        AsyncCallAppVesion task1 = new AsyncCallAppVesion();
//        task1.execute();
        txtLoginPassword.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(txtLoginPassword) {
            public boolean onDrawableClick() {
                if (intSet == 1) {
                    txtLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    intSet = 0;
                } else {
                    txtLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    intSet = 1;
                }

                return true;
            }
        });

        txtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER ||
                            keyCode == KeyEvent.KEYCODE_ENTER) {
                        btnLogin.performClick();
                        return true;
                    }
                }

                return false;
            }
        });

        txtLoginPassword.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    btnLogin.performClick();
                    return true;
                }
                return false;
            }
        });

        String linkAPI = new clsMainBL().getLinkAPI();
        try {
            URL u = new URL(linkAPI);
            linkAPI = u.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        TextView txtVersionLogin = (TextView) findViewById(R.id.txtVersionLogin);
        txtVersionLogin.setText(pInfo.versionName + "\n" + linkAPI);
        txtVersionLogin.setGravity(Gravity.CENTER_HORIZONTAL);
        spnRole = (Spinner) findViewById(R.id.spnType);

        spnRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedRole = spnRole.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        TextView tvForgotPassword = (TextView) findViewById(R.id.tv_forgot_password);
        tvForgotPassword.setPaintFlags(tvForgotPassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAccount();
            }
        });

        List<tLogErrorData> datass = new tLogErrorBL().getAllData();

        TextView tvPushError = (TextView) findViewById(R.id.tv_push_error);
        if (datass.size() > 0) {
            tvPushError.setVisibility(View.VISIBLE);
        } else {
            tvPushError.setVisibility(View.GONE);
        }
        tvPushError = (TextView) findViewById(R.id.tv_push_error);
        tvPushError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pushError = new Intent(Login.this, ActivityPushError.class);
                pushError.putExtra("status", 0);
                startActivity(pushError);
//                pushError();
            }
        });

        btnCheckVersion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                AsyncCallAppVersion task = new AsyncCallAppVersion();
                task.execute();
            }
        });

        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intProcesscancel = 0;
                if (txtLoginEmail.getText().length() == 0) {
                    showCustomToast(Login.this, "Please input username", false);
                } else {
                    if (spnRole.getCount() == 0) {
                        txtEmail1 = txtLoginEmail.getText().toString();
                        AsyncCallRole task = new AsyncCallRole();
                        task.execute();
                    } else {
                        txtEmail1 = txtLoginEmail.getText().toString();
                        txtPassword1 = txtLoginPassword.getText().toString();
                        AsyncCallLogin task = new AsyncCallLogin();
                        task.execute();
                    }
                }
            }
        });

        Button btnExit = (Button) findViewById(R.id.buttonExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);

                builder.setTitle("Exit");
                builder.setMessage("Do you want to exit?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        Button btnPing = (Button) findViewById(R.id.buttonPing);
        btnPing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUrl = new mconfigDA(new clsMainBL().getDb()).getData(new clsMainBL().getDb(), enumConfigData.ApiKalbe.getidConfigData()).get_txtValue();

                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    URL url = new URL(strUrl);
                    HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.connect();

                    assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
                    showCustomToast(Login.this, "Connected", true);

                } catch (IOException e) {
                    showCustomToast(Login.this, "Not connected", false);
                }
            }
        });

        Button btnResetPass = (Button) findViewById(R.id.button_reset);
        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAccount();
            }
        });

        ArrayAdapter<String> adapterspnBranch = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrdefaultBranch);
        spnRole.setAdapter(adapterspnBranch);
        spnRole.setEnabled(false);

        AsyncCallAppVersion task = new AsyncCallAppVersion();
        task.execute();
    }



    @Override
    protected void onStart() {
        super.onStart();
        if (isMyServiceRunning(MyTrackingLocationService.class)) {
            stopService(new Intent(Login.this, MyTrackingLocationService.class));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isMyServiceRunning(MyTrackingLocationService.class)) {
            stopService(new Intent(Login.this, MyTrackingLocationService.class));
        }
    }

    private void resetAccount() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View promptView = inflater.inflate(R.layout.fragment_reset_password, null);
        final EditText etEmail = (EditText) promptView.findViewById(R.id.et_email_reset);
        final TextInputLayout tiEmail = (TextInputLayout) promptView.findViewById(R.id.input_layout_email_reset);
        etEmail.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        Button btnReset = (Button) promptView.findViewById(R.id.button_reset);
        btnReset.setText(R.string.forgotpassword);
        new clsMainActivity().removeErrorMessage(tiEmail);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().equals("")) {
                    new clsMainActivity().setErrorMessage(getApplicationContext(), tiEmail, etEmail, "Email cannot empty");
                } else if (!etEmail.getText().toString().equals("")) {
                    userName = etEmail.getText().toString();
                    AsyncCallReset task = new AsyncCallReset();
                    task.execute();
                }
            }
        });
        android.support.v7.app.AlertDialog.Builder alertBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        alertBuilder.setView(promptView);
        alertDialog = alertBuilder.create();
        alertBuilder.setCancelable(false);
        alertDialog.show();
    }

    private class AsyncCallLogin extends AsyncTask<JSONArray, Void, JSONArray> {

        private ProgressDialog Dialog = new ProgressDialog(Login.this);

        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            String nameRole = selectedRole;
            try {
                Json = new tUserLoginBL().LoginNew(String.valueOf(txtEmail1), String.valueOf(txtPassword1), HMRole.get(nameRole), null, null, "", pInfo.versionName);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return Json;
        }

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            showCustomToast(Login.this, new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata.size() > 0) {
                for (Object aRoledata : roledata) {
                    JSONObject innerObj = (JSONObject) aRoledata;
                    Long IntResult = (Long) innerObj.get("_pboolValid");
                    String PstrMessage = (String) innerObj.get("_pstrMessage");

                    if (IntResult == 1) {
                        tUserLoginData _tUserLoginData = new tUserLoginData();
                        new mCounterNumberBL().saveDateTimeServer((String) innerObj.get("DatetimeNow"));
                        _tUserLoginData.set_intId(1);
                        _tUserLoginData.set_txtCab((String) innerObj.get("TxtCab"));
                        _tUserLoginData.set_txtDataId((String) innerObj.get("TxtDataId"));
                        _tUserLoginData.set_txtDeviceId((String) innerObj.get("TxtDeviceId"));
                        _tUserLoginData.set_TxtEmail((String) innerObj.get("TxtEmail"));
                        _tUserLoginData.set_TxtEmpId((String) innerObj.get("TxtEmpId"));
                        _tUserLoginData.set_txtName((String) innerObj.get("TxtName"));
                        _tUserLoginData.set_txtPassword((String) innerObj.get("TxtPassword"));
                        _tUserLoginData.set_txtPathImage((String) innerObj.get("TxtPathImage"));
                        _tUserLoginData.set_txtRoleId((String) innerObj.get("TxtRoleId"));
                        _tUserLoginData.set_txtRoleName((String) innerObj.get("TxtRoleName"));
                        _tUserLoginData.set_txtUserId((String) innerObj.get("TxtUserId"));
                        _tUserLoginData.set_txtUserName((String) innerObj.get("TxtUserName"));
                        _tUserLoginData.set_dtLastLogin((String) innerObj.get("DtLastLogin"));
                        _tUserLoginData.set_txtOutletCode((String) innerObj.get("TxtOutletCode"));
                        _tUserLoginData.set_txtOutletName((String) innerObj.get("TxtOutletName"));
                        _tUserLoginData.set_txtBranchCode((String) innerObj.get("TxtBranchCode"));
                        _tUserLoginData.set_txtImei((String) innerObj.get("TxtImei"));
                        _tUserLoginData.set_txtSubmissionID((String) innerObj.get("TxtSubmissonId"));
                        _tUserLoginData.set_txtCheckLocation((String) innerObj.get("IntRadius"));

                        String TxtSubmissonId = (String) innerObj.get("TxtSubmissonId");
                        if (TxtSubmissonId.equals("") || TxtSubmissonId == null) {
                            showCustomToast(Login.this, new clsHardCode().txtMessDataSubmissionIdNotFound, false);
                            Dialog.dismiss();
                            return;
                        }

                        new tDeviceInfoUserBL().SaveInfoDevice(_tUserLoginData.get_TxtEmpId(), _tUserLoginData.get_txtDeviceId(), _tUserLoginData.get_txtImei());
                        new tUserLoginBL().saveData(_tUserLoginData);

                        JSONArray JsonArrayDetail = (JSONArray) innerObj.get("ListOfMWebMenuAPI");
                        if (JsonArrayDetail != null) {
                            Iterator iDetail = JsonArrayDetail.iterator();
                            List<mMenuData> listData = new ArrayList<>();
                            while (iDetail.hasNext()) {
                                JSONObject innerObjDetail = (JSONObject) iDetail.next();
                                mMenuData data = new mMenuData();
                                data.set_IntMenuID(String.valueOf(innerObjDetail.get("IntMenuID")));
                                data.set_IntOrder((Long) innerObjDetail.get("IntOrder"));
                                data.set_IntParentID((Long) innerObjDetail.get("IntParentID"));
                                data.set_TxtDescription((String) innerObjDetail.get("TxtDescription"));
                                data.set_TxtLink((String) innerObjDetail.get("TxtLink"));
                                data.set_TxtMenuName((String) innerObjDetail.get("TxtMenuName"));
                                listData.add(data);
                            }
                            new mMenuBL().SaveData(listData);
                        }

                        JSONArray JsonArrayDetailCountConsumer = (JSONArray) innerObj.get("ListOfmEmployeeAreaCountConsumerMTD");
                        Iterator iDetailConsumer = JsonArrayDetailCountConsumer.iterator();
                        List<mCountConsumerMTDData> listDataConsumer = new ArrayList<>();
                        while (iDetailConsumer.hasNext()) {
                            JSONObject innerObjDetail = (JSONObject) iDetailConsumer.next();
                            mCountConsumerMTDData data = new mCountConsumerMTDData();
                            data.setJumlah(innerObjDetail.get("_jumlah").toString());
                            data.setTxtBranchCode(innerObjDetail.get("_txtBranchCode").toString());
                            data.setTxtBranchName(innerObjDetail.get("_txtBranchName").toString());
                            data.setTxtOutletCode(innerObjDetail.get("_txtOutletCode").toString());
                            data.setTxtOutletName(innerObjDetail.get("_txtOutletName").toString());
                            data.setTxtRegionName(innerObjDetail.get("_txtRegionName").toString());
                            listDataConsumer.add(data);
                        }
                        new mCountConsumerMTDBL().SaveData(listDataConsumer);

                        JSONArray JsonArrayDetailmDownloadData = (JSONArray) innerObj.get("ListOftDownloadData_mobile");
                        if (JsonArrayDetailmDownloadData != null) {
                            Iterator iDetailmDownloadData = JsonArrayDetailmDownloadData.iterator();
                            List<mDownloadMasterData_mobileData> listDatamDownloadData = new ArrayList<>();
                            while (iDetailmDownloadData.hasNext()) {
                                JSONObject innerObjDetail = (JSONObject) iDetailmDownloadData.next();
                                mDownloadMasterData_mobileData data = new mDownloadMasterData_mobileData();
                                data.set_intId((String) innerObjDetail.get("_intID"));
                                data.set_intModule((String) innerObjDetail.get("_intModule"));
                                data.set_txtModuleName((String) innerObjDetail.get("_txtModuleName"));
                                data.set_txtMasterData((String) innerObjDetail.get("_txtMasterData"));
                                data.set_intVersionApp((String) innerObjDetail.get("_intVersionApp"));
                                data.set_txtTypeApp((String) innerObjDetail.get("_txtTypeApp"));
                                data.set_txtVersion((String) innerObjDetail.get("_txtVersion"));
                                listDatamDownloadData.add(data);
                            }
                            new mDownloadMasterData_mobileBL().SaveData(listDatamDownloadData);
                        }
                        if (!isMyServiceRunning(MyServiceNative.class)) {
                            startService(new Intent(Login.this, MyServiceNative.class));
                        }
                        if (!isMyServiceRunning(MyTrackingLocationService.class)) {
                            startService(new Intent(Login.this, MyTrackingLocationService.class));
                        }

                        finish();
                        Intent myIntent = new Intent(Login.this, MainMenu.class);
                        myIntent.putExtra("keyMainMenu", "main_menu");
                        startActivity(myIntent);
                    } else {
                        showCustomToast(Login.this, PstrMessage, false);
                        txtLoginPassword.requestFocus();
                    }
                }

            } else {
                showCustomToast(Login.this, new clsHardCode().txtMessDataNotFound, false);
                txtLoginEmail.requestFocus();
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(new clsHardCode().txtMessLogin);
            Dialog.setCancelable(false);
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private class AsyncCallRole extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            JSONArray roledata = new JSONArray();
            try {
                roledata = new mUserRoleBL().getRoleAndOutletReturnJSON(txtEmail1, pInfo.versionName, getApplicationContext());

            } catch (ParseException e) {
                e.printStackTrace();
            }

            return roledata;
        }

        private ProgressDialog Dialog = new ProgressDialog(Login.this);

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            showCustomToast(Login.this, new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray _JSONArray) {
            spnRole.setAdapter(null);
            txtLoginEmail.requestFocus();

            if (_JSONArray != null) {
                Iterator i = _JSONArray.iterator();
                String message;
                clsHelper _clsHelper = new clsHelper();
                List<mUserRoleData> Listdata = new ArrayList<>();
                APIData dtAPIDATA = new APIData();
                SQLiteDatabase db = getDb();
                mEmployeeAreaDA _mEmployeeAreaDA = new mEmployeeAreaDA(db);
                _mEmployeeAreaDA.DeleteAllDataMConfig(db);
                try {
                    while (i.hasNext()) {
                        org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();

                        mUserRoleDA _mUserRoleDA = new mUserRoleDA(db);
                        _mUserRoleDA.DeleteAllDataMConfig(db);
                        int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
                        message = String.valueOf(innerObj.get("_pstrMessage"));
                        if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {

                            JSONArray JsonArray_role;
                            JsonArray_role = _clsHelper.ResultJsonArray(String.valueOf(innerObj.get("ListMWebUserRoleAPI")));

                            for (Object aJsonArray_role : JsonArray_role) {
                                JSONObject innerObj_detail = (JSONObject) aJsonArray_role;
                                mUserRoleData _data = new mUserRoleData();
                                int index = _mUserRoleDA.getContactsCount(db) + 1;
                                _data.set_intId(String.valueOf(index));
                                _data.set_intRoleId(String.valueOf(innerObj_detail.get("IntRoleID")));
                                _data.set_txtUserId(String.valueOf(innerObj_detail.get("IntUserID")));
                                _data.set_txtRoleName(String.valueOf(innerObj_detail.get("TxtRoleName")));
                                _mUserRoleDA.SaveDataMConfig(db, _data);
                                Listdata.add(_data);
                            }

                            org.json.simple.JSONArray JsonArray_outlet = _clsHelper.ResultJsonArray(String.valueOf(innerObj.get("Listvw_SalesInsentive_EmployeeAreaData")));

                            for (Object aJsonArray_outlet : JsonArray_outlet) {
                                JSONObject innerObj_detail = (JSONObject) aJsonArray_outlet;
                                mEmployeeAreaData _data = new mEmployeeAreaData();
                                int index = _mEmployeeAreaDA.getContactsCount(db) + 1;
                                _data.set_intID(String.valueOf(index));
                                _data.set_intBranchId(String.valueOf(innerObj_detail.get("IntBranchId")));
                                _data.set_intChannelId(String.valueOf(innerObj_detail.get("IntChannelId")));
                                _data.set_intEmployeeId(String.valueOf(innerObj_detail.get("IntEmployeeId")));
                                _data.set_intOutletId(String.valueOf(innerObj_detail.get("IntOutletId")));
                                _data.set_intRayonId(String.valueOf(innerObj_detail.get("IntRayonId")));
                                _data.set_intRegionId(String.valueOf(innerObj_detail.get("IntRegionId")));
                                _data.set_txtBranchCode(String.valueOf(innerObj_detail.get("TxtBranchCode")));
                                _data.set_txtBranchName(String.valueOf(innerObj_detail.get("TxtBranchName")));
                                _data.set_txtName(String.valueOf(innerObj_detail.get("TxtName")));
                                _data.set_txtNIK(String.valueOf(innerObj_detail.get("TxtNIK")));
                                _data.set_txtOutletCode(String.valueOf(innerObj_detail.get("TxtOutletCode")));
                                _data.set_txtOutletName(String.valueOf(innerObj_detail.get("TxtOutletName")));
                                _data.set_txtRayonCode(String.valueOf(innerObj_detail.get("TxtRayonCode")));
                                _data.set_txtRayonName(String.valueOf(innerObj_detail.get("TxtRayonName")));
                                _data.set_txtRegionName(String.valueOf(innerObj_detail.get("TxtRegionName")));

                                //_mEmployeeAreaDA.SaveDataMConfig(db, _data);
                            }

                            if (Listdata.size() > 0) {
                                arrrole = new ArrayList<>();
                                for (mUserRoleData dt : Listdata) {
                                    arrrole.add(dt.get_txtRoleName());
                                    HMRole.put(dt.get_txtRoleName(), dt.get_intRoleId());
                                }
                                spnRole.setAdapter(new MyAdapter(Login.this, R.layout.custom_spinner, arrrole));
                                spnRole.setEnabled(true);

                            } else if (Listdata.size() == 0) {
                                txtLoginEmail.requestFocus();
                            } else {
                                if (intProcesscancel == 1) {
                                    onCancelled();
                                } else {
                                    showCustomToast(Login.this, clsHardcode.txtMessNetworkOffline, false);
                                }
                                txtLoginEmail.requestFocus();
                            }
                        } else {
                            Handler h = new Handler(Looper.getMainLooper());
                            final String finalMessage = message;
                            h.post(new Runnable() {
                                public void run() {
                                    new clsMainActivity().showCustomToast(Login.this, finalMessage, false);
                                }
                            });

                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                showCustomToast(Login.this, clsHardcode.txtMessNetworkOffline, false);
            }


            Dialog.dismiss();
        }

        int intProcesscancel = 0;

        @Override
        protected void onPreExecute() {
            Dialog.setMessage(new clsHardCode().txtMessGetUserRole);
            Dialog.setCancelable(false);
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }

    private class MyAdapter extends ArrayAdapter<String> {
        MyAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
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
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.custom_spinner, parent, false);
            TextView label = (TextView) row.findViewById(R.id.tvTitle);
            label.setText(arrrole.get(position));
            TextView sub = (TextView) row.findViewById(R.id.tvDesc);
            sub.setVisibility(View.GONE);
            row.setBackgroundColor(Color.TRANSPARENT);
            return row;
        }

    }

    private class AsyncCallAppVersion extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray JsonData = null;
            try {
                JsonData = new clsHelperBL().GetDatamversionAppPostData(pInfo.versionName);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return JsonData;
        }

        private ProgressDialog Dialog = new ProgressDialog(Login.this);

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            showCustomToast(Login.this, new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray JsonArry) {
            llContentWarning.setVisibility(View.GONE);
            llContent.setVisibility(View.GONE);
            btnCheckVersion.setVisibility(View.GONE);
            if (JsonArry != null) {
                arrrole = new ArrayList<>();

                Iterator i = JsonArry.iterator();
                Boolean resUpdate = false;
                String txtLink = "";
                while (i.hasNext()) {
                    JSONObject innerObj = (JSONObject) i.next();
                    int boolValid = Integer.valueOf(String.valueOf(innerObj.get("_pboolValid")));
                    String pstrMessage = String.valueOf(String.valueOf(innerObj.get("_pstrMessage")));
                    if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                        llContent.setVisibility(View.VISIBLE);
                        if (pInfo.versionName.equals(innerObj.get("TxtVersion").toString())) {
                            resUpdate = false;
                        } else {
                            resUpdate = true;
                            txtLink = String.valueOf(innerObj.get("TxtLinkApp"));
                        }
                    } else {
                        llContentWarning.setVisibility(View.VISIBLE);
                        tvWarning.setText(pstrMessage);
                        showToast(Login.this, pstrMessage);
                    }
                }
                if (resUpdate) {
                    // instantiate it within the onCreate method
                    mProgressDialog = new ProgressDialog(Login.this);
                    mProgressDialog.setMessage("Please Wait For Downloading File....");
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    mProgressDialog.setCancelable(false);

                    // execute this when the downloader must be fired
                    final DownloadTask downloadTask = new DownloadTask(Login.this);
                    downloadTask.execute(txtLink);

                    mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            downloadTask.cancel(true);
                        }
                    });
                }
            } else {
                llContentWarning.setVisibility(View.VISIBLE);
                btnCheckVersion.setVisibility(View.VISIBLE);
                tvWarning.setText(clsHardcode.txtMessNetworkOffline);
                showCustomToast(Login.this, clsHardcode.txtMessNetworkOffline, false);
            }
            Dialog.dismiss();
        }

        int intProcesscancel = 0;

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Checking Your FPRS Version");
            Dialog.setCancelable(false);
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }

    private class DownloadTask extends AsyncTask<String, Integer, String> {
        private Context context;
        private PowerManager.WakeLock mWakeLock;

        DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
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
                String txtPath = new clsHardCode().txtPathUserData;
                File mediaStorageDir = new File(txtPath);
                // Create the storage directory if it does not exist
                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                        return null;
                    }
                }
                output = new FileOutputStream(txtPath + "kalbespgmobile.apk");

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
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
            mProgressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
            mProgressDialog.dismiss();
            if (result != null)
                showToast(context, "Download error: " + result);
            else {
                showToast(context, "File downloaded");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String txtPath = new clsHardCode().txtPathUserData + "kalbespgmobile.apk";
                intent.setDataAndType(Uri.fromFile(new File(txtPath)), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                if (Build.VERSION.SDK_INT >= 24) {
                    intent.setDataAndType(FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(txtPath)), "application/vnd.android.package-archive");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                } else {
                    intent.setDataAndType(Uri.fromFile(new File(txtPath)), "application/vnd.android.package-archive");
                }
                //intent.setDataAndType(Uri.fromFile(new File(txtPath)), "application/vnd.android.package-archive");

                startActivity(intent);
            }
        }
    }

    private class AsyncCallReset extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;
            try {
                Json = new tUserLoginBL().resetPassword(String.valueOf(userName), pInfo.versionName);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(Login.this);

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            showCustomToast(Login.this, new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null && roledata.size() > 0) {
                for (Object aRoledata : roledata) {
                    JSONObject innerObj = (JSONObject) aRoledata;
                    Long IntResult = (Long) innerObj.get("_pboolValid");
                    String PstrMessage = (String) innerObj.get("_pstrMessage");

                    if (IntResult == 1) {
                        showCustomToast(Login.this, PstrMessage, true);
                        alertDialog.dismiss();
                    } else {
                        showCustomToast(Login.this, "Failed" + PstrMessage, false);
                    }
                }
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    showCustomToast(Login.this, new clsHardCode().txtMessUnableToConnect, false);
                }
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(new clsHardCode().txtMessReset);
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
}
