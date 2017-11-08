package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.mTypeLeaveMobileData;
import library.spgmobile.common.mUserLOBData;
import library.spgmobile.dal.mEmployeeSalesProductDA;
import library.spgmobile.dal.mTypeLeaveMobileDA;
import library.spgmobile.dal.mUserLOBDA;

/**
 * Created by aan.junianto on 27/10/2017.
 */

public class mUserLOBBL extends clsMainBL {
    public void saveData(List<mUserLOBData> Listdata){
        SQLiteDatabase db=getDb();
        mUserLOBDA _mUserLOBDA=new mUserLOBDA(db);
        _mUserLOBDA.DeleteAllData(db);
        for(mUserLOBData data:Listdata){
            _mUserLOBDA.SaveData(db, data);
        }
        db.close();
    }

    public List<mUserLOBData> GetAllData(){
        SQLiteDatabase db=getDb();
        mUserLOBDA _mUserLOBDA=new mUserLOBDA(db);
        List<mUserLOBData> listData = _mUserLOBDA.getAllData(db);
        db.close();
        return listData;
    }

    public int  getContactsCount(){
        SQLiteDatabase db=getDb();
        mUserLOBDA _mUserLOBDA=new mUserLOBDA(db);
        int intCount=_mUserLOBDA.getContactsCount(db);
        db.close();
        return intCount;
    }
}
