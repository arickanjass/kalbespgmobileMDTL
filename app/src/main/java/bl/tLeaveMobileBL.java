package bl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tLeaveMobileData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tLeaveMobileDA;
import library.spgmobile.dal.tUserLoginDA;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class tLeaveMobileBL extends clsMainBL {
    public void saveData(List<tLeaveMobileData> Listdata) {
        SQLiteDatabase db = getDb();
        tLeaveMobileDA _tLeaveMobileDA = new tLeaveMobileDA(db);
        for (tLeaveMobileData data : Listdata) {
            _tLeaveMobileDA.SaveDataMConfig(db, data);
        }
    }

    public List<tLeaveMobileData> getData(String id) {
        List<tLeaveMobileData> listData = new ArrayList<tLeaveMobileData>();
        tLeaveMobileDA _tLeaveMobileDA = new tLeaveMobileDA(db);
        if (id.equals("")) {
            listData = _tLeaveMobileDA.getAllData(db);
        } else {
            tLeaveMobileData data = new tLeaveMobileData();
            data = _tLeaveMobileDA.getData(db, id);
            listData.add(data);
        }
        return listData;
    }

    public List<tLeaveMobileData> GetAllData(){
        SQLiteDatabase db=getDb();
        tLeaveMobileDA _tLeaveMobileDA = new tLeaveMobileDA(db);
        return _tLeaveMobileDA.getAllData(db);
    }

    public List<tLeaveMobileData> getAllDataToPushData() {
        SQLiteDatabase db = getDb();
        tLeaveMobileDA _tLeaveMobileDA = new tLeaveMobileDA(db);
        List<tLeaveMobileData> ListData = new ArrayList<tLeaveMobileData>();
        ListData = _tLeaveMobileDA.getAllDataPushData(db);
        return ListData;
    }

    public int getContactsCount() {
        SQLiteDatabase db = getDb();
        tLeaveMobileDA _tLeaveMobileDA = new tLeaveMobileDA(db);
        return _tLeaveMobileDA.getContactsCount(db);
    }

    public JSONArray DownloadDataLeave(String versionName) throws Exception {
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);

        String strVal2 = "";
        mconfigData dataAPI = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String datenow = dateFormat.format(date);

        //ambil version dari webservices
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtlinkAPI = new linkAPI();
        String txtMethod = "GetDatatLeaveMobile";
        JSONObject resJson = new JSONObject();
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam(datenow + "|"+ _dataUserLogin.get_TxtEmpId() + "||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);

        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.pushtData(strLinkAPI, dtlinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));

        //String strLinkAPI= dtlinkAPI.QueryString(strVal2);
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));

        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        _db.close();
        return JsonArray;
    }
}
