package com.kalbenutritionals.app.kalbespgmobile;

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
import java.util.Map;
import java.util.Set;

import library.spgmobile.dal.clsHardCode;

/**
 * Created by XSIS on 15/03/2017.
 */

    public class LocalReportSender implements ReportSender {

        private final Map<ReportField, String> mMapping = new HashMap<ReportField, String>();
        private FileWriter crashReport;

        public LocalReportSender(Context ctx) {
            // the destination
//            File logFile = new File(new clsHardCode().txtPathApp, "log.txt");
            Calendar calendar = Calendar.getInstance();
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            String fileName = dateFormat.format(calendar.getTime())+"_log.txt";

            String fileName = "log_"+dateFormat.format(date)+".txt";
            File logFolder = new File(new clsHardCode().txtPathApp, fileName);


            crashReport = null;
            try {
                crashReport = new FileWriter(logFolder, true);

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

            while (i.hasNext()) {
                Map.Entry<String, String> me = (Map.Entry<String, String>) i.next();
                buf.append("[" + me.getKey() + "] = " + "'"+me.getValue()+"'").append("\n");
            }
            buf.append("\n").append("----------------*****----------------");
            buf.append("\n").append("\n");
            buf.flush();
            buf.close();
        } catch (IOException e) {
            Log.e("TAG", "IO ERROR", e);
        }
    }
}

