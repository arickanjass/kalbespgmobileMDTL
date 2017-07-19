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

import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tSalesProductHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.enumCounterData;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tSalesProductHeaderDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by ASUS ZE on 14/07/2016.
 */
public class tSalesProductHeaderBL extends clsMainBL {
    SQLiteDatabase db=getDb();

    public void SaveData(tSalesProductHeaderData dt){
        SQLiteDatabase _db =getDb();
        tSalesProductHeaderDA _tSalesProductDA = new tSalesProductHeaderDA(_db);
        _tSalesProductDA.SaveDatatSalesProductHeaderData(_db, dt);
    }
    //    public List<tCustomerBaseData> getAllCustomerBase(){
//        SQLiteDatabase _db=getDb();
//
//        tCustomerBaseDA _tCustomerBaseDA = new tCustomerBaseDA(_db);
//        List<tCustomerBaseData> dt = _tCustomerBaseDA.getAllData(_db);
//
//        return dt;
//    }
    public List<tSalesProductHeaderData> getAllSalesProductHeader(){
        SQLiteDatabase _db =getDb();
        tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(_db);
        List<tSalesProductHeaderData> dt = _tSalesProductHeaderDA.getAllData(_db);
        return dt ;
    }

    public List<tSalesProductHeaderData> getAllSalesProductHeaderByOutlet(String outletcode){
        SQLiteDatabase _db =getDb();
        tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(_db);
        List<tSalesProductHeaderData> dt = _tSalesProductHeaderDA.getAllDataByOutletCode(_db, outletcode);
        return dt ;
    }

    public List<tSalesProductHeaderData> getLastData(){
        SQLiteDatabase _db =getDb();
        tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(_db);
        List<tSalesProductHeaderData> dt = _tSalesProductHeaderDA.getLastData(_db);
        return dt ;
    }

    public List<tSalesProductHeaderData> getAllDataByIntSyc(String val){
        SQLiteDatabase _db =getDb();
        tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(_db);
        List<tSalesProductHeaderData> dt = _tSalesProductHeaderDA.getAllDataByIntSyc(_db,val);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public List<tSalesProductHeaderData> getAllDataByIntSycAndOutlet(String val, String outlet){
        SQLiteDatabase _db =getDb();
        tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(_db);
        List<tSalesProductHeaderData> dt = _tSalesProductHeaderDA.getAllDataByIntSycAndOutlet(_db,val, outlet);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public List<tSalesProductHeaderData> getAllSalesProductHeaderByOutletCode(String code){
        SQLiteDatabase _db =getDb();
        List<tSalesProductHeaderData> dt;
        tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(_db);
        if(code.equals("ALLOUTLET")){
            dt = _tSalesProductHeaderDA.getAllData(_db);
        } else {
            dt = _tSalesProductHeaderDA.getAllDataByOutletCode(_db,code);
        }

        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public List<tSalesProductHeaderData> getAllSalesProductHeaderByOutletCodeHome(String code){
        SQLiteDatabase _db =getDb();
        tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(_db);
        List<tSalesProductHeaderData> dt = _tSalesProductHeaderDA.getAllDataByOutletCode(_db,code);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public List<tSalesProductHeaderData> getDataByNoSO(String noso){
        SQLiteDatabase _db =getDb();
        tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(_db);
        List<tSalesProductHeaderData> dt = _tSalesProductHeaderDA.getDataByNoSO(_db,noso);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public List<tSalesProductHeaderData> getAllSalesBydtDateCheckin(String date){
        SQLiteDatabase _db =getDb();
        tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(_db);
        List<tSalesProductHeaderData> dt = _tSalesProductHeaderDA.getAllDataByOutletCode(_db,date);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt ;
    }

    public void generateNoso(tSalesProductHeaderData dt) {
        SQLiteDatabase _db = getDb();
        tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(_db);

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

        List<tSalesProductHeaderData> dttas = getLastData();

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

    public JSONArray DownloadReso(String versionName) throws Exception{
        SQLiteDatabase _db=getDb();
        tUserLoginDA _tUserLoginDA=new tUserLoginDA(_db);
        mconfigDA _mconfigDA =new mconfigDA(_db);

        String strVal2="";
        mconfigData dataAPI = _mconfigDA.getData(db,enumConfigData.ApiKalbe.getidConfigData());
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
        String txtMethod="GetDataTransactionReso";
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
}
