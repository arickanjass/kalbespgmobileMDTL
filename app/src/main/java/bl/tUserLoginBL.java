package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tDeviceInfoUserDA;
import library.spgmobile.dal.tUserLoginDA;

public class tUserLoginBL extends clsMainBL{
	public JSONArray Logout(String versionApp) throws ParseException{
		SQLiteDatabase db=getDb();
		JSONArray res=new JSONArray();
		mconfigDA _mconfigDA=new mconfigDA(db);
		tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		List<tUserLoginData> dttAbsenUserData= _tUserLoginDA.getUserLoginNow(db);

		String txtDomain= _mconfigDA.getDomainKalbeData(db);
		//String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;

		linkAPI dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod("tUserLogin_mobileLogOut");
		dtlinkAPI.set_txtParam(dttAbsenUserData.get(0).get_txtDataId());
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionApp);
		String strLinkAPI= dtlinkAPI.QueryString(getLinkAPI());
		APIData dtAPIDATA=new APIData();
		clsHelper _clsHelper=new clsHelper();
		String JsonData= _clsHelper.pushtData(strLinkAPI, "", Integer.valueOf(getBackGroundServiceOnline()));
		res= _clsHelper.ResultJsonArray(JsonData);
		db.close();
		//String txtParam=
		return res;
	}

	public JSONArray LogoutFromPushData(String versionApp) throws ParseException{
		SQLiteDatabase db=getDb();
		JSONArray res=new JSONArray();
		mconfigDA _mconfigDA=new mconfigDA(db);
		tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		List<tUserLoginData> dttAbsenUserData= _tUserLoginDA.getAllData(db);

		String txtDomain= _mconfigDA.getDomainKalbeData(db);
		//String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;

		linkAPI dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod("tUserLogin_mobileLogOut");
		dtlinkAPI.set_txtParam(dttAbsenUserData.get(0).get_txtDataId());
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionApp);
		String strLinkAPI= dtlinkAPI.QueryString(getLinkAPI());
		APIData dtAPIDATA=new APIData();
		clsHelper _clsHelper=new clsHelper();
		String JsonData= _clsHelper.pushtData(strLinkAPI, "", Integer.valueOf(getBackGroundServiceOnline()));
		res= _clsHelper.ResultJsonArray(JsonData);
		//String txtParam=
		db.close();
		return res;
	}

	public JSONArray Login(String txtUserName,String txtPass, String intRoleId,String versionApp) throws ParseException{
		SQLiteDatabase db=getDb();
		JSONArray res=new JSONArray();
		mconfigDA _mconfigDA=new mconfigDA(db);
		tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
		String txtDomain= _mconfigDA.getDomainKalbeData(db);
		//String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;
		JSONObject resJson = new JSONObject();
		resJson.put("domain", txtDomain);
		resJson.put("username", txtUserName);
		resJson.put("pass", txtPass);
		resJson.put("deviceid", "");
		resJson.put("imeiNumber", dt.get_txtImei());
		resJson.put("version", dt.get_txtVersion());
		resJson.put("device", dt.get_txtDevice());
		resJson.put("model", dt.get_txtModel());
		resJson.put("introle", intRoleId);
		linkAPI dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod("GetDataMWebUserWithActiveDirectoryAndDatabaseNewInsentiveAndMenuAccess");
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
	public JSONArray LoginNew(String txtUserName,String txtPass, String intRoleId,String txtOutletCode, String txtOutletName, String txtBranchCode, String versionApp) throws ParseException{
		SQLiteDatabase db=getDb();
		JSONArray res=new JSONArray();
		mconfigDA _mconfigDA=new mconfigDA(db);
		tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
		String txtDomain= _mconfigDA.getDomainKalbeData(db);
		//String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;
		JSONObject resJson = new JSONObject();
		resJson.put("domain", txtDomain);
		resJson.put("username", txtUserName);
		resJson.put("pass", txtPass);
		resJson.put("deviceid", "");
		resJson.put("imeiNumber", dt.get_txtImei().toString());
		resJson.put("version", dt.get_txtVersion());
		resJson.put("device", dt.get_txtDevice());
		resJson.put("model", dt.get_txtModel());
		resJson.put("introle", intRoleId);
		resJson.put("txtOutletCode", txtOutletCode);
		resJson.put("txtOutletName", txtOutletName);
		resJson.put("txtBranchCode", txtBranchCode);
        mconfigData dataAPI = _mconfigDA.getData(db, enumConfigData.TypeMobile.getidConfigData());
        String strVal2 = "";
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        resJson.put("TypeMobile", strVal2);
		linkAPI dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod("GetDataMWebUserWithActiveDirectoryAndDatabaseNewInsentiveAndMenuAccessTypeMobile");
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

	public JSONArray resetPassword(String txtUserName, String versionApp) throws ParseException{
		SQLiteDatabase db=getDb();
		JSONArray res=new JSONArray();
		mconfigDA _mconfigDA=new mconfigDA(db);
		tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
		String txtDomain= _mconfigDA.getDomainKalbeData(db);
		//String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;
		JSONObject resJson = new JSONObject();
		resJson.put("txtUserID", txtUserName);
		linkAPI dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod("ForgotPassword");
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

	public JSONArray changePassword(String txtUserName,String oldPass, String newPass, String versionApp) throws ParseException{
		SQLiteDatabase db=getDb();
		JSONArray res=new JSONArray();
		mconfigDA _mconfigDA=new mconfigDA(db);
		tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
		String txtDomain= _mconfigDA.getDomainKalbeData(db);
		//String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;
		JSONObject resJson = new JSONObject();
		resJson.put("txtUserID", txtUserName);
		resJson.put("ptxtPasswordOLD", oldPass);
		resJson.put("ptxtPasswordNEW", newPass);
		linkAPI dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod("ChangePassword");
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

	public void saveData(tUserLoginData data){
		SQLiteDatabase db=getDb();
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		_tUserLoginDA.SaveDatatUserLoginData(db, data);
		db.close();
	}

    public tUserLoginData getUserLogin(){
        SQLiteDatabase db=getDb();
		tUserLoginData dt=new tUserLoginDA(db).getData(db, 1);
		db.close();
        return dt;
    }

	public tUserLoginData getUserLoginByUserId(String id){
		SQLiteDatabase db=getDb();
		tUserLoginData dt=new tUserLoginDA(db).getDataByUserId(db, id);
		db.close();
		return dt;
	}
}
