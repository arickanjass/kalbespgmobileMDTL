package com.kalbenutritionals.app.kalbespgmobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import library.salesforce.common.jawabanModel;

/**
 * Created by Arick.Anjasmara on 11/05/2017.
 */

public class FragmentKuesioner extends Fragment{
    View v;
    private TabLayout tabLayout;
    private ViewPager viewPager;

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
                EditText etTestGet = (EditText) v.findViewById(1);
                RadioGroup rgTestGet = (RadioGroup) v.findViewById(2);

                int indexRadioGroup = rgTestGet.indexOfChild(v.findViewById(rgTestGet.getCheckedRadioButtonId()));

                Spinner spinner = (Spinner) v.findViewById(3);

                String txtJawabanCheckbox = "";

                CheckBox cbTestGet = (CheckBox) v.findViewById(400);

                if(cbTestGet.isChecked()){
                    txtJawabanCheckbox += " Index 0 checked";
                }

                cbTestGet = (CheckBox) v.findViewById(401);

                if(cbTestGet.isChecked()){
                    txtJawabanCheckbox += " Index 1 checked";
                }

                cbTestGet = (CheckBox) v.findViewById(402);

                if(cbTestGet.isChecked()){
                    txtJawabanCheckbox += " Index 2 checked";
                }

                SeekBar seekbar = (SeekBar) v.findViewById(5);

                int[] tabIcons = {
                        R.drawable.ic_error
                };


                if(etTestGet.getText().equals("")){
                    tabLayout.getTabAt(0).setIcon(tabIcons[0]);
                }

                if(indexRadioGroup == -1){
                    tabLayout.getTabAt(1).setIcon(tabIcons[0]);
                }

                if(txtJawabanCheckbox.equals("")){
                    tabLayout.getTabAt(3).setIcon(tabIcons[0]);
                }

                Toast.makeText(getContext(), "1. Edit Text = " + etTestGet.getText().toString() +
                        " | 2. Radio Index = " + indexRadioGroup +
                        " | 3. Spinner Index = " + spinner.getSelectedItemPosition() +
                        " | 4. Checkbox status = " + txtJawabanCheckbox +
                        " | 5. Seekbar value = " + seekbar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        List<jawabanModel> listJawaban = new ArrayList<>();

        jawabanModel _jawabanModel = new jawabanModel();
        _jawabanModel.setKey("Malas");
        _jawabanModel.setValue("0");

        listJawaban.add(_jawabanModel);

        _jawabanModel = new jawabanModel();
        _jawabanModel.setKey("Rajin jika disuruh");
        _jawabanModel.setValue("50");

        listJawaban.add(_jawabanModel);

        _jawabanModel = new jawabanModel();
        _jawabanModel.setKey("Rajin namun sering telat absen");
        _jawabanModel.setValue("75");

        listJawaban.add(_jawabanModel);

        _jawabanModel = new jawabanModel();
        _jawabanModel.setKey("Rajin pangkal pandai");
        _jawabanModel.setValue("100");

        listJawaban.add(_jawabanModel);


        //type 1 == edittext
        adapter.addFrag(new FragmentKuesionerPart(1, "Berikan pesan dan kesan selama anda bekerja di SHP!", 1, null), "Soal 1");
        //type 2 == radiobutton
        adapter.addFrag(new FragmentKuesionerPart(2, "Pilih sifat yang paling melekat kepada anda", 2, listJawaban), "Soal 2");
        //type 3 == spinner
        adapter.addFrag(new FragmentKuesionerPart(3, "Pilih sifat yang paling melekat kepada anda", 3, listJawaban), "Soal 3");
        //type 4 == checkbox
        adapter.addFrag(new FragmentKuesionerPart(4, "Pilih sifat yang paling melekat kepada anda minimal 1", 4, listJawaban), "Soal 4");
        //type 5 == seekbar
        adapter.addFrag(new FragmentKuesionerPart(5, "Bagaimana penilaian anda terhadap team leader anda? (skala 0-100)", 5, null), "Soal 5");
        adapter.addFrag(new FragmentKuesionerPart(6, "Bagaimana penilaian anda terhadap team leader anda? (skala 0-100)", 5, null), "Soal 6");
        adapter.addFrag(new FragmentKuesionerPart(7, "Bagaimana penilaian anda terhadap team leader anda? (skala 0-100)", 5, null), "Soal 7");
        adapter.addFrag(new FragmentKuesionerPart(8, "Bagaimana penilaian anda terhadap team leader anda? (skala 0-100)", 5, null), "Soal 8");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

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
