package library.spgmobile.common;

import library.spgmobile.dal.clsHardCode;

/**
 * Created by Dewi Oktaviani on 04/07/2017.
 */

public class tGroupQuestionMappingData {
    private String _intId;
    private String _txtGroupQuestion;
    private String _intRoleId;
    private String _dtStart;
    private String _dtEnd;
    public static final String TABLE_Question = new clsHardCode().txtTable_tGroupQuestionMapping;
    public String get_intRoleId() {
        return _intRoleId;
    }

    public void set_intRoleId(String _intRoleId) {
        this._intRoleId = _intRoleId;
    }

    public String get_dtStart() {
        return _dtStart;
    }

    public void set_dtStart(String _dtStart) {
        this._dtStart = _dtStart;
    }

    public String get_dtEnd() {
        return _dtEnd;
    }

    public void set_dtEnd(String _dtEnd) {
        this._dtEnd = _dtEnd;
    }

    public String get_intId() {
        return _intId;
    }

    public void set_intId(String _intId) {
        this._intId = _intId;
    }

    public String get_txtGroupQuestion() {
        return _txtGroupQuestion;
    }

    public void set_txtGroupQuestion(String _txtGroupQuestion) {
        this._txtGroupQuestion = _txtGroupQuestion;
    }
    public String Property_intId = "intId";
    public String Property_txtGroupQuestion = "txtGroupQuestion";
    public String Property_intRoleId = "intRoleId";
    public String Property_dtStart = "dtStart";
    public String Property_dtEnd = "dtEnd";
    public String Property_ListOftGroupQuestionMappingData = "ListOftGroupQuestionMappingData";
    public String Property_All = Property_intId + "," + Property_txtGroupQuestion + "," + Property_intRoleId + "," + Property_dtStart + "," + Property_dtEnd;
    public String Property_AllS = TABLE_Question+ "."+ Property_intId + "," + TABLE_Question+ "." + Property_txtGroupQuestion + ","
            +  TABLE_Question+ "." + Property_intRoleId + "," + TABLE_Question+ "." + Property_dtStart + "," + TABLE_Question+ "." +  Property_dtEnd;
    public tGroupQuestionMappingData(){super();}
}
