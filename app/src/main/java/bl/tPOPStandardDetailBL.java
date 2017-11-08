package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.tPOPStandardDetailData;
import library.spgmobile.dal.tPOPStandardDetailDA;

/**
 * Created by Dewi Oktaviani on 18/10/2017.
 */

public class tPOPStandardDetailBL extends clsMainBL {
    //SQLiteDatabase db = getDb();

    public void SaveData (tPOPStandardDetailData dt){
        SQLiteDatabase _db = getDb();
        tPOPStandardDetailDA _tPOPStandardDetailDA = new tPOPStandardDetailDA (_db);
        _tPOPStandardDetailDA.SaveDatatPOPStandardDetail(_db, dt);
        _db.close();
    }

    public tPOPStandardDetailData GetDataById(String intId){
        SQLiteDatabase _db = getDb();
        tPOPStandardDetailDA _tPOPStandardDetailDA = new tPOPStandardDetailDA(_db);
        tPOPStandardDetailData listData = _tPOPStandardDetailDA.GetDataByHeaderId(_db, intId);
        _db.close();
        return listData;
    }
}
