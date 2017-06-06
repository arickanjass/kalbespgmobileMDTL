package library.salesforce.dal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import library.salesforce.common.KoordinasiOutletData;

/**
 * Created by Rian Andrivani on 6/6/2017.
 */

public class KoordinasiOutletDA {
    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_KoordinasiOutlet;

    // Contacts Table Columns names
    public KoordinasiOutletDA(SQLiteDatabase db) {
        KoordinasiOutletData dt = new KoordinasiOutletData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_intId + " TEXT PRIMARY KEY,"
                + dt.Property_dtDate + " TEXT NULL,"
                + dt.Property_txtKeterangan + " TEXT NULL,"
                + dt.Property_txtUserId + " TEXT NULL,"
                + dt.Property_txtUsername + " TEXT NULL,"
                + dt.Property_txtRoleId + " TEXT NULL,"
                + dt.Property_txtOutletCode + " TEXT NULL,"
                + dt.Property_txtOutletName + " TEXT NULL,"
                + dt.Property_txtBranchCode + " TEXT NULL,"
                + dt.Property_txtBranchName + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL,"
                + dt.Property_intSync + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
    }

    public void SaveDataKoordinasiOutlet(SQLiteDatabase db, KoordinasiOutletData data) {
        KoordinasiOutletData dt = new KoordinasiOutletData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_dtDate, String.valueOf(data.get_dtDate()));
        cv.put(dt.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
        cv.put(dt.Property_txtUserId, String.valueOf(data.get_txtUserId()));
        cv.put(dt.Property_txtUsername, String.valueOf(data.get_txtUsername()));
        cv.put(dt.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
        cv.put(dt.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
        cv.put(dt.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
        cv.put(dt.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
        cv.put(dt.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
        cv.put(dt.Property_intSubmit, String.valueOf(data.get_intSubmit()));
        cv.put(dt.Property_intSync, String.valueOf(data.get_intSync()));
        if (data.get_intId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_intId, String.valueOf(data.get_intId()));
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }
}
