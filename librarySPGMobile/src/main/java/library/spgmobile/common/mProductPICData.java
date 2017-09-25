package library.spgmobile.common;

/**
 * Created by ASUS ZE on 24/03/2017.
 */

public class mProductPICData extends APIData {

    private String _intId;
    public String _txtNIK;
    public String _txtName;
    public String _txtBrandDetailGramCode;
    public String _txtProductBrandDetailGramName;
    public String _decHJD;
    public String _decBobot;
    public String _txtProductDetailCode;
    public String _txtProductDetailName;
    public String _txtLobName;
    public String _txtMasterId;
    public String _txtNamaMasterData;
    public String _txtKeterangan;

    public mProductPICData() {
        super();
        // TODO Auto-generated constructor stub
    }
    public mProductPICData(String _txtNIK, String _txtName,
                           String _txtBrandDetailGramCode,
                           String _txtProductBrandDetailGramName, String _decHJD,
                           String _decBobot, String _txtProductDetailCode, String _txtProductDetailName, String _txtLobName, String _txtMasterId, String _txtNamaMasterData, String _txtKeterangan) {
        this._txtNIK = _txtNIK;
        this._txtName = _txtName;
        this._txtBrandDetailGramCode = _txtBrandDetailGramCode;
        this._txtProductBrandDetailGramName = _txtProductBrandDetailGramName;
        this._decHJD = _decHJD;
        this._decBobot = _decBobot;
        this._txtProductDetailCode = _txtProductDetailCode;
        this._txtProductDetailName = _txtProductDetailName;
        this._txtLobName = _txtLobName;
        this._txtMasterId = _txtMasterId;
        this._txtNamaMasterData = _txtNamaMasterData;
        this._txtKeterangan = _txtKeterangan;
    }

    public String Property_txtNIK="txtNIK";
    public String Property_intId="intId";
    public String Property_txtName="txtName";
    public String Property_txtBrandDetailGramCode="txtBrandDetailGramCode";
    public String Property_txtProductBrandDetailGramName="txtProductBrandDetailGramName";
    public String Property_decHJD="decHJD";
    public String Property_decBobot="decBobot";
    public String Property_txtProductDetailCode="txtProductDetailCode";
    public String Property_txtProductDetailName="txtProductDetailName";
    public String Property_txtLobName="txtLobName";
    public String Property_txtMasterId="txtMasterId";
    public String Property_txtNamaMasterData="txtNamaMasterData";
    public String Property_txtKeterangan="txtKeterangan";
    public String Property_ListOfmProductPICData="ListOfmProductPICData";
    public String Property_All= Property_intId +","+ Property_decBobot +","+Property_decHJD+","+Property_txtBrandDetailGramCode+","+
            Property_txtName+","+Property_txtNIK+","+Property_txtProductBrandDetailGramName+","+Property_txtProductDetailCode+","+Property_txtProductDetailName+","+Property_txtLobName+","+Property_txtMasterId+","+Property_txtNamaMasterData+","+Property_txtKeterangan;

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

    public synchronized String get_txtName() {
        return _txtName;
    }

    public synchronized void set_txtName(String _txtName) {
        this._txtName = _txtName;
    }

    public synchronized String get_txtBrandDetailGramCode() {
        return _txtBrandDetailGramCode;
    }

    public synchronized void set_txtBrandDetailGramCode(String _txtBrandDetailGramCode) {
        this._txtBrandDetailGramCode = _txtBrandDetailGramCode;
    }

    public synchronized String get_txtProductBrandDetailGramName() {
        return _txtProductBrandDetailGramName;
    }

    public synchronized void set_txtProductBrandDetailGramName(String _txtProductBrandDetailGramName) {
        this._txtProductBrandDetailGramName = _txtProductBrandDetailGramName;
    }

    public synchronized String get_decHJD() {
        return _decHJD;
    }

    public synchronized void set_decHJD(String _decHJD) {
        this._decHJD = _decHJD;
    }

    public synchronized String get_decBobot() {
        return _decBobot;
    }

    public synchronized void set_decBobot(String _decBobot) {
        this._decBobot = _decBobot;
    }

    public synchronized String get_txtProductDetailCode() {
        return _txtProductDetailCode;
    }

    public synchronized void set_txtProductDetailCode(String _txtProductDetailCode) {
        this._txtProductDetailCode = _txtProductDetailCode;
    }

    public synchronized String get_txtProductDetailName() {
        return _txtProductDetailName;
    }

    public synchronized void set_txtProductDetailName(String _txtProductDetailName) {
        this._txtProductDetailName = _txtProductDetailName;
    }

    public synchronized String get_txtLobName() {
        return _txtLobName;
    }

    public synchronized void set_txtLobName(String _txtLobName) {
        this._txtLobName = _txtLobName;
    }

    public synchronized String get_txtMasterId() {
        return _txtMasterId;
    }

    public synchronized void set_txtMasterId(String _txtMasterId) {
        this._txtMasterId = _txtMasterId;
    }

    public synchronized String get_txtNamaMasterData() {
        return _txtNamaMasterData;
    }

    public synchronized void set_txtNamaMasterData(String _txtNamaMasterData) {
        this._txtNamaMasterData = _txtNamaMasterData;
    }

    public synchronized String get_txtKeterangan() {
        return _txtKeterangan;
    }

    public synchronized void set_txtKeterangan(String _txtKeterangan) {
        this._txtKeterangan = _txtKeterangan;
    }
}
