package library.spgmobile.common;

/**
 * Created by aan.junianto on 27/10/2017.
 */

public class mUserLOBData extends APIData {
    public String get_intId() {
        return _intId;
    }

    public void set_intId(String _intId) {
        this._intId = _intId;
    }

    public String get_txtUserId() {
        return _txtUserId;
    }

    public void set_txtUserId(String _txtUserId) {
        this._txtUserId = _txtUserId;
    }

    public String get_intRoleId() {
        return _intRoleId;
    }

    public void set_intRoleId(String _intRoleId) {
        this._intRoleId = _intRoleId;
    }

    public String get_txtRoleName() {
        return _txtRoleName;
    }

    public void set_txtRoleName(String _txtRoleName) {
        this._txtRoleName = _txtRoleName;
    }

    public String get_txtLOBName() {
        return _txtLOBName;
    }

    public void set_txtLOBName(String _txtLOBName) {
        this._txtLOBName = _txtLOBName;
    }

    private String _intId;
    private String _txtUserId;
    private String _intRoleId;
    private String _txtRoleName;
    private String _txtLOBName;

    public String Property_intId="intId";
    public String Property_txtUserId="txtUserId";
    public String Property_intRoleId="intRoleId";
    public String Property_txtRoleName="txtRoleName";
    public String Property_txtLOBName="txtLOBName";
    public String Property_All=Property_intId+","+Property_txtLOBName;
    public String Property_ListOfmUserLOBData="ListOfmUserLOBData";
}
