package library.spgmobile.common;

import library.spgmobile.common.APIData;

/**
 * Created by aan.junianto on 31/10/2017.
 */

public class tLogDownloadData extends APIData {
    public String _intId;

    public String get_intId() {
        return _intId;
    }

    public void set_intId(String _intId) {
        this._intId = _intId;
    }

    public String get_txtModuleName() {
        return _txtModuleName;
    }

    public void set_txtModuleName(String _txtModuleName) {
        this._txtModuleName = _txtModuleName;
    }

    public String get_dtLastDownload() {
        return _dtLastDownload;
    }

    public void set_dtLastDownload(String _dtLastDownload) {
        this._dtLastDownload = _dtLastDownload;
    }

    public String _txtModuleName;
    public String _dtLastDownload;

    public tLogDownloadData() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String Property_intId="intId";
    public String Property_txtModuleName="txtModuleName";
    public String Property_dtLastDownload="dtLastDownload";
    public String Property_All= Property_intId +","+Property_txtModuleName+","+
            Property_dtLastDownload;

}
