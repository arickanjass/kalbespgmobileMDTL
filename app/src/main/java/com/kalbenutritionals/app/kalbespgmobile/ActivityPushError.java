package com.kalbenutritionals.app.kalbespgmobile;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import bl.clsHelperBL;
import bl.tLogErrorBL;
import library.spgmobile.common.APIData;
import library.spgmobile.common.clsPushData;
import library.spgmobile.common.dataJson;
import library.spgmobile.common.tLogErrorData;
import library.spgmobile.dal.clsHardCode;

/**
 * Created by Robert on 13/04/2017.
 */

public class ActivityPushError extends clsMainActivity {
    View v;
    ProgressBar progressBar;
    TextView txtPercentage;
    Button btnPush;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_push_error);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtPercentage = (TextView) findViewById(R.id.txtPercentage);
        btnPush = (Button) findViewById(R.id.btn_push_err);
        btnPush.setEnabled(true);
        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPush.setEnabled(false);
                btnPush.setBackgroundColor(Color.GREEN);
                AsyncCallRole asyncCallRole = new AsyncCallRole();
                asyncCallRole.execute();
            }
        });

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Push Error");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class AsyncCallRole extends AsyncTask<String, Integer, List<tLogErrorData>> {

        Boolean result = false;
        clsHardCode _path = new clsHardCode();

        @Override
        protected List<tLogErrorData> doInBackground(String... params) {
            List<dataJson> roledata = new ArrayList<dataJson>();
            clsPushData dtJson = new clsHelperBL().pushDataError();
            dataJson dtdataJson = new dataJson();
//            if(dtJson!=null){
            String versionName = "";
            try {
                versionName = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
//                    versionName = new clsMainActivity().getApplicationContext().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            try {





                HashMap<String, String> FileUpload = new HashMap<String, String>();
                for (tLogErrorData dt : dtJson.getDtdataJson().getListOftErrorLogData()) {
                    File file = new File(_path.txtPathApp + dt.get_txtFileName());
                    if (file.exists()) {
                        FileUpload.put(dt.get_txtFileName(), _path.txtPathApp + dt.get_txtFileName());
                    }
                }

                JSONArray Jresult = new clsHelperBL().callPushErrorReturnJson(versionName, dtJson.getDtdataJson().txtJSON().toString(), FileUpload);
//                    JSONArray Jresult = null;
                new clsHelperBL().saveDataPush(dtJson.getDtdataJson(), Jresult);

                APIData dtAPIDATA = new APIData();
                Iterator i = Jresult.iterator();
                boolean validPush = false;
                while (i.hasNext()) {
                    org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
                    int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
                    if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                        validPush = true;
                        result = true;
                    } else {
                        validPush = false;
                        break;
                    }
                }

                dtdataJson.setIntResult("1");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                dtdataJson.setIntResult("0");
                dtdataJson.setTxtMessage("Please Check Your Connection !!!");
            }
//            }
//            else
//            {
//                dtdataJson.setIntResult("0");
//                dtdataJson.setTxtMessage("No Data");
//            }
            roledata.add(dtdataJson);
//            return uploadFile(params[0]);
            return null;
        }
        private ProgressDialog Dialog = new ProgressDialog(getApplicationContext());

        @Override
        protected void onPostExecute(List<tLogErrorData> tErrorLogDatas) {
            /*SQLiteDatabase db = new clsMainBL().getDb();
            tLogErrorDA _tLogErrorDA = new tLogErrorDA(db);
            _tLogErrorDA.DropTable(db);
            _tLogErrorDA = new tLogErrorDA(db);
            super.onPostExecute(tErrorLogDatas);*/

            if (result) {
                new tLogErrorBL().deleteFileLogFromDevice();
                finish();
                Intent intent = new Intent(ActivityPushError.this, Splash.class);
                startActivity(intent);
                new clsMainActivity().showCustomToast(getApplicationContext(), "Success", true);
            } else {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // Making progress bar visible
            progressBar.setVisibility(View.VISIBLE);

            // updating progress bar value
            progressBar.setProgress(values[0]);

            // updating percentage value
            txtPercentage.setText(String.valueOf(values[0]) + "%");

            btnPush.setVisibility(View.GONE);
        }

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            showCustomToast(ActivityPushError.this, new clsHardCode().txtMessCancelRequest, false);
        }

    }
}

