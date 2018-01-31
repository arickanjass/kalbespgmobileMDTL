package library.spgmobile.common;

/**
 * Created by aan.junianto on 30/01/2018.
 */

public class clsReturnData {
    private String _IdData;

    public String get_IdData() {
        return _IdData;
    }

    public void set_IdData(String _IdData) {
        this._IdData = _IdData;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_desc() {
        return _desc;
    }

    public void set_desc(String _desc) {
        this._desc = _desc;
    }

    public Boolean get_boleanValue() {
        return _boleanValue;
    }

    public void set_boleanValue(Boolean _boleanValue) {
        this._boleanValue = _boleanValue;
    }

    private String _title;
    private String _desc;
    private Boolean _boleanValue;

    public Boolean get_boleanValueProduct() {
        return _boleanValueProduct;
    }

    public void set_boleanValueProduct(Boolean _boleanValueProduct) {
        this._boleanValueProduct = _boleanValueProduct;
    }

    private Boolean _boleanValueProduct;
}
