package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mCounterNumberData;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.common.tKemasanRusakHeaderData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.enumCounterData;
import library.spgmobile.dal.mCounterNumberDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tDeviceInfoUserDA;
import library.spgmobile.dal.tKemasanRusakHeaderDA;
import library.spgmobile.dal.tSalesProductQuantityHeaderDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by aan.junianto on 10/10/2017.
 */

public class tKemasanRusakHeaderBL extends clsMainBL{
    public List<tKemasanRusakHeaderData> getAllHeader() {
        SQLiteDatabase _db = getDb();
        tKemasanRusakHeaderDA _tKemasanRusakHeaderDA = new tKemasanRusakHeaderDA(_db);
        List<tKemasanRusakHeaderData> dt = _tKemasanRusakHeaderDA.getAllData(_db);
        _db.close();
        return dt;
    }

    public org.json.simple.JSONArray DownloadTransactionKemasanRusak(String versionName) throws Exception{
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
        String txtMethod = "GetDataTransactionKemasanRusak";
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

    public org.json.simple.JSONArray DownloadNOKemasanRusak(String versionName, String txtUserId, String txtEmpId) throws ParseException {
        SQLiteDatabase _db = getDb();
        tUserLoginDA  _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);
        org.json.simple.JSONArray jsonArray = new org.json.simple.JSONArray();
        // tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
        tDeviceInfoUserData deviceInfoUserData = new tDeviceInfoUserDA(_db).getData(_db, 1);
        String txtDomain = _mconfigDA.getDomainKalbeData(_db);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("txtUserId", txtUserId);
        linkAPI dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod("GetDataNoKemasanRusak");
        dtlinkAPI.set_txtParam("");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtlinkAPI.QueryString(getLinkAPI());
        APIData dtAPIDATA = new APIData();
        clsHelper _clsHelper = new clsHelper();
        String jsonData = _clsHelper.pushtData(strLinkAPI, String.valueOf(jsonObject), Integer.valueOf(getBackGroundServiceOnline()));
        jsonArray = _clsHelper.ResultJsonArray(jsonData);
        Iterator iterator = jsonArray.iterator();
        Boolean flag = true;
        String ErrorMess = "";
        mCounterNumberDA _mCounterNumberDA = new mCounterNumberDA(_db);
        while (iterator.hasNext()){
            JSONObject innerObj = (JSONObject)iterator.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
                mCounterNumberData data = new mCounterNumberData();
                data.set_intId(enumCounterData.NoKRS.getidCounterData());
                data.set_txtDeskripsi((String) innerObj.get("_pstrMethodRequest"));
                data.set_txtName((String) innerObj.get("_pstrMethodRequest"));
                data.set_txtValue((String) innerObj.get("_pstrArgument"));
                _mCounterNumberDA.SaveDataMConfig(db, data);
            } else {
                flag = false;
                ErrorMess = (String) innerObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
        _db.close();
        return jsonArray;
    }

    public void SaveData(tKemasanRusakHeaderData dt) {
        SQLiteDatabase _db = getDb();
        tKemasanRusakHeaderDA _tKemasanRusakHeaderDA = new tKemasanRusakHeaderDA(_db);
        _tKemasanRusakHeaderDA.SaveData(_db, dt);
        _db.close();
    }

    public List<tKemasanRusakHeaderData> getAllHeaderByOutletCode(String code){
        SQLiteDatabase _db = getDb();
        tKemasanRusakHeaderDA _tKemasanRusakHeaderDA = new tKemasanRusakHeaderDA(_db);
        List<tKemasanRusakHeaderData> dt;
        if(code.equals("ALLOUTLET")){
            dt = _tKemasanRusakHeaderDA.getAllData(_db);
        } else {
            dt = _tKemasanRusakHeaderDA.getAllDataByOutletCode(_db,code);
        }

        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }
    public List<tKemasanRusakHeaderData> getLastData() {
        SQLiteDatabase _db = getDb();
        tKemasanRusakHeaderDA _tKemasanRusakHeaderDA = new tKemasanRusakHeaderDA(_db);
        List<tKemasanRusakHeaderData> dt = _tKemasanRusakHeaderDA.getLastData(_db);
        _db.close();
        return dt;
    }
    public List<tKemasanRusakHeaderData> getDataByNo(String id){
        SQLiteDatabase _db = getDb();
        List<tKemasanRusakHeaderData> dtDetail = new tKemasanRusakHeaderDA(_db).getDataByNo(_db, id);
        _db.close();
        return dtDetail;
    }
}
