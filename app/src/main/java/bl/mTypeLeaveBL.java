package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mTypeLeaveMobileData;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mTypeLeaveMobileDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tUserLoginDA;

public class mTypeLeaveBL extends clsMainBL{

	public void saveData(List<mTypeLeaveMobileData> Listdata){
		SQLiteDatabase db=getDb();
		mTypeLeaveMobileDA _mTypeLeaveMobileDA=new mTypeLeaveMobileDA(db);
		_mTypeLeaveMobileDA.DeleteAllData(db);
		for(mTypeLeaveMobileData data:Listdata){
			_mTypeLeaveMobileDA.SaveData(db, data);	
		}
		db.close();
	}
	
	public List<mTypeLeaveMobileData> GetAllData(){
		SQLiteDatabase db=getDb();
		mTypeLeaveMobileDA _TypeLeaveMobileDA = new mTypeLeaveMobileDA(db);
		List<mTypeLeaveMobileData> listData = _TypeLeaveMobileDA.getAllData(db);
		db.close();
		return listData;
	}

	public mTypeLeaveMobileData GetDataByintTypeLeave(String id){
		SQLiteDatabase db=getDb();
		mTypeLeaveMobileDA _TypeLeaveMobileDA = new mTypeLeaveMobileDA(db);
		mTypeLeaveMobileData listData = _TypeLeaveMobileDA.getData(db, id);
		db.close();
		return listData;
	}

	public int  getContactsCount(){
		SQLiteDatabase db=getDb();
		mTypeLeaveMobileDA _mTypeLeaveMobileDA=new mTypeLeaveMobileDA(db);
		int intCount=_mTypeLeaveMobileDA.getContactsCount(db);
		db.close();
		return intCount;
	}
	
	public org.json.simple.JSONArray DownloadTypeLeave2(String versionName) throws Exception{
		//ambil linkapi Database sqllite
		SQLiteDatabase _db=getDb();
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(_db);
		mconfigDA _mconfigDA =new mconfigDA(_db);
		
		String strVal2="";
		mconfigData dataAPI = _mconfigDA.getData(_db,enumConfigData.ApiKalbe.getidConfigData());
		strVal2 = dataAPI.get_txtValue();
		if (dataAPI.get_txtValue() == "") {
			strVal2 = dataAPI.get_txtDefaultValue();
		}
		//ambil version dari webservices
		tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
		clsHelper _help =new clsHelper();
		linkAPI dtlinkAPI=new linkAPI();
		String txtMethod="GetDatamTypeLeaveMobile";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId()+"|" + _dataUserLogin.get_txtRoleId());
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionName);
		String strLinkAPI= dtlinkAPI.QueryString(strVal2);
		String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
		org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(JsonData);
		_db.close();
		return JsonArray;
	}

}
