package bl;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.kalbenutritionals.app.kalbespgmobile.Network.mBranchTempData;

import org.json.simple.JSONArray;

import java.util.Iterator;
import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mEmployeeBranchData;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mEmployeeBranchDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tUserLoginDA;

public class mEmployeeBranchBL extends clsMainBL {
    public void saveData(List<mEmployeeBranchData> Listdata) {
        SQLiteDatabase db = getDb();
        mEmployeeBranchDA _mEmployeeBranchDA = new mEmployeeBranchDA(db);
        _mEmployeeBranchDA.DeleteAllDataMConfig(db);
        for (mEmployeeBranchData data : Listdata) {
            _mEmployeeBranchDA.SaveDataMConfig(db, data);
        }
        db.close();
    }

    public void deleteAllData() {
        SQLiteDatabase db = getDb();
        mEmployeeBranchDA _mEmployeeBranchDA = new mEmployeeBranchDA(db);
        _mEmployeeBranchDA.DeleteAllDataMConfig(db);
        db.close();
    }

    public void saveDataCustomExec(List<mBranchTempData> Listdata) {
        SQLiteDatabase db = getDb();
        String sql = "insert into " + new clsHardCode().txtTable_mEmployeeBranch + " (EmpId, txtNIK, txtName, txtBranchCode, txtBranchName, intID) values (?, ?, ?, ?, ?, ?);";

        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(sql);

        for (int i = 0; i < Listdata.size(); i++) {
            if (Listdata.get(i).getPboolValid().toString().equals("1")) {
                stmt.bindString(1, String.valueOf(Listdata.get(i).getEmpId()));
                stmt.bindString(2, String.valueOf(Listdata.get(i).getTxtNIK()));
                stmt.bindString(3, String.valueOf(Listdata.get(i).getTxtName()));
                stmt.bindString(4, String.valueOf(Listdata.get(i).getTxtBranchCode()));
                stmt.bindString(5, String.valueOf(Listdata.get(i).getTxtBranchName()));
                stmt.bindString(6, String.valueOf(Listdata.get(i).getIntID()));

                stmt.executeInsert();
                stmt.clearBindings();
            }
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<mEmployeeBranchData> GetAllData() {
        SQLiteDatabase db = getDb();
        mEmployeeBranchDA _mEmployeeBranchDA = new mEmployeeBranchDA(db);
        List<mEmployeeBranchData> ListData = _mEmployeeBranchDA.getAllData(db);
        db.close();
        return ListData;
    }

    public JSONArray DownloadEmployeeBranch2(String versionName) throws Exception {
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
        String txtMethod = "GetDatavw_SalesInsentive_EmployeeBranch";
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.ResultJsonData(_help.getHTML(strLinkAPI));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        APIData dtAPIDATA = new APIData();
        _db.close();
        return JsonArray;
    }

    public String DownloadEmployeeBranchEnhance(String versionName) throws Exception {
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);

        String strVal2 = "";
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue().equals("")) {
            strVal2 = dataAPI.get_txtDefaultValue();
        }

        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtlinkAPI = new linkAPI();
        String txtMethod = "GetDatavw_SalesInsentive_EmployeeBranch";
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.ResultJsonData(_help.getHTML(strLinkAPI));
        _db.close();
        return JsonData;
    }

    public void DownloadEmployeeBranch(String versionName) throws Exception {
        //ambil linkapi Database sqllite
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
        String txtMethod = "GetDatavw_SalesInsentive_EmployeeBranch";
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
        mEmployeeBranchDA _mEmployeeBranchDA = new mEmployeeBranchDA(_db);
        _mEmployeeBranchDA.DeleteAllDataMConfig(_db);

        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                mEmployeeBranchData _data = new mEmployeeBranchData();
                mEmployeeBranchData _dataProperty = new mEmployeeBranchData();

                _data.set_EmpId((String) innerObj.get("EmpId"));
                _data.set_txtBranchCode((String) innerObj.get("IntBranchId"));
                _data.set_intID((String) innerObj.get("IntID"));
                _data.set_txtBranchCode((String) innerObj.get("TxtBranchCode"));
                _data.set_txtBranchName((String) innerObj.get("TxtBranchName"));
                _data.set_txtNIK((String) innerObj.get("TxtNIK"));
                _data.set_txtName((String) innerObj.get("TxtName"));
                _mEmployeeBranchDA.SaveDataMConfig(_db, _data);
            } else {
                flag = false;
                ErrorMess = (String) innerObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
        _db.close();
    }
}
