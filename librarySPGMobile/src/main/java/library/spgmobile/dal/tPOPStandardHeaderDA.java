package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tJawabanUserData;
import library.spgmobile.common.tPOPStandardHeaderData;

/**
 * Created by Dewi Oktaviani on 17/10/2017.
 */

public class tPOPStandardHeaderDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tPOPStandardHeader;
    //create table
    public tPOPStandardHeaderDA(SQLiteDatabase db){
        tPOPStandardHeaderData dt = new tPOPStandardHeaderData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "( " + dt.Property_intId + " TEXT PRIMARY KEY," + dt.Property_txtType + " TEXT NULL,"
                + dt.Property_bolHavePOP + " TEXT NULL," + dt.Property_txtCategory + " TEXT NULL," + dt.Property_txtReason + " TEXT NULL,"
                + dt.Property_intRoleId + " TEXT NULL," + dt.Property_txtUserName + " TEXT NULL," + dt.Property_txtNIK + " TEXT NULL,"
                + dt.Property_txtOutletCode + " TEXT NULL," + dt.Property_txtOutletName + " TEXT NULL," + dt.Property_txtBranchCode + " TEXT NULL,"
                + dt.Property_txtBranchName + " TEXT NULL," + dt.Property_dtDatetime + " TEXT NULL," + dt.Property_intSync + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    //drop table
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    //insert value
    public void SaveDatatPOPStandardHeader(SQLiteDatabase db, tPOPStandardHeaderData data){
        tPOPStandardHeaderData dt = new tPOPStandardHeaderData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_intId, String.valueOf(data.get_intId()));
        cv.put(dt.Property_txtType, String.valueOf(data.get_txtType()));
        cv.put(dt.Property_bolHavePOP, String.valueOf(data.get_bolHavePOP()));
        cv.put(dt.Property_txtCategory, String.valueOf(data.get_txtCategory()));
        cv.put(dt.Property_txtReason, String.valueOf(data.get_txtReason()));
        cv.put(dt.Property_intRoleId, String.valueOf(data.get_intRoleId()));
        cv.put(dt.Property_txtUserName, String.valueOf(data.get_txtUserName()));
        cv.put(dt.Property_txtNIK, String.valueOf(data.get_txtNIK()));
        cv.put(dt.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
        cv.put(dt.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
        cv.put(dt.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
        cv.put(dt.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
        cv.put(dt.Property_dtDatetime, String.valueOf(data.get_DtDatetime()));
        if (data.get_intId() == null){
            cv.put(dt.Property_intSubmit, String.valueOf(data.get_intSubmit()));
            cv.put(dt.Property_intSync, String.valueOf(data.get_intSync()));
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_intSubmit, String.valueOf(data.get_intSubmit()));
            cv.put(dt.Property_intSync, String.valueOf(data.get_intSync()));
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }
    public void DeleteAlltPOPStandardHeader(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<tPOPStandardHeaderData> GetAllData(SQLiteDatabase db){
        List<tPOPStandardHeaderData> contactList = new ArrayList<tPOPStandardHeaderData>();
        tPOPStandardHeaderData dt = new tPOPStandardHeaderData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY intId ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tPOPStandardHeaderData contact = new tPOPStandardHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtType(cursor.getString(1));
                contact.set_bolHavePOP(cursor.getString(2));
                contact.set_txtCategory(cursor.getString(3));
                contact.set_txtReason(cursor.getString(4));
                contact.set_intRoleId(cursor.getString(5));
                contact.set_txtUserName(cursor.getString(6));
                contact.set_txtNIK(cursor.getString(7));
                contact.set_txtOutletCode(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_txtBranchCode(cursor.getString(10));
                contact.set_txtBranchName(cursor.getString(11));
                contact.set_DtDatetime(cursor.getString(12));
                contact.set_intSync(cursor.getString(13));
                contact.set_intSubmit(cursor.getString(14));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPOPStandardHeaderData> GetByHeaderId(SQLiteDatabase db, String intId){
        List<tPOPStandardHeaderData> contactList = new ArrayList<tPOPStandardHeaderData>();
        tPOPStandardHeaderData dt = new tPOPStandardHeaderData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " Where " + dt.Property_intId + "='" + intId + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tPOPStandardHeaderData contact = new tPOPStandardHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtType(cursor.getString(1));
                contact.set_bolHavePOP(cursor.getString(2));
                contact.set_txtCategory(cursor.getString(3));
                contact.set_txtReason(cursor.getString(4));
                contact.set_intRoleId(cursor.getString(5));
                contact.set_txtUserName(cursor.getString(6));
                contact.set_txtNIK(cursor.getString(7));
                contact.set_txtOutletCode(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_txtBranchCode(cursor.getString(10));
                contact.set_txtBranchName(cursor.getString(11));
                contact.set_DtDatetime(cursor.getString(12));
                contact.set_intSync(cursor.getString(13));
                contact.set_intSubmit(cursor.getString(14));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPOPStandardHeaderData> GetByOutletCode(SQLiteDatabase db, String code, String type){
        List<tPOPStandardHeaderData> contactList = new ArrayList<tPOPStandardHeaderData>();
        tPOPStandardHeaderData dt = new tPOPStandardHeaderData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " Where " + dt.Property_txtOutletCode + "='" + code + "' AND " + dt.Property_txtType + "='" + type + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tPOPStandardHeaderData contact = new tPOPStandardHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtType(cursor.getString(1));
                contact.set_bolHavePOP(cursor.getString(2));
                contact.set_txtCategory(cursor.getString(3));
                contact.set_txtReason(cursor.getString(4));
                contact.set_intRoleId(cursor.getString(5));
                contact.set_txtUserName(cursor.getString(6));
                contact.set_txtNIK(cursor.getString(7));
                contact.set_txtOutletCode(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_txtBranchCode(cursor.getString(10));
                contact.set_txtBranchName(cursor.getString(11));
                contact.set_DtDatetime(cursor.getString(12));
                contact.set_intSync(cursor.getString(13));
                contact.set_intSubmit(cursor.getString(14));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPOPStandardHeaderData> GetByOutletCodeReport(SQLiteDatabase db, String code){
        List<tPOPStandardHeaderData> contactList = new ArrayList<tPOPStandardHeaderData>();
        tPOPStandardHeaderData dt = new tPOPStandardHeaderData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " Where " + dt.Property_txtOutletCode + "='" + code + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tPOPStandardHeaderData contact = new tPOPStandardHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtType(cursor.getString(1));
                contact.set_bolHavePOP(cursor.getString(2));
                contact.set_txtCategory(cursor.getString(3));
                contact.set_txtReason(cursor.getString(4));
                contact.set_intRoleId(cursor.getString(5));
                contact.set_txtUserName(cursor.getString(6));
                contact.set_txtNIK(cursor.getString(7));
                contact.set_txtOutletCode(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_txtBranchCode(cursor.getString(10));
                contact.set_txtBranchName(cursor.getString(11));
                contact.set_DtDatetime(cursor.getString(12));
                contact.set_intSync(cursor.getString(13));
                contact.set_intSubmit(cursor.getString(14));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPOPStandardHeaderData> GetAllOutlet(SQLiteDatabase db){
        List<tPOPStandardHeaderData> contactList = new ArrayList<tPOPStandardHeaderData>();
        tPOPStandardHeaderData dt = new tPOPStandardHeaderData();
        String selectQuery = "Select " + dt.Property_txtOutletName + "," + dt.Property_txtOutletCode + " FROM " + TABLE_CONTACTS + " GROUP BY " + dt.Property_txtOutletName + "," + dt.Property_txtOutletCode;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tPOPStandardHeaderData contact = new tPOPStandardHeaderData();
                contact.set_txtOutletName(cursor.getString(0));
                contact.set_txtOutletCode(cursor.getString(1));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPOPStandardHeaderData> GetTypePOPByOutlet(SQLiteDatabase db, String outletName){
        List<tPOPStandardHeaderData> contactList = new ArrayList<tPOPStandardHeaderData>();
        tPOPStandardHeaderData dt = new tPOPStandardHeaderData();
        String selectQuery = "Select " + dt.Property_type + " FROM " + TABLE_CONTACTS + " Where " + dt.Property_txtOutletName +
                "='" + outletName + "' GROUP BY " + dt.Property_txtType;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tPOPStandardHeaderData contact = new tPOPStandardHeaderData();
                contact.set_txtType(cursor.getString(0));
                contact.set_txtBranchName(cursor.getString(1));
                contact.set_txtOutletName(cursor.getString(2));
                contact.set_txtOutletCode(cursor.getString(3));
                contact.set_txtUserName(cursor.getString(4));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPOPStandardHeaderData> GetAllOutletbyName(SQLiteDatabase db, String code){
        List<tPOPStandardHeaderData> contactList = new ArrayList<tPOPStandardHeaderData>();
        tPOPStandardHeaderData dt = new tPOPStandardHeaderData();
        String selectQuery = "Select " + dt.Property_txtOutletName + "," + dt.Property_txtOutletCode + " FROM " + TABLE_CONTACTS + " Where " + dt.Property_txtOutletName + "='" +
                code + "' GROUP BY " + dt.Property_txtOutletName;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tPOPStandardHeaderData contact = new tPOPStandardHeaderData();
                contact.set_txtOutletName(cursor.getString(0));
                contact.set_txtOutletCode(cursor.getString(1));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPOPStandardHeaderData> GetByOutletAndSync(SQLiteDatabase db, String code, String sync){
        List<tPOPStandardHeaderData> contactList = new ArrayList<tPOPStandardHeaderData>();
        tPOPStandardHeaderData dt = new tPOPStandardHeaderData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " Where " + dt.Property_txtOutletCode + "='" + code + "' AND "
                + dt.Property_intSync + "='" + sync + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tPOPStandardHeaderData contact = new tPOPStandardHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtType(cursor.getString(1));
                contact.set_bolHavePOP(cursor.getString(2));
                contact.set_txtCategory(cursor.getString(3));
                contact.set_txtReason(cursor.getString(4));
                contact.set_intRoleId(cursor.getString(5));
                contact.set_txtUserName(cursor.getString(6));
                contact.set_txtNIK(cursor.getString(7));
                contact.set_txtOutletCode(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_txtBranchCode(cursor.getString(10));
                contact.set_txtBranchName(cursor.getString(11));
                contact.set_DtDatetime(cursor.getString(12));
                contact.set_intSync(cursor.getString(13));
                contact.set_intSubmit(cursor.getString(14));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public int getAllCheckPushData(SQLiteDatabase db) {
        List<tPOPStandardHeaderData> contactList = null;
        // Select All Query
        tPOPStandardHeaderData dt = new tPOPStandardHeaderData();
        String selectQuery = "SELECT  1 FROM "
                + TABLE_CONTACTS +" WHERE " +dt.Property_intSubmit +" ='1' And "+dt.Property_intSync+"=0" ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // return count
        int index = cursor.getCount();
        cursor.close();
        return index;
    }

    public List<tPOPStandardHeaderData> GetDataToPush(SQLiteDatabase db){
        List<tPOPStandardHeaderData> contactList = null;
        tPOPStandardHeaderData dt = new tPOPStandardHeaderData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " Where " + dt.Property_intSubmit + "=1 AND " + dt.Property_intSync + "=0";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tPOPStandardHeaderData>();
            do {
                tPOPStandardHeaderData contact = new tPOPStandardHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtType(cursor.getString(1));
                contact.set_bolHavePOP(cursor.getString(2));
                contact.set_txtCategory(cursor.getString(3));
                contact.set_txtReason(cursor.getString(4));
                contact.set_intRoleId(cursor.getString(5));
                contact.set_txtUserName(cursor.getString(6));
                contact.set_txtNIK(cursor.getString(7));
                contact.set_txtOutletCode(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_txtBranchCode(cursor.getString(10));
                contact.set_txtBranchName(cursor.getString(11));
                contact.set_DtDatetime(cursor.getString(12));
                contact.set_intSync(cursor.getString(13));
                contact.set_intSubmit(cursor.getString(14));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public tPOPStandardHeaderData GetByLastBeforeSaveDetail(SQLiteDatabase db){
        tPOPStandardHeaderData contact = new tPOPStandardHeaderData();
        tPOPStandardHeaderData dt = new tPOPStandardHeaderData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " Where " + dt.Property_intSubmit + "=0";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                contact.set_intId(cursor.getString(0));
                contact.set_txtType(cursor.getString(1));
                contact.set_bolHavePOP(cursor.getString(2));
                contact.set_txtCategory(cursor.getString(3));
                contact.set_txtReason(cursor.getString(4));
                contact.set_intRoleId(cursor.getString(5));
                contact.set_txtUserName(cursor.getString(6));
                contact.set_txtNIK(cursor.getString(7));
                contact.set_txtOutletCode(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_txtBranchCode(cursor.getString(10));
                contact.set_txtBranchName(cursor.getString(11));
                contact.set_DtDatetime(cursor.getString(12));
                contact.set_intSync(cursor.getString(13));
                contact.set_intSubmit(cursor.getString(14));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contact;
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
