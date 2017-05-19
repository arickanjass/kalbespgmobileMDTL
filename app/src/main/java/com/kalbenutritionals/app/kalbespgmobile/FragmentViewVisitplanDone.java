package com.kalbenutritionals.app.kalbespgmobile;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Transformers.BaseTransformer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import addons.adapter.AdapterListVisitplan;
import bl.tVisitPlanRealisasiBL;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import library.salesforce.common.clsSwipeList;
import library.salesforce.common.tVisitPlanRealisasiData;

/**
 * Created by Robert on 16/05/2017.
 */

public class FragmentViewVisitplanDone extends Fragment implements IXListViewListener {
    View v;
    ListView lvVisitPlan;
    HashMap hmIdRealisasi = new HashMap<String, String>();;
    private AdapterListVisitplan mAdapter;
    private ArrayList<clsSwipeList> swipeListProduct = new ArrayList<clsSwipeList>();
    ImageView icon;
    private static Bitmap mybitmap1;
    private static Bitmap mybitmap2;
    private SliderLayout mDemoSlider;
    private String ID_REALISASI = "IdRealisasi";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_view_realisasi_header_done, container, false);
        lvVisitPlan = (ListView) v.findViewById(R.id.list_view_tRealisasi);

        loadData();
        return v;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
    private void loadData() {
        List<tVisitPlanRealisasiData> dtVisitPlan = new tVisitPlanRealisasiBL().getAllDataByIntSubmit("1");

        lvVisitPlan = (ListView) v.findViewById(R.id.list_view_tRealisasi);
        clsSwipeList swplist;

        swipeListProduct.clear();

        for (int i = 0; i < dtVisitPlan.size(); i++) {
            swplist = new clsSwipeList();
            swplist.set_txtId(dtVisitPlan.get(i).get_txtDataIDRealisasi());
            swplist.set_txtTitle(dtVisitPlan.get(i).get_txtOutletName());
            swplist.set_txtDescription(dtVisitPlan.get(i).get_txtDesc());
            hmIdRealisasi.put(i,dtVisitPlan.get(i).get_txtDataIDRealisasi());
            if(dtVisitPlan.get(i).get_intSubmit().equals("1")){
                swplist.set_intPIC("Done");
                lvVisitPlan.setClickable(false);
            }else{
                swplist.set_intPIC("Plan");
            }
            swipeListProduct.add(swplist);
        }

        clsMainActivity clsMain = new clsMainActivity();

        mAdapter = clsMain.setListVisitPlan(getActivity().getApplicationContext(), swipeListProduct);

        lvVisitPlan.setAdapter(mAdapter);
        lvVisitPlan.setEmptyView(v.findViewById(R.id.LayoutEmpty));
        lvVisitPlan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idR = hmIdRealisasi.get(position).toString();
                tVisitPlanRealisasiData data = new tVisitPlanRealisasiBL().getAllDataByHeaderId(idR);
                popUpDetail(data);
            }
        });

    }
    private void popUpDetail(tVisitPlanRealisasiData data){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.pop_up_visitplan_detail, null);

        final TextView outlet = (TextView) promptView.findViewById(R.id.tvNamaOutlet);
        final TextView dateVisited = (TextView) promptView.findViewById(R.id.tvDateVisited);
        final TextView txtDesc = (TextView) promptView.findViewById(R.id.description);
        final TextView txtDescReply = (TextView) promptView.findViewById(R.id.descriptionReply);
        final TextView txtLong = (TextView) promptView.findViewById(R.id.tvLong);
        final TextView txtLat = (TextView) promptView.findViewById(R.id.tvLat);
        final TextView txtAcc = (TextView) promptView.findViewById(R.id.tvAcc);
        final TextView txtDistance = (TextView) promptView.findViewById(R.id.tvDistance);
        final ImageButton img1 = (ImageButton) promptView.findViewById(R.id.imageVisitplan1);
        final ImageButton img2 = (ImageButton) promptView.findViewById(R.id.imageVisitplan2);
        mDemoSlider = (SliderLayout) promptView.findViewById(R.id.slider);


        img1.setEnabled(true);
        img2.setEnabled(true);
        File folder = new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata");
        folder.mkdir();

        final byte[] imgFile = data.get_dtPhoto1();
        if (imgFile != null) {
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
                        .description(data.get_txtDesc())
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
        final byte[] imgFile2 = data.get_dtPhoto2();
        if (imgFile2 != null) {
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
                    .description(data.get_txtDesc())
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

        outlet.setText(data.get_txtOutletName().toString());
        dateVisited.setText(data.get_dtDateRealisasi().toString());
        txtDesc.setText(data.get_txtDesc().toString());
        txtDescReply.setText(data.get_txtDescReply().toString());
        txtLong.setText(data.get_txtLong());
        txtLat.setText(data.get_txtLat());
        txtAcc.setText(data.get_txtAcc());
        txtDistance.setText(data.get_intDistance());

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }
}
