package library.spgmobile.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mProductCompetitorData;
import library.spgmobile.common.mProductSPGData;
import library.spgmobile.common.mUserLOBData;

/**
 * Created by ASUS ZE on 24/03/2017.
 */

public class mProductSPGDA {
    public mProductSPGDA(SQLiteDatabase db) {
        mProductSPGData dt = new mProductSPGData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "(" + dt.Property_intId
                + " TEXT PRIMARY KEY," + dt.Property_decBobot
                + " TEXT NULL," + dt.Property_decHJD + " TEXT  NULL,"
                + dt.Property_txtBrandDetailGramCode + " TEXT  NULL,"
                + dt.Property_txtName + " TEXT  NULL,"
                + dt.Property_txtNIK + " TEXT  NULL,"
                + dt.Property_txtProductBrandDetailGramName + " TEXT  NULL,"
                + dt.Property_txtProductDetailCode + " TEXT  NULL,"
                + dt.Property_txtProductDetailName + " TEXT  NULL,"
                + dt.Property_txtLobName + " TEXT  NULL,"
                + dt.Property_txtMasterId + " TEXT  NULL,"
                + dt.Property_txtNamaMasterData + " TEXT  NULL,"
                + dt.Property_txtKeterangan + " TEXT  NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mProductSPG;

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void SaveDataMConfig(SQLiteDatabase db, mProductSPGData data) {
        mProductSPGData dt = new mProductSPGData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_intId
                + "," + dt.Property_decBobot
                + ","+ dt.Property_decHJD
                + "," + dt.Property_txtBrandDetailGramCode
                + "," + dt.Property_txtName
                + "," + dt.Property_txtNIK
                + "," + dt.Property_txtProductBrandDetailGramName
                + "," + dt.Property_txtProductDetailCode
                + "," + dt.Property_txtProductDetailName
                + "," + dt.Property_txtLobName
                + "," + dt.Property_txtMasterId
                + "," + dt.Property_txtNamaMasterData
                + "," + dt.Property_txtKeterangan
                + ") " + "values('"
                + String.valueOf(data.get_intId()) + "','"
                + String.valueOf(data.get_decBobot()) + "','"
                + String.valueOf(data.get_decHJD()) + "','"
                + String.valueOf(data.get_txtBrandDetailGramCode()) + "','"
                + String.valueOf(data.get_txtName()) + "','"
                + String.valueOf(data.get_txtNIK()) + "','"
                + String.valueOf(data.get_txtProductBrandDetailGramName()) + "','"
                + String.valueOf(data.get_txtProductDetailCode()) + "','"
                + String.valueOf(data.get_txtProductDetailName()) + "','"
                + String.valueOf(data.get_txtLobName()) + "','"
                + String.valueOf(data.get_txtMasterId()) + "','"
                + String.valueOf(data.get_txtNamaMasterData()) + "','"
                + String.valueOf(data.get_txtKeterangan()) + "')");
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    public void DeleteAllDataMConfig(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    // Getting single contact
    public mProductSPGData getData(SQLiteDatabase db, int id) {
        mProductSPGData dt = new mProductSPGData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        dt.Property_intId
                        , dt.Property_decBobot
                        , dt.Property_decHJD
                        , dt.Property_txtBrandDetailGramCode
                        , dt.Property_txtName
                        , dt.Property_txtNIK
                        , dt.Property_txtProductBrandDetailGramName
                        , dt.Property_txtProductDetailCode
                        , dt.Property_txtProductDetailName
                        , dt.Property_txtLobName
                        , dt.Property_txtMasterId
                        , dt.Property_txtNamaMasterData
                        , dt.Property_txtKeterangan},
                dt.Property_intId + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        mProductSPGData contact = new mProductSPGData();
        if (cursor.getCount() > 0) {
            contact.set_intId(cursor.getString(0));
            contact.set_decBobot(cursor.getString(1));
            contact.set_decHJD(cursor.getString(2));
            contact.set_txtBrandDetailGramCode(cursor.getString(3));
            contact.set_txtName(cursor.getString(4));
            contact.set_txtNIK(cursor.getString(5));
            contact.set_txtProductBrandDetailGramName(cursor.getString(6));
            contact.set_txtProductDetailCode(cursor.getString(7));
            contact.set_txtProductDetailName(cursor.getString(8));
            contact.set_txtLobName(cursor.getString(9));
            contact.set_txtMasterId(cursor.getString(10));
            contact.set_txtNamaMasterData(cursor.getString(11));
            contact.set_txtKeterangan(cursor.getString(12));
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }

    // Getting All Contacts
    public List<mProductSPGData> getAllData(SQLiteDatabase db) {
        List<mProductSPGData> contactList = new ArrayList<mProductSPGData>();
        // Select All Query
        mProductSPGData dt = new mProductSPGData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS+" ORDER BY "+ dt.Property_txtProductBrandDetailGramName +" ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mProductSPGData contact = new mProductSPGData();
                contact.set_intId(cursor.getString(0));
                contact.set_decBobot(cursor.getString(1));
                contact.set_decHJD(cursor.getString(2));
                contact.set_txtBrandDetailGramCode(cursor.getString(3));
                contact.set_txtName(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_txtProductBrandDetailGramName(cursor.getString(6));
                contact.set_txtProductDetailCode(cursor.getString(7));
                contact.set_txtProductDetailName(cursor.getString(8));
                contact.set_txtLobName(cursor.getString(9));
                contact.set_txtMasterId(cursor.getString(10));
                contact.set_txtNamaMasterData(cursor.getString(11));
                contact.set_txtKeterangan(cursor.getString(12));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }


    public List<mProductSPGData> getAllDataByKN(SQLiteDatabase db, List<mUserLOBData>  mUserLOBDataList, String txtNik) {
        List<mProductSPGData> contactList = new ArrayList<mProductSPGData>();

        mProductSPGData data = new mProductSPGData();

        String query = "()";

        if (mUserLOBDataList != null){
            query = "(";
            for (int i = 0; i < mUserLOBDataList.size(); i++) {
                query = query + "'" + mUserLOBDataList.get(i).get_txtLOBName() + "'";
                query = query + ((i + 1) != mUserLOBDataList.size() ? "," : ")");
            }
        }

        String countQuery = "SELECT * FROM " + TABLE_CONTACTS +" WHERE "+ data.Property_txtNIK+" = '"+txtNik+"' AND " +data.Property_txtLobName +" IN " + query;
        Cursor cursor = db.rawQuery(countQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mProductSPGData contact = new mProductSPGData();
                contact.set_intId(cursor.getString(0));
                contact.set_decBobot(cursor.getString(1));
                contact.set_decHJD(cursor.getString(2));
                contact.set_txtBrandDetailGramCode(cursor.getString(3));
                contact.set_txtName(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_txtProductBrandDetailGramName(cursor.getString(6));
                contact.set_txtProductDetailCode(cursor.getString(7));
                contact.set_txtProductDetailName(cursor.getString(8));
                contact.set_txtLobName(cursor.getString(9));
                contact.set_txtMasterId(cursor.getString(10));
                contact.set_txtNamaMasterData(cursor.getString(11));
                contact.set_txtKeterangan(cursor.getString(12));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<mProductSPGData> getDataByMasterId(SQLiteDatabase db, String masterId) {
        List<mProductSPGData> contactList = new ArrayList<mProductSPGData>();
        // Select All Query
        mProductSPGData dt = new mProductSPGData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS+" WHERE "+ dt.Property_txtMasterId +" = '"+ masterId +"' ORDER BY "+ dt.Property_txtProductBrandDetailGramName +" ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mProductSPGData contact = new mProductSPGData();
                contact.set_intId(cursor.getString(0));
                contact.set_decBobot(cursor.getString(1));
                contact.set_decHJD(cursor.getString(2));
                contact.set_txtBrandDetailGramCode(cursor.getString(3));
                contact.set_txtName(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_txtProductBrandDetailGramName(cursor.getString(6));
                contact.set_txtProductDetailCode(cursor.getString(7));
                contact.set_txtProductDetailName(cursor.getString(8));
                contact.set_txtLobName(cursor.getString(9));
                contact.set_txtMasterId(cursor.getString(10));
                contact.set_txtNamaMasterData(cursor.getString(11));
                contact.set_txtKeterangan(cursor.getString(12));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }
    public List<mProductSPGData> GetDataProductByNoSo(SQLiteDatabase db, String IdSO) {
        List<mProductSPGData> contactList = new ArrayList<mProductSPGData>();
        // Select All Query
        mProductSPGData dt = new mProductSPGData();
        String selectQuery = "select a.intId,"+
                " IFNULL(b.intQty,0) as decBobot,decHJD,a.[txtBrandDetailGramCode],a.[txtName],"+
                " a.[txtNIK],a.[txtProductBrandDetailGramName] from mEmployeeSalesProduct a"+
                " left join ("+
                " select a.*,b.txtCodeProduct,b.intQty from [tSalesProductHeader] a"+
                " left join [tSalesProductDetail] b on a.[intId]=b.[txtNoSo] and b.[intActive]=1"+
                " where a.intId='"+IdSO+"'"+
                " ) as b on a.txtBrandDetailGramCode = b.txtCodeProduct  ORDER BY IFNULL(cast(b.intQty as int),0) DESC ,a.txtBrandDetailGramCode ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mProductSPGData contact = new mProductSPGData();
                contact.set_intId(cursor.getString(0));
                contact.set_decBobot(cursor.getString(1));
                contact.set_decHJD(cursor.getString(2));
                contact.set_txtBrandDetailGramCode(cursor.getString(3));
                contact.set_txtName(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_txtProductBrandDetailGramName(cursor.getString(6));
                contact.set_txtProductDetailCode(cursor.getString(7));
                contact.set_txtProductDetailName(cursor.getString(8));
                contact.set_txtLobName(cursor.getString(9));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }
    public List<mProductSPGData> SearchData(SQLiteDatabase db, String Id, String Name) {
        List<mProductSPGData> contactList = new ArrayList<mProductSPGData>();
        // Select All Query
        mProductSPGData dt = new mProductSPGData();
        String Param="1=1";
        if(Id.length()>0){
            Param+=" And "+dt.Property_txtBrandDetailGramCode+"='"+Id+"'";
        }
        if(Name.length()>0){
            Param+=" And "+dt.Property_txtProductBrandDetailGramName+" like '%"+Name+"%'";
        }

        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS + " Where "+Param
                +" ORDER BY "+ dt.Property_txtBrandDetailGramCode+" ASC,"+dt.Property_decBobot+" DESC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mProductSPGData contact = new mProductSPGData();
                contact.set_intId(cursor.getString(0));
                contact.set_decBobot(cursor.getString(1));
                contact.set_decHJD(cursor.getString(2));
                contact.set_txtBrandDetailGramCode(cursor.getString(3));
                contact.set_txtName(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_txtProductBrandDetailGramName(cursor.getString(6));
                contact.set_txtProductDetailCode(cursor.getString(7));
                contact.set_txtProductDetailName(cursor.getString(8));
                contact.set_txtLobName(cursor.getString(9));
                contact.set_txtMasterId(cursor.getString(10));
                contact.set_txtNamaMasterData(cursor.getString(11));
                contact.set_txtKeterangan(cursor.getString(12));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    // Deleting single contact
    public void deleteContact(SQLiteDatabase db, int id) {
        mProductSPGData dt = new mProductSPGData();
        db.delete(TABLE_CONTACTS, dt.Property_intId + " = ?",
                new String[] { String.valueOf(id) });
        // db.close();
    }

    // Getting contacts Count
    public int getContactsCount(SQLiteDatabase db) {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        int countData = cursor.getCount();
        cursor.close();
        // return count
        return countData;
    }

    // Getting contacts Count
    public int getContactsCountByKN(SQLiteDatabase db, List<mUserLOBData>  mUserLOBDataList) {
        mProductSPGData data = new mProductSPGData();

        String query = "()";

        if (mUserLOBDataList != null){
            query = "(";
            for (int i = 0; i < mUserLOBDataList.size(); i++) {
                query = query + "'" + mUserLOBDataList.get(i).get_txtLOBName() + "'";
                query = query + ((i + 1) != mUserLOBDataList.size() ? "," : ")");
            }
        }

        String countQuery = "SELECT * FROM " + TABLE_CONTACTS +" WHERE "+data.Property_txtLobName +" IN " + query;
        Cursor cursor = db.rawQuery(countQuery, null);
        int countData = cursor.getCount();
        cursor.close();
        // return count
        return countData;
    }
    public int getContactsCountByKN(SQLiteDatabase db, List<mUserLOBData>  mUserLOBDataList,String txtNik) {
        mProductSPGData data = new mProductSPGData();

        String query = "()";

        if (mUserLOBDataList != null){
            query = "(";
            for (int i = 0; i < mUserLOBDataList.size(); i++) {
                query = query + "'" + mUserLOBDataList.get(i).get_txtLOBName() + "'";
                query = query + ((i + 1) != mUserLOBDataList.size() ? "," : ")");
            }
        }

        String countQuery = "SELECT * FROM " + TABLE_CONTACTS +" WHERE "+data.Property_txtNIK+"='"+txtNik+"' AND "+data.Property_txtLobName +" IN " + query;
        Cursor cursor = db.rawQuery(countQuery, null);
        int countData = cursor.getCount();
        cursor.close();
        // return count
        return countData;
    }
    // Getting contacts Count
    public int getContactsCountDataSubmissionId(SQLiteDatabase db) {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS + " where txtMasterId in('null','')";
        Cursor cursor = db.rawQuery(countQuery, null);
        int countData = cursor.getCount();
        cursor.close();
        // return count
        return countData;
    }

    public void deleteContactsCountByKN(SQLiteDatabase db, List<mUserLOBData>  mUserLOBDataList) {
        mProductSPGData data = new mProductSPGData();

        String query = "()";

        if (mUserLOBDataList != null){
            query = "(";
            for (int i = 0; i < mUserLOBDataList.size(); i++) {
                query = query + "'" + mUserLOBDataList.get(i).get_txtLOBName() + "'";
                query = query + ((i + 1) != mUserLOBDataList.size() ? "," : ")");
            }
        }

        db.execSQL( "DELETE FROM " + TABLE_CONTACTS +" WHERE "+data.Property_txtLobName +" IN " + query);
    }
    public List<mProductSPGData> getDataByMasterIdByKN(SQLiteDatabase db, String masterId, List<mUserLOBData>  mUserLOBDataList) {
        List<mProductSPGData> contactList = new ArrayList<mProductSPGData>();
        // Select All Query
        mProductSPGData data = new mProductSPGData();

        String query = "()";

        if (mUserLOBDataList != null){
            query = "(";
            for (int i = 0; i < mUserLOBDataList.size(); i++) {
                query = query + "'" + mUserLOBDataList.get(i).get_txtLOBName() + "'";
                query = query + ((i + 1) != mUserLOBDataList.size() ? "," : ")");
            }
        }

        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS +" WHERE "+data.Property_txtLobName +" IN " + query + " AND " + data.Property_txtMasterId +" = '"+ masterId +"' ORDER BY "+ data.Property_txtProductBrandDetailGramName +" ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mProductSPGData contact = new mProductSPGData();
                contact.set_intId(cursor.getString(0));
                contact.set_decBobot(cursor.getString(1));
                contact.set_decHJD(cursor.getString(2));
                contact.set_txtBrandDetailGramCode(cursor.getString(3));
                contact.set_txtName(cursor.getString(4));
                contact.set_txtNIK(cursor.getString(5));
                contact.set_txtProductBrandDetailGramName(cursor.getString(6));
                contact.set_txtProductDetailCode(cursor.getString(7));
                contact.set_txtProductDetailName(cursor.getString(8));
                contact.set_txtLobName(cursor.getString(9));
                contact.set_txtMasterId(cursor.getString(10));
                contact.set_txtNamaMasterData(cursor.getString(11));
                contact.set_txtKeterangan(cursor.getString(12));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }
}
