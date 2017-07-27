package library.spgmobile.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mTypePertanyaanData;

/**
 * Created by Dewi Oktaviani on 03/05/2017.
 */

public class mTypePertanyaanDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mTypePertanyaan;
    public mTypePertanyaanDA(SQLiteDatabase db){
        mTypePertanyaanData dt = new mTypePertanyaanData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                +TABLE_CONTACTS + "( " + dt.Property_intTypeQuestionId + " TEXT PRIMARY KEY," + dt.Property_txtTypeQuestion + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    public void SaveDatamTypePertanyaan(SQLiteDatabase db, mTypePertanyaanData data){
        mTypePertanyaanData dt = new mTypePertanyaanData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_intTypeQuestionId + ","
                + dt.Property_txtTypeQuestion + ") " + "values('"
                + String.valueOf(data.get_intTypeQuestionId()) + "','"
                + String.valueOf(data.get_txtTypeQuestion()) + "')");
    }
    public void DeleteAllDatamTypePertanyaan(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<mTypePertanyaanData> GetAllData(SQLiteDatabase db){
        List<mTypePertanyaanData> contactList = new ArrayList<mTypePertanyaanData>();
        mTypePertanyaanData dt = new mTypePertanyaanData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY intTypeQuestionId ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mTypePertanyaanData contact = new mTypePertanyaanData();
                contact.set_intTypeQuestionId(cursor.getString(0));
                contact.set_txtTypeQuestion(cursor.getString(1));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}

