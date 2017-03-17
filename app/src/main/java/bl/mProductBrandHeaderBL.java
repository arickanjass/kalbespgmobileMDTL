package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import library.salesforce.common.APIData;
import library.salesforce.common.clsHelper;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mEmployeeSalesProductData;
import library.salesforce.common.mProductBrandHeaderData;
import library.salesforce.common.mconfigData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.mEmployeeSalesProductDA;
import library.salesforce.dal.mProductBrandHeaderDA;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tUserLoginDA;

public class mProductBrandHeaderBL extends clsMainBL{
	public JSONArray DownloadBrandHeader(String versionName) throws Exception{
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
			if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){				
				intsum+=1;
				mProductBrandHeaderData _data =new mProductBrandHeaderData();
				//mEmployeeSalesProductData _dataProperty =new mEmployeeSalesProductData();
				_data.set_intmProductUmbrandId((String) innerObj.get("IntmProductUmbrandId"));
				_data.set_txtAliasName((String) innerObj.get("TxtAliasName"));
				_data.set_txtProductBrandCode((String) innerObj.get("TxtProductBrandCode"));
				_data.set_txtProductBrandName((String) innerObj.get("TxtProductBrandName"));
				_mProductBrandHeaderDA.SaveDataMConfig(db, _data);
			}else{
				flag=false;
				ErrorMess=(String) innerObj.get(dtAPIDATA.strMessage);
				break;
			}
		}
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
        return ListData;
    }
}
