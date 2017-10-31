package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;

import java.util.Iterator;
import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsFileAttach_mobile;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mCounterNumberData;
import library.spgmobile.common.mEmployeeSalesProductData;
import library.spgmobile.common.mUserLOBData;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsFileAttach_mobileDA;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.enumCounterData;
import library.spgmobile.dal.mCounterNumberDA;
import library.spgmobile.dal.mEmployeeSalesProductDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tUserLoginDA;

public class mEmployeeSalesProductBL extends clsMainBL{
	public JSONArray DownloadEmployeeSalesProduct(String versionName, List<mUserLOBData> mUserLOBDataList) throws Exception{
		//ambil linkapi Database sqllite
		SQLiteDatabase _db=getDb();
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(_db);
		mconfigDA _mconfigDA =new mconfigDA(_db);

		String strVal2="";
		mconfigData dataAPI = _mconfigDA.getData(db,enumConfigData.ApiKalbe.getidConfigData());
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
		APIData dtAPIDATA=new APIData();
		//String aa=new clsHelper().linkAPI(db);
		Iterator i = JsonArray.iterator();
		Boolean flag=true;
		String ErrorMess="";
		mEmployeeSalesProductDA _mEmployeeBranchDA=new mEmployeeSalesProductDA(_db);
		_mEmployeeBranchDA.deleteAllDataByKN(_db,mUserLOBDataList);
		int intsum= _mEmployeeBranchDA.getContactsCount(_db);
		while (i.hasNext()) {
			org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
			int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
			if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
				intsum+=1;
				mEmployeeSalesProductData _data =new mEmployeeSalesProductData();
				//mEmployeeSalesProductData _dataProperty =new mEmployeeSalesProductData();
				_data.set_intId(String.valueOf(intsum));
				_data.set_decBobot((String) innerObj.get("DecBobot"));
				_data.set_decHJD((String) innerObj.get("DecHJD"));
				_data.set_txtBrandDetailGramCode((String) innerObj.get("TxtBrandDetailGramCode"));
				_data.set_txtNIK((String) innerObj.get("TxtNIK"));
				_data.set_txtName((String) innerObj.get("TxtName"));
				_data.set_txtProductBrandDetailGramName((String) innerObj.get("TxtProductBrandDetailGramName"));
				_data.set_txtProductDetailCode((String) innerObj.get("TxtProductDetailCode"));
				_data.set_txtProductDetailName((String) innerObj.get("TxtProductDetailName"));
				_data.set_txtLobName((String) innerObj.get("TxtLobName"));
				_mEmployeeBranchDA.SaveDataMConfig(db, _data);
			}else{
				flag=false;
				ErrorMess=(String) innerObj.get(dtAPIDATA.strMessage);
				break;
			}
		}
		txtMethod="GetDataNoSalesProduct";
		dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam(_dataUserLogin.get_txtUserId()+"|||");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionName);
		strLinkAPI= dtlinkAPI.QueryString(strVal2);
		JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
		JsonArray= _help.ResultJsonArray(JsonData);
		dtAPIDATA=new APIData();
		i = JsonArray.iterator();
		mCounterNumberDA _mCounterNumberDA=new mCounterNumberDA(_db);
		while (i.hasNext()) {
			org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
			int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
			if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
				mCounterNumberData _data =new mCounterNumberData();
				_data.set_intId(enumCounterData.NoDataSO.getidCounterData());
				_data.set_txtDeskripsi((String) innerObj.get("_pstrMethodRequest"));
				_data.set_txtName((String) innerObj.get("_pstrMethodRequest"));
				_data.set_txtValue((String) innerObj.get("_pstrArgument"));
				_mCounterNumberDA.SaveDataMConfig(db, _data);
			}else{
				flag=false;
				ErrorMess=(String) innerObj.get(dtAPIDATA.strMessage);
				break;
			}
		}
		_db.close();
		return JsonArray;
	}

	public List<mEmployeeSalesProductData> GetAllData(){
		SQLiteDatabase db=getDb();
		mEmployeeSalesProductDA _mEmployeeSalesProductDA= new mEmployeeSalesProductDA(db);
		List<mEmployeeSalesProductData>ListData=_mEmployeeSalesProductDA.getAllData(db);
		db.close();
		return ListData;
	}

	public List<mEmployeeSalesProductData> GetAllDataByKN(List<mUserLOBData> mUserLOBDataList){
		SQLiteDatabase db=getDb();
		mEmployeeSalesProductDA _mEmployeeSalesProductDA= new mEmployeeSalesProductDA(db);
		List<mEmployeeSalesProductData>ListData=_mEmployeeSalesProductDA.getAllDataByKNReso(db, mUserLOBDataList);
		db.close();
		return ListData;
	}

	public List<mEmployeeSalesProductData> GetAllDataByKNNotReso(List<mUserLOBData> mUserLOBDataList){
		SQLiteDatabase db=getDb();
		mEmployeeSalesProductDA _mEmployeeSalesProductDA= new mEmployeeSalesProductDA(db);
		List<mEmployeeSalesProductData>ListData=_mEmployeeSalesProductDA.getAllDataByKN(db, mUserLOBDataList);
		db.close();
		return ListData;
	}

	public List<mEmployeeSalesProductData> getAllDataNotWhere(){
		SQLiteDatabase db=getDb();
		mEmployeeSalesProductDA _mEmployeeSalesProductDA= new mEmployeeSalesProductDA(db);
		List<mEmployeeSalesProductData>ListData=_mEmployeeSalesProductDA.getAllDataNotWhere(db);
		db.close();
		return ListData;
	}
	public List<mEmployeeSalesProductData> GetDataByProductName(String Name){
		SQLiteDatabase db=getDb();
		mEmployeeSalesProductDA _mEmployeeSalesProductDA= new mEmployeeSalesProductDA(db);
		List<mEmployeeSalesProductData>ListData=_mEmployeeSalesProductDA.SearchData(db, "", Name);
		db.close();
		return ListData;
	}

	public int  getContactsCount(){
		SQLiteDatabase db=getDb();
		mEmployeeSalesProductDA _mEmployeeSalesProductDA= new mEmployeeSalesProductDA(db);
		return _mEmployeeSalesProductDA.getContactsCount(db);
	}

	public int  getContactsCountByKN(List<mUserLOBData> mUserLOBDataList){
		SQLiteDatabase db=getDb();
		mEmployeeSalesProductDA _mEmployeeSalesProductDA= new mEmployeeSalesProductDA(db);
		return _mEmployeeSalesProductDA.getContactsCountByKN(db, mUserLOBDataList);
	}

	public int deleteContactByKN(List<mUserLOBData> mUserLOBDataList){
		SQLiteDatabase db=getDb();
		mEmployeeSalesProductDA _mEmployeeSalesProductDA= new mEmployeeSalesProductDA(db);
		_mEmployeeSalesProductDA.deleteContactsCountByKN(db, mUserLOBDataList);
		return  _mEmployeeSalesProductDA.getContactsCount(db);
	}

	public void saveData(mEmployeeSalesProductData data){
		SQLiteDatabase db=getDb();
		mEmployeeSalesProductDA _mEmployeeSalesProductDA= new mEmployeeSalesProductDA(db);
		_mEmployeeSalesProductDA.SaveDataMConfig(db, data);
	}
	public void DeleteAllData(){
		SQLiteDatabase db=getDb();
		mEmployeeSalesProductDA _mEmployeeSalesProductDA= new mEmployeeSalesProductDA(db);
		_mEmployeeSalesProductDA.DeleteAllDataMConfig(db);
	}
}
