package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.tPOPStandardHeaderData;
import library.spgmobile.dal.tPOPStandardHeaderDA;

/**
 * Created by Dewi Oktaviani on 18/10/2017.
 */

public class tPOPStandardHeaderBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveData (tPOPStandardHeaderData dt){
        SQLiteDatabase _db = getDb();
        tPOPStandardHeaderDA _tPOPStandardHeaderDA = new tPOPStandardHeaderDA (_db);
        _tPOPStandardHeaderDA.SaveDatatPOPStandardHeader(_db, dt);
    }

    public tPOPStandardHeaderData GetByLastBeforeSaveDetail(){
        SQLiteDatabase db=getDb();
        tPOPStandardHeaderDA _tPOPStandardHeaderDA = new tPOPStandardHeaderDA(db);
        tPOPStandardHeaderData dt= new tPOPStandardHeaderData();
        dt=_tPOPStandardHeaderDA.GetByLastBeforeSaveDetail(db);
        return dt;
    }

    public List<tPOPStandardHeaderData> GetDataById(String intId){
        SQLiteDatabase _db = getDb();
        tPOPStandardHeaderDA _tPOPStandardHeaderDA = new tPOPStandardHeaderDA(_db);
        List<tPOPStandardHeaderData> listData = _tPOPStandardHeaderDA.GetByHeaderId(_db, intId);
        return listData;
    }

    public List<tPOPStandardHeaderData> GetDataByOutletCode(String code, String type){
        SQLiteDatabase _db = getDb();
        tPOPStandardHeaderDA _tPOPStandardHeaderDA = new tPOPStandardHeaderDA(_db);
        List<tPOPStandardHeaderData> listData = _tPOPStandardHeaderDA.GetByOutletCode(_db, code, type);
        return listData;
    }

}
