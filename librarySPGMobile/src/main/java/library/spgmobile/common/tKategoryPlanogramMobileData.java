package library.spgmobile.common;

/**
 * Created by aan.junianto on 28/08/2017.
 */

public class tKategoryPlanogramMobileData {
    private String _intKategoryPlanogram;
    private String _txtType;
    private String _txtName;
    private String _bitActive;

    public String get_intIsCheckValid() {
        return _intIsCheckValid;
    }

    public void set_intIsCheckValid(String _intIsCheckValid) {
        this._intIsCheckValid = _intIsCheckValid;
    }

    private String _intIsCheckValid;

    public String Property_intKategoryPlanogram = "intKategoryPlanogram";
    public String Property_txtType = "txtType";
    public String Property_txtName = "txtName";
    public String Property_bitActive = "bitActive";
    public String Property_intIsCheckValid = "intIsCheckValid";
    public String Property_All = Property_intKategoryPlanogram + "," +
            Property_bitActive + "," +
            Property_txtName + "," +
            Property_txtType + "," +
            Property_intIsCheckValid
            ;

    public String get_intKategoryPlanogram() {
        return _intKategoryPlanogram;
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

    public void set_intKategoryPlanogram(String _intKategoryPlanogram) {
        this._intKategoryPlanogram = _intKategoryPlanogram;
    }

    public void set_txtType(String _txtType) {
        this._txtType = _txtType;
    }

    public void set_txtName(String _txtName) {
        this._txtName = _txtName;
    }

    public tKategoryPlanogramMobileData() {
    }
}
