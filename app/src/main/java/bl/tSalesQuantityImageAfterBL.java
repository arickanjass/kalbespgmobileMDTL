package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.tSalesQuantityImageAfterData;
import library.salesforce.dal.tSalesQuantityImageAfterDA;

/**
 * Created by Rian Andrivani on 4/10/2017.
 */

public class tSalesQuantityImageAfterBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveData(List<tSalesQuantityImageAfterData> ListData) {
        SQLiteDatabase _db = getDb();
        tSalesQuantityImageAfterDA _tSalesQuantityImageDA = new tSalesQuantityImageAfterDA(_db);
        for (tSalesQuantityImageAfterData data:ListData){
            _tSalesQuantityImageDA.SaveDataImageAfter(_db, data);
        }
        db.close();
    }

    public List<tSalesQuantityImageAfterData> getDataHeaderId(String id) {
        SQLiteDatabase db=getDb();
        tSalesQuantityImageAfterDA _tSalesQuantityImageDA = new tSalesQuantityImageAfterDA(db);
        List<tSalesQuantityImageAfterData>ListData = _tSalesQuantityImageDA.getDataHeaderId(db, id);
        db.close();
        return ListData;
    }
}
