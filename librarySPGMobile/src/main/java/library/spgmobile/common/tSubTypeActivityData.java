package library.spgmobile.common;

/**
 * Created by rhezaTesar on 7/18/2017.
 */

public class tSubTypeActivityData {
    private String _intSubTypeActivity;
    private String _txtType;
    private String _txtName;
    private String _bitActive;

    public String Property_intSubTypeActivity = "intSubTypeActivity";
    public String Property_txtType = "txtType";
    public String Property_txtName = "txtName";
    public String Property_bitActive = "bitActive";
    public String Property_All = Property_intSubTypeActivity + "," +
            Property_bitActive + "," +
            Property_txtName + "," +
            Property_txtType;

    public String get_intSubTypeActivity() {
        return _intSubTypeActivity;
    }

    public String get_txtType() {
        return _txtType;
    }

    public String get_txtName() {
        return _txtName;
    }

    public String get_bitActive() {
        return _bitActive;
    }

    public void set_bitActive(String _bitActive) {
        this._bitActive = _bitActive;
    }

    public void set_intSubTypeActivity(String _intSubTypeActivity) {
        this._intSubTypeActivity = _intSubTypeActivity;
    }

    public void set_txtType(String _txtType) {
        this._txtType = _txtType;
    }

    public void set_txtName(String _txtName) {
        this._txtName = _txtName;
    }

    public tSubTypeActivityData() {
    }
}
