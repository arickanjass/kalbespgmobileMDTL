package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.mPertanyaanData;
import library.salesforce.dal.mPertanyaanDA;

/**
 * Created by XSIS on 05/05/2017.
 */

public class mPertanyaanBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveData(mPertanyaanData dt){
        SQLiteDatabase _db = getDb();
        mPertanyaanDA _mPertanyaanDA = new mPertanyaanDA(_db);
        _mPertanyaanDA.SaveDatamPertanyaan(_db, dt);
    }
    public List<mPertanyaanData> GetAllData(){
        SQLiteDatabase _db = getDb();
        mPertanyaanDA _mPertanyaanDA = new mPertanyaanDA(db);
        List<mPertanyaanData> dt = _mPertanyaanDA.GetAllData(_db);
        return dt;
    }
}
