package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.mReasonPOPStandardData;
import library.spgmobile.dal.mReasonPOPStandardDA;

/**
 * Created by Dewi Oktaviani on 18/10/2017.
 */

public class mReasonPOPStandardBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveData (mReasonPOPStandardData dt){
        SQLiteDatabase _db = getDb();
        mReasonPOPStandardDA _mReasonPOPStandardDA = new mReasonPOPStandardDA (_db);
        _mReasonPOPStandardDA.SaveDatamReasonPOPStandard(_db, dt);
        _db.close();
    }

    public List<mReasonPOPStandardData> GetAllData(){
        SQLiteDatabase _db = getDb();
        mReasonPOPStandardDA _mReasonPOPStandardDA = new mReasonPOPStandardDA(_db);
        List<mReasonPOPStandardData> dt = _mReasonPOPStandardDA.GetAllData(_db);
        _db.close();
        return dt;
    }
}
