package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.mPertanyaanData;
import library.spgmobile.common.tGroupQuestionMappingData;
import library.spgmobile.dal.tGroupQuestionMappingDA;

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

    public List<tGroupQuestionMappingData> GetDataById(int intId){
        SQLiteDatabase _db = getDb();
        tGroupQuestionMappingDA _tGroupQuestionMappingDA = new tGroupQuestionMappingDA(_db);
        List<tGroupQuestionMappingData> listData = _tGroupQuestionMappingDA.GetDataById(_db, intId);
        return listData;
    }

    public List<tGroupQuestionMappingData> GetDataByIdActive(){
        SQLiteDatabase _db = getDb();
        tGroupQuestionMappingDA _tGroupQuestionMappingDA = new tGroupQuestionMappingDA(_db);
        List<tGroupQuestionMappingData> listData = _tGroupQuestionMappingDA.GetDataByIdActive(_db);
        return listData;
    }

    public List<tGroupQuestionMappingData> GetDataByQuestionId(String intQuestionId){
        SQLiteDatabase _db = getDb();
        tGroupQuestionMappingDA _tGroupQuestionMappingDA = new tGroupQuestionMappingDA(_db);
        List<tGroupQuestionMappingData> listData = _tGroupQuestionMappingDA.GetDataByQuestionId(_db, intQuestionId);
        return listData;
    }
    public List<mPertanyaanData> GetDataByQstId(String intQuestionId){
        SQLiteDatabase _db = getDb();
        tGroupQuestionMappingDA _tGroupQuestionMappingDA = new tGroupQuestionMappingDA(_db);
        List<mPertanyaanData> listData = _tGroupQuestionMappingDA.GetDataByQstId(_db, intQuestionId);
        return listData;
    }

    public void deleteAllDatatGroupQuestionMapping(){
        SQLiteDatabase _db = getDb();
        tGroupQuestionMappingDA _tGroupQuestionMappingDA = new tGroupQuestionMappingDA(_db);
        _tGroupQuestionMappingDA.DeleteAllDatatGroupQuestionMapping(_db);
    }
}
