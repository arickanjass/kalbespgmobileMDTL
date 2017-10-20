package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tPOPStandardDetailData;

/**
 * Created by Dewi Oktaviani on 17/10/2017.
 */

public class tPOPStandardDetailDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tPOPStandardDetail;
    //create table
    public tPOPStandardDetailDA(SQLiteDatabase db){
        tPOPStandardDetailData dt = new tPOPStandardDetailData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "( " + dt.Property_intId + " TEXT PRIMARY KEY," + dt.Property_intHeaderId + " TEXT NULL,"
                + dt.Property_txtImg1 + " TEXT NULL," + dt.Property_txtImg2 + " TEXT NULL," + dt.Property_dtDatetime + " TEXT NULL,"
                + dt.Property_intSync + " TEXT NULL," + dt.Property_intSubmit + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    //drop table
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    //insert value
    public void SaveDatatPOPStandardDetail(SQLiteDatabase db, tPOPStandardDetailData data){
        tPOPStandardDetailData dt = new tPOPStandardDetailData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_intId, String.valueOf(data.get_intId()));
        cv.put(dt.Property_intHeaderId, String.valueOf(data.get_intHeaderId()));
        cv.put(dt.Property_txtImg1, data.get_txtImg1());
        cv.put(dt.Property_txtImg2, data.get_txtImg2());
        cv.put(dt.Property_dtDatetime, String.valueOf(data.get_dtDatetime()));
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
    public void DeleteAlltPOPStandardDetail(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<tPOPStandardDetailData> GetAllData(SQLiteDatabase db){
        List<tPOPStandardDetailData> contactList = new ArrayList<tPOPStandardDetailData>();
        tPOPStandardDetailData dt = new tPOPStandardDetailData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY intId ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tPOPStandardDetailData contact = new tPOPStandardDetailData();
                contact.set_intId(cursor.getString(0));
                contact.set_intHeaderId(cursor.getString(1));
                contact.set_txtImg1(cursor.getBlob(2));
                contact.set_txtImg2(cursor.getBlob(3));
                contact.set_dtDatetime(cursor.getString(4));
                contact.set_intSync(cursor.getString(5));
                contact.set_intSubmit(cursor.getString(6));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPOPStandardDetailData> GetDataToPush(SQLiteDatabase db){
        List<tPOPStandardDetailData> contactList = null;
        tPOPStandardDetailData dt = new tPOPStandardDetailData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " Where " + dt.Property_intSubmit + "=1 AND " + dt.Property_intSync + "=0";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tPOPStandardDetailData>();
            do {
                tPOPStandardDetailData contact = new tPOPStandardDetailData();
                contact.set_intId(cursor.getString(0));
                contact.set_intHeaderId(cursor.getString(1));
                contact.set_txtImg1(cursor.getBlob(2));
                contact.set_txtImg2(cursor.getBlob(3));
                contact.set_dtDatetime(cursor.getString(4));
                contact.set_intSync(cursor.getString(5));
                contact.set_intSubmit(cursor.getString(6));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public tPOPStandardDetailData GetDataByHeaderId(SQLiteDatabase db, String intHeaderId){
        tPOPStandardDetailData contact = null;
        tPOPStandardDetailData dt = new tPOPStandardDetailData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " Where " + dt.Property_intHeaderId + "='" + intHeaderId + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contact = new tPOPStandardDetailData();
            do {
                contact.set_intId(cursor.getString(0));
                contact.set_intHeaderId(cursor.getString(1));
                contact.set_txtImg1(cursor.getBlob(2));
                contact.set_txtImg2(cursor.getBlob(3));
                contact.set_dtDatetime(cursor.getString(4));
                contact.set_intSync(cursor.getString(5));
                contact.set_intSubmit(cursor.getString(6));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contact;
    }
}
