package library.salesforce.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.mParentData;

/**
 * Created by XSIS on 03/05/2017.
 */

public class mParentDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mParent;
    //create table
    public mParentDA(SQLiteDatabase db){
        mParentData dt = new mParentData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "( " + dt.Property_intParentId + " TEXT PRIMARY KEY," + dt.Property_txtParentName + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    //drop table
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    //insert value
    public void SaveDatamParent(SQLiteDatabase db, mParentData data){
        mParentData dt = new mParentData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
        + dt.Property_intParentId + ","
        + dt.Property_txtParentName + ") " + "values('"
        + String.valueOf(data.get_intParentId()) + "','"
        + String.valueOf(data.get_txtParentName()) + "')");
    }
    public void DeleteAllDatamParent(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<mParentData> GetAllData(SQLiteDatabase db){
        List<mParentData> contactList = new ArrayList<mParentData>();
        mParentData dt = new mParentData();
        String selectQuery = "Select " + dt.Property_All + " FROM" + TABLE_CONTACTS + " ORDER BY intParentId DESC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mParentData contact = new mParentData();
                contact.set_intParentId(cursor.getString(0));
                contact.set_txtParentName(cursor.getString(1));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
