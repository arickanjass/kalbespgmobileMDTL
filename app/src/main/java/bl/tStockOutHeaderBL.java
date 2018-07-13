package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tOverStockHeaderData;
import library.spgmobile.common.tStockOutHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tOverStockHeaderDA;
import library.spgmobile.dal.tStockOutHeaderDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by aan.junianto on 5/07/2018.
 */

public class tStockOutHeaderBL extends clsMainBL {
    public void SaveData(tStockOutHeaderData dt) {
        SQLiteDatabase _db = getDb();
        tStockOutHeaderDA _tOverStockHeaderDA = new tStockOutHeaderDA(_db);
        _tOverStockHeaderDA.SaveDataOverStockData(_db, dt);
        _db.close();
    }
    public List<tStockOutHeaderData> getAllOverStockHeaderByOutletCode(String code){
        SQLiteDatabase _db = getDb();
        tStockOutHeaderDA _tOverStockHeaderDA = new tStockOutHeaderDA(_db);
        List<tStockOutHeaderData> dt;
        if(code.equals("ALLOUTLET")){
            dt = _tOverStockHeaderDA.getAllData(_db);
        } else {
            dt = _tOverStockHeaderDA.getAllDataByOutletCode(_db,code);
        }

        if(dt == null){
            dt = new ArrayList<>(0);
        }
        _db.close();
        return dt ;
    }
    public List<tStockOutHeaderData> getDataByNoOverStock(String id){
        SQLiteDatabase _db = getDb();
        List<tStockOutHeaderData> dtDetail = new tStockOutHeaderDA(_db).getDataByTxtOverStock(_db, id);
        _db.close();
        return dtDetail;
    }
    public void SaveData2(List<tStockOutHeaderData> Listdata) {
        SQLiteDatabase _db = getDb();
        tStockOutHeaderDA _tOverStockHeaderDA = new tStockOutHeaderDA(_db);
        for (tStockOutHeaderData data:Listdata){
            _tOverStockHeaderDA.SaveDataOverStockData(_db, data);
        }
        _db.close();
    }
    public org.json.simple.JSONArray DownloadTransactionStockOut(String versionName) throws Exception{
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
        String txtMethod = "GetDataTransactionStockOut";
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
    public List<tStockOutHeaderData> getAllOverStockHeader() {
        SQLiteDatabase _db = getDb();
        tStockOutHeaderDA _tOverStockHeaderDA = new tStockOutHeaderDA(_db);
        List<tStockOutHeaderData> dt = _tOverStockHeaderDA.getAllData(_db);
        _db.close();
        return dt;
    }

    public List<tStockOutHeaderData> getLastData() {
        SQLiteDatabase _db = getDb();
        tStockOutHeaderDA _tOverStockHeaderDA = new tStockOutHeaderDA(_db);
        List<tStockOutHeaderData> dt = _tOverStockHeaderDA.getLastData(_db);
        _db.close();
        return dt;
    }

    public int getCountOStockStatusSubmit(String code) {
        SQLiteDatabase _db = getDb();
        int count = new tStockOutHeaderDA(_db).countOStockStatusSubmit(_db, code);
        _db.close();
        return count;
    }
    public int countOStockPush( String code) {
        SQLiteDatabase _db = getDb();
        int count = new tStockOutHeaderDA(_db).countOStockPush(_db, code);
        _db.close();
        return count;
    }
}
