package library.spgmobile.common;

/**
 * Created by Dewi Oktaviani on 17/10/2017.
 */

public class mReasonPOPStandardData {
    public mReasonPOPStandardData(){super();}

    public String get_intId() {
        return _intId;
    }

    public void set_intId(String _intId) {
        this._intId = _intId;
    }

    public String get_txtReason() {
        return _txtReason;
    }

    public void set_txtReason(String _txtReason) {
        this._txtReason = _txtReason;
    }

    private String _intId;
    private String _txtReason;

    public String Property_intId = "intId";
    public String Property_txtReason = "txtReason";
    public String Property_All = Property_intId + "," + Property_txtReason;
    public String Property_ListOfmReasonPOPStandardData = "ListOfmReasonPOPStandardData";
}
