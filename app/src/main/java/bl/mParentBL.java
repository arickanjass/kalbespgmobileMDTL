package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import library.salesforce.common.clsHelper;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mParentData;
import library.salesforce.common.mconfigData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.mParentDA;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tUserLoginDA;

/**
 * Created by Dewi Oktaviani on 05/05/2017.
 */

public class mParentBL extends clsMainBL{
    SQLiteDatabase db = getDb();

    public void SaveDatamParent(mParentData dt){
        SQLiteDatabase _db = getDb();
        mParentDA _mParentDA = new mParentDA(_db);
        _mParentDA.SaveDatamParent(_db, dt);

    }
    public List<mParentData> GetAllData(){
        SQLiteDatabase _db = getDb();
        mParentDA _mParentDA = new mParentDA(_db);
        List<mParentData> listData = _mParentDA.GetAllData(_db);
        db.close();
        return listData;
    }
    public JSONArray DownlaodDataQuesioner(String versionName) throws Exception{
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);
        String strVal2 = "";
        mconfigData dataApi = _mconfigDA .getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataApi.get_txtValue();
        if (dataApi.get_txtValue() == ""){
            strVal2 = dataApi.get_txtDefaultValue();
        }
        tUserLoginData _tUserLoginData = _tUserLoginDA.getData(db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtLinkAPI = new linkAPI();
        String txtMethod = "GetDataQuesioner_mobile";
        JSONObject rseJson = new JSONObject();
        dtLinkAPI.set_txtMethod(txtMethod);
        dtLinkAPI.set_txtParam("");
        dtLinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtLinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtLinkAPI.QueryString(strVal2);
        String JsonData = _help .pushtData(strLinkAPI, dtLinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));
        JSONArray jsonArray = _help.ResultJsonArray(JsonData);
        _db.close();
        return jsonArray;
    }
}
