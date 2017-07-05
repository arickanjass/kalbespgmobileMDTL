package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.tGroupQuestionMappingData;
import library.salesforce.dal.tGroupQuestionMappingDA;

/**
 * Created by Dewi Oktaviani on 04/07/2017.
 */

public class tGroupQuestionMappingBL extends clsMainBL{
    SQLiteDatabase db = getDb();

    public void saveDatatGroupQuestionMapping(tGroupQuestionMappingData dt){
        SQLiteDatabase _db = getDb();
        tGroupQuestionMappingDA _tGroupQuestionMappingDA = new tGroupQuestionMappingDA(_db);
        _tGroupQuestionMappingDA.SaveDatatGroupQuestionMapping(_db, dt);
    }

    public List<tGroupQuestionMappingData> GetAllData(){
        SQLiteDatabase _db = getDb();
        tGroupQuestionMappingDA _tGroupQuestionMappingDA = new tGroupQuestionMappingDA(_db);
        List<tGroupQuestionMappingData> listData = _tGroupQuestionMappingDA.GetAllData(_db);
        return listData;
    }

    public void deleteAllDatatGroupQuestionMapping(){
        SQLiteDatabase _db = getDb();
        tGroupQuestionMappingDA _tGroupQuestionMappingDA = new tGroupQuestionMappingDA(_db);
        _tGroupQuestionMappingDA.DeleteAllDatatGroupQuestionMapping(_db);
    }
}
