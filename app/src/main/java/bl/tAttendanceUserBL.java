package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tAttendanceUserData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tAbsenUserDA;
import library.spgmobile.dal.tAttendanceUserDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by aan.junianto on 22/08/2017.
 */

public class tAttendanceUserBL extends clsMainBL {
    public void saveData(tAttendanceUserData data){
        SQLiteDatabase db=getDb();
        tAttendanceUserDA _tAttendanceUserDA=new tAttendanceUserDA(db);
        _tAttendanceUserDA.SaveDatatAbsenUserData(db, data);
        db.close();
    }
    public tAttendanceUserData getDataCheckInActive(){
        SQLiteDatabase db=getDb();
        tAttendanceUserDA _tAttendanceUserDA=new tAttendanceUserDA(db);
        tAttendanceUserData dt=new tAttendanceUserData();
        dt=_tAttendanceUserDA.getDataCheckInActive(db);
        db.close();
        return dt;
    }
    public int  getContactsCount(){
        SQLiteDatabase db=getDb();
        tAttendanceUserDA _tAttendanceUserDA=new tAttendanceUserDA(db);
        int intCount=_tAttendanceUserDA.getContactsCount(db);
        db.close();
        return intCount;
    }
    public List<tAttendanceUserData> getAllDataActive(){
        SQLiteDatabase db=getDb();
        tAttendanceUserDA _tAttendanceUserDA=new tAttendanceUserDA(db);
        List<tAttendanceUserData> ListData=new ArrayList<tAttendanceUserData>();
        ListData=_tAttendanceUserDA.getAllDataActive(db);
        db.close();
        return ListData;
    }
    public JSONArray DownloadAttendance(String versionName) throws Exception {
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);

        String strVal2 = "";
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        //ambil version dari webservices
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtlinkAPI = new linkAPI();
        String txtMethod = "GetDataAttendanceFPE_mobile";
        JSONObject resJson = new JSONObject();
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_txtUserId());
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);

//		String strLinkAPI = dtlinkAPI.QueryString(strVal2);
//		String JsonData = _help.pushtData(strLinkAPI, dtlinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));

        String strLinkAPI= dtlinkAPI.QueryString(strVal2);
        String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));

        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);

        _db.close();
        return JsonArray;
    }
    public void checkOutSystem(String id, String time) {
        SQLiteDatabase db = getDb();
        tAttendanceUserDA _tAttendanceUserDA=new tAttendanceUserDA(db);
        _tAttendanceUserDA.checkoutSystem(db, id, time);
        db.close();
    }
}
