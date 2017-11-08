package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mCountConsumerMTDData;
import library.spgmobile.dal.mCountConsumerMTDDA;

public class mCountConsumerMTDBL extends clsMainBL {

    public void SaveData(List<mCountConsumerMTDData> Listdata) {
        SQLiteDatabase db = getDb();
        mCountConsumerMTDDA _mCountConsumerMTD = new mCountConsumerMTDDA(db);

        for (mCountConsumerMTDData data : Listdata) {
            _mCountConsumerMTD.SaveData(db, data);
        }
        db.close();
    }

    public List<mCountConsumerMTDData> getAllmCountConsumerMTDDA(String code){
        SQLiteDatabase db =getDb();
        List<mCountConsumerMTDData> dt;
        mCountConsumerMTDDA _mCountConsumerMTDDA = new mCountConsumerMTDDA(db);
        if(code.equals("ALLOUTLET")){
            dt = _mCountConsumerMTDDA.getAllDataByOutlet(db,"");
        } else {
            dt = _mCountConsumerMTDDA.getAllDataByOutlet(db,code);
        }

        if(dt == null){
            dt = new ArrayList<>(0);
        }
        db.close();
        return dt ;
    }
    public List<mCountConsumerMTDData> getAllmCountConsumerMTDDA(){
        SQLiteDatabase db =getDb();
        List<mCountConsumerMTDData> dt;
        mCountConsumerMTDDA _mCountConsumerMTDDA = new mCountConsumerMTDDA(db);
        dt=_mCountConsumerMTDDA.getAllDataByOutlet(db);
        if(dt == null){
            dt = new ArrayList<>(0);
        }
        db.close();
        return dt ;
    }

    public int getCountConsumerMTD(String code) {
        SQLiteDatabase _db = getDb();
        int count = new mCountConsumerMTDDA(_db).countCustomerBaseHomeAbsen(_db, code);
        _db.close();
        return count;
    }
}