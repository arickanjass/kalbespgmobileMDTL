package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tPurchaseOrderHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tActivityDA;
import library.spgmobile.dal.tPurchaseOrderHeaderDA;
import library.spgmobile.dal.tUserLoginDA;

public class tActivityBL extends clsMainBL{
	public JSONArray DownloadActivity(String versionName) throws Exception {
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
//		String txtMethod = "GetActivity";
		String txtMethod = "GetDataTActivityMobile";
		JSONObject resJson = new JSONObject();
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("||||||" + _dataUserLogin.get_TxtEmpId());
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionName);

		String strLinkAPI = dtlinkAPI.QueryString(strVal2);
		String JsonData = _help.pushtData(strLinkAPI, dtlinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));

//		String strLinkAPI= dtlinkAPI.QueryString(strVal2);
//		String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));

		org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);

		_db.close();
		return JsonArray;
	}

	public int getCountActivity(){
		SQLiteDatabase _db=getDb();

		tActivityDA _tActivityDA = new tActivityDA(_db);
		List<tActivityData> dt = _tActivityDA.getAllData(_db);
		_db.close();
		return dt.size();
	}

	public void saveData(List<tActivityData> Listdata){
		SQLiteDatabase db=getDb();
		tActivityDA _tActivityDA = new tActivityDA(db);
		for(tActivityData data:Listdata){
			_tActivityDA.SaveDatatActivityData(db, data);
		}
		db.close();
	}

	public List<tActivityData> getDataNew(){
		SQLiteDatabase db=getDb();
		tActivityDA _tActivityDA=new tActivityDA(db);
		List<tActivityData> listData=_tActivityDA.getAllDataNew(db);
		db.close();
		return listData;
	}

	public tActivityData getDataByBitActive(){
		SQLiteDatabase db=getDb();
		tActivityDA _tActivityDA=new tActivityDA(db);
		tActivityData listData=_tActivityDA.getAllDataByBitActive(db);
		db.close();
		return listData;
	}

	public List<tActivityData> getAllData(){
		SQLiteDatabase db=getDb();
		tActivityDA _tActivityDA=new tActivityDA(db);
		List<tActivityData> listData=_tActivityDA.getAllData(db);
		db.close();
		return listData;
	}

	public List<tActivityData> getAllOutlet(String outletName){
		SQLiteDatabase db=getDb();
		tActivityDA _tActivityDA=new tActivityDA(db);
		List<tActivityData> listData=_tActivityDA.getAllOutlet(db, outletName);
		db.close();
		return listData;
	}

	public List<tActivityData> getAllDataByOutletCode(String outletcode){
		SQLiteDatabase _db = getDb();
		tActivityDA _tActivityDA=new tActivityDA(_db);
		List<tActivityData> dt;
		if(outletcode.equals("ALLOUTLET")){
			dt = _tActivityDA.getAllData(_db);
		} else {
			dt = _tActivityDA.getAllDataByOutletCode(_db,outletcode);
		}

		if(dt == null){
			dt = new ArrayList<>(0);
		}
		_db.close();
		return dt;
	}



	public List<tActivityData> getAllDataByIntSyc(String val){
		SQLiteDatabase _db =getDb();
		tActivityDA _tActivityDA = new tActivityDA(_db);
		List<tActivityData> dt = _tActivityDA.getAllDataByIntSyc(_db,val);
		if(dt == null){
			dt = new ArrayList<>(0);
		}
		return dt ;
	}

	public List<tActivityData> getAllDataByIntSycAndOutlet(String val, String outlet){
		SQLiteDatabase _db =getDb();
		tActivityDA _tActivityDA = new tActivityDA(_db);
		List<tActivityData> dt = _tActivityDA.getAllDataByIntSycAndOutlet(_db,val, outlet);
		if(dt == null){
			dt = new ArrayList<>(0);
		}
		return dt ;
	}

}
