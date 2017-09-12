package addons.tableview;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kalbenutritionals.app.kalbespgmobile.FragmentKuesioner;
import com.kalbenutritionals.app.kalbespgmobile.ReportDetailQuiz;
import com.kalbenutritionals.app.kalbespgmobile.PdfView;
import com.kalbenutritionals.app.kalbespgmobile.R;
import com.kalbenutritionals.app.kalbespgmobile.clsMainActivity;

import java.io.File;
import java.text.NumberFormat;
import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import library.spgmobile.common.ReportTable;
import library.spgmobile.dal.clsHardCode;


public class ReportTableDataAdapter extends TableDataAdapter<ReportTable> {

    private static final int TEXT_SIZE = 14;
    private static final NumberFormat PRICE_FORMATTER = NumberFormat.getNumberInstance();


    public ReportTableDataAdapter(final Context context, final List<ReportTable> data) {
        super(context, data);
    }

    @Override
    public View getCellView(final int rowIndex, final int columnIndex, final ViewGroup parentView) {
        final ReportTable data = getRowData(rowIndex);
        View renderedView = null;

        if(data.get_report_type() == "Reso"){
            switch (columnIndex) {
                case 1:
                    renderedView = renderString(data.get_no_so(), "left");
                    break;
                case 2:
                    renderedView = renderString(data.get_total_product(), "right");
                    break;
                case 3:
                    renderedView = renderString(data.get_total_item(), "right");
                    break;
                case 4:
                    renderedView = renderString(data.get_total_price(), "right");
                    break;
                case 5:
                    renderedView = renderString(data.get_status(), "left");
                    break;
                default:
                    break;
            }
        }

        if(data.get_report_type() == "Stock In Hand"){
            switch (columnIndex) {
                case 1:
                    renderedView = renderString(data.get_no_so(), "left");
                    break;
                case 2:
                    renderedView = renderString(data.get_total_product(), "right");
                    break;
                case 3:
                    renderedView = renderString(data.get_total_item(), "right");
                    break;
//                case 4:
//                    renderedView = renderString(data.get_total_price(), "right");
//                    break;
                case 4:
                    renderedView = renderString(data.get_status(), "left");
                    break;
                default:
                    break;
            }
        }

        if(data.get_report_type() == "no data"){
            switch (columnIndex) {
                case 1:
                    renderedView = renderString(data.get_no_po(), "center");
                default:
                    break;
            }
        }

        if(data.get_report_type() == "Customer Base"){
	        switch (columnIndex) {
	            case 1:
	                renderedView = renderString(data.get_no_cb(), "left");
	                break;
	            case 2:
	                renderedView = renderString(data.get_customer_name(), "left");
	                break;
	            case 3:
	            	renderedView = renderString(data.get_no_tlp(), "right");
	            	break;
                case 4:
                    renderedView = renderString(data.get_pic(), "left");
                    break;
                case 5:
                    renderedView = renderString(data.get_total_member(), "right");
                    break;
                case 6:
                    renderedView = renderString(data.get_total_product(),"right");
                    break;
                default:
                    break;
	        }
        }

        if(data.get_report_type() == "Activity"){
            switch (columnIndex) {
                case 1:
                    renderedView = renderString(data.get_txtOutletName(), "left");
                    break;
                case 2:
                    renderedView = renderString(data.get_txtDesc(), "left");
                    break;
                default:
                    break;
            }
        }

        if(data.get_report_type() == "Planogram"){
            switch (columnIndex) {
                case 1:
                    renderedView = renderString(data.get_txtOutletName(), "left");
                    break;
                case 2:
                    renderedView = renderString(data.get_txtDesc(), "left");
                    break;
                default:
                    break;
            }
        }

        if(data.get_report_type() == "Po"){
            switch (columnIndex) {
                case 1:
                    renderedView = renderString(data.get_no_po(), "left");
                    break;
                case 2:
                    renderedView = renderString(data.get_total_product(), "right");
                    break;
                case 3:
                    renderedView = renderString(data.get_total_item(), "right");
                    break;
                case 4:
                    renderedView = renderString(data.get_total_price(), "right");
                    break;
                case 5:
                    renderedView = renderString(data.get_status(), "left");
                    break;
                default:
                    break;
            }
        }

        if(data.get_report_type() == "QStock"){
            switch (columnIndex) {
                case 1:
                    renderedView = renderString(data.get_txtQuantityStock(), "left");
                    break;
                case 2:
                    renderedView = renderString(data.get_total_product(), "right");
                    break;
                case 3:
                    renderedView = renderString(data.get_total_item(), "right");
                    break;
//                case 4:
//                    renderedView = renderString(data.get_total_price(), "right");
//                    break;
                case 4:
                    renderedView = renderString(data.get_status(), "left");
                    break;
                default:
                    break;
            }
        }
        if(data.get_report_type() == "Customer Base MTD"){
            switch (columnIndex) {
                case 1:
                    renderedView = renderString(data.get_outlet_code(), "left");
                    break;
                case 2:
                    renderedView = renderString(data.get_outlet_name(), "left");
                    break;
                case 3:
                    renderedView = renderString(data.get_sum_daily(), "right");
                    break;
                case 4:
                    renderedView = renderString(data.get_sum_MTD(), "right");
                    break;
                default:
                    break;
            }
        }

        if(data.get_report_type() == "Kuesioner"){

            switch (columnIndex) {
                case 1:
                    renderedView = renderString(data.get_RepeatQuiz(), "left");
                    break;
                case 2:
                    renderedView = renderString(data.get_Group_Question(), "left");
                    break;
                case 3:
                    renderedView =  renderString(data.get_txtOutletName(), "left");
                    break;
                case 4:
                    renderedView = renderStringViewDetail(data.get_dateTime(), data.get_dummy(), "left");
                    break;
                default:
                    break;
            }
        }

        if(data.get_report_type() == "Kuesioner Detail"){

            switch (columnIndex) {
                case 1:
                    renderedView = renderStringDetail(data.get_Category(), "left");
                    break;
                case 2:
                    renderedView = renderStringDetail(data.get_RepeatQuiz(), "left");
                    break;
                case 3:
                    renderedView =  renderStringDetail(data.get_Question(), "left");
                    break;
                case 4:
                    if (data.get_type().equals("8") || data.get_type().equals("7")){
                        renderedView = renderStringImage(data.get_Answer(), "left");
                    } else {
                        renderedView = renderStringDetail(data.get_Answer(), "left");
                    }
                    break;
                default:
                    break;
            }
        }

        return renderedView;
    }

    private View renderString(final String value, final String align) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        
        if(align.equals("right")){
        	textView.setGravity(Gravity.RIGHT);
        }
        
        return textView;
    }

    private View renderStringDetail(final String value, final String align) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        textView.setTextColor(Color.BLACK);

        if(align.equals("right")){
            textView.setGravity(Gravity.RIGHT);
        }

        return textView;
    }

    private View renderStringImage(final String value, final String align) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        textView.setTextColor(Color.BLUE);
        final String fileExtension = value.substring(value.lastIndexOf("."));
        final File mediaStorageDir = new File(new clsHardCode().txtFolderQuiz + File.separator + value);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction(Intent.ACTION_VIEW);
                    if (fileExtension.contains(".jpg")){
                        intent.setDataAndType(Uri.fromFile(mediaStorageDir), "image/*");
                    }else if (fileExtension.contains(".doc")){
                        intent.setDataAndType(Uri.fromFile(mediaStorageDir), "application/msword");
                    } else if (fileExtension.contains(".xls")){
                        intent.setDataAndType(Uri.fromFile(mediaStorageDir), "application/vnd.ms-excel");
                    }else if (fileExtension.contains(".pdf")){
                        intent.setDataAndType(Uri.fromFile(mediaStorageDir), "application/pdf");
                    }
                    getContext().startActivity(intent);
                }catch (ActivityNotFoundException e){
                    clsMainActivity _clsMainActivity = new clsMainActivity();
                    _clsMainActivity.showCustomToast(getContext(), "You haven't app for open this file", false);
                }
            }
        });

        if(align.equals("right")){
            textView.setGravity(Gravity.RIGHT);
        }

        return textView;
    }

    private View renderStringViewDetail(final String value, final String dummy, final String align) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        textView.setTextColor(Color.BLUE);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String[] passingValue = {dummy, value};
                        Bundle bundle = new Bundle();
                        bundle.putStringArray("Key_HeaderId", passingValue);
                        Intent intent = new Intent(getContext().getApplicationContext(), ReportDetailQuiz.class);
                        intent.putExtra("Key_HeaderId", passingValue);
                        getContext().startActivity(intent);
            }
        });

        if(align.equals("right")){
            textView.setGravity(Gravity.RIGHT);
        }

        return textView;
    }
}
