package library.salesforce.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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
                + dt.Property_txtNIK + " TEXT NULL,"
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
        cv.put(dt.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
        cv.put(dt.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
        cv.put(dt.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
        cv.put(dt.Property_txtNIK, String.valueOf(data.get_txtNIK()));
        cv.put(dt.Property_intSubmit, String.valueOf(data.get_intSubmit()));
        cv.put(dt.Property_intSync, String.valueOf(data.get_intSync()));
        if (data.get_intId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_intId, String.valueOf(data.get_intId()));
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }

    // Getting contacts Count
    public int getContactsCount(SQLiteDatabase db) {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        int countData = cursor.getCount();
        cursor.close();
        return countData;
    }

    public List<KoordinasiOutletData> getAllData(SQLiteDatabase db) {
        List<KoordinasiOutletData> contactList = new ArrayList<KoordinasiOutletData>();
        // select All Query
        KoordinasiOutletData dt = new KoordinasiOutletData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                KoordinasiOutletData contact = new KoordinasiOutletData();
                contact.set_intId(String.valueOf(cursor.getString(0)));
                contact.set_dtDate(cursor.getString(1));
                contact.set_txtKeterangan(cursor.getString(2));
                contact.set_txtUserId(cursor.getString(3));
                contact.set_txtUsername(cursor.getString(4));
                contact.set_txtRoleId(cursor.getString(5));
                contact.set_txtOutletCode(cursor.getString(6));
                contact.set_txtOutletName(cursor.getString(7));
                contact.set_txtBranchCode(cursor.getString(8));
                contact.set_txtBranchName(cursor.getString(9));
                contact.set_txtNIK(cursor.getString(10));
                contact.set_intSubmit(cursor.getString(11));
                contact.set_intSync(cursor.getString(12));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public KoordinasiOutletData getData(SQLiteDatabase db, String id) {
        KoordinasiOutletData dt = new KoordinasiOutletData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                dt.Property_intId, dt.Property_dtDate, dt.Property_txtKeterangan, dt.Property_txtUserId,
                dt.Property_txtUsername, dt.Property_txtRoleId, dt.Property_txtOutletCode, dt.Property_txtOutletName,
                dt.Property_txtBranchCode, dt.Property_txtBranchName, dt.Property_txtNIK, dt.Property_intSubmit, dt.Property_intSync},
                dt.Property_intId + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        KoordinasiOutletData contact = new KoordinasiOutletData();
        if (cursor.getCount() > 0) {
            contact.set_intId(String.valueOf(cursor.getString(0)));
            contact.set_dtDate(cursor.getString(1));
            contact.set_txtKeterangan(cursor.getString(2));
            contact.set_txtUserId(cursor.getString(3));
            contact.set_txtUsername(cursor.getString(4));
            contact.set_txtRoleId(cursor.getString(5));
            contact.set_txtOutletCode(cursor.getString(6));
            contact.set_txtOutletName(cursor.getString(7));
            contact.set_txtBranchCode(cursor.getString(8));
            contact.set_txtBranchName(cursor.getString(9));
            contact.set_txtNIK(cursor.getString(10));
            contact.set_intSubmit(cursor.getString(11));
            contact.set_intSync(cursor.getString(12));
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }

    public List<KoordinasiOutletData> getAllDataByOutletCode(SQLiteDatabase db, String code){
        List<KoordinasiOutletData> contactList = null;
        // select all query
        KoordinasiOutletData dt = new KoordinasiOutletData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_txtOutletCode + "='" + code + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<KoordinasiOutletData>();
            do {
                KoordinasiOutletData contact = new KoordinasiOutletData();
                contact.set_intId(cursor.getString(0));
                contact.set_dtDate(cursor.getString(1));
                contact.set_txtKeterangan(cursor.getString(2));
                contact.set_txtUserId(cursor.getString(3));
                contact.set_txtUsername(cursor.getString(4));
                contact.set_txtRoleId(cursor.getString(5));
                contact.set_txtOutletCode(cursor.getString(6));
                contact.set_txtOutletName(cursor.getString(7));
                contact.set_txtBranchCode(cursor.getString(8));
                contact.set_txtBranchName(cursor.getString(9));
                contact.set_txtNIK(cursor.getString(10));
                contact.set_intSubmit(cursor.getString(11));
                contact.set_intSync(cursor.getString(12));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    // Getting All Contacts
    public List<KoordinasiOutletData> getAllDataToPushData(SQLiteDatabase db){
        List<KoordinasiOutletData> contactList = null;
        KoordinasiOutletData dt = new KoordinasiOutletData();

        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + "=1 AND " + dt.Property_intSync + "=0";
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<KoordinasiOutletData>();
            do {
                KoordinasiOutletData contact = new KoordinasiOutletData();
                contact.set_intId(cursor.getString(0));
                contact.set_dtDate(cursor.getString(1));
                contact.set_txtKeterangan(cursor.getString(2));
                contact.set_txtUserId(cursor.getString(3));
                contact.set_txtUsername(cursor.getString(4));
                contact.set_txtRoleId(cursor.getString(5));
                contact.set_txtOutletCode(cursor.getString(6));
                contact.set_txtOutletName(cursor.getString(7));
                contact.set_txtBranchCode(cursor.getString(8));
                contact.set_txtBranchName(cursor.getString(9));
                contact.set_txtNIK(cursor.getString(10));
                contact.set_intSubmit(cursor.getString(11));
                contact.set_intSync(cursor.getString(12));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
