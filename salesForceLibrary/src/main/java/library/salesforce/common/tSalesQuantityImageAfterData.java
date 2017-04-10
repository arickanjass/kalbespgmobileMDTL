package library.salesforce.common;

/**
 * Created by Rian Andrivani on 4/10/2017.
 */

public class tSalesQuantityImageAfterData {
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

    public synchronized byte[] get_after1() {
        return _after1;
    }

    public synchronized void set_after1(byte[] _after1) {
        this._after1 = _after1;
    }

    public synchronized byte[] get_after2() {
        return _after2;
    }

    public synchronized void set_after2(byte[] _after2) {
        this._after2 = _after2;
    }

    private String _txtId;
    private String _txtHeaderId;
    private byte[] _after1;
    private byte[] _after2;

    public String Property_txtId = "txtId";
    public String Property_txtHeaderId = "txtHeaderId";
    public String Property_after1 = "byteAfter1";
    public String Property_after2 = "byteAfter2";

    public String Property_All = Property_txtId +","+
            Property_txtHeaderId +","+
            Property_after1 +","+
            Property_after2;
}
