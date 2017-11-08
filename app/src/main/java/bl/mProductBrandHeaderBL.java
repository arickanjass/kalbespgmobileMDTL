package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mEmployeeSalesProductData;
import library.spgmobile.common.mProductBrandHeaderData;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tLogDownloadData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mEmployeeSalesProductDA;
import library.spgmobile.dal.mProductBrandHeaderDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tUserLoginDA;

public class mProductBrandHeaderBL extends clsMainBL{
	public JSONArray DownloadBrandHeader(String versionName, String ll_brand) throws Exception{
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
		String txtMethod="GetDatamProductBrandHeader";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("|||");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionName);
		String strLinkAPI= dtlinkAPI.QueryString(strVal2);
		String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
		JSONArray JsonArray= _help.ResultJsonArray(JsonData);
		APIData dtAPIDATA=new APIData();
		//String aa=new clsHelper().linkAPI(db);
		Iterator i = JsonArray.iterator();
		Boolean flag=true;
		String ErrorMess="";
		mProductBrandHeaderDA _mProductBrandHeaderDA=new mProductBrandHeaderDA(_db);
		_mProductBrandHeaderDA.DeleteAllDataMConfig(_db);
		int intsum= _mProductBrandHeaderDA.getContactsCount(_db);
		while (i.hasNext()) {
			org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
			int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));

			String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrArgument()));
			tLogDownloadData _tLogDownloadData = new tLogDownloadData();
			List<tLogDownloadData> tLogDownloadDataList = new ArrayList<>();

			_tLogDownloadData.set_txtModuleName(ll_brand);
			_tLogDownloadData.set_dtLastDownload(pstrArgumet);

			tLogDownloadDataList.add(_tLogDownloadData);
			new tLogDownloadBL().SaveData(tLogDownloadDataList);

			if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){				
				intsum+=1;
				mProductBrandHeaderData _data =new mProductBrandHeaderData();
				//mEmployeeSalesProductData _dataProperty =new mEmployeeSalesProductData();
				_data.set_intmProductUmbrandId((String) innerObj.get("IntmProductUmbrandId"));
				_data.set_txtAliasName((String) innerObj.get("TxtAliasName"));
				_data.set_txtProductBrandCode((String) innerObj.get("TxtProductBrandCode"));
				_data.set_txtProductBrandName((String) innerObj.get("TxtProductBrandName"));
				_mProductBrandHeaderDA.SaveDataMConfig(_db, _data);
			}else{
				flag=false;
				ErrorMess=(String) innerObj.get(dtAPIDATA.strMessage);
				break;
			}
		}
		_db.close();
		return JsonArray;
	}

	public List<mProductBrandHeaderData> GetAllData(){
		SQLiteDatabase db=getDb();
		mProductBrandHeaderDA _mProductBrandHeaderDA= new mProductBrandHeaderDA(db);
		List<mProductBrandHeaderData>ListData=_mProductBrandHeaderDA.getAllData(db);
		db.close();
		return ListData;
	}

    public List<mProductBrandHeaderData> getData(String idBrand) {
        SQLiteDatabase db=getDb();
        mProductBrandHeaderDA _mProductBrandHeaderDA=new mProductBrandHeaderDA(db);
        List<mProductBrandHeaderData> ListData=new ArrayList<mProductBrandHeaderData>();
        if(idBrand.equals("")){
            ListData=_mProductBrandHeaderDA.getAllData(db);
        }else{
            mProductBrandHeaderData dt=new mProductBrandHeaderData();
            dt=_mProductBrandHeaderDA.getData(db, String.valueOf(idBrand));
            ListData.add(dt);
        }
		db.close();
        return ListData;
    }

	public int  getContactsCount(){
		SQLiteDatabase db=getDb();
		mProductBrandHeaderDA _mProductBrandHeaderDA=new mProductBrandHeaderDA(db);
		int intCount=_mProductBrandHeaderDA.getContactsCount(db);
		db.close();
		return intCount;
	}

	public void saveData(mProductBrandHeaderData data){
		SQLiteDatabase db=getDb();
		mProductBrandHeaderDA _mProductBrandHeaderDA=new mProductBrandHeaderDA(db);
		_mProductBrandHeaderDA.SaveDataMConfig(db, data);
		db.close();
	}

	public void DeleteAllData(){
		SQLiteDatabase db=getDb();
		mProductBrandHeaderDA _mProductBrandHeaderDA=new mProductBrandHeaderDA(db);
		_mProductBrandHeaderDA.DeleteAllDataMConfig(db);
		db.close();
	}
}
