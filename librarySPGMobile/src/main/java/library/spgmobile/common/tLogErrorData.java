package library.spgmobile.common;

/**
 * Created by Robert on 06/04/2017.
 */

public class tLogErrorData {
    private String _intLogId;
    private String _txtUserId;
    private String _txtRoleId;
    private String _txtRoleName;
    private String _dtDate;
    private String _txtDeviceId;
    private String _intSubmit;
    private String _intSync;
    private String _txtFileName;

    public String Property_intLogId = "intLogId";
    public String Property_txtUserId = "txtLogUserId";
    public String Property_txtRoleId = "txtRoleId";
    public String Property_txtRoleName = "txtRoleName";
    public String Property_txtDeviceId = "txtDeviceId";
    public String Property_dtDate = "dtDate";
    public String Property_intSubmit = "intSubmit";
    public String Property_intSync = "intSync";
    public String Property_txtFileName = "txtFileName";
    public String Property_ListLogError = "listLogError";
    public String Property_All = Property_intLogId + "," + Property_txtUserId + "," + Property_txtRoleId + "," + Property_txtRoleName + "," + Property_txtDeviceId + "," + Property_dtDate + "," + Property_intSubmit + "," + Property_intSync + "," + Property_txtFileName;

    public String get_intLogId() {
        return _intLogId;
    }

    public void set_intLogId(String _intLogId) {
        this._intLogId = _intLogId;
    }

    public String get_txtRoleId(){
        return _txtRoleId;
    }
    public void set_txtRoleId(String _txtRoleId){
        this._txtRoleId = _txtRoleId;
    }

    public String get_txtRoleName(){
        return _txtRoleName;
    }

    public void set_txtRoleName(String _txtRoleName) {
        this._txtRoleName = _txtRoleName;
    }

    public String get_TxtUserId() {
        return _txtUserId;
    }

    public void set_txtUserId(String _txtUserId) {
        this._txtUserId = _txtUserId;
    }

    public String get_dtDate() {
        return _dtDate;
    }

    public void set_dtDate(String _dtDate) {
        this._dtDate = _dtDate;
    }

    public String get_txtDeviceId() {
        return _txtDeviceId;
    }

    public void set_txtDeviceId(String _txtDeviceId) {
        this._txtDeviceId = _txtDeviceId;
    }

    public String get_intSubmit(){
        return _intSubmit;
    }
    public void set_intSubmit(String _intSubmit){
        this._intSubmit = _intSubmit;
    }
    public String get_intSync(){
        return _intSync;
    }
    public void set_intSync(String _intSync){
        this._intSubmit = _intSync;
    }
    public String get_txtFileName(){
        return _txtFileName;
    }
    public void set_txtFileName(String _txtFileName){
        this._txtFileName = _txtFileName;
    }
}
