package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.mCategoryPOPStandardData;
import library.spgmobile.dal.mCategoryPOPStandardDA;

/**
 * Created by Dewi Oktaviani on 18/10/2017.
 */

public class mCategoryPOPStandardBL extends clsMainBL {
    //SQLiteDatabase db = getDb();

    public void SaveData (mCategoryPOPStandardData dt){
        SQLiteDatabase _db = getDb();
        mCategoryPOPStandardDA _mCategoryPOPStandardDA = new mCategoryPOPStandardDA (_db);
        _mCategoryPOPStandardDA.SaveData(_db, dt);
        _db.close();
    }
    public List<mCategoryPOPStandardData> GetAllData(){
        SQLiteDatabase _db = getDb();
        mCategoryPOPStandardDA _mCategoryPOPStandardDA = new mCategoryPOPStandardDA(_db);
        List<mCategoryPOPStandardData> dt = _mCategoryPOPStandardDA.GetAllData(_db);
        _db.close();
        return dt;
    }
}
