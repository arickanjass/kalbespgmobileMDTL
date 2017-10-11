package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.KoordinasiOutletData;
import library.spgmobile.common.KoordinasiOutletImageData;
import library.spgmobile.common.tKemasanRusakImageData;
import library.spgmobile.common.tTidakSesuaiPesananHeaderData;
import library.spgmobile.common.tTidakSesuaiPesananImageData;

/**
 * Created by aan.junianto on 10/10/2017.
 */

public class tTidakSesuaiPesananImageDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tTidakSesuaiPesananImage;

    public tTidakSesuaiPesananImageDA(SQLiteDatabase db) {
        tTidakSesuaiPesananImageData dt = new tTidakSesuaiPesananImageData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_txtId + " TEXT PRIMARY KEY,"
                + dt.Property_txtHeaderId + " TEXT NULL,"
                + dt.Property_txtImage + " BLOB NULL,"
                + dt.Property_intPosition + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // upgrading database
    public void Droptable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
    }

    public void SaveDataImage(SQLiteDatabase db, tTidakSesuaiPesananImageData data) {
        tTidakSesuaiPesananImageData dt = new tTidakSesuaiPesananImageData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_txtHeaderId, String.valueOf(data.get_txtHeaderId()));
        cv.put(dt.Property_txtImage, data.get_txtImage());
        cv.put(dt.Property_intPosition, String.valueOf(data.get_intPosition()));
        if (data.get_txtId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_txtId, String.valueOf(data.get_txtId()));
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }
    public List<tTidakSesuaiPesananImageData> getDataByHeaderId(SQLiteDatabase db, String id) {
        List<tTidakSesuaiPesananImageData> contactList = null;
        tTidakSesuaiPesananImageData dt = new tTidakSesuaiPesananImageData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_txtId,
                        dt.Property_txtHeaderId, dt.Property_txtImage, dt.Property_intPosition}, dt.Property_txtHeaderId + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                contactList = new ArrayList<tTidakSesuaiPesananImageData>();
                do {
                    tTidakSesuaiPesananImageData contact = new tTidakSesuaiPesananImageData();
                    contact.set_txtId(String.valueOf(cursor.getString(0)));
                    contact.set_txtHeaderId(cursor.getString(1));
                    contact.set_txtImage(cursor.getBlob(2));
                    contact.set_intPosition(cursor.getString(3));
                    contactList.add(contact);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return contactList;
    }
    public List<tTidakSesuaiPesananImageData> getAllDataToPushData(SQLiteDatabase db, List<tTidakSesuaiPesananHeaderData> ListOfKoordinasiOutlet){
        List<tTidakSesuaiPesananImageData> contactList = null;
        tTidakSesuaiPesananImageData dt = new tTidakSesuaiPesananImageData();

        String KoordinasiOutlet = "()";

        if (ListOfKoordinasiOutlet != null){
            KoordinasiOutlet = "(";
            for (int i = 0; i < ListOfKoordinasiOutlet.size(); i++){
                KoordinasiOutlet = KoordinasiOutlet + "'" + ListOfKoordinasiOutlet.get(i).get_intId() +"'";
                KoordinasiOutlet = KoordinasiOutlet + ((i + 1) != ListOfKoordinasiOutlet.size() ? "," : ")");
            }
        }
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS + " WHERE "+dt.Property_txtHeaderId+" IN " + KoordinasiOutlet;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tTidakSesuaiPesananImageData>();
            do {
                tTidakSesuaiPesananImageData contact = new tTidakSesuaiPesananImageData();
                contact.set_txtId(cursor.getString(0));
                contact.set_txtHeaderId(cursor.getString(1));
                contact.set_txtImage(cursor.getBlob(2));
                contact.set_intPosition(cursor.getString(3));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
