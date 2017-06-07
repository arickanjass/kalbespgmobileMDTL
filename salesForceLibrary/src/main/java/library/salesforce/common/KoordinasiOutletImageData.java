package library.salesforce.common;

/**
 * Created by Rian Andrivani on 6/7/2017.
 */

public class KoordinasiOutletImageData {

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

    private String _txtId;
    private String _txtHeaderId;
    private byte[] _txtImage;
    private String _intPosition;

    public String Property_txtId = "txtId";
    public String Property_txtHeaderId = "txtHeaderId";
    public String Property_txtImage = "txtImage";
    public String Property_intPosition = "intPosition";
    public String Property_ListOfKoordinasiOutletImageData = "ListOfKoordinasiOutletImageData";

    public String Property_All = Property_txtId +","+
            Property_txtHeaderId +","+
            Property_txtImage +","+
            Property_intPosition;
}
