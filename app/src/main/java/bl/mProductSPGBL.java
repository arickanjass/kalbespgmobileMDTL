package bl;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.kalbenutritionals.app.kalbespgmobile.Network.mProductSPGTempData;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mProductSPGData;
import library.spgmobile.common.mUserLOBData;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.mProductSPGDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tDeviceInfoUserDA;

/**
 * Created by ASUS ZE on 24/03/2017.
 */

public class mProductSPGBL extends clsMainBL {
    public void deleteAllData() {
        SQLiteDatabase db = getDb();
        mProductSPGDA _mProductSPGDA=new mProductSPGDA(db);
        _mProductSPGDA.DeleteAllDataMConfig(db);
        db.close();
    }
    public void deleteAllDataByKN(List<mUserLOBData> mUserLOBDataList) {
        SQLiteDatabase db = getDb();
        mProductSPGDA _mProductSPGDA=new mProductSPGDA(db);
        _mProductSPGDA.deleteContactsCountByKN(db, mUserLOBDataList);
        db.close();
    }
    public void saveData(List<mProductSPGData> Listdata) {
        SQLiteDatabase db = getDb();
        mProductSPGDA _mProductSPGDA=new mProductSPGDA(db);
        for (mProductSPGData data : Listdata) {
            _mProductSPGDA.SaveDataMConfig(db, data);
        }
        db.close();
    }

    public void saveDataCustomExec(List<mProductSPGTempData> Listdata) {
        SQLiteDatabase db = getDb();
        String sql = "insert into " + new clsHardCode().txtTable_mProductSPG + " (txtNIK, intId, txtName, txtBrandDetailGramCode, txtProductBrandDetailGramName, decHJD, decBobot, txtProductDetailCode, txtProductDetailName, txtLobName, txtMasterId, txtNamaMasterData, txtKeterangan) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

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

    public List<mProductSPGData> GetAllData(){
        SQLiteDatabase db=getDb();
        mProductSPGDA _mProductSPGDA=new mProductSPGDA(db);
        List<mProductSPGData>ListData=_mProductSPGDA.getAllData(db);
        db.close();
        return ListData;
    }

    public List<mProductSPGData> GetAllDataByKN(List<mUserLOBData> _mUserLOBData, String txtNIK){
        SQLiteDatabase db=getDb();
        mProductSPGDA _mProductSPGDA=new mProductSPGDA(db);
        List<mProductSPGData>ListData=_mProductSPGDA.getAllDataByKN(db, _mUserLOBData, txtNIK);
        db.close();
        return ListData;
    }

    public List<mProductSPGData> GetDataByMasterId(String masterId){
        SQLiteDatabase db=getDb();
        mProductSPGDA _mProductSPGDA=new mProductSPGDA(db);
        List<mProductSPGData>ListData=_mProductSPGDA.getDataByMasterId(db,masterId);
        db.close();
        return ListData;
    }

    public List<mProductSPGData> GetDataByMasterIdByKN(String masterId, List<mUserLOBData> mUserLOBDataList){
        SQLiteDatabase db=getDb();
        mProductSPGDA _mProductSPGDA=new mProductSPGDA(db);
        List<mProductSPGData>ListData=_mProductSPGDA.getDataByMasterIdByKN(db,masterId, mUserLOBDataList);
        db.close();
        return ListData;
    }

    public int getContactCount(){
        int count = 0;
        SQLiteDatabase db=getDb();
        mProductSPGDA _mProductSPGDA=new mProductSPGDA(db);
        count = _mProductSPGDA.getContactsCount(db);
        db.close();
        return count;
    }

    public int getContactCountByKN(List<mUserLOBData> mUserLOBDataList){
        int count = 0;
        SQLiteDatabase db=getDb();
        mProductSPGDA _mProductSPGDA=new mProductSPGDA(db);
        count = _mProductSPGDA.getContactsCountByKN(db, mUserLOBDataList);
        db.close();
        return count;
    }

    public int getContactCountBySubCode(String txtSubCode){
        int count = 0;
        SQLiteDatabase db=getDb();
        mProductSPGDA _mProductSPGDA=new mProductSPGDA(db);
        count = _mProductSPGDA.getContactsCountBySubCode(db, txtSubCode);
        db.close();
        return count;
    }

    public int getContactCountByKN(List<mUserLOBData> mUserLOBDataList,String txtNik){
        int count = 0;
        SQLiteDatabase db=getDb();
        mProductSPGDA _mProductSPGDA=new mProductSPGDA(db);
        count = _mProductSPGDA.getContactsCountByKN(db, mUserLOBDataList,txtNik);
        db.close();
        return count;
    }
    public int getContactCountSubId(){
        int count = 0;
        SQLiteDatabase db=getDb();
        mProductSPGDA _mProductSPGDA=new mProductSPGDA(db);
        count = _mProductSPGDA.getContactsCountDataSubmissionId(db);
        db.close();
        return count;
    }

    public String DownloadProductSPGEnhance(String versionApp, String txtUserId, String txtEmpId) throws ParseException {
        SQLiteDatabase db=getDb();
        JSONObject resJson = new JSONObject();
        resJson.put("TxtNIK", txtEmpId);
        linkAPI dtlinkAPI=new linkAPI();
        dtlinkAPI.set_txtMethod("GetDatavw_SalesInsentive_EmployeeSalesProductSPG");
        dtlinkAPI.set_txtParam("");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionApp);
        String strLinkAPI= dtlinkAPI.QueryString(getLinkAPI());
        clsHelper _clsHelper=new clsHelper();
        String JsonData= _clsHelper.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
        db.close();
        return JsonData;
    }

    public JSONArray DownloadProductSPG(String versionApp, String txtUserId, String txtEmpId) throws ParseException {
        SQLiteDatabase db=getDb();
        JSONArray res=new JSONArray();
        mconfigDA _mconfigDA=new mconfigDA(db);
        tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
        String txtDomain= _mconfigDA.getDomainKalbeData(db);
        //String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;
        JSONObject resJson = new JSONObject();
        resJson.put("TxtNIK", txtEmpId);
        linkAPI dtlinkAPI=new linkAPI();
        dtlinkAPI.set_txtMethod("GetDatavw_SalesInsentive_EmployeeSalesProductSPG");
        dtlinkAPI.set_txtParam("");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionApp);
        String strLinkAPI= dtlinkAPI.QueryString(getLinkAPI());
        APIData dtAPIDATA=new APIData();
        clsHelper _clsHelper=new clsHelper();
        String JsonData= _clsHelper.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
        res= _clsHelper.ResultJsonArray(JsonData);
        db.close();
        //String txtParam=
        return res;
    }
}
