package bl;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;

import android.database.sqlite.SQLiteDatabase;
import library.salesforce.common.APIData;
import library.salesforce.common.clsHelper;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mPriceInOutletData;
import library.salesforce.common.mconfigData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.mPriceInOutletDA;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tUserLoginDA;

public class mPriceInOutletBL extends clsMainBL {
	public void saveData(List<mPriceInOutletData> Listdata){
		SQLiteDatabase db=getDb();
		mPriceInOutletDA _mPriceInOutletDA=new mPriceInOutletDA(db);
		_mPriceInOutletDA.DeleteAllData(db);
		for(mPriceInOutletData data:Listdata){
			_mPriceInOutletDA.SaveData(db, data);	
		}
	}
	public List<mPriceInOutletData> GetAllData(){
		SQLiteDatabase db=getDb();
		mPriceInOutletDA _mPriceInOutletDA=new mPriceInOutletDA(db);
		return _mPriceInOutletDA.getAllData(db);
	}
	public mPriceInOutletData getData(String Id){
		SQLiteDatabase db=getDb();
		mPriceInOutletDA _mPriceInOutletDA=new mPriceInOutletDA(db);
		return _mPriceInOutletDA.getData(db, Id);
	}
	public List<mPriceInOutletData> GetAllDataByProductCode(String Id){
		SQLiteDatabase db=getDb();
		mPriceInOutletDA _mPriceInOutletDA=new mPriceInOutletDA(db);
		return _mPriceInOutletDA.getDataByintProductCode(db, Id);
	}
	public List<mPriceInOutletData> getAllDataByCabId(String Id){
		SQLiteDatabase db=getDb();
		mPriceInOutletDA _mPriceInOutletDA=new mPriceInOutletDA(db);
		return _mPriceInOutletDA.getDataAreaByCabId(db, Id);
	}
	public List<mPriceInOutletData> getAllDataBytxtOutletCodeByIntProductCode(String txtOutletCode,String intProductCode){
		SQLiteDatabase db=getDb();
		mPriceInOutletDA _mPriceInOutletDA=new mPriceInOutletDA(db);
		return _mPriceInOutletDA.getDataBytxtOutletCodeByintProductCode(db, txtOutletCode, intProductCode);
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
		return JsonArray;
	}
}
