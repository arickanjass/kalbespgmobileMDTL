package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.tKemasanRusakImageData;
import library.spgmobile.common.tSalesProductQuantityImageData;
import library.spgmobile.dal.tKemasanRusakImageDA;
import library.spgmobile.dal.tSalesProductQuantityImageDA;

/**
 * Created by aan.junianto on 10/10/2017.
 */

public class tKemasanRusakImageBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveData(List<tKemasanRusakImageData> ListData) {
        SQLiteDatabase _db = getDb();
        tKemasanRusakImageDA _tKemasanRusakImageDA = new tKemasanRusakImageDA(db);
        for (tKemasanRusakImageData data:ListData){
            _tKemasanRusakImageDA.SaveDataImage(_db, data);
        }
        db.close();
    }
    public List<tKemasanRusakImageData> getDataByHeaderId(String id) {
        SQLiteDatabase db=getDb();
        tKemasanRusakImageDA _tKemasanRusakImageDA = new tKemasanRusakImageDA(db);
        List<tKemasanRusakImageData>ListData = _tKemasanRusakImageDA.getDataByHeaderId(db, id);
        db.close();
        return ListData;
    }
}
