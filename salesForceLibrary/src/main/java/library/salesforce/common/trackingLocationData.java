package library.salesforce.common;

/**
 * Created by Rian Andrivani on 5/15/2017.
 */

public class trackingLocationData {
    public synchronized String get_intId() {
        return _intId;
    }

    public synchronized void set_intId(String _intId) {
        this._intId = _intId;
    }

    public synchronized String get_txtLongitude() {
        return _txtLongitude;
    }

    public synchronized void set_txtLongitude(String _txtLongitude) {
        this._txtLongitude = _txtLongitude;
    }

    public synchronized String get_txtLatitude() {
        return _txtLatitude;
    }

    public synchronized void set_txtLatitude(String _txtLatitude) {
        this._txtLatitude = _txtLatitude;
    }

    public synchronized String get_txtAccuracy() {
        return _txtAccuracy;
    }

    public synchronized void set_txtAccuracy(String _txtAccuracy) {
        this._txtAccuracy = _txtAccuracy;
    }

    public synchronized String get_txtTime() {
        return _txtTime;
    }

    public synchronized void set_txtTime(String _txtTime) {
        this._txtTime = _txtTime;
    }

    private String _intId;
    private String _txtLongitude;
    private String _txtLatitude;
    private String _txtAccuracy;
    private String _txtTime;

    public String Property_intId = "intId";
    public String Property_txtLongitude = "txtLongitude";
    public String Property_txtLatitude = "txtLatitude";
    public String Property_txtAccuracy = "txtAccuracy";
    public String Property_txtTime = "Time";
    public String Property_ListOftrackingLocation = "ListOftrackingLocation";
    public String Property_All =
            Property_intId + "," +
            Property_txtLongitude + "," +
            Property_txtLatitude + "," +
            Property_txtAccuracy + "," +
                    Property_txtTime;

    public trackingLocationData() {
        super();
    }

    public trackingLocationData(String _intId, String _txtLongitude,
                                String _txtLatitude, String _txtAccuracy, String _txtTime) {
        this._intId = _intId;
        this._txtLongitude = _txtLongitude;
        this._txtLatitude = _txtLatitude;
        this._txtAccuracy = _txtAccuracy;
        this._txtTime = _txtTime;
    }
}
