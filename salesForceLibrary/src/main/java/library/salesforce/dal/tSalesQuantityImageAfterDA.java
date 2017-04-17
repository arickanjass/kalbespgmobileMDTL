package library.salesforce.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.tSalesProductQuantityHeaderData;
import library.salesforce.common.tSalesQuantityImageAfterData;

/**
 * Created by Rian Andrivani on 4/10/2017.
 */

public class tSalesQuantityImageAfterDA {

    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tSalesQuantityImageAfter;

    public tSalesQuantityImageAfterDA(SQLiteDatabase db) {
        tSalesQuantityImageAfterData dt = new tSalesQuantityImageAfterData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_txtId + " TEXT PRIMARY KEY,"
                + dt.Property_txtHeaderId + " TEXT NULL,"
                + dt.Property_after1 + " BLOB NULL,"
                + dt.Property_after2 + " BLOB NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // upgrading database
    public void Droptable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
    }

    public void SaveDataImageAfter(SQLiteDatabase db, tSalesQuantityImageAfterData data) {
        tSalesQuantityImageAfterData dt = new tSalesQuantityImageAfterData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_txtHeaderId, String.valueOf(data.get_txtHeaderId()));
        cv.put(dt.Property_after1, data.get_after1());
        cv.put(dt.Property_after2, data.get_after2());
        if (data.get_txtId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_txtId, String.valueOf(data.get_txtId()));
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }

    public List<tSalesQuantityImageAfterData> getDataHeaderId(SQLiteDatabase db, String id) {
        List<tSalesQuantityImageAfterData> contactList = null;
        tSalesQuantityImageAfterData dt = new tSalesQuantityImageAfterData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_txtId,
        dt.Property_txtHeaderId, dt.Property_after1, dt.Property_after2}, dt.Property_txtHeaderId + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                contactList = new ArrayList<tSalesQuantityImageAfterData>();
                do {
                    tSalesQuantityImageAfterData contact = new tSalesQuantityImageAfterData();
                    contact.set_txtId(String.valueOf(cursor.getString(0)));
                    contact.set_txtHeaderId(cursor.getString(1));
                    contact.set_after1(cursor.getBlob(2));
                    contact.set_after2(cursor.getBlob(3));
                    contactList.add(contact);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return contactList;
    }

    public List<tSalesQuantityImageAfterData> getAllDataToPushData(SQLiteDatabase db, List<tSalesProductQuantityHeaderData> ListOfSalesProductQuantityHeader){
        List<tSalesQuantityImageAfterData> contactList = null;
        tSalesQuantityImageAfterData dt = new tSalesQuantityImageAfterData();

        String tSalesProductQuantityHeader = "()";

        if (ListOfSalesProductQuantityHeader != null){
            tSalesProductQuantityHeader = "(";
            for (int i = 0; i < ListOfSalesProductQuantityHeader.size(); i++){
                tSalesProductQuantityHeader = tSalesProductQuantityHeader + "'" + ListOfSalesProductQuantityHeader.get(i).get_intId() +"'";
                tSalesProductQuantityHeader = tSalesProductQuantityHeader + ((i + 1) != ListOfSalesProductQuantityHeader.size() ? "," : ")");
            }
        }
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS + " WHERE "+dt.Property_txtHeaderId+" IN " + tSalesProductQuantityHeader;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesQuantityImageAfterData>();
            do {
                tSalesQuantityImageAfterData contact = new tSalesQuantityImageAfterData();
                contact.set_txtId(cursor.getString(0));
                contact.set_txtHeaderId(cursor.getString(1));
                contact.set_after1(cursor.getBlob(2));
                contact.set_after2(cursor.getBlob(3));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
