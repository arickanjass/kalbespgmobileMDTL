package library.spgmobile.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.tCustomerBasedMobileDetailData;
import library.spgmobile.common.tCustomerBasedMobileDetailProductData;

public class tCustomerBasedMobileDetailProductDA {
	// All Static variables

	// Contacts table name
	private static final String TABLE_NAME = new clsHardCode().txtTable_tCustomerBasedMobileDetailProduct;

	// Contacts Table Columns names
	public tCustomerBasedMobileDetailProductDA(SQLiteDatabase db) {
		tCustomerBasedMobileDetailProductData dt=new tCustomerBasedMobileDetailProductData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
				+ dt.Property_intTrCustomerIdDetailProduct + " TEXT PRIMARY KEY,"
				+ dt.Property_intTrCustomerIdDetail + " TEXT NULL,"
				+ dt.Property_txtProductBrandCode + " TEXT NULL,"
				+ dt.Property_txtProductBrandName + " TEXT NULL,"
				+ dt.Property_txtProductCompetitorCode + " TEXT NULL,"
				+ dt.Property_txtProductCompetitorName + " TEXT NULL,"
				+ dt.Property_txtProductBrandQty + " INT NULL,"
				+ dt.Property_txtProductBrandCodeCRM+ " TEXT NULL,"
				+ dt.Property_txtLOB + " TEXT NULL,"
				+ dt.Property_bitActive + " TEXT NULL,"
				+ dt.Property_dtInserted + " TEXT NULL,"
				+ dt.Property_dtUpdated + " TEXT NULL,"
				+ dt.Property_txtInsertedBy + " TEXT NULL,"
				+ dt.Property_txtUpdatedBy + " TEXT NULL" + ")";
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
	public void SaveDatatCustomerBasedMobileDetailProductData(SQLiteDatabase db,tCustomerBasedMobileDetailProductData data) {
		tCustomerBasedMobileDetailProductData dt=new tCustomerBasedMobileDetailProductData();
		db.execSQL("INSERT OR REPLACE into "+TABLE_NAME+" ("+dt.Property_ALL+") "+
				"values('"	+String.valueOf(data.get_intTrCustomerIdDetailProduct())+"','"
				+String.valueOf(data.get_intTrCustomerIdDetail())+"','"
				+String.valueOf(data.get_txtProductBrandCode())+"','"
				+String.valueOf(data.get_txtProductBrandName())+"','"
				+String.valueOf(data.get_txtProductCompetitorCode())+"','"
				+String.valueOf(data.get_txtProductCompetitorName())+"','"
				+String.valueOf(data.get_txtProductBrandQty())+"','"
				+String.valueOf(data.get_txtProductBrandCodeCRM())+"','"
				+String.valueOf(data.get_txtLOB())+"','"
				+String.valueOf(data.get_bitActive())+"','"
				+String.valueOf(data.get_dtInserted())+"','"
				+String.valueOf(data.get_dtUpdated())+"','"
				+String.valueOf(data.get_txtInsertedBy())+"','"
				+String.valueOf(data.get_txtUpdatedBy())+"')"
		);
	}

	// Getting single contact
	public tCustomerBasedMobileDetailProductData getData(SQLiteDatabase db,String id) {
		tCustomerBasedMobileDetailProductData dt=new tCustomerBasedMobileDetailProductData();
		String[] tableColumns = new String[] {
				dt.Property_intTrCustomerIdDetailProduct,
				dt.Property_intTrCustomerIdDetail,
				dt.Property_txtProductBrandCode,
				dt.Property_txtProductBrandName,
				dt.Property_txtProductCompetitorCode,
				dt.Property_txtProductCompetitorName,
				dt.Property_txtProductBrandQty,
				dt.Property_txtProductBrandCodeCRM,
				dt.Property_txtLOB,
				dt.Property_bitActive,
				dt.Property_dtInserted,
				dt.Property_dtUpdated,
				dt.Property_txtInsertedBy,
				dt.Property_txtUpdatedBy
		};
		String whereClause = dt.Property_intTrCustomerIdDetailProduct + "=?";
		String[] whereArgs = new String[] {
				String.valueOf(id)
		};
		String groupBy = null;
		String havingBy = null;
		String orderBy = dt.Property_intTrCustomerIdDetailProduct;

		Cursor cursor = db.query(TABLE_NAME,
				tableColumns,
				whereClause,
				whereArgs,
				groupBy,
				havingBy,
				orderBy);
		if (cursor != null)
			cursor.moveToFirst();
		tCustomerBasedMobileDetailProductData contact = new tCustomerBasedMobileDetailProductData();
		contact.set_intTrCustomerIdDetailProduct(cursor.getString(0));
		contact.set_intTrCustomerIdDetail(cursor.getString(1));
		contact.set_txtProductBrandCode(cursor.getString(2));
		contact.set_txtProductBrandName(cursor.getString(3));
		contact.set_txtProductCompetitorCode(cursor.getString(4));
		contact.set_txtProductCompetitorName(cursor.getString(5));
		contact.set_txtProductBrandQty(cursor.getString(6));
		contact.set_txtProductBrandCodeCRM(cursor.getString(7));
		contact.set_txtLOB(cursor.getString(8));
		contact.set_bitActive(cursor.getString(9));
		contact.set_dtInserted(cursor.getString(10));
		contact.set_dtUpdated(cursor.getString(11));
		contact.set_txtInsertedBy(cursor.getString(12));
		contact.set_txtUpdatedBy(cursor.getString(13));
		// return contact
		cursor.close();
		return contact;
	}

	public List<tCustomerBasedMobileDetailProductData> getAllDataByCustomerDetailId(SQLiteDatabase db,String id) {
		tCustomerBasedMobileDetailProductData dt=new tCustomerBasedMobileDetailProductData();
		List<tCustomerBasedMobileDetailProductData> contactList = new ArrayList<tCustomerBasedMobileDetailProductData>();
		String[] tableColumns = new String[] {
				dt.Property_intTrCustomerIdDetailProduct,
				dt.Property_intTrCustomerIdDetail,
				dt.Property_txtProductBrandCode,
				dt.Property_txtProductBrandName,
				dt.Property_txtProductCompetitorCode,
				dt.Property_txtProductCompetitorName,
				dt.Property_txtProductBrandQty,
				dt.Property_txtProductBrandCodeCRM,
				dt.Property_txtLOB,
				dt.Property_bitActive,
				dt.Property_dtInserted,
				dt.Property_dtUpdated,
				dt.Property_txtInsertedBy,
				dt.Property_txtUpdatedBy
		};
		String whereClause = dt.Property_intTrCustomerIdDetail + "=?";
		String[] whereArgs = new String[] {
				String.valueOf(id)
		};
		String groupBy = null;
		String havingBy = null;
		String orderBy = dt.Property_dtInserted + " desc";

		Cursor cursor = db.query(TABLE_NAME,
				tableColumns,
				whereClause,
				whereArgs,
				groupBy,
				havingBy,
				orderBy);

		if (cursor.moveToFirst()) {
			do {
				tCustomerBasedMobileDetailProductData contact = new tCustomerBasedMobileDetailProductData();
				contact.set_intTrCustomerIdDetailProduct(cursor.getString(0));
				contact.set_intTrCustomerIdDetail(cursor.getString(1));
				contact.set_txtProductBrandCode(cursor.getString(2));
				contact.set_txtProductBrandName(cursor.getString(3));
				contact.set_txtProductCompetitorCode(cursor.getString(4));
				contact.set_txtProductCompetitorName(cursor.getString(5));
				contact.set_txtProductBrandQty(cursor.getString(6));
				contact.set_txtProductBrandCodeCRM(cursor.getString(7));
				contact.set_txtLOB(cursor.getString(8));
				contact.set_bitActive(cursor.getString(9));
				contact.set_dtInserted(cursor.getString(10));
				contact.set_dtUpdated(cursor.getString(11));
				contact.set_txtInsertedBy(cursor.getString(12));
				contact.set_txtUpdatedBy(cursor.getString(13));
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return contactList;
	}

	// Getting All Contacts
	public List<tCustomerBasedMobileDetailProductData> getAllData(SQLiteDatabase db) {
		List<tCustomerBasedMobileDetailProductData> contactList = new ArrayList<tCustomerBasedMobileDetailProductData>();
		// Select All Query
		tCustomerBasedMobileDetailProductData dt=new tCustomerBasedMobileDetailProductData();
		String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_NAME;

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				tCustomerBasedMobileDetailProductData contact = new tCustomerBasedMobileDetailProductData();
				contact.set_intTrCustomerIdDetailProduct(cursor.getString(0));
				contact.set_intTrCustomerIdDetail(cursor.getString(1));
				contact.set_txtProductBrandCode(cursor.getString(2));
				contact.set_txtProductBrandName(cursor.getString(3));
				contact.set_txtProductCompetitorCode(cursor.getString(4));
				contact.set_txtProductCompetitorName(cursor.getString(5));
				contact.set_txtProductBrandQty(cursor.getString(6));
				contact.set_txtProductBrandCodeCRM(cursor.getString(7));
				contact.set_txtLOB(cursor.getString(8));
				contact.set_bitActive(cursor.getString(9));
				contact.set_dtInserted(cursor.getString(10));
				contact.set_dtUpdated(cursor.getString(11));
				contact.set_txtInsertedBy(cursor.getString(12));
				contact.set_txtUpdatedBy(cursor.getString(13));
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}

	public List<tCustomerBasedMobileDetailProductData> getPushData(SQLiteDatabase db, List<tCustomerBasedMobileDetailData> ListOftCustomerBasedMobileDetail) {
		List<tCustomerBasedMobileDetailProductData> contactList = null;

		String tCustomerBasedMobileDetail = "()";

		if (ListOftCustomerBasedMobileDetail != null) {
			tCustomerBasedMobileDetail = "(";
			for (int i = 0; i < ListOftCustomerBasedMobileDetail.size(); i++) {
				tCustomerBasedMobileDetail = tCustomerBasedMobileDetail + "'" + ListOftCustomerBasedMobileDetail.get(i).get_intTrCustomerIdDetail() + "'";

				tCustomerBasedMobileDetail = tCustomerBasedMobileDetail + ((i + 1) != ListOftCustomerBasedMobileDetail.size() ? "," : ")");
			}
		}

		// Select All Query
		tCustomerBasedMobileDetailProductData dt = new tCustomerBasedMobileDetailProductData();
		String selectQuery = "SELECT " + dt.Property_ALL + " FROM " + TABLE_NAME + " WHERE " + dt.Property_intTrCustomerIdDetail + " IN " + tCustomerBasedMobileDetail;

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tCustomerBasedMobileDetailProductData>();
			do {
				tCustomerBasedMobileDetailProductData contact = new tCustomerBasedMobileDetailProductData();
				contact.set_intTrCustomerIdDetailProduct(cursor.getString(0));
				contact.set_intTrCustomerIdDetail(cursor.getString(1));
				contact.set_txtProductBrandCode(cursor.getString(2));
				contact.set_txtProductBrandName(cursor.getString(3));
				contact.set_txtProductCompetitorCode(cursor.getString(4));
				contact.set_txtProductCompetitorName(cursor.getString(5));
				contact.set_txtProductBrandQty(cursor.getString(6));
				contact.set_txtProductBrandCodeCRM(cursor.getString(7));
				contact.set_txtLOB(cursor.getString(8));
				contact.set_bitActive(cursor.getString(9));
				contact.set_dtInserted(cursor.getString(10));
				contact.set_dtUpdated(cursor.getString(11));
				contact.set_txtInsertedBy(cursor.getString(12));
				contact.set_txtUpdatedBy(cursor.getString(13));
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}

	public void deleteByID(SQLiteDatabase db,String id) {
		tCustomerBasedMobileDetailProductData dt = new tCustomerBasedMobileDetailProductData();
		String whereClause =dt.Property_intTrCustomerIdDetailProduct + " = ?";
		String[] whereArgs = new String[] {
				String.valueOf(id)
		};

		db.delete(TABLE_NAME, whereClause,whereArgs);
	}

	public void deleteByIDDetail(SQLiteDatabase db,String id) {
		tCustomerBasedMobileDetailProductData dt = new tCustomerBasedMobileDetailProductData();
		String whereClause =dt.Property_intTrCustomerIdDetail + " = ?";
		String[] whereArgs = new String[] {
				String.valueOf(id)
		};

		db.delete(TABLE_NAME, whereClause,whereArgs);
	}

	public void DeleteAllDAta(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DELETE FROM " + TABLE_NAME);
	}

	// Getting contacts Count
	public int getContactsCount(SQLiteDatabase db) {
		String countQuery = "SELECT * FROM " + TABLE_NAME;
		Cursor cursor = db.rawQuery(countQuery, null);
		int num =cursor.getCount();
		cursor.close();

		// return count
		return num;
	}

	public List<tCustomerBasedMobileDetailData> getContactsCountProdCompIsNull(SQLiteDatabase db, String txtId) {
		List<tCustomerBasedMobileDetailData> retList = null;
		String countQuery = "select b.* from tCustomerBasedMobileHeader a\n" +
				"left join tCustomerBasedMobileDetail b on a.intTrCustomerId=b.intTrCustomerId\n" +
				"left join tCustomerBasedMobileDetailProduct c on b.intTrCustomerIdDetail=c.intTrCustomerIdDetail\n" +
				"left join mTypeSubmissionMobile d on a.txtSubmissionCode=d.txtMasterID\n" +
				"where \n" +
				"a.bitActive='0' and\n" +
//				"--a.intTrCustomerId='73cecf62-fa52-49e8-999f-e4152bdfc5ae' and a.bitActive='1' and txtSubmissionCode='0001' \n" +
				"b.intTrCustomerId ='" + txtId + "'"+ " and\n" +
				"c.txtProductCompetitorCode = 'null' or c.txtProductCompetitorCode = ''";
		Cursor cursor = db.rawQuery(countQuery, null);
		if(cursor.moveToFirst()){
			retList = new ArrayList<tCustomerBasedMobileDetailData>();
			do {
				tCustomerBasedMobileDetailData contact = new tCustomerBasedMobileDetailData();
				contact.set_intTrCustomerIdDetail(cursor.getString(0));
				contact.set_intTrCustomerId(cursor.getString(1));
				contact.set_txtNamaDepan(cursor.getString(2));
				contact.set_txtGender(cursor.getString(3));
				contact.set_txtTglLahir(cursor.getString(4));
				contact.set_txtUsiaKehamilan(cursor.getString(5));
				contact.set_intNo(cursor.getString(6));
				contact.set_intPIC(cursor.getString(7));
				contact.set_bitActive(cursor.getString(8));
				contact.set_dtInserted(cursor.getString(9));
				contact.set_dtUpdated(cursor.getString(10));
				contact.set_txtInsertedBy(cursor.getString(11));
				contact.set_txtUpdatedBy(cursor.getString(12));
				contact.set_intAge(cursor.getString(13));
				contact.set_intAgeTypeFlag(cursor.getString(14));
				retList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();

		// return count
		return retList;
	}

}
