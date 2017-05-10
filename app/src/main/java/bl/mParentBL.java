package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.mParentData;
import library.salesforce.dal.mParentDA;

/**
 * Created by XSIS on 05/05/2017.
 */

public class mParentBL extends clsMainBL{
    SQLiteDatabase db = getDb();

    public void SaveDatamParent(mParentData dt){
        SQLiteDatabase _db = getDb();
        mParentDA _mParentDA = new mParentDA(_db);
        _mParentDA.SaveDatamParent(_db, dt);
    }
    public List<mParentData> GetAllData(){
        SQLiteDatabase _db = getDb();
        mParentDA _mParentDA = new mParentDA(_db);
        List<mParentData> listData = _mParentDA.GetAllData(_db);
        db.close();
        return listData;
    }
}
