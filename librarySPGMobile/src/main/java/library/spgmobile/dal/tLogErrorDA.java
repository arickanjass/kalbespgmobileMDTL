package library.spgmobile.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tLogErrorData;

/**
 * Created by Robert on 06/04/2017.
 */

public class tLogErrorDA {
    private static final String TABLE_NAME = new clsHardCode().txtLogError;

    public tLogErrorDA(SQLiteDatabase db) {
        tLogErrorData dt = new tLogErrorData();
        String CREATE_CONTACT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + dt.Property_intLogId + " TEXT PRIMARY KEY,"
                + dt.Property_txtUserId + " TEXT NULL,"
                + dt.Property_txtRoleId + " TEXT NULL,"
                + dt.Property_txtRoleName + " TEXT NULL,"
                + dt.Property_dtDate + " TEXT NULL,"
                + dt.Property_txtDeviceId + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL,"
                + dt.Property_intSync + " TEXT NULL,"
                + dt.Property_txtFileName + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    //Upgrading database
    public void DropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     **/
    //adding new contact
    public void SaveLogData(SQLiteDatabase db, tLogErrorData data) {
        tLogErrorData dt = new tLogErrorData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_NAME + " (" + dt.Property_All + ") " +
                "values('" + String.valueOf(data.get_intLogId()) + "','"
                + String.valueOf(data.get_TxtUserId()) + "','"
                + String.valueOf(data.get_txtRoleId()) + "','"
                + String.valueOf(data.get_txtRoleName()) + "','"
                + String.valueOf(data.get_txtDeviceId()) + "','"
                + String.valueOf(data.get_dtDate()) + "','"
                + String.valueOf(data.get_intSubmit()) + "','"
                + String.valueOf(data.get_intSync()) + "','"
                + String.valueOf(data.get_txtFileName()) + "')"
        );
    }

    public List<tLogErrorData> getAllData(SQLiteDatabase db) {
        tLogErrorData dt = new tLogErrorData();
        List<tLogErrorData> dataList = new ArrayList<tLogErrorData>();

        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tLogErrorData data = new tLogErrorData();
                data.set_intLogId(cursor.getString(0));
                data.set_txtUserId(cursor.getString(1));
                data.set_txtRoleId(cursor.getString(2));
                data.set_txtRoleName(cursor.getString(3));
                data.set_txtDeviceId(cursor.getString(4));
                data.set_dtDate(cursor.getString(5));
                data.set_intSubmit(cursor.getString(6));
                data.set_intSync(cursor.getString(7));
                data.set_txtFileName(cursor.getString(8));
                dataList.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return dataList;
    }

    public List<tLogErrorData> getAllDataByFileName(SQLiteDatabase db,String txtFileName) {
        tLogErrorData dt = new tLogErrorData();
        List<tLogErrorData> dataList = new ArrayList<tLogErrorData>();

        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_NAME +" Where "+dt.Property_txtFileName+"='"+txtFileName+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tLogErrorData data = new tLogErrorData();
                data.set_intLogId(cursor.getString(0));
                data.set_txtUserId(cursor.getString(1));
                data.set_txtRoleId(cursor.getString(2));
                data.set_txtRoleName(cursor.getString(3));
                data.set_txtDeviceId(cursor.getString(4));
                data.set_dtDate(cursor.getString(5));
                data.set_intSubmit(cursor.getString(6));
                data.set_intSync(cursor.getString(7));
                data.set_txtFileName(cursor.getString(8));
                dataList.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return dataList;
    }
    public List<tLogErrorData> getPushData(SQLiteDatabase db) {
        tLogErrorData dt = new tLogErrorData();
        List<tLogErrorData> dataList = new ArrayList<tLogErrorData>();

        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tLogErrorData data = new tLogErrorData();
                data.set_intLogId(cursor.getString(0));
                data.set_txtUserId(cursor.getString(1));
                data.set_txtRoleId(cursor.getString(2));
                data.set_txtRoleName(cursor.getString(3));
                data.set_txtDeviceId(cursor.getString(4));
                data.set_dtDate(cursor.getString(5));
                data.set_intSubmit(cursor.getString(6));
                data.set_intSync(cursor.getString(7));
                data.set_txtFileName(cursor.getString(8));
                dataList.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return dataList;
    }

    public void DeleteAllData(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
