package library.salesforce.dal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import library.salesforce.common.trackingLocationData;

/**
 * Created by Rian Andrivani on 5/15/2017.
 */

public class trackingLocationDA {
    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_trackingLocation;

    // Contacts Table Columns names
    public trackingLocationDA(SQLiteDatabase db){
        trackingLocationData dt = new trackingLocationData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_intId + " TEXT PRIMARY KEY,"
                + dt.Property_txtLongitude + " TEXT NULL,"
                + dt.Property_txtLatitude + " TEXT NULL,"
                + dt.Property_txtAccuracy + " TEXT NULL,"
                + dt.Property_txtTime + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
    }

    public void SaveDataTrackingLocation(SQLiteDatabase db, trackingLocationData data) {
        trackingLocationData dt = new trackingLocationData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_txtLongitude, String.valueOf(data.get_txtLongitude()));
        cv.put(dt.Property_txtLatitude, String.valueOf(data.get_txtLatitude()));
        cv.put(dt.Property_txtAccuracy, String.valueOf(data.get_txtAccuracy()));
        cv.put(dt.Property_txtTime, String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        if (data.get_intId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_intId, String.valueOf(data.get_intId()));
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }

}
