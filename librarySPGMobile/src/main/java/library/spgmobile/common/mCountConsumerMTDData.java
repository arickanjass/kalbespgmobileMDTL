package library.spgmobile.common;

public class mCountConsumerMTDData {
    private String jumlah;
    private String txtRegionName;
    private String txtBranchCode;
    private String txtBranchName;
    private String txtOutletCode;
    private String txtOutletName;


    public String Property_jumlah = "jumlah";
    public String Property_txtRegionName = "txtRegionName";
    public String Property_txtBranchCode = "txtBranchCode";
    public String Property_txtBranchName = "txtBranchName";
    public String Property_txtOutletCode = "txtOutletCode";
    public String Property_txtOutletName = "txtOutletName";

    public String Property_ALL = Property_jumlah + "," +
            Property_txtRegionName + "," +
            Property_txtBranchCode + "," +
            Property_txtBranchName + "," +
            Property_txtOutletCode + "," +
            Property_txtOutletName;

    public mCountConsumerMTDData() {

    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTxtRegionName() {
        return txtRegionName;
    }

    public void setTxtRegionName(String txtRegionName) {
        this.txtRegionName = txtRegionName;
    }

    public String getTxtBranchCode() {
        return txtBranchCode;
    }

    public void setTxtBranchCode(String txtBranchCode) {
        this.txtBranchCode = txtBranchCode;
    }

    public String getTxtBranchName() {
        return txtBranchName;
    }

    public void setTxtBranchName(String txtBranchName) {
        this.txtBranchName = txtBranchName;
    }

    public String getTxtOutletCode() {
        return txtOutletCode;
    }

    public void setTxtOutletCode(String txtOutletCode) {
        this.txtOutletCode = txtOutletCode;
    }

    public String getTxtOutletName() {
        return txtOutletName;
    }

    public void setTxtOutletName(String txtOutletName) {
        this.txtOutletName = txtOutletName;
    }
}