package library.spgmobile.common;

/**
 * Created by Rian Andrivani on 6/6/2017.
 */

public class KoordinasiOutletData {
    public synchronized String get_intId() {
        return _intId;
    }

    public synchronized void set_intId(String _intId) {
        this._intId = _intId;
    }

    public synchronized String get_dtDate() {
        return _dtDate;
    }

    public synchronized void set_dtDate(String _dtDate) {
        this._dtDate = _dtDate;
    }

    public synchronized String get_txtUserId() {
        return _txtUserId;
    }

    public synchronized void set_txtUserId(String _txtUserId) {
        this._txtUserId = _txtUserId;
    }

    public synchronized String get_txtUsername() {
        return _txtUsername;
    }

    public synchronized void set_txtUsername(String _txtUsername) {
        this._txtUsername = _txtUsername;
    }

    public synchronized String get_txtRoleId() {
        return _txtRoleId;
    }

    public synchronized void set_txtRoleId(String _txtRoleId) {
        this._txtRoleId = _txtRoleId;
    }

    public synchronized String get_txtOutletCode() {
        return _txtOutletCode;
    }

    public synchronized void set_txtOutletCode(String _txtOutletCode) {
        this._txtOutletCode = _txtOutletCode;
    }

    public synchronized String get_txtOutletName() {
        return _txtOutletName;
    }

    public synchronized void set_txtOutletName(String _txtOutletName) {
        this._txtOutletName = _txtOutletName;
    }

    public synchronized String get_txtBranchCode() {
        return _txtBranchCode;
    }

    public synchronized void set_txtBranchCode(String _txtBranchCode) {
        this._txtBranchCode = _txtBranchCode;
    }

    public synchronized String get_txtBranchName() {
        return _txtBranchName;
    }

    public synchronized void set_txtBranchName(String _txtBranchName) {
        this._txtBranchName = _txtBranchName;
    }

    public synchronized String get_txtKeterangan() {
        return _txtKeterangan;
    }

    public synchronized void set_txtKeterangan(String _txtKeterangan) {
        this._txtKeterangan = _txtKeterangan;
    }

    public synchronized String get_txtNIK() {
        return _txtNIK;
    }

    public synchronized void set_txtNIK(String _txtNIK) {
        this._txtNIK = _txtNIK;
    }

    public synchronized String get_intSubmit() {
        return _intSubmit;
    }

    public synchronized void set_intSubmit(String _intSubmit) {
        this._intSubmit = _intSubmit;
    }

    public synchronized String get_intSync() {
        return _intSync;
    }

    public synchronized void set_intSync(String _intSync) {
        this._intSync = _intSync;
    }

    private String _intId;
    private String _dtDate;
    private String _txtKeterangan;
    private String _txtUserId;
    private String _txtUsername;
    private String _txtRoleId;
    private String _txtOutletCode;
    private String _txtOutletName;
    private String _txtBranchCode;
    private String _txtBranchName;
    private String _txtNIK;
    private String _intSubmit;
    private String _intSync;

    public String Property_intId = "intId";
    public String Property_dtDate = "dtDate";
    public String Property_txtKeterangan = "txtKeterangan";
    public String Property_txtUserId = "txtUserId";
    public String Property_txtUsername = "txtUsername";
    public String Property_txtRoleId = "txtRoleId";
    public String Property_txtOutletCode = "txtOutletCode";
    public String Property_txtOutletName = "txtOutletName";
    public String Property_txtBranchCode = "txtBranchCode";
    public String Property_txtBranchName = "txtBranchName";
    public String Property_txtNIK = "txtNIK";
    public String Property_intSubmit = "intSubmit";
    public String Property_intSync = "intSync";
    public String Property_ListOfKoordinasiOutlet = "ListOfKoordinasiOutletData";
    public String Property_All = Property_intId + "," +
            Property_dtDate + "," +
            Property_txtKeterangan + "," +
            Property_txtUserId + "," +
            Property_txtUsername + "," +
            Property_txtRoleId + "," +
            Property_txtOutletCode + "," +
            Property_txtOutletName + "," +
            Property_txtBranchCode + "," +
            Property_txtBranchName + "," +
            Property_txtNIK + "," +
            Property_intSubmit + "," +
            Property_intSync;

    public KoordinasiOutletData() {
        super();
    }
}
