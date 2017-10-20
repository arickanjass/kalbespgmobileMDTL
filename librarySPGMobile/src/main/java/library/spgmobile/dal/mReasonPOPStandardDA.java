package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mReasonPOPStandardData;

/**
 * Created by Dewi Oktaviani on 17/10/2017.
 */

public class mReasonPOPStandardDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mReasonPOPStandard;
    //create table
    public mReasonPOPStandardDA(SQLiteDatabase db){
        mReasonPOPStandardData dt = new mReasonPOPStandardData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "( " + dt.Property_intId + " TEXT PRIMARY KEY," + dt.Property_txtReason + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    //drop table
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    //insert value
    public void SaveDatamReasonPOPStandard(SQLiteDatabase db, mReasonPOPStandardData data){
        mReasonPOPStandardData dt = new mReasonPOPStandardData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_intId, String.valueOf(data.get_intId()));
        cv.put(dt.Property_txtReason, String.valueOf(data.get_txtReason()));
        if (data.get_intId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        }else {
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }
    public void DeleteAllDatamReasonPOPStandard(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<mReasonPOPStandardData> GetAllData(SQLiteDatabase db){
        List<mReasonPOPStandardData> contactList = new ArrayList<mReasonPOPStandardData>();
        mReasonPOPStandardData dt = new mReasonPOPStandardData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY intId ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mReasonPOPStandardData contact = new mReasonPOPStandardData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtReason(cursor.getString(1));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
