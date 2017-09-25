package library.spgmobile.common;

/**
 * Created by aan.junianto on 10/08/2017.
 */

public class tPlanogramMobileData extends APIData{
    public synchronized String get_intId() {
        return _intId;
    }

    public synchronized void set_intId(String _intId) {
        this._intId = _intId;
    }

    public synchronized String get_txtNIK() {
        return _txtNIK;
    }

    public synchronized void set_txtNIK(String _txtNIK) {
        this._txtNIK = _txtNIK;
    }

    public synchronized String get_txtKeterangan() {
        return _txtKeterangan;
    }

    public synchronized void set_txtKeterangan(String _txtKeterangan) {
        this._txtKeterangan = _txtKeterangan;
    }

    public synchronized String get_dtDate() {
        return _dtDate;
    }

    public synchronized void set_dtDate(String _dtDate) {
        this._dtDate = _dtDate;
    }

    public synchronized String get_OutletCode() {
        return _OutletCode;
    }

    public synchronized void set_OutletCode(String _OutletCode) {
        this._OutletCode = _OutletCode;
    }

    public synchronized String get_OutletName() {
        return _OutletName;
    }

    public synchronized void set_OutletName(String _OutletName) {
        this._OutletName = _OutletName;
    }

    public synchronized String get_UserId() {
        return _UserId;
    }

    public synchronized void set_UserId(String _UserId) {
        this._UserId = _UserId;
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

    public synchronized String get_intIdAbsenUser() {
        return _intIdAbsenUser;
    }

    public synchronized void set_intIdAbsenUser(String _intIdAbsenUser) {
        this._intIdAbsenUser = _intIdAbsenUser;
    }

    public synchronized String get_txtRoleId() {
        return _txtRoleId;
    }

    public synchronized void set_txtRoleId(String _txtRoleId) {
        this._txtRoleId = _txtRoleId;
    }

    public synchronized byte[] get_txtAfterImg1() {
        return _txtAfterImg1;
    }

    public synchronized void set_txtAfterImg1(byte[] _txtAfterImg1) {
        this._txtAfterImg1 = _txtAfterImg1;
    }

    public synchronized byte[] get_txtAfterImg2() {
        return _txtAfterImg2;
    }

    public synchronized void set_txtAfterImg2(byte[] _txtAfterImg2) {
        this._txtAfterImg2 = _txtAfterImg2;
    }

    public synchronized byte[] get_txtBeforeImg1() {
        return _txtBeforeImg1;
    }

    public synchronized void set_txtBeforeImg1(byte[] _txtBeforeImg1) {
        this._txtBeforeImg1 = _txtBeforeImg1;
    }

    public synchronized byte[] get_txtBeforeImg2() {
        return _txtBeforeImg2;
    }

    public synchronized void set_txtBeforeImg2(byte[] _txtBeforeImg2) {
        this._txtBeforeImg2 = _txtBeforeImg2;
    }

    public String get_txtIdPlanogram() {
        return _txtIdPlanogram;
    }

    public void set_txtIdPlanogram(String _txtIdPlanogram) {
        this._txtIdPlanogram = _txtIdPlanogram;
    }
    public String get_bitActive() {
        return _bitActive;
    }

    public void set_bitActive(String _bitActive) {
        this._bitActive = _bitActive;
    }

    public String get_txtDeviceId() {
        return _txtDeviceId;
    }

    public void set_txtDeviceId(String _txtDeviceId) {
        this._txtDeviceId = _txtDeviceId;
    }

    private String _intId;
    private String _txtIdPlanogram;
    private String _txtNIK;
    private String _txtKeterangan;
    private String _dtDate;
    private String _OutletCode;
    private String _OutletName;
    private String _UserId;
    private String _intSubmit;
    private String _intSync;
    private String _bitActive;
    private String _txtBranchCode;
    private String _txtBranchName;
    private String _intIdAbsenUser;
    private String _txtRoleId;
    private byte[] _txtAfterImg1;
    private byte[] _txtAfterImg2;
    private byte[] _txtBeforeImg1;
    private byte[] _txtBeforeImg2;
    private String _txtDeviceId;

    public String get_txtIdCategory() {
        return _txtIdCategory;
    }

    public void set_txtIdCategory(String _txtIdCategory) {
        this._txtIdCategory = _txtIdCategory;
    }

    public String get_txtCategoryName() {
        return _txtCategoryName;
    }

    public void set_txtCategoryName(String _txtCategoryName) {
        this._txtCategoryName = _txtCategoryName;
    }

    private  String _txtIdCategory;
    private  String _txtCategoryName;

    public String get_intIsValid() {
        return _intIsValid;
    }

    public void set_intIsValid(String _intIsValid) {
        this._intIsValid = _intIsValid;
    }

    private String _intIsValid;

    public String Property_intId = "intId";
    public String Property_txtIdPlanogram = "txtIdPlanogram";
    public String Property_txtNIK = "txtNIK";
    public String Property_txtKeterangan = "txtKeterangan";
    public String Property_txtDate = "dtDate";
    public String Property_OutletCode = "OutletCode";
    public String Property_OutletName = "OutletName";
    public String Property_UserId = "UserId";
    public String Property_intSubmit = "intSubmit";
    public String Property_intSync = "intSync";
    public String Property_bitActive = "bitActive";
    public String Property_txtBranchCode = "txtBranchCode";
    public String Property_txtBranchName = "txtBranchName";
    public String Property_intIdAbsenUser = "intIdAbsenUser";
    public String Property_txtRoleId = "txtRoleId";
    public String Property_txtAfterImg1="txtAfterImg1";
    public String Property_txtAfterImg2="txtAfterImg2";
    public String Property_txtBeforeImg1="txtBeforeImg1";
    public String Property_txtBeforeImg2="txtBeforeImg2";
    public String Property_txtDeviceId="txtDeviceId";
    public String Property_txtIdCategory="txtIdCategory";
    public String Property_txtCategoryName="txtCategoryName";
    public String Property_intIsValid="intIsValid";
    public String Property_ListOftPlanogramMobileData = "ListOftPlanogramMobileData";

    public tPlanogramMobileData() {
        super();
    }

    public String Property_All =
            Property_intId + "," +
                    Property_txtIdPlanogram + "," +
                    Property_txtNIK + "," +
                    Property_txtKeterangan + "," +
                    Property_txtDate + "," +
                    Property_OutletCode + "," +
                    Property_OutletName + "," +
                    Property_UserId + "," +
                    Property_intSubmit + "," +
                    Property_intSync + "," +
                    Property_bitActive + "," +
                    Property_txtBranchCode + "," +
                    Property_txtBranchName + "," +
                    Property_intIdAbsenUser + "," +
                    Property_txtRoleId + "," +
                    Property_txtBeforeImg1 + "," +
                    Property_txtBeforeImg2 + "," +
                    Property_txtAfterImg1 + "," +
                    Property_txtAfterImg2 + "," +
                    Property_txtDeviceId + "," +
                    Property_txtIdCategory + "," +
                    Property_txtCategoryName + "," +
                    Property_intIsValid;
}
