package com.kalbenutritionals.app.kalbespgmobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bl.mListJawabanBL;
import bl.mPertanyaanBL;
import bl.tAbsenUserBL;
import bl.tJawabanUserBL;
import library.salesforce.common.jawabanModel;
import library.salesforce.common.mListJawabanData;
import library.salesforce.common.mPertanyaanData;
import library.salesforce.common.tJawabanUserData;
import library.salesforce.common.tUserLoginData;

/**
 * Created by Arick.Anjasmara on 11/05/2017.
 */

public class FragmentKuesioner extends Fragment {
    View v;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<jawabanModel> modelJawaban;
    final HashMap<String, String> HMPertanyaan = new HashMap<String, String>();
    final HashMap<String, String> HMJawaban = new HashMap<String, String>();
    List<String> dataPertanyaan;
    List<String> dataJawaban;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    final List<mPertanyaanData> listDataPertanyaan = new mPertanyaanBL().GetAllData();
    List<View> listAnswer = new ArrayList<View>();
    private SeekBar seekbar;
    private CheckBox cbTestGet;
    private Spinner spinner;
    private EditText etTestGet;
    private ListView listView;
    private LinearLayout linearLayout;
    private RadioGroup rgTestGet;
    clsMainActivity _clsMainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_kuesioner, container, false);

        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        //disesuaikan jumlah soal
        viewPager.setOffscreenPageLimit(100);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        _clsMainActivity = new clsMainActivity();

        final FloatingActionButton fb = (FloatingActionButton) v.findViewById(R.id.fab);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ini buat nyimpen widget di dalam list array di View
                for (int i = 0; i < listDataPertanyaan.size(); i++) {
                    if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("5")) {
                        seekbar = (SeekBar) v.findViewById(Integer.parseInt(HMPertanyaan.get(dataPertanyaan.get(i))));
                        listAnswer.add(seekbar);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("1")) {
                        spinner = (Spinner) v.findViewById(Integer.parseInt(HMPertanyaan.get(dataPertanyaan.get(i))));
                        listAnswer.add(spinner);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("3")) {
                        etTestGet = (EditText) v.findViewById(Integer.parseInt(HMPertanyaan.get(dataPertanyaan.get(i))));
                        listAnswer.add(etTestGet);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("2")) {
                        listView = (ListView) v.findViewById(Integer.parseInt(HMPertanyaan.get(dataPertanyaan.get(i))));
                        for (int x = 0; x < listView.getChildCount(); x++) {
                            View nextChild = listView.getChildAt(x);
                            if (nextChild instanceof CheckBox) {
                                cbTestGet = (CheckBox) nextChild;
                            }
                        }
                        listAnswer.add(listView);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("4")) {
                        rgTestGet = (RadioGroup) v.findViewById(Integer.parseInt(HMPertanyaan.get(dataPertanyaan.get(i))));
                        listAnswer.add(rgTestGet);
                    }
                }

                int[] tabIcons = {
                        R.drawable.ic_error,
                };
                //ini buat validasi kalo jawaban masih kosong
                boolean validate = true;
                for (int i = 0; i < listDataPertanyaan.size(); i++) {
                    View tab = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
                    TextView tv = (TextView) tab.findViewById(R.id.custom_text);
                    tv.setText(mFragmentTitleList.get(i));
                    tv.setCompoundDrawablesWithIntrinsicBounds(tabIcons[0], 0, 0, 0);
                    View jawaban = listAnswer.get(i);

                    if (jawaban instanceof SeekBar ){
                        tabLayout.getTabAt(i).setCustomView(null);
                        if ((seekbar = (SeekBar) jawaban).getProgress() == 0) {
                            tabLayout.getTabAt(i).setCustomView(tab);
                            validate = false;
                        } else {
                            tabLayout.getTabAt(i).setCustomView(null);
                        }
                    }
                    if (jawaban instanceof Spinner){
                        tabLayout.getTabAt(i).setCustomView(null);
                        if ((spinner= (Spinner) jawaban).getSelectedItem().toString().equals("Select One")) {
                            tabLayout.getTabAt(i).setCustomView(tab);
                            validate = false;
                        } else {
                            tabLayout.getTabAt(i).setCustomView(null);
                        }
                    }
                    if (jawaban instanceof EditText){
                        tabLayout.getTabAt(i).setCustomView(null);
                        if ((etTestGet = (EditText)jawaban).getText().toString().equals("")) {
                            tabLayout.getTabAt(i).setCustomView(tab);
                            validate = false;
                        } else {
                            tabLayout.getTabAt(i).setCustomView(null);
                        }
                    }
                    if (jawaban instanceof RadioGroup ){
                        tabLayout.getTabAt(i).setCustomView(null);
                        if ((rgTestGet = (RadioGroup)jawaban).getCheckedRadioButtonId() == -1) {
                            tabLayout.getTabAt(i).setCustomView(tab);
                            validate = false;
                        } else {
                            tabLayout.getTabAt(i).setCustomView(null);
                        }
                    }

                    if (jawaban instanceof ListView) {
                        tabLayout.getTabAt(i).setCustomView(null);
                        ListView listView = (ListView) listAnswer.get(i);
                        int count = 0;
                        for (int x = 0; x < listView.getChildCount(); x++) {
                            View nextChild = listView.getChildAt(x);
                            if (nextChild instanceof CheckBox) {
                                CheckBox checkBox = (CheckBox) nextChild;
                                if (checkBox.isChecked()) {
                                    count++;
                                }
                            }
                        }
                        if (count == 0) {
                            tabLayout.getTabAt(i).setCustomView(tab);
                            validate = false;
                        } else {
                            tabLayout.getTabAt(i).setCustomView(null);
                        }
                    }
                }
                if (validate) { //kalau jawaban sudah di isi jalankan ini
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Confirm");
                    alertDialog.setMessage("Are you sure?");
                    alertDialog.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SaveQuiz();
                            _clsMainActivity.showCustomToast(getActivity(), "Saved", true);
                            Intent myIntent = new Intent(getContext(), MainMenu.class);
                            startActivity(myIntent);
                        }
                    });
                    alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });

                    alertDialog.show();
                }else{
                    _clsMainActivity.showCustomToast(getActivity(), "Please fill empty Field...", false);
                }


            }
        });

        return v;
    }

    private void SaveQuiz() {
        for (int i = 0; i < listDataPertanyaan.size(); i++) {
            tJawabanUserData dt = new tJawabanUserData();
            tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
            List<mListJawabanData> mListJawabanDatas = new mListJawabanBL().GetDataByTypeQuestion(HMPertanyaan.get(HMPertanyaan.get(dataPertanyaan.get(i))), HMPertanyaan.get(dataPertanyaan.get(i)));
            dataJawaban = new ArrayList<>();
            if (mListJawabanDatas.size() > 0) {
                for (mListJawabanData jd : mListJawabanDatas) {
                    dataJawaban.add(jd.get_txtKey());
                    HMJawaban.put(jd.get_txtKey(), jd.get_intListAnswerId());
                    HMJawaban.put(jd.get_intListAnswerId(), jd.get_txtValue());
                }
            }
            View answer = listAnswer.get(i);
            if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("5")) {
                SeekBar seekBar = (SeekBar) listAnswer.get(i);
                tabLayout.getTabAt(i).setIcon(null);
                dt.set_intUserAnswer(new clsMainActivity().GenerateGuid());
                dt.set_intUserId(dataUserActive.get_txtUserId());
                dt.set_intRoleId(dataUserActive.get_txtRoleId());
                dt.set_intQuestionId(listDataPertanyaan.get(i).get_intQuestionId());
                dt.set_intTypeQuestionId(listDataPertanyaan.get(i).get_intTypeQuestionId());
                dt.set_bolHaveAnswerList(listDataPertanyaan.get(i).get_bolHaveAnswerList());
                dt.set_intAnswerId(HMJawaban.get(seekBar.getProgress()));
                dt.set_txtValue(String.valueOf(seekBar.getProgress()));
                dt.set_decBobot("");
                new tJawabanUserBL().SaveDatatJawabanUser(dt);
            } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("1")) {
                Spinner spinner = (Spinner) listAnswer.get(i);
                tabLayout.getTabAt(i).setIcon(null);
                dt.set_intUserAnswer(new clsMainActivity().GenerateGuid());
                dt.set_intUserId(dataUserActive.get_txtUserId());
                dt.set_intRoleId(dataUserActive.get_txtRoleId());
                dt.set_intQuestionId(listDataPertanyaan.get(i).get_intQuestionId());
                dt.set_intTypeQuestionId(listDataPertanyaan.get(i).get_intTypeQuestionId());
                dt.set_bolHaveAnswerList(listDataPertanyaan.get(i).get_bolHaveAnswerList());
                dt.set_intAnswerId(HMJawaban.get(spinner.getSelectedItem().toString()));
                dt.set_txtValue(HMJawaban.get(HMJawaban.get(spinner.getSelectedItem().toString())));
                dt.set_decBobot("");
                new tJawabanUserBL().SaveDatatJawabanUser(dt);
            } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("3")) {
                EditText editText = (EditText) listAnswer.get(i);
                tabLayout.getTabAt(i).setIcon(null);
                dt.set_intUserAnswer(new clsMainActivity().GenerateGuid());
                dt.set_intUserId(dataUserActive.get_txtUserId());
                dt.set_intRoleId(dataUserActive.get_txtRoleId());
                dt.set_intQuestionId(listDataPertanyaan.get(i).get_intQuestionId());
                dt.set_intTypeQuestionId(listDataPertanyaan.get(i).get_intTypeQuestionId());
                dt.set_bolHaveAnswerList(listDataPertanyaan.get(i).get_bolHaveAnswerList());
                dt.set_intAnswerId(HMJawaban.get(editText.getText().toString()));
                dt.set_txtValue(editText.getText().toString());
                dt.set_decBobot("");
                new tJawabanUserBL().SaveDatatJawabanUser(dt);
            } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("2")) {
                ListView listView = (ListView) listAnswer.get(i);
                for (int x = 0; x < listView.getChildCount(); x++) {
                    View nextChild = listView.getChildAt(x);
                    if (nextChild instanceof CheckBox) {
                        cbTestGet = (CheckBox) nextChild;
                        if (cbTestGet.isChecked()) {
                            tabLayout.getTabAt(i).setIcon(null);
                            dt.set_intUserAnswer(new clsMainActivity().GenerateGuid());
                            dt.set_intUserId(dataUserActive.get_txtUserId());
                            dt.set_intRoleId(dataUserActive.get_txtRoleId());
                            dt.set_intQuestionId(listDataPertanyaan.get(i).get_intQuestionId());
                            dt.set_intTypeQuestionId(listDataPertanyaan.get(i).get_intTypeQuestionId());
                            dt.set_bolHaveAnswerList(listDataPertanyaan.get(i).get_bolHaveAnswerList());
                            dt.set_intAnswerId(HMJawaban.get(cbTestGet.getText().toString()));
                            dt.set_txtValue(HMJawaban.get(HMJawaban.get(cbTestGet.getText().toString())));
                            dt.set_decBobot("");
                            new tJawabanUserBL().SaveDatatJawabanUser(dt);
                        }
                    }
                }
            } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("4")) {
                RadioGroup radioGroup = (RadioGroup) listAnswer.get(i);

                if (!radioGroup.onCheckIsTextEditor()) {
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    if (selectedId > -1) {
                        tabLayout.getTabAt(i).setIcon(null);
                        RadioButton radioButton = (RadioButton) v.findViewById(selectedId);
                        if (radioButton.isChecked()) {
                            dt.set_intUserAnswer(new clsMainActivity().GenerateGuid());
                            dt.set_intUserId(dataUserActive.get_txtUserId());
                            dt.set_intRoleId(dataUserActive.get_txtRoleId());
                            dt.set_intQuestionId(listDataPertanyaan.get(i).get_intQuestionId());
                            dt.set_intTypeQuestionId(listDataPertanyaan.get(i).get_intTypeQuestionId());
                            dt.set_bolHaveAnswerList(listDataPertanyaan.get(i).get_bolHaveAnswerList());
                            dt.set_intAnswerId(HMJawaban.get(radioButton.getText().toString()));
                            dt.set_txtValue(HMJawaban.get(HMJawaban.get(radioButton.getText().toString())));
                            dt.set_decBobot("");
                            new tJawabanUserBL().SaveDatatJawabanUser(dt);
                        }
                    }
                }
            }
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        dataPertanyaan = new ArrayList<>();
        List<mPertanyaanData> listDataPertanyaan = new mPertanyaanBL().GetAllData();

//mapping pertanyaan
        if (listDataPertanyaan.size() > 0) {
            for (mPertanyaanData dt : listDataPertanyaan) {
                dataPertanyaan.add(dt.get_txtQuestionDesc());
                HMPertanyaan.put(dt.get_txtQuestionDesc(), dt.get_intQuestionId());
                HMPertanyaan.put(dt.get_intQuestionId(), dt.get_intTypeQuestionId());
            }
        }

//isi fragment dan get jawaban berdasarkan type pertanyaan

        for (int i = 0; i < listDataPertanyaan.size(); i++) {
            modelJawaban = new ArrayList<jawabanModel>();
            List<mListJawabanData> mListJawabanDatas = new mListJawabanBL().GetDataByTypeQuestion(HMPertanyaan.get(HMPertanyaan.get(dataPertanyaan.get(i))), HMPertanyaan.get(dataPertanyaan.get(i)));
            if (mListJawabanDatas.size() > 0 && listDataPertanyaan.get(i).get_intTypeQuestionId() == HMPertanyaan.get(HMPertanyaan.get(dataPertanyaan.get(i)))) {
                for (int j = 0; j < mListJawabanDatas.size(); j++) {
                    jawabanModel dt = new jawabanModel();
                    dt.setKey(mListJawabanDatas.get(j).get_txtKey());
                    dt.setValue(mListJawabanDatas.get(j).get_txtValue());
                    modelJawaban.add(dt);
                }
            }
            adapter.addFrag(new FragmentKuesionerPart(Integer.parseInt(HMPertanyaan.get(dataPertanyaan.get(i))), dataPertanyaan.get(i), Integer.parseInt(HMPertanyaan.get(HMPertanyaan.get(dataPertanyaan.get(i)))), modelJawaban), "Soal " + HMPertanyaan.get(dataPertanyaan.get(i)));
        }
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
