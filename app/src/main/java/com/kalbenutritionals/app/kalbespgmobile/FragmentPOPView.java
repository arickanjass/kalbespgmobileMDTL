package com.kalbenutritionals.app.kalbespgmobile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
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
import bl.tAbsenUserBL;
import bl.tPOPStandardDetailBL;
import bl.tPOPStandardHeaderBL;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;
import library.spgmobile.common.clsSwipeList;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tPOPStandardDetailData;
import library.spgmobile.common.tPOPStandardHeaderData;
import library.spgmobile.common.visitplanAbsenData;
import library.spgmobile.dal.clsHardCode;

import static com.kalbenutritionals.app.kalbespgmobile.R.id.fill;
import static com.kalbenutritionals.app.kalbespgmobile.R.id.ln_img_pop;
import static com.kalbenutritionals.app.kalbespgmobile.R.id.rl_pop;
import static com.kalbenutritionals.app.kalbespgmobile.R.id.textView9;

/**
 * Created by Dewi Oktaviani on 17/10/2017.
 */

public class FragmentPOPView extends Fragment implements IXListViewListener{
    View v;
    FloatingActionButton fab;
    Toolbar toolbar;
    String [] bundleType;
    private static List<clsSwipeList> swipeList = new ArrayList<clsSwipeList>();
    private AppAdapterViewCusBase mAdapter;
    private PullToRefreshSwipeMenuListView mListView;
    private Handler mHandler;
    private static Map<String, HashMap> mapMenu;
    private SliderLayout mDemoSlider;

    static List<tPOPStandardHeaderData> dt;
    tAbsenUserData dataOutletCheckIn;
    private static Bitmap mybitmap1;
    private static Bitmap mybitmap2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_popstandard_view, container, false);
        fab = (FloatingActionButton) v.findViewById(R.id.fabPOPStandard);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            bundleType = bundle.getStringArray("Key_POPId");
        }
        toolbar.setSubtitle(bundleType[0]);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                bundle.putStringArray("Key_POPId", bundleType);
                FragmentAddPOP fragmentAddPOP = new FragmentAddPOP();
                fragmentAddPOP.setArguments(bundle);
                FragmentTransaction fragmentTransactionPOP = getFragmentManager().beginTransaction();
                fragmentTransactionPOP.replace(R.id.frame, fragmentAddPOP);
                fragmentTransactionPOP.commit();
            }
        });
        loadData();
        return v;
    }

    private void loadData(){
        clsSwipeList swplist;
        dataOutletCheckIn = new tAbsenUserBL().getDataCheckInActive();
        dt  = new tPOPStandardHeaderBL().GetDataByOutletCode(dataOutletCheckIn.get_txtOutletCode(), bundleType[0]);

        swipeList.clear();

        for (int i = 0; i < dt.size(); i++){
            String status = dt.get(i).get_intSubmit().equals("1") && dt.get(i).get_intSync().equals("1") ? "Sync" : "Submit";
            swplist = new clsSwipeList();
            swplist.set_txtTitle("Type : " + dt.get(i).get_txtType() + "\n" + "Category : " + dt.get(i).get_txtCategory());
            String Status = "";
            if (dt.get(i).get_bolHavePOP().equals("1")){
                Status = "Yes";
            } else {
                Status = "No";
            }
            swplist.set_txtDescription("There is " + dt.get(i).get_txtType() + " : " + Status);
            swplist.set_txtDescription2(status);
            swipeList.add(swplist);
        }

        clsMainActivity clsMain = new clsMainActivity();

        mListView = (PullToRefreshSwipeMenuListView) v.findViewById(R.id.listViewPOPStandard);
        mAdapter = clsMain.setListViewCusBase(getActivity().getApplication(), swipeList);
        mListView.setAdapter(mAdapter);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        mListView.setEmptyView(v.findViewById(R.id.ln_pop_view));
        mHandler = new Handler();

        HashMap<String, String> mapView = new HashMap<>();

        mapView.put("name", "View");
        mapView.put("bgColor", "#3498db");

        mapMenu = new HashMap<String, HashMap>();
        mapMenu.put("0", mapView);
        SwipeMenuCreator creator = clsMain.setCreator(getActivity().getApplicationContext(), mapMenu);
        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        //isi nanti
                        viewList(getContext().getApplicationContext(), position);
                }
            }
        });
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        RefreshTime.setRefreshTime(getContext(), " " + df.format(new Date()));
        mListView.setRefreshTime(RefreshTime.getRefreshTime(getActivity().getApplicationContext()));
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

    private void viewList(Context ctx, final int position) {
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
        mDemoSlider = (SliderLayout) promptView.findViewById(R.id.sliderPOP);

        //adding outlet name di view
        final TextView tv_outlet = (TextView) promptView.findViewById(R.id.tv_date);
        tv_outlet.setVisibility(View.VISIBLE);

        visitplanAbsenData dtAbsensVisitplan = new clsHelperBL().getDataCheckInActive();
        String outlet = "-";

        if(dtAbsensVisitplan!=null){
            outlet = dt.get(position).get_txtOutletName();
            if(dt.get(position).get_txtOutletName().toString().equals("null")){
                outlet = "No Outlet";
            }
        }
        tv_outlet.setText(outlet);

        String statusText = dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("1") ? "Sync" : "Submit";

        status.setText("Status : " + statusText);
        category.setText(dt.get(position).get_txtCategory());

        etDesc.setText("There is " + dt.get(position).get_txtType() + " : ");
        String sts = "";
        if (dt.get(position).get_bolHavePOP().equals("1")){
            sts = "Yes";
            img1.setEnabled(true);
            img2.setEnabled(true);
            mDemoSlider.setVisibility(View.VISIBLE);
//            ln_img.setVisibility(View.VISIBLE);
        } else {
            sts = "No";
            reason.setText(dt.get(position).get_txtReason());
            rl_reason.setVisibility(View.VISIBLE);
        }
        statusPOP.setText(sts);

//        File folder = new File(Environment.getExtedernalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata");
        File folder = new File(new clsHardCode().txtPathTempData);
        folder.mkdir();

        tPOPStandardDetailData detail = new tPOPStandardDetailBL().GetDataById(dt.get(position).get_intId());
        final byte[] imgFile = detail.get_txtImg1();
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
                    .description(dt.get(position).get_txtType())
                    .image(file)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            new clsMainActivity().zoomImage(mybitmap1, getActivity());
                        }
                    });

            mDemoSlider.addSlider(textSliderView);

        } else {
            img1.setVisibility(View.GONE);
        }
        final byte[] imgFile2 = detail.get_txtImg2();
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
                    .description(dt.get(position).get_txtType())
                    .image(file)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            new clsMainActivity().zoomImage(mybitmap2, getActivity());
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
    }
}
