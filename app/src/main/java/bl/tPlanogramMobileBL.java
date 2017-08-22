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
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tPlanogramMobileData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tSubTypeActivityData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tActivityDA;
import library.spgmobile.dal.tPlanogramMobileDA;
import library.spgmobile.dal.tSalesProductQuantityHeaderDA;
import library.spgmobile.dal.tSubTypeActivityDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by aan.junianto on 10/08/2017.
 */

public class tPlanogramMobileBL extends clsMainBL{
    public tPlanogramMobileData getDataByBitActive(){
        SQLiteDatabase db=getDb();
        tPlanogramMobileDA _tPlanogramMobileDA=new tPlanogramMobileDA(db);
        tPlanogramMobileData listData=_tPlanogramMobileDA.getAllDataByBitActive(db);
        return listData;
    }
    public void saveDataList(List<tPlanogramMobileData> Listdata){
        SQLiteDatabase db=getDb();
        tPlanogramMobileDA _tPlanogramMobileDA=new tPlanogramMobileDA(db);
        for(tPlanogramMobileData data:Listdata){
            _tPlanogramMobileDA.SaveDataPlanogram(db, data);
        }
    }
    public void saveData(tPlanogramMobileData _tPlanogramMobileData){
        SQLiteDatabase db=getDb();
        tPlanogramMobileDA _tPlanogramMobileDA=new tPlanogramMobileDA(db);
        _tPlanogramMobileDA.SaveDataPlanogram(db, _tPlanogramMobileData);
    }
    public List<tPlanogramMobileData> getAllPlanogramByOutletCode(String code){
        SQLiteDatabase _db = getDb();
        tPlanogramMobileDA _tPlanogramMobileDA = new tPlanogramMobileDA(_db);
        List<tPlanogramMobileData> dt;
        if(code.equals("ALLOUTLET")){
            dt = _tPlanogramMobileDA.getAllData(_db);
        } else {
            dt = _tPlanogramMobileDA.getAllDataByOutletCode(_db,code);
        }

        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }
    public List<tPlanogramMobileData> getAllData(){
        SQLiteDatabase _db = getDb();
        List<tPlanogramMobileData> dtDetail = new tPlanogramMobileDA(_db).getAll(_db);
        _db.close();
        return dtDetail;
    }
    public org.json.simple.JSONArray DownloadTransactionPlanogram(String versionName) throws Exception{
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);
        String strVAl2 = "";
        mconfigData dataApi = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVAl2 = dataApi.get_txtValue();
        if (dataApi.get_txtValue() == ""){
            strVAl2 = dataApi.get_txtDefaultValue();
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateNow = dateFormat.format(date);

        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtLinkAPI = new linkAPI();
        String txtMethod = "GetDataTransactionPlanogram";
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
}