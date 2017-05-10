package library.salesforce.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.tVisitPlanHeader_MobileData;
import library.salesforce.common.tVisitPlanRealisasiData;

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
                + dt.Property_dtDate + " INT NULL,"
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
                + dt.Property_intSubmit + " TEXT NULL,"
                + dt.Property_intPush + " TEXT NULL"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    public void DropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
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
                + dt.Property_intSubmit + ","
                + dt.Property_intPush +") " +
                "values('" + String.valueOf(data.get_txtDataIDRealisasi()) + "','"
                + String.valueOf(data.get_intDetailID()) + "','"
                + String.valueOf(data.get_intCategoryVisitPlan()) + "','"
                + String.valueOf(data.get_intHeaderID()) + "','"
                + String.valueOf(data.get_intUserID()) + "','"
                + String.valueOf(data.get_txtOutletCode()) + "','"
                + String.valueOf(data.get_txtOutletName()) + "','"
                + String.valueOf(data.get_txtBranchCode()) + "','"
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
                + String.valueOf(data.get_intSubmit()) + "','"
                + String.valueOf(data.get_intPush()) + "')");
    }
    public void UpdateDatatVisitPlan_MobileData(SQLiteDatabase db, tVisitPlanRealisasiData data) {
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        data.set_intSubmit("1");
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_dtDateRealisasi,data.get_dtDateRealisasi()); //These Fields should be your String values of actual column names
        cv.put(dt.Property_dtDateRealisasiDevice,data.get_dtDateRealisasiDevice());
        cv.put(dt.Property_txtDescReply,data.get_txtDescReply());
        cv.put(dt.Property_dtPhoto1,data.get_dtPhoto1());
        cv.put(dt.Property_dtPhoto2,data.get_dtPhoto2());
        cv.put(dt.Property_txtLong,data.get_txtLong());
        cv.put(dt.Property_txtLat,data.get_txtLat());
        cv.put(dt.Property_intDistance,data.get_intDistance());
        cv.put(dt.Property_txtRoleId,data.get_txtRoleId());
        cv.put(dt.Property_intSubmit,data.get_intSubmit());
        cv.put(dt.Property_intPush,data.get_intPush());
        db.update(TABLE_CONTACTS, cv, dt.Property_txtDataIDRealisasi+"='"+data.get_txtDataIDRealisasi()+"'", null);
    }
    public void DownloadDatatVisitPlan_MobileData(SQLiteDatabase db, tVisitPlanRealisasiData data) {
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        tVisitPlanRealisasiData dataSql = null;
//        if ()
        dataSql = getDataByDataIDRealisasi(db,data.get_txtDataIDRealisasi());
        if (dataSql.get_txtDataIDRealisasi()!=null){
                if (!dataSql.get_dtDateRealisasi().toString().equals("null")){
                    data.set_dtDateRealisasi(dataSql.get_dtDateRealisasi().toString());
                }
                if(!dataSql.get_dtDateRealisasiDevice().toString().equals("null")){
                    data.set_dtDateRealisasiDevice(dataSql.get_dtDateRealisasiDevice().toString());
                }
                if(!dataSql.get_txtDescReply().toString().equals("null")){
                    data.set_txtDescReply(dataSql.get_txtDescReply().toString());
                }
                if(dataSql.get_dtPhoto1()!= null){
                    data.set_dtPhoto1(dataSql.get_dtPhoto1());
                }
                if(!dataSql.get_dtPhoto2().toString().equals("null")){
                    data.set_dtPhoto2(dataSql.get_dtPhoto2());
                }
                if(!dataSql.get_txtLong().toString().equals("null")){
                    data.set_txtLong(dataSql.get_txtLong().toString());
                }
                if(!dataSql.get_txtLat().toString().equals("null")){
                    data.set_txtLat(dataSql.get_txtLat().toString());
                }
                if(!dataSql.get_intDistance().toString().equals("null")){
                    data.set_intDistance(dataSql.get_intDistance().toString());
                }
                if(!dataSql.get_txtRoleId().toString().equals("null")){
                    data.set_txtRoleId(dataSql.get_txtRoleId().toString());
                }
                if(!dataSql.get_intSubmit().toString().equals("null")){
                    data.set_intSubmit(dataSql.get_intSubmit().toString());
                }
                if(!dataSql.get_intPush().toString().equals("null")){
                    data.set_intPush(dataSql.get_intPush().toString());
                }

        }

        data.set_intSubmit("0");
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " (" + dt.Property_txtDataIDRealisasi + ","
                + dt.Property_intCategoryVisitPlan + ","
                + dt.Property_intDetailID + ","
                + dt.Property_intHeaderID + ","
                + dt.Property_intUserID + ","
                + dt.Property_txtOutletCode + ","
                + dt.Property_txtOutletName + ","
                + dt.Property_txtBranchCode + ","
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
                + dt.Property_intSubmit + ","
                + dt.Property_intPush +") " +
                "values('" + String.valueOf(data.get_txtDataIDRealisasi()) + "','"
                + String.valueOf(data.get_intDetailID()) + "','"
                + String.valueOf(data.get_intCategoryVisitPlan()) + "','"
                + String.valueOf(data.get_intHeaderID()) + "','"
                + String.valueOf(data.get_intUserID()) + "','"
                + String.valueOf(data.get_txtOutletCode()) + "','"
                + String.valueOf(data.get_txtOutletName()) + "','"
                + String.valueOf(data.get_txtBranchCode()) + "','"
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
                + String.valueOf(data.get_intSubmit()) + "','"
                + String.valueOf(data.get_intPush()) + "')");
    }
    public List<tVisitPlanRealisasiData> getPushData(SQLiteDatabase db, List<tVisitPlanHeader_MobileData> ListOftVisitPlanRealisasiData) {
        List<tVisitPlanRealisasiData> contactList = null;

        String _tVisitPlanRealisasiData = "()";

        if (ListOftVisitPlanRealisasiData != null) {
            _tVisitPlanRealisasiData = "(";
            for (int i = 0; i < ListOftVisitPlanRealisasiData.size(); i++) {
                _tVisitPlanRealisasiData = _tVisitPlanRealisasiData + "'" + ListOftVisitPlanRealisasiData.get(i).get_intHeaderId() + "'";

                _tVisitPlanRealisasiData = _tVisitPlanRealisasiData + ((i + 1) != ListOftVisitPlanRealisasiData.size() ? "," : ")");
            }
//			tSalesProductHeader = tSalesProductHeader + "";
        }

        // Select All Query
        tVisitPlanRealisasiData dt = new tVisitPlanRealisasiData();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intHeaderID + " IN " + _tVisitPlanRealisasiData;

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
                contact.set_dtDate(cursor.getString(8));
                contact.set_intBobot(cursor.getString(9));
                contact.set_dtDateRealisasi(cursor.getString(10));
                contact.set_dtDateRealisasiDevice(cursor.getString(11));
                contact.set_txtDesc(cursor.getString(12));
                contact.set_txtDescReply(cursor.getString(13));
                contact.set_dtPhoto1(cursor.getBlob(14));
                contact.set_dtPhoto2(cursor.getBlob(15));
                contact.set_txtLong(cursor.getString(16));
                contact.set_txtLat(cursor.getString(17));
                contact.set_txtAcc(cursor.getString(18));
                contact.set_txtLongSource(cursor.getString(19));
                contact.set_txtLatSource(cursor.getString(20));
                contact.set_intDistance(cursor.getString(21));
                contact.set_bitActive(cursor.getString(22));
                contact.set_txtRoleId(cursor.getString(23));
                contact.set_intSubmit(cursor.getString(24));
                contact.set_intPush(cursor.getString(25));
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
                contact.set_dtDate(cursor.getString(8));
                contact.set_intBobot(cursor.getString(9));
                contact.set_dtDateRealisasi(cursor.getString(10));
                contact.set_dtDateRealisasiDevice(cursor.getString(11));
                contact.set_txtDesc(cursor.getString(12));
                contact.set_txtDescReply(cursor.getString(13));
                contact.set_dtPhoto1(cursor.getBlob(14));
                contact.set_dtPhoto2(cursor.getBlob(15));
                contact.set_txtLong(cursor.getString(16));
                contact.set_txtLat(cursor.getString(17));
                contact.set_txtAcc(cursor.getString(18));
                contact.set_txtLongSource(cursor.getString(19));
                contact.set_txtLatSource(cursor.getString(20));
                contact.set_intDistance(cursor.getString(21));
                contact.set_bitActive(cursor.getString(22));
                contact.set_txtRoleId(cursor.getString(23));
                contact.set_intSubmit(cursor.getString(24));
                contact.set_intPush(cursor.getString(25));
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
                dt.Property_intSubmit,
                dt.Property_intPush
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
                contact.set_dtDate(cursor.getString(8));
                contact.set_intBobot(cursor.getString(9));
                contact.set_dtDateRealisasi(cursor.getString(10));
                contact.set_dtDateRealisasiDevice(cursor.getString(11));
                contact.set_txtDesc(cursor.getString(12));
                contact.set_txtDescReply(cursor.getString(13));
                contact.set_dtPhoto1(cursor.getBlob(14));
                contact.set_dtPhoto2(cursor.getBlob(15));
                contact.set_txtLong(cursor.getString(16));
                contact.set_txtLat(cursor.getString(17));
                contact.set_txtAcc(cursor.getString(18));
                contact.set_txtLongSource(cursor.getString(19));
                contact.set_txtLatSource(cursor.getString(20));
                contact.set_intDistance(cursor.getString(21));
                contact.set_bitActive(cursor.getString(22));
                contact.set_txtRoleId(cursor.getString(23));
                contact.set_intSubmit(cursor.getString(24));
                contact.set_intPush(cursor.getString(25));
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
                dt.Property_intSubmit,
                dt.Property_intPush
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
                contact.set_dtDate(cursor.getString(8));
                contact.set_intBobot(cursor.getString(9));
                contact.set_dtDateRealisasi(cursor.getString(10));
                contact.set_dtDateRealisasiDevice(cursor.getString(11));
                contact.set_txtDesc(cursor.getString(12));
                contact.set_txtDescReply(cursor.getString(13));
                contact.set_dtPhoto1(cursor.getBlob(14));
                contact.set_dtPhoto2(cursor.getBlob(15));
                contact.set_txtLong(cursor.getString(16));
                contact.set_txtLat(cursor.getString(17));
                contact.set_txtAcc(cursor.getString(18));
                contact.set_txtLongSource(cursor.getString(19));
                contact.set_txtLatSource(cursor.getString(20));
                contact.set_intDistance(cursor.getString(21));
                contact.set_bitActive(cursor.getString(22));
                contact.set_txtRoleId(cursor.getString(23));
                contact.set_intSubmit(cursor.getString(24));
                contact.set_intPush(cursor.getString(25));
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
}
