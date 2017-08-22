package bl;

import android.database.sqlite.SQLiteDatabase;

import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tAttendanceUserData;
import library.spgmobile.dal.tAbsenUserDA;
import library.spgmobile.dal.tAttendanceUserDA;

/**
 * Created by aan.junianto on 22/08/2017.
 */

public class tAttendanceUserBL extends clsMainBL {
    public void saveData(tAttendanceUserData data){
        SQLiteDatabase db=getDb();
        tAttendanceUserDA _tAttendanceUserDA=new tAttendanceUserDA(db);
        _tAttendanceUserDA.SaveDatatAbsenUserData(db, data);
    }
    public tAttendanceUserData getDataCheckInActive(){
        SQLiteDatabase db=getDb();
        tAttendanceUserDA _tAttendanceUserDA=new tAttendanceUserDA(db);
        tAttendanceUserData dt=new tAttendanceUserData();
        dt=_tAttendanceUserDA.getDataCheckInActive(db);
        return dt;
    }
    public int  getContactsCount(){
        SQLiteDatabase db=getDb();
        tAttendanceUserDA _tAttendanceUserDA=new tAttendanceUserDA(db);
        return _tAttendanceUserDA.getContactsCount(db);
    }
}
