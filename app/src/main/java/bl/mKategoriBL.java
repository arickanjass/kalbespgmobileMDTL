package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.mKategoriData;
import library.salesforce.dal.mKategoriDA;

/**
 * Created by Dewi Oktaviani on 05/05/2017.
 */

public class mKategoriBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveData(mKategoriData dt){
        SQLiteDatabase _db = getDb();
        mKategoriDA _mKategoriDA = new mKategoriDA(db);
        _mKategoriDA.SaveDatamKategori(_db, dt);
    }

    private List<mKategoriData> GetAllData(){
        SQLiteDatabase _db = getDb();
        mKategoriDA _mKategoriDA = new mKategoriDA(_db);
        List<mKategoriData> dt = _mKategoriDA.GetAllData(_db);
        return dt;
    }
}
