package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.mPertanyaanData;
import library.spgmobile.dal.mPertanyaanDA;

/**
 * Created by Dewi Oktaviani on 05/05/2017.
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

    public List<mPertanyaanData> GetDataByGroupQuestion(int groupId){
        SQLiteDatabase _db = getDb();
        mPertanyaanDA _mPertanyaanDA = new mPertanyaanDA(_db);
        List<mPertanyaanData> dt = _mPertanyaanDA.GetDataBYGroupQuestion(_db,groupId);
        return dt;
    }

    public void DeletemPertanyaan(){
        SQLiteDatabase _db = getDb();
        mPertanyaanDA _mPertanyaanDA = new mPertanyaanDA(_db);
        _mPertanyaanDA.DeleteAllDatamPertanyaan(_db) ;
    }
}
