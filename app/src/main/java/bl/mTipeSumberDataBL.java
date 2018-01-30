package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.mEmployeeAreaData;
import library.spgmobile.common.mTipeSumberData;
import library.spgmobile.dal.mEmployeeAreaDA;
import library.spgmobile.dal.mTipeSumberDA;

/**
 * Created by aan.junianto on 19/01/2018.
 */

public class mTipeSumberDataBL extends clsMainBL {
    public List<mTipeSumberData> GetAllDataListByOutletCode(String txtSumberDataID) {
        SQLiteDatabase db = getDb();
        mTipeSumberDA _mTipeSumberDA = new mTipeSumberDA(db);
        List<mTipeSumberData> listdata = _mTipeSumberDA.getDataListSingle(db, txtSumberDataID);
        db.close();
        return listdata;
    }
    public void deleteAllData() {
        SQLiteDatabase db = getDb();
        mTipeSumberDA _mTipeSumberDA = new mTipeSumberDA(db);
        _mTipeSumberDA.DeleteAllDataMConfig(db);
        db.close();
    }

    public void saveData(List<mTipeSumberData> Listdata) {
        SQLiteDatabase db = getDb();
        mTipeSumberDA _mTipeSumberDA = new mTipeSumberDA(db);
//        _mTipeSumberDA.DeleteAllDataMConfig(db);
        for (mTipeSumberData data : Listdata) {
            _mTipeSumberDA.SaveDataMConfig(db, data);
        }
        db.close();
    }

    public void saveDataOne(mTipeSumberData data) {
        SQLiteDatabase db = getDb();
        mTipeSumberDA _mTipeSumberDA = new mTipeSumberDA(db);
            _mTipeSumberDA.SaveDataMConfig(db, data);
        db.close();
    }
}
