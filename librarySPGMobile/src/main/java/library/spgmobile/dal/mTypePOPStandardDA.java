package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mTypePOPStandardData;

/**
 * Created by Dewi Oktaviani on 17/10/2017.
 */

public class mTypePOPStandardDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mTypePOPStandard;
    //create table
    public mTypePOPStandardDA(SQLiteDatabase db){
        mTypePOPStandardData dt = new mTypePOPStandardData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "( " + dt.Property_intId + " TEXT PRIMARY KEY," + dt.Property_txtType + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    //drop table
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    //insert value
    public void SaveDatamTypePOPStandard(SQLiteDatabase db, mTypePOPStandardData data){
        mTypePOPStandardData dt = new mTypePOPStandardData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_intId, String.valueOf(data.get_intId()));
        cv.put(dt.Property_txtType, String.valueOf(data.get_txtType()));
        if (data.get_intId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        }else {
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }
    public void DeleteAllDatamTypePOPStandard(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<mTypePOPStandardData> GetAllData(SQLiteDatabase db){
        List<mTypePOPStandardData> contactList = new ArrayList<mTypePOPStandardData>();
        mTypePOPStandardData dt = new mTypePOPStandardData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY intId ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mTypePOPStandardData contact = new mTypePOPStandardData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtType(cursor.getString(1));
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
