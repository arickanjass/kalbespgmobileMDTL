package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.tCustomerBasedMobileDetailData;
import library.spgmobile.common.tCustomerBasedMobileDetailProductData;
import library.spgmobile.dal.tCustomerBasedMobileDetailProductDA;

public class tCustomerBasedMobileDetailProductBL extends clsMainBL{
	//SQLiteDatabase db;

	public void saveData(tCustomerBasedMobileDetailProductData dt){
		SQLiteDatabase _db=getDb();
		tCustomerBasedMobileDetailProductDA _tCustomerBasedMobileDetailProductDA = new tCustomerBasedMobileDetailProductDA(_db);
		_tCustomerBasedMobileDetailProductDA.SaveDatatCustomerBasedMobileDetailProductData(_db, dt);
		_db.close();
	}

	public List<tCustomerBasedMobileDetailProductData> getDataByCustomerDetailId(String id) {
		SQLiteDatabase _db=getDb();
		List<tCustomerBasedMobileDetailProductData> dtCustomerList = new tCustomerBasedMobileDetailProductDA(_db).getAllDataByCustomerDetailId(_db, id);
		_db.close();
		return dtCustomerList;
	}

	public void deleteData(String id){
		SQLiteDatabase _db=getDb();
		new tCustomerBasedMobileDetailProductDA(_db).deleteByIDDetail(_db, id);
		_db.close();
	}

	public void deleteDataByProductId(String id){
		SQLiteDatabase _db=getDb();
		new tCustomerBasedMobileDetailProductDA(_db).deleteByID(_db, id);
		_db.close();
	}

	public List<tCustomerBasedMobileDetailData> getProdCompIsNull(String txtId){
		SQLiteDatabase _db=getDb();
		List<tCustomerBasedMobileDetailData> dtCustomerList = new tCustomerBasedMobileDetailProductDA(_db).getContactsCountProdCompIsNull(_db, txtId);
		return  dtCustomerList;
	}
}
