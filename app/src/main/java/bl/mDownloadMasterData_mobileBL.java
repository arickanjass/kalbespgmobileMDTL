package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.Iterator;
import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mDownloadMasterData_mobileData;
import library.spgmobile.common.mPriceInOutletData;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mDownloadMasterData_mobileDA;
import library.spgmobile.dal.mPriceInOutletDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tDeviceInfoUserDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by ASUS ZE on 02/05/2017.
 */

public class mDownloadMasterData_mobileBL extends clsMainBL {
    public void deleteAllData() {
        SQLiteDatabase db = getDb();
        mDownloadMasterData_mobileDA _mDownloadMasterData_mobileDA=new mDownloadMasterData_mobileDA(db);
        _mDownloadMasterData_mobileDA.DeleteAllDataMConfig(db);
    }
    public List<mDownloadMasterData_mobileData> GetAllData(){
        SQLiteDatabase db=getDb();
        mDownloadMasterData_mobileDA _mDownloadMasterData_mobileDA=new mDownloadMasterData_mobileDA(db);
        List<mDownloadMasterData_mobileData>ListData=_mDownloadMasterData_mobileDA.getAllData(db);
        db.close();
        return ListData;
    }
    public List<mDownloadMasterData_mobileData> getAllDataDistict(){
        SQLiteDatabase db=getDb();
        mDownloadMasterData_mobileDA _mDownloadMasterData_mobileDA=new mDownloadMasterData_mobileDA(db);
        List<mDownloadMasterData_mobileData>ListData=_mDownloadMasterData_mobileDA.getAllDataDistict(db);
        db.close();
        return ListData;
    }
    public int getContactCount(){
        int count = 0;
        mDownloadMasterData_mobileDA _mDownloadMasterData_mobileDA=new mDownloadMasterData_mobileDA(db);
        count = _mDownloadMasterData_mobileDA.getContactsCount(db);
        return count;
    }

    public void SaveData(List<mDownloadMasterData_mobileData> Listdata) {
        SQLiteDatabase db = getDb();
        mDownloadMasterData_mobileDA _mDownloadMasterData_mobileDA = new mDownloadMasterData_mobileDA(db);
        _mDownloadMasterData_mobileDA.DeleteAllDataMConfig(db);
        Long index = Long.valueOf(_mDownloadMasterData_mobileDA.getContactsCount(db) + 1);
        for (mDownloadMasterData_mobileData data : Listdata) {
            data.set_intId(String.valueOf(index));
            _mDownloadMasterData_mobileDA.SaveDataMConfig(db, data);
            index += 1;
        }
        db.close();
    }

    public JSONArray DownloadmDownloadMasterData_mobile(String versionApp, String roleId) throws ParseException {
        SQLiteDatabase db=getDb();
        JSONArray res=new JSONArray();
        mconfigDA _mconfigDA=new mconfigDA(db);
        tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
        String txtDomain= _mconfigDA.getDomainKalbeData(db);
        //String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;
        JSONObject resJson = new JSONObject();
        resJson.put("IntRoleId", "100");
        resJson.put("TxtVersionApp", versionApp);
        linkAPI dtlinkAPI=new linkAPI();
        dtlinkAPI.set_txtMethod("GetDatatDownloadMasterData_mobile");
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

    public void GetBundleMasterAndTransactionAll(String versionName) throws Exception{
        //ambil linkapi Database sqllite
        SQLiteDatabase _db=getDb();
        tUserLoginDA _tUserLoginDA=new tUserLoginDA(_db);
        mconfigDA _mconfigDA =new mconfigDA(_db);
        tUserLoginData dttUserLoginData=new tUserLoginBL().getUserActive();
        String strVal2="";
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        //ambil version dari webservices
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        clsHelper _help =new clsHelper();
        linkAPI dtlinkAPI=new linkAPI();
        String txtMethod="GetBundleMasterAndTransactionAll";
        JSONObject resJson = new JSONObject();
        resJson.put("txtNIK", dttUserLoginData.get_TxtEmpId());
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId()+"|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strLinkAPI= dtlinkAPI.QueryString(strVal2);
        String JsonData= _help.pushtData(strLinkAPI,String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
        org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(JsonData);
    }
}
