package bl;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.kalbenutritionals.app.kalbespgmobile.mProductCompTempData;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tActivityMobileData;
import library.spgmobile.common.tSubTypeActivityData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tActivityMobileDA;
import library.spgmobile.dal.tSubTypeActivityDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by rhezaTesar on 7/18/2017.
 */

public class tSubTypeActivityBL extends clsMainBL{
    public void saveData(List<tSubTypeActivityData> dt) {
        SQLiteDatabase db = getDb();
        tSubTypeActivityDA _tSubTypeActivityDA = new tSubTypeActivityDA(db);
        _tSubTypeActivityDA.deleteContact(db);
        for (tSubTypeActivityData data : dt) {
            tSubTypeActivityDA.SaveDatatSubTypeActivityData(db, data);
        }
        db.close();
    }

    public void saveDataCustomExec(List<tSubTypeActivityData> Listdata) {
        SQLiteDatabase db = getDb();
        String sql = "insert into "+ new clsHardCode().txtTable_tSubTypeActivity + " (intSubTypeActivity, txtType, txtName, bitActive) values (?, ?, ?, ?);";

        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(sql);

        for (int i = 0; i < Listdata.size(); i++) {
            stmt.bindString(1, Listdata.get(i).get_intSubTypeActivity());
            stmt.bindString(2, Listdata.get(i).get_txtType());
            stmt.bindString(3, Listdata.get(i).get_txtName());
            stmt.bindString(4, Listdata.get(i).get_bitActive());

            stmt.executeInsert();
            stmt.clearBindings();
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<tSubTypeActivityData> getAllData(){
        SQLiteDatabase _db = getDb();
        List<tSubTypeActivityData> dtDetail = new tSubTypeActivityDA(_db).getAll(_db);
        _db.close();
        return dtDetail;
    }

    public List<tSubTypeActivityData> getAllDataByTxtType(String txtType){
        SQLiteDatabase _db =getDb();
        tSubTypeActivityDA _tSubTypeActivityDA = new tSubTypeActivityDA(_db);
        List<tSubTypeActivityData> dtDetail = _tSubTypeActivityDA.getAllDataByTxtType(_db, txtType);
        if(dtDetail == null){
            dtDetail = new ArrayList<>();
        }
        _db.close();
        return dtDetail;
    }

    public JSONArray DownloadtSubTypeActivity(String versionName, String intRoleId) throws Exception{
        SQLiteDatabase _db=getDb();
        tUserLoginDA _tUserLoginDA=new tUserLoginDA(_db);
        mconfigDA _mconfigDA =new mconfigDA(_db);

        String strVal2="";
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String datenow = dateFormat.format(date);

        //ambil version dari webservices
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        clsHelper _help =new clsHelper();
        linkAPI dtlinkAPI=new linkAPI();
        String txtMethod="getDatatSubTypeActivity";
        JSONObject resJson = new JSONObject();
        resJson.put("intRoleId", intRoleId);
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam("|" + _dataUserLogin.get_TxtEmpId() + "|" + datenow);
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);

        String strLinkAPI= dtlinkAPI.QueryString(strVal2);
        String JsonData= _help.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));

        //String strLinkAPI= dtlinkAPI.QueryString(strVal2);
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));

        JSONArray JsonArray= _help.ResultJsonArray(JsonData);
        _db.close();
        return JsonArray;
    }
}
