package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.tPlanogramImageData;
import library.spgmobile.common.tSalesProductQuantityImageData;
import library.spgmobile.dal.tPlanogramImageDA;
import library.spgmobile.dal.tPlanogramMobileDA;
import library.spgmobile.dal.tSalesProductQuantityImageDA;

/**
 * Created by aan.junianto on 14/08/2017.
 */

public class tPlanogramImageBL extends clsMainBL {
    //SQLiteDatabase db = getDb();

    public void SaveData(List<tPlanogramImageData> ListData) {
        SQLiteDatabase _db = getDb();
        tPlanogramImageDA _tPlanogramImageDA = new tPlanogramImageDA(_db);
        for (tPlanogramImageData data:ListData){
            _tPlanogramImageDA.SaveDataImage(_db, data);
        }
        _db.close();
    }

    public List<tPlanogramImageData> getDataHeaderId(String id) {
        SQLiteDatabase db=getDb();
        tPlanogramImageDA _tPlanogramImageDA = new tPlanogramImageDA(db);
        List<tPlanogramImageData>ListData = _tPlanogramImageDA.getDataHeaderId(db, id);
        db.close();
        return ListData;
    }

    public void deleteTrId(String id) {
        SQLiteDatabase _db=getDb();
        new tPlanogramImageDA(_db).deleteByID(_db, id);
        _db.close();
    }
}
