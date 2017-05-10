package library.salesforce.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.tJawabanUserData;


/**
 * Created by XSIS on 04/05/2017.
 */

public class tJawabanUserDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tJawabanUser;
    public tJawabanUserDA(SQLiteDatabase db){
        tJawabanUserData dt = new tJawabanUserData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "( " + dt.Property_intUserAnswer + " TEXT PRIMARY KEY,"
                + dt.Property_intUserId + " TEXT NULL," + dt.Property_intRoleId + " TEXT NULL,"
                + dt.Property_intQuestionId + " TEXT NULL," + dt.Property_intTypeQuestionId + " TEXT NULL,"
                + dt.Property_bolHaveAnswerList + " TEXT NULL," + dt.Property_intAnswerId + " TEXT NULL,"
                + dt.Property_txtValue + " TEXT NULL," + dt.Property_decBobot + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    public void SaveDatatJawabanUser(SQLiteDatabase db, tJawabanUserData data){
        tJawabanUserData dt = new tJawabanUserData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_intUserAnswer + ","
                + dt.Property_intUserId + ","
                + dt.Property_intRoleId + ","
                + dt.Property_intQuestionId + ","
                + dt.Property_intTypeQuestionId + ","
                + dt.Property_bolHaveAnswerList + ","
                + dt.Property_intAnswerId + ","
                + dt.Property_txtValue + ","
                + dt.Property_decBobot + ") " + "values('"
                + String.valueOf(data.get_intUserAnswer()) + "','"
                + String.valueOf(data.get_intUserId()) + "','"
                + String.valueOf(data.get_intRoleId()) + "','"
                + String.valueOf(data.get_intQuestionId()) + "','"
                + String.valueOf(data.get_intTypeQuestionId()) + "','"
                + String.valueOf(data.get_bolHaveAnswerList()) + "','"
                + String.valueOf(data.get_intAnswerId()) + "','"
                + String.valueOf(data.get_txtValue()) + "','"
                + String.valueOf(data.get_decBobot()) + "')");
    }
    public void DeleteAllDatatJawabanUser(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<tJawabanUserData> GetAllData(SQLiteDatabase db){
        List<tJawabanUserData> contactList = new ArrayList<tJawabanUserData>();
        tJawabanUserData dt = new tJawabanUserData();
        String selectQuery = "Select " + dt.Property_All + " FROM" + TABLE_CONTACTS + " ORDER BY intQuestionId DESC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tJawabanUserData contact = new tJawabanUserData();
                contact.set_intUserAnswer(cursor.getString(0));
                contact.set_intUserId(cursor.getString(1));
                contact.set_intRoleId(cursor.getString(2));
                contact.set_intQuestionId(cursor.getString(3));
                contact.set_intTypeQuestionId(cursor.getString(4));
                contact.set_bolHaveAnswerList(cursor.getString(5));
                contact.set_intAnswerId(cursor.getString(6));
                contact.set_txtValue(cursor.getString(7));
                contact.set_decBobot(cursor.getString(8));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
