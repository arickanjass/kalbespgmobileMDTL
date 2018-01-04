package library.spgmobile.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tKemasanRusakDetailData;
import library.spgmobile.common.tKemasanRusakHeaderData;
import library.spgmobile.common.tSalesProductQuantityDetailData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;

/**
 * Created by aan.junianto on 10/10/2017.
 */

public class tKemasanRusakDetailDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tKemasanRusakDetail;

    // Contacts Table Columns names

    public tKemasanRusakDetailDA(SQLiteDatabase db) {
        tKemasanRusakDetailData dt = new tKemasanRusakDetailData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_intId + " TEXT PRIMARY KEY,"
                + dt.Property_dtDate + " TEXT NULL,"
                + dt.Property_intPrice + " TEXT NULL,"
                + dt.Property_txtCodeProduct + " TEXT NULL,"
                + dt.Property_txtKeterangan + " TEXT NULL,"
                + dt.Property_txtProduct + " TEXT NULL,"
                + dt.Property_txtExpireDate + " TEXT NULL,"
                + dt.Property_txtQuantity + " TEXT NULL,"
                + dt.Property_intTotal + " TEXT NULL,"
                + dt.Property_txtKemasanRusak + " TEXT NULL,"
                + dt.Property_intActive + " TEXT NULL,"
                + dt.Property_txtNIK + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void SaveData(SQLiteDatabase db, tKemasanRusakDetailData data) {
        tKemasanRusakDetailData dt = new tKemasanRusakDetailData();
        db.execSQL("INSERT OR REPLACE into " +TABLE_CONTACTS+ " (" +dt.Property_intId+ ","
                +dt.Property_dtDate+","
                +dt.Property_intPrice+","
                +dt.Property_txtCodeProduct+","
                +dt.Property_txtKeterangan+","
                +dt.Property_txtProduct+","
                +dt.Property_txtExpireDate+","
                +dt.Property_txtQuantity+","
                +dt.Property_intTotal+","
                +dt.Property_txtKemasanRusak +","
                // +dt.Property_intActive+","
                +dt.Property_txtNIK+") "+
                "values('" +String.valueOf(data.getIntId())+"','"
                +String.valueOf(data.get_dtDate())+"','"
                +String.valueOf(data.get_intPrice())+"','"
                +String.valueOf(data.get_txtCodeProduct())+"','"
                +String.valueOf(data.get_txtKeterangan())+"','"
                +String.valueOf(data.getTxtProduct())+"','"
                +String.valueOf(data.getTxtExpireDate())+"','"
                +String.valueOf(data.getTxtQuantity())+"','"
                +String.valueOf(data.get_intTotal())+"','"
                +String.valueOf(data.get_txtKemasanRusak())+"','"
                //+String.valueOf(data.get_intActive())+"','"
                +String.valueOf(data.get_txtNIK())+"')");
    }

    public List<tKemasanRusakDetailData> getDataByNo(SQLiteDatabase db, String No) {
        List<tKemasanRusakDetailData> contactList = null;
        tKemasanRusakDetailData dt = new tKemasanRusakDetailData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_intId,
                        dt.Property_dtDate, dt.Property_intPrice, dt.Property_txtQuantity,
                        dt.Property_txtCodeProduct, dt.Property_txtKeterangan, dt.Property_txtProduct,
                        dt.Property_txtNIK, dt.Property_intTotal, dt.Property_txtKemasanRusak, dt.Property_txtExpireDate}, dt.Property_txtKemasanRusak + "=?",
                new String[] { String.valueOf(No) }, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                contactList = new ArrayList<tKemasanRusakDetailData>();
                do {
                    tKemasanRusakDetailData contact = new tKemasanRusakDetailData();
                    contact.setIntId(String.valueOf(cursor.getString(0)));
                    contact.set_dtDate(cursor.getString(1));
                    contact.set_intPrice(cursor.getString(2));
                    contact.setTxtQuantity(cursor.getString(3));
                    contact.set_txtCodeProduct(cursor.getString(4));
                    contact.set_txtKeterangan(cursor.getString(5));
                    contact.setTxtProduct(cursor.getString(6));
                    contact.set_txtNIK(cursor.getString(7));
                    contact.set_intTotal(cursor.getString(8));
                    contact.set_txtKemasanRusak(cursor.getString(9));
                    contact.setTxtExpireDate(cursor.getString(10));
                    contactList.add(contact);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return contactList;
    }
    public void deleteByID(SQLiteDatabase db, String id){
        tKemasanRusakDetailData dt = new tKemasanRusakDetailData();
        String whereClause = dt.Property_intId + " = ?";
        String[] whereArgs = new String[]{
                String.valueOf(id)
        };
        db.delete(TABLE_CONTACTS, whereClause, whereArgs);
    }
    // Getting single contact
    public List<tKemasanRusakDetailData> getDataByKemasanRusak(SQLiteDatabase db, String id) {
        tKemasanRusakDetailData dt = new tKemasanRusakDetailData();
        List<tKemasanRusakDetailData> contactList = new ArrayList<tKemasanRusakDetailData>();
        String[] tableColumns = new String[] {
                dt.Property_intId,
                dt.Property_dtDate,
                dt.Property_intPrice,
                dt.Property_txtCodeProduct,
                dt.Property_txtKeterangan,
                dt.Property_txtProduct,
                dt.Property_txtExpireDate,
                dt.Property_txtQuantity,
                dt.Property_intTotal,
                dt.Property_txtKemasanRusak,
                dt.Property_txtNIK
        };
        String whereClause = dt.Property_txtKemasanRusak + "=?";
        String[] whereArgs = new String[]{
                String.valueOf(id)
        };
        String groupBy = null;
        String havingBy = null;
        String orderBy = dt.Property_dtDate;

        Cursor cursor = db.query(TABLE_CONTACTS,
                tableColumns,
                whereClause,
                whereArgs,
                groupBy,
                havingBy,
                orderBy);

        if (cursor.moveToFirst()) {
            do {
                tKemasanRusakDetailData contact = new tKemasanRusakDetailData();
                contact.setIntId(cursor.getString(0));
                contact.set_dtDate(cursor.getString(1));
                contact.set_intPrice(cursor.getString(2));
                contact.set_txtCodeProduct(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.setTxtProduct(cursor.getString(5));
                contact.setTxtExpireDate(cursor.getString(6));
                contact.setTxtQuantity(cursor.getString(7));
                contact.set_intTotal(cursor.getString(8));
                contact.set_txtKemasanRusak(cursor.getString(9));
                contact.set_txtNIK(cursor.getString(10));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
    // push data
    public List<tKemasanRusakDetailData> getAllDataToPushData(SQLiteDatabase db, List<tKemasanRusakHeaderData> ListOfSalesProductQuantityHeader) {
        List<tKemasanRusakDetailData> contactList = null;
        // select All Query
        tKemasanRusakDetailData dt = new tKemasanRusakDetailData();

        String tSalesProductQuantityHeader = "()";

        if (ListOfSalesProductQuantityHeader != null){
            tSalesProductQuantityHeader = "(";
            for (int i = 0; i < ListOfSalesProductQuantityHeader.size(); i++) {
                tSalesProductQuantityHeader = tSalesProductQuantityHeader + "'" + ListOfSalesProductQuantityHeader.get(i).get_txtKemasanRusak() + "'";
                tSalesProductQuantityHeader = tSalesProductQuantityHeader + ((i + 1) != ListOfSalesProductQuantityHeader.size() ? "," : ")");
            }
        }
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtKemasanRusak +" IN " + tSalesProductQuantityHeader;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tKemasanRusakDetailData>();
            do {
                tKemasanRusakDetailData contact = new tKemasanRusakDetailData();
                contact.setIntId(cursor.getString(0));
                contact.set_dtDate(cursor.getString(1));
                contact.set_intPrice(cursor.getString(2));
                contact.set_txtCodeProduct(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.setTxtProduct(cursor.getString(5));
                contact.setTxtExpireDate(cursor.getString(6));
                contact.setTxtQuantity(cursor.getString(7));
                contact.set_intTotal(cursor.getString(8));
                contact.set_txtKemasanRusak(cursor.getString(9));
                contact.set_intActive(cursor.getString(10));
                contact.set_txtNIK(cursor.getString(11));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }


    public List<tKemasanRusakDetailData> getAllDataKeep(SQLiteDatabase db, List<tKemasanRusakHeaderData> ListOfSalesProductQuantityHeader) {
        List<tKemasanRusakDetailData> contactList = null;
        // select All Query
        tKemasanRusakDetailData dt = new tKemasanRusakDetailData();

        String tSalesProductQuantityHeader = "()";

        if (ListOfSalesProductQuantityHeader != null){
            tSalesProductQuantityHeader = "(";
            for (int i = 0; i < ListOfSalesProductQuantityHeader.size(); i++) {
                tSalesProductQuantityHeader = tSalesProductQuantityHeader + "'" + ListOfSalesProductQuantityHeader.get(i).get_txtKemasanRusak() + "'";
                tSalesProductQuantityHeader = tSalesProductQuantityHeader + ((i + 1) != ListOfSalesProductQuantityHeader.size() ? "," : ")");
            }
        }
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtKemasanRusak +" IN " + tSalesProductQuantityHeader;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tKemasanRusakDetailData>();
            do {
                tKemasanRusakDetailData contact = new tKemasanRusakDetailData();
                contact.setIntId(cursor.getString(0));
                contact.set_dtDate(cursor.getString(1));
                contact.set_txtCodeProduct(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.setTxtProduct(cursor.getString(4));
                contact.setTxtExpireDate(cursor.getString(5));
                contact.setTxtQuantity(cursor.getString(6));
                contact.set_txtKemasanRusak(cursor.getString(7));
                contact.set_intPrice(cursor.getString(8));
                contact.set_intTotal(cursor.getString(9));
                contact.set_intActive(cursor.getString(10));
                contact.set_txtNIK(cursor.getString(11));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }


    public List<tKemasanRusakDetailData> getAllData(SQLiteDatabase db) {
        List<tKemasanRusakDetailData> contactList = null;
        // select all query
        tKemasanRusakDetailData dt = new tKemasanRusakDetailData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tKemasanRusakDetailData>();
            do {
                tKemasanRusakDetailData contact = new tKemasanRusakDetailData();
                contact.setIntId(cursor.getString(0));
                contact.set_dtDate(cursor.getString(1));
                contact.set_txtCodeProduct(cursor.getString(2));
                contact.set_txtKeterangan(cursor.getString(3));
                contact.setTxtProduct(cursor.getString(4));
                contact.setTxtExpireDate(cursor.getString(5));
                contact.setTxtQuantity(cursor.getString(6));
                contact.set_txtKemasanRusak(cursor.getString(7));
                contact.set_intPrice(cursor.getString(8));
                contact.set_intTotal(cursor.getString(9));
                contact.set_intActive(cursor.getString(10));
                contact.set_txtNIK(cursor.getString(11));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
