package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mListJawabanData;

/**
 * Created by Dewi Oktaviani on 03/05/2017.
 */

public class mListJawabanDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mListJawaban;
    public mListJawabanDA(SQLiteDatabase db){
        mListJawabanData dt = new mListJawabanData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "( " + dt.Property_intListAnswerId + " TEXT PRIMARY KEY," + dt.Property_intQuestionId + " TEXT NULL,"
                + dt.Property_intTypeQuestionId + " TEXT NULL,"
                + dt.Property_txtKey + " TEXT NULL," + dt.Property_txtValue + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    public void SaveDatamListJawaban(SQLiteDatabase db, mListJawabanData data){
        mListJawabanData dt = new mListJawabanData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_intListAnswerId, String.valueOf(data.get_intListAnswerId()));
        cv.put(dt.Property_intQuestionId, String.valueOf(data.get_intQuestionId()));
        cv.put(dt.Property_intTypeQuestionId, String.valueOf(data.get_intTypeQuestionId()));
        cv.put(dt.Property_txtKey, String.valueOf(data.get_txtKey()));
        cv.put(dt.Property_txtValue, String.valueOf(data.get_txtValue()));
        if (data.get_intListAnswerId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }
    public void DeleteAllDatamListJawaban(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<mListJawabanData> GetAllData(SQLiteDatabase db){
        List<mListJawabanData> contactList = new ArrayList<mListJawabanData>();
        mListJawabanData dt = new mListJawabanData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY txtValue ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mListJawabanData contact = new mListJawabanData();
                contact.set_intListAnswerId(cursor.getString(0));
                contact.set_intQuestionId(cursor.getString(1));
                contact.set_intTypeQuestionId(cursor.getString(2));
                contact.set_txtKey(cursor.getString(3));
                contact.set_txtValue(cursor.getString(4));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
    public List<mListJawabanData> GetDataByTypeQuestion(SQLiteDatabase db, String typeID, String qID){
        List<mListJawabanData> contactList = new ArrayList<mListJawabanData>();
        mListJawabanData dt = new mListJawabanData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intTypeQuestionId + "='" + typeID + "' AND " + dt.Property_intQuestionId +
                "='" + qID + "' ORDER BY txtValue ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mListJawabanData contact = new mListJawabanData();
                contact.set_intListAnswerId(cursor.getString(0));
                contact.set_intQuestionId(cursor.getString(1));
                contact.set_intTypeQuestionId(cursor.getString(2));
                contact.set_txtKey(cursor.getString(3));
                contact.set_txtValue(cursor.getString(4));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public mListJawabanData GetDataById(SQLiteDatabase db, String intId){
        mListJawabanData dt = new mListJawabanData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intListAnswerId + "='" + intId + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        mListJawabanData contact = null;
        if (cursor.moveToFirst()){
            do {
                contact = new mListJawabanData();
                contact.set_intListAnswerId(cursor.getString(0));
                contact.set_intQuestionId(cursor.getString(1));
                contact.set_intTypeQuestionId(cursor.getString(2));
                contact.set_txtKey(cursor.getString(3));
                contact.set_txtValue(cursor.getString(4));
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
