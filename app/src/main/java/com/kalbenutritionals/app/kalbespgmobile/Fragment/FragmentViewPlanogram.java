package com.kalbenutritionals.app.kalbespgmobile.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.kalbenutritionals.app.kalbespgmobile.R;
import com.kalbenutritionals.app.kalbespgmobile.clsMainActivity;

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
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import library.spgmobile.common.clsSwipeList;
import library.spgmobile.common.tPlanogramImageData;
import library.spgmobile.common.tPlanogramMobileData;
import library.spgmobile.common.visitplanAbsenData;
import library.spgmobile.dal.clsHardCode;

/**
 * Created by aan.junianto on 10/08/2017.
 */

public class FragmentViewPlanogram extends Fragment implements IXListViewListener{

    private List<clsSwipeList> swipeList = new ArrayList<clsSwipeList>();
    private AppAdapterViewCusBase mAdapter;

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
        v = inflater.inflate(R.layout.fragment_view_global_new, container, false);

        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        final FabSpeedDial fabSpeedDial = (FabSpeedDial) v.findViewById(R.id.fabView);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//                toolbar.setTitle("Add Planogram");
//
//                FragmentAddPlanogram fragmentAddPlanogram = new FragmentAddPlanogram();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame, fragmentAddPlanogram);
//                fragmentTransaction.commit();
//            }
//        });
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                NavigationView nv = (NavigationView) getActivity().findViewById(R.id.navigation_view);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                switch(menuItem.getItemId()){

                    case R.id.action_add:
                        toolbar.setTitle("Add Planogram");
//                        nv.setCheckedItem(2);
                        FragmentAddPlanogram fragmentAddPlanogram = new FragmentAddPlanogram();
                        Bundle args = new Bundle();
                        args.putString("id", "null");
                        args.putString("param", "null");
                        fragmentAddPlanogram.setArguments(args);
                        fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragmentAddPlanogram);
                        fragmentTransaction.commit();
                        return true;

                    case R.id.action_submitAll:
                        saveDataSubmitAll();
//                        new clsMainActivity().showCustomToast(getContext(), "submit", true);
                        return true;

                    default:
                        return false;
                }
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

    private void saveDataSubmitAll() {

//        tAbsenUserData dtActive = new tAbsenUserBL().getDataCheckInActive();

        visitplanAbsenData _viAbsenData = new visitplanAbsenData();
        _viAbsenData = new clsHelperBL().getDataCheckInActive();

        final List<tPlanogramMobileData> data = new tPlanogramMobileBL().getAllDataSelectImageNotNullByOutletUnsubmit(_viAbsenData.get_txtOutletCode());

        if(data!=null&&data.size()!=0){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure to submit all transaction ? ");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    for(tPlanogramMobileData dt : data){
                        new tPlanogramMobileBL().updateDataSubmit(dt);
                    }
                    new clsMainActivity().showCustomToast(getContext(), "submit successfull", true);
                    loadData();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        } else {
            new clsMainActivity().showCustomToast(getContext(), "There is No data to submit", false);
        }
    }

    private void loadData() {
        visitplanAbsenData dtActive = new clsHelperBL().getDataCheckInActive();

        clsSwipeList swplist;
        dt = new tPlanogramMobileBL().getAllHeaderByOutletCodeForView(dtActive.get_txtOutletCode());

        swipeList.clear();

        String status ="";
        if (dt != null) {
            for (int i = 0; i < dt.size(); i++) {
                if(dt.get(i).get_intSubmit().equals("1") && dt.get(i).get_intSync().equals("1")){
                    status = "Sync";
                } else if (dt.get(i).get_intSubmit().equals("1") && dt.get(i).get_intSync().equals("0")){
                    status = "Submit";
                } else if(dt.get(i).get_intSubmit().equals("0") && dt.get(i).get_intSync().equals("0")){
                    status = "Saved";
                }
                swplist = new clsSwipeList();
                swplist.set_txtTitle("Category : " + dt.get(i).get_txtCategoryName());
                String desc = dt.get(i).get_txtKeterangan();
                if(desc.length()>20){
                    desc = dt.get(i).get_txtKeterangan().substring(0,20) + "...";
                }
                swplist.set_txtDescription("Description : " + desc);
                swplist.set_txtDescription2(status);
                swipeList.add(swplist);;
            }
        }

        clsMainActivity clsMain = new clsMainActivity();

        mListView = (PullToRefreshSwipeMenuListView) v.findViewById(R.id.SwipelistView);
        mAdapter = clsMain.setListViewCusBase(getActivity().getApplicationContext(), swipeList);
        mListView.setAdapter(mAdapter);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        mListView.setEmptyView(v.findViewById(R.id.LayoutEmpty));
        mHandler = new Handler();

        HashMap<String, String> mapView = new HashMap<String, String>();
        HashMap<String, String> mapEdit = new HashMap<String, String>();
        HashMap<String, String> mapDelete = new HashMap<String, String>();

        mapView.put("name", "View");
        mapView.put("bgColor", "#3498db");

        mapEdit.put("name", "Edit");
        mapEdit.put("bgColor", "#2980b9");

        mapDelete.put("name", "Delete");
        mapDelete.put("bgColor", "#c0392b");

        mapMenu = new HashMap<String, HashMap>();
        mapMenu.put("0", mapView);
        mapMenu.put("1", mapEdit);
        mapMenu.put("2", mapDelete);

        SwipeMenuCreator creator = clsMain.setCreatorForCusBased(getActivity().getApplicationContext(), mapMenu);
        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        viewImage(getActivity().getApplicationContext(), position);
                        break;
                    case 1:
                        editList(getActivity().getApplicationContext(), position);
//                        new clsMainActivity().showCustomToast(getContext(), "edit", true);
                        break;
                    case 2:
                        deleteList(getActivity().getApplicationContext(), position);
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
        final TextView status = (TextView) promptView.findViewById(R.id.textView9Quantity);
        final TextView tv_category = (TextView) promptView.findViewById(R.id.tv_category);
        final TextView tv_valid = (TextView) promptView.findViewById(R.id.tv_valid);
        mDemoSlider = (SliderLayout) promptView.findViewById(R.id.sliderQuantity);

        String statusTran = "";
        if(dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("1")){
            statusTran = "Sync";
        } else if (dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("0")){
            statusTran = "Submit";
        } else if(dt.get(position).get_intSubmit().equals("0") && dt.get(position).get_intSync().equals("0")){
            statusTran = "Save";
        }
        dataImage = new tPlanogramImageBL().getDataHeaderId(dt.get(position).get_txtIdPlanogram());

        String validDisplay = dt.get(position).get_intIsValid().equals("1") ? "Plano Sesuai" : "Plano Tidak Sesuai";

        if(dt.get(position).get_intIsValid().toString().equals("null")){
                validDisplay = "";
        }

        tv_category.setVisibility(View.VISIBLE);
        tv_category.setText("Category : "+dt.get(position).get_txtCategoryName().toString());

        tv_valid.setVisibility(View.VISIBLE);
        tv_valid.setText(validDisplay);

        status.setText("Status : "+statusTran);

        rbKalbe.setTextColor(Color.BLACK);
        rbKalbe.setEnabled(false);

        img1.setEnabled(true);
        img2.setEnabled(true);
        img3.setEnabled(true);
        img4.setEnabled(true);

        btnSave.setVisibility(View.GONE);
        etDesc.setText(dt.get(position).get_txtKeterangan());

//        File folder = new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata");
        File folder = new File(new clsHardCode().txtPathTempData);
        folder.mkdir();

        boolean validSubmitImageBefore = false;
        boolean validSubmitImageAfter = false;

//        final byte[] imgFile = dt.get(position).get_txtBeforeImg1();
        for (tPlanogramImageData imgDt : dataImage){
            final byte[] imgFile = imgDt.get_txtImage();
            if (imgFile != null) {
                if (imgDt.get_txtType().equals("AFTER") && imgDt.get_intPosition().equals("1")) {
                    validSubmitImageAfter = true;
                    mybitmap1 = BitmapFactory.decodeByteArray(imgFile, 0, imgFile.length);
                    Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap1, 150, 150, true);
                    img1.setImageBitmap(bitmap);

                    File file = null;
                    try {
//                        file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
                        file = File.createTempFile("image-", ".jpg", new File(new clsHardCode().txtPathTempData));
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
                    validSubmitImageAfter = true;
                    mybitmap2 = BitmapFactory.decodeByteArray(imgFile2, 0, imgFile2.length);
                    Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap2, 150, 150, true);
                    img2.setImageBitmap(bitmap);

                    File file = null;
                    try {
//                        file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
                        file = File.createTempFile("image-", ".jpg", new File(new clsHardCode().txtPathTempData));
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
                    validSubmitImageBefore = true;
                    mybitmap3 = BitmapFactory.decodeByteArray(imgFile3, 0, imgFile3.length);
                    Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap3, 150, 150, true);
                    img3.setImageBitmap(bitmap);

                    File file = null;
                    try {
//                        file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
                        file = File.createTempFile("image-", ".jpg", new File(new clsHardCode().txtPathTempData));
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
                    validSubmitImageBefore = true;
                    mybitmap4 = BitmapFactory.decodeByteArray(imgFile4, 0, imgFile4.length);
                    Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap4, 150, 150, true);
                    img4.setImageBitmap(bitmap);

                    File file = null;
                    try {
//                        file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
                        file = File.createTempFile("image-", ".jpg", new File(new clsHardCode().txtPathTempData));
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
        final boolean finalValidSubmitImageBefore = validSubmitImageBefore;
        final boolean finalValidSubmitImageAfter = validSubmitImageAfter;
        alertDialogBuilder
                .setCancelable(false)
                .setNeutralButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(finalValidSubmitImageBefore && finalValidSubmitImageAfter){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                            builder.setTitle("Warning");
                            builder.setMessage("Are you sure to submit ");

                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    tPlanogramMobileData data = new tPlanogramMobileData();
                                    data.set_txtIdPlanogram(dt.get(position).get_txtIdPlanogram());
                                    data.set_intSubmit("1");
                                    new tPlanogramMobileBL().updateDataSubmit(data);
                                    dialog.cancel();
                                    loadData();
                                }
                            });

                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alert = builder.create();
                            alert.show();
                        } else {
                            String mess = !finalValidSubmitImageBefore ? "Please take photo Before" : "Please take photo After";
                            new clsMainActivity().showCustomToast(getContext(), mess, false);
                        }
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new clsMainActivity().deleteTempFolder();
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();

        if(!statusTran.equals("")&&statusTran.equals("Save")){
            alertD.getButton(AlertDialog.BUTTON_NEUTRAL).setEnabled(true);
        } else {
            alertD.getButton(AlertDialog.BUTTON_NEUTRAL).setEnabled(false);
        }

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

    private void editList(Context applicationContext, int position) {
        boolean isEditalbe = false;
        String statusTran = "";
        if(dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("1")){
            statusTran = "Syncronized";
        } else if (dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("0")){
            statusTran = "Submit";
        } else if(dt.get(position).get_intSubmit().equals("0") && dt.get(position).get_intSync().equals("0")){
            statusTran = "Save";
            isEditalbe = true;
        }

        if(isEditalbe){
            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            toolbar.setTitle("Add Planogram");

            FragmentAddPlanogram fragmentAddPlanogram = new FragmentAddPlanogram();
            Bundle args = new Bundle();
            args.putString("id", dt.get( position).get_txtIdPlanogram());
            args.putString("param", "edit");
            fragmentAddPlanogram.setArguments(args);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragmentAddPlanogram);
            fragmentTransaction.commit();
        } else {
            String name = dt.get( position).get_txtCategoryName().toString();
            new clsMainActivity().showCustomToast(getContext(), "Cannot edit, " + "\n data was " + statusTran, false);
        }
    }

    private void deleteList(Context applicationContext, final int position) {
        boolean isDeleteable = false;
        String statusTran = "";
        if(dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("1")){
            statusTran = "Syncronized";
        } else if (dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("0")){
            statusTran = "Submit";
        } else if(dt.get(position).get_intSubmit().equals("0") && dt.get(position).get_intSync().equals("0")){
            statusTran = "Save";
            isDeleteable = true;
        }

        if(isDeleteable){
            final List<tPlanogramImageData> detailData = new tPlanogramImageBL().getDataHeaderId(dt.get(position).get_txtIdPlanogram());

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setTitle("Warning");
            builder.setMessage("Are you sure to delete?" + "\n The operation cannot be undone...");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    new tPlanogramMobileBL().deleteTrId(dt.get( position).get_txtIdPlanogram());
                    if(detailData!=null&&detailData.size()>0){
                        for(tPlanogramImageData data : detailData){
                            new tPlanogramImageBL().deleteTrId(data.get_txtId());
                        }
                    }
                    new clsMainActivity().showCustomToast(getContext(), "Transaction data has been deleted", true);
                    loadData();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        } else {
            new clsMainActivity().showCustomToast(getContext(), "Cannot delete data, data was " + statusTran, false);
        }
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
