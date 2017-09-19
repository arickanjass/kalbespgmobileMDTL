package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tJawabanUserHeaderData;
import library.spgmobile.dal.tJawabanUserHeaderDA;

/**
 * Created by Dewi Oktaviani on 04/09/2017.
 */

public class tJawabanUserHeaderBL extends clsMainBL {

    SQLiteDatabase db = getDb();

    public void SaveDatatJawabanHeaderUser(tJawabanUserHeaderData dt){
        SQLiteDatabase _db = getDb();
        tJawabanUserHeaderDA _tJawabanUserDA = new tJawabanUserHeaderDA(_db);
        _tJawabanUserDA.SaveDatatJawabanUserHeader(_db, dt);

    }
    public List<tJawabanUserHeaderData> GetAllData(){
        SQLiteDatabase _db = getDb();
        tJawabanUserHeaderDA _tJawabanUserDA = new tJawabanUserHeaderDA(_db);
        List<tJawabanUserHeaderData> listData = _tJawabanUserDA.GetAllDatas(_db);
        db.close();
        return listData;
    }

    public List<tJawabanUserHeaderData> GetDataByOutletCode(String code, String date){
        SQLiteDatabase _db = getDb();
        tJawabanUserHeaderDA _tJawabanUserDA = new tJawabanUserHeaderDA(_db);
        List<tJawabanUserHeaderData> listData ;
        if (code.equals("ALLOUTLET")){
            listData = _tJawabanUserDA.GetAllData(_db, date);
        } else {
            listData = _tJawabanUserDA.GetDataByOutletCode(_db, code, date);
        }
        if (listData == null){
            listData = new ArrayList<>(0);
        }
        return listData;
    }

    public List<tJawabanUserHeaderData> GetDataByOutletCodeAndSync(String code, String date, String sync){
        SQLiteDatabase _db = getDb();
        tJawabanUserHeaderDA _tJawabanUserDA = new tJawabanUserHeaderDA(_db);
        List<tJawabanUserHeaderData> listData = _tJawabanUserDA.GetDataByOutletCodeAndSync(_db, code, date, sync);
        if (listData == null){
            listData = new ArrayList<>(0);
        }
        return listData;
    }

    public List<tJawabanUserHeaderData> GetLastBeforeSaveDetail(){
        SQLiteDatabase _db = getDb();
        tJawabanUserHeaderDA _tJawabanUserDA = new tJawabanUserHeaderDA(_db);
        List<tJawabanUserHeaderData> listData = _tJawabanUserDA.GetLastBeforeSaveDetail(_db);
        db.close();
        return listData;
    }

    public tJawabanUserHeaderData GetDataByHeaderId(String intHeaderId){
        SQLiteDatabase db=getDb();
        tJawabanUserHeaderDA _tJawabanUserHeaderDA = new tJawabanUserHeaderDA(db);
        tJawabanUserHeaderData dt= new tJawabanUserHeaderData();
        dt=_tJawabanUserHeaderDA.GetDataByHeaderId(db,intHeaderId);
        return dt;
    }
}
