package library.spgmobile.common;

/**
 * Created by Dewi Oktaviani on 04/09/2017.
 */

public class tJawabanUserHeaderData {
    private String _intRoleId;
    private String _intSubmit;
    private String _intSync;
    private String _intNik;
    private String _txtOutletCode;
    private String _intHeaderId;
    private String _dtDate;
    private String _txtUserName;
    private String _intId;
    private String _intGroupQuestionId;
    private String _txtOutletName;

    public String get_dtDatetime() {
        return _dtDatetime;
    }

    public void set_dtDatetime(String _dtDatetime) {
        this._dtDatetime = _dtDatetime;
    }

    private String _dtDatetime;

    public String get_txtOutletName() {
        return _txtOutletName;
    }

    public void set_txtOutletName(String _txtOutletName) {
        this._txtOutletName = _txtOutletName;
    }

    public String get_intGroupQuestionId() {
        return _intGroupQuestionId;
    }

    public void set_intGroupQuestionId(String _intGroupQuestionId) {
        this._intGroupQuestionId = _intGroupQuestionId;
    }


    public String get_intId() {
        return _intId;
    }

    public void set_intId(String _intId) {
        this._intId = _intId;
    }

    public String get_txtOutletCode() {
        return _txtOutletCode;
    }

    public void set_txtOutletCode(String _txtOutletCode) {
        this._txtOutletCode = _txtOutletCode;
    }

    public String get_intHeaderId() {
        return _intHeaderId;
    }

    public void set_intHeaderId(String _intHeaderId) {
        this._intHeaderId = _intHeaderId;
    }

    public String get_dtDate() {
        return _dtDate;
    }

    public void set_dtDate(String _dtDate) {
        this._dtDate = _dtDate;
    }

    public String get_txtUserName() {
        return _txtUserName;
    }

    public void set_txtUserName(String _txtUserName) {
        this._txtUserName = _txtUserName;
    }

    public String get_intNik() {
        return _intNik;
    }

    public void set_intNik(String _intNik) {
        this._intNik = _intNik;
    }

    public String get_intSubmit() {
        return _intSubmit;
    }

    public void set_intSubmit(String _intSubmit) {
        this._intSubmit = _intSubmit;
    }

    public String get_intSync() {
        return _intSync;
    }

    public void set_intSync(String _intSync) {
        this._intSync = _intSync;
    }

    public synchronized String get_intRoleId() {
        return _intRoleId;
    }

    public synchronized void set_intRoleId(String _intRoleId) {
        this._intRoleId = _intRoleId;
    }

    public String Property_txtUserName = "txtUserName";
    public String Property_intRoleId = "intRoleId";
    public String Property_intSubmit = "intSubmit";
    public String Property_intSync = "intSync";
    public String Property_intNik = "intNik";
    public String Property_txtOutletCode = "txtOutletCode";
    public String Property_intHeaderId = "intHeaderId";
    public String Property_dtDate = "dtDate";
    public String Property_intId = "intId";
    public String Property_intGroupQuestionId = "intGroupQuestionId";
    public String Property_txtOutletName = "txtOutletName";
    public String Property_dtDatetime = "dtDatetime";
    public String Property_ListOftJawabanUserHeaderData = "ListOftJawabanUserHeaderData";
    public String Property_All =  Property_intHeaderId + "," + Property_intNik + "," + Property_txtUserName
            + "," + Property_intRoleId + "," + Property_txtOutletCode + "," + Property_dtDate
            + "," + Property_intSubmit + "," + Property_intSync
            + "," + Property_intGroupQuestionId + "," + Property_txtOutletName +  "," +Property_dtDatetime ;
    public tJawabanUserHeaderData(){
        super();
    }
}
