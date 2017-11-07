package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mPertanyaanData;
import library.spgmobile.common.tJawabanUserData;


/**
 * Created by Dewi Oktaviani on 03/05/2017.
 */

public class mPertanyaanDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mPertanyaan;
    private static final String TEBLE_JAWABANSPG = new clsHardCode().txtTable_tJawabanUser;
    public mPertanyaanDA(SQLiteDatabase db){
        mPertanyaanData dt = new mPertanyaanData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "( " + dt.Property_intQuestionId + " TEXT PRIMARY KEY," + dt.Property_intSoalId + " INTEGER NULL," + dt.Property_intCategoryId + " INTEGER NULL," + dt.Property_txtQuestionDesc + " TEXT NULL,"
                + dt.Property_intTypeQuestionId + " TEXT NULL," + dt.Property_decBobot + " TEXT NULL," + dt.Property_bolHaveAnswerList + " TEXT NULL," + dt.Property_inttGroupQuestionMapping + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    public void SaveDatamPertanyaan(SQLiteDatabase db, mPertanyaanData data){
        mPertanyaanData dt = new mPertanyaanData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_intQuestionId, String.valueOf(data.get_intQuestionId()));
        cv.put(dt.Property_intSoalId, String.valueOf(data.get_intSoalId()));
        cv.put(dt.Property_intCategoryId, String.valueOf(data.get_intCategoryId()));
        cv.put(dt.Property_txtQuestionDesc, String.valueOf(data.get_txtQuestionDesc()));
        cv.put(dt.Property_intTypeQuestionId, String.valueOf(data.get_intTypeQuestionId()));
        cv.put(dt.Property_decBobot, String.valueOf(data.get_decBobot()));
        cv.put(dt.Property_bolHaveAnswerList, String.valueOf(data.get_bolHaveAnswerList()));
        cv.put(dt.Property_inttGroupQuestionMapping, String.valueOf(data.get_inttGroupQuestionMapping()));
        if (data.get_intQuestionId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }
    public void DeleteAllDatamPertanyaan(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<mPertanyaanData> GetAllData(SQLiteDatabase db){
        List<mPertanyaanData> contactList = new ArrayList<mPertanyaanData>();
        mPertanyaanData dt = new mPertanyaanData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY inttGroupQuestionMapping ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mPertanyaanData contact = new mPertanyaanData();
                contact.set_intQuestionId(cursor.getString(0));
                contact.set_intSoalId(cursor.getString(1));
                contact.set_intCategoryId(cursor.getString(2));
                contact.set_txtQuestionDesc(cursor.getString(3));
                contact.set_intTypeQuestionId(cursor.getString(4));
                contact.set_decBobot(cursor.getString(5));
                contact.set_bolHaveAnswerList(cursor.getString(6));
                contact.set_inttGroupQuestionMapping(cursor.getString(7));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<mPertanyaanData> GetDataBYGroupQuestion(SQLiteDatabase db, int groupId){

        List<mPertanyaanData> contactList = new ArrayList<mPertanyaanData>();
        mPertanyaanData dt = new mPertanyaanData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_inttGroupQuestionMapping + "='" + groupId +"' ORDER BY intCategoryId, intSoalId ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mPertanyaanData contact = new mPertanyaanData();
                contact.set_intQuestionId(cursor.getString(0));
                contact.set_intSoalId(cursor.getString(1));
                contact.set_intCategoryId(cursor.getString(2));
                contact.set_txtQuestionDesc(cursor.getString(3));
                contact.set_intTypeQuestionId(cursor.getString(4));
                contact.set_decBobot(cursor.getString(5));
                contact.set_bolHaveAnswerList(cursor.getString(6));
                contact.set_inttGroupQuestionMapping(cursor.getString(7));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<mPertanyaanData> GetDataBYGroupQuestionCheck(SQLiteDatabase db, int groupId){

        List<mPertanyaanData> contactList = new ArrayList<mPertanyaanData>();
        mPertanyaanData dt = new mPertanyaanData();
        tJawabanUserData dtJawaban = new tJawabanUserData();
        String selectQuery = "Select " + dt.Property_AllS + " FROM " + TABLE_CONTACTS +  " LEFT OUTER JOIN " + TEBLE_JAWABANSPG + " ON " + TABLE_CONTACTS + "." + dt.Property_intQuestionId + "="
                + TEBLE_JAWABANSPG + "." + dtJawaban.Property_intQuestionId + " WHERE " + TEBLE_JAWABANSPG + "." + dtJawaban.Property_intQuestionId + " IS NULL AND " +dt.Property_inttGroupQuestionMapping + "='" + groupId +"' ORDER BY intCategoryId, intSoalId ASC ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mPertanyaanData contact = new mPertanyaanData();
                contact.set_intQuestionId(cursor.getString(0));
                contact.set_intSoalId(cursor.getString(1));
                contact.set_intCategoryId(cursor.getString(2));
                contact.set_txtQuestionDesc(cursor.getString(3));
                contact.set_intTypeQuestionId(cursor.getString(4));
                contact.set_decBobot(cursor.getString(5));
                contact.set_bolHaveAnswerList(cursor.getString(6));
                contact.set_inttGroupQuestionMapping(cursor.getString(7));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
    public List<mPertanyaanData> GetDataBYGroupQuestionCheckId(SQLiteDatabase db, int groupId){

        List<mPertanyaanData> contactList = new ArrayList<mPertanyaanData>();
        mPertanyaanData dt = new mPertanyaanData();
        tJawabanUserData dtJawaban = new tJawabanUserData();
        String selectQuery = "Select " + dt.Property_AllS + " FROM " + TABLE_CONTACTS +  " LEFT OUTER JOIN " + TEBLE_JAWABANSPG + " ON " + TABLE_CONTACTS + "." + dt.Property_intQuestionId + "="
                + TEBLE_JAWABANSPG + "." + dtJawaban.Property_intQuestionId + " WHERE " +dt.Property_inttGroupQuestionMapping + "='" + groupId +"' ORDER BY intCategoryId, intSoalId ASC ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mPertanyaanData contact = new mPertanyaanData();
                contact.set_intQuestionId(cursor.getString(0));
                contact.set_intSoalId(cursor.getString(1));
                contact.set_intCategoryId(cursor.getString(2));
                contact.set_txtQuestionDesc(cursor.getString(3));
                contact.set_intTypeQuestionId(cursor.getString(4));
                contact.set_decBobot(cursor.getString(5));
                contact.set_bolHaveAnswerList(cursor.getString(6));
                contact.set_inttGroupQuestionMapping(cursor.getString(7));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<mPertanyaanData> GetDataByQuestionId(SQLiteDatabase db, String intId){

        List<mPertanyaanData> contactList = new ArrayList<mPertanyaanData>();
        mPertanyaanData dt = new mPertanyaanData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " Where " + dt.Property_intQuestionId + "='" + intId + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mPertanyaanData contact = new mPertanyaanData();
                contact.set_intQuestionId(cursor.getString(0));
                contact.set_intSoalId(cursor.getString(1));
                contact.set_intCategoryId(cursor.getString(2));
                contact.set_txtQuestionDesc(cursor.getString(3));
                contact.set_intTypeQuestionId(cursor.getString(4));
                contact.set_decBobot(cursor.getString(5));
                contact.set_bolHaveAnswerList(cursor.getString(6));
                contact.set_inttGroupQuestionMapping(cursor.getString(7));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<mPertanyaanData> GetDataByCategoryInAndByGroupId(SQLiteDatabase db, String groupId, String categoryId){

        List<mPertanyaanData> contactList = new ArrayList<mPertanyaanData>();
        mPertanyaanData dt = new mPertanyaanData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " Where " + dt.Property_inttGroupQuestionMapping + "='" + groupId + "' AND" + dt.Property_intCategoryId + "='" + categoryId + "' Group BY intCategoryId Order BY intCategoryId ASC ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mPertanyaanData contact = new mPertanyaanData();
                contact.set_intQuestionId(cursor.getString(0));
                contact.set_intSoalId(cursor.getString(1));
                contact.set_intCategoryId(cursor.getString(2));
                contact.set_txtQuestionDesc(cursor.getString(3));
                contact.set_intTypeQuestionId(cursor.getString(4));
                contact.set_decBobot(cursor.getString(5));
                contact.set_bolHaveAnswerList(cursor.getString(6));
                contact.set_inttGroupQuestionMapping(cursor.getString(7));
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

