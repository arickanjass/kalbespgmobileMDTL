package library.spgmobile.common;

/**
 * Created by aan.junianto on 19/01/2018.
 */

public class mTipeSumberData {
    public String txtSumberDataID;
    public String txtCabangID;

    public String getTxtSumberDataID() {
        return txtSumberDataID;
    }

    public void setTxtSumberDataID(String txtSumberDataID) {
        this.txtSumberDataID = txtSumberDataID;
    }

    public String getTxtCabangID() {
        return txtCabangID;
    }

    public void setTxtCabangID(String txtCabangID) {
        this.txtCabangID = txtCabangID;
    }

    public String getTipeSumberDataID() {
        return TipeSumberDataID;
    }

    public void setTipeSumberDataID(String tipeSumberDataID) {
        TipeSumberDataID = tipeSumberDataID;
    }

    public String getTxtNamaInstitusi() {
        return txtNamaInstitusi;
    }

    public void setTxtNamaInstitusi(String txtNamaInstitusi) {
        this.txtNamaInstitusi = txtNamaInstitusi;
    }

    public String getTxtAlamat() {
        return txtAlamat;
    }

    public void setTxtAlamat(String txtAlamat) {
        this.txtAlamat = txtAlamat;
    }

    public String getTxtNamaPropinsi() {
        return txtNamaPropinsi;
    }

    public void setTxtNamaPropinsi(String txtNamaPropinsi) {
        this.txtNamaPropinsi = txtNamaPropinsi;
    }

    public String getTxtNamaCabang() {
        return txtNamaCabang;
    }

    public void setTxtNamaCabang(String txtNamaCabang) {
        this.txtNamaCabang = txtNamaCabang;
    }

    public String getTxtLatitude() {
        return txtLatitude;
    }

    public void setTxtLatitude(String txtLatitude) {
        this.txtLatitude = txtLatitude;
    }

    public String getTxtLongitude() {
        return txtLongitude;
    }

    public void setTxtLongitude(String txtLongitude) {
        this.txtLongitude = txtLongitude;
    }

    public String getTxtAcc() {
        return txtAcc;
    }

    public void setTxtAcc(String txtAcc) {
        this.txtAcc = txtAcc;
    }

    public String TipeSumberDataID;
    public String txtNamaInstitusi;
    public String txtAlamat;
    public String txtNamaPropinsi;
    public String txtNamaCabang;
    public String txtLatitude;
    public String txtLongitude;
    public String txtAcc;


    public String Property_TipeSumberDataID="tipeSumberDataID";
    public String Property_txtNamaInstitusi="txtNamaInstitusi";
    public String Property_txtAlamat="txtAlamat";
    public String Property_txtNamaPropinsi="txtNamaPropinsi";
    public String Property_txtNamaCabang="txtNamaCabang";
    public String Property_txtLatitude="txtLatitude";
    public String Property_txtLongitude="txtLongitude";
    public String Property_txtAcc="txtAcc";
    public String Property_ListOfmTipeSumberData="ListOfmTipeSumberData";
    public String Property_All=Property_TipeSumberDataID +","+ Property_txtNamaInstitusi
            +","+ Property_txtAlamat
            +","+ Property_txtNamaPropinsi
            +","+ Property_txtNamaCabang
            +","+ Property_txtLatitude
            +","+ Property_txtLongitude
            +","+ Property_txtAcc;
}
