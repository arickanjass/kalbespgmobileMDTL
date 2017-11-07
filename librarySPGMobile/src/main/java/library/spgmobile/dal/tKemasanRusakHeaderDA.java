package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tKemasanRusakHeaderData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tVisitPlanRealisasiData;

/**
 * Created by aan.junianto on 10/10/2017.
 */

public class tKemasanRusakHeaderDA {
    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tKemasanRusakHeader;

    // Contacts Table Columns names

    public tKemasanRusakHeaderDA(SQLiteDatabase db){
        tKemasanRusakHeaderData dt = new tKemasanRusakHeaderData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_intId + " TEXT PRIMARY KEY,"
                + dt.Property_txtKemasanRusak + " TEXT NULL,"
                + dt.Property_txtDate + " TEXT NULL,"
                + dt.Property_OutletCode + " TEXT NULL,"
                + dt.Property_OutletName + " TEXT NULL,"
                + dt.Property_txtKeterangan + " TEXT NULL,"
                + dt.Property_intSumItem + " TEXT NULL,"
                + dt.Property_intSumAmount + " TEXT NULL,"
                + dt.Property_UserId + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL,"
                + dt.Property_intSync + " TEXT NULL,"
                + dt.Property_txtBranchCode + " TEXT NULL,"
                + dt.Property_txtBranchName + " TEXT NULL,"
                + dt.Property_intIdAbsenUser + " TEXT NULL,"
                + dt.Property_txtRoleId + " TEXT NULL,"
                + dt.Property_txtNIK + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // upgrading database
    public void Droptable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    public List<tKemasanRusakHeaderData> getAllData(SQLiteDatabase db) {
        List<tKemasanRusakHeaderData> contactList = null;
        // select all query
        tKemasanRusakHeaderData dt = new tKemasanRusakHeaderData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY txtKemasanRusak DESC";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tKemasanRusakHeaderData>();
            do {
                tKemasanRusakHeaderData contact = new tKemasanRusakHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtKemasanRusak(cursor.getString(1));
                contact.set_dtDate(cursor.getString(2));
                contact.set_OutletCode(cursor.getString(3));
                contact.set_OutletName(cursor.getString(4));
                contact.set_txtKeterangan(cursor.getString(5));
                contact.set_intSumItem(cursor.getString(6));
                contact.set_intSumAmount(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtNIK(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    // Adding new contact
    public void SaveData(SQLiteDatabase db, tKemasanRusakHeaderData data) {
        tKemasanRusakHeaderData dt = new tKemasanRusakHeaderData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_txtKemasanRusak, data.get_txtKemasanRusak());
        cv.put(dt.Property_OutletCode, data.get_OutletCode());
        cv.put(dt.Property_OutletName, data.get_OutletName());
        cv.put(dt.Property_txtDate, data.get_dtDate());
        cv.put(dt.Property_txtKeterangan, data.get_txtKeterangan());
        cv.put(dt.Property_txtNIK, data.get_txtNIK());
        cv.put(dt.Property_intSumAmount, data.get_intSumAmount());
        cv.put(dt.Property_intSumItem, data.get_intSumItem());
        cv.put(dt.Property_UserId, data.get_UserId());
        cv.put(dt.Property_intSubmit, data.get_intSubmit());
        cv.put(dt.Property_intSync, data.get_intSync());
        cv.put(dt.Property_txtBranchCode, data.get_txtBranchCode());
        cv.put(dt.Property_txtBranchName, data.get_txtBranchName());
        cv.put(dt.Property_intIdAbsenUser, data.get_intIdAbsenUser());
        cv.put(dt.Property_txtRoleId, data.get_txtRoleId());
        if (data.get_intId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_intId, data.get_intId());
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }
    public List<tKemasanRusakHeaderData> getAllDataByOutletCode(SQLiteDatabase db, String code){
        List<tKemasanRusakHeaderData> contactList = null;
        // select all query
        tKemasanRusakHeaderData dt = new tKemasanRusakHeaderData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_OutletCode + "='" + code + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tKemasanRusakHeaderData>();
            do {
                tKemasanRusakHeaderData contact = new tKemasanRusakHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtKemasanRusak(cursor.getString(1));
                contact.set_dtDate(cursor.getString(2));
                contact.set_OutletCode(cursor.getString(3));
                contact.set_OutletName(cursor.getString(4));
                contact.set_txtKeterangan(cursor.getString(5));
                contact.set_intSumItem(cursor.getString(6));
                contact.set_intSumAmount(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtNIK(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
    public List<tKemasanRusakHeaderData> getLastData(SQLiteDatabase db) {
        List<tKemasanRusakHeaderData> contactList = null;
        // select all query
        tKemasanRusakHeaderData dt = new tKemasanRusakHeaderData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToLast()) {
            contactList = new ArrayList<tKemasanRusakHeaderData>();
            do {
                tKemasanRusakHeaderData contact = new tKemasanRusakHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtKemasanRusak(cursor.getString(1));
                contact.set_dtDate(cursor.getString(2));
                contact.set_OutletCode(cursor.getString(3));
                contact.set_OutletName(cursor.getString(4));
                contact.set_txtKeterangan(cursor.getString(5));
                contact.set_intSumItem(cursor.getString(6));
                contact.set_intSumAmount(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtNIK(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tKemasanRusakHeaderData> getDataByNo(SQLiteDatabase db, String id){
        List<tKemasanRusakHeaderData> contactList = new ArrayList<tKemasanRusakHeaderData>();
        // select all query
        tKemasanRusakHeaderData dt = new tKemasanRusakHeaderData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_txtKemasanRusak + "='" + id + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                tKemasanRusakHeaderData contact = new tKemasanRusakHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtKemasanRusak(cursor.getString(1));
                contact.set_dtDate(cursor.getString(2));
                contact.set_OutletCode(cursor.getString(3));
                contact.set_OutletName(cursor.getString(4));
                contact.set_txtKeterangan(cursor.getString(5));
                contact.set_intSumItem(cursor.getString(6));
                contact.set_intSumAmount(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtNIK(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
    //getting data status submit
    public int countStatusSubmit(SQLiteDatabase db, String code) {

        String selectQuery = "select coalesce(sum(1),0) from [tKemasanRusakHeader] where OutletCode='" + code + "' and intSubmit=1 and [intSync]=0";

        Cursor cursor = db.rawQuery(selectQuery, null);

        int count = 0;

        if (cursor.moveToFirst()) {
            do {
                count=Integer.valueOf(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return count;
    }

    //getting count data push
    public int countPush(SQLiteDatabase db,  String code) {
        String selectQuery = "select coalesce(sum(1),0) from [tKemasanRusakHeader] where OutletCode='" + code + "' and intSubmit=1 and [intSync]=1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        int count = 0;

        if (cursor.moveToFirst()) {
            do {
                count=Integer.valueOf(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return count;
    }
    public int getAllCheckPushData(SQLiteDatabase db) {
        List<tKemasanRusakHeaderData> contactList = null;
        // Select All Query
        tKemasanRusakHeaderData dt = new tKemasanRusakHeaderData();
        String selectQuery = "SELECT  1 FROM "
                + TABLE_CONTACTS +" WHERE " +dt.Property_intSubmit +" ='1' And "+dt.Property_intSync+"=0" ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // return count
        int index = cursor.getCount();
        cursor.close();
        return index;
    }
    // Getting All Contacts
    public List<tKemasanRusakHeaderData> getAllDataToPushData(SQLiteDatabase db){
        List<tKemasanRusakHeaderData> contactList = null;
        // Select All Query
        tKemasanRusakHeaderData dt = new tKemasanRusakHeaderData();

        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + "=1 AND " + dt.Property_intSync + "=0";
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tKemasanRusakHeaderData>();
            do {
                tKemasanRusakHeaderData contact = new tKemasanRusakHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtKemasanRusak(cursor.getString(1));
                contact.set_dtDate(cursor.getString(2));
                contact.set_OutletCode(cursor.getString(3));
                contact.set_OutletName(cursor.getString(4));
                contact.set_txtKeterangan(cursor.getString(5));
                contact.set_intSumItem(cursor.getString(6));
                contact.set_intSumAmount(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtNIK(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }
    // Getting contacts Count
    public int getContactsCount(SQLiteDatabase db) {
        String countQuery = "SELECT 1 FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        // return count
        int index = cursor.getCount();
        cursor.close();
        return index;
    }
}
