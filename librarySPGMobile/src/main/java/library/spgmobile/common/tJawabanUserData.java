package library.spgmobile.common;

/**
 * Created by Dewi Oktaviani on 03/05/2017.
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
    private String _intSubmit;
    private String _intSync;
    private String _intNik;
    private byte[] _ptQuiz;
    private byte[] _txtFileQuiz;


    public byte[] get_txtFileQuiz() {
        return _txtFileQuiz;
    }

    public void set_txtFileQuiz(byte[] _txtFileQuiz) {
        this._txtFileQuiz = _txtFileQuiz;
    }

    public byte[] get_ptQuiz() {
        return _ptQuiz;
    }

    public void set_ptQuiz(byte[] _ptQuiz) {
        this._ptQuiz = _ptQuiz;
    }

    public String get_intNik() {
        return _intNik;
    }

    public void set_intNik(String _intNik) {
        this._intNik = _intNik;
    }

    public String get_intSubmit() {
        return _intSubmit;
    }

    public void set_intSubmit(String _intSubmit) {
        this._intSubmit = _intSubmit;
    }

    public String get_intSync() {
        return _intSync;
    }

    public void set_intSync(String _intSync) {
        this._intSync = _intSync;
    }

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
    public String Property_intSubmit = "intSubmit";
    public String Property_intSync = "intSync";
    public String Property_intNik = "intNik";
    public String Property_ptQuiz = "ptQuiz";
    public String Property_txtFileQuiz = "txtFileQuiz";
    public String Property_ListOftJawabanUserData = "ListOftJawabanUserData";
    public String Property_All = Property_intUserAnswer + "," + Property_intUserId + "," + Property_intNik + "," + Property_intRoleId + "," + Property_intQuestionId + "," + Property_intTypeQuestionId + "," + Property_bolHaveAnswerList
            + "," + Property_intAnswerId + "," + Property_txtValue + "," + Property_ptQuiz + "," + Property_txtFileQuiz + "," + Property_decBobot + "," + Property_intSubmit + "," + Property_intSync;
    public tJawabanUserData(){
        super();
    }
}

