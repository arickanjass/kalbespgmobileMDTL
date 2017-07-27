package library.spgmobile.dal;

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
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_intListAnswerId + ","
                + dt.Property_intQuestionId + ","
                + dt.Property_intTypeQuestionId + ","
                + dt.Property_txtKey + ","
                + dt.Property_txtValue + ") " + "values('"
                + String.valueOf(data.get_intListAnswerId()) + "','"
                + String.valueOf(data.get_intQuestionId()) + "','"
                + String.valueOf(data.get_intTypeQuestionId()) + "','"
                + String.valueOf(data.get_txtKey()) + "','"
                + String.valueOf(data.get_txtValue()) + "')");
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
}
