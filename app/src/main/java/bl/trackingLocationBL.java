package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.trackingLocationData;
import library.salesforce.dal.trackingLocationDA;

/**
 * Created by Rian Andrivani on 5/15/2017.
 */

public class trackingLocationBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveDataLocation(List<trackingLocationData> ListData) {
        SQLiteDatabase _db = getDb();
        trackingLocationDA _trackingLocationDA = new trackingLocationDA(_db);
        for (trackingLocationData data:ListData){
            _trackingLocationDA.SaveDataTrackingLocation(_db, data);
        }
        db.close();
    }

    public List<trackingLocationData> getAllDataTrackingLocation() {
        SQLiteDatabase _db = getDb();
        trackingLocationDA _trackingLocationDA = new trackingLocationDA(_db);
        List<trackingLocationData> dt = _trackingLocationDA.getAllData(_db);
        db.close();
        return dt;
    }
}
