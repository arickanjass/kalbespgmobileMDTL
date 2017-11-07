package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mParentData;

/**
 * Created by Dewi Oktaviani on 03/05/2017.
 */

public class mParentDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mParent;
    //create table
    public mParentDA(SQLiteDatabase db){
        mParentData dt = new mParentData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "( " + dt.Property_intParentId + " TEXT PRIMARY KEY," + dt.Property_txtParentName + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    //drop table
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    //insert value
    public void SaveDatamParent(SQLiteDatabase db, mParentData data){
        mParentData dt = new mParentData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_intParentId, String.valueOf(data.get_intParentId()));
        cv.put(dt.Property_txtParentName, String.valueOf(data.get_txtParentName()));
        if (data.get_intParentId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }
    public void DeleteAllDatamParent(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<mParentData> GetAllData(SQLiteDatabase db){
        List<mParentData> contactList = new ArrayList<mParentData>();
        mParentData dt = new mParentData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY intParentId ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mParentData contact = new mParentData();
                contact.set_intParentId(cursor.getString(0));
                contact.set_txtParentName(cursor.getString(1));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
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
