package library.salesforce.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.mKategoriData;
import library.salesforce.common.mParentData;

/**
 * Created by Dewi Oktaviani on 03/05/2017.
 */

public class mKategoriDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mKategori;
    public mKategoriDA(SQLiteDatabase db){
        mKategoriData dt = new mKategoriData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                +TABLE_CONTACTS + "( " +dt.Property_intCategoryId + " TEXT PRIMARY KEY," + dt.Property_intParentId + " TEXT NULL, " + dt.Property_txtCategoryName + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    public void SaveDatamKategori(SQLiteDatabase db, mKategoriData data){
        mKategoriData dt = new mKategoriData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_intCategoryId + ","
                + dt.Property_intParentId + ","
                + dt.Property_txtCategoryName + ") "+ "values('"
                + String.valueOf(data.get_intCategoryId()) + "','"
                + String.valueOf(data.get_intParentId()) + "','"
                + String.valueOf(data.get_txtCategoryName()) + "')");
    }
    public void DeleteAllDatamKategori(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<mKategoriData> GetAllData(SQLiteDatabase db){
        List<mKategoriData> contactList = new ArrayList<mKategoriData>();
        mKategoriData dt = new mKategoriData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY intCategoryId ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                mKategoriData contact = new mKategoriData();
                contact.set_intCategoryId(cursor.getString(0));
                contact.set_intParentId(cursor.getString(1));
                contact.set_txtCategoryName(cursor.getString(2));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
