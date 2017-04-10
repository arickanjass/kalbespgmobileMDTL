package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.tSalesQuantityImageBeforeData;
import library.salesforce.dal.tSalesQuantityImageBeforeDA;

/**
 * Created by Rian Andrivani on 4/10/2017.
 */

public class tSalesQuantityImageBeforeBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveData(List<tSalesQuantityImageBeforeData> ListData) {
        SQLiteDatabase _db = getDb();
        tSalesQuantityImageBeforeDA _tSalesQuantityImageBeforeDA = new tSalesQuantityImageBeforeDA(_db);
        for (tSalesQuantityImageBeforeData data:ListData){
            _tSalesQuantityImageBeforeDA.SaveDataImageBefore(_db, data);
        }
        db.close();
    }

    public List<tSalesQuantityImageBeforeData> getDataHeaderId(String id) {
        SQLiteDatabase db=getDb();
        tSalesQuantityImageBeforeDA _tSalesQuantityImageBeforeDA = new tSalesQuantityImageBeforeDA(db);
        List<tSalesQuantityImageBeforeData>ListData = _tSalesQuantityImageBeforeDA.getDataHeaderId(db, id);
        db.close();
        return ListData;
    }
}
