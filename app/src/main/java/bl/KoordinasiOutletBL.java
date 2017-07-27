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
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.KoordinasiOutletDA;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by Rian Andrivani on 6/6/2017.
 */

public class KoordinasiOutletBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveDataKoordinasiOutlet(KoordinasiOutletData dt) {
        SQLiteDatabase _db = getDb();
        KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(_db);
        _KoordinasiOutletDA.SaveDataKoordinasiOutlet(_db, dt);
        db.close();
    }

    public int getContactCount() {
        SQLiteDatabase db = getDb();
        KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(db);
        return _KoordinasiOutletDA.getContactsCount(db);
    }

    public List<KoordinasiOutletData> getData(String id) {
        List<KoordinasiOutletData> listData = new ArrayList<KoordinasiOutletData>();
        KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(db);
        if (id.equals("")){
            listData = _KoordinasiOutletDA.getAllData(db);
        } else {
            KoordinasiOutletData data = new KoordinasiOutletData();
            data = _KoordinasiOutletDA.getData(db, id);
            listData.add(data);
        }
        return listData;
    }

    public List<KoordinasiOutletData> getAllDataByOutletCode(String code){
        SQLiteDatabase _db = getDb();
        KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(_db);
        List<KoordinasiOutletData> dt = _KoordinasiOutletDA.getAllDataByOutletCode(_db, code);

        _db.close();
        return dt ;
    }

    public List<KoordinasiOutletData> getAllKoordinasiOutletData() {
        SQLiteDatabase _db = getDb();
        KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(_db);
        List<KoordinasiOutletData> dt = _KoordinasiOutletDA.getAllData(_db);
        _db.close();
        return dt;
    }

    public org.json.simple.JSONArray DownloadDataKoordinasiOutlet(String versionName) throws Exception{
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
        String txtMethod = "GetDataKoordinasiOutlet";
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
}
