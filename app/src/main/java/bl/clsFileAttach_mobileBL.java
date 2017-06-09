package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.clsFileAttach_mobile;
import library.salesforce.common.tNotificationData;
import library.salesforce.dal.clsFileAttach_mobileDA;
import library.salesforce.dal.tNotificationDA;

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
    }
}
