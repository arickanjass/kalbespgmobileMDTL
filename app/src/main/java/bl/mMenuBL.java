package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mEmployeeAreaData;
import library.spgmobile.common.mMenuData;
import library.spgmobile.common.tLeaveMobileData;
import library.spgmobile.common.tVisitPlanRealisasiData;
import library.spgmobile.dal.mCategoryPOPStandardDA;
import library.spgmobile.dal.mEmployeeAreaDA;
import library.spgmobile.dal.mEmployeeBranchDA;
import library.spgmobile.dal.mEmployeeSalesProductDA;
import library.spgmobile.dal.mMenuDA;
import library.spgmobile.dal.mProductBarcodeDA;
import library.spgmobile.dal.mProductBrandHeaderDA;
import library.spgmobile.dal.mProductCompetitorDA;
import library.spgmobile.dal.mProductPICDA;
import library.spgmobile.dal.mProductSPGDA;
import library.spgmobile.dal.mReasonPOPStandardDA;
import library.spgmobile.dal.mTypeLeaveMobileDA;
import library.spgmobile.dal.mTypePOPStandardDA;
import library.spgmobile.dal.mTypeSubmissionMobileDA;
import library.spgmobile.dal.tAbsenUserDA;
import library.spgmobile.dal.tAttendanceUserDA;
import library.spgmobile.dal.tKategoryPlanogramMobileDA;
import library.spgmobile.dal.tLeaveMobileDA;
import library.spgmobile.dal.tSalesProductHeaderDA;
import library.spgmobile.dal.tSubTypeActivityDA;
import library.spgmobile.dal.tVisitPlanRealisasiDA;

public class mMenuBL extends clsMainBL {
    public void SaveData(List<mMenuData> Listdata) {
        SQLiteDatabase db = getDb();
        mMenuDA _mMenuDA = new mMenuDA(db);
        _mMenuDA.DeleteAllDAta(db);
        Long index = Long.valueOf(_mMenuDA.getContactsCount(db) + 1);
        for (mMenuData data : Listdata) {
            data.set_intId(index);
            _mMenuDA.SaveDataMConfig(db, data);
            index += 1;
        }
        db.close();
    }

    public List<mMenuData> GetAllData() {
        SQLiteDatabase db = getDb();
        mMenuDA _mMenuDA = new mMenuDA(db);
        List<mMenuData> dt = _mMenuDA.getAllData(db);
        db.close();
        return dt;
    }

    public mMenuData getMenuDataByMenuName(String menuName) {
        SQLiteDatabase db = getDb();
        mMenuDA _mMenuDA = new mMenuDA(db);
        mMenuData dt = _mMenuDA.getDataByNamaMenu(db, menuName);
        db.close();
        return dt;
    }

    public String getIntParentID(){
        SQLiteDatabase db = getDb();
        mMenuDA _mMenuDA = new mMenuDA(db);
        String intParentID = null;
        intParentID = _mMenuDA.getIntParentID(db);
        db.close();
        return intParentID;
    }

    public mMenuData getMenuDataByMenuName2(String menuName) {
        SQLiteDatabase db = getDb();
        mMenuDA _mMenuDA = new mMenuDA(db);
        mMenuData dt = _mMenuDA.getDataByNamaMenu2(db, menuName);
        db.close();
        return dt;
    }

    public List<mMenuData> getDatabyParentId(String id) {
        SQLiteDatabase db = getDb();
        mMenuDA _mMenuDA = new mMenuDA(db);
        tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(db);
        List<mMenuData> listData = _mMenuDA.getDatabyParentId(db, id);
        List<mMenuData> tmpData = new ArrayList<>();
        List<tLeaveMobileData> listDataLeave = new tLeaveMobileBL().getData("");
        for (mMenuData data : listData) {
            if (listDataLeave.size() == 0 && data.get_TxtDescription().contains("mnReporting")) {
                if (_tSalesProductHeaderDA.getContactsCount(db) > 0) {
                    tmpData.add(data);
                }
            } else if (data.get_TxtDescription().contains("mnVisitPlanMobile")
                    || data.get_TxtDescription().contains("mnPushDataSPG")
                    || data.get_TxtDescription().contains("mnGeoTagging")
                    || data.get_TxtDescription().contains("mnAbsenSPG")
                    || data.get_TxtDescription().contains("mnAbsenFPE")
                    || data.get_TxtDescription().contains("mnAbsenTL")
                    ||data.get_TxtDescription().contains("mnLeaveSPG")
                    ||data.get_TxtDescription().contains("mnLeaveMD")
                    ||data.get_TxtDescription().contains("mnLeaveTL")) {
                mEmployeeAreaDA _mEmployeeAreaDA = new mEmployeeAreaDA(db);
                mEmployeeBranchDA _mEmployeeBranchDA = new mEmployeeBranchDA(db);
                mProductBarcodeDA _mProductBarcodeDA = new mProductBarcodeDA(db);
                tLeaveMobileDA _tLeaveMobileDA = new tLeaveMobileDA(db);
                mEmployeeSalesProductDA _mEmployeeSalesProductDA = new mEmployeeSalesProductDA(db);

                //Untuk visitplan harus pengecekan master data
                if (data.get_TxtDescription().contains("mnVisitPlanMobile")||data.get_TxtDescription().contains("mnPushDataSPG")||data.get_TxtDescription().contains("mnLeaveMD")||data.get_TxtDescription().contains("mnGeoTagging")) {
                    _mEmployeeBranchDA = new mEmployeeBranchDA(db);
                    _mEmployeeAreaDA = new mEmployeeAreaDA(db);
                    _mEmployeeSalesProductDA = new mEmployeeSalesProductDA(db);
                    mProductSPGDA _mProductSPGDA = new mProductSPGDA(db);
                    mProductPICDA _mProductPICDA = new mProductPICDA(db);
                    mProductCompetitorDA _mProductCompetitorDA = new mProductCompetitorDA(db);
                    mTypeSubmissionMobileDA _mTypeSubmissionMobileDA = new mTypeSubmissionMobileDA(db);
                    mTypeLeaveMobileDA _mTypeLeaveMobileDA = new mTypeLeaveMobileDA(db);
                    tKategoryPlanogramMobileDA _tKategoryPlanogramMobileDA = new tKategoryPlanogramMobileDA(db);
                    tSubTypeActivityDA _tSubTypeActivityDA = new tSubTypeActivityDA(db);
                    if (data.get_TxtDescription().contains("mnVisitPlanMobile") &&
                            _mEmployeeAreaDA.getContactsCount(db) > 0 &&
                            _mEmployeeBranchDA.getContactsCount(db) > 0 &&
//                            _mEmployeeSalesProductDA.getContactsCount(db) > 0 &&
                            //_mProductSPGDA.getContactsCount(db) > 0 &&
                            //_mProductPICDA.getContactsCount(db) > 0 &&
                            //_mProductCompetitorDA.getContactsCount(db) > 0 &&
                            //_mTypeSubmissionMobileDA.getContactsCount(db) > 0 &&
                            _mTypeLeaveMobileDA.getContactsCount(db) > 0 &&
                            _tKategoryPlanogramMobileDA.getContactsCount(db) > 0 &&
                            _tSubTypeActivityDA.getContactsCount(db) > 0
                            ) {
                        int validate = 0;
                        if (listDataLeave.size() == 0) {
                            validate = 1;
//                            List<mEmployeeAreaData> datamEmployeeArea = new mEmployeeAreaBL().GetAllData();
//
//                            for (mEmployeeAreaData dt : datamEmployeeArea) {
//                                if (dt.get_txtLatitude().equals("") || dt.get_txtLatitude() == null || dt.get_txtLatitude().equals("") && dt.get_txtLongitude().equals("") || dt.get_txtLongitude() == null || dt.get_txtLongitude().equals("")) {
//                                    validate = 0;
//                                }
//                            }
                        }
                        if (validate == 1) {
                            tmpData.add(data);
                        }
                    }
                    else if (data.get_TxtDescription().contains("mnPushDataSPG") && listDataLeave.size() == 0&&_mEmployeeBranchDA.getContactsCount(db) > 0) {
                        tmpData.add(data);
                    }
                    else if(data.get_TxtDescription().contains("mnLeaveMD")){
                        boolean validAddmnLeave = false;
                        tVisitPlanRealisasiDA _tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
                        //md
                        if (_tVisitPlanRealisasiDA.getContactsCountSubmit(db) == 0 && _mTypeLeaveMobileDA.getContactsCount(db) > 0) {
                            validAddmnLeave = true;
                        }

                        if(validAddmnLeave){
                            tmpData.add(data);
                        }
                    }
                    else if(data.get_TxtDescription().contains("mnGeoTagging")&&
                            _mEmployeeAreaDA.getContactsCount(db) > 0 &&
                            _mTypeLeaveMobileDA.getContactsCount(db) > 0){
                        int validate = 0;
                        if (listDataLeave.size() == 0) {
                            validate = 1;
//                            List<mEmployeeAreaData> datamEmployeeArea = new mEmployeeAreaBL().GetAllData();
//
//                            for (mEmployeeAreaData dt : datamEmployeeArea) {
//                                if (dt.get_txtLatitude().equals("") || dt.get_txtLatitude() == null || dt.get_txtLatitude().equals("") && dt.get_txtLongitude().equals("") || dt.get_txtLongitude() == null || dt.get_txtLongitude().equals("")) {
//                                    validate = 0;
//                                }
//                            }
                        }
                        if (validate == 1) {
                            tmpData.add(data);
                        }
                    }
                }

                //Untuk absen SPG Mobile harus pengecekan master data: Branch, Outlet, Product, Brand, Product SPG Customerbased, Product PIC Customerbased, Product Competitor, Type Submission, Type Leave
                else if (data.get_TxtDescription().contains("mnAbsenSPG")||data.get_TxtDescription().contains("mnPushDataSPG")||data.get_TxtDescription().contains("mnLeaveSPG")) {
                    _mEmployeeBranchDA = new mEmployeeBranchDA(db);
                    _mEmployeeAreaDA = new mEmployeeAreaDA(db);
                    _mEmployeeSalesProductDA = new mEmployeeSalesProductDA(db);
                    mProductSPGDA _mProductSPGDA = new mProductSPGDA(db);
                    mProductPICDA _mProductPICDA = new mProductPICDA(db);
                    mProductCompetitorDA _mProductCompetitorDA = new mProductCompetitorDA(db);
                    mTypeSubmissionMobileDA _mTypeSubmissionMobileDA = new mTypeSubmissionMobileDA(db);
                    mTypeLeaveMobileDA _mTypeLeaveMobileDA = new mTypeLeaveMobileDA(db);

                    if (data.get_TxtDescription().contains("mnAbsenSPG")&&
                            _mEmployeeAreaDA.getContactsCount(db) > 0 &&
                            _mEmployeeBranchDA.getContactsCount(db) > 0 &&
//                            _mEmployeeSalesProductDA.getContactsCount(db) > 0 &&
//                            _mProductSPGDA.getContactsCount(db) > 0 &&
//                            _mProductPICDA.getContactsCount(db) > 0 &&
//                            _mProductCompetitorDA.getContactsCount(db) > 0 &&
                            _mTypeSubmissionMobileDA.getContactsCount(db) > 0 &&
                            _mTypeLeaveMobileDA.getContactsCount(db) > 0
                            ) {
                        if (listDataLeave.size() == 0) {
                            tmpData.add(data);
                        }
                    }
                    else if (data.get_TxtDescription().contains("mnPushDataSPG") && listDataLeave.size() == 0&&_mEmployeeBranchDA.getContactsCount(db) > 0) {
                        tmpData.add(data);
                    }

                    else if(data.get_TxtDescription().contains("mnLeaveSPG")){
                        boolean validAddmnLeave = false;
                        //spg dan tl
                        tAbsenUserDA _tAbsenUserDA = new tAbsenUserDA(db);
                        if (_tAbsenUserDA.getContactsCountSubmit(db) == 0 && _mTypeLeaveMobileDA.getContactsCount(db) > 0) {
                            validAddmnLeave = true;
                        }

                        if(validAddmnLeave){
                            tmpData.add(data);
                        }
                    }
                }

                else if (data.get_TxtDescription().contains("mnAbsenTL")||data.get_TxtDescription().contains("mnPushDataSPG")||data.get_TxtDescription().contains("mnLeaveTL")) {
                    _mEmployeeBranchDA = new mEmployeeBranchDA(db);
                    _mEmployeeAreaDA = new mEmployeeAreaDA(db);
                    _mEmployeeSalesProductDA = new mEmployeeSalesProductDA(db);
                    mProductSPGDA _mProductSPGDA = new mProductSPGDA(db);
                    mProductPICDA _mProductPICDA = new mProductPICDA(db);
                    mProductCompetitorDA _mProductCompetitorDA = new mProductCompetitorDA(db);
                    mTypeSubmissionMobileDA _mTypeSubmissionMobileDA = new mTypeSubmissionMobileDA(db);
                    mTypeLeaveMobileDA _mTypeLeaveMobileDA = new mTypeLeaveMobileDA(db);
                    tSubTypeActivityDA _tSubTypeActivityDA = new tSubTypeActivityDA(db);
                    mTypePOPStandardDA _mTypePOPStandardDA = new mTypePOPStandardDA(db);
                    mReasonPOPStandardDA _mReasonPOPStandardDA = new mReasonPOPStandardDA(db);
                    mCategoryPOPStandardDA _mCategoryPOPStandardDA = new mCategoryPOPStandardDA(db);

                    if (data.get_TxtDescription().contains("mnAbsenTL")&&
                            _mEmployeeSalesProductDA.getContactsCount(db) > 0 &&
                            _tSubTypeActivityDA.getContactsCount(db) > 0 &&
                            _mEmployeeBranchDA.getContactsCount(db) > 0 &&
                            _mEmployeeAreaDA.getContactsCount(db)>0 &&
                            _mTypeLeaveMobileDA.getContactsCount(db)>0 &&
                            _mTypePOPStandardDA.getContactsCount(db)>0 &&
                            _mReasonPOPStandardDA.getContactsCount(db)>0 &&
                            _mCategoryPOPStandardDA.getContactsCount(db) >0
                            ) {
                        if (listDataLeave.size() == 0) {
                            tmpData.add(data);
                        }
                    }
                    else if (data.get_TxtDescription().contains("mnPushDataSPG") && listDataLeave.size() == 0&& _mEmployeeBranchDA.getContactsCount(db) > 0) {
                        tmpData.add(data);
                    }
                    else if(data.get_TxtDescription().contains("mnLeaveTL")){
                        boolean validAddmnLeave = false;
                        //spg dan tl
                        tAbsenUserDA _tAbsenUserDA = new tAbsenUserDA(db);
                        if (_tAbsenUserDA.getContactsCountSubmit(db) == 0 && _mTypeLeaveMobileDA.getContactsCount(db) > 0) {
                            validAddmnLeave = true;
                        }

                        if(validAddmnLeave){
                            tmpData.add(data);
                        }
                    }
                }

                //Jika Absen FPE
                else if (data.get_TxtDescription().contains("mnAbsenFPE")||data.get_TxtDescription().contains("mnPushDataSPG")||data.get_TxtDescription().contains("mnLeaveSPG")) {
                    _mEmployeeAreaDA = new mEmployeeAreaDA(db);
                    _mEmployeeBranchDA = new mEmployeeBranchDA(db);
                    mTypeLeaveMobileDA _mTypeLeaveMobileDA = new mTypeLeaveMobileDA(db);
                    tSubTypeActivityDA _tSubTypeActivityDA = new tSubTypeActivityDA(db);
                    if (data.get_TxtDescription().contains("mnAbsenFPE")&&
                            _mEmployeeSalesProductDA.getContactsCount(db) > 0 &&
                            _tSubTypeActivityDA.getContactsCount(db) > 0 &&
                            _mEmployeeBranchDA.getContactsCount(db) > 0) {
                        int validate = 0;
                        if (listDataLeave.size() == 0) {
                            validate = 1;
                        }
                        if (validate == 1) {
                            tmpData.add(data);
                        }
                    }

                    else if (data.get_TxtDescription().contains("mnPushDataSPG") && listDataLeave.size() == 0) {
                        tmpData.add(data);
                    }

                    else if(data.get_TxtDescription().contains("mnLeaveSPG")){
                        boolean validAddmnLeave = false;
                        //fpe
                        tAttendanceUserDA _tAttendanceUserDA = new tAttendanceUserDA(db);
                        if (_tAttendanceUserDA.getContactsCountSubmit(db) == 0 && _mTypeLeaveMobileDA.getContactsCount(db) > 0) {
                            validAddmnLeave = true;
                        }

                        if(validAddmnLeave){
                            tmpData.add(data);
                        }
                    }
                }

                else if (data.get_TxtDescription().contains("mnKoordinasiOutlet")) {
                    tmpData.add(data);
                }

            }

//            else if(data.get_TxtDescription().contains("mnLeaveSPG")){
//                boolean validAddmnLeave = false;
//                mTypeLeaveMobileDA _mTypeLeaveMobileDA = new mTypeLeaveMobileDA(db);
//                _mTypeLeaveMobileDA = new mTypeLeaveMobileDA(db);
//                tVisitPlanRealisasiDA _tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
//
//                //md
//                if (_tVisitPlanRealisasiDA.getContactsCountSubmit(db) == 0 && _mTypeLeaveMobileDA.getContactsCount(db) > 0) {
//                    validAddmnLeave = true;
//                }
//
//                //spg dan tl
//                tAbsenUserDA _tAbsenUserDA = new tAbsenUserDA(db);
//                if (_tAbsenUserDA.getContactsCountSubmit(db) == 0 && _mTypeLeaveMobileDA.getContactsCount(db) > 0) {
//                    validAddmnLeave = true;
//                }
//
//                //fpe
//                tAttendanceUserDA _tAttendanceUserDA = new tAttendanceUserDA(db);
//                if (_tAttendanceUserDA.getContactsCountSubmit(db) == 0 && _mTypeLeaveMobileDA.getContactsCount(db) > 0) {
//                    validAddmnLeave = true;
//                }
//
//                if(validAddmnLeave){
//                    tmpData.add(data);
//                }
//            }

            else {
                tmpData.add(data);
            }
        }
        db.close();
        return tmpData;
    }

    public List<mMenuData> getDatabyParentIdNew(String id) {
        SQLiteDatabase db = getDb();
        mMenuDA _mMenuDA = new mMenuDA(db);
        List<mMenuData> listData = _mMenuDA.getDatabyParentId(db, id);
        List<mMenuData> tmpData = new ArrayList<>();
        List<tLeaveMobileData> listDataLeave = new tLeaveMobileBL().getData("");

        for (mMenuData data : listData) {
            //Untuk visitplan harus pengecekan master data
            if (data.get_TxtDescription().contains("mnVisitPlanMobile")) {
                mEmployeeAreaDA _mEmployeeAreaDA = new mEmployeeAreaDA(db);
                mEmployeeBranchDA _mEmployeeBranchDA = new mEmployeeBranchDA(db);
                if (_mEmployeeAreaDA.getContactsCount(db) > 0 && _mEmployeeBranchDA.getContactsCount(db) > 0) {
                    int validate = 0;
                    if (listDataLeave.size() == 0) {
                        validate = 1;
                        List<mEmployeeAreaData> datamEmployeeArea = new mEmployeeAreaBL().GetAllData();

                        for (mEmployeeAreaData dt : datamEmployeeArea) {
                            if (dt.get_txtLatitude().equals("") || dt.get_txtLatitude() == null || dt.get_txtLatitude().equals("") && dt.get_txtLongitude().equals("") || dt.get_txtLongitude() == null || dt.get_txtLongitude().equals("")) {
                                validate = 0;
                            }
                        }
                    }
                    if (validate == 1) {
                        tmpData.add(data);
                    }
                }
            }

            //Untuk absen SPG Mobile harus pengecekan master data: Branch, Outlet, Product, Brand, Product SPG Customerbased, Product PIC Customerbased, Product Competitor, Type Submission, Type Leave
            else if (data.get_TxtDescription().contains("mnAbsenSPG")) {
                mEmployeeBranchDA _mEmployeeBranchDA = new mEmployeeBranchDA(db);
                mEmployeeAreaDA _mEmployeeAreaDA = new mEmployeeAreaDA(db);
                mEmployeeSalesProductDA _mEmployeeSalesProductDA = new mEmployeeSalesProductDA(db);
                mProductSPGDA _mProductSPGDA = new mProductSPGDA(db);
                mProductPICDA _mProductPICDA = new mProductPICDA(db);
                mProductCompetitorDA _mProductCompetitorDA = new mProductCompetitorDA(db);
                mTypeSubmissionMobileDA _mTypeSubmissionMobileDA = new mTypeSubmissionMobileDA(db);
                mTypeLeaveMobileDA _mTypeLeaveMobileDA = new mTypeLeaveMobileDA(db);

                if (_mEmployeeAreaDA.getContactsCount(db) > 0 &&
                        _mEmployeeBranchDA.getContactsCount(db) > 0 &&
                        _mEmployeeSalesProductDA.getContactsCount(db) > 0 &&
                        _mProductSPGDA.getContactsCount(db) > 0 &&
                        _mProductPICDA.getContactsCount(db) > 0 &&
                        _mProductCompetitorDA.getContactsCount(db) > 0 &&
                        _mTypeSubmissionMobileDA.getContactsCount(db) > 0 &&
                        _mTypeLeaveMobileDA.getContactsCount(db) > 0
                        ) {
                    if (listDataLeave.size() == 0) {
                        tmpData.add(data);
                    }
                }
            }
            //Jika Absen FPE
            else if (data.get_TxtDescription().contains("mnAbsenFPE")) {
                mEmployeeAreaDA _mEmployeeAreaDA = new mEmployeeAreaDA(db);
                mEmployeeBranchDA _mEmployeeBranchDA = new mEmployeeBranchDA(db);
                if (_mEmployeeAreaDA.getContactsCount(db) > 0 && _mEmployeeBranchDA.getContactsCount(db) > 0) {
                    int validate = 0;
                    if (listDataLeave.size() == 0) {
                        validate = 1;
                        List<mEmployeeAreaData> datamEmployeeArea = new mEmployeeAreaBL().GetAllData();

                        for (mEmployeeAreaData dt : datamEmployeeArea) {
                            if (dt.get_txtLatitude().equals("") || dt.get_txtLatitude() == null || dt.get_txtLatitude().equals("") && dt.get_txtLongitude().equals("") || dt.get_txtLongitude() == null || dt.get_txtLongitude().equals("")) {
                                validate = 0;
                            }
                        }
                    }
                    if (validate == 1) {
                        tmpData.add(data);
                    }
                }
            }
            //Jika menu leave
            else if (data.get_TxtDescription().contains("mnLeave")) {
                mTypeLeaveMobileDA _mTypeLeaveMobileDA = new mTypeLeaveMobileDA(db);
                tAbsenUserDA _tAbsenUserDA = new tAbsenUserDA(db);
                if (_tAbsenUserDA.getContactsCountSubmit(db) == 0 && _mTypeLeaveMobileDA.getContactsCount(db) > 0) {
                    tmpData.add(data);
                }
            }

            else if (data.get_TxtDescription().contains("mnKoordinasiOutlet")) {
                    tmpData.add(data);
            }

            else {
                tmpData.add(data);
            }
        }

        db.close();
        return tmpData;
    }

}