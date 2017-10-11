package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.tKemasanRusakDetailData;
import library.spgmobile.common.tSalesProductQuantityDetailData;
import library.spgmobile.dal.tKemasanRusakDetailDA;
import library.spgmobile.dal.tSalesProductQuantityDetailDA;

/**
 * Created by aan.junianto on 10/10/2017.
 */

public class tKemasanRusakDetailBL extends clsMainBL {
    public List<tKemasanRusakDetailData> GetDataByNo(String No){
        SQLiteDatabase db=getDb();
        tKemasanRusakDetailDA _tKemasanRusakDetailDA = new tKemasanRusakDetailDA(db);
        List<tKemasanRusakDetailData>ListData = _tKemasanRusakDetailDA.getDataByNo(db, No);
        db.close();
        return ListData;
    }
    public void saveData(tKemasanRusakDetailData dt) {
        SQLiteDatabase _db = getDb();
        tKemasanRusakDetailDA _tKemasanRusakDetailDA = new tKemasanRusakDetailDA(db);
        _tKemasanRusakDetailDA.SaveData(_db, dt);
        _db.close();
    }
    public void deleteData(tKemasanRusakDetailData dt){
        SQLiteDatabase _db = getDb();
        new tKemasanRusakDetailDA(_db).deleteByID(_db, dt.getIntId());
        _db.close();
    }

    public List<tKemasanRusakDetailData> GetDataByNoKemasanRusak(String id) {
        SQLiteDatabase _db =getDb();
        List<tKemasanRusakDetailData> ListData = new tKemasanRusakDetailDA(_db).getDataByKemasanRusak(_db, id);
        _db.close();
        return ListData;
    }
}
