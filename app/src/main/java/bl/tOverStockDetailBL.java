package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.tOverStockDetailData;
import library.spgmobile.common.tSalesProductQuantityDetailData;
import library.spgmobile.dal.tOverStockDetailDA;
import library.spgmobile.dal.tSalesProductQuantityDetailDA;

/**
 * Created by aan.junianto on 15/09/2017.
 */

public class tOverStockDetailBL extends clsMainBL {
    //SQLiteDatabase db;

    public List<tOverStockDetailData> GetDataNoOverStock(String Noso){
        SQLiteDatabase db=getDb();
        tOverStockDetailDA _tOverStockDetailDA = new tOverStockDetailDA(db);
        List<tOverStockDetailData>ListData = _tOverStockDetailDA.getDataNoOverStock(db, Noso);
        db.close();
        return ListData;
    }

    public void saveData(tOverStockDetailData dt) {
        SQLiteDatabase _db = getDb();
        tOverStockDetailDA _tOverStockDetailDA = new tOverStockDetailDA(_db);
        _tOverStockDetailDA.SaveDatatOverStockDetailData(_db, dt);
        _db.close();
    }

    public List<tOverStockDetailData> getAllDataByHeaderId(String id){
        SQLiteDatabase _db = getDb();
        List<tOverStockDetailData> dtDetail = new tOverStockDetailDA(_db).getAllDataByHeaderId(_db, id);
        _db.close();
        return dtDetail;
    }

    public List<tOverStockDetailData> GetDataByNoOverStock(String id) {
        SQLiteDatabase _db =getDb();
        List<tOverStockDetailData> ListData = new tOverStockDetailDA(_db).getDataByTxtOverStock(_db, id);
        _db.close();
        return ListData;
    }

    public void deleteData(tOverStockDetailData dt){
        SQLiteDatabase _db = getDb();
        new tOverStockDetailDA(_db).deleteByID(_db, dt.getIntId());
        _db.close();
    }

    public void deleteDataByProductId(String id) {
        SQLiteDatabase _db=getDb();
        new tOverStockDetailDA(_db).deleteByID(_db, id);
        _db.close();
    }
}
