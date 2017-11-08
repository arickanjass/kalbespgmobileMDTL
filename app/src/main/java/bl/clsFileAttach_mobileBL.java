package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.spgmobile.common.clsFileAttach_mobile;
import library.spgmobile.dal.clsFileAttach_mobileDA;

/**
 * Created by aan.junianto on 8/06/2017.
 */

public class clsFileAttach_mobileBL extends clsMainBL {
    public void saveData(List<clsFileAttach_mobile> Listdata){
        SQLiteDatabase db=getDb();
        clsFileAttach_mobileDA _clsFileAttach_mobileDA=new clsFileAttach_mobileDA(db);
        for(clsFileAttach_mobile data:Listdata){
            _clsFileAttach_mobileDA.SaveDataMConfig(db, data);
        }
        db.close();
    }

    public int  getContactsCount(){
        SQLiteDatabase db=getDb();
        clsFileAttach_mobileDA _clsFileAttach_mobileDA=new clsFileAttach_mobileDA(db);
        int intCount=_clsFileAttach_mobileDA.getContactsCount(db);
        db.close();
        return intCount;
    }

    public List<clsFileAttach_mobile> GetAllData(){
        SQLiteDatabase db=getDb();
        clsFileAttach_mobileDA _clsFileAttach_mobileDA=new clsFileAttach_mobileDA(db);
        List<clsFileAttach_mobile> listData=_clsFileAttach_mobileDA.getAllData(db);
        db.close();
        return listData;
    }

    public clsFileAttach_mobile getData(String id){
        clsFileAttach_mobile data=new clsFileAttach_mobile();
        clsFileAttach_mobileDA _clsFileAttach_mobileDA=new clsFileAttach_mobileDA(db);
            data=_clsFileAttach_mobileDA.getData(db, id);
        db.close();
        return data;
    }
    public List<clsFileAttach_mobile> getDataByIdHeader(String id){
        List<clsFileAttach_mobile> listData = new ArrayList<>();
        clsFileAttach_mobileDA _clsFileAttach_mobileDA=new clsFileAttach_mobileDA(db);
        listData=_clsFileAttach_mobileDA.getDataByIdHeader(db, id);
        db.close();
        return listData;
    }
}
