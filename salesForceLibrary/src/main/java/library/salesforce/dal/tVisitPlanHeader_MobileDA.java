package library.salesforce.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.tVisitPlanHeader_MobileData;

/**
 * Created by Robert on 26/04/2017.
 */

public class tVisitPlanHeader_MobileDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tVisitPlanHeader_Mobile;

    public tVisitPlanHeader_MobileDA(SQLiteDatabase db){
            tVisitPlanHeader_MobileData dt = new tVisitPlanHeader_MobileData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_intHeaderId + " TEXT PRIMARY KEY,"
                + dt.Property_txtUserId + " TEXT NULL,"
                + dt.Property_txtPeriode + " TEXT NULL,"
                + dt.Property_intUnplan + " TEXT NULL,"
                + dt.Property_intGuIdUnplan + " TEXT NULL,"
                + dt.Property_txtBranchCode + " TEXT NULL,"
                + dt.Property_bitActive + " TEXT NULL,"
                + dt.Property_dtStart + " TEXT NULL,"
                + dt.Property_dtEnd + " TEXT NULL,"
                + dt.Property_intSumBobot + " TEXT NULL,"
                + dt.Property_intRealisasi + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL,"
                + dt.Property_intPush + " TEXT NULL"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_CONTACTS);
    }
    /*public void UpdateDatatVisitPlan_MobileData(SQLiteDatabase db, tVisitPlanHeader_MobileData data) {
        tVisitPlanHeader_MobileData dt = new tVisitPlanHeader_MobileData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_intSubmit,data.get_intSubmit());
        db.update(TABLE_CONTACTS, cv, dt.Property_intHeaderId+"='"+data.get_intHeaderId()+"'", null);
    }*/

    public void SaveDatatVisitPlanHeader_MobileData(SQLiteDatabase db, tVisitPlanHeader_MobileData data){
        tVisitPlanHeader_MobileData dt = new tVisitPlanHeader_MobileData();
        db.execSQL("INSERT OR REPLACE into "+TABLE_CONTACTS+" ("+dt.Property_intHeaderId+ ","
                +dt.Property_txtUserId+ ","
                +dt.Property_txtPeriode+ ","
                +dt.Property_intUnplan+ ","
                +dt.Property_intGuIdUnplan+ ","
                +dt.Property_txtBranchCode+ ","
                +dt.Property_bitActive+ ","
                +dt.Property_dtStart+ ","
                +dt.Property_dtEnd+ ","
                +dt.Property_intSumBobot+ ","
                +dt.Property_intRealisasi+ ","
                +dt.Property_intSubmit+ ","
                +dt.Property_intPush+ ") "+
                "values('" +String.valueOf(data.get_intHeaderId())+"','"
                +String.valueOf(data.get_txtUserId())+"','"
                +String.valueOf(data.get_txtPeriode())+"','"
                +String.valueOf(data.get_intUnplan())+"','"
                +String.valueOf(data.get_intGuIdUnplan())+"','"
                +String.valueOf(data.get_txtBranchCode())+"','"
                +String.valueOf(data.get_bitActive())+"','"
                +String.valueOf(data.get_dtStart())+"','"
                +String.valueOf(data.get_dtEnd())+"','"
                +String.valueOf(data.get_intSumBobot())+"','"
                +String.valueOf(data.get_intRealisasi())+"','"
                +String.valueOf(data.get_intSubmit())+"','"
                +String.valueOf(data.get_intPush())+"')");
    }

    public List<tVisitPlanHeader_MobileData> getAllDataByHeaderId(SQLiteDatabase db, String id) {
        tVisitPlanHeader_MobileData dt = new tVisitPlanHeader_MobileData();
        List<tVisitPlanHeader_MobileData> contactList = new ArrayList<tVisitPlanHeader_MobileData>();
        String[] tableColumns = new String[] {
                dt.Property_intHeaderId,
                dt.Property_txtUserId,
                dt.Property_txtPeriode,
                dt.Property_intUnplan,
                dt.Property_intGuIdUnplan,
                dt.Property_txtBranchCode,
                dt.Property_bitActive,
                dt.Property_dtStart,
                dt.Property_dtEnd,
                dt.Property_intSumBobot,
                dt.Property_intRealisasi,
                dt.Property_intSubmit,
                dt.Property_intPush
        };
        String whereClause = dt.Property_intHeaderId + "=?";
        String[] whereArgs = new String[] {
                String.valueOf(id)
        };
        String groupBy = null;
        String havingBy = null;
        String orderBy = dt.Property_intHeaderId;

        Cursor cursor = db.query(TABLE_CONTACTS,
                tableColumns,
                whereClause,
                whereArgs,
                groupBy,
                havingBy,
                orderBy);

        if (cursor.moveToFirst()) {
            do {
                tVisitPlanHeader_MobileData contact = new tVisitPlanHeader_MobileData();
                contact.set_intHeaderId(cursor.getString(0));
                contact.set_txtUserId(cursor.getString(1));
                contact.set_txtPeriode(cursor.getString(2));
                contact.set_intUnplan(cursor.getString(3));
                contact.set_intGuidUnplan(cursor.getString(4));
                contact.set_txtBranchCode(cursor.getString(5));
                contact.set_bitActive(cursor.getString(6));
                contact.set_dtStart(cursor.getString(7));
                contact.set_dtEnd(cursor.getString(8));
                contact.set_intSumBobot(cursor.getString(9));
                contact.set_intRealisasi(cursor.getString(10));
                contact.set_intSubmit(cursor.getString(11));
                contact.set_intPush(cursor.getString(12));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tVisitPlanHeader_MobileData> getPushData(SQLiteDatabase db) {
        List<tVisitPlanHeader_MobileData> contactList = null;
        // Select All Query
        tVisitPlanHeader_MobileData dt = new tVisitPlanHeader_MobileData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + "='1' AND " + dt.Property_intPush + "='0'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tVisitPlanHeader_MobileData>();
            do {
                tVisitPlanHeader_MobileData contact = new tVisitPlanHeader_MobileData();
                contact.set_intHeaderId(cursor.getString(0));
                contact.set_txtUserId(cursor.getString(1));
                contact.set_txtPeriode(cursor.getString(2));
                contact.set_intUnplan(cursor.getString(3));
                contact.set_intGuidUnplan(cursor.getString(4));
                contact.set_txtBranchCode(cursor.getString(5));
                contact.set_bitActive(cursor.getString(6));
                contact.set_dtStart(cursor.getString(7));
                contact.set_dtEnd(cursor.getString(8));
                contact.set_intSumBobot(cursor.getString(9));
                contact.set_intRealisasi(cursor.getString(10));
                contact.set_intSubmit(cursor.getString(11));
                contact.set_intPush(cursor.getString(12));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tVisitPlanHeader_MobileData> getAllData(SQLiteDatabase db){
        List<tVisitPlanHeader_MobileData> contactList = new ArrayList<tVisitPlanHeader_MobileData>();
        // Select All Query
        tVisitPlanHeader_MobileData dt=new tVisitPlanHeader_MobileData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tVisitPlanHeader_MobileData contact = new tVisitPlanHeader_MobileData();
                contact.set_intHeaderId(cursor.getString(0));
                contact.set_txtUserId(cursor.getString(1));
                contact.set_txtPeriode(cursor.getString(2));
                contact.set_intUnplan(cursor.getString(3));
                contact.set_intGuidUnplan(cursor.getString(4));
                contact.set_txtBranchCode(cursor.getString(5));
                contact.set_bitActive(cursor.getString(6));
                contact.set_dtStart(cursor.getString(7));
                contact.set_dtEnd(cursor.getString(8));
                contact.set_intSumBobot(cursor.getString(9));
                contact.set_intRealisasi(cursor.getString(10));
                contact.set_intSubmit(cursor.getString(11));
                contact.set_intPush(cursor.getString(12));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }
    public void DeleteAllData(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    // Deleting single contact
    public void deleteContact(SQLiteDatabase db, String id) {
        tVisitPlanHeader_MobileData dt = new tVisitPlanHeader_MobileData();
        db.delete(TABLE_CONTACTS, dt.Property_intHeaderId + " = ? ",
                new String[] {String.valueOf(id)});
    }

    public void deleteByHeaderID(SQLiteDatabase db, String id){
        tVisitPlanHeader_MobileData dt = new tVisitPlanHeader_MobileData();
        String whereClause = dt.Property_intHeaderId + " = ?";
        String[] whereArgs = new String[]{
                String.valueOf(id)
        };
        db.delete(TABLE_CONTACTS, whereClause, whereArgs);
    }



}
