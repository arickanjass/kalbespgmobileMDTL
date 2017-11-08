package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tNotificationData;
import library.spgmobile.dal.tNotificationDA;

public class tNotificationBL extends clsMainBL{
	public void saveData(List<tNotificationData> Listdata){
		SQLiteDatabase db=getDb();
		tNotificationDA _tNotificationDA=new tNotificationDA(db);		
		for(tNotificationData data:Listdata){
			_tNotificationDA.SaveDataMConfig(db, data);	
		}
		db.close();
	}
	public List<tNotificationData> getData(String id){
		SQLiteDatabase db=getDb();
		List<tNotificationData> listData=new ArrayList<tNotificationData>();
		tNotificationDA _tNotificationDA=new tNotificationDA(db);
		if(id.equals("")){
			listData=_tNotificationDA.getAllData(db);
		}else{
			tNotificationData data=new tNotificationData();
			data=_tNotificationDA.getData(db, id);
			listData.add(data);
		}
		db.close();
		return listData;
	}
	
	public List<tNotificationData> getDataStatus(String status){
		SQLiteDatabase db=getDb();
		List<tNotificationData> listData=new ArrayList<tNotificationData>();
		tNotificationDA _tNotificationDA=new tNotificationDA(db);
		if(status.equals("")){
			listData=_tNotificationDA.getAllData(db);
		}else{
			tNotificationData data=new tNotificationData();
			data=(tNotificationData) _tNotificationDA.getDataStatusN(db, status);
			listData.add(data);
		}
		db.close();
		return listData;
	}
	
	public List<tNotificationData> getAllDataWillAlert(String Status){
		SQLiteDatabase db=getDb();
		List<tNotificationData> listData=new ArrayList<tNotificationData>();
		tNotificationDA _tNotificationDA=new tNotificationDA(db);
		listData=_tNotificationDA.getAllDataWillAlert(db,Status);
		db.close();
		return listData;
	}

	public int  getContactsCount(){
		SQLiteDatabase db=getDb();
		tNotificationDA _tNotificationDA=new tNotificationDA(db);
		int intCount=_tNotificationDA.getContactsCount(db);
		db.close();
		return intCount;
	}
	public int  getContactsCountStatus(){
		SQLiteDatabase db=getDb();
		tNotificationDA _tNotificationDA=new tNotificationDA(db);
		int intCount=_tNotificationDA.getContactsCountStatus(db);
		db.close();
		return intCount;
	}
	
	public void SaveDataUpdate(List<tNotificationData> listData){
		SQLiteDatabase db=getDb();
		tNotificationDA _tNotificationDA=new tNotificationDA(db);
		for(tNotificationData dt : listData){
			//_tNotificationDA.UpdateDataItemForSubmit(db, dt.get_guiID());
			_tNotificationDA.updateStatus(db, dt.get_guiID());
		}
		db.close();
	}
	public List<tNotificationData> GetAllData(){
		SQLiteDatabase db=getDb();
		tNotificationDA _tNotificationDA = new tNotificationDA(db);
		List<tNotificationData> listData=_tNotificationDA.getAllData(db);
		db.close();
		return listData;
	}
	
	
}
