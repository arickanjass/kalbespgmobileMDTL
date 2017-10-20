package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mCategoryPOPStandardData;

/**
 * Created by Dewi Oktaviani on 17/10/2017.
 */

public class mCategoryPOPStandardDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mCategoryPOPStandard;
    //create table
    public mCategoryPOPStandardDA(SQLiteDatabase db){
        mCategoryPOPStandardData dt = new mCategoryPOPStandardData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "( " + dt.Property_intId + " TEXT PRIMARY KEY," + dt.Property_txtCategoryCode + " TEXT NULL,"
                + dt.Property_txtCategoryName + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    //drop table
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    //insert value
    public void SaveData(SQLiteDatabase db, mCategoryPOPStandardData data){
        mCategoryPOPStandardData dt = new mCategoryPOPStandardData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_intId, String.valueOf(data.get_intId()));
        cv.put(dt.Property_txtCategoryCode, String.valueOf(data.get_txtCategoryCode()));
        cv.put(dt.Property_txtCategoryName, String.valueOf(data.get_txtCategoryName()));
       if (data.get_intId() == null){
           db.insert(TABLE_CONTACTS, null, cv);
       }else {
           db.replace(TABLE_CONTACTS, null, cv);
       }
    }
    public void DeleteAllData(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<mCategoryPOPStandardData> GetAllData(SQLiteDatabase db){
        List<mCategoryPOPStandardData> contactList = new ArrayList<mCategoryPOPStandardData>();
        mCategoryPOPStandardData dt = new mCategoryPOPStandardData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY intId ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mCategoryPOPStandardData contact = new mCategoryPOPStandardData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtCategoryCode(cursor.getString(1));
                contact.set_txtCategoryName(cursor.getString(2));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
