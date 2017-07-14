package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.clsFileAttach_mobile;
import library.salesforce.common.clsLogReceiverHeader_mobile;
import library.salesforce.common.tNotificationData;
import library.salesforce.dal.clsFileAttach_mobileDA;
import library.salesforce.dal.clsLogReceiverHeader_mobileDA;

/**
 * Created by aan.junianto on 6/07/2017.
 */

public class clsLogReceiverHeader_mobileBL extends clsMainBL {
    public void saveData(List<clsLogReceiverHeader_mobile> Listdata){
        SQLiteDatabase db=getDb();
        clsLogReceiverHeader_mobileDA _clsLogReceiverHeader_mobileDA=new clsLogReceiverHeader_mobileDA(db);
        for(clsLogReceiverHeader_mobile data:Listdata){
            _clsLogReceiverHeader_mobileDA.SaveDataMConfig(db, data);
        }
    }

    public int getContactsCountStatus(tNotificationData data){
        SQLiteDatabase db=getDb();
        clsLogReceiverHeader_mobileDA _clsLogReceiverHeader_mobileDA=new clsLogReceiverHeader_mobileDA(db);
        int num = _clsLogReceiverHeader_mobileDA.getContactsCountStatus(db, data.get_guiID());
        return num;
    }
}
