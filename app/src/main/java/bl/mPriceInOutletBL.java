package bl;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;

import android.database.sqlite.SQLiteDatabase;
import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mPriceInOutletData;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mPriceInOutletDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tUserLoginDA;

public class mPriceInOutletBL extends clsMainBL {
	public void saveData(List<mPriceInOutletData> Listdata){
		SQLiteDatabase db=getDb();
		mPriceInOutletDA _mPriceInOutletDA=new mPriceInOutletDA(db);
		_mPriceInOutletDA.DeleteAllData(db);
		for(mPriceInOutletData data:Listdata){
			_mPriceInOutletDA.SaveData(db, data);	
		}
		db.close();
	}
	public List<mPriceInOutletData> GetAllData(){
		SQLiteDatabase db=getDb();
		mPriceInOutletDA _mPriceInOutletDA=new mPriceInOutletDA(db);
		List<mPriceInOutletData> listdata=_mPriceInOutletDA.getAllData(db);
		db.close();
		return listdata;
	}
	public mPriceInOutletData getData(String Id){
		SQLiteDatabase db=getDb();
		mPriceInOutletDA _mPriceInOutletDA=new mPriceInOutletDA(db);
		mPriceInOutletData dt =_mPriceInOutletDA.getData(db, Id);
		db.close();
		return dt;
	}
	public List<mPriceInOutletData> GetAllDataByProductCode(String Id){
		SQLiteDatabase db=getDb();
		mPriceInOutletDA _mPriceInOutletDA=new mPriceInOutletDA(db);
		List<mPriceInOutletData> listData=_mPriceInOutletDA.getDataByintProductCode(db, Id);
		db.close();
		return listData;
	}
	public List<mPriceInOutletData> getAllDataByCabId(String Id){
		SQLiteDatabase db=getDb();
		mPriceInOutletDA _mPriceInOutletDA=new mPriceInOutletDA(db);
		List<mPriceInOutletData> listData=_mPriceInOutletDA.getDataAreaByCabId(db, Id);
		db.close();
		return listData;
	}
	public List<mPriceInOutletData> getAllDataBytxtOutletCodeByIntProductCode(String txtOutletCode,String intProductCode){
		SQLiteDatabase db=getDb();
		mPriceInOutletDA _mPriceInOutletDA=new mPriceInOutletDA(db);
		List<mPriceInOutletData> listData=_mPriceInOutletDA.getDataBytxtOutletCodeByintProductCode(db, txtOutletCode, intProductCode);
		db.close();
		return listData;
	}
	
	public void DownloadmPriceInOutlet(String versionName) throws Exception{
		//ambil linkapi Database sqllite
		SQLiteDatabase _db=getDb();
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(_db);
		mconfigDA _mconfigDA =new mconfigDA(_db);
		tUserLoginData dttUserLoginData=new tUserLoginBL().getUserActive(); 
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
		String txtMethod="GetPriceInOutletByEmpIdActive";
		JSONObject resJson = new JSONObject();
		resJson.put("txtNIK", dttUserLoginData.get_TxtEmpId());
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId()+"|||");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionName);
		String strLinkAPI= dtlinkAPI.QueryString(strVal2);
		String JsonData= _help.pushtData(strLinkAPI,String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
		org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(JsonData);
		APIData dtAPIDATA=new APIData();
		//String aa=new clsHelper().linkAPI(db);
		Iterator i = JsonArray.iterator();
		Boolean flag=true;
		String ErrorMess="";
		mPriceInOutletDA _mPriceInOutletDA=new mPriceInOutletDA(_db);
		_mPriceInOutletDA.DeleteAllData(_db);
		int intsum= _mPriceInOutletDA.getContactsCount(_db);
		while (i.hasNext()) {
			org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
			int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
			if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){				
				intsum+=1;
				mPriceInOutletData _data = new mPriceInOutletData();
				_data.set_decPriceHJD((String) innerObj.get("_decPriceHJD"));
				_data.set_intIdItemPrice((String) innerObj.get("_intIdItemPrice"));
				_data.set_intProductCode(String.valueOf(innerObj.get("_intProductCode")));
				_data.set_txtProductName((String) innerObj.get("_txtProductName"));
				_data.set_txtBranchCode((String) innerObj.get("_txtBranchCode"));
				_data.set_txtOutletCode((String) innerObj.get("_txtOutletCode"));
				_data.set_txtOutletName((String) innerObj.get("_txtOutletName"));
				_mPriceInOutletDA.SaveData(_db, _data);
			}else{
				flag=false;
				ErrorMess=(String) innerObj.get(dtAPIDATA.strMessage);
				break;
			}
		}
		_db.close();
	}
	public org.json.simple.JSONArray DownloadmPriceInOutlet2(String versionName) throws Exception{
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
		tUserLoginData dttUserLoginData=new tUserLoginBL().getUserActive();
		JSONObject resJson = new JSONObject();
		resJson.put("txtNIK", dttUserLoginData.get_TxtEmpId());
		//ambil version dari webservices
		tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
		clsHelper _help =new clsHelper();
		linkAPI dtlinkAPI=new linkAPI();
		String txtMethod="GetPriceInOutletByEmpIdActive";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId()+"|||");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionName);
		String strLinkAPI= dtlinkAPI.QueryString(strVal2);
		String JsonData= _help.pushtData(strLinkAPI,String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
		org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(JsonData);
		_db.close();
		return JsonArray;
	}
}
