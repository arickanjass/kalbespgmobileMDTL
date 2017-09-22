package library.spgmobile.dal;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mCategoryKoordinasiOutletData;

/**
 * Created by Dewi Oktaviani on 22/09/2017.
 */

public class mCategoryKoordinasiOutletDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mCategoryKoordinasiOutlet;

    // Contacts Table Columns names
    public mCategoryKoordinasiOutletDA(SQLiteDatabase db) {
        mCategoryKoordinasiOutletData dt = new mCategoryKoordinasiOutletData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_intId + " TEXT PRIMARY KEY,"
                + dt.Property_txtCategory + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
    }

    public void SaveDataCategoryKoordinasiOutlet(SQLiteDatabase db, mCategoryKoordinasiOutletData data) {
        mCategoryKoordinasiOutletData dt = new mCategoryKoordinasiOutletData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_intId, String.valueOf(data.get_intId()));
        cv.put(dt.Property_txtCategory, String.valueOf(data.get_txtCategory()));
        if (data.get_intId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_intId, String.valueOf(data.get_intId()));
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }

    public List<mCategoryKoordinasiOutletData> GetAllData(SQLiteDatabase db) {
        List<mCategoryKoordinasiOutletData> contactList = new ArrayList<mCategoryKoordinasiOutletData>();
        // select All Query
        mCategoryKoordinasiOutletData dt = new mCategoryKoordinasiOutletData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                mCategoryKoordinasiOutletData contact = new mCategoryKoordinasiOutletData();
                contact.set_intId(String.valueOf(cursor.getString(0)));
                contact.set_txtCategory(cursor.getString(1));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
