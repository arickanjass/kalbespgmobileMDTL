package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.mListJawabanData;
import library.salesforce.dal.mListJawabanDA;

/**
 * Created by Dewi Oktaviani on 05/05/2017.
 */

public class mListJawabanBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveData(mListJawabanData dt){
        SQLiteDatabase _db = getDb();
        mListJawabanDA _mListJawabanDA = new mListJawabanDA(_db);
        _mListJawabanDA.SaveDatamListJawaban(_db, dt);
    }

    public List<mListJawabanData> GetAllData(){
        SQLiteDatabase _db = getDb();
        mListJawabanDA _mListJawabanDA = new mListJawabanDA(_db);
        List<mListJawabanData> dt = _mListJawabanDA.GetAllData(_db);
        return dt;
    }
    public List<mListJawabanData> GetDataByTypeQuestion(String typeId, String qId){
        SQLiteDatabase _db = getDb();
        mListJawabanDA _mListJawabanDA = new mListJawabanDA(_db);
        List<mListJawabanData> dt = _mListJawabanDA.GetDataByTypeQuestion(_db, typeId, qId);
        return dt;
    }

    public void DeletemListJawaban(){
        SQLiteDatabase _db = getDb();
        mListJawabanDA _mListJawabanDA = new mListJawabanDA(_db);
        _mListJawabanDA.DeleteAllDatamListJawaban(_db) ;
    }
}
