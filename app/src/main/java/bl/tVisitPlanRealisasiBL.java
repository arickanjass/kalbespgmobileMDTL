package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.tVisitPlanRealisasiData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tOverStockHeaderDA;
import library.spgmobile.dal.tUserLoginDA;
import library.spgmobile.dal.tVisitPlanRealisasiDA;

/**
 * Created by Robert on 27/04/2017.
 */

public class tVisitPlanRealisasiBL extends clsMainBL{
    public void saveData(List<tVisitPlanRealisasiData> dt) {
        SQLiteDatabase db = getDb();
        tVisitPlanRealisasiDA _tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
//        _tVisitPlanRealisasiDA.DeleteAllData(db);
        for (tVisitPlanRealisasiData data : dt) {
            _tVisitPlanRealisasiDA.SaveDatatVisitPlan_MobileData(db, data);
        }
        db.close();
    }
    public void UpdateData(tVisitPlanRealisasiData dt) {
        SQLiteDatabase db = getDb();
        tVisitPlanRealisasiDA _tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
//        _tVisitPlanRealisasiDA.DeleteAllData(db);
            _tVisitPlanRealisasiDA.UpdateDatatVisitPlan_MobileData(db, dt);
        db.close();
    }
    public void downloadData(List<tVisitPlanRealisasiData> dt) {
        SQLiteDatabase db = getDb();
        tVisitPlanRealisasiDA _tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
//        _tVisitPlanRealisasiDA.DeleteAllData(db);
        for (tVisitPlanRealisasiData data : dt) {
            _tVisitPlanRealisasiDA.DownloadDatatVisitPlan_MobileData(db, data);
        }
        db.close();
    }
    public List<tVisitPlanRealisasiData> GetAllData(){
        SQLiteDatabase _db = getDb();
        List<tVisitPlanRealisasiData> dtDetail = new tVisitPlanRealisasiDA(_db).getAllData(_db);
        _db.close();
        return dtDetail;
    }

    public tVisitPlanRealisasiData getDataByHeaderId(String id){
        SQLiteDatabase _db = getDb();
        tVisitPlanRealisasiData dtDetail = new tVisitPlanRealisasiDA(_db).getDataByDataIDRealisasi(_db,id);
        _db.close();
        return dtDetail;
    }
    public tVisitPlanRealisasiData getDataCheckinActive(){
        SQLiteDatabase _db = getDb();
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiDA(_db).getDataCheckInActive(_db);
        _db.close();
        return dt;
    }
    public List<tVisitPlanRealisasiData>  getAllDataActiveOrderByDate(){
        SQLiteDatabase db=getDb();
        tVisitPlanRealisasiDA _tVisitPlanRealisasiDA=new tVisitPlanRealisasiDA(db);
        List<tVisitPlanRealisasiData> ListData=new ArrayList<tVisitPlanRealisasiData>();
        ListData=_tVisitPlanRealisasiDA.getAllDataActiveOrderByDate(db);
        return ListData;
    }
    public List<tVisitPlanRealisasiData> getAllData(){
        SQLiteDatabase db=getDb();
        tVisitPlanRealisasiDA _tActivityDA=new tVisitPlanRealisasiDA(db);
        List<tVisitPlanRealisasiData> listData=_tActivityDA.getAllData(db);
        return listData;
    }
    public List<tVisitPlanRealisasiData> getAllDataByIntSubmit(String intSubmit){
        SQLiteDatabase db=getDb();
        tVisitPlanRealisasiDA _tActivityDA=new tVisitPlanRealisasiDA(db);
        List<tVisitPlanRealisasiData> listData=_tActivityDA.getAllDataByIntSubmit(db, intSubmit);
        return listData;
    }
    public tVisitPlanRealisasiData getDataByIdOutlet(String id){
        SQLiteDatabase db=getDb();
        tVisitPlanRealisasiDA _tActivityDA=new tVisitPlanRealisasiDA(db);
        tVisitPlanRealisasiData listData=_tActivityDA.getDataByDataIDOutlet(db,id);
        return listData;
    }

    public void deleteDataByIDRealisasi(tVisitPlanRealisasiData dt){
        SQLiteDatabase _db = getDb();
        new tVisitPlanRealisasiDA(_db).deleteByIDRealisasi(_db, dt.get_txtDataIDRealisasi());
        _db.close();
    }

    public JSONArray DownloadRealisasiVisitPlan(String versionName) throws Exception {
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);
        String strVal2 = "";
        mconfigData dataAPI = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        //ambil version dari webservices
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtlinkAPI = new linkAPI();
        String txtMethod = "GetDatatTransaksiRealisasiVisitPlan";
        JSONObject resJson = new JSONObject();
        resJson.put("txtNIK", _dataUserLogin.get_TxtEmpId());
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId()+"|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);

        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
//        String JsonData = _help.ResultJsonData(_help.getHTML(strLinkAPI));
        String JsonData= _help.pushtData(strLinkAPI,String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        //String strLinkAPI= dtlinkAPI.QueryString(strVal2);
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
        APIData dtAPIDATA = new APIData();

        _db.close();
        return JsonArray;
    }
    public void checkOutSystem(String id, String time) {
        SQLiteDatabase db = getDb();
        tVisitPlanRealisasiDA _tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
        _tVisitPlanRealisasiDA.checkoutSystem(db, id, time);
        db.close();
    }
    public int getCountOutVisitStatusSubmit(String code) {
        SQLiteDatabase _db = getDb();
        int count = new tVisitPlanRealisasiDA(_db).countOutVisitStatusSubmit(_db, code);
        return count;
    }
    public int countOutVisitPush( String code) {
        SQLiteDatabase _db = getDb();
        int count = new tVisitPlanRealisasiDA(_db).countOutVisitPush(_db, code);
        return count;
    }
}
