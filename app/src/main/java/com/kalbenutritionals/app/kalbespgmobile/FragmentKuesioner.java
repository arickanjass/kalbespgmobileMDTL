package com.kalbenutritionals.app.kalbespgmobile;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import bl.mEmployeeSalesProductBL;
import bl.mKategoriBL;
import bl.mListJawabanBL;
import bl.mPertanyaanBL;
import bl.tAbsenUserBL;
import bl.tGroupQuestionMappingBL;
import bl.tHirarkiBISBL;
import bl.tJawabanUserBL;
import bl.tJawabanUserHeaderBL;
import library.spgmobile.common.jawabanModel;
import library.spgmobile.common.mEmployeeSalesProductData;
import library.spgmobile.common.mKategoriData;
import library.spgmobile.common.mListJawabanData;
import library.spgmobile.common.mPertanyaanData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tGroupQuestionMappingData;
import library.spgmobile.common.tHirarkiBIS;
import library.spgmobile.common.tJawabanUserData;
import library.spgmobile.common.tJawabanUserHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.tJawabanUserHeaderDA;

/**
 * Created by Arick.Anjasmara on 11/05/2017.
 */

public class FragmentKuesioner extends Fragment {
    View v;
    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private Toolbar toolbar;
    private ArrayList<jawabanModel> modelJawaban;
    final HashMap<String, String> HMPertanyaan = new HashMap<String, String>();
    final HashMap<String, String> HMPertanyaan2 = new HashMap<String, String>();
    final HashMap<String, String> HMPertanyaan3 = new HashMap<String, String>();
    final HashMap<String, String> HMJawaban = new HashMap<String, String>();
    final HashMap<String, String> HMKategori = new HashMap<String, String>();
    List<String> dataPertanyaan;
    List<String> dataPertanyaan2;
    List<String> dataPertanyaan3;
    List<String> dataKategori;
    List<String> dataJawaban;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
//    final List<mPertanyaanData> listDataPertanyaan = new mPertanyaanBL().GetAllData();
    List<View> listAnswer = new ArrayList<View>();
    private SeekBar seekbar;
    private CheckBox cbTestGet;
    private Spinner spinner;
    private EditText etTestGet;
    private ListView listView;
    private LinearLayout linearLayout, layoutDate, layoutFileQuiz, layoutImgQuiz;
    private ImageView imageView;
    private RadioGroup rgTestGet;
    private EditText dateView;
    clsMainActivity _clsMainActivity;
    private int value, intGroupId;
    private TextView textView, tvQuiz, tvFileQuiz, tvFilePathQuiz, tvImgQuiz;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_kuesioner, container, false);

        viewPager = (CustomViewPager) v.findViewById(R.id.viewpager);
        final FloatingActionButton fbNext = (FloatingActionButton) v.findViewById(R.id.fab);
        final FloatingActionButton fbPrev = (FloatingActionButton) v.findViewById(R.id.fabkiri);
        final FloatingActionButton fbSubmit = (FloatingActionButton) v.findViewById(R.id.fabSubmit);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        tvQuiz = (TextView) v.findViewById(R.id.appBarQuiz);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            intGroupId = bundle.getInt("Key_GroupId");
        }
        final  List<mPertanyaanData> listDataPertanyaan = new mPertanyaanBL().GetDataByGroupQuestion(intGroupId);

        final List<tGroupQuestionMappingData> mappingDataList = new tGroupQuestionMappingBL().GetDataById(intGroupId);
        for (tGroupQuestionMappingData dt : mappingDataList){
            toolbar.setTitle(dt.get_txtGroupQuestion());
        }

        //disesuaikan jumlah soal
        viewPager.setOffscreenPageLimit(listDataPertanyaan.size());
        viewPager.setPagingEnabled(false);
        setupViewPager(viewPager);
        final FrameLayout frameLayout = (FrameLayout) v.findViewById(R.id.fbSubmit);

        final int iterator = viewPager.getCurrentItem();
        tvQuiz.setText("Soal " + HMPertanyaan3.get(HMPertanyaan3.get(dataPertanyaan3.get(iterator))));
        toolbar.setSubtitle(HMKategori.get(HMPertanyaan2.get(HMPertanyaan.get(dataPertanyaan.get(iterator)))));
        _clsMainActivity = new clsMainActivity();
        if ( iterator == 0){
            fbPrev.setVisibility(View.INVISIBLE);}

        if(iterator >= listDataPertanyaan.size()-1){
            fbNext.setVisibility(View.INVISIBLE);
            frameLayout.setVisibility(View.VISIBLE);
        } else if (iterator > 0 && iterator < listDataPertanyaan.size() -1){
            fbPrev.setVisibility(View.VISIBLE);
            fbNext.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.INVISIBLE);
        } else if (iterator == 0){
            fbPrev.setVisibility(View.INVISIBLE);
            frameLayout.setVisibility(View.INVISIBLE);
        }
        fbNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ini buat nyimpen widget di dalam list array di View
                for (int i = 0; i < listDataPertanyaan.size(); i++) {
                    if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("5")) {
                        linearLayout = (LinearLayout) v.findViewById(i + 1);
                        for (int x = 0; x < linearLayout.getChildCount(); x++) {
                            View nextChild = linearLayout.getChildAt(x);
                            if (nextChild instanceof TextView) {
                                textView = (TextView) nextChild;
                                textView = (TextView) v.findViewById(linearLayout.getId() * 200);
                            } else if (nextChild instanceof SeekBar) {
                                seekbar = (SeekBar) nextChild;
                                seekbar = (SeekBar) v.findViewById(linearLayout.getId() * 33);
                            }
                        }
                        listAnswer.add(linearLayout);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("1")) {
                        spinner = (Spinner) v.findViewById(i + 1);
                        listAnswer.add(spinner);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("3")) {
                        etTestGet = (EditText) v.findViewById(i + 1);
                        listAnswer.add(etTestGet);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("2")) {
                        listView = (ListView) v.findViewById(i + 1);
                        for (int x = 0; x < listView.getChildCount(); x++) {
                            View nextChild = listView.getChildAt(x);
                            if (nextChild instanceof CheckBox) {
                                cbTestGet = (CheckBox) nextChild;
                            }
                        }
                        listAnswer.add(listView);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("6")) {
                        rgTestGet = (RadioGroup) v.findViewById(i + 1);
                        listAnswer.add(rgTestGet);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("4")) {
                        layoutDate = (LinearLayout) v.findViewById(i+1);
                        for (int x = 0; x < layoutDate.getChildCount(); x++) {
                            View nextChild = layoutDate.getChildAt(x);
                            if (nextChild instanceof EditText) {
                                EditText editText = (EditText) nextChild;
                                dateView = (EditText) v.findViewById(editText.getId());
                            }
                        }
                        listAnswer.add(layoutDate);
                    }else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("7")){
                        layoutImgQuiz = (LinearLayout) v.findViewById(i+1);
                        for (int x = 0; x < layoutImgQuiz.getChildCount(); x++) {
                            View nextChild = layoutImgQuiz.getChildAt(x);
                            if (nextChild instanceof ImageView) {
                                ImageView img = (ImageView) nextChild;
                                imageView = (ImageView) v.findViewById(img.getId());
                            }else if (nextChild instanceof TextView) {
                                TextView textView = (TextView) nextChild;
                                tvImgQuiz = (TextView) v.findViewById(textView.getId());
                            }
                        }
                        listAnswer.add(layoutImgQuiz);
                    }else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("8")) {
                        layoutFileQuiz = (LinearLayout) v.findViewById(i+1);
                        for (int x = 0; x < layoutFileQuiz.getChildCount(); x++) {
                            View nextChild = layoutFileQuiz.getChildAt(x);
                            if (nextChild instanceof TextView) {
                                TextView textView = (TextView) nextChild;
                                if (textView.getId() == layoutFileQuiz.getId() * 37){
                                    tvFileQuiz = (TextView) v.findViewById(textView.getId());
                                }else if (textView.getId() == layoutFileQuiz.getId() * 63){
                                    tvFilePathQuiz = (TextView) v.findViewById(textView.getId());
                                }
                            }
                        }
                        listAnswer.add(layoutFileQuiz);
                    }
                }
                int iterator = viewPager.getCurrentItem() + 1;
                if (messValidasi(viewPager.getCurrentItem()).length()==0){
                    viewPager.setCurrentItem(iterator);
                    tvQuiz.setText("Soal " + HMPertanyaan3.get(HMPertanyaan3.get(dataPertanyaan3.get(iterator))));
                    toolbar.setSubtitle(HMKategori.get(HMPertanyaan2.get(HMPertanyaan.get(dataPertanyaan.get(iterator)))));
                    if(iterator >= listDataPertanyaan.size()-1){
                        fbNext.setVisibility(View.INVISIBLE);
                        fbPrev.setVisibility(View.VISIBLE);
                        frameLayout.setVisibility(View.VISIBLE);
                    } else if (iterator > 0 && iterator < listDataPertanyaan.size() -1){
                        fbPrev.setVisibility(View.VISIBLE);
                        fbNext.setVisibility(View.VISIBLE);
                        frameLayout.setVisibility(View.INVISIBLE);
                    } else if (iterator == 0){
                        fbPrev.setVisibility(View.INVISIBLE);
                        fbNext.setVisibility(View.VISIBLE);
                        frameLayout.setVisibility(View.INVISIBLE);
                    }
                } else {
                    _clsMainActivity.showCustomToast(getActivity(), messValidasi(viewPager.getCurrentItem()), false);
                }
            }
        });
        fbPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ini buat nyimpen widget di dalam list array di View
                for (int i = 0; i < listDataPertanyaan.size(); i++) {
                    if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("5")) {
                        linearLayout = (LinearLayout) v.findViewById(i + 1);
                        for (int x = 0; x < linearLayout.getChildCount(); x++) {
                            View nextChild = linearLayout.getChildAt(x);
                            if (nextChild instanceof TextView) {
                                textView = (TextView) nextChild;
                                textView = (TextView) v.findViewById(linearLayout.getId() * 200);
                            } else if (nextChild instanceof SeekBar) {
                                seekbar = (SeekBar) nextChild;
                                seekbar = (SeekBar) v.findViewById(linearLayout.getId() * 33);
                            }
                        }
                        listAnswer.add(linearLayout);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("1")) {
                        spinner = (Spinner) v.findViewById(i + 1);
                        listAnswer.add(spinner);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("3")) {
                        etTestGet = (EditText) v.findViewById(i + 1);
                        listAnswer.add(etTestGet);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("2")) {
                        listView = (ListView) v.findViewById(i + 1);
                        for (int x = 0; x < listView.getChildCount(); x++) {
                            View nextChild = listView.getChildAt(x);
                            if (nextChild instanceof CheckBox) {
                                cbTestGet = (CheckBox) nextChild;
                            }
                        }
                        listAnswer.add(listView);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("6")) {
                        rgTestGet = (RadioGroup) v.findViewById(i + 1);
                        listAnswer.add(rgTestGet);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("4")) {
                        layoutDate = (LinearLayout) v.findViewById(i+1);
                        for (int x = 0; x < layoutDate.getChildCount(); x++) {
                            View nextChild = layoutDate.getChildAt(x);
                            if (nextChild instanceof EditText) {
                                EditText editText = (EditText) nextChild;
                                dateView = (EditText) v.findViewById(editText.getId());
                            }
                        }
                        listAnswer.add(layoutDate);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("7")){
                        layoutImgQuiz = (LinearLayout) v.findViewById(i+1);
                        for (int x = 0; x < layoutImgQuiz.getChildCount(); x++) {
                            View nextChild = layoutImgQuiz.getChildAt(x);
                            if (nextChild instanceof ImageView) {
                                ImageView img = (ImageView) nextChild;
                                imageView = (ImageView) v.findViewById(img.getId());
                            }else if (nextChild instanceof TextView) {
                                TextView textView = (TextView) nextChild;
                                tvImgQuiz = (TextView) v.findViewById(textView.getId());
                            }
                        }
                        listAnswer.add(layoutImgQuiz);
                    }else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("8")) {
                        layoutFileQuiz = (LinearLayout) v.findViewById(i+1);
                        for (int x = 0; x < layoutFileQuiz.getChildCount(); x++) {
                            View nextChild = layoutFileQuiz.getChildAt(x);
                            if (nextChild instanceof TextView) {
                                TextView textView = (TextView) nextChild;
                                if (textView.getId() == layoutFileQuiz.getId() * 37){
                                    tvFileQuiz = (TextView) v.findViewById(textView.getId());
                                }else if (textView.getId() == layoutFileQuiz.getId() * 63){
                                    tvFilePathQuiz = (TextView) v.findViewById(textView.getId());
                                }
                            }
                        }
                        listAnswer.add(layoutFileQuiz);
                    }
                }
                int iterator = viewPager.getCurrentItem() - 1;
                if (messValidasi(viewPager.getCurrentItem()).length() == 0){
                    viewPager.setCurrentItem(iterator);
                        tvQuiz.setText("Soal " + HMPertanyaan3.get(HMPertanyaan3.get(dataPertanyaan3.get(iterator))));
                        toolbar.setSubtitle(HMKategori.get(HMPertanyaan2.get(HMPertanyaan.get(dataPertanyaan.get(iterator)))));
                    if(iterator >= listDataPertanyaan.size()-1){
                        fbNext.setVisibility(View.INVISIBLE);
                        fbPrev.setVisibility(View.VISIBLE);
                        frameLayout.setVisibility(View.VISIBLE);
                    } else if (iterator > 0 && iterator < listDataPertanyaan.size() -1){
                        fbPrev.setVisibility(View.VISIBLE);
                        fbNext.setVisibility(View.VISIBLE);
                        frameLayout.setVisibility(View.INVISIBLE);
                    } else if (iterator == 0){
                        fbPrev.setVisibility(View.INVISIBLE);
                        fbNext.setVisibility(View.VISIBLE);
                        frameLayout.setVisibility(View.INVISIBLE);
                    }
                } else {
                    _clsMainActivity.showCustomToast(getActivity(),  messValidasi(viewPager.getCurrentItem()), false);
                }
            }
        });

        fbSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ini buat nyimpen widget di dalam list array di View
                for (int i = 0; i < listDataPertanyaan.size(); i++) {
                    if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("5")) {
                        linearLayout = (LinearLayout) v.findViewById(i + 1);
                        for (int x = 0; x < linearLayout.getChildCount(); x++) {
                            View nextChild = linearLayout.getChildAt(x);
                            if (nextChild instanceof TextView) {
                                textView = (TextView) nextChild;
                                textView = (TextView) v.findViewById(linearLayout.getId() * 200);
                            } else if (nextChild instanceof SeekBar) {
                                seekbar = (SeekBar) nextChild;
                                seekbar = (SeekBar) v.findViewById(linearLayout.getId() * 33);
                            }
                        }
                        listAnswer.add(linearLayout);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("1")) {
                        spinner = (Spinner) v.findViewById(i + 1);
                        listAnswer.add(spinner);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("3")) {
                        etTestGet = (EditText) v.findViewById(i + 1);
                        listAnswer.add(etTestGet);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("2")) {
                        listView = (ListView) v.findViewById(i + 1);
                        for (int x = 0; x < listView.getChildCount(); x++) {
                            View nextChild = listView.getChildAt(x);
                            if (nextChild instanceof CheckBox) {
                                cbTestGet = (CheckBox) nextChild;
                            }
                        }
                        listAnswer.add(listView);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("6")) {
                        rgTestGet = (RadioGroup) v.findViewById(i + 1);
                        listAnswer.add(rgTestGet);
                    } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("4")) {
                        layoutDate = (LinearLayout) v.findViewById(i+1);
                        for (int x = 0; x < layoutDate.getChildCount(); x++) {
                            View nextChild = layoutDate.getChildAt(x);
                            if (nextChild instanceof EditText) {
                                EditText editText = (EditText) nextChild;
                                dateView = (EditText) v.findViewById(editText.getId());
                            }
                        }
                        listAnswer.add(layoutDate);
                    }else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("7")){
                        layoutImgQuiz = (LinearLayout) v.findViewById(i+1);
                        for (int x = 0; x < layoutImgQuiz.getChildCount(); x++) {
                            View nextChild = layoutImgQuiz.getChildAt(x);
                            if (nextChild instanceof ImageView) {
                                ImageView img = (ImageView) nextChild;
                                imageView = (ImageView) v.findViewById(img.getId());
                            }else if (nextChild instanceof TextView) {
                                TextView textView = (TextView) nextChild;
                                tvImgQuiz = (TextView) v.findViewById(textView.getId());
                            }
                        }
                        listAnswer.add(layoutImgQuiz);
                    }else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("8")) {
                        layoutFileQuiz = (LinearLayout) v.findViewById(i+1);
                        for (int x = 0; x < layoutFileQuiz.getChildCount(); x++) {
                            View nextChild = layoutFileQuiz.getChildAt(x);
                            if (nextChild instanceof TextView) {
                                TextView textView = (TextView) nextChild;
                                if (textView.getId() == layoutFileQuiz.getId() * 37){
                                    tvFileQuiz = (TextView) v.findViewById(textView.getId());
                                }else if (textView.getId() == layoutFileQuiz.getId() * 63){
                                    tvFilePathQuiz = (TextView) v.findViewById(textView.getId());
                                }
                            }
                        }
                        listAnswer.add(layoutFileQuiz);
                    }
                }
                if (messValidasi(viewPager.getCurrentItem()).length() == 0){
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Confirm");
                    alertDialog.setMessage("Are you sure?");
                    alertDialog.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                        ProgressDialog progressDialog = new ProgressDialog(getContext());
                        @Override
                        public void onClick(final DialogInterface dialog, int which) {
                            AsyncSaveQuiz task = new AsyncSaveQuiz();
                            task.execute();
                        }
                    });
                    alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });

                    alertDialog.show();
                } else {
                    _clsMainActivity.showCustomToast(getActivity(),  messValidasi(viewPager.getCurrentItem()), false);
                }
            }
        });

        return v;
    }

    private String messValidasi(int i){
        boolean validate = true;
        String message = "";
        final String defaultMessage= "Please fill empty Field...";
        //ini buat ngatur validasinya
        final View jawaban = listAnswer.get(i);
        if (jawaban instanceof LinearLayout) {
            LinearLayout layout = (LinearLayout) jawaban;
            if (layout == (linearLayout = (LinearLayout) jawaban)) {
                for (int x = 0; x < linearLayout.getChildCount(); x++) {
                    View nextChild = linearLayout.getChildAt(x);
                    if (nextChild instanceof TextView) {
                        if (nextChild.getId() == linearLayout.getId() * 37) {
                            tvFileQuiz = (TextView) nextChild;
                            if (tvFileQuiz.getText().toString().equals("no file choosen")) {
                                validate = false;
                                message = "Please pick a file...";
                            } else {
                            }
                        } else if (nextChild.getId() == linearLayout.getId() * 200) {
                            textView = (TextView) nextChild;
                            if (textView.getText().toString().equals("Sliding of the blue pointer")) {
                                message = defaultMessage;
                                validate = false;
                            } else {
                            }
                        } else if (nextChild.getId() == linearLayout.getId() * 145) {
                            dateView = (EditText) nextChild;
                            if (dateView.getText().toString().equals("")) {
                                validate = false;
                                message = "Please pick a date...";
                            } else {
                            }
                        }
                    } else if (nextChild instanceof ImageView) {
                        if (nextChild.getId() == linearLayout.getId() * 77) {
                            imageView = (ImageView) nextChild;
                            if (imageView.getBackground() != null) {
                                validate = false;
                                message ="Please take a photo";
                            } else {
                            }
                        }
                    }
                }
            }
        }
        if (jawaban instanceof Spinner) {
            if ((spinner = (Spinner) jawaban).getSelectedItem().toString().equals("Select One")) {
                validate = false;
                message = defaultMessage;
            } else if ((spinner = (Spinner) jawaban).getSelectedItem().toString().equals("You Have Finished This Section")){
                message = "All SPG have been chosen";
                validate = false;
            } else {
            }
        }
        if (jawaban instanceof EditText) {
            if ((etTestGet = (EditText) jawaban).getText().toString().equals("") || (etTestGet = (EditText) jawaban).getText().toString().endsWith(" ")) {
                validate = false;
                message = defaultMessage;
            } else {
            }
        }
        if (jawaban instanceof RadioGroup) {
            if ((rgTestGet = (RadioGroup) jawaban).getCheckedRadioButtonId() == -1) {
                validate = false;
                message = defaultMessage;
            } else {
            }
        }

        if (jawaban instanceof ListView) {
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
                validate = false;
                message = defaultMessage;
            } else {
            }
        }
        if(validate == false){
            return message;
        } else {
            return "";
        }
    }

    private class AsyncSaveQuiz extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

               getValueQuiz();

            return null;
        }

        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPostExecute(Void aVoid) {
            new clsMainActivity().showCustomToast(getContext(), "Saved", true);
            FragmentKuesionerAwal fragmentKuesionerAwal = new FragmentKuesionerAwal();
            FragmentTransaction fragmentTransactionkuesionerAwal = getFragmentManager().beginTransaction();
            fragmentTransactionkuesionerAwal.replace(R.id.frame, fragmentKuesionerAwal);
            fragmentTransactionkuesionerAwal.commit();
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Saving your answer...");
            Dialog.setCancelable(false);
            Dialog.show();
        }
    }

    private List<tJawabanUserData> getValueQuiz() {
        List<tJawabanUserData> listData = new ArrayList<>();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            intGroupId = bundle.getInt("Key_GroupId");
        }
        final  List<mPertanyaanData> listDataPertanyaan = new mPertanyaanBL().GetDataByGroupQuestion(intGroupId);
        if (listDataPertanyaan.size() > 0){
            tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
            tAbsenUserData dataOutletCheckIn = new tAbsenUserBL().getDataCheckInActive();
            tJawabanUserHeaderData data = new tJawabanUserHeaderData();
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date time = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;
            try {
               date = sdf.parse(dataUserActive.get_dtLastLogin());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String timeNow = timeFormat.format(time);
            String dateNow = dateFormat.format(date);
            data.set_intHeaderId(ImagePick.GenerateGuid());
            data.set_intNik(dataUserActive.get_TxtEmpId());
            data.set_txtUserName(dataUserActive.get_txtUserName());
            data.set_intRoleId(dataUserActive.get_txtRoleId());
            data.set_txtOutletCode(dataOutletCheckIn.get_txtOutletCode());
            data.set_txtOutletName(dataOutletCheckIn.get_txtOutletName());
            data.set_dtDatetime(dateNow+" "+timeNow);
            data.set_dtDate(dateNow);
            data.set_intGroupQuestionId(String.valueOf(intGroupId));
            data.set_intSubmit("0");
            data.set_intSync("0");
            data.set_intAverage("0");
            data.set_intSum("0");
            new tJawabanUserHeaderBL().SaveDatatJawabanHeaderUser(data);
            List<tJawabanUserHeaderData> dataHeader = new tJawabanUserHeaderBL().GetLastBeforeSaveDetail();
            for (int i = 0; i < listDataPertanyaan.size(); i++) {
                List<mListJawabanData> mListJawabanDatas = new mListJawabanBL().GetDataByTypeQuestion(HMPertanyaan.get(HMPertanyaan.get(dataPertanyaan.get(i))), HMPertanyaan.get(dataPertanyaan.get(i)));
                dataJawaban = new ArrayList<>();
                if (mListJawabanDatas.size() > 0) {
                    if (mListJawabanDatas.get(0).get_txtValue().equals("SPG01")){
                        List<tHirarkiBIS> listSPG = new tHirarkiBISBL().GetDataByOutlet(dataOutletCheckIn.get_txtOutletCode());
                        if (listSPG.size() > 0) {
                            for (tHirarkiBIS dt : listSPG) {
                                dataJawaban.add(dt.get_txtName());
                                HMJawaban.put(dt.get_txtName(), dt.get_txtNik());
                                HMJawaban.put(dt.get_txtNik(), mListJawabanDatas.get(0).get_intListAnswerId());
                            }
                        }
                    } else if (mListJawabanDatas.get(0).get_txtValue().equals("CUS01")){
                        List<mEmployeeSalesProductData> listDataProductKalbe = new mEmployeeSalesProductBL().GetAllData();
                        if (listDataProductKalbe.size() > 0) {
                            for (mEmployeeSalesProductData dt : listDataProductKalbe) {
                                dataJawaban.add(dt.get_txtProductBrandDetailGramName());
                                HMJawaban.put(dt.get_txtProductBrandDetailGramName(), dt.get_txtBrandDetailGramCode());
                                HMJawaban.put(dt.get_txtBrandDetailGramCode(), mListJawabanDatas.get(0).get_intListAnswerId());
                            }
                        }
                    }else {
                        for (mListJawabanData jd : mListJawabanDatas) {
                            dataJawaban.add(jd.get_txtKey());
                            HMJawaban.put(jd.get_txtKey(), jd.get_txtValue());
                            HMJawaban.put(jd.get_txtValue(), jd.get_intListAnswerId());
                        }
                    }
                }

                if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("5")) {
                    LinearLayout linearLayout = (LinearLayout) listAnswer.get(i);
                    for (int x = 0; x < linearLayout.getChildCount(); x++) {
                        View nextChild = linearLayout.getChildAt(x);
                        if (nextChild instanceof SeekBar) {
                            seekbar = (SeekBar) nextChild;
                            tJawabanUserData dt = new tJawabanUserData();
                            dt.set_intUserAnswer(ImagePick.GenerateGuid());
                            dt.set_intUserId(dataUserActive.get_txtUserId());
                            dt.set_intNik(dataUserActive.get_TxtEmpId());
                            dt.set_intRoleId(dataUserActive.get_txtRoleId());
                            dt.set_intQuestionId(listDataPertanyaan.get(i).get_intQuestionId());
                            dt.set_intTypeQuestionId(listDataPertanyaan.get(i).get_intTypeQuestionId());
                            dt.set_bolHaveAnswerList(listDataPertanyaan.get(i).get_bolHaveAnswerList());
                            dt.set_intAnswerId(HMJawaban.get(HMJawaban.get(seekbar.getProgress())));
                            dt.set_txtValue(String.valueOf(seekbar.getProgress()));
                            dt.set_ptQuiz(null);
                            dt.set_txtFileQuiz(null);
                            dt.set_txtPath(null);
                            dt.set_decBobot("");
                            dt.set_intSubmit("1");
                            dt.set_intSync("0");
                            dt.set_intHeaderId(dataHeader.get(0).get_intHeaderId());
                            dt.set_dtDate(dataHeader.get(0).get_dtDate());
                            dt.set_dtDatetime(dateNow +" "+ timeNow);
                            listData.add(dt);
                            new tJawabanUserBL().SaveDatatJawabanUser(dt);
                        }
                    }
                } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("1")) {
                    Spinner spinner = (Spinner) listAnswer.get(i);
                    tJawabanUserData dt = new tJawabanUserData();
                    dt.set_intUserAnswer(ImagePick.GenerateGuid());
                    dt.set_intUserId(dataUserActive.get_txtUserId());
                    dt.set_intNik(dataUserActive.get_TxtEmpId());
                    dt.set_intRoleId(dataUserActive.get_txtRoleId());
                    dt.set_intQuestionId(listDataPertanyaan.get(i).get_intQuestionId());
                    dt.set_intTypeQuestionId(listDataPertanyaan.get(i).get_intTypeQuestionId());
                    dt.set_bolHaveAnswerList(listDataPertanyaan.get(i).get_bolHaveAnswerList());
                    dt.set_intAnswerId(HMJawaban.get(HMJawaban.get(spinner.getSelectedItem().toString())));
                    dt.set_txtValue(HMJawaban.get(spinner.getSelectedItem().toString()));
                    dt.set_ptQuiz(null);
                    dt.set_txtFileQuiz(null);
                    dt.set_txtPath(null);
                    dt.set_decBobot("");
                    dt.set_intSubmit("1");
                    dt.set_intSync("0");
                    dt.set_intHeaderId(dataHeader.get(0).get_intHeaderId());
                    dt.set_dtDate(dataHeader.get(0).get_dtDate());
                    dt.set_dtDatetime(dateNow +" "+ timeNow);
                    listData.add(dt);
                    new tJawabanUserBL().SaveDatatJawabanUser(dt);
                } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("3")) {
                    EditText editText = (EditText) listAnswer.get(i);
                    tJawabanUserData dt = new tJawabanUserData();
                    dt.set_intUserAnswer(ImagePick.GenerateGuid());
                    dt.set_intUserId(dataUserActive.get_txtUserId());
                    dt.set_intNik(dataUserActive.get_TxtEmpId());
                    dt.set_intRoleId(dataUserActive.get_txtRoleId());
                    dt.set_intQuestionId(listDataPertanyaan.get(i).get_intQuestionId());
                    dt.set_intTypeQuestionId(listDataPertanyaan.get(i).get_intTypeQuestionId());
                    dt.set_bolHaveAnswerList(listDataPertanyaan.get(i).get_bolHaveAnswerList());
                    dt.set_intAnswerId(HMJawaban.get(HMJawaban.get(editText.getText().toString())));
                    dt.set_txtValue(editText.getText().toString());
                    dt.set_ptQuiz(null);
                    dt.set_txtFileQuiz(null);
                    dt.set_txtPath(null);
                    dt.set_decBobot("");
                    dt.set_intSubmit("1");
                    dt.set_intSync("0");
                    dt.set_intHeaderId(dataHeader.get(0).get_intHeaderId());
                    dt.set_dtDate(dataHeader.get(0).get_dtDate());
                    dt.set_dtDatetime(dateNow +" "+ timeNow);
                    listData.add(dt);
                    new tJawabanUserBL().SaveDatatJawabanUser(dt);
                } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("2")) {
                    ListView listView = (ListView) listAnswer.get(i);
                    for (int x = 0; x < listView.getChildCount(); x++) {
                        View nextChild = listView.getChildAt(x);
                        if (nextChild instanceof CheckBox) {
                            cbTestGet = (CheckBox) nextChild;
                            if (cbTestGet.isChecked()) {
                                tJawabanUserData dt = new tJawabanUserData();
                                dt.set_intUserAnswer(ImagePick.GenerateGuid());
                                dt.set_intUserId(dataUserActive.get_txtUserId());
                                dt.set_intNik(dataUserActive.get_TxtEmpId());
                                dt.set_intRoleId(dataUserActive.get_txtRoleId());
                                dt.set_intQuestionId(listDataPertanyaan.get(i).get_intQuestionId());
                                dt.set_intTypeQuestionId(listDataPertanyaan.get(i).get_intTypeQuestionId());
                                dt.set_bolHaveAnswerList(listDataPertanyaan.get(i).get_bolHaveAnswerList());
                                dt.set_intAnswerId(HMJawaban.get(HMJawaban.get(cbTestGet.getText().toString())));
                                dt.set_txtValue(HMJawaban.get(cbTestGet.getText().toString()));
                                dt.set_ptQuiz(null);
                                dt.set_txtFileQuiz(null);
                                dt.set_txtPath(null);
                                dt.set_decBobot("");
                                dt.set_intSubmit("1");
                                dt.set_intSync("0");
                                dt.set_intHeaderId(dataHeader.get(0).get_intHeaderId());
                                dt.set_dtDate(dataHeader.get(0).get_dtDate());
                                dt.set_dtDatetime(dateNow +" "+ timeNow);
                                listData.add(dt);
                                new tJawabanUserBL().SaveDatatJawabanUser(dt);
                            }
                        }
                    }
                } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("6")) {
                    RadioGroup radioGroup = (RadioGroup) listAnswer.get(i);

                    if (!radioGroup.onCheckIsTextEditor()) {
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        if (selectedId > -1) {
                            RadioButton radioButton = (RadioButton) v.findViewById(selectedId);
                            if (radioButton.isChecked()) {
                                tJawabanUserData dt = new tJawabanUserData();
                                dt.set_intUserAnswer(ImagePick.GenerateGuid());
                                dt.set_intUserId(dataUserActive.get_txtUserId());
                                dt.set_intNik(dataUserActive.get_TxtEmpId());
                                dt.set_intRoleId(dataUserActive.get_txtRoleId());
                                dt.set_intQuestionId(listDataPertanyaan.get(i).get_intQuestionId());
                                dt.set_intTypeQuestionId(listDataPertanyaan.get(i).get_intTypeQuestionId());
                                dt.set_bolHaveAnswerList(listDataPertanyaan.get(i).get_bolHaveAnswerList());
                                dt.set_intAnswerId(HMJawaban.get(HMJawaban.get(radioButton.getText().toString())));
                                dt.set_txtValue(HMJawaban.get(radioButton.getText().toString()));
                                dt.set_ptQuiz(null);
                                dt.set_txtFileQuiz(null);
                                dt.set_txtPath(null);
                                dt.set_decBobot("");
                                dt.set_intSubmit("1");
                                dt.set_intSync("0");
                                dt.set_intHeaderId(dataHeader.get(0).get_intHeaderId());
                                dt.set_dtDate(dataHeader.get(0).get_dtDate());
                                dt.set_dtDatetime(dateNow +" "+ timeNow);
                                listData.add(dt);
                                new tJawabanUserBL().SaveDatatJawabanUser(dt);
                            }
                        }
                    }
                } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("4")) {
                    LinearLayout linearLayout = (LinearLayout) listAnswer.get(i);
                    for (int x = 0; x < linearLayout.getChildCount(); x++) {
                        View nextChild = linearLayout.getChildAt(x);
                        if (nextChild instanceof EditText) {
                            dateView = (EditText) nextChild;
                            tJawabanUserData dt = new tJawabanUserData();
                            dt.set_intUserAnswer(ImagePick.GenerateGuid());
                            dt.set_intUserId(dataUserActive.get_txtUserId());
                            dt.set_intNik(dataUserActive.get_TxtEmpId());
                            dt.set_intRoleId(dataUserActive.get_txtRoleId());
                            dt.set_intQuestionId(listDataPertanyaan.get(i).get_intQuestionId());
                            dt.set_intTypeQuestionId(listDataPertanyaan.get(i).get_intTypeQuestionId());
                            dt.set_bolHaveAnswerList(listDataPertanyaan.get(i).get_bolHaveAnswerList());
                            dt.set_intAnswerId(HMJawaban.get(HMJawaban.get(dateView.getText().toString())));
                            dt.set_txtValue(dateView.getText().toString());
                            dt.set_ptQuiz(null);
                            dt.set_txtFileQuiz(null);
                            dt.set_txtPath(null);
                            dt.set_decBobot("");
                            dt.set_intSubmit("1");
                            dt.set_intSync("0");
                            dt.set_intHeaderId(dataHeader.get(0).get_intHeaderId());
                            dt.set_dtDate(dataHeader.get(0).get_dtDate());
                            dt.set_dtDatetime(dateNow +" "+ timeNow);
                            listData.add(dt);
                            new tJawabanUserBL().SaveDatatJawabanUser(dt);
                        }
                    }
                } else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("7")) {
                    LinearLayout linearLayout = (LinearLayout) listAnswer.get(i);

                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    for (int z = 0; z < linearLayout.getChildCount(); z++) {
                        View nextChild = linearLayout.getChildAt(z);
                        if (nextChild instanceof TextView) {
                            tvImgQuiz = (TextView) nextChild;
                            String selectedImage = tvImgQuiz.getText().toString();
                            String fileName = selectedImage.substring(selectedImage.lastIndexOf('/')+1, selectedImage.length());
                            Bitmap bm = BitmapFactory.decodeFile(selectedImage, bitmapOptions);
                            byte[] byteQuiz = ImagePick.byteQuiz(bm);
                            tJawabanUserData dt7 = new tJawabanUserData();
                            dt7.set_intUserAnswer(ImagePick.GenerateGuid());
                            dt7.set_intUserId(dataUserActive.get_txtUserId());
                            dt7.set_intNik(dataUserActive.get_TxtEmpId());
                            dt7.set_intRoleId(dataUserActive.get_txtRoleId());
                            dt7.set_intQuestionId(listDataPertanyaan.get(i).get_intQuestionId());
                            dt7.set_intTypeQuestionId(listDataPertanyaan.get(i).get_intTypeQuestionId());
                            dt7.set_bolHaveAnswerList(listDataPertanyaan.get(i).get_bolHaveAnswerList());
                            dt7.set_intAnswerId(null);
                            dt7.set_txtValue(fileName);
                            dt7.set_ptQuiz(byteQuiz);
                            dt7.set_txtFileQuiz(null);
                            dt7.set_txtPath(selectedImage);
                            dt7.set_decBobot("");
                            dt7.set_intSubmit("1");
                            dt7.set_intSync("0");
                            dt7.set_intHeaderId(dataHeader.get(0).get_intHeaderId());
                            dt7.set_dtDate(dataHeader.get(0).get_dtDate());
                            dt7.set_dtDatetime(dateNow +" "+ timeNow);
                            listData.add(dt7);
                            new tJawabanUserBL().SaveDatatJawabanUser(dt7);
                        }
                    }
                }else if (listDataPertanyaan.get(i).get_intTypeQuestionId().equals("8")) {
                    LinearLayout linearLayout = (LinearLayout) listAnswer.get(i);
                    for (int z = 0; z < linearLayout.getChildCount(); z++) {
                        View nextChild = linearLayout.getChildAt(z);
                        if (nextChild instanceof Button) {

                        } else {
                            TextView textView = (TextView) nextChild;
                            if (textView.getId() == layoutFileQuiz.getId() * 37){
                                tvFileQuiz = (TextView) nextChild;
                                tJawabanUserData dt7 = new tJawabanUserData();
                                String uri = tvFilePathQuiz.getText().toString();
                                String fileName = uri.substring(uri.lastIndexOf('/')+1, uri.length());
                                byte[] byteFile = null;
                                try {
                                    byteFile = ImagePick.getFile(Uri.parse(uri), getContext());
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                dt7.set_intUserAnswer(ImagePick.GenerateGuid());
                                dt7.set_intUserId(dataUserActive.get_txtUserId());
                                dt7.set_intNik(dataUserActive.get_TxtEmpId());
                                dt7.set_intRoleId(dataUserActive.get_txtRoleId());
                                dt7.set_intQuestionId(listDataPertanyaan.get(i).get_intQuestionId());
                                dt7.set_intTypeQuestionId(listDataPertanyaan.get(i).get_intTypeQuestionId());
                                dt7.set_bolHaveAnswerList(listDataPertanyaan.get(i).get_bolHaveAnswerList());
                                dt7.set_intAnswerId(HMJawaban.get(HMJawaban.get(textView.getText().toString())));
                                dt7.set_txtValue(fileName);
                                dt7.set_ptQuiz(null);
                                dt7.set_txtFileQuiz(byteFile);
                                dt7.set_txtPath(uri);
                                dt7.set_decBobot("");
                                dt7.set_intSubmit("1");
                                dt7.set_intSync("0");
                                dt7.set_intHeaderId(dataHeader.get(0).get_intHeaderId());
                                dt7.set_dtDate(dataHeader.get(0).get_dtDate());
                                dt7.set_dtDatetime(dateNow +" "+ timeNow);
                                listData.add(dt7);
                                new tJawabanUserBL().SaveDatatJawabanUser(dt7);
                            }else if (textView.getId() == layoutFileQuiz.getId() * 63){
                                tvFilePathQuiz = (TextView) nextChild;
                            }
                        }
                    }
                }
            }
            for (tJawabanUserHeaderData dt : dataHeader){

                int total = 0;
                int penyebut = 0;
                List<tJawabanUserData> list = new tJawabanUserBL().GetDataByHeaderId(dt.get_intHeaderId());
                if (list.size()>0){
                    //cek parentnya soal bukan
                    mKategoriData kategoriData = new mKategoriBL().GetCategoryById(listDataPertanyaan.get(0).get_intCategoryId());
                    if (kategoriData.get_intParentId().equals("2")){
                        for(tJawabanUserData datum : list){
                            if (datum.get_intTypeQuestionId().equals("5")){
                                total += Integer.parseInt(datum.get_txtValue().trim());
                                penyebut +=1;
                            }
                        }
                        Double average = Double.parseDouble(String.valueOf(total))/penyebut;
                        DecimalFormat df = new DecimalFormat("#.##");
                        String avg = df.format(average).replace(",", ".");
                        dt.set_intSum(String.valueOf(total));
                        dt.set_intAverage(avg);
                    }
                }
                dt.set_intSubmit("1");
                dt.set_intId(dataHeader.get(0).get_intId());
                new tJawabanUserHeaderBL().SaveDatatJawabanHeaderUser(dt);
            }
        }
        return listData;
    }


    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        dataPertanyaan = new ArrayList<>();
        dataPertanyaan2 = new ArrayList<>();
        dataPertanyaan3 = new ArrayList<>();

        dataKategori = new ArrayList<>();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            intGroupId = bundle.getInt("Key_GroupId");
        }
        List<mPertanyaanData > pertanyaanDataListbyGroupid = new mPertanyaanBL().GetDataByGroupQuestion(intGroupId);
        List<mKategoriData> kategoriDataList = new mKategoriBL().GetAllData();

//mapping pertanyaan
        if (pertanyaanDataListbyGroupid.size() > 0) {
            for (mPertanyaanData dt : pertanyaanDataListbyGroupid) {
                dataPertanyaan.add(dt.get_txtQuestionDesc());
                HMPertanyaan.put(dt.get_txtQuestionDesc(), dt.get_intQuestionId());
                HMPertanyaan.put(dt.get_intQuestionId(), dt.get_intTypeQuestionId());
            }
        }

        if (pertanyaanDataListbyGroupid.size() > 0) {
            for (mPertanyaanData dt : pertanyaanDataListbyGroupid) {
                dataPertanyaan3.add(dt.get_txtQuestionDesc());
                HMPertanyaan3.put(dt.get_txtQuestionDesc(), dt.get_intQuestionId());
                HMPertanyaan3.put(dt.get_intQuestionId(), dt.get_intSoalId());
            }
        }
        //mapping pertanyaan yang kedua
        if (pertanyaanDataListbyGroupid.size() > 0) {
            for (mPertanyaanData data : pertanyaanDataListbyGroupid) {
                dataPertanyaan2.add(data.get_intQuestionId());
                HMPertanyaan2.put(data.get_intQuestionId(), data.get_intCategoryId());
            }
        }

//mapping kategori
        if (kategoriDataList.size() > 0) {
            for (mKategoriData dt : kategoriDataList) {
                dataKategori.add(dt.get_intCategoryId());
                HMKategori.put(dt.get_intCategoryId(), dt.get_txtCategoryName());
            }
        }
//isi fragment dan get jawaban berdasarkan type pertanyaan

        for (int i = 0; i < pertanyaanDataListbyGroupid.size(); i++) {
            modelJawaban = new ArrayList<jawabanModel>();
            List<mListJawabanData> mListJawabanDatas = new mListJawabanBL().GetDataByTypeQuestion(HMPertanyaan.get(HMPertanyaan.get(dataPertanyaan.get(i))), HMPertanyaan.get(dataPertanyaan.get(i)));
            if (mListJawabanDatas.size() > 0 && pertanyaanDataListbyGroupid.get(i).get_intTypeQuestionId() == HMPertanyaan.get(HMPertanyaan.get(dataPertanyaan.get(i)))) {
                for (int j = 0; j < mListJawabanDatas.size(); j++) {
                    jawabanModel dt = new jawabanModel();
                    dt.setKey(mListJawabanDatas.get(j).get_txtKey());
                    dt.setValue(mListJawabanDatas.get(j).get_txtValue());
                    modelJawaban.add(dt);
                }
            }
            adapter.addFrag(new FragmentKuesionerPart(HMPertanyaan.get(dataPertanyaan.get(i)), i + 1, dataPertanyaan.get(i), Integer.parseInt( HMPertanyaan.get(HMPertanyaan.get(dataPertanyaan.get(i)))), modelJawaban), "SOAL " + HMPertanyaan3.get(HMPertanyaan3.get(dataPertanyaan3.get(i))));
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
