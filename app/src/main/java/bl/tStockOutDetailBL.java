package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.tOverStockDetailData;
import library.spgmobile.common.tStockOutDetailData;
import library.spgmobile.dal.tOverStockDetailDA;
import library.spgmobile.dal.tStockOutDetailDA;

/**
 * Created by aan.junianto on 5/07/2018.
 */

public class tStockOutDetailBL extends clsMainBL{

    public List<tStockOutDetailData> GetDataNoOverStock(String Noso){
        SQLiteDatabase db=getDb();
        tStockOutDetailDA _tOverStockDetailDA = new tStockOutDetailDA(db);
        List<tStockOutDetailData>ListData = _tOverStockDetailDA.getDataNoOverStock(db, Noso);
        db.close();
        return ListData;
    }

    public List<tStockOutDetailData> GetDataByNoOverStock(String id) {
        SQLiteDatabase _db =getDb();
        List<tStockOutDetailData> ListData = new tStockOutDetailDA(_db).getDataByTxtOverStock(_db, id);
        _db.close();
        return ListData;
    }

    public void deleteData(tStockOutDetailData dt){
        SQLiteDatabase _db = getDb();
        new tStockOutDetailDA(_db).deleteByID(_db, dt.getIntId());
        _db.close();
    }

    public void saveData(tStockOutDetailData dt) {
        SQLiteDatabase _db = getDb();
        tStockOutDetailDA _tOverStockDetailDA = new tStockOutDetailDA(_db);
        _tOverStockDetailDA.SaveDatatOverStockDetailData(_db, dt);
        _db.close();
    }


}
