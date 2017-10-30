package library.spgmobile.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mTypeLeaveMobileData;
import library.spgmobile.common.mUserLOBData;

/**
 * Created by aan.junianto on 27/10/2017.
 */

public class mUserLOBDA {
    public mUserLOBDA(SQLiteDatabase db) {
        mUserLOBData dt = new mUserLOBData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "(" + dt.Property_intId + " INTEGER PRIMARY KEY,"
                + dt.Property_txtLOBName + " TEXT  NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mUserLOB;

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void SaveData(SQLiteDatabase db, mUserLOBData data) {
        mUserLOBData dt = new mUserLOBData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_All + ") " + "values('"
                + String.valueOf(data.get_intId()) + "','"
                + String.valueOf(data.get_txtLOBName()) + "')");
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    public void DeleteAllData(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    // Getting single contact
    public mTypeLeaveMobileData getData(SQLiteDatabase db, String id) {
        mTypeLeaveMobileData dt = new mTypeLeaveMobileData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        dt.Property_intTipeLeave, dt.Property_txtTipeLeaveName},
                dt.Property_intTipeLeave + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        mTypeLeaveMobileData contact = new mTypeLeaveMobileData();
        if (cursor.getCount() > 0) {
            contact.set_intTipeLeave(String.valueOf( cursor.getString(0)));
            contact.set_txtTipeLeaveName(cursor.getString(1));
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }


    // Getting All Contacts
    public List<mUserLOBData> getAllData(SQLiteDatabase db) {
        List<mUserLOBData> contactList = new ArrayList<mUserLOBData>();
        // Select All Query
        mUserLOBData dt = new mUserLOBData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                mUserLOBData contact = new mUserLOBData();
                contact.set_intId(String.valueOf( cursor.getString(0)));
                contact.set_txtLOBName(cursor.getString(1));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    // Deleting single contact
    public void deleteContact(SQLiteDatabase db, String id) {
        mTypeLeaveMobileData dt = new mTypeLeaveMobileData();
        db.delete(TABLE_CONTACTS, dt.Property_intTipeLeave + " = ?",
                new String[] { String.valueOf(id) });
        // db.close();
    }

    // Getting contacts Count
    public int getContactsCount(SQLiteDatabase db) {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        int countData = cursor.getCount();
        cursor.close();
        // return count
        return countData;
    }
}
