package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tJawabanUserData;


/**
 * Created by Dewi Oktaviani on 04/05/2017.
 */

public class tJawabanUserDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tJawabanUser;
    public tJawabanUserDA(SQLiteDatabase db){
        tJawabanUserData dt = new tJawabanUserData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "( " + dt.Property_intUserAnswer + " TEXT PRIMARY KEY,"
                + dt.Property_intUserId + " TEXT NULL,"  + dt.Property_intNik + " TEXT NULL,"
                + dt.Property_intRoleId + " TEXT NULL," + dt.Property_intQuestionId + " TEXT NULL,"
                + dt.Property_intTypeQuestionId + " TEXT NULL,"+ dt.Property_bolHaveAnswerList + " TEXT NULL,"
                + dt.Property_intAnswerId + " TEXT NULL," + dt.Property_txtValue + " TEXT NULL,"
                + dt.Property_ptQuiz + " TEXT NULL,"  + dt.Property_txtFileQuiz + " TEXT NULL," + dt.Property_decBobot + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL," + dt.Property_intSync + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    public void SaveDatatJawabanUser(SQLiteDatabase db, tJawabanUserData data){
        tJawabanUserData dt = new tJawabanUserData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_intUserAnswer, String.valueOf(data.get_intUserAnswer()));
        cv.put(dt.Property_intUserId, String.valueOf(data.get_intUserId()));
        cv.put(dt.Property_intNik, String.valueOf(data.get_intNik()));
        cv.put(dt.Property_intRoleId, String.valueOf(data.get_intRoleId()));
        cv.put(dt.Property_intQuestionId, String.valueOf(data.get_intQuestionId()));
        cv.put(dt.Property_intTypeQuestionId, String.valueOf(data.get_intTypeQuestionId()));
        cv.put(dt.Property_bolHaveAnswerList, String.valueOf(data.get_bolHaveAnswerList()));
        cv.put(dt.Property_intAnswerId, String.valueOf(data.get_intAnswerId()));
        cv.put(dt.Property_txtValue, String.valueOf(data.get_txtValue()));
        cv.put(dt.Property_ptQuiz, data.get_ptQuiz());
        cv.put(dt.Property_txtFileQuiz, data.get_txtFileQuiz());
        cv.put(dt.Property_decBobot, String.valueOf(data.get_decBobot()));
        cv.put(dt.Property_intSubmit, String.valueOf(data.get_intSubmit()));
        if (data.get_intAnswerId() == null){
            cv.put(dt.Property_intSync, String.valueOf(data.get_intSync()));
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_intSync, String.valueOf(data.get_intSync()));
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }
    public void DeleteAllDatatJawabanUser(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<tJawabanUserData> GetAllData(SQLiteDatabase db){
        List<tJawabanUserData> contactList = new ArrayList<tJawabanUserData>();
        tJawabanUserData dt = new tJawabanUserData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY intUserId ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tJawabanUserData contact = new tJawabanUserData();
                contact.set_intUserAnswer(cursor.getString(0));
                contact.set_intUserId(cursor.getString(1));
                contact.set_intNik(cursor.getString(2));
                contact.set_intRoleId(cursor.getString(3));
                contact.set_intQuestionId(cursor.getString(4));
                contact.set_intTypeQuestionId(cursor.getString(5));
                contact.set_bolHaveAnswerList(cursor.getString(6));
                contact.set_intAnswerId(cursor.getString(7));
                contact.set_txtValue(cursor.getString(8));
                byte[] blob = cursor.getBlob(9);
                contact.set_ptQuiz(blob);
                contact.set_decBobot(cursor.getString(10));
                contact.set_intSubmit(cursor.getString(11));
                contact.set_intSync(cursor.getString(12));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
    public List<tJawabanUserData> GetDataToPushAnswer(SQLiteDatabase db){
        List<tJawabanUserData> contactList = new ArrayList<tJawabanUserData>();
        tJawabanUserData dt = new tJawabanUserData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + "=1 AND " + dt.Property_intSync + " =0";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tJawabanUserData contact = new tJawabanUserData();
                contact.set_intUserAnswer(cursor.getString(0));
                contact.set_intUserId(cursor.getString(1));
                contact.set_intNik(cursor.getString(2));
                contact.set_intRoleId(cursor.getString(3));
                contact.set_intQuestionId(cursor.getString(4));
                contact.set_intTypeQuestionId(cursor.getString(5));
                contact.set_bolHaveAnswerList(cursor.getString(6));
                contact.set_intAnswerId(cursor.getString(7));
                contact.set_txtValue(cursor.getString(8));
                byte[] blob = cursor.getBlob(9);
                contact.set_ptQuiz(blob);
                byte[] blobFile = cursor.getBlob(10);
                contact.set_txtFileQuiz(blobFile);
                contact.set_decBobot(cursor.getString(11));
                contact.set_intSubmit(cursor.getString(12));
                contact.set_intSync(cursor.getString(13));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}