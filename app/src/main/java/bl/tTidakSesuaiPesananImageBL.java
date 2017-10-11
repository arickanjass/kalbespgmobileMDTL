package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.KoordinasiOutletImageData;
import library.spgmobile.common.tKemasanRusakImageData;
import library.spgmobile.common.tTidakSesuaiPesananImageData;
import library.spgmobile.dal.KoordinasiOutletImageDA;
import library.spgmobile.dal.tKemasanRusakImageDA;
import library.spgmobile.dal.tTidakSesuaiPesananImageDA;

/**
 * Created by aan.junianto on 10/10/2017.
 */

public class tTidakSesuaiPesananImageBL extends clsMainBL{

    SQLiteDatabase db = getDb();

    public void SaveDataImage(List<tTidakSesuaiPesananImageData> ListData) {
        SQLiteDatabase _db = getDb();
        tTidakSesuaiPesananImageDA _tTidakSesuaiPesananImageDA = new tTidakSesuaiPesananImageDA(db);
        for (tTidakSesuaiPesananImageData data:ListData){
            _tTidakSesuaiPesananImageDA.SaveDataImage(_db,data);
        }
        db.close();
    }

    public List<tTidakSesuaiPesananImageData> getDataByHeaderId(String id) {
        SQLiteDatabase db=getDb();
        tTidakSesuaiPesananImageDA _tTidakSesuaiPesananImageDA = new tTidakSesuaiPesananImageDA(db);
        List<tTidakSesuaiPesananImageData>ListData = _tTidakSesuaiPesananImageDA.getDataByHeaderId(db, id);
        db.close();
        return ListData;
    }
}
