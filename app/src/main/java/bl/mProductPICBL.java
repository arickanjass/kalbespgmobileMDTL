package bl;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.kalbenutritionals.app.kalbespgmobile.mProductPICTempData;
import com.kalbenutritionals.app.kalbespgmobile.mProductSPGTempData;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mProductPICData;
import library.spgmobile.common.mUserLOBData;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.mProductPICDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tDeviceInfoUserDA;

/**
 * Created by ASUS ZE on 24/03/2017.
 */

public class mProductPICBL extends clsMainBL {
    public void deleteAllData() {
        SQLiteDatabase db = getDb();
        mProductPICDA _mProductPICDA = new mProductPICDA(db);
        _mProductPICDA.DeleteAllDataMConfig(db);
        db.close();
    }

    public void deleteAllDataByKN(List<mUserLOBData> mUserLOBDataList) {
        SQLiteDatabase db = getDb();
        mProductPICDA _mProductPICDA = new mProductPICDA(db);
        _mProductPICDA.deleteContactsCountByKN(db, mUserLOBDataList);
        db.close();
    }

    public void saveData(List<mProductPICData> Listdata) {
        SQLiteDatabase db = getDb();
        mProductPICDA _mProductPICDA = new mProductPICDA(db);
        for (mProductPICData data : Listdata) {
            _mProductPICDA.SaveDataMConfig(db, data);
        }
        db.close();
    }

    public void saveDataCustomExec(List<mProductPICTempData> Listdata) {
        SQLiteDatabase db = getDb();
        String sql = "insert into " + new clsHardCode().txtTable_mProductPIC + " (txtNIK, intId, txtName, txtBrandDetailGramCode, txtProductBrandDetailGramName, decHJD, decBobot, txtProductDetailCode, txtProductDetailName, txtLobName, txtMasterId, txtNamaMasterData, txtKeterangan) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(sql);

        for (int i = 0; i < Listdata.size(); i++) {
            if (Listdata.get(i).getPboolValid().toString().equals("1")) {
                stmt.bindString(1, String.valueOf(Listdata.get(i).getTxtNIK()));
                stmt.bindString(2, String.valueOf(i + 1));
                stmt.bindString(3, String.valueOf(Listdata.get(i).getTxtName()));
                stmt.bindString(4, String.valueOf(Listdata.get(i).getTxtBrandDetailGramCode()));
                stmt.bindString(5, String.valueOf(Listdata.get(i).getTxtProductBrandDetailGramName()));
                stmt.bindString(6, String.valueOf(Listdata.get(i).getDecHJD()));
                stmt.bindString(7, String.valueOf(Listdata.get(i).getDecBobot()));
                stmt.bindString(8, String.valueOf(Listdata.get(i).getTxtProductDetailCode()));
                stmt.bindString(9, String.valueOf(Listdata.get(i).getTxtProductDetailName()));
                stmt.bindString(10, String.valueOf(Listdata.get(i).getTxtLobName()));
                stmt.bindString(11, String.valueOf(Listdata.get(i).getTxtMasterId()));
                stmt.bindString(12, String.valueOf(Listdata.get(i).getTxtNamaMasterData()));
                stmt.bindString(13, String.valueOf(Listdata.get(i).getTxtKeterangan()));

                stmt.executeInsert();
                stmt.clearBindings();
            }
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<mProductPICData> GetAllData() {
        SQLiteDatabase db = getDb();
        mProductPICDA _mProductPICDA = new mProductPICDA(db);
        List<mProductPICData> ListData = _mProductPICDA.getAllData(db);
        db.close();
        return ListData;
    }

    public List<mProductPICData> GetAllDataByKN(List<mUserLOBData> _mUserLOBData, String txtNIK ) {
        SQLiteDatabase db = getDb();
        mProductPICDA _mProductPICDA = new mProductPICDA(db);
        List<mProductPICData> ListData = _mProductPICDA.getAllDataByKN(db, _mUserLOBData, txtNIK);
        db.close();
        return ListData;
    }

    public List<mProductPICData> GetDataByMasterId(String masterId) {
        SQLiteDatabase db = getDb();
        mProductPICDA _mProductPICDA = new mProductPICDA(db);
        List<mProductPICData> ListData = _mProductPICDA.getDataByMasterId(db, masterId);
        db.close();
        return ListData;
    }

    public List<mProductPICData> GetDataByMasterIdByKN(String masterId, List<mUserLOBData> mUserLOBDataList) {
        SQLiteDatabase db = getDb();
        mProductPICDA _mProductPICDA = new mProductPICDA(db);
        List<mProductPICData> ListData = _mProductPICDA.getDataByMasterIdByKN(db, masterId, mUserLOBDataList);
        db.close();
        return ListData;
    }

    public int getContactCount() {
        int count = 0;
        SQLiteDatabase db = getDb();
        mProductPICDA _mProductPICDA = new mProductPICDA(db);
        count = _mProductPICDA.getContactsCount(db);
        db.close();
        return count;
    }

    public int getContactCountByKN(List<mUserLOBData> mUserLOBDataList) {
        int count = 0;
        SQLiteDatabase db = getDb();
        mProductPICDA _mProductPICDA = new mProductPICDA(db);
        count = _mProductPICDA.getContactsCountByKN(db, mUserLOBDataList);
        db.close();
        return count;
    }
    public int getContactCountByKN(List<mUserLOBData> mUserLOBDataList,String txtNik) {
        int count = 0;
        SQLiteDatabase db = getDb();
        mProductPICDA _mProductPICDA = new mProductPICDA(db);
        count = _mProductPICDA.getContactsCountByKN(db, mUserLOBDataList,txtNik);
        db.close();
        return count;
    }
    public int getContactCountSubId() {
        int count = 0;
        SQLiteDatabase db = getDb();
        mProductPICDA _mProductPICDA = new mProductPICDA(db);
        count = _mProductPICDA.getContactsCountDataSubmissionId(db);
        db.close();
        return count;
    }

    public JSONArray DownloadProductPIC(String versionApp, String txtUserId, String txtEmpId) throws ParseException {
        SQLiteDatabase db = getDb();
        JSONArray res = new JSONArray();
        mconfigDA _mconfigDA = new mconfigDA(db);
        tDeviceInfoUserData dt = new tDeviceInfoUserDA(db).getData(db, 1);
        String txtDomain = _mconfigDA.getDomainKalbeData(db);
        //String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;
        JSONObject resJson = new JSONObject();
        resJson.put("TxtNIK", txtEmpId);
        linkAPI dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod("GetDatavw_SalesInsentive_EmployeeSalesProductPIC");
        dtlinkAPI.set_txtParam("");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionApp);
        String strLinkAPI = dtlinkAPI.QueryString(getLinkAPI());
        APIData dtAPIDATA = new APIData();
        clsHelper _clsHelper = new clsHelper();
        String JsonData = _clsHelper.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
        res = _clsHelper.ResultJsonArray(JsonData);
        db.close();
        //String txtParam=
        return res;
    }

    public String DownloadProductPICEnhance(String versionApp, String txtUserId, String txtEmpId) throws ParseException {
        JSONObject resJson = new JSONObject();
        resJson.put("TxtNIK", txtEmpId);
        linkAPI dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod("GetDatavw_SalesInsentive_EmployeeSalesProductPIC");
        dtlinkAPI.set_txtParam("");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionApp);
        String strLinkAPI = dtlinkAPI.QueryString(getLinkAPI());
        clsHelper _clsHelper = new clsHelper();
        return _clsHelper.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
    }
}
