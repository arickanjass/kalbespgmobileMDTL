package library.spgmobile.common;

/**
 * Created by Dewi Oktaviani on 17/10/2017.
 */

public class mTypePOPStandardData {
    public String get_intId() {
        return _intId;
    }

    public void set_intId(String _intId) {
        this._intId = _intId;
    }

    public String get_txtType() {
        return _txtType;
    }

    public void set_txtType(String _txtType) {
        this._txtType = _txtType;
    }

    private String _intId;
    private String _txtType;
     public String Property_intId = "intId";
    public String Property_txtType = "txtType";
    public String Property_All = Property_intId + "," + Property_txtType;
    public String Property_ListOfmTypePOPStandard = "ListOfmTypePOPStandard";
    public mTypePOPStandardData(){super();}
}
