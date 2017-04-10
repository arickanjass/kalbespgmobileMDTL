package library.salesforce.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.tSalesQuantityImageBeforeData;

/**
 * Created by Rian Andrivani on 4/10/2017.
 */

public class tSalesQuantityImageBeforeDA {

    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tSalesQuantityImageBefore;

    public tSalesQuantityImageBeforeDA(SQLiteDatabase db) {
        tSalesQuantityImageBeforeData dt = new tSalesQuantityImageBeforeData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_txtId + " TEXT PRIMARY KEY,"
                + dt.Property_txtHeaderId + " TEXT NULL,"
                + dt.Property_before1 + " BLOB NULL,"
                + dt.Property_before2 + " BLOB NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // upgrading database
    public void Droptable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
    }

    public void SaveDataImageBefore(SQLiteDatabase db, tSalesQuantityImageBeforeData data) {
        tSalesQuantityImageBeforeData dt = new tSalesQuantityImageBeforeData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_txtHeaderId, String.valueOf(data.get_txtHeaderId()));
        cv.put(dt.Property_before1, data.get_before1());
        cv.put(dt.Property_before2, data.get_before2());
        if (data.get_txtId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_txtId, String.valueOf(data.get_txtId()));
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }

    public List<tSalesQuantityImageBeforeData> getDataHeaderId(SQLiteDatabase db, String id) {
        List<tSalesQuantityImageBeforeData> contactList = null;
        tSalesQuantityImageBeforeData dt = new tSalesQuantityImageBeforeData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_txtId,
        dt.Property_txtHeaderId, dt.Property_before1, dt.Property_before2}, dt.Property_txtHeaderId + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                contactList = new ArrayList<tSalesQuantityImageBeforeData>();
                do {
                    tSalesQuantityImageBeforeData contact = new tSalesQuantityImageBeforeData();
                    contact.set_txtId(String.valueOf(cursor.getString(0)));
                    contact.set_txtHeaderId(cursor.getString(1));
                    contact.set_before1(cursor.getBlob(2));
                    contact.set_before2(cursor.getBlob(3));
                    contactList.add(contact);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return contactList;
    }

}
