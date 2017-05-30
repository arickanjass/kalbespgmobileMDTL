package com.kalbenutritionals.app.kalbespgmobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bl.mKategoriBL;
import bl.mListJawabanBL;
import bl.mPertanyaanBL;
import bl.tAbsenUserBL;
import bl.tJawabanUserBL;
import library.salesforce.common.jawabanModel;
import library.salesforce.common.mKategoriData;
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
    final HashMap<String, String> HMPertanyaan2 = new HashMap<String, String>();
    final HashMap<String, String> HMJawaban = new HashMap<String, String>();
    final HashMap<String, String> HMKategori = new HashMap<String, String>();
    List<String> dataPertanyaan;
    List<String> dataPertanyaan2;
    List<String> dataKategori;
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
    private TextView textView;
    private LinearLayout linearLayout;
    private RadioGroup rgTestGet;
    clsMainActivity _clsMainActivity;
    private int value;;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_kuesioner, container, false);

        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        //disesuaikan jumlah soal
        viewPager.setOffscreenPageLimit(listDataPertanyaan.size());

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            value = bundle.getInt("key_view");
        }
        setupViewPager(viewPager);
        viewPager.setCurrentItem(value);

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
                        linearLayout = (LinearLayout) v.findViewById(Integer.parseInt(HMPertanyaan.get(dataPertanyaan.get(i))));
                        for (int x = 0; x < linearLayout.getChildCount(); x++) {
                            View nextChild = linearLayout.getChildAt(x);
                            if (nextChild instanceof TextView){
                                textView = (TextView)nextChild;
                                textView = (TextView)v.findViewById(linearLayout.getId()*200);
                            } else if (nextChild instanceof SeekBar){
                                seekbar = (SeekBar) nextChild;
                                seekbar = (SeekBar) v.findViewById(linearLayout.getId() *33);
                            }
                        }
                        listAnswer.add(linearLayout);
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
                    //ini buat custom tab
                    View tab = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
                    tab.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    tab.setBackgroundResource(R.drawable.bg_tablayout2);
                    tab.setPadding(3,0,15,0);
                    TextView tv = (TextView) tab.findViewById(R.id.custom_text);
                    Drawable img = getContext().getResources().getDrawable(
                            R.drawable.ic_error);
                    final int width = img.getIntrinsicWidth();
                    final int height = img.getIntrinsicHeight();
                    float scaleWidth = (float) 0.75f/width;
                    float scaleHeight = (float) 0.75f/height;
                    Matrix matrix = new Matrix();
                    matrix.postScale(scaleWidth, scaleHeight);;
                    Bitmap bitmap = ((BitmapDrawable) img).getBitmap();
// Scale it to 50 x 50
                    Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                    tv.setText(mFragmentTitleList.get(i));
                    tv.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);

                    //ini buat ngatur validasinya
                    View jawaban = listAnswer.get(i);
                    if (jawaban instanceof LinearLayout ){
                        tabLayout.getTabAt(i).setCustomView(null);
                        for (int x = 0; x < linearLayout.getChildCount(); x++) {
                            View nextChild = linearLayout.getChildAt(x);
                            if (nextChild instanceof TextView){
                                textView = (TextView)nextChild;
                                if (textView.getText().toString().equals("Sliding of the blue pointer")) {
                                    tabLayout.getTabAt(i).setCustomView(tab);
                                    validate = false;
                                } else {
                                    tabLayout.getTabAt(i).setCustomView(null);
                                }
                            }
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
                LinearLayout linearLayout = (LinearLayout) listAnswer.get(i);
                for (int x = 0; x < linearLayout.getChildCount(); x++) {
                    View nextChild = linearLayout.getChildAt(x);
                    if (nextChild instanceof SeekBar) {
                        seekbar = (SeekBar) nextChild;
                        tabLayout.getTabAt(i).setIcon(null);
                        dt.set_intUserAnswer(new clsMainActivity().GenerateGuid());
                        dt.set_intUserId(dataUserActive.get_txtUserId());
                        dt.set_intRoleId(dataUserActive.get_txtRoleId());
                        dt.set_intQuestionId(listDataPertanyaan.get(i).get_intQuestionId());
                        dt.set_intTypeQuestionId(listDataPertanyaan.get(i).get_intTypeQuestionId());
                        dt.set_bolHaveAnswerList(listDataPertanyaan.get(i).get_bolHaveAnswerList());
                        dt.set_intAnswerId(HMJawaban.get(seekbar.getProgress()));
                        dt.set_txtValue(String.valueOf(seekbar.getProgress()));
                        dt.set_decBobot("");
                        new tJawabanUserBL().SaveDatatJawabanUser(dt);
                    }
                }
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

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        dataPertanyaan = new ArrayList<>();
        dataPertanyaan2 = new ArrayList<>();
        dataKategori = new ArrayList<>();
        List<mPertanyaanData> listDataPertanyaan = new mPertanyaanBL().GetAllData();
        List<mKategoriData> kategoriDataList = new mKategoriBL().GetAllData();

//mapping pertanyaan
        if (listDataPertanyaan.size() > 0) {
            for (mPertanyaanData dt : listDataPertanyaan) {
                dataPertanyaan.add(dt.get_txtQuestionDesc());
                HMPertanyaan.put(dt.get_txtQuestionDesc(), dt.get_intQuestionId());
                HMPertanyaan.put(dt.get_intQuestionId(), dt.get_intTypeQuestionId());
            }
        }
        //mapping pertanyaan yang kedua
        if (listDataPertanyaan.size() > 0) {
            for (mPertanyaanData data : listDataPertanyaan) {
                dataPertanyaan2.add(data.get_intQuestionId());
                HMPertanyaan2.put(data.get_intQuestionId(), data.get_intCategoryId());
            }
        }

//mapping kategori
        if (kategoriDataList.size() > 0){
            for (mKategoriData dt : kategoriDataList){
                dataKategori.add(dt.get_intCategoryId());
                HMKategori.put(dt.get_intCategoryId(), dt.get_txtCategoryName());
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
            adapter.addFrag(new FragmentKuesionerPart(HMKategori.get(HMPertanyaan2.get(HMPertanyaan.get(dataPertanyaan.get(i)))),i+1, dataPertanyaan.get(i), Integer.parseInt(HMPertanyaan.get(HMPertanyaan.get(dataPertanyaan.get(i)))), modelJawaban), "SOAL " + (i+1));
        }
        viewPager.setAdapter(adapter);
    }

   public class ViewPagerAdapter extends FragmentPagerAdapter {


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
