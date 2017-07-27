package library.spgmobile.common;

/**
 * Created by Rian Andrivani on 3/8/2017.
 */

public class mTypeSubmissionMobile {

    public synchronized String get_txtGrupMasterID() {
        return _txtGrupMasterID;
    }

    public synchronized void set_txtGrupMasterID(String _txtGrupMasterID) {
        this._txtGrupMasterID = _txtGrupMasterID;
    }

    public synchronized String get_txtMasterID() {
        return _txtMasterID;
    }

    public synchronized void set_txtMasterID(String _txtMasterID) {
        this._txtMasterID = _txtMasterID;
    }

    public synchronized String get_txtNamaMasterData() {
        return _txtNamaMasterData;
    }

    public synchronized void set_txtNamaMasterData(String _txtNamaMasterData) {
        this._txtNamaMasterData = _txtNamaMasterData;
    }

    public synchronized String get_txtKeterangan() {
        return _txtKeterangan;
    }

    public synchronized void set_txtKeterangan(String _txtKeterangan) {
        this._txtKeterangan = _txtKeterangan;
    }
    public synchronized String get_intLastActiveSelection() {
        return _intLastActiveSelection;
    }

    public synchronized void set_intLastActiveSelection(String _intLastActiveSelection) {
        this._intLastActiveSelection = _intLastActiveSelection;
    }

    private String _txtGrupMasterID;
    private String _txtMasterID;
    private String _txtNamaMasterData;
    private String _txtKeterangan;
    private String _intLastActiveSelection;

    public mTypeSubmissionMobile(){

    }

    public String Property_txtGrupMasterID="txtGrupMasterID";
    public String Property_txtMasterID="txtMasterID";
    public String Property_txtNamaMasterData="txtNamaMasterData";
    public String Property_txtKeterangan="txtKeterangan";
    public String Property_intLastActiveSelection="intLastActiveSelection";
    public String Property_ListOfmTypeSubmissionMobile="ListOfmTypeSubmissionMobile";
    public String Property_All=Property_txtGrupMasterID+","+Property_txtMasterID+","+Property_txtNamaMasterData+","+
            Property_txtKeterangan+","+Property_intLastActiveSelection;
}