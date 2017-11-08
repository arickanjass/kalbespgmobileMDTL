package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.spgmobile.common.clsLogReceiverHeader_mobile;
import library.spgmobile.common.tNotificationData;
import library.spgmobile.dal.clsLogReceiverHeader_mobileDA;

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
        db.close();
    }

    public int getContactsCountStatus(tNotificationData data){
        SQLiteDatabase db=getDb();
        clsLogReceiverHeader_mobileDA _clsLogReceiverHeader_mobileDA=new clsLogReceiverHeader_mobileDA(db);
        int num = _clsLogReceiverHeader_mobileDA.getContactsCountStatus(db, data.get_guiID());
        db.close();
        return num;
    }
}
