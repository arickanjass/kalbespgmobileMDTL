package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mTypePOPStandardData;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mTypePOPStandardDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by Dewi Oktaviani on 18/10/2017.
 */

public class mTypePOPStandardBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveData (mTypePOPStandardData dt){
        SQLiteDatabase _db = getDb();
        mTypePOPStandardDA _mTypePOPStandardDA = new mTypePOPStandardDA(_db);
        _mTypePOPStandardDA.SaveDatamTypePOPStandard(_db, dt);
    }

    public List<mTypePOPStandardData> GetAllData(){
        SQLiteDatabase _db = getDb();
        mTypePOPStandardDA _mTypePOPStandardDA = new mTypePOPStandardDA(_db);
        List<mTypePOPStandardData> dt = _mTypePOPStandardDA.GetAllData(_db);
        return dt;
    }
    public JSONArray DownlaodDataPOPStandard(String versionName) throws Exception{
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);
        String strVal2 = "";
        mconfigData dataApi = _mconfigDA .getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataApi.get_txtValue();
        if (dataApi.get_txtValue() == ""){
            strVal2 = dataApi.get_txtDefaultValue();
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateNow = dateFormat.format(date);

        tUserLoginData _tUserLoginData = _tUserLoginDA.getData(db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtLinkAPI = new linkAPI();
        String txtMethod = "GetDataPOPStandard_mobile";
        dtLinkAPI.set_txtMethod(txtMethod);
        dtLinkAPI.set_txtParam(_tUserLoginData.get_txtRoleId() + "|" + _tUserLoginData .get_TxtEmpId());
        dtLinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtLinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtLinkAPI.QueryString(strVal2);
        String JsonData = _help .pushtData(strLinkAPI, dtLinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));
        JSONArray jsonArray = _help.ResultJsonArray(JsonData);
        _db.close();
        return jsonArray;
    }
}
