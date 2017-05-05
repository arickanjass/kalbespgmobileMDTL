package bl;

import android.database.sqlite.SQLiteDatabase;
import android.widget.LinearLayout;

import java.util.List;

import library.salesforce.common.mTypePertanyaanData;
import library.salesforce.dal.mTypePertanyaanDA;

/**
 * Created by XSIS on 05/05/2017.
 */

public class mTypePertanyaanBL extends clsMainBL{
    SQLiteDatabase db = getDb();

    public void SaveData (mTypePertanyaanData dt){
        SQLiteDatabase _db = getDb();
        mTypePertanyaanDA _mTypePertanyaanDA = new mTypePertanyaanDA(_db);
        _mTypePertanyaanDA.SaveDatamTypePertanyaan(_db, dt);
    }

    public List<mTypePertanyaanData> GetAllData(){
        SQLiteDatabase _db = getDb();
        mTypePertanyaanDA _mTypePertanyaanDA = new mTypePertanyaanDA(_db);
        List<mTypePertanyaanData> dt = _mTypePertanyaanDA.GetAllData(_db);
        return dt;
    }
}
