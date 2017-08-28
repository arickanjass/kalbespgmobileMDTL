package library.spgmobile.common;

/**
 * Created by aan.junianto on 6/07/2017.
 */

public class clsLogReceiverHeader_mobile {

    public synchronized String getTxtIdLogReceiver() {
        return txtIdLogReceiver;
    }

    public synchronized void setTxtIdLogReceiver(String txtIdLogReceiver) {
        this.txtIdLogReceiver = txtIdLogReceiver;
    }

    public synchronized String getTxtStatus() {
        return txtStatus;
    }

    public synchronized void setTxtStatus(String txtStatus) {
        this.txtStatus = txtStatus;
    }

    public synchronized String getTxtNIK() {
        return txtNIK;
    }

    public synchronized void setTxtNIK(String txtNIK) {
        this.txtNIK = txtNIK;
    }

    public synchronized String getTxtUSerName() {
        return txtUSerName;
    }

    public synchronized void setTxtUSerName(String txtUSerName) {
        this.txtUSerName = txtUSerName;
    }

    public synchronized String getDtInserted() {
        return dtInserted;
    }

    public synchronized void setDtInserted(String dtInserted) {
        this.dtInserted = dtInserted;
    }

    public synchronized String getDtUpdated() {
        return dtUpdated;
    }

    public synchronized void setDtUpdated(String dtUpdated) {
        this.dtUpdated = dtUpdated;
    }

    public synchronized String getIntActive() {
        return intActive;
    }

    public synchronized void setIntActive(String intActive) {
        this.intActive = intActive;
    }

    public synchronized String getIntSubmit() {
        return intSubmit;
    }

    public synchronized void setIntSubmit(String intSubmit) {
        this.intSubmit = intSubmit;
    }

    public synchronized String getIntSync() {
        return intSync;
    }

    public synchronized void setIntSync(String intSync) {
        this.intSync = intSync;
    }

    public synchronized String getTxtIdReceiver() {
        return txtIdReceiver;
    }

    public synchronized void setTxtIdReceiver(String txtIdReceiver) {
        this.txtIdReceiver = txtIdReceiver;
    }

    public synchronized String getTxtGuidLogin() {
        return txtGuidLogin;
    }

    public synchronized void setTxtGuidLogin(String txtGuidLogin) {
        this.txtGuidLogin = txtGuidLogin;
    }

    public synchronized String getTxtIdHeaderNotif() {
        return txtIdHeaderNotif;
    }

    public synchronized void setTxtIdHeaderNotif(String txtIdHeaderNotif) {
        this.txtIdHeaderNotif = txtIdHeaderNotif;
    }
    public String getTxtRoleId() {
        return txtRoleId;
    }

    public void setTxtRoleId(String txtRoleId) {
        this.txtRoleId = txtRoleId;
    }

    private String txtIdLogReceiver;
    private String txtStatus;
    private String txtNIK;
    private String txtUSerName;
    private String dtInserted;
    private String dtUpdated;
    private String intActive;
    private String intSubmit;
    private String intSync;
    private String txtIdReceiver;
    private String txtGuidLogin;
    private String txtIdHeaderNotif;
    private String txtRoleId;


    public String Property_txtIdLogReceiver = "txtIdLogReceiver";
    public String Property_txtStatus = "txtStatus";
    public String Property_txtNIK = "txtNIK";
    public String Property_txtUSerName = "txtUSerName"; 
    public String Property_dtInserted = "dtInserted";
    public String Property_dtUpdated = "dtUpdated";
    public String Property_intActive = "intActive";
    public String Property_intSubmit = "intSubmit";
    public String Property_intSync = "intSync";
    public String Property_txtIdReceiver = "txtIdReceiver";
    public String Property_txtGuidLogin = "txtGuidLogin";
    public String Property_txtIdHeaderNotif = "txtIdHeaderNotif";
    public String Property_txtRoleId = "txtRoleId";

    public String Property_ListOfclsLogReceiverHeader_mobile ="ListOfclsLogReceiverHeader_mobile";

    public String PropertyAll=Property_txtIdLogReceiver+","+Property_txtStatus+","+Property_txtNIK+","+Property_txtUSerName+","+Property_dtInserted+","+
            Property_dtUpdated+","+Property_intActive+","+Property_intSubmit+","+Property_intSync+","+Property_txtIdReceiver+","+Property_txtGuidLogin+","+
            Property_txtIdHeaderNotif+","+Property_txtRoleId;

}
