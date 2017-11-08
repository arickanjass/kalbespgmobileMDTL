package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.KoordinasiOutletImageData;
import library.spgmobile.dal.KoordinasiOutletImageDA;

/**
 * Created by Rian Andrivani on 6/7/2017.
 */

public class KoordinasiOutletImageBL extends clsMainBL {
    //SQLiteDatabase db = getDb();

    public void SaveDataImage(List<KoordinasiOutletImageData> ListData) {
        SQLiteDatabase _db = getDb();
        KoordinasiOutletImageDA _KoordinasiOutletImageDA = new KoordinasiOutletImageDA(_db);
        for (KoordinasiOutletImageData data:ListData){
            _KoordinasiOutletImageDA.SaveDataImage(_db,data);
        }
        _db.close();
    }

    public List<KoordinasiOutletImageData> getDataHeaderId(String id) {
        SQLiteDatabase db = getDb();
        KoordinasiOutletImageDA _KoordinasiOutletImageDA = new KoordinasiOutletImageDA(db);
        List<KoordinasiOutletImageData>ListData = _KoordinasiOutletImageDA.getDataHeaderId(db, id);
        db.close();
        return ListData;
    }
}
