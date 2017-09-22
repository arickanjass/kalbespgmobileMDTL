package library.spgmobile.common;

/**
 * Created by Dewi Oktaviani on 22/09/2017.
 */

public class mCategoryKoordinasiOutletData {
    public String get_intId() {
        return _intId;
    }

    public void set_intId(String _intId) {
        this._intId = _intId;
    }

    public String get_txtCategory() {
        return _txtCategory;
    }

    public void set_txtCategory(String _txtCategory) {
        this._txtCategory = _txtCategory;
    }

    public String get_intRoleId() {
        return _intRoleId;
    }

    public void set_intRoleId(String _intRoleId) {
        this._intRoleId = _intRoleId;
    }

    private String _intId;
    private String _txtCategory;
    private String _intRoleId;

    public String Property_intId = "intId";
    public String Property_txtCategory = "txtCategory";
    public String Property_intRoleId = "intRoleId";
    public String Property_ListOfmCategoryKoordinasiOutlet = "ListOfmCategoryKoordinasiOutlet";
    public String Property_All = Property_intId + "," + Property_txtCategory;

    public mCategoryKoordinasiOutletData(){
        super();
    }
}
