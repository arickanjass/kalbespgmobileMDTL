package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.KoordinasiOutletData;
import library.salesforce.dal.KoordinasiOutletDA;

/**
 * Created by Rian Andrivani on 6/6/2017.
 */

public class KoordinasiOutletBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveDataKoordinasiOutlet(KoordinasiOutletData dt) {
        SQLiteDatabase _db = getDb();
        KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(_db);
        _KoordinasiOutletDA.SaveDataKoordinasiOutlet(_db, dt);
        db.close();
    }

    public int getContactCount() {
        SQLiteDatabase db = getDb();
        KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(db);
        return _KoordinasiOutletDA.getContactsCount(db);
    }

    public List<KoordinasiOutletData> getData(String id) {
        List<KoordinasiOutletData> listData = new ArrayList<KoordinasiOutletData>();
        KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(db);
        if (id.equals("")){
            listData = _KoordinasiOutletDA.getAllData(db);
        } else {
            KoordinasiOutletData data = new KoordinasiOutletData();
            data = _KoordinasiOutletDA.getData(db, id);
            listData.add(data);
        }
        return listData;
    }
}
