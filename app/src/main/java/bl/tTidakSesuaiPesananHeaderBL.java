package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import library.spgmobile.common.KoordinasiOutletData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tActivityMobileData;
import library.spgmobile.common.tTidakSesuaiPesananHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.KoordinasiOutletDA;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tActivityMobileDA;
import library.spgmobile.dal.tTidakSesuaiPesananHeaderDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by aan.junianto on 10/10/2017.
 */

public class tTidakSesuaiPesananHeaderBL extends clsMainBL {
    public List<tTidakSesuaiPesananHeaderData> getAllData() {
        SQLiteDatabase _db = getDb();
        tTidakSesuaiPesananHeaderDA _tTidakSesuaiPesananHeaderDA = new tTidakSesuaiPesananHeaderDA(_db);
        List<tTidakSesuaiPesananHeaderData> dt = _tTidakSesuaiPesananHeaderDA.getAllData(_db);
        _db.close();
        return dt;
    }
    public org.json.simple.JSONArray DownloadDataTidakSesuaiPesanan(String versionName) throws Exception{
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
        String txtMethod = "GetDataTidakSesuaiPesanan";
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

    public void SaveData(tTidakSesuaiPesananHeaderData dt) {
        SQLiteDatabase _db = getDb();
        tTidakSesuaiPesananHeaderDA _tTidakSesuaiPesananHeaderDA = new tTidakSesuaiPesananHeaderDA(_db);
        _tTidakSesuaiPesananHeaderDA.SaveData(_db, dt);
        db.close();
    }

    public List<tTidakSesuaiPesananHeaderData> getAllDataByOutletCodeReport(String outletcode){
        SQLiteDatabase _db = getDb();
        tTidakSesuaiPesananHeaderDA _tTidakSesuaiPesananHeaderDA = new tTidakSesuaiPesananHeaderDA(_db);
        List<tTidakSesuaiPesananHeaderData> dt;
        if(outletcode.equals("ALLOUTLET")){
            dt = _tTidakSesuaiPesananHeaderDA.getAllData(_db);
        } else {
            dt = _tTidakSesuaiPesananHeaderDA.getAllDataByOutletCode(_db,outletcode);
        }

        if(dt == null){
            dt = new ArrayList<>(0);
        }
        return dt;
    }
    public List<tTidakSesuaiPesananHeaderData> getAllDataByOutletCode(String code){
        SQLiteDatabase _db = getDb();
        tTidakSesuaiPesananHeaderDA _tTidakSesuaiPesananHeaderDA = new tTidakSesuaiPesananHeaderDA(_db);
        List<tTidakSesuaiPesananHeaderData> dt = _tTidakSesuaiPesananHeaderDA.getAllDataByOutletCode(_db, code);

        _db.close();
        return dt ;
    }
}
