package library.spgmobile.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mCountConsumerMTDData;

public class mCountConsumerMTDDA {
    // All Static variables

    // Contacts table name
    private static final String TABLE_NAME = new clsHardCode().txtTable_mCountConsumerMTD;

    // Contacts Table Columns names

    public mCountConsumerMTDDA(SQLiteDatabase db) {
        mCountConsumerMTDData dt = new mCountConsumerMTDData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + dt.Property_jumlah + " TEXT,"
                + dt.Property_txtRegionName + " TEXT,"
                + dt.Property_txtBranchCode + " TEXT,"
                + dt.Property_txtBranchName + " TEXT,"
                + dt.Property_txtOutletCode + " TEXT,"
                + dt.Property_txtOutletName + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
    }

    public void SaveData(SQLiteDatabase db, mCountConsumerMTDData data) {
        mCountConsumerMTDData dt=new mCountConsumerMTDData();

        db.execSQL("INSERT OR REPLACE into "+ TABLE_NAME+" ("+dt.Property_jumlah+",'"
                +dt.Property_txtRegionName+"','"
                +dt.Property_txtBranchCode+"','"
                +dt.Property_txtBranchName+"','"
                +dt.Property_txtOutletCode+"','"
                +dt.Property_txtOutletName+"') "+
                "values('"	+String.valueOf(data.getJumlah())+"','"
                +String.valueOf(data.getTxtRegionName())+"','"
                +String.valueOf(data.getTxtBranchCode())+"','"
                +String.valueOf(data.getTxtBranchName())+"','"
                +String.valueOf(data.getTxtOutletCode())+"','"
                +String.valueOf(data.getTxtOutletName())+"')");
    }

    public int countCustomerBaseHomeAbsen(SQLiteDatabase db, String code) {
        List<mCountConsumerMTDData> contactList = new ArrayList<mCountConsumerMTDData>();
        // Select All Query
        mCountConsumerMTDData dt = new mCountConsumerMTDData();

        String selectQuery = "select jumlah from " + TABLE_NAME + " where " + dt.Property_txtOutletCode + "='" + code + "'";

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
}
