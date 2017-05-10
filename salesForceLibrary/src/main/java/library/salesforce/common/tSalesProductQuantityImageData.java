package library.salesforce.common;

/**
 * Created by Rian Andrivani on 4/26/2017.
 */

public class tSalesProductQuantityImageData {

    public synchronized String get_txtId() {
        return _txtId;
    }

    public synchronized void set_txtId(String _txtId) {
        this._txtId = _txtId;
    }

    public synchronized String get_txtHeaderId() {
        return _txtHeaderId;
    }

    public synchronized void set_txtHeaderId(String _txtHeaderId) {
        this._txtHeaderId = _txtHeaderId;
    }

    public synchronized byte[] get_txtImage() {
        return _txtImage;
    }

    public synchronized void set_txtImage(byte[] _txtImage) {
        this._txtImage = _txtImage;
    }

    public synchronized String get_intPosition() {
        return _intPosition;
    }

    public synchronized void set_intPosition(String _intPosition) {
        this._intPosition = _intPosition;
    }

    public synchronized String get_txtType() {
        return _txtType;
    }

    public synchronized void set_txtType(String _txtType) {
        this._txtType = _txtType;
    }

    private String _txtId;
    private String _txtHeaderId;
    private byte[] _txtImage;
    private String _intPosition;
    private String _txtType;

    public String Property_txtId = "txtId";
    public String Property_txtHeaderId = "txtQuantityStock";
    public String Property_txtImage = "txtImage";
    public String Property_intPosition = "intPosition";
    public String Property_txtType = "txtType";
    public String Property_ListOftSalesProductQuantityImageData = "ListOftSalesProductQuantityImageData";

    public String Property_All = Property_txtId +","+
            Property_txtHeaderId +","+
            Property_txtImage +","+
            Property_intPosition +","+
            Property_txtType;
}
