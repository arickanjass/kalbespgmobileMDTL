package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mTypePOPStandardData;
import library.spgmobile.common.tPOPStandardHeaderData;
import library.spgmobile.dal.tActivityMobileDA;
import library.spgmobile.dal.tPOPStandardHeaderDA;

/**
 * Created by Dewi Oktaviani on 18/10/2017.
 */

public class tPOPStandardHeaderBL extends clsMainBL {
    //SQLiteDatabase db = getDb();

    public void SaveData (tPOPStandardHeaderData dt){
        SQLiteDatabase _db = getDb();
        tPOPStandardHeaderDA _tPOPStandardHeaderDA = new tPOPStandardHeaderDA (_db);
        _tPOPStandardHeaderDA.SaveDatatPOPStandardHeader(_db, dt);
        _db.close();
    }

    public tPOPStandardHeaderData GetByLastBeforeSaveDetail(){
        SQLiteDatabase db=getDb();
        tPOPStandardHeaderDA _tPOPStandardHeaderDA = new tPOPStandardHeaderDA(db);
        tPOPStandardHeaderData dt= new tPOPStandardHeaderData();
        dt=_tPOPStandardHeaderDA.GetByLastBeforeSaveDetail(db);
        db.close();
        return dt;
    }

    public List<tPOPStandardHeaderData> GetDataById(String intId){
        SQLiteDatabase _db = getDb();
        tPOPStandardHeaderDA _tPOPStandardHeaderDA = new tPOPStandardHeaderDA(_db);
        List<tPOPStandardHeaderData> listData = _tPOPStandardHeaderDA.GetByHeaderId(_db, intId);
        _db.close();
        return listData;
    }

    public List<tPOPStandardHeaderData> GetDataByOutletCode(String code, String type){
        SQLiteDatabase _db = getDb();
        tPOPStandardHeaderDA _tPOPStandardHeaderDA = new tPOPStandardHeaderDA(_db);
        List<tPOPStandardHeaderData> listData = _tPOPStandardHeaderDA.GetByOutletCode(_db, code, type);
        _db.close();
        return listData;
    }

    public List<tPOPStandardHeaderData> GetTypePOPByOutlet(String outletName){
        SQLiteDatabase _db = getDb();
        tPOPStandardHeaderDA _tPOPStandardHeaderDA = new tPOPStandardHeaderDA(_db);
        List<tPOPStandardHeaderData> listData = _tPOPStandardHeaderDA.GetTypePOPByOutlet(_db, outletName);
        _db.close();
        return listData;
    }

    public List<tPOPStandardHeaderData> GetDataByOutletCodeReport(String code){
        SQLiteDatabase _db = getDb();
        tPOPStandardHeaderDA _tPOPStandardHeaderDA= new tPOPStandardHeaderDA(_db);
        List<tPOPStandardHeaderData> listData ;
        if (code.equals("ALLOUTLET")){
            listData = _tPOPStandardHeaderDA.GetAllData(_db);
                          } else {
            listData = _tPOPStandardHeaderDA.GetByOutletCodeReport(_db, code);
        }
        if (listData == null){
            listData = new ArrayList<>(0);
        }
        _db.close();
        return listData;
    }

    public List<tPOPStandardHeaderData> GetOutletCodeReport(String code){
        SQLiteDatabase _db = getDb();
        tPOPStandardHeaderDA _tPOPStandardHeaderDA= new tPOPStandardHeaderDA(_db);
        List<tPOPStandardHeaderData> listData ;
        if (code.equals("ALL OUTLET")){
            listData = _tPOPStandardHeaderDA.GetAllOutlet(_db);
        } else {
            listData = _tPOPStandardHeaderDA.GetAllOutletbyName(_db, code);
        }
        if (listData == null){
            listData = new ArrayList<>(0);
        }
        _db.close();
        return listData;
    }

    public List<tPOPStandardHeaderData> GetDataByOutletCodeAndSync(String code, String sync){
        SQLiteDatabase _db = getDb();
        tPOPStandardHeaderDA _tPOPStandardHeaderDA = new tPOPStandardHeaderDA(_db);
        List<tPOPStandardHeaderData> listData = _tPOPStandardHeaderDA.GetByOutletAndSync(_db, code, sync);
        if (listData == null){
            listData = new ArrayList<>(0);
        }
        _db.close();
        return listData;
    }

    public int countDataMandatory(List<mTypePOPStandardData> ListmTypePOPStandardData, String txtOutletCode) {
        SQLiteDatabase _db = getDb();
        int count = new tPOPStandardHeaderDA(_db).countDataMandatory(_db, ListmTypePOPStandardData, txtOutletCode);
        _db.close();
        return count;
    }
}
