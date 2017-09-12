package library.spgmobile.common;

/**
 * Created by Dewi Oktaviani on 11/09/2017.
 */

public class tHirarkiBIS {
    private String _txtNik;
    private String _txtName;
    private String _txtLOB;
    private String _intBranchId;
    private String _txtBranchCode;
    private String _txtBranchName;
    private String _intOutletId;
    private String _txtOutletCode;
    private String _txtOutletName;

    public String get_intId() {
        return _intId;
    }

    public void set_intId(String _intId) {
        this._intId = _intId;
    }

    private String _intId;
    public String get_txtNik() {
        return _txtNik;
    }

    public void set_txtNik(String _txtNik) {
        this._txtNik = _txtNik;
    }

    public String get_txtName() {
        return _txtName;
    }

    public void set_txtName(String _txtName) {
        this._txtName = _txtName;
    }

    public String get_txtLOB() {
        return _txtLOB;
    }

    public void set_txtLOB(String _txtLOB) {
        this._txtLOB = _txtLOB;
    }

    public String get_intBranchId() {
        return _intBranchId;
    }

    public void set_intBranchId(String _intBranchId) {
        this._intBranchId = _intBranchId;
    }

    public String get_txtBranchCode() {
        return _txtBranchCode;
    }

    public void set_txtBranchCode(String _txtBranchCode) {
        this._txtBranchCode = _txtBranchCode;
    }

    public String get_txtBranchName() {
        return _txtBranchName;
    }

    public void set_txtBranchName(String _txtBranchName) {
        this._txtBranchName = _txtBranchName;
    }

    public String get_intOutletId() {
        return _intOutletId;
    }

    public void set_intOutletId(String _intOutletId) {
        this._intOutletId = _intOutletId;
    }

    public String get_txtOutletCode() {
        return _txtOutletCode;
    }

    public void set_txtOutletCode(String _txtOutletCode) {
        this._txtOutletCode = _txtOutletCode;
    }

    public String get_txtOutletName() {
        return _txtOutletName;
    }

    public void set_txtOutletName(String _txtOutletName) {
        this._txtOutletName = _txtOutletName;
    }
    public String Property_txtNik = "txtNik";
    public String Property_txtName = "txtName";
    public  String Property_txtLOB = "txtLOB";
    public  String Property_intBranchId = "intBranchId";
    public  String Property_txtBranchCode = "txtBranchCode";
    public  String Property_txtBranchName = "txtBranchName";
    public  String Property_intOutletId ="intOutletId";
    public  String Property_txtOutletCode = "txtOutletCode";
    public  String Property_txtOutletName ="txtOutletName";
    public String Property_intId = "intId";
    public String Property_All = Property_txtNik + "," + Property_txtName + "," + Property_txtLOB + ","
            + Property_intBranchId + "," + Property_txtBranchCode + "," + Property_txtBranchName + ","
            + Property_intOutletId + "," + Property_txtOutletCode + "," + Property_txtOutletName;
}
