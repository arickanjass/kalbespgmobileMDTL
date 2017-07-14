package library.salesforce.common;

/**
 * Created by Rian Andrivani on 3/8/2017.
 */

public class mProductCompetitorData {
    public synchronized String get_txtProductDetailCode() {
        return _txtProductDetailCode;
    }

    public synchronized void set_txtProductDetailCode(String _txtProductDetailCode) {
        this._txtProductDetailCode = _txtProductDetailCode;
    }

    public synchronized String get_txtLobName() {
        return _txtLobName;
    }

    public synchronized void set_txtLobName(String _txtLobName) {
        this._txtLobName = _txtLobName;
    }

    public synchronized String get_GroupProduct() {
        return _GroupProduct;
    }

    public synchronized void set_GroupProduct(String _GroupProduct) {
        this._GroupProduct = _GroupProduct;
    }

    public synchronized String get_txtProdukid() {
        return _txtProdukid;
    }

    public synchronized void set_txtProdukid(String _txtProdukid) {
        this._txtProdukid = _txtProdukid;
    }

    public synchronized String get_txtProdukKompetitorID() {
        return _txtProdukKompetitorID;
    }

    public synchronized void set_txtProdukKompetitorID(String _txtProdukKompetitorID) {
        this._txtProdukKompetitorID = _txtProdukKompetitorID;
    }

    public synchronized String get_txtCRMCode() {
        return _txtCRMCode;
    }

    public synchronized void set_txtCRMCode(String _txtCRMCode) {
        this._txtCRMCode = _txtCRMCode;
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

    public String get_txtID() {
        return _txtID;
    }

    public void set_txtID(String _txtID) {
        this._txtID = _txtID;
    }

    private String _txtID;
    private String _txtProductDetailCode;
    private String _txtLobName;
    private String _GroupProduct;
    private String _txtProdukid;
    private String _txtProdukKompetitorID;
    private String _txtCRMCode;
    private String _txtNIK;
    private String _txtName;
    public mProductCompetitorData(){

    }

    public String Property_txtID="txtID";
    public String Property_txtProductDetailCode="txtProductDetailCode";
    public String Property_txtLobName="txtLobName";
    public String Property_GroupProduct="GroupProduct";
    public String Property_txtProdukid="txtProdukid";
    public String Property_txtProdukKompetitorID="txtProdukKompetitorID";
    public String Property_txtCRMCode="txtCRMCode";
    public String Property_txtNIK="txtNIK";
    public String Property_txtName="txtName";
    public String Property_ListOfmProductCompetitor="ListOfmProductCompetitor";
    public String Property_All=Property_txtID+","+Property_txtProductDetailCode+","+Property_txtLobName+","+
            Property_GroupProduct+","+ Property_txtProdukid+","+Property_txtProdukKompetitorID+","+Property_txtCRMCode+","+
            Property_txtNIK+","+Property_txtName;

}