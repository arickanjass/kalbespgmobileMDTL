package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mProductCompetitorData;
import library.spgmobile.common.mUserLOBData;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.mEmployeeSalesProductDA;
import library.spgmobile.dal.mProductCompetitorDA;
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

    public List<mProductCompetitorData> GetAllData(){
        SQLiteDatabase db=getDb();
        mProductCompetitorDA _mProductCompetitorDA=new mProductCompetitorDA(db);
        List<mProductCompetitorData>ListData=_mProductCompetitorDA.getAllData(db);
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
    public int  getContactsCountByKN(List<mUserLOBData> mUserLOBDataList){
        SQLiteDatabase db=getDb();
        mProductCompetitorDA _mProductCompetitorDA=new mProductCompetitorDA(db);
        int intcount=_mProductCompetitorDA.getContactsCountByKN(db, mUserLOBDataList);
        db.close();
        return intcount;
    }
}
