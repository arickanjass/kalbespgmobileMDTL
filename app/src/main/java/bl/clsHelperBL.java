package bl;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.Toast;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.KoordinasiOutletData;
import library.spgmobile.common.KoordinasiOutletImageData;
import library.spgmobile.common.clsFileAttach_mobile;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.clsLogReceiverDetail_mobile;
import library.spgmobile.common.clsLogReceiverHeader_mobile;
import library.spgmobile.common.clsMappingPushFile;
import library.spgmobile.common.clsPushData;
import library.spgmobile.common.dataJson;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mCategoryKoordinasiOutletData;
import library.spgmobile.common.mCategoryVisitPlanData;
import library.spgmobile.common.mCounterNumberData;
import library.spgmobile.common.mEmployeeAreaData;
import library.spgmobile.common.mEmployeeBranchData;
import library.spgmobile.common.mEmployeeSalesProductData;
import library.spgmobile.common.mParentData;
import library.spgmobile.common.mProductBrandHeaderData;
import library.spgmobile.common.mProductCompetitorData;
import library.spgmobile.common.mProductPICData;
import library.spgmobile.common.mProductSPGData;
import library.spgmobile.common.mTypeLeaveMobileData;
import library.spgmobile.common.mTypePOPStandardData;
import library.spgmobile.common.mTypeSubmissionMobile;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tActivityMobileData;
import library.spgmobile.common.tAttendanceUserData;
import library.spgmobile.common.tCustomerBasedMobileDetailData;
import library.spgmobile.common.tCustomerBasedMobileDetailProductData;
import library.spgmobile.common.tCustomerBasedMobileHeaderData;
import library.spgmobile.common.tHirarkiBIS;
import library.spgmobile.common.tJawabanUserData;
import library.spgmobile.common.tJawabanUserHeaderData;
import library.spgmobile.common.tKategoryPlanogramMobileData;
import library.spgmobile.common.tKemasanRusakDetailData;
import library.spgmobile.common.tKemasanRusakHeaderData;
import library.spgmobile.common.tKemasanRusakImageData;
import library.spgmobile.common.tLeaveMobileData;
import library.spgmobile.common.tLogErrorData;
import library.spgmobile.common.tNotificationData;
import library.spgmobile.common.tOverStockDetailData;
import library.spgmobile.common.tOverStockHeaderData;
import library.spgmobile.common.tPOPStandardDetailData;
import library.spgmobile.common.tPOPStandardHeaderData;
import library.spgmobile.common.tPlanogramImageData;
import library.spgmobile.common.tPlanogramMobileData;
import library.spgmobile.common.tPurchaseOrderDetailData;
import library.spgmobile.common.tPurchaseOrderHeaderData;
import library.spgmobile.common.tSalesProductDetailData;
import library.spgmobile.common.tSalesProductHeaderData;
import library.spgmobile.common.tSalesProductQuantityDetailData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tSalesProductQuantityImageData;
import library.spgmobile.common.tStockInHandDetailData;
import library.spgmobile.common.tStockInHandHeaderData;
import library.spgmobile.common.tSubTypeActivityData;
import library.spgmobile.common.tTidakSesuaiPesananHeaderData;
import library.spgmobile.common.tTidakSesuaiPesananImageData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.tVisitPlanHeader_MobileData;
import library.spgmobile.common.tVisitPlanRealisasiData;
import library.spgmobile.common.trackingLocationData;
import library.spgmobile.common.visitplanAbsenData;
import library.spgmobile.dal.KoordinasiOutletDA;
import library.spgmobile.dal.KoordinasiOutletImageDA;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.clsLogReceiverDetail_mobileDA;
import library.spgmobile.dal.clsLogReceiverHeader_mobileDA;
import library.spgmobile.dal.enumConfigData;
import library.spgmobile.dal.enumCounterData;
import library.spgmobile.dal.mCategoryKoordinasiOutletDA;
import library.spgmobile.dal.mCategoryPOPStandardDA;
import library.spgmobile.dal.mCounterNumberDA;
import library.spgmobile.dal.mReasonPOPStandardDA;
import library.spgmobile.dal.mTypePOPStandardDA;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tAbsenUserDA;
import library.spgmobile.dal.tActivityDA;
import library.spgmobile.dal.tActivityMobileDA;
import library.spgmobile.dal.tAttendanceUserDA;
import library.spgmobile.dal.tCustomerBasedMobileDetailDA;
import library.spgmobile.dal.tCustomerBasedMobileDetailProductDA;
import library.spgmobile.dal.tCustomerBasedMobileHeaderDA;
import library.spgmobile.dal.tHirarkiBISDA;
import library.spgmobile.dal.tJawabanUserDA;
import library.spgmobile.dal.tJawabanUserHeaderDA;
import library.spgmobile.dal.tKemasanRusakDetailDA;
import library.spgmobile.dal.tKemasanRusakHeaderDA;
import library.spgmobile.dal.tKemasanRusakImageDA;
import library.spgmobile.dal.tLeaveMobileDA;
import library.spgmobile.dal.tLogErrorDA;
import library.spgmobile.dal.tOverStockDetailDA;
import library.spgmobile.dal.tOverStockHeaderDA;
import library.spgmobile.dal.tPOPStandardDetailDA;
import library.spgmobile.dal.tPOPStandardHeaderDA;
import library.spgmobile.dal.tPlanogramImageDA;
import library.spgmobile.dal.tPlanogramMobileDA;
import library.spgmobile.dal.tPurchaseOrderDetailDA;
import library.spgmobile.dal.tPurchaseOrderHeaderDA;
import library.spgmobile.dal.tSalesProductDetailDA;
import library.spgmobile.dal.tSalesProductHeaderDA;
import library.spgmobile.dal.tSalesProductQuantityDetailDA;
import library.spgmobile.dal.tSalesProductQuantityHeaderDA;
import library.spgmobile.dal.tSalesProductQuantityImageDA;
import library.spgmobile.dal.tStockInHandDetailDA;
import library.spgmobile.dal.tStockInHandHeaderDA;
import library.spgmobile.dal.tTidakSesuaiPesananHeaderDA;
import library.spgmobile.dal.tTidakSesuaiPesananImageDA;
import library.spgmobile.dal.tUserLoginDA;
import library.spgmobile.dal.tVisitPlanHeader_MobileDA;
import library.spgmobile.dal.tVisitPlanRealisasiDA;
import library.spgmobile.dal.trackingLocationDA;

//import org.xml.sax.DTDHandler;
//import com.kalbe.salesforce.TableNotif;
//import com.kalbe.service.MyNotificationService;
//import android.content.Intent;
//import come.example.viewbadger.ShortcutBadger;

public class clsHelperBL extends clsMainBL {
    public void DeleteAllDB() {
        SQLiteDatabase db = getDb();
        new clsHelper().DeleteAllDB(db);
        db.close();
    }
    public void InsertDefaultMconfig() {
        SQLiteDatabase _db = getDb();
        mconfigDA _mconfigDA = new mconfigDA(_db);
        _mconfigDA.InsertDefaultMconfig(_db);
        _db.close();
    }
    public visitplanAbsenData getDataCheckInActive(){
        SQLiteDatabase db=getDb();
        tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
        tAbsenUserData dataAbsen;
        dataAbsen=_tAbsenUserDA.getDataCheckInActive(db);
        tVisitPlanRealisasiDA  _tTVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
        tVisitPlanRealisasiData dataVisit;
        dataVisit = _tTVisitPlanRealisasiDA.getDataCheckInActive(db);
        tAttendanceUserDA _tAttendanceUserDA = new tAttendanceUserDA(db);
        tAttendanceUserData dataAbsenFPE;
        dataAbsenFPE = _tAttendanceUserDA.getDataCheckInActive(db);
        visitplanAbsenData dataReturn = new visitplanAbsenData();
        if (dataAbsen == null && dataVisit.get_txtDataIDRealisasi() != null && dataAbsenFPE==null){
            dataReturn.set_txtId(dataVisit.get_txtDataIDRealisasi().toString());
            dataReturn.set_txtOutletCode(dataVisit.get_txtOutletCode().toString());
            dataReturn.set_txtOutletName(dataVisit.get_txtOutletName().toString());
            dataReturn.set_txtBranchCode(dataVisit.get_txtBranchCode().toString());
            dataReturn.set_txtBranchName(dataVisit.get_txtBranchName().toString());
            dataReturn.set_txtDeviceId(dataVisit.get_deviceId());

            dataReturn.set_dtDateCheckIn(dataVisit.get_dtDate().toString());
            dataReturn.set_dtDateCheckOut(dataVisit.get_dateCheckout().toString());
            dataReturn.set_intSubmit(dataVisit.get_intSubmit());
            dataReturn.set_intSync(dataVisit.get_intPush());
            dataReturn.set_txtAccuracy(dataVisit.get_txtAcc());
            dataReturn.set_txtLatitude(dataVisit.get_txtLat());
            dataReturn.set_txtLongitude(dataVisit.get_txtLong());
            byte[] blob1 = dataVisit.get_dtPhoto1();
            dataReturn.set_txtImg1(blob1);
            byte[] blob2 = dataVisit.get_dtPhoto2();
            dataReturn.set_txtUserId(dataVisit.get_intUserID());
            dataReturn.set_txtRoleId(dataVisit.get_txtRoleId());
            dataReturn.set_txtImg2(blob2);


            dataReturn.setType("visitPlan");
        } else if (dataAbsen != null && dataVisit.get_txtDataIDRealisasi() == null && dataAbsenFPE==null){
            dataReturn.set_txtId(dataAbsen.get_intId().toString());
            dataReturn.set_txtOutletCode(dataAbsen.get_txtOutletCode().toString());
            dataReturn.set_txtOutletName(dataAbsen.get_txtOutletName().toString());
            dataReturn.set_txtBranchCode(dataAbsen.get_txtBranchCode().toString());
            dataReturn.set_txtBranchName(dataAbsen.get_txtBranchName().toString());
            dataReturn.set_txtDeviceId(dataAbsen.get_txtDeviceId());

            dataReturn.set_dtDateCheckIn(dataAbsen.get_dtDateCheckIn().toString());
            dataReturn.set_dtDateCheckOut(dataAbsen.get_dtDateCheckOut().toString());
            dataReturn.set_intSubmit(dataAbsen.get_intSubmit());
            dataReturn.set_intSync(dataAbsen.get_intSync());
            dataReturn.set_txtAbsen(dataAbsen.get_txtAbsen());
            dataReturn.set_txtAccuracy(dataAbsen.get_txtAccuracy());
            dataReturn.set_txtLatitude(dataAbsen.get_txtLatitude());
            dataReturn.set_txtLongitude(dataAbsen.get_txtLongitude());
            if (dataAbsen.get_txtImg1() != null){
                byte[] blob1 = dataAbsen.get_txtImg1();
                dataReturn.set_txtImg1(blob1);
            }
            if (dataAbsen.get_txtImg2() != null){
                byte[] blob2 = dataAbsen.get_txtImg2();
                dataReturn.set_txtImg2(blob2);
            }
            dataReturn.set_txtUserId(dataAbsen.get_txtUserId());
            dataReturn.set_txtRoleId(dataAbsen.get_txtRoleId());

            dataReturn.setType("absen");
        } else if (dataAbsen == null && dataVisit.get_txtDataIDRealisasi() == null && dataAbsenFPE!=null){
            dataReturn.set_txtId(dataAbsenFPE.get_intId().toString());
            dataReturn.set_txtOutletCode(dataAbsenFPE.get_txtOutletCode().toString());
            dataReturn.set_txtOutletName(dataAbsenFPE.get_txtOutletName().toString());
            dataReturn.set_txtBranchCode(dataAbsenFPE.get_txtBranchCode().toString());
            dataReturn.set_txtBranchName(dataAbsenFPE.get_txtBranchName().toString());
            dataReturn.set_txtDeviceId(dataAbsenFPE.get_txtDeviceId());

            dataReturn.set_dtDateCheckIn(dataAbsenFPE.get_dtDateCheckIn().toString());
            dataReturn.set_dtDateCheckOut(dataAbsenFPE.get_dtDateCheckOut().toString());
            dataReturn.set_intSubmit(dataAbsenFPE.get_intSubmit());
            dataReturn.set_intSync(dataAbsenFPE.get_intSync());
            dataReturn.set_txtAbsen(dataAbsenFPE.get_txtAbsen());
            dataReturn.set_txtAccuracy(dataAbsenFPE.get_txtAccuracy());
            dataReturn.set_txtLatitude(dataAbsenFPE.get_txtLatitude());
            dataReturn.set_txtLongitude(dataAbsenFPE.get_txtLongitude());
            if (dataAbsenFPE.get_txtImg1() != null){
                byte[] blob1 = dataAbsenFPE.get_txtImg1();
                dataReturn.set_txtImg1(blob1);
            }
            if (dataAbsenFPE.get_txtImg2() != null){
                byte[] blob2 = dataAbsenFPE.get_txtImg2();
                dataReturn.set_txtImg2(blob2);
            }
            dataReturn.set_txtUserId(dataAbsenFPE.get_txtUserId());
            dataReturn.set_txtRoleId(dataAbsenFPE.get_txtRoleId());
            dataReturn.set_txtDesc(dataAbsenFPE.get_txtDesc());

            dataReturn.setType("absenFPE");
        } else{
            dataReturn = null;
        }
        db.close();
        return dataReturn;
    }

    public List<visitplanAbsenData>  getAllDataActiveOrderByDate(){
        SQLiteDatabase db=getDb();
        tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
        List<tAbsenUserData> dataAbsen=new ArrayList<>();
        dataAbsen=_tAbsenUserDA.getAllDataActiveOrderByDate(db);
        tVisitPlanRealisasiDA  _tTVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
        List<tVisitPlanRealisasiData> dataVisit = new ArrayList<>();
        dataVisit = _tTVisitPlanRealisasiDA.getAllDataActiveOrderByDate(db);
        List<visitplanAbsenData> dataReturn = new ArrayList<>();
        tAttendanceUserDA _tAttendanceUserDA = new tAttendanceUserDA(db);
        List<tAttendanceUserData> dataAbsenFPE;
        dataAbsenFPE = _tAttendanceUserDA.getAllDataActiveOrderByDate(db);

        if(dataAbsen == null && dataVisit == null && dataAbsenFPE == null){
            dataReturn = null;
        }
        if (dataVisit != null){
            if(dataVisit.size()>0){
                for(tVisitPlanRealisasiData data : dataVisit){
                    if(data.get_dateCheckout().equals("null")){
                        continue;
                    }
                    visitplanAbsenData visitAbsen = new visitplanAbsenData();
                    visitAbsen.set_txtId(data.get_txtDataIDRealisasi().toString());
                    visitAbsen.set_txtOutletCode(data.get_txtOutletCode().toString());
                    visitAbsen.set_txtOutletName(data.get_txtOutletName().toString());
                    visitAbsen.set_txtBranchCode(data.get_txtBranchCode().toString());
                    visitAbsen.set_txtBranchName(data.get_txtBranchName().toString());
                    visitAbsen.set_txtDeviceId(data.get_deviceId());

                    visitAbsen.set_dtDateCheckIn(data.get_dtDate().toString());
                    visitAbsen.set_dtDateCheckOut(data.get_dateCheckout().toString());
                    visitAbsen.set_intSubmit(data.get_intSubmit());
                    visitAbsen.set_intSync(data.get_intPush());
                    visitAbsen.set_txtAccuracy(data.get_txtAcc());
                    visitAbsen.set_txtLatitude(data.get_txtLat());
                    visitAbsen.set_txtLongitude(data.get_txtLong());
                    byte[] blob1 = data.get_dtPhoto1();
//				Bitmap bmp1 = BitmapFactory.decodeByteArray(blob1, 0, blob1.length);
                    visitAbsen.set_txtImg1(blob1);
                    byte[] blob2 = data.get_dtPhoto2();
                    visitAbsen.set_txtUserId(data.get_intUserID());
                    visitAbsen.set_txtRoleId(data.get_txtRoleId());
//				Bitmap bmp2 = BitmapFactory.decodeByteArray(blob2, 0, blob2.length);
                    visitAbsen.set_txtImg2(blob2);


                    visitAbsen.setType("visitPlan");
                    dataReturn.add(visitAbsen);
                }
            }
        }
        if (dataAbsen != null){
            if(dataAbsen.size()>0){
                for(tAbsenUserData data : dataAbsen){
                    if(data.get_dtDateCheckOut().equals("null")){
                        continue;
                    }
                    visitplanAbsenData visitAbsen = new visitplanAbsenData();
                    visitAbsen.set_txtId(data.get_intId().toString());
                    visitAbsen.set_txtOutletCode(data.get_txtOutletCode().toString());
                    visitAbsen.set_txtOutletName(data.get_txtOutletName().toString());
                    visitAbsen.set_txtBranchCode(data.get_txtBranchCode().toString());
                    visitAbsen.set_txtBranchName(data.get_txtBranchName().toString());
                    visitAbsen.set_txtDeviceId(data.get_txtDeviceId());

                    visitAbsen.set_dtDateCheckIn(data.get_dtDateCheckIn().toString());
                    visitAbsen.set_dtDateCheckOut(data.get_dtDateCheckOut().toString());
                    visitAbsen.set_intSubmit(data.get_intSubmit());
                    visitAbsen.set_intSync(data.get_intSync());
                    visitAbsen.set_txtAbsen(data.get_txtAbsen());
                    visitAbsen.set_txtAccuracy(data.get_txtAccuracy());
                    visitAbsen.set_txtLatitude(data.get_txtLatitude());
                    visitAbsen.set_txtLongitude(data.get_txtLongitude());
                    if (data.get_txtImg1() != null){
                        byte[] blob1 = data.get_txtImg1();
                        visitAbsen.set_txtImg1(blob1);
                    }
//				Bitmap bmp1 = BitmapFactory.decodeByteArray(blob1, 0, blob1.length);
                    if (data.get_txtImg2() != null){
                        byte[] blob2 = data.get_txtImg2();
                        visitAbsen.set_txtImg2(blob2);
                    }
                    visitAbsen.set_txtUserId(data.get_txtUserId());
                    visitAbsen.set_txtRoleId(data.get_txtRoleId());
//				Bitmap bmp2 = BitmapFactory.decodeByteArray(blob2, 0, blob2.length);

                    visitAbsen.setType("absen");
                    dataReturn.add(visitAbsen);
                }
            }
        }

        if (dataAbsenFPE != null){
            if(dataAbsenFPE.size()>0){
                for(tAttendanceUserData data : dataAbsenFPE){
                    if(data.get_dtDateCheckOut().equals("null")){
                        continue;
                    }
                    visitplanAbsenData visitAbsen = new visitplanAbsenData();
                    visitAbsen.set_txtId(data.get_intId().toString());
                    visitAbsen.set_txtOutletCode(data.get_txtOutletCode().toString());
                    visitAbsen.set_txtOutletName(data.get_txtOutletName().toString());
                    visitAbsen.set_txtBranchCode(data.get_txtBranchCode().toString());
                    visitAbsen.set_txtBranchName(data.get_txtBranchName().toString());
                    visitAbsen.set_txtDeviceId(data.get_txtDeviceId());

                    visitAbsen.set_dtDateCheckIn(data.get_dtDateCheckIn().toString());
                    visitAbsen.set_dtDateCheckOut(data.get_dtDateCheckOut().toString());
                    visitAbsen.set_intSubmit(data.get_intSubmit());
                    visitAbsen.set_intSync(data.get_intSync());
                    visitAbsen.set_txtAbsen(data.get_txtAbsen());
                    visitAbsen.set_txtAccuracy(data.get_txtAccuracy());
                    visitAbsen.set_txtLatitude(data.get_txtLatitude());
                    visitAbsen.set_txtLongitude(data.get_txtLongitude());
                    if (data.get_txtImg1() != null){
                        byte[] blob1 = data.get_txtImg1();
                        visitAbsen.set_txtImg1(blob1);
                    }
                    if (data.get_txtImg2() != null){
                        byte[] blob2 = data.get_txtImg2();
                        visitAbsen.set_txtImg2(blob2);
                    }
                    visitAbsen.set_txtUserId(data.get_txtUserId());
                    visitAbsen.set_txtRoleId(data.get_txtRoleId());
                    visitAbsen.set_txtDesc(data.get_txtDesc());

                    visitAbsen.setType("absenFPE");
                    dataReturn.add(visitAbsen);
                }
            }
        }
        db.close();
        return dataReturn;
    }

    public void DownloadData(String versionName) throws ParseException {
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        JSONObject resJson = new JSONObject();
        resJson.put("User", _dataUserLogin.get_txtUserId());
        resJson.put("txtBranchCode", "");
        resJson.put("txtOutletCode", "");
        resJson.put("txtDeviceId", _dataUserLogin.get_txtDeviceId());
        mconfigDA _mconfigDA = new mconfigDA(_db);
        String strVal2 = "";
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        clsHelper _help = new clsHelper();
        linkAPI dtlinkAPI = new linkAPI();
        String txtMethod = "DownloadAllDataTransaction";
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
        String JsonData = _help.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);

        APIData dtAPIDATA = new APIData();
        //String aa=new clsHelper().linkAPI(db);
        Iterator i = JsonArray.iterator();
        Boolean flag = true;
        String ErrorMess = "";
        clsHelper _clsHelper = new clsHelper();
        _db.close();
    }
    public clsPushData pushDataError() {
        clsPushData dtclsPushData = new clsPushData();
        dataJson dtPush = new dataJson();
        SQLiteDatabase db = getDb();
        tLogErrorDA _tLogErrorDA = new tLogErrorDA(db);

        List<tLogErrorData> ListOftLogError = _tLogErrorDA.getPushData(db);
        clsHardCode _path = new clsHardCode();
        HashMap<String, String> FileUpload = new HashMap<String, String>();
        if (ListOftLogError != null) {
            dtPush.setListOftErrorLogData(ListOftLogError);
        } else {
            dtPush = null;
        }
        db.close();
        dtclsPushData.setDtdataJson(dtPush);
        return dtclsPushData;
    }
    public org.json.simple.JSONArray callPushErrorReturnJson(String versionName, String strJson, HashMap<String, String> ListOfDataFile) throws Exception {
        SQLiteDatabase _db = getDb();
        Boolean flag = true;
        String ErrorMess = "";
        String txtMethod = "PushDataErrorSPGMobile";
        linkAPI dtlinkAPI = new linkAPI();
        clsHelper _help = new clsHelper();
        dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod(txtMethod);
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
//        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
//        dtlinkAPI.set_txtParam(_dataUserLogin.get_txtUserId() + "|||");
//        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
//        dtlinkAPI.set_txtVesion(versionName);
        String strVal2 = "";
        mconfigDA _mconfigDA = new mconfigDA(_db);
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        dataAPI = _mconfigDA.getData(_db, enumConfigData.BackGroundServiceOnline.getidConfigData());
        String TimeOut = dataAPI.get_txtValue();
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.PushErrorFile(strLinkAPI, strJson, Integer.valueOf(TimeOut), ListOfDataFile);
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        APIData dtAPIDATA = new APIData();
        Iterator i = JsonArray.iterator();
        mCounterNumberDA _mCounterNumberDA = new mCounterNumberDA(_db);
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                mCounterNumberData _data = new mCounterNumberData();
                _data.set_intId(enumCounterData.dtPushKBN.getidCounterData());
                _data.set_txtDeskripsi((String) innerObj.get("_pstrMethodRequest"));
                _data.set_txtName((String) innerObj.get("_pstrMethodRequest"));
                _data.set_txtValue((String) innerObj.get("_pstrArgument"));
                _mCounterNumberDA.SaveDataMConfig(_db, _data);
            } else {
                flag = false;
                ErrorMess = (String) innerObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
        _db.close();
        return JsonArray;
    }

    public org.json.simple.JSONArray callPushDataFileReturnJson(String methodName,String versionName, String strJson, HashMap<String, String> ListOfDataFile) throws Exception {
        SQLiteDatabase _db = getDb();
        Boolean flag = true;
        String ErrorMess = "";
        String txtMethod = methodName;
        linkAPI dtlinkAPI = new linkAPI();
        clsHelper _help = new clsHelper();
        dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod(txtMethod);
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
//        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
//        dtlinkAPI.set_txtParam(_dataUserLogin.get_txtUserId() + "|||");
//        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
//        dtlinkAPI.set_txtVesion(versionName);
        String strVal2 = "";
        mconfigDA _mconfigDA = new mconfigDA(_db);
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        dataAPI = _mconfigDA.getData(_db, enumConfigData.BackGroundServiceOnline.getidConfigData());
        String TimeOut = dataAPI.get_txtValue();
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.PushErrorFile(strLinkAPI, strJson, Integer.valueOf(TimeOut), ListOfDataFile);
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        APIData dtAPIDATA = new APIData();
        Iterator i = JsonArray.iterator();
        mCounterNumberDA _mCounterNumberDA = new mCounterNumberDA(_db);
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                mCounterNumberData _data = new mCounterNumberData();
                _data.set_intId(enumCounterData.dtPushKBN.getidCounterData());
                _data.set_txtDeskripsi((String) innerObj.get("_pstrMethodRequest"));
                _data.set_txtName((String) innerObj.get("_pstrMethodRequest"));
                _data.set_txtValue((String) innerObj.get("_pstrArgument"));
                _mCounterNumberDA.SaveDataMConfig(_db, _data);
            } else {
                flag = false;
                ErrorMess = (String) innerObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
        _db.close();
        return JsonArray;
    }


    public org.json.simple.JSONArray callPushDataReturnJson(String versionName, String strJson, HashMap<clsMappingPushFile, byte[]> ListOfDataFile) throws Exception {
        SQLiteDatabase _db = getDb();
        Boolean flag = true;
        String ErrorMess = "";
        String txtMethod = "PushDataSPGMobile";
        linkAPI dtlinkAPI = new linkAPI();
        clsHelper _help = new clsHelper();
        dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod(txtMethod);
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_txtUserId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strVal2 = "";
        mconfigDA _mconfigDA = new mconfigDA(_db);
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        dataAPI = _mconfigDA.getData(_db, enumConfigData.BackGroundServiceOnline.getidConfigData());
        String TimeOut = dataAPI.get_txtValue();
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.PushDataWithFile(strLinkAPI, strJson, Integer.valueOf(TimeOut), ListOfDataFile);
//        String JsonData2 = "[{\"_pboolValid\":0,\"_pstrArgument\":\"2018\\/01\\/14 22:13:57:081\",\"_pstrMessage\":\"Transaction (Process ID 121) was deadlocked on lock | communication buffer resources with another process and has been chosen as the deadlock victim. Rerun the transaction.\",\"_pstrMethodRequest\":\"PushDataSPGMobile\",\"ListOfBranch\":null,\"ListOfEmployeeBranchData\":null,\"ListOfOutletCRM\":null,\"ListOfTFileAttach_mobile\":null,\"ListOfTNotificationHeaderSPG_mobile\":null,\"ListOfXTeamRegionAPI\":null,\"ListOfclsDataPOPStandardAPI\":null,\"ListOfclsDataQuesionerAPI\":null,\"ListOfclsDataStockInHandAPI\":null,\"ListOfclsDatatPurchaseOrderAPI\":null,\"ListOfclsKemasanRusakHeader\":null,\"ListOfclsKoordinasiOutletImage_mobile\":null,\"ListOfclsKoordinasiOutlet_mobile\":null,\"ListOfclsMGeolocationOutlet\":null,\"ListOfclsMItemSalesPack_Stock\":null,\"ListOfclsPlanogramMobile\":null,\"ListOfclsTGRNDetail_mobile\":null,\"ListOfclsTGRNHeader_mobile\":null,\"ListOfclsTJawabanSPGHeader_Mobile\":null,\"ListOfclsTJawabanSPG_Mobile\":null,\"ListOfclsTKemasanRusakDetail_mobile\":null,\"ListOfclsTKemasanRusakHeader_mobile\":null,\"ListOfclsTKemasanRusakImage_mobile\":null,\"ListOfclsTOverStockDetail_mobile\":null,\"ListOfclsTOverStockHeader_mobile\":null,\"ListOfclsTPODetail_mobile\":null,\"ListOfclsTPOHeader_mobile\":null,\"ListOfclsTPOStatus_mobile\":null,\"ListOfclsTPenguaranDetail_mobile\":null,\"ListOfclsTPenguaranHeader_Mobile\":null,\"ListOfclsTPenguaranStatus_mobile\":null,\"ListOfclsTPlanogramImage_mobile\":null,\"ListOfclsTPurchaseOrderDetail_Mobile\":null,\"ListOfclsTPurchaseOrderHeader_Mobile\":null,\"ListOfclsTSalesOrderDetail_Mobile\":null,\"ListOfclsTSalesOrderHeader_Mobile\":null,\"ListOfclsTSalesProductDetail_Mobile\":null,\"ListOfclsTSalesProductHeader_Mobile\":null,\"ListOfclsTSalesProductQuantityDetail_mobile\":null,\"ListOfclsTSalesProductQuantityHeader_mobile\":null,\"ListOfclsTSalesProductQuantityImage_mobile\":null,\"ListOfclsTStockOpnameDetail_mobile\":null,\"ListOfclsTStockOpnameHeader_mobile\":null,\"ListOfclsTTransactionDetail\":null,\"ListOfclsTVisitplanDetail_Mobile\":null,\"ListOfclsTVisitplanHeader_Mobile\":null,\"ListOfclsTidakSesuaiPesananOutletImage_mobile\":null,\"ListOfclsTnotificationHeader_mobile\":null,\"ListOfclsTrackingLocation_mobile\":null,\"ListOfclsclsTidakSesuaiPesanan\":null,\"ListOfclsdataCustomerBasedAPI\":null,\"ListOfclsdataResoAPI\":null,\"ListOfclsmBranch\":null,\"ListOfclsmPriceInOutlet\":null,\"ListOfclsmstockawal_mobile\":null,\"ListOfclstOverStockHeader\":null,\"ListOfclstQuantityStockHeader\":null,\"ListOfkoordinasiOutletImage_mobile\":null,\"ListOfkoordinasiOutlet_mobile\":null,\"ListOfmCategoryKoordinasiOutlet_mobile\":null,\"ListOfmCategoryVisitPlanData\":null,\"ListOfmKategoriQuesioner_mobile\":null,\"ListOfmListJawabanQuesioner_mobile\":null,\"ListOfmParentQuesioner_mobile\":null,\"ListOfmPertanyaanQuesioner_mobile\":null,\"ListOfmProductBarcode\":null,\"ListOfmProductBrandHeader\":null,\"ListOfmQuesionerViewHeaderWeb\":null,\"ListOfmTypeLeaveMobile\":null,\"ListOfmTypePertanyaanQuesioner_mobile\":null,\"ListOfmTypeSubmissionMobile\":null,\"ListOftAbsenUser_mobile\":null,\"ListOftActivity_mobile\":null,\"ListOftActivity_mobileNew\":null,\"ListOftAttendanceUser_mobile\":null,\"ListOftCustomerBasedMobileDetail\":null,\"ListOftCustomerBasedMobileDetailProduct\":null,\"ListOftCustomerBasedMobileHeader\":null,\"ListOftHirartkiBis_mobile\":null,\"ListOftJawabanSPGHeader_mobile\":null,\"ListOftJawabanSPG_mobile\":null,\"ListOftKategoryPlanogram\":null,\"ListOftLeaveMobile\":null,\"ListOftLogReceiverDetailNotif_mobile\":null,\"ListOftLogReceiverHeaderNotif_mobile\":null,\"ListOftOverStockDetail_mobile\":null,\"ListOftOverStockHeader_mobile\":null,\"ListOftPOPStandardDetail_mobile\":null,\"ListOftPOPStandardHeader_mobile\":null,\"ListOftPlanogramImage_mobile\":null,\"ListOftPlanogramMobile\":null,\"ListOftPlanogram_mobile\":null,\"ListOftPurchaseOrderDetail_mobile\":null,\"ListOftPurchaseOrderHeader_mobile\":null,\"ListOftSalesProductDetail_mobile\":null,\"ListOftSalesProductHeader_mobile\":null,\"ListOftSalesProductQuantityDetail_mobile\":null,\"ListOftSalesProductQuantityHeader_mobile\":null,\"ListOftStockInHandDetail_mobile\":null,\"ListOftStockInHandHeader_mobile\":null,\"ListOftSubTypeActivity\":nul";
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        APIData dtAPIDATA = new APIData();
        Iterator i = JsonArray.iterator();
        mCounterNumberDA _mCounterNumberDA = new mCounterNumberDA(_db);
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                mCounterNumberData _data = new mCounterNumberData();
                _data.set_intId(enumCounterData.dtPushKBN.getidCounterData());
                _data.set_txtDeskripsi((String) innerObj.get("_pstrMethodRequest"));
                _data.set_txtName((String) innerObj.get("_pstrMethodRequest"));
                _data.set_txtValue((String) innerObj.get("_pstrArgument"));
                _mCounterNumberDA.SaveDataMConfig(_db, _data);
            } else {
                flag = false;
                ErrorMess = (String) innerObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
        _db.close();
        return JsonArray;
    }

    public org.json.simple.JSONArray callPushDataReturnJsonParamMethod(String txtMethod,String versionName, String strJson) throws Exception {
        SQLiteDatabase _db = getDb();
        Boolean flag = true;
        String ErrorMess = "";
        linkAPI dtlinkAPI = new linkAPI();
        clsHelper _help = new clsHelper();
        dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod(txtMethod);
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_txtUserId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strVal2 = "";
        mconfigDA _mconfigDA = new mconfigDA(_db);
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        dataAPI = _mconfigDA.getData(_db, enumConfigData.BackGroundServiceOnline.getidConfigData());
        String TimeOut = dataAPI.get_txtValue();
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.pushtData(strLinkAPI, strJson, Integer.valueOf(TimeOut));
//        String JsonData2 = "[{\"_pboolValid\":0,\"_pstrArgument\":\"2018\\/01\\/14 22:13:57:081\",\"_pstrMessage\":\"Transaction (Process ID 121) was deadlocked on lock | communication buffer resources with another process and has been chosen as the deadlock victim. Rerun the transaction.\",\"_pstrMethodRequest\":\"PushDataSPGMobile\",\"ListOfBranch\":null,\"ListOfEmployeeBranchData\":null,\"ListOfOutletCRM\":null,\"ListOfTFileAttach_mobile\":null,\"ListOfTNotificationHeaderSPG_mobile\":null,\"ListOfXTeamRegionAPI\":null,\"ListOfclsDataPOPStandardAPI\":null,\"ListOfclsDataQuesionerAPI\":null,\"ListOfclsDataStockInHandAPI\":null,\"ListOfclsDatatPurchaseOrderAPI\":null,\"ListOfclsKemasanRusakHeader\":null,\"ListOfclsKoordinasiOutletImage_mobile\":null,\"ListOfclsKoordinasiOutlet_mobile\":null,\"ListOfclsMGeolocationOutlet\":null,\"ListOfclsMItemSalesPack_Stock\":null,\"ListOfclsPlanogramMobile\":null,\"ListOfclsTGRNDetail_mobile\":null,\"ListOfclsTGRNHeader_mobile\":null,\"ListOfclsTJawabanSPGHeader_Mobile\":null,\"ListOfclsTJawabanSPG_Mobile\":null,\"ListOfclsTKemasanRusakDetail_mobile\":null,\"ListOfclsTKemasanRusakHeader_mobile\":null,\"ListOfclsTKemasanRusakImage_mobile\":null,\"ListOfclsTOverStockDetail_mobile\":null,\"ListOfclsTOverStockHeader_mobile\":null,\"ListOfclsTPODetail_mobile\":null,\"ListOfclsTPOHeader_mobile\":null,\"ListOfclsTPOStatus_mobile\":null,\"ListOfclsTPenguaranDetail_mobile\":null,\"ListOfclsTPenguaranHeader_Mobile\":null,\"ListOfclsTPenguaranStatus_mobile\":null,\"ListOfclsTPlanogramImage_mobile\":null,\"ListOfclsTPurchaseOrderDetail_Mobile\":null,\"ListOfclsTPurchaseOrderHeader_Mobile\":null,\"ListOfclsTSalesOrderDetail_Mobile\":null,\"ListOfclsTSalesOrderHeader_Mobile\":null,\"ListOfclsTSalesProductDetail_Mobile\":null,\"ListOfclsTSalesProductHeader_Mobile\":null,\"ListOfclsTSalesProductQuantityDetail_mobile\":null,\"ListOfclsTSalesProductQuantityHeader_mobile\":null,\"ListOfclsTSalesProductQuantityImage_mobile\":null,\"ListOfclsTStockOpnameDetail_mobile\":null,\"ListOfclsTStockOpnameHeader_mobile\":null,\"ListOfclsTTransactionDetail\":null,\"ListOfclsTVisitplanDetail_Mobile\":null,\"ListOfclsTVisitplanHeader_Mobile\":null,\"ListOfclsTidakSesuaiPesananOutletImage_mobile\":null,\"ListOfclsTnotificationHeader_mobile\":null,\"ListOfclsTrackingLocation_mobile\":null,\"ListOfclsclsTidakSesuaiPesanan\":null,\"ListOfclsdataCustomerBasedAPI\":null,\"ListOfclsdataResoAPI\":null,\"ListOfclsmBranch\":null,\"ListOfclsmPriceInOutlet\":null,\"ListOfclsmstockawal_mobile\":null,\"ListOfclstOverStockHeader\":null,\"ListOfclstQuantityStockHeader\":null,\"ListOfkoordinasiOutletImage_mobile\":null,\"ListOfkoordinasiOutlet_mobile\":null,\"ListOfmCategoryKoordinasiOutlet_mobile\":null,\"ListOfmCategoryVisitPlanData\":null,\"ListOfmKategoriQuesioner_mobile\":null,\"ListOfmListJawabanQuesioner_mobile\":null,\"ListOfmParentQuesioner_mobile\":null,\"ListOfmPertanyaanQuesioner_mobile\":null,\"ListOfmProductBarcode\":null,\"ListOfmProductBrandHeader\":null,\"ListOfmQuesionerViewHeaderWeb\":null,\"ListOfmTypeLeaveMobile\":null,\"ListOfmTypePertanyaanQuesioner_mobile\":null,\"ListOfmTypeSubmissionMobile\":null,\"ListOftAbsenUser_mobile\":null,\"ListOftActivity_mobile\":null,\"ListOftActivity_mobileNew\":null,\"ListOftAttendanceUser_mobile\":null,\"ListOftCustomerBasedMobileDetail\":null,\"ListOftCustomerBasedMobileDetailProduct\":null,\"ListOftCustomerBasedMobileHeader\":null,\"ListOftHirartkiBis_mobile\":null,\"ListOftJawabanSPGHeader_mobile\":null,\"ListOftJawabanSPG_mobile\":null,\"ListOftKategoryPlanogram\":null,\"ListOftLeaveMobile\":null,\"ListOftLogReceiverDetailNotif_mobile\":null,\"ListOftLogReceiverHeaderNotif_mobile\":null,\"ListOftOverStockDetail_mobile\":null,\"ListOftOverStockHeader_mobile\":null,\"ListOftPOPStandardDetail_mobile\":null,\"ListOftPOPStandardHeader_mobile\":null,\"ListOftPlanogramImage_mobile\":null,\"ListOftPlanogramMobile\":null,\"ListOftPlanogram_mobile\":null,\"ListOftPurchaseOrderDetail_mobile\":null,\"ListOftPurchaseOrderHeader_mobile\":null,\"ListOftSalesProductDetail_mobile\":null,\"ListOftSalesProductHeader_mobile\":null,\"ListOftSalesProductQuantityDetail_mobile\":null,\"ListOftSalesProductQuantityHeader_mobile\":null,\"ListOftStockInHandDetail_mobile\":null,\"ListOftStockInHandHeader_mobile\":null,\"ListOftSubTypeActivity\":nul";
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        APIData dtAPIDATA = new APIData();
        Iterator i = JsonArray.iterator();
        mCounterNumberDA _mCounterNumberDA = new mCounterNumberDA(_db);
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                mCounterNumberData _data = new mCounterNumberData();
                _data.set_intId(enumCounterData.dtPushKBN.getidCounterData());
                _data.set_txtDeskripsi((String) innerObj.get("_pstrMethodRequest"));
                _data.set_txtName((String) innerObj.get("_pstrMethodRequest"));
                _data.set_txtValue((String) innerObj.get("_pstrArgument"));
                _mCounterNumberDA.SaveDataMConfig(_db, _data);
            } else {
                flag = false;
                ErrorMess = (String) innerObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
        _db.close();
        return JsonArray;
    }

    public JSONArray callPushDataReturnJsonParamMethodJObject(String txtMethod, String versionName, String strJson) throws Exception {
        SQLiteDatabase _db = getDb();
        Boolean flag = true;
        String ErrorMess = "";
        linkAPI dtlinkAPI = new linkAPI();
        clsHelper _help = new clsHelper();
        dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod(txtMethod);
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_txtUserId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strVal2 = "";
        mconfigDA _mconfigDA = new mconfigDA(_db);
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        dataAPI = _mconfigDA.getData(_db, enumConfigData.BackGroundServiceOnline.getidConfigData());
        String TimeOut = dataAPI.get_txtValue();
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.pushtData(strLinkAPI, strJson, Integer.valueOf(TimeOut));
//        String JsonData2 = "[{\"_pboolValid\":0,\"_pstrArgument\":\"2018\\/01\\/14 22:13:57:081\",\"_pstrMessage\":\"Transaction (Process ID 121) was deadlocked on lock | communication buffer resources with another process and has been chosen as the deadlock victim. Rerun the transaction.\",\"_pstrMethodRequest\":\"PushDataSPGMobile\",\"ListOfBranch\":null,\"ListOfEmployeeBranchData\":null,\"ListOfOutletCRM\":null,\"ListOfTFileAttach_mobile\":null,\"ListOfTNotificationHeaderSPG_mobile\":null,\"ListOfXTeamRegionAPI\":null,\"ListOfclsDataPOPStandardAPI\":null,\"ListOfclsDataQuesionerAPI\":null,\"ListOfclsDataStockInHandAPI\":null,\"ListOfclsDatatPurchaseOrderAPI\":null,\"ListOfclsKemasanRusakHeader\":null,\"ListOfclsKoordinasiOutletImage_mobile\":null,\"ListOfclsKoordinasiOutlet_mobile\":null,\"ListOfclsMGeolocationOutlet\":null,\"ListOfclsMItemSalesPack_Stock\":null,\"ListOfclsPlanogramMobile\":null,\"ListOfclsTGRNDetail_mobile\":null,\"ListOfclsTGRNHeader_mobile\":null,\"ListOfclsTJawabanSPGHeader_Mobile\":null,\"ListOfclsTJawabanSPG_Mobile\":null,\"ListOfclsTKemasanRusakDetail_mobile\":null,\"ListOfclsTKemasanRusakHeader_mobile\":null,\"ListOfclsTKemasanRusakImage_mobile\":null,\"ListOfclsTOverStockDetail_mobile\":null,\"ListOfclsTOverStockHeader_mobile\":null,\"ListOfclsTPODetail_mobile\":null,\"ListOfclsTPOHeader_mobile\":null,\"ListOfclsTPOStatus_mobile\":null,\"ListOfclsTPenguaranDetail_mobile\":null,\"ListOfclsTPenguaranHeader_Mobile\":null,\"ListOfclsTPenguaranStatus_mobile\":null,\"ListOfclsTPlanogramImage_mobile\":null,\"ListOfclsTPurchaseOrderDetail_Mobile\":null,\"ListOfclsTPurchaseOrderHeader_Mobile\":null,\"ListOfclsTSalesOrderDetail_Mobile\":null,\"ListOfclsTSalesOrderHeader_Mobile\":null,\"ListOfclsTSalesProductDetail_Mobile\":null,\"ListOfclsTSalesProductHeader_Mobile\":null,\"ListOfclsTSalesProductQuantityDetail_mobile\":null,\"ListOfclsTSalesProductQuantityHeader_mobile\":null,\"ListOfclsTSalesProductQuantityImage_mobile\":null,\"ListOfclsTStockOpnameDetail_mobile\":null,\"ListOfclsTStockOpnameHeader_mobile\":null,\"ListOfclsTTransactionDetail\":null,\"ListOfclsTVisitplanDetail_Mobile\":null,\"ListOfclsTVisitplanHeader_Mobile\":null,\"ListOfclsTidakSesuaiPesananOutletImage_mobile\":null,\"ListOfclsTnotificationHeader_mobile\":null,\"ListOfclsTrackingLocation_mobile\":null,\"ListOfclsclsTidakSesuaiPesanan\":null,\"ListOfclsdataCustomerBasedAPI\":null,\"ListOfclsdataResoAPI\":null,\"ListOfclsmBranch\":null,\"ListOfclsmPriceInOutlet\":null,\"ListOfclsmstockawal_mobile\":null,\"ListOfclstOverStockHeader\":null,\"ListOfclstQuantityStockHeader\":null,\"ListOfkoordinasiOutletImage_mobile\":null,\"ListOfkoordinasiOutlet_mobile\":null,\"ListOfmCategoryKoordinasiOutlet_mobile\":null,\"ListOfmCategoryVisitPlanData\":null,\"ListOfmKategoriQuesioner_mobile\":null,\"ListOfmListJawabanQuesioner_mobile\":null,\"ListOfmParentQuesioner_mobile\":null,\"ListOfmPertanyaanQuesioner_mobile\":null,\"ListOfmProductBarcode\":null,\"ListOfmProductBrandHeader\":null,\"ListOfmQuesionerViewHeaderWeb\":null,\"ListOfmTypeLeaveMobile\":null,\"ListOfmTypePertanyaanQuesioner_mobile\":null,\"ListOfmTypeSubmissionMobile\":null,\"ListOftAbsenUser_mobile\":null,\"ListOftActivity_mobile\":null,\"ListOftActivity_mobileNew\":null,\"ListOftAttendanceUser_mobile\":null,\"ListOftCustomerBasedMobileDetail\":null,\"ListOftCustomerBasedMobileDetailProduct\":null,\"ListOftCustomerBasedMobileHeader\":null,\"ListOftHirartkiBis_mobile\":null,\"ListOftJawabanSPGHeader_mobile\":null,\"ListOftJawabanSPG_mobile\":null,\"ListOftKategoryPlanogram\":null,\"ListOftLeaveMobile\":null,\"ListOftLogReceiverDetailNotif_mobile\":null,\"ListOftLogReceiverHeaderNotif_mobile\":null,\"ListOftOverStockDetail_mobile\":null,\"ListOftOverStockHeader_mobile\":null,\"ListOftPOPStandardDetail_mobile\":null,\"ListOftPOPStandardHeader_mobile\":null,\"ListOftPlanogramImage_mobile\":null,\"ListOftPlanogramMobile\":null,\"ListOftPlanogram_mobile\":null,\"ListOftPurchaseOrderDetail_mobile\":null,\"ListOftPurchaseOrderHeader_mobile\":null,\"ListOftSalesProductDetail_mobile\":null,\"ListOftSalesProductHeader_mobile\":null,\"ListOftSalesProductQuantityDetail_mobile\":null,\"ListOftSalesProductQuantityHeader_mobile\":null,\"ListOftStockInHandDetail_mobile\":null,\"ListOftStockInHandHeader_mobile\":null,\"ListOftSubTypeActivity\":nul";
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));

        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        return JsonArray;
    }

    public String downloadAllData(String versionName, String strJson) throws Exception {
        SQLiteDatabase _db = getDb();
        Boolean flag = true;
        String ErrorMess = "";
        String txtMethod = "DownloadAllDataMobile";
        linkAPI dtlinkAPI = new linkAPI();
        clsHelper _help = new clsHelper();
        dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod(txtMethod);
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_txtUserId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strVal2 = "";
        mconfigDA _mconfigDA = new mconfigDA(_db);
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        dataAPI = _mconfigDA.getData(_db, enumConfigData.BackGroundServiceOnline.getidConfigData());
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.pushtData(strLinkAPI, strJson, Integer.valueOf(getBackGroundServiceOnline()));
        _db.close();
        return JsonData;
    }

    public org.json.simple.JSONArray SQLiteGenerateRequest(String versionName) throws Exception {
        SQLiteDatabase _db = getDb();
        Boolean flag = true;
        String ErrorMess = "";
        String txtMethod = "SQLiteGenerator";
        linkAPI dtlinkAPI = new linkAPI();
        clsHelper _help = new clsHelper();
        dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod(txtMethod);
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateNow = dateFormat.format(date);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId() + "|" + _dataUserLogin.get_txtRoleId() + "|" + _dataUserLogin.get_txtUserId() + "|" + dateNow + "|" +  _dataUserLogin.get_txtBranchCode() +"||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strVal2 = "";
        mconfigDA _mconfigDA = new mconfigDA(_db);
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        dataAPI = _mconfigDA.getData(_db, enumConfigData.BackGroundServiceOnline.getidConfigData());
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.pushtData(strLinkAPI, "", Integer.valueOf(getBackGroundServiceOnline()));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        APIData dtAPIDATA = new APIData();
        Iterator i = JsonArray.iterator();
        _db.close();
        return JsonArray;
    }

    public String DownloadEmployeeAreaEnhance(String versionName) throws Exception {
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);

        String strVal2 = "";
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        //ambil version dari webservices
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtlinkAPI = new linkAPI();
        String txtMethod = "DownloadAllDataMobileEnhance";
        JSONObject resJson = new JSONObject();
        resJson.put("txtNIK", _dataUserLogin.get_TxtEmpId());
        resJson.put("txtRoleId", _dataUserLogin.get_txtRoleId());
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);

        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));

        _db.close();
        return JsonData;
    }

    public org.json.simple.JSONArray GetDatamversionAppPostData(String versionName) throws Exception {
        SQLiteDatabase _db = getDb();
        String txtMethod = "GetDatamversionAppPostData";
        linkAPI dtlinkAPI = new linkAPI();
        clsHelper _help = new clsHelper();
        dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod(txtMethod);
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        dtlinkAPI.set_txtParam("|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strVal2 = "";
        mconfigDA _mconfigDA = new mconfigDA(_db);
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }

        String TimeOut = new clsMainBL().getBackGroundServiceOnline();
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        JSONObject resJson = new JSONObject();
        resJson.put("intVersionApp", "");
        resJson.put("txtTypeApp", new clsMainBL().getTypeMobile());
        resJson.put("txtVersion", "");
        resJson.put("status", "1");
        String JsonData = _help.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(TimeOut));
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        APIData dtAPIDATA = new APIData();
        Iterator i = JsonArray.iterator();
        _db.close();
        return JsonArray;
    }

    public void callPushData(String versionName, String strJson, HashMap<String, Byte[]> ListOfDataFile) throws Exception {
        SQLiteDatabase _db = getDb();
        Boolean flag = true;
        String ErrorMess = "";
        String txtMethod = "PushDataKBN";
        linkAPI dtlinkAPI = new linkAPI();
        clsHelper _help = new clsHelper();
        dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod(txtMethod);
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_txtUserId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strVal2 = "";
        mconfigDA _mconfigDA = new mconfigDA(_db);
        mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        dataAPI = _mconfigDA.getData(_db, enumConfigData.BackGroundServiceOnline.getidConfigData());
        String TimeOut = dataAPI.get_txtValue();
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = null;
//				=_help.PushDataWithFile(strLinkAPI, strJson, Integer.valueOf(TimeOut), ListOfDataFile);
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        APIData dtAPIDATA = new APIData();
        Iterator i = JsonArray.iterator();
        mCounterNumberDA _mCounterNumberDA = new mCounterNumberDA(_db);
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                mCounterNumberData _data = new mCounterNumberData();
                _data.set_intId(enumCounterData.dtPushKBN.getidCounterData());
                _data.set_txtDeskripsi((String) innerObj.get("_pstrMethodRequest"));
                _data.set_txtName((String) innerObj.get("_pstrMethodRequest"));
                _data.set_txtValue((String) innerObj.get("_pstrArgument"));
                _mCounterNumberDA.SaveDataMConfig(_db, _data);
            } else {
                flag = false;
                ErrorMess = (String) innerObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
        _db.close();
    }

    public clsPushData pushData(String versionName) {
        clsPushData dtclsPushData = new clsPushData();
        dataJson dtPush = new dataJson();
        SQLiteDatabase db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(db);
        HashMap<clsMappingPushFile, byte[]> FileUpload = null;
        if (_tUserLoginDA.getContactsCount(db) > 0) {
            tUserLoginData _tUserLoginData = _tUserLoginDA.getData(db, 1);
            dtPush.set_txtVersionApp(versionName);
            dtPush.set_txtUserId(_tUserLoginData.get_txtUserId());
            dtPush.set_txtSessionLoginId(_tUserLoginData.get_txtDataId());
            dtPush.set_intRoleId(_tUserLoginData.get_txtRoleId());
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                mCounterNumberDA _mCounterNumberDA = new mCounterNumberDA(db);
                mCounterNumberData _data = new mCounterNumberData();
                _data.set_intId(enumCounterData.MonitorSchedule.getidCounterData());
                _data.set_txtDeskripsi("value menunjukan waktu terakhir menjalankan services");
                _data.set_txtName("Monitor Service");
                _data.set_txtValue(dateFormat.format(cal.getTime()));
                _mCounterNumberDA.SaveDataMConfig(db, _data);

                //new clsInit().PushData(db,versionName);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            tVisitPlanRealisasiDA _tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
            tVisitPlanHeader_MobileDA _tVisitPlanHeader_MobileDA = new tVisitPlanHeader_MobileDA(db);
            tAbsenUserDA _tAbsenUserDA = new tAbsenUserDA(db);
            tAttendanceUserDA _tAttendanceUserDA = new tAttendanceUserDA(db);
            tActivityDA _tActivityDA = new tActivityDA(db);
            tActivityMobileDA _tActivityMobileDA = new tActivityMobileDA(db);
            tPlanogramMobileDA _tPlanogramMobileDA = new tPlanogramMobileDA(db);
            tPlanogramImageDA _tPlanogramImageDA = new tPlanogramImageDA(db);
            tLeaveMobileDA _tLeaveMobileDA =new tLeaveMobileDA(db);
            tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(db);
            tSalesProductDetailDA _tSalesProductDetailDA = new tSalesProductDetailDA(db);
            tPurchaseOrderDetailDA _tPurchaseOrderDetailDA = new tPurchaseOrderDetailDA(db);
            tJawabanUserDA _tJawabanUserDA = new tJawabanUserDA(db);
            tHirarkiBISDA _tHirarkiBISDA = new tHirarkiBISDA(db);
            tJawabanUserHeaderDA _tJawabanUserHeaderDA = new tJawabanUserHeaderDA(db);
            tPOPStandardDetailDA _tPOPStandardDetailDA = new tPOPStandardDetailDA(db);
            tPOPStandardHeaderDA _tPOPStandardHeaderDA = new tPOPStandardHeaderDA(db);
            mTypePOPStandardDA _mTypePOPStandardDA = new mTypePOPStandardDA(db);
            mCategoryPOPStandardDA _mCategoryPOPStandardDA = new mCategoryPOPStandardDA(db);
            mReasonPOPStandardDA _mReasonPOPStandardDA = new mReasonPOPStandardDA(db);
            tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(db);
            tSalesProductQuantityHeaderDA _tSalesProductQuantityDA = new tSalesProductQuantityHeaderDA(db);
            tSalesProductQuantityDetailDA _tSalesProductQuantityDetailDA = new tSalesProductQuantityDetailDA(db);
            tKemasanRusakHeaderDA _tKemasanRusakHeaderDA = new tKemasanRusakHeaderDA(db);
            tKemasanRusakDetailDA _tKemasanRusakDetailDA = new tKemasanRusakDetailDA(db);
            tOverStockHeaderDA _tOverStockHeaderDA = new tOverStockHeaderDA(db);
            tOverStockDetailDA _tOverStockDetailDA = new tOverStockDetailDA(db);
            tSalesProductQuantityImageDA _tSalesProductQuantityImageDA = new tSalesProductQuantityImageDA(db);
            tKemasanRusakImageDA _tKemasanRusakImageDA = new tKemasanRusakImageDA(db);
            tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(db);
            tCustomerBasedMobileDetailDA _tCustomerBasedMobileDetailDA = new tCustomerBasedMobileDetailDA(db);
            tCustomerBasedMobileDetailProductDA _tCustomerBasedMobileDetailProductDA = new tCustomerBasedMobileDetailProductDA(db);
            trackingLocationDA _trackingLocationDA = new trackingLocationDA(db);
            KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(db);
            mCategoryKoordinasiOutletDA _mCategoryKoordinasiOutletDA = new mCategoryKoordinasiOutletDA(db);
            KoordinasiOutletImageDA _KoordinasiOutletImageDA = new KoordinasiOutletImageDA(db);
            tTidakSesuaiPesananHeaderDA _tTidakSesuaiPesananHeaderDA = new tTidakSesuaiPesananHeaderDA(db);
            tTidakSesuaiPesananImageDA _tTidakSesuaiPesananImageDA = new tTidakSesuaiPesananImageDA(db);
            tStockInHandDetailDA _tStockInHandDetailDA = new tStockInHandDetailDA(db);
            tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(db);

            clsLogReceiverHeader_mobileDA _clsLogReceiverHeader_mobileDA = new clsLogReceiverHeader_mobileDA(db);
            clsLogReceiverDetail_mobileDA _clsLogReceiverDetail_mobileDA = new clsLogReceiverDetail_mobileDA(db);

            List<tVisitPlanHeader_MobileData> ListOftVisitPlanHeader_MobileData = _tVisitPlanHeader_MobileDA.getPushData(db);
            List<tVisitPlanRealisasiData> ListOftVisitPlanRealisasiDataDetail = _tVisitPlanRealisasiDA.getPushData(db);
            List<tCustomerBasedMobileHeaderData> ListOftCustomerBasedMobileHeader = _tCustomerBasedMobileHeaderDA.getPushData(db);
            List<tCustomerBasedMobileDetailData> ListOftCustomerBasedMobileDetail = _tCustomerBasedMobileDetailDA.getPushData(db, ListOftCustomerBasedMobileHeader);
            List<tCustomerBasedMobileDetailProductData> ListOftCustomerBasedMobileDetailProduct = _tCustomerBasedMobileDetailProductDA.getPushData(db, ListOftCustomerBasedMobileDetail);

            List<tSalesProductHeaderData> ListOfSalesProductHeader = _tSalesProductHeaderDA.getAllDataToPushData(db);
            List<tSalesProductDetailData> ListOfSalesProductDetail = _tSalesProductDetailDA.getAllDataToPushData(db, ListOfSalesProductHeader);
            List<tStockInHandHeaderData> ListOftStockInHandHeaderData = _tStockInHandHeaderDA.getAllDataToPushData(db);
            List<tStockInHandDetailData> ListOftStockInHandDetailData = _tStockInHandDetailDA.getAllDataToPushData(db, ListOftStockInHandHeaderData);
            List<tPurchaseOrderHeaderData> ListOfPurchaseOrderHeader = _tPurchaseOrderHeaderDA.getAllDataToPushData(db);
            List<tPurchaseOrderDetailData> ListOfPurchaseOrderDetail = _tPurchaseOrderDetailDA.getAllDataToPushDataPO(db, ListOfPurchaseOrderHeader);
            List<tSalesProductQuantityHeaderData> ListOfSalesProductQuantityHeader = _tSalesProductQuantityDA.getAllDataToPushData(db);
            List<tOverStockHeaderData> ListOftOverStockHeader = _tOverStockHeaderDA.getAllDataToPushData(db);
            List<tJawabanUserData> ListOfJawabanUser = _tJawabanUserDA.GetDataToPushAnswer(db);
            List<tJawabanUserHeaderData> ListOfJawabanUserHeader = _tJawabanUserHeaderDA.GetDataToPushAnswer(db);
            List<tPOPStandardHeaderData> ListOftPOPStandardHeader = _tPOPStandardHeaderDA.GetDataToPush(db);
            List<tPOPStandardDetailData> ListOftPOPStandarDetail = _tPOPStandardDetailDA.GetDataToPush(db);
            List<tSalesProductQuantityDetailData> ListOfSalesProductQuantityDetail = _tSalesProductQuantityDetailDA.getAllDataToPushData(db, ListOfSalesProductQuantityHeader);
            List<tKemasanRusakHeaderData> ListOftKemasanRusakHeaderData = _tKemasanRusakHeaderDA.getAllDataToPushData(db);
            List<tKemasanRusakDetailData> ListOftKemasanRusakDetailData = _tKemasanRusakDetailDA.getAllDataToPushData(db, ListOftKemasanRusakHeaderData);
            List<tKemasanRusakImageData> ListOfKemasanRusakImage = _tKemasanRusakImageDA.getAllDataToPushData(db, ListOftKemasanRusakHeaderData);
            List<tOverStockDetailData> ListOftOverStockDetail = _tOverStockDetailDA.getAllDataToPushData(db, ListOftOverStockHeader);
            List<trackingLocationData> ListOfTrackingLocation = _trackingLocationDA.getAllDataToPushData(db);
            List<KoordinasiOutletData> ListOfKoordinasiOutlet = _KoordinasiOutletDA.getAllDataToPushData(db);
            List<KoordinasiOutletImageData> ListOfKoordinasiOutletImage = _KoordinasiOutletImageDA.getAllDataToPushData(db, ListOfKoordinasiOutlet);
            List<tTidakSesuaiPesananHeaderData> ListOftTidakSesuaiPesananHeaderData = _tTidakSesuaiPesananHeaderDA.getAllDataToPushData(db);
            List<tTidakSesuaiPesananImageData> ListOftTidakSesuaiPesananImageData = _tTidakSesuaiPesananImageDA.getAllDataToPushData(db, ListOftTidakSesuaiPesananHeaderData);
            List<tLeaveMobileData> ListOftLeaveData=_tLeaveMobileDA.getAllDataPushData(db);
            List<tAbsenUserData> ListOftAbsenUserData = _tAbsenUserDA.getAllDataToPushData(db);
            List<tAttendanceUserData> ListOftAttendanceUserData = _tAttendanceUserDA.getAllDataToPushData(db);
            List<tActivityData> ListOftActivityData = _tActivityDA.getAllDataToPushData(db);
            List<tActivityMobileData> ListOftActivityMobileData = _tActivityMobileDA.getAllDataToPushData(db);
            List<tPlanogramMobileData> ListOftPlanogramMobileData = _tPlanogramMobileDA.getAllDataToPushData(db);
            List<tPlanogramImageData> ListOftPlanogramImageData = _tPlanogramImageDA.getAllDataToPushData(db, ListOftPlanogramMobileData);

            List<clsLogReceiverHeader_mobile> ListOfLogReceiverHeader_mobile = _clsLogReceiverHeader_mobileDA.getAllDataToPushData(db);
            List<clsLogReceiverDetail_mobile> ListOfLogReceiverDetail_mobile = _clsLogReceiverDetail_mobileDA.getAllDataToPushData(db);

            FileUpload = new HashMap<clsMappingPushFile, byte[]>();
            if (ListOftAbsenUserData != null) {
                dtPush.setListOftAbsenUserData(ListOftAbsenUserData);
                for (tAbsenUserData dttAbsenUserData : ListOftAbsenUserData) {
                    if (dttAbsenUserData.get_txtImg1() != null) {
                        clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                        mappingPushFile.setKey("FUAbsen" + dttAbsenUserData.get_intId() + "-1");
                        mappingPushFile.setEkstension(".jpg");
                        FileUpload.put(mappingPushFile, dttAbsenUserData.get_txtImg1());
                    }
                    if (dttAbsenUserData.get_txtImg2() != null) {
                        clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                        mappingPushFile.setKey("FUAbsen" + dttAbsenUserData.get_intId() + "-2");
                        mappingPushFile.setEkstension(".jpg");
                        FileUpload.put(mappingPushFile, dttAbsenUserData.get_txtImg2());
                    }
                }
            }
            if (ListOftAttendanceUserData != null) {
                dtPush.setListOftAttendanceUserData(ListOftAttendanceUserData);
                for (tAttendanceUserData dttAttendanceUserData : ListOftAttendanceUserData) {
                    if (dttAttendanceUserData.get_txtImg1() != null) {
                        clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                        mappingPushFile.setKey("FUAttendanceFPE" + dttAttendanceUserData.get_intId() + "-1");
                        mappingPushFile.setEkstension(".jpg");
                        FileUpload.put(mappingPushFile, dttAttendanceUserData.get_txtImg1());
                    }
                    if (dttAttendanceUserData.get_txtImg2() != null) {
                        clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                        mappingPushFile.setKey("FUAttendanceFPE" + dttAttendanceUserData.get_intId() + "-2");
                        mappingPushFile.setEkstension(".jpg");
                        FileUpload.put(mappingPushFile, dttAttendanceUserData.get_txtImg2());
                    }
                }
            }
            if (ListOftActivityData != null) {
                dtPush.setListOftActivityData(ListOftActivityData);
                for (tActivityData dttActivityData : ListOftActivityData) {
                    if (dttActivityData.get_txtImg1() != null) {
                        clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                        mappingPushFile.setKey("FUActivity" + dttActivityData.get_intId() + "-1");
                        mappingPushFile.setEkstension(".jpg");
                        FileUpload.put(mappingPushFile, dttActivityData.get_txtImg1());
                    }
                    if (dttActivityData.get_txtImg2() != null) {
                        clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                        mappingPushFile.setKey("FUActivity" + dttActivityData.get_intId() + "-2");
                        mappingPushFile.setEkstension(".jpg");
                        FileUpload.put(mappingPushFile, dttActivityData.get_txtImg2());
                    }
                }
            }
            if (ListOftActivityMobileData != null) {
                dtPush.setListOftActivityMobileData(ListOftActivityMobileData);
                for (tActivityMobileData dttActivityMobileData : ListOftActivityMobileData) {

                    if (dttActivityMobileData.get_txtImg1() != null) {
                        clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                        mappingPushFile.setKey("FUActivityMobileNew" + dttActivityMobileData.get_intId() + "-1");
                        mappingPushFile.setEkstension(".jpg");
                        FileUpload.put(mappingPushFile, dttActivityMobileData.get_txtImg1());
                    }
                    if (dttActivityMobileData.get_txtImg2() != null) {
                        clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                        mappingPushFile.setKey("FUActivityMobileNew" + dttActivityMobileData.get_intId() + "-2");
                        mappingPushFile.setEkstension(".jpg");
                        FileUpload.put(mappingPushFile, dttActivityMobileData.get_txtImg2());
                    }
                }
            }
            if (ListOfKemasanRusakImage != null){
                dtPush.setListOftKemasanRusakImageData(ListOfKemasanRusakImage);
                for (tKemasanRusakImageData dttImageData : ListOfKemasanRusakImage) {
                    if (dttImageData.get_txtImage() != null) {
                        if (dttImageData.get_txtType().equals("After") &&  dttImageData.get_intPosition().equals("1")) {
                            clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                            mappingPushFile.setKey("FUKRS" + dttImageData.get_txtId());
                            mappingPushFile.setEkstension(".jpg");
                            FileUpload.put(mappingPushFile, dttImageData.get_txtImage());
                        }
                        if (dttImageData.get_txtType().equals("After") &&  dttImageData.get_intPosition().equals("2")) {
                            clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                            mappingPushFile.setKey("FUKRS" + dttImageData.get_txtId());
                            mappingPushFile.setEkstension(".jpg");
                            FileUpload.put(mappingPushFile, dttImageData.get_txtImage());
                        }
                    }
                }
            }
            if (ListOftPlanogramImageData != null){
                dtPush.setListOftPlanogramImageData(ListOftPlanogramImageData);
                for (tPlanogramImageData dttPlanogramImageData : ListOftPlanogramImageData) {
                    clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                    if (dttPlanogramImageData.get_txtImage() != null) {
                        if (dttPlanogramImageData.get_txtType().equals("AFTER") &&  dttPlanogramImageData.get_intPosition().equals("1")) {
                            mappingPushFile.setKey("FUPlanogram" + dttPlanogramImageData.get_txtId());
                            mappingPushFile.setEkstension(".jpg");
                            FileUpload.put(mappingPushFile, dttPlanogramImageData.get_txtImage());
                        }
                        if (dttPlanogramImageData.get_txtType().equals("AFTER") &&  dttPlanogramImageData.get_intPosition().equals("2")) {
                            mappingPushFile.setKey("FUPlanogram" + dttPlanogramImageData.get_txtId());
                            mappingPushFile.setEkstension(".jpg");
                            FileUpload.put(mappingPushFile, dttPlanogramImageData.get_txtImage());
                        }
                        if (dttPlanogramImageData.get_txtType().equals("BEFORE") &&  dttPlanogramImageData.get_intPosition().equals("1")) {
                            mappingPushFile.setKey("FUPlanogram" + dttPlanogramImageData.get_txtId());
                            mappingPushFile.setEkstension(".jpg");
                            FileUpload.put(mappingPushFile, dttPlanogramImageData.get_txtImage());
                        }
                        if (dttPlanogramImageData.get_txtType().equals("BEFORE") &&  dttPlanogramImageData.get_intPosition().equals("2")) {
                            mappingPushFile.setKey("FUPlanogram" + dttPlanogramImageData.get_txtId());
                            mappingPushFile.setEkstension(".jpg");
                            FileUpload.put(mappingPushFile, dttPlanogramImageData.get_txtImage());
                        }
                    }
                }
            }
            if (ListOfKoordinasiOutletImage != null){
                dtPush.setListOfKoordinasiOutletImageData(ListOfKoordinasiOutletImage);
                for (KoordinasiOutletImageData dttKoordinasiOutletImageData : ListOfKoordinasiOutletImage) {
                    clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                    if (dttKoordinasiOutletImageData.get_txtImage() != null) {
                        if (dttKoordinasiOutletImageData.get_intPosition().equals("1")) {
                            mappingPushFile.setKey("FUKDO" + dttKoordinasiOutletImageData.get_txtId());
                            mappingPushFile.setEkstension(".jpg");
                            FileUpload.put(mappingPushFile, dttKoordinasiOutletImageData.get_txtImage());
                        }
                        if (dttKoordinasiOutletImageData.get_intPosition().equals("2")) {
                            mappingPushFile.setKey("FUKDO" + dttKoordinasiOutletImageData.get_txtId());
                            mappingPushFile.setEkstension(".jpg");
                            FileUpload.put(mappingPushFile, dttKoordinasiOutletImageData.get_txtImage());
                        }
                    }
                }
            }
            if (ListOftTidakSesuaiPesananImageData != null){
                dtPush.setListOftTidakSesuaiPesananImageData(ListOftTidakSesuaiPesananImageData);
                for (tTidakSesuaiPesananImageData dttTidakSesuaiPesananImageData : ListOftTidakSesuaiPesananImageData) {
                    if (dttTidakSesuaiPesananImageData.get_txtImage() != null) {
                        if (dttTidakSesuaiPesananImageData.get_intPosition().equals("1")) {
                            clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                            mappingPushFile.setKey("FUTSPO" + dttTidakSesuaiPesananImageData.get_txtId());
                            mappingPushFile.setEkstension(".jpg");
                            FileUpload.put(mappingPushFile, dttTidakSesuaiPesananImageData.get_txtImage());
                        }
                        if (dttTidakSesuaiPesananImageData.get_intPosition().equals("2")) {
                            clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                            mappingPushFile.setKey("FUTSPO" + dttTidakSesuaiPesananImageData.get_txtId());
                            mappingPushFile.setEkstension(".jpg");
                            FileUpload.put(mappingPushFile, dttTidakSesuaiPesananImageData.get_txtImage());
                        }
                    }
                }
            }
            if (ListOftVisitPlanRealisasiDataDetail != null){
                for (tVisitPlanRealisasiData dttVisitPlanRealisasiData : ListOftVisitPlanRealisasiDataDetail) {
                    clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                    if (dttVisitPlanRealisasiData.get_dtPhoto1() != null) {
                        mappingPushFile.setKey("VisitPlan" + dttVisitPlanRealisasiData.get_txtDataIDRealisasi() + "-1");
                        mappingPushFile.setEkstension(".jpg");
                        FileUpload.put(mappingPushFile, dttVisitPlanRealisasiData.get_dtPhoto1());
                    }
                    if (dttVisitPlanRealisasiData.get_dtPhoto2() != null) {
                        mappingPushFile.setKey("VisitPlan" + dttVisitPlanRealisasiData.get_txtDataIDRealisasi() + "-2");
                        mappingPushFile.setEkstension(".jpg");
                        FileUpload.put(mappingPushFile, dttVisitPlanRealisasiData.get_dtPhoto2());
                    }
                }
                dtPush.setListOftVisitPlanRealisasiData(ListOftVisitPlanRealisasiDataDetail);
            }
            if (ListOftVisitPlanHeader_MobileData != null){
                dtPush.setListOftVisitPlanHeader_MobileData(ListOftVisitPlanHeader_MobileData);
            }
            if(ListOftLeaveData!=null){
                dtPush.setListOftLeaveMobileData(ListOftLeaveData);
            }
//
            if (ListOfSalesProductHeader != null) {
                dtPush.setListOftSalesProductHeaderData(ListOfSalesProductHeader);
            }

            if (ListOfSalesProductDetail != null) {
                dtPush.setListOftSalesProductDetailData(ListOfSalesProductDetail);
            }
            if (ListOftStockInHandHeaderData != null) {
                dtPush.setListOftStockInHandHeaderData(ListOftStockInHandHeaderData);
            }

            if (ListOftStockInHandDetailData != null) {
                dtPush.setListOftStockInHandDetailData(ListOftStockInHandDetailData);
            }
            if (ListOfJawabanUser != null){
                for (tJawabanUserData dttJawabanUserData : ListOfJawabanUser) {
                    clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                    if (dttJawabanUserData.get_ptQuiz() != null) {
                        String fileName = dttJawabanUserData.get_txtValue();
                        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                        mappingPushFile.setKey("FUQuizMobileNew" + dttJawabanUserData.get_intUserAnswer() + "-1");
                        mappingPushFile.setEkstension(fileExtension);
                        FileUpload.put(mappingPushFile, dttJawabanUserData.get_ptQuiz());
                    }
                    if (dttJawabanUserData.get_txtFileQuiz() != null) {
                        String fileName = dttJawabanUserData.get_txtValue();
                        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                        mappingPushFile.setKey("FUQuizFileMobileNew" + dttJawabanUserData.get_intUserAnswer() + "-2");
                        mappingPushFile.setEkstension(fileExtension);
                        FileUpload.put(mappingPushFile, dttJawabanUserData.get_txtFileQuiz());
                    }
                }
                dtPush.setListOftJawabanUserData(ListOfJawabanUser);
            }

            if (ListOfJawabanUserHeader  != null){
                dtPush.setListOftJawabanUserHeaderData(ListOfJawabanUserHeader);
            }
            if (ListOftPOPStandardHeader != null){
                dtPush.setListOftPOPStandarHeaderdData(ListOftPOPStandardHeader);
            }
            if (ListOftPOPStandarDetail != null){
                for (tPOPStandardDetailData dttPOPStandardDetailData : ListOftPOPStandarDetail){
                    if (dttPOPStandardDetailData.get_txtImg1() != null){
                        clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                        mappingPushFile.setKey("FUPOPStandard" + dttPOPStandardDetailData.get_intId() + "-1");
                        mappingPushFile.setEkstension(".jpg");
                        FileUpload.put(mappingPushFile, dttPOPStandardDetailData.get_txtImg1());
                    }
                    if (dttPOPStandardDetailData.get_txtImg2() != null){
                        clsMappingPushFile mappingPushFile = new clsMappingPushFile();
                        mappingPushFile.setKey("FUPOPStandard" + dttPOPStandardDetailData.get_intId() + "-2");
                        mappingPushFile.setEkstension(".jpg");
                        FileUpload.put(mappingPushFile, dttPOPStandardDetailData.get_txtImg2());
                    }
                }
                dtPush.setListOftPOPStandardDetailData(ListOftPOPStandarDetail);
            }
            if (ListOfPurchaseOrderDetail != null){
                dtPush.setListOftPurchaseOrderDetailData(ListOfPurchaseOrderDetail);
            }
            if (ListOfPurchaseOrderHeader != null){
                dtPush.setListOftPurchaseOrderHeaderData(ListOfPurchaseOrderHeader);
            }

            if (ListOfSalesProductQuantityDetail != null){
                dtPush.setListOftSalesProductQuantityDetailData(ListOfSalesProductQuantityDetail);
            }
            if (ListOfSalesProductQuantityHeader != null){
                dtPush.setListOftSalesProductQuantityData(ListOfSalesProductQuantityHeader);
            }

            if (ListOftKemasanRusakDetailData != null){
                dtPush.setListOftKemasanRusakDetailData(ListOftKemasanRusakDetailData);
            }
            if (ListOftKemasanRusakHeaderData != null){
                dtPush.setListOftKemasanRusakHeaderData(ListOftKemasanRusakHeaderData);
            }

            if (ListOftOverStockDetail != null){
                dtPush.setListOftOverStockDetailData(ListOftOverStockDetail);
            }
            if (ListOftOverStockHeader != null){
                dtPush.setListOftOverStockHeaderData(ListOftOverStockHeader);
            }

            if (ListOftPlanogramMobileData != null){
                dtPush.setListOftPlanogramMobileData(ListOftPlanogramMobileData);
            }

            if (ListOfTrackingLocation != null){
                dtPush.setListOfTrackingLocationData(ListOfTrackingLocation);
            }

            if (ListOfKoordinasiOutlet != null) {
                dtPush.setListOfKoordinasiOutletData(ListOfKoordinasiOutlet);
            }
            if (ListOftTidakSesuaiPesananHeaderData != null) {
                dtPush.setListOftTidakSesuaiPesananHeaderData(ListOftTidakSesuaiPesananHeaderData);
            }
            if (ListOftCustomerBasedMobileHeader != null) {
                dtPush.set_ListOftCustomerBasedMobileHeaderData(ListOftCustomerBasedMobileHeader);
            }

            if (ListOftCustomerBasedMobileDetail != null) {
                dtPush.setListOftCustomerBasedMobileDetailData(ListOftCustomerBasedMobileDetail);
            }

            if (ListOftCustomerBasedMobileDetailProduct != null) {
                dtPush.setListOftCustomerBasedMobileDetailProductData(ListOftCustomerBasedMobileDetailProduct);
            }/*
            if (ListOftVisitPlanHeader_MobileData != null){
                dtPush.setListOftVisitPlanHeader_MobileData(ListOftVisitPlanHeader_MobileData);
            }
*/
            if (ListOfLogReceiverHeader_mobile != null){
                dtPush.setListOfLogReceiverHeader_mobile(ListOfLogReceiverHeader_mobile);
            }

            if (ListOfLogReceiverDetail_mobile != null){
                dtPush.setListOfLogReceiverDetail_mobile(ListOfLogReceiverDetail_mobile);
            }
        } else {
            dtPush = null;
        }
        db.close();
        dtclsPushData.setDtdataJson(dtPush);
        dtclsPushData.setFileUpload(FileUpload);
        return dtclsPushData;
    }

    public clsPushData downloadData(String versionName) {
        clsPushData dtclsPushData = new clsPushData();
        dataJson dtPush = new dataJson();
        SQLiteDatabase db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(db);
        HashMap<clsMappingPushFile, byte[]> FileUpload = null;
        if (_tUserLoginDA.getContactsCount(db) > 0) {
            tUserLoginData _tUserLoginData = _tUserLoginDA.getData(db, 1);
            dtPush.set_txtVersionApp(versionName);
            dtPush.set_txtUserId(_tUserLoginData.get_txtUserId());
            dtPush.set_txtSessionLoginId(_tUserLoginData.get_txtDataId());
            dtPush.set_intRoleId(_tUserLoginData.get_txtRoleId());
            dtPush.set_txtEmpId(_tUserLoginData.get_TxtEmpId());
            dtPush.set_txtBranchCode(_tUserLoginData.get_txtBranchCode());

//            try {
//                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//                Calendar cal = Calendar.getInstance();
//                mCounterNumberDA _mCounterNumberDA = new mCounterNumberDA(db);
//                mCounterNumberData _data = new mCounterNumberData();
//                _data.set_intId(enumCounterData.MonitorSchedule.getidCounterData());
//                _data.set_txtDeskripsi("value menunjukan waktu terakhir menjalankan services");
//                _data.set_txtName("Monitor Service");
//                _data.set_txtValue(dateFormat.format(cal.getTime()));
//                _mCounterNumberDA.SaveDataMConfig(db, _data);
//
//                //new clsInit().PushData(db,versionName);
//            } catch (Exception e1) {
//                // TODO Auto-generated catch block
//                e1.printStackTrace();
//            }

            //master data
            tSubTypeActivityData dttSubTypeActivityData = new tSubTypeActivityData();
            tKategoryPlanogramMobileData dttKategoryPlanogramMobileData = new tKategoryPlanogramMobileData();
            mEmployeeBranchData dtmEmployeeBranchData = new mEmployeeBranchData();
            mTypeLeaveMobileData dtmTypeLeaveMobileData = new mTypeLeaveMobileData();
            mEmployeeSalesProductData dtmEmployeeSalesProductData = new mEmployeeSalesProductData();
            mProductBrandHeaderData dtmProductBrandHeaderData = new mProductBrandHeaderData();
            mCategoryVisitPlanData dtmCategoryVisitPlanData = new mCategoryVisitPlanData();
            mEmployeeAreaData dtmEmployeeAreaData = new mEmployeeAreaData();
            mProductCompetitorData dtmProductCompetitorData = new mProductCompetitorData();
            mProductSPGData dtmProductSPGData = new mProductSPGData();
            mProductPICData dtmProductPICData = new mProductPICData();
            mTypeSubmissionMobile dtmTypeSubmissionMobile = new mTypeSubmissionMobile();
            mCategoryKoordinasiOutletData dtmCategoryKoordinasiOutletData = new mCategoryKoordinasiOutletData();

            //master data
            dttSubTypeActivityData.setBoolValid("0");
            dttKategoryPlanogramMobileData.setBoolValid("0");
            dtmEmployeeBranchData.setBoolValid("0");
            dtmTypeLeaveMobileData.setBoolValid("0");
            dtmEmployeeSalesProductData.setBoolValid("0");
            dtmProductBrandHeaderData.setBoolValid("0");
            dtmCategoryVisitPlanData.setBoolValid("0");
            dtmEmployeeAreaData.setBoolValid("0");
            dtmProductCompetitorData.setBoolValid("0");
            dtmProductSPGData.setBoolValid("0");
            dtmProductPICData.setBoolValid("0");
            dtmTypeSubmissionMobile.setBoolValid("0");
            dtmCategoryKoordinasiOutletData.setBoolValid("0");

            dtPush.setDttSubTypeActivityData(dttSubTypeActivityData);
            dtPush.setDttKategoryPlanogramMobileData(dttKategoryPlanogramMobileData);
            dtPush.setDtmEmployeeBranchData(dtmEmployeeBranchData);
            dtPush.setDtmTypeLeaveMobileData(dtmTypeLeaveMobileData);
            dtPush.setDtmEmployeeSalesProductData(dtmEmployeeSalesProductData);
            dtPush.setDtmProductBrandHeaderData(dtmProductBrandHeaderData);
            dtPush.setDtmCategoryVisitPlanData(dtmCategoryVisitPlanData);
            dtPush.setDtmEmployeeAreaData(dtmEmployeeAreaData);
            dtPush.setDtmProductCompetitorData(dtmProductCompetitorData);
            dtPush.setDtmProductSPGData(dtmProductSPGData);
            dtPush.setDtmProductPICData(dtmProductPICData);
            dtPush.setDtmTypeSubmissionMobile(dtmTypeSubmissionMobile);
            dtPush.setDtmCategoryKoordinasiOutletData(dtmCategoryKoordinasiOutletData);

            //transaction data
            tVisitPlanRealisasiData dttVisitPlanRealisasiData = new tVisitPlanRealisasiData();
            tAttendanceUserData dttAttendanceUserData = new tAttendanceUserData();
            //no so
            tSalesProductHeaderData dttSalesProductHeaderData = new tSalesProductHeaderData();
            //no po
            tPurchaseOrderHeaderData dttPurchaseOrderHeaderData = new tPurchaseOrderHeaderData();
            tStockInHandHeaderData dttStockInHandHeaderData = new tStockInHandHeaderData();
            mParentData dtmParentData = new mParentData();
            tHirarkiBIS dttHirarkiBIS = new tHirarkiBIS();
            mTypePOPStandardData dtmTypePOPStandardData = new mTypePOPStandardData();
            //noQTS
            tSalesProductQuantityHeaderData dttSalesProductQuantityHeaderData = new tSalesProductQuantityHeaderData();
            //noOVS
            tOverStockHeaderData dttOverStockHeaderData = new tOverStockHeaderData();
            trackingLocationData dttrackingLocationData = new trackingLocationData();
            KoordinasiOutletData dtKoordinasiOutletData = new KoordinasiOutletData();
            tPlanogramMobileData dttPlanogramMobileData = new tPlanogramMobileData();
            tActivityData dttActivityData = new tActivityData();
            tActivityMobileData dttActivityMobileData = new tActivityMobileData();
            tCustomerBasedMobileHeaderData dttCustomerBasedMobileHeaderData = new tCustomerBasedMobileHeaderData();
            tAbsenUserData dttAbsenUserData = new tAbsenUserData();
            tLeaveMobileData dttLeaveMobileData = new tLeaveMobileData();
            tKemasanRusakHeaderData dttKemasanRusakHeaderData = new tKemasanRusakHeaderData();
            tTidakSesuaiPesananHeaderData dttTidakSesuaiPesananHeaderData = new tTidakSesuaiPesananHeaderData();

            //transaction data
            dtmTypePOPStandardData.setBoolValid("0");
            dttVisitPlanRealisasiData.setBoolValid("0");
            dttAttendanceUserData.setBoolValid("0");
            dttSalesProductHeaderData.setBoolValid("0");
            dttPurchaseOrderHeaderData.setBoolValid("0");
            dttStockInHandHeaderData.setBoolValid("0");
            dtmParentData.setBoolValid("0");
            dttHirarkiBIS.setBoolValid("0");
            dttSalesProductQuantityHeaderData.setBoolValid("0");
            dttOverStockHeaderData.setBoolValid("0");
            dttrackingLocationData.setBoolValid("0");
            dtKoordinasiOutletData.setBoolValid("0");
            dttPlanogramMobileData.setBoolValid("0");
            dttActivityData.setBoolValid("0");
            dttActivityMobileData.setBoolValid("0");
            dttCustomerBasedMobileHeaderData.setBoolValid("0");
            dttAbsenUserData.setBoolValid("0");
            dttLeaveMobileData.setBoolValid("0");
            dttKemasanRusakHeaderData.setBoolValid("0");
            dttTidakSesuaiPesananHeaderData.setBoolValid("0");

            //Transaction data
            dtPush.setDttVisitPlanRealisasiData(dttVisitPlanRealisasiData);
            dtPush.setDttAttendanceUserData(dttAttendanceUserData);
            dtPush.setDttSalesProductHeaderData(dttSalesProductHeaderData);
            dtPush.setDttPurchaseOrderHeaderData(dttPurchaseOrderHeaderData);
            dtPush.setDttStockInHandHeaderData(dttStockInHandHeaderData);
            dtPush.setDtmParentData(dtmParentData);
            dtPush.setDttHirarkiBIS(dttHirarkiBIS);
            dtPush.setDttSalesProductQuantityHeaderData(dttSalesProductQuantityHeaderData);
            dtPush.setDttOverStockHeaderData(dttOverStockHeaderData);
            dtPush.setDttrackingLocationData(dttrackingLocationData);
            dtPush.setDtKoordinasiOutletData(dtKoordinasiOutletData);
            dtPush.setDttPlanogramMobileData(dttPlanogramMobileData);
            dtPush.setDttActivityData(dttActivityData);
            dtPush.setDttActivityMobileData(dttActivityMobileData);
            dtPush.setDttCustomerBasedMobileHeaderData(dttCustomerBasedMobileHeaderData);
            dtPush.setDttAbsenUserData(dttAbsenUserData);
            dtPush.setDttLeaveMobileData(dttLeaveMobileData);
            dtPush.setDttKemasanRusakHeaderData(dttKemasanRusakHeaderData);
            dtPush.setDttTidakSesuaiPesananHeaderData(dttTidakSesuaiPesananHeaderData);
            dtPush.setDtmTypePOPStandardData(dtmTypePOPStandardData);

        } else {
            dtPush = null;
        }
        db.close();
        dtclsPushData.setDtdataJson(dtPush);
        dtclsPushData.setFileUpload(FileUpload);
        return dtclsPushData;
    }

    public void saveDataPush(dataJson dtJson, org.json.simple.JSONArray JsonResult) {
        SQLiteDatabase db = getDb();
        APIData dtAPIDATA = new APIData();
        Iterator i = JsonResult.iterator();
        boolean validPush = false;
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                validPush = true;
            } else {
                validPush = false;
                break;
            }
        }

        if (validPush && dtJson.getListOftAbsenUserData() != null) {
            Iterator j = null;
            j = JsonResult.iterator();
            while (j.hasNext()) {
                org.json.simple.JSONObject innerObj_Header = (org.json.simple.JSONObject) j.next();
                org.json.simple.JSONArray JsonArray_Detail = (JSONArray) innerObj_Header.get("ListOftAbsenUser_mobile");
                Iterator jDetail = JsonArray_Detail.iterator();
                while (jDetail.hasNext()) {
                    org.json.simple.JSONObject innerObj_Detail = (org.json.simple.JSONObject) jDetail.next();
                    for (tAbsenUserData dt : dtJson.getListOftAbsenUserData()) {
                        tAbsenUserDA _tAbsenUserDA = new tAbsenUserDA(db);
                        if (String.valueOf(innerObj_Detail.get("TxtDataIdFromSource")).equals(dt.get_intId())) {
                            dt.set_intSync("1");
                            dt.set_txtAbsen(String.valueOf(innerObj_Detail.get("TxtDataId")));
                            _tAbsenUserDA.SaveDatatAbsenUserData(db, dt);
                        }
                    }
                }
            }
        }

        if (validPush && dtJson.getListOftVisitPlanRealisasiData() != null) {
            for (tVisitPlanRealisasiData dt : dtJson.getListOftVisitPlanRealisasiData()) {
                tVisitPlanRealisasiDA _tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
                dt.set_intPush("1");
                _tVisitPlanRealisasiDA.UpdatePushVisitPlan_MobileData(db, dt);
            }
        }
        if (validPush && dtJson.getListOftStockInHandHeaderData() != null) {
            for (tStockInHandHeaderData dt : dtJson.getListOftStockInHandHeaderData()) {
                tStockInHandHeaderDA _tStockInHandHeaderDA = new tStockInHandHeaderDA(db);
                dt.set_intSync("1");
                _tStockInHandHeaderDA.SaveDatatStockInHandHeaderData(db, dt);
            }
        }
        if (validPush && dtJson.getListOftActivityData() != null) {
            for (tActivityData dt : dtJson.getListOftActivityData()) {
                tActivityDA _tActivityDA = new tActivityDA(db);
                dt.set_intIdSyn("1");
                _tActivityDA.SaveDatatActivityData(db, dt);
            }
        }

        if (validPush && dtJson.getListOftActivityMobileData() != null) {
            for (tActivityMobileData dt : dtJson.getListOftActivityMobileData()) {
                tActivityMobileDA _tActivityMobileDA = new tActivityMobileDA(db);
                dt.set_intIdSyn("1");
                _tActivityMobileDA.SaveDatatActivityData(db, dt);
            }
        }

        if (validPush && dtJson.getListOftLeaveMobileData() != null) {
            for (tLeaveMobileData dt : dtJson.getListOftLeaveMobileData()) {
                tLeaveMobileDA _tLeaveMobileDA = new tLeaveMobileDA(db);
                dt.set_intLeaveIdSync("1");
                _tLeaveMobileDA.SaveDataMConfig(db, dt);
            }
        }

        if (validPush && dtJson.getListOftSalesProductHeaderData() != null) {
            for (tSalesProductHeaderData dt : dtJson.getListOftSalesProductHeaderData()) {
                tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(db);
                dt.set_intSync("1");
                _tSalesProductHeaderDA.SaveDatatSalesProductHeaderData(db, dt);
            }
        }

        if (validPush && dtJson.getListOftPurchaseOrderHeaderData() != null){
            for (tPurchaseOrderHeaderData dt : dtJson.getListOftPurchaseOrderHeaderData()){
                tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(db);
                dt.set_intSync("1");
                _tPurchaseOrderHeaderDA.SaveDatatPurchaseOrderHeaderData(db,dt);
            }
        }

        if (validPush && dtJson.getListOftPOPStandarHeaderdData() != null){
            for (tPOPStandardHeaderData dt : dtJson.getListOftPOPStandarHeaderdData()){
                tPOPStandardHeaderDA _tPOPStandardHeaderDA = new tPOPStandardHeaderDA(db);
                dt.set_intSync("1");
                _tPOPStandardHeaderDA.SaveDatatPOPStandardHeader(db, dt);
            }
        }

        if (validPush && dtJson.getListOftPOPStandardDetailData() != null){
            for (tPOPStandardDetailData dt : dtJson.getListOftPOPStandardDetailData()){
                tPOPStandardDetailDA _tPOPStandardDetailDA = new tPOPStandardDetailDA(db);
                dt.set_intSync("1");
                _tPOPStandardDetailDA.SaveDatatPOPStandardDetail(db, dt);
            }
        }
        if (validPush && dtJson.getListOftJawabanUserData() != null){
            for (tJawabanUserData dt : dtJson.getListOftJawabanUserData()){
                tJawabanUserDA _tJawabanUserDA = new tJawabanUserDA(db);
                dt.set_intSync("1");
                _tJawabanUserDA.SaveDatatJawabanUser(db,dt);
            }
        }

        if (validPush && dtJson.getListOftJawabanUserHeaderData() != null){
            for (tJawabanUserHeaderData dt : dtJson.getListOftJawabanUserHeaderData()){
                tJawabanUserHeaderDA _tJawabanUserHeaderDA = new tJawabanUserHeaderDA(db);
                dt.set_intSync("1");
                _tJawabanUserHeaderDA.SaveDatatJawabanUserHeader(db, dt);
            }
        }

        if (validPush && dtJson.getListOftSalesProductQuantityHeaderData() != null){
            for (tSalesProductQuantityHeaderData dt : dtJson.getListOftSalesProductQuantityHeaderData()){
                tSalesProductQuantityHeaderDA _tSalesProductQuantityDA = new tSalesProductQuantityHeaderDA(db);
                dt.set_intSync("1");
                _tSalesProductQuantityDA.SaveDataSalesProductQuantityData(db, dt);
            }
        }

        if (validPush && dtJson.getListOftKemasanRusakHeaderData() != null){
            for (tKemasanRusakHeaderData dt : dtJson.getListOftKemasanRusakHeaderData()){
                tKemasanRusakHeaderDA _tKemasanRusakHeaderDA = new tKemasanRusakHeaderDA(db);
                dt.set_intSync("1");
                _tKemasanRusakHeaderDA.SaveData(db, dt);
            }
        }

        if (validPush && dtJson.getListOftOverStockHeaderData() != null){
            for (tOverStockHeaderData dt : dtJson.getListOftOverStockHeaderData()){
                tOverStockHeaderDA _tOverStockHeaderDA = new tOverStockHeaderDA(db);
                dt.set_intSync("1");
                _tOverStockHeaderDA.SaveDataOverStockData(db, dt);
            }
        }

        if (validPush && dtJson.getListOftPlanogramMobileData() != null){
            for (tPlanogramMobileData dt : dtJson.getListOftPlanogramMobileData()){
                tPlanogramMobileDA _tPlanogramMobileDA = new tPlanogramMobileDA(db);
                dt.set_intSync("1");
                _tPlanogramMobileDA.saveDataPush(db, dt.get_txtIdPlanogram());
            }
        }

        if (validPush && dtJson .getListOftAttendanceUserData() != null){
            for (tAttendanceUserData dt : dtJson.getListOftAttendanceUserData()){
                tAttendanceUserDA _tAttendanceUserDA = new tAttendanceUserDA(db);
                dt.set_intSync("1");
                _tAttendanceUserDA.SaveDataSync(db, dt.get_intId());
            }
        }
        if (validPush && dtJson.getListOfTrackingLocationData() != null){
            for (trackingLocationData dt : dtJson.getListOfTrackingLocationData()){
                trackingLocationDA _trackingLocationDA = new trackingLocationDA(db);
                dt.set_intSync("1");
                _trackingLocationDA.SaveDataTrackingLocation(db, dt);
            }
        }

        if (validPush && dtJson.getListOfKoordinasiOutletData() != null){
            for (KoordinasiOutletData dt : dtJson.getListOfKoordinasiOutletData()){
                KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(db);
                dt.set_intSync("1");
                _KoordinasiOutletDA.SaveDataKoordinasiOutlet(db, dt);
            }
        }

        if (validPush && dtJson.getListOftTidakSesuaiPesananHeaderData() != null){
            for (tTidakSesuaiPesananHeaderData dt : dtJson.getListOftTidakSesuaiPesananHeaderData()){
                tTidakSesuaiPesananHeaderDA _tTidakSesuaiPesananHeaderDA = new tTidakSesuaiPesananHeaderDA(db);
                dt.set_intSync("1");
                _tTidakSesuaiPesananHeaderDA.SaveData(db, dt);
            }
        }

        if (validPush && dtJson.get_ListOftCustomerBasedMobileHeaderData() != null) {
            Iterator j = null;
            j = JsonResult.iterator();
            while (j.hasNext()) {
                org.json.simple.JSONObject innerObj_Header = (org.json.simple.JSONObject) j.next();
                org.json.simple.JSONArray JsonArray_Detail = (JSONArray) innerObj_Header.get("ListOftCustomerBasedMobileHeader");
                Iterator jDetail = JsonArray_Detail.iterator();
                while (jDetail.hasNext()) {
                    org.json.simple.JSONObject innerObj_Detail = (org.json.simple.JSONObject) jDetail.next();
                    for (tCustomerBasedMobileHeaderData dt : dtJson.get_ListOftCustomerBasedMobileHeaderData()) {
                        tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(db);
                        if (String.valueOf(innerObj_Detail.get("_txtTrCustomerId")).equals(dt.get_intTrCustomerId())) {
                            dt.set_intSync("1");
                            dt.set_txtSubmissionId(String.valueOf(innerObj_Detail.get("_txtSubmissionId")));

                            _tCustomerBasedMobileHeaderDA.SaveDatatCustomerBasedMobileHeaderData(db, dt);
                        }
                    }
                }
            }
        }

        if(validPush && dtJson.getListOfLogReceiverHeader_mobile() != null){
            for (clsLogReceiverHeader_mobile dt : dtJson.getListOfLogReceiverHeader_mobile()){
                clsLogReceiverHeader_mobileDA _clsLogReceiverHeader_mobileDA = new clsLogReceiverHeader_mobileDA(db);
                dt.setIntSync("1");
                _clsLogReceiverHeader_mobileDA.SaveDataMConfig(db, dt);
            }
        }

        if(validPush && dtJson.getListOfLogReceiverDetail_mobile() != null){
            for (clsLogReceiverDetail_mobile dt : dtJson.getListOfLogReceiverDetail_mobile()){
                clsLogReceiverDetail_mobileDA _clsLogReceiverDetail_mobileDA = new clsLogReceiverDetail_mobileDA(db);
                dt.setIntSync("1");
                _clsLogReceiverDetail_mobileDA.SaveDataMConfig(db, dt);
            }
        }

        Iterator j2 = null;
        j2 = JsonResult.iterator();
        while (j2.hasNext()) {
            org.json.simple.JSONObject innerObj_Header = (org.json.simple.JSONObject) j2.next();
            org.json.simple.JSONArray JsonArray_Detail = (JSONArray) innerObj_Header.get("ListOfTNotificationHeaderSPG_mobile");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            if (JsonArray_Detail != null) {
                tNotificationBL _tNotificationBL = new tNotificationBL();
                List<tNotificationData> listDatatNotificationData = new ArrayList<tNotificationData>();
                Iterator jDetail = JsonArray_Detail.iterator();
                int index = _tNotificationBL.getContactsCount() + 1;
                while (jDetail.hasNext()) {
                    org.json.simple.JSONObject innerObj_Detail = (org.json.simple.JSONObject) jDetail.next();
                    tNotificationData dttNotificationData = new tNotificationData();
                    dttNotificationData.set_bitActive(String.valueOf(innerObj_Detail.get("_intActive")));
                    dttNotificationData.set_dtPublishEnd(String.valueOf(innerObj_Detail.get("_dtEnd")));
                    dttNotificationData.set_guiID(String.valueOf(innerObj_Detail.get("_txtIdHeaderNotif")));
                    dttNotificationData.set_intIndex(String.valueOf(index));
                    dttNotificationData.set_dtInserted(String.valueOf(dateFormat.format(cal.getTime())));
//                    dttNotificationData.set_intPriority(String.valueOf(innerObj_Detail.get("IntPriority")));
                    dttNotificationData.set_intSubmit("1");
                    dttNotificationData.set_intSync("1");
                    dttNotificationData.set_tPublishStart(String.valueOf(innerObj_Detail.get("_dtStart")));
                    dttNotificationData.set_txtBranchCode("");
                    dttNotificationData.set_txtDescription(String.valueOf(innerObj_Detail.get("_txtDesc")));
                    dttNotificationData.set_txtImage("");
                    dttNotificationData.set_txtLink(String.valueOf(innerObj_Detail.get("_txtLinkFileAttach")));
                    dttNotificationData.set_txtOutlet("");
                    dttNotificationData.set_txtOutletName("");
                    dttNotificationData.set_txtStatus("2");
                    dttNotificationData.set_txtTitle(String.valueOf(innerObj_Detail.get("_txtTitle")));
                    listDatatNotificationData.add(dttNotificationData);
                    index += 1;
                }
                _tNotificationBL.saveData(listDatatNotificationData);
            }
        }

        Iterator j3 = null;
        j3 = JsonResult.iterator();
        while (j3.hasNext()) {
            org.json.simple.JSONObject innerObj_Header = (org.json.simple.JSONObject) j3.next();
            org.json.simple.JSONArray JsonArray_Detail = (JSONArray) innerObj_Header.get("ListOfTFileAttach_mobile");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();

            if (JsonArray_Detail != null) {
                clsFileAttach_mobileBL _clsFileAttach_mobileBL = new clsFileAttach_mobileBL();
                List<clsFileAttach_mobile> listDataclsFileAttach_mobile = new ArrayList<clsFileAttach_mobile>();
                Iterator jDetail = JsonArray_Detail.iterator();
                int index = _clsFileAttach_mobileBL.getContactsCount() + 1;
                while (jDetail.hasNext()) {
                    org.json.simple.JSONObject innerObj_Detail = (org.json.simple.JSONObject) jDetail.next();
                    clsFileAttach_mobile dtclsFileAttach_mobile = new clsFileAttach_mobile();
                    dtclsFileAttach_mobile.set_txtIdHeaderNotif(String.valueOf(innerObj_Detail.get("_txtIdHeaderNotif")));
//                    dtclsFileAttach_mobile.set_byteInitialVector(String.valueOf(innerObj_Detail.get("_byteInitialVector")));
                    dtclsFileAttach_mobile.set_txtIDFile(String.valueOf(innerObj_Detail.get("_txtIDFile")));
                    dtclsFileAttach_mobile.set_dtInserted(String.valueOf(dateFormat.format(cal.getTime())));
                    dtclsFileAttach_mobile.set_intSubmit("1");
                    dtclsFileAttach_mobile.set_intSync("1");
                    dtclsFileAttach_mobile.set_intActive("0");
                    dtclsFileAttach_mobile.set_intLog("1");
                    dtclsFileAttach_mobile.set_txtDesc(String.valueOf(innerObj_Detail.get("_txtDesc")));
                    dtclsFileAttach_mobile.set_txtLinkFileAttach(String.valueOf(innerObj_Detail.get("_txtLinkFileAttach")));
                    dtclsFileAttach_mobile.set_txtNameFileEncrypt(String.valueOf(innerObj_Detail.get("_txtNameFileEncrypt")));
                    dtclsFileAttach_mobile.set_txtTypeFile(String.valueOf(innerObj_Detail.get("_txtTypeFile")));
                    dtclsFileAttach_mobile.set_intStatus("RECEIVED");
                    listDataclsFileAttach_mobile.add(dtclsFileAttach_mobile);
                    index += 1;
                }
                _clsFileAttach_mobileBL.saveData(listDataclsFileAttach_mobile);
            }
        }

        db.close();
    }
}
