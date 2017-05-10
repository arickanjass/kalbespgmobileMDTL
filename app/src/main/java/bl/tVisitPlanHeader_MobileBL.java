package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.tVisitPlanHeader_MobileData;
import library.salesforce.dal.tVisitPlanHeader_MobileDA;

/**
 * Created by Robert on 27/04/2017.
 */

public class tVisitPlanHeader_MobileBL extends clsMainBL {

    public void saveData(List<tVisitPlanHeader_MobileData> dt) {
        SQLiteDatabase db = getDb();
        tVisitPlanHeader_MobileDA _tVisitPlanHeader_MobileDA = new tVisitPlanHeader_MobileDA(db);
        _tVisitPlanHeader_MobileDA.DeleteAllData(db);
        for (tVisitPlanHeader_MobileData data : dt) {
            _tVisitPlanHeader_MobileDA.SaveDatatVisitPlanHeader_MobileData(db, data);
        }
        db.close();
    }
    public List<tVisitPlanHeader_MobileData> getAllData(){
        SQLiteDatabase _db = getDb();
        List<tVisitPlanHeader_MobileData> dtDetail = new tVisitPlanHeader_MobileDA(_db).getAllData(_db);
        _db.close();
        return dtDetail;
    }

    public List<tVisitPlanHeader_MobileData> getAllDataByHeaderId(String id){
        SQLiteDatabase _db = getDb();
        List<tVisitPlanHeader_MobileData> dtDetail = new tVisitPlanHeader_MobileDA(_db).getAllDataByHeaderId(_db,id);
        _db.close();
        return dtDetail;
    }


    public void deleteDataByHeaderId(tVisitPlanHeader_MobileData dt){
        SQLiteDatabase _db = getDb();
        new tVisitPlanHeader_MobileDA(_db).deleteByHeaderID(_db, dt.get_intHeaderId());
        _db.close();
    }
}
