package library.spgmobile.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.clsLogReceiverHeader_mobile;

/**
 * Created by aan.junianto on 6/07/2017.
 */

public class clsLogReceiverHeader_mobileDA {
    public clsLogReceiverHeader_mobileDA(SQLiteDatabase db) {
        clsLogReceiverHeader_mobile dt = new clsLogReceiverHeader_mobile();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "(" + dt.Property_txtIdLogReceiver + " TEXT PRIMARY KEY,"
                + dt.Property_txtStatus + " TEXT NULL,"
                + dt.Property_txtNIK + " TEXT NULL,"
                + dt.Property_txtUSerName + " TEXT NULL,"
                + dt.Property_dtInserted + " TEXT NULL,"
                + dt.Property_dtUpdated + " TEXT NULL,"
                + dt.Property_intActive + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL,"
                + dt.Property_intSync + " TEXT NULL,"
                + dt.Property_txtIdReceiver + " TEXT NULL,"
                + dt.Property_txtGuidLogin + " TEXT NULL,"
                + dt.Property_txtIdHeaderNotif + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_clsLogReceiverHeader_mobile;

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

    }
    // Adding new contact
    public void SaveDataMConfig(SQLiteDatabase db, clsLogReceiverHeader_mobile data) {
        clsLogReceiverHeader_mobile dt = new clsLogReceiverHeader_mobile();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.PropertyAll
                + ") " + "values('"
                + String.valueOf(data.getTxtIdLogReceiver()) + "','"
                + String.valueOf(data.getTxtStatus()) + "','"
                + String.valueOf(data.getTxtNIK()) + "','"
                + String.valueOf(data.getTxtUSerName()) + "','"
                + String.valueOf(data.getDtInserted()) + "','"
                + String.valueOf(data.getDtUpdated()) + "','"
                + String.valueOf(data.getIntActive()) + "','"
                + String.valueOf(data.getIntSubmit()) + "','"
                + String.valueOf(data.getIntSync()) + "','"
                + String.valueOf(data.getTxtIdReceiver()) + "','"
                + String.valueOf(data.getTxtGuidLogin()) + "','"
                + String.valueOf(data.getTxtIdHeaderNotif()) + "')");
//			db.close();
    }
    public void DeleteAllData(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
    }
    // Getting single contact
    public clsLogReceiverHeader_mobile getData(SQLiteDatabase db, String id) {
        clsLogReceiverHeader_mobile dt = new clsLogReceiverHeader_mobile();
        String selectQuery = "SELECT  " + dt.PropertyAll + " FROM "
                + TABLE_CONTACTS+" WHERE "+dt.Property_txtIdLogReceiver+"='"+id+"'";;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        clsLogReceiverHeader_mobile contact = new clsLogReceiverHeader_mobile();
        if (cursor.getCount() > 0) {
            contact.setTxtIdLogReceiver(cursor.getString(0));
            contact.setTxtStatus(cursor.getString(1));
            contact.setTxtNIK(cursor.getString(2));
            contact.setTxtUSerName(cursor.getString(3));
            contact.setDtInserted(cursor.getString(4));
            contact.setDtUpdated(cursor.getString(5));
            contact.setIntActive(cursor.getString(6));
            contact.setIntSubmit(cursor.getString(7));
            contact.setIntSync(cursor.getString(8));
            contact.setTxtIdReceiver(cursor.getString(9));
            contact.setTxtGuidLogin(cursor.getString(10));
            contact.setTxtIdHeaderNotif(cursor.getString(11));
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }

    public List<clsLogReceiverHeader_mobile> getDataByIdHeader(SQLiteDatabase db, String id) {
        List<clsLogReceiverHeader_mobile> contactList = new ArrayList<clsLogReceiverHeader_mobile>();
        clsLogReceiverHeader_mobile dt = new clsLogReceiverHeader_mobile();
        String selectQuery = "SELECT  " + dt.PropertyAll + " FROM "
                + TABLE_CONTACTS+" WHERE "+dt.Property_txtIdHeaderNotif+"='"+id+"'";;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<clsLogReceiverHeader_mobile>();
            do {
                clsLogReceiverHeader_mobile contact = new clsLogReceiverHeader_mobile();
                contact.setTxtIdLogReceiver(cursor.getString(0));
                contact.setTxtStatus(cursor.getString(1));
                contact.setTxtNIK(cursor.getString(2));
                contact.setTxtUSerName(cursor.getString(3));
                contact.setDtInserted(cursor.getString(4));
                contact.setDtUpdated(cursor.getString(5));
                contact.setIntActive(cursor.getString(6));
                contact.setIntSubmit(cursor.getString(7));
                contact.setIntSync(cursor.getString(8));
                contact.setTxtIdReceiver(cursor.getString(9));
                contact.setTxtGuidLogin(cursor.getString(10));
                contact.setTxtIdHeaderNotif(cursor.getString(11));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<clsLogReceiverHeader_mobile> getAllDataWillAlert(SQLiteDatabase db, String Status) {
        List<clsLogReceiverHeader_mobile> contactList = null;
        // Select All Query
        clsLogReceiverHeader_mobile dt = new clsLogReceiverHeader_mobile();
        String selectQuery = "SELECT  " + dt.PropertyAll + " FROM "
                + TABLE_CONTACTS +" WHERE "+dt.Property_txtStatus+"='"+Status+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<clsLogReceiverHeader_mobile>();
            do {
                clsLogReceiverHeader_mobile contact = new clsLogReceiverHeader_mobile();
                contact.setTxtIdLogReceiver(cursor.getString(0));
                contact.setTxtStatus(cursor.getString(1));
                contact.setTxtNIK(cursor.getString(2));
                contact.setTxtUSerName(cursor.getString(3));
                contact.setDtInserted(cursor.getString(4));
                contact.setDtUpdated(cursor.getString(5));
                contact.setIntActive(cursor.getString(6));
                contact.setIntSubmit(cursor.getString(7));
                contact.setIntSync(cursor.getString(8));
                contact.setTxtIdReceiver(cursor.getString(9));
                contact.setTxtGuidLogin(cursor.getString(10));
                contact.setTxtIdHeaderNotif(cursor.getString(11));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<clsLogReceiverHeader_mobile> getAllData(SQLiteDatabase db) {
        List<clsLogReceiverHeader_mobile> contactList = null;
        // Select All Query
        clsLogReceiverHeader_mobile dt = new clsLogReceiverHeader_mobile();
        String selectQuery = "SELECT  " + dt.PropertyAll + " FROM "
                + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<clsLogReceiverHeader_mobile>();
            do {
                clsLogReceiverHeader_mobile contact = new clsLogReceiverHeader_mobile();
                contact.setTxtIdLogReceiver(cursor.getString(0));
                contact.setTxtStatus(cursor.getString(1));
                contact.setTxtNIK(cursor.getString(2));
                contact.setTxtUSerName(cursor.getString(3));
                contact.setDtInserted(cursor.getString(4));
                contact.setDtUpdated(cursor.getString(5));
                contact.setIntActive(cursor.getString(6));
                contact.setIntSubmit(cursor.getString(7));
                contact.setIntSync(cursor.getString(8));
                contact.setTxtIdReceiver(cursor.getString(9));
                contact.setTxtGuidLogin(cursor.getString(10));
                contact.setTxtIdHeaderNotif(cursor.getString(11));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    // Getting All Contacts
    public List<clsLogReceiverHeader_mobile> getAllDataToPushData(SQLiteDatabase db) {
        List<clsLogReceiverHeader_mobile> contactList = null;
        // Select All Query
        clsLogReceiverHeader_mobile dt=new clsLogReceiverHeader_mobile();
        String selectQuery = "SELECT  "+dt.PropertyAll+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intSubmit+"='1' AND "+dt.Property_intSync+"='0'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<clsLogReceiverHeader_mobile>();
            do {
                clsLogReceiverHeader_mobile contact = new clsLogReceiverHeader_mobile();
                contact.setTxtIdLogReceiver(cursor.getString(0));
                contact.setTxtStatus(cursor.getString(1));
                contact.setTxtNIK(cursor.getString(2));
                contact.setTxtUSerName(cursor.getString(3));
                contact.setDtInserted(cursor.getString(4));
                contact.setDtUpdated(cursor.getString(5));
                contact.setIntActive(cursor.getString(6));
                contact.setIntSubmit(cursor.getString(7));
                contact.setIntSync(cursor.getString(8));
                contact.setTxtIdReceiver(cursor.getString(9));
                contact.setTxtGuidLogin(cursor.getString(10));
                contact.setTxtIdHeaderNotif(cursor.getString(11));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    // Deleting single contact
    public void deleteContactByNotifId(SQLiteDatabase db,String id) {
        clsLogReceiverHeader_mobile dt = new clsLogReceiverHeader_mobile();
        db.delete(TABLE_CONTACTS, dt.Property_txtIdLogReceiver + " = ?",
                new String[] { String.valueOf(id) });
    }
    public void DeleteAllDAta(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    // Getting contacts Count
    public int getContactsCount(SQLiteDatabase db) {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        int num =cursor.getCount();
        cursor.close();

        // return count
        return num;
    }

    public void updateStatus(SQLiteDatabase db,String dataid) {
        clsLogReceiverHeader_mobile dt=new clsLogReceiverHeader_mobile();
        db.execSQL("Update "+TABLE_CONTACTS+" set  "
                +dt.Property_txtStatus+"=0"
                +" Where " + dt.Property_txtIdLogReceiver +"='"+ dataid+"'");
    }
    //count status
    public int getContactsCountStatus(SQLiteDatabase db, String idHeaderNotif) {
        clsLogReceiverHeader_mobile dt=new clsLogReceiverHeader_mobile();
        String countQuery = "SELECT 1 FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtStatus+"='READ' AND " + dt.Property_txtIdHeaderNotif+"='"+idHeaderNotif+"'";
        Cursor cursor = db.rawQuery(countQuery, null);
        int num =cursor.getCount();
        cursor.close();

        // return count
        return num;
    }
}
