package com.kalbenutritionals.app.kalbespgmobile.Utils;

import android.content.Context;
import android.util.Log;

import org.acra.ACRA;
import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.collector.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import bl.tLogErrorBL;
import bl.tUserLoginBL;
import library.spgmobile.common.tLogErrorData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;

/**
 * Created by XSIS on 15/03/2017.
 */

public class LocalReportSender implements ReportSender {

    private final Map<ReportField, String> mMapping = new HashMap<ReportField, String>();
    private FileWriter crashReport;
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String fileName = "log_"+dateFormat.format(date)+".txt";
    public LocalReportSender(Context ctx) {
        // the destination
        File logFile = new File(new clsHardCode().txtPathApp, fileName);
        crashReport = null;
        try {
            crashReport = new FileWriter(logFile, true);
//            String idData = new tLogErrorBL().;


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isNull(String aString) {
        return aString == null || ACRAConstants.NULL_VALUE.equals(aString);
    }

    private Map<String, String> remap(Map<ReportField, String> report) {

        ReportField[] fields = ACRA.getConfig().customReportContent();
        if (fields.length == 0) {
            fields = ACRAConstants.DEFAULT_REPORT_FIELDS;
        }

        final Map<String, String> finalReport = new HashMap<String, String>(
                report.size());
        for (ReportField field : fields) {
            if (mMapping == null || mMapping.get(field) == null) {
                finalReport.put(field.toString(), report.get(field));
            } else {
                finalReport.put(mMapping.get(field), report.get(field));
            }
        }
        return finalReport;
    }
    @Override
    public void send(Context context, CrashReportData errorContent) throws ReportSenderException {


        final Map<String, String> finalReport = remap(errorContent);

        try {
            BufferedWriter buf = new BufferedWriter(crashReport);

            Set<Map.Entry<String, String>> set = finalReport.entrySet();
            Iterator<Map.Entry<String, String>> i = set.iterator();

            tLogErrorData _tLogErrorData = new tLogErrorData();
            tUserLoginData dataLogin = new tUserLoginBL().getUserLogin();
            buf.append("").append("\n");
            if (dataLogin!=null){
                _tLogErrorData.set_txtUserId(dataLogin.get_txtUserId());

            }else{
                _tLogErrorData.set_txtUserId("null");
            }
            List<tLogErrorData> dataError = new tLogErrorBL().getAllData();
            if (dataError.size()>0){
                for(tLogErrorData data : dataError){
                    if(data.get_txtFileName().equals(fileName)){
                        String id = data.get_intLogId();
                        _tLogErrorData.set_intLogId(id);

                        String roleId = data.get_txtRoleId();
                        _tLogErrorData.set_txtRoleId(roleId);

                        String roleName = data.get_txtRoleName();
                        _tLogErrorData.set_txtRoleName(roleName);
                    }else{
                        _tLogErrorData.set_intLogId(GenerateGuid());
                    }
                }


            }else{
                _tLogErrorData.set_intLogId(GenerateGuid());
            }
//            _tLogErrorData.set_intLogId(GenerateGuid());
            _tLogErrorData.set_intSubmit("1");
            _tLogErrorData.set_txtFileName(fileName);
            _tLogErrorData.set_dtDate(new Date().toString());
            if (dataLogin!=null){
                _tLogErrorData.set_txtRoleId(dataLogin.get_txtRoleId());
                _tLogErrorData.set_txtUserId(dataLogin.get_txtUserId());
                _tLogErrorData.set_txtRoleName(dataLogin.get_txtRoleName());
                buf.append("\n").append("User  id :"+ dataLogin.get_txtUserId() );
                buf.append("\n").append("Role  id :"+ dataLogin.get_txtRoleId() );
                buf.append("\n").append("Role  name :"+ dataLogin.get_txtRoleName() );
            }
            new tLogErrorBL().saveData(_tLogErrorData);

            while (i.hasNext()) {
                Map.Entry<String, String> me = (Map.Entry<String, String>) i.next();
                buf.append("[" + me.getKey() + "] = " + "'"+me.getValue()+"'").append("\n");
            }
            /*List<tLogErrorData> dataError = new tLogErrorBL().getAllData();
            tLogErrorData _tLogErrorData = new tLogErrorData();
            if (dataError.size()>0){
                for(tLogErrorData data : dataError){
                    if(data.get_txtFileName().equals(fileName)){
                        String id = data.get_intLogId();
                        _tLogErrorData.set_intLogId(id);

                        String roleId = data.get_txtRoleId();
                        _tLogErrorData.set_txtRoleId(roleId);

                        String roleName = data.get_txtRoleName();
                        _tLogErrorData.set_txtRoleName(roleName);
                    }else{
                        _tLogErrorData.set_intLogId(new clsMainActivity().GenerateGuid());
                    }
                }


            }else{
                _tLogErrorData.set_intLogId(new clsMainActivity().GenerateGuid());
            }

            _tLogErrorData.set_txtFileName(fileName);
            _tLogErrorData.set_dtDate(new Date().toString());*/

            buf.append("\n").append("----------------*****----------------");
            buf.append("\n").append("\n");
            buf.flush();
            buf.close();
        } catch (IOException e) {
            Log.e("TAG", "IO ERROR", e);
        }
    }
    public String GenerateGuid() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }
}