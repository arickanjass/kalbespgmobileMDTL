package library.spgmobile.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tKategoryPlanogramMobileData;
import library.spgmobile.common.tSubTypeActivityData;

/**
 * Created by aan.junianto on 28/08/2017.
 */

public class tKategoryPlanogramMobileDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tKategoryPlanogram;

    public tKategoryPlanogramMobileDA(SQLiteDatabase db) {
        tKategoryPlanogramMobileData dt = new tKategoryPlanogramMobileData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_intKategoryPlanogram + " TEXT PRIMARY KEY,"
                + dt.Property_bitActive + " TEXT NULL,"
                + dt.Property_txtName + " TEXT NULL,"
                + dt.Property_txtType + " TEXT NULL,"
                + dt.Property_intIsCheckValid + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void deleteContact(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
    }
    public static void SaveDatatKategoryPlanogramMobileData(SQLiteDatabase db, tKategoryPlanogramMobileData data) {
        tKategoryPlanogramMobileData dt=new tKategoryPlanogramMobileData();
        String strVal=null;
        db.execSQL("INSERT OR REPLACE into "+TABLE_CONTACTS+" ("+dt.Property_intKategoryPlanogram+","
                +dt.Property_txtName+","
                +dt.Property_txtType+","
                +dt.Property_bitActive+","
                +dt.Property_intIsCheckValid+") "+
                "values("+"'"
                +String.valueOf(data.get_intKategoryPlanogram())+"','"
                +String.valueOf(data.get_txtName())+"','"
                +String.valueOf(data.get_txtType())+"','"
                +String.valueOf(data.get_bitActive())+"','"
                +String.valueOf(data.get_intIsCheckValid())+"')");
    }
    // upgrading database
    public void Droptable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
    }

    //get data by txt type
    public List<tKategoryPlanogramMobileData> getAllDataByTxtType(SQLiteDatabase db, String txtType) {
        List<tKategoryPlanogramMobileData> contactList = null;
        // Select All Query
        tKategoryPlanogramMobileData dt=new tKategoryPlanogramMobileData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtType +"='"+txtType+"'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<tKategoryPlanogramMobileData>();
            do {
                tKategoryPlanogramMobileData contact = new tKategoryPlanogramMobileData();
                contact.set_intKategoryPlanogram(String.valueOf(cursor.getString(0)));
                contact.set_bitActive(cursor.getString(1));
                contact.set_txtName(cursor.getString(2));
                contact.set_txtType(cursor.getString(3));
                contact.set_intIsCheckValid(cursor.getString(4));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }


    public tKategoryPlanogramMobileData getDataHeaderId(SQLiteDatabase db, String id) {
        tKategoryPlanogramMobileData contactList = null;
        tKategoryPlanogramMobileData dt = new tKategoryPlanogramMobileData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_intKategoryPlanogram,
                        dt.Property_txtName, dt.Property_txtType, dt.Property_bitActive, dt.Property_intIsCheckValid}, dt.Property_intKategoryPlanogram + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                contactList = new tKategoryPlanogramMobileData();
                do {
                    tKategoryPlanogramMobileData contact = new tKategoryPlanogramMobileData();
                    contact.set_intKategoryPlanogram(String.valueOf(cursor.getString(0)));
                    contact.set_txtName(cursor.getString(1));
                    contact.set_txtType(cursor.getString(2));
                    contact.set_bitActive(cursor.getString(3));
                    contact.set_intIsCheckValid(cursor.getString(4));
                    contactList = contact;
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return contactList;
    }

    // Getting All Contacts
    public List<tKategoryPlanogramMobileData> getAll(SQLiteDatabase db) {
        List<tKategoryPlanogramMobileData> contactList = new ArrayList<tKategoryPlanogramMobileData>();
        // select All Query
        tKategoryPlanogramMobileData dt = new tKategoryPlanogramMobileData();
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                tKategoryPlanogramMobileData contact = new tKategoryPlanogramMobileData();
                contact.set_intKategoryPlanogram(String.valueOf(cursor.getString(0)));
                contact.set_bitActive(cursor.getString(1));
                contact.set_txtName(cursor.getString(2));
                contact.set_txtType(cursor.getString(3));
                contact.set_intIsCheckValid(cursor.getString(4));
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
