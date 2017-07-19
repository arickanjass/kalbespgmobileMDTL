package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import library.spgmobile.common.APIData;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.linkAPI;
import library.spgmobile.common.mconfigData;
import library.spgmobile.common.tPurchaseOrderDetailData;
import library.spgmobile.common.tPurchaseOrderHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.mconfigDA;
import library.spgmobile.dal.tPurchaseOrderDetailDA;
import library.spgmobile.dal.tPurchaseOrderHeaderDA;
import library.spgmobile.dal.tUserLoginDA;
import library.spgmobile.dal.enumConfigData;

/**
 * Created by XSIS on 21/03/2017.
 */

public class tPurchaseOrderDetailBL extends clsMainBL {
    public List<tPurchaseOrderDetailData> getDataByNoPO(String NoPO){
        SQLiteDatabase db = getDb();
        tPurchaseOrderDetailDA _tPurchaseOrderDetailDA = new tPurchaseOrderDetailDA(db);
        List<tPurchaseOrderDetailData> ListData = _tPurchaseOrderDetailDA.getDataByNoOrder(db,NoPO);
        db.close();
        return ListData;

    }

    public void DownloadEmployeeTransaction(String versionName) throws Exception{
        SQLiteDatabase _db = getDb();
        tPurchaseOrderDetailDA _tPurchaseOrderDetailDa = new tPurchaseOrderDetailDA(_db);
        tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(_db);
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        String strVal2 = "";
        mconfigData dataAPI = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == ""){
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        clsHelper _help =new clsHelper();
        linkAPI dtlinkAPI=new linkAPI();
        String txtMethod="GetDataTransactionPO";
        dtlinkAPI.set_txtMethod(txtMethod);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        dtlinkAPI.set_txtParam("|"+_dataUserLogin.get_TxtEmpId()+"|"+dateFormat.format(calendar.getTime()));
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.ResultJsonData(_help.getHTML(strLinkAPI));
        JSONArray jsonArray = _help.ResultJsonArray(JsonData);
        APIData dtAPIDATA = new APIData();
        Iterator i = jsonArray.iterator();
        Boolean flag = true;
        String ErrorMessage = " ";
        Long intData = _tPurchaseOrderDetailDa.getContactsCountPO(db);
        while (i.hasNext()){
            JSONObject innerDetailObj = (JSONObject)i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerDetailObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
                JSONArray JsonArrayDetail = _help.ResultJsonArray(String.valueOf(innerDetailObj.get("ListDatatPurchaseOrder_mobile")));
                JSONArray JsonArrayHeader = _help.ResultJsonArray(String.valueOf(innerDetailObj.get("ListDatatPurchaseOrder_mobile")));
                Iterator iDetail = JsonArrayDetail.iterator();
                Iterator iHeader = JsonArrayHeader.iterator();
                while (iHeader.hasNext()){
                    JSONObject InnerDetailObj = (JSONObject)iHeader.next();
                    _tPurchaseOrderHeaderDA.deleteContactPO(db, String.valueOf(innerDetailObj.get("TxtNoOrder")));
                    _tPurchaseOrderDetailDa.deleteByNoOrder(db, String.valueOf(innerDetailObj.get("TxtNoOrder")));
                    tPurchaseOrderHeaderData _data = new tPurchaseOrderHeaderData();
                    _data.set_dtDate(dateFormat.format(calendar.getTime()));
                    _data.set_intId(String.valueOf(innerDetailObj.get("TxtNoOrder")));
                    _data.set_intIdAbsenUser(String.valueOf(_dataUserLogin.get_intId()));
                    _data.set_intSubmit("1");
                    _data.set_intSumAmount(String.valueOf(innerDetailObj.get("IntSumAmount")));
                    _data.set_intSumItem(String.valueOf(innerDetailObj.get("IntSumItem")));
                    _data.set_intSync("1");
                    _data.set_OutletCode(String.valueOf(innerDetailObj.get("TxtOutletCode")));
                    _data.set_OutletName(String.valueOf(innerDetailObj.get("TxtOutletName")));
                    _data.set_txtBranchCode(String.valueOf(innerDetailObj.get("TxtBranchCode")));
                    _data.set_txtBranchName(String.valueOf(innerDetailObj.get("TxtBranchName")));
                    _data.set_txtKeterangan(String.valueOf(innerDetailObj.get("TxtKeterangan")));
                    _data.set_txtNIK((String) _dataUserLogin.get_TxtEmpId());
                    _data.set_UserId((String) _dataUserLogin.get_txtUserId());
                    _tPurchaseOrderHeaderDA.SaveDatatPurchaseOrderHeaderData(db, _data);
                }
                while (iDetail.hasNext()){
                    intData +=1;
                    JSONObject innerDetailObject = (JSONObject)iDetail.next();
                    tPurchaseOrderDetailData _data = new tPurchaseOrderDetailData();
                    String intIdDetail = String.valueOf(intData);
                    _data.set_intId(String.valueOf(intIdDetail));
                    _data.set_dtDate(dateFormat.format(calendar.getTime()));
                    _data.set_intPrice((String) innerDetailObject.get("IntPrice"));
                    _data.set_intQty((String)innerDetailObject.get("IntQty"));
                    _data.set_txtCodeProduct((String) innerDetailObject.get("TxtCodeProduct"));
                    _data.set_txtKeterangan("");
                    _data.set_txtNameProduct((String) innerDetailObject.get("TxtNameProduct"));
                    _data.set_txtNoOrder((String) innerDetailObject.get("TxtNoOrder"));
                    _data.set_intActive("1");
                    _data.set_txtNIK(_dataUserLogin.get_TxtEmpId());
                    _data.set_intTotal((String) innerDetailObject.get("IntTotal"));
                    _tPurchaseOrderDetailDa.SaveDatatPurchaseOrderDetailData(db, _data);
                }
            }else {
                flag = false;
                ErrorMessage = (String) innerDetailObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
    }



}
