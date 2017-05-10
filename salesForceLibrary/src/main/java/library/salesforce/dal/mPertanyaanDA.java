package library.salesforce.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.mPertanyaanData;


/**
 * Created by XSIS on 03/05/2017.
 */

public class mPertanyaanDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mPertanyaan;
    public mPertanyaanDA(SQLiteDatabase db){
        mPertanyaanData dt = new mPertanyaanData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "( " + dt.Property_intQuestionId + " TEXT PRIMARY KEY," + dt.Property_intCategoryId + " TEXT NULL," + dt.Property_txtQuestionDesc + " TEXT NULL,"
                + dt.Property_intTypeQuestionId + " TEXT NULL," + dt.Property_decBobot + " TEXT NULL," + dt.Property_bolHaveAnswerList + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    public void SaveDatamPertanyaan(SQLiteDatabase db, mPertanyaanData data){
        mPertanyaanData dt = new mPertanyaanData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_intQuestionId + ","
                + dt.Property_intCategoryId + ","
                + dt.Property_txtQuestionDesc + ","
                + dt.Property_intTypeQuestionId + ","
                + dt.Property_decBobot + ","
                + dt.Property_bolHaveAnswerList + ") " + "values('"
                + String.valueOf(data.get_intQuestionId()) + "','"
                + String.valueOf(data.get_intCategoryId()) + "','"
                + String.valueOf(data.get_txtQuestionDesc()) + "','"
                + String.valueOf(data.get_intTypeQuestionId()) + "','"
                + String.valueOf(data.get_decBobot()) + "','"
                + String.valueOf(data.get_bolHaveAnswerList()) + "')");
    }
    public void DeleteAllDatamPertanyaan(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<mPertanyaanData> GetAllData(SQLiteDatabase db){
        List<mPertanyaanData> contactList = new ArrayList<mPertanyaanData>();
        mPertanyaanData dt = new mPertanyaanData();
        String selectQuery = "Select " + dt.Property_All + " FROM" + TABLE_CONTACTS + " ORDER BY intQuestionId DESC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mPertanyaanData contact = new mPertanyaanData();
                contact.set_intQuestionId(cursor.getString(0));
                contact.set_intCategoryId(cursor.getString(1));
                contact.set_txtQuestionDesc(cursor.getString(2));
                contact.set_intTypeQuestionId(cursor.getString(3));
                contact.set_decBobot(cursor.getString(4));
                contact.set_bolHaveAnswerList(cursor.getString(5));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
