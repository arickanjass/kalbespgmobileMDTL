package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.tJawabanUserData;
import library.spgmobile.dal.tJawabanUserDA;

/**
 * Created by XSIS on 18/05/2017.
 */

public class tJawabanUserBL extends clsMainBL {
    //SQLiteDatabase db = getDb();

    public void SaveDatatJawabanUser(tJawabanUserData dt){
        SQLiteDatabase _db = getDb();
        tJawabanUserDA _tJawabanUserDA = new tJawabanUserDA(_db);
        _tJawabanUserDA.SaveDatatJawabanUser(_db, dt);
        _db.close();
    }
    public List<tJawabanUserData> GetAllData(){
        SQLiteDatabase _db = getDb();
        tJawabanUserDA _tJawabanUserDA = new tJawabanUserDA(_db);
        List<tJawabanUserData> listData = _tJawabanUserDA.GetAllData(_db);
        _db.close();
        return listData;
    }

    public List<tJawabanUserData> GetDataByHeaderId(String intHeaderId){
        SQLiteDatabase _db = getDb();
        tJawabanUserDA _tJawabanUserDA = new tJawabanUserDA(_db);
        List<tJawabanUserData> listData = _tJawabanUserDA.GetDataByHeaderId(_db, intHeaderId);
        _db.close();
        return listData;
    }

    public List<tJawabanUserData> GetDataByHeaderIdOrderBySoalId(String intHeaderId){
        SQLiteDatabase _db = getDb();
        tJawabanUserDA _tJawabanUserDA = new tJawabanUserDA(_db);
        List<tJawabanUserData> listData = _tJawabanUserDA.GetDataByHeaderIdOrderBySoalId(_db, intHeaderId);
        _db.close();
        return listData;
    }

    public List<tJawabanUserData> GetDataByQuestionId(String intQuestionId, String intHeaderId){
        SQLiteDatabase _db = getDb();
        tJawabanUserDA _tJawabanUserDA = new tJawabanUserDA(_db);
        List<tJawabanUserData> listData = _tJawabanUserDA.GetDataByQuestionId(_db,intQuestionId, intHeaderId);
        _db.close();
        return listData;
    }
}
