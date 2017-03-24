package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
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
import library.salesforce.common.tPurchaseOrderHeaderData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.enumCounterData;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tPurchaseOrderHeaderDA;
import library.salesforce.dal.tUserLoginDA;

/**
 * Created by XSIS on 22/03/2017.
 */

public class tPurchaseOrderHeaderBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveData (tPurchaseOrderHeaderData dt){
        SQLiteDatabase _db = getDb();
        tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(_db);
        _tPurchaseOrderHeaderDA.SaveDatatPurchaseOrderHeaderData(_db, dt);
    }

    public List<tPurchaseOrderHeaderData> getAllPurchaseOrderHeader() {
        SQLiteDatabase _db = getDb();
        tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(_db);
        List<tPurchaseOrderHeaderData> dt = _tPurchaseOrderHeaderDA.getAllData(_db);
        return dt;
    }

    public List<tPurchaseOrderHeaderData> getAllPurchaseOrderHeaderByOutlet(String outletCode) {
        SQLiteDatabase _db = getDb();
        tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(_db);
        List<tPurchaseOrderHeaderData> dt = _tPurchaseOrderHeaderDA.getAllDataByOutletCode(_db, outletCode);
        return dt;
    }

    public List<tPurchaseOrderHeaderData> getLastData() {
        SQLiteDatabase _db = getDb();
        tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(_db);
        List<tPurchaseOrderHeaderData> dt = _tPurchaseOrderHeaderDA.getLastDataPO(_db);
        return dt;
    }

    public List<tPurchaseOrderHeaderData> getAllDataByIntSyc(String val) {
        SQLiteDatabase _db = getDb();
        tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(_db);
        List<tPurchaseOrderHeaderData> dt = _tPurchaseOrderHeaderDA.getAllDataByIntSync(_db, val);
        if (dt == null){
            dt = new ArrayList<>(0);
        }
        return dt;
    }

    public List<tPurchaseOrderHeaderData> getAllDataByIntSycAndOutlet(String val, String outlet) {
        SQLiteDatabase _db = getDb();
        tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(_db);
        List<tPurchaseOrderHeaderData> dt = _tPurchaseOrderHeaderDA.getAllDataByIntSyncAndOutlet(_db, val, outlet);
        if (dt == null){
            dt = new ArrayList<>(0);
        }
        return dt;
    }

    public List<tPurchaseOrderHeaderData> getAllPurchaseOrderHeaderByOutletCode(String code) {
        SQLiteDatabase _db = getDb();
        tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(_db);
        List<tPurchaseOrderHeaderData> dt = _tPurchaseOrderHeaderDA.getAllDataByOutletCode(_db, code);
        if (dt == null){
            dt = new ArrayList<>(0);
        }
        return dt;
    }

    public List<tPurchaseOrderHeaderData> getDataByNoOrder(String noPO) {
        SQLiteDatabase _db = getDb();
        tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(_db);
        List<tPurchaseOrderHeaderData> dt = _tPurchaseOrderHeaderDA.getAllDataByNoOrder(_db, noPO);
        if (dt == null){
            dt = new ArrayList<>(0);
        }
        return dt;
    }

    public List<tPurchaseOrderHeaderData> getAllPurchaseOrderBydtDateCheckin(String date) {
        SQLiteDatabase _db = getDb();
        tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(_db);
        List<tPurchaseOrderHeaderData> dt = _tPurchaseOrderHeaderDA.getAllDataByDateCheckin(_db, date);
        if (dt == null){
            dt = new ArrayList<>(0);
        }
        return dt;
    }

    public void generateNoPO(tPurchaseOrderHeaderData dt){
        SQLiteDatabase _db = getDb();
        tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(_db);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        String dtDate = dateFormat.format(calendar.getTime());
        String[] split = dtDate.split("-");
        String yy = split[0];
        String mm = split[1];
        String dd = split[2];
        String txtNoPOCode = new mCounterNumberBL().getData(enumCounterData.NoDataSO);
        List<tPurchaseOrderHeaderData> dttas = getLastData();
        String noPO = null;
    }

    public JSONArray DownloadPO(String versionName) throws Exception{
        SQLiteDatabase _db = getDb();
        tUserLoginDA  _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);
        String strVal2 = "";
        mconfigData dataAPI = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == ""){
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String dateNow = dateFormat.format(date);
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtlinkAPI = new linkAPI();
        String txtMethod = "GetDataTransactionPO";
        JSONObject resJson = new JSONObject();
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam("|"+_dataUserLogin.get_TxtEmpId()+"|"+dateNow);
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData= _help.pushtData(strLinkAPI, dtlinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));

        JSONArray jsonArray = _help.ResultJsonArray(JsonData);
        return jsonArray;
    }
}
