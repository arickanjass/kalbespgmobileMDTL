package com.kalbenutritionals.app.kalbespgmobile;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import adapter.AppAdapterViewCusBase;
import bl.clsHelperBL;
import bl.tOverStockDetailBL;
import bl.tOverStockHeaderBL;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;
import library.spgmobile.common.clsSwipeList;
import library.spgmobile.common.tOverStockDetailData;
import library.spgmobile.common.tOverStockHeaderData;
import library.spgmobile.common.visitplanAbsenData;

/**
 * Created by aan.junianto on 15/09/2017.
 */

public class FragmentViewOverStockMD extends Fragment implements IXListViewListener {
    View v;

    private static List<clsSwipeList> swipeList = new ArrayList<clsSwipeList>();
    private AppAdapterViewCusBase mAdapter;
    private PullToRefreshSwipeMenuListView mListView;
    private Handler mHandler;
    private static Map<String, HashMap> mapMenu;
    static List<tOverStockHeaderData> dt;
    static List<tOverStockDetailData> data;
//    static List<tSalesProductQuantityImageData> dataImage;
    private FloatingActionButton fab;

    private SliderLayout mDemoSlider;

    private static Bitmap mybitmap1;
    private static Bitmap mybitmap2;
    private static Bitmap mybitmap3;
    private static Bitmap mybitmap4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_view_quantity_stock, container, false);

        fab = (FloatingActionButton) v.findViewById(R.id.fabQuntity);

        // click Button +
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                toolbar.setTitle("Add Over Stock");

                FragmentAddOverStockMD fragmentAddOverStockMD = new FragmentAddOverStockMD();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragmentAddOverStockMD);
                fragmentTransaction.commit();
            }
        });

        loadData();

        return v;
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                mListView.stopRefresh();
                mListView.stopLoadMore();
            }
        }, 500);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoad();
            }
        }, 1);
    }

    private void onLoad() {
        mListView.setRefreshTime(RefreshTime.getRefreshTime(getActivity().getApplicationContext()));
        mListView.stopRefresh();
        mListView.stopLoadMore();
    }

    private void viewList(Context ctx, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.activity_preview_quantity, null);

        final TextView _tvNoSO = (TextView) promptView.findViewById(R.id.tvnoSOtbl);
        final TextView _tvKet = (TextView) promptView.findViewById(R.id.tvkettbl);
        _tvNoSO.setText(dt.get(position).get_txtOverStock());
        _tvKet.setText(dt.get(position).get_txtKeterangan());
        final TextView tv_item = (TextView) promptView.findViewById(R.id.tvItemtbl);
        tv_item.setTypeface(null, Typeface.BOLD);
        tv_item.setText(String.valueOf(dt.get(position).get_intSumItem()));
        final  TextView tv_amount = (TextView) promptView.findViewById(R.id.tvSumAmount) ;
        tv_amount.setTypeface(null, Typeface.BOLD);
        tv_amount.setText(String.valueOf(dt.get(position).get_intSumAmount()));
        final  TextView tv_status = (TextView) promptView.findViewById(R.id.tvStatus);
        tv_status.setTypeface(null, Typeface.BOLD);

        final TableRow tr_neared = (TableRow) promptView.findViewById(R.id.tr_neared);
        tr_neared.setVisibility(View.GONE);

        if (dt.get(position).get_intSubmit().equals("1")&&dt.get(position).get_intSync().equals("0")){
            tv_status.setText("submit");
        } else if (dt.get(position).get_intSubmit().equals("1")&&dt.get(position).get_intSync().equals("1")){
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

        data = new tOverStockDetailBL().GetDataNoOverStock(dt.get(position).get_txtOverStock());

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
            product.setWidth(400);
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
            qty.setText(dat.getTxtQuantity() +" pcs");
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

//    private void viewImage(Context ctx, final int position) {
//        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
//        final View promptView = layoutInflater.inflate(R.layout.fragment_quantity_view_image, null);
//
//        final LinearLayout lnlayout = (LinearLayout) promptView.findViewById(R.id.lnlayoutQuantity);
//
//        lnlayout.setFocusable(true);
//        lnlayout.setFocusableInTouchMode(true);
//
//        final TextView etDesc = (TextView) promptView.findViewById(R.id.etNamaQuantity);
//        final ImageButton img1 = (ImageButton) promptView.findViewById(R.id.imageButtonQuantity);
//        final ImageButton img2 = (ImageButton) promptView.findViewById(R.id.imageButton2Quantity);
//        final ImageButton img3 = (ImageButton) promptView.findViewById(R.id.imageButton3Quantity);
//        final ImageButton img4 = (ImageButton) promptView.findViewById(R.id.imageButton4Quantity);
//        final Button btnSave = (Button) promptView.findViewById(R.id.btnSaveQuantity);
//        final RadioButton rbKalbe = (RadioButton) promptView.findViewById(R.id.rbKalbeQuantity);
//        final RadioButton rbCompetitor = (RadioButton) promptView.findViewById(R.id.rbCompetitorQuantity);
//        final TextView status = (TextView) promptView.findViewById(textView9Quantity);
//        mDemoSlider = (SliderLayout) promptView.findViewById(R.id.sliderQuantity);
//
//        String statusText = dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("1") ? "Sync" : "Submit";
//        dataImage = new tSalesProductQuantityImageBL().getDataHeaderId(dt.get(position).get_txtQuantityStock());
//
//        status.setText("Status : " +statusText);
//
//        rbKalbe.setTextColor(Color.BLACK);
//        rbKalbe.setEnabled(false);
//
//        img1.setEnabled(true);
//        img2.setEnabled(true);
//        img3.setEnabled(true);
//        img4.setEnabled(true);
//
//        btnSave.setVisibility(View.GONE);
//        etDesc.setText(dt.get(position).get_txtKeterangan());
//
//        File folder = new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata");
//        folder.mkdir();
//
////        final byte[] imgFile = dt.get(position).get_txtBeforeImg1();
//        for (tSalesProductQuantityImageData imgDt : dataImage){
//            final byte[] imgFile = imgDt.get_txtImage();
//            if (imgFile != null) {
//                if (imgDt.get_txtType().equals("After") && imgDt.get_intPosition().equals("1")) {
//                    mybitmap1 = BitmapFactory.decodeByteArray(imgFile, 0, imgFile.length);
//                    Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap1, 150, 150, true);
//                    img1.setImageBitmap(bitmap);
//
//                    File file = null;
//                    try {
//                        file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
//                        FileOutputStream out = new FileOutputStream(file);
//                        out.write(imgFile);
//                        out.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    TextSliderView textSliderView = new TextSliderView(getContext());
//                    textSliderView
//                            .description("After 1")
//                            .image(file)
//                            .setScaleType(BaseSliderView.ScaleType.Fit)
//                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
//                                @Override
//                                public void onSliderClick(BaseSliderView slider) {
//                                    new clsMainActivity().zoomImage(mybitmap1, getActivity());
//                                }
//                            });
//
//                    mDemoSlider.addSlider(textSliderView);
//                }
//            } else {
//                img1.setVisibility(View.GONE);
//            }
//
//            final byte[] imgFile2 = imgDt.get_txtImage();
//            if (imgFile2 != null) {
//                if (imgDt.get_txtType().equals("After") && imgDt.get_intPosition().equals("2")) {
//                    mybitmap2 = BitmapFactory.decodeByteArray(imgFile2, 0, imgFile2.length);
//                    Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap2, 150, 150, true);
//                    img2.setImageBitmap(bitmap);
//
//                    File file = null;
//                    try {
//                        file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
//                        FileOutputStream out = new FileOutputStream(file);
//                        out.write(imgFile2);
//                        out.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    TextSliderView textSliderView = new TextSliderView(getContext());
//                    textSliderView
//                            .description("After 2")
//                            .image(file)
//                            .setScaleType(BaseSliderView.ScaleType.Fit)
//                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
//                                @Override
//                                public void onSliderClick(BaseSliderView slider) {
//                                    new clsMainActivity().zoomImage(mybitmap2, getActivity());
//                                }
//                            });
//
//                    mDemoSlider.addSlider(textSliderView);
//                }
//            } else {
//                img2.setVisibility(View.GONE);
//            }
//
//            final byte[] imgFile3 = imgDt.get_txtImage();
//            if (imgFile3 != null) {
//                if (imgDt.get_txtType().equals("Before") && imgDt.get_intPosition().equals("1")) {
//                    mybitmap3 = BitmapFactory.decodeByteArray(imgFile3, 0, imgFile3.length);
//                    Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap3, 150, 150, true);
//                    img3.setImageBitmap(bitmap);
//
//                    File file = null;
//                    try {
//                        file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
//                        FileOutputStream out = new FileOutputStream(file);
//                        out.write(imgFile3);
//                        out.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    TextSliderView textSliderView = new TextSliderView(getContext());
//                    textSliderView
//                            .description("Before 1")
//                            .image(file)
//                            .setScaleType(BaseSliderView.ScaleType.Fit)
//                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
//                                @Override
//                                public void onSliderClick(BaseSliderView slider) {
//                                    new clsMainActivity().zoomImage(mybitmap3, getActivity());
//                                }
//                            });
//
//                    mDemoSlider.addSlider(textSliderView);
//                }
//            } else {
//                img3.setVisibility(View.GONE);
//            }
//
//            final byte[] imgFile4 = imgDt.get_txtImage();
//            if (imgFile4 != null) {
//                if (imgDt.get_txtType().equals("Before") && imgDt.get_intPosition().equals("2")) {
//                    mybitmap4 = BitmapFactory.decodeByteArray(imgFile4, 0, imgFile4.length);
//                    Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap4, 150, 150, true);
//                    img4.setImageBitmap(bitmap);
//
//                    File file = null;
//                    try {
//                        file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
//                        FileOutputStream out = new FileOutputStream(file);
//                        out.write(imgFile4);
//                        out.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    TextSliderView textSliderView = new TextSliderView(getContext());
//                    textSliderView
//                            .description("Before 2")
//                            .image(file)
//                            .setScaleType(BaseSliderView.ScaleType.Fit)
//                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
//                                @Override
//                                public void onSliderClick(BaseSliderView slider) {
//                                    new clsMainActivity().zoomImage(mybitmap4, getActivity());
//                                }
//                            });
//
//                    mDemoSlider.addSlider(textSliderView);
//                }
//            } else {
//                img4.setVisibility(View.GONE);
//            }
//
//            if (imgFile == null || imgFile2 == null || imgFile3 == null || imgFile4 == null) {
//                mDemoSlider.stopAutoCycle();
//                mDemoSlider.setPagerTransformer(false, new BaseTransformer() {
//                    @Override
//                    protected void onTransform(View view, float v) {
//                    }
//                });
//            } else {
//                mDemoSlider.stopAutoCycle();
//                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
//                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//                mDemoSlider.setCustomAnimation(new DescriptionAnimation());
//                mDemoSlider.setDuration(4000);
//            }
//        }
//
//        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
//        alertDialogBuilder.setView(promptView);
//        alertDialogBuilder
//                .setCancelable(false)
//                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        new clsMainActivity().deleteTempFolder();
//                        dialog.cancel();
//                    }
//                });
//        final AlertDialog alertD = alertDialogBuilder.create();
//        alertD.show();
//
//        img1.setClickable(true);
//        img1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new clsMainActivity().zoomImage(mybitmap1, getActivity());
//            }
//        });
//
//        img2.setClickable(true);
//        img2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new clsMainActivity().zoomImage(mybitmap2, getActivity());
//            }
//        });
//
//        img3.setClickable(true);
//        img3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new clsMainActivity().zoomImage(mybitmap3, getActivity());
//            }
//        });
//
//        img4.setClickable(true);
//        img4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new clsMainActivity().zoomImage(mybitmap4, getActivity());
//            }
//        });
//    }

    private void loadData() {
//        tAbsenUserData dtActive = new tAbsenUserBL().getDataCheckInActive();
        visitplanAbsenData dtActive = new clsHelperBL().getDataCheckInActive();

        clsSwipeList swplist;
        dt = new tOverStockHeaderBL().getAllOverStockHeaderByOutletCode(dtActive.get_txtOutletCode());

        swipeList.clear();

        if (dt != null) {
            for (int i = 0; i < dt.size(); i++) {
                swplist = new clsSwipeList();
                swplist.set_txtTitle(dt.get(i).get_txtOverStock());
                String desc = dt.get(i).get_txtKeterangan();
                if(desc.length()>20){
                    desc = dt.get(i).get_txtKeterangan().substring(0,20) + "...";
                }
                swplist.set_txtDescription("Description : " + desc);
                if (dt.get(i).get_intSubmit().equals("1")&&dt.get(i).get_intSync().equals("0")){
                    swplist.set_txtDescription2("Submit");
                } else if (dt.get(i).get_intSubmit().equals("1")&&dt.get(i).get_intSync().equals("1")){
                    swplist.set_txtDescription2("Sync");
                }

                swipeList.add(swplist);
            }
        }

        clsMainActivity clsMain = new clsMainActivity();

        mListView = (PullToRefreshSwipeMenuListView) v.findViewById(R.id.listViewQuntity);
        mAdapter = clsMain.setListViewCusBase(getActivity().getApplicationContext(), swipeList);
        mListView.setAdapter(mAdapter);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        mListView.setEmptyView(v.findViewById(R.id.LayoutEmptyQuntity));
        mListView.setXListViewListener(this);
        mHandler = new Handler();

        HashMap<String, String> mapView = new HashMap<String, String>();
//        HashMap<String, String> mapImage = new HashMap<String, String>();

        mapView.put("name", "View");
        mapView.put("bgColor", "#3498db");

//        mapImage.put("name", "View");
//        mapImage.put("bgColor", "#FF0000");

        mapMenu = new HashMap<String, HashMap>();
        mapMenu.put("0", mapView);
//        mapMenu.put("1", mapImage);

        SwipeMenuCreator creator = clsMain.setCreator(getActivity().getApplicationContext(), mapMenu);
        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                clsSwipeList item = swipeList.get(position);
                switch (index) {
                    case 0:
                        viewList(getActivity().getApplicationContext(), position);
                        break;
//                    case 1:
//                        viewImage(getActivity().getApplicationContext(), position);
                }
            }
        });

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        RefreshTime.setRefreshTime(getContext(), " " + df.format(new Date()));
        mListView.setRefreshTime(RefreshTime.getRefreshTime(getActivity().getApplicationContext()));

    }
}
