package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import library.salesforce.common.APIData;
import library.salesforce.common.clsHelper;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mProductBarcodeData;
import library.salesforce.common.mconfigData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.mProductBarcodeDA;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tUserLoginDA;

public class mProductBarcodeBL extends clsMainBL {
	public void saveData(List<mProductBarcodeData> Listdata){
		SQLiteDatabase db=getDb();
		mProductBarcodeDA _mProductBarcodeDA=new mProductBarcodeDA(db);
		_mProductBarcodeDA.DeleteAllDAta(db);
		for(mProductBarcodeData data:Listdata){
			_mProductBarcodeDA.SaveDatamProductBarcodeData(db, data);	
		}
	}
	public List<mProductBarcodeData> GetAllData(){
		SQLiteDatabase db=getDb();
		mProductBarcodeDA _mProductBarcodeDA=new mProductBarcodeDA(db);
		return _mProductBarcodeDA.getAllData(db);
	}
	public mProductBarcodeData getData(String Id){
		SQLiteDatabase db=getDb();
		mProductBarcodeDA _mProductBarcodeDA=new mProductBarcodeDA(db);
		return _mProductBarcodeDA.getData(db, Id);
	}
	public List<mProductBarcodeData> GetAllDataByProductCode(String Id){
		SQLiteDatabase db=getDb();
		mProductBarcodeDA _mProductBarcodeDA=new mProductBarcodeDA(db);
		return _mProductBarcodeDA.getAllDataByProductCode(db, Id);
	}
	public List<mProductBarcodeData> getAllDataByBarcode(String Id){
		SQLiteDatabase db=getDb();
		mProductBarcodeDA _mProductBarcodeDA=new mProductBarcodeDA(db);
		return _mProductBarcodeDA.getAllDataByBarcode(db, Id);
		
	}
	public List<mProductBarcodeData> GetAllDataWithTypeAndSearch(String Type,String txtSearch){
		SQLiteDatabase db=getDb();
		mProductBarcodeDA _mProductBarcodeDA=new mProductBarcodeDA(db);
		if(Type.contains("1.")){
			return _mProductBarcodeDA.getAllDataByProductName(db, txtSearch);	
		}else{
			List<mProductBarcodeData> tmp=_mProductBarcodeDA.getAllDataByProductCode(db, txtSearch);
			if(tmp==null){
				tmp=new ArrayList<mProductBarcodeData>();
			}
			return tmp;
		}
		
	}
	public void DownloadmProductBarcode(String versionName) throws Exception{
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
		String txtMethod="GetDatavw_SalesInsentive_EmployeeSalesProductDetail";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId()+"|||");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionName);
		String strLinkAPI= dtlinkAPI.QueryString(strVal2);
		String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
		org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(JsonData);
		APIData dtAPIDATA=new APIData();
		//String aa=new clsHelper().linkAPI(db);
		Iterator i = JsonArray.iterator();
		Boolean flag=true;
		String ErrorMess="";
		mProductBarcodeDA _mProductBarcodeDA=new mProductBarcodeDA(_db);
		_mProductBarcodeDA.DeleteAllDAta(_db);
		int intsum= _mProductBarcodeDA.getContactsCount(_db);
		while (i.hasNext()) {
			org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
			int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
			if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){				
				intsum+=1;
				mProductBarcodeData _data = new mProductBarcodeData();
				
				_data.set_intProductCode(String.valueOf(innerObj.get("_intProductCode")));
				_data.set_txtProductCode((String) innerObj.get("_txtProductCode"));
				_data.set_txtBarcode((String) innerObj.get("_txtBarcode"));
				_data.set_txtProductName((String) innerObj.get("_txtProductName"));
				_mProductBarcodeDA.SaveDatamProductBarcodeData(_db, _data);
			}else{
				flag=false;
				ErrorMess=(String) innerObj.get(dtAPIDATA.strMessage);
				break;
			}
		}
		
	}
	public org.json.simple.JSONArray DownloadmProductBarcode2(String versionName) throws Exception{
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
		tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
		clsHelper _help =new clsHelper();
		linkAPI dtlinkAPI=new linkAPI();
		String txtMethod="GetDatavw_SalesInsentive_EmployeeSalesProductDetail";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId()+"|||");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionName);
		String strLinkAPI= dtlinkAPI.QueryString(strVal2);
		String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
		org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(JsonData);

		return JsonArray;
	}
}
