package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.tCustomerBasedMobileDetailData;
import library.salesforce.dal.tCustomerBasedMobileDetailDA;

public class tCustomerBasedMobileDetailBL extends clsMainBL{
	SQLiteDatabase db;

	public void saveData(tCustomerBasedMobileDetailData dt){
		SQLiteDatabase _db=getDb();
		tCustomerBasedMobileDetailDA _tCustomerBasedMobileDetailDA = new tCustomerBasedMobileDetailDA(_db);
        _tCustomerBasedMobileDetailDA.SaveDatatCustomerBasedMobileDetailData(_db, dt);
	}

    public void updateDataValueById(tCustomerBasedMobileDetailData dt, String id){
        SQLiteDatabase _db=getDb();
        tCustomerBasedMobileDetailDA _tCustomerBasedMobileDetailDA = new tCustomerBasedMobileDetailDA(_db);
        _tCustomerBasedMobileDetailDA.updateDataById(_db, dt, id);
    }

	public List<tCustomerBasedMobileDetailData> getAllDataByHeaderId(String id) {
		SQLiteDatabase _db=getDb();
		List<tCustomerBasedMobileDetailData> dtDetail = new tCustomerBasedMobileDetailDA(_db).getAllDataByHeaderId(_db, id);
		return dtDetail;
	}

    public tCustomerBasedMobileDetailData getAllDataByHeaderIdandintPIC(String id) {
        SQLiteDatabase _db=getDb();
        tCustomerBasedMobileDetailData dtDetail = new tCustomerBasedMobileDetailDA(_db).getAllDataByHeaderIdandintPIC(_db, id);
        return dtDetail;
    }

    public void deleteData(tCustomerBasedMobileDetailData dt) {
        SQLiteDatabase _db=getDb();
        new tCustomerBasedMobileDetailDA(_db).deleteByID(_db, dt.get_intTrCustomerIdDetail());
    }

}
