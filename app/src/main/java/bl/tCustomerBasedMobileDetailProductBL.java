package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.tCustomerBasedMobileDetailData;
import library.salesforce.common.tCustomerBasedMobileDetailProductData;
import library.salesforce.dal.tCustomerBasedMobileDetailDA;
import library.salesforce.dal.tCustomerBasedMobileDetailProductDA;

public class tCustomerBasedMobileDetailProductBL extends clsMainBL{
	SQLiteDatabase db;

	public void saveData(tCustomerBasedMobileDetailProductData dt){
		SQLiteDatabase _db=getDb();
		tCustomerBasedMobileDetailProductDA _tCustomerBasedMobileDetailProductDA = new tCustomerBasedMobileDetailProductDA(_db);
		_tCustomerBasedMobileDetailProductDA.SaveDatatCustomerBasedMobileDetailProductData(_db, dt);
	}

	public List<tCustomerBasedMobileDetailProductData> getDataByCustomerDetailId(String id) {
		SQLiteDatabase _db=getDb();
		List<tCustomerBasedMobileDetailProductData> dtCustomerList = new tCustomerBasedMobileDetailProductDA(_db).getAllDataByCustomerDetailId(_db, id);
		return dtCustomerList;
	}

    public void deleteData(String id){
        SQLiteDatabase _db=getDb();
        new tCustomerBasedMobileDetailProductDA(_db).deleteByIDDetail(_db, id);
    }
}
