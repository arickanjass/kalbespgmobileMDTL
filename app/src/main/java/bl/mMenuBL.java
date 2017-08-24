package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.mEmployeeAreaData;
import library.spgmobile.common.mMenuData;
import library.spgmobile.common.tLeaveMobileData;
import library.spgmobile.dal.mEmployeeAreaDA;
import library.spgmobile.dal.mEmployeeBranchDA;
import library.spgmobile.dal.mEmployeeSalesProductDA;
import library.spgmobile.dal.mMenuDA;
import library.spgmobile.dal.mProductBarcodeDA;
import library.spgmobile.dal.mProductBrandHeaderDA;
import library.spgmobile.dal.mProductCompetitorDA;
import library.spgmobile.dal.mProductPICDA;
import library.spgmobile.dal.mProductSPGDA;
import library.spgmobile.dal.mTypeLeaveMobileDA;
import library.spgmobile.dal.mTypeSubmissionMobileDA;
import library.spgmobile.dal.tAbsenUserDA;
import library.spgmobile.dal.tLeaveMobileDA;
import library.spgmobile.dal.tSalesProductHeaderDA;

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
                    || data.get_TxtDescription().contains("mnLeaveSPG")) {
                mEmployeeAreaDA _mEmployeeAreaDA = new mEmployeeAreaDA(db);
                mEmployeeBranchDA _mEmployeeBranchDA = new mEmployeeBranchDA(db);
                mProductBarcodeDA _mProductBarcodeDA = new mProductBarcodeDA(db);
                tLeaveMobileDA _tLeaveMobileDA = new tLeaveMobileDA(db);
                mEmployeeSalesProductDA _mEmployeeSalesProductDA = new mEmployeeSalesProductDA(db);
                if (_mEmployeeAreaDA.getContactsCount(db) > 0 && _mEmployeeBranchDA.getContactsCount(db) > 0) {
                    if (listDataLeave.size() == 0 && _mEmployeeAreaDA.getContactsCount(db) > 0 && _mEmployeeBranchDA.getContactsCount(db) > 0 && data.get_TxtDescription().contains("mnAbsenSPG")) {

                        int validate = 0;
                        if (data.get_TxtDescription().contains("mnVisitPlanMobile") || data.get_TxtDescription().contains("mnVisitPlanMobile")) {

                            validate = 1;

                            List<mEmployeeAreaData> datamEmployeeArea = new mEmployeeAreaBL().GetAllData();

                            for (mEmployeeAreaData dt : datamEmployeeArea) {
                                if (dt.get_txtLatitude() == "" || dt.get_txtLatitude() == null || dt.get_txtLatitude().equals("")
                                        && dt.get_txtLongitude() == "" || dt.get_txtLongitude() == null || dt.get_txtLongitude().equals("")) {
                                    validate = 0;
                                }
                            }
                        }

                        if (validate == 1) {
                            tmpData.add(data);
                        }
                    } else if (data.get_TxtDescription().contains("mnLeave") && listDataLeave.size() > 0 && _mEmployeeAreaDA.getContactsCount(db) > 0 && _mEmployeeBranchDA.getContactsCount(db) > 0) {
                        tmpData.add(data);

                    } else if (data.get_TxtDescription().contains("mnPushDataSPG") && listDataLeave.size() == 0) {
                        tmpData.add(data);
                    } else if (data.get_TxtDescription().contains("mnLeave")) {
                        mTypeLeaveMobileDA _mTypeLeaveMobileDA = new mTypeLeaveMobileDA(db);
                        tAbsenUserDA _tAbsenUserDA = new tAbsenUserDA(db);
                        if (_tAbsenUserDA.getContactsCountSubmit(db) == 0 && _mTypeLeaveMobileDA.getContactsCount(db) > 0) {
                            tmpData.add(data);
                        }
                    }


                } else if (data.get_TxtDescription().contains("mnLeave")) {
                    mTypeLeaveMobileDA _mTypeLeaveMobileDA = new mTypeLeaveMobileDA(db);
                    tAbsenUserDA _tAbsenUserDA = new tAbsenUserDA(db);
                    if (_tAbsenUserDA.getContactsCountSubmit(db) == 0 && _mTypeLeaveMobileDA.getContactsCount(db) > 0) {
                        tmpData.add(data);
                    }
                }
            } else {
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