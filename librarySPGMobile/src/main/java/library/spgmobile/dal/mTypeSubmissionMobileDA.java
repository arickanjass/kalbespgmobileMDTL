package library.spgmobile.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mTypeSubmissionMobile;

/**
 * Created by Rian Andrivani on 3/8/2017.
 */

public class mTypeSubmissionMobileDA {
    public mTypeSubmissionMobileDA(SQLiteDatabase db) {
        mTypeSubmissionMobile dt = new mTypeSubmissionMobile();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "("
                + dt.Property_txtMasterID + " TEXT PRIMARY KEY,"
                + dt.Property_txtGrupMasterID + " TEXT NULL,"
                + dt.Property_txtNamaMasterData + " TEXT NULL,"
                + dt.Property_txtKeterangan + " TEXT NULL,"
                + dt.Property_intLastActiveSelection + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mTypeSubmissionMobile;

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void SaveDataMConfig(SQLiteDatabase db, mTypeSubmissionMobile data) {
        mTypeSubmissionMobile dt = new mTypeSubmissionMobile();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_txtGrupMasterID
                + "," + dt.Property_txtMasterID
                + "," + dt.Property_txtNamaMasterData
                + "," + dt.Property_txtKeterangan
                + "," + dt.Property_intLastActiveSelection
                + ") " + "values('"
                + String.valueOf(data.get_txtGrupMasterID()) + "','"
                + String.valueOf(data.get_txtMasterID()) + "','"
                + String.valueOf(data.get_txtNamaMasterData()) + "','"
                + String.valueOf(data.get_txtKeterangan()) + "','"
                + String.valueOf(data.get_intLastActiveSelection()) + "'" + ")");
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }

    public void UpdateLastSelected(SQLiteDatabase db, String id) {
        mTypeSubmissionMobile dt = new mTypeSubmissionMobile();
        db.execSQL("update " + TABLE_CONTACTS + " set " + dt.Property_intLastActiveSelection + "='0'");
        db.execSQL("update " + TABLE_CONTACTS + " set " + dt.Property_intLastActiveSelection + "='1' where " + dt.Property_txtKeterangan + "='" + id + "'");
    }

    public void DeleteAllDataMConfig(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }

    // Getting single contact
    public mTypeSubmissionMobile getData(SQLiteDatabase db, String id) {
        mTypeSubmissionMobile dt = new mTypeSubmissionMobile();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{
                        dt.Property_txtGrupMasterID, dt.Property_txtMasterID
                        , dt.Property_txtNamaMasterData
                        , dt.Property_txtKeterangan, dt.Property_intLastActiveSelection
                },
                dt.Property_txtGrupMasterID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        mTypeSubmissionMobile contact = new mTypeSubmissionMobile();
        if (cursor.getCount() > 0) {
            contact.set_txtGrupMasterID(cursor.getString(0));
            contact.set_txtMasterID(cursor.getString(1));
            contact.set_txtNamaMasterData(cursor.getString(2));
            contact.set_txtKeterangan(cursor.getString(3));
            contact.set_intLastActiveSelection(cursor.getString(4));
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }

    public mTypeSubmissionMobile getDataForReporting(SQLiteDatabase db, String id) {
        mTypeSubmissionMobile dt = new mTypeSubmissionMobile();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{
                        dt.Property_txtGrupMasterID, dt.Property_txtMasterID
                        , dt.Property_txtNamaMasterData
                        , dt.Property_txtKeterangan, dt.Property_intLastActiveSelection
                },
                dt.Property_txtKeterangan + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        mTypeSubmissionMobile contact = new mTypeSubmissionMobile();
        if (cursor.getCount() > 0) {
            contact.set_txtGrupMasterID(cursor.getString(0));
            contact.set_txtMasterID(cursor.getString(1));
            contact.set_txtNamaMasterData(cursor.getString(2));
            contact.set_txtKeterangan(cursor.getString(3));
            contact.set_intLastActiveSelection(cursor.getString(4));
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }

    public mTypeSubmissionMobile getLastSelected(SQLiteDatabase db, String id) {
        mTypeSubmissionMobile dt = new mTypeSubmissionMobile();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intLastActiveSelection + "='1'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        mTypeSubmissionMobile contact = new mTypeSubmissionMobile();
        if (cursor.getCount() > 0) {
            contact.set_txtGrupMasterID(cursor.getString(0));
            contact.set_txtMasterID(cursor.getString(1));
            contact.set_txtNamaMasterData(cursor.getString(2));
            contact.set_txtKeterangan(cursor.getString(3));
            contact.set_intLastActiveSelection(cursor.getString(4));
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }

    // Getting all contact
    public List<mTypeSubmissionMobile> getAllData(SQLiteDatabase db) {
        List<mTypeSubmissionMobile> contactList = new ArrayList<mTypeSubmissionMobile>();
        // Select All Query
        mTypeSubmissionMobile dt = new mTypeSubmissionMobile();
        String selectQuery = "SELECT " + dt.Property_All + " FROM "
                + TABLE_CONTACTS + " ORDER BY " + dt.Property_txtMasterID + " ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mTypeSubmissionMobile contact = new mTypeSubmissionMobile();
                contact.set_txtGrupMasterID(cursor.getString(0));
                contact.set_txtMasterID(cursor.getString(1));
                contact.set_txtNamaMasterData(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.set_intLastActiveSelection(cursor.getString(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    // Deleting single contact
    public void deleteContact(SQLiteDatabase db, String id) {
        mTypeSubmissionMobile dt = new mTypeSubmissionMobile();
        db.delete(TABLE_CONTACTS, dt.Property_txtGrupMasterID + " = ?",
                new String[]{String.valueOf(id)});
        // db.close();
    }

    // Getting contacts Count
    public int getContactsCount(SQLiteDatabase db) {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        int countData = cursor.getCount();
        cursor.close();
        return countData;
    }
}
