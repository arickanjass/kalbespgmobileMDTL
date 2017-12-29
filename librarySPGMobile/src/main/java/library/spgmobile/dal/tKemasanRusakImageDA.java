package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tKemasanRusakDetailData;
import library.spgmobile.common.tKemasanRusakHeaderData;
import library.spgmobile.common.tKemasanRusakImageData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tSalesProductQuantityImageData;

/**
 * Created by aan.junianto on 10/10/2017.
 */

public class tKemasanRusakImageDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tKemasanRusakImage;

    public tKemasanRusakImageDA(SQLiteDatabase db) {
        tKemasanRusakImageData dt = new tKemasanRusakImageData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_txtId + " TEXT PRIMARY KEY,"
                + dt.Property_txtHeaderId + " TEXT NULL,"
                + dt.Property_txtImage + " BLOB NULL,"
                + dt.Property_intPosition + " TEXT NULL,"
                + dt.Property_txtType + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // upgrading database
    public void Droptable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
    }

    public void SaveDataImage(SQLiteDatabase db, tKemasanRusakImageData data) {
        tKemasanRusakImageData dt = new tKemasanRusakImageData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_txtHeaderId, String.valueOf(data.get_txtHeaderId()));
        cv.put(dt.Property_txtImage, data.get_txtImage());
        cv.put(dt.Property_intPosition, String.valueOf(data.get_intPosition()));
        cv.put(dt.Property_txtType, String.valueOf(data.get_txtType()));
        if (data.get_txtId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_txtId, String.valueOf(data.get_txtId()));
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }

    public List<tKemasanRusakImageData> getDataByHeaderId(SQLiteDatabase db, String id) {
        List<tKemasanRusakImageData> contactList = null;
        tKemasanRusakImageData dt = new tKemasanRusakImageData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_txtId,
                        dt.Property_txtHeaderId, dt.Property_txtImage, dt.Property_intPosition,
                        dt.Property_txtType}, dt.Property_txtHeaderId + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                contactList = new ArrayList<tKemasanRusakImageData>();
                do {
                    tKemasanRusakImageData contact = new tKemasanRusakImageData();
                    contact.set_txtId(String.valueOf(cursor.getString(0)));
                    contact.set_txtHeaderId(cursor.getString(1));
                    contact.set_txtImage(cursor.getBlob(2));
                    contact.set_intPosition(cursor.getString(3));
                    contact.set_txtType(cursor.getString(4));
                    contactList.add(contact);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return contactList;
    }
    public List<tKemasanRusakImageData> getAllDataToPushData(SQLiteDatabase db, List<tKemasanRusakHeaderData> ListOfSalesProductQuantityHeader){
        List<tKemasanRusakImageData> contactList = null;
        tKemasanRusakImageData dt = new tKemasanRusakImageData();

        String tSalesProductQuantityHeader = "()";

        if (ListOfSalesProductQuantityHeader != null){
            tSalesProductQuantityHeader = "(";
            for (int i = 0; i < ListOfSalesProductQuantityHeader.size(); i++){
                tSalesProductQuantityHeader = tSalesProductQuantityHeader + "'" + ListOfSalesProductQuantityHeader.get(i).get_txtKemasanRusak() +"'";
                tSalesProductQuantityHeader = tSalesProductQuantityHeader + ((i + 1) != ListOfSalesProductQuantityHeader.size() ? "," : ")");
            }
        }
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS + " WHERE "+dt.Property_txtHeaderId+" IN " + tSalesProductQuantityHeader;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tKemasanRusakImageData>();
            do {
                tKemasanRusakImageData contact = new tKemasanRusakImageData();
                contact.set_txtId(cursor.getString(0));
                contact.set_txtHeaderId(cursor.getString(1));
                contact.set_txtImage(cursor.getBlob(2));
                contact.set_intPosition(cursor.getString(3));
                contact.set_txtType(cursor.getString(4));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tKemasanRusakImageData> getAllData(SQLiteDatabase db){
        List<tKemasanRusakImageData> contactList = null;
        // select all query
        tKemasanRusakImageData dt = new tKemasanRusakImageData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tKemasanRusakImageData>();
            do {
                tKemasanRusakImageData contact = new tKemasanRusakImageData();
                contact.set_txtId(cursor.getString(0));
                contact.set_txtHeaderId(cursor.getString(1));
                contact.set_txtImage(cursor.getBlob(2));
                contact.set_intPosition(cursor.getString(3));
                contact.set_txtType(cursor.getString(4));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
