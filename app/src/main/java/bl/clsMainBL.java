package bl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONObject;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import library.spgmobile.common.APIData;
import library.spgmobile.common.KoordinasiOutletData;
import library.spgmobile.common.KoordinasiOutletImageData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.clsRole;
import library.spgmobile.common.clsStatusMenuStart;
import library.spgmobile.common.dataJson;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mMenuData;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tActivityMobileData;
import library.spgmobile.common.tAttendanceUserData;
import library.spgmobile.common.tHirarkiBIS;
import library.spgmobile.common.tJawabanUserData;
import library.spgmobile.common.tKemasanRusakHeaderData;
import library.spgmobile.common.tLeaveMobileData;
import library.spgmobile.common.tOverStockHeaderData;
import library.spgmobile.common.tPOPStandardHeaderData;
import library.spgmobile.common.tPlanogramMobileData;
import library.spgmobile.common.tPurchaseOrderDetailData;
import library.spgmobile.common.tPurchaseOrderHeaderData;
import library.spgmobile.common.tSalesProductDetailData;
import library.spgmobile.common.tSalesProductHeaderData;
import library.spgmobile.common.tSalesProductQuantityDetailData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tSalesProductQuantityImageData;
import library.spgmobile.common.tStockInHandHeaderData;
import library.spgmobile.common.tTidakSesuaiPesananHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.tVisitPlanRealisasiData;
import library.spgmobile.common.trackingLocationData;
import library.spgmobile.dal.KoordinasiOutletDA;
import library.spgmobile.dal.KoordinasiOutletImageDA;
import library.spgmobile.dal.clsEnumDownloadData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.enumRole;
import library.spgmobile.dal.enumStatusMenuStart;
import library.spgmobile.dal.mCategoryKoordinasiOutletDA;
import library.spgmobile.dal.mCategoryVisitPlanDA;
import library.spgmobile.dal.mEmployeeAreaDA;
import library.spgmobile.dal.mEmployeeBranchDA;
import library.spgmobile.dal.mEmployeeSalesProductDA;
import library.spgmobile.dal.mKategoriDA;
import library.spgmobile.dal.mListJawabanDA;
import library.spgmobile.dal.mMenuDA;
import library.spgmobile.dal.mParentDA;
import library.spgmobile.dal.mPertanyaanDA;
import library.spgmobile.dal.mProductBrandHeaderDA;
import library.spgmobile.dal.mProductCompetitorDA;
import library.spgmobile.dal.mProductPICDA;
import library.spgmobile.dal.mProductSPGDA;
import library.spgmobile.dal.mTypeLeaveMobileDA;
import library.spgmobile.dal.mTypePertanyaanDA;
import library.spgmobile.dal.mTypeSubmissionMobileDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tAbsenUserDA;
import library.spgmobile.dal.tActivityDA;
import library.spgmobile.dal.tActivityMobileDA;
import library.spgmobile.dal.tAttendanceUserDA;
import library.spgmobile.dal.tCustomerBasedMobileHeaderDA;
import library.spgmobile.dal.tGroupQuestionMappingDA;
import library.spgmobile.dal.tHirarkiBISDA;
import library.spgmobile.dal.tJawabanUserDA;
import library.spgmobile.dal.tKategoryPlanogramMobileDA;
import library.spgmobile.dal.tKemasanRusakHeaderDA;
import library.spgmobile.dal.tLeaveMobileDA;
import library.spgmobile.dal.tOverStockHeaderDA;
import library.spgmobile.dal.tPOPStandardHeaderDA;
import library.spgmobile.dal.tPlanogramMobileDA;
import library.spgmobile.dal.tPurchaseOrderDetailDA;
import library.spgmobile.dal.tPurchaseOrderHeaderDA;
import library.spgmobile.dal.tSalesProductDetailDA;
import library.spgmobile.dal.tSalesProductHeaderDA;
import library.spgmobile.dal.tSalesProductQuantityDetailDA;
import library.spgmobile.dal.tSalesProductQuantityHeaderDA;
import library.spgmobile.dal.tSalesProductQuantityImageDA;
import library.spgmobile.dal.tStockInHandHeaderDA;
import library.spgmobile.dal.tSubTypeActivityDA;
import library.spgmobile.dal.tTidakSesuaiPesananHeaderDA;
import library.spgmobile.dal.tUserLoginDA;
import library.spgmobile.dal.tVisitPlanRealisasiDA;
import library.spgmobile.dal.trackingLocationDA;

public class clsMainBL {
	SQLiteDatabase db;
	public clsMainBL() {
		super();
		this.db = getDb();
	}
	
	public SQLiteDatabase getDb() {
		clsHardCode _clsHardCode;
		clsHelper _clsHelper=new clsHelper();
		_clsHardCode =new clsHardCode();
		_clsHelper.createFolderApp();
		String rootDB = _clsHardCode.txtDatabaseName;
		db=SQLiteDatabase.openOrCreateDatabase(rootDB, null);
		return db;
	}
	public String getLinkAPI(){
		this.db = getDb();
		String txtLinkAPI=new mconfigDA(db).getLinkAPIData(db);
		this.db.close();
		return txtLinkAPI;
	}
	public String getTypeMobile(){
		this.db = getDb();
		String txtLinkAPI=new mconfigDA(db).getTypeMobile(db);
		this.db.close();
		return txtLinkAPI;
	}
	public String getLIVE(){
		this.db = getDb();
		String txtLinkAPI=new mconfigDA(db).getLIVE(db);
		this.db.close();
		return txtLinkAPI;
	}
	public String getBackGroundServiceOnline(){
		this.db = getDb();
		String valueBackGroundServiceOnline=new mconfigDA(db).getBackGroundServiceOnlineData(db);
		this.db.close();
		return valueBackGroundServiceOnline;
	}
	public tUserLoginData getUserActive() {
		this.db = getDb();
		List<tUserLoginData> listData= new ArrayList<>();
		tUserLoginData data = new tUserLoginData();
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		listData=_tUserLoginDA.getUserLoginNow(db);
		if (listData.size()==0){
			listData=_tUserLoginDA.getAllData(db);
		}
		db.close();
		if(listData!=null){
			data = listData.get(0);
		}
		return data;
	}
	public String GenerateGuid(){
		 UUID uuid = UUID.randomUUID();
		 String randomUUIDString = uuid.toString();
		 return randomUUIDString;
	 }
	public String GenerateGuid(Context context){
		DeviceUuidFactory _DeviceUuidFactory=new DeviceUuidFactory(context);
		return _DeviceUuidFactory.getDeviceUuid().toString();
	 }
	public clsStatusMenuStart checkUserActive() throws ParseException{
		this.db = getDb();
		tSalesProductHeaderDA _tSalesProductHeaderDA=new tSalesProductHeaderDA(db);
		tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(db);
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
    	tActivityDA _tActivityDA=new tActivityDA(db);
    	tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
    	tLeaveMobileDA _tLeaveMobileDA=new tLeaveMobileDA(db);
		tVisitPlanRealisasiDA _tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
		tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(db);
		tSalesProductQuantityHeaderDA _tSalesProductQuantityHeaderDA = new tSalesProductQuantityHeaderDA(db);
		tKemasanRusakHeaderDA _tKemasanRusakHeaderDA = new tKemasanRusakHeaderDA(db);
		tOverStockHeaderDA _tOverStockHeaderDA = new tOverStockHeaderDA(db);
		tPlanogramMobileDA _tPlanogramMobileDA = new tPlanogramMobileDA(db);
		tActivityMobileDA _tActivityMobileDA = new tActivityMobileDA(db);
		tJawabanUserDA _tJawabanUserDA = new tJawabanUserDA(db);
		tPOPStandardHeaderDA _tPOPStandardHeaderDA = new tPOPStandardHeaderDA(db);
		KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(db);
		tTidakSesuaiPesananHeaderDA _tTidakSesuaiPesananHeaderDA = new tTidakSesuaiPesananHeaderDA(db);
		tAttendanceUserDA _tAttendanceUserDA = new tAttendanceUserDA(db);
    	mMenuDA _mMenuDA=new mMenuDA(db);
		tCustomerBasedMobileHeaderDA _TCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(db);

    	clsStatusMenuStart _clsStatusMenuStart =new clsStatusMenuStart();

    	if(_tUserLoginDA.CheckLoginNow(db)){
    		_clsStatusMenuStart.set_intStatus(enumStatusMenuStart.UserActiveLogin);
    	}else{

    		Boolean dvalid=false;

			//trans spg
			int countDataPushSalesProductHeader = _tSalesProductHeaderDA.getAllCheckToPushData(db);
			int countDataPushActivity = _tActivityDA.getAllCheckToPushData(db);
			int countDataPushCustomerBased = _TCustomerBasedMobileHeaderDA.getAllCheckPushData(db);
			int counDataSaveCustomerBased = _TCustomerBasedMobileHeaderDA.getAllDataSave(db);

			//trans md
			int listVisitplan = _tVisitPlanRealisasiDA.getAllCheckPushData(db);
			int listStockInHandSave = _tStockInHandHeaderDA.getAllCheckToPushDataSave(db);
			int listStockInHandSubmit = _tStockInHandHeaderDA.getAllCheckToPushDataSubmit(db);
			int listtKemasanRusakHeaderData = _tKemasanRusakHeaderDA.getAllCheckPushData(db);
			int listtOverStockHeader = _tOverStockHeaderDA.getAllCheckPushData(db);
			int listtPlanogramSave = _tPlanogramMobileDA.getAllCheckPushDataSave(db);
			int listtPlanogramSubmit = _tPlanogramMobileDA.getAllCheckPushDataSubmit(db);
			int listtTidakSesuaiPesananHeaderData = _tTidakSesuaiPesananHeaderDA.getAllCheckPushData(db);

			//trans tl
			int listJawabanUser = _tJawabanUserDA.getAllCheckPushData(db);
			int listPOPStandard = _tPOPStandardHeaderDA.getAllCheckPushData(db);
			int listKoordinasiOutlet = _KoordinasiOutletDA.getAllCheckPushData(db);

			//trans fpe
			int listAttendanceUser = _tAttendanceUserDA.getAllCheckPushData(db);

			//trans spg dan tl
			int countDataPushAbsenUser = _tAbsenUserDA.getAllCheckToPushData(db);

			//trans md dan tl
			int listtSalesProductQuantity = _tSalesProductQuantityHeaderDA.getAllCheckPushData(db);
			int listtActivityMobile = _tActivityMobileDA.getAllCheckPushData(db);

			int listPODataPush = _tPurchaseOrderHeaderDA.getAllCheckToPushData(db);

			// trans spg,md,dan tl
			int countDataPushLeave = _tLeaveMobileDA.getAllCheckPushData(db);

			if (countDataPushAbsenUser > 0 && dvalid == false) {
				dvalid = true;
			}
			if (countDataPushSalesProductHeader > 0 && dvalid == false) {
				dvalid = true;
			}
			if (countDataPushActivity > 0 && dvalid == false) {
				dvalid = true;
			}
			if (countDataPushCustomerBased > 0 && dvalid == false) {
				dvalid = true;
			}
			if (counDataSaveCustomerBased > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listVisitplan > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listStockInHandSave > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listStockInHandSubmit > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listtKemasanRusakHeaderData > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listtOverStockHeader > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listtPlanogramSave > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listtPlanogramSubmit > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listtTidakSesuaiPesananHeaderData > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listJawabanUser > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listPOPStandard > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listKoordinasiOutlet > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listAttendanceUser > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listtSalesProductQuantity > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listtActivityMobile > 0 && dvalid == false) {
				dvalid = true;
			}
			if (listPODataPush > 0 && dvalid == false) {
				dvalid = true;
			}
			if (countDataPushLeave > 0 && dvalid == false) {
				dvalid = true;
			}
    		if(dvalid){
    			mMenuData listMenuData= _mMenuDA.getDataByName(db, "mnUploadDataMobile");
    			_clsStatusMenuStart.set_intStatus(enumStatusMenuStart.PushDataSPGMobile);
    			_clsStatusMenuStart.set_mMenuData(listMenuData);
    		}else{
        		new clsHelper().DeleteAllDB(db);
        		_clsStatusMenuStart.set_intStatus(enumStatusMenuStart.FormLogin);
    		}
    	}
    	this.db.close();
    	return _clsStatusMenuStart;
	}

	public String getValueItemDownloadData(String txtMasterData){
		int value = 0;
		String tmpvalue ="0";

//        List<trackingLocationData> listtrackingLocationData = new trackingLocationBL().getAllDataTrackingLocation();

		if(txtMasterData.equals(clsEnumDownloadData.ll_kategoriVisitPlan.name())){
			value = new mCategoryVisitPlanDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_dataVisitPlan.name())){
			value = new tVisitPlanRealisasiDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_branch.name())){
			value = new mEmployeeBranchDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_outlet.name())){
			value = new mEmployeeAreaDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_product.name())){
			value = new mEmployeeSalesProductDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_type_leave.name())){
			value = new mTypeLeaveMobileDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_brand.name())){
			value = new mProductBrandHeaderDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_reso.name())){
			value = new tSalesProductHeaderDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_data_reso.name())){
			value = new tSalesProductHeaderDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_data_stockIH.name())){
			value = new tStockInHandHeaderDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_customerbased.name())){
			value = new tCustomerBasedMobileHeaderDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_data_customerbased.name())){
			value = new tCustomerBasedMobileHeaderDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_activity.name())){
			value = new tActivityDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_data_activity.name())){
			value = new tActivityDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_data_activityV2.name())){
			value = new tActivityMobileDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_absen.name())){
			value = new tAbsenUserDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_data_attendance.name())){
			value = new tAttendanceUserDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_dataQuesioner.name())){
			int parent = new mParentDA(db).getContactsCount(db);
			int mPertanyaan = new mPertanyaanDA(db).getContactsCount(db);
			int typePertanyaanData = new mTypePertanyaanDA(db).getContactsCount(db);
			int tGroupQuestionMappingData = new tGroupQuestionMappingDA(db).getContactsCount(db);
			int kategoriData = new mKategoriDA(db).getContactsCount(db);
			if(parent>0&&mPertanyaan>0&&typePertanyaanData>0&&tGroupQuestionMappingData>0&&kategoriData>0){
				value = parent;
			}
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_dataSPG.name())){
			value = new tHirarkiBISDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_purchase_order.name())){
			value = new tPurchaseOrderHeaderDA(db).getContactCountPO(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_dataPurchaseOrder.name())){
			value = new tPurchaseOrderHeaderDA(db).getContactCountPO(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_dataQuantityStock.name())){
			value = new tSalesProductQuantityHeaderDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_data_overStock.name())){
			value = new tOverStockHeaderDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_product_competitor.name())){
			value = new mProductCompetitorDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_dataKordinasiOutlet.name())){
			value = new KoordinasiOutletDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_CategoryKoordinasiOutlet.name())){
			value = new mCategoryKoordinasiOutletDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_type_submission.name())){
			value = new mTypeSubmissionMobileDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_product_pic.name())){
			value = new mProductPICDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_subtypeactivity.name())){
			value = new tSubTypeActivityDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_data_planogram.name())){
			value = new tPlanogramMobileDA(db).getContactsCount(db);
		}
		if(txtMasterData.equals(clsEnumDownloadData.ll_kategoryPlanogram.name())){
			value = new tKategoryPlanogramMobileDA(db).getContactsCount(db);
		}

		return String.valueOf(value);
	}

	public clsRole checkUserRole() throws ParseException{
		clsRole _clsRole = new clsRole();
		tUserLoginData dt = new tUserLoginBL().getUserActive();
		if(dt!=null){
			if(dt.get_txtRoleId().equals(enumRole.MDMobile)){
				_clsRole.set_txtRoleId(enumRole.MDMobile);
			}
		}
		return _clsRole;
	}
	public dataJson GetAllPushData(String VersionName) throws Exception{
		SQLiteDatabase db=getDb();
		dataJson dtJson=new dataJson();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		tLeaveMobileDA _tLeaveMobileDA=new tLeaveMobileDA(db);

		db.close();
		return dtJson;
	}
	public void PushData(String VersionName) throws Exception{
		SQLiteDatabase _db=getDb();
		mconfigDA _mconfigDA =new mconfigDA(_db);
		String _StrLINKAPI="";
		mconfigData dataAPI = _mconfigDA.getData(_db,enumConfigData.ApiKalbe.getidConfigData());
		_StrLINKAPI = dataAPI.get_txtValue();
		if (dataAPI.get_txtValue() == "") {
			_StrLINKAPI = dataAPI.get_txtDefaultValue();
		}
		clsHelper _help =new clsHelper();
		String root = new clsHardCode().txtPathUserData;
		File myDir = new File(root);
		myDir.mkdirs();
		linkAPI dtlinkAPI=new linkAPI();
		String txtMethod="PushDataTabsenUser";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(VersionName);
		String strLinkAPI= dtlinkAPI.QueryString(_StrLINKAPI);
		
		dataAPI = _mconfigDA.getData(_db,enumConfigData.BackGroundServiceOnline.getidConfigData());
		String TimeOut = dataAPI.get_txtValue();
		if (dataAPI.get_txtValue() == "") {
			TimeOut = dataAPI.get_txtDefaultValue();
		}
		
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(_db);
		List<tAbsenUserData> listDataAbsen= _tAbsenUserDA.getAllDataToPushData(_db);
		if(listDataAbsen != null){
			for (tAbsenUserData dataAbsen : listDataAbsen) {
				List<tAbsenUserData> tmplistDataAbsen=new ArrayList<tAbsenUserData>();
				tmplistDataAbsen.add(dataAbsen);
				dataJson Json= new dataJson();
				Json.setIntResult("1");
				Json.setListOftAbsenUserData(tmplistDataAbsen);
				//String Html= new clsHelper().pushtData(strLinkAPI,Json.txtJSON().toString(),Integer.valueOf(TimeOut));
				String Html= new clsHelper().PushDataWithFile(strLinkAPI,Json.txtJSON().toString(),Integer.valueOf(TimeOut),String.valueOf(dataAbsen.get_txtImg1()),String.valueOf(dataAbsen.get_txtImg2()));
				org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()) {
					APIData dtAPIDATA=new APIData();
					org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
					int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
					if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dataAbsen.set_intSync("1");
						dataAbsen.set_txtAbsen(String.valueOf(innerObj.get(dtAPIDATA.strArgument)));
						_tAbsenUserDA.SaveDatatAbsenUserData(_db, dataAbsen);
					}
				}
			}
		}

		dtlinkAPI = new linkAPI();
		txtMethod = "PushDataTHeaderPO";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtMethod(VersionName);
		strLinkAPI = dtlinkAPI.QueryString(_StrLINKAPI);

		tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(_db);
		List<tPurchaseOrderHeaderData> ListDataTPOHeader = _tPurchaseOrderHeaderDA.getAllDataToPushData(_db);
		if (ListDataTPOHeader != null) {
			for (tPurchaseOrderHeaderData dataHeader : ListDataTPOHeader) {
				dataJson Json = new dataJson();
				List<tPurchaseOrderHeaderData> tmpListDataPOHeaderData = new ArrayList<tPurchaseOrderHeaderData>();
				tmpListDataPOHeaderData.add(dataHeader);
				tAbsenUserData _tmpDataAbsen = _tAbsenUserDA.getData(_db, Integer.valueOf(dataHeader.get_intIdAbsenUser()));
				List<tAbsenUserData> tmpListDataUserAbsen = new ArrayList<tAbsenUserData>();
				tmpListDataUserAbsen.add(_tmpDataAbsen);
				tPurchaseOrderDetailDA _tPurchaseOrderDetailDA = new tPurchaseOrderDetailDA(_db);
				List<tPurchaseOrderDetailData> tmpListPurchaseOrderDetail = _tPurchaseOrderDetailDA.getPurchaseOrderDetailByHeaderId(_db, dataHeader.get_intId());
				if (tmpListPurchaseOrderDetail != null) {
					Json.setListOftPurchaseOrderDetailData(tmpListPurchaseOrderDetail);
				}
				Json.setListOftAbsenUserData(tmpListDataUserAbsen);
				Json.setListOftPurchaseOrderHeaderData(tmpListDataPOHeaderData);
				String Html = new clsHelper().pushtData(strLinkAPI, Json.txtJSON().toString(), Integer.valueOf(TimeOut));
				org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()){
					APIData dtAPIDATA = new APIData();
					JSONObject InnerObj = (JSONObject) i.next();
					int boolValid = Integer.valueOf(String.valueOf(InnerObj.get(dtAPIDATA.boolValid)));
					if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dataHeader.set_intSync("1");
						_tPurchaseOrderHeaderDA.SaveDatatPurchaseOrderHeaderData(_db, dataHeader);
					}
				}

			}
		}

		dtlinkAPI = new linkAPI();
		txtMethod = "PushDataTHeaderQuantityStock";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtMethod(VersionName);
		strLinkAPI = dtlinkAPI.QueryString(_StrLINKAPI);

		tSalesProductQuantityHeaderDA _tSalesProductQuantityHeaderDA = new tSalesProductQuantityHeaderDA(_db);
		List<tSalesProductQuantityHeaderData> ListDataTQuantityStockHeader = _tSalesProductQuantityHeaderDA.getAllDataToPushData(_db);
		if (ListDataTQuantityStockHeader != null) {
			for (tSalesProductQuantityHeaderData dataHeader : ListDataTQuantityStockHeader) {
				dataJson Json = new dataJson();
				List<tSalesProductQuantityHeaderData> tmpListDataQuantityStockHeaderData = new ArrayList<tSalesProductQuantityHeaderData>();
				tmpListDataQuantityStockHeaderData.add(dataHeader);
				tAbsenUserData _tmpDataAbsen = _tAbsenUserDA.getData(_db, Integer.valueOf(dataHeader.get_intIdAbsenUser()));
				List<tAbsenUserData> tmpListDataUserAbsen = new ArrayList<tAbsenUserData>();
				tmpListDataUserAbsen.add(_tmpDataAbsen);
				tSalesProductQuantityDetailDA _tSalesProductQuantityDetailDA = new tSalesProductQuantityDetailDA(_db);
				List<tSalesProductQuantityDetailData> tmpListQuantityStockDetail = _tSalesProductQuantityDetailDA.getSalesProductQuantityDetailByHeaderId(_db, dataHeader.get_txtQuantityStock());
				if (tmpListQuantityStockDetail != null){
					Json.setListOftSalesProductQuantityDetailData(tmpListQuantityStockDetail);
				}
				tSalesProductQuantityImageDA _tSalesProductQuantityImageDA = new tSalesProductQuantityImageDA(_db);
				List<tSalesProductQuantityImageData> tmpListQuantityStockImage = _tSalesProductQuantityImageDA.getSalesProductQuantityImageByHeaderId(_db, dataHeader.get_txtQuantityStock());
				if (tmpListQuantityStockImage != null){
					Json.setListOftSalesProductQuantityImageData(tmpListQuantityStockImage);
				}
				Json.setListOftAbsenUserData(tmpListDataUserAbsen);
				Json.setListOftSalesProductQuantityData(tmpListDataQuantityStockHeaderData);
				String Html = new clsHelper().pushtData(strLinkAPI, Json.txtJSON().toString(), Integer.valueOf(TimeOut));
				org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()){
					APIData dtAPIDATA = new APIData();
					JSONObject InnerObj = (JSONObject) i.next();
					int boolValid = Integer.valueOf(String.valueOf(InnerObj.get(dtAPIDATA.boolValid)));
					if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dataHeader.set_intSync("1");
						_tSalesProductQuantityHeaderDA.SaveDataSalesProductQuantityData(_db, dataHeader);
					}
				}
			}
		}

		dtlinkAPI = new linkAPI();
		txtMethod = "PushDataTrackingLocation";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtMethod(VersionName);
		strLinkAPI = dtlinkAPI.QueryString(_StrLINKAPI);

		trackingLocationDA _trackingLocationDA = new trackingLocationDA(_db);
		List<trackingLocationData> ListDataTrackingLocation = _trackingLocationDA.getAllDataToPushData(_db);
		if (ListDataTrackingLocation != null) {
			for (trackingLocationData dataHeader : ListDataTrackingLocation) {
				dataJson Json = new dataJson();
				List<trackingLocationData> tmpListDataTrackingLocationData = new ArrayList<trackingLocationData>();
				tmpListDataTrackingLocationData.add(dataHeader);
				Json.setListOfTrackingLocationData(tmpListDataTrackingLocationData);
				String Html = new clsHelper().pushtData(strLinkAPI, Json.txtJSON().toString(), Integer.valueOf(TimeOut));
				org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()){
					APIData dtAPIDATA = new APIData();
					JSONObject InnerObj = (JSONObject) i.next();
					int boolValid = Integer.valueOf(String.valueOf(InnerObj.get(dtAPIDATA.boolValid)));
					if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dataHeader.set_intSync("1");
						_trackingLocationDA.SaveDataTrackingLocation(_db, dataHeader);
					}
				}
			}
		}

		dtlinkAPI = new linkAPI();
		txtMethod = "PushDataKoordinasiOutlet";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtMethod(VersionName);
		strLinkAPI = dtlinkAPI.QueryString(_StrLINKAPI);

		KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(_db);
		List<KoordinasiOutletData> ListDataKoordinasiOutlet = _KoordinasiOutletDA.getAllDataToPushData(_db);
		if (ListDataKoordinasiOutlet != null) {
			for (KoordinasiOutletData dataHeader : ListDataKoordinasiOutlet) {
				dataJson Json = new dataJson();
				List<KoordinasiOutletData> tmpListDataKoordinasiOutletData = new ArrayList<KoordinasiOutletData>();
				tmpListDataKoordinasiOutletData.add(dataHeader);

				KoordinasiOutletImageDA _KoordinasiOutletImageDA = new KoordinasiOutletImageDA(_db);
				List<KoordinasiOutletImageData> tmpListKoordinasiOutletImage = _KoordinasiOutletImageDA.getDataHeaderId(_db, dataHeader.get_intId());
				if (tmpListKoordinasiOutletImage != null) {
					Json.setListOfKoordinasiOutletImageData(tmpListKoordinasiOutletImage);
				}
				Json.setListOfKoordinasiOutletData(tmpListDataKoordinasiOutletData);
				String Html = new clsHelper().pushtData(strLinkAPI, Json.txtJSON().toString(), Integer.valueOf(TimeOut));
				org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()){
					APIData dtAPIDATA = new APIData();
					JSONObject InnerObj = (JSONObject) i.next();
					int boolValid = Integer.valueOf(String.valueOf(InnerObj.get(dtAPIDATA.boolValid)));
					if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dataHeader.set_intSync("1");
						_KoordinasiOutletDA.SaveDataKoordinasiOutlet(_db, dataHeader);
					}
				}
			}
		}

			dtlinkAPI=new linkAPI();
			txtMethod="PushDataTHeaderSales";
			dtlinkAPI.set_txtMethod(txtMethod);
			dtlinkAPI.set_txtParam("");
			dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
			dtlinkAPI.set_txtVesion(VersionName);
			strLinkAPI= dtlinkAPI.QueryString(_StrLINKAPI);

			tSalesProductHeaderDA _tSalesProductHeaderDA=new tSalesProductHeaderDA(_db);
			List<tSalesProductHeaderData> ListDataTsalesHeader= _tSalesProductHeaderDA.getAllDataToPushData(_db);
			if(ListDataTsalesHeader!=null){
				for (tSalesProductHeaderData dataheader : ListDataTsalesHeader) {
					dataJson Json= new dataJson();
					List<tSalesProductHeaderData> tmplistDatatSalesProductHeaderData=new ArrayList<tSalesProductHeaderData>();
					tmplistDatatSalesProductHeaderData.add(dataheader);
					tAbsenUserData _tmpdataabsen= _tAbsenUserDA.getData(_db, Integer.valueOf(dataheader.get_intIdAbsenUser()));
					List<tAbsenUserData> tmpListDataUserAbsen=new ArrayList<tAbsenUserData>();
					tmpListDataUserAbsen.add(_tmpdataabsen);
					tSalesProductDetailDA _tSalesProductDetailDA=new tSalesProductDetailDA(_db);

				List<tSalesProductDetailData> tmpListSalesProductDetail= _tSalesProductDetailDA.getSalesProductDetailByHeaderId(_db, dataheader.get_intId());
				if(tmpListSalesProductDetail!=null){
					Json.setListOftSalesProductDetailData(tmpListSalesProductDetail);
				}
				Json.setListOftAbsenUserData(tmpListDataUserAbsen);
				Json.setListOftSalesProductHeaderData(tmplistDatatSalesProductHeaderData);
				String Html= new clsHelper().pushtData(strLinkAPI,Json.txtJSON().toString(),Integer.valueOf(TimeOut));
				org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()) {
					APIData dtAPIDATA=new APIData();
					org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
					int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
					if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dataheader.set_intSync("1");
						_tSalesProductHeaderDA.SaveDatatSalesProductHeaderData(_db, dataheader);
					}
				}
			}
		}
		
		dtlinkAPI=new linkAPI();
		txtMethod="SaveDataTActivityMobile";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(VersionName);
		strLinkAPI= dtlinkAPI.QueryString(_StrLINKAPI);
		
		tActivityDA _tActivityDA=new tActivityDA(_db);
		List<tActivityData> ListDatatActivityData= _tActivityDA.getAllDataToPushData(_db);
		if(ListDatatActivityData!=null){
			for (tActivityData dataActivity : ListDatatActivityData) {
				List<tActivityData> tmpListDatatActivityData=new ArrayList<tActivityData>();
				tmpListDatatActivityData.add(dataActivity);
				dataJson Json= new dataJson();
				Json.setIntResult("1");
				Json.setListOftActivityData(tmpListDatatActivityData);
				//
				String Html= new clsHelper().PushDataWithFile(strLinkAPI,Json.txtJSON().toString(),Integer.valueOf(TimeOut),String.valueOf(dataActivity.get_txtImg1()),String.valueOf(dataActivity.get_txtImg2()));
				org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()) {
					APIData dtAPIDATA=new APIData();
					org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
					int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
					if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dataActivity.set_intIdSyn(String.valueOf(innerObj.get(dtAPIDATA.strArgument)));
						_tActivityDA.SaveDatatActivityData(_db, dataActivity);
					}
				}
			}
		}
		
		dtlinkAPI=new linkAPI();
		txtMethod="APISaveDatatCustomerBase";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(VersionName);
		strLinkAPI= dtlinkAPI.QueryString(_StrLINKAPI);
		
		dtlinkAPI=new linkAPI();
		txtMethod="SaveDatatLeaveMobile";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(VersionName);
		strLinkAPI= dtlinkAPI.QueryString(_StrLINKAPI);
		
		tLeaveMobileDA _tLeaveMobileDA=new tLeaveMobileDA(_db);
		List<tLeaveMobileData> listoftLeaveMobileData= _tLeaveMobileDA.getAllDataPushData(_db);
		if(listoftLeaveMobileData!=null){
			for (tLeaveMobileData dttLeaveMobileData : listoftLeaveMobileData) {
				//_tLeaveMobileDA.deleteContact(_db, dttLeaveMobileData.get_intLeaveIdSync());
				List<tLeaveMobileData> tmpListDatatLeaveMobileData=new ArrayList<tLeaveMobileData>();
				tmpListDatatLeaveMobileData.add(dttLeaveMobileData);
				dataJson Json= new dataJson();
				Json.setIntResult("1");
				Json.setListOftLeaveMobileData(tmpListDatatLeaveMobileData);
				String Html= new clsHelper().pushtData(strLinkAPI,Json.txtJSON().toString(),Integer.valueOf(TimeOut));
				org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()) {
					APIData dtAPIDATA=new APIData();
					org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
					int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
					if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dttLeaveMobileData.set_intLeaveIdSync(String.valueOf(innerObj.get(dtAPIDATA.strArgument)));
						_tLeaveMobileDA.SaveDataMConfig(_db, dttLeaveMobileData);
					}
				}
			}
		}
		_db.close();
	}
}
