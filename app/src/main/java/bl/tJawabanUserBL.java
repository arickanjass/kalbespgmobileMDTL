package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.tJawabanUserData;
import library.salesforce.dal.tJawabanUserDA;

/**
 * Created by XSIS on 18/05/2017.
 */

public class tJawabanUserBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveDatatJawabanUser(tJawabanUserData dt){
        SQLiteDatabase _db = getDb();
        tJawabanUserDA _tJawabanUserDA = new tJawabanUserDA(_db);
        _tJawabanUserDA.SaveDatatJawabanUser(_db, dt);

    }
    public List<tJawabanUserData> GetAllData(){
        SQLiteDatabase _db = getDb();
        tJawabanUserDA _tJawabanUserDA = new tJawabanUserDA(_db);
        List<tJawabanUserData> listData = _tJawabanUserDA.GetAllData(_db);
        db.close();
        return listData;
    }
}
