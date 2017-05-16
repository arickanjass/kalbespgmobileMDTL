package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

import library.salesforce.common.APIData;
import library.salesforce.common.clsHelper;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mconfigData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.common.tVisitPlanRealisasiData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tUserLoginDA;
import library.salesforce.dal.tVisitPlanRealisasiDA;

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
    public void UpdateData(List<tVisitPlanRealisasiData> dt) {
        SQLiteDatabase db = getDb();
        tVisitPlanRealisasiDA _tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
//        _tVisitPlanRealisasiDA.DeleteAllData(db);
        for (tVisitPlanRealisasiData data : dt) {
            _tVisitPlanRealisasiDA.UpdateDatatVisitPlan_MobileData(db, data);
        }
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

    public tVisitPlanRealisasiData getAllDataByHeaderId(String id){
        SQLiteDatabase _db = getDb();
        tVisitPlanRealisasiData dtDetail = new tVisitPlanRealisasiDA(_db).getDataByDataIDRealisasi(_db,id);
        _db.close();
        return dtDetail;
    }
    public List<tVisitPlanRealisasiData> getAllData(){
        SQLiteDatabase db=getDb();
        tVisitPlanRealisasiDA _tActivityDA=new tVisitPlanRealisasiDA(db);
        List<tVisitPlanRealisasiData> listData=_tActivityDA.getAllData(db);
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
}
