package library.spgmobile.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mDownloadMasterData_mobileData;
import library.spgmobile.common.tLogDownloadData;

/**
 * Created by aan.junianto on 1/11/2017.
 */

public class tLogDownloadDA {
    public tLogDownloadDA(SQLiteDatabase db) {
        tLogDownloadData dt = new tLogDownloadData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "("
                + dt.Property_txtModuleName + " TEXT  PRIMARY KEY,"
                + dt.Property_dtLastDownload + " TEXT  NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tLogDonwload;

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void SaveDataMConfig(SQLiteDatabase db, tLogDownloadData data) {
        tLogDownloadData dt = new tLogDownloadData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_txtModuleName
                + "," + dt.Property_dtLastDownload
                + ") " + "values('"
                + String.valueOf(data.get_txtModuleName()) + "','"
                + String.valueOf(data.get_dtLastDownload()) + "')");
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    public void DeleteAllDataMConfig(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    // Getting single contact
    public tLogDownloadData getData(SQLiteDatabase db, String id) {
        tLogDownloadData dt = new tLogDownloadData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        dt.Property_txtModuleName
                        , dt.Property_dtLastDownload},
                dt.Property_txtModuleName + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        tLogDownloadData contact = new tLogDownloadData();
        if (cursor.getCount() > 0) {
            contact.set_txtModuleName(cursor.getString(0));
            contact.set_dtLastDownload(cursor.getString(1));
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }

    // Getting All Contacts
    public List<tLogDownloadData> getAllData(SQLiteDatabase db) {
        List<tLogDownloadData> contactList = new ArrayList<tLogDownloadData>();
        // Select All Query
        tLogDownloadData dt = new tLogDownloadData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS+" ORDER BY "+ dt.Property_txtModuleName +" ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                tLogDownloadData contact = new tLogDownloadData();
                contact.set_txtModuleName(cursor.getString(0));
                contact.set_dtLastDownload(cursor.getString(1));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    // Getting All Contacts
    public List<mDownloadMasterData_mobileData> getAllDataDistict(SQLiteDatabase db) {
        List<mDownloadMasterData_mobileData> contactList = new ArrayList<mDownloadMasterData_mobileData>();
        // Select All Query
        mDownloadMasterData_mobileData dt = new mDownloadMasterData_mobileData();
        String selectQuery = "SELECT  DISTINCT " + dt.Property_txtMasterDataName + "," + dt.Property_txtMasterData + " FROM "
                + TABLE_CONTACTS+" ORDER BY "+ dt.Property_intModule;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mDownloadMasterData_mobileData contact = new mDownloadMasterData_mobileData();
                contact.set_txtMasterDataName(cursor.getString(0));
                contact.set_txtMasterData(cursor.getString(1));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    // Getting All Contacts based on masterId
    public List<mDownloadMasterData_mobileData> getDataByMasterId(SQLiteDatabase db, String masterId) {
        List<mDownloadMasterData_mobileData> contactList = new ArrayList<mDownloadMasterData_mobileData>();
        // Select All Query
        mDownloadMasterData_mobileData dt = new mDownloadMasterData_mobileData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS + " WHERE "+ dt.Property_intModule +" = '"+ masterId +"' ORDER BY "+ dt.Property_txtModuleName +" ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mDownloadMasterData_mobileData contact = new mDownloadMasterData_mobileData();
                contact.set_intId(cursor.getString(0));
                contact.set_intModule(cursor.getString(1));
                contact.set_txtModuleName(cursor.getString(2));
                contact.set_txtMasterData(cursor.getString(3));
                contact.set_intVersionApp(cursor.getString(4));
                contact.set_txtTypeApp(cursor.getString(5));
                contact.set_txtVersion(cursor.getString(6));
                contact.set_txtMasterDataName(cursor.getString(7));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    // Deleting single contact
    public void deleteContact(SQLiteDatabase db, int id) {
        mDownloadMasterData_mobileData dt = new mDownloadMasterData_mobileData();
        db.delete(TABLE_CONTACTS, dt.Property_intId + " = ?",
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
