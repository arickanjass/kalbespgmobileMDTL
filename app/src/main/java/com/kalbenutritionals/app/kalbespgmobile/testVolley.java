package com.kalbenutritionals.app.kalbespgmobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

import bl.clsMainBL;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tDeviceInfoUserDA;

public class testVolley extends Activity implements View.OnClickListener {

    private TextView msgResponse;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_volley);

        Button btnPost = (Button) findViewById(R.id.btnPostLogin);
        msgResponse = (TextView) findViewById(R.id.tvResponse);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        btnPost.setOnClickListener(this);
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }

    private void makeStringReq() {
        showProgressDialog();

        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        linkAPI dtlinkAPI=new linkAPI();
        dtlinkAPI.set_txtMethod("GetDataMWebUserWithActiveDirectoryAndDatabaseNewInsentiveAndMenuAccess");
        dtlinkAPI.set_txtParam("");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        if (pInfo != null) {
            dtlinkAPI.set_txtVesion(pInfo.versionName);
        }
        String strLinkAPI= dtlinkAPI.QueryString(new clsMainBL().getLinkAPI());

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, strLinkAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        msgResponse.setText(response);
                        hideProgressDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        msgResponse.setText(error.toString());
                        hideProgressDialog();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                JSONObject resJson = new JSONObject();

                Map<String, String> params = new HashMap<>();

                SQLiteDatabase db = new clsMainBL().getDb();
                mconfigDA _mconfigDA = new mconfigDA(db);
                String txtDomain = _mconfigDA.getDomainKalbeData(db);
                tDeviceInfoUserData dt = new tDeviceInfoUserDA(db).getData(db, 1);
                resJson.put("domain", txtDomain);
                resJson.put("username", "kdr.susanti");
                resJson.put("pass", "sanghiang");
                resJson.put("deviceid", "");
                resJson.put("imeiNumber", dt.get_txtImei());
                resJson.put("version", dt.get_txtVersion());
                resJson.put("device", dt.get_txtDevice());
                resJson.put("model", dt.get_txtModel());
                resJson.put("introle", "120");
                resJson.put("txtOutletCode", null);
                resJson.put("txtOutletName", null);
                resJson.put("txtBranchCode", null);

                params.put("Params", String.valueOf(resJson));
                db.close();
                return params;
            }
        };
        queue.add(postRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPostLogin:
                makeStringReq();
                break;
        }

    }

}
