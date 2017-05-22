package library.salesforce.common;

/**
 * Created by Dewi Oktaviani on 03/05/2017.
 */

public class mKategoriData {
    private String _intCategoryId;
    private String _intParentId;
    private String _txtCategoryName;

    public synchronized String get_intCategoryId() {
        return _intCategoryId;
    }

    public synchronized void set_intCategoryId(String _intCategoryId) {
        this._intCategoryId = _intCategoryId;
    }

    public synchronized String get_intParentId() {
        return _intParentId;
    }

    public synchronized void set_intParentId(String _intParentId) {
        this._intParentId = _intParentId;
    }

    public synchronized String get_txtCategoryName() {
        return _txtCategoryName;
    }

    public synchronized void set_txtCategoryName(String _txtCategoryName) {
        this._txtCategoryName = _txtCategoryName;
    }
    public String Property_intCategoryId = "intCategoryId";
    public String Property_intParentId = "intParentId";
    public String Property_txtCategoryName = "txtCategoryName";
    public String Property_ListOfmKategoriData = "ListOfmKategoriData";
    public  String Property_All = Property_intCategoryId + "," + Property_intParentId + "," + Property_txtCategoryName;

    public mKategoriData(){
        super();
    }
}
