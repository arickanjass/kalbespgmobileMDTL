package library.spgmobile.common;

/**
 * Created by aan.junianto on 14/09/2017.
 */

public class tOverStockDetailData {
    public synchronized String getTxtProduct() {
        return txtProduct;
    }

    public synchronized void setTxtProduct(String txtProduct) {
        this.txtProduct = txtProduct;
    }

    public synchronized String getTxtExpireDate() {
        return txtExpireDate;
    }

    public synchronized void setTxtExpireDate(String txtExpireDate) {
        this.txtExpireDate = txtExpireDate;
    }

    public synchronized String getTxtQuantity() {
        return txtQuantity;
    }

    public synchronized void setTxtQuantity(String txtQuantity) {
        this.txtQuantity = txtQuantity;
    }

    public synchronized String getIntId() {
        return intId;
    }

    public synchronized void setIntId(String intId) {
        this.intId = intId;
    }

    public synchronized String get_txtOverStock() {
        return _txtOverStock;
    }

    public synchronized void set_txtOverStock(String _txtOverStock) {
        this._txtOverStock = _txtOverStock;
    }

    public synchronized String get_intActive() {
        return _intActive;
    }

    public synchronized void set_intActive(String _intActive) {
        this._intActive = _intActive;
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

    public synchronized String get_txtCodeProduct() {
        return _txtCodeProduct;
    }

    public synchronized void set_txtCodeProduct(String _txtCodeProduct) {
        this._txtCodeProduct = _txtCodeProduct;
    }

    public synchronized String get_intPrice() {
        return _intPrice;
    }

    public synchronized void set_intPrice(String _intPrice) {
        this._intPrice = _intPrice;
    }

    public synchronized String get_intTotal() {
        return _intTotal;
    }

    public synchronized void set_intTotal(String _intTotal) {
        this._intTotal = _intTotal;
    }

    private String intId;
    private String _txtNIK;
    private String _txtKeterangan;
    private String _dtDate;
    private String _txtCodeProduct;
    private String txtProduct;
    private String txtExpireDate;
    private String txtQuantity;
    private String _intPrice;
    private String _intTotal;
    private String _txtOverStock;
    private String _intActive;

    public String Property_intId = "intId";
    public String Property_txtNIK = "txtUserId";
    public String Property_txtKeterangan = "txtKeterangan";
    public String Property_dtDate = "dtDate";
    public String Property_txtCodeProduct = "txtCodeProduct";
    public String Property_txtProduct = "txtProduct";
    public String Property_txtExpireDate = "txtExpireDate";
    public String Property_txtQuantity = "txtQuantity";
    public String Property_txtOverStock = "txtOverStock";
    public String Property_intPrice = "intPrice";
    public String Property_intTotal = "intTotal";
    public String Property_intActive = "intActive";
    public String Property_ListOftOverStockDetailData = "ListOftOverStockDetailData";

    public String Property_All = Property_intId + "," +
            Property_dtDate + "," +
            Property_txtCodeProduct + "," +
            Property_txtKeterangan + "," +
            Property_txtProduct + "," +
            Property_txtExpireDate + "," +
            Property_txtQuantity + "," +
            Property_txtOverStock + "," +
            Property_intPrice + "," +
            Property_intTotal + "," +
            Property_intActive + "," +
            Property_txtNIK;

    public tOverStockDetailData() {
        super();
    }

    public tOverStockDetailData(String intId, String _dtDate, String _intPrice, String _txtCodeProduct, String txtProduct,
                                           String _txtKeterangan, String txtExpireDate, String txtQuantity, String _txtNIK, String _intTotal,
                                           String _txtOverStock, String _intActive) {
        this.intId = intId;
        this._txtNIK = _txtNIK;
        this._dtDate = _dtDate;
        this._intPrice = _intPrice;
        this._txtCodeProduct = _txtCodeProduct;
        this.txtProduct = txtProduct;
        this._txtKeterangan = _txtKeterangan;
        this.txtExpireDate = txtExpireDate;
        this.txtQuantity = txtQuantity;
        this._intTotal = _intTotal;
        this._txtOverStock = _txtOverStock;
        this._intActive = _intActive;
    }
}
