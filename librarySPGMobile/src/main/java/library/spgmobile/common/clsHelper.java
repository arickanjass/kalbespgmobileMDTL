package library.spgmobile.common;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.UUID;

import library.spgmobile.dal.KoordinasiOutletDA;
import library.spgmobile.dal.KoordinasiOutletImageDA;
import library.spgmobile.dal.clsFileAttach_mobileDA;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.clsLogReceiverDetail_mobileDA;
import library.spgmobile.dal.clsLogReceiverHeader_mobileDA;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.mCategoryVisitPlanDA;
import library.spgmobile.dal.mCountConsumerMTDDA;
import library.spgmobile.dal.mCounterNumberDA;
import library.spgmobile.dal.mDownloadMasterData_mobileDA;
import library.spgmobile.dal.mEmployeeAreaDA;
import library.spgmobile.dal.mEmployeeBranchDA;
import library.spgmobile.dal.mEmployeeSalesProductDA;
import library.spgmobile.dal.mKategoriDA;
import library.spgmobile.dal.mListJawabanDA;
import library.spgmobile.dal.mMenuDA;
import library.spgmobile.dal.mNotificationDA;
import library.spgmobile.dal.mParentDA;
import library.spgmobile.dal.mPertanyaanDA;
import library.spgmobile.dal.mPriceInOutletDA;
import library.spgmobile.dal.mProductBarcodeDA;
import library.spgmobile.dal.mProductBrandHeaderDA;
import library.spgmobile.dal.mProductCompetitorDA;
import library.spgmobile.dal.mProductPICDA;
import library.spgmobile.dal.mProductSPGDA;
import library.spgmobile.dal.mTypeLeaveMobileDA;
import library.spgmobile.dal.mTypePertanyaanDA;
import library.spgmobile.dal.mTypeSubmissionMobileDA;
import library.spgmobile.dal.mUserRoleDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tAbsenUserDA;
import library.spgmobile.dal.tActivityDA;
import library.spgmobile.dal.tActivityMobileDA;
import library.spgmobile.dal.tCustomerBasedMobileDetailDA;
import library.spgmobile.dal.tCustomerBasedMobileDetailProductDA;
import library.spgmobile.dal.tCustomerBasedMobileHeaderDA;
import library.spgmobile.dal.tDeviceInfoUserDA;
import library.spgmobile.dal.tDisplayPictureDA;
import library.spgmobile.dal.tJawabanUserDA;
import library.spgmobile.dal.tLeaveMobileDA;
import library.spgmobile.dal.tNotificationDA;
import library.spgmobile.dal.tPurchaseOrderDetailDA;
import library.spgmobile.dal.tPurchaseOrderHeaderDA;
import library.spgmobile.dal.tSalesProductDetailDA;
import library.spgmobile.dal.tSalesProductHeaderDA;
import library.spgmobile.dal.tSalesProductQuantityDetailDA;
import library.spgmobile.dal.tSalesProductQuantityHeaderDA;
import library.spgmobile.dal.tSalesProductQuantityImageDA;
import library.spgmobile.dal.tSubTypeActivityDA;
import library.spgmobile.dal.tUserLoginDA;
import library.spgmobile.dal.tVisitPlanHeader_MobileDA;
import library.spgmobile.dal.tVisitPlanRealisasiDA;
import library.spgmobile.dal.trackingLocationDA;

public class clsHelper {
	public void InitlizeDB(){
		SQLiteDatabase db;
		clsHardCode clsdthc= new clsHardCode();
		db=SQLiteDatabase.openOrCreateDatabase(clsdthc.txtDatabaseName, null);
		mconfigDA _mconfigDA=new mconfigDA(db);
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		tDisplayPictureDA _tDisplayPictureDA = new tDisplayPictureDA(db);
		_mconfigDA.DropTable(db);
		_tUserLoginDA.DropTable(db);
		_mconfigDA=new mconfigDA(db);
		_tUserLoginDA=new tUserLoginDA(db);
		_tDisplayPictureDA= new tDisplayPictureDA(db);
		db.close();
	}
	public String GenerateGuid(){
		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		return randomUUIDString;
	}
	public String PushErrorFile(String urlToRead, String DataJson, Integer intTimeOut, HashMap<String, String> ListOfDataFile) {
		String charset = "UTF-8";
		File uploadFile1 = null;
		String requestURL = urlToRead;
		String Result = "";
		clsHelper _clsClsHelper = new clsHelper();
		clsHardCode _path = new clsHardCode();
		File folder = new File(_path.txtPathApp);
		try {
			MultipartUtility multipart = new MultipartUtility(requestURL, charset, intTimeOut);

			//multipart.addHeaderField("User-Agent", "CodeJava");
			//multipart.addHeaderField("DataHeader", DataJson);

			multipart.addFormField("dataField", DataJson);
			//multipart.addFormField("keywords", "Java,upload,Spring");

			for (Entry<String, String> entry : ListOfDataFile.entrySet()) {
				String key = entry.getKey();
//                String value = entry.getValue();

//				byte [] array = entry.getValue();
				File file = new File(entry.getValue());
//				FileOutputStream out = new FileOutputStream( file );
//				out.write( array );
//				out.close();

				multipart.addFilePart(key, new File(file.getAbsolutePath()));
			}
			List<String> response = multipart.finish();
			//System.out.println("SERVER REPLIED:");

			for (String line : response) {
				Result += line;
				System.out.println(line);
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}

//		if (folder.isDirectory())
//		{
//			String[] children = folder.list();
//			for (int i = 0; i < children.length; i++)
//			{
//				new File(folder, children[i]).delete();
//			}
//			folder.delete();
//		}
		return _clsClsHelper.ResultJsonData(Result);
	}
	public String PushDataWithFile(String urlToRead,String DataJson,Integer intTimeOut,HashMap<String,byte[]> ListOfDataFile){
		String charset = "UTF-8";

		String requestURL = urlToRead;
		String Result="";
		clsHelper _clsClsHelper = new clsHelper();

		File folder = new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata");
		folder.mkdir();

		try {
			MultipartUtility multipart = new MultipartUtility(requestURL, charset,intTimeOut);

			//multipart.addHeaderField("User-Agent", "CodeJava");
			//multipart.addHeaderField("DataHeader", DataJson);

			multipart.addFormField("dataField",DataJson);
			//multipart.addFormField("keywords", "Java,upload,Spring");

			for(Entry<String, byte[]> entry : ListOfDataFile.entrySet()) {
				String key = entry.getKey();
//                String value = entry.getValue();

				byte [] array = entry.getValue();
				File file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
				FileOutputStream out = new FileOutputStream( file );
				out.write( array );
				out.close();

				multipart.addFilePart(key, new File(file.getAbsolutePath()));
			}
			List<String> response = multipart.finish();
			//System.out.println("SERVER REPLIED:");

			for (String line : response) {
				Result+=line;
				System.out.println(line);
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}

		if (folder.isDirectory())
		{
			String[] children = folder.list();
			for (int i = 0; i < children.length; i++)
			{
				new File(folder, children[i]).delete();
			}
			folder.delete();
		}

		return _clsClsHelper.ResultJsonData(Result);
	}
	public String PushDataWithFile(String urlToRead,String DataJson,Integer intTimeOut,String File1,String File2){
		String charset = "UTF-8";
		File uploadFile1 = null;
		File uploadFile2 = null;
		if(File1.contains("file:")){
			uploadFile1 = new File(File1.substring(7));
		}

		if(File2.contains("file:")){
			uploadFile2 = new File(File2.substring(7));
		}

		String requestURL = urlToRead;
		String Result="";
		clsHelper _clsClsHelper = new clsHelper();
		try {
			MultipartUtility multipart = new MultipartUtility(requestURL, charset,intTimeOut);

			multipart.addHeaderField("User-Agent", "CodeJava");
			multipart.addHeaderField("DataHeader", DataJson);

			multipart.addFormField("dataField",DataJson);
			multipart.addFormField("keywords", "Java,upload,Spring");
			if(uploadFile1 != null){
				if(uploadFile1.exists()){
					multipart.addFilePart("fileUpload1", uploadFile1);
				}
			}
			if(uploadFile2 != null){
				if(uploadFile2.exists()){
					multipart.addFilePart("fileUpload2", uploadFile2);
				}
			}
			List<String> response = multipart.finish();

			System.out.println("SERVER REPLIED:");

			for (String line : response) {
				Result+=line;
				System.out.println(line);
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
		return _clsClsHelper.ResultJsonData(Result);
	}
	public String pushtData(String urlToRead,String DataJson,Integer intTimeOut) {
		//notify("asa","asda","asdas");
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		clsHelper _clsClsHelper = new clsHelper();
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(intTimeOut);
			conn.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
			conn.setRequestProperty("Accept","*/*");
			String param=DataJson;
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setFixedLengthStreamingMode(param.getBytes().length);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("charset", "utf-8");
			//conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.close();
			String response= "";
			Scanner inStream = new Scanner(conn.getInputStream());
			while(inStream.hasNextLine())
			{
				response+=(inStream.nextLine());
			}
			conn.disconnect();
			result=_clsClsHelper.ResultJsonData(response);
		} catch (IOException e) {
			result=e.getMessage();
		} catch (Exception e) {
			result=e.getMessage();
		}
		return result;
	}

	public void DeleteAllDB(SQLiteDatabase db){
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		tSalesProductHeaderDA _tSalesProductHeaderDA=new tSalesProductHeaderDA(db);
		tSalesProductDetailDA _tSalesProductDetailDA=new tSalesProductDetailDA(db);
		tPurchaseOrderDetailDA _tPurchaseOrderDetailDA = new tPurchaseOrderDetailDA(db);
		tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(db);
		tDeviceInfoUserDA _tDeviceInfoUserDA=new tDeviceInfoUserDA(db);
		mProductBrandHeaderDA _mProductBrandHeaderDA=new mProductBrandHeaderDA(db);
		mNotificationDA _mNotificationDA=new mNotificationDA(db);
		mEmployeeAreaDA _mEmployeeAreaDA=new mEmployeeAreaDA(db);
		mEmployeeBranchDA _mEmployeeBranchDA=new mEmployeeBranchDA(db);
		mEmployeeSalesProductDA _mEmployeeSalesProductDA=new mEmployeeSalesProductDA(db);
		mCounterNumberDA _mCounterNumberDA=new mCounterNumberDA(db);
		clsFileAttach_mobileDA _clsFileAttach_mobileDA = new clsFileAttach_mobileDA(db);
		clsLogReceiverHeader_mobileDA _clsLogReceiverHeader_mobileDA = new clsLogReceiverHeader_mobileDA(db);
		clsLogReceiverDetail_mobileDA _clsLogReceiverDetail_mobileDA = new clsLogReceiverDetail_mobileDA(db);
		tNotificationDA _tNotificationDA=new tNotificationDA(db);
		tActivityDA _tActivityDA=new tActivityDA(db);
		tActivityMobileDA _tActivityMobileDA=new tActivityMobileDA(db);
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		tLeaveMobileDA _tLeaveMobileDA=new tLeaveMobileDA(db);
		mMenuDA _mMenuDA=new mMenuDA(db);
		mTypeLeaveMobileDA _mTypeLeaveMobileDA=new mTypeLeaveMobileDA(db);
		mUserRoleDA _mUserRoleDA=new mUserRoleDA(db);
		mPriceInOutletDA _mPriceInOutletDA=new mPriceInOutletDA(db);
		mProductBarcodeDA _mProductBarcodeDA=new mProductBarcodeDA(db);
		tDisplayPictureDA _tDisplayPictureDA = new tDisplayPictureDA(db);
		mDownloadMasterData_mobileDA _mDownloadMasterData_mobileDA = new mDownloadMasterData_mobileDA(db);
		mParentDA _mParentDA = new mParentDA(db);
		mKategoriDA _mKategoriDA = new mKategoriDA(db);
		mTypePertanyaanDA _mTypePertanyaanDA = new mTypePertanyaanDA(db);
		mListJawabanDA _mListJawabanDA = new mListJawabanDA(db);
		mPertanyaanDA _mPertanyaanDA = new mPertanyaanDA(db);
		tJawabanUserDA _tJawabanUserDA = new tJawabanUserDA(db);
		mCategoryVisitPlanDA _mMCategoryVisitPlanDA = new mCategoryVisitPlanDA(db);
		trackingLocationDA _trackingLocationDA = new trackingLocationDA(db);
		KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(db);
		KoordinasiOutletImageDA _KoordinasiOutletImageDA = new KoordinasiOutletImageDA(db);

		tVisitPlanHeader_MobileDA _tVisitPlanHeader_mobileDA = new tVisitPlanHeader_MobileDA(db);
		tVisitPlanRealisasiDA _tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
		mProductCompetitorDA _mProductCompetitorDA = new mProductCompetitorDA(db);
		mTypeSubmissionMobileDA _mTypeSubmissionMobileDA = new mTypeSubmissionMobileDA(db);
		mProductSPGDA _mProductSPGDA = new mProductSPGDA(db);
		mProductPICDA _mProductPICDA = new mProductPICDA(db);
		tSalesProductQuantityHeaderDA _tSalesProductQuantityDA = new tSalesProductQuantityHeaderDA(db);
		tSalesProductQuantityDetailDA _tSalesProductQuantityDetailDA = new tSalesProductQuantityDetailDA(db);
		tSalesProductQuantityImageDA _tSalesProductQuantityImageDA = new tSalesProductQuantityImageDA(db);
		tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(db);
		tCustomerBasedMobileDetailDA _tCustomerBasedMobileDetailDA = new tCustomerBasedMobileDetailDA(db);
		tCustomerBasedMobileDetailProductDA _tCustomerBasedMobileDetailProductDA = new tCustomerBasedMobileDetailProductDA(db);
		_tDisplayPictureDA = new tDisplayPictureDA(db);
		mCountConsumerMTDDA _mCountConsumerMTDDA = new mCountConsumerMTDDA(db);
		tSubTypeActivityDA _tSubTypeActivityDA = new tSubTypeActivityDA(db);

		_tSubTypeActivityDA.Droptable(db);
		_mMCategoryVisitPlanDA.DropTable(db);
		_tVisitPlanHeader_mobileDA.DropTable(db);
		_tVisitPlanRealisasiDA.DropTable(db);
		//_tDisplayPictureDA.DropTable(db);
		_mDownloadMasterData_mobileDA.DropTable(db);
		_mProductSPGDA.DropTable(db);
		_mProductPICDA.DropTable(db);
		_mCountConsumerMTDDA.DropTable(db);
		_mProductCompetitorDA.DropTable(db);
		_mTypeSubmissionMobileDA.DropTable(db);
		_tSalesProductQuantityDA.Droptable(db);
		_tSalesProductQuantityDetailDA.DropTable(db);
		_tSalesProductQuantityImageDA.Droptable(db);
		_tCustomerBasedMobileHeaderDA.DropTable(db);
		_tCustomerBasedMobileDetailDA.DropTable(db);
		_tCustomerBasedMobileDetailProductDA.DropTable(db);
		_clsFileAttach_mobileDA.DropTable(db);
		_clsLogReceiverHeader_mobileDA.DropTable(db);
		_clsLogReceiverDetail_mobileDA.DropTable(db);
		_tNotificationDA.DropTable(db);
		_mPriceInOutletDA.DropTable(db);
		_mProductBarcodeDA.DropTable(db);
		_mUserRoleDA.DropTable(db);
		_tLeaveMobileDA.DropTable(db);
		_mProductBrandHeaderDA.DropTable(db);
		_tActivityDA.DropTable(db);
		_tActivityMobileDA.DropTable(db);
		_mEmployeeAreaDA.DropTable(db);
		_mNotificationDA.DropTable(db);
		_mEmployeeBranchDA.DropTable(db);
		_mEmployeeSalesProductDA.DropTable(db);
		_tUserLoginDA.DropTable(db);
		_tSalesProductHeaderDA.DropTable(db);
		_tSalesProductDetailDA.DropTable(db);
		_tPurchaseOrderHeaderDA.DropTable(db);
		_tPurchaseOrderDetailDA.DropTable(db);
		_tDeviceInfoUserDA.DropTable(db);
		_mMenuDA.DropTable(db);
		_mCounterNumberDA.DropTable(db);
		_tAbsenUserDA.DropTable(db);
		_mTypeLeaveMobileDA.DropTable(db);
		_mParentDA.DropTable(db);
		_mKategoriDA.DropTable(db);
		_mTypePertanyaanDA.DropTable(db);
		_mListJawabanDA.DropTable(db);
		_mPertanyaanDA.DropTable(db);
		_tJawabanUserDA.DropTable(db);
		_trackingLocationDA.DropTable(db);
		_KoordinasiOutletDA.DropTable(db);
		_KoordinasiOutletImageDA.Droptable(db);

		_mMCategoryVisitPlanDA = new mCategoryVisitPlanDA(db);
		_tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
		_tVisitPlanHeader_mobileDA = new tVisitPlanHeader_MobileDA(db);
		_mProductCompetitorDA = new mProductCompetitorDA(db);
		_mTypeSubmissionMobileDA = new mTypeSubmissionMobileDA(db);
		_tSalesProductQuantityDA = new tSalesProductQuantityHeaderDA(db);
		_tSalesProductQuantityDetailDA = new tSalesProductQuantityDetailDA(db);
		_tSalesProductQuantityImageDA = new tSalesProductQuantityImageDA(db);
		_tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(db);
		_tCustomerBasedMobileDetailDA = new tCustomerBasedMobileDetailDA(db);
		_tCustomerBasedMobileDetailProductDA = new tCustomerBasedMobileDetailProductDA(db);
		_tDisplayPictureDA = new tDisplayPictureDA(db);
		_mDownloadMasterData_mobileDA = new mDownloadMasterData_mobileDA(db);
		_mParentDA = new mParentDA(db);
		_mKategoriDA = new mKategoriDA(db);
		_mTypePertanyaanDA = new mTypePertanyaanDA(db);
		_mListJawabanDA = new mListJawabanDA(db);
		_mPertanyaanDA = new mPertanyaanDA(db);
		_tJawabanUserDA = new tJawabanUserDA(db);
		_trackingLocationDA = new trackingLocationDA(db);
		_KoordinasiOutletDA = new KoordinasiOutletDA(db);
		_KoordinasiOutletImageDA = new KoordinasiOutletImageDA(db);
		new mProductSPGDA(db);
		new mProductPICDA(db);
		new mCountConsumerMTDDA(db);
		new tSubTypeActivityDA(db);

		_mPriceInOutletDA=new mPriceInOutletDA(db);
		_mUserRoleDA=new mUserRoleDA(db);
		_mTypeLeaveMobileDA=new mTypeLeaveMobileDA(db);
		_tLeaveMobileDA=new tLeaveMobileDA(db);
		_tUserLoginDA=new tUserLoginDA(db);
		_tSalesProductHeaderDA=new tSalesProductHeaderDA(db);
		_tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(db);
		_tPurchaseOrderDetailDA = new tPurchaseOrderDetailDA(db);
		_tSalesProductDetailDA=new tSalesProductDetailDA(db);
		_tActivityDA=new tActivityDA(db);
		_tActivityMobileDA=new tActivityMobileDA(db);
		_tDeviceInfoUserDA=new tDeviceInfoUserDA(db);
		_mNotificationDA=new mNotificationDA(db);
		_mEmployeeAreaDA=new mEmployeeAreaDA(db);
		_mEmployeeBranchDA=new mEmployeeBranchDA(db);
		_mEmployeeSalesProductDA=new mEmployeeSalesProductDA(db);
		_mCounterNumberDA=new mCounterNumberDA(db);
		_tAbsenUserDA=new tAbsenUserDA(db);
		_mMenuDA=new mMenuDA(db);
		_mProductBrandHeaderDA=new mProductBrandHeaderDA(db);
		_clsFileAttach_mobileDA = new clsFileAttach_mobileDA(db);
		_clsLogReceiverHeader_mobileDA = new clsLogReceiverHeader_mobileDA(db);
		_clsLogReceiverDetail_mobileDA = new clsLogReceiverDetail_mobileDA(db);
		_tNotificationDA=new tNotificationDA(db);
		clsHardCode clsdthc=new clsHardCode();
		clsHelper _clsHelper=new clsHelper();
		File dir = new File(clsdthc.txtPathUserData);
		_clsHelper.DeleteRecursive(dir);
		mconfigDA _mconfigDA = new mconfigDA(db);
		int sumdata = _mconfigDA.getContactsCount(db);
		if (sumdata == 0) {
			_mconfigDA.InsertDefaultMconfig(db);
		}

	}

	public String ResultJsonData(String dt){
		return dt.substring(16,dt.length()-2);
	}
	public JSONArray ResultJsonArray(String dt) throws ParseException{
		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(dt);
		JSONArray lang= (JSONArray) obj;
		return lang;
	}
	public String linkAPI(SQLiteDatabase db){
		//ambil linkapi Database sqllite
		mconfigDA _mconfigDA=new mconfigDA(db);
		String strVal2="";
		mconfigData dataAPI = _mconfigDA.getData(db,
				enumConfigData.ApiKalbe.getidConfigData());
		strVal2 = dataAPI.get_txtValue();
		if (dataAPI.get_txtValue() == "") {
			strVal2 = dataAPI.get_txtDefaultValue();
		}
		//ambil version dari webservices
		linkAPI dtlinkAPI=new linkAPI();
		return dtlinkAPI.QueryString(strVal2);
	}
	void DeleteRecursive(File fileOrDirectory) {
		if (fileOrDirectory.isDirectory())
			for (File child : fileOrDirectory.listFiles())
				DeleteRecursive(child);
		fileOrDirectory.delete();
	}
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// CREATE A MATRIX FOR THE MANIPULATION
		Matrix matrix = new Matrix();
		// RESIZE THE BIT MAP
		matrix.postScale(scaleWidth, scaleHeight);

		// "RECREATE" THE NEW BITMAP
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
				matrix, false);

		return resizedBitmap;
	}
	public Bitmap downloadFile(String fileUrl){
		Bitmap bmImg=null;
		URL myFileUrl =null;
		try {
			myFileUrl= new URL(fileUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bmImg = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bmImg;
	}
	public String getHTML(String urlToRead) throws Exception {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	public void createFolderUserData(){
		clsHardCode clsdthc = new clsHardCode();

		File appDirUser = new File(clsdthc.txtPathUserData);

		if(!appDirUser.exists() && !appDirUser.isDirectory())
		{
			// create empty directory
			if (appDirUser.mkdirs())
			{
				Log.i("CreateDir","App dir created");
			}
			else
			{
				Log.w("CreateDir","Unable to create app dir!");
			}
		}
		else
		{
			Log.i("CreateDir","App dir already exists");
		}
	}
	public void createFolderApp(){
		clsHardCode clsdthc = new clsHardCode();
		File appDir=new File(clsdthc.txtPathApp);

		if(!appDir.exists() && !appDir.isDirectory())
		{
			// create empty directory
			if (appDir.mkdirs())
			{
				Log.i("CreateDir","App dir created");
			}
			else
			{
				Log.w("CreateDir","Unable to create app dir!");
			}
		}
		else
		{
			Log.i("CreateDir","App dir already exists");
		}
	}
	public String generateNewId(String OldId,String Separator,String Length){
		String itemStyle = OldId;
		String[] split = itemStyle.split(Separator,0);
		String itemID = split[1];
		Long num0x= (long) 0 ;

		if(itemID.contains("0")){
			num0x = Long.valueOf(itemID.substring(itemID.indexOf("0")));
		}else{
			num0x = Long.valueOf(itemID);
		}
		String second = split[0]+Separator+String.format("%0"+Length+"d", num0x + 1);
		return second;
	}
	public static boolean externalMemoryAvailable() {
		return android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}

	public static String getAvailableInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return formatSize(availableBlocks * blockSize);
	}

	public static String getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return formatSize(totalBlocks * blockSize);
	}

	public static String getAvailableExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return formatSize(availableBlocks * blockSize);
		} else {
			return "-1";
		}
	}

	public static String getTotalExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return formatSize(totalBlocks * blockSize);
		} else {
			return "-1";
		}
	}

	public static String formatSize(long size) {
		String suffix = null;

		if (size >= 1024) {
			suffix = "KB";
			size /= 1024;
			if (size >= 1024) {
				suffix = "MB";
				size /= 1024;
			}
		}

		StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

		int commaOffset = resultBuffer.length() - 3;
		while (commaOffset > 0) {
			resultBuffer.insert(commaOffset, ',');
			commaOffset -= 3;
		}

		if (suffix != null) resultBuffer.append(suffix);
		return resultBuffer.toString();
	}
}
