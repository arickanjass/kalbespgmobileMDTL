package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tActivityMobileData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tActivityDA;
import library.spgmobile.dal.tActivityMobileDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by rhezaTesar on 7/20/2017.
 */

public class tActivityMobileBL extends clsMainBL{
    public JSONArray DownloadActivityV2(String versionName) throws Exception {
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);

        String strVal2 = "";
        mconfigData dataAPI = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        //ambil version dari webservices
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtlinkAPI = new linkAPI();
//		String txtMethod = "GetActivity";
        String txtMethod = "GetDataTActivityMobileNew";
        JSONObject resJson = new JSONObject();
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam("||||||" + _dataUserLogin.get_TxtEmpId());
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);

        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.pushtData(strLinkAPI, dtlinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));

//		String strLinkAPI= dtlinkAPI.QueryString(strVal2);
//		String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));

        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);

        _db.close();
        return JsonArray;
    }

    public int getCountActivity(){
        SQLiteDatabase _db=getDb();

        tActivityMobileDA _tActivityDA = new tActivityMobileDA(_db);
        List<tActivityMobileData> dt = _tActivityDA.getAllData(_db);

        return dt.size();
    }

    public void saveData(List<tActivityMobileData> Listdata){
        SQLiteDatabase db=getDb();
        tActivityMobileDA _tActivityDA = new tActivityMobileDA(db);
        for(tActivityMobileData data:Listdata){
            _tActivityDA.SaveDatatActivityData (db, data);
        }
    }

    public List<tActivityMobileData> getDataNew(){
        SQLiteDatabase db=getDb();
        tActivityMobileDA _tActivityDA=new tActivityMobileDA(db);
        List<tActivityMobileData> listData=_tActivityDA.getAllDataNew(db);
        return listData;
    }

    public tActivityMobileData getDataByBitActive(){
        SQLiteDatabase db=getDb();
        tActivityMobileDA _tActivityDA=new tActivityMobileDA(db);
        tActivityMobileData listData=_tActivityDA.getAllDataByBitActive(db);
        return listData;
    }

    public List<tActivityMobileData> getAllData(){
        SQLiteDatabase db=getDb();
        tActivityMobileDA _tActivityDA=new tActivityMobileDA(db);
        List<tActivityMobileData> listData=_tActivityDA.getAllData(db);
        return listData;
    }

    public List<tActivityMobileData> getAllDataByOutletCode(String outletcode){
        SQLiteDatabase _db = getDb();
        tActivityMobileDA _tActivityDA=new tActivityMobileDA(db);
        List<tActivityMobileData> dt;
        if(outletcode.equals("ALLOUTLET")){
            dt = _tActivityDA.getAllData(_db);
        } else {
            dt = _tActivityDA.getAllDataByOutletCode(_db,outletcode);
        }

        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt;
    }

    public List<tActivityMobileData> getAllDataByIntSyc(String val){
        SQLiteDatabase _db =getDb();
        tActivityMobileDA _tActivityDA = new tActivityMobileDA(_db);
        List<tActivityMobileData> dt = _tActivityDA.getAllDataByIntSyc(_db,val);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public List<tActivityMobileData> getAllDataByIntSycAndOutlet(String val, String outlet){
        SQLiteDatabase _db =getDb();
        tActivityMobileDA _tActivityDA = new tActivityMobileDA(_db);
        List<tActivityMobileData> dt = _tActivityDA.getAllDataByIntSycAndOutlet(_db,val, outlet);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }
}
