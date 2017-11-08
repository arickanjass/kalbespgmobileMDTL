package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mCategoryVisitPlanData;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mCategoryVisitPlanDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by Robert on 03/05/2017.
 */

public class mCategoryVisitPlanBL extends clsMainBL {

    public void saveData(List<mCategoryVisitPlanData> Listdata) {
        SQLiteDatabase db = getDb();
        mCategoryVisitPlanDA _mCategoryVisitPlanDA = new mCategoryVisitPlanDA(db);
        _mCategoryVisitPlanDA.DeleteAllDataCategoryVisitPlan(db);
        for (mCategoryVisitPlanData data : Listdata) {
            _mCategoryVisitPlanDA.SaveDataCategoryVisitPlan(db, data);
        }
        db.close();
    }

    public mCategoryVisitPlanData GetAllDataByOutletCode(String idcategory) {
        SQLiteDatabase db = getDb();
        mCategoryVisitPlanDA _mCategoryVisitPlanDA = new mCategoryVisitPlanDA(db);
        mCategoryVisitPlanData listdata = _mCategoryVisitPlanDA.getDataByintCategoryVisitPlan(db, idcategory);
        db.close();
        return listdata;
    }

    public List<mCategoryVisitPlanData> GetAllData() {
        SQLiteDatabase db = getDb();
        mCategoryVisitPlanDA _mEmployeeAreaDA = new mCategoryVisitPlanDA(db);
        List<mCategoryVisitPlanData> listdata = _mEmployeeAreaDA.getAllData(db);
        db.close();
        return listdata;
    }

    public JSONArray DownloadCategoryVisitPlanData(String versionName) throws Exception {
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);
        String strVal2 = "";
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        //ambil version dari webservices
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtlinkAPI = new linkAPI();
        String txtMethod = "GetDataCategoryVisitPlan";
        JSONObject resJson = new JSONObject();
//        resJson.put("txtNIK", _dataUserLogin.get_TxtEmpId());
        dtlinkAPI.set_txtMethod(txtMethod);
//        dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId()+"|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);

        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.ResultJsonData(_help.getHTML(strLinkAPI));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        //String strLinkAPI= dtlinkAPI.QueryString(strVal2);
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
        APIData dtAPIDATA = new APIData();

        _db.close();
        return JsonArray;
    }
}
