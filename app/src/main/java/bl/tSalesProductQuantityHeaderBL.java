package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import library.salesforce.common.clsHelper;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mconfigData;
import library.salesforce.common.tSalesProductQuantityData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.enumCounterData;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tSalesProductQuantityDA;
import library.salesforce.dal.tUserLoginDA;

/**
 * Created by Rian Andrivani on 3/23/2017.
 */

public class tSalesProductQuantityHeaderBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveData(tSalesProductQuantityData dt) {
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityDA _tSalesQuantityDA = new tSalesProductQuantityDA(_db);
        _tSalesQuantityDA.SaveDataSalesProductQuantityData(_db, dt);
    }

    public List<tSalesProductQuantityData> getAllSalesQuantityHeader() {
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityDA _tSalesProductQuantityDA = new tSalesProductQuantityDA(_db);
        List<tSalesProductQuantityData> dt = _tSalesProductQuantityDA.getAllData(_db);
        return dt;
    }

    public List<tSalesProductQuantityData> getAllSalesQuantityHeaderByOutlet(String outletcode) {
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityDA _tSalesQuantityHeaderDA = new tSalesProductQuantityDA(_db);
        List<tSalesProductQuantityData> dt = _tSalesQuantityHeaderDA.getAllDataByOutletCode(_db, outletcode);
        return dt;
    }

    public List<tSalesProductQuantityData> getLastData() {
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityDA _tSalesQuantityHeaderDA = new tSalesProductQuantityDA(_db);
        List<tSalesProductQuantityData> dt = _tSalesQuantityHeaderDA.getLastData(_db);
        return dt;
    }

    public List<tSalesProductQuantityData> getAllDataByIntSyc(String val){
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityDA _tSalesQuantityHeaderDA = new tSalesProductQuantityDA(_db);
        List<tSalesProductQuantityData> dt = _tSalesQuantityHeaderDA.getAllDataByIntSyc(_db, val);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public List<tSalesProductQuantityData> getAllDataByIntSycAndOutlet(String val, String outlet){
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityDA _tSalesQuantityHeaderDA = new tSalesProductQuantityDA(_db);
        List<tSalesProductQuantityData> dt = _tSalesQuantityHeaderDA.getAllDataByIntSycAndOutlet(_db,val, outlet);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public List<tSalesProductQuantityData> getAllSalesProductHeaderByOutletCode(String code){
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityDA _tSalesQuantityHeaderDA = new tSalesProductQuantityDA(_db);
        List<tSalesProductQuantityData> dt = _tSalesQuantityHeaderDA.getAllDataByOutletCode(_db, code);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public List<tSalesProductQuantityData> getDataByNoSO(String id){
        SQLiteDatabase _db = getDb();
        List<tSalesProductQuantityData> dtDetail = new tSalesProductQuantityDA(_db).getDataByQuantityStock(_db, id);
        return dtDetail;
    }

    public List<tSalesProductQuantityData> getAllSalesBydtDateCheckin(String date){
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityDA _tSalesQuantityHeaderDA = new tSalesProductQuantityDA(_db);
        List<tSalesProductQuantityData> dt = _tSalesQuantityHeaderDA.getAllDataByOutletCode(_db, date);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public void generateQuantityStock(tSalesProductQuantityData dt) {
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityDA _tSalesQuantityHeaderDA = new tSalesProductQuantityDA(_db);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

//		mCounterNumberDA _mCountNumberDA = new mCounterNumberDA(db);
//		mCounterNumberData _ListOfmCounterNumberData = _mCountNumberDA.getData(db, 2);
        String dtDate = dateFormat.format(cal.getTime());
        String[] split = dtDate.split("-");
        String yy = split[0];
        String mm = split[1];
        String dd = split[2];

        String txtNoSoCode = new mCounterNumberBL().getData(enumCounterData.NoDataSO);

        List<tSalesProductQuantityData> dttas = getLastData();

        String QtyStock = null;
    }

    public org.json.simple.JSONArray DownloadQuantity(String versionName) throws Exception{
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA=new tUserLoginDA(_db);
        mconfigDA _mconfigDA =new mconfigDA(_db);

        String strVal2="";
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
        clsHelper _help =new clsHelper();
        linkAPI dtlinkAPI=new linkAPI();
        String txtMethod="GetDataTransactionQuantity";
        JSONObject resJson = new JSONObject();
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam("|" + _dataUserLogin.get_TxtEmpId() + "|" + datenow);
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);

        String strLinkAPI= dtlinkAPI.QueryString(strVal2);
        String JsonData= _help.pushtData(strLinkAPI, dtlinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));

        //String strLinkAPI= dtlinkAPI.QueryString(strVal2);
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));

        org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(JsonData);
        _db.close();
        return JsonArray;
    }

    public void deleteData(tSalesProductQuantityData dt) {
        SQLiteDatabase _db=getDb();
        new tSalesProductQuantityDA(_db).deleteByNoso(_db, dt.get_txtQuantityStock());
    }
}
