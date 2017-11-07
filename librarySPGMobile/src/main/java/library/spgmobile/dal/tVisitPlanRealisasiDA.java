package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tLeaveMobileData;
import library.spgmobile.common.tVisitPlanRealisasiData;

/**
 * Created by Robert on 26/04/2017.
 */

public class tVisitPlanRealisasiDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tVisitplanRealisasi;

    public tVisitPlanRealisasiDA(SQLiteDatabase db) {
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_txtDataIDRealisasi + " TEXT PRIMARY KEY,"
                + dt.Property_intCategoryVisitPlan + " TEXT NULL,"
                + dt.Property_intDetailID + " TEXT NULL,"
                + dt.Property_intHeaderID + " TEXT NULL,"
                + dt.Property_intUserID + " TEXT NULL,"
                + dt.Property_txtOutletCode + " TEXT NULL,"
                + dt.Property_txtOutletName + " TEXT NULL,"
                + dt.Property_txtBranchCode + " TEXT NULL,"
                + dt.Property_txtBranchName + " TEXT NULL,"
                + dt.Property_dtDate + " TEXT NULL,"
                + dt.Property_intBobot + " TEXT NULL,"
                + dt.Property_dtDateRealisasi + " TEXT NULL,"
                + dt.Property_dtDateRealisasiDevice + " TEXT NULL,"
                + dt.Property_txtDesc + " TEXT NULL,"
                + dt.Property_txtDescReply + " TEXT NULL,"
                + dt.Property_dtPhoto1 + " BLOB NULL,"
                + dt.Property_dtPhoto2 + " BLOB NULL,"
                + dt.Property_txtLong + " TEXT NULL,"
                + dt.Property_txtLat + " TEXT NULL,"
                + dt.Property_txtAcc + " TEXT NULL,"
                + dt.Property_txtLongSource + " TEXT NULL,"
                + dt.Property_txtLatSource + " TEXT NULL,"
                + dt.Property_intDistance + " TEXT NULL,"
                + dt.Property_bitActive + " TEXT NULL,"
                + dt.Property_txtRoleId + " TEXT NULL,"
                + dt.Property_deviceId + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL,"
                + dt.Property_intPush + " TEXT NULL,"
                + dt.Property_intCheckOut + " TEXT NULL,"
                + dt.Property_dateCheckOut + " TEXT NULL"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    public void DropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    public tVisitPlanRealisasiData getDataCheckInActive(SQLiteDatabase db) {
        // Select All Query
        tVisitPlanRealisasiData dt=new tVisitPlanRealisasiData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" Where "+dt.Property_dateCheckOut+" in ('null','') AND intSubmit='1'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        tVisitPlanRealisasiData contact = new tVisitPlanRealisasiData();;
        // looping through all rows and adding to list
        //query nya ga dpt kadangan ah pusing
        if (cursor.moveToFirst()) {
            do {
                contact.set_txtDataIDRealisasi(cursor.getString(0));
                contact.set_intCategoryVisitPlan(cursor.getString(1));
                contact.set_intDetailID(cursor.getString(2));
                contact.set_intHeaderID(cursor.getString(3));
                contact.set_intUserID(cursor.getString(4));
                contact.set_txtOutletCode(cursor.getString(5));
                contact.set_txtOutletName(cursor.getString(6));
                contact.set_txtBranchCode(cursor.getString(7));
                contact.set_txtBranchName(cursor.getString(8));
                contact.set_dtDate(cursor.getString(9));
                contact.set_intBobot(cursor.getString(10));
                contact.set_dtDateRealisasi(cursor.getString(11));
                contact.set_dtDateRealisasiDevice(cursor.getString(12));
                contact.set_txtDesc(cursor.getString(13));
                contact.set_txtDescReply(cursor.getString(14));
                byte[] blob1 = cursor.getBlob(15);
                contact.set_dtPhoto1(blob1);
                byte[] blob2 = cursor.getBlob(16);
                contact.set_dtPhoto2(blob2);
                contact.set_txtLong(cursor.getString(17));
                contact.set_txtLat(cursor.getString(18));
                contact.set_txtAcc(cursor.getString(19));
                contact.set_txtLongSource(cursor.getString(20));
                contact.set_txtLatSource(cursor.getString(21));
                contact.set_intDistance(cursor.getString(22));
                contact.set_bitActive(cursor.getString(23));
                contact.set_txtRoleId(cursor.getString(24));
                contact.set_deviceId(cursor.getString(25));
                contact.set_intSubmit(cursor.getString(26));
                contact.set_intPush(cursor.getString(27));
                contact.set_intCheckout(cursor.getString(28));
                contact.set_dateCheckout(cursor.getString(29));
                // Adding contact to list
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contact;
    }

    public void SaveDatatVisitPlan_MobileData(SQLiteDatabase db, tVisitPlanRealisasiData data) {
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        data.set_intSubmit("1");
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " (" + dt.Property_txtDataIDRealisasi + ","
                + dt.Property_intCategoryVisitPlan + ","
                + dt.Property_intDetailID + ","
                + dt.Property_intHeaderID + ","
                + dt.Property_intUserID + ","
                + dt.Property_txtOutletCode + ","
                + dt.Property_txtOutletName + ","
                + dt.Property_txtBranchCode + ","
                + dt.Property_txtBranchName + ","
                + dt.Property_dtDate + ","
                + dt.Property_intBobot + ","
                + dt.Property_dtDateRealisasi + ","
                + dt.Property_dtDateRealisasiDevice + ","
                + dt.Property_txtDesc + ","
                + dt.Property_txtDescReply + ","
                + dt.Property_dtPhoto1 + ","
                + dt.Property_dtPhoto2 + ","
                + dt.Property_txtLong + ","
                + dt.Property_txtLat + ","
                + dt.Property_txtAcc + ","
                + dt.Property_txtLongSource + ","
                + dt.Property_txtLatSource + ","
                + dt.Property_intDistance + ","
                + dt.Property_bitActive + ","
                + dt.Property_txtRoleId + ","
                + dt.Property_deviceId + ","
                + dt.Property_intSubmit + ","
                + dt.Property_intPush + ","
                + dt.Property_intCheckOut + ","
                + dt.Property_dateCheckOut +") " +
                "values('" + String.valueOf(data.get_txtDataIDRealisasi()) + "','"
                + String.valueOf(data.get_intDetailID()) + "','"
                + String.valueOf(data.get_intCategoryVisitPlan()) + "','"
                + String.valueOf(data.get_intHeaderID()) + "','"
                + String.valueOf(data.get_intUserID()) + "','"
                + String.valueOf(data.get_txtOutletCode()) + "','"
                + String.valueOf(data.get_txtOutletName()) + "','"
                + String.valueOf(data.get_txtBranchCode()) + "','"
                + String.valueOf(data.get_txtBranchName()) + "','"
                + String.valueOf(data.get_dtDate()) + "','"
                + String.valueOf(data.get_intBobot()) + "','"
                + String.valueOf(data.get_dtDateRealisasi()) + "','"
                + String.valueOf(data.get_dtDateRealisasiDevice()) + "','"
                + String.valueOf(data.get_txtDesc()) + "','"
                + String.valueOf(data.get_txtDescReply()) + "','"
                + String.valueOf(data.get_dtPhoto1()) + "','"
                + String.valueOf(data.get_dtPhoto2()) + "','"
                + String.valueOf(data.get_txtLong()) + "','"
                + String.valueOf(data.get_txtLat()) + "','"
                + String.valueOf(data.get_txtAcc()) + "','"
                + String.valueOf(data.get_txtLongSource()) + "','"
                + String.valueOf(data.get_txtLatSource()) + "','"
                + String.valueOf(data.get_intDistance()) + "','"
                + String.valueOf(data.get_bitActive()) + "','"
                + String.valueOf(data.get_txtRoleId()) + "','"
                + String.valueOf(data.get_deviceId()) + "','"
                + String.valueOf(data.get_intSubmit()) + "','"
                + String.valueOf(data.get_intPush()) + "','"
                + String.valueOf(data.get_intCheckout()) + "','"
                + String.valueOf(data.get_dateCheckout()) + "')");
    }
    public void UpdateDatatVisitPlan_MobileData(SQLiteDatabase db, tVisitPlanRealisasiData data) {
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_dtDateRealisasi,data.get_dtDateRealisasi()); //These Fields should be your String values of actual column names
        cv.put(dt.Property_dtDateRealisasiDevice,data.get_dtDateRealisasiDevice());
        cv.put(dt.Property_txtDescReply,data.get_txtDescReply());
        cv.put(dt.Property_dtPhoto1,data.get_dtPhoto1());
        cv.put(dt.Property_dtPhoto2,data.get_dtPhoto2());
        cv.put(dt.Property_txtLong,data.get_txtLong());
        cv.put(dt.Property_txtLat,data.get_txtLat());
        cv.put(dt.Property_txtAcc,data.get_txtAcc());
        cv.put(dt.Property_intDistance,data.get_intDistance());
        cv.put(dt.Property_txtRoleId,data.get_txtRoleId());
        cv.put(dt.Property_deviceId,data.get_deviceId());
        cv.put(dt.Property_intSubmit,data.get_intSubmit());
        if (data.get_intPush()!=null){
            cv.put(dt.Property_intPush,data.get_intPush());
        }
        if (data.get_dateCheckout()!=null){
            cv.put(dt.Property_dateCheckOut,data.get_dateCheckout());
        }
        db.update(TABLE_CONTACTS, cv, dt.Property_txtDataIDRealisasi+"='"+data.get_txtDataIDRealisasi()+"'", null);
    }
    public void UpdatePushVisitPlan_MobileData(SQLiteDatabase db, tVisitPlanRealisasiData data) {
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_intPush,data.get_intPush()); //These Fields should be your String values of actual column names
        db.update(TABLE_CONTACTS, cv, dt.Property_txtDataIDRealisasi+"='"+data.get_txtDataIDRealisasi()+"'", null);
    }
    public void insertDataDownload(SQLiteDatabase db,tVisitPlanRealisasiData data){
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " (" + dt.Property_txtDataIDRealisasi + ","
                + dt.Property_intCategoryVisitPlan + ","
                + dt.Property_intDetailID + ","
                + dt.Property_intHeaderID + ","
                + dt.Property_intUserID + ","
                + dt.Property_txtOutletCode + ","
                + dt.Property_txtOutletName + ","
                + dt.Property_txtBranchCode + ","
                + dt.Property_txtBranchName + ","
                + dt.Property_dtDate + ","
                + dt.Property_intBobot + ","
                + dt.Property_dtDateRealisasi + ","
                + dt.Property_dtDateRealisasiDevice + ","
                + dt.Property_txtDesc + ","
                + dt.Property_txtDescReply + ","
                + dt.Property_dtPhoto1 + ","
                + dt.Property_dtPhoto2 + ","
                + dt.Property_txtLong + ","
                + dt.Property_txtLat + ","
                + dt.Property_txtAcc + ","
                + dt.Property_txtLongSource + ","
                + dt.Property_txtLatSource + ","
                + dt.Property_intDistance + ","
                + dt.Property_bitActive + ","
                + dt.Property_txtRoleId + ","
                + dt.Property_deviceId + ","
                + dt.Property_intSubmit + ","
                + dt.Property_intPush + ","
                + dt.Property_intCheckOut + ","
                + dt.Property_dateCheckOut +") " +
                "values('" + String.valueOf(data.get_txtDataIDRealisasi()) + "','"
                + String.valueOf(data.get_intDetailID()) + "','"
                + String.valueOf(data.get_intCategoryVisitPlan()) + "','"
                + String.valueOf(data.get_intHeaderID()) + "','"
                + String.valueOf(data.get_intUserID()) + "','"
                + String.valueOf(data.get_txtOutletCode()) + "','"
                + String.valueOf(data.get_txtOutletName()) + "','"
                + String.valueOf(data.get_txtBranchCode()) + "','"
                + String.valueOf(data.get_txtBranchName()) + "','"
                + String.valueOf(data.get_dtDate()) + "','"
                + String.valueOf(data.get_intBobot()) + "','"
                + String.valueOf(data.get_dtDateRealisasi()) + "','"
                + String.valueOf(data.get_dtDateRealisasiDevice()) + "','"
                + String.valueOf(data.get_txtDesc()) + "','"
                + String.valueOf(data.get_txtDescReply()) + "','"
                + data.get_dtPhoto1() + "','"
                + data.get_dtPhoto2() + "','"
                + String.valueOf(data.get_txtLong()) + "','"
                + String.valueOf(data.get_txtLat()) + "','"
                + String.valueOf(data.get_txtAcc()) + "','"
                + String.valueOf(data.get_txtLongSource()) + "','"
                + String.valueOf(data.get_txtLatSource()) + "','"
                + String.valueOf(data.get_intDistance()) + "','"
                + String.valueOf(data.get_bitActive()) + "','"
                + String.valueOf(data.get_txtRoleId()) + "','"
                + String.valueOf(data.get_deviceId()) + "','"
                + String.valueOf(data.get_intSubmit()) + "','"
                + String.valueOf(data.get_intPush()) + "','"
                + String.valueOf(data.get_intCheckout()) + "','"
                + String.valueOf(data.get_dateCheckout()) + "')");
    }
    public void DownloadDatatVisitPlan_MobileData(SQLiteDatabase db, tVisitPlanRealisasiData data) {
        tVisitPlanRealisasiData dataSql = dataSql = getDataByDataIDRealisasi(db,data.get_txtDataIDRealisasi());
        if (dataSql.get_txtDataIDRealisasi()!=null){
            if (!dataSql.get_intSubmit().toString().equals("1")){
                insertDataDownload(db,data);
            }
        }else{
            insertDataDownload(db,data);
        }
    }
    public List<tVisitPlanRealisasiData> getPushData(SQLiteDatabase db) {
        List<tVisitPlanRealisasiData> contactList = null;

        String _tVisitPlanRealisasiData = "()";

        /*if (ListOftVisitPlanRealisasiData != null) {
            _tVisitPlanRealisasiData = "(";
            for (int i = 0; i < ListOftVisitPlanRealisasiData.size(); i++) {
                _tVisitPlanRealisasiData = _tVisitPlanRealisasiData + "'" + ListOftVisitPlanRealisasiData.get(i).get_intHeaderId() + "'";

                _tVisitPlanRealisasiData = _tVisitPlanRealisasiData + ((i + 1) != ListOftVisitPlanRealisasiData.size() ? "," : ")");
            }
//			tSalesProductHeader = tSalesProductHeader + "";
        }*/

        // Select All Query
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
//        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intHeaderID + " IN " + _tVisitPlanRealisasiData;
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + "='1' AND " + dt.Property_intPush + "='0'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tVisitPlanRealisasiData>();
            do {
                tVisitPlanRealisasiData contact = new tVisitPlanRealisasiData();
                contact.set_txtDataIDRealisasi(cursor.getString(0));
                contact.set_intCategoryVisitPlan(cursor.getString(1));
                contact.set_intDetailID(cursor.getString(2));
                contact.set_intHeaderID(cursor.getString(3));
                contact.set_intUserID(cursor.getString(4));
                contact.set_txtOutletCode(cursor.getString(5));
                contact.set_txtOutletName(cursor.getString(6));
                contact.set_txtBranchCode(cursor.getString(7));
                contact.set_txtBranchName(cursor.getString(8));
                contact.set_dtDate(cursor.getString(9));
                contact.set_intBobot(cursor.getString(10));
                contact.set_dtDateRealisasi(cursor.getString(11));
                contact.set_dtDateRealisasiDevice(cursor.getString(12));
                contact.set_txtDesc(cursor.getString(13));
                contact.set_txtDescReply(cursor.getString(14));
                byte[] blob1 = cursor.getBlob(15);
                contact.set_dtPhoto1(blob1);
                byte[] blob2 = cursor.getBlob(16);
                contact.set_dtPhoto2(blob2);
                contact.set_txtLong(cursor.getString(17));
                contact.set_txtLat(cursor.getString(18));
                contact.set_txtAcc(cursor.getString(19));
                contact.set_txtLongSource(cursor.getString(20));
                contact.set_txtLatSource(cursor.getString(21));
                contact.set_intDistance(cursor.getString(22));
                contact.set_bitActive(cursor.getString(23));
                contact.set_txtRoleId(cursor.getString(24));
                contact.set_deviceId(cursor.getString(25));
                contact.set_intSubmit(cursor.getString(26));
                contact.set_intPush(cursor.getString(27));
                contact.set_intCheckout(cursor.getString(28));
                contact.set_dateCheckout(cursor.getString(29));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public int getAllCheckPushData(SQLiteDatabase db) {
        List<tVisitPlanRealisasiData> contactList = null;
        // Select All Query
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        String selectQuery = "SELECT  1 FROM "
                + TABLE_CONTACTS +" WHERE " +dt.Property_intPush +" ='0' And "+dt.Property_intSubmit+"=1" ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // return count
        int index = cursor.getCount();
        cursor.close();
        return index;
    }

    public List<tVisitPlanRealisasiData> getAllDataActiveOrderByDate(SQLiteDatabase db) {
        List<tVisitPlanRealisasiData> contactList = null;
        // Select All Query
        tVisitPlanRealisasiData dt=new tVisitPlanRealisasiData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE " + dt.Property_intSubmit+"=1" + " ORDER  BY dateCheckout DESC";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<tVisitPlanRealisasiData>();
            do {
                tVisitPlanRealisasiData contact = new tVisitPlanRealisasiData();
                contact.set_txtDataIDRealisasi(cursor.getString(0));
                contact.set_intCategoryVisitPlan(cursor.getString(1));
                contact.set_intDetailID(cursor.getString(2));
                contact.set_intHeaderID(cursor.getString(3));
                contact.set_intUserID(cursor.getString(4));
                contact.set_txtOutletCode(cursor.getString(5));
                contact.set_txtOutletName(cursor.getString(6));
                contact.set_txtBranchCode(cursor.getString(7));
                contact.set_txtBranchName(cursor.getString(8));
                contact.set_dtDate(cursor.getString(9));
                contact.set_intBobot(cursor.getString(10));
                contact.set_dtDateRealisasi(cursor.getString(11));
                contact.set_dtDateRealisasiDevice(cursor.getString(12));
                contact.set_txtDesc(cursor.getString(13));
                contact.set_txtDescReply(cursor.getString(14));
                byte[] blob1 = cursor.getBlob(15);
                contact.set_dtPhoto1(blob1);
                byte[] blob2 = cursor.getBlob(16);
                contact.set_dtPhoto2(blob2);
                contact.set_txtLong(cursor.getString(17));
                contact.set_txtLat(cursor.getString(18));
                contact.set_txtAcc(cursor.getString(19));
                contact.set_txtLongSource(cursor.getString(20));
                contact.set_txtLatSource(cursor.getString(21));
                contact.set_intDistance(cursor.getString(22));
                contact.set_bitActive(cursor.getString(23));
                contact.set_txtRoleId(cursor.getString(24));
                contact.set_deviceId(cursor.getString(25));
                contact.set_intSubmit(cursor.getString(26));
                contact.set_intPush(cursor.getString(27));
                contact.set_intCheckout(cursor.getString(28));
                contact.set_dateCheckout(cursor.getString(29));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tVisitPlanRealisasiData> getAllData(SQLiteDatabase db) {
        List<tVisitPlanRealisasiData> contactList = new ArrayList<tVisitPlanRealisasiData>();
        // Select All Query
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tVisitPlanRealisasiData contact = new tVisitPlanRealisasiData();
                contact.set_txtDataIDRealisasi(cursor.getString(0));
                contact.set_intCategoryVisitPlan(cursor.getString(1));
                contact.set_intDetailID(cursor.getString(2));
                contact.set_intHeaderID(cursor.getString(3));
                contact.set_intUserID(cursor.getString(4));
                contact.set_txtOutletCode(cursor.getString(5));
                contact.set_txtOutletName(cursor.getString(6));
                contact.set_txtBranchCode(cursor.getString(7));
                contact.set_txtBranchName(cursor.getString(8));
                contact.set_dtDate(cursor.getString(9));
                contact.set_intBobot(cursor.getString(10));
                contact.set_dtDateRealisasi(cursor.getString(11));
                contact.set_dtDateRealisasiDevice(cursor.getString(12));
                contact.set_txtDesc(cursor.getString(13));
                contact.set_txtDescReply(cursor.getString(14));
                byte[] blob1 = cursor.getBlob(15);
                contact.set_dtPhoto1(blob1);
                byte[] blob2 = cursor.getBlob(16);
                contact.set_dtPhoto2(blob2);
                contact.set_txtLong(cursor.getString(17));
                contact.set_txtLat(cursor.getString(18));
                contact.set_txtAcc(cursor.getString(19));
                contact.set_txtLongSource(cursor.getString(20));
                contact.set_txtLatSource(cursor.getString(21));
                contact.set_intDistance(cursor.getString(22));
                contact.set_bitActive(cursor.getString(23));
                contact.set_txtRoleId(cursor.getString(24));
                contact.set_deviceId(cursor.getString(25));
                contact.set_intSubmit(cursor.getString(26));
                contact.set_intPush(cursor.getString(27));
                contact.set_intCheckout(cursor.getString(28));
                contact.set_dateCheckout(cursor.getString(29));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tVisitPlanRealisasiData> getAllDataByIntSubmit(SQLiteDatabase db, String intSubmit) {
        List<tVisitPlanRealisasiData> contactList = new ArrayList<tVisitPlanRealisasiData>();
        // Select All Query
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit +" = "+ intSubmit;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tVisitPlanRealisasiData contact = new tVisitPlanRealisasiData();
                contact.set_txtDataIDRealisasi(cursor.getString(0));
                contact.set_intCategoryVisitPlan(cursor.getString(1));
                contact.set_intDetailID(cursor.getString(2));
                contact.set_intHeaderID(cursor.getString(3));
                contact.set_intUserID(cursor.getString(4));
                contact.set_txtOutletCode(cursor.getString(5));
                contact.set_txtOutletName(cursor.getString(6));
                contact.set_txtBranchCode(cursor.getString(7));
                contact.set_txtBranchName(cursor.getString(8));
                contact.set_dtDate(cursor.getString(9));
                contact.set_intBobot(cursor.getString(10));
                contact.set_dtDateRealisasi(cursor.getString(11));
                contact.set_dtDateRealisasiDevice(cursor.getString(12));
                contact.set_txtDesc(cursor.getString(13));
                contact.set_txtDescReply(cursor.getString(14));
                byte[] blob1 = cursor.getBlob(15);
                contact.set_dtPhoto1(blob1);
                byte[] blob2 = cursor.getBlob(16);
                contact.set_dtPhoto2(blob2);
                contact.set_txtLong(cursor.getString(17));
                contact.set_txtLat(cursor.getString(18));
                contact.set_txtAcc(cursor.getString(19));
                contact.set_txtLongSource(cursor.getString(20));
                contact.set_txtLatSource(cursor.getString(21));
                contact.set_intDistance(cursor.getString(22));
                contact.set_bitActive(cursor.getString(23));
                contact.set_txtRoleId(cursor.getString(24));
                contact.set_deviceId(cursor.getString(25));
                contact.set_intSubmit(cursor.getString(26));
                contact.set_intPush(cursor.getString(27));
                contact.set_intCheckout(cursor.getString(28));
                contact.set_dateCheckout(cursor.getString(29));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public tVisitPlanRealisasiData getDataByDataIDRealisasi(SQLiteDatabase db, String id) {
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        tVisitPlanRealisasiData contact = new tVisitPlanRealisasiData();
        String[] tableColumns = new String[]{
                dt.Property_txtDataIDRealisasi,
                dt.Property_intCategoryVisitPlan,
                dt.Property_intDetailID,
                dt.Property_intHeaderID,
                dt.Property_intUserID,
                dt.Property_txtOutletCode,
                dt.Property_txtOutletName,
                dt.Property_txtBranchCode,
                dt.Property_txtBranchName,
                dt.Property_dtDate,
                dt.Property_intBobot,
                dt.Property_dtDateRealisasi,
                dt.Property_dtDateRealisasiDevice,
                dt.Property_txtDesc,
                dt.Property_txtDescReply,
                dt.Property_dtPhoto1,
                dt.Property_dtPhoto2,
                dt.Property_txtLong,
                dt.Property_txtLat,
                dt.Property_txtAcc,
                dt.Property_txtLongSource,
                dt.Property_txtLatSource,
                dt.Property_intDistance,
                dt.Property_bitActive,
                dt.Property_txtRoleId,
                dt.Property_deviceId,
                dt.Property_intSubmit,
                dt.Property_intPush,
                dt.Property_intCheckOut,
                dt.Property_dateCheckOut
        };
        String whereClause = dt.Property_txtDataIDRealisasi + "=?";
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
//                tVisitPlanRealisasiData contact = new tVisitPlanRealisasiData();
                contact.set_txtDataIDRealisasi(cursor.getString(0));
                contact.set_intCategoryVisitPlan(cursor.getString(1));
                contact.set_intDetailID(cursor.getString(2));
                contact.set_intHeaderID(cursor.getString(3));
                contact.set_intUserID(cursor.getString(4));
                contact.set_txtOutletCode(cursor.getString(5));
                contact.set_txtOutletName(cursor.getString(6));
                contact.set_txtBranchCode(cursor.getString(7));
                contact.set_txtBranchName(cursor.getString(8));
                contact.set_dtDate(cursor.getString(9));
                contact.set_intBobot(cursor.getString(10));
                contact.set_dtDateRealisasi(cursor.getString(11));
                contact.set_dtDateRealisasiDevice(cursor.getString(12));
                contact.set_txtDesc(cursor.getString(13));
                contact.set_txtDescReply(cursor.getString(14));
                byte[] blob1 = cursor.getBlob(15);
                contact.set_dtPhoto1(blob1);
                byte[] blob2 = cursor.getBlob(16);
                contact.set_dtPhoto2(blob2);
                contact.set_txtLong(cursor.getString(17));
                contact.set_txtLat(cursor.getString(18));
                contact.set_txtAcc(cursor.getString(19));
                contact.set_txtLongSource(cursor.getString(20));
                contact.set_txtLatSource(cursor.getString(21));
                contact.set_intDistance(cursor.getString(22));
                contact.set_bitActive(cursor.getString(23));
                contact.set_txtRoleId(cursor.getString(24));
                contact.set_deviceId(cursor.getString(25));
                contact.set_intSubmit(cursor.getString(26));
                contact.set_intPush(cursor.getString(27));
                contact.set_intCheckout(cursor.getString(28));
                contact.set_dateCheckout(cursor.getString(29));
//                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contact;
    }

    public tVisitPlanRealisasiData getDataByDataIDOutlet(SQLiteDatabase db, String id) {
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        tVisitPlanRealisasiData contact = new tVisitPlanRealisasiData();
        String[] tableColumns = new String[]{
                dt.Property_txtDataIDRealisasi,
                dt.Property_intCategoryVisitPlan,
                dt.Property_intDetailID,
                dt.Property_intHeaderID,
                dt.Property_intUserID,
                dt.Property_txtOutletCode,
                dt.Property_txtOutletName,
                dt.Property_txtBranchCode,
                dt.Property_txtBranchName,
                dt.Property_dtDate,
                dt.Property_intBobot,
                dt.Property_dtDateRealisasi,
                dt.Property_dtDateRealisasiDevice,
                dt.Property_txtDesc,
                dt.Property_txtDescReply,
                dt.Property_dtPhoto1,
                dt.Property_dtPhoto2,
                dt.Property_txtLong,
                dt.Property_txtLat,
                dt.Property_txtAcc,
                dt.Property_txtLongSource,
                dt.Property_txtLatSource,
                dt.Property_intDistance,
                dt.Property_bitActive,
                dt.Property_txtRoleId,
                dt.Property_deviceId,
                dt.Property_intSubmit,
                dt.Property_intPush,
                dt.Property_intCheckOut,
                dt.Property_dateCheckOut
        };
        String whereClause = dt.Property_txtOutletCode + "=?";
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
//                tVisitPlanRealisasiData contact = new tVisitPlanRealisasiData();
                contact.set_txtDataIDRealisasi(cursor.getString(0));
                contact.set_intCategoryVisitPlan(cursor.getString(1));
                contact.set_intDetailID(cursor.getString(2));
                contact.set_intHeaderID(cursor.getString(3));
                contact.set_intUserID(cursor.getString(4));
                contact.set_txtOutletCode(cursor.getString(5));
                contact.set_txtOutletName(cursor.getString(6));
                contact.set_txtBranchCode(cursor.getString(7));
                contact.set_txtBranchName(cursor.getString(8));
                contact.set_dtDate(cursor.getString(9));
                contact.set_intBobot(cursor.getString(10));
                contact.set_dtDateRealisasi(cursor.getString(11));
                contact.set_dtDateRealisasiDevice(cursor.getString(12));
                contact.set_txtDesc(cursor.getString(13));
                contact.set_txtDescReply(cursor.getString(14));
                byte[] blob1 = cursor.getBlob(15);
                contact.set_dtPhoto1(blob1);
                byte[] blob2 = cursor.getBlob(16);
                contact.set_dtPhoto2(blob2);
                contact.set_txtLong(cursor.getString(17));
                contact.set_txtLat(cursor.getString(18));
                contact.set_txtAcc(cursor.getString(19));
                contact.set_txtLongSource(cursor.getString(20));
                contact.set_txtLatSource(cursor.getString(21));
                contact.set_intDistance(cursor.getString(22));
                contact.set_bitActive(cursor.getString(23));
                contact.set_txtRoleId(cursor.getString(24));
                contact.set_deviceId(cursor.getString(25));
                contact.set_intSubmit(cursor.getString(26));
                contact.set_intPush(cursor.getString(27));
                contact.set_intCheckout(cursor.getString(28));
                contact.set_dateCheckout(cursor.getString(29));
//                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contact;
    }
    public void DeleteAllData(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    // Deleting single contact
    public void deleteContact(SQLiteDatabase db, String id) {
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        db.delete(TABLE_CONTACTS, dt.Property_txtDataIDRealisasi + " = ? ",
                new String[] {String.valueOf(id)});
    }

    public void deleteByIDRealisasi(SQLiteDatabase db, String id){
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        String whereClause = dt.Property_txtDataIDRealisasi + " = ?";
        String[] whereArgs = new String[]{
                String.valueOf(id)
        };
        db.delete(TABLE_CONTACTS, whereClause, whereArgs);
    }
    public int checkoutSystem(SQLiteDatabase db, String id, String dTime) {
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();

        ContentValues values = new ContentValues();
        values.put(dt.Property_dateCheckOut, dTime);
        values.put(dt.Property_intPush, "0");
        // updating row
        return db.update(TABLE_CONTACTS, values, dt.Property_txtDataIDRealisasi + " = ? ",
                new String[] { String.valueOf(id) });
    }
    //getting data status submit
    public int countOutVisitStatusSubmit(SQLiteDatabase db, String code) {

        String selectQuery = "select coalesce(sum(1),0) from [tVisitplanRealisasi] where txtOutletCode='" + code + "' and intSubmit=1 and [intPush]=0";

        Cursor cursor = db.rawQuery(selectQuery, null);

        int count = 0;

        if (cursor.moveToFirst()) {
            do {
                count=Integer.valueOf(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return count;
    }

    //getting data status unsubmit
    public int countOutVisitStatusUnPush(SQLiteDatabase db, String code) {

        String selectQuery = "select coalesce(sum(1),0) from [tVisitplanRealisasi] where intSubmit=1 and [intPush]=0";

        Cursor cursor = db.rawQuery(selectQuery, null);

        int count = 0;

        if (cursor.moveToFirst()) {
            do {
                count=Integer.valueOf(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return count;
    }
    //getting count data push
    public int countOutVisitPush(SQLiteDatabase db,  String code) {
        String selectQuery = "select coalesce(sum(1),0) from [tVisitplanRealisasi] where intSubmit=1 and [intPush]=1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        int count = 0;

        if (cursor.moveToFirst()) {
            do {
                count=Integer.valueOf(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return count;
    }
    // Getting contacts Count
    public int getContactsCountSubmit(SQLiteDatabase db) {
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intSubmit +" = 1";
        Cursor cursor = db.rawQuery(countQuery, null);
        int num =cursor.getCount();
        cursor.close();

        // return count
        return num;
    }
    // Getting contacts Count
    public int getContactsCount(SQLiteDatabase db) {
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        int num =cursor.getCount();
        cursor.close();

        // return count
        return num;
    }
}
