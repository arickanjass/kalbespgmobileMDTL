package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mCounterNumberData;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.common.tOverStockHeaderData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.enumCounterData;
import library.spgmobile.dal.mCounterNumberDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tDeviceInfoUserDA;
import library.spgmobile.dal.tOverStockHeaderDA;
import library.spgmobile.dal.tSalesProductQuantityHeaderDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by aan.junianto on 15/09/2017.
 */

public class tOverStockHeaderBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveData(tOverStockHeaderData dt) {
        SQLiteDatabase _db = getDb();
        tOverStockHeaderDA _tOverStockHeaderDA = new tOverStockHeaderDA(_db);
        _tOverStockHeaderDA.SaveDataOverStockData(_db, dt);
        _db.close();
    }

    public void SaveData2(List<tOverStockHeaderData> Listdata) {
        SQLiteDatabase _db = getDb();
        tOverStockHeaderDA _tOverStockHeaderDA = new tOverStockHeaderDA(_db);
        for (tOverStockHeaderData data:Listdata){
            _tOverStockHeaderDA.SaveDataOverStockData(_db, data);
        }
        _db.close();
    }

    public int getCountQStockStatusSubmit(String code) {
        SQLiteDatabase _db = getDb();
        int count = new tOverStockHeaderDA(_db).countQStockStatusSubmit(_db, code);
        return count;
    }

    public int countQStockPush( String code) {
        SQLiteDatabase _db = getDb();
        int count = new tOverStockHeaderDA(_db).countQStockPush(_db, code);
        return count;
    }

    public List<tOverStockHeaderData> getAllData2(){
        SQLiteDatabase db=getDb();
        tOverStockHeaderDA _tOverStockHeaderDA = new tOverStockHeaderDA(db);
        List<tOverStockHeaderData> listData=_tOverStockHeaderDA.getAllData2(db);
        db.close();
        return listData;
    }

    public List<tOverStockHeaderData> getAllOverStockHeader() {
        SQLiteDatabase _db = getDb();
        tOverStockHeaderDA _tOverStockHeaderDA = new tOverStockHeaderDA(_db);
        List<tOverStockHeaderData> dt = _tOverStockHeaderDA.getAllData(_db);
        _db.close();
        return dt;
    }

    public List<tOverStockHeaderData> getAllOverStockHeaderByOutlet(String outletcode) {
        SQLiteDatabase _db = getDb();
        tOverStockHeaderDA _tOverStockHeaderDA = new tOverStockHeaderDA(_db);
        List<tOverStockHeaderData> dt = _tOverStockHeaderDA.getAllDataByOutletCode(_db, outletcode);
        _db.close();
        return dt;
    }

    public List<tOverStockHeaderData> getLastData() {
        SQLiteDatabase _db = getDb();
        tOverStockHeaderDA _tOverStockHeaderDA = new tOverStockHeaderDA(_db);
        List<tOverStockHeaderData> dt = _tOverStockHeaderDA.getLastData(_db);
        _db.close();
        return dt;
    }

    public List<tOverStockHeaderData> getAllDataByIntSyc(String val){
        SQLiteDatabase _db = getDb();
        tOverStockHeaderDA _tOverStockHeaderDA = new tOverStockHeaderDA(_db);
        List<tOverStockHeaderData> dt = _tOverStockHeaderDA.getAllDataByIntSyc(_db, val);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        _db.close();
        return dt ;
    }

    public List<tOverStockHeaderData> getAllDataByIntSycAndOutlet(String val, String outlet){
        SQLiteDatabase _db = getDb();
        tOverStockHeaderDA _tOverStockHeaderDA = new tOverStockHeaderDA(_db);
        List<tOverStockHeaderData> dt = _tOverStockHeaderDA.getAllDataByIntSycAndOutlet(_db,val, outlet);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        _db.close();
        return dt ;
    }

    public List<tOverStockHeaderData> getAllOverStockHeaderByOutletCode(String code){
        SQLiteDatabase _db = getDb();
        tOverStockHeaderDA _tOverStockHeaderDA = new tOverStockHeaderDA(_db);
        List<tOverStockHeaderData> dt;
        if(code.equals("ALLOUTLET")){
            dt = _tOverStockHeaderDA.getAllData(_db);
        } else {
            dt = _tOverStockHeaderDA.getAllDataByOutletCode(_db,code);
        }

        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public List<tOverStockHeaderData> getDataByNoOverStock(String id){
        SQLiteDatabase _db = getDb();
        List<tOverStockHeaderData> dtDetail = new tOverStockHeaderDA(_db).getDataByTxtOverStock(_db, id);
        _db.close();
        return dtDetail;
    }

    public List<tOverStockHeaderData> getAllSalesBydtDateCheckin(String date){
        SQLiteDatabase _db = getDb();
        tOverStockHeaderDA _tOverStockHeaderDA = new tOverStockHeaderDA(_db);
        List<tOverStockHeaderData> dt = _tOverStockHeaderDA.getAllDataByOutletCode(_db, date);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        _db.close();
        return dt ;
    }

    public void generateNoOverStock(tOverStockHeaderData dt) {
        SQLiteDatabase _db = getDb();
        tOverStockHeaderDA _tOverStockHeaderDA = new tOverStockHeaderDA(_db);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

//		mCounterNumberDA _mCountNumberDA = new mCounterNumberDA(db);
//		mCounterNumberData _ListOfmCounterNumberData = _mCountNumberDA.getData(db, 2);
        String dtDate = dateFormat.format(cal.getTime());
        String[] split = dtDate.split("-");
        String yy = split[0];
        String mm = split[1];
        String dd = split[2];

        String txtNoQuantityStockCode = new mCounterNumberBL().getData(enumCounterData.NoOS);

        List<tOverStockHeaderData> dttas = getLastData();
        _db.close();
        String noQuantityStock = null;
    }

    public org.json.simple.JSONArray DownloadNOOverStock(String versionName, String txtUserId, String txtEmpId) throws ParseException {
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);
        org.json.simple.JSONArray jsonArray = new org.json.simple.JSONArray();
        // tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
        tDeviceInfoUserData deviceInfoUserData = new tDeviceInfoUserDA(_db).getData(_db, 1);
        String txtDomain = _mconfigDA.getDomainKalbeData(_db);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("txtUserId", txtUserId);
        linkAPI dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod("GetDataNoOverStock");
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
                data.set_intId(enumCounterData.NoOS.getidCounterData());
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

    public org.json.simple.JSONArray DownloadTransactionOverStock(String versionName) throws Exception{
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
        String txtMethod = "GetDataTransactionOverStock";
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

    public void deleteData(tOverStockHeaderData dt) {
        SQLiteDatabase _db=getDb();
        new tOverStockHeaderDA(_db).deleteByQuantityStock(_db, dt.get_txtOverStock());
        _db.close();
    }
}
