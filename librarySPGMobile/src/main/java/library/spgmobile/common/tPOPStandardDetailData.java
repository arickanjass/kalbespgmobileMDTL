package library.spgmobile.common;

/**
 * Created by Dewi Oktaviani on 17/10/2017.
 */

public class tPOPStandardDetailData {
    public tPOPStandardDetailData(){super();}
    private String _intId;
    private String _intHeaderId;
    private byte[] _txtImg1;
    private byte[] _txtImg2;
    private String _dtDatetime;
    private String _intSync;
    private String _intSubmit;

    public String get_intId() {
        return _intId;
    }

    public void set_intId(String _intId) {
        this._intId = _intId;
    }

    public String get_intHeaderId() {
        return _intHeaderId;
    }

    public void set_intHeaderId(String _intHeaderId) {
        this._intHeaderId = _intHeaderId;
    }

    public byte[] get_txtImg1() {
        return _txtImg1;
    }

    public void set_txtImg1(byte[] _txtImg1) {
        this._txtImg1 = _txtImg1;
    }

    public byte[] get_txtImg2() {
        return _txtImg2;
    }

    public void set_txtImg2(byte[] _txtImg2) {
        this._txtImg2 = _txtImg2;
    }

    public String get_dtDatetime() {
        return _dtDatetime;
    }

    public void set_dtDatetime(String _dtDatetime) {
        this._dtDatetime = _dtDatetime;
    }

    public String get_intSync() {
        return _intSync;
    }

    public void set_intSync(String _intSync) {
        this._intSync = _intSync;
    }

    public String get_intSubmit() {
        return _intSubmit;
    }

    public void set_intSubmit(String _intSubmit) {
        this._intSubmit = _intSubmit;
    }

    public String Property_intId = "intId";
    public String Property_intHeaderId = "intHeaderId";
    public String Property_txtImg1 = "txtImg1";
    public String Property_txtImg2 = "txtImg2";
    public String Property_dtDatetime = "dtDatetime";
    public String Property_intSync = "intSync";
    public String Property_intSubmit = "intSubmit";
    public String Property_All = Property_intId + "," + Property_intHeaderId + "," + Property_txtImg1 + "," + Property_txtImg2 + ","
            + Property_dtDatetime + "," + Property_intSync + "," + Property_intSubmit;
    public String Property_ListOftPOPStandardDetailData = "ListOftPOPStandardDetailData";
}
