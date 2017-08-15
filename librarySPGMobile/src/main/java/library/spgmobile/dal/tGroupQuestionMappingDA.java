package library.spgmobile.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mPertanyaanData;
import library.spgmobile.common.tGroupQuestionMappingData;
import library.spgmobile.common.tJawabanUserData;

/**
 * Created by Dewi Oktaviani on 04/07/2017.
 */

public class tGroupQuestionMappingDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tGroupQuestionMapping;
    private static final String TABLE_PERTANYAAN = new clsHardCode().txtTable_mPertanyaan;
    private static final String TEBLE_JAWABANSPG = new clsHardCode().txtTable_tJawabanUser;
    //create table
    public tGroupQuestionMappingDA(SQLiteDatabase db){
        tGroupQuestionMappingData dt = new tGroupQuestionMappingData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "( " + dt.Property_intId + " TEXT PRIMARY KEY," + dt.Property_txtGroupQuestion + " TEXT NULL," +
                dt.Property_intRoleId + " TEXT NULL," + dt.Property_txtRepeatQuestion + " TEXT NULL," + dt.Property_dtStart + " TEXT NULL," + dt.Property_dtEnd + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    //drop table
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    //insert value
    public void SaveDatatGroupQuestionMapping(SQLiteDatabase db, tGroupQuestionMappingData data){
        tGroupQuestionMappingData dt = new tGroupQuestionMappingData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
            + dt.Property_intId + ","
            + dt.Property_txtGroupQuestion + ","
            + dt.Property_intRoleId + ","
            + dt.Property_txtRepeatQuestion  + ","
            + dt.Property_dtStart + ","
            + dt.Property_dtEnd + ") " + "values('"
            + String.valueOf(data.get_intId()) + "','"
            + String.valueOf(data.get_txtGroupQuestion()) + "','"
            + String.valueOf(data.get_intRoleId()) + "','"
            + String.valueOf(data.get_txtRepeatQuestion()) + "','"
            + String.valueOf(data.get_dtStart()) + "','"
            + String.valueOf(data.get_dtEnd()) + "')");
    }
    public void DeleteAllDatatGroupQuestionMapping(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<tGroupQuestionMappingData> GetAllData(SQLiteDatabase db){
        List<tGroupQuestionMappingData> contactList = new ArrayList<tGroupQuestionMappingData>();
        tGroupQuestionMappingData dt = new tGroupQuestionMappingData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY intId ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tGroupQuestionMappingData contact = new tGroupQuestionMappingData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtGroupQuestion(cursor.getString(1));
                contact.set_intRoleId(cursor.getString(2));
                contact.set_txtRepeatQuestion(cursor.getString(3));
                contact.set_dtStart(cursor.getString(4));
                contact.set_dtEnd(cursor.getString(5));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tGroupQuestionMappingData> GetDataById(SQLiteDatabase db, int intId){
        List<tGroupQuestionMappingData> contactList = new ArrayList<tGroupQuestionMappingData>();
        tGroupQuestionMappingData dt = new tGroupQuestionMappingData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intId + "='" + intId + "' ORDER BY intId ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tGroupQuestionMappingData contact = new tGroupQuestionMappingData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtGroupQuestion(cursor.getString(1));
                contact.set_intRoleId(cursor.getString(2));
                contact.set_txtRepeatQuestion(cursor.getString(3));
                contact.set_dtStart(cursor.getString(4));
                contact.set_dtEnd(cursor.getString(5));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
    public List<tGroupQuestionMappingData> GetDataByIdActive(SQLiteDatabase db){
        List<tGroupQuestionMappingData> contactList = new ArrayList<tGroupQuestionMappingData>();
        tGroupQuestionMappingData dt = new tGroupQuestionMappingData();
        mPertanyaanData dtQuestion = new mPertanyaanData();
        tJawabanUserData dtJawaban = new tJawabanUserData();
        String selectQuery = "Select " + dt.Property_AllS + " FROM " + TABLE_CONTACTS + " lEFT OUTER JOIN " + TABLE_PERTANYAAN +
                " ON " + TABLE_CONTACTS + "." + dt.Property_intId + "=" + TABLE_PERTANYAAN + "." + dtQuestion.Property_inttGroupQuestionMapping +
                " LEFT OUTER JOIN " + TEBLE_JAWABANSPG + " ON " + TABLE_PERTANYAAN + "." + dtQuestion.Property_intQuestionId + "="
                + TEBLE_JAWABANSPG + "." + dtJawaban.Property_intQuestionId + " WHERE " + TEBLE_JAWABANSPG + "." + dtJawaban.Property_intQuestionId +
                " IS NULL " + " GROUP BY " + dt.Property_AllS  + " ORDER BY intId ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tGroupQuestionMappingData contact = new tGroupQuestionMappingData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtGroupQuestion(cursor.getString(1));
                contact.set_intRoleId(cursor.getString(2));
                contact.set_txtRepeatQuestion(cursor.getString(3));
                contact.set_dtStart(cursor.getString(4));
                contact.set_dtEnd(cursor.getString(5));  
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
