package addons.tableview;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Transformers.BaseTransformer;
import com.kalbenutritionals.app.kalbespgmobile.FragmentKuesioner;
import com.kalbenutritionals.app.kalbespgmobile.ImagePick;
import com.kalbenutritionals.app.kalbespgmobile.ReportDetailQuiz;
import com.kalbenutritionals.app.kalbespgmobile.PdfView;
import com.kalbenutritionals.app.kalbespgmobile.R;
import com.kalbenutritionals.app.kalbespgmobile.clsMainActivity;
import com.kalbenutritionals.app.kalbespgmobile.intentCustom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

import bl.clsHelperBL;
import bl.tKemasanRusakDetailBL;
import bl.tKemasanRusakHeaderBL;
import bl.tOverStockDetailBL;
import bl.tOverStockHeaderBL;
import bl.tPOPStandardDetailBL;
import bl.tPOPStandardHeaderBL;
import bl.tSalesProductQuantityDetailBL;
import bl.tSalesProductQuantityHeaderBL;
import bl.tStockInHandDetailBL;
import bl.tStockInHandHeaderBL;
import de.codecrafters.tableview.TableDataAdapter;
import library.spgmobile.common.ReportTable;
import library.spgmobile.common.tKemasanRusakDetailData;
import library.spgmobile.common.tKemasanRusakHeaderData;
import library.spgmobile.common.tOverStockDetailData;
import library.spgmobile.common.tOverStockHeaderData;
import library.spgmobile.common.tPOPStandardDetailData;
import library.spgmobile.common.tPOPStandardHeaderData;
import library.spgmobile.common.tSalesProductQuantityDetailData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tStockInHandDetailData;
import library.spgmobile.common.tStockInHandHeaderData;
import library.spgmobile.common.visitplanAbsenData;
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
                case 4:
                    renderedView = renderString(data.get_status(), "left");
                    break;
                case 5:
                    renderedView = renderViewDetailNearEdTl(data.get_view_detail(), data.get_no_so(), data.get_report_type(), "left");
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
                    renderedView = renderString(data.get_total_member(), "right");
                    break;
                case 5:
                    renderedView = renderString(data.get_total_product(), "right");
                    break;
                case 6:
                    renderedView = renderString(data.get_total_item(),"right");
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

        if(data.get_report_type() == "ActivityV2"){
            switch (columnIndex) {
                case 1:
                    renderedView = renderString(data.get_txtOutletName(), "left");
                    break;
                case 2:
                    renderedView = renderString(data.get_status(), "left");
                    break;
                case 3:
                    renderedView = renderString(data.get_Category(), "left");
                    break;
                case 4:
                    renderedView = renderString(data.get_txtDesc(), "left");
                    break;
                default:
                    break;
            }
        }
        if(data.get_report_type() == "Tidak Sesuai Pesanan"){
            switch (columnIndex) {
                case 1:
                    renderedView = renderString(data.get_RepeatQuiz(), "left");
                    break;
                case 2:
                    renderedView = renderString(data.get_txtOutletName(), "left");
                    break;
                case 3:
                    renderedView = renderString(data.get_txtDesc(), "left");
                    break;
                default:
                    break;
            }
        }

        if(data.get_report_type() == "Koordinasi Outlet"){
            switch (columnIndex) {
                case 1:
                    renderedView = renderString(data.get_RepeatQuiz(), "left");
                    break;
                case 2:
                    renderedView = renderString(data.get_txtOutletName(), "left");
                    break;
                case 3:
                    renderedView = renderString(data.get_Category(), "left");
                    break;
                case 4:
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
                    renderedView = renderString(data.get_Category(), "left");
                    break;
                case 3:
                    renderedView = renderString(data.get_status(), "left");
                    break;
                case 4:
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
                    renderedView = renderString(data.get_status() , "left");
                    break;
                case 5:
                    renderedView = renderViewDetailNearEdTl(data.get_view_detail(), data.get_txtQuantityStock(), data.get_dummy() , "left");
                default:
                    break;
            }
        }

        if(data.get_report_type() == "KemasanRusak"){
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
                    renderedView = renderString(data.get_status() , "left");
                    break;
                case 5:
                    renderedView = renderViewDetailNearEdTl(data.get_view_detail(), data.get_txtQuantityStock(), data.get_dummy() , "left");
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
                    renderedView = renderString(data.get_dateTime(), "left");
                    break;
                case 5:
                    renderedView = renderStringViewDetail(data.get_view_detail(),data.get_dateTime(), data.get_dummy(), "left");
                    break;
                default:
                    break;
            }
        }

        if(data.get_report_type() == "POP Standard TL"){

            switch (columnIndex) {
                case 1:
                    renderedView = renderString(data.get_RepeatQuiz(), "left");
                    break;
                case 2:
                    renderedView = renderString(data.get_type(), "left");
                    break;
                case 3:
                    renderedView =  renderString(data.get_Category(), "left");
                    break;
                case 4:
                    renderedView = renderViewDetailNearEdTl(data.get_view_detail(), data.get_dummy(), data.get_report_type(), "left");
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
                        renderedView = renderStringImage(data.get_Answer(), data.get_answer(), "left");
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

    private View renderStringImage(final String value, final byte[] dataArray, final String align) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        textView.setTextColor(Color.BLUE);

        final String fileExtension = value.substring(value.lastIndexOf("."));
//        final File mediaStorageDir = new File(new clsHardCode().txtFolderQuiz + File.separator + value);
        final File mediaStorageDir = null;


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction(Intent.ACTION_VIEW);
                    Uri uri = null;
                    if (fileExtension.contains(".png")||fileExtension.contains(".jpg") ){
                        File mediaStorageDir = ImagePick.decodeByteArraytoFileImage(dataArray, new clsHardCode().txtPathTempData);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //use this if Lollipop_Mr1 (API 22) or above
                            uri =  FileProvider.getUriForFile(getContext().getApplicationContext(), getContext().getApplicationContext().getPackageName()+".provider", mediaStorageDir);
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        } else {
                            uri = Uri.fromFile(mediaStorageDir);
                        }
                        intent.setDataAndType(uri, "image/*");
                    }else if (fileExtension.contains(".doc")){
                        File mediaStorageDir =  ImagePick.decodeByteArraytoTempFile(dataArray, new clsHardCode().txtPathTempData, fileExtension);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //use this if Lollipop_Mr1 (API 22) or above
                            uri =  FileProvider.getUriForFile(getContext().getApplicationContext(), getContext().getApplicationContext().getPackageName()+".provider", mediaStorageDir);
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        } else {
                            uri = Uri.fromFile(mediaStorageDir);
                        }
                        intent.setDataAndType(uri, "application/msword");
                    } else if (fileExtension.contains(".xls")){
                        File mediaStorageDir =  ImagePick.decodeByteArraytoTempFile(dataArray, new clsHardCode().txtPathTempData, fileExtension);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //use this if Lollipop_Mr1 (API 22) or above
                            uri =  FileProvider.getUriForFile(getContext().getApplicationContext(), getContext().getApplicationContext().getPackageName()+".provider", mediaStorageDir);
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        } else {
                            uri = Uri.fromFile(mediaStorageDir);
                        }
                        intent.setDataAndType(uri, "application/vnd.ms-excel");
                    }else if (fileExtension.contains(".pdf")){
                        File mediaStorageDir =  ImagePick.decodeByteArraytoTempFile(dataArray, new clsHardCode().txtPathTempData, fileExtension);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //use this if Lollipop_Mr1 (API 22) or above
                            uri =  FileProvider.getUriForFile(getContext().getApplicationContext(), getContext().getApplicationContext().getPackageName()+".provider", mediaStorageDir);
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        } else {
                            uri = Uri.fromFile(mediaStorageDir);
                        }
                        intent.setDataAndType(uri, "application/pdf");
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

    private View renderStringViewDetail(final String view, final String value, final String dummy, final String align) {
        final Button textView = new Button(getContext());
        textView.setText(view);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundResource(R.drawable.btn_download_all);

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
    private View renderViewDetailNearEd(final String value, final String dummy, final String align){
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        textView.setTextColor(Color.BLUE);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDetailNearEd(dummy);
            }
        });

        if(align.equals("right")){
            textView.setGravity(Gravity.RIGHT);
        }

        return textView;
    }

    private View renderViewDetailNearEdTl(final String value, final String dummy, final String type, final String align){
        final Button textView = new Button(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundResource(R.drawable.btn_download_all);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == "Stock In Hand"){
                    viewDetailStockOnHand(dummy);
                } else if (type == "Near ED"){
                    viewDetailNearEd(dummy);
                } else if (type == "Over Stock"){
                    viewDetailOverStock(dummy);
                } else if (type == "Kemasan Rusak"){
                    viewDetailKemasanRusak(dummy);
                } else if(type == "POP Standard TL"){
                    ViewDetailPOPStandard(dummy);
                }

            }
        });

        if(align.equals("right")){
            textView.setGravity(Gravity.RIGHT);
        }

        return textView;
    }

    private void viewDetailOverStock(final String dummy) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.activity_preview_quantity, null);

        final TextView _tvNoSO = (TextView) promptView.findViewById(R.id.tvnoSOtbl);
        final TextView _tvKet = (TextView) promptView.findViewById(R.id.tvkettbl);

        List<tOverStockHeaderData> header = new tOverStockHeaderBL().getDataByNoOverStock(dummy);
        _tvNoSO.setText(header.get(0).get_txtOverStock());
        _tvKet.setText(header.get(0).get_txtKeterangan());
        final TextView tv_item = (TextView) promptView.findViewById(R.id.tvItemtbl);
        tv_item.setTypeface(null, Typeface.BOLD);
        tv_item.setText(String.valueOf(header.get(0).get_intSumItem()));
        final  TextView tv_amount = (TextView) promptView.findViewById(R.id.tvSumAmount) ;
        tv_amount.setTypeface(null, Typeface.BOLD);
        tv_amount.setText(String.valueOf(header.get(0).get_intSumAmount()));
        final  TextView tv_status = (TextView) promptView.findViewById(R.id.tvStatus);
        tv_status.setTypeface(null, Typeface.BOLD);

        final TableRow tr_neared = (TableRow) promptView.findViewById(R.id.tr_neared);
        tr_neared.setVisibility(View.GONE);

        if (header.get(0).get_intSubmit().equals("1")&&header.get(0).get_intSync().equals("0")){
            tv_status.setText("submit");
        } else if (header.get(0).get_intSubmit().equals("1")&&header.get(0).get_intSync().equals("1")){
            tv_status.setText("Sync");
        }

        TableLayout tlb = (TableLayout) promptView.findViewById(R.id.tlProductQty);
        tlb.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        TableLayout tl = new TableLayout(getContext());

        String[] colTextHeader = {"Nama", "Qty", "ED"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tr.addView(tv,params);
        }
        tl.addView(tr);

        List<tOverStockDetailData> data = new tOverStockDetailBL().GetDataNoOverStock(header.get(0).get_txtOverStock());

        double qtySum=0;
        double qtyNum;
        for(tOverStockDetailData dat : data){
            tr = new TableRow(getContext());
            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

            int leftMargin=0;
            int topMargin=0;
            int rightMargin=0;
            int bottomMargin=0;
            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            tr.setLayoutParams(tableRowParams);

            TextView product = new TextView(getContext());
            product.setTextSize(12);
            product.setWidth(600);
            product.setPadding(10, 10, 10, 10);
            product.setBackgroundColor(Color.parseColor("#f0f0f0"));
            product.setTextColor(Color.BLACK);
            product.setText(dat.getTxtProduct());
            tr.addView(product,params);

            TextView qty = new TextView(getContext());
            qty.setTextSize(12);
            qty.setPadding(10, 10, 10, 10);
            qty.setBackgroundColor(Color.parseColor("#f0f0f0"));
            qty.setTextColor(Color.BLACK);
            qty.setGravity(Gravity.RIGHT);
            qty.setText(dat.getTxtQuantity()+ " pcs");
            tr.addView(qty,params);

            TextView price = new TextView(getContext());
            price.setTextSize(12);
            price.setPadding(10, 10, 10, 10);
            price.setBackgroundColor(Color.parseColor("#f0f0f0"));
            price.setTextColor(Color.BLACK);
            price.setGravity(Gravity.RIGHT);
            price.setText(new clsMainActivity().giveFormatDate2(dat.getTxtExpireDate()));
            tr.addView(price,params);
//
//            TextView amount = new TextView(getContext());
//            amount.setTextSize(12);
//            amount.setWidth(200);
//            amount.setPadding(10, 10, 10, 10);
//            amount.setBackgroundColor(Color.parseColor("#f0f0f0"));
//            amount.setTextColor(Color.BLACK);
//            amount.setGravity(Gravity.RIGHT);
//            double prc = Double.valueOf(dat.get_intPrice());
//            double itm = Double.valueOf(dat.getTxtQuantity());
//            qtyNum = prc * itm;
//            qtySum += qtyNum;
//            amount.setText(new clsMainActivity().convertNumberDec(qtyNum));
//            tr.addView(amount,params);

            tl.addView(tr, tableRowParams);
        }

        tlb.addView(tl);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }

    private void viewDetailStockOnHand(final String dummy) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.activity_preview_so, null);

        List<tStockInHandHeaderData> header = new tStockInHandHeaderBL().getDataByNoSO(dummy);

        final TextView _tvNoSO = (TextView) promptView.findViewById(R.id.tvnoSOtbl);
        final TextView _tvKet = (TextView) promptView.findViewById(R.id.tvkettbl);
        _tvNoSO.setText(": " + dummy);
        _tvKet.setText(": " + header.get(0).get_txtKeterangan());
        final TextView tv_item = (TextView) promptView.findViewById(R.id.tvItemtbl);
        tv_item.setTypeface(null, Typeface.BOLD);
        tv_item.setText(": " + String.valueOf(header.get(0).get_intSumItem()));
        final  TextView tv_amount = (TextView) promptView.findViewById(R.id.tvSumAmount) ;
        tv_amount.setTypeface(null, Typeface.BOLD);
        tv_amount.setText(": " + new clsMainActivity().convertNumberDec(Double.valueOf(header.get(0).get_intSumAmount())));
        final  TextView tv_status = (TextView) promptView.findViewById(R.id.tvStatus);
        tv_status.setTypeface(null, Typeface.BOLD);

        final TableRow tr_amount = (TableRow) promptView.findViewById(R.id.tr_amount);
        tr_amount.setVisibility(View.GONE);

        if (header.get(0).get_intSubmit().equals("1")&&header.get(0).get_intSync().equals("0")){
            tv_status.setText(": Submit");
        } else if (header.get(0).get_intSubmit().equals("1")&&header.get(0).get_intSync().equals("1")){
            tv_status.setText(": Sync");
        }

        TableLayout tlb = (TableLayout) promptView.findViewById(R.id.tlProduct);
        tlb.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        TableLayout tl = new TableLayout(getContext());

        String[] colTextHeader = {"Name", "Qty"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tr.addView(tv,params);
        }
        tl.addView(tr);

        List<tStockInHandDetailData> data= new tStockInHandDetailBL().GetDataByNoSO(dummy);

        double qtySum=0;
        double qtyNum;
        for(tStockInHandDetailData dat : data){
            tr = new TableRow(getContext());
            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

            int leftMargin=0;
            int topMargin=0;
            int rightMargin=0;
            int bottomMargin=0;
            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            tr.setLayoutParams(tableRowParams);

            TextView product = new TextView(getContext());
            product.setTextSize(12);
            product.setWidth(600);
            product.setPadding(10, 10, 10, 10);
            product.setBackgroundColor(Color.parseColor("#f0f0f0"));
            product.setTextColor(Color.BLACK);
            product.setText(dat.get_txtNameProduct());
            tr.addView(product,params);

            TextView qty = new TextView(getContext());
            qty.setTextSize(12);
            qty.setPadding(10, 10, 10, 10);
            qty.setBackgroundColor(Color.parseColor("#f0f0f0"));
            qty.setTextColor(Color.BLACK);
            qty.setGravity(Gravity.RIGHT);
            qty.setText(dat.get_intQty()+ " pcs");
            tr.addView(qty,params);
            tl.addView(tr, tableRowParams);
        }

        tlb.addView(tl);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }

    private void viewDetailNearEd(final String dummy) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.activity_preview_quantity, null);

        List<tSalesProductQuantityHeaderData> dtheader = new tSalesProductQuantityHeaderBL().getDataByNoQuantityStock(dummy);

        final TextView _tvNoSO = (TextView) promptView.findViewById(R.id.tvnoSOtbl);
        final TextView _tvKet = (TextView) promptView.findViewById(R.id.tvkettbl);
        _tvNoSO.setText(dtheader.get(0).get_txtQuantityStock());
        _tvKet.setText(dtheader.get(0).get_txtKeterangan());
        final TextView tv_item = (TextView) promptView.findViewById(R.id.tvItemtbl);
        tv_item.setTypeface(null, Typeface.BOLD);
        tv_item.setText(String.valueOf(dtheader.get(0).get_intSumItem()));
        final  TextView tv_amount = (TextView) promptView.findViewById(R.id.tvSumAmount) ;
        tv_amount.setTypeface(null, Typeface.BOLD);
        tv_amount.setText(String.valueOf(dtheader.get(0).get_intSumAmount()));
        final  TextView tv_status = (TextView) promptView.findViewById(R.id.tvStatus);
        tv_status.setTypeface(null, Typeface.BOLD);

        final TableRow tr_neared = (TableRow) promptView.findViewById(R.id.tr_neared);
        final TableRow tr_keterangan = (TableRow) promptView.findViewById(R.id.tr_keterangan);
        tr_keterangan.setVisibility(View.GONE);
        tr_neared.setVisibility(View.GONE);

        if (dtheader.get(0).get_intSubmit().equals("1")&&dtheader.get(0).get_intSync().equals("0")){
            tv_status.setText("submit");
        } else if (dtheader.get(0).get_intSubmit().equals("1")&&dtheader.get(0).get_intSync().equals("1")){
            tv_status.setText("Sync");
        }

        TableLayout tlb = (TableLayout) promptView.findViewById(R.id.tlProductQty);
        tlb.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        TableLayout tl = new TableLayout(getContext());

        String[] colTextHeader = {"Nama", "Qty", "ED"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tr.addView(tv,params);
        }
        tl.addView(tr);

        List<tSalesProductQuantityDetailData> dt_detail = new tSalesProductQuantityDetailBL().GetDataByNoQuantityStock(dummy);

        double qtySum=0;
        double qtyNum;
        for(tSalesProductQuantityDetailData dat : dt_detail){
            tr = new TableRow(getContext());
            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

            int leftMargin=0;
            int topMargin=0;
            int rightMargin=0;
            int bottomMargin=0;
            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            tr.setLayoutParams(tableRowParams);

            TextView product = new TextView(getContext());
            product.setTextSize(12);
            product.setWidth(600);
            product.setPadding(10, 10, 10, 10);
            product.setBackgroundColor(Color.parseColor("#f0f0f0"));
            product.setTextColor(Color.BLACK);
            product.setText(dat.getTxtProduct());
            tr.addView(product,params);

            TextView qty = new TextView(getContext());
            qty.setTextSize(12);
            qty.setPadding(10, 10, 10, 10);
            qty.setBackgroundColor(Color.parseColor("#f0f0f0"));
            qty.setTextColor(Color.BLACK);
            qty.setGravity(Gravity.RIGHT);
            qty.setText(dat.getTxtQuantity()+ " pcs");
            tr.addView(qty,params);

            TextView price = new TextView(getContext());
            price.setTextSize(12);
            price.setPadding(10, 10, 10, 10);
            price.setBackgroundColor(Color.parseColor("#f0f0f0"));
            price.setTextColor(Color.BLACK);
            price.setGravity(Gravity.RIGHT);
            price.setText(new clsMainActivity().giveFormatDate2(dat.getTxtExpireDate()));
            tr.addView(price,params);
//
//            TextView amount = new TextView(getContext());
//            amount.setTextSize(12);
//            amount.setWidth(200);
//            amount.setPadding(10, 10, 10, 10);
//            amount.setBackgroundColor(Color.parseColor("#f0f0f0"));
//            amount.setTextColor(Color.BLACK);
//            amount.setGravity(Gravity.RIGHT);
//            double prc = Double.valueOf(dat.get_intPrice());
//            double itm = Double.valueOf(dat.getTxtQuantity());
//            qtyNum = prc * itm;
//            qtySum += qtyNum;
//            amount.setText(new clsMainActivity().convertNumberDec(qtyNum));
//            tr.addView(amount,params);

            tl.addView(tr, tableRowParams);
        }

        tlb.addView(tl);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }
    private void viewDetailKemasanRusak(final String dummy) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.activity_preview_quantity, null);

        List<tKemasanRusakHeaderData> dtheader = new tKemasanRusakHeaderBL().getDataByNo(dummy);

        final TextView _tvNoSO = (TextView) promptView.findViewById(R.id.tvnoSOtbl);
        final TextView _tvKet = (TextView) promptView.findViewById(R.id.tvkettbl);
        _tvNoSO.setText(dtheader.get(0).get_txtKemasanRusak());
        _tvKet.setText(dtheader.get(0).get_txtKeterangan());
        final TextView tv_item = (TextView) promptView.findViewById(R.id.tvItemtbl);
        tv_item.setTypeface(null, Typeface.BOLD);
        tv_item.setText(String.valueOf(dtheader.get(0).get_intSumItem()));
        final  TextView tv_amount = (TextView) promptView.findViewById(R.id.tvSumAmount) ;
        tv_amount.setTypeface(null, Typeface.BOLD);
        tv_amount.setText(String.valueOf(dtheader.get(0).get_intSumAmount()));
        final  TextView tv_status = (TextView) promptView.findViewById(R.id.tvStatus);
        tv_status.setTypeface(null, Typeface.BOLD);

        final TableRow tr_neared = (TableRow) promptView.findViewById(R.id.tr_neared);
        tr_neared.setVisibility(View.GONE);

        if (dtheader.get(0).get_intSubmit().equals("1")&&dtheader.get(0).get_intSync().equals("0")){
            tv_status.setText("submit");
        } else if (dtheader.get(0).get_intSubmit().equals("1")&&dtheader.get(0).get_intSync().equals("1")){
            tv_status.setText("Sync");
        }

        TableLayout tlb = (TableLayout) promptView.findViewById(R.id.tlProductQty);
        tlb.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        TableLayout tl = new TableLayout(getContext());

        String[] colTextHeader = {"Nama", "Qty", "ED"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tr.addView(tv,params);
        }
        tl.addView(tr);

        List<tKemasanRusakDetailData> dt_detail = new tKemasanRusakDetailBL().GetDataByNoKemasanRusak(dummy);

        double qtySum=0;
        double qtyNum;
        for(tKemasanRusakDetailData dat : dt_detail){
            tr = new TableRow(getContext());
            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

            int leftMargin=0;
            int topMargin=0;
            int rightMargin=0;
            int bottomMargin=0;
            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            tr.setLayoutParams(tableRowParams);

            TextView product = new TextView(getContext());
            product.setTextSize(12);
            product.setWidth(600);
            product.setPadding(10, 10, 10, 10);
            product.setBackgroundColor(Color.parseColor("#f0f0f0"));
            product.setTextColor(Color.BLACK);
            product.setText(dat.getTxtProduct());
            tr.addView(product,params);

            TextView qty = new TextView(getContext());
            qty.setTextSize(12);
            qty.setPadding(10, 10, 10, 10);
            qty.setBackgroundColor(Color.parseColor("#f0f0f0"));
            qty.setTextColor(Color.BLACK);
            qty.setGravity(Gravity.RIGHT);
            qty.setText(dat.getTxtQuantity()+ " pcs");
            tr.addView(qty,params);

            TextView price = new TextView(getContext());
            price.setTextSize(12);
            price.setPadding(10, 10, 10, 10);
            price.setBackgroundColor(Color.parseColor("#f0f0f0"));
            price.setTextColor(Color.BLACK);
            price.setGravity(Gravity.RIGHT);
            price.setText(new clsMainActivity().giveFormatDate2(dat.getTxtExpireDate()));
            tr.addView(price,params);
//
//            TextView amount = new TextView(getContext());
//            amount.setTextSize(12);
//            amount.setWidth(200);
//            amount.setPadding(10, 10, 10, 10);
//            amount.setBackgroundColor(Color.parseColor("#f0f0f0"));
//            amount.setTextColor(Color.BLACK);
//            amount.setGravity(Gravity.RIGHT);
//            double prc = Double.valueOf(dat.get_intPrice());
//            double itm = Double.valueOf(dat.getTxtQuantity());
//            qtyNum = prc * itm;
//            qtySum += qtyNum;
//            amount.setText(new clsMainActivity().convertNumberDec(qtyNum));
//            tr.addView(amount,params);

            tl.addView(tr, tableRowParams);
        }

        tlb.addView(tl);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }
    private static Bitmap mybitmap1;
    private static Bitmap mybitmap2;
    private void ViewDetailPOPStandard(final String dummy){
        tPOPStandardDetailData data = new tPOPStandardDetailBL().GetDataById(dummy);
        List<tPOPStandardHeaderData> dt = new tPOPStandardHeaderBL().GetDataById(dummy);
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        final View promptView = layoutInflater.inflate(R.layout.fragment_pop_standard_view_detail, null);

        final LinearLayout lnlayout = (LinearLayout) promptView.findViewById(R.id.ln_pop_view_detail);

        lnlayout.setFocusable(true);
        lnlayout.setFocusableInTouchMode(true);
        final TextView statusPOP = (TextView) promptView.findViewById(R.id.etNamaPOP);
        final TextView etDesc = (TextView) promptView.findViewById(R.id.tvDescPOP);
        final ImageButton img1 = (ImageButton) promptView.findViewById(R.id.imageButton);
        final ImageButton img2 = (ImageButton) promptView.findViewById(R.id.imageButton2);
        final TextView reason = (TextView) promptView.findViewById(R.id.reasondetPOP);
        final TextView category = (TextView) promptView.findViewById(R.id.etCatPOP);
        final RelativeLayout rl_reason = (RelativeLayout) promptView.findViewById(R.id.rl_pop);
        final LinearLayout ln_img = (LinearLayout) promptView.findViewById(R.id.ln_img_pop);
        final TextView status = (TextView) promptView.findViewById(R.id.textView9POP);
        final SliderLayout mDemoSlider = (SliderLayout) promptView.findViewById(R.id.sliderPOP);

        //adding outlet name di view
        final TextView tv_outlet = (TextView) promptView.findViewById(R.id.tv_date);
        tv_outlet.setVisibility(View.VISIBLE);

        visitplanAbsenData dtAbsensVisitplan = new clsHelperBL().getDataCheckInActive();
        String outlet = "-";

        if(dtAbsensVisitplan!=null){
            outlet = dt.get(0).get_txtOutletName();
            if(dt.get(0).get_txtOutletName().toString().equals("null")){
                outlet = "No Outlet";
            }
        }
        tv_outlet.setText(outlet);

        String statusText = dt.get(0).get_intSubmit().equals("1") && dt.get(0).get_intSync().equals("1") ? "Sync" : "Submit";

        status.setText("Status : " + statusText);
        category.setText(dt.get(0).get_txtCategory());

        etDesc.setText("There is " + dt.get(0).get_txtType() + " : ");
        String sts = "";
        if (dt.get(0).get_bolHavePOP().equals("1")){
            sts = "Yes";
            img1.setEnabled(true);
            img2.setEnabled(true);
            mDemoSlider.setVisibility(View.VISIBLE);
//            ln_img.setVisibility(View.VISIBLE);
        } else {
            sts = "No";
            reason.setText(dt.get(0).get_txtReason());
            rl_reason.setVisibility(View.VISIBLE);
        }
        statusPOP.setText(sts);

//        File folder = new File(Environment.getExtedernalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata");
        File folder = new File(new clsHardCode().txtPathTempData);
        folder.mkdir();

        final byte[] imgFile = data.get_txtImg1();
        if (imgFile != null) {
            mybitmap1 = BitmapFactory.decodeByteArray(imgFile, 0, imgFile.length);
            Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap1, 150, 150, true);
            img1.setImageBitmap(bitmap);

            File file = null;
            try {
                file = File.createTempFile("image-", ".jpg", new File(new clsHardCode().txtPathTempData));
                FileOutputStream out = new FileOutputStream(file);
                out.write(imgFile);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .description(dt.get(0).get_txtType())
                    .image(file)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            new clsMainActivity().zoomImage(mybitmap1, getContext());
                        }
                    });

            mDemoSlider.addSlider(textSliderView);

        } else {
            img1.setVisibility(View.GONE);
        }
        final byte[] imgFile2 = data.get_txtImg2();
        if (imgFile2 != null) {
            mybitmap2 = BitmapFactory.decodeByteArray(imgFile2, 0, imgFile2.length);
            Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap2, 150, 150, true);
            img2.setImageBitmap(bitmap);

            File file = null;
            try {
                file = File.createTempFile("image-", ".jpg", new File(new clsHardCode().txtPathTempData));
                FileOutputStream out = new FileOutputStream(file);
                out.write(imgFile2);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .description(dt.get(0).get_txtType())
                    .image(file)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            new clsMainActivity().zoomImage(mybitmap2, getContext());
                        }
                    });

            mDemoSlider.addSlider(textSliderView);
        } else {
            img2.setVisibility(View.GONE);
        }

        if (imgFile == null || imgFile2 == null) {
            mDemoSlider.stopAutoCycle();
            mDemoSlider.setPagerTransformer(false, new BaseTransformer() {
                @Override
                protected void onTransform(View view, float v) {
                }
            });
        } else {
            mDemoSlider.stopAutoCycle();
            mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
            mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mDemoSlider.setCustomAnimation(new DescriptionAnimation());
            mDemoSlider.setDuration(4000);
        }

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new clsMainActivity().deleteTempFolder();
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();

        img1.setClickable(true);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new clsMainActivity().zoomImage(mybitmap1, getContext());
            }
        });

        img2.setClickable(true);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new clsMainActivity().zoomImage(mybitmap2, getContext());
            }
        });
    }
}
