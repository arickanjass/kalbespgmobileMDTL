package library.spgmobile.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mProductPICData;
import library.spgmobile.common.mProductSPGData;
import library.spgmobile.common.mTipeSumberData;

/**
 * Created by aan.junianto on 19/01/2018.
 */

public class mTipeSumberDA {
    public mTipeSumberDA(SQLiteDatabase db) {
        mTipeSumberData dt = new mTipeSumberData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "("
                + dt.Property_TipeSumberDataID + " TEXT PRIMARY KEY,"
                + dt.Property_txtNamaInstitusi + " TEXT NULL,"
                + dt.Property_txtAlamat + " TEXT  NULL,"
                + dt.Property_txtNamaPropinsi + " TEXT  NULL,"
                + dt.Property_txtNamaCabang + " TEXT  NULL,"
                + dt.Property_txtLatitude + " TEXT  NULL,"
                + dt.Property_txtLongitude + " TEXT  NULL,"
                + dt.Property_txtAcc + " TEXT  NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mTipeSumberData;

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void SaveDataMConfig(SQLiteDatabase db, mTipeSumberData data) {
        mTipeSumberData dt = new mTipeSumberData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_TipeSumberDataID
                + "," + dt.Property_txtNamaInstitusi
                + ","+ dt.Property_txtAlamat
                + "," + dt.Property_txtNamaPropinsi
                + "," + dt.Property_txtNamaCabang
                + "," + dt.Property_txtLatitude
                + "," + dt.Property_txtLongitude
                + "," + dt.Property_txtAcc
                + ") " + "values('"
                + String.valueOf(data.getTipeSumberDataID()) + "','"
                + String.valueOf(data.getTxtNamaInstitusi()) + "','"
                + String.valueOf(data.getTxtAlamat()) + "','"
                + String.valueOf(data.getTxtNamaPropinsi()) + "','"
                + String.valueOf(data.getTxtNamaCabang()) + "','"
                + String.valueOf(data.getTxtLatitude()) + "','"
                + String.valueOf(data.getTxtLongitude()) + "','"
                + String.valueOf(data.getTxtAcc()) + "')");
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }

    // Getting single contact
    public List<mTipeSumberData> getDataList(SQLiteDatabase db, String txtSumberDataID) {
        mTipeSumberData dt = new mTipeSumberData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        dt.Property_TipeSumberDataID
                        , dt.Property_txtNamaInstitusi
                        , dt.Property_txtAlamat
                        , dt.Property_txtNamaPropinsi
                        , dt.Property_txtNamaCabang
                        , dt.Property_txtLatitude
                        , dt.Property_txtLongitude
                        , dt.Property_txtAcc},
                dt.Property_TipeSumberDataID + "=?", new String[] { String.valueOf(txtSumberDataID) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        mTipeSumberData contact = new mTipeSumberData();
        List<mTipeSumberData> contactList = new ArrayList<>();
        if (cursor.getCount() > 0) {
            contact.setTipeSumberDataID(cursor.getString(0));
            contact.setTxtNamaInstitusi(cursor.getString(1));
            contact.setTxtAlamat(cursor.getString(2));
            contact.setTxtNamaPropinsi(cursor.getString(3));
            contact.setTxtNamaCabang(cursor.getString(4));
            contact.setTxtLatitude(cursor.getString(5));
            contact.setTxtLongitude(cursor.getString(6));
            contact.setTxtAcc(cursor.getString(7));
            contactList.add(contact);
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contactList;
    }

    public List<mTipeSumberData> getDataListSingle(SQLiteDatabase db, String txtSumberDataID) {
        mTipeSumberData dt = new mTipeSumberData();
//        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
//                        dt.Property_TipeSumberDataID
//                        , dt.Property_txtNamaInstitusi
//                        , dt.Property_txtAlamat
//                        , dt.Property_txtNamaPropinsi
//                        , dt.Property_txtNamaCabang
//                        , dt.Property_txtLatitude
//                        , dt.Property_txtLongitude
//                        , dt.Property_txtAcc},
//                dt.Property_TipeSumberDataID + "=?", new String[] { String.valueOf(txtSumberDataID) },
//                null, null, null, null);
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS +" where " + dt.Property_TipeSumberDataID + " = '" + txtSumberDataID + "'" ;
        Cursor cursor = db.rawQuery(selectQuery, null);
//        if (cursor != null)
//            cursor.moveToFirst();

        List<mTipeSumberData> contactList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do{
                mTipeSumberData contact = new mTipeSumberData();
                contact.setTipeSumberDataID(cursor.getString(0));
                contact.setTxtNamaInstitusi(cursor.getString(1));
                contact.setTxtAlamat(cursor.getString(2));
                contact.setTxtNamaPropinsi(cursor.getString(3));
                contact.setTxtNamaCabang(cursor.getString(4));
                contact.setTxtLatitude(cursor.getString(5));
                contact.setTxtLongitude(cursor.getString(6));
                contact.setTxtAcc(cursor.getString(7));
                contactList.add(contact);
            }  while (cursor.moveToNext());

            // return contact
        }
//        else {
//            contact = null;
//        }
        cursor.close();
        return contactList;
    }

    public void DeleteAllDataMConfig(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
}
