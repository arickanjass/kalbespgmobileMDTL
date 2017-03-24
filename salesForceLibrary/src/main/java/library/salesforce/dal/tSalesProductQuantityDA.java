package library.salesforce.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.tSalesProductHeaderData;
import library.salesforce.common.tSalesProductQuantityData;

/**
 * Created by Rian Andrivani on 3/16/2017.
 */

public class tSalesProductQuantityDA {
    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tSalesQuantityStock;

    // Contacts Table Columns names

    public tSalesProductQuantityDA(SQLiteDatabase db){
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_intId + " TEXT PRIMARY KEY,"
                + dt.Property_txtQuantityStock + " TEXT NULL,"
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
                + dt.Property_txtAfterImg1 + " TEXT NULL,"
                + dt.Property_txtAfterImg2 + " TEXT NULL,"
                + dt.Property_txtBeforeImg1 + " TEXT NULL,"
                + dt.Property_txtBeforeImg2 + " TEXT NULL,"
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

    // Adding new contact
    public void SaveDataSalesProductQuantityData(SQLiteDatabase db, tSalesProductQuantityData data) {
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " (" + dt.Property_intId + ","
                + dt.Property_txtQuantityStock + ","
                + dt.Property_OutletCode + ","
                + dt.Property_OutletName + ","
                + dt.Property_txtDate + ","
                + dt.Property_txtKeterangan + ","
                + dt.Property_intSumAmount + ","
                + dt.Property_intSumItem + ","
                + dt.Property_UserId + ","
                + dt.Property_intSubmit + ","
                + dt.Property_intSync + ","
                + dt.Property_txtBranchCode + ","
                + dt.Property_txtBranchName + ","
                + dt.Property_intIdAbsenUser + ","
                + dt.Property_txtRoleId + ","
                + dt.Property_txtAfterImg1 + ","
                + dt.Property_txtAfterImg2 + ","
                + dt.Property_txtBeforeImg1 + ","
                + dt.Property_txtBeforeImg2 + ","
                + dt.Property_txtNIK + ") " +
                "values('" + String.valueOf(data.get_intId()) + "','"
                + String.valueOf(data.get_txtQuantityStock()) + "','"
                + String.valueOf(data.get_OutletCode()) + "','"
                + String.valueOf(data.get_OutletName()) + "','"
                + String.valueOf(data.get_dtDate()) + "','"
                + String.valueOf(data.get_txtKeterangan()) + "','"
                + String.valueOf(data.get_intSumAmount()) + "','"
                + String.valueOf(data.get_intSumItem()) + "','"
                + String.valueOf(data.get_UserId()) + "','"
                + String.valueOf(data.get_intSubmit()) + "','"
                + String.valueOf(data.get_intSync()) + "','"
                + String.valueOf(data.get_txtBranchCode()) + "','"
                + String.valueOf(data.get_txtBranchName()) + "','"
                + String.valueOf(data.get_intIdAbsenUser()) + "','"
                + String.valueOf(data.get_txtRoleId()) + "','"
                + String.valueOf(data.get_txtAfterImg1()) + "','"
                + String.valueOf(data.get_txtAfterImg2()) + "','"
                + String.valueOf(data.get_txtBeforeImg1()) + "','"
                + String.valueOf(data.get_txtBeforeImg2()) + "','"
                + String.valueOf(data.get_txtNIK()) + "')");
    }

    public void UpdateDataItem(SQLiteDatabase db, tSalesProductQuantityData data) {
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        db.execSQL("Update " + TABLE_CONTACTS + " set  "
                + dt.Property_intSumAmount + "="
                + String.valueOf(data.get_intSumAmount())
                + dt.Property_intSumItem + "="
                + String.valueOf(data.get_intSumItem())
                + " Where " + dt.Property_intId + "='" + data.get_intId() + "'");
    }

    public void UpdateDataItemForSubmit(SQLiteDatabase db, String dataid) {
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        db.execSQL("Update " + TABLE_CONTACTS + " set  "
                + dt.Property_intSubmit + "=1"
                + " Where " + dt.Property_intId + "='" + dataid + "'");
    }

    // Getting single contact
    public tSalesProductQuantityData getData(SQLiteDatabase db, String id){
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{dt.Property_intId,
                        dt.Property_OutletCode, dt.Property_OutletName,
                        dt.Property_txtDate, dt.Property_txtKeterangan, dt.Property_txtNIK
                        , dt.Property_intSumAmount, dt.Property_intSumItem, dt.Property_UserId, dt.Property_intSubmit, dt.Property_txtRoleId
                        , dt.Property_intSync, dt.Property_txtBranchCode, dt.Property_txtBranchName, dt.Property_intIdAbsenUser
                        , dt.Property_txtAfterImg1, dt.Property_txtAfterImg2, dt.Property_txtBeforeImg1, dt.Property_txtBeforeImg2}, dt.Property_intId + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        tSalesProductQuantityData contact = null;
        if (cursor.getCount() > 0) {
            contact = new tSalesProductQuantityData();
            if (cursor != null)
                cursor.moveToFirst();
            contact.set_intId(cursor.getString(0));
            contact.set_OutletCode(cursor.getString(1));
            contact.set_OutletName(cursor.getString(2));
            contact.set_dtDate(cursor.getString(3));
            contact.set_txtKeterangan(cursor.getString(4));
            contact.set_txtNIK(cursor.getString(5));
            contact.set_intSumAmount(cursor.getString(6));
            contact.set_intSumItem(cursor.getString(7));
            contact.set_UserId(cursor.getString(8));
            contact.set_intSubmit(cursor.getString(9));
            contact.set_intSync(cursor.getString(10));
            contact.set_txtBranchCode(cursor.getString(11));
            contact.set_txtBranchName(cursor.getString(12));
            contact.set_intIdAbsenUser(cursor.getString(13));
            contact.set_txtRoleId(cursor.getString(14));
            contact.set_txtAfterImg1(cursor.getBlob(15));
            contact.set_txtAfterImg2(cursor.getBlob(16));
            contact.set_txtBeforeImg1(cursor.getBlob(17));
            contact.set_txtBeforeImg2(cursor.getBlob(18));
        }
        cursor.close();
        return contact;
    }

    public boolean CheckDataPushData(SQLiteDatabase db) {
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        String selectQuery = "SELECT 1 FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + "=1 AND " + dt.Property_intSync + "=0";
        Cursor cursor = db.rawQuery(selectQuery, null);
        boolean result = false;
        if (cursor.getCount() > 0) {
            result = true;
        }
        cursor.close();
        // return contact list
        return result;
    }

    // Getting All Contacts
    public List<tSalesProductQuantityData> getAllDataToPushData(SQLiteDatabase db){
        List<tSalesProductQuantityData> contactList = null;
        // Select All Query
        tSalesProductQuantityData dt = new tSalesProductQuantityData();

        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + "=1 AND " + dt.Property_intSync + "=0";
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityData>();
            do {
                tSalesProductQuantityData contact = new tSalesProductQuantityData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtQuantityStock(cursor.getString(1));
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
                contact.set_txtAfterImg1(cursor.getBlob(16));
                contact.set_txtAfterImg2(cursor.getBlob(17));
                contact.set_txtBeforeImg1(cursor.getBlob(18));
                contact.set_txtBeforeImg2(cursor.getBlob(19));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tSalesProductQuantityData> getAllData(SQLiteDatabase db) {
        List<tSalesProductQuantityData> contactList = null;
        // select all query
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY txtNoSo DESC";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityData>();
            do {
                tSalesProductQuantityData contact = new tSalesProductQuantityData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtQuantityStock(cursor.getString(1));
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
                contact.set_txtAfterImg1(cursor.getBlob(16));
                contact.set_txtAfterImg2(cursor.getBlob(17));
                contact.set_txtBeforeImg1(cursor.getBlob(18));
                contact.set_txtBeforeImg2(cursor.getBlob(19));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tSalesProductQuantityData> getAllDataByOutlet(SQLiteDatabase db, String txtOutletCode) {
        List<tSalesProductQuantityData> contactList = null;
        // select all query
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_OutletCode + "='" + txtOutletCode + "' AND " + dt.Property_intSubmit + "=1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityData>();
            do {
                tSalesProductQuantityData contact = new tSalesProductQuantityData();
                contact.set_intId(cursor.getString(0));
                contact.set_OutletCode(cursor.getString(1));
                contact.set_OutletName(cursor.getString(2));
                contact.set_dtDate(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_intSumAmount(cursor.getString(6));
                contact.set_intSumItem(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtAfterImg1(cursor.getBlob(15));
                contact.set_txtAfterImg2(cursor.getBlob(16));
                contact.set_txtBeforeImg1(cursor.getBlob(17));
                contact.set_txtBeforeImg2(cursor.getBlob(18));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tSalesProductQuantityData> getAllDataSubmit(SQLiteDatabase db) {
        List<tSalesProductQuantityData> contactList = null;
        // Select All Query
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + "=1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tSalesProductQuantityData>();
            do {
                tSalesProductQuantityData contact = new tSalesProductQuantityData();
                contact.set_intId(cursor.getString(0));
                contact.set_OutletCode(cursor.getString(1));
                contact.set_OutletName(cursor.getString(2));
                contact.set_dtDate(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_intSumAmount(cursor.getString(6));
                contact.set_intSumItem(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtAfterImg1(cursor.getBlob(15));
                contact.set_txtAfterImg2(cursor.getBlob(16));
                contact.set_txtBeforeImg1(cursor.getBlob(17));
                contact.set_txtBeforeImg2(cursor.getBlob(18));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tSalesProductQuantityData> getAllDataNotSync(SQLiteDatabase db) {
        List<tSalesProductQuantityData> contactList = null;
        // select All Query
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSync + "=0";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityData>();
            do {
                tSalesProductQuantityData contact = new tSalesProductQuantityData();
                contact.set_intId(cursor.getString(0));
                contact.set_OutletCode(cursor.getString(1));
                contact.set_OutletName(cursor.getString(2));
                contact.set_dtDate(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_intSumAmount(cursor.getString(6));
                contact.set_intSumItem(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tSalesProductQuantityData> getAllDataByIdAbsen(SQLiteDatabase db, String Id) {
        List<tSalesProductQuantityData> contactList = null;
        // select all query
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intIdAbsenUser + "='" + Id + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityData>();
            do {
                tSalesProductQuantityData contact = new tSalesProductQuantityData();
                contact.set_intId(cursor.getString(0));
                contact.set_OutletCode(cursor.getString(1));
                contact.set_OutletName(cursor.getString(2));
                contact.set_dtDate(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_intSumAmount(cursor.getString(6));
                contact.set_intSumItem(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtAfterImg1(cursor.getBlob(15));
                contact.set_txtAfterImg2(cursor.getBlob(16));
                contact.set_txtBeforeImg1(cursor.getBlob(17));
                contact.set_txtBeforeImg2(cursor.getBlob(18));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    //get data by int sync
    public List<tSalesProductQuantityData> getAllDataByIntSyc(SQLiteDatabase db, String int_sync) {
        List<tSalesProductQuantityData> contactList = null;
        // select all query
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSync + "='" + int_sync + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityData>();
            do {
                tSalesProductQuantityData contact = new tSalesProductQuantityData();
                contact.set_intId(cursor.getString(0));
                contact.set_OutletCode(cursor.getString(1));
                contact.set_OutletName(cursor.getString(2));
                contact.set_dtDate(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_intSumAmount(cursor.getString(6));
                contact.set_intSumItem(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtAfterImg1(cursor.getBlob(15));
                contact.set_txtAfterImg2(cursor.getBlob(16));
                contact.set_txtBeforeImg1(cursor.getBlob(17));
                contact.set_txtBeforeImg2(cursor.getBlob(18));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tSalesProductQuantityData> getAllDataByIntSycAndOutlet(SQLiteDatabase db, String int_sync, String outlet){
        List<tSalesProductQuantityData> contactList = null;
        // select all query
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSync + "='" + int_sync + "' AND " + dt.Property_OutletCode + "='" + outlet + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityData>();
            do {
                tSalesProductQuantityData contact = new tSalesProductQuantityData();
                contact.set_intId(cursor.getString(0));
                contact.set_OutletCode(cursor.getString(1));
                contact.set_OutletName(cursor.getString(2));
                contact.set_dtDate(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_intSumAmount(cursor.getString(6));
                contact.set_intSumItem(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtAfterImg1(cursor.getBlob(15));
                contact.set_txtAfterImg2(cursor.getBlob(16));
                contact.set_txtBeforeImg1(cursor.getBlob(17));
                contact.set_txtBeforeImg2(cursor.getBlob(18));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tSalesProductQuantityData> getAllDataByOutletCode(SQLiteDatabase db, String code){
        List<tSalesProductQuantityData> contactList = null;
        // select all query
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_OutletCode + "='" + code + "'" + " ORDER BY txtNoSo DESC ";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityData>();
            do {
                tSalesProductQuantityData contact = new tSalesProductQuantityData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtQuantityStock(cursor.getString(1));
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
                contact.set_txtAfterImg1(cursor.getBlob(15));
                contact.set_txtAfterImg2(cursor.getBlob(16));
                contact.set_txtBeforeImg1(cursor.getBlob(17));
                contact.set_txtBeforeImg2(cursor.getBlob(18));
                // Adding contact to list
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tSalesProductQuantityData> getDataByQuantityStock(SQLiteDatabase db, String quantityStock){
        List<tSalesProductQuantityData> contactList = null;
        // select all query
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_txtQuantityStock + "='" + quantityStock + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityData>();
            do {
                tSalesProductQuantityData contact = new tSalesProductQuantityData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtQuantityStock(cursor.getString(1));
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
                contact.set_txtAfterImg1(cursor.getBlob(16));
                contact.set_txtAfterImg2(cursor.getBlob(17));
                contact.set_txtBeforeImg1(cursor.getBlob(18));
                contact.set_txtBeforeImg2(cursor.getBlob(19));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tSalesProductQuantityData> getAllDataByDateCheckin(SQLiteDatabase db, String date) {
        List<tSalesProductQuantityData> contactList = null;
        // select All Query
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_txtDate + "='" + date + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityData>();
            do {
                tSalesProductQuantityData contact = new tSalesProductQuantityData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtQuantityStock(cursor.getString(1));
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
                contact.set_txtAfterImg1(cursor.getBlob(16));
                contact.set_txtAfterImg2(cursor.getBlob(17));
                contact.set_txtBeforeImg1(cursor.getBlob(18));
                contact.set_txtBeforeImg2(cursor.getBlob(19));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    // Deleting single contact
    public void deleteContact(SQLiteDatabase db, String id) {
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        db.delete(TABLE_CONTACTS, dt.Property_intId + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void DeleteAllDAta(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
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

    public List<tSalesProductQuantityData> getLastData(SQLiteDatabase db) {
        List<tSalesProductQuantityData> contactList = null;
        // select all query
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityData>();
            do {
                tSalesProductQuantityData contact = new tSalesProductQuantityData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtQuantityStock(cursor.getString(1));
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
                contact.set_txtAfterImg1(cursor.getBlob(16));
                contact.set_txtAfterImg2(cursor.getBlob(17));
                contact.set_txtBeforeImg1(cursor.getBlob(18));
                contact.set_txtBeforeImg2(cursor.getBlob(19));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

}
