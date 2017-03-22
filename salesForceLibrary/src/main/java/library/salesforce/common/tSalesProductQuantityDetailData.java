package library.salesforce.common;

/**
 * Created by Rian Andrivani on 3/21/2017.
 */

public class tSalesProductQuantityDetailData {
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

    public synchronized String get_txtNoSo() {
        return _txtNoSo;
    }

    public synchronized void set_txtNoSo(String _txtNoSo) {
        this._txtNoSo = _txtNoSo;
    }

    public synchronized String get_intActive() {
        return _intActive;
    }

    public synchronized void set_intActive(String _intActive) {
        this._intActive = _intActive;
    }

    private String intId;
    private String txtProduct;
    private String txtExpireDate;
    private String txtQuantity;
    private String _txtNoSo;
    private String _intActive;

    public String Property_intId = "intId";
    public String Property_txtProduct = "txtProduct";
    public String Property_txtExpireDate = "txtExpireDate";
    public String Property_txtQuantity = "txtQuantity";
    public String Property_txtNoSo = "txtNoSo";
    public String Property_intActive = "intActive";
    public String Property_ListOftSalesProductQuantityDetailData = "ListOftSalesProductQuantityDetailData";

    public String Property_All =
            Property_intId + "," +
            Property_txtProduct + "," +
            Property_txtExpireDate + "," +
            Property_txtQuantity + "," +
            Property_txtNoSo + "," +
            Property_intActive;

    public tSalesProductQuantityDetailData() {
        super();
        // TODO Auto-generated constructor stub
    }

    public tSalesProductQuantityDetailData(String intId, String txtProduct, String txtExpireDate, String txtQuantity,
    String _txtNoSo, String _intActive) {
        this.intId = intId;
        this.txtProduct = txtProduct;
        this.txtExpireDate = txtExpireDate;
        this.txtQuantity = txtQuantity;
        this.Property_txtNoSo = _txtNoSo;
        this.Property_intActive = _intActive;
    }
}
