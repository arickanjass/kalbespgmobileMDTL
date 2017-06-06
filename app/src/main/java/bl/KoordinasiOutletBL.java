package bl;

import android.database.sqlite.SQLiteDatabase;

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
}
