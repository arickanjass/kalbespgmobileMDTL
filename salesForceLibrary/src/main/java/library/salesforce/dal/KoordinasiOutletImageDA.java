package library.salesforce.dal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import library.salesforce.common.KoordinasiOutletImageData;

/**
 * Created by Rian Andrivani on 6/7/2017.
 */

public class KoordinasiOutletImageDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_KoordinasiOutletImage;

    public KoordinasiOutletImageDA(SQLiteDatabase db) {
        KoordinasiOutletImageData dt = new KoordinasiOutletImageData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_txtId + " TEXT PRIMARY KEY,"
                + dt.Property_txtHeaderId + " TEXT NULL,"
                + dt.Property_txtImage + " BLOB NULL,"
                + dt.Property_intPosition + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // upgrading database
    public void Droptable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
    }

    public void SaveDataImage(SQLiteDatabase db, KoordinasiOutletImageData data) {
        KoordinasiOutletImageData dt = new KoordinasiOutletImageData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_txtHeaderId, String.valueOf(data.get_txtHeaderId()));
        cv.put(dt.Property_txtImage, data.get_txtImage());
        cv.put(dt.Property_intPosition, String.valueOf(data.get_intPosition()));
        if (data.get_txtId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_txtId, String.valueOf(data.get_txtId()));
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }
}
