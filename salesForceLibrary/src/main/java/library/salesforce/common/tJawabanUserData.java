package library.salesforce.common;

/**
 * Created by XSIS on 03/05/2017.
 */

public class tJawabanUserData {
    private String _intUserAnswer;
    private String _intUserId;
    private String _intRoleId;
    private String _intQuestionId;
    private String _intTypeQuestionId;
    private String _bolHaveAnswerList;
    private String _intAnswerId;
    private String _txtValue;
    private String _decBobot;

    public synchronized String get_intUserAnswer() {
        return _intUserAnswer;
    }

    public synchronized void set_intUserAnswer(String _intUserAnswer) {
        this._intUserAnswer = _intUserAnswer;
    }

    public synchronized String get_intUserId() {
        return _intUserId;
    }

    public synchronized void set_intUserId(String _intUserId) {
        this._intUserId = _intUserId;
    }

    public synchronized String get_intRoleId() {
        return _intRoleId;
    }

    public synchronized void set_intRoleId(String _intRoleId) {
        this._intRoleId = _intRoleId;
    }

    public synchronized String get_intQuestionId() {
        return _intQuestionId;
    }

    public synchronized void set_intQuestionId(String _intQuestionId) {
        this._intQuestionId = _intQuestionId;
    }

    public synchronized String get_intTypeQuestionId() {
        return _intTypeQuestionId;
    }

    public synchronized void set_intTypeQuestionId(String _intTypeQuestionId) {
        this._intTypeQuestionId = _intTypeQuestionId;
    }

    public synchronized String get_bolHaveAnswerList() {
        return _bolHaveAnswerList;
    }

    public synchronized void set_bolHaveAnswerList(String _bolHaveAnswerList) {
        this._bolHaveAnswerList = _bolHaveAnswerList;
    }

    public synchronized String get_intAnswerId() {
        return _intAnswerId;
    }

    public synchronized void set_intAnswerId(String _intAnswerId) {
        this._intAnswerId = _intAnswerId;
    }

    public synchronized String get_txtValue() {
        return _txtValue;
    }

    public synchronized void set_txtValue(String _txtValue) {
        this._txtValue = _txtValue;
    }

    public synchronized String get_decBobot() {
        return _decBobot;
    }

    public synchronized void set_decBobot(String _decBobot) {
        this._decBobot = _decBobot;
    }

    public String Property_intUserAnswer = "intUserAnswer";
    public String Property_intUserId = "intUserId";
    public String Property_intRoleId = "intRoleId";
    public String Property_intQuestionId = "intQuestionId";
    public String Property_intTypeQuestionId = "intTypeQuestionId";
    public String Property_bolHaveAnswerList = "bolHaveAnswerList";
    public String Property_intAnswerId = "intAnswerId";
    public String Property_txtValue = "txtValue";
    public String Property_decBobot = "decBobot";
    public String Property_ListOfmJawabanUserData = "ListOfmJawabanUserData";
    public String Property_All = Property_intUserAnswer + "," + Property_intUserId + "," + Property_intRoleId + "," + Property_intQuestionId + "," + Property_intTypeQuestionId + "," + Property_bolHaveAnswerList
            + "," + Property_intAnswerId + "," + Property_txtValue + "," + Property_decBobot;
    public tJawabanUserData(){
        super();
    }
}

