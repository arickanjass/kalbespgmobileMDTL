package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mTypeSubmissionMobile;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.mTypeSubmissionMobileDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tDeviceInfoUserDA;

/**
 * Created by ASUS ZE on 09/03/2017.
 */

public class mTypeSubmissionMobileBL extends clsMainBL {

    public void saveData(List<mTypeSubmissionMobile> Listdata) {
        SQLiteDatabase db = getDb();
        mTypeSubmissionMobileDA _mTypeSubmissionMobileDA = new mTypeSubmissionMobileDA(db);
        for (mTypeSubmissionMobile data : Listdata) {
            _mTypeSubmissionMobileDA.SaveDataMConfig(db, data);
        }
    }
    public mTypeSubmissionMobile getDataBySubmissionCode(String submissionCode) {
        SQLiteDatabase db = getDb();
        mTypeSubmissionMobileDA _mTypeSubmissionMobileDA = new mTypeSubmissionMobileDA(db);
        mTypeSubmissionMobile typeSubmissionMobile = _mTypeSubmissionMobileDA.getDataForReporting(db, submissionCode);
        return  typeSubmissionMobile;
    }

    public List<mTypeSubmissionMobile> GetAllData() {
        SQLiteDatabase db = getDb();
        mTypeSubmissionMobileDA _mTypeSubmissionMobileDA = new mTypeSubmissionMobileDA(db);
        List<mTypeSubmissionMobile> ListData = _mTypeSubmissionMobileDA.getAllData(db);
        db.close();
        return ListData;
    }

    public mTypeSubmissionMobile GetLastSelected() {
        SQLiteDatabase db = getDb();
        mTypeSubmissionMobileDA _mTypeSubmissionMobileDA = new mTypeSubmissionMobileDA(db);
        mTypeSubmissionMobile ListData = _mTypeSubmissionMobileDA.getLastSelected(db, "1");
        db.close();
        return ListData;
    }

    public void updateLastSelected(String id) {
        SQLiteDatabase db = getDb();
        mTypeSubmissionMobileDA _mTypeSubmissionMobileDA = new mTypeSubmissionMobileDA(db);
        _mTypeSubmissionMobileDA.UpdateLastSelected(db, id);
        db.close();
    }

    public JSONArray DownloadTypeSubmission(String versionApp, String txtUserId, String txtEmpId) throws ParseException {
        SQLiteDatabase db = getDb();
        JSONArray res = new JSONArray();
        mconfigDA _mconfigDA = new mconfigDA(db);
        tDeviceInfoUserData dt = new tDeviceInfoUserDA(db).getData(db, 1);
        String txtDomain = _mconfigDA.getDomainKalbeData(db);
        //String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;
        JSONObject resJson = new JSONObject();
        resJson.put("TxtMasterID", txtEmpId);
        linkAPI dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod("GetDatamTypeSubmissionMobile");
        dtlinkAPI.set_txtParam("");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionApp);
        String strLinkAPI = dtlinkAPI.QueryString(getLinkAPI());
        APIData dtAPIDATA = new APIData();
        clsHelper _clsHelper = new clsHelper();
        String JsonData = _clsHelper.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
        res = _clsHelper.ResultJsonArray(JsonData);
        //String txtParam=
        return res;
    }
}
