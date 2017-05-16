package com.kalbenutritionals.app.kalbespgmobile;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.badgeall.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bl.mListJawabanBL;
import bl.mPertanyaanBL;
import library.salesforce.common.jawabanModel;
import library.salesforce.common.mListJawabanData;
import library.salesforce.common.mPertanyaanData;

/**
 * Created by Arick.Anjasmara on 11/05/2017.
 */

public class FragmentKuesioner extends Fragment{
    View v;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<jawabanModel> modelJawaban;
    final HashMap<String, String> HMPertanyaan = new HashMap<String, String>();
    List<String> dataPertanyaan;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private SeekBar seekbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_kuesioner, container, false);

        viewPager = (ViewPager) v.findViewById(R.id.viewpager);

        //disesuaikan jumlah soal
        viewPager.setOffscreenPageLimit(10);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fb = (FloatingActionButton) v.findViewById(R.id.fab);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<mPertanyaanData> listDataPertanyaan = new mPertanyaanBL().GetAllData();
//                EditText etTestGet = (EditText) v.findViewById(1);
//                RadioGroup rgTestGet = (RadioGroup) v.findViewById(2);
//
//                int indexRadioGroup = rgTestGet.indexOfChild(v.findViewById(rgTestGet.getCheckedRadioButtonId()));
//
//                Spinner spinner = (Spinner) v.findViewById(3);
////
//                String txtJawabanCheckbox = "";
//
//                CheckBox cbTestGet = (CheckBox) v.findViewById(400);
//
//                if(cbTestGet.isChecked()){
//                    txtJawabanCheckbox += " Index 0 checked";
//                }
//
//                cbTestGet = (CheckBox) v.findViewById(401);
//
//                if(cbTestGet.isChecked()){
//                    txtJawabanCheckbox += " Index 1 checked";
//                }
//
//                cbTestGet = (CheckBox) v.findViewById(402);
//
//                if(cbTestGet.isChecked()){
//                    txtJawabanCheckbox += " Index 2 checked";
//                }
                List<SeekBar> seekBarList = new ArrayList<SeekBar>();
                for (int i = 0; i < listDataPertanyaan.size()-1; i++){
                   seekbar = (SeekBar) v.findViewById(Integer.parseInt(HMPertanyaan.get(dataPertanyaan.get(i))));
                    seekBarList.add(seekbar);
                }

////
////                int[] tabIcons = {
////                        R.drawable.ic_error,
////                        R.drawable.ic_person,
////                        R.drawable.b_camera
////                };
////
//////                if(etTestGet.getText().toString().equals("")){
//////                    tabLayout.getTabAt(0).setIcon( tabIcons[0]);
//////                }
//////
////
//////                if(indexRadioGroup == -1){
//////                    tabLayout.getTabAt(1).setIcon( tabIcons[0]);
////////                    tabLayout.getTabAt(1).setCustomView(tabOne);
////////                    setupTabIcons();
//////                }else {
//////                    tabLayout.getTabAt(1).setIcon(null);
////////                    tabLayout.getTabAt(1).setCustomView(null);
//////                }
////
////                if(txtJawabanCheckbox.equals("")){
////                    tabLayout.getTabAt(3).setIcon(tabIcons[0]);
////                }
                for (int i = 0; i < listDataPertanyaan.size()-1; i++){
                    Toast.makeText(getContext(), "1. Edit Text = " + seekBarList.get(0).getProgress() +
                            " | 2. Radio Index = " + seekBarList.get(1).getProgress() +
                            " | 3. Spinner Index = " + seekBarList.get(2).getProgress() , Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;
    }
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tabs, null);
        tabOne.setText(mFragmentTitleList.get(1));
//        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, 0 ,R.drawable.ic_error, 0);
        tabOne.setTextSize(16);
        tabOne.setGravity(Gravity.CENTER_VERTICAL);

        tabLayout.getTabAt(1).setCustomView(tabOne);

    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        List<mListJawabanData> jawabanDataList = new mListJawabanBL().GetAllData();

        modelJawaban = new ArrayList<jawabanModel>();

        if (jawabanDataList.size() > 0) {
            for (int i = 0; i < jawabanDataList.size(); i++) {
                jawabanModel dt = new jawabanModel();
                dt.setKey(jawabanDataList.get(i).get_txtKey());
                dt.setValue(jawabanDataList.get(i).get_txtValue());
                modelJawaban.add(dt);
            }
        }
        dataPertanyaan = new ArrayList<>();
        List<mPertanyaanData> listDataPertanyaan = new mPertanyaanBL().GetAllData();

        if (listDataPertanyaan.size() > 0) {
            for (mPertanyaanData dt : listDataPertanyaan) {
                dataPertanyaan.add(dt.get_txtQuestionDesc());
                HMPertanyaan.put(dt.get_txtQuestionDesc(), dt.get_intQuestionId());
//                HMPertanyaan.put(dt.get_intQuestionId(), dt.get_intQuestionId());
                HMPertanyaan.put(dt.get_intQuestionId(), dt.get_intTypeQuestionId());
            }
        }

        for (int i = 0; i < listDataPertanyaan.size()-1; i++){
            adapter.addFrag(new FragmentKuesionerPart(Integer.parseInt(HMPertanyaan.get(dataPertanyaan.get(i))), dataPertanyaan.get(i), Integer.parseInt(HMPertanyaan.get(HMPertanyaan.get(dataPertanyaan.get(i)))), null), "Soal " + HMPertanyaan.get(dataPertanyaan.get(i)));
        }
//        //type 1 == edittext0
//        adapter.addFrag(new FragmentKuesionerPart(Integer.parseInt(HMPertanyaan.get(dataPertanyaan.get(0))), dataPertanyaan.get(0), Integer.parseInt(HMPertanyaan.get(HMPertanyaan.get(dataPertanyaan.get(0)))), null), "Soal " + HMPertanyaan.get(dataPertanyaan.get(0)));
//        //type 2 == radiobutton
//        adapter.addFrag(new FragmentKuesionerPart(Integer.parseInt(HMPertanyaan.get(dataPertanyaan.get(1))), dataPertanyaan.get(1), Integer.parseInt(HMPertanyaan.get(HMPertanyaan.get(dataPertanyaan.get(1)))), null), "Soal " + HMPertanyaan.get(dataPertanyaan.get(1)));
//        //type 3 == spinner
//        adapter.addFrag(new FragmentKuesionerPart(Integer.parseInt(HMPertanyaan.get(dataPertanyaan.get(2))), dataPertanyaan.get(2), Integer.parseInt(HMPertanyaan.get(HMPertanyaan.get(dataPertanyaan.get(2)))), null), "Soal " + HMPertanyaan.get(dataPertanyaan.get(2)));
        //type 4 == checkbox
        adapter.addFrag(new FragmentKuesionerPart(Integer.parseInt(HMPertanyaan.get(dataPertanyaan.get(3))), dataPertanyaan.get(3), Integer.parseInt(HMPertanyaan.get(HMPertanyaan.get(dataPertanyaan.get(3)))), modelJawaban), "Soal " + HMPertanyaan.get(dataPertanyaan.get(3)));
        //type 5 == seekbar
        adapter.addFrag(new FragmentKuesionerPart(5, "Bagaimana penilaian anda terhadap team leader anda? (skala 0-100)", 5, null), "Soal 5");
        adapter.addFrag(new FragmentKuesionerPart(6, "Bagaimana penilaian anda terhadap team leader anda? (skala 0-100)", 5, null), "Soal 6");
        adapter.addFrag(new FragmentKuesionerPart(7, "Bagaimana penilaian anda terhadap team leader anda? (skala 0-100)", 5, null), "Soal 7");
        adapter.addFrag(new FragmentKuesionerPart(8, "Bagaimana penilaian anda terhadap team leader anda? (skala 0-100)", 5, null), "Soal 8");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {


        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
