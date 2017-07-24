package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.tSalesProductQuantityImageData;
import library.spgmobile.dal.tSalesProductQuantityImageDA;

/**
 * Created by Rian Andrivani on 4/26/2017.
 */

public class tSalesProductQuantityImageBL extends clsMainBL{
    SQLiteDatabase db = getDb();

    public void SaveData(List<tSalesProductQuantityImageData> ListData) {
        SQLiteDatabase _db = getDb();
        tSalesProductQuantityImageDA _tSalesProductQuantityImageDA = new tSalesProductQuantityImageDA(_db);
        for (tSalesProductQuantityImageData data:ListData){
            _tSalesProductQuantityImageDA.SaveDataImage(_db, data);
        }
        db.close();
    }

    public List<tSalesProductQuantityImageData> getDataHeaderId(String id) {
        SQLiteDatabase db=getDb();
        tSalesProductQuantityImageDA _tSalesProductQuantityImageDA = new tSalesProductQuantityImageDA(db);
        List<tSalesProductQuantityImageData>ListData = _tSalesProductQuantityImageDA.getDataHeaderId(db, id);
        db.close();
        return ListData;
    }
}
