package library.spgmobile.common;

/**
 * Created by Dewi Oktaviani on 17/10/2017.
 */

public class tPOPStandardHeaderData {
    public tPOPStandardHeaderData(){super();}
    private String _intId;
    private String _txtType;
    private String _bolHavePOP;
    private String _txtCategory;
    private String _txtReason;
    private String _intRoleId;
    private String _txtUserName;
    private String _txtNIK;
    private String _txtOutletName;
    private String _txtOutletCode;
    private String _txtBranchName;
    private String _txtBranchCode;
    private String _dtDatetime;
    private String _intSubmit;
    private String _intSync;

    public String get_intId() {
        return _intId;
    }

    public void set_intId(String _intId) {
        this._intId = _intId;
    }

    public String get_txtType() {
        return _txtType;
    }

    public void set_txtType(String _txtType) {
        this._txtType = _txtType;
    }

    public String get_bolHavePOP() {
        return _bolHavePOP;
    }

    public void set_bolHavePOP(String _bolHavePOP) {
        this._bolHavePOP = _bolHavePOP;
    }

    public String get_txtCategory() {
        return _txtCategory;
    }

    public void set_txtCategory(String _txtCategory) {
        this._txtCategory = _txtCategory;
    }

    public String get_txtReason() {
        return _txtReason;
    }

    public void set_txtReason(String _txtReason) {
        this._txtReason = _txtReason;
    }

    public String get_intRoleId() {
        return _intRoleId;
    }

    public void set_intRoleId(String _intRoleId) {
        this._intRoleId = _intRoleId;
    }

    public String get_txtUserName() {
        return _txtUserName;
    }

    public void set_txtUserName(String _txtUserName) {
        this._txtUserName = _txtUserName;
    }

    public String get_txtNIK() {
        return _txtNIK;
    }

    public void set_txtNIK(String _txtNIK) {
        this._txtNIK = _txtNIK;
    }

    public String get_txtOutletName() {
        return _txtOutletName;
    }

    public void set_txtOutletName(String _txtOutletName) {
        this._txtOutletName = _txtOutletName;
    }

    public String get_txtOutletCode() {
        return _txtOutletCode;
    }

    public void set_txtOutletCode(String _txtOutletCode) {
        this._txtOutletCode = _txtOutletCode;
    }

    public String get_txtBranchName() {
        return _txtBranchName;
    }

    public void set_txtBranchName(String _txtBranchName) {
        this._txtBranchName = _txtBranchName;
    }

    public String get_txtBranchCode() {
        return _txtBranchCode;
    }

    public void set_txtBranchCode(String _txtBranchCode) {
        this._txtBranchCode = _txtBranchCode;
    }

    public String get_DtDatetime() {
        return _dtDatetime;
    }

    public void set_DtDatetime(String _dtDatetime) {
        this._dtDatetime = _dtDatetime;
    }

    public String get_intSubmit() {
        return _intSubmit;
    }

    public void set_intSubmit(String _intSubmit) {
        this._intSubmit = _intSubmit;
    }

    public String get_intSync() {
        return _intSync;
    }

    public void set_intSync(String _intSync) {
        this._intSync = _intSync;
    }

    public String Property_intId = "intId";
    public String Property_txtType = "txtType";
    public String Property_bolHavePOP = "bolHavePOP";
    public String Property_txtCategory = "txtCategory";
    public String Property_txtReason = "txtReason";
    public String Property_intRoleId = "intRoleId";
    public String Property_txtUserName = "txtUserName";
    public String Property_txtNIK = "txtNIK";
    public String Property_txtOutletCode = "txtOutletCode";
    public String Property_txtOutletName = "txtOutletName";
    public String Property_txtBranchName = "txtBranchName";
    public String Property_txtBranchCode = "txtBranchCode";
    public String Property_dtDatetime = "dtDatetime";
    public String Property_intSubmit = "intSubmit";
    public String Property_intSync = "intSync";
    public String Property_type = Property_txtType + "," + Property_txtBranchName + "," + Property_txtOutletName + ","
            + Property_txtOutletCode + "," + Property_txtUserName;
    public String Property_All = Property_intId + "," + Property_txtType + "," + Property_bolHavePOP + "," + Property_txtCategory + ","
            + Property_txtReason + "," + Property_intRoleId + "," + Property_txtUserName + "," + Property_txtNIK + ","
            + Property_txtOutletCode + "," + Property_txtOutletName + "," + Property_txtBranchCode + "," + Property_txtBranchName
            + "," + Property_dtDatetime + "," + Property_intSync + "," + Property_intSubmit;
    public String Property_ListOftPOPStandarHeaderdData = "ListOftPOPStandarHeaderdData";
 }
