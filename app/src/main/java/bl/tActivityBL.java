package bl;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import library.salesforce.common.APIData;
import library.salesforce.common.clsHelper;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mconfigData;
import library.salesforce.common.tActivityData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tActivityDA;
import library.salesforce.dal.tUserLoginDA;

public class tActivityBL extends clsMainBL{
	public JSONArray DownloadActivity(String versionName) throws Exception {
		SQLiteDatabase _db = getDb();
		tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
		mconfigDA _mconfigDA = new mconfigDA(_db);

		String strVal2 = "";
		mconfigData dataAPI = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
		strVal2 = dataAPI.get_txtValue();
		if (dataAPI.get_txtValue() == "") {
			strVal2 = dataAPI.get_txtDefaultValue();
		}
		//ambil version dari webservices
		tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
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

		return dt.size();
	}

	public void saveData(List<tActivityData> Listdata){
		SQLiteDatabase db=getDb();
		tActivityDA _tActivityDA = new tActivityDA(db);
		for(tActivityData data:Listdata){
			_tActivityDA.SaveDatatActivityData(db, data);
		}
	}

	public List<tActivityData> getDataNew(){
		SQLiteDatabase db=getDb();
		tActivityDA _tActivityDA=new tActivityDA(db);
		List<tActivityData> listData=_tActivityDA.getAllDataNew(db);
		return listData;
	}

	public tActivityData getDataByBitActive(){
		SQLiteDatabase db=getDb();
		tActivityDA _tActivityDA=new tActivityDA(db);
		tActivityData listData=_tActivityDA.getAllDataByBitActive(db);
		return listData;
	}

	public List<tActivityData> getAllData(){
		SQLiteDatabase db=getDb();
		tActivityDA _tActivityDA=new tActivityDA(db);
		List<tActivityData> listData=_tActivityDA.getAllData(db);
		return listData;
	}

	public List<tActivityData> getAllDataByOutletCode(String outletcode){
		SQLiteDatabase db=getDb();
		tActivityDA _tActivityDA=new tActivityDA(db);
		List<tActivityData> listData=_tActivityDA.getAllDataByOutletCode(db, outletcode);
		return listData;
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
