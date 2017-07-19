package library.salesforce.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.mCategoryVisitPlanData;

/**
 * Created by Robert on 03/05/2017.
 */

public class mCategoryVisitPlanDA {

    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mCategoryVisitPlan;

    public mCategoryVisitPlanDA(SQLiteDatabase db){
        mCategoryVisitPlanData dt = new mCategoryVisitPlanData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "("
                + dt.Property_intCategoryVisitPlan+ " TEXT PRIMARY KEY,"
                + dt.Property_txtCatVisitPlan + " TEXT NULL,"
                + dt.Property_bitActive +" TEXT  NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

    }

    // Adding new contact
    public void SaveDataCategoryVisitPlan(SQLiteDatabase db, mCategoryVisitPlanData data) {
        mCategoryVisitPlanData dt = new mCategoryVisitPlanData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_intCategoryVisitPlan
                + "," + dt.Property_txtCatVisitPlan
                + ","+ dt.Property_bitActive
                + ") " + "values('"
                + String.valueOf(data.getIntCategoryVisitPlan()) + "','"
                + String.valueOf(data.getTxtCatVisitPlan()) + "','"
                + String.valueOf(data.getBitActive()) +  "')");
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    public void DeleteAllDataCategoryVisitPlan(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    // Getting single contact
    public mCategoryVisitPlanData getData(SQLiteDatabase db, int id) {
        mCategoryVisitPlanData dt = new mCategoryVisitPlanData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        dt.Property_intCategoryVisitPlan , dt.Property_txtCatVisitPlan
                        , dt.Property_bitActive},
                dt.Property_intCategoryVisitPlan + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        mCategoryVisitPlanData contact = new mCategoryVisitPlanData();
        if (cursor.getCount() > 0) {
            contact.setIntCategoryVisitPlan(cursor.getString(0));
            contact.setTxtCatVisitPlan(cursor.getString(1));
            contact.setBitActive(cursor.getString(2));
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }
    public mCategoryVisitPlanData getDataByintCategoryVisitPlan(SQLiteDatabase db, String id) {
        mCategoryVisitPlanData dt = new mCategoryVisitPlanData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        dt.Property_intCategoryVisitPlan , dt.Property_txtCatVisitPlan
                        , dt.Property_bitActive},
                dt.Property_intCategoryVisitPlan + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        mCategoryVisitPlanData contact = new mCategoryVisitPlanData();
        if (cursor.getCount() > 0) {
            contact.setIntCategoryVisitPlan(cursor.getString(0));
            contact.setTxtCatVisitPlan(cursor.getString(1));
            contact.setBitActive(cursor.getString(2));
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }

    // Getting All Contacts
    public List<mCategoryVisitPlanData> getAllData(SQLiteDatabase db) {
        List<mCategoryVisitPlanData> contactList = new ArrayList<mCategoryVisitPlanData>();
        // Select All Query
        mCategoryVisitPlanData dt = new mCategoryVisitPlanData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mCategoryVisitPlanData contact = new mCategoryVisitPlanData();
                contact.setIntCategoryVisitPlan(cursor.getString(0));
                contact.setTxtCatVisitPlan(cursor.getString(1));
                contact.setBitActive(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    // Deleting single contact
    public void deleteContact(SQLiteDatabase db, int id) {
        mCategoryVisitPlanData dt = new mCategoryVisitPlanData();
        db.delete(TABLE_CONTACTS, dt.Property_intCategoryVisitPlan + " = ?",
                new String[] { String.valueOf(id) });
        // db.close();
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
