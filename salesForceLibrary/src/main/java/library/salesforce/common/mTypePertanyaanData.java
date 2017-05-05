package library.salesforce.common;

/**
 * Created by XSIS on 03/05/2017.
 */

public class mTypePertanyaanData {
    private String _intTypeQuestionId;
    private String _txtTypeQuestion;

    public synchronized String get_intTypeQuestionId() {
        return _intTypeQuestionId;
    }

    public synchronized void set_intTypeQuestionId(String _intTypeQuestionId) {
        this._intTypeQuestionId = _intTypeQuestionId;
    }

    public synchronized String get_txtTypeQuestion() {
        return _txtTypeQuestion;
    }

    public synchronized void set_txtTypeQuestion(String _txtTypeQuestion) {
        this._txtTypeQuestion = _txtTypeQuestion;
    }

    public String Property_intTypeQuestionId = "intTypeQuestionId";
    public String Property_txtTypeQuestion = "txtTypeQuestion";
    public String Property_ListOfmTypePertanyaanData = "ListOfmTypePertanyaanData";
    public String Property_All = Property_intTypeQuestionId + "," + Property_txtTypeQuestion;
    public mTypePertanyaanData(){
        super();
    }
}
