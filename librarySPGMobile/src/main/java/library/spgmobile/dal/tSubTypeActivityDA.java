package library.spgmobile.dal;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tSubTypeActivityData;

/**
 * Created by rhezaTesar on 7/18/2017.
 */

public class tSubTypeActivityDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tSubTypeActivity;

    public tSubTypeActivityDA(SQLiteDatabase db) {
        tSubTypeActivityData dt = new tSubTypeActivityData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_intSubTypeActivity + " TEXT PRIMARY KEY,"
                + dt.Property_bitActive + " TEXT NULL,"
                + dt.Property_txtName + " TEXT NULL,"
                + dt.Property_txtType + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void deleteContact(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
    }
    public static void SaveDatatSubTypeActivityData(SQLiteDatabase db, tSubTypeActivityData data) {
        tSubTypeActivityData dt=new tSubTypeActivityData();
        String strVal=null;
        db.execSQL("INSERT OR REPLACE into "+TABLE_CONTACTS+" ("+dt.Property_intSubTypeActivity+","
                +dt.Property_txtName+","
                +dt.Property_txtType+","
                +dt.Property_bitActive+") "+
                "values("+"'"
                +String.valueOf(data.get_intSubTypeActivity())+"','"
                +String.valueOf(data.get_txtName())+"','"
                +String.valueOf(data.get_txtType())+"','"
                +String.valueOf(data.get_bitActive())+"')");
    }
    // upgrading database
    public void Droptable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
    }

    //get data by txt type
    public List<tSubTypeActivityData> getAllDataByTxtType(SQLiteDatabase db, String txtType) {
        List<tSubTypeActivityData> contactList = null;
        // Select All Query
        tSubTypeActivityData dt=new tSubTypeActivityData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtType +"='"+txtType+"'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<tSubTypeActivityData>();
            do {
                tSubTypeActivityData contact = new tSubTypeActivityData();
                contact.set_intSubTypeActivity(String.valueOf(cursor.getString(0)));
                contact.set_bitActive(cursor.getString(1));
                contact.set_txtName(cursor.getString(2));
                contact.set_txtType(cursor.getString(3));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }


    public List<tSubTypeActivityData> getDataHeaderId(SQLiteDatabase db, String id) {
        List<tSubTypeActivityData> contactList = null;
        tSubTypeActivityData dt = new tSubTypeActivityData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_intSubTypeActivity,
                        dt.Property_txtName, dt.Property_txtType, dt.Property_bitActive}, dt.Property_intSubTypeActivity + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                contactList = new ArrayList<tSubTypeActivityData>();
                do {
                    tSubTypeActivityData contact = new tSubTypeActivityData();
                    contact.set_intSubTypeActivity(String.valueOf(cursor.getString(0)));
                    contact.set_txtName(cursor.getString(1));
                    contact.set_txtType(cursor.getString(2));
                    contact.set_bitActive(cursor.getString(3));
                    contactList.add(contact);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return contactList;
    }

    // Getting All Contacts
    public List<tSubTypeActivityData> getAll(SQLiteDatabase db) {
        List<tSubTypeActivityData> contactList = new ArrayList<tSubTypeActivityData>();
        // select All Query
        tSubTypeActivityData dt = new tSubTypeActivityData();
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                tSubTypeActivityData contact = new tSubTypeActivityData();
                contact.set_intSubTypeActivity(String.valueOf(cursor.getString(0)));
                contact.set_bitActive(cursor.getString(1));
                contact.set_txtName(cursor.getString(2));
                contact.set_txtType(cursor.getString(3));
                contactList.add(contact);
            } while (cursor.moveToNext());
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
