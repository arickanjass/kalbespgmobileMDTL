package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.mDownloadMasterData_mobileData;
import library.spgmobile.common.tLogDownloadData;
import library.spgmobile.dal.mDownloadMasterData_mobileDA;
import library.spgmobile.dal.tLogDownloadDA;

/**
 * Created by aan.junianto on 1/11/2017.
 */

public class tLogDownloadBL extends clsMainBL {
    public List<tLogDownloadData> GetAllData(){
        SQLiteDatabase db=getDb();
        tLogDownloadDA _tLogDownloadDA=new tLogDownloadDA(db);
        List<tLogDownloadData>ListData=_tLogDownloadDA.getAllData(db);
        db.close();
        return ListData;
    }
    public void SaveData(List<tLogDownloadData> Listdata) {
        SQLiteDatabase db = getDb();
        tLogDownloadDA _tLogDownloadDA=new tLogDownloadDA(db);
//        _tLogDownloadDA.DeleteAllDataMConfig(db);
        for (tLogDownloadData data : Listdata) {
            _tLogDownloadDA.SaveDataMConfig(db, data);
        }
        db.close();
    }
    public tLogDownloadData getDataById(String id) {
        SQLiteDatabase db = getDb();
        tLogDownloadDA _tLogDownloadDA=new tLogDownloadDA(db);
        tLogDownloadData dt = new tLogDownloadData();
            dt = _tLogDownloadDA.getData(db, id);
        db.close();
        return dt;
    }
}
