package library.salesforce.common;

/**
 * Created by Robert on 26/04/2017.
 */

public class tVisitPlanRealisasiData {

    private String _txtDataIDRealisasi ;
    private String _intCategoryVisitPlan;
    private String _intDetailID ;
    private String _intHeaderID ;
    private String _intUserID;
    private String _txtOutletCode ;
    private String _txtOutletName ;
    private String _txtBranchCode ;
    private String _dtDate ;
    private String _intBobot ;
    private String _dtDateRealisasi ;
    private String _dtDateRealisasiDevice ;
    private String _txtDesc ;
    private String _txtDescReply ;
    private byte[] _dtPhoto1;
    private byte[] _dtPhoto2;
    private String _txtLong ;
    private String _txtLat ;
    private String _txtAcc ;
    private String _txtLongSource ;
    private String _txtLatSource ;
    private String _intDistance ;
    private String _bitActive ;
    private String _txtRoleId;
    private String _intPush;
    private String _intSubmit;
    private String _intCheckout;
    private String _dateCheckout;

    public String Property_txtDataIDRealisasi = "txtDataIDRealisasi";
    public String Property_intCategoryVisitPlan = "intCategoryVisitPlan";
    public String Property_intDetailID = "intDetailID";
    public String Property_intHeaderID = "intHeaderID";
    public String Property_intUserID = "intUserID";
    public String Property_txtOutletCode = "txtOutletCode";
    public String Property_txtOutletName = "txtOutletName";
    public String Property_txtBranchCode = "txtBranchCode";
    public String Property_dtDate = "dtDate";
    public String Property_intBobot = "intBobot";
    public String Property_dtDateRealisasi = "dtDateRealisasi";
    public String Property_dtDateRealisasiDevice = "dtDateRealisasiDevice";
    public String Property_txtDesc = "txtDesc";
    public String Property_txtDescReply = "txtDescReply";
    public String Property_dtPhoto1 = "dtPhoto1";
    public String Property_dtPhoto2 = "dtPhoto2";
    public String Property_txtLong = "txtLong";
    public String Property_txtLat = "txtLat";
    public String Property_txtAcc = "txtAcc";
    public String Property_txtLongSource = "txtLongSource";
    public String Property_txtLatSource = "txtLatSource";
    public String Property_intDistance = "intDistance";
    public String Property_bitActive = "bitActive";
    public String Property_txtRoleId = "txtRoleId";
    public String Property_intSubmit = "intSubmit";
    public String Property_intPush = "intPush";
    public String Property_intCheckOut = "intCheckout";
    public String Property_dateCheckOut = "dateCheckout";



    public String Property_All=Property_txtDataIDRealisasi + "," +
            Property_intCategoryVisitPlan + "," +
            Property_intDetailID + "," +
            Property_intHeaderID + "," +
            Property_intUserID + "," +
            Property_txtOutletCode + "," +
            Property_txtOutletName + "," +
            Property_txtBranchCode + "," +
            Property_dtDate + "," +
            Property_intBobot + "," +
            Property_dtDateRealisasi + "," +
            Property_dtDateRealisasiDevice + "," +
            Property_txtDesc + "," +
            Property_txtDescReply + "," +
            Property_dtPhoto1 + "," +
            Property_dtPhoto2 + "," +
            Property_txtLong + "," +
            Property_txtLat + "," +
            Property_txtAcc + "," +
            Property_txtLongSource + "," +
            Property_txtLatSource + "," +
            Property_intDistance + "," +
            Property_bitActive + "," +
            Property_txtRoleId + "," +
            Property_intSubmit + "," +
            Property_intPush+ "," +
            Property_intCheckOut+ "," +
            Property_dateCheckOut;

    public synchronized String get_intCheckout() {
        return _intCheckout;
    }

    public synchronized void set_intCheckout(String _intCheckout) {
        this._intCheckout = _intCheckout;
    }

    public synchronized String get_dateCheckout() {
        return _dateCheckout;
    }

    public synchronized void set_dateCheckout(String _dateCheckout) {
        this._dateCheckout = _dateCheckout;
    }

    public synchronized String get_intPush(){
        return _intPush;
    }
    public synchronized void set_intPush(String _intPush){
        this._intPush = _intPush;
    }
    public synchronized String get_intSubmit(){
        return _intSubmit;
    }
    public synchronized void set_intSubmit(String _intSubmit){
        this._intSubmit = _intSubmit;
    }

    public synchronized String get_txtDataIDRealisasi() {
        return _txtDataIDRealisasi;
    }

    public synchronized void set_txtDataIDRealisasi(String _txtDataIDRealisasi) {
        this._txtDataIDRealisasi = _txtDataIDRealisasi;
    }

    public synchronized String get_intCategoryVisitPlan(){
        return _intCategoryVisitPlan;
    }
    public synchronized void set_intCategoryVisitPlan(String _intCategoryVisitPlan){
        this._intCategoryVisitPlan = _intCategoryVisitPlan;
    }

    public synchronized String get_txtRoleId(){
        return _txtRoleId;
    }
    public synchronized void set_txtRoleId(String _txtRoleId){
        this._txtRoleId = _txtRoleId;
    }

    public synchronized String get_intDetailID() {
        return _intDetailID;
    }

    public synchronized void set_intDetailID(String _intDetailID) {
        this._intDetailID = _intDetailID;
    }
    public synchronized String get_intHeaderID() {
        return _intHeaderID;
    }

    public synchronized void set_intHeaderID(String _intHeaderID) {
        this._intHeaderID = _intHeaderID;
    }
    public synchronized String get_intUserID(){
        return _intUserID;
    }
    public synchronized void set_intUserID(String _intUserID){
        this._intUserID = _intUserID;
    }
    public synchronized String get_txtOutletCode() {
        return _txtOutletCode;
    }

    public synchronized void set_txtOutletCode(String _txtOutletCode) {
        this._txtOutletCode = _txtOutletCode;
    }
    public synchronized String get_txtOutletName() {
        return _txtOutletName;
    }

    public synchronized void set_txtOutletName(String _txtOutletName) {
        this._txtOutletName = _txtOutletName;
    }
    public synchronized String get_txtBranchCode() {
        return _txtBranchCode;
    }

    public synchronized void set_txtBranchCode(String _txtBranchCode) {
        this._txtBranchCode = _txtBranchCode;
    }
    public synchronized String get_dtDate() {
        return _dtDate;
    }

    public synchronized void set_dtDate(String _dtDate) {
        this._dtDate = _dtDate;
    }
    public synchronized String get_dtDateRealisasiDevice() {
        return _dtDateRealisasiDevice;
    }

    public synchronized void set_dtDateRealisasiDevice(String _dtDateRealisasiDevice) {
        this._dtDateRealisasiDevice = _dtDateRealisasiDevice;
    }
    public synchronized String get_dtDateRealisasi() {
        return _dtDateRealisasi;
    }

    public synchronized void set_dtDateRealisasi(String _dtDateRealisasi) {
        this._dtDateRealisasi = _dtDateRealisasi;
    }
    public synchronized String get_txtDesc() {
        return _txtDesc;
    }

    public synchronized void set_txtDesc(String _txtDesc) {
        this._txtDesc = _txtDesc;
    }
    public synchronized String get_txtDescReply() {
        return _txtDescReply;
    }

    public synchronized void set_txtDescReply(String _txtDescReply) {
        this._txtDescReply = _txtDescReply;
    }
    public synchronized String get_intBobot() {
        return _intBobot;
    }

    public synchronized void set_intBobot(String _intBobot) {
        this._intBobot = _intBobot;
    } public synchronized byte[] get_dtPhoto1() {
        return _dtPhoto1;
    }

    public synchronized void set_dtPhoto1(byte[] _dtPhoto1) {
        this._dtPhoto1 = _dtPhoto1;
    }
    public synchronized byte[] get_dtPhoto2() {
        return _dtPhoto2;
    }

    public synchronized void set_dtPhoto2(byte[] _dtPhoto2) {
        this._dtPhoto2 = _dtPhoto2;
    }
    public synchronized String get_txtLong() {
        return _txtLong;
    }

    public synchronized void set_txtLong(String _txtLong) {
        this._txtLong = _txtLong;
    }
    public synchronized String get_txtLat() {
        return _txtLat;
    }

    public synchronized void set_txtLat(String _txtLat) {
        this._txtLat = _txtLat;
    } public synchronized String get_txtAcc() {
        return _txtAcc;
    }

    public synchronized void set_txtAcc(String _txtAcc) {
        this._txtAcc = _txtAcc;
    }
    public synchronized String get_txtLongSource() {
        return _txtLongSource;
    }

    public synchronized void set_txtLongSource(String _txtLongSource) {
        this._txtLongSource = _txtLongSource;
    }
    public synchronized String get_txtLatSource() {
        return _txtLatSource;
    }

    public synchronized void set_txtLatSource(String _txtLatSource) {
        this._txtLatSource = _txtLatSource;
    }
    public synchronized String get_intDistance() {
        return _intDistance;
    }

    public synchronized void set_intDistance(String _intDistance) {
        this._intDistance = _intDistance;
    }
    public synchronized String get_bitActive() {
        return _bitActive;
    }

    public synchronized void set_bitActive(String _bitActive) {
        this._bitActive = _bitActive;
    }


}
