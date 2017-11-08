package bl;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.dal.tDeviceInfoUserDA;

public class tDeviceInfoUserBL extends clsMainBL {
	public void SaveInfoDevice(String txtUserId,String txtDeviceId, String imeiNumber){
		SQLiteDatabase db=getDb();
		tDeviceInfoUserDA _tDeviceInfoUserDA=new tDeviceInfoUserDA(db);
		tDeviceInfoUserData data= new tDeviceInfoUserData();
		int num=1;
		data.set_intId(num);
		data.set_txtUserId(txtUserId);
		data.set_txtDeviceId(txtDeviceId);
		data.set_txtDevice(android.os.Build.DEVICE);
		data.set_txtModel(android.os.Build.MANUFACTURER+" "+android.os.Build.MODEL);
		data.set_txtImei(imeiNumber);
		data.set_txtVersion(System.getProperty("os.version"));
        _tDeviceInfoUserDA.SaveDatatDeviceInfoUserData(db, data);
		db.close();
	}
	public List<tDeviceInfoUserData> getData(int id){
		SQLiteDatabase db=getDb();
		List<tDeviceInfoUserData> listData=new ArrayList<tDeviceInfoUserData>();
		tDeviceInfoUserDA _tDeviceInfoUserDA=new tDeviceInfoUserDA(db);
		if(id == 0){
			listData=_tDeviceInfoUserDA.getAllData(db);
		}else{
			tDeviceInfoUserData data=new tDeviceInfoUserData();
			data=_tDeviceInfoUserDA.getData(db, id);
			listData.add(data);
		}
		db.close();
		return listData;
	}
}
