package library.spgmobile.common;

/**
 * Created by Robert on 26/04/2017.
 */

public class tVisitPlanHeader_MobileData {

    private String _intHeaderId;
    private String _txtUserId;
    private String _txtPeriode;
    private String _intUnplan;
    private String _txtGuIdUnplan;
    private String _txtBranchCode;
    private String _bitActive;
    private String _dtStart;
    private String _dtEnd;
    private String _intSumBobot;
    private String _intRealisasi;
    private String _intSubmit;
    private String _intPush;
    private String _txtRoleId;
    public String get_txtRoleId() {
        return _txtRoleId;
    }

    public void set_txtRoleId(String _txtRoleId) {
        this._txtRoleId = _txtRoleId;
    }


    public synchronized String get_intSubmit() {
        return _intSubmit;
    }

    public synchronized void set_intSubmit(String _intSubmit) {
        this._intSubmit = _intSubmit;
    }
    public synchronized String get_intPush() {
        return _intPush;
    }

    public synchronized void set_intPush(String _intPush) {
        this._intPush = _intPush;
    }
    public synchronized String get_intHeaderId() {
        return _intHeaderId;
    }

    public synchronized void set_intHeaderId(String _intHeaderId) {
        this._intHeaderId = _intHeaderId;
    }

    public synchronized String get_txtUserId() {
        return _txtUserId;
    }

    public synchronized void set_txtUserId(String _txtUserId) {
        this._txtUserId = _txtUserId;
    }

    public synchronized String get_txtPeriode() {
        return _txtPeriode;
    }

    public synchronized void set_txtPeriode(String _txtPeriode) {
        this._txtPeriode = _txtPeriode;
    }

    public synchronized String get_intUnplan() {
        return _intUnplan;
    }

    public synchronized void set_intUnplan(String _intUnplan) {
        this._intUnplan = _intUnplan;
    }

    public synchronized String get_txtGuIdUnplan() {
        return _txtGuIdUnplan;
    }

    public synchronized void set_txtGuidUnplan(String _txtGuIdUnplan) {
        this._txtGuIdUnplan = _txtGuIdUnplan;
    }

    public synchronized String get_txtBranchCode() {
        return _txtBranchCode;
    }

    public synchronized void set_txtBranchCode(String _txtBranchCode) {
        this._txtBranchCode = _txtBranchCode;
    }

    public synchronized String get_bitActive() {
        return _bitActive;
    }

    public synchronized void set_bitActive(String _bitActive) {
        this._bitActive = _bitActive;
    }

    public synchronized String get_dtStart() {
        return _dtStart;
    }

    public synchronized void set_dtStart(String _dtStart) {
        this._dtStart = _dtStart;
    }

    public synchronized String get_dtEnd() {
        return _dtEnd;
    }

    public synchronized void set_dtEnd(String _dtEnd) {
        this._dtEnd = _dtEnd;
    }

    public synchronized String get_intSumBobot() {
        return _intSumBobot;
    }

    public synchronized void set_intSumBobot(String _intSumBobot) {
        this._intSumBobot = _intSumBobot;
    }

    public synchronized String get_intRealisasi() {
        return _intRealisasi;
    }

    public synchronized void set_intRealisasi(String _intRealisasi) {
        this._intRealisasi = _intRealisasi;
    }

    public String Property_intHeaderId = "intHeaderId";
    public String Property_txtUserId = "txtUserId";
    public String Property_txtPeriode = "txtPeriode";
    public String Property_intUnplan = "intUnplan";
    public String Property_txtGuIdUnplan = "txtGuIdUnplan";
    public String Property_txtBranchCode = "txtBranchCode";
    public String Property_bitActive = "bitActive";
    public String Property_dtStart = "dtStart";
    public String Property_dtEnd = "dtEnd";
    public String Property_intSumBobot = "intSumBobot";
    public String Property_intRealisasi = "intRealisasi";
    public String Property_intSubmit = "intSubmit";
    public String Property_intPush = "intPush";
    public String Property_txtRoleId = "txtRoleId";
    public String Property_ListOftVisitPlanHeader_MobileData = "ListOftVisitplanHeaderData";
    public String Property_All =
            Property_intHeaderId + "," +
                    Property_txtUserId + "," +
                    Property_txtPeriode+ "," +
                    Property_intUnplan+ "," +
                    Property_txtGuIdUnplan + "," +
                    Property_txtBranchCode+ "," +
                    Property_bitActive+ "," +
                    Property_dtStart+ "," +
                    Property_dtEnd+ "," +
                    Property_intSumBobot+ "," +
                    Property_intRealisasi+"," +
                    Property_intSubmit+ "," +
                    Property_intPush;

    public tVisitPlanHeader_MobileData(){
        super();
    }

}
