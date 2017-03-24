package library.salesforce.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.NfcEvent;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.tPurchaseOrderHeaderData;

/**
 * Created by XSIS on 20/03/2017.
 */

public class tPurchaseOrderHeaderDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tPurchaseOrderHeader;

    public tPurchaseOrderHeaderDA(SQLiteDatabase db){
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_intId + " TEXT PRIMARY KEY,"
                + dt.Property_txtNoOrder + " TEXT NULL,"
                + dt.Property_txtDate + " TEXT NULL,"
                + dt.Property_OutletCode + " TEXT NULL,"
                + dt.Property_OutletName + " TEXT NULL,"
                + dt.Property_txtKeterangan + " TEXT NULL,"
                + dt.Property_intSumItem + " TEXT NULL,"
                + dt.Property_intSumAmount + " TEXT NULL,"
                + dt.Property_UserId + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL,"
                + dt.Property_intSync + " TEXT NULL,"
                + dt.Property_txtBranchCode + " TEXT NULL,"
                + dt.Property_txtBranchName + " TEXT NULL,"
                + dt.Property_intIdAbsenUser + " TEXT NULL,"
                + dt.Property_txtRoleId + " TEXT NULL,"
                + dt.Property_txtNIK + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    //Upgrading Database
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }

    public void SaveDatatPurchaseOrderHeaderData(SQLiteDatabase db, tPurchaseOrderHeaderData data){
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " (" + dt.Property_intId + ","
                + dt.Property_txtNoOrder + ","
                + dt.Property_OutletCode + ","
                + dt.Property_OutletName + ","
                + dt.Property_txtDate + ","
                + dt.Property_txtKeterangan + ","
                + dt.Property_intSumAmount + ","
                + dt.Property_intSumItem + ","
                + dt.Property_UserId + ","
                + dt.Property_intSubmit + ","
                + dt.Property_intSync + ","
                + dt.Property_txtBranchCode + ","
                + dt.Property_txtBranchName + ","
                + dt.Property_intIdAbsenUser + ","
                + dt.Property_txtRoleId + ","
                + dt.Property_txtNIK + ") " +
                "values('" + String.valueOf(data.get_intId()) + "','"
                + String.valueOf(data.get_txtNoOrder()) + "','"
                + String.valueOf(data.get_OutletCode()) + "','"
                + String.valueOf(data.get_OutletName()) + "','"
                + String.valueOf(data.get_dtDate()) + "','"
                + String.valueOf(data.get_txtKeterangan()) + "','"
                + String.valueOf(data.get_intSumAmount()) + "','"
                + String.valueOf(data.get_intSumItem()) + "','"
                + String.valueOf(data.get_UserId()) + "','"
                + String.valueOf(data.get_intSubmit()) + "','"
                + String.valueOf(data.get_intSync()) + "','"
                + String.valueOf(data.get_txtBranchCode()) + "','"
                + String.valueOf(data.get_txtBranchName()) + "','"
                + String.valueOf(data.get_intIdAbsenUser()) + "','"
                + String.valueOf(data.get_txtRoleId()) + "','"
                + String.valueOf(data.get_txtNIK()) + "')");
    }

    public void UpdateDataItem(SQLiteDatabase db, tPurchaseOrderHeaderData data){
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        db.execSQL("Update " + TABLE_CONTACTS + " set "
                + dt.Property_intSumAmount + " = " + String.valueOf(data.get_intSumAmount())
                + dt.Property_intSumItem + " = "
                + String.valueOf(data.get_intSumItem())
                + " Where " + dt.Property_intId + " ='" + data.get_intId() + "'");
    }

    public void UpdateDataItemForSubmit(SQLiteDatabase db, String dataId){
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        db.execSQL("Update " + TABLE_CONTACTS + " set "
                + dt.Property_intSubmit + " = 1 "
                + " Where " + dt.Property_intId + " ='" + dataId + "'");
    }

    public tPurchaseOrderHeaderData getData(SQLiteDatabase db, String id){
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{dt.Property_intId, dt.Property_OutletCode, dt.Property_OutletName,
                dt.Property_txtDate, dt.Property_txtKeterangan, dt.Property_txtNIK
                , dt.Property_intSumAmount, dt.Property_intSumItem, dt.Property_UserId, dt.Property_intSubmit, dt.Property_txtRoleId
                , dt.Property_intSync, dt.Property_txtBranchCode, dt.Property_txtBranchName, dt.Property_intIdAbsenUser}, dt.Property_intId + "=?",  new String[]{String.valueOf(id)}, null, null, null, null);
        tPurchaseOrderHeaderData contact = null;
        if (cursor.getCount() > 0){
            contact = new tPurchaseOrderHeaderData();
            if (cursor != null)
                cursor.moveToFirst();
            contact.set_intId(cursor.getString(0));
            contact.set_OutletCode(cursor.getString(1));
            contact.set_OutletName(cursor.getString(2));
            contact.set_dtDate(cursor.getString(3));
            contact.set_txtKeterangan(cursor.getString(4));
            contact.set_txtNIK(cursor.getString(5));
            contact.set_intSumAmount(cursor.getString(6));
            contact.set_intSumItem(cursor.getString(7));
            contact.set_UserId(cursor.getString(8));
            contact.set_intSubmit(cursor.getString(9));
            contact.set_intSync(cursor.getString(10));
            contact.set_txtBranchCode(cursor.getString(11));
            contact.set_txtBranchName(cursor.getString(12));
            contact.set_intIdAbsenUser(cursor.getString(13));
            contact.set_txtRoleId(cursor.getString(14));
        }
        cursor.close();
        return contact;
    }

    public boolean CheckDataPushData(SQLiteDatabase db){
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        String selectQuery = "SELECT 1 FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + " = 1 AND " + dt.Property_intSync + " = 0 ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        boolean result = false;
        if (cursor.getCount() > 0){
            result = true;
        }
        cursor.close();
        return result;
    }

    public List<tPurchaseOrderHeaderData> getAllDataToPushData(SQLiteDatabase db){
        List<tPurchaseOrderHeaderData> contactList = null;
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + " = 1 AND " + dt.Property_intSync + " = 0 ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tPurchaseOrderHeaderData>();
            do {
                tPurchaseOrderHeaderData contact = new tPurchaseOrderHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtNoOrder(cursor.getString(1));
                contact.set_dtDate(cursor.getString(2));
                contact.set_OutletCode(cursor.getString(3));
                contact.set_OutletName(cursor.getString(4));
                contact.set_txtKeterangan(cursor.getString(5));
                contact.set_intSumItem(cursor.getString(6));
                contact.set_intSumAmount(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getColumnName(13));
                contact.set_txtNIK(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPurchaseOrderHeaderData> getAllData(SQLiteDatabase db){
        List<tPurchaseOrderHeaderData> contactList = null;
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY txtNoOrder DESC ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tPurchaseOrderHeaderData>();
            do {
                tPurchaseOrderHeaderData contact = new tPurchaseOrderHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtNoOrder(cursor.getString(1));
                contact.set_dtDate(cursor.getString(2));
                contact.set_OutletCode(cursor.getString(3));
                contact.set_OutletName(cursor.getString(4));
                contact.set_txtKeterangan(cursor.getString(5));
                contact.set_intSumItem(cursor.getString(6));
                contact.set_intSumAmount(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getColumnName(13));
                contact.set_txtNIK(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPurchaseOrderHeaderData> getAllDataByOutlet(SQLiteDatabase db, String txtOutletCode){
        List<tPurchaseOrderHeaderData> contactList = null;
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_OutletCode + " = '" + txtOutletCode + "'AND " + dt.Property_intSubmit + " = 1 ";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tPurchaseOrderHeaderData>();
            do {
                tPurchaseOrderHeaderData contact = new tPurchaseOrderHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_OutletCode(cursor.getString(1));
                contact.set_OutletName(cursor.getString(2));
                contact.set_dtDate(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_intSumAmount(cursor.getString(6));
                contact.set_intSumItem(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_txtRoleId(cursor.getString(10));
                contact.set_intSync(cursor.getString(11));
                contact.set_txtBranchCode(cursor.getString(12));
                contact.set_txtBranchName(cursor.getString(13));
                contact.set_intIdAbsenUser(cursor.getColumnName(14));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPurchaseOrderHeaderData> getAllDataSubmit(SQLiteDatabase db, String txtOutletCode){
        List<tPurchaseOrderHeaderData> contactList = null;
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + " = 1 ";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tPurchaseOrderHeaderData>();
            do {
                tPurchaseOrderHeaderData contact = new tPurchaseOrderHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_OutletCode(cursor.getString(1));
                contact.set_OutletName(cursor.getString(2));
                contact.set_dtDate(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_intSumAmount(cursor.getString(6));
                contact.set_intSumItem(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_txtRoleId(cursor.getString(10));
                contact.set_intSync(cursor.getString(11));
                contact.set_txtBranchCode(cursor.getString(12));
                contact.set_txtBranchName(cursor.getString(13));
                contact.set_intIdAbsenUser(cursor.getColumnName(14));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPurchaseOrderHeaderData> getAllDataNotSync(SQLiteDatabase db){
        List<tPurchaseOrderHeaderData> contactList = null;
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSync + " = 0 ";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tPurchaseOrderHeaderData>();
            do {
                tPurchaseOrderHeaderData contact = new tPurchaseOrderHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_OutletCode(cursor.getString(1));
                contact.set_OutletName(cursor.getString(2));
                contact.set_dtDate(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_intSumAmount(cursor.getString(6));
                contact.set_intSumItem(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_txtRoleId(cursor.getString(10));
                contact.set_intSync(cursor.getString(11));
                contact.set_txtBranchCode(cursor.getString(12));
                contact.set_txtBranchName(cursor.getString(13));
                contact.set_intIdAbsenUser(cursor.getColumnName(14));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }


    public List<tPurchaseOrderHeaderData> getAllDataBYIdAbsen(SQLiteDatabase db, String id){
        List<tPurchaseOrderHeaderData> contactList = null;
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intIdAbsenUser + " = '" + id + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tPurchaseOrderHeaderData>();
            do {
                tPurchaseOrderHeaderData contact = new tPurchaseOrderHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_OutletCode(cursor.getString(1));
                contact.set_OutletName(cursor.getString(2));
                contact.set_dtDate(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_intSumAmount(cursor.getString(6));
                contact.set_intSumItem(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_txtRoleId(cursor.getString(10));
                contact.set_intSync(cursor.getString(11));
                contact.set_txtBranchCode(cursor.getString(12));
                contact.set_txtBranchName(cursor.getString(13));
                contact.set_intIdAbsenUser(cursor.getColumnName(14));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPurchaseOrderHeaderData> getAllDataByIntSync(SQLiteDatabase db, String int_sync){
        List<tPurchaseOrderHeaderData> contactList = null;
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSync + " ='" + int_sync + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tPurchaseOrderHeaderData>();
            do {
                tPurchaseOrderHeaderData contact = new tPurchaseOrderHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_OutletCode(cursor.getString(1));
                contact.set_OutletName(cursor.getString(2));
                contact.set_dtDate(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_intSumAmount(cursor.getString(6));
                contact.set_intSumItem(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_txtRoleId(cursor.getString(10));
                contact.set_intSync(cursor.getString(11));
                contact.set_txtBranchCode(cursor.getString(12));
                contact.set_txtBranchName(cursor.getString(13));
                contact.set_intIdAbsenUser(cursor.getColumnName(14));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPurchaseOrderHeaderData> getAllDataByIntSyncAndOutlet(SQLiteDatabase db, String int_sync, String outlet){
        List<tPurchaseOrderHeaderData> contactList = null;
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSync + " ='" + int_sync + "' AND " + dt.Property_OutletCode + " ='"+ outlet +"'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tPurchaseOrderHeaderData>();
            do {
                tPurchaseOrderHeaderData contact = new tPurchaseOrderHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_OutletCode(cursor.getString(1));
                contact.set_OutletName(cursor.getString(2));
                contact.set_dtDate(cursor.getString(3));
                contact.set_txtKeterangan(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_intSumAmount(cursor.getString(6));
                contact.set_intSumItem(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_txtRoleId(cursor.getString(10));
                contact.set_intSync(cursor.getString(11));
                contact.set_txtBranchCode(cursor.getString(12));
                contact.set_txtBranchName(cursor.getString(13));
                contact.set_intIdAbsenUser(cursor.getColumnName(14));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPurchaseOrderHeaderData> getAllDataByOutletCode(SQLiteDatabase db, String code){
        List<tPurchaseOrderHeaderData> contactList = null;
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_OutletCode + " ='" + code + "'" + " ORDER BY txtNoOrder DESC";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tPurchaseOrderHeaderData>();
            do {
                tPurchaseOrderHeaderData contact = new tPurchaseOrderHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtNoOrder(cursor.getString(1));
                contact.set_dtDate(cursor.getString(2));
                contact.set_OutletCode(cursor.getString(3));
                contact.set_OutletName(cursor.getString(4));
                contact.set_txtKeterangan(cursor.getString(5));
                contact.set_intSumItem(cursor.getString(6));
                contact.set_intSumAmount(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getColumnName(13));
                contact.set_txtNIK(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPurchaseOrderHeaderData> getAllDataByNoOrder(SQLiteDatabase db, String NoOrder){
        List<tPurchaseOrderHeaderData> contactList = null;
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_txtNoOrder + " ='" + NoOrder + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tPurchaseOrderHeaderData>();
            do {
                tPurchaseOrderHeaderData contact = new tPurchaseOrderHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtNoOrder(cursor.getString(1));
                contact.set_dtDate(cursor.getString(2));
                contact.set_OutletCode(cursor.getString(3));
                contact.set_OutletName(cursor.getString(4));
                contact.set_txtKeterangan(cursor.getString(5));
                contact.set_intSumItem(cursor.getString(6));
                contact.set_intSumAmount(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtNIK(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tPurchaseOrderHeaderData> getAllDataByDateCheckin(SQLiteDatabase db, String date){
        List<tPurchaseOrderHeaderData> contactList = null;
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_txtDate + " ='" + date + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tPurchaseOrderHeaderData>();
            do {
                tPurchaseOrderHeaderData contact = new tPurchaseOrderHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtNoOrder(cursor.getString(1));
                contact.set_dtDate(cursor.getString(2));
                contact.set_OutletCode(cursor.getString(3));
                contact.set_OutletName(cursor.getString(4));
                contact.set_txtKeterangan(cursor.getString(5));
                contact.set_intSumItem(cursor.getString(6));
                contact.set_intSumAmount(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtNIK(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public void deleteContactPO(SQLiteDatabase db, String id){
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        db.delete(TABLE_CONTACTS, dt.Property_intId + " = ?", new String[]{String.valueOf(id)});
    }

    public void deleteAllDataPO(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }

    public int getContactCountPO(SQLiteDatabase db){
        String countQuery = "SELECT 1 FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        int index = cursor.getCount();
        cursor.close();
        return index;
    }

    public List<tPurchaseOrderHeaderData> getLastDataPO(SQLiteDatabase db){
        List<tPurchaseOrderHeaderData> contactList = null;
        tPurchaseOrderHeaderData dt = new tPurchaseOrderHeaderData();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToLast()){
            contactList = new ArrayList<tPurchaseOrderHeaderData>();
            do {
                tPurchaseOrderHeaderData contact = new tPurchaseOrderHeaderData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtNoOrder(cursor.getString(1));
                contact.set_dtDate(cursor.getString(2));
                contact.set_OutletCode(cursor.getString(3));
                contact.set_OutletName(cursor.getString(4));
                contact.set_txtKeterangan(cursor.getString(5));
                contact.set_intSumItem(cursor.getString(6));
                contact.set_intSumAmount(cursor.getString(7));
                contact.set_UserId(cursor.getString(8));
                contact.set_intSubmit(cursor.getString(9));
                contact.set_intSync(cursor.getString(10));
                contact.set_txtBranchCode(cursor.getString(11));
                contact.set_txtBranchName(cursor.getString(12));
                contact.set_intIdAbsenUser(cursor.getString(13));
                contact.set_txtNIK(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
