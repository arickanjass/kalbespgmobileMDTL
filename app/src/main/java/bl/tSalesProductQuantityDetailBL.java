package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.tSalesProductQuantityData;
import library.salesforce.common.tSalesProductQuantityDetailData;
import library.salesforce.dal.tSalesProductQuantityDA;
import library.salesforce.dal.tSalesProductQuantityDetailDA;

/**
 * Created by Rian Andrivani on 3/17/2017.
 */

public class tSalesProductQuantityDetailBL extends clsMainBL{
    SQLiteDatabase db;

    public void saveData(tSalesProductQuantityDetailData dt) {
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityDetailDA _tSalesProductQuantityDetailDA = new tSalesProductQuantityDetailDA(_db);
        _tSalesProductQuantityDetailDA.SaveDatatSalesProductQuantityDetailData(_db, dt);
    }

    public List<tSalesProductQuantityDetailData> getAllDataByHeaderId(String id){
        SQLiteDatabase _db = getDb();
        List<tSalesProductQuantityDetailData> dtDetail = new tSalesProductQuantityDetailDA(_db).getAllDataByHeaderId(_db, id);
        return dtDetail;
    }

    public List<tSalesProductQuantityDetailData> GetDataByNoSO(String Noso) {
        SQLiteDatabase db =getDb();
        tSalesProductQuantityDetailDA _tSalesProductQuantityDetailDA = new tSalesProductQuantityDetailDA(db);
        List<tSalesProductQuantityDetailData> ListData = _tSalesProductQuantityDetailDA.getDataByNoSo(db, Noso);
        db.close();
        return ListData;
    }

}