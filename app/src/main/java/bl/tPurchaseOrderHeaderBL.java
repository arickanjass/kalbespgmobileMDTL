package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
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
import library.spgmobile.common.tPurchaseOrderHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.enumCounterData;
import library.spgmobile.dal.mCounterNumberDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tDeviceInfoUserDA;
import library.spgmobile.dal.tPurchaseOrderHeaderDA;
import library.spgmobile.dal.tUserLoginDA;

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
        List<tPurchaseOrderHeaderData> dt;
        if(code.equals("ALLOUTLET")){
            dt = _tPurchaseOrderHeaderDA.getAllData(_db);
        } else {
            dt = _tPurchaseOrderHeaderDA.getAllDataByOutletCode(_db,code);
        }

        if(dt == null){
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
        String txtNoPOCode = new mCounterNumberBL().getData(enumCounterData.NoPurchaseOrder);
        List<tPurchaseOrderHeaderData> dttas = getLastData();
        String noPO = null;
    }

    public JSONArray DownloadNOPO(String versionName, String txtUserId, String txtEmpId) throws ParseException{
        SQLiteDatabase _db = getDb();
        tUserLoginDA  _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);
        JSONArray jsonArray = new JSONArray();
       // tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
        tDeviceInfoUserData deviceInfoUserData = new tDeviceInfoUserDA(_db).getData(_db, 1);
        String txtDomain = _mconfigDA.getDomainKalbeData(_db);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("txtUserId", txtUserId);
        linkAPI dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod("GetDataNoPurchaseOrderMDTL");
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
                data.set_intId(enumCounterData.NoPurchaseOrder.getidCounterData());
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

    public JSONArray DownloadTransactionPO(String versionName) throws Exception{
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
        String txtMethod = "GetDataTransactionPurchaseOrder"; 
        JSONObject resJson = new JSONObject();
        dtLinkAPI.set_txtMethod(txtMethod);
        dtLinkAPI.set_txtParam("|" + _dataUserLogin.get_TxtEmpId() + "|" + dateNow);
        dtLinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtLinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtLinkAPI.QueryString(strVAl2);
        String JsonData = _help.pushtData(strLinkAPI, dtLinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));
        JSONArray jsonArray = _help.ResultJsonArray(JsonData);
        _db.close();
        return jsonArray;

    }
}
