package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import library.spgmobile.common.tJawabanUserHeaderData;
import library.spgmobile.common.tUserLoginData;

/**
 * Created by Dewi Oktaviani on 04/09/2017.
 */

public class tJawabanUserHeaderDA {
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tJawabanUserHeader;
    public tJawabanUserHeaderDA(SQLiteDatabase db){
        tJawabanUserHeaderData dt = new tJawabanUserHeaderData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "( "  + dt.Property_intHeaderId + " TEXT PRIMARY KEY,"
                + dt.Property_intNik + " TEXT NULL,"  + dt.Property_txtUserName + " TEXT NULL,"
                + dt.Property_intGroupQuestionId + " TEXT NULL,"+ dt.Property_intRoleId + " TEXT NULL,"
                + dt.Property_txtOutletCode + " TEXT NULL," + dt.Property_txtOutletName + " TEXT NULL,"
                + dt.Property_dtDate + " TEXT NULL," + dt.Property_dtDatetime + " TEXT NULL,"
                + dt.Property_intSum + " TEXT NULL," + dt.Property_intAverage + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL," + dt.Property_intSync + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    public void SaveDatatJawabanUserHeader(SQLiteDatabase db, tJawabanUserHeaderData data){
        tJawabanUserHeaderData dt = new tJawabanUserHeaderData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_intHeaderId, String.valueOf(data.get_intHeaderId()));
        cv.put(dt.Property_intNik, String.valueOf(data.get_intNik()));
        cv.put(dt.Property_txtUserName, String.valueOf(data.get_txtUserName()));
        cv.put(dt.Property_intGroupQuestionId, String.valueOf(data.get_intGroupQuestionId()));
        cv.put(dt.Property_intRoleId, String.valueOf(data.get_intRoleId()));
        cv.put(dt.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
        cv.put(dt.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
        cv.put(dt.Property_dtDate, String.valueOf(data.get_dtDate()));
        cv.put(dt.Property_dtDatetime, String.valueOf(data.get_dtDatetime()));
        if (data.get_intHeaderId() == null){
            cv.put(dt.Property_intSum, String.valueOf(data.get_intSum()));
            cv.put(dt.Property_intAverage, String.valueOf(data.get_intAverage()));
            cv.put(dt.Property_intSubmit, String.valueOf(data.get_intSubmit()));
            cv.put(dt.Property_intSync, String.valueOf(data.get_intSync()));
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_intSum, String.valueOf(data.get_intSum()));
            cv.put(dt.Property_intAverage, String.valueOf(data.get_intAverage()));
            cv.put(dt.Property_intSubmit, String.valueOf(data.get_intSubmit()));
            cv.put(dt.Property_intSync, String.valueOf(data.get_intSync()));
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }
    public void DeleteAllDatatJawabanUser(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    public List<tJawabanUserHeaderData> GetAllData(SQLiteDatabase db, String dateLogin){
        List<tJawabanUserHeaderData> contactList = new ArrayList<tJawabanUserHeaderData>();
        tJawabanUserHeaderData dt = new tJawabanUserHeaderData();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateLogin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dateNow = dateFormat.format(date);
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_dtDate + "='" + dateNow + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tJawabanUserHeaderData contact = new tJawabanUserHeaderData();
                contact.set_intHeaderId(cursor.getString(0));
                contact.set_intNik(cursor.getString(1));
                contact.set_txtUserName(cursor.getString(2));
                contact.set_intRoleId(cursor.getString(3));
                contact.set_txtOutletCode(cursor.getString(4));
                contact.set_dtDate(cursor.getString(5));
                contact.set_intSubmit(cursor.getString(6));
                contact.set_intSync(cursor.getString(7));
                contact.set_intGroupQuestionId(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_dtDatetime(cursor.getString(10));
                contact.set_intAverage(cursor.getString(11));
                contact.set_intSum(cursor.getString(12));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
    public List<tJawabanUserHeaderData> GetAllDatas(SQLiteDatabase db){
        List<tJawabanUserHeaderData> contactList = new ArrayList<tJawabanUserHeaderData>();
        tJawabanUserHeaderData dt = new tJawabanUserHeaderData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY dtDatetime ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tJawabanUserHeaderData contact = new tJawabanUserHeaderData();
                contact.set_intHeaderId(cursor.getString(0));
                contact.set_intNik(cursor.getString(1));
                contact.set_txtUserName(cursor.getString(2));
                contact.set_intRoleId(cursor.getString(3));
                contact.set_txtOutletCode(cursor.getString(4));
                contact.set_dtDate(cursor.getString(5));
                contact.set_intSubmit(cursor.getString(6));
                contact.set_intSync(cursor.getString(7));
                contact.set_intGroupQuestionId(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_dtDatetime(cursor.getString(10));
                contact.set_intAverage(cursor.getString(11));
                contact.set_intSum(cursor.getString(12));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tJawabanUserHeaderData> GetDataByOutletCode(SQLiteDatabase db, String code, String dateLogin){
        List<tJawabanUserHeaderData> contactList = new ArrayList<tJawabanUserHeaderData>();
        tJawabanUserHeaderData dt = new tJawabanUserHeaderData();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateLogin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dateNow = dateFormat.format(date);
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_dtDate + "='" + dateNow + "' AND " + dt.Property_txtOutletCode + "='" + code + "' ORDER BY dtDatetime ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tJawabanUserHeaderData contact = new tJawabanUserHeaderData();
                contact.set_intHeaderId(cursor.getString(0));
                contact.set_intNik(cursor.getString(1));
                contact.set_txtUserName(cursor.getString(2));
                contact.set_intRoleId(cursor.getString(3));
                contact.set_txtOutletCode(cursor.getString(4));
                contact.set_dtDate(cursor.getString(5));
                contact.set_intSubmit(cursor.getString(6));
                contact.set_intSync(cursor.getString(7));
                contact.set_intGroupQuestionId(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_dtDatetime(cursor.getString(10));
                contact.set_intAverage(cursor.getString(11));
                contact.set_intSum(cursor.getString(12));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<tJawabanUserHeaderData> GetLastBeforeSaveDetail(SQLiteDatabase db){
        List<tJawabanUserHeaderData> contactList = new ArrayList<tJawabanUserHeaderData>();
        tJawabanUserHeaderData dt = new tJawabanUserHeaderData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + "=0";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                tJawabanUserHeaderData contact = new tJawabanUserHeaderData();
                contact.set_intHeaderId(cursor.getString(0));
                contact.set_intNik(cursor.getString(1));
                contact.set_txtUserName(cursor.getString(2));
                contact.set_intRoleId(cursor.getString(3));
                contact.set_txtOutletCode(cursor.getString(4));
                contact.set_dtDate(cursor.getString(5));
                contact.set_intSubmit(cursor.getString(6));
                contact.set_intSync(cursor.getString(7));
                contact.set_intGroupQuestionId(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_dtDatetime(cursor.getString(10));
                contact.set_intAverage(cursor.getString(11));
                contact.set_intSum(cursor.getString(12));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public tJawabanUserHeaderData GetDataByHeaderId(SQLiteDatabase db, String intHeaderId){
        tJawabanUserHeaderData dt = new tJawabanUserHeaderData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intHeaderId + "='" + intHeaderId + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        tJawabanUserHeaderData contact = null;
        if (cursor.moveToFirst()){
            do {
               contact = new tJawabanUserHeaderData();
                contact.set_intHeaderId(cursor.getString(0));
                contact.set_intNik(cursor.getString(1));
                contact.set_txtUserName(cursor.getString(2));
                contact.set_intRoleId(cursor.getString(3));
                contact.set_txtOutletCode(cursor.getString(4));
                contact.set_dtDate(cursor.getString(5));
                contact.set_intSubmit(cursor.getString(6));
                contact.set_intSync(cursor.getString(7));
                contact.set_intGroupQuestionId(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_dtDatetime(cursor.getString(10));
                contact.set_intAverage(cursor.getString(11));
                contact.set_intSum(cursor.getString(12));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contact;
    }

    public List<tJawabanUserHeaderData> GetDataToPushAnswer(SQLiteDatabase db){
        List<tJawabanUserHeaderData> contactList = null;
        tJawabanUserHeaderData dt = new tJawabanUserHeaderData();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + "=1 AND " + dt.Property_intSync + " =0";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            contactList = new ArrayList<tJawabanUserHeaderData>();
            do {
                tJawabanUserHeaderData contact = new tJawabanUserHeaderData();
                contact.set_intHeaderId(cursor.getString(0));
                contact.set_intNik(cursor.getString(1));
                contact.set_txtUserName(cursor.getString(2));
                contact.set_intRoleId(cursor.getString(3));
                contact.set_txtOutletCode(cursor.getString(4));
                contact.set_dtDate(cursor.getString(5));
                contact.set_intSubmit(cursor.getString(6));
                contact.set_intSync(cursor.getString(7));
                contact.set_intGroupQuestionId(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_dtDatetime(cursor.getString(10));
                contact.set_intAverage(cursor.getString(11));
                contact.set_intSum(cursor.getString(12));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
