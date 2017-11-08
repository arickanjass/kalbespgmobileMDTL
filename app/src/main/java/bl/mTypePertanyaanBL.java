package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.mTypePertanyaanData;
import library.spgmobile.dal.mTypePertanyaanDA;

/**
 * Created by Dewi Oktaviani on 05/05/2017.
 */

public class mTypePertanyaanBL extends clsMainBL{
    //SQLiteDatabase db = getDb();

    public void SaveData (mTypePertanyaanData dt){
        SQLiteDatabase _db = getDb();
        mTypePertanyaanDA _mTypePertanyaanDA = new mTypePertanyaanDA(_db);
        _mTypePertanyaanDA.SaveDatamTypePertanyaan(_db, dt);
        _db.close();
    }

    public List<mTypePertanyaanData> GetAllData(){
        SQLiteDatabase _db = getDb();
        mTypePertanyaanDA _mTypePertanyaanDA = new mTypePertanyaanDA(_db);
        List<mTypePertanyaanData> dt = _mTypePertanyaanDA.GetAllData(_db);
        _db.close();
        return dt;
    }

    public void DeletemTypePertanyaan(){
        SQLiteDatabase _db = getDb();
        mTypePertanyaanDA _mTypePertanyaanDA = new mTypePertanyaanDA(_db);
        _mTypePertanyaanDA.DeleteAllDatamTypePertanyaan(_db);
        _db.close();
    }
}
