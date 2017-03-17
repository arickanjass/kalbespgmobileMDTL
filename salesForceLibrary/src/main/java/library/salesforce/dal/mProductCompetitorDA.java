package library.salesforce.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.mProductCompetitorData;

/**
 * Created by Rian Andrivani on 3/8/2017.
 */

public class mProductCompetitorDA {
    public mProductCompetitorDA(SQLiteDatabase db) {
        mProductCompetitorData dt = new mProductCompetitorData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "(" + dt.Property_txtID
                + " TEXT PRIMARY KEY," + dt.Property_txtProductDetailCode
                + " TEXT NULL," + dt.Property_txtLobName
                + " TEXT NULL," + dt.Property_GroupProduct
                + " TEXT NULL," + dt.Property_txtProdukid
                + " TEXT NULL," + dt.Property_txtProdukKompetitorID
                + " TEXT NULL," + dt.Property_txtCRMCode
                + " TEXT NULL," + dt.Property_txtNIK + " TEXT  NULL,"
                + dt.Property_txtName + " TEXT  NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mProductCompetitorData;

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void SaveDataMConfig(SQLiteDatabase db, mProductCompetitorData data) {
        mProductCompetitorData dt = new mProductCompetitorData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_txtID
                + "," + dt.Property_txtProductDetailCode
                + ","+ dt.Property_txtLobName
                + "," + dt.Property_GroupProduct
                + "," + dt.Property_txtProdukid
                + "," + dt.Property_txtProdukKompetitorID
                + "," + dt.Property_txtCRMCode
                + "," + dt.Property_txtNIK
                + "," + dt.Property_txtName
                + ") " + "values('"
                + String.valueOf(data.get_txtID()) + "','"
                + String.valueOf(data.get_txtProductDetailCode()) + "','"
                + String.valueOf(data.get_txtLobName()) + "','"
                + String.valueOf(data.get_GroupProduct()) + "','"
                + String.valueOf(data.get_txtProdukid()) + "','"
                + String.valueOf(data.get_txtProdukKompetitorID()) + "','"
                + String.valueOf(data.get_txtCRMCode()) + "','"
                + String.valueOf(data.get_txtNIK()) + "','"
                + String.valueOf(data.get_txtName()) + "'"+ ")");
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    public void DeleteAllDataMConfig(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    // Getting single contact
    public mProductCompetitorData getData(SQLiteDatabase db, String id) {
        mProductCompetitorData dt = new mProductCompetitorData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{
                dt.Property_txtID, dt.Property_txtProductDetailCode
                , dt.Property_txtLobName
                , dt.Property_GroupProduct
                , dt.Property_txtProdukid
                , dt.Property_txtProdukKompetitorID
                , dt.Property_txtCRMCode
                , dt.Property_txtNIK
                , dt.Property_txtName
                },
                dt.Property_txtID + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        mProductCompetitorData contact = new mProductCompetitorData();
        if (cursor.getCount() > 0) {
            contact.set_txtID(cursor.getString(0));
            contact.set_txtProductDetailCode(cursor.getString(1));
            contact.set_txtLobName(cursor.getString(2));
            contact.set_GroupProduct(cursor.getString(3));
            contact.set_txtProdukid(cursor.getString(4));
            contact.set_txtProdukKompetitorID(cursor.getString(5));
            contact.set_txtCRMCode(cursor.getString(6));
            contact.set_txtNIK(cursor.getString(7));
            contact.set_txtName(cursor.getString(8));
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }

    // Getting All Contacts
    public List<mProductCompetitorData> getAllData(SQLiteDatabase db) {
        List<mProductCompetitorData> contactList = new ArrayList<mProductCompetitorData>();
        // Select All Query
        mProductCompetitorData dt = new mProductCompetitorData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS +" ORDER BY "+dt.Property_txtNIK+" ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mProductCompetitorData contact = new mProductCompetitorData();
                contact.set_txtID(cursor.getString(0));
                contact.set_txtProductDetailCode(cursor.getString(1));
                contact.set_txtLobName(cursor.getString(2));
                contact.set_GroupProduct(cursor.getString(3));
                contact.set_txtProdukid(cursor.getString(4));
                contact.set_txtProdukKompetitorID(cursor.getString(5));
                contact.set_txtCRMCode(cursor.getString(6));
                contact.set_txtNIK(cursor.getString(7));
                contact.set_txtName(cursor.getString(8));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    // Deleting single contact
    public void deleteContact(SQLiteDatabase db, String id) {
        mProductCompetitorData dt = new mProductCompetitorData();
        db.delete(TABLE_CONTACTS, dt.Property_txtID + " = ?",
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
