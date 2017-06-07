package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.KoordinasiOutletImageData;
import library.salesforce.dal.KoordinasiOutletImageDA;

/**
 * Created by Rian Andrivani on 6/7/2017.
 */

public class KoordinasiOutletImageBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveDataImage(List<KoordinasiOutletImageData> ListData) {
        SQLiteDatabase _db = getDb();
        KoordinasiOutletImageDA _KoordinasiOutletImageDA = new KoordinasiOutletImageDA(_db);
        for (KoordinasiOutletImageData data:ListData){
            _KoordinasiOutletImageDA.SaveDataImage(_db,data);
        }
        db.close();
    }
}
