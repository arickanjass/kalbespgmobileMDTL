package library.spgmobile.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class dataJson {

    public synchronized List<tEmployeeBRWithLOBData> getListOftEmployeeBRWithLOBData() {
        return ListOftEmployeeBRWithLOBData;
    }

    public synchronized void setListOftEmployeeBRWithLOBData(
            List<tEmployeeBRWithLOBData> listOftEmployeeBRWithLOBData) {
        ListOftEmployeeBRWithLOBData = listOftEmployeeBRWithLOBData;
    }

    public synchronized List<tLeaveMobileData> getListOftLeaveMobileData() {
        return ListOftLeaveMobileData;
    }

    public synchronized void setListOftLeaveMobileData(
            List<tLeaveMobileData> listOftLeaveMobileData) {
        ListOftLeaveMobileData = listOftLeaveMobileData;
    }

    public synchronized List<mTypeLeaveMobileData> getListOfmTypeLeaveMobileData() {
        return ListOfmTypeLeaveMobileData;
    }

    public synchronized void setListOfmTypeLeaveMobileData(
            List<mTypeLeaveMobileData> listOfmTypeLeaveMobileData) {
        ListOfmTypeLeaveMobileData = listOfmTypeLeaveMobileData;
    }

    public synchronized List<mProductBrandHeaderData> getListOfmProductBrandHeaderData() {
        return ListOfmProductBrandHeaderData;
    }

    public synchronized void setListOfmProductBrandHeaderData(
            List<mProductBrandHeaderData> listOfmProductBrandHeaderData) {
        ListOfmProductBrandHeaderData = listOfmProductBrandHeaderData;
    }

    public synchronized List<tActivityData> getListOftActivityData() {
        return ListOftActivityData;
    }

    public synchronized void setListOftActivityData(
            List<tActivityData> listOftActivityData) {
        ListOftActivityData = listOftActivityData;
    }

    public synchronized List<tActivityMobileData> getListOftActivityMobileData() {
        return ListOftActivityMobileData;
    }

    public synchronized void setListOftActivityMobileData(
            List<tActivityMobileData> listOftActivityMobileData) {
        ListOftActivityMobileData = listOftActivityMobileData;
    }

    public synchronized List<mGeolocationOutletSPGData> getListOfmGeolocationOutletSPGData() {
        return ListOfmGeolocationOutletSPGData;
    }

    public synchronized void setListOfmGeolocationOutletSPGData(
            List<mGeolocationOutletSPGData> listOfmGeolocationOutletSPGData) {
        ListOfmGeolocationOutletSPGData = listOfmGeolocationOutletSPGData;
    }

    public synchronized List<tAbsenUserData> getListOftAbsenUserData() {
        return ListOftAbsenUserData;
    }

    public synchronized void setListOftAbsenUserData(
            List<tAbsenUserData> listOftAbsenUserData) {
        ListOftAbsenUserData = listOftAbsenUserData;
    }

    public synchronized List<mEmployeeAreaData> getListOfmEmployeeAreaData() {
        return ListOfmEmployeeAreaData;
    }

    public synchronized void setListOfmEmployeeAreaData(
            List<mEmployeeAreaData> listOfmEmployeeAreaData) {
        ListOfmEmployeeAreaData = listOfmEmployeeAreaData;
    }

    public synchronized List<mEmployeeBranchData> getListOfmEmployeeBranchData() {
        return ListOfmEmployeeBranchData;
    }

    public synchronized void setListOfmEmployeeBranchData(
            List<mEmployeeBranchData> listOfmEmployeeBranchData) {
        ListOfmEmployeeBranchData = listOfmEmployeeBranchData;
    }

    public synchronized List<mEmployeeSalesProductData> getListOfmEmployeeSalesProductData() {
        return ListOfmEmployeeSalesProductData;
    }

    public synchronized void setListOfmEmployeeSalesProductData(
            List<mEmployeeSalesProductData> listOfmEmployeeSalesProductData) {
        ListOfmEmployeeSalesProductData = listOfmEmployeeSalesProductData;
    }

    public synchronized List<mNotificationData> getListOfmNotificationData() {
        return ListOfmNotificationData;
    }

    public synchronized void setListOfmNotificationData(
            List<mNotificationData> listOfmNotificationData) {
        ListOfmNotificationData = listOfmNotificationData;
    }

    public synchronized List<tSalesProductDetailData> getListOftSalesProductDetailData() {
        return ListOftSalesProductDetailData;
    }

    public synchronized void setListOftSalesProductDetailData(
            List<tSalesProductDetailData> listOftSalesProductDetailData) {
        ListOftSalesProductDetailData = listOftSalesProductDetailData;
    }

    public synchronized List<tSalesProductHeaderData> getListOftSalesProductHeaderData() {
        return ListOftSalesProductHeaderData;
    }

    public synchronized void setListOftSalesProductHeaderData(
            List<tSalesProductHeaderData> listOftSalesProductHeaderData) {
        ListOftSalesProductHeaderData = listOftSalesProductHeaderData;
    }

    public synchronized List<tPurchaseOrderDetailData> getListOftPurchaseOrderDetailData(){
        return ListOftPurchaseOrderDetailData;
    }
    public synchronized void setListOftPurchaseOrderDetailData(
            List<tPurchaseOrderDetailData> listOftPurchaseOrderDetailData){
        ListOftPurchaseOrderDetailData = listOftPurchaseOrderDetailData;
    }

    public synchronized List<tSalesProductQuantityDetailData> getListOftSalesProductQuantityDetailData() {
        return ListOftSalesProductQuantityDetailData;
    }

    public synchronized void setListOftSalesProductQuantityDetailData(List<tSalesProductQuantityDetailData> listOftSalesProductQuantityDetailData) {
        ListOftSalesProductQuantityDetailData = listOftSalesProductQuantityDetailData;
    }

    public synchronized List<tSalesProductQuantityImageData> getListOftSalesProductQuantityImageData() {
        return ListOftSalesProductQuantityImageData;
    }

    public synchronized void setListOftSalesProductQuantityImageData(List<tSalesProductQuantityImageData> listOftSalesProductQuantityImageData) {
        ListOftSalesProductQuantityImageData = listOftSalesProductQuantityImageData;
    }

    public synchronized List<tPurchaseOrderHeaderData> getListOftPurchaseOrderHeaderData() {
        return ListOftPurchaseOrderHeaderData;
    }

    public synchronized void setListOftPurchaseOrderHeaderData(List<tPurchaseOrderHeaderData> listOftPurchaseOrderHeaderData) {
        ListOftPurchaseOrderHeaderData = listOftPurchaseOrderHeaderData;
    }

    public synchronized List<tJawabanUserData> getListOftJawabanUserData(){
        return ListOftJawabanUserData;
    }

    public synchronized void setListOftJawabanUserData(List<tJawabanUserData> listOftJawabanUserData){
        ListOftJawabanUserData = listOftJawabanUserData;
    }
    public synchronized List<tSalesProductQuantityHeaderData> getListOftSalesProductQuantityHeaderData() {
        return ListOftSalesProductQuantityData;
    }

    public synchronized void setListOftSalesProductQuantityData(List<tSalesProductQuantityHeaderData> listOftSalesProductQuantityData) {
        ListOftSalesProductQuantityData = listOftSalesProductQuantityData;
    }

    public synchronized List<trackingLocationData> getListOfTrackingLocationData() {
        return ListOfTrackingLocationData;
    }

    public synchronized void setListOfTrackingLocationData(List<trackingLocationData> listOfTrackingLocationData) {
        ListOfTrackingLocationData = listOfTrackingLocationData;
    }

    public synchronized List<KoordinasiOutletData> getListOfKoordinasiOutletData() {
        return ListOfKoordinasiOutletData;
    }

    public synchronized void setListOfKoordinasiOutletData(List<KoordinasiOutletData> listOfKoordinasiOutletData) {
        ListOfKoordinasiOutletData = listOfKoordinasiOutletData;
    }

    public synchronized List<KoordinasiOutletImageData> getListOfKoordinasiOutletImageData() {
        return ListOfKoordinasiOutletImageData;
    }

    public synchronized void setListOfKoordinasiOutletImageData(List<KoordinasiOutletImageData> listOfKoordinasiOutletImageData) {
        ListOfKoordinasiOutletImageData = listOfKoordinasiOutletImageData;
    }

    public synchronized List<tDeviceInfoUserData> getListDatatDeviceInfoUser() {
        return ListDatatDeviceInfoUser;
    }

    public synchronized void setListDatatDeviceInfoUser(
            List<tDeviceInfoUserData> listDatatDeviceInfoUser) {
        ListDatatDeviceInfoUser = listDatatDeviceInfoUser;
    }

    public synchronized List<mMenuData> getListOfmMenuData() {
        return ListOfmMenuData;
    }

    public synchronized void setListOfmMenuData(List<mMenuData> listOfmMenuData) {
        ListOfmMenuData = listOfmMenuData;
    }

    public synchronized String getTxtValue() {
        return txtValue;
    }

    public synchronized void setTxtValue(String txtValue) {
        this.txtValue = txtValue;
    }

    public String getTxtMethod() {
        return txtMethod;
    }

    public void setTxtMethod(String txtMethod) {
        this.txtMethod = txtMethod;
    }

    public List<tUserLoginData> getListDatatUserLogin() {
        return ListDatatUserLogin;
    }

    public void setListDatatUserLogin(List<tUserLoginData> listDatatUserLogin) {
        ListDatatUserLogin = listDatatUserLogin;
    }

    public JSONObject txtJSON() throws JSONException {
        JSONObject resJson = new JSONObject();
        Collection<JSONObject> itemsListJquey = new ArrayList<JSONObject>();
        try {
            if(this.getListOftErrorLogData() != null){
                tLogErrorData _tLogErrorData = new tLogErrorData();
                for (tLogErrorData data : this.getListOftErrorLogData()){
                    JSONObject item1 = new JSONObject();
                    item1.put(_tLogErrorData.Property_intLogId, String.valueOf(data.get_intLogId()));
                    item1.put(_tLogErrorData.Property_txtUserId, String.valueOf(data.get_TxtUserId()));
                    item1.put(_tLogErrorData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    item1.put(_tLogErrorData.Property_txtRoleName, String.valueOf(data.get_txtRoleName()));
                    item1.put(_tLogErrorData.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(_tLogErrorData.Property_txtFileName, String.valueOf(data.get_txtFileName()));
                    itemsListJquey.add(item1);
                }
                resJson.put(_tLogErrorData.Property_ListLogError, new JSONArray(itemsListJquey));
            }
            if (this.getListDatamConfig() != null) {
                mconfigData dtConfig = new mconfigData();
                for (mconfigData data : this.getListDatamConfig()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_intEditAdmin, String.valueOf(data.get_intEditAdmin()));
                    item1.put(dtConfig.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dtConfig.Property_txtName, String.valueOf(data.get_txtName()));
                    item1.put(dtConfig.Property_txtValue, String.valueOf(data.get_txtValue()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtConfig.Property_ListOfMconfig, new JSONArray(itemsListJquey));
            }

            if (this.get_ListOfmProductBarcodeData() != null) {
                mProductBarcodeData dt = new mProductBarcodeData();
                for (mProductBarcodeData data : this.get_ListOfmProductBarcodeData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(data.Property_intProductCode, String.valueOf(data.get_intProductCode()));
                    item1.put(data.Property_txtBarcode, String.valueOf(data.get_txtBarcode()));
                    item1.put(data.Property_txtProductCode, String.valueOf(data.get_txtProductCode()));
                    item1.put(data.Property_txtProductName, String.valueOf(data.get_txtProductName()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dt.Property_ListOfmProductBarcodeData, new JSONArray(itemsListJquey));
            }

            if (this.getListDatatUserLogin() != null) {
                tUserLoginData dttUserLoginData = new tUserLoginData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tUserLoginData data : this.getListDatatUserLogin()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttUserLoginData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttUserLoginData.Property_txtPassword, String.valueOf(data.get_txtPassword()));
                    item1.put(dttUserLoginData.Property_txtPathImage, String.valueOf(data.get_txtPathImage()));
                    item1.put(dttUserLoginData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    item1.put(dttUserLoginData.Property_txtRoleName, String.valueOf(data.get_txtRoleName()));
                    item1.put(dttUserLoginData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item1.put(dttUserLoginData.Property_txtDataId, String.valueOf(data.get_txtDataId()));
                    item1.put(dttUserLoginData.Property_DtCheckIn, String.valueOf(data.get_dtCheckIn()));
                    item1.put(dttUserLoginData.Property_DtCheckOut, String.valueOf(data.get_dtCheckOut()));
                    item1.put(dttUserLoginData.Property_DtLastLogin, String.valueOf(data.get_dtLastLogin()));
                    item1.put(dttUserLoginData.Property_DtLogOut, String.valueOf(data.get_dtLogOut()));
                    item1.put(dttUserLoginData.Property_TxtCab, String.valueOf(data.get_txtCab()));
                    item1.put(dttUserLoginData.Property_TxtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(dttUserLoginData.Property_TxtEmail, String.valueOf(data.get_TxtEmail()));
                    item1.put(dttUserLoginData.Property_TxtEmpId, String.valueOf(data.get_TxtEmpId()));
                    item1.put(dttUserLoginData.Property_txtName, String.valueOf(data.get_txtName()));
                    item1.put(dttUserLoginData.Property_txtPassword, String.valueOf(data.get_txtPassword()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttUserLoginData.Property_ListOftUserLoginData, new JSONArray(itemsListJquey));
            }

            if (this.getListDatatDeviceInfoUser() != null) {
                tDeviceInfoUserData dttDeviceInfoUserData = new tDeviceInfoUserData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tDeviceInfoUserData data : this.getListDatatDeviceInfoUser()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttDeviceInfoUserData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttDeviceInfoUserData.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(dttDeviceInfoUserData.Property_txtDevice, String.valueOf(data.get_txtDevice()));
                    item1.put(dttDeviceInfoUserData.Property_txtModel, String.valueOf(data.get_txtModel()));
                    item1.put(dttDeviceInfoUserData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item1.put(dttDeviceInfoUserData.Property_txtImei, String.valueOf(data.get_txtImei()));
                    item1.put(dttDeviceInfoUserData.Property_txtVersion, String.valueOf(data.get_txtVersion()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttDeviceInfoUserData.Property_ListOftDeviceInfoUserData, new JSONArray(itemsListJquey));
            }

            if (this.getListOfmMenuData() != null) {
                mMenuData dtmMenuData = new mMenuData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (mMenuData data : this.getListOfmMenuData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dtmMenuData.Property_intId, Long.valueOf(data.get_intId()));
                    item1.put(dtmMenuData.Property_IntOrder, Long.valueOf(data.get_IntOrder()));
                    item1.put(dtmMenuData.Property_IntParentID, Long.valueOf(data.get_IntParentID()));
                    item1.put(dtmMenuData.Property_TxtDescription, String.valueOf(data.get_TxtDescription()));
                    item1.put(dtmMenuData.Property_TxtLink, String.valueOf(data.get_TxtLink()));
                    item1.put(dtmMenuData.Property_TxtMenuName, String.valueOf(data.get_TxtMenuName()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtmMenuData.Property_ListOfMMenuData, new JSONArray(itemsListJquey));
            }
            if (this.getListOftStockInHandHeaderData() != null) {
                tStockInHandHeaderData dttStockInHandHeaderData = new tStockInHandHeaderData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tStockInHandHeaderData data : this.getListOftStockInHandHeaderData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttStockInHandHeaderData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttStockInHandHeaderData.Property_txtNoSo, String.valueOf(data.get_txtNoSo()));
                    item1.put(dttStockInHandHeaderData.Property_OutletCode, String.valueOf(data.get_OutletCode()));
                    item1.put(dttStockInHandHeaderData.Property_OutletName, String.valueOf(data.get_OutletName()));
                    item1.put(dttStockInHandHeaderData.Property_txtDate, String.valueOf(data.get_dtDate()));
                    item1.put(dttStockInHandHeaderData.Property_intIdAbsenUser, String.valueOf(data.get_intIdAbsenUser()));
                    item1.put(dttStockInHandHeaderData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dttStockInHandHeaderData.Property_intSumAmount, String.valueOf(data.get_intSumAmount()));
                    item1.put(dttStockInHandHeaderData.Property_intSumItem, String.valueOf(data.get_intSumItem()));
                    item1.put(dttStockInHandHeaderData.Property_intSync, String.valueOf(data.get_intSync()));
                    item1.put(dttStockInHandHeaderData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dttStockInHandHeaderData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item1.put(dttStockInHandHeaderData.Property_UserId, String.valueOf(data.get_UserId()));
                    item1.put(dttStockInHandHeaderData.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
                    item1.put(dttStockInHandHeaderData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item1.put(dttStockInHandHeaderData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttStockInHandHeaderData.Property_ListOftSalesProductHeaderData, new JSONArray(itemsListJquey));
            }
            if (this.getListOftStockInHandDetailData() != null) {
                tStockInHandDetailData dttStockInHandDetailData = new tStockInHandDetailData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tStockInHandDetailData data : this.getListOftStockInHandDetailData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttStockInHandDetailData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttStockInHandDetailData.Property_dtDate, String.valueOf(data.get_dtDate()));
                    item1.put(dttStockInHandDetailData.Property_intPrice, String.valueOf(data.get_intPrice()));
                    item1.put(dttStockInHandDetailData.Property_intQty, String.valueOf(data.get_intQty()));
                    item1.put(dttStockInHandDetailData.Property_txtCodeProduct, String.valueOf(data.get_txtCodeProduct()));
                    item1.put(dttStockInHandDetailData.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
                    item1.put(dttStockInHandDetailData.Property_txtNameProduct, String.valueOf(data.get_txtNameProduct()));
                    item1.put(dttStockInHandDetailData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item1.put(dttStockInHandDetailData.Property_intTotal, String.valueOf(data.get_intTotal()));
                    item1.put(dttStockInHandDetailData.Property_txtNoSo, String.valueOf(data.get_txtNoSo()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttStockInHandDetailData.Property_ListOftSalesProductDetailData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftSalesProductDetailData() != null) {
                tSalesProductDetailData dttSalesProductDetailData = new tSalesProductDetailData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tSalesProductDetailData data : this.getListOftSalesProductDetailData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttSalesProductDetailData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttSalesProductDetailData.Property_dtDate, String.valueOf(data.get_dtDate()));
                    item1.put(dttSalesProductDetailData.Property_intPrice, String.valueOf(data.get_intPrice()));
                    item1.put(dttSalesProductDetailData.Property_intQty, String.valueOf(data.get_intQty()));
                    item1.put(dttSalesProductDetailData.Property_txtCodeProduct, String.valueOf(data.get_txtCodeProduct()));
                    item1.put(dttSalesProductDetailData.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
                    item1.put(dttSalesProductDetailData.Property_txtNameProduct, String.valueOf(data.get_txtNameProduct()));
                    item1.put(dttSalesProductDetailData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item1.put(dttSalesProductDetailData.Property_intTotal, String.valueOf(data.get_intTotal()));
                    item1.put(dttSalesProductDetailData.Property_txtNoSo, String.valueOf(data.get_txtNoSo()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttSalesProductDetailData.Property_ListOftSalesProductDetailData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftPurchaseOrderDetailData() != null){
                tPurchaseOrderDetailData dttPurchaseOrderDetailData = new tPurchaseOrderDetailData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tPurchaseOrderDetailData data : this.getListOftPurchaseOrderDetailData()){
                    JSONObject item = new JSONObject();
                    item.put(dttPurchaseOrderDetailData.Property_intId, String.valueOf(data.get_intId()));
                    item.put(dttPurchaseOrderDetailData.Property_dtDate, String.valueOf(data.get_dtDate()));
                    item.put(dttPurchaseOrderDetailData.Property_intPrice, String.valueOf(data.get_intPrice()));
                    item.put(dttPurchaseOrderDetailData.Property_intQty, String.valueOf(data.get_intQty()));
                    item.put(dttPurchaseOrderDetailData.Property_txtCodeProduct, String.valueOf(data.get_txtCodeProduct()));
                    item.put(dttPurchaseOrderDetailData.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
                    item.put(dttPurchaseOrderDetailData.Property_txtNameProduct, String.valueOf(data.get_txtNameProduct()));
                    item.put(dttPurchaseOrderDetailData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item.put(dttPurchaseOrderDetailData.Property_intTotal, String.valueOf(data.get_intTotal()));
                    item.put(dttPurchaseOrderDetailData.Property_txtNoOrder, String.valueOf(data.get_txtNoOrder()));
                    item.put(dttPurchaseOrderDetailData.Property_intActive, String.valueOf(data.get_intActive()));
                    itemsListJquey.add(item);
                }
                resJson.put(dttPurchaseOrderDetailData.Property_ListOftPurchaseOrderDetailData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftVisitPlanHeader_MobileData() != null){
                tVisitPlanHeader_MobileData dttVisitPlanHeader_MobileData = new tVisitPlanHeader_MobileData();
                itemsListJquey = new ArrayList<JSONObject>();
                for(tVisitPlanHeader_MobileData data : this.getListOftVisitPlanHeader_MobileData()){
                    JSONObject item = new JSONObject();
                    item.put(dttVisitPlanHeader_MobileData.Property_intHeaderId, String.valueOf(data.get_intHeaderId()));
                    item.put(dttVisitPlanHeader_MobileData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item.put(dttVisitPlanHeader_MobileData.Property_txtPeriode, String.valueOf(data.get_txtPeriode()));
                    item.put(dttVisitPlanHeader_MobileData.Property_intUnplan, String.valueOf(data.get_intUnplan()));
                    item.put(dttVisitPlanHeader_MobileData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item.put(dttVisitPlanHeader_MobileData.Property_bitActive, String.valueOf(data.get_bitActive()));
                    item.put(dttVisitPlanHeader_MobileData.Property_dtStart, String.valueOf(data.get_dtStart()));
                    item.put(dttVisitPlanHeader_MobileData.Property_dtEnd, String.valueOf(data.get_dtEnd()));
                    item.put(dttVisitPlanHeader_MobileData.Property_intSumBobot, String.valueOf(data.get_intSumBobot()));
//                    item.put(dttVisitPlanHeader_MobileData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    itemsListJquey.add(item);
                }
                resJson.put(dttVisitPlanHeader_MobileData.Property_ListOftVisitPlanHeader_MobileData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftVisitPlanRealisasiData() != null){
                List<tVisitPlanRealisasiData> list = getListOftVisitPlanRealisasiData();
                /*for (int i = 0; i<= list.size(); i++){
                    list.get(i).get_intSubmit();
                }*/

                tVisitPlanRealisasiData dttVisitPlanRealisasiData = new tVisitPlanRealisasiData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tVisitPlanRealisasiData data : this.getListOftVisitPlanRealisasiData()){
                    JSONObject item = new JSONObject();
                    item.put(dttVisitPlanRealisasiData.Property_txtDataIDRealisasi, String.valueOf(data.get_txtDataIDRealisasi()));
                    item.put(dttVisitPlanRealisasiData.Property_intCategoryVisitPlan, String.valueOf(data.get_intCategoryVisitPlan()));
                    item.put(dttVisitPlanRealisasiData.Property_intDetailID, String.valueOf(data.get_intDetailID()));
                    item.put(dttVisitPlanRealisasiData.Property_intHeaderID, String.valueOf(data.get_intHeaderID()));
                    item.put(dttVisitPlanRealisasiData.Property_intUserID, String.valueOf(data.get_intUserID()));
                    item.put(dttVisitPlanRealisasiData.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
                    item.put(dttVisitPlanRealisasiData.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
                    item.put(dttVisitPlanRealisasiData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item.put(dttVisitPlanRealisasiData.Property_dtDate, String.valueOf(data.get_dtDate()));
                    item.put(dttVisitPlanRealisasiData.Property_intBobot, String.valueOf(data.get_intBobot()));
                    item.put(dttVisitPlanRealisasiData.Property_dtDateRealisasi, String.valueOf(data.get_dtDateRealisasi()));
                    item.put(dttVisitPlanRealisasiData.Property_dtDateRealisasiDevice, String.valueOf(data.get_dtDateRealisasiDevice()));
                    item.put(dttVisitPlanRealisasiData.Property_txtDesc, String.valueOf(data.get_txtDesc()));
                    item.put(dttVisitPlanRealisasiData.Property_txtDescReply, String.valueOf(data.get_txtDescReply()));
                    item.put(dttVisitPlanRealisasiData.Property_dtPhoto1, String.valueOf(data.get_dtPhoto1()));
                    item.put(dttVisitPlanRealisasiData.Property_dtPhoto2, String.valueOf(data.get_dtPhoto2()));
                    item.put(dttVisitPlanRealisasiData.Property_txtLong, String.valueOf(data.get_txtLong()));
                    item.put(dttVisitPlanRealisasiData.Property_txtLat, String.valueOf(data.get_txtLat()));
                    item.put(dttVisitPlanRealisasiData.Property_txtAcc, String.valueOf(data.get_txtAcc()));
                    item.put(dttVisitPlanRealisasiData.Property_txtLongSource, String.valueOf(data.get_txtLongSource()));
                    item.put(dttVisitPlanRealisasiData.Property_txtLatSource, String.valueOf(data.get_txtLatSource()));
                    item.put(dttVisitPlanRealisasiData.Property_intDistance, String.valueOf(data.get_intDistance()));
                    item.put(dttVisitPlanRealisasiData.Property_bitActive, String.valueOf(data.get_bitActive()));
                    item.put(dttVisitPlanRealisasiData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    item.put(dttVisitPlanRealisasiData.Property_deviceId, String.valueOf(data.get_deviceId()));
                    item.put(dttVisitPlanRealisasiData.Property_dateCheckOut, String.valueOf(data.get_dateCheckout()));
                    itemsListJquey.add(item);
                }
                resJson.put(dttVisitPlanRealisasiData.Property_listOftVisitPlanRealisasiData, new JSONArray(itemsListJquey));
            }


            if (this.getListOftSalesProductQuantityDetailData() != null){
                tSalesProductQuantityDetailData dttSalesProductQuantityDetailData = new tSalesProductQuantityDetailData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tSalesProductQuantityDetailData data : this.getListOftSalesProductQuantityDetailData()){
                    JSONObject item = new JSONObject();
                    item.put(dttSalesProductQuantityDetailData.Property_intId, String.valueOf(data.getIntId()));
                    item.put(dttSalesProductQuantityDetailData.Property_dtDate, String.valueOf(data.get_dtDate()));
                    item.put(dttSalesProductQuantityDetailData.Property_intPrice, String.valueOf(data.get_intTotal()));
                    item.put(dttSalesProductQuantityDetailData.Property_txtQuantity, String.valueOf(data.getTxtExpireDate()));
                    item.put(dttSalesProductQuantityDetailData.Property_txtCodeProduct, String.valueOf(data.get_intPrice()));
                    item.put(dttSalesProductQuantityDetailData.Property_txtKeterangan, String.valueOf(data.get_txtCodeProduct()));
                    item.put(dttSalesProductQuantityDetailData.Property_txtExpireDate, String.valueOf(data.getTxtProduct()));
                    item.put(dttSalesProductQuantityDetailData.Property_txtProduct, String.valueOf(data.get_txtKeterangan()));
                    item.put(dttSalesProductQuantityDetailData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item.put(dttSalesProductQuantityDetailData.Property_intTotal, String.valueOf(data.get_txtQuantityStock()));
                    item.put(dttSalesProductQuantityDetailData.Property_txtQuantityStock, String.valueOf(data.getTxtQuantity()));
                    itemsListJquey.add(item);
                }
                resJson.put(dttSalesProductQuantityDetailData.Property_ListOftSalesProductQuantityDetailData, new JSONArray(itemsListJquey));
            }

//            if (this.getListOftSalesProductQuantityImageData() != null) {
//                tSalesProductQuantityImageData dttSalesProductQuantityImageData = new tSalesProductQuantityImageData();
//                itemsListJquey = new ArrayList<JSONObject>();
//                for (tSalesProductQuantityImageData data : this.getListOftSalesProductQuantityImageData()) {
//                    JSONObject item = new JSONObject();
//                    item.put(dttSalesProductQuantityImageData.Property_txtId, String.valueOf(data.get_txtId()));
//                    item.put(dttSalesProductQuantityImageData.Property_txtHeaderId, String.valueOf(data.get_txtHeaderId()));
//                    item.put(dttSalesProductQuantityImageData.Property_txtImage, String.valueOf(data.get_txtImage()));
//                    item.put(dttSalesProductQuantityImageData.Property_intPosition, String.valueOf(data.get_intPosition()));
//                    item.put(dttSalesProductQuantityImageData.Property_txtType, String.valueOf(data.get_txtType()));
//                    itemsListJquey.add(item);
//                }
//                resJson.put(dttSalesProductQuantityImageData.Property_ListOftSalesProductQuantityImageData, new JSONArray(itemsListJquey));
//            }

            if (this.getListOftSalesProductHeaderData() != null) {
                tSalesProductHeaderData dttSalesProductHeaderData = new tSalesProductHeaderData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tSalesProductHeaderData data : this.getListOftSalesProductHeaderData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttSalesProductHeaderData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttSalesProductHeaderData.Property_txtNoSo, String.valueOf(data.get_txtNoSo()));
                    item1.put(dttSalesProductHeaderData.Property_OutletCode, String.valueOf(data.get_OutletCode()));
                    item1.put(dttSalesProductHeaderData.Property_OutletName, String.valueOf(data.get_OutletName()));
                    item1.put(dttSalesProductHeaderData.Property_txtDate, String.valueOf(data.get_dtDate()));
                    item1.put(dttSalesProductHeaderData.Property_intIdAbsenUser, String.valueOf(data.get_intIdAbsenUser()));
                    item1.put(dttSalesProductHeaderData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dttSalesProductHeaderData.Property_intSumAmount, String.valueOf(data.get_intSumAmount()));
                    item1.put(dttSalesProductHeaderData.Property_intSumItem, String.valueOf(data.get_intSumItem()));
                    item1.put(dttSalesProductHeaderData.Property_intSync, String.valueOf(data.get_intSync()));
                    item1.put(dttSalesProductHeaderData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dttSalesProductHeaderData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item1.put(dttSalesProductHeaderData.Property_UserId, String.valueOf(data.get_UserId()));
                    item1.put(dttSalesProductHeaderData.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
                    item1.put(dttSalesProductHeaderData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item1.put(dttSalesProductHeaderData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttSalesProductHeaderData.Property_ListOftSalesProductHeaderData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftPurchaseOrderHeaderData() != null){
                tPurchaseOrderHeaderData dttPurchaseOrderHeaderData = new tPurchaseOrderHeaderData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tPurchaseOrderHeaderData data : this.getListOftPurchaseOrderHeaderData()){
                    JSONObject item1 = new JSONObject();
                    item1.put(dttPurchaseOrderHeaderData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttPurchaseOrderHeaderData.Property_txtNoOrder, String.valueOf(data.get_txtNoOrder()));
                    item1.put(dttPurchaseOrderHeaderData.Property_OutletCode, String.valueOf(data.get_OutletCode()));
                    item1.put(dttPurchaseOrderHeaderData.Property_OutletName, String.valueOf(data.get_OutletName()));
                    item1.put(dttPurchaseOrderHeaderData.Property_txtDate, String.valueOf(data.get_dtDate()));
                    item1.put(dttPurchaseOrderHeaderData.Property_intIdAbsenUser, String.valueOf(data.get_intIdAbsenUser()));
                    item1.put(dttPurchaseOrderHeaderData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dttPurchaseOrderHeaderData.Property_intSumAmount, String.valueOf(data.get_intSumAmount()));
                    item1.put(dttPurchaseOrderHeaderData.Property_intSumItem, String.valueOf(data.get_intSumItem()));
                    item1.put(dttPurchaseOrderHeaderData.Property_intSync, String.valueOf(data.get_intSync()));
                    item1.put(dttPurchaseOrderHeaderData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dttPurchaseOrderHeaderData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item1.put(dttPurchaseOrderHeaderData.Property_UserId, String.valueOf(data.get_UserId()));
                    item1.put(dttPurchaseOrderHeaderData.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
                    item1.put(dttPurchaseOrderHeaderData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item1.put(dttPurchaseOrderHeaderData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttPurchaseOrderHeaderData.Property_ListOftPurchaseOrderHeaderData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftPOPStandarHeaderdData() != null){
                tPOPStandardHeaderData dttPOPStandardHeaderData = new tPOPStandardHeaderData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tPOPStandardHeaderData data : this.ListOftPOPStandarHeaderdData){
                    JSONObject item = new JSONObject();
                    item.put(dttPOPStandardHeaderData.Property_intId, String.valueOf(data.get_intId()));
                    item.put(dttPOPStandardHeaderData.Property_txtType, String.valueOf(data.get_txtType()));
                    item.put(dttPOPStandardHeaderData.Property_bolHavePOP, String.valueOf(data.get_bolHavePOP()));
                    item.put(dttPOPStandardHeaderData.Property_txtCategory, String.valueOf(data.get_txtCategory()));
                    item.put(dttPOPStandardHeaderData.Property_txtReason, String.valueOf(data.get_txtReason()));
                    item.put(dttPOPStandardHeaderData.Property_intRoleId, String.valueOf(data.get_intRoleId()));
                    item.put(dttPOPStandardHeaderData.Property_txtUserName, String.valueOf(data.get_txtUserName()));
                    item.put(dttPOPStandardHeaderData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item.put(dttPOPStandardHeaderData.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
                    item.put(dttPOPStandardHeaderData.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
                    item.put(dttPOPStandardHeaderData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item.put(dttPOPStandardHeaderData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item.put(dttPOPStandardHeaderData.Property_dtDatetime, String.valueOf(data.get_DtDatetime()));
                    itemsListJquey.add(item);
                }
                resJson.put(dttPOPStandardHeaderData.Property_ListOftPOPStandarHeaderdData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftPOPStandardDetailData() != null){
                tPOPStandardDetailData dttPOPStandardDetailData = new tPOPStandardDetailData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tPOPStandardDetailData data : this.getListOftPOPStandardDetailData()){
                    JSONObject item = new JSONObject();
                    item.put(dttPOPStandardDetailData.Property_intId, String.valueOf(data.get_intId()));
                    item.put(dttPOPStandardDetailData.Property_intHeaderId, String.valueOf(data.get_intHeaderId()));
                    item.put(dttPOPStandardDetailData.Property_txtImg1, String.valueOf(data.get_txtImg1()));
                    item.put(dttPOPStandardDetailData.Property_txtImg2, String.valueOf(data.get_txtImg2()));
                    item.put(dttPOPStandardDetailData.Property_dtDatetime, String.valueOf(data.get_dtDatetime()));
                    itemsListJquey.add(item);
                }
                resJson.put(dttPOPStandardDetailData.Property_ListOftPOPStandardDetailData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftJawabanUserHeaderData() != null){
                tJawabanUserHeaderData dttJawabanUserHeaderData = new tJawabanUserHeaderData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tJawabanUserHeaderData data :this.getListOftJawabanUserHeaderData()){
                    JSONObject item = new JSONObject();
                    item.put(dttJawabanUserHeaderData.Property_intHeaderId, String.valueOf(data.get_intHeaderId()));
                    item.put(dttJawabanUserHeaderData.Property_intNik, String.valueOf(data.get_intNik()));
                    item.put(dttJawabanUserHeaderData.Property_txtUserName, String.valueOf(data.get_txtUserName()));
                    item.put(dttJawabanUserHeaderData.Property_intGroupQuestionId, String.valueOf(data.get_intGroupQuestionId()));
                    item.put(dttJawabanUserHeaderData.Property_intRoleId, String.valueOf(data.get_intRoleId()));
                    item.put(dttJawabanUserHeaderData.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
                    item.put(dttJawabanUserHeaderData.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
                    item.put(dttJawabanUserHeaderData.Property_dtDate, String.valueOf(data.get_dtDate()));
                    item.put(dttJawabanUserHeaderData.Property_dtDatetime, String.valueOf(data.get_dtDatetime()));
                    item.put(dttJawabanUserHeaderData.Property_intSum, String.valueOf(data.get_intSum()));
                    item.put(dttJawabanUserHeaderData.Property_intAverage, String.valueOf(data.get_intAverage()));
                    itemsListJquey.add(item);
                }
                resJson.put(dttJawabanUserHeaderData.Property_ListOftJawabanUserHeaderData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftJawabanUserData() != null){
                tJawabanUserData dtJawabanUserData = new tJawabanUserData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tJawabanUserData data  : this.getListOftJawabanUserData()){
                    JSONObject item = new JSONObject();
                    item.put(dtJawabanUserData.Property_intUserAnswer, String.valueOf(data.get_intUserAnswer()));
                    item.put(dtJawabanUserData.Property_intHeaderId, String.valueOf(data.get_intHeaderId()));
                    item.put(dtJawabanUserData.Property_intUserId, String.valueOf(data.get_intUserId()));
                    item.put(dtJawabanUserData.Property_intNik, String.valueOf(data.get_intNik()));
                    item.put(dtJawabanUserData.Property_intRoleId, String.valueOf(data.get_intRoleId()));
                    item.put(dtJawabanUserData.Property_intQuestionId, String.valueOf(data.get_intQuestionId()));
                    item.put(dtJawabanUserData.Property_intTypeQuestionId, String.valueOf(data.get_intTypeQuestionId()));
                    item.put(dtJawabanUserData.Property_bolHaveAnswerList, String.valueOf(data.get_bolHaveAnswerList()));
                    item.put(dtJawabanUserData.Property_intAnswerId, String.valueOf(data.get_intAnswerId()));
                    item.put(dtJawabanUserData.Property_txtValue, String.valueOf(data.get_txtValue()));
                    item.put(dtJawabanUserData.Property_ptQuiz, String.valueOf(data.get_ptQuiz()));
                    item.put(dtJawabanUserData.Property_txtFileQuiz, String.valueOf(data.get_txtFileQuiz()));
                    item.put(dtJawabanUserData.Property_decBobot, String.valueOf(data.get_decBobot()));
                    item.put(dtJawabanUserData.Property_dtDate, String.valueOf(data.get_dtDate()));
                    item.put(dtJawabanUserData.Property_dtDatetime, String.valueOf(data.get_dtDatetime()));
                    itemsListJquey.add(item);
                }
                resJson.put(dtJawabanUserData.Property_ListOftJawabanUserData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftOverStockHeaderData() != null){
                tOverStockHeaderData dttOverStockHeaderData = new tOverStockHeaderData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tOverStockHeaderData data : this.getListOftOverStockHeaderData()){
                    JSONObject item1 = new JSONObject();
                    item1.put(dttOverStockHeaderData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttOverStockHeaderData.Property_txtOverStock, String.valueOf(data.get_txtOverStock()));
                    item1.put(dttOverStockHeaderData.Property_OutletCode, String.valueOf(data.get_OutletCode()));
                    item1.put(dttOverStockHeaderData.Property_OutletName, String.valueOf(data.get_OutletName()));
                    item1.put(dttOverStockHeaderData.Property_txtDate, String.valueOf(data.get_dtDate()));
                    item1.put(dttOverStockHeaderData.Property_intIdAbsenUser, String.valueOf(data.get_intIdAbsenUser()));
                    item1.put(dttOverStockHeaderData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dttOverStockHeaderData.Property_intSumAmount, String.valueOf(data.get_intSumAmount()));
                    item1.put(dttOverStockHeaderData.Property_intSumItem, String.valueOf(data.get_intSumItem()));
                    item1.put(dttOverStockHeaderData.Property_intSync, String.valueOf(data.get_intSync()));
                    item1.put(dttOverStockHeaderData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dttOverStockHeaderData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item1.put(dttOverStockHeaderData.Property_UserId, String.valueOf(data.get_UserId()));
                    item1.put(dttOverStockHeaderData.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
                    item1.put(dttOverStockHeaderData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item1.put(dttOverStockHeaderData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttOverStockHeaderData.Property_ListOftOverStockHeaderData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftOverStockDetailData() != null){
                tOverStockDetailData dttOverStockDetailData = new tOverStockDetailData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tOverStockDetailData data : this.getListOftOverStockDetailData()){
                    JSONObject item = new JSONObject();
                    item.put(dttOverStockDetailData.Property_intId, String.valueOf(data.getIntId()));
                    item.put(dttOverStockDetailData.Property_dtDate, String.valueOf(data.get_dtDate()));
                    item.put(dttOverStockDetailData.Property_intPrice, String.valueOf(data.get_intTotal()));
                    item.put(dttOverStockDetailData.Property_txtQuantity, String.valueOf(data.getTxtExpireDate()));
                    item.put(dttOverStockDetailData.Property_txtCodeProduct, String.valueOf(data.get_intPrice()));
                    item.put(dttOverStockDetailData.Property_txtKeterangan, String.valueOf(data.get_txtCodeProduct()));
                    item.put(dttOverStockDetailData.Property_txtExpireDate, String.valueOf(data.getTxtProduct()));
                    item.put(dttOverStockDetailData.Property_txtProduct, String.valueOf(data.get_txtKeterangan()));
                    item.put(dttOverStockDetailData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item.put(dttOverStockDetailData.Property_intTotal, String.valueOf(data.get_txtOverStock()));
                    item.put(dttOverStockDetailData.Property_txtOverStock, String.valueOf(data.getTxtQuantity()));
                    itemsListJquey.add(item);
                }
                resJson.put(dttOverStockDetailData.Property_ListOftOverStockDetailData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftSalesProductQuantityHeaderData() != null){
                tSalesProductQuantityHeaderData dttSalesProductQuantityData = new tSalesProductQuantityHeaderData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tSalesProductQuantityHeaderData data : this.getListOftSalesProductQuantityHeaderData()){
                    JSONObject item1 = new JSONObject();
                    item1.put(dttSalesProductQuantityData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttSalesProductQuantityData.Property_txtQuantityStock, String.valueOf(data.get_txtQuantityStock()));
                    item1.put(dttSalesProductQuantityData.Property_OutletCode, String.valueOf(data.get_OutletCode()));
                    item1.put(dttSalesProductQuantityData.Property_OutletName, String.valueOf(data.get_OutletName()));
                    item1.put(dttSalesProductQuantityData.Property_txtDate, String.valueOf(data.get_dtDate()));
                    item1.put(dttSalesProductQuantityData.Property_intIdAbsenUser, String.valueOf(data.get_intIdAbsenUser()));
                    item1.put(dttSalesProductQuantityData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dttSalesProductQuantityData.Property_intSumAmount, String.valueOf(data.get_intSumAmount()));
                    item1.put(dttSalesProductQuantityData.Property_intSumItem, String.valueOf(data.get_intSumItem()));
                    item1.put(dttSalesProductQuantityData.Property_intSync, String.valueOf(data.get_intSync()));
                    item1.put(dttSalesProductQuantityData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dttSalesProductQuantityData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item1.put(dttSalesProductQuantityData.Property_UserId, String.valueOf(data.get_UserId()));
                    item1.put(dttSalesProductQuantityData.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
                    item1.put(dttSalesProductQuantityData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item1.put(dttSalesProductQuantityData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttSalesProductQuantityData.Property_ListOftSalesProductHeaderData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftKemasanRusakHeaderData() != null){
                tKemasanRusakHeaderData dttKemasanRusakHeaderData = new tKemasanRusakHeaderData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tKemasanRusakHeaderData data : this.getListOftKemasanRusakHeaderData()){
                    JSONObject item1 = new JSONObject();
                    item1.put(dttKemasanRusakHeaderData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttKemasanRusakHeaderData.Property_txtKemasanRusak, String.valueOf(data.get_txtKemasanRusak()));
                    item1.put(dttKemasanRusakHeaderData.Property_OutletCode, String.valueOf(data.get_OutletCode()));
                    item1.put(dttKemasanRusakHeaderData.Property_OutletName, String.valueOf(data.get_OutletName()));
                    item1.put(dttKemasanRusakHeaderData.Property_txtDate, String.valueOf(data.get_dtDate()));
                    item1.put(dttKemasanRusakHeaderData.Property_intIdAbsenUser, String.valueOf(data.get_intIdAbsenUser()));
                    item1.put(dttKemasanRusakHeaderData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dttKemasanRusakHeaderData.Property_intSumAmount, String.valueOf(data.get_intSumAmount()));
                    item1.put(dttKemasanRusakHeaderData.Property_intSumItem, String.valueOf(data.get_intSumItem()));
                    item1.put(dttKemasanRusakHeaderData.Property_intSync, String.valueOf(data.get_intSync()));
                    item1.put(dttKemasanRusakHeaderData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dttKemasanRusakHeaderData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item1.put(dttKemasanRusakHeaderData.Property_UserId, String.valueOf(data.get_UserId()));
                    item1.put(dttKemasanRusakHeaderData.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
                    item1.put(dttKemasanRusakHeaderData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item1.put(dttKemasanRusakHeaderData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttKemasanRusakHeaderData.Property_ListOftKemasanRusakHeaderData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftKemasanRusakDetailData() != null){
                tKemasanRusakDetailData dttKemasanRusakDetailData = new tKemasanRusakDetailData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tKemasanRusakDetailData data : this.getListOftKemasanRusakDetailData()){
                    JSONObject item = new JSONObject();
                    item.put(dttKemasanRusakDetailData.Property_intId, String.valueOf(data.getIntId()));
                    item.put(dttKemasanRusakDetailData.Property_dtDate, String.valueOf(data.get_dtDate()));
                    item.put(dttKemasanRusakDetailData.Property_intPrice, String.valueOf(data.get_intTotal()));
                    item.put(dttKemasanRusakDetailData.Property_txtQuantity, String.valueOf(data.getTxtExpireDate()));
                    item.put(dttKemasanRusakDetailData.Property_txtCodeProduct, String.valueOf(data.get_intPrice()));
                    item.put(dttKemasanRusakDetailData.Property_txtKeterangan, String.valueOf(data.get_txtCodeProduct()));
                    item.put(dttKemasanRusakDetailData.Property_txtExpireDate, String.valueOf(data.getTxtProduct()));
                    item.put(dttKemasanRusakDetailData.Property_txtProduct, String.valueOf(data.get_txtKeterangan()));
                    item.put(dttKemasanRusakDetailData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item.put(dttKemasanRusakDetailData.Property_intTotal, String.valueOf(data.get_txtKemasanRusak()));
                    item.put(dttKemasanRusakDetailData.Property_txtKemasanRusak, String.valueOf(data.getTxtQuantity()));
                    itemsListJquey.add(item);
                }
                resJson.put(dttKemasanRusakDetailData.Property_ListOftKemasanRusakDetailData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftKemasanRusakImageData() != null) {
                tKemasanRusakImageData dttKemasanRusakImageData = new tKemasanRusakImageData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tKemasanRusakImageData data : this.getListOftKemasanRusakImageData()) {
                    JSONObject item = new JSONObject();
                    item.put(dttKemasanRusakImageData.Property_txtId, String.valueOf(data.get_txtId()));
                    item.put(dttKemasanRusakImageData.Property_txtHeaderId, String.valueOf(data.get_txtHeaderId()));
                    item.put(dttKemasanRusakImageData.Property_txtImage, String.valueOf(data.get_txtImage()));
                    item.put(dttKemasanRusakImageData.Property_intPosition, String.valueOf(data.get_intPosition()));
                    item.put(dttKemasanRusakImageData.Property_txtType, String.valueOf(data.get_txtType()));
                    itemsListJquey.add(item);
                }
                resJson.put(dttKemasanRusakImageData.Property_ListOftKemasanRusakImageData, new JSONArray(itemsListJquey));
            }

            if (this.getListOfTrackingLocationData() != null){
                trackingLocationData dtTrackingLocationData = new trackingLocationData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (trackingLocationData data : this.getListOfTrackingLocationData()){
                    JSONObject item1 = new JSONObject();
                    item1.put(dtTrackingLocationData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dtTrackingLocationData.Property_txtLongitude, String.valueOf(data.get_txtLongitude()));
                    item1.put(dtTrackingLocationData.Property_txtLatitude, String.valueOf(data.get_txtLatitude()));
                    item1.put(dtTrackingLocationData.Property_txtAccuracy, String.valueOf(data.get_txtAccuracy()));
                    item1.put(dtTrackingLocationData.Property_txtTime, String.valueOf(data.get_txtTime()));
                    item1.put(dtTrackingLocationData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item1.put(dtTrackingLocationData.Property_txtUsername, String.valueOf(data.get_txtUsername()));
                    item1.put(dtTrackingLocationData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    item1.put(dtTrackingLocationData.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(dtTrackingLocationData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dtTrackingLocationData.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
                    item1.put(dtTrackingLocationData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item1.put(dtTrackingLocationData.Property_intSequence, String.valueOf(data.get_intSequence()));
                    item1.put(dtTrackingLocationData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dtTrackingLocationData.Property_intSync, String.valueOf(data.get_intSync()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtTrackingLocationData.Property_ListOftrackingLocation, new JSONArray(itemsListJquey));
            }

            if (this.getListOfKoordinasiOutletData() != null){
                KoordinasiOutletData dtKoordinasiOutletData = new KoordinasiOutletData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (KoordinasiOutletData data : this.getListOfKoordinasiOutletData()){
                    JSONObject item = new JSONObject();
                    item.put(dtKoordinasiOutletData.Property_intId, String.valueOf(data.get_intId()));
                    item.put(dtKoordinasiOutletData.Property_dtDate, String.valueOf(data.get_dtDate()));
                    item.put(dtKoordinasiOutletData.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
                    item.put(dtKoordinasiOutletData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item.put(dtKoordinasiOutletData.Property_txtUsername, String.valueOf(data.get_txtUsername()));
                    item.put(dtKoordinasiOutletData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    item.put(dtKoordinasiOutletData.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
                    item.put(dtKoordinasiOutletData.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
                    item.put(dtKoordinasiOutletData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item.put(dtKoordinasiOutletData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item.put(dtKoordinasiOutletData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item.put(dtKoordinasiOutletData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item.put(dtKoordinasiOutletData.Property_intSync, String.valueOf(data.get_intSync()));
                    item.put(dtKoordinasiOutletData.Property_intCategoriId, String.valueOf(data.get_intCategoriId()));
                    item.put(dtKoordinasiOutletData.Property_txtCategory, String.valueOf(data.get_txtCategory()));
                    itemsListJquey.add(item);
                }
                resJson.put(dtKoordinasiOutletData.Property_ListOfKoordinasiOutlet, new JSONArray(itemsListJquey));
            }

            if (this.getListOfKoordinasiOutletImageData() != null){
                KoordinasiOutletImageData dtKoordinasiOutletImageData = new KoordinasiOutletImageData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (KoordinasiOutletImageData data : this.getListOfKoordinasiOutletImageData()){
                    JSONObject item = new JSONObject();
                    item.put(dtKoordinasiOutletImageData.Property_txtId, String.valueOf(data.get_txtId()));
                    item.put(dtKoordinasiOutletImageData.Property_txtHeaderId, String.valueOf(data.get_txtHeaderId()));
                    item.put(dtKoordinasiOutletImageData.Property_txtImage, String.valueOf(data.get_txtImage()));
                    item.put(dtKoordinasiOutletImageData.Property_intPosition, String.valueOf(data.get_intPosition()));
                    itemsListJquey.add(item);
                }
                resJson.put(dtKoordinasiOutletImageData.Property_ListOfKoordinasiOutletImageData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftTidakSesuaiPesananHeaderData() != null){
                tTidakSesuaiPesananHeaderData dtKoordinasiOutletData = new tTidakSesuaiPesananHeaderData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tTidakSesuaiPesananHeaderData data : this.getListOftTidakSesuaiPesananHeaderData()){
                    JSONObject item = new JSONObject();
                    item.put(dtKoordinasiOutletData.Property_intId, String.valueOf(data.get_intId()));
                    item.put(dtKoordinasiOutletData.Property_dtDate, String.valueOf(data.get_dtDate()));
                    item.put(dtKoordinasiOutletData.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
                    item.put(dtKoordinasiOutletData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item.put(dtKoordinasiOutletData.Property_txtUsername, String.valueOf(data.get_txtUsername()));
                    item.put(dtKoordinasiOutletData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    item.put(dtKoordinasiOutletData.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
                    item.put(dtKoordinasiOutletData.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
                    item.put(dtKoordinasiOutletData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item.put(dtKoordinasiOutletData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item.put(dtKoordinasiOutletData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item.put(dtKoordinasiOutletData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item.put(dtKoordinasiOutletData.Property_intSync, String.valueOf(data.get_intSync()));
                    item.put(dtKoordinasiOutletData.Property_intCategoriId, String.valueOf(data.get_intCategoriId()));
                    item.put(dtKoordinasiOutletData.Property_txtCategory, String.valueOf(data.get_txtCategory()));
                    itemsListJquey.add(item);
                }
                resJson.put(dtKoordinasiOutletData.Property_ListOftTidakSesuaiPesananHeaderData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftTidakSesuaiPesananImageData() != null){
                tTidakSesuaiPesananImageData dtKoordinasiOutletImageData = new tTidakSesuaiPesananImageData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tTidakSesuaiPesananImageData data : this.getListOftTidakSesuaiPesananImageData()){
                    JSONObject item = new JSONObject();
                    item.put(dtKoordinasiOutletImageData.Property_txtId, String.valueOf(data.get_txtId()));
                    item.put(dtKoordinasiOutletImageData.Property_txtHeaderId, String.valueOf(data.get_txtHeaderId()));
                    item.put(dtKoordinasiOutletImageData.Property_txtImage, String.valueOf(data.get_txtImage()));
                    item.put(dtKoordinasiOutletImageData.Property_intPosition, String.valueOf(data.get_intPosition()));
                    itemsListJquey.add(item);
                }
                resJson.put(dtKoordinasiOutletImageData.Property_ListOfKoordinasiOutletImageData, new JSONArray(itemsListJquey));
            }

            if (this.get_ListOftCustomerBasedMobileHeaderData() != null) {
                tCustomerBasedMobileHeaderData dttCustomerBasedHeaderData = new tCustomerBasedMobileHeaderData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tCustomerBasedMobileHeaderData data : this.get_ListOftCustomerBasedMobileHeaderData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttCustomerBasedHeaderData.Property_intTrCustomerId, String.valueOf(data.get_intTrCustomerId()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtSubmissionId, String.valueOf(data.get_txtSubmissionId()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtSubmissionCode, String.valueOf(data.get_txtSubmissionCode()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtSumberData, String.valueOf(data.get_txtSumberData()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtNamaSumberData, String.valueOf(data.get_txtNamaSumberData()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtNamaDepan, String.valueOf(data.get_txtNamaDepan()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtGender, String.valueOf(data.get_txtGender()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtTelp, String.valueOf(data.get_txtTelp()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtTelp2, String.valueOf(data.get_txtTelp2()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtTelpKantor, String.valueOf(data.get_txtTelpKantor()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtEmail, String.valueOf(data.get_txtEmail()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtPINBBM, String.valueOf(data.get_txtPINBBM()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtALamat, String.valueOf(data.get_txtALamat()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtRoleId, String .valueOf(data.get_txtRoleId()));
                    item1.put(dttCustomerBasedHeaderData.Property_intPIC, String.valueOf(data.get_intPIC()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(dttCustomerBasedHeaderData.Property_bitActive, String.valueOf(data.get_bitActive()));
                    item1.put(dttCustomerBasedHeaderData.Property_dtDate, String.valueOf(data.get_dtDate()));
                    item1.put(dttCustomerBasedHeaderData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dttCustomerBasedHeaderData.Property_intSync, String.valueOf(data.get_intSync()));
                    item1.put(dttCustomerBasedHeaderData.Property_txtTglLahir, String.valueOf(data.get_txtTglLahir()));
                    item1.put(dttCustomerBasedHeaderData.Property_intAge, String.valueOf(data.get_intAge()));
                    item1.put(dttCustomerBasedHeaderData.Property_intAgeTypeFlag, String.valueOf(data.get_intAgeTypeFlag()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttCustomerBasedHeaderData.Property_ListOftCustomerBasedMobileHeaderData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftCustomerBasedMobileDetailData() != null) {
                tCustomerBasedMobileDetailData dttCustomerBasedDetailData = new tCustomerBasedMobileDetailData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tCustomerBasedMobileDetailData data : this.getListOftCustomerBasedMobileDetailData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttCustomerBasedDetailData.Property_intTrCustomerIdDetail, String.valueOf(data.get_intTrCustomerIdDetail()));
                    item1.put(dttCustomerBasedDetailData.Property_intTrCustomerId, String.valueOf(data.get_intTrCustomerId()));
                    item1.put(dttCustomerBasedDetailData.Property_txtNamaDepan, String.valueOf(data.get_txtNamaDepan()));
                    item1.put(dttCustomerBasedDetailData.Property_txtGender, String.valueOf(data.get_txtGender()));
                    item1.put(dttCustomerBasedDetailData.Property_intNo, String.valueOf(data.get_intNo()));
                    item1.put(dttCustomerBasedDetailData.Property_intPIC, String.valueOf(data.get_intPIC()));
                    item1.put(dttCustomerBasedDetailData.Property_bitActive, String.valueOf(data.get_bitActive()));
                    item1.put(dttCustomerBasedDetailData.Property_dtInserted, String.valueOf(data.get_dtInserted()));
                    item1.put(dttCustomerBasedDetailData.Property_dtUpdated, String.valueOf(data.get_dtUpdated()));
                    item1.put(dttCustomerBasedDetailData.Property_txtInsertedBy, String.valueOf(data.get_txtInsertedBy()));
                    item1.put(dttCustomerBasedDetailData.Property_txtUpdatedBy, String.valueOf(data.get_txtUpdatedBy()));
                    item1.put(dttCustomerBasedDetailData.Property_txtUsiaKehamilan, String.valueOf(data.get_txtUsiaKehamilan()));
                    item1.put(dttCustomerBasedDetailData.Property_txtTglLahir, String.valueOf(data.get_txtTglLahir()));
                    item1.put(dttCustomerBasedDetailData.Property_intAge, String.valueOf(data.get_intAge()));
                    item1.put(dttCustomerBasedDetailData.Property_intAgeTypeFlag, String.valueOf(data.get_intAgeTypeFlag()));

                    itemsListJquey.add(item1);
                }
                resJson.put(dttCustomerBasedDetailData.Property_ListOftCustomerBasedMobileDetailData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftCustomerBasedMobileDetailProductData() != null) {
                tCustomerBasedMobileDetailProductData dttCustomerBasedDetailProductData = new tCustomerBasedMobileDetailProductData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tCustomerBasedMobileDetailProductData data : this.getListOftCustomerBasedMobileDetailProductData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttCustomerBasedDetailProductData.Property_intTrCustomerIdDetailProduct, String.valueOf(data.get_intTrCustomerIdDetailProduct()));
                    item1.put(dttCustomerBasedDetailProductData.Property_intTrCustomerIdDetail, String.valueOf(data.get_intTrCustomerIdDetail()));
                    item1.put(dttCustomerBasedDetailProductData.Property_txtProductBrandCode, String.valueOf(data.get_txtProductBrandCode()));
                    item1.put(dttCustomerBasedDetailProductData.Property_txtProductBrandName, String.valueOf(data.get_txtProductBrandName()));
                    item1.put(dttCustomerBasedDetailProductData.Property_txtProductBrandQty, String.valueOf(data.get_txtProductBrandQty()));
                    item1.put(dttCustomerBasedDetailProductData.Property_bitActive, String.valueOf(data.get_bitActive()));
                    item1.put(dttCustomerBasedDetailProductData.Property_dtInserted, String.valueOf(data.get_dtInserted()));
                    item1.put(dttCustomerBasedDetailProductData.Property_dtUpdated, String.valueOf(data.get_dtUpdated()));
                    item1.put(dttCustomerBasedDetailProductData.Property_txtInsertedBy, String.valueOf(data.get_txtInsertedBy()));
                    item1.put(dttCustomerBasedDetailProductData.Property_txtUpdatedBy, String.valueOf(data.get_txtUpdatedBy()));
                    item1.put(dttCustomerBasedDetailProductData.Property_txtProductCompetitorCode, String.valueOf(data.get_txtProductCompetitorCode()));
                    item1.put(dttCustomerBasedDetailProductData.Property_txtProductCompetitorName, String.valueOf(data.get_txtProductCompetitorName()));
                    item1.put(dttCustomerBasedDetailProductData.Property_txtLOB, String.valueOf(data.get_txtLOB()));
                    item1.put(dttCustomerBasedDetailProductData.Property_txtProductBrandCodeCRM, String.valueOf(data.get_txtProductBrandCodeCRM()));

                    itemsListJquey.add(item1);
                }
                resJson.put(dttCustomerBasedDetailProductData.Property_ListOftCustomerBasedMobileDetailProductData, new JSONArray(itemsListJquey));
            }

            if (this.getListOfmEmployeeAreaData() != null) {
                mEmployeeAreaData dtmEmployeeAreaData = new mEmployeeAreaData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (mEmployeeAreaData data : this.getListOfmEmployeeAreaData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dtmEmployeeAreaData.Property_intBranchId, String.valueOf(data.get_intBranchId()));
                    item1.put(dtmEmployeeAreaData.Property_intChannelId, String.valueOf(data.get_intChannelId()));
                    item1.put(dtmEmployeeAreaData.Property_intEmployeeId, String.valueOf(data.get_intEmployeeId()));
                    item1.put(dtmEmployeeAreaData.Property_intID, String.valueOf(data.get_intID()));
                    item1.put(dtmEmployeeAreaData.Property_intOutletId, String.valueOf(data.get_intOutletId()));
                    item1.put(dtmEmployeeAreaData.Property_intRayonId, String.valueOf(data.get_intRayonId()));
                    item1.put(dtmEmployeeAreaData.Property_intRegionId, String.valueOf(data.get_intRegionId()));
                    item1.put(dtmEmployeeAreaData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dtmEmployeeAreaData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item1.put(dtmEmployeeAreaData.Property_txtName, String.valueOf(data.get_txtName()));
                    item1.put(dtmEmployeeAreaData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item1.put(dtmEmployeeAreaData.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
                    item1.put(dtmEmployeeAreaData.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
                    item1.put(dtmEmployeeAreaData.Property_txtRayonName, String.valueOf(data.get_txtRayonCode()));
                    item1.put(dtmEmployeeAreaData.Property_txtRayonName, String.valueOf(data.get_txtRayonName()));
                    item1.put(dtmEmployeeAreaData.Property_txtRegionName, String.valueOf(data.get_txtRegionName()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtmEmployeeAreaData.Property_ListOfmEmployeeAreaData, new JSONArray(itemsListJquey));
            }


            if (this.getListOfmEmployeeBranchData() != null) {
                mEmployeeBranchData dtmEmployeeBranchData = new mEmployeeBranchData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (mEmployeeBranchData data : this.getListOfmEmployeeBranchData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dtmEmployeeBranchData.Property_EmpId, String.valueOf(data.get_EmpId()));
                    item1.put(dtmEmployeeBranchData.Property_intID, String.valueOf(data.get_intID()));
                    item1.put(dtmEmployeeBranchData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dtmEmployeeBranchData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item1.put(dtmEmployeeBranchData.Property_txtName, String.valueOf(data.get_txtName()));
                    item1.put(dtmEmployeeBranchData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtmEmployeeBranchData.Property_ListOfEmployeeBranchData, new JSONArray(itemsListJquey));
            }

            if (this.getListOfmEmployeeSalesProductData() != null) {
                mEmployeeSalesProductData dtmEmployeeSalesProductData = new mEmployeeSalesProductData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (mEmployeeSalesProductData data : this.getListOfmEmployeeSalesProductData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dtmEmployeeSalesProductData.Property_decBobot, String.valueOf(data.get_decBobot()));
                    item1.put(dtmEmployeeSalesProductData.Property_decHJD, String.valueOf(data.get_decHJD()));
                    item1.put(dtmEmployeeSalesProductData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dtmEmployeeSalesProductData.Property_txtBrandDetailGramCode, String.valueOf(data.get_txtBrandDetailGramCode()));
                    item1.put(dtmEmployeeSalesProductData.Property_txtName, String.valueOf(data.get_txtName()));
                    item1.put(dtmEmployeeSalesProductData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item1.put(dtmEmployeeSalesProductData.Property_txtProductBrandDetailGramName, String.valueOf(data.get_txtProductBrandDetailGramName()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtmEmployeeSalesProductData.Property_ListOfmEmployeeSalesProductData, new JSONArray(itemsListJquey));
            }


            if (this.getListOfmNotificationData() != null) {
                mNotificationData dtmNotificationData = new mNotificationData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (mNotificationData data : this.getListOfmNotificationData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dtmNotificationData.Property_dtPublishEnd, String.valueOf(data.get_dtPublishEnd()));
                    item1.put(dtmNotificationData.Property_dtPublishStart, String.valueOf(data.get_dtPublishStart()));
                    item1.put(dtmNotificationData.Property_dtStatus, String.valueOf(data.get_dtStatus()));
                    item1.put(dtmNotificationData.Property_intSync, String.valueOf(data.get_intSync()));
                    item1.put(dtmNotificationData.Property_txtDataId, String.valueOf(data.get_txtDataId()));
                    item1.put(dtmNotificationData.Property_txtDescription, String.valueOf(data.get_txtDescription()));
                    item1.put(dtmNotificationData.Property_txtImage, String.valueOf(data.get_txtImage()));
                    item1.put(dtmNotificationData.Property_txtNotifId, String.valueOf(data.get_txtNotifId()));
                    item1.put(dtmNotificationData.Property_txtStatus, String.valueOf(data.get_txtStatus()));
                    item1.put(dtmNotificationData.Property_txtTitle, String.valueOf(data.get_txtTitle()));
                    item1.put(dtmNotificationData.Property_txtUserID, String.valueOf(data.get_txtUserID()));
                    item1.put(dtmNotificationData.Property_txtLinkImage, String.valueOf(data.get_txtLinkImage()));
                    item1.put(dtmNotificationData.Property_intSuccessDLFile, String.valueOf(data.get_intSuccessDLFile()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtmNotificationData.Property_ListOfmNotificationData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftAbsenUserData() != null) {
                tAbsenUserData dttAbsenUserData = new tAbsenUserData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tAbsenUserData data : this.getListOftAbsenUserData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttAbsenUserData.Property_dtDateCheckIn, String.valueOf(data.get_dtDateCheckIn()));
                    item1.put(dttAbsenUserData.Property_dtDateCheckOut, String.valueOf(data.get_dtDateCheckOut()));
                    item1.put(dttAbsenUserData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttAbsenUserData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dttAbsenUserData.Property_intSync, String.valueOf(data.get_intSync()));
                    item1.put(dttAbsenUserData.Property_txtAbsen, String.valueOf(data.get_txtAbsen()));
                    item1.put(dttAbsenUserData.Property_txtAccuracy, String.valueOf(data.get_txtAccuracy()));
                    item1.put(dttAbsenUserData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dttAbsenUserData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item1.put(dttAbsenUserData.Property_txtLatitude, String.valueOf(data.get_txtLatitude()));
                    item1.put(dttAbsenUserData.Property_txtLongitude, String.valueOf(data.get_txtLongitude()));
                    item1.put(dttAbsenUserData.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
                    item1.put(dttAbsenUserData.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
                    item1.put(dttAbsenUserData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item1.put(dttAbsenUserData.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(dttAbsenUserData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    item1.put(dttAbsenUserData.Property_txtImg1, String.valueOf(data.get_txtImg1()));
                    item1.put(dttAbsenUserData.Property_txtImg2, String.valueOf(data.get_txtImg2()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttAbsenUserData.Property_ListOftAbsenUser, new JSONArray(itemsListJquey));
            }

            if (this.getListOftAttendanceUserData() != null) {
                tAttendanceUserData dttAttendanceUserData = new tAttendanceUserData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tAttendanceUserData data : this.getListOftAttendanceUserData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttAttendanceUserData.Property_dtDateCheckIn, String.valueOf(data.get_dtDateCheckIn()));
                    item1.put(dttAttendanceUserData.Property_dtDateCheckOut, String.valueOf(data.get_dtDateCheckOut()));
                    item1.put(dttAttendanceUserData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttAttendanceUserData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dttAttendanceUserData.Property_intSync, String.valueOf(data.get_intSync()));
                    item1.put(dttAttendanceUserData.Property_txtAbsen, String.valueOf(data.get_txtAbsen()));
                    item1.put(dttAttendanceUserData.Property_txtAccuracy, String.valueOf(data.get_txtAccuracy()));
                    item1.put(dttAttendanceUserData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dttAttendanceUserData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item1.put(dttAttendanceUserData.Property_txtLatitude, String.valueOf(data.get_txtLatitude()));
                    item1.put(dttAttendanceUserData.Property_txtLongitude, String.valueOf(data.get_txtLongitude()));
                    item1.put(dttAttendanceUserData.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
                    item1.put(dttAttendanceUserData.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
                    item1.put(dttAttendanceUserData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item1.put(dttAttendanceUserData.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(dttAttendanceUserData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    item1.put(dttAttendanceUserData.Property_txtImg1, String.valueOf(data.get_txtImg1()));
                    item1.put(dttAttendanceUserData.Property_txtImg2, String.valueOf(data.get_txtImg2()));
                    item1.put(dttAttendanceUserData.Property_txtDesc, String.valueOf(data.get_txtDesc()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttAttendanceUserData.Property_ListOftAttendanceUser, new JSONArray(itemsListJquey));
            }

            if (this.getListOfmGeolocationOutletSPGData() != null) {
                mGeolocationOutletSPGData dtmGeolocationOutletSPGData = new mGeolocationOutletSPGData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (mGeolocationOutletSPGData data : this.getListOfmGeolocationOutletSPGData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dtmGeolocationOutletSPGData.Property_IntId, String.valueOf(data.get_intId()));
                    item1.put(dtmGeolocationOutletSPGData.Property_txtAcc, String.valueOf(data.get_txtAcc()));
                    item1.put(dtmGeolocationOutletSPGData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dtmGeolocationOutletSPGData.Property_txtLatitude, String.valueOf(data.get_txtLatitude()));
                    item1.put(dtmGeolocationOutletSPGData.Property_txtLongitude, String.valueOf(data.get_txtLongitude()));
                    item1.put(dtmGeolocationOutletSPGData.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtmGeolocationOutletSPGData.Property_ListOfmGeolocationOutletSPG, new JSONArray(itemsListJquey));
            }

            if (this.getListOftActivityData() != null) {
                tActivityData dttActivityData = new tActivityData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tActivityData data : this.getListOftActivityData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttActivityData.Property_dtActivity, String.valueOf(data.get_dtActivity()));
                    item1.put(dttActivityData.Property_intActive, String.valueOf(data.get_intActive()));
                    item1.put(dttActivityData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttActivityData.Property_intIdSyn, String.valueOf(data.get_intIdSyn()));
                    item1.put(dttActivityData.Property_intFlag, String.valueOf(data.get_intFlag()));
                    item1.put(dttActivityData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dttActivityData.Property_txtDesc, String.valueOf(data.get_txtDesc()));
                    item1.put(dttActivityData.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(dttActivityData.Property_txtImg1, String.valueOf(data.get_txtImg1()));
                    item1.put(dttActivityData.Property_txtImg2, String.valueOf(data.get_txtImg2()));
                    item1.put(dttActivityData.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
                    item1.put(dttActivityData.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
                    item1.put(dttActivityData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item1.put(dttActivityData.Property_txtBranch, String.valueOf(data.get_txtBranch()));
                    item1.put(dttActivityData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttActivityData.Property_ListOfTActivity, new JSONArray(itemsListJquey));
            }

            if (this.getListOftActivityMobileData() != null) {
                tActivityMobileData dttActivityMobileData = new tActivityMobileData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tActivityMobileData data : this.getListOftActivityMobileData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttActivityMobileData.Property_dtActivity, String.valueOf(data.get_dtActivity()));
                    item1.put(dttActivityMobileData.Property_intActive, String.valueOf(data.get_intActive()));
                    item1.put(dttActivityMobileData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttActivityMobileData.Property_intIdSyn, String.valueOf(data.get_intIdSyn()));
                    item1.put(dttActivityMobileData.Property_intFlag, String.valueOf(data.get_intFlag()));
                    item1.put(dttActivityMobileData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dttActivityMobileData.Property_txtDesc, String.valueOf(data.get_txtDesc()));
                    item1.put(dttActivityMobileData.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(dttActivityMobileData.Property_txtImg1, String.valueOf(data.get_txtImg1()));
                    item1.put(dttActivityMobileData.Property_txtImg2, String.valueOf(data.get_txtImg2()));
                    item1.put(dttActivityMobileData.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
                    item1.put(dttActivityMobileData.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
                    item1.put(dttActivityMobileData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item1.put(dttActivityMobileData.Property_txtBranch, String.valueOf(data.get_txtBranch()));
                    item1.put(dttActivityMobileData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    item1.put(dttActivityMobileData.Property_intSubTypeActivity, String.valueOf(data.get_intSubTypeActivity()));
                    item1.put(dttActivityMobileData.Property_txtTypeActivity, String.valueOf(data.get_txtTypeActivity()));
                    item1.put(dttActivityMobileData.Property__intIsValid, String.valueOf(data.get_intIsValid()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttActivityMobileData.Property_ListOfTActivityMobile, new JSONArray(itemsListJquey));
            }

            if (this.getListOftPlanogramMobileData() != null) {
                tPlanogramMobileData dttPlanogramMobileData = new tPlanogramMobileData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tPlanogramMobileData data : this.getListOftPlanogramMobileData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttPlanogramMobileData.Property_txtIdPlanogram, String.valueOf(data.get_txtIdPlanogram()));
                    item1.put(dttPlanogramMobileData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item1.put(dttPlanogramMobileData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttPlanogramMobileData.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
                    item1.put(dttPlanogramMobileData.Property_txtDate, String.valueOf(data.get_dtDate()));
                    item1.put(dttPlanogramMobileData.Property_OutletCode, String.valueOf(data.get_OutletCode()));
                    item1.put(dttPlanogramMobileData.Property_OutletName, String.valueOf(data.get_OutletName()));
                    item1.put(dttPlanogramMobileData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dttPlanogramMobileData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item1.put(dttPlanogramMobileData.Property_UserId, String.valueOf(data.get_UserId()));
                    item1.put(dttPlanogramMobileData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    item1.put(dttPlanogramMobileData.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(dttPlanogramMobileData.Property_txtIdCategory, String.valueOf(data.get_txtIdCategory()));
                    item1.put(dttPlanogramMobileData.Property_txtCategoryName, String.valueOf(data.get_txtCategoryName()));
                    item1.put(dttPlanogramMobileData.Property_intIsValid, String.valueOf(data.get_intIsValid()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttPlanogramMobileData.Property_ListOftPlanogramMobileData, new JSONArray(itemsListJquey));
            }

            if (this.getListOftPlanogramImageData() != null) {
                tPlanogramImageData dttPlanogramImageData = new tPlanogramImageData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tPlanogramImageData data : this.getListOftPlanogramImageData()) {
                    JSONObject item = new JSONObject();
                    item.put(dttPlanogramImageData.Property_txtId, String.valueOf(data.get_txtId()));
                    item.put(dttPlanogramImageData.Property_txtHeaderId, String.valueOf(data.get_txtHeaderId()));
                    item.put(dttPlanogramImageData.Property_txtImage, String.valueOf(data.get_txtImage()));
                    item.put(dttPlanogramImageData.Property_intPosition, String.valueOf(data.get_intPosition()));
                    item.put(dttPlanogramImageData.Property_txtType, String.valueOf(data.get_txtType()));
                    itemsListJquey.add(item);
                }
                resJson.put(dttPlanogramImageData.Property_ListOftPlanogramImageData, new JSONArray(itemsListJquey));
            }

            if (this.getListOfmProductBrandHeaderData() != null) {
                mProductBrandHeaderData dtmProductBrandHeaderData = new mProductBrandHeaderData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (mProductBrandHeaderData data : this.getListOfmProductBrandHeaderData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dtmProductBrandHeaderData.Property_intmProductUmbrandId, String.valueOf(data.get_intmProductUmbrandId()));
                    item1.put(dtmProductBrandHeaderData.Property_txtAliasName, String.valueOf(data.get_txtAliasName()));
                    item1.put(dtmProductBrandHeaderData.Property_txtProductBrandCode, String.valueOf(data.get_txtProductBrandCode()));
                    item1.put(dtmProductBrandHeaderData.Property_txtProductBrandName, String.valueOf(data.get_txtProductBrandName()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtmProductBrandHeaderData.Property_ListOfmProductBrandHeader, new JSONArray(itemsListJquey));
            }


            if (this.getListOfmTypeLeaveMobileData() != null) {
                mTypeLeaveMobileData dtmTypeLeaveMobileData = new mTypeLeaveMobileData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (mTypeLeaveMobileData data : this.getListOfmTypeLeaveMobileData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dtmTypeLeaveMobileData.Property_intTipeLeave, String.valueOf(data.get_intTipeLeave()));
                    item1.put(dtmTypeLeaveMobileData.Property_txtTipeLeaveName, String.valueOf(data.get_txtTipeLeaveName()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtmTypeLeaveMobileData.PropertyListOfmTypeLeaveMobileData, new JSONArray(itemsListJquey));
            }


            if (this.getListOftLeaveMobileData() != null) {
                List<mTypeLeaveMobileData> _ListOfDatamTypeLeaveMobileData = getListOfmTypeLeaveMobileData();
                tLeaveMobileData dttLeaveMobileData = new tLeaveMobileData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tLeaveMobileData data : this.getListOftLeaveMobileData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttLeaveMobileData.Property_dtLeave, String.valueOf(data.get_dtLeave()));
                    item1.put(dttLeaveMobileData.Property_intLeaveId, String.valueOf(data.get_intLeaveId()));
                    item1.put(dttLeaveMobileData.Property_intLeaveIdSync, String.valueOf(data.get_intLeaveIdSync()));
                    item1.put(dttLeaveMobileData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dttLeaveMobileData.Property_txtAlasan, String.valueOf(data.get_txtAlasan()));
                    item1.put(dttLeaveMobileData.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(dttLeaveMobileData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    item1.put(dttLeaveMobileData.Property_txtTypeAlasan, String.valueOf(data.get_txtTypeAlasan()));
                    if (_ListOfDatamTypeLeaveMobileData != null) {
                        for (mTypeLeaveMobileData d : _ListOfDatamTypeLeaveMobileData) {
                            if (Integer.valueOf(d.get_intTipeLeave()) == Integer.valueOf(data.get_txtTypeAlasan())) {
                                item1.put(dttLeaveMobileData.Property_txtTypeAlasanName, String.valueOf(d.get_txtTipeLeaveName()));
                                break;
                            }
                        }
                    }
                    item1.put(dttLeaveMobileData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item1.put(dttLeaveMobileData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttLeaveMobileData.PropertyListOftLeaveMobileData, new JSONArray(itemsListJquey));
            }


            if (this.getListOftEmployeeBRWithLOBData() != null) {
                tEmployeeBRWithLOBData dttEmployeeBRWithLOBData = new tEmployeeBRWithLOBData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tEmployeeBRWithLOBData data : this.getListOftEmployeeBRWithLOBData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttEmployeeBRWithLOBData.Property_IntEmployeeDetailId, String.valueOf(data.get_IntEmployeeDetailId()));
                    item1.put(dttEmployeeBRWithLOBData.Property_IntEmployeeId, String.valueOf(data.get_IntEmployeeId()));
                    item1.put(dttEmployeeBRWithLOBData.Property_IntlobId, String.valueOf(data.get_IntlobId()));
                    item1.put(dttEmployeeBRWithLOBData.Property_TxtLobName, String.valueOf(data.get_TxtLobName()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttEmployeeBRWithLOBData.Property_ListOftEmployeeBRWithLOBData, new JSONArray(itemsListJquey));
            }

            if (this.getListOfLogReceiverHeader_mobile() != null) {
                clsLogReceiverHeader_mobile dtclsLogReceiverHeader_mobile = new clsLogReceiverHeader_mobile();
                itemsListJquey = new ArrayList<JSONObject>();
                for (clsLogReceiverHeader_mobile data : this.getListOfLogReceiverHeader_mobile()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dtclsLogReceiverHeader_mobile.Property_txtIdLogReceiver, String.valueOf(data.getTxtIdLogReceiver()));
                    item1.put(dtclsLogReceiverHeader_mobile.Property_txtStatus, String.valueOf(data.getTxtStatus()));
                    item1.put(dtclsLogReceiverHeader_mobile.Property_txtNIK, String.valueOf(data.getTxtNIK()));
                    item1.put(dtclsLogReceiverHeader_mobile.Property_txtUSerName, String.valueOf(data.getTxtUSerName()));
                    item1.put(dtclsLogReceiverHeader_mobile.Property_dtInserted, String.valueOf(data.getDtInserted()));
                    item1.put(dtclsLogReceiverHeader_mobile.Property_dtUpdated, String.valueOf(data.getDtUpdated()));
                    item1.put(dtclsLogReceiverHeader_mobile.Property_intActive, String.valueOf(data.getIntActive()));
                    item1.put(dtclsLogReceiverHeader_mobile.Property_intSubmit, String.valueOf(data.getIntSubmit()));
                    item1.put(dtclsLogReceiverHeader_mobile.Property_intSync, String.valueOf(data.getIntSync()));
                    item1.put(dtclsLogReceiverHeader_mobile.Property_txtIdReceiver, String.valueOf(data.getTxtIdReceiver()));
                    item1.put(dtclsLogReceiverHeader_mobile.Property_txtGuidLogin, String.valueOf(data.getTxtGuidLogin()));
                    item1.put(dtclsLogReceiverHeader_mobile.Property_txtIdHeaderNotif, String.valueOf(data.getTxtIdHeaderNotif()));
                    item1.put(dtclsLogReceiverHeader_mobile.Property_txtRoleId, String.valueOf(data.getTxtRoleId()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtclsLogReceiverHeader_mobile.Property_ListOfclsLogReceiverHeader_mobile, new JSONArray(itemsListJquey));
            }

            if (this.getListOfLogReceiverDetail_mobile() != null) {
                clsLogReceiverDetail_mobile dtclsLogReceiverDetail_mobile = new clsLogReceiverDetail_mobile();
                itemsListJquey = new ArrayList<JSONObject>();
                for (clsLogReceiverDetail_mobile data : this.getListOfLogReceiverDetail_mobile()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dtclsLogReceiverDetail_mobile.Property_txtIdLogReceiverDetail, String.valueOf(data.getTxtIdLogReceiverDetail()));
                    item1.put(dtclsLogReceiverDetail_mobile.Property_txtStatus, String.valueOf(data.getTxtStatus()));
                    item1.put(dtclsLogReceiverDetail_mobile.Property_txtNIK, String.valueOf(data.getTxtNIK()));
                    item1.put(dtclsLogReceiverDetail_mobile.Property_txtUSerName, String.valueOf(data.getTxtUSerName()));
                    item1.put(dtclsLogReceiverDetail_mobile.Property_dtInserted, String.valueOf(data.getDtInserted()));
                    item1.put(dtclsLogReceiverDetail_mobile.Property_dtUpdated, String.valueOf(data.getDtUpdated()));
                    item1.put(dtclsLogReceiverDetail_mobile.Property_intActive, String.valueOf(data.getIntActive()));
                    item1.put(dtclsLogReceiverDetail_mobile.Property_intSubmit, String.valueOf(data.getIntSubmit()));
                    item1.put(dtclsLogReceiverDetail_mobile.Property_intSync, String.valueOf(data.getIntSync()));
                    item1.put(dtclsLogReceiverDetail_mobile.Property_txtIDFile, String.valueOf(data.getTxtIDFile()));
                    item1.put(dtclsLogReceiverDetail_mobile.Property_txtIdReceiver, String.valueOf(data.getTxtIdReceiver()));
                    item1.put(dtclsLogReceiverDetail_mobile.Property_txtGuidLogin, String.valueOf(data.getTxtGuidLogin()));
                    item1.put(dtclsLogReceiverDetail_mobile.Property_txtIdHeaderNotif, String.valueOf(data.getTxtIdHeaderNotif()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtclsLogReceiverDetail_mobile.Property_ListOfclsLogReceiverDetail_mobile, new JSONArray(itemsListJquey));
            }

        } catch (Exception ex) {

        }
        resJson.put(Property_intResult, getIntResult());
        resJson.put(Property_txtDescription, getTxtDescription());
        resJson.put(Property_txtMessage, getTxtMessage());
        resJson.put(Property_txtMethod, getTxtMethod());
        resJson.put(Property_txtValue, getTxtValue());
        resJson.put(Property_txtSessionLoginId, get_txtSessionLoginId());
        resJson.put(Property_txtUserId, get_txtUserId());
        resJson.put(Property_txtVersionApp, get_txtVersionApp());
        return resJson;
    }

    public JSONObject generateJsonReqDownloadAllData() throws JSONException {
        JSONObject resJson = new JSONObject();
        Collection<JSONObject> itemsListJquey = new ArrayList<JSONObject>();
        try {

            if(this.getDttSubTypeActivityData()!=null){
                if(this.getDttSubTypeActivityData().getBoolValid().equals("1")){
                    tSubTypeActivityData dtConfig = new tSubTypeActivityData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttSubTypeActivityData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftSubTypeActivityData, item1);
                }
            }

            if(this.getDttKategoryPlanogramMobileData()!=null){
                if(this.getDttKategoryPlanogramMobileData().getBoolValid().equals("1")){
                    tKategoryPlanogramMobileData dtConfig = new tKategoryPlanogramMobileData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttKategoryPlanogramMobileData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftKategoryPlanogramMobileData, item1);
                }
            }

            if(this.getDtmCategoryKoordinasiOutletData()!=null){
                if(this.getDtmCategoryKoordinasiOutletData().getBoolValid().equals("1")){
                    mCategoryKoordinasiOutletData dtConfig = new mCategoryKoordinasiOutletData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDtmCategoryKoordinasiOutletData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfmCategoryKoordinasiOutlet, item1);
                }
            }

            if(this.getDttKemasanRusakHeaderData()!=null){
                if(this.getDttKemasanRusakHeaderData().getBoolValid().equals("1")){
                    tKemasanRusakHeaderData dtConfig = new tKemasanRusakHeaderData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttKemasanRusakHeaderData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftKemasanRusakHeaderData, item1);
                }
            }

            if(this.getDttTidakSesuaiPesananHeaderData()!=null){
                if(this.getDttTidakSesuaiPesananHeaderData().getBoolValid().equals("1")){
                    tTidakSesuaiPesananHeaderData dtConfig = new tTidakSesuaiPesananHeaderData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttTidakSesuaiPesananHeaderData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftTidakSesuaiPesananHeaderData, item1);
                }
            }

            if(this.getDtmEmployeeBranchData()!=null){
                if(this.getDtmEmployeeBranchData().getBoolValid().equals("1")){
                    mEmployeeBranchData dtConfig = new mEmployeeBranchData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDtmEmployeeBranchData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfEmployeeBranchData, item1);
                }
            }

            if(this.getDtmTypeLeaveMobileData()!=null){
                if(this.getDtmTypeLeaveMobileData().getBoolValid().equals("1")){
                    mTypeLeaveMobileData dtConfig = new mTypeLeaveMobileData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDtmTypeLeaveMobileData().getBoolValid()));
                    resJson.put(dtConfig.PropertyListOfmTypeLeaveMobileData, item1);
                }
            }

            if(this.getDtmEmployeeSalesProductData()!=null){
                if(this.getDtmEmployeeSalesProductData().getBoolValid().equals("1")){
                    mEmployeeSalesProductData dtConfig = new mEmployeeSalesProductData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDtmEmployeeSalesProductData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfmEmployeeSalesProductData, item1);
                }
            }

            if(this.getDtmProductBrandHeaderData()!=null){
                if(this.getDtmProductBrandHeaderData().getBoolValid().equals("1")){
                    mProductBrandHeaderData dtConfig = new mProductBrandHeaderData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDtmProductBrandHeaderData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfmProductBrandHeader, item1);
                }
            }

            if(this.getDtmCategoryVisitPlanData()!=null){
                if(this.getDtmCategoryVisitPlanData().getBoolValid().equals("1")){
                    mCategoryVisitPlanData dtConfig = new mCategoryVisitPlanData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDtmCategoryVisitPlanData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfmCategoryVisitPlanData, item1);
                }
            }

            if(this.getDtmEmployeeAreaData()!=null){
                if(this.getDtmEmployeeAreaData().getBoolValid().equals("1")){
                    mEmployeeAreaData dtConfig = new mEmployeeAreaData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDtmEmployeeAreaData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfmEmployeeAreaData, item1);
                }
            }

            if(this.getDtmProductCompetitorData()!=null){
                if(this.getDtmProductCompetitorData().getBoolValid().equals("1")){
                    mProductCompetitorData dtConfig = new mProductCompetitorData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDtmProductCompetitorData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfmProductCompetitor, item1);
                }
            }

            if(this.getDtmProductSPGData()!=null){
                if(this.getDtmProductSPGData().getBoolValid().equals("1")){
                    mProductSPGData dtConfig = new mProductSPGData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDtmProductSPGData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfmProductSPGData, item1);
                }
            }

            if(this.getDtmProductPICData()!=null){
                if(this.getDtmProductPICData().getBoolValid().equals("1")){
                    mProductPICData dtConfig = new mProductPICData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDtmProductPICData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfmProductPICData, item1);
                }
            }

            if(this.getDtmTypeSubmissionMobile()!=null){
                if(this.getDtmTypeSubmissionMobile().getBoolValid().equals("1")){
                    mTypeSubmissionMobile dtConfig = new mTypeSubmissionMobile();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDtmTypeSubmissionMobile().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfmTypeSubmissionMobile, item1);
                }
            }

            if(this.getDttVisitPlanRealisasiData()!=null){
                if(this.getDttVisitPlanRealisasiData().getBoolValid().equals("1")){
                    tVisitPlanRealisasiData dtConfig = new tVisitPlanRealisasiData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttVisitPlanRealisasiData().getBoolValid()));
                    resJson.put(dtConfig.Property_listOftVisitPlanRealisasiData, item1);
                }
            }

            if(this.getDttAttendanceUserData()!=null){
                if(this.getDttAttendanceUserData().getBoolValid().equals("1")){
                    tAttendanceUserData dtConfig = new tAttendanceUserData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttAttendanceUserData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftAttendanceUser, item1);
                }
            }

            if(this.getDttSalesProductHeaderData()!=null){
                if(this.getDttSalesProductHeaderData().getBoolValid().equals("1")){
                    tSalesProductHeaderData dtConfig = new tSalesProductHeaderData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttSalesProductHeaderData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftSalesProductHeaderData, item1);
                }
            }

            if(this.getDttPurchaseOrderHeaderData()!=null){
                if(this.getDttPurchaseOrderHeaderData().getBoolValid().equals("1")){
                    tPurchaseOrderHeaderData dtConfig = new tPurchaseOrderHeaderData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttPurchaseOrderHeaderData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftPurchaseOrderHeaderData, item1);
                }
            }

            if(this.getDttStockInHandHeaderData()!=null){
                if(this.getDttStockInHandHeaderData().getBoolValid().equals("1")){
                    tStockInHandHeaderData dtConfig = new tStockInHandHeaderData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttStockInHandHeaderData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftSalesProductHeaderData, item1);
                }
            }

            if(this.getDtmParentData()!=null){
                if(this.getDtmParentData().getBoolValid().equals("1")){
                    mParentData dtConfig = new mParentData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDtmParentData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfmParentData, item1);
                }
            }

            if (this.getDtmTypePOPStandardData() != null){
                if (this.getDtmTypePOPStandardData().getBoolValid().equals("1")){
                    mTypePOPStandardData dtConfig = new mTypePOPStandardData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDtmTypePOPStandardData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfmTypePOPStandard, item1);
                }
            }
            if(this.getDttHirarkiBIS()!=null){
                if(this.getDttHirarkiBIS().getBoolValid().equals("1")){
                    tHirarkiBIS dtConfig = new tHirarkiBIS();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttHirarkiBIS().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftHirarkiBIS, item1);
                }
            }

            if(this.getDttSalesProductQuantityHeaderData()!=null){
                if(this.getDttSalesProductQuantityHeaderData().getBoolValid().equals("1")){
                    tSalesProductQuantityHeaderData dtConfig = new tSalesProductQuantityHeaderData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttSalesProductQuantityHeaderData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftSalesProductHeaderData, item1);
                }
            }

            if(this.getDttOverStockHeaderData()!=null){
                if(this.getDttOverStockHeaderData().getBoolValid().equals("1")){
                    tOverStockHeaderData dtConfig = new tOverStockHeaderData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttOverStockHeaderData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftOverStockHeaderData, item1);
                }
            }

            if(this.getDttrackingLocationData()!=null){
                if(this.getDttrackingLocationData().getBoolValid().equals("1")){
                    trackingLocationData dtConfig = new trackingLocationData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttrackingLocationData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftrackingLocation, item1);
                }
            }

            if(this.getDtKoordinasiOutletData()!=null){
                if(this.getDtKoordinasiOutletData().getBoolValid().equals("1")){
                    KoordinasiOutletData dtConfig = new KoordinasiOutletData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDtKoordinasiOutletData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfKoordinasiOutlet, item1);
                }
            }

            if(this.getDttPlanogramMobileData()!=null){
                if(this.getDttPlanogramMobileData().getBoolValid().equals("1")){
                    tPlanogramMobileData dtConfig = new tPlanogramMobileData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttPlanogramMobileData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftPlanogramMobileData, item1);
                }
            }

            if(this.getDttActivityData()!=null){
                if(this.getDttActivityData().getBoolValid().equals("1")){
                    tActivityData dtConfig = new tActivityData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttActivityData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfTActivity, item1);
                }
            }

            if(this.getDttActivityMobileData()!=null){
                if(this.getDttActivityMobileData().getBoolValid().equals("1")){
                    tActivityMobileData dtConfig = new tActivityMobileData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttActivityMobileData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOfTActivityMobile, item1);
                }
            }
            if(this.getDttCustomerBasedMobileHeaderData()!=null){
                if(this.getDttCustomerBasedMobileHeaderData().getBoolValid().equals("1")){
                    tCustomerBasedMobileHeaderData dtConfig = new tCustomerBasedMobileHeaderData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttCustomerBasedMobileHeaderData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftCustomerBasedMobileHeaderData, item1);
                }
            }
            if(this.getDttAbsenUserData()!=null){
                if(this.getDttAbsenUserData().getBoolValid().equals("1")){
                    tAbsenUserData dtConfig = new tAbsenUserData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttAbsenUserData().getBoolValid()));
                    resJson.put(dtConfig.Property_ListOftAbsenUser, item1);
                }
            }
            if(this.getDttLeaveMobileData()!=null){
                if(this.getDttLeaveMobileData().getBoolValid().equals("1")){
                    tLeaveMobileData dtConfig = new tLeaveMobileData();
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_boolValid, String.valueOf(this.getDttLeaveMobileData().getBoolValid()));
                    resJson.put(dtConfig.PropertyListOftLeaveMobileData, item1);
                }
            }

        } catch (Exception ex) {

        }
        resJson.put(Property_intResult, getIntResult());
        resJson.put(Property_txtBranchCode, get_txtBranchCode());
        resJson.put(Property_dtDate, get_dtDate());
        resJson.put(Property_txtDescription, getTxtDescription());
        resJson.put(Property_txtMessage, getTxtMessage());
        resJson.put(Property_txtMethod, getTxtMethod());
        resJson.put(Property_txtValue, getTxtValue());
        resJson.put(Property_txtSessionLoginId, get_txtSessionLoginId());
        resJson.put(Property_txtUserId, get_txtUserId());
        resJson.put(Property_intRoleId, get_intRoleId());
        resJson.put(Property_txtEmpId, get_txtEmpId());
        resJson.put(Property_txtVersionApp, get_txtVersionApp());
        return resJson;
    }

    public JSONObject GenerateJsontSalesProductDetailData(tSalesProductDetailData data) throws JSONException {
        JSONObject item1 = new JSONObject();
        item1.put(data.Property_dtDate, String.valueOf(data.get_dtDate()));
        item1.put(data.Property_intActive, String.valueOf(data.get_intActive()));
        item1.put(data.Property_intId, String.valueOf(data.get_intId()));
        item1.put(data.Property_intPrice, String.valueOf(data.get_intPrice()));
        item1.put(data.Property_intQty, String.valueOf(data.get_intQty()));
        item1.put(data.Property_intTotal, String.valueOf(data.get_intTotal()));
        item1.put(data.Property_txtCodeProduct, String.valueOf(data.get_txtCodeProduct()));
        item1.put(data.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
        item1.put(data.Property_txtNameProduct, String.valueOf(data.get_txtNameProduct()));
        item1.put(data.Property_txtNIK, String.valueOf(data.get_txtNIK()));
        item1.put(data.Property_txtNoSo, String.valueOf(data.get_txtNoSo()));
        return item1;
    }

    public JSONObject GenerateJsontSalesProductHeaderData(tSalesProductHeaderData data) throws JSONException {
        JSONObject item1 = new JSONObject();
        item1.put(data.Property_intId, String.valueOf(data.get_intId()));
        item1.put(data.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
        item1.put(data.Property_txtNIK, String.valueOf(data.get_txtNIK()));
        item1.put(data.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
        item1.put(data.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
        item1.put(data.Property_txtDate, String.valueOf(data.get_dtDate()));
        item1.put(data.Property_intIdAbsenUser, String.valueOf(data.get_intIdAbsenUser()));
        item1.put(data.Property_intSumAmount, String.valueOf(data.get_intSumAmount()));
        item1.put(data.Property_intSumItem, String.valueOf(data.get_intSumItem()));
        item1.put(data.Property_OutletCode, String.valueOf(data.get_OutletCode()));
        item1.put(data.Property_OutletName, String.valueOf(data.get_OutletName()));
        item1.put(data.Property_UserId, String.valueOf(data.get_UserId()));
//        item1.put(data.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
        return item1;
    }

    public String getIntResult() {
        return intResult;
    }

    public void setIntResult(String intResult) {
        this.intResult = intResult;
    }

    public String getTxtMessage() {
        return txtMessage;
    }

    public void setTxtMessage(String txtMessage) {
        this.txtMessage = txtMessage;
    }

    public String getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(String txtDescription) {
        this.txtDescription = txtDescription;
    }

    public List<mconfigData> getListDatamConfig() {
        return ListDatamConfig;
    }

    public void setListDatamConfig(List<mconfigData> listDatamConfig) {
        ListDatamConfig = listDatamConfig;
    }

    public dataJson() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String intResult;
    private String txtMessage;
    private String txtMethod;
    private String txtValue;
    private String txtDescription;
    private List<mconfigData> ListDatamConfig;
    private List<tUserLoginData> ListDatatUserLogin;
    private List<tDeviceInfoUserData> ListDatatDeviceInfoUser;
    private List<mMenuData> ListOfmMenuData;
    private List<tSalesProductDetailData> ListOftSalesProductDetailData;
    private List<tSalesProductHeaderData> ListOftSalesProductHeaderData;
    private List<tPurchaseOrderDetailData> ListOftPurchaseOrderDetailData;
    private List<tSalesProductQuantityDetailData> ListOftSalesProductQuantityDetailData;

    public List<tKemasanRusakDetailData> getListOftKemasanRusakDetailData() {
        return ListOftKemasanRusakDetailData;
    }

    public void setListOftKemasanRusakDetailData(List<tKemasanRusakDetailData> listOftKemasanRusakDetailData) {
        ListOftKemasanRusakDetailData = listOftKemasanRusakDetailData;
    }

    private List<tKemasanRusakDetailData> ListOftKemasanRusakDetailData;

    public List<tOverStockDetailData> getListOftOverStockDetailData() {
        return ListOftOverStockDetailData;
    }

    public void setListOftOverStockDetailData(List<tOverStockDetailData> listOftOverStockDetailData) {
        ListOftOverStockDetailData = listOftOverStockDetailData;
    }

    private List<tOverStockDetailData> ListOftOverStockDetailData;
    private List<tSalesProductQuantityImageData> ListOftSalesProductQuantityImageData;

    public List<tKemasanRusakImageData> getListOftKemasanRusakImageData() {
        return ListOftKemasanRusakImageData;
    }

    public void setListOftKemasanRusakImageData(List<tKemasanRusakImageData> listOftKemasanRusakImageData) {
        ListOftKemasanRusakImageData = listOftKemasanRusakImageData;
    }

    public List<tPOPStandardHeaderData> getListOftPOPStandarHeaderdData() {
        return ListOftPOPStandarHeaderdData;
    }

    public void setListOftPOPStandarHeaderdData(List<tPOPStandardHeaderData> listOftPOPStandarHeaderdData) {
        ListOftPOPStandarHeaderdData = listOftPOPStandarHeaderdData;
    }

    public List<tPOPStandardDetailData> getListOftPOPStandardDetailData() {
        return ListOftPOPStandardDetailData;
    }

    public void setListOftPOPStandardDetailData(List<tPOPStandardDetailData> listOftPOPStandardDetailData) {
        ListOftPOPStandardDetailData = listOftPOPStandardDetailData;
    }

    private List<tPOPStandardHeaderData> ListOftPOPStandarHeaderdData;
    private List<tPOPStandardDetailData> ListOftPOPStandardDetailData;
    private List<tKemasanRusakImageData> ListOftKemasanRusakImageData;
    private List<tPlanogramImageData> ListOftPlanogramImageData;
    private List<tPurchaseOrderHeaderData> ListOftPurchaseOrderHeaderData;
    private List<tJawabanUserData> ListOftJawabanUserData;
    private List<tJawabanUserHeaderData> ListOftJawabanUserHeaderData;
    private List<tSalesProductQuantityHeaderData> ListOftSalesProductQuantityData;

    public List<tKemasanRusakHeaderData> getListOftKemasanRusakHeaderData() {
        return ListOftKemasanRusakHeaderData;
    }

    public void setListOftKemasanRusakHeaderData(List<tKemasanRusakHeaderData> listOftKemasanRusakHeaderData) {
        ListOftKemasanRusakHeaderData = listOftKemasanRusakHeaderData;
    }

    private List<tKemasanRusakHeaderData> ListOftKemasanRusakHeaderData;

    public List<tOverStockHeaderData> getListOftOverStockHeaderData() {
        return ListOftOverStockHeaderData;
    }

    public void setListOftOverStockHeaderData(List<tOverStockHeaderData> listOftOverStockHeaderData) {
        ListOftOverStockHeaderData = listOftOverStockHeaderData;
    }

    private List<tOverStockHeaderData> ListOftOverStockHeaderData;
    private List<tPlanogramMobileData> ListOftPlanogramMobileData;
    private List<tCustomerBasedMobileHeaderData> ListOftCustomerBasedMobileHeaderData;
    private List<tCustomerBasedMobileDetailData> ListOftCustomerBasedMobileDetailData;
    private List<tCustomerBasedMobileDetailProductData> ListOftCustomerBasedMobileDetailProductData;
    private List<tVisitPlanHeader_MobileData> ListOftVisitPlanHeader_MobileData;
    private List<tVisitPlanRealisasiData> ListOftVisitPlanRealisasiData;
    private List<trackingLocationData> ListOfTrackingLocationData;
    private List<KoordinasiOutletData> ListOfKoordinasiOutletData;
    private List<KoordinasiOutletImageData> ListOfKoordinasiOutletImageData;
    private List<tTidakSesuaiPesananHeaderData> ListOftTidakSesuaiPesananHeaderData;

    public List<tTidakSesuaiPesananHeaderData> getListOftTidakSesuaiPesananHeaderData() {
        return ListOftTidakSesuaiPesananHeaderData;
    }

    public void setListOftTidakSesuaiPesananHeaderData(List<tTidakSesuaiPesananHeaderData> listOftTidakSesuaiPesananHeaderData) {
        ListOftTidakSesuaiPesananHeaderData = listOftTidakSesuaiPesananHeaderData;
    }

    public List<tTidakSesuaiPesananImageData> getListOftTidakSesuaiPesananImageData() {
        return ListOftTidakSesuaiPesananImageData;
    }

    public void setListOftTidakSesuaiPesananImageData(List<tTidakSesuaiPesananImageData> listOftTidakSesuaiPesananImageData) {
        ListOftTidakSesuaiPesananImageData = listOftTidakSesuaiPesananImageData;
    }

    private List<tTidakSesuaiPesananImageData> ListOftTidakSesuaiPesananImageData;


    public List<tJawabanUserHeaderData> getListOftJawabanUserHeaderData() {
        return ListOftJawabanUserHeaderData;
    }

    public void setListOftJawabanUserHeaderData(List<tJawabanUserHeaderData> listOftJawabanUserHeaderData) {
        ListOftJawabanUserHeaderData = listOftJawabanUserHeaderData;
    }
    public List<tStockInHandHeaderData> getListOftStockInHandHeaderData() {
        return ListOftStockInHandHeaderData;
    }

    public void setListOftStockInHandHeaderData(List<tStockInHandHeaderData> listOftStockInHandHeaderData) {
        ListOftStockInHandHeaderData = listOftStockInHandHeaderData;
    }

    public List<tStockInHandDetailData> getListOftStockInHandDetailData() {
        return ListOftStockInHandDetailData;
    }

    public void setListOftStockInHandDetailData(List<tStockInHandDetailData> listOftStockInHandDetailData) {
        ListOftStockInHandDetailData = listOftStockInHandDetailData;
    }

    private List<tStockInHandDetailData> ListOftStockInHandDetailData;
    private List<tStockInHandHeaderData> ListOftStockInHandHeaderData;

    public List<tPlanogramMobileData> getListOftPlanogramMobileData() {
        return ListOftPlanogramMobileData;
    }

    public void setListOftPlanogramMobileData(List<tPlanogramMobileData> listOftPlanogramMobileData) {
        ListOftPlanogramMobileData = listOftPlanogramMobileData;
    }


    public List<tPlanogramImageData> getListOftPlanogramImageData() {
        return ListOftPlanogramImageData;
    }

    public void setListOftPlanogramImageData(List<tPlanogramImageData> listOftPlanogramImageData) {
        ListOftPlanogramImageData = listOftPlanogramImageData;
    }

    public List<clsLogReceiverHeader_mobile> getListOfLogReceiverHeader_mobile() {
        return ListOfLogReceiverHeader_mobile;
    }

    public void setListOfLogReceiverHeader_mobile(List<clsLogReceiverHeader_mobile> listOfLogReceiverHeader_mobile) {
        ListOfLogReceiverHeader_mobile = listOfLogReceiverHeader_mobile;
    }
    public synchronized List<tLogErrorData> getListOftErrorLogData() {
        return ListOftErrorLogData;
    }

    public synchronized void setListOftErrorLogData(
            List<tLogErrorData> listOftErrorLogData) {
        ListOftErrorLogData = listOftErrorLogData;
    }

    public List<clsLogReceiverDetail_mobile> getListOfLogReceiverDetail_mobile() {
        return ListOfLogReceiverDetail_mobile;
    }

    public void setListOfLogReceiverDetail_mobile(List<clsLogReceiverDetail_mobile> listOfLogReceiverDetail_mobile) {
        ListOfLogReceiverDetail_mobile = listOfLogReceiverDetail_mobile;
    }

    private List<clsLogReceiverHeader_mobile> ListOfLogReceiverHeader_mobile;
    private List<clsLogReceiverDetail_mobile> ListOfLogReceiverDetail_mobile;

    private List<mEmployeeAreaData> ListOfmEmployeeAreaData;
    private List<mEmployeeBranchData> ListOfmEmployeeBranchData;
    private List<mEmployeeSalesProductData> ListOfmEmployeeSalesProductData;
    private List<mNotificationData> ListOfmNotificationData;
    private List<tLogErrorData> ListOftErrorLogData;
    private List<tAbsenUserData> ListOftAbsenUserData;

    private tVisitPlanRealisasiData dttVisitPlanRealisasiData;
    private tAttendanceUserData dttAttendanceUserData;
    private tSalesProductHeaderData dttSalesProductHeaderData;
    private tPurchaseOrderHeaderData dttPurchaseOrderHeaderData;
    private tStockInHandHeaderData dttStockInHandHeaderData;

    public tVisitPlanRealisasiData getDttVisitPlanRealisasiData() {
        return dttVisitPlanRealisasiData;
    }

    public void setDttVisitPlanRealisasiData(tVisitPlanRealisasiData dttVisitPlanRealisasiData) {
        this.dttVisitPlanRealisasiData = dttVisitPlanRealisasiData;
    }

    public tAttendanceUserData getDttAttendanceUserData() {
        return dttAttendanceUserData;
    }

    public void setDttAttendanceUserData(tAttendanceUserData dttAttendanceUserData) {
        this.dttAttendanceUserData = dttAttendanceUserData;
    }

    public tSalesProductHeaderData getDttSalesProductHeaderData() {
        return dttSalesProductHeaderData;
    }

    public void setDttSalesProductHeaderData(tSalesProductHeaderData dttSalesProductHeaderData) {
        this.dttSalesProductHeaderData = dttSalesProductHeaderData;
    }

    public tPurchaseOrderHeaderData getDttPurchaseOrderHeaderData() {
        return dttPurchaseOrderHeaderData;
    }

    public void setDttPurchaseOrderHeaderData(tPurchaseOrderHeaderData dttPurchaseOrderHeaderData) {
        this.dttPurchaseOrderHeaderData = dttPurchaseOrderHeaderData;
    }

    public tStockInHandHeaderData getDttStockInHandHeaderData() {
        return dttStockInHandHeaderData;
    }

    public void setDttStockInHandHeaderData(tStockInHandHeaderData dttStockInHandHeaderData) {
        this.dttStockInHandHeaderData = dttStockInHandHeaderData;
    }

    public mParentData getDtmParentData() {
        return dtmParentData;
    }

    public void setDtmParentData(mParentData dtmParentData) {
        this.dtmParentData = dtmParentData;
    }

    public tHirarkiBIS getDttHirarkiBIS() {
        return dttHirarkiBIS;
    }

    public void setDttHirarkiBIS(tHirarkiBIS dttHirarkiBIS) {
        this.dttHirarkiBIS = dttHirarkiBIS;
    }

    public tSalesProductQuantityHeaderData getDttSalesProductQuantityHeaderData() {
        return dttSalesProductQuantityHeaderData;
    }

    public void setDttSalesProductQuantityHeaderData(tSalesProductQuantityHeaderData dttSalesProductQuantityHeaderData) {
        this.dttSalesProductQuantityHeaderData = dttSalesProductQuantityHeaderData;
    }

    public tOverStockHeaderData getDttOverStockHeaderData() {
        return dttOverStockHeaderData;
    }

    public void setDttOverStockHeaderData(tOverStockHeaderData dttOverStockHeaderData) {
        this.dttOverStockHeaderData = dttOverStockHeaderData;
    }

    public trackingLocationData getDttrackingLocationData() {
        return dttrackingLocationData;
    }

    public void setDttrackingLocationData(trackingLocationData dttrackingLocationData) {
        this.dttrackingLocationData = dttrackingLocationData;
    }

    public KoordinasiOutletData getDtKoordinasiOutletData() {
        return dtKoordinasiOutletData;
    }

    public void setDtKoordinasiOutletData(KoordinasiOutletData dtKoordinasiOutletData) {
        this.dtKoordinasiOutletData = dtKoordinasiOutletData;
    }

    public tPlanogramMobileData getDttPlanogramMobileData() {
        return dttPlanogramMobileData;
    }

    public void setDttPlanogramMobileData(tPlanogramMobileData dttPlanogramMobileData) {
        this.dttPlanogramMobileData = dttPlanogramMobileData;
    }

    public tActivityData getDttActivityData() {
        return dttActivityData;
    }

    public void setDttActivityData(tActivityData dttActivityData) {
        this.dttActivityData = dttActivityData;
    }

    public tActivityMobileData getDttActivityMobileData() {
        return dttActivityMobileData;
    }

    public void setDttActivityMobileData(tActivityMobileData dttActivityMobileData) {
        this.dttActivityMobileData = dttActivityMobileData;
    }

    public tCustomerBasedMobileHeaderData getDttCustomerBasedMobileHeaderData() {
        return dttCustomerBasedMobileHeaderData;
    }

    public void setDttCustomerBasedMobileHeaderData(tCustomerBasedMobileHeaderData dttCustomerBasedMobileHeaderData) {
        this.dttCustomerBasedMobileHeaderData = dttCustomerBasedMobileHeaderData;
    }

    public tAbsenUserData getDttAbsenUserData() {
        return dttAbsenUserData;
    }

    public void setDttAbsenUserData(tAbsenUserData dttAbsenUserData) {
        this.dttAbsenUserData = dttAbsenUserData;
    }

    public tLeaveMobileData getDttLeaveMobileData() {
        return dttLeaveMobileData;
    }

    public void setDttLeaveMobileData(tLeaveMobileData dttLeaveMobileData) {
        this.dttLeaveMobileData = dttLeaveMobileData;
    }

    private mParentData dtmParentData;
    private tHirarkiBIS dttHirarkiBIS;
    private tSalesProductQuantityHeaderData dttSalesProductQuantityHeaderData;
    private tOverStockHeaderData dttOverStockHeaderData;
    private trackingLocationData dttrackingLocationData;
    private KoordinasiOutletData dtKoordinasiOutletData;
    private tPlanogramMobileData dttPlanogramMobileData;
    private tActivityData dttActivityData;
    private tActivityMobileData dttActivityMobileData;
    private tCustomerBasedMobileHeaderData dttCustomerBasedMobileHeaderData;
    private tAbsenUserData dttAbsenUserData;
    private tLeaveMobileData dttLeaveMobileData;

    public tSubTypeActivityData getDttSubTypeActivityData() {
        return dttSubTypeActivityData;
    }

    public void setDttSubTypeActivityData(tSubTypeActivityData dttSubTypeActivityData) {
        this.dttSubTypeActivityData = dttSubTypeActivityData;
    }

    private tSubTypeActivityData dttSubTypeActivityData;

    public tKategoryPlanogramMobileData getDttKategoryPlanogramMobileData() {
        return dttKategoryPlanogramMobileData;
    }

    public void setDttKategoryPlanogramMobileData(tKategoryPlanogramMobileData dttKategoryPlanogramMobileData) {
        this.dttKategoryPlanogramMobileData = dttKategoryPlanogramMobileData;
    }

    private tKategoryPlanogramMobileData dttKategoryPlanogramMobileData;

    public mEmployeeBranchData getDtmEmployeeBranchData() {
        return dtmEmployeeBranchData;
    }

    public void setDtmEmployeeBranchData(mEmployeeBranchData dtmEmployeeBranchData) {
        this.dtmEmployeeBranchData = dtmEmployeeBranchData;
    }

    private mEmployeeBranchData dtmEmployeeBranchData;

    public mTypeLeaveMobileData getDtmTypeLeaveMobileData() {
        return dtmTypeLeaveMobileData;
    }

    public void setDtmTypeLeaveMobileData(mTypeLeaveMobileData dtmTypeLeaveMobileData) {
        this.dtmTypeLeaveMobileData = dtmTypeLeaveMobileData;
    }

    private mTypeLeaveMobileData dtmTypeLeaveMobileData;

    public mEmployeeSalesProductData getDtmEmployeeSalesProductData() {
        return dtmEmployeeSalesProductData;
    }

    public void setDtmEmployeeSalesProductData(mEmployeeSalesProductData dtmEmployeeSalesProductData) {
        this.dtmEmployeeSalesProductData = dtmEmployeeSalesProductData;
    }

    private mEmployeeSalesProductData dtmEmployeeSalesProductData;

    public mProductBrandHeaderData getDtmProductBrandHeaderData() {
        return dtmProductBrandHeaderData;
    }

    public void setDtmProductBrandHeaderData(mProductBrandHeaderData dtmProductBrandHeaderData) {
        this.dtmProductBrandHeaderData = dtmProductBrandHeaderData;
    }

    private mProductBrandHeaderData dtmProductBrandHeaderData;

    public mCategoryVisitPlanData getDtmCategoryVisitPlanData() {
        return dtmCategoryVisitPlanData;
    }

    public void setDtmCategoryVisitPlanData(mCategoryVisitPlanData dtmCategoryVisitPlanData) {
        this.dtmCategoryVisitPlanData = dtmCategoryVisitPlanData;
    }

    private mCategoryVisitPlanData dtmCategoryVisitPlanData;

    public mEmployeeAreaData getDtmEmployeeAreaData() {
        return dtmEmployeeAreaData;
    }

    public void setDtmEmployeeAreaData(mEmployeeAreaData dtmEmployeeAreaData) {
        this.dtmEmployeeAreaData = dtmEmployeeAreaData;
    }

    private mEmployeeAreaData dtmEmployeeAreaData;

    public mProductCompetitorData getDtmProductCompetitorData() {
        return dtmProductCompetitorData;
    }

    public void setDtmProductCompetitorData(mProductCompetitorData dtmProductCompetitorData) {
        this.dtmProductCompetitorData = dtmProductCompetitorData;
    }

    private mProductCompetitorData dtmProductCompetitorData;

    public mProductSPGData getDtmProductSPGData() {
        return dtmProductSPGData;
    }

    public void setDtmProductSPGData(mProductSPGData dtmProductSPGData) {
        this.dtmProductSPGData = dtmProductSPGData;
    }

    private mProductSPGData dtmProductSPGData;

    public mProductPICData getDtmProductPICData() {
        return dtmProductPICData;
    }

    public void setDtmProductPICData(mProductPICData dtmProductPICData) {
        this.dtmProductPICData = dtmProductPICData;
    }

    private mProductPICData dtmProductPICData;

    public mTypeSubmissionMobile getDtmTypeSubmissionMobile() {
        return dtmTypeSubmissionMobile;
    }

    public void setDtmTypeSubmissionMobile(mTypeSubmissionMobile dtmTypeSubmissionMobile) {
        this.dtmTypeSubmissionMobile = dtmTypeSubmissionMobile;
    }

    private mTypeSubmissionMobile dtmTypeSubmissionMobile;

    public mCategoryKoordinasiOutletData getDtmCategoryKoordinasiOutletData() {
        return dtmCategoryKoordinasiOutletData;
    }

    public void setDtmCategoryKoordinasiOutletData(mCategoryKoordinasiOutletData dtmCategoryKoordinasiOutletData) {
        this.dtmCategoryKoordinasiOutletData = dtmCategoryKoordinasiOutletData;
    }

    private mCategoryKoordinasiOutletData dtmCategoryKoordinasiOutletData;

    public tKemasanRusakHeaderData getDttKemasanRusakHeaderData() {
        return dttKemasanRusakHeaderData;
    }

    public void setDttKemasanRusakHeaderData(tKemasanRusakHeaderData dttKemasanRusakHeaderData) {
        this.dttKemasanRusakHeaderData = dttKemasanRusakHeaderData;
    }

    public tTidakSesuaiPesananHeaderData getDttTidakSesuaiPesananHeaderData() {
        return dttTidakSesuaiPesananHeaderData;
    }

    public void setDttTidakSesuaiPesananHeaderData(tTidakSesuaiPesananHeaderData dttTidakSesuaiPesananHeaderData) {
        this.dttTidakSesuaiPesananHeaderData = dttTidakSesuaiPesananHeaderData;
    }

    private tKemasanRusakHeaderData dttKemasanRusakHeaderData;
    private tTidakSesuaiPesananHeaderData dttTidakSesuaiPesananHeaderData;

    public mTypePOPStandardData getDtmTypePOPStandardData() {
        return dtmTypePOPStandardData;
    }

    public void setDtmTypePOPStandardData(mTypePOPStandardData dtmTypePOPStandardData) {
        this.dtmTypePOPStandardData = dtmTypePOPStandardData;
    }

    private mTypePOPStandardData dtmTypePOPStandardData;

    public List<tAttendanceUserData> getListOftAttendanceUserData() {
        return ListOftAttendanceUserData;
    }

    public void setListOftAttendanceUserData(List<tAttendanceUserData> listOftAttendanceUserData) {
        ListOftAttendanceUserData = listOftAttendanceUserData;
    }

    private List<tAttendanceUserData> ListOftAttendanceUserData;
    private List<mGeolocationOutletSPGData> ListOfmGeolocationOutletSPGData;
    private List<tActivityData> ListOftActivityData;
    private List<tActivityMobileData> ListOftActivityMobileData;
    private List<mProductBrandHeaderData> ListOfmProductBrandHeaderData;
    private List<tLeaveMobileData> ListOftLeaveMobileData;
    private List<mTypeLeaveMobileData> ListOfmTypeLeaveMobileData;
    private List<tEmployeeBRWithLOBData> ListOftEmployeeBRWithLOBData;

    private List<mProductBarcodeData> _ListOfmProductBarcodeData;
    private List<mParentData> ListOfmParentData;
    private List<mKategoriData> ListOfmKategoriData;
    private List<mTypePertanyaanData> ListOfmTypePertanyaanData;
    private List<mPertanyaanData> ListOfmPertanyaanData;
    private List<mListJawabanData> ListofmListJawabanData;

    public synchronized List<mParentData> getListOfmParentData() {
        return ListOfmParentData;
    }

    public synchronized void setListOfmParentData(List<mParentData> listOfmParentData) {
        ListOfmParentData = listOfmParentData;
    }

    public synchronized List<mKategoriData> getListOfmKategoriData() {
        return ListOfmKategoriData;
    }

    public synchronized void setListOfmKategoriData(List<mKategoriData> listOfmKategoriData) {
        ListOfmKategoriData = listOfmKategoriData;
    }

    public synchronized List<mTypePertanyaanData> getListOfmTypePertanyaanData() {
        return ListOfmTypePertanyaanData;
    }

    public synchronized void setListOfmTypePertanyaanData(List<mTypePertanyaanData> listOfmTypePertanyaanData) {
        ListOfmTypePertanyaanData = listOfmTypePertanyaanData;
    }

    public synchronized List<mPertanyaanData> getListOfmPertanyaanData() {
        return ListOfmPertanyaanData;
    }

    public synchronized void setListOfmPertanyaanData(List<mPertanyaanData> listOfmPertanyaanData) {
        ListOfmPertanyaanData = listOfmPertanyaanData;
    }

    public synchronized List<mListJawabanData> getListofmListJawabanData() {
        return ListofmListJawabanData;
    }

    public synchronized void setListofmListJawabanData(List<mListJawabanData> listofmListJawabanData) {
        ListofmListJawabanData = listofmListJawabanData;
    }

    public List<tCustomerBasedMobileHeaderData> get_ListOftCustomerBasedMobileHeaderData() {
        return _ListOftCustomerBasedMobileHeaderData;
    }

    public void set_ListOftCustomerBasedMobileHeaderData(List<tCustomerBasedMobileHeaderData> _ListOftCustomerBasedMobileHeaderData) {
        this._ListOftCustomerBasedMobileHeaderData = _ListOftCustomerBasedMobileHeaderData;
    }

    private List<tCustomerBasedMobileHeaderData> _ListOftCustomerBasedMobileHeaderData;

    public List<mProductBarcodeData> get_ListOfmProductBarcodeData() {
        return _ListOfmProductBarcodeData;
    }

    public void set_ListOfmProductBarcodeData(List<mProductBarcodeData> _ListOfmProductBarcodeData) {
        this._ListOfmProductBarcodeData = _ListOfmProductBarcodeData;
    }

    private String _txtSessionLoginId;

    public String get_txtSessionLoginId() {
        return _txtSessionLoginId;
    }

    public void set_txtSessionLoginId(String _txtSessionLoginId) {
        this._txtSessionLoginId = _txtSessionLoginId;
    }

    private String _txtUserId;

    public String get_txtUserId() {
        return _txtUserId;
    }

    public void set_txtUserId(String _txtUserId) {
        this._txtUserId = _txtUserId;
    }

    public String get_txtVersionApp() {
        return _txtVersionApp;
    }

    public void set_txtVersionApp(String _txtVersionApp) {
        this._txtVersionApp = _txtVersionApp;
    }

    public String _txtVersionApp;

    public String get_intRoleId() {
        return _intRoleId;
    }

    public void set_intRoleId(String _intRoleId) {
        this._intRoleId = _intRoleId;
    }

    private String _intRoleId;

    public String get_txtBranchCode() {
        return _txtBranchCode;
    }

    public void set_txtBranchCode(String _txtBranchCode) {
        this._txtBranchCode = _txtBranchCode;
    }

    private String _txtBranchCode;

    public String get_txtEmpId() {
        return _txtEmpId;
    }

    public void set_txtEmpId(String _txtEmpId) {
        this._txtEmpId = _txtEmpId;
    }

    private String _txtEmpId;

    public String get_dtDate() {
        return _dtDate;
    }

    public void set_dtDate(String _dtDate) {
        this._dtDate = _dtDate;
    }

    private String _dtDate;

    private String Property_txtBranchCode = "txtBranchCode";
    private String Property_dtDate = "dtDate";
    private String Property_txtEmpId = "txtEmpId";
    private String Property_intRoleId = "intRoleId";
    private String Property_txtUserId = "txtUserId";
    private String Property_txtSessionLoginId = "txtSessionLoginId";
    public String Property_intResult = "intResult";
    public String Property_txtMessage = "txtMessage";
    public String Property_txtDescription = "txtDescription";
    public String Property_txtMethod = "txtMethod";
    public String Property_txtValue = "txtValue";
    public String Property_txtVersionApp = "txtVesionApp";

    public List<tCustomerBasedMobileHeaderData> getListOftCustomerBasedMobileHeaderData() {
        return ListOftCustomerBasedMobileHeaderData;
    }

    public void setListOftCustomerBasedMobileHeaderData(List<tCustomerBasedMobileHeaderData> listOftCustomerBasedMobileHeaderData) {
        ListOftCustomerBasedMobileHeaderData = listOftCustomerBasedMobileHeaderData;
    }

    public List<tCustomerBasedMobileDetailData> getListOftCustomerBasedMobileDetailData() {
        return ListOftCustomerBasedMobileDetailData;
    }

    public void setListOftCustomerBasedMobileDetailData(List<tCustomerBasedMobileDetailData> listOftCustomerBasedMobileDetailData) {
        ListOftCustomerBasedMobileDetailData = listOftCustomerBasedMobileDetailData;
    }

    public List<tCustomerBasedMobileDetailProductData> getListOftCustomerBasedMobileDetailProductData() {
        return ListOftCustomerBasedMobileDetailProductData;
    }

    public void setListOftCustomerBasedMobileDetailProductData(List<tCustomerBasedMobileDetailProductData> listOftCustomerBasedMobileDetailProductData) {
        ListOftCustomerBasedMobileDetailProductData = listOftCustomerBasedMobileDetailProductData;
    }
    public List<tVisitPlanHeader_MobileData> getListOftVisitPlanHeader_MobileData(){
        return ListOftVisitPlanHeader_MobileData;
    }
    public void setListOftVisitPlanHeader_MobileData(List<tVisitPlanHeader_MobileData> listOftVisitPlanHeader_MobileData){
        ListOftVisitPlanHeader_MobileData = listOftVisitPlanHeader_MobileData;
    }
    public List<tVisitPlanRealisasiData> getListOftVisitPlanRealisasiData(){
        return ListOftVisitPlanRealisasiData;
    }
    public void setListOftVisitPlanRealisasiData(List<tVisitPlanRealisasiData> listOftVisitPlanRealisasiData){
        ListOftVisitPlanRealisasiData = listOftVisitPlanRealisasiData;
    }
}
