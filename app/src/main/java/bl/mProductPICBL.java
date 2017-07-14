package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

import library.salesforce.common.APIData;
import library.salesforce.common.clsHelper;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mProductPICData;
import library.salesforce.common.tDeviceInfoUserData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.mProductPICDA;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tDeviceInfoUserDA;

/**
 * Created by ASUS ZE on 24/03/2017.
 */

public class mProductPICBL extends clsMainBL {
    public void deleteAllData() {
        SQLiteDatabase db = getDb();
        mProductPICDA _mProductPICDA=new mProductPICDA(db);
        _mProductPICDA.DeleteAllDataMConfig(db);
    }
    public void saveData(List<mProductPICData> Listdata) {
        SQLiteDatabase db = getDb();
        mProductPICDA _mProductPICDA=new mProductPICDA(db);
        for (mProductPICData data : Listdata) {
            _mProductPICDA.SaveDataMConfig(db, data);
        }
    }

    public List<mProductPICData> GetAllData(){
        SQLiteDatabase db=getDb();
        mProductPICDA _mProductPICDA=new mProductPICDA(db);
        List<mProductPICData>ListData=_mProductPICDA.getAllData(db);
        db.close();
        return ListData;
    }

    public List<mProductPICData> GetDataByMasterId(String masterId){
        SQLiteDatabase db=getDb();
        mProductPICDA _mProductPICDA=new mProductPICDA(db);
        List<mProductPICData>ListData=_mProductPICDA.getDataByMasterId(db, masterId);
        db.close();
        return ListData;
    }

    public int getContactCount(){
        int count = 0;
        mProductPICDA _mProductPICDA=new mProductPICDA(db);
        count = _mProductPICDA.getContactsCount(db);
        return count;
    }

    public JSONArray DownloadProductPIC(String versionApp, String txtUserId, String txtEmpId) throws ParseException {
        SQLiteDatabase db=getDb();
        JSONArray res=new JSONArray();
        mconfigDA _mconfigDA=new mconfigDA(db);
        tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
        String txtDomain= _mconfigDA.getDomainKalbeData(db);
        //String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;
        JSONObject resJson = new JSONObject();
        resJson.put("TxtNIK", txtEmpId);
        linkAPI dtlinkAPI=new linkAPI();
        dtlinkAPI.set_txtMethod("GetDatavw_SalesInsentive_EmployeeSalesProductPIC");
        dtlinkAPI.set_txtParam("");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionApp);
        String strLinkAPI= dtlinkAPI.QueryString(getLinkAPI());
        APIData dtAPIDATA=new APIData();
        clsHelper _clsHelper=new clsHelper();
        String JsonData= _clsHelper.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
        res= _clsHelper.ResultJsonArray(JsonData);
        //String txtParam=
        return res;
    }
}
