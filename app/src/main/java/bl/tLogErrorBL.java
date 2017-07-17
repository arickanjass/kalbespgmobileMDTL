package bl;

import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.tLogErrorData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.tLogErrorDA;

/**
 * Created by Robert on 06/04/2017.
 */

public class tLogErrorBL extends clsMainBL {
    SQLiteDatabase db;
    public void saveData(tLogErrorData dt){
       SQLiteDatabase _db = getDb();
        tLogErrorDA _tLogErrorDA = new tLogErrorDA(_db);
        _tLogErrorDA.SaveLogData(_db, dt);
    }

    public List<tLogErrorData> getAllData(){
        SQLiteDatabase _db = getDb();
        List<tLogErrorData> dt = new tLogErrorDA(_db).getAllData(_db);
        return dt;
    }
    public List<String> deleteFileLogFromDevice(){
        SQLiteDatabase _db = getDb();
        List<String> listFileName =new ArrayList<>();
        File yourDir = new File(new clsHardCode().txtPathApp);
        for (File f : yourDir.listFiles()) {
            if (f.isFile())
                if(f.getName().contains("log_")){
                    f.delete();
                }
            // Do your stuff
        }
        new tLogErrorDA(_db).DeleteAllData(_db);

        return  listFileName;
    }
    public List<String> getFileLogFromDevice(){
        SQLiteDatabase _db = getDb();
        List<String> listFileName =new ArrayList<>();
        File yourDir = new File(new clsHardCode().txtPathApp);
        //File yourDir = new File(sdCardRoot, new clsHardCode().txtPathApp);
        for (File f : yourDir.listFiles()) {
            if (f.isFile())
                if(f.getName().contains("log_")){
                    List<tLogErrorData> listDataLogError= new tLogErrorDA(_db).getAllDataByFileName(_db,f.getName().toString());
                    if(listDataLogError.size()>0){
                        listFileName.add(f.getName().toString());
                    }
                }
            // Do your stuff
        }
        if(listFileName.size()==0){
            for (File f : yourDir.listFiles()) {
                if (f.isFile())
                    if(f.getName().contains("log_")){
                        f.delete();
                    }
                // Do your stuff
            }
            new tLogErrorDA(_db).DeleteAllData(_db);
        }
        return  listFileName;
    }

    public void deleteData(tLogErrorData dt){
        SQLiteDatabase _db=getDb();
        new tLogErrorDA(_db).DeleteAllData(_db);
    }
}
