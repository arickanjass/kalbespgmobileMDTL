package library.spgmobile.common;

/**
 * Created by Dewi Oktaviani on 03/05/2017.
 */

public class mParentData {
    private String _intParentId;
    private String _txtParentName;

    public  String get_intParentId() {
        return _intParentId;
    }

    public synchronized void set_intParentId(String _intParentId) {
        this._intParentId = _intParentId;
    }

    public synchronized String get_txtParentName() {
        return _txtParentName;
    }

    public synchronized void set_txtParentName(String _txtParentName) {
        this._txtParentName = _txtParentName;
    }
    public String Property_intParentId = "intParentId";
    public String Property_txtParentName = "txtParentName";
    public String Property_ListOfmParentData = "ListOfmParentData";
    public String Property_All = Property_intParentId + "," + Property_txtParentName;
    public mParentData(){
        super();
    }
}
