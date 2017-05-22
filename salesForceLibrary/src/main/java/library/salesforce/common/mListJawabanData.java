package library.salesforce.common;

/**
 * Created by Dewi Oktaviani on 03/05/2017.
 */

public class mListJawabanData {
    private String _intListAnswerId;
    private String _intQuestionId;
    private String _intTypeQuestionId;
    private String _txtKey;
    private String _txtValue;

    public synchronized String get_intListAnswerId() {
        return _intListAnswerId;
    }

    public synchronized void set_intListAnswerId(String _intListAnswerId) {
        this._intListAnswerId = _intListAnswerId;
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

    public synchronized String get_txtKey() {
        return _txtKey;
    }

    public synchronized void set_txtKey(String _txtKey) {
        this._txtKey = _txtKey;
    }

    public synchronized String get_txtValue() {
        return _txtValue;
    }

    public synchronized void set_txtValue(String _txtValue) {
        this._txtValue = _txtValue;
    }

    public String Property_intListAnswerId = "intListAnswerId";
    public String Property_intQuestionId = "intQuestionId";
    public String Property_intTypeQuestionId = "intTypeQuestionId";
    public String Property_txtKey = "txtKey";
    public String Property_txtValue = "txtValue";
    public String Property_All = Property_intListAnswerId + "," + Property_intQuestionId + "," + Property_intTypeQuestionId + ","
            + Property_txtKey + "," + Property_txtValue;
    public String Property_ListOfmListJawabanData = "ListOfmListJawabanData";
    public mListJawabanData(){
        super();
    }
}
