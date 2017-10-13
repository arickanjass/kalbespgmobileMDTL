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
import library.spgmobile.common.tPlanogramMobileData;
import library.spgmobile.common.tSalesProductHeaderData;
import library.spgmobile.common.tStockInHandHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.enumCounterData;
import library.spgmobile.dal.mCounterNumberDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tDeviceInfoUserDA;
import library.spgmobile.dal.tPlanogramMobileDA;
import library.spgmobile.dal.tSalesProductHeaderDA;
import library.spgmobile.dal.tStockInHandHeaderDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by aan.junianto on 23/08/2017.
 */

public class tStockInHandHeaderBL extends clsMainBL{
    SQLiteDatabase db=getDb();

    public void SaveData(tStockInHandHeaderData dt){
        SQLiteDatabase _db =getDb();
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        _tStockInHandHeaderDA.SaveDatatStockInHandHeaderData(_db, dt);
    }
    //    public List<tCustomerBaseData> getAllCustomerBase(){
//        SQLiteDatabase _db=getDb();
//
//        tCustomerBaseDA _tCustomerBaseDA = new tCustomerBaseDA(_db);
//        List<tCustomerBaseData> dt = _tCustomerBaseDA.getAllData(_db);
//
//        return dt;
//    }
    public List<tStockInHandHeaderData> getAllSalesProductHeader(){
        SQLiteDatabase _db =getDb();
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        List<tStockInHandHeaderData> dt = _tStockInHandHeaderDA.getAllData(_db);
        return dt ;
    }

    public List<tStockInHandHeaderData> getAllSalesProductHeaderByOutlet(String outletcode){
        SQLiteDatabase _db =getDb();
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        List<tStockInHandHeaderData> dt = _tStockInHandHeaderDA.getAllDataByOutletCode(_db, outletcode);
        return dt ;
    }

    public List<tStockInHandHeaderData> getLastData(){
        SQLiteDatabase _db =getDb();
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        List<tStockInHandHeaderData> dt = _tStockInHandHeaderDA.getLastData(_db);
        return dt ;
    }

    public List<tStockInHandHeaderData> getAllDataByIntSyc(String val){
        SQLiteDatabase _db =getDb();
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        List<tStockInHandHeaderData> dt = _tStockInHandHeaderDA.getAllDataByIntSyc(_db,val);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public List<tStockInHandHeaderData> getAllDataByIntSycAndOutlet(String val, String outlet){
        SQLiteDatabase _db =getDb();
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        List<tStockInHandHeaderData> dt = _tStockInHandHeaderDA.getAllDataByIntSycAndOutlet(_db,val, outlet);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public int countPlanogramHomeAbsenPush( String code) {
        SQLiteDatabase _db = getDb();
        int count = new tStockInHandHeaderDA(_db).countHomeAbsenPush(_db, code);
        return count;
    }

    public int getCountAllStatusSave(String code) {
        SQLiteDatabase _db = getDb();
        int count = new tStockInHandHeaderDA(_db).countHomeAbsenByStatusSave(_db, code);
        return count;
    }

    public int getCountAllPlanogramByStatusSubmit(String code) {
        SQLiteDatabase _db = getDb();
        int count = new tStockInHandHeaderDA(_db).countHomeAbsenByStatusSubmit(_db, code);
        return count;
    }

    public List<tStockInHandHeaderData> getAllSalesProductHeaderByOutletCode(String code){
        SQLiteDatabase _db =getDb();
        List<tStockInHandHeaderData> dt;
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        if(code.equals("ALLOUTLET")){
            dt = _tStockInHandHeaderDA.getAllData(_db);
        } else {
            dt = _tStockInHandHeaderDA.getAllDataByOutletCode(_db,code);
        }

        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public List<tStockInHandHeaderData> getAllSalesProductHeaderByOutletCodeReport(String code){
        SQLiteDatabase _db =getDb();
        List<tStockInHandHeaderData> dt;
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        if(code.equals("ALLOUTLET")){
            dt = _tStockInHandHeaderDA.getAllDataReport(_db);
        } else {
            dt = _tStockInHandHeaderDA.getAllDataByOutletCodeReport(_db,code);
        }

        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public List<tStockInHandHeaderData> getAllSalesProductHeaderByOutletCodeHome(String code){
        SQLiteDatabase _db =getDb();
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        List<tStockInHandHeaderData> dt = _tStockInHandHeaderDA.getAllDataByOutletCode(_db,code);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public List<tStockInHandHeaderData> getDataByNoSO(String noso){
        SQLiteDatabase _db =getDb();
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        List<tStockInHandHeaderData> dt = _tStockInHandHeaderDA.getDataByNoSO(_db,noso);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public tStockInHandHeaderData getDataByNoSOOne(String noso){
        SQLiteDatabase _db =getDb();
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        tStockInHandHeaderData dt = _tStockInHandHeaderDA.getDataByNoSOSelectOne(_db,noso);
        return dt ;
    }

    public List<tStockInHandHeaderData> getAllSalesBydtDateCheckin(String date){
        SQLiteDatabase _db =getDb();
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        List<tStockInHandHeaderData> dt = _tStockInHandHeaderDA.getAllDataByOutletCode(_db,date);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public void generateNoso(tStockInHandHeaderData dt) {
        SQLiteDatabase _db = getDb();
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);

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

        List<tStockInHandHeaderData> dttas = getLastData();

        String noSO = null;

//        if (dttas == null || dttas.size() == 0) {
//            noCustomerBase = "1";
//        } else {
//            String oldVersion = dttas.get(0).get_txtSubmissionId();
//            String[] splitSubmission = oldVersion.split("\\.");
//            if ((dd + mm + yy.substring(2)).equals(splitSubmission[1])) {
//                String lastCount = oldVersion.substring(oldVersion.length() - 3);
//                noCustomerBase = String.valueOf(Integer.parseInt(lastCount) + 1);
//            } else {
//                noCustomerBase = "1";
//            }
//        }
//
//        if (getDataByBitActive().get_txtSubmissionId() == null) {
//            String txtSubmissionId = txtSubmissionCode + "." + dd + mm + yy.substring(2) + "." + String.format("%03d", Integer.parseInt(noCustomerBase));
//
//            dt.set_txtSubmissionId(txtSubmissionId);
//            dt.set_txtSubmissionCode(txtSubmissionCode);
//        }
//
//        _tCustomerBasedMobileHeaderDA.SaveDatatCustomerBasedMobileHeaderData(_db, dt);
    }

    public JSONArray DownloadSIH(String versionName) throws Exception{
        SQLiteDatabase _db=getDb();
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
        String txtMethod="GetDataTransactionStockInHand";
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

    public JSONArray DownloadNOSIH(String versionName, String txtUserId, String txtEmpId) throws ParseException {
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
        dtlinkAPI.set_txtMethod("GetDataNoStockInHand");
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
                data.set_intId(enumCounterData.NoSIH.getidCounterData());
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

    public void updateDataSubmit(tStockInHandHeaderData dt) {
        SQLiteDatabase _db = getDb();
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        _tStockInHandHeaderDA.updateDataSubmit(_db, dt);
    }

    public void deleteTrId(String id) {
        SQLiteDatabase _db=getDb();
        new tStockInHandHeaderDA(_db).deleteContact(_db, id);
    }
    public List<tStockInHandHeaderData> getAllHeaderByOutletCodeUnsubmit(String code) {
        SQLiteDatabase _db = getDb();
        List<tStockInHandHeaderData> dt;
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        dt = _tStockInHandHeaderDA.getAllDataByOutletCodeUnsubmit(_db, code);
        if (dt == null) {
            dt = new ArrayList<>(0);
        }
        return dt;
    }
    public void updateDataSubmit(tPlanogramMobileData dt) {
        SQLiteDatabase _db = getDb();
        tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(_db);
        _tStockInHandHeaderDA.updateDataSubmit(_db, dt);
    }
}
