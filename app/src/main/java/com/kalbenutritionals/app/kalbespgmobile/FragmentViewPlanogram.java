package com.kalbenutritionals.app.kalbespgmobile;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Transformers.BaseTransformer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import adapter.AppAdapterViewCusBase;
import bl.clsHelperBL;
import bl.tPlanogramImageBL;
import bl.tPlanogramMobileBL;
import bl.tSalesProductQuantityHeaderBL;
import bl.tSalesProductQuantityImageBL;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;
import library.spgmobile.common.AppAdapter;
import library.spgmobile.common.clsSwipeList;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tPlanogramImageData;
import library.spgmobile.common.tPlanogramMobileData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tSalesProductQuantityImageData;
import library.spgmobile.common.visitplanAbsenData;

import static com.kalbenutritionals.app.kalbespgmobile.R.id.textView9;
import static com.kalbenutritionals.app.kalbespgmobile.R.id.textView9Quantity;

/**
 * Created by aan.junianto on 10/08/2017.
 */

public class FragmentViewPlanogram extends Fragment implements IXListViewListener{

    private List<clsSwipeList> swipeList = new ArrayList<clsSwipeList>();
    private AppAdapter mAdapter;

    private PullToRefreshSwipeMenuListView mListView;

    private Map<String, HashMap> mapMenu;
    private SliderLayout mDemoSlider;

    private Bitmap mybitmap1;
    private Bitmap mybitmap2;
    private Bitmap mybitmap3;
    private Bitmap mybitmap4;

    private FloatingActionButton fab;
    Handler mHandler;

    private List<tPlanogramMobileData> dt;

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_view_global, container, false);

        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                toolbar.setTitle("Add Planogram");

                FragmentAddPlanogram fragmentAddPlanogram = new FragmentAddPlanogram();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragmentAddPlanogram);
                fragmentTransaction.commit();
            }
        });
        final PullToRefreshSwipeMenuListView swipeMenuList = (PullToRefreshSwipeMenuListView) v.findViewById(R.id.SwipelistView);
        swipeMenuList.setPullRefreshEnable(true);
//        swipeMenuList.setOnScrollListener(new AbsListView.OnScrollListener() {
//            private int previousDistanceFromFirstCellToTop;
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                View firstCell = mListView2.getChildAt(0);
//                int distanceFromFirstCellTop = mListView2.getFirstVisiblePosition() * firstCell.getHeight()-firstCell.getTop();
//
//
//                if(distanceFromFirstCellTop > previousDistanceFromFirstCellToTop){
//                    fab.hide();
//                }
//                if(distanceFromFirstCellTop < previousDistanceFromFirstCellToTop){
//                    fab.show();
//                }
//                previousDistanceFromFirstCellToTop = distanceFromFirstCellTop;
//            }
//        });
        loadData();
        return v;
    }

    private void loadData() {
        visitplanAbsenData dtActive = new clsHelperBL().getDataCheckInActive();

        clsSwipeList swplist;
        dt = new tPlanogramMobileBL().getAllPlanogramByOutletCode(dtActive.get_txtOutletCode());

        swipeList.clear();

        if (dt != null) {
            for (int i = 0; i < dt.size(); i++) {
                String status = dt.get(i).get_intSubmit().equals("1") && dt.get(i).get_intSync().equals("1") ? "Sync" : "Submit";
                swplist = new clsSwipeList();
                swplist.set_txtTitle("Outlet : " + dt.get(i).get_OutletName());
                swplist.set_txtDescription("Description : " + dt.get(i).get_txtKeterangan() + "\n" + status);
                swipeList.add(swplist);
            }
        }

        clsMainActivity clsMain = new clsMainActivity();

        mListView = (PullToRefreshSwipeMenuListView) v.findViewById(R.id.SwipelistView);
        mAdapter = clsMain.setList(getActivity().getApplicationContext(), swipeList);
        mListView.setAdapter(mAdapter);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        mListView.setEmptyView(v.findViewById(R.id.LayoutEmpty));
        mHandler = new Handler();

        HashMap<String, String> mapView = new HashMap<String, String>();

        mapView.put("name", "View");
        mapView.put("bgColor", "#3498db");

        mapMenu = new HashMap<String, HashMap>();
        mapMenu.put("0", mapView);

        SwipeMenuCreator creator = clsMain.setCreator(getActivity().getApplicationContext(), mapMenu);
        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        viewImage(getActivity().getApplicationContext(), position);
                }
            }
        });

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        RefreshTime.setRefreshTime(getContext(), " " + df.format(new Date()));
        mListView.setRefreshTime(RefreshTime.getRefreshTime(getActivity().getApplicationContext()));
    }

    private List<tPlanogramImageData> dataImage;
    private void viewImage(Context ctx, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        final View promptView = layoutInflater.inflate(R.layout.fragment_quantity_view_image, null);

        final LinearLayout lnlayout = (LinearLayout) promptView.findViewById(R.id.lnlayoutQuantity);

        lnlayout.setFocusable(true);
        lnlayout.setFocusableInTouchMode(true);

        final TextView etDesc = (TextView) promptView.findViewById(R.id.etNamaQuantity);
        final ImageButton img1 = (ImageButton) promptView.findViewById(R.id.imageButtonQuantity);
        final ImageButton img2 = (ImageButton) promptView.findViewById(R.id.imageButton2Quantity);
        final ImageButton img3 = (ImageButton) promptView.findViewById(R.id.imageButton3Quantity);
        final ImageButton img4 = (ImageButton) promptView.findViewById(R.id.imageButton4Quantity);
        final Button btnSave = (Button) promptView.findViewById(R.id.btnSaveQuantity);
        final RadioButton rbKalbe = (RadioButton) promptView.findViewById(R.id.rbKalbeQuantity);
        final RadioButton rbCompetitor = (RadioButton) promptView.findViewById(R.id.rbCompetitorQuantity);
        final TextView status = (TextView) promptView.findViewById(textView9Quantity);
        mDemoSlider = (SliderLayout) promptView.findViewById(R.id.sliderQuantity);

        String statusText = dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("1") ? "Sync" : "Submit";
        dataImage = new tPlanogramImageBL().getDataHeaderId(dt.get(position).get_txtIdPlanogram());

        status.setText(statusText);

        rbKalbe.setTextColor(Color.BLACK);
        rbKalbe.setEnabled(false);

        img1.setEnabled(true);
        img2.setEnabled(true);
        img3.setEnabled(true);
        img4.setEnabled(true);

        btnSave.setVisibility(View.GONE);
        etDesc.setText(dt.get(position).get_txtKeterangan());

        File folder = new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata");
        folder.mkdir();

//        final byte[] imgFile = dt.get(position).get_txtBeforeImg1();
        for (tPlanogramImageData imgDt : dataImage){
            final byte[] imgFile = imgDt.get_txtImage();
            if (imgFile != null) {
                if (imgDt.get_txtType().equals("AFTER") && imgDt.get_intPosition().equals("1")) {
                    mybitmap1 = BitmapFactory.decodeByteArray(imgFile, 0, imgFile.length);
                    Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap1, 150, 150, true);
                    img1.setImageBitmap(bitmap);

                    File file = null;
                    try {
                        file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
                        FileOutputStream out = new FileOutputStream(file);
                        out.write(imgFile);
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    TextSliderView textSliderView = new TextSliderView(getContext());
                    textSliderView
                            .description(imgDt.get_txtType())
                            .image(file)
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                @Override
                                public void onSliderClick(BaseSliderView slider) {
                                    new clsMainActivity().zoomImage(mybitmap1, getActivity());
                                }
                            });

                    mDemoSlider.addSlider(textSliderView);
                }
            } else {
                img1.setVisibility(View.GONE);
            }

            final byte[] imgFile2 = imgDt.get_txtImage();
            if (imgFile2 != null) {
                if (imgDt.get_txtType().equals("AFTER") && imgDt.get_intPosition().equals("2")) {
                    mybitmap2 = BitmapFactory.decodeByteArray(imgFile2, 0, imgFile2.length);
                    Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap2, 150, 150, true);
                    img2.setImageBitmap(bitmap);

                    File file = null;
                    try {
                        file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
                        FileOutputStream out = new FileOutputStream(file);
                        out.write(imgFile2);
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    TextSliderView textSliderView = new TextSliderView(getContext());
                    textSliderView
                            .description(imgDt.get_txtType())
                            .image(file)
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                @Override
                                public void onSliderClick(BaseSliderView slider) {
                                    new clsMainActivity().zoomImage(mybitmap2, getActivity());
                                }
                            });

                    mDemoSlider.addSlider(textSliderView);
                }
            } else {
                img2.setVisibility(View.GONE);
            }

            final byte[] imgFile3 = imgDt.get_txtImage();
            if (imgFile3 != null) {
                if (imgDt.get_txtType().equals("BEFORE") && imgDt.get_intPosition().equals("1")) {
                    mybitmap3 = BitmapFactory.decodeByteArray(imgFile3, 0, imgFile3.length);
                    Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap3, 150, 150, true);
                    img3.setImageBitmap(bitmap);

                    File file = null;
                    try {
                        file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
                        FileOutputStream out = new FileOutputStream(file);
                        out.write(imgFile3);
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    TextSliderView textSliderView = new TextSliderView(getContext());
                    textSliderView
                            .description(imgDt.get_txtType())
                            .image(file)
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                @Override
                                public void onSliderClick(BaseSliderView slider) {
                                    new clsMainActivity().zoomImage(mybitmap3, getActivity());
                                }
                            });

                    mDemoSlider.addSlider(textSliderView);
                }
            } else {
                img3.setVisibility(View.GONE);
            }

            final byte[] imgFile4 = imgDt.get_txtImage();
            if (imgFile4 != null) {
                if (imgDt.get_txtType().equals("BEFORE") && imgDt.get_intPosition().equals("2")) {
                    mybitmap4 = BitmapFactory.decodeByteArray(imgFile4, 0, imgFile4.length);
                    Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap4, 150, 150, true);
                    img4.setImageBitmap(bitmap);

                    File file = null;
                    try {
                        file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
                        FileOutputStream out = new FileOutputStream(file);
                        out.write(imgFile4);
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    TextSliderView textSliderView = new TextSliderView(getContext());
                    textSliderView
                            .description(imgDt.get_txtType())
                            .image(file)
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                @Override
                                public void onSliderClick(BaseSliderView slider) {
                                    new clsMainActivity().zoomImage(mybitmap4, getActivity());
                                }
                            });

                    mDemoSlider.addSlider(textSliderView);
                }
            } else {
                img4.setVisibility(View.GONE);
            }

            if (imgFile == null || imgFile2 == null || imgFile3 == null || imgFile4 == null) {
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
                new clsMainActivity().zoomImage(mybitmap1, getActivity());
            }
        });

        img2.setClickable(true);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new clsMainActivity().zoomImage(mybitmap2, getActivity());
            }
        });

        img3.setClickable(true);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new clsMainActivity().zoomImage(mybitmap3, getActivity());
            }
        });

        img4.setClickable(true);
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new clsMainActivity().zoomImage(mybitmap4, getActivity());
            }
        });
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
                loadData();
            }
        }, 1);
    }
}