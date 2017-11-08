package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.trackingLocationData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tUserLoginDA;
import library.spgmobile.dal.trackingLocationDA;

/**
 * Created by Rian Andrivani on 5/15/2017.
 */

public class trackingLocationBL extends clsMainBL {
    //SQLiteDatabase db = getDb();

    public void SaveDataLocation(List<trackingLocationData> ListData) {
        SQLiteDatabase _db = getDb();
        trackingLocationDA _trackingLocationDA = new trackingLocationDA(_db);
        for (trackingLocationData data:ListData){
            _trackingLocationDA.SaveDataTrackingLocation(_db, data);
        }
        _db.close();
    }

    public void SaveDataTrackingLocation(trackingLocationData dt) {
        SQLiteDatabase _db = getDb();
        trackingLocationDA _trackingLocationDA = new trackingLocationDA(_db);
        _trackingLocationDA.SaveDataDownloadTrackingLocation(_db, dt);
        _db.close();
    }

    public List<trackingLocationData> getAllDataTrackingLocation() {
        SQLiteDatabase _db = getDb();
        trackingLocationDA _trackingLocationDA = new trackingLocationDA(_db);
        List<trackingLocationData> dt = _trackingLocationDA.getAllData(_db);
        _db.close();
        return dt;
    }

    public List<trackingLocationData> getAllLastData() {
        SQLiteDatabase _db = getDb();
        trackingLocationDA _trackingLocationDA = new trackingLocationDA(_db);
        List<trackingLocationData> dt = _trackingLocationDA.getAllLastData(_db);
        _db.close();
        return dt;
    }

    public org.json.simple.JSONArray DownloadTrackingLocation(String versionName) throws Exception{
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);
        String strVAl2 = "";
        mconfigData dataApi = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVAl2 = dataApi.get_txtValue();
        if (dataApi.get_txtValue() == ""){
            strVAl2 = dataApi.get_txtDefaultValue();
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateNow = dateFormat.format(date);

        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtLinkAPI = new linkAPI();
        String txtMethod = "GetDataTrackingLocation";
        JSONObject resJson = new JSONObject();
        dtLinkAPI.set_txtMethod(txtMethod);
        dtLinkAPI.set_txtParam("|" + _dataUserLogin.get_TxtEmpId() + "|" + dateNow);
        dtLinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtLinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtLinkAPI.QueryString(strVAl2);
        String JsonData = _help.pushtData(strLinkAPI, dtLinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));
        org.json.simple.JSONArray jsonArray = _help.ResultJsonArray(JsonData);
        _db.close();
        return jsonArray;

    }

    public int getContactCount() {
        SQLiteDatabase db = getDb();
        trackingLocationDA _trackingLocationDA = new trackingLocationDA(db);
        int intCount=_trackingLocationDA.getContactsCount(db);
        db.close();
        return intCount;
    }
}
