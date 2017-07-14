package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.clsFileAttach_mobile;
import library.salesforce.common.clsLogReceiverDetail_mobile;
import library.salesforce.common.clsLogReceiverHeader_mobile;
import library.salesforce.common.tNotificationData;
import library.salesforce.dal.clsLogReceiverDetail_mobileDA;
import library.salesforce.dal.clsLogReceiverHeader_mobileDA;

/**
 * Created by aan.junianto on 6/07/2017.
 */

public class clsLogReceiverDetail_mobileBL extends clsMainBL {
    public void saveData(List<clsLogReceiverDetail_mobile> Listdata){
        SQLiteDatabase db=getDb();
        clsLogReceiverDetail_mobileDA _clsLogReceiverDetail_mobileDA=new clsLogReceiverDetail_mobileDA(db);
        for(clsLogReceiverDetail_mobile data:Listdata){
            _clsLogReceiverDetail_mobileDA.SaveDataMConfig(db, data);
        }
    }

    public int getContactsCountStatus(clsFileAttach_mobile data){
        SQLiteDatabase db=getDb();
        clsLogReceiverDetail_mobileDA _clsLogReceiverDetail_mobileDA=new clsLogReceiverDetail_mobileDA(db);
        int num = _clsLogReceiverDetail_mobileDA.getContactsCountStatus(db, data.get_txtIDFile());
        return num;
    }
}
