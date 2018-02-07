package library.spgmobile.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tCustomerBasedMobileHeaderData;

public class tCustomerBasedMobileHeaderDA {
    // All Static variables

    // Contacts table name
    private static final String TABLE_NAME = new clsHardCode().txtTable_tCustomerBasedMobileHeader;

    // Contacts Table Columns names

    public tCustomerBasedMobileHeaderDA(SQLiteDatabase db) {
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + dt.Property_intTrCustomerId + " TEXT PRIMARY KEY,"
                + dt.Property_txtSubmissionId + " TEXT NULL,"
                + dt.Property_txtSubmissionCode + " TEXT NULL,"
                + dt.Property_txtBranchCode + " TEXT NULL,"
                + dt.Property_txtSumberData + " TEXT NULL,"
                + dt.Property_txtNamaSumberData + " TEXT NULL,"
                + dt.Property_txtNamaDepan + " TEXT NULL,"
                + dt.Property_txtGender + " TEXT NULL,"
                + dt.Property_txtTelp + " TEXT NULL,"
                + dt.Property_txtTelp2 + " TEXT NULL,"
                + dt.Property_txtTelpKantor + " TEXT NULL,"
                + dt.Property_txtEmail + " TEXT NULL,"
                + dt.Property_txtPINBBM + " TEXT NULL,"
                + dt.Property_txtALamat + " TEXT NULL,"
                + dt.Property_txtUserId + " TEXT NULL,"
                + dt.Property_txtRoleId + " TEXT NULL,"
                + dt.Property_intPIC + " TEXT NULL,"
                + dt.Property_txtDeviceId + " TEXT NULL,"
                + dt.Property_bitActive + " TEXT NULL,"
                + dt.Property_txtLOB + " TEXT NULL,"
                + dt.Property_dtDate + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL,"
                + dt.Property_intSync + " TEXT NULL,"
                + dt.Property_txtTglLahir + " TEXT NULL,"
                + dt.Property_intAge + " TEXT NULL,"
                + dt.Property_intAgeTypeFlag + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     **/

    // Adding new contact
    public void SaveDatatCustomerBasedMobileHeaderData(SQLiteDatabase db, tCustomerBasedMobileHeaderData data) {
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_NAME + " (" + dt.Property_ALL + ") " +
                "values('" + String.valueOf(data.get_intTrCustomerId()) + "','"
                + String.valueOf(data.get_txtSubmissionId()) + "','"
                + String.valueOf(data.get_txtSubmissionCode()) + "','"
                + String.valueOf(data.get_txtBranchCode()) + "','"
                + String.valueOf(data.get_txtSumberData()) + "','"
                + String.valueOf(data.get_txtNamaSumberData()) + "','"
                + String.valueOf(data.get_txtNamaDepan().replace("'", "`")) + "','"
                + String.valueOf(data.get_txtGender()) + "','"
                + String.valueOf(data.get_txtTelp()) + "','"
                + String.valueOf(data.get_txtTelp2()) + "','"
                + String.valueOf(data.get_txtTelpKantor().replace("'", "`")) + "','"
                + String.valueOf(data.get_txtEmail()) + "','"
                + String.valueOf(data.get_txtPINBBM()) + "','"
                + String.valueOf(data.get_txtALamat()) + "','"
                + String.valueOf(data.get_txtUserId()) + "','"
                + String.valueOf(data.get_txtRoleId()) + "','"
                + String.valueOf(data.get_intPIC()) + "','"
                + String.valueOf(data.get_txtDeviceId()) + "','"
                + String.valueOf(data.get_bitActive()) + "','"
                + String.valueOf(data.get_txtLOB()) + "','"
                + String.valueOf(data.get_dtDate()) + "','"
                + String.valueOf(data.get_intSubmit()) + "','"
                + String.valueOf(data.get_intSync()) + "','"
                + String.valueOf(data.get_txtTglLahir()) + "','"
                + String.valueOf(data.get_intAge()) + "','"
                + String.valueOf(data.get_intAgeTypeFlag()) + "')"
        );
    }

    // Getting single contact
    public tCustomerBasedMobileHeaderData getData(SQLiteDatabase db, String id) {
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();
        String[] tableColumns = new String[]{
                dt.Property_intTrCustomerId,
                dt.Property_txtSubmissionId,
                dt.Property_txtSubmissionCode,
                dt.Property_txtBranchCode,
                dt.Property_txtSumberData,
                dt.Property_txtNamaSumberData,
                dt.Property_txtNamaDepan,
                dt.Property_txtGender,
                dt.Property_txtTelp,
                dt.Property_txtTelp2,
                dt.Property_txtTelpKantor,
                dt.Property_txtEmail,
                dt.Property_txtPINBBM,
                dt.Property_txtALamat,
                dt.Property_txtUserId,
                dt.Property_intPIC,
                dt.Property_txtDeviceId,
                dt.Property_bitActive,
                dt.Property_txtLOB,
                dt.Property_dtDate,
                dt.Property_intSubmit,
                dt.Property_intSync,
                dt.Property_txtTglLahir,
                dt.Property_intAge,
                dt.Property_intAgeTypeFlag
        };
        String whereClause = dt.Property_intTrCustomerId + "=?";
        String[] whereArgs = new String[]{
                String.valueOf(id)
        };
        String groupBy = null;
        String havingBy = null;
        String orderBy = dt.Property_intTrCustomerId;

        Cursor cursor = db.query(TABLE_NAME,
                tableColumns,
                whereClause,
                whereArgs,
                groupBy,
                havingBy,
                orderBy);
        if (cursor != null)
            cursor.moveToFirst();
        tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
        contact.set_intTrCustomerId(cursor.getString(0));
        contact.set_txtSubmissionId(cursor.getString(1));
        contact.set_txtSubmissionCode(cursor.getString(2));
        contact.set_txtBranchCode(cursor.getString(3));
        contact.set_txtSumberData(cursor.getString(4));
        contact.set_txtNamaSumberData(cursor.getString(5));
        contact.set_txtNamaDepan(cursor.getString(6));
        contact.set_txtGender(cursor.getString(7));
        contact.set_txtTelp(cursor.getString(8));
        contact.set_txtTelp2(cursor.getString(9));
        contact.set_txtTelpKantor(cursor.getString(10));
        contact.set_txtEmail(cursor.getString(11));
        contact.set_txtPINBBM(cursor.getString(12));
        contact.set_txtALamat(cursor.getString(13));
        contact.set_txtUserId(cursor.getString(14));
        contact.set_intPIC(cursor.getString(15));
        contact.set_txtDeviceId(cursor.getString(16));
        contact.set_bitActive(cursor.getString(17));
        contact.set_txtLOB(cursor.getString(18));
        contact.set_dtDate(cursor.getString(19));
        contact.set_intSubmit(cursor.getString(20));
        contact.set_intSync(cursor.getString(21));
        contact.set_txtTglLahir(cursor.getString(22));
        contact.set_intAge(cursor.getString(23));
        contact.set_intAgeTypeFlag(cursor.getString(24));
        // return contact
        cursor.close();
        return contact;
    }

    // Getting All Contacts
    public List<tCustomerBasedMobileHeaderData> getAllData(SQLiteDatabase db) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_intSubmit + "='1' ORDER BY txtSubmissionId DESC";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
                contact.set_intTrCustomerId(cursor.getString(0));
                contact.set_txtSubmissionId(cursor.getString(1));
                contact.set_txtSubmissionCode(cursor.getString(2));
                contact.set_txtBranchCode(cursor.getString(3));
                contact.set_txtSumberData(cursor.getString(4));
                contact.set_txtNamaSumberData(cursor.getString(5));
                contact.set_txtNamaDepan(cursor.getString(6));
                contact.set_txtGender(cursor.getString(7));
                contact.set_txtTelp(cursor.getString(8));
                contact.set_txtTelp2(cursor.getString(9));
                contact.set_txtTelpKantor(cursor.getString(10));
                contact.set_txtEmail(cursor.getString(11));
                contact.set_txtPINBBM(cursor.getString(12));
                contact.set_txtALamat(cursor.getString(13));
                contact.set_txtUserId(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contact.set_intPIC(cursor.getString(16));
                contact.set_txtDeviceId(cursor.getString(17));
                contact.set_bitActive(cursor.getString(18));
                contact.set_txtLOB(cursor.getString(19));
                contact.set_dtDate(cursor.getString(20));
                contact.set_intSubmit(cursor.getString(21));
                contact.set_intSync(cursor.getString(22));
                contact.set_txtTglLahir(cursor.getString(23));
                contact.set_intAge(cursor.getString(24));
                contact.set_intAgeTypeFlag(cursor.getString(25));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tCustomerBasedMobileHeaderData> getAllDataNonSync(SQLiteDatabase db) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_intSubmit + "='0' AND " + dt.Property_bitActive + "='1' AND " + dt.Property_intSync + "='0' ORDER BY txtSubmissionId DESC";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
                contact.set_intTrCustomerId(cursor.getString(0));
                contact.set_txtSubmissionId(cursor.getString(1));
                contact.set_txtSubmissionCode(cursor.getString(2));
                contact.set_txtBranchCode(cursor.getString(3));
                contact.set_txtSumberData(cursor.getString(4));
                contact.set_txtNamaSumberData(cursor.getString(5));
                contact.set_txtNamaDepan(cursor.getString(6));
                contact.set_txtGender(cursor.getString(7));
                contact.set_txtTelp(cursor.getString(8));
                contact.set_txtTelp2(cursor.getString(9));
                contact.set_txtTelpKantor(cursor.getString(10));
                contact.set_txtEmail(cursor.getString(11));
                contact.set_txtPINBBM(cursor.getString(12));
                contact.set_txtALamat(cursor.getString(13));
                contact.set_txtUserId(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contact.set_intPIC(cursor.getString(16));
                contact.set_txtDeviceId(cursor.getString(17));
                contact.set_bitActive(cursor.getString(18));
                contact.set_txtLOB(cursor.getString(19));
                contact.set_dtDate(cursor.getString(20));
                contact.set_intSubmit(cursor.getString(21));
                contact.set_intSync(cursor.getString(22));
                contact.set_txtTglLahir(cursor.getString(23));
                contact.set_intAge(cursor.getString(24));
                contact.set_intAgeTypeFlag(cursor.getString(25));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tCustomerBasedMobileHeaderData> getAllDataNew(SQLiteDatabase db) {

        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
                contact.set_intTrCustomerId(cursor.getString(0));
                contact.set_txtSubmissionId(cursor.getString(1));
                contact.set_txtSubmissionCode(cursor.getString(2));
                contact.set_txtBranchCode(cursor.getString(3));
                contact.set_txtSumberData(cursor.getString(4));
                contact.set_txtNamaSumberData(cursor.getString(5));
                contact.set_txtNamaDepan(cursor.getString(6));
                contact.set_txtGender(cursor.getString(7));
                contact.set_txtTelp(cursor.getString(8));
                contact.set_txtTelp2(cursor.getString(9));
                contact.set_txtTelpKantor(cursor.getString(10));
                contact.set_txtEmail(cursor.getString(11));
                contact.set_txtPINBBM(cursor.getString(12));
                contact.set_txtALamat(cursor.getString(13));
                contact.set_txtUserId(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contact.set_intPIC(cursor.getString(16));
                contact.set_txtDeviceId(cursor.getString(17));
                contact.set_bitActive(cursor.getString(18));
                contact.set_txtLOB(cursor.getString(19));
                contact.set_dtDate(cursor.getString(20));
                contact.set_intSubmit(cursor.getString(21));
                contact.set_intSync(cursor.getString(22));
                contact.set_txtTglLahir(cursor.getString(23));
                contact.set_intAge(cursor.getString(24));
                contact.set_intAgeTypeFlag(cursor.getString(25));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }


    public List<tCustomerBasedMobileHeaderData> getAllDataReporting(SQLiteDatabase db) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " ORDER BY txtSubmissionId DESC";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
                contact.set_intTrCustomerId(cursor.getString(0));
                contact.set_txtSubmissionId(cursor.getString(1));
                contact.set_txtSubmissionCode(cursor.getString(2));
                contact.set_txtBranchCode(cursor.getString(3));
                contact.set_txtSumberData(cursor.getString(4));
                contact.set_txtNamaSumberData(cursor.getString(5));
                contact.set_txtNamaDepan(cursor.getString(6));
                contact.set_txtGender(cursor.getString(7));
                contact.set_txtTelp(cursor.getString(8));
                contact.set_txtTelp2(cursor.getString(9));
                contact.set_txtTelpKantor(cursor.getString(10));
                contact.set_txtEmail(cursor.getString(11));
                contact.set_txtPINBBM(cursor.getString(12));
                contact.set_txtALamat(cursor.getString(13));
                contact.set_txtUserId(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contact.set_intPIC(cursor.getString(16));
                contact.set_txtDeviceId(cursor.getString(17));
                contact.set_bitActive(cursor.getString(18));
                contact.set_txtLOB(cursor.getString(19));
                contact.set_dtDate(cursor.getString(20));
                contact.set_intSubmit(cursor.getString(21));
                contact.set_intSync(cursor.getString(22));
                contact.set_txtTglLahir(cursor.getString(23));
                contact.set_intAge(cursor.getString(24));
                contact.set_intAgeTypeFlag(cursor.getString(25));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public int countCustomerBaseHome(SQLiteDatabase db) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

        String selectQuery = "select a.intTrCustomerIdDetail, b.txtProductDetailCode,count(1) from tCustomerBasedMobileDetailProduct a left join mEmployeeSalesProduct b on a.txtProductBrandCode = b.txtBrandDetailGramCode group by a.intTrCustomerIdDetail, b.txtProductDetailCode";

        Cursor cursor = db.rawQuery(selectQuery, null);

        int count = 0;

        if (cursor.moveToFirst()) {
            do {
                count++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return count;
    }
    public int countCustomerBaseReportingAll(SQLiteDatabase db, String intTrCustomerId) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

//        String selectQuery = "select a.intTrCustomerIdDetail, b.txtProductDetailCode,count(1) from tCustomerBasedMobileDetailProduct a left join mEmployeeSalesProduct b on a.txtProductBrandCode = b.txtBrandDetailGramCode where a.intTrCustomerIdDetail in \n" +
//                "(select d.intTrCustomerIdDetail from tCustomerBasedMobileHeader c left join tCustomerBasedMobileDetail d on c.intTrCustomerId = d.intTrCustomerId where c.intTrCustomerId='" + intTrCustomerId + "') \n" +
//                "group by a.intTrCustomerIdDetail, b.txtProductDetailCode";
        String selectQuery= ""+
                "select intTrCustomerIdDetail,count(txtBrandDetailGramCode) jumlahproduct,txtSumberData,QTY,txtNamaSumberData from ( "  +
                "        select b.intTrCustomerIdDetail, d.txtBrandDetailGramCode,txtSumberData,sum(c.[txtProductBrandQty])QTY, "+
                "a.txtNamaSumberData from [tCustomerBasedMobileHeader] a "+
                "left join [tCustomerBasedMobileDetail] b on a.[intTrCustomerId]=b.intTrCustomerId "+
                "left join [tCustomerBasedMobileDetailProduct] c on b.[intTrCustomerIdDetail]=c.[intTrCustomerIdDetail] "+
                "left join mEmployeeSalesProduct d on c.txtProductBrandCodeCRM = d.txtProductDetailCode  "+
                " WHERE a.intTrCustomerId='" + intTrCustomerId + "'" +
                "group by b.intTrCustomerIdDetail, d.txtProductDetailCode,a.txtSumberData "+
                ") as a "+
                "group by intTrCustomerIdDetail,txtSumberData,QTY,txtNamaSumberData ";
        Cursor cursor = db.rawQuery(selectQuery, null);

        int count = 0;

        if (cursor.moveToFirst()) {
            do {
                count += Integer.parseInt(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return count;
    }

    public int countCustomerBaseReportingOutlet(SQLiteDatabase db, String intTrCustomerId, String outletCode) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

//        String selectQuery = "select a.intTrCustomerIdDetail, b.txtProductDetailCode,count(1) from tCustomerBasedMobileDetailProduct a left join mEmployeeSalesProduct b on a.txtProductBrandCode = b.txtBrandDetailGramCode where a.intTrCustomerIdDetail in \n" +
//                "(select d.intTrCustomerIdDetail from tCustomerBasedMobileHeader c left join tCustomerBasedMobileDetail d on c.intTrCustomerId = d.intTrCustomerId where c.intTrCustomerId='" + intTrCustomerId + "' and c.txtSumberData='" + outletCode + "') \n" +
//                "group by a.intTrCustomerIdDetail, b.txtProductDetailCode";
        String selectQuery= ""+
                "select intTrCustomerIdDetail,count(txtBrandDetailGramCode) jumlahproduct,txtSumberData,QTY,txtNamaSumberData from ( "  +
                "        select b.intTrCustomerIdDetail, d.txtBrandDetailGramCode,txtSumberData,sum(c.[txtProductBrandQty])QTY, "+
                "a.txtNamaSumberData from [tCustomerBasedMobileHeader] a "+
                "left join [tCustomerBasedMobileDetail] b on a.[intTrCustomerId]=b.intTrCustomerId "+
                "left join [tCustomerBasedMobileDetailProduct] c on b.[intTrCustomerIdDetail]=c.[intTrCustomerIdDetail] "+
                "left join mEmployeeSalesProduct d on c.txtProductBrandCodeCRM = d.txtProductDetailCode  "+
                " WHERE a.intTrCustomerId='" + intTrCustomerId + "' AND a.txtSumberData='"+outletCode+"'" +
                "group by b.intTrCustomerIdDetail, d.txtProductDetailCode,a.txtSumberData "+
                ") as a "+
                "group by intTrCustomerIdDetail,txtSumberData,QTY,txtNamaSumberData ";
        Cursor cursor = db.rawQuery(selectQuery, null);

        int count = 0;

        if (cursor.moveToFirst()) {
            do {
                count++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return count;
    }

    public int countAllCustomerBaseBrandByDetail(SQLiteDatabase db, String id) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

        String selectQuery = "select a.intTrCustomerIdDetail, b.txtProductDetailCode,count(1) from tCustomerBasedMobileDetailProduct a left join mEmployeeSalesProduct b on a.txtProductBrandCode = b.txtBrandDetailGramCode group by a.intTrCustomerIdDetail, b.txtProductDetailCode";

        Cursor cursor = db.rawQuery(selectQuery, null);

        int count = 0;

        if (cursor.moveToFirst()) {
            do {
                count++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return count;
    }

    public int countCustomerBaseHomeByStatus(SQLiteDatabase db, String status) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

        String selectQuery = "select a.intTrCustomerIdDetail, b.txtProductDetailCode,count(1) from tCustomerBasedMobileDetailProduct a left join mEmployeeSalesProduct b on a.txtProductBrandCode = b.txtBrandDetailGramCode where a.intTrCustomerIdDetail in \n" +
                "(select d.intTrCustomerIdDetail from tCustomerBasedMobileHeader c left join tCustomerBasedMobileDetail d on c.intTrCustomerId = d.intTrCustomerId where c.bitActive='1' and c.intSubmit='1' and c.intSync='" + status + "'" +
                ") group by a.intTrCustomerIdDetail, b.txtProductDetailCode";

        Cursor cursor = db.rawQuery(selectQuery, null);

        int count = 0;

        if (cursor.moveToFirst()) {
            do {
                count++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return count;
    }

    public int countCustomerBaseHomeAbsen(SQLiteDatabase db, String code) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

        String selectQuery = "select coalesce(sum(1),0) as jumlah from ( select c.[intTrCustomerId] \n" +
                "from tCustomerBasedMobileHeader c left join tCustomerBasedMobileDetail d on c.intTrCustomerId = d.intTrCustomerId left join tCustomerBasedMobileDetailProduct e on d.[intTrCustomerIdDetail]=e.[intTrCustomerIdDetail]left join mEmployeeSalesProduct b on e.txtProductBrandCode = b.txtBrandDetailGramCode where c.txtSumberData='" + code + "' and c.bitActive='1' group by d.intTrCustomerIdDetail,e.[txtProductBrandCodeCRM] \n" +
                ") as a";

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
    public int countCustomerBaseHomeAbsenPush(SQLiteDatabase db,  String code) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

        //String selectQuery = "select a.intTrCustomerIdDetail, b.txtProductDetailCode,count(1) from tCustomerBasedMobileDetailProduct a left join mEmployeeSalesProduct b on a.txtProductBrandCode = b.txtBrandDetailGramCode where a.intTrCustomerIdDetail in \n" +
        //        "(select d.intTrCustomerIdDetail from tCustomerBasedMobileHeader c left join tCustomerBasedMobileDetail d on c.intTrCustomerId = d.intTrCustomerId where txtSumberData='" + code + "' and c.bitActive='1' and c.intSubmit='1' and c.intSync='" + status + "') \n" +
        //        "group by a.intTrCustomerIdDetail, b.txtProductDetailCode";
        String selectQuery = "select coalesce(sum(1),0) from [tCustomerBasedMobileHeader] where txtSumberData='" + code + "' and intSubmit=1 and [intSync]=1";

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
    public int countCustomerBaseHomeAbsenUnpush(SQLiteDatabase db,  String code) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

        //String selectQuery = "select a.intTrCustomerIdDetail, b.txtProductDetailCode,count(1) from tCustomerBasedMobileDetailProduct a left join mEmployeeSalesProduct b on a.txtProductBrandCode = b.txtBrandDetailGramCode where a.intTrCustomerIdDetail in \n" +
        //        "(select d.intTrCustomerIdDetail from tCustomerBasedMobileHeader c left join tCustomerBasedMobileDetail d on c.intTrCustomerId = d.intTrCustomerId where txtSumberData='" + code + "' and c.bitActive='1' and c.intSubmit='1' and c.intSync='" + status + "') \n" +
        //        "group by a.intTrCustomerIdDetail, b.txtProductDetailCode";
        String selectQuery = "select coalesce(sum(1),0) from [tCustomerBasedMobileHeader] where txtSumberData='" + code + "' and intSubmit=1 and [intSync]=0";

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

    public int countCustomerBaseHomeAbsenByStatus(SQLiteDatabase db, String status, String code) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

        String selectQuery = "select a.intTrCustomerIdDetail, b.txtProductDetailCode,count(1) from tCustomerBasedMobileDetailProduct a left join mEmployeeSalesProduct b on a.txtProductBrandCode = b.txtBrandDetailGramCode where a.intTrCustomerIdDetail in \n" +
                "(select d.intTrCustomerIdDetail from tCustomerBasedMobileHeader c left join tCustomerBasedMobileDetail d on c.intTrCustomerId = d.intTrCustomerId where txtSumberData='" + code + "' and c.bitActive='1' and c.intSubmit='1' and c.intSync='" + status + "') \n" +
                "group by a.intTrCustomerIdDetail, b.txtProductDetailCode";

        Cursor cursor = db.rawQuery(selectQuery, null);

        int count = 0;

        if (cursor.moveToFirst()) {
            do {
                count++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return count;
    }

    public int countCustomerBaseHomeAbsenByStatusSave(SQLiteDatabase db, String code) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

//        String selectQuery = "select a.intTrCustomerIdDetail, b.txtProductDetailCode,coalesce(count(1),0) from tCustomerBasedMobileDetailProduct a left join mEmployeeSalesProduct b on a.txtProductBrandCode = b.txtBrandDetailGramCode where a.intTrCustomerIdDetail in \n" +
//                "(select d.intTrCustomerIdDetail from tCustomerBasedMobileHeader c left join tCustomerBasedMobileDetail d on c.intTrCustomerId = d.intTrCustomerId where txtSumberData='" + code + "' and c.bitActive='1' and c.intSubmit='0' and c.intSync='0') \n" +
//                "group by a.intTrCustomerIdDetail, b.txtProductDetailCode";

        String selectQuery = "select coalesce(sum(1),0) from [tCustomerBasedMobileHeader] where txtSumberData='" + code + "' and bitActive=1 and intSubmit=0 and [intSync]=0";

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

    public List<tCustomerBasedMobileHeaderData> getAllDataByOutletCode(SQLiteDatabase db, String code) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

//        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_txtSumberData + "='" + code + "'" + " AND " + dt.Property_intSubmit + " ='1' AND bitActive = '1' ORDER BY txtSubmissionId DESC ";
        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_txtSumberData + "='" + code + "'" + " ORDER BY txtSubmissionId DESC ";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
                contact.set_intTrCustomerId(cursor.getString(0));
                contact.set_txtSubmissionId(cursor.getString(1));
                contact.set_txtSubmissionCode(cursor.getString(2));
                contact.set_txtBranchCode(cursor.getString(3));
                contact.set_txtSumberData(cursor.getString(4));
                contact.set_txtNamaSumberData(cursor.getString(5));
                contact.set_txtNamaDepan(cursor.getString(6));
                contact.set_txtGender(cursor.getString(7));
                contact.set_txtTelp(cursor.getString(8));
                contact.set_txtTelp2(cursor.getString(9));
                contact.set_txtTelpKantor(cursor.getString(10));
                contact.set_txtEmail(cursor.getString(11));
                contact.set_txtPINBBM(cursor.getString(12));
                contact.set_txtALamat(cursor.getString(13));
                contact.set_txtUserId(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contact.set_intPIC(cursor.getString(16));
                contact.set_txtDeviceId(cursor.getString(17));
                contact.set_bitActive(cursor.getString(18));
                contact.set_txtLOB(cursor.getString(19));
                contact.set_dtDate(cursor.getString(20));
                contact.set_intSubmit(cursor.getString(21));
                contact.set_intSync(cursor.getString(22));
                contact.set_txtTglLahir(cursor.getString(23));
                contact.set_intAge(cursor.getString(24));
                contact.set_intAgeTypeFlag(cursor.getString(25));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tCustomerBasedMobileHeaderData> getAllDataByOutletCodeForView(SQLiteDatabase db, String code) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

//        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_txtSumberData + "='" + code + "'" + " AND " + dt.Property_intSubmit + " ='1' AND bitActive = '1' ORDER BY txtSubmissionId DESC ";
        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_txtSumberData + "='" + code + "'" + " AND bitActive='1' ORDER BY txtSubmissionId DESC ";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
                contact.set_intTrCustomerId(cursor.getString(0));
                contact.set_txtSubmissionId(cursor.getString(1));
                contact.set_txtSubmissionCode(cursor.getString(2));
                contact.set_txtBranchCode(cursor.getString(3));
                contact.set_txtSumberData(cursor.getString(4));
                contact.set_txtNamaSumberData(cursor.getString(5));
                contact.set_txtNamaDepan(cursor.getString(6));
                contact.set_txtGender(cursor.getString(7));
                contact.set_txtTelp(cursor.getString(8));
                contact.set_txtTelp2(cursor.getString(9));
                contact.set_txtTelpKantor(cursor.getString(10));
                contact.set_txtEmail(cursor.getString(11));
                contact.set_txtPINBBM(cursor.getString(12));
                contact.set_txtALamat(cursor.getString(13));
                contact.set_txtUserId(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contact.set_intPIC(cursor.getString(16));
                contact.set_txtDeviceId(cursor.getString(17));
                contact.set_bitActive(cursor.getString(18));
                contact.set_txtLOB(cursor.getString(19));
                contact.set_dtDate(cursor.getString(20));
                contact.set_intSubmit(cursor.getString(21));
                contact.set_intSync(cursor.getString(22));
                contact.set_txtTglLahir(cursor.getString(23));
                contact.set_intAge(cursor.getString(24));
                contact.set_intAgeTypeFlag(cursor.getString(25));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tCustomerBasedMobileHeaderData> getAllDataByOutletCodeUnsubmit(SQLiteDatabase db, String code) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

//        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_txtSumberData + "='" + code + "'" + " AND " + dt.Property_intSubmit + " ='1' AND bitActive = '1' ORDER BY txtSubmissionId DESC ";
        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_txtSumberData + "='" + code + "'" + " AND bitActive='1' AND intSubmit='0' AND intSync='0' ORDER BY txtSubmissionId DESC ";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
                contact.set_intTrCustomerId(cursor.getString(0));
                contact.set_txtSubmissionId(cursor.getString(1));
                contact.set_txtSubmissionCode(cursor.getString(2));
                contact.set_txtBranchCode(cursor.getString(3));
                contact.set_txtSumberData(cursor.getString(4));
                contact.set_txtNamaSumberData(cursor.getString(5));
                contact.set_txtNamaDepan(cursor.getString(6));
                contact.set_txtGender(cursor.getString(7));
                contact.set_txtTelp(cursor.getString(8));
                contact.set_txtTelp2(cursor.getString(9));
                contact.set_txtTelpKantor(cursor.getString(10));
                contact.set_txtEmail(cursor.getString(11));
                contact.set_txtPINBBM(cursor.getString(12));
                contact.set_txtALamat(cursor.getString(13));
                contact.set_txtUserId(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contact.set_intPIC(cursor.getString(16));
                contact.set_txtDeviceId(cursor.getString(17));
                contact.set_bitActive(cursor.getString(18));
                contact.set_txtLOB(cursor.getString(19));
                contact.set_dtDate(cursor.getString(20));
                contact.set_intSubmit(cursor.getString(21));
                contact.set_intSync(cursor.getString(22));
                contact.set_txtTglLahir(cursor.getString(23));
                contact.set_intAge(cursor.getString(24));
                contact.set_intAgeTypeFlag(cursor.getString(25));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public int getAllCheckPushData(SQLiteDatabase db) {
        List<tCustomerBasedMobileHeaderData> contactList = null;
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();
        String selectQuery = "SELECT  1 FROM "
                + TABLE_NAME +" WHERE " +dt.Property_intSync +" ='0' And "+dt.Property_intSubmit+"=1" ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // return count
        int index = cursor.getCount();
        cursor.close();
        return index;
    }

    public int getAllDataSave(SQLiteDatabase db) {
        List<tCustomerBasedMobileHeaderData> contactList = null;
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();
        String selectQuery = "SELECT  1 FROM "
                + TABLE_NAME +" WHERE " +dt.Property_intSync +" ='0' And "+dt.Property_intSubmit+"=0" ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // return count
        int index = cursor.getCount();
        cursor.close();
        return index;
    }

    public List<tCustomerBasedMobileHeaderData> getPushData(SQLiteDatabase db) {
        List<tCustomerBasedMobileHeaderData> contactList = null;
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();
        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_intSubmit + "='1' AND " + dt.Property_intSync + "='0'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
            do {
                tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
                contact.set_intTrCustomerId(cursor.getString(0));
                contact.set_txtSubmissionId(cursor.getString(1));
                contact.set_txtSubmissionCode(cursor.getString(2));
                contact.set_txtBranchCode(cursor.getString(3));
                contact.set_txtSumberData(cursor.getString(4));
                contact.set_txtNamaSumberData(cursor.getString(5));
                contact.set_txtNamaDepan(cursor.getString(6));
                contact.set_txtGender(cursor.getString(7));
                contact.set_txtTelp(cursor.getString(8));
                contact.set_txtTelp2(cursor.getString(9));
                contact.set_txtTelpKantor(cursor.getString(10));
                contact.set_txtEmail(cursor.getString(11));
                contact.set_txtPINBBM(cursor.getString(12));
                contact.set_txtALamat(cursor.getString(13));
                contact.set_txtUserId(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contact.set_intPIC(cursor.getString(16));
                contact.set_txtDeviceId(cursor.getString(17));
                contact.set_bitActive(cursor.getString(18));
                contact.set_txtLOB(cursor.getString(19));
                contact.set_dtDate(cursor.getString(20));
                contact.set_intSubmit(cursor.getString(21));
                contact.set_intSync(cursor.getString(22));
                contact.set_txtTglLahir(cursor.getString(23));
                contact.set_intAge(cursor.getString(24));
                contact.set_intAgeTypeFlag(cursor.getString(25));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tCustomerBasedMobileHeaderData> getDataToSubmit(SQLiteDatabase db) {
        List<tCustomerBasedMobileHeaderData> contactList = null;
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();
        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_intSubmit + "='0' AND " + dt.Property_intSync + "='0' AND "+ dt.Property_bitActive + "='1'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
            do {
                tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
                contact.set_intTrCustomerId(cursor.getString(0));
                contact.set_txtSubmissionId(cursor.getString(1));
                contact.set_txtSubmissionCode(cursor.getString(2));
                contact.set_txtBranchCode(cursor.getString(3));
                contact.set_txtSumberData(cursor.getString(4));
                contact.set_txtNamaSumberData(cursor.getString(5));
                contact.set_txtNamaDepan(cursor.getString(6));
                contact.set_txtGender(cursor.getString(7));
                contact.set_txtTelp(cursor.getString(8));
                contact.set_txtTelp2(cursor.getString(9));
                contact.set_txtTelpKantor(cursor.getString(10));
                contact.set_txtEmail(cursor.getString(11));
                contact.set_txtPINBBM(cursor.getString(12));
                contact.set_txtALamat(cursor.getString(13));
                contact.set_txtUserId(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contact.set_intPIC(cursor.getString(16));
                contact.set_txtDeviceId(cursor.getString(17));
                contact.set_bitActive(cursor.getString(18));
                contact.set_txtLOB(cursor.getString(19));
                contact.set_dtDate(cursor.getString(20));
                contact.set_intSubmit(cursor.getString(21));
                contact.set_intSync(cursor.getString(22));
                contact.set_txtTglLahir(cursor.getString(23));
                contact.set_intAge(cursor.getString(24));
                contact.set_intAgeTypeFlag(cursor.getString(25));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tCustomerBasedMobileHeaderData> getNewData(SQLiteDatabase db) {
        List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();
        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_intSubmit + "='0'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
                contact.set_intTrCustomerId(cursor.getString(0));
                contact.set_txtSubmissionId(cursor.getString(1));
                contact.set_txtSubmissionCode(cursor.getString(2));
                contact.set_txtBranchCode(cursor.getString(3));
                contact.set_txtSumberData(cursor.getString(4));
                contact.set_txtNamaSumberData(cursor.getString(5));
                contact.set_txtNamaDepan(cursor.getString(6));
                contact.set_txtGender(cursor.getString(7));
                contact.set_txtTelp(cursor.getString(8));
                contact.set_txtTelp2(cursor.getString(9));
                contact.set_txtTelpKantor(cursor.getString(10));
                contact.set_txtEmail(cursor.getString(11));
                contact.set_txtPINBBM(cursor.getString(12));
                contact.set_txtALamat(cursor.getString(13));
                contact.set_txtUserId(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contact.set_intPIC(cursor.getString(16));
                contact.set_txtDeviceId(cursor.getString(17));
                contact.set_bitActive(cursor.getString(18));
                contact.set_txtLOB(cursor.getString(19));
                contact.set_dtDate(cursor.getString(20));
                contact.set_intSubmit(cursor.getString(21));
                contact.set_intSync(cursor.getString(22));
                contact.set_txtTglLahir(cursor.getString(23));
                contact.set_intAge(cursor.getString(24));
                contact.set_intAgeTypeFlag(cursor.getString(25));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }


    // Deleting single contact
    public void deleteByID(SQLiteDatabase db, String id) {
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();
        String whereClause = dt.Property_intTrCustomerId + " = ?";
        String[] whereArgs = new String[]{
                String.valueOf(id)
        };

        db.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public void DeleteAllDAta(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    // Getting contacts Count
    public int getContactsCount(SQLiteDatabase db) {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int num = cursor.getCount();
        cursor.close();

        // return count
        return num;
    }

    public tCustomerBasedMobileHeaderData getDataByBitActive(SQLiteDatabase db) {
        tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();

        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();
        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_bitActive + " = '0'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            contact.set_intTrCustomerId(cursor.getString(0));
            contact.set_txtSubmissionId(cursor.getString(1));
            contact.set_txtSubmissionCode(cursor.getString(2));
            contact.set_txtBranchCode(cursor.getString(3));
            contact.set_txtSumberData(cursor.getString(4));
            contact.set_txtNamaSumberData(cursor.getString(5));
            contact.set_txtNamaDepan(cursor.getString(6));
            contact.set_txtGender(cursor.getString(7));
            contact.set_txtTelp(cursor.getString(8));
            contact.set_txtTelp2(cursor.getString(9));
            contact.set_txtTelpKantor(cursor.getString(10));
            contact.set_txtEmail(cursor.getString(11));
            contact.set_txtPINBBM(cursor.getString(12));
            contact.set_txtALamat(cursor.getString(13));
            contact.set_txtUserId(cursor.getString(14));
            contact.set_txtRoleId(cursor.getString(15));
            contact.set_intPIC(cursor.getString(16));
            contact.set_txtDeviceId(cursor.getString(17));
            contact.set_bitActive(cursor.getString(18));
            contact.set_txtLOB(cursor.getString(19));
            contact.set_dtDate(cursor.getString(20));
            contact.set_intSubmit(cursor.getString(21));
            contact.set_intSync(cursor.getString(22));
            contact.set_txtTglLahir(cursor.getString(23));
            contact.set_intAge(cursor.getString(24));
            contact.set_intAgeTypeFlag(cursor.getString(25));
        }
        cursor.close();
        return contact;
    }

    public int getDataByNameAndTlp(SQLiteDatabase db, String txtName, String txtTlp) {
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + dt.Property_txtNamaDepan + " = '" + txtName + "' and " + dt.Property_txtTelp + " = '" + txtTlp + "' and " + dt.Property_bitActive + " = '1'" ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        int countData = cursor.getCount();
        cursor.close();
        // return count
        return countData;
    }

    public List<tCustomerBasedMobileHeaderData> getAllDataByIntSyc(SQLiteDatabase db, String int_sync) {
        List<tCustomerBasedMobileHeaderData> contactList = null;
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();
        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_intSync + "='" + int_sync + "' AND " + dt.Property_intSubmit + "='1'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
            do {
                tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
                contact.set_intTrCustomerId(cursor.getString(0));
                contact.set_txtSubmissionId(cursor.getString(1));
                contact.set_txtSubmissionCode(cursor.getString(2));
                contact.set_txtBranchCode(cursor.getString(3));
                contact.set_txtSumberData(cursor.getString(4));
                contact.set_txtNamaSumberData(cursor.getString(5));
                contact.set_txtNamaDepan(cursor.getString(6));
                contact.set_txtGender(cursor.getString(7));
                contact.set_txtTelp(cursor.getString(8));
                contact.set_txtTelp2(cursor.getString(9));
                contact.set_txtTelpKantor(cursor.getString(10));
                contact.set_txtEmail(cursor.getString(11));
                contact.set_txtPINBBM(cursor.getString(12));
                contact.set_txtALamat(cursor.getString(13));
                contact.set_txtUserId(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contact.set_intPIC(cursor.getString(16));
                contact.set_txtDeviceId(cursor.getString(17));
                contact.set_bitActive(cursor.getString(18));
                contact.set_txtLOB(cursor.getString(19));
                contact.set_dtDate(cursor.getString(20));
                contact.set_intSubmit(cursor.getString(21));
                contact.set_intSync(cursor.getString(22));
                contact.set_txtTglLahir(cursor.getString(23));
                contact.set_intAge(cursor.getString(24));
                contact.set_intAgeTypeFlag(cursor.getString(25));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tCustomerBasedMobileHeaderData> getAllDataByIntSycAndOutlet(SQLiteDatabase db, String int_sync, String outlet) {
        List<tCustomerBasedMobileHeaderData> contactList = null;
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();
        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_intSync + "='" + int_sync + "' AND " + dt.Property_intSubmit + "='1' AND " + dt.Property_txtSumberData + "='" + outlet + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
            do {
                tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
                contact.set_intTrCustomerId(cursor.getString(0));
                contact.set_txtSubmissionId(cursor.getString(1));
                contact.set_txtSubmissionCode(cursor.getString(2));
                contact.set_txtBranchCode(cursor.getString(3));
                contact.set_txtSumberData(cursor.getString(4));
                contact.set_txtNamaSumberData(cursor.getString(5));
                contact.set_txtNamaDepan(cursor.getString(6));
                contact.set_txtGender(cursor.getString(7));
                contact.set_txtTelp(cursor.getString(8));
                contact.set_txtTelp2(cursor.getString(9));
                contact.set_txtTelpKantor(cursor.getString(10));
                contact.set_txtEmail(cursor.getString(11));
                contact.set_txtPINBBM(cursor.getString(12));
                contact.set_txtALamat(cursor.getString(13));
                contact.set_txtUserId(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contact.set_intPIC(cursor.getString(16));
                contact.set_txtDeviceId(cursor.getString(17));
                contact.set_bitActive(cursor.getString(18));
                contact.set_txtLOB(cursor.getString(19));
                contact.set_dtDate(cursor.getString(20));
                contact.set_intSubmit(cursor.getString(21));
                contact.set_intSync(cursor.getString(22));
                contact.set_txtTglLahir(cursor.getString(23));
                contact.set_intAge(cursor.getString(24));
                contact.set_intAgeTypeFlag(cursor.getString(25));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tCustomerBasedMobileHeaderData> getLastData(SQLiteDatabase db) {
        List<tCustomerBasedMobileHeaderData> contactList = null;
        // Select All Query
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();
        String selectQuery = "SELECT  " + dt.Property_ALL + " FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToLast()) {
            contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
            do {
                tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
                contact.set_intTrCustomerId(cursor.getString(0));
                contact.set_txtSubmissionId(cursor.getString(1));
                contact.set_txtSubmissionCode(cursor.getString(2));
                contact.set_txtBranchCode(cursor.getString(3));
                contact.set_txtSumberData(cursor.getString(4));
                contact.set_txtNamaSumberData(cursor.getString(5));
                contact.set_txtNamaDepan(cursor.getString(6));
                contact.set_txtGender(cursor.getString(7));
                contact.set_txtTelp(cursor.getString(8));
                contact.set_txtTelp2(cursor.getString(9));
                contact.set_txtTelpKantor(cursor.getString(10));
                contact.set_txtEmail(cursor.getString(11));
                contact.set_txtPINBBM(cursor.getString(12));
                contact.set_txtALamat(cursor.getString(13));
                contact.set_txtUserId(cursor.getString(14));
                contact.set_txtRoleId(cursor.getString(15));
                contact.set_intPIC(cursor.getString(16));
                contact.set_txtDeviceId(cursor.getString(17));
                contact.set_bitActive(cursor.getString(18));
                contact.set_txtLOB(cursor.getString(19));
                contact.set_dtDate(cursor.getString(20));
                contact.set_intSubmit(cursor.getString(21));
                contact.set_intSync(cursor.getString(22));
                contact.set_txtTglLahir(cursor.getString(23));
                contact.set_intAge(cursor.getString(24));
                contact.set_intAgeTypeFlag(cursor.getString(25));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public int updateDataSubmit(SQLiteDatabase db, tCustomerBasedMobileHeaderData data) {
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();

        ContentValues values = new ContentValues();
        values.put(dt.Property_intSubmit, "1");

        // updating row
        return db.update(TABLE_NAME, values, dt.Property_intTrCustomerId + " = ? ",
                new String[] { String.valueOf(data.get_intTrCustomerId()) });
    }
}
