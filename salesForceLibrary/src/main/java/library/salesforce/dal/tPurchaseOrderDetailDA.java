package library.salesforce.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.tPurchaseOrderDetailData;
import library.salesforce.common.tPurchaseOrderHeaderData;

/**
 * Created by XSIS on 20/03/2017.
 */

public class tPurchaseOrderDetailDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tPurchaseOrderDetail;

    public tPurchaseOrderDetailDA(SQLiteDatabase db){
        tPurchaseOrderDetailData dt = new tPurchaseOrderDetailData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_intId + " TEXT PRIMARY KEY,"
                + dt.Property_dtDate + " TEXT NULL,"
                + dt.Property_intPrice + " TEXT NULL,"
                + dt.Property_intQty + " TEXT NULL,"
                + dt.Property_txtCodeProduct + " TEXT NULL,"
                + dt.Property_txtKeterangan + " TEXT NULL,"
                + dt.Property_txtNameProduct + " TEXT NULL,"
                + dt.Property_intTotal + " TEXT NULL,"
                + dt.Property_txtNoOrder + " TEXT NULL,"
                + dt.Property_intActive + " TEXT NULL,"
                + dt.Property_txtNIK + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_CONTACTS);
    }

    public void SaveDatatPurchaseOrderDetailData(SQLiteDatabase db, tPurchaseOrderDetailData data){
        tPurchaseOrderDetailData dt = new tPurchaseOrderDetailData();
        db.execSQL("INSERT OR REPLACE into "+TABLE_CONTACTS+" ("+dt.Property_intId+","
                +dt.Property_dtDate+","
                +dt.Property_intPrice+","
                +dt.Property_intQty+","
                +dt.Property_txtCodeProduct+","
                +dt.Property_txtKeterangan+","
                +dt.Property_txtNameProduct+","
                +dt.Property_intTotal+","
                +dt.Property_txtNoOrder+","
                +dt.Property_intActive+","
                +dt.Property_txtNIK+") "+
        "value('"+String.valueOf(data.get_intId())+"','"
            +String.valueOf(data.get_dtDate())+"','"
            +String.valueOf(data.get_intPrice())+"','"
            +String.valueOf(data.get_intQty())+"','"
            +String.valueOf(data.get_txtCodeProduct())+"','"
            +String.valueOf(data.get_txtKeterangan())+"','"
            +String.valueOf(data.get_txtNameProduct())+"','"
            +String.valueOf(data.get_intTotal())+"','"
            +String.valueOf(data.get_txtNoOrder())+"','"
            +String.valueOf(data.get_intActive())+"','"
            +String.valueOf(data.get_txtNIK())+"')");
    }

    public void UpdateInActiveDataPO(SQLiteDatabase db, String id){
     tPurchaseOrderDetailData dt = new tPurchaseOrderDetailData();
        db.execSQL("Update "+TABLE_CONTACTS+" SET "+dt.Property_intActive+" = 0 "+
        " WHERE "+dt.Property_intId+" = '"+id+"'");
    }

    public tPurchaseOrderDetailData getData(SQLiteDatabase db, int id){
        tPurchaseOrderDetailData dt = new tPurchaseOrderDetailData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{dt.Property_intId, dt.Property_dtDate, dt.Property_intPrice, dt.Property_intQty,
        dt.Property_txtCodeProduct, dt.Property_txtKeterangan, dt.Property_txtNameProduct, dt.Property_txtNIK, dt.Property_intTotal, dt.Property_txtNoOrder,
        dt.Property_intActive}, dt.Property_intId +" = ? ", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
           cursor.moveToFirst();
                tPurchaseOrderDetailData contact = new tPurchaseOrderDetailData(String.valueOf(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8), cursor.getString(9), cursor.getString(10));

            cursor.close();
            return contact;
    }

    public List<tPurchaseOrderDetailData> getDataByNoOrder(SQLiteDatabase db, String NoOrder) {
        List<tPurchaseOrderDetailData> contactList = null;
        tPurchaseOrderDetailData dt = new tPurchaseOrderDetailData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{dt.Property_intId,
                dt.Property_dtDate, dt.Property_intPrice, dt.Property_intQty, dt.Property_txtCodeProduct, dt.Property_txtKeterangan,
                dt.Property_txtKeterangan, dt.Property_txtNameProduct, dt.Property_txtNIK, dt.Property_intTotal, dt.Property_txtNoOrder,
                dt.Property_intActive},dt.Property_txtNoOrder + " = ? ",
                new String[]{String.valueOf(NoOrder)}, null, null, null, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                contactList = new ArrayList<tPurchaseOrderDetailData>();
                do {
                    tPurchaseOrderDetailData contact = new tPurchaseOrderDetailData();
                    contact.set_intId(String.valueOf(cursor.getString(0)));
                    contact.set_dtDate(cursor.getString(1));
                    contact.set_intPrice(cursor.getString(2));
                    contact.set_intQty(cursor.getString(3));
                    contact.set_txtCodeProduct(cursor.getString(4));
                    contact.set_txtKeterangan(cursor.getString(5));
                    contact.set_txtNameProduct(cursor.getString(6));
                    contact.set_txtNIK(cursor.getString(7));
                    contact.set_intTotal(cursor.getString(8));
                    contact.set_txtNoOrder(cursor.getString(9));
                    contact.set_intActive(cursor.getString(10));
                    contactList.add(contact);
                } while (cursor.moveToFirst());
            }
        }
        cursor.close();
        return contactList;
    }

    public List<tPurchaseOrderDetailData>getPurchaseOrderDetailByHeaderId(SQLiteDatabase db, String txtNoOrder){
        List<tPurchaseOrderDetailData> contactList = new ArrayList<tPurchaseOrderDetailData>();
        tPurchaseOrderDetailData dt = new tPurchaseOrderDetailData();
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS + " Where "+dt.Property_txtNoOrder+"='"+txtNoOrder+"'AND "
                +dt.Property_intActive+"=1";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tPurchaseOrderDetailData contact = new tPurchaseOrderDetailData();
                contact.set_intId(String.valueOf(cursor.getString(1)));
                contact.set_dtDate(cursor.getString(0));
                contact.set_intPrice(cursor.getString(2));
                contact.set_intQty(cursor.getString(3));
                contact.set_txtCodeProduct(cursor.getString(4));
                contact.set_txtKeterangan(cursor.getString(5));
                contact.set_txtNameProduct(cursor.getString(6));
                contact.set_intTotal(cursor.getString(7));
                contact.set_txtNIK(cursor.getString(8));
                contact.set_txtNoOrder(cursor.getString(9));
                contact.set_intActive(cursor.getString(10));
                contactList.add(contact);
            } while (cursor.moveToFirst());
        }
        cursor.close();
        return contactList;
    }

    public List<tPurchaseOrderDetailData> getAllDataPO(SQLiteDatabase db){
        List<tPurchaseOrderDetailData> contactList = new ArrayList<tPurchaseOrderDetailData>();
        tPurchaseOrderDetailData dt = new tPurchaseOrderDetailData();
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS + " Where "+dt.Property_intActive+"=1";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tPurchaseOrderDetailData contact = new tPurchaseOrderDetailData();
                contact.set_intId(String.valueOf(cursor.getString(1)));
                contact.set_dtDate(cursor.getString(0));
                contact.set_intPrice(cursor.getString(2));
                contact.set_intQty(cursor.getString(3));
                contact.set_txtCodeProduct(cursor.getString(4));
                contact.set_txtKeterangan(cursor.getString(5));
                contact.set_txtNameProduct(cursor.getString(6));
                contact.set_intTotal(cursor.getString(7));
                contact.set_txtNIK(cursor.getString(8));
                contact.set_txtNoOrder(cursor.getString(9));
                contact.set_intActive(cursor.getString(10));
                contactList.add(contact);
            } while (cursor.moveToFirst());
        }
        cursor.close();
        return contactList;
    }

    public void deleteAllDataPO(SQLiteDatabase db){
        db.execSQL("DELETE FROM "+TABLE_CONTACTS);
    }
    public void deleteContact(SQLiteDatabase db, String id){
        tPurchaseOrderDetailData dt = new tPurchaseOrderDetailData();
        db.delete(TABLE_CONTACTS, dt.Property_intId+" = ?", new String[]{String.valueOf(id)});
    }

    public void deleteByNoOrder(SQLiteDatabase db, String NoOrder){
        tPurchaseOrderDetailData dt = new tPurchaseOrderDetailData();
        db.delete(TABLE_CONTACTS, dt.Property_txtNoOrder+" = ?", new String[]{NoOrder});
    }

    public Long getContactsCountPO (SQLiteDatabase db){
        String countQuery = "SELECT 1 FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        long intres = (long) cursor.getCount();
        cursor.close();
        return intres;
    }

    public List<tPurchaseOrderDetailData> getAllDataToPushDataPO(SQLiteDatabase db, List<tPurchaseOrderHeaderData> ListOfPurchaseOrderHeader){
        List<tPurchaseOrderDetailData> contactList = null;
        tPurchaseOrderDetailData dt = new tPurchaseOrderDetailData();
        String tPurchaseOrderHeader = "()";
        if (ListOfPurchaseOrderHeader != null){
            tPurchaseOrderHeader = "(";
            for (int i = 0; i < ListOfPurchaseOrderHeader.size(); i++){
                tPurchaseOrderHeader = tPurchaseOrderHeader + "'" + ListOfPurchaseOrderHeader.get(i).get_txtNoOrder() + "'";
                tPurchaseOrderHeader = tPurchaseOrderHeader + ((i+1) != ListOfPurchaseOrderHeader.size() ? "," : ")");
            }
        }
        String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS + " WHERE "+dt.Property_txtNoOrder+" IN " + tPurchaseOrderHeader;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            contactList = new ArrayList<tPurchaseOrderDetailData>();
            do {
                tPurchaseOrderDetailData contact = new tPurchaseOrderDetailData();
                contact.set_intId(cursor.getString(0));
                contact.set_dtDate(cursor.getString(1));
                contact.set_intPrice(cursor.getString(2));
                contact.set_intQty(cursor.getString(3));
                contact.set_txtCodeProduct(cursor.getString(4));
                contact.set_txtKeterangan(cursor.getString(5));
                contact.set_txtNameProduct(cursor.getString(6));
                contact.set_intTotal(cursor.getString(7));
                contact.set_txtNoOrder(cursor.getString(8));
                contact.set_intActive(cursor.getString(9));
                contact.set_txtNIK(cursor.getString(10));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
