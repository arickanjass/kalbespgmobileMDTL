package library.spgmobile.common;

/**
 * Created by rhezaTesar on 10/10/2016.
 */

public class clsSpinner {
    private String ID;
    private String Nama;

    public clsSpinner(String ID, String nama) {
        this.ID = ID;
        Nama = nama;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String toString(){
        return Nama;
    }
}