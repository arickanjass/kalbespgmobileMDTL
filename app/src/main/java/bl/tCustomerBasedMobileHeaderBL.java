package bl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kalbenutritionals.app.kalbespgmobile.clsMainActivity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import library.salesforce.common.clsHelper;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mconfigData;
import library.salesforce.common.tCustomerBasedMobileDetailData;
import library.salesforce.common.tCustomerBasedMobileDetailProductData;
import library.salesforce.common.tCustomerBasedMobileHeaderData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tCustomerBasedMobileHeaderDA;
import library.salesforce.dal.tUserLoginDA;

public class tCustomerBasedMobileHeaderBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void saveData(tCustomerBasedMobileHeaderData dt) {
        SQLiteDatabase _db = getDb();
        tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(_db);

        String txtSubmissionCode = new tUserLoginBL().getUserActive().get_txtSubmissionID();

        _tCustomerBasedMobileHeaderDA.SaveDatatCustomerBasedMobileHeaderData(_db, dt);
    }

    public void deleteTrId(String intTrCustomerId) {
        SQLiteDatabase _db=getDb();
        new tCustomerBasedMobileHeaderDA(_db).deleteByID(_db, intTrCustomerId);
    }

    public void updateDataSubmit(tCustomerBasedMobileHeaderData dt) {
        SQLiteDatabase _db = getDb();
        tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(_db);
        _tCustomerBasedMobileHeaderDA.updateDataSubmit(_db, dt);
    }

    public tCustomerBasedMobileHeaderData getDataByBitActive() {
        SQLiteDatabase _db = getDb();
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderDA(_db).getDataByBitActive(_db);
        return dt;
    }
    public tCustomerBasedMobileHeaderData getDataById(String idTrCustomer) {
        SQLiteDatabase _db = getDb();
        tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderDA(_db).getData(_db, idTrCustomer);
        return dt;
    }

    public List<tCustomerBasedMobileHeaderData> getAllCustomerBasedMobileHeaderByOutletCode(String code) {
        SQLiteDatabase _db = getDb();
        List<tCustomerBasedMobileHeaderData> dt;
        tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(_db);
        dt = _tCustomerBasedMobileHeaderDA.getAllDataByOutletCode(_db, code);
        if (dt == null) {
            dt = new ArrayList<>(0);
        }
        return dt;
    }

    public List<tCustomerBasedMobileHeaderData> getAllCustomerBasedMobileHeaderByOutletCodeForView(String code) {
        SQLiteDatabase _db = getDb();
        List<tCustomerBasedMobileHeaderData> dt;
        tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(_db);
        dt = _tCustomerBasedMobileHeaderDA.getAllDataByOutletCodeForView(_db, code);
        if (dt == null) {
            dt = new ArrayList<>(0);
        }
        return dt;
    }

    public List<tCustomerBasedMobileHeaderData> getAllCustomerBasedMobileHeaderByOutletCodeUnsubmit(String code) {
        SQLiteDatabase _db = getDb();
        List<tCustomerBasedMobileHeaderData> dt;
        tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(_db);
        dt = _tCustomerBasedMobileHeaderDA.getAllDataByOutletCodeUnsubmit(_db, code);
        if (dt == null) {
            dt = new ArrayList<>(0);
        }
        return dt;
    }

    public List<tCustomerBasedMobileHeaderData> getAllCustomerBasedMobileHeaderByOutletCodeReporting(String code) {
        SQLiteDatabase _db = getDb();
        List<tCustomerBasedMobileHeaderData> dt;
        tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(_db);
        if(code.equals("ALLOUTLET")){
            dt = _tCustomerBasedMobileHeaderDA.getAllDataReporting(_db);
        } else {
            dt = _tCustomerBasedMobileHeaderDA.getAllDataByOutletCode(_db, code);
        }
        if (dt == null) {
            dt = new ArrayList<>(0);
        }
        return dt;
    }

    public Boolean save(Context context) {
        SQLiteDatabase _db = getDb();

        Calendar c = Calendar.getInstance();
        int lYear = c.get(Calendar.YEAR);
        int lMonth = c.get(Calendar.MONTH) + 1;
        int lDay = c.get(Calendar.DATE);

        String dateNow = Integer.valueOf(lYear) + "-" + Integer.valueOf(lMonth) + "-" + Integer.valueOf(lDay);

        Boolean status = false;
        tCustomerBasedMobileHeaderData dtHeader = getDataByBitActive();
        if (dtHeader != null) {
            List<tCustomerBasedMobileDetailData> dtDetail = new tCustomerBasedMobileDetailBL().getAllDataByHeaderId(dtHeader.get_intTrCustomerId());
            if (dtDetail != null) {
                for (tCustomerBasedMobileDetailData dt : dtDetail) {
                    List<tCustomerBasedMobileDetailProductData> dtProduct = new tCustomerBasedMobileDetailProductBL().getDataByCustomerDetailId(dt.get_intTrCustomerIdDetail());
                    if(dt.get_txtTglLahir().equals(dateNow) || dt.get_txtTglLahir() == null || dt.get_txtTglLahir().equals("null") || dt.get_txtTglLahir().equals("")){
                        new clsMainActivity().showCustomToast(context, "Failed to save: \n" + dt.get_txtNamaDepan() + "'s Date of birth has not been set", false);
                        status = false;
                        break;
                    }
                    if (dtProduct == null || dtProduct.size() == 0) {
                        new clsMainActivity().showCustomToast(context, "Failed to save: \n" + dt.get_txtNamaDepan() + "'s product usage has not defined", false);
                        status = false;
                        break;
                    } else {
                        status = true;
                    }
                }
            }
        }

        if (status) {
            dtHeader.set_bitActive("1");
            dtHeader.set_intSubmit("0");
            new tCustomerBasedMobileHeaderDA(_db).SaveDatatCustomerBasedMobileHeaderData(_db, dtHeader);

            List<tCustomerBasedMobileDetailData> dtDetail = new tCustomerBasedMobileDetailBL().getAllDataByHeaderId(dtHeader.get_intTrCustomerId());

            for (tCustomerBasedMobileDetailData dt : dtDetail) {
                dt.set_bitActive("0");
                new tCustomerBasedMobileDetailBL().saveData(dt);

                List<tCustomerBasedMobileDetailProductData> dtProduct = new tCustomerBasedMobileDetailProductBL().getDataByCustomerDetailId(dt.get_intTrCustomerIdDetail());

                for (tCustomerBasedMobileDetailProductData dtP : dtProduct) {
                    dtP.set_bitActive("0");
                    new tCustomerBasedMobileDetailProductBL().saveData(dtP);
                }
            }

            new clsMainActivity().showCustomToast(context, "Saved", true);
        }

        return status;
    }

    public List<tCustomerBasedMobileHeaderData> getAllData() {
        SQLiteDatabase _db = getDb();
        List<tCustomerBasedMobileHeaderData> dt = new tCustomerBasedMobileHeaderDA(_db).getAllData(_db);
        return dt;
    }

    public List<tCustomerBasedMobileHeaderData> getAllDataNonSync() {
        SQLiteDatabase _db = getDb();
        List<tCustomerBasedMobileHeaderData> dt = new tCustomerBasedMobileHeaderDA(_db).getAllDataNonSync(_db);
        return dt;
    }

    public List<tCustomerBasedMobileHeaderData> getAllDataToSubmit() {
        SQLiteDatabase _db = getDb();
        List<tCustomerBasedMobileHeaderData> dt = new tCustomerBasedMobileHeaderDA(_db).getDataToSubmit(_db);
        return dt;
    }

    public int getCountAllCustomerBased() {
        SQLiteDatabase _db = getDb();
        int count = new tCustomerBasedMobileHeaderDA(_db).countCustomerBaseHome(_db);
        return count;
    }

    public int getCountProductAllCustomerBased(String intTrCustomerId, String code) {
        SQLiteDatabase _db = getDb();
        int count = 0;
        if(code.equals("ALLOUTLET")){
            count = new tCustomerBasedMobileHeaderDA(_db).countCustomerBaseReportingAll(_db, intTrCustomerId);
        } else {
            count = new tCustomerBasedMobileHeaderDA(_db).countCustomerBaseReportingOutlet(_db, intTrCustomerId, code);
        }

        return count;
    }

    public int getCountAllCustomerBasedAbsen(String code) {
        SQLiteDatabase _db = getDb();
        int count = new tCustomerBasedMobileHeaderDA(_db).countCustomerBaseHomeAbsen(_db, code);
        return count;
    }

    public int getCountAllCustomerBasedAbsenByStatus(String status, String code) {
        SQLiteDatabase _db = getDb();
        int count = new tCustomerBasedMobileHeaderDA(_db).countCustomerBaseHomeAbsenByStatus(_db, status, code);
        return count;
    }
    public int getCountAllCustomerBasedAbsenUnpush( String code) {
        SQLiteDatabase _db = getDb();
        int count = new tCustomerBasedMobileHeaderDA(_db).countCustomerBaseHomeAbsenUnpush(_db, code);
        return count;
    }
    public int countCustomerBaseHomeAbsenPush( String code) {
        SQLiteDatabase _db = getDb();
        int count = new tCustomerBasedMobileHeaderDA(_db).countCustomerBaseHomeAbsenPush(_db, code);
        return count;
    }
    public int getCountAllCustomerBasedAbsenByStatusSave(String code) {
        SQLiteDatabase _db = getDb();
        int count = new tCustomerBasedMobileHeaderDA(_db).countCustomerBaseHomeAbsenByStatusSave(_db, code);
        return count;
    }

    public int getCountAllCustomerBasedByStatus(String status) {
        SQLiteDatabase _db = getDb();
        int count = new tCustomerBasedMobileHeaderDA(_db).countCustomerBaseHomeByStatus(_db, status);
        return count;
    }

    public List<tCustomerBasedMobileHeaderData> getAllDataByIntSyc(String val) {
        SQLiteDatabase _db = getDb();
        tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(_db);
        List<tCustomerBasedMobileHeaderData> dt = _tCustomerBasedMobileHeaderDA.getAllDataByIntSyc(_db, val);
        if (dt == null) {
            dt = new ArrayList<>(0);
        }
        return dt;
    }

    public List<tCustomerBasedMobileHeaderData> getAllDataByIntSycAndOutlet(String val, String outlet) {
        SQLiteDatabase _db = getDb();
        tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(_db);
        List<tCustomerBasedMobileHeaderData> dt = _tCustomerBasedMobileHeaderDA.getAllDataByIntSycAndOutlet(_db, val, outlet);
        if (dt == null) {
            dt = new ArrayList<>(0);
        }
        return dt;
    }

    public List<tCustomerBasedMobileHeaderData> getLastData() {
        SQLiteDatabase _db = getDb();
        tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(_db);
        List<tCustomerBasedMobileHeaderData> dt = _tCustomerBasedMobileHeaderDA.getLastData(_db);
        return dt;
    }

    public JSONArray DownloadCustomerBase(String versionName) throws Exception {
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);

        String strVal2 = "";
        mconfigData dataAPI = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String datenow = dateFormat.format(date);

        //ambil version dari webservices
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtlinkAPI = new linkAPI();
        String txtMethod = "GetDataCustomerBased";
        JSONObject resJson = new JSONObject();
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId() + "|" + datenow);
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);

        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.pushtData(strLinkAPI, dtlinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));

        //String strLinkAPI= dtlinkAPI.QueryString(strVal2);
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));

        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        _db.close();
        return JsonArray;
    }

    public String generateSubmissionId(String noCustomerBase) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

//		mCounterNumberDA _mCountNumberDA = new mCounterNumberDA(db);
//		mCounterNumberData _ListOfmCounterNumberData = _mCountNumberDA.getData(db, 2);
        String dtDate = dateFormat.format(cal.getTime());
        String[] split = dtDate.split("-");
        String yy = split[0];
        String mm = split[1];
        String dd = split[2];

        String txtSubmissionCode = new tUserLoginBL().getUserActive().get_txtSubmissionID();

        String txtSubmissionId = null;

        txtSubmissionId = txtSubmissionCode;
        //+ "." + dd + mm + yy.substring(2) + "." + noCustomerBase;

        return txtSubmissionId;
    }
}
