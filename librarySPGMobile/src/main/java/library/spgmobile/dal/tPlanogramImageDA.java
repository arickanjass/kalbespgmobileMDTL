package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tPlanogramImageData;
import library.spgmobile.common.tPlanogramMobileData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tSalesProductQuantityImageData;

/**
 * Created by aan.junianto on 14/08/2017.
 */

public class tPlanogramImageDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tPlanogramImage;

    public tPlanogramImageDA(SQLiteDatabase db) {
        tPlanogramImageData dt = new tPlanogramImageData();
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

    public void SaveDataImage(SQLiteDatabase db, tPlanogramImageData data) {
        tPlanogramImageData dt = new tPlanogramImageData();
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

    public List<tPlanogramImageData> getDataHeaderId(SQLiteDatabase db, String id) {
        List<tPlanogramImageData> contactList = null;
        tPlanogramImageData dt = new tPlanogramImageData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_txtId,
                        dt.Property_txtHeaderId, dt.Property_txtImage, dt.Property_intPosition,
                        dt.Property_txtType}, dt.Property_txtHeaderId + "=?",
                new String[] { String.valueOf(id) }, null, null, dt.Property_txtType + " desc", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                contactList = new ArrayList<tPlanogramImageData>();
                do {
                    tPlanogramImageData contact = new tPlanogramImageData();
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

    // Getting All Contacts
    public List<tSalesProductQuantityImageData> getSalesProductQuantityImageByHeaderId(SQLiteDatabase db, String txtNoQuantityStock) {
        List<tSalesProductQuantityImageData> contactList = new ArrayList<tSalesProductQuantityImageData>();
        // select All Query
        tSalesProductQuantityImageData dt = new tSalesProductQuantityImageData();
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS +" Where "+dt.Property_txtHeaderId+"='"+txtNoQuantityStock;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                tSalesProductQuantityImageData contact = new tSalesProductQuantityImageData();
                contact.set_txtId(String.valueOf(cursor.getString(0)));
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

    public List<tPlanogramImageData> getAllDataToPushData(SQLiteDatabase db, List<tPlanogramMobileData> ListOfttPlanogramMobileData){
        List<tPlanogramImageData> contactList = null;
        tPlanogramImageData dt = new tPlanogramImageData();

        String tPlanogramMobileData = "()";

        if (ListOfttPlanogramMobileData != null){
            tPlanogramMobileData = "(";
            for (int i = 0; i < ListOfttPlanogramMobileData.size(); i++){
                tPlanogramMobileData = tPlanogramMobileData + "'" + ListOfttPlanogramMobileData.get(i).get_txtIdPlanogram() +"'";
                tPlanogramMobileData = tPlanogramMobileData + ((i + 1) != ListOfttPlanogramMobileData.size() ? "," : ")");
            }
        }
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS + " WHERE "+dt.Property_txtHeaderId+" IN " + tPlanogramMobileData;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tPlanogramImageData>();
            do {
                tPlanogramImageData contact = new tPlanogramImageData();
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
    // Deleting single contact
    public void deleteByID(SQLiteDatabase db, String id) {
        tPlanogramImageData dt = new tPlanogramImageData();
        String whereClause = dt.Property_txtId + " = ?";
        String[] whereArgs = new String[]{
                String.valueOf(id)
        };

        db.delete(TABLE_CONTACTS, whereClause, whereArgs);
    }
}
