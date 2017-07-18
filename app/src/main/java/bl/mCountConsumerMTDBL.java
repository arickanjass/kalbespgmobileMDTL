package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.mCountConsumerMTDData;
import library.salesforce.dal.mCountConsumerMTDDA;

public class mCountConsumerMTDBL extends clsMainBL {

    public void SaveData(List<mCountConsumerMTDData> Listdata) {
        SQLiteDatabase db = getDb();
        mCountConsumerMTDDA _mCountConsumerMTD = new mCountConsumerMTDDA(db);

        for (mCountConsumerMTDData data : Listdata) {
            _mCountConsumerMTD.SaveData(db, data);
        }
        db.close();
    }

    public int getCountConsumerMTD(String code) {
        SQLiteDatabase _db = getDb();
        int count = new mCountConsumerMTDDA(_db).countCustomerBaseHomeAbsen(_db, code);
        return count;
    }
}