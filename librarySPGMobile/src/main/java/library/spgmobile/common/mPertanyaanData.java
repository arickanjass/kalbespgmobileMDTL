package library.spgmobile.common;

/**
 * Created by Dewi Oktaviani on 03/05/2017.
 */

public class mPertanyaanData {
    private String _intQuestionId;
    private String _intCategoryId;
    private String _txtQuestionDesc;
    private String _intTypeQuestionId;
    private String _decBobot;
    private String _bolHaveAnswerList;
    private String _inttGroupQuestionMapping;
    private String _intSoalId;

    public String get_intSoalId() {
        return _intSoalId;
    }

    public void set_intSoalId(String _intSoalId) {
        this._intSoalId = _intSoalId;   
    }

    public synchronized String get_inttGroupQuestionMapping() {
        return _inttGroupQuestionMapping;
    }

    public synchronized void set_inttGroupQuestionMapping(String _inttGroupQuestionMapping) {
        this._inttGroupQuestionMapping = _inttGroupQuestionMapping;
    }

    public synchronized String get_intQuestionId() {
        return _intQuestionId;
    }

    public synchronized void set_intQuestionId(String _intQuestionId) {
        this._intQuestionId = _intQuestionId;
    }

    public synchronized String get_intCategoryId() {
        return _intCategoryId;
    }

    public synchronized void set_intCategoryId(String _intCategoryId) {
        this._intCategoryId = _intCategoryId;
    }

    public synchronized String get_txtQuestionDesc() {
        return _txtQuestionDesc;
    }

    public synchronized void set_txtQuestionDesc(String _txtQuestionDesc) {
        this._txtQuestionDesc = _txtQuestionDesc;
    }

    public synchronized String get_intTypeQuestionId() {
        return _intTypeQuestionId;
    }

    public synchronized void set_intTypeQuestionId(String _intTypeQuestionId) {
        this._intTypeQuestionId = _intTypeQuestionId;
    }

    public synchronized String get_decBobot() {
        return _decBobot;
    }

    public synchronized void set_decBobot(String _decBobot) {
        this._decBobot = _decBobot;
    }

    public synchronized String get_bolHaveAnswerList() {
        return _bolHaveAnswerList;
    }

    public synchronized void set_bolHaveAnswerList(String _bolHaveAnswerList) {
        this._bolHaveAnswerList = _bolHaveAnswerList;
    }
    public String Property_intQuestionId = "intQuestionId";
    public String Property_intCategoryId = "intCategoryId";
    public String Property_txtQuestionDesc = "txtQuestionDesc";
    public String Property_intTypeQuestionId = "intTypeQuestionId";
    public String Property_decBobot = "decBobot";
    public String Property_bolHaveAnswerList = "bolHaveAnswerList";
    public String Property_intSoalId = "intSoalId";
    public String Property_ListOfmPertanyaanData = "ListOfmPertanyaanData";
    public String Property_inttGroupQuestionMapping = "inttGroupQuestionMapping";
    public String Property_All = Property_intQuestionId + "," + Property_intSoalId + "," + Property_intCategoryId + "," + Property_txtQuestionDesc + "," + Property_intTypeQuestionId
            + "," + Property_decBobot + "," + Property_bolHaveAnswerList + "," + Property_intSoalId + "," + Property_inttGroupQuestionMapping ;
    public mPertanyaanData(){
        super();
    }
}
