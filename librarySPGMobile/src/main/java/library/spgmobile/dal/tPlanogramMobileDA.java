package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tCustomerBasedMobileHeaderData;
import library.spgmobile.common.tPlanogramImageData;
import library.spgmobile.common.tPlanogramMobileData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tSubTypeActivityData;

/**
 * Created by aan.junianto on 10/08/2017.
 */

public class tPlanogramMobileDA {
    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tPlanogramMobile;

    // Contacts Table Columns names

    public tPlanogramMobileDA(SQLiteDatabase db){
        tPlanogramMobileData dt = new tPlanogramMobileData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_intId + " INTEGER,"
                + dt.Property_txtIdPlanogram + " TEXT PRIMARY KEY,"
                + dt.Property_txtNIK + " TEXT NULL,"
                + dt.Property_txtKeterangan + " TEXT NULL,"
                + dt.Property_txtDate + " TEXT NULL,"
                + dt.Property_OutletCode + " TEXT NULL,"
                + dt.Property_OutletName + " TEXT NULL,"
                + dt.Property_UserId + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL,"
                + dt.Property_intSync + " TEXT NULL,"
                + dt.Property_bitActive + " TEXT NULL,"
                + dt.Property_txtBranchCode + " TEXT NULL,"
                + dt.Property_txtBranchName + " TEXT NULL,"
                + dt.Property_intIdAbsenUser + " TEXT NULL,"
                + dt.Property_txtRoleId + " TEXT NULL,"
                + dt.Property_txtBeforeImg1 + " BLOB NULL,"
                + dt.Property_txtBeforeImg2 + " BLOB NULL,"
                + dt.Property_txtAfterImg1 + " BLOB NULL,"
                + dt.Property_txtAfterImg2 + " BLOB NULL,"
                + dt.Property_txtDeviceId + " TEXT NULL,"
                + dt.Property_txtIdCategory + " TEXT NULL,"
                + dt.Property_txtCategoryName + " TEXT NULL,"
                + dt.Property_intIsValid + " TEXT NULL" + ")";
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
    public void SaveDataPlanogram(SQLiteDatabase db, tPlanogramMobileData data) {
        tPlanogramMobileData dt = new tPlanogramMobileData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_txtIdPlanogram, data.get_txtIdPlanogram());
        cv.put(dt.Property_txtNIK, data.get_txtNIK());
        cv.put(dt.Property_txtKeterangan, data.get_txtKeterangan());
        cv.put(dt.Property_txtDate, data.get_dtDate());
        cv.put(dt.Property_OutletCode, data.get_OutletCode());
        cv.put(dt.Property_OutletName, data.get_OutletName());
        cv.put(dt.Property_UserId, data.get_UserId());
        cv.put(dt.Property_intSubmit, data.get_intSubmit());
        cv.put(dt.Property_intSync, data.get_intSync());
        cv.put(dt.Property_bitActive, data.get_bitActive());
        cv.put(dt.Property_txtBranchCode, data.get_txtBranchCode());
        cv.put(dt.Property_txtBranchName, data.get_txtBranchName());
        cv.put(dt.Property_intIdAbsenUser, data.get_intIdAbsenUser());
        cv.put(dt.Property_txtRoleId, data.get_txtRoleId());
        cv.put(dt.Property_txtDeviceId, data.get_txtDeviceId());
        cv.put(dt.Property_txtIdCategory, data.get_txtIdCategory());
        cv.put(dt.Property_txtCategoryName, data.get_txtCategoryName());
        cv.put(dt.Property_intIsValid, data.get_intIsValid());
//        cv.put(dt.Property_txtBeforeImg1, data.get_txtBeforeImg1());
//        cv.put(dt.Property_txtBeforeImg2, data.get_txtBeforeImg2());
//        cv.put(dt.Property_txtAfterImg1, data.get_txtAfterImg1());
//        cv.put(dt.Property_txtAfterImg2, data.get_txtAfterImg2());
        if(data.get_txtIdPlanogram().equals(getAllDataById(db,data.get_txtIdPlanogram()).get_txtIdPlanogram())){
            db.update(TABLE_CONTACTS, cv, dt.Property_txtIdPlanogram+"='"+data.get_txtIdPlanogram()+"'", null);
        } else{
            db.insert(TABLE_CONTACTS, null, cv);
        }
    }

    public tPlanogramMobileData getAllDataByBitActive(SQLiteDatabase db) {
        tPlanogramMobileData contact = new tPlanogramMobileData();
        // Select All Query
        tPlanogramMobileData dt=new tPlanogramMobileData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_bitActive+"=1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                contact.set_intId(cursor.getString(0));
                contact.set_txtIdPlanogram(cursor.getString(1));
                contact.set_txtNIK(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.set_dtDate(cursor.getString(4));
                contact.set_OutletCode(cursor.getString(5));
                contact.set_OutletName(cursor.getString(6));
                contact.set_UserId(cursor.getString(7));
                contact.set_intSubmit(cursor.getString(8));
                contact.set_intSync(cursor.getString(9));
                contact.set_bitActive(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtBeforeImg1(cursor.getBlob(15));
                contact.set_txtBeforeImg2(cursor.getBlob(16));
                contact.set_txtAfterImg1(cursor.getBlob(17));
                contact.set_txtAfterImg2(cursor.getBlob(18));
                contact.set_txtDeviceId(cursor.getString(19));
                contact.set_txtIdCategory(cursor.getString(20));
                contact.set_txtCategoryName(cursor.getString(21));
                contact.set_intIsValid(cursor.getString(22));
                // Adding contact to list
//						contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contact;
    }

    // Getting All Contacts
    public List<tPlanogramMobileData> getAll(SQLiteDatabase db) {
        List<tPlanogramMobileData> contactList = new ArrayList<tPlanogramMobileData>();
        // select All Query
        tPlanogramMobileData dt = new tPlanogramMobileData();
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                tPlanogramMobileData contact = new tPlanogramMobileData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtIdPlanogram(cursor.getString(1));
                contact.set_txtNIK(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.set_dtDate(cursor.getString(4));
                contact.set_OutletCode(cursor.getString(5));
                contact.set_OutletName(cursor.getString(6));
                contact.set_UserId(cursor.getString(7));
                contact.set_intSubmit(cursor.getString(8));
                contact.set_intSync(cursor.getString(9));
                contact.set_bitActive(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtBeforeImg1(cursor.getBlob(15));
                contact.set_txtBeforeImg2(cursor.getBlob(16));
                contact.set_txtAfterImg1(cursor.getBlob(17));
                contact.set_txtAfterImg2(cursor.getBlob(18));
                contact.set_txtDeviceId(cursor.getString(19));
                contact.set_txtIdCategory(cursor.getString(20));
                contact.set_txtCategoryName(cursor.getString(21));
                contact.set_intIsValid(cursor.getString(22));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    // Getting All Contacts
    public tPlanogramMobileData getDataSave(SQLiteDatabase db, String codeOutlet) {
        tPlanogramMobileData contact = new tPlanogramMobileData();
        // select All Query
        tPlanogramMobileData dt = new tPlanogramMobileData();
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + "=0 AND " + dt.Property_bitActive + "=0 AND " + dt.Property_OutletCode  + "='" + codeOutlet + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                contact.set_intId(cursor.getString(0));
                contact.set_txtIdPlanogram(cursor.getString(1));
                contact.set_txtNIK(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.set_dtDate(cursor.getString(4));
                contact.set_OutletCode(cursor.getString(5));
                contact.set_OutletName(cursor.getString(6));
                contact.set_UserId(cursor.getString(7));
                contact.set_intSubmit(cursor.getString(8));
                contact.set_intSync(cursor.getString(9));
                contact.set_bitActive(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtBeforeImg1(cursor.getBlob(15));
                contact.set_txtBeforeImg2(cursor.getBlob(16));
                contact.set_txtAfterImg1(cursor.getBlob(17));
                contact.set_txtAfterImg2(cursor.getBlob(18));
                contact.set_txtDeviceId(cursor.getString(19));
                contact.set_txtIdCategory(cursor.getString(20));
                contact.set_txtCategoryName(cursor.getString(21));
                contact.set_intIsValid(cursor.getString(22));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contact;
    }

    public void UpdateDataItem(SQLiteDatabase db, tSalesProductQuantityHeaderData data) {
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
        db.execSQL("Update " + TABLE_CONTACTS + " set  "
                + dt.Property_intSumAmount + "="
                + String.valueOf(data.get_intSumAmount())
                + dt.Property_intSumItem + "="
                + String.valueOf(data.get_intSumItem())
                + " Where " + dt.Property_intId + "='" + data.get_intId() + "'");
    }

    public void UpdateDataItemForSubmit(SQLiteDatabase db, String dataid) {
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
        db.execSQL("Update " + TABLE_CONTACTS + " set  "
                + dt.Property_intSubmit + "=1"
                + " Where " + dt.Property_intId + "='" + dataid + "'");
    }

    // Getting single contact
    public tSalesProductQuantityHeaderData getData(SQLiteDatabase db, String id){
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{dt.Property_intId, dt.Property_txtQuantityStock,
                        dt.Property_OutletCode, dt.Property_OutletName,
                        dt.Property_txtDate, dt.Property_txtKeterangan, dt.Property_txtNIK
                        , dt.Property_intSumAmount, dt.Property_intSumItem, dt.Property_UserId, dt.Property_intSubmit, dt.Property_txtRoleId
                        , dt.Property_intSync, dt.Property_txtBranchCode, dt.Property_txtBranchName, dt.Property_intIdAbsenUser
                        , dt.Property_txtAfterImg1, dt.Property_txtAfterImg2, dt.Property_txtBeforeImg1, dt.Property_txtBeforeImg2}, dt.Property_intId + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        tSalesProductQuantityHeaderData contact = null;
        if (cursor.getCount() > 0) {
            contact = new tSalesProductQuantityHeaderData();
            if (cursor != null)
                cursor.moveToFirst();
            contact.set_intId(cursor.getString(0));
            contact.set_txtQuantityStock(cursor.getString(1));
            contact.set_OutletCode(cursor.getString(2));
            contact.set_OutletName(cursor.getString(3));
            contact.set_dtDate(cursor.getString(4));
            contact.set_txtKeterangan(cursor.getString(5));
            contact.set_txtNIK(cursor.getString(6));
            contact.set_intSumAmount(cursor.getString(7));
            contact.set_intSumItem(cursor.getString(8));
            contact.set_UserId(cursor.getString(9));
            contact.set_intSubmit(cursor.getString(10));
            contact.set_intSync(cursor.getString(11));
            contact.set_txtBranchCode(cursor.getString(12));
            contact.set_txtBranchName(cursor.getString(13));
            contact.set_intIdAbsenUser(cursor.getString(14));
            contact.set_txtRoleId(cursor.getString(15));
            contact.set_txtAfterImg1(cursor.getBlob(16));
            contact.set_txtAfterImg2(cursor.getBlob(17));
            contact.set_txtBeforeImg1(cursor.getBlob(18));
            contact.set_txtBeforeImg2(cursor.getBlob(19));
        }
        cursor.close();
        return contact;
    }

    public boolean CheckDataPushData(SQLiteDatabase db) {
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
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

    //getting data status submit
    public int countQStockStatusSubmit(SQLiteDatabase db, String code) {

        String selectQuery = "select coalesce(sum(1),0) from [tSalesProductQuantityHeader] where OutletCode='" + code + "' and intSubmit=1 and [intSync]=0";

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
    public int countQStockPush(SQLiteDatabase db,  String code) {
        String selectQuery = "select coalesce(sum(1),0) from [tSalesProductQuantityHeader] where OutletCode='" + code + "' and intSubmit=1 and [intSync]=1";

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

    // Getting All Contacts
    public List<tPlanogramMobileData> getAllDataToPushData(SQLiteDatabase db){
        List<tPlanogramMobileData> contactList = null;
        // Select All Query
        tPlanogramMobileData dt = new tPlanogramMobileData();

        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + "=1 AND " + dt.Property_intSync + "=0";
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tPlanogramMobileData>();
            do {
                tPlanogramMobileData contact = new tPlanogramMobileData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtIdPlanogram(cursor.getString(1));
                contact.set_txtNIK(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.set_dtDate(cursor.getString(4));
                contact.set_OutletCode(cursor.getString(5));
                contact.set_OutletName(cursor.getString(6));
                contact.set_UserId(cursor.getString(7));
                contact.set_intSubmit(cursor.getString(8));
                contact.set_intSync(cursor.getString(9));
                contact.set_bitActive(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtBeforeImg1(cursor.getBlob(15));
                contact.set_txtBeforeImg2(cursor.getBlob(16));
                contact.set_txtAfterImg1(cursor.getBlob(17));
                contact.set_txtAfterImg2(cursor.getBlob(18));
                contact.set_txtDeviceId(cursor.getString(19));
                contact.set_txtIdCategory(cursor.getString(20));
                contact.set_txtCategoryName(cursor.getString(21));
                contact.set_intIsValid(cursor.getString(22));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tPlanogramMobileData> getAllDataSelectImageNotNull(SQLiteDatabase db){
        List<tPlanogramMobileData> contactList = null;
        tPlanogramMobileData dt = new tPlanogramMobileData();

        String selectQuery = "SELECT DISTINCT "+dt.Property_All+" FROM " + TABLE_CONTACTS+" a " +
                "inner JOIN tPlanogramImage b ON b.txtHeaderId = a.txtIdPlanogram and b.txtType='BEFORE'\n" +
                "inner JOIN tPlanogramImage c ON c.txtHeaderId = a.txtIdPlanogram and c.txtType='AFTER'" +
                " WHERE " + dt.Property_intSubmit + "=1 AND " + dt.Property_intSync + "=0";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tPlanogramMobileData>();
            do {
                tPlanogramMobileData contact = new tPlanogramMobileData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtIdPlanogram(cursor.getString(1));
                contact.set_txtNIK(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.set_dtDate(cursor.getString(4));
                contact.set_OutletCode(cursor.getString(5));
                contact.set_OutletName(cursor.getString(6));
                contact.set_UserId(cursor.getString(7));
                contact.set_intSubmit(cursor.getString(8));
                contact.set_intSync(cursor.getString(9));
                contact.set_bitActive(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtBeforeImg1(cursor.getBlob(15));
                contact.set_txtBeforeImg2(cursor.getBlob(16));
                contact.set_txtAfterImg1(cursor.getBlob(17));
                contact.set_txtAfterImg2(cursor.getBlob(18));
                contact.set_txtDeviceId(cursor.getString(19));
                contact.set_txtIdCategory(cursor.getString(20));
                contact.set_txtCategoryName(cursor.getString(21));
                contact.set_intIsValid(cursor.getString(22));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPlanogramMobileData> getAllDataSelectImageNotNullByOutletUnsubmit(SQLiteDatabase db, String code){
        List<tPlanogramMobileData> contactList = null;
        tPlanogramMobileData dt = new tPlanogramMobileData();

        String selectQuery = "SELECT DISTINCT "+dt.Property_All+" FROM " + TABLE_CONTACTS+" a " +
                "inner JOIN tPlanogramImage b ON b.txtHeaderId = a.txtIdPlanogram and b.txtType='BEFORE'\n" +
                "inner JOIN tPlanogramImage c ON c.txtHeaderId = a.txtIdPlanogram and c.txtType='AFTER'" +
                " WHERE " + dt.Property_intSubmit + "=0 AND " + dt.Property_intSync + "=0 AND "  + dt.Property_OutletCode  + "='" + code + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tPlanogramMobileData>();
            do {
                tPlanogramMobileData contact = new tPlanogramMobileData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtIdPlanogram(cursor.getString(1));
                contact.set_txtNIK(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.set_dtDate(cursor.getString(4));
                contact.set_OutletCode(cursor.getString(5));
                contact.set_OutletName(cursor.getString(6));
                contact.set_UserId(cursor.getString(7));
                contact.set_intSubmit(cursor.getString(8));
                contact.set_intSync(cursor.getString(9));
                contact.set_bitActive(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtBeforeImg1(cursor.getBlob(15));
                contact.set_txtBeforeImg2(cursor.getBlob(16));
                contact.set_txtAfterImg1(cursor.getBlob(17));
                contact.set_txtAfterImg2(cursor.getBlob(18));
                contact.set_txtDeviceId(cursor.getString(19));
                contact.set_txtIdCategory(cursor.getString(20));
                contact.set_txtCategoryName(cursor.getString(21));
                contact.set_intIsValid(cursor.getString(22));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPlanogramMobileData> getAllData(SQLiteDatabase db) {
        List<tPlanogramMobileData> contactList = null;
        // select all query
        tPlanogramMobileData dt = new tPlanogramMobileData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS  + " WHERE " + dt.Property_intSubmit + " =1 " + " ORDER BY dtDate ASC";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tPlanogramMobileData>();
            do {
                tPlanogramMobileData contact = new tPlanogramMobileData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtIdPlanogram(cursor.getString(1));
                contact.set_txtNIK(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.set_dtDate(cursor.getString(4));
                contact.set_OutletCode(cursor.getString(5));
                contact.set_OutletName(cursor.getString(6));
                contact.set_UserId(cursor.getString(7));
                contact.set_intSubmit(cursor.getString(8));
                contact.set_intSync(cursor.getString(9));
                contact.set_bitActive(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtBeforeImg1(cursor.getBlob(15));
                contact.set_txtBeforeImg2(cursor.getBlob(16));
                contact.set_txtAfterImg1(cursor.getBlob(17));
                contact.set_txtAfterImg2(cursor.getBlob(18));
                contact.set_txtDeviceId(cursor.getString(19));
                contact.set_txtIdCategory(cursor.getString(20));
                contact.set_txtCategoryName(cursor.getString(21));
                contact.set_intIsValid(cursor.getString(22));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tSalesProductQuantityHeaderData> getAllDataByOutlet(SQLiteDatabase db, String txtOutletCode) {
        List<tSalesProductQuantityHeaderData> contactList = null;
        // select all query
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_OutletCode + "='" + txtOutletCode + "' AND " + dt.Property_intSubmit + "=1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityHeaderData>();
            do {
                tSalesProductQuantityHeaderData contact = new tSalesProductQuantityHeaderData();
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

    public List<tSalesProductQuantityHeaderData> getAllDataSubmit(SQLiteDatabase db) {
        List<tSalesProductQuantityHeaderData> contactList = null;
        // Select All Query
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + "=1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tSalesProductQuantityHeaderData>();
            do {
                tSalesProductQuantityHeaderData contact = new tSalesProductQuantityHeaderData();
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

    public List<tSalesProductQuantityHeaderData> getAllDataNotSync(SQLiteDatabase db) {
        List<tSalesProductQuantityHeaderData> contactList = null;
        // select All Query
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSync + "=0";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityHeaderData>();
            do {
                tSalesProductQuantityHeaderData contact = new tSalesProductQuantityHeaderData();
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

    public List<tSalesProductQuantityHeaderData> getAllDataByIdAbsen(SQLiteDatabase db, String Id) {
        List<tSalesProductQuantityHeaderData> contactList = null;
        // select all query
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intIdAbsenUser + "='" + Id + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityHeaderData>();
            do {
                tSalesProductQuantityHeaderData contact = new tSalesProductQuantityHeaderData();
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

    public tPlanogramMobileData getAllDataById(SQLiteDatabase db, String Id) {
        List<tPlanogramMobileData> contactList = null;
        // select all query
        tPlanogramMobileData contact = new tPlanogramMobileData();
        String selectQuery = "SELECT  " + contact.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + contact.Property_txtIdPlanogram + "='" + Id + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tPlanogramMobileData>();
            do {
//                tPlanogramMobileData contact = new tPlanogramMobileData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtIdPlanogram(cursor.getString(1));
                contact.set_txtNIK(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.set_dtDate(cursor.getString(4));
                contact.set_OutletCode(cursor.getString(5));
                contact.set_OutletName(cursor.getString(6));
                contact.set_UserId(cursor.getString(7));
                contact.set_intSubmit(cursor.getString(8));
                contact.set_intSync(cursor.getString(9));
                contact.set_bitActive(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtBeforeImg1(cursor.getBlob(15));
                contact.set_txtBeforeImg2(cursor.getBlob(16));
                contact.set_txtAfterImg1(cursor.getBlob(17));
                contact.set_txtAfterImg2(cursor.getBlob(18));
                contact.set_txtDeviceId(cursor.getString(19));
                contact.set_txtIdCategory(cursor.getString(20));
                contact.set_txtCategoryName(cursor.getString(21));
                contact.set_intIsValid(cursor.getString(22));

                // Adding contact to list
//                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contact;
    }


    //get data by int sync
    public List<tSalesProductQuantityHeaderData> getAllDataByIntSyc(SQLiteDatabase db, String int_sync) {
        List<tSalesProductQuantityHeaderData> contactList = null;
        // select all query
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSync + "='" + int_sync + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityHeaderData>();
            do {
                tSalesProductQuantityHeaderData contact = new tSalesProductQuantityHeaderData();
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

    public List<tSalesProductQuantityHeaderData> getAllDataByIntSycAndOutlet(SQLiteDatabase db, String int_sync, String outlet){
        List<tSalesProductQuantityHeaderData> contactList = null;
        // select all query
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSync + "='" + int_sync + "' AND " + dt.Property_OutletCode + "='" + outlet + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityHeaderData>();
            do {
                tSalesProductQuantityHeaderData contact = new tSalesProductQuantityHeaderData();
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

    public List<tPlanogramMobileData> getAllDataByOutletCode(SQLiteDatabase db, String code){
        List<tPlanogramMobileData> contactList = null;
        // select all query
        tPlanogramMobileData dt = new tPlanogramMobileData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_OutletCode + "='" + code + "' ";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tPlanogramMobileData>();
            do {
                tPlanogramMobileData contact = new tPlanogramMobileData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtIdPlanogram(cursor.getString(1));
                contact.set_txtNIK(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.set_dtDate(cursor.getString(4));
                contact.set_OutletCode(cursor.getString(5));
                contact.set_OutletName(cursor.getString(6));
                contact.set_UserId(cursor.getString(7));
                contact.set_intSubmit(cursor.getString(8));
                contact.set_intSync(cursor.getString(9));
                contact.set_bitActive(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtBeforeImg1(cursor.getBlob(15));
                contact.set_txtBeforeImg2(cursor.getBlob(16));
                contact.set_txtAfterImg1(cursor.getBlob(17));
                contact.set_txtAfterImg2(cursor.getBlob(18));
                contact.set_txtDeviceId(cursor.getString(19));
                contact.set_txtIdCategory(cursor.getString(20));
                contact.set_txtCategoryName(cursor.getString(21));
                contact.set_intIsValid(cursor.getString(22));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tSalesProductQuantityHeaderData> getDataByQuantityStock(SQLiteDatabase db, String id){
        List<tSalesProductQuantityHeaderData> contactList = new ArrayList<tSalesProductQuantityHeaderData>();
        // select all query
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_txtQuantityStock + "='" + id + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                tSalesProductQuantityHeaderData contact = new tSalesProductQuantityHeaderData();
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

    public List<tSalesProductQuantityHeaderData> getAllDataByDateCheckin(SQLiteDatabase db, String date) {
        List<tSalesProductQuantityHeaderData> contactList = null;
        // select All Query
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_txtDate + "='" + date + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityHeaderData>();
            do {
                tSalesProductQuantityHeaderData contact = new tSalesProductQuantityHeaderData();
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

    public List<tSalesProductQuantityHeaderData> getAllData2(SQLiteDatabase db) {
        List<tSalesProductQuantityHeaderData> contactList = new ArrayList<tSalesProductQuantityHeaderData>();
        // Select All Query
        tSalesProductQuantityHeaderData dt=new tSalesProductQuantityHeaderData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                tSalesProductQuantityHeaderData contact = new tSalesProductQuantityHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtQuantityStock(cursor.getString(1));
                contact.set_txtNIK(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.set_dtDate(cursor.getString(4));
                contact.set_OutletCode(cursor.getString(5));
                contact.set_OutletName(cursor.getString(6));
                contact.set_intSumItem(cursor.getString(7));
                contact.set_intSumAmount(cursor.getString(8));
                contact.set_UserId(cursor.getString(9));
                contact.set_intSubmit(cursor.getString(10));
                contact.set_intSync(cursor.getString(11));
                contact.set_txtBranchCode(cursor.getString(12));
                contact.set_txtBranchName(cursor.getString(13));
                contact.set_intIdAbsenUser(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contact.set_txtAfterImg1(cursor.getBlob(16));
                contact.set_txtAfterImg2(cursor.getBlob(17));
                contact.set_txtBeforeImg1(cursor.getBlob(18));
                contact.set_txtAfterImg2(cursor.getBlob(19));
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
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
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

    public int saveDataPush(SQLiteDatabase db, String id) {
        tPlanogramMobileData dt = new tPlanogramMobileData();

        ContentValues values = new ContentValues();
        values.put(dt.Property_intSync, "1");

        // updating row
        return db.update(TABLE_CONTACTS, values, dt.Property_txtIdPlanogram + " = ? ",
                new String[] { String.valueOf(id) });
    }

    public List<tSalesProductQuantityHeaderData> getLastData(SQLiteDatabase db) {
        List<tSalesProductQuantityHeaderData> contactList = null;
        // select all query
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToLast()) {
            contactList = new ArrayList<tSalesProductQuantityHeaderData>();
            do {
                tSalesProductQuantityHeaderData contact = new tSalesProductQuantityHeaderData();
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

    public void deleteByQuantityStock(SQLiteDatabase db, String Noso) {
        tSalesProductQuantityHeaderData dt = new tSalesProductQuantityHeaderData();
        String whereClause = dt.Property_txtQuantityStock + " = ?";
        String[] whereArgs = new String[]{
                String.valueOf(Noso)
        };

        db.delete(TABLE_CONTACTS, whereClause, whereArgs);
    }
    //getting count data push
    public int countPlanogramHomeAbsenPush(SQLiteDatabase db,  String code) {
        String selectQuery = "select coalesce(sum(1),0) from [tPlanogramMobile] where OutletCode='" + code + "' and intSubmit=1 and [intSync]=1";

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
    //getting data status submit
    public int countPlanogramHomeAbsenByStatusSubmit(SQLiteDatabase db, String code) {

        String selectQuery = "select coalesce(sum(1),0) from [tPlanogramMobile] where OutletCode='" + code + "' and intSubmit=1 and [intSync]=0";

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
    //getting data status save
    public int countPlanogramHomeAbsenByStatusSave(SQLiteDatabase db, String code) {

        String selectQuery = "select coalesce(sum(1),0) from [tPlanogramMobile] where OutletCode='" + code + "' and intSubmit=0 and [intSync]=0";

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
    public int updateDataSubmit(SQLiteDatabase db, tPlanogramMobileData data) {
        tPlanogramMobileData dt = new tPlanogramMobileData();

        ContentValues values = new ContentValues();
        values.put(dt.Property_intSubmit, "1");

        // updating row
        return db.update(TABLE_CONTACTS, values, dt.Property_txtIdPlanogram + " = ? ",
                new String[] { String.valueOf(data.get_txtIdPlanogram()) });
    }


    public List<tPlanogramMobileData> getAllDataByOutletCodeUnsubmit(SQLiteDatabase db, String code) {
        List<tPlanogramMobileData> contactList = new ArrayList<tPlanogramMobileData>();
        // Select All Query
        tPlanogramMobileData dt = new tPlanogramMobileData();

//        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_txtSumberData + "='" + code + "'" + " AND " + dt.Property_intSubmit + " ='1' AND bitActive = '1' ORDER BY txtSubmissionId DESC ";
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_OutletCode + "='" + code + "'" + " AND bitActive='1' AND intSubmit='0' AND intSync='0' ORDER BY " + dt.Property_txtDate + " DESC ";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tPlanogramMobileData contact = new tPlanogramMobileData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtIdPlanogram(cursor.getString(1));
                contact.set_txtNIK(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.set_dtDate(cursor.getString(4));
                contact.set_OutletCode(cursor.getString(5));
                contact.set_OutletName(cursor.getString(6));
                contact.set_UserId(cursor.getString(7));
                contact.set_intSubmit(cursor.getString(8));
                contact.set_intSync(cursor.getString(9));
                contact.set_bitActive(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtBeforeImg1(cursor.getBlob(15));
                contact.set_txtBeforeImg2(cursor.getBlob(16));
                contact.set_txtAfterImg1(cursor.getBlob(17));
                contact.set_txtAfterImg2(cursor.getBlob(18));
                contact.set_txtDeviceId(cursor.getString(19));
                contact.set_txtIdCategory(cursor.getString(20));
                contact.set_txtCategoryName(cursor.getString(21));
                contact.set_intIsValid(cursor.getString(22));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tPlanogramMobileData> getAllDataByOutletCodeForView(SQLiteDatabase db, String code) {
        List<tPlanogramMobileData> contactList = new ArrayList<tPlanogramMobileData>();
        // Select All Query
        tPlanogramMobileData dt = new tPlanogramMobileData();

//        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_txtSumberData + "='" + code + "'" + " AND " + dt.Property_intSubmit + " ='1' AND bitActive = '1' ORDER BY txtSubmissionId DESC ";
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_OutletCode + "='" + code + "'" + " AND bitActive='0' ORDER BY " + dt.Property_intId + " DESC ";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tPlanogramMobileData contact = new tPlanogramMobileData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtIdPlanogram(cursor.getString(1));
                contact.set_txtNIK(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.set_dtDate(cursor.getString(4));
                contact.set_OutletCode(cursor.getString(5));
                contact.set_OutletName(cursor.getString(6));
                contact.set_UserId(cursor.getString(7));
                contact.set_intSubmit(cursor.getString(8));
                contact.set_intSync(cursor.getString(9));
                contact.set_bitActive(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtRoleId(cursor.getString(14));
                contact.set_txtBeforeImg1(cursor.getBlob(15));
                contact.set_txtBeforeImg2(cursor.getBlob(16));
                contact.set_txtAfterImg1(cursor.getBlob(17));
                contact.set_txtAfterImg2(cursor.getBlob(18));
                contact.set_txtDeviceId(cursor.getString(19));
                contact.set_txtIdCategory(cursor.getString(20));
                contact.set_txtCategoryName(cursor.getString(21));
                contact.set_intIsValid(cursor.getString(22));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    // Deleting single contact
    public void deleteByID(SQLiteDatabase db, String id) {
        tPlanogramMobileData dt = new tPlanogramMobileData();
        String whereClause = dt.Property_txtIdPlanogram + " = ?";
        String[] whereArgs = new String[]{
                String.valueOf(id)
        };

        db.delete(TABLE_CONTACTS, whereClause, whereArgs);
    }

}
