package library.spgmobile.common;

/**
 * Created by Dewi Oktaviani on 17/10/2017.
 */

public class mCategoryPOPStandardData {
    public mCategoryPOPStandardData(){super();}
    private String _intId;
    private String _txtCategoryCode;
    private String _txtCategoryName;

    public String get_intId() {
        return _intId;
    }

    public void set_intId(String _intId) {
        this._intId = _intId;
    }

    public String get_txtCategoryCode() {
        return _txtCategoryCode;
    }

    public void set_txtCategoryCode(String _txtCategoryCode) {
        this._txtCategoryCode = _txtCategoryCode;
    }

    public String get_txtCategoryName() {
        return _txtCategoryName;
    }

    public void set_txtCategoryName(String _txtCategoryName) {
        this._txtCategoryName = _txtCategoryName;
    }

    public String Property_intId = "intId";
    public String Property_txtCategoryCode = "txtCategoryCode";
    public  String Property_txtCategoryName = "txtCategoryName";
    public String Property_All = Property_intId + "," + Property_txtCategoryCode + "," + Property_txtCategoryName;
    public String Property_ListOfmCategoryPOPStandard = "ListOfmCategoryPOPStandard";
}
