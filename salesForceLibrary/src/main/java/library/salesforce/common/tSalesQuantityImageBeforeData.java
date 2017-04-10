package library.salesforce.common;

/**
 * Created by Rian Andrivani on 4/10/2017.
 */

public class tSalesQuantityImageBeforeData {
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

    public synchronized byte[] get_before1() {
        return _before1;
    }

    public synchronized void set_before1(byte[] _before1) {
        this._before1 = _before1;
    }

    public synchronized byte[] get_before2() {
        return _before2;
    }

    public synchronized void set_before2(byte[] _before2) {
        this._before2 = _before2;
    }

    private String _txtId;
    private String _txtHeaderId;
    private byte[] _before1;
    private byte[] _before2;

    public String Property_txtId = "txtId";
    public String Property_txtHeaderId = "txtHeaderId";
    public String Property_before1 = "byteBefore1";
    public String Property_before2 = "byteBefore2";

    public String Property_All = Property_txtId +","+
            Property_txtHeaderId +","+
            Property_before1 +","+
            Property_before2;
}
