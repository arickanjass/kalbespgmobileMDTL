package library.salesforce.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.tSalesProductQuantityDetailData;

/**
 * Created by Rian Andrivani on 3/22/2017.
 */

public class tSalesProductQuantityDetailDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tSalesProductQuantityDetail;

    // Contacts Table Columns names

    public tSalesProductQuantityDetailDA(SQLiteDatabase db) {
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();
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
                + dt.Property_txtNoSo + " TEXT NULL,"
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
    public void SaveDatatSalesProductQuantityDetailData(SQLiteDatabase db, tSalesProductQuantityDetailData data) {
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();
        db.execSQL("INSERT OR REPLACE into " +TABLE_CONTACTS+ " (" +dt.Property_intId+ ","
                +dt.Property_dtDate+","
                +dt.Property_intPrice+","
                +dt.Property_txtCodeProduct+","
                +dt.Property_txtKeterangan+","
                +dt.Property_txtProduct+","
                +dt.Property_txtExpireDate+","
                +dt.Property_txtQuantity+","
                +dt.Property_intTotal+","
                +dt.Property_txtNoSo+","
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
                +String.valueOf(data.get_txtNoSo())+"','"
                //+String.valueOf(data.get_intActive())+"','"
                +String.valueOf(data.get_txtNIK())+"')");
    }

    // Adding new contact
    public void UpdateInactiveData(SQLiteDatabase db,String id) {
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();
        db.execSQL("Update "+TABLE_CONTACTS+" SET "+dt.Property_intActive+" = 0 " +
        " WHERE "+ dt.Property_intId +" = '" +id+"'");
    }

    // Getting single contact
    public tSalesProductQuantityDetailData getData(SQLiteDatabase db, int id) {
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {dt.Property_intId,
                dt.Property_dtDate,dt.Property_intPrice,dt.Property_txtCodeProduct,dt.Property_txtKeterangan,
                dt.Property_txtProduct, dt.Property_txtExpireDate, dt.Property_txtQuantity, dt.Property_intTotal
                ,dt.Property_txtNoSo,dt.Property_intActive,dt.Property_txtNIK}, dt.Property_intId + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        tSalesProductQuantityDetailData contact = new tSalesProductQuantityDetailData(String.valueOf(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4), cursor.getString(5),
                cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10),
                cursor.getString(11));
        cursor.close();
        return contact;
    }

    public List<tSalesProductQuantityDetailData> getDataNoso(SQLiteDatabase db, String Noso) {
        List<tSalesProductQuantityDetailData> contactList = null;
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_intId,
                dt.Property_dtDate, dt.Property_intPrice, dt.Property_txtQuantity,
                dt.Property_txtCodeProduct, dt.Property_txtKeterangan, dt.Property_txtProduct,
                dt.Property_txtNIK, dt.Property_intTotal, dt.Property_txtNoSo}, dt.Property_txtNoSo + "=?",
                new String[] { String.valueOf(Noso) }, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                contactList = new ArrayList<tSalesProductQuantityDetailData>();
                do {
                    tSalesProductQuantityDetailData contact = new tSalesProductQuantityDetailData();
                    contact.setIntId(String.valueOf(cursor.getString(0)));
                    contact.set_dtDate(cursor.getString(1));
                    contact.set_intPrice(cursor.getString(2));
                    contact.setTxtQuantity(cursor.getString(3));
                    contact.set_txtCodeProduct(cursor.getString(4));
                    contact.set_txtKeterangan(cursor.getString(5));
                    contact.setTxtProduct(cursor.getString(6));
                    contact.set_txtNIK(cursor.getString(7));
                    contact.set_intTotal(cursor.getString(8));
                    contact.set_txtNoSo(cursor.getString(9));
                    contactList.add(contact);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return contactList;
    }

    // Getting single contact
    public List<tSalesProductQuantityDetailData> getDataByNoSo(SQLiteDatabase db, String id) {
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();
        List<tSalesProductQuantityDetailData> contactList = new ArrayList<tSalesProductQuantityDetailData>();
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
                dt.Property_txtNoSo,
                dt.Property_txtNIK
        };
        String whereClause = dt.Property_txtNoSo + "=?";
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
                    tSalesProductQuantityDetailData contact = new tSalesProductQuantityDetailData();
                    contact.setIntId(cursor.getString(0));
                    contact.set_dtDate(cursor.getString(1));
                    contact.set_intPrice(cursor.getString(2));
                    contact.set_txtCodeProduct(cursor.getString(3));
                    contact.set_txtKeterangan(cursor.getString(4));
                    contact.setTxtProduct(cursor.getString(5));
                    contact.setTxtExpireDate(cursor.getString(6));
                    contact.setTxtQuantity(cursor.getString(7));
                    contact.set_intTotal(cursor.getString(8));
                    contact.set_txtNoSo(cursor.getString(9));
                    contact.set_txtNIK(cursor.getString(10));
                    contactList.add(contact);
                } while (cursor.moveToNext());
            }
        cursor.close();
        return contactList;
    }

    // Getting All Contacts
    public List<tSalesProductQuantityDetailData> getSalesProductQuantityDetailByHeaderId(SQLiteDatabase db, String txtNoSO) {
        List<tSalesProductQuantityDetailData> contactList = new ArrayList<tSalesProductQuantityDetailData>();
        // select All Query
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" Where "+dt.Property_txtNoSo+"='"+txtNoSO+"' AND "+dt.Property_intActive+"=1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                tSalesProductQuantityDetailData contact = new tSalesProductQuantityDetailData();
                contact.setIntId(String.valueOf(cursor.getString(0)));
                contact.set_dtDate(cursor.getString(1));
                contact.set_intPrice(cursor.getString(2));
                contact.set_txtCodeProduct(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.setTxtProduct(cursor.getString(5));
                contact.setTxtExpireDate(cursor.getString(6));
                contact.setTxtQuantity(cursor.getString(7));
                contact.set_txtNIK(cursor.getString(8));
                contact.set_intTotal(cursor.getString(9));
                contact.set_txtNoSo(cursor.getString(10));
                contact.set_intActive(cursor.getString(11));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tSalesProductQuantityDetailData> getAllDataByHeaderId(SQLiteDatabase db, String id) {
        List<tSalesProductQuantityDetailData> contactList = new ArrayList<tSalesProductQuantityDetailData>();
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_txtNoSo + "='" + id + "' ORDER BY " + dt.Property_dtDate + " desc";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tSalesProductQuantityDetailData contact = new tSalesProductQuantityDetailData();
                contact.setIntId(String.valueOf(cursor.getString(0)));
                contact.set_dtDate(cursor.getString(1));
                contact.set_intPrice(cursor.getString(2));
                contact.set_txtCodeProduct(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.setTxtProduct(cursor.getString(5));
                contact.setTxtExpireDate(cursor.getString(6));
                contact.setTxtQuantity(cursor.getString(7));
                contact.set_txtNIK(cursor.getString(8));
                contact.set_intTotal(cursor.getString(9));
                contact.set_txtNoSo(cursor.getString(10));
                contact.set_intActive(cursor.getString(11));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    // Getting All Contacts
    public List<tSalesProductQuantityDetailData> getAllData(SQLiteDatabase db) {
        List<tSalesProductQuantityDetailData> contactList = new ArrayList<tSalesProductQuantityDetailData>();
        // select All Query
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intActive+"=1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                tSalesProductQuantityDetailData contact = new tSalesProductQuantityDetailData();
                contact.setIntId(String.valueOf(cursor.getString(0)));
                contact.set_dtDate(cursor.getString(1));
                contact.set_intPrice(cursor.getString(2));
                contact.set_txtCodeProduct(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.setTxtProduct(cursor.getString(5));
                contact.setTxtExpireDate(cursor.getString(6));
                contact.setTxtQuantity(cursor.getString(7));
                contact.set_txtNIK(cursor.getString(8));
                contact.set_intTotal(cursor.getString(9));
                contact.set_txtNoSo(cursor.getString(10));
                contact.set_intActive(cursor.getString(11));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public void DeleteAllDAta(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    // Deleting single contact
    public void deleteContact(SQLiteDatabase db, String id) {
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();
        db.delete(TABLE_CONTACTS, dt.Property_intId + " = ? ",
                new String[] {String.valueOf(id)});
    }
    public void deleteByNOSO(SQLiteDatabase db, String NOSO) {
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();
        db.delete(TABLE_CONTACTS, dt.Property_txtNoSo + " = ?",
                new String[] { NOSO });
    }

    // Getting contacts Count
    public Long getContactsCount(SQLiteDatabase db) {
        String countQuery = "SELECT 1 FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        long intres=(long) cursor.getCount();
        cursor.close();

        // return count
        return intres;
    }

    public List<tSalesProductQuantityDetailData> getAllDataByNoso(SQLiteDatabase db, String id) {
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();
        List<tSalesProductQuantityDetailData> contactList = new ArrayList<tSalesProductQuantityDetailData>();
        String[] tableColumns = new String[] {
                dt.Property_intId,
                dt.Property_dtDate,
                dt.Property_intPrice,
                dt.Property_txtCodeProduct,
                dt.Property_txtKeterangan,
                dt.Property_txtProduct,
                dt.Property_txtExpireDate,
                dt.Property_txtQuantity,
                dt.Property_txtNIK,
                dt.Property_intTotal,
                dt.Property_txtNoSo,
                dt.Property_intActive
        };
        String whereClause = dt.Property_intId + "=?";
        String[] whereArgs = new String[] {
                String.valueOf(id)
        };
        String groupBy = null;
        String havingBy = null;
        String orderBy = dt.Property_intId;

        Cursor cursor = db.query(TABLE_CONTACTS,
                tableColumns,
                whereClause,
                whereArgs,
                groupBy,
                havingBy,
                orderBy);

        if (cursor.moveToFirst()) {
            do {
                tSalesProductQuantityDetailData contact = new tSalesProductQuantityDetailData();
                contact.setIntId(cursor.getString(0));
                contact.set_dtDate(cursor.getString(1));
                contact.set_intPrice(cursor.getString(2));
                contact.set_txtCodeProduct(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.setTxtProduct(cursor.getString(5));
                contact.setTxtExpireDate(cursor.getString(6));
                contact.setTxtQuantity(cursor.getString(7));
                contact.set_txtNIK(cursor.getString(8));
                contact.set_intTotal(cursor.getString(9));
                contact.set_txtNoSo(cursor.getString(10));
                contact.set_intActive(cursor.getString(11));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    // push data
    public List<tSalesProductQuantityDetailData> getAllDataToPushData(SQLiteDatabase db, List<tSalesProductQuantityDetailData> ListOfSalesProductQuantityHeader) {
        List<tSalesProductQuantityDetailData> contactList = null;
        // select All Query
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();

        String tSalesProductQuantityHeader = "()";

        if (ListOfSalesProductQuantityHeader != null){
            tSalesProductQuantityHeader = "(";
            for (int i = 0; i < ListOfSalesProductQuantityHeader.size(); i++) {
                tSalesProductQuantityHeader = tSalesProductQuantityHeader + "'" + ListOfSalesProductQuantityHeader.get(i).get_txtNoSo() + "'";
                tSalesProductQuantityHeader = tSalesProductQuantityHeader + ((i + 1) != ListOfSalesProductQuantityHeader.size() ? "," : ")");
            }
        }
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtNoSo+" IN " + tSalesProductQuantityHeader;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tSalesProductQuantityDetailData>();
            do {
                tSalesProductQuantityDetailData contact = new tSalesProductQuantityDetailData();
                contact.setIntId(cursor.getString(0));
                contact.set_dtDate(cursor.getString(1));
                contact.set_intPrice(cursor.getString(2));
                contact.set_txtCodeProduct(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.setTxtProduct(cursor.getString(5));
                contact.setTxtExpireDate(cursor.getString(6));
                contact.setTxtQuantity(cursor.getString(7));
                contact.set_intTotal(cursor.getString(8));
                contact.set_txtNoSo(cursor.getString(9));
                contact.set_intActive(cursor.getString(10));
                contact.set_txtNIK(cursor.getString(11));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public void deleteByID(SQLiteDatabase db, String id){
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();
        String whereClause = dt.Property_intId + " = ?";
        String[] whereArgs = new String[]{
                String.valueOf(id)
        };
        db.delete(TABLE_CONTACTS, whereClause, whereArgs);
    }
}
