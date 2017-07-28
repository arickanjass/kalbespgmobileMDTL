package com.kalbenutritionals.app.kalbespgmobile;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.Iterator;
import java.util.List;

import bl.tLogErrorBL;
import bl.tUserLoginBL;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;

/**
 * Created by Robert on 03/04/2017.
 */

public class FragmentSettings extends Fragment {

    View v;

    String[] menuList = {"Change Password", "Push Data Error", ""};
    String userName, oldPassword, newPassword = "";
    private PackageInfo pInfo = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_settings, null);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_listview_settings, menuList);

        tUserLoginData dt = new tUserLoginBL().getUserActive();

        userName = dt.get_txtUserName().toString();


        try {
            pInfo = getContext().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        final List<String> datass = new tLogErrorBL().getFileLogFromDevice();
        boolean push = false;
        if (datass.size() > 0) {
            push = true;
        }
        ListView listView = (ListView) v.findViewById(R.id.menu_list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ubahPassword();
                }
                if (position == 1 && datass.size() == 0) {
                    new clsMainActivity().showCustomToast(getContext(), "No Error", true);
                } else if (position == 1 && datass.size() > 0) {
                    pushError();
                }
            }
        });
        /*listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    ubahPassword();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


        return v;
    }

    private void pushError() {
        Intent intent = new Intent(getActivity(), ActivityPushError.class);
        intent.putExtra("status", 1);
        startActivity(intent);
    }

    int intSet1 = 1;
    int intSet2 = 1;
    int intSet3 = 1;

    private void ubahPassword() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.popup_setting_ubah_password, null);

        final EditText passLama = (EditText) promptView.findViewById(R.id.etPassLama);
        final EditText passBaru1 = (EditText) promptView.findViewById(R.id.etPassbaru1);
        final EditText passBaru2 = (EditText) promptView.findViewById(R.id.etPassbaru2);

        final TextInputLayout tiPassLama = (TextInputLayout) promptView.findViewById(R.id.input_pass_lama);
        final TextInputLayout tiPassBaru1 = (TextInputLayout) promptView.findViewById(R.id.input_pass_baru1);
        final TextInputLayout tiPassBaru2 = (TextInputLayout) promptView.findViewById(R.id.input_pass_baru2);

        passLama.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(passLama) {
            public boolean onDrawableClick() {
                if (intSet1 == 1) {
                    passLama.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    intSet1 = 0;
                } else {
                    passLama.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    intSet1 = 1;
                }

                return true;
            }
        });
        passBaru1.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(passBaru1) {
            public boolean onDrawableClick() {
                if (intSet2 == 1) {
                    passBaru1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    intSet2 = 0;
                } else {
                    passBaru1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    intSet2 = 1;
                }

                return true;
            }
        });
        passBaru2.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(passBaru2) {
            public boolean onDrawableClick() {
                if (intSet3 == 1) {
                    passBaru2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    intSet3 = 0;
                } else {
                    passBaru2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    intSet3 = 1;
                }

                return true;
            }
        });


        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
        alertBuilder.setView(promptView);
        alertBuilder.setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        final AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new clsMainActivity().removeErrorMessage(tiPassLama);
                new clsMainActivity().removeErrorMessage(tiPassBaru1);
                new clsMainActivity().removeErrorMessage(tiPassBaru2);

                boolean validate = true;
                if (passLama.getText().toString().equals("")) {
                    validate = false;
                    new clsMainActivity().setErrorMessage(getContext(), tiPassLama, passLama, "Password lama wajib di isi");
                }
                if (passBaru1.getText().toString().equals("")) {
                    validate = false;
                    new clsMainActivity().setErrorMessage(getContext(), tiPassBaru1, passBaru1, "Password baru wajib di isi");
                }
                if (passBaru2.getText().toString().equals("")) {
                    new clsMainActivity().setErrorMessage(getContext(), tiPassBaru2, passBaru2, "Ulangi password baru wajib di isi");
                    validate = false;
                } else if (!passBaru1.getText().toString().equals(passBaru2.getText().toString()) && !passBaru2.getText().toString().equals("")) {
                    new clsMainActivity().setErrorMessage(getContext(), tiPassBaru2, passBaru2, "Password baru tidak sama");
                    validate = false;
                }

                if (validate) {
                    oldPassword = passLama.getText().toString();
                    newPassword = passBaru1.getText().toString();
                    AsyncCallChangePassword task = new AsyncCallChangePassword();
                    task.execute();
                    alertDialog.dismiss();
//                    new clsMainActivity().showCustomToast(getContext(), "Password telah di ubah", true);
                }
            }
        });
    }

    int intProcesscancel = 0;

    private class AsyncCallChangePassword extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new tUserLoginBL().changePassword(String.valueOf(userName), oldPassword, newPassword, pInfo.versionName);
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
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null && roledata.size() > 0) {
                Iterator i = roledata.iterator();
                while (i.hasNext()) {
                    JSONObject innerObj = (JSONObject) i.next();
                    Long IntResult = (Long) innerObj.get("_pboolValid");
                    String PstrMessage = (String) innerObj.get("_pstrMessage");

                    if (IntResult == 1) {
//                        tUserLoginData _tUserLoginData=new tUserLoginData();
//                        new mCounterNumberBL().saveDateTimeServer((String) innerObj.get("DatetimeNow"));
//                        _tUserLoginData.set_intId(1);
//                        _tUserLoginData.set_txtCab((String) innerObj.get("TxtCab"));
//                        _tUserLoginData.set_txtDataId((String) innerObj.get("TxtDataId"));
//                        _tUserLoginData.set_txtDeviceId((String) innerObj.get("TxtDeviceId"));
//                        _tUserLoginData.set_TxtEmail((String) innerObj.get("TxtEmail"));
//                        _tUserLoginData.set_TxtEmpId((String) innerObj.get("TxtEmpId"));
//                        _tUserLoginData.set_txtName((String) innerObj.get("TxtName"));
//                        _tUserLoginData.set_txtPassword((String) innerObj.get("TxtPassword"));
//                        _tUserLoginData.set_txtPathImage((String) innerObj.get("TxtPathImage"));
//                        _tUserLoginData.set_txtRoleId((String) innerObj.get("TxtRoleId"));
//                        _tUserLoginData.set_txtRoleName((String) innerObj.get("TxtRoleName"));
//                        _tUserLoginData.set_txtUserId((String) innerObj.get("TxtUserId"));
//                        _tUserLoginData.set_txtUserName((String) innerObj.get("TxtUserName"));
//                        _tUserLoginData.set_dtLastLogin((String) innerObj.get("DtLastLogin"));
//                        _tUserLoginData.set_txtOutletCode((String) innerObj.get("TxtOutletCode"));
//                        _tUserLoginData.set_txtOutletName((String) innerObj.get("TxtOutletName"));
//                        _tUserLoginData.set_txtBranchCode((String) innerObj.get("TxtBranchCode"));
//                        _tUserLoginData.set_txtSubmissionID((String) innerObj.get("TxtSubmissonId"));
//                        _tUserLoginData.set_txtCheckLocation((String) innerObj.get("TDeviceInfoUser_mobile"));
//
//                        String TxtSubmissonId = (String) innerObj.get("TxtSubmissonId");
//                        if(TxtSubmissonId.equals("")||TxtSubmissonId==null){
//                            showCustomToast(Login.this, new clsHardCode().txtMessDataSubmissionIdNotFound, false);
//                            Dialog.dismiss();
//                            return;
//                        }
//
//                        new tDeviceInfoUserBL().SaveInfoDevice(_tUserLoginData.get_TxtEmpId(), _tUserLoginData.get_txtDeviceId());
//                        new tUserLoginBL().saveData(_tUserLoginData);
//
////                        String nameOutlet = spnOutlet.getSelectedItem().toString();
////                        new mEmployeeAreaBL().DeleteEmployeeNotInId(HMOutletCode.get(nameOutlet));
//
//                        JSONArray JsonArrayDetail=(JSONArray) innerObj.get("ListOfMWebMenuAPI");
//                        Iterator iDetail = JsonArrayDetail.iterator();
//                        List<mMenuData> listData=new ArrayList<mMenuData>();
//                        while (iDetail.hasNext()) {
//                            JSONObject innerObjDetail = (JSONObject) iDetail.next();
//                            mMenuData data=new mMenuData();
//                            data.set_IntMenuID(String.valueOf((Long) innerObjDetail.get("IntMenuID")));
//                            data.set_IntOrder((Long) innerObjDetail.get("IntOrder"));
//                            data.set_IntParentID((Long) innerObjDetail.get("IntParentID"));
//                            data.set_TxtDescription((String) innerObjDetail.get("TxtDescription"));
//                            data.set_TxtLink((String) innerObjDetail.get("TxtLink"));
//                            data.set_TxtMenuName((String) innerObjDetail.get("TxtMenuName"));
//                            listData.add(data);
//                        }
//                        new mMenuBL().SaveData(listData);
////                        startService(new Intent(Login.this, MyServiceNative.class));
//                        finish();
//                        Intent myIntent = new Intent(Login.this, MainMenu.class);
//                        myIntent.putExtra("keyMainMenu", "main_menu");
//                        startActivity(myIntent);
                        new clsMainActivity().showCustomToast(getContext(), PstrMessage, true);
                    } else {
                        new clsMainActivity().showCustomToast(getContext(), PstrMessage, false);
                    }
                }

            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessNetworkOffline, false);
                }

            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(new clsHardCode().txtMessChangePass);
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
