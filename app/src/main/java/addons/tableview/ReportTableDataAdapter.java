package addons.tableview;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInstaller;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.kalbenutritionals.app.kalbespgmobile.FragmentKuesioner;
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
                    renderedView = renderString(data.get_Group_Question(), "left");
                    break;
                case 2:
                    renderedView = renderString(data.get_Question(), "left");
                    break;
                case 3:
                    if (data.get_type().equals("8") || data.get_type().equals("7")){
                        renderedView = renderStringImage(data.get_Answer(), "left");
                    } else {
                        renderedView = renderString(data.get_Answer(), "left");
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
//                        Bundle bundle = new Bundle();
//                        bundle.putString("Key_QuizFile", mediaStorageDir.getPath().toString());
//                        Intent intentPDf = new Intent(getContext().getApplicationContext(), PdfView.class);
//                        intentPDf.putExtra("Key_QuizFile", mediaStorageDir.getPath().toString());
//                        getContext().startActivity(intentPDf);
                        intent.setDataAndType(Uri.fromFile(mediaStorageDir), "application/pdf");
                    }
                    getContext().startActivity(intent);
                }catch (ActivityNotFoundException e){
                    clsMainActivity _clsMainActivity = new clsMainActivity();
                    _clsMainActivity.showCustomToast(getContext(), "You haven't app for open this file", false);
                }
//                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
//                final View promptView = layoutInflater.inflate(R.layout.activity_kuesioner, null);
//                LinearLayout layout = (LinearLayout) promptView.findViewById(R.id.ll_quiz);
////                LinearLayout linearLayout = new LinearLayout(getContext());
////                linearLayout.setOrientation(LinearLayout.VERTICAL);
////                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 300);
////                layoutParams.gravity = Gravity.CENTER;
////                linearLayout.setLayoutParams(layoutParams);
////                layout.addView(linearLayout);
//                ImageView imageView = new ImageView(getContext());
//                LinearLayout.LayoutParams layoutParamImg = new LinearLayout.LayoutParams(200,200);
//                layoutParamImg.gravity = Gravity.CENTER;
//                imageView.setLayoutParams(layoutParamImg);
//                BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//                Bitmap bm = BitmapFactory.decodeFile(String.valueOf(mediaStorageDir), bitmapOptions);
//                imageView.setImageBitmap(bm);
//                layout.addView(imageView);
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
//                alertDialogBuilder.setView(promptView);
//                alertDialogBuilder.setCancelable(false);
//                alertDialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                final AlertDialog alertD = alertDialogBuilder.create();
//                alertD.show();
            }
        });

        if(align.equals("right")){
            textView.setGravity(Gravity.RIGHT);
        }

        return textView;
    }

}
