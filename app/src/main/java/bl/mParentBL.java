package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mParentData;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tHirarkiBIS;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mParentDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tHirarkiBISDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by Dewi Oktaviani on 05/05/2017.
 */

public class mParentBL extends clsMainBL{
    //SQLiteDatabase db = getDb();

    public void SaveDatamParent(mParentData dt){
        SQLiteDatabase _db = getDb();
        mParentDA _mParentDA = new mParentDA(_db);
        _mParentDA.SaveDatamParent(_db, dt);
    }

    public List<mParentData> GetAllData(){
        SQLiteDatabase _db = getDb();
        mParentDA _mParentDA = new mParentDA(_db);
        List<mParentData> listData = _mParentDA.GetAllData(_db);
        _db.close();
        return listData;
    }

    public void DeletemParent(){
        SQLiteDatabase _db = getDb();
        mParentDA _mParentDA = new mParentDA(_db);
        _mParentDA.DeleteAllDatamParent(_db);
        _db.close();
    }

    public JSONArray DownlaodDataQuesioner(String versionName) throws Exception{
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);
        String strVal2 = "";
        mconfigData dataApi = _mconfigDA .getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataApi.get_txtValue();
        if (dataApi.get_txtValue() == ""){
            strVal2 = dataApi.get_txtDefaultValue();
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateNow = dateFormat.format(date);

        tUserLoginData _tUserLoginData = _tUserLoginDA.getData(_db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtLinkAPI = new linkAPI();
        String txtMethod = "GetDataQuesioner_mobile";
        dtLinkAPI.set_txtMethod(txtMethod);
        dtLinkAPI.set_txtParam(_tUserLoginData.get_txtRoleId() + "|" + dateNow + "|" + _tUserLoginData .get_txtUserId()  + "|" + _tUserLoginData .get_TxtEmpId() + "|" + _tUserLoginData.get_txtBranchCode());
        dtLinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtLinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtLinkAPI.QueryString(strVal2);
        String JsonData = _help .pushtData(strLinkAPI, dtLinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));
        JSONArray jsonArray = _help.ResultJsonArray(JsonData);
        _db.close();
        return jsonArray;
    }

    public JSONArray DownlaodDataSPGfromTL(String versionName) throws Exception{
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);
        String strVal2 = "";
        mconfigData dataApi = _mconfigDA .getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataApi.get_txtValue();
        if (dataApi.get_txtValue() == ""){
            strVal2 = dataApi.get_txtDefaultValue();
        }

        tUserLoginData _tUserLoginData = _tUserLoginDA.getData(_db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtLinkAPI = new linkAPI();
        String txtMethod = "GetDataSPGFromTL_mobile";
        dtLinkAPI.set_txtMethod(txtMethod);
        dtLinkAPI.set_txtParam(_tUserLoginData.get_txtBranchCode() + "|" + _tUserLoginData .get_TxtEmpId());
        dtLinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtLinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtLinkAPI.QueryString(strVal2);
        String JsonData = _help .pushtData(strLinkAPI, dtLinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));
        JSONArray jsonArray = _help.ResultJsonArray(JsonData);
        _db.close();
        return jsonArray;
    }
}
