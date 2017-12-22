package bl;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.kalbenutritionals.app.kalbespgmobile.mBranchTempData;
import com.kalbenutritionals.app.kalbespgmobile.mEmployeeAreaTempData;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mEmployeeAreaData;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mEmployeeAreaDA;
import library.spgmobile.dal.mEmployeeBranchDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tUserLoginDA;

public class mEmployeeAreaBL extends clsMainBL {
    public void saveData(List<mEmployeeAreaData> Listdata) {
        SQLiteDatabase db = getDb();
        mEmployeeAreaDA _mEmployeeAreaDA = new mEmployeeAreaDA(db);
        _mEmployeeAreaDA.DeleteAllDataMConfig(db);
        for (mEmployeeAreaData data : Listdata) {
            _mEmployeeAreaDA.SaveDataMConfig(db, data);
        }
        db.close();
    }

    public void saveDataCustomExec(List<mEmployeeAreaTempData> Listdata) {
        SQLiteDatabase db = getDb();
        String sql = "insert into " + new clsHardCode().txtTable_mEmployeeArea + " (intEmployeeId, txtNIK, txtName, intID, intRegionId, txtRegionName, intBranchId, txtBranchCode, txtBranchName, intRayonId, txtRayonCode, txtRayonName, intChannelId, intOutletId, txtOutletCode, txtOutletName, txtLatitude, txtLongitude) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(sql);

        for (int i = 0; i < Listdata.size(); i++) {
            if (Listdata.get(i).getPboolValid().toString().equals("1")) {
                stmt.bindString(1, String.valueOf(Listdata.get(i).getIntEmployeeId()));
                stmt.bindString(2, String.valueOf(Listdata.get(i).getTxtNIK()));
                stmt.bindString(3, String.valueOf(Listdata.get(i).getTxtName()));
                stmt.bindString(4, String.valueOf(Listdata.get(i).getIntID()));
                stmt.bindString(5, String.valueOf(Listdata.get(i).getIntRegionId()));
                stmt.bindString(6, String.valueOf(Listdata.get(i).getTxtRegionName()));
                stmt.bindString(7, String.valueOf(Listdata.get(i).getIntBranchId()));
                stmt.bindString(8, String.valueOf(Listdata.get(i).getTxtBranchCode()));
                stmt.bindString(9, String.valueOf(Listdata.get(i).getTxtBranchName()));
                stmt.bindString(10, String.valueOf(Listdata.get(i).getIntRayonId()));
                stmt.bindString(11, String.valueOf(Listdata.get(i).getTxtRayonCode()));
                stmt.bindString(12, String.valueOf(Listdata.get(i).getTxtRayonName()));
                stmt.bindString(13, String.valueOf(Listdata.get(i).getIntChannelId()));
                stmt.bindString(14, String.valueOf(Listdata.get(i).getIntOutletId()));
                stmt.bindString(15, String.valueOf(Listdata.get(i).getTxtOutletCode()));
                stmt.bindString(16, String.valueOf(Listdata.get(i).getTxtOutletName()));
                stmt.bindString(17, String.valueOf(Listdata.get(i).getTxtLatitude()));
                stmt.bindString(18, String.valueOf(Listdata.get(i).getTxtLongitude()));

                stmt.executeInsert();
                stmt.clearBindings();
            }
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void deleteAllData() {
        SQLiteDatabase db = getDb();
        mEmployeeAreaDA _mmEmployeeAreaDA = new mEmployeeAreaDA(db);
        _mmEmployeeAreaDA.DeleteAllDataMConfig(db);
        db.close();
    }

    public mEmployeeAreaData GetAllDataByOutletCode(String idOutlet) {
        SQLiteDatabase db = getDb();
        mEmployeeAreaDA _mEmployeeAreaDA = new mEmployeeAreaDA(db);
        mEmployeeAreaData listdata = _mEmployeeAreaDA.getDataByOutlet(db, idOutlet);
        db.close();
        return listdata;
    }

    public List<mEmployeeAreaData> GetAllData() {
        SQLiteDatabase db = getDb();
        mEmployeeAreaDA _mEmployeeAreaDA = new mEmployeeAreaDA(db);
        List<mEmployeeAreaData> lisdata = _mEmployeeAreaDA.getAllData(db);
        db.close();
        return lisdata;
    }

    public void DeleteEmployeeNotInId(String outletCode) {
        SQLiteDatabase db = getDb();
        mEmployeeAreaDA _mEmployeeAreaDA = new mEmployeeAreaDA(db);
        List<mEmployeeAreaData> dt = GetAllData();

        for (int i = 0; i < dt.size(); i++) {
            if (dt.get(i).get_txtOutletCode().equals(outletCode)) {
                continue;
            } else {
                new mEmployeeAreaDA(db).deleteContact(db, Integer.parseInt(dt.get(i).get_intID()));
            }
        }
        db.close();
    }

    public JSONArray DownloadEmployeeArea2(String versionName) throws Exception {
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
        String txtMethod = "GetDatavw_SalesInsentive_EmployeeArea";
        JSONObject resJson = new JSONObject();
        resJson.put("txtNIK", _dataUserLogin.get_TxtEmpId());
        resJson.put("txtOutletCode", null);
        resJson.put("txtBranchCode", null);
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);

        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));

        //String strLinkAPI= dtlinkAPI.QueryString(strVal2);
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));

        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        _db.close();
        return JsonArray;
    }

    public String DownloadEmployeeAreaEnhance(String versionName) throws Exception {
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
        String txtMethod = "GetDatavw_SalesInsentive_EmployeeArea";
        JSONObject resJson = new JSONObject();
        resJson.put("txtNIK", _dataUserLogin.get_TxtEmpId());
        resJson.put("txtOutletCode", null);
        resJson.put("txtBranchCode", null);
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);

        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));

        _db.close();
        return JsonData;
    }

    public void DownloadEmployeeArea(String versionName) throws Exception {
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
        String txtMethod = "GetDatavw_SalesInsentive_EmployeeArea";
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.ResultJsonData(_help.getHTML(strLinkAPI));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        APIData dtAPIDATA = new APIData();
        //String aa=new clsHelper().linkAPI(db);
        Iterator i = JsonArray.iterator();
        Boolean flag = true;
        String ErrorMess = "";
        mEmployeeAreaDA _mEmployeeAreaData = new mEmployeeAreaDA(_db);
        _mEmployeeAreaData.DeleteAllDataMConfig(_db);
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                mEmployeeAreaData _data = new mEmployeeAreaData();
                _data.set_intBranchId((String) innerObj.get("IntBranchId"));
                _data.set_intChannelId((String) innerObj.get("IntChannelId"));
                _data.set_intEmployeeId((String) innerObj.get("IntEmployeeId"));
                _data.set_intID((String) innerObj.get("IntID"));
                _data.set_intOutletId((String) innerObj.get("IntOutletId"));
                _data.set_intChannelId((String) innerObj.get("IntChannelId"));
                _data.set_intRayonId((String) innerObj.get("IntRayonId"));
                _data.set_intRegionId((String) innerObj.get("IntRegionId"));
                _data.set_txtBranchCode((String) innerObj.get("TxtBranchCode"));
                _data.set_txtBranchName((String) innerObj.get("TxtBranchName"));
                _data.set_txtNIK((String) innerObj.get("TxtNIK"));
                _data.set_txtName((String) innerObj.get("TxtName"));
                _data.set_txtOutletCode((String) innerObj.get("TxtOutletCode"));
                _data.set_txtOutletName((String) innerObj.get("TxtOutletName"));
                _data.set_txtRayonCode((String) innerObj.get("TxtRayonCode"));
                _data.set_txtRayonName((String) innerObj.get("TxtRayonName"));
                _data.set_txtRegionName((String) innerObj.get("TxtRegionName"));
                _mEmployeeAreaData.SaveDataMConfig(_db, _data);

            } else {
                flag = false;
                ErrorMess = (String) innerObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
        _db.close();
    }
}
