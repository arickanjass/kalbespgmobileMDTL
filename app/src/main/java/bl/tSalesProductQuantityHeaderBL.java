package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import library.salesforce.common.APIData;
import library.salesforce.common.clsHelper;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mCounterNumberData;
import library.salesforce.common.mconfigData;
import library.salesforce.common.tDeviceInfoUserData;
import library.salesforce.common.tSalesProductQuantityHeaderData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.enumCounterData;
import library.salesforce.dal.mCounterNumberDA;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tDeviceInfoUserDA;
import library.salesforce.dal.tSalesProductQuantityHeaderDA;
import library.salesforce.dal.tUserLoginDA;

/**
 * Created by Rian Andrivani on 3/23/2017.
 */

public class tSalesProductQuantityHeaderBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveData(tSalesProductQuantityHeaderData dt) {
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityHeaderDA _tSalesQuantityDA = new tSalesProductQuantityHeaderDA(_db);
        _tSalesQuantityDA.SaveDataSalesProductQuantityData(_db, dt);
        _db.close();
    }

    public void SaveData2(List<tSalesProductQuantityHeaderData> Listdata) {
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityHeaderDA _tSalesQuantityDA = new tSalesProductQuantityHeaderDA(_db);
        for (tSalesProductQuantityHeaderData data:Listdata){
            _tSalesQuantityDA.SaveDataSalesProductQuantityData(_db, data);
        }
        _db.close();
    }

    public List<tSalesProductQuantityHeaderData> getAllData2(){
        SQLiteDatabase db=getDb();
        tSalesProductQuantityHeaderDA _tSalesProductQuantityDA=new tSalesProductQuantityHeaderDA(db);
        List<tSalesProductQuantityHeaderData> listData=_tSalesProductQuantityDA.getAllData2(db);
        db.close();
        return listData;
    }

    public List<tSalesProductQuantityHeaderData> getAllSalesQuantityHeader() {
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityHeaderDA _tSalesProductQuantityDA = new tSalesProductQuantityHeaderDA(_db);
        List<tSalesProductQuantityHeaderData> dt = _tSalesProductQuantityDA.getAllData(_db);
        _db.close();
        return dt;
    }

    public List<tSalesProductQuantityHeaderData> getAllSalesQuantityHeaderByOutlet(String outletcode) {
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityHeaderDA _tSalesQuantityHeaderDA = new tSalesProductQuantityHeaderDA(_db);
        List<tSalesProductQuantityHeaderData> dt = _tSalesQuantityHeaderDA.getAllDataByOutletCode(_db, outletcode);
        _db.close();
        return dt;
    }

    public List<tSalesProductQuantityHeaderData> getLastData() {
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityHeaderDA _tSalesQuantityHeaderDA = new tSalesProductQuantityHeaderDA(_db);
        List<tSalesProductQuantityHeaderData> dt = _tSalesQuantityHeaderDA.getLastData(_db);
        _db.close();
        return dt;
    }

    public List<tSalesProductQuantityHeaderData> getAllDataByIntSyc(String val){
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityHeaderDA _tSalesQuantityHeaderDA = new tSalesProductQuantityHeaderDA(_db);
        List<tSalesProductQuantityHeaderData> dt = _tSalesQuantityHeaderDA.getAllDataByIntSyc(_db, val);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        _db.close();
        return dt ;
    }

    public List<tSalesProductQuantityHeaderData> getAllDataByIntSycAndOutlet(String val, String outlet){
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityHeaderDA _tSalesQuantityHeaderDA = new tSalesProductQuantityHeaderDA(_db);
        List<tSalesProductQuantityHeaderData> dt = _tSalesQuantityHeaderDA.getAllDataByIntSycAndOutlet(_db,val, outlet);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        _db.close();
        return dt ;
    }

    public List<tSalesProductQuantityHeaderData> getAllSalesProductHeaderByOutletCode(String code){
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityHeaderDA _tSalesQuantityHeaderDA = new tSalesProductQuantityHeaderDA(_db);
        List<tSalesProductQuantityHeaderData> dt = _tSalesQuantityHeaderDA.getAllDataByOutletCode(_db, code);

        _db.close();
        return dt ;
    }

    public List<tSalesProductQuantityHeaderData> getDataByNoQuantityStock(String id){
        SQLiteDatabase _db = getDb();
        List<tSalesProductQuantityHeaderData> dtDetail = new tSalesProductQuantityHeaderDA(_db).getDataByQuantityStock(_db, id);
        _db.close();
        return dtDetail;
    }

    public List<tSalesProductQuantityHeaderData> getAllSalesBydtDateCheckin(String date){
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityHeaderDA _tSalesQuantityHeaderDA = new tSalesProductQuantityHeaderDA(_db);
        List<tSalesProductQuantityHeaderData> dt = _tSalesQuantityHeaderDA.getAllDataByOutletCode(_db, date);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        _db.close();
        return dt ;
    }

    public void generateQuantityStock(tSalesProductQuantityHeaderData dt) {
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityHeaderDA _tSalesQuantityHeaderDA = new tSalesProductQuantityHeaderDA(_db);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

//		mCounterNumberDA _mCountNumberDA = new mCounterNumberDA(db);
//		mCounterNumberData _ListOfmCounterNumberData = _mCountNumberDA.getData(db, 2);
        String dtDate = dateFormat.format(cal.getTime());
        String[] split = dtDate.split("-");
        String yy = split[0];
        String mm = split[1];
        String dd = split[2];

        String txtNoQuantityStockCode = new mCounterNumberBL().getData(enumCounterData.NoQuantityStock);

        List<tSalesProductQuantityHeaderData> dttas = getLastData();
        _db.close();
        String noQuantityStock = null;
    }

    public org.json.simple.JSONArray DownloadNOQuantityStock(String versionName, String txtUserId, String txtEmpId) throws ParseException {
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
        dtlinkAPI.set_txtMethod("GetDataNoQuantityStock");
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
                data.set_intId(enumCounterData.NoQuantityStock.getidCounterData());
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

    public org.json.simple.JSONArray DownloadTransactionQuantityStock(String versionName) throws Exception{
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
        String txtMethod = "GetDataTransactionQuantityStock";
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

    public void deleteData(tSalesProductQuantityHeaderData dt) {
        SQLiteDatabase _db=getDb();
        new tSalesProductQuantityHeaderDA(_db).deleteByQuantityStock(_db, dt.get_txtQuantityStock());
        _db.close();
    }
}
