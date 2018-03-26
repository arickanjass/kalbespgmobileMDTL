package bl;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.kalbenutritionals.app.kalbespgmobile.mProductCompTempData;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mProductCompetitorData;
import library.spgmobile.common.mUserLOBData;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.mEmployeeSalesProductDA;
import library.spgmobile.dal.mProductCompetitorDA;
import library.spgmobile.dal.mProductSPGDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tDeviceInfoUserDA;

/**
 * Created by ASUS ZE on 08/03/2017.
 */

public class mProductCompetitorBL extends clsMainBL {

    public void deleteAllData() {
        SQLiteDatabase db = getDb();
        mProductCompetitorDA _mProductCompetitorDA=new mProductCompetitorDA(db);
        _mProductCompetitorDA.DeleteAllDataMConfig(db);
        db.close();
    }
    public void deleteAllDataByKN(List<mUserLOBData> mUserLOBDataList) {
        SQLiteDatabase db = getDb();
        mProductCompetitorDA _mProductCompetitorDA=new mProductCompetitorDA(db);
        _mProductCompetitorDA.deleteContactsCountByKN(db, mUserLOBDataList);
        db.close();
    }
    public void saveData(List<mProductCompetitorData> Listdata) {
        SQLiteDatabase db = getDb();
        mProductCompetitorDA _mProductCompetitorDA=new mProductCompetitorDA(db);
        for (mProductCompetitorData data : Listdata) {
            _mProductCompetitorDA.SaveDataMConfig(db, data);
        }
        db.close();
    }

    public void saveDataCustomExec(List<mProductCompTempData> Listdata) {
        SQLiteDatabase db = getDb();
        String sql = "insert into "+ new clsHardCode().txtTable_mProductCompetitorData + " (txtID, txtProductDetailCode, txtLobName, GroupProduct, txtProdukid, txtProdukKompetitorID, txtCRMCode, txtNIK, txtName) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(sql);

        for (int i = 0; i < Listdata.size(); i++) {
            stmt.bindString(1, new clsHelper().GenerateGuid());
            stmt.bindString(2, Listdata.get(i).getTxtProductDetailCode());
            stmt.bindString(3, Listdata.get(i).getTxtLobName());
            stmt.bindString(4, Listdata.get(i).getTxtGroupProduct());
            stmt.bindString(5, Listdata.get(i).getTxtProdukid());
            stmt.bindString(6, Listdata.get(i).getTxtProdukKompetitorID());
            stmt.bindString(7, Listdata.get(i).getTxtBranchCRMCode());
            stmt.bindString(8, Listdata.get(i).getTxtNIK());
            stmt.bindString(9, Listdata.get(i).getTxtName());
            
            stmt.executeInsert();
            stmt.clearBindings();
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<mProductCompetitorData> GetAllData(){
        SQLiteDatabase db=getDb();
        mProductCompetitorDA _mProductCompetitorDA=new mProductCompetitorDA(db);
        List<mProductCompetitorData>ListData=_mProductCompetitorDA.getAllData(db);
        db.close();
        return ListData;
    }

    public List<mProductCompetitorData> GetAllDataByKN(List<mUserLOBData> _mUserLOBData, String txtNIK){
        SQLiteDatabase db=getDb();
        mProductCompetitorDA _mProductCompetitorDA=new mProductCompetitorDA(db);
        List<mProductCompetitorData>ListData=_mProductCompetitorDA.getAllDataByKN(db, _mUserLOBData, txtNIK);
        db.close();
        return ListData;
    }

    public List<mProductCompetitorData> GetListDataByProductKN(String idProductKN){
        SQLiteDatabase db=getDb();
        mProductCompetitorDA _mProductCompetitorDA=new mProductCompetitorDA(db);
        List<mProductCompetitorData>ListData=_mProductCompetitorDA.getListDataByProductKN(db, idProductKN);
        db.close();
        return ListData;
    }

    public List<mProductCompetitorData> GetListDataByProductKNByKN(String idProductKN, List<mUserLOBData> mUserLOBDataList){
        SQLiteDatabase db=getDb();
        mProductCompetitorDA _mProductCompetitorDA=new mProductCompetitorDA(db);
        List<mProductCompetitorData>ListData=_mProductCompetitorDA.getListDataByProductKNByKN(db, idProductKN, mUserLOBDataList);
        db.close();
        return ListData;
    }

    public JSONArray DownloadProdctCompetitor(String versionApp, String txtUserId, String txtEmpId) throws ParseException {
        SQLiteDatabase db=getDb();
        JSONArray res=new JSONArray();
        mconfigDA _mconfigDA=new mconfigDA(db);
        tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
        String txtDomain= _mconfigDA.getDomainKalbeData(db);
        //String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;
        JSONObject resJson = new JSONObject();
        resJson.put("TxtNIK", txtEmpId);
        resJson.put("TxtLobName", "");
        resJson.put("TxtProductDetailCode", "");
        linkAPI dtlinkAPI=new linkAPI();
        dtlinkAPI.set_txtMethod("GetDatavw_SalesInsentive_EmployeeSalesProductCompetitor");
        dtlinkAPI.set_txtParam("");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionApp);
        String strLinkAPI= dtlinkAPI.QueryString(getLinkAPI());
        APIData dtAPIDATA=new APIData();
        clsHelper _clsHelper=new clsHelper();
        String JsonData= _clsHelper.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
        res= _clsHelper.ResultJsonArray(JsonData);
        //String txtParam=
        db.close();
        return res;
    }

    public String DownloadProdctCompetitorEnhance(String versionApp, String txtUserId, String txtEmpId) throws ParseException {
        JSONObject resJson = new JSONObject();
        resJson.put("TxtNIK", txtEmpId);
        resJson.put("TxtLobName", "");
        resJson.put("TxtProductDetailCode", "");
        linkAPI dtlinkAPI=new linkAPI();
        dtlinkAPI.set_txtMethod("GetDatavw_SalesInsentive_EmployeeSalesProductCompetitor");
        dtlinkAPI.set_txtParam("");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionApp);
        String strLinkAPI= dtlinkAPI.QueryString(getLinkAPI());
        clsHelper _clsHelper=new clsHelper();
        return _clsHelper.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
    }
    public int  getContactsCountByKN(List<mUserLOBData> mUserLOBDataList,String txtNIK){
        SQLiteDatabase db=getDb();
        mProductCompetitorDA _mProductCompetitorDA=new mProductCompetitorDA(db);
        int intcount=_mProductCompetitorDA.getContactsCountByKN(db, mUserLOBDataList,txtNIK);
        db.close();
        return intcount;
    }
    public int  getContactsCountByKN(List<mUserLOBData> mUserLOBDataList){
        SQLiteDatabase db=getDb();
        mProductCompetitorDA _mProductCompetitorDA=new mProductCompetitorDA(db);
        int intcount=_mProductCompetitorDA.getContactsCountByKN(db, mUserLOBDataList);
        db.close();
        return intcount;
    }

//    public int getContactCountBySubCode(String txtSubCode){
//        int count = 0;
//        SQLiteDatabase db=getDb();
//        mProductCompetitorDA _mProductCompetitorDA=new mProductCompetitorDA(db);
//        count = _mProductCompetitorDA.getContactsCountBySubCode(db, txtSubCode);
//        db.close();
//        return count;
//    }
}
