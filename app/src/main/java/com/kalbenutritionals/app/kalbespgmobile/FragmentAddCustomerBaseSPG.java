package com.kalbenutritionals.app.kalbespgmobile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import addons.adapter.AdapterListProductCustomerBased;
import bl.clsHelperBL;
import bl.mProductCompetitorBL;
import bl.mProductPICBL;
import bl.mProductSPGBL;
import bl.mTypeSubmissionMobileBL;
import bl.tCustomerBasedMobileDetailBL;
import bl.tCustomerBasedMobileDetailProductBL;
import bl.tCustomerBasedMobileHeaderBL;
import bl.tUserLoginBL;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import library.spgmobile.common.AppAdapter;
import library.spgmobile.common.ModelListview;
import library.spgmobile.common.clsSwipeList;
import library.spgmobile.common.mProductCompetitorData;
import library.spgmobile.common.mProductPICData;
import library.spgmobile.common.mProductSPGData;
import library.spgmobile.common.mTypeSubmissionMobile;
import library.spgmobile.common.tCustomerBasedMobileDetailData;
import library.spgmobile.common.tCustomerBasedMobileDetailProductData;
import library.spgmobile.common.tCustomerBasedMobileHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.visitplanAbsenData;

public class FragmentAddCustomerBaseSPG extends Fragment implements View.OnClickListener, IXListViewListener {
    private ArrayList<ModelListview> modelItems;
    private List<tCustomerBasedMobileDetailData> dtDetail;
    tCustomerBasedMobileHeaderData dtHeader;
    ListView lvProduct;
    MyAdapter dataAdapter;

    Spinner spnSubmissionCode;
    EditText etCustomerBasedNo, etEmail, etNama, etTelpon, etTelpon2, etAlamat, etTelponKantor, etPinBBM, etTglLhr, etAge;
    TextInputLayout textInputLayoutNama, textInputLayoutTelp, textInputLayoutTelp2, textInputLayoutTelpKantor, textInputLayoutEmail, textInputLayoutTglLahir, textInputLayoutAge;
    DatePicker dpHeader;
    ScrollView sv;

    TableRow row1, row2, row3, row4, row5, row6;
    ImageView imgReadMore;

    CheckBox cbPIC;
    RadioGroup radioGenderGroup;
    RadioGroup radioDateType;
    RadioButton radioGenderButton;
    ImageButton btnSave;
    List<tCustomerBasedMobileDetailProductData> dtListDetailProduct;
    AdapterListProductCustomerBased AdapterProduct;
    View v;

    List<tCustomerBasedMobileDetailData> dtListDetail;

    private List<clsSwipeList> swipeList = new ArrayList<>();
    private ArrayList<clsSwipeList> swipeListProduct = new ArrayList<>();

    private Handler mHandler;
    private HashMap<String, String> HMSubmision = new HashMap<>();

    private String txtDetailIDCustomerBased;
    String idTrCustomer = null;
    String param = null;
    Date dt_hidden;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dtDetail = new ArrayList<>();
        v = inflater.inflate(R.layout.fragment_customerbase_add, container, false);

        Bundle mBundle = getArguments();
        if (mBundle != null) {
            idTrCustomer = mBundle.getString("idTrCustomer");
            param = mBundle.getString("param");
        }
        row1 = (TableRow) v.findViewById(R.id.row_telp2);
        row2 = (TableRow) v.findViewById(R.id.row_telp_kantor);
        row3 = (TableRow) v.findViewById(R.id.row_bbm);
        row4 = (TableRow) v.findViewById(R.id.row_alamat);
        row5 = (TableRow) v.findViewById(R.id.row_status);
        row6 = (TableRow) v.findViewById(R.id.row_email);
        imgReadMore = (ImageView) v.findViewById(R.id.img_read_more);
        imgReadMore.setTag(1);
        imgReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = String.valueOf(imgReadMore.getTag());
                if (tag.equals("1")) {
                    imgReadMore.setTag(2);
                    imgReadMore.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    row1.setVisibility(View.VISIBLE);
                    row2.setVisibility(View.VISIBLE);
                    row3.setVisibility(View.VISIBLE);
                    row4.setVisibility(View.VISIBLE);
                    row5.setVisibility(View.VISIBLE);
                    row6.setVisibility(View.VISIBLE);
                } else if (tag.equals("2")) {
                    imgReadMore.setTag(1);
                    imgReadMore.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    row1.setVisibility(View.GONE);
                    row2.setVisibility(View.GONE);
                    row3.setVisibility(View.GONE);
                    row4.setVisibility(View.GONE);
                    row5.setVisibility(View.GONE);
                    row6.setVisibility(View.GONE);
                }
            }
        });


        etCustomerBasedNo = (EditText) v.findViewById(R.id.etCustomerBasedNo);
        etAlamat = (EditText) v.findViewById(R.id.etAlamat);
        textInputLayoutNama = (TextInputLayout) v.findViewById(R.id.input_layout_nama);
        etNama = (EditText) v.findViewById(R.id.etNama);
        dpHeader = (DatePicker) v.findViewById(R.id.dp_tgl_lahir_header);
        etTglLhr = (EditText) v.findViewById(R.id.etTanggalLahir);

        etNama.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        etNama.setSingleLine(false);

        InputFilter filter2 = new InputFilter() {
            boolean canEnterSpace = false;

            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {

                if (etNama.getText().toString().equals("")) {
                    canEnterSpace = false;
                }

                StringBuilder builder = new StringBuilder();

                for (int i = start; i < end; i++) {
                    char currentChar = source.charAt(i);

                    if (Character.isLetterOrDigit(currentChar) || currentChar == '_') {
                        builder.append(currentChar);
                        canEnterSpace = true;
                    }

                    if (Character.isWhitespace(currentChar) && canEnterSpace) {
                        builder.append(currentChar);
                    }

                }
                return builder.toString();
            }

        };
        InputFilter inputFilter = new InputFilter.AllCaps();
        etNama.setFilters(new InputFilter[]{filter2,inputFilter});

        etTglLhr.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etTglLhr) {
            @Override
            public boolean onDrawableClick() {
                showDatePicker();
                return false;
            }
        });
        if (etTglLhr.getText().toString().equals("")) {
            etTglLhr.setHint("Not set");
        } else {
            etTglLhr.setHint("");
        }

        textInputLayoutTelp = (TextInputLayout) v.findViewById(R.id.input_layout_telp);
        etTelpon = (EditText) v.findViewById(R.id.etTelpon);

        textInputLayoutTelp2 = (TextInputLayout) v.findViewById(R.id.input_layout_telp2);
        etTelpon2 = (EditText) v.findViewById(R.id.etTelpon2);

        textInputLayoutEmail = (TextInputLayout) v.findViewById(R.id.input_layout_email);
        etEmail = (EditText) v.findViewById(R.id.etEmail);

        textInputLayoutTelpKantor = (TextInputLayout) v.findViewById(R.id.input_layout_telpKantor);
        etTelponKantor = (EditText) v.findViewById(R.id.etTelponKantor);

        etPinBBM = (EditText) v.findViewById(R.id.etPinBBM);
        cbPIC = (CheckBox) v.findViewById(R.id.cbPIC);

        etTelpon.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etTelpon) {
            public boolean onDrawableClick() {
                if (!etTelpon.getText().toString().equals("")) {
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", etTelpon.getText().toString(), null)));
                }
                return true;
            }
        });
        etTelpon2.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etTelpon2) {
            public boolean onDrawableClick() {
                if (!etTelpon2.getText().toString().equals("")) {
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", etTelpon2.getText().toString(), null)));
                }
                return true;
            }
        });
        spnSubmissionCode = (Spinner) v.findViewById(R.id.spn_submission);

        final List<mTypeSubmissionMobile> typeSubmissionDataList = new mTypeSubmissionMobileBL().GetAllData();
        List<String> arrData = new ArrayList<>();
        if (typeSubmissionDataList.size() > 0) {
            for (mTypeSubmissionMobile dt : typeSubmissionDataList) {
                arrData.add(dt.get_txtNamaMasterData());
                HMSubmision.put(dt.get_txtNamaMasterData(), dt.get_txtKeterangan());
                HMSubmision.put(dt.get_txtKeterangan(), dt.get_txtMasterID());
            }
        }
        ArrayAdapter<String> adapterSubmission = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, arrData);
        spnSubmissionCode.setAdapter(adapterSubmission);

        dtHeader = new tCustomerBasedMobileHeaderBL().getDataByBitActive();

        radioDateType = (RadioGroup) v.findViewById(R.id.radioDateType);
        etAge = (EditText) v.findViewById(R.id.etAge);

        textInputLayoutTglLahir = (TextInputLayout) v.findViewById(R.id.input_layout_tgl_lahir);
        textInputLayoutAge = (TextInputLayout) v.findViewById(R.id.input_layout_age);

        if (dtHeader.get_intAgeTypeFlag() != null) {

            TextInputLayout tl_tglLahir = (TextInputLayout) v.findViewById(R.id.input_layout_tgl_lahir);
            TextInputLayout tl_age = (TextInputLayout) v.findViewById(R.id.input_layout_age);

            if (dtHeader.get_intAgeTypeFlag().equals("Date")) {
                radioDateType.check(R.id.radioDate);

                if (dtHeader.get_txtTglLahir().equals("null")) {
                    dpHeader.setMaxDate(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
                } else {

                    String stringDatedb = dtHeader.get_txtTglLahir();
                    String[] parts = stringDatedb.split("-");

                    int year = Integer.valueOf(parts[0]);
                    int month = Integer.valueOf(parts[1]);
                    int day = Integer.valueOf(parts[2]);

                    dpHeader.updateDate(year, month, day);
                    clsMainActivity clsMainMonth = new clsMainActivity();
                    String month2 = clsMainMonth.months[month];
                    etTglLhr.setText(day + " - " + month2 + " - " + year);
                    etTglLhr.setHint("");
                }
                tl_tglLahir.setVisibility(View.VISIBLE);
                tl_age.setVisibility(View.GONE);
                etAge.setText(dtHeader.get_intAge());
            } else if (dtHeader.get_intAgeTypeFlag().equals("Age")) {
                radioDateType.check(R.id.radioAge);
                etAge.setText(dtHeader.get_intAge());
                tl_age.setVisibility(View.VISIBLE);
                tl_tglLahir.setVisibility(View.GONE);
            }
        }

        radioDateType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TextInputLayout tl_tglLahir = (TextInputLayout) v.findViewById(R.id.input_layout_tgl_lahir);
                TextInputLayout tl_age = (TextInputLayout) v.findViewById(R.id.input_layout_age);

                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    if (checkedRadioButton.getText().equals("PIC Date of Birth")) {
                        tl_tglLahir.setVisibility(View.VISIBLE);
                        tl_age.setVisibility(View.GONE);

                    } else if (checkedRadioButton.getText().equals("PIC Age (years)")) {
                        tl_age.setVisibility(View.VISIBLE);
                        tl_tglLahir.setVisibility(View.GONE);
                        etAge.requestFocus();
                    }
                }
            }
        });

        mTypeSubmissionMobile dataByLastSelected = new mTypeSubmissionMobileBL().GetLastSelected();

        if (dataByLastSelected != null) {
            spnSubmissionCode.setSelection(adapterSubmission.getPosition(dataByLastSelected.get_txtNamaMasterData()));
        }

        dpHeader.setMaxDate(System.currentTimeMillis() - 24 * 60 * 60 * 1000);

        int year;
        int month;
        int day;

        if (dtHeader.get_intTrCustomerId() != null) {
            etCustomerBasedNo.setText(dtHeader.get_txtSubmissionId());
            etAlamat.setText(dtHeader.get_txtALamat());
            etNama.setText(dtHeader.get_txtNamaDepan());
            etTelpon.setText(dtHeader.get_txtTelp());
            etTelpon2.setText(dtHeader.get_txtTelp2());
            etTelponKantor.setText(dtHeader.get_txtTelpKantor());
            etEmail.setText(dtHeader.get_txtEmail());
            etPinBBM.setText(dtHeader.get_txtPINBBM());
            cbPIC.setChecked(dtHeader.get_intPIC().equals("1"));
            if (dtHeader.get_txtTglLahir().equals("null")) {
                dpHeader.setMaxDate(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
                etTglLhr.setHint("Not set");
            } else {

                String stringDatedb = dtHeader.get_txtTglLahir();
                String[] parts = stringDatedb.split("-");

                year = Integer.valueOf(parts[0]);
                month = Integer.valueOf(parts[1]);
                day = Integer.valueOf(parts[2]);

                dpHeader.updateDate(year, month, day);
                clsMainActivity clsMainMonth = new clsMainActivity();
                String month2 = clsMainMonth.months[month];
                etTglLhr.setText(day + " - " + month2 + " - " + year);
            }


            RadioButton rbLaki = (RadioButton) v.findViewById(R.id.radioMale);
            RadioButton rbPerempuan = (RadioButton) v.findViewById(R.id.radioFemale);

            if (dtHeader.get_txtGender().equals("Perempuan")) {
                rbPerempuan.setChecked(true);
                rbLaki.setChecked(false);
            } else {
                rbLaki.setChecked(true);
                rbPerempuan.setChecked(false);
            }

            String key = "";
            for (Map.Entry<String, String> entry : HMSubmision.entrySet()) {
                if (entry.getValue().equals(dtHeader.get_txtSubmissionCode())) {
                    key = entry.getKey();
                }
            }

            spnSubmissionCode.setSelection(adapterSubmission.getPosition(key));
        } else if (mBundle != null && !idTrCustomer.equals("null")) {
            dtHeader = new tCustomerBasedMobileHeaderBL().getDataById(idTrCustomer);

            etCustomerBasedNo.setText(dtHeader.get_txtSubmissionId());
            etAlamat.setText(dtHeader.get_txtALamat());
            etNama.setText(dtHeader.get_txtNamaDepan());
            etTelpon.setText(dtHeader.get_txtTelp());
            etTelpon2.setText(dtHeader.get_txtTelp2());
            etTelponKantor.setText(dtHeader.get_txtTelpKantor());
            etEmail.setText(dtHeader.get_txtEmail());
            etPinBBM.setText(dtHeader.get_txtPINBBM());
            cbPIC.setChecked(dtHeader.get_intPIC().equals("1"));
            if (dtHeader.get_txtTglLahir().equals("null")) {
                dpHeader.setMaxDate(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
            } else {

                String stringDatedb = dtHeader.get_txtTglLahir();
                String[] parts = stringDatedb.split("-");

                year = Integer.valueOf(parts[0]);
                month = Integer.valueOf(parts[1]);
                day = Integer.valueOf(parts[2]);

                dpHeader.updateDate(year, month, day);
                clsMainActivity clsMainMonth = new clsMainActivity();
                String month2 = clsMainMonth.months[month];
                etTglLhr.setText(day + " - " + month2 + " - " + year);
                etTglLhr.setHint("");
            }


            RadioButton rbLaki = (RadioButton) v.findViewById(R.id.radioMale);
            RadioButton rbPerempuan = (RadioButton) v.findViewById(R.id.radioFemale);

            if (dtHeader.get_txtGender().equals("Perempuan")) {
                rbPerempuan.setChecked(true);
                rbLaki.setChecked(false);
            } else {
                rbLaki.setChecked(true);
                rbPerempuan.setChecked(false);
            }

            String key = "";
            for (Map.Entry<String, String> entry : HMSubmision.entrySet()) {
                if (entry.getValue().equals(dtHeader.get_txtSubmissionCode())) {
                    key = entry.getKey();
                }
            }

            spnSubmissionCode.setSelection(adapterSubmission.getPosition(key));
        }

        spnSubmissionCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                etCustomerBasedNo.setText(new tCustomerBasedMobileHeaderBL().generateSubmissionId(typeSubmissionDataList.get(i).get_txtKeterangan()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btnAdd = (Button) v.findViewById(R.id.btnAdd);
        ImageButton btnAddPerson = (ImageButton) v.findViewById(R.id.btnAddPerson);
        btnSave = (ImageButton) v.findViewById(R.id.btnSave);
        ImageButton btnBack = (ImageButton) v.findViewById(R.id.btnBackToHeader);
        btnAdd.setOnClickListener(this);
        btnAddPerson.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        InputFilter[] fArray = new InputFilter[3];

        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetterOrDigit(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        return "";
                    }
                }
                return null;
            }
        };

        fArray[0] = new InputFilter.LengthFilter(8);
        fArray[1] = new InputFilter.AllCaps();
        fArray[2] = filter;

        etPinBBM.setFilters(fArray);

        etAlamat.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        etAlamat.setSingleLine(false);

        InputFilter inputFilter_etAlamat1 = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                if (charSequence.equals("")) { // for backspace
                    return charSequence;
                }
                if (charSequence.toString().matches("[a-zA-Z0-9. ]+")) {
                    return charSequence;
                }
                return "";
            }
        };

        InputFilter inputFilter_etAlamat2 = new InputFilter.AllCaps();

        etAlamat.setFilters(new InputFilter[]{inputFilter_etAlamat1,inputFilter_etAlamat2});

        return v;
    }

    private void showDatePicker() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.popup_datepicker, null);
        final DatePicker dp = (DatePicker) promptView.findViewById(R.id.dp_tgl_lahir);

        String dt = dtHeader.get_txtTglLahir();
        Bundle args = new Bundle();
        if ((dt == null || dt.equals("null")) && etTglLhr.getText().toString().equals("")) {
            args.putInt("year", 1900);
            args.putInt("month", 0);
            args.putInt("day", 1);
            dp.init(1900, 0, 1, null);
        } else {
            if (dt_hidden != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(dt_hidden);
                dp.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
            } else if (dt != null) {
                String[] parts = dt.split("-");
                String part1 = parts[0]; //year
                String part2 = parts[1]; //month
                String part3 = parts[2]; //date
                args.putInt("year", Integer.parseInt(part1));
                args.putInt("month", Integer.parseInt(part2) - 1);
                args.putInt("day", Integer.parseInt(part3));
                dp.init(Integer.parseInt(part1), Integer.parseInt(part2) - 1, Integer.parseInt(part3), null);
            }
        }

        dp.setMaxDate(System.currentTimeMillis() - 24 * 60 * 60 * 1000);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clsMainActivity clsMainMonth = new clsMainActivity();

                        String month2 = clsMainMonth.months[dp.getMonth() + 1];
                        etTglLhr.setText(String.valueOf(dp.getDayOfMonth()) + " - " + month2 + " - " + String.valueOf(dp.getYear()));
                        etTglLhr.setHint("");

                        int day = dp.getDayOfMonth();
                        int month = dp.getMonth();
                        int year = dp.getYear();

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);

                        dt_hidden = calendar.getTime();
                    }
                })
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.setTitle("Set Birth Date");
        alertD.show();


//        DatePickerFragment date = new DatePickerFragment();
//        /**
//         * Set Up Current Date Into dialog
//         */
//        Calendar calender = Calendar.getInstance();
//        calender.add(Calendar.DATE, -1);
//        String dt = dtHeader.get_txtTglLahir();
//        Bundle args = new Bundle();
//        if (dt == null || dt.equals("null")) {
//            args.putInt("year", 1900);
//            args.putInt("month", 0);
//            args.putInt("day", 1);
//            date.setArguments(args);
//
//        } else {
//            String[] parts = dt.split("-");
//            String part1 = parts[0]; //year
//            String part2 = parts[1]; //month
//            String part3 = parts[2]; //date
//            args.putInt("year", Integer.parseInt(part1));
//            args.putInt("month", Integer.parseInt(part2) - 1);
//            args.putInt("day", Integer.parseInt(part3));
//            date.setArguments(args);
//        }
//        /**
//         * Set Call back to capture selected date
//         */
//        date.setCallBack(ondate);
//        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            clsMainActivity clsMainMonth = new clsMainActivity();
            String month2 = clsMainMonth.months[monthOfYear + 1];
            etTglLhr.setText(String.valueOf(dayOfMonth) + " - " + month2 + " - " + String.valueOf(year));
            etTglLhr.setHint("");
        }
    };

    @Override
    public void onClick(View view) {

        LinearLayout lnTop = (LinearLayout) v.findViewById(R.id.linearLayoutTop);
        LinearLayout lnBottom = (LinearLayout) v.findViewById(R.id.linearLayoutBottom);

        switch (view.getId()) {
            case R.id.btnBackToHeader:
                lnTop.setVisibility(View.VISIBLE);
                lnBottom.setVisibility(View.GONE);
                break;

            case R.id.btnAddPerson:
                popUpAddPerson(new tCustomerBasedMobileDetailData());
                break;

            case R.id.btnAdd:

                final boolean validate = validateHeader();
                if (validate) {
                    dtHeader = new tCustomerBasedMobileHeaderBL().getDataByBitActive();

                    if (dtHeader.get_intTrCustomerId() == null && !idTrCustomer.equals("null")) {
                        dtHeader = new tCustomerBasedMobileHeaderBL().getDataById(idTrCustomer);
                    }

                    boolean validatedetailProduct = false;
                    if (dtHeader.get_txtSubmissionCode() != null) {
                        List<tCustomerBasedMobileDetailData> tCustomerBasedMobileDetailDatas = new tCustomerBasedMobileDetailBL().getAllDataByHeaderId(dtHeader.get_intTrCustomerId());
                        if (tCustomerBasedMobileDetailDatas.size() > 0) {
                            for (tCustomerBasedMobileDetailData dataDetail : tCustomerBasedMobileDetailDatas) {
                                List<tCustomerBasedMobileDetailProductData> listdetailProduct = new tCustomerBasedMobileDetailProductBL().getDataByCustomerDetailId(dataDetail.get_intTrCustomerIdDetail());

                                if (listdetailProduct.size() > 0) {
                                    validatedetailProduct = true;
                                    break;
                                }
                            }
                        }
                    }


                    if (validatedetailProduct && !dtHeader.get_txtSubmissionCode().equals(HMSubmision.get(spnSubmissionCode.getSelectedItem()))) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Warning");
                        builder.setMessage("You have changed the Submission Type, are you sure to delete registered Product?");

                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //spnSubmissionCode.setSelection();
                            }
                        });

                        final AlertDialog alert = builder.create();
                        alert.show();
                        alert.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                boolean deleteDetailProduct = true;
                                viewListPerson(deleteDetailProduct);
                                alert.dismiss();
                            }
                        });
                    } else {
                        boolean deleteDetailProduct = false;
                        viewListPerson(deleteDetailProduct);
                    }
                } else {
                    sv = (ScrollView) v.findViewById(R.id.scroll);
                    sv.fullScroll(ScrollView.FOCUS_UP);
                }

                break;

            case R.id.btnSave:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        saveCustomerBase();
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
                break;
        }
    }

    private void viewListPerson(boolean deleteDetailProduct) {
        dtHeader = new tCustomerBasedMobileHeaderBL().getDataByBitActive();

        if (dtHeader.get_intTrCustomerId() == null && !idTrCustomer.equals("null")) {
            dtHeader = new tCustomerBasedMobileHeaderBL().getDataById(idTrCustomer);
        }

        if (deleteDetailProduct) {
            List<tCustomerBasedMobileDetailData> tCustomerBasedMobileDetailDatas = new tCustomerBasedMobileDetailBL().getAllDataByHeaderId(dtHeader.get_intTrCustomerId());
            if (tCustomerBasedMobileDetailDatas.size() > 0) {
                for (tCustomerBasedMobileDetailData dataDetail : tCustomerBasedMobileDetailDatas) {
                    List<tCustomerBasedMobileDetailProductData> listdetailProduct = new tCustomerBasedMobileDetailProductBL().getDataByCustomerDetailId(dataDetail.get_intTrCustomerIdDetail());

                    if (listdetailProduct.size() > 0) {
                        for (tCustomerBasedMobileDetailProductData data : listdetailProduct) {
                            new tCustomerBasedMobileDetailProductBL().deleteDataByProductId(data.get_intTrCustomerIdDetailProduct());
                        }
                    }
                }
            }
        }

        setTablePerson();
        LinearLayout lnTop = (LinearLayout) v.findViewById(R.id.linearLayoutTop);
        LinearLayout lnBottom = (LinearLayout) v.findViewById(R.id.linearLayoutBottom);


        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

        imgReadMore.setTag(1);
        imgReadMore.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
        row1.setVisibility(View.GONE);
        row2.setVisibility(View.GONE);
        row3.setVisibility(View.GONE);
        row4.setVisibility(View.GONE);
        row5.setVisibility(View.GONE);
        row6.setVisibility(View.GONE);

//                        if (isValidEmail(etEmail.getText().toString())) {
        TextView tvCode = (TextView) v.findViewById(R.id.tvCode);
        TextView tvNama = (TextView) v.findViewById(R.id.tvNamaPreview);
        TextView tvGender = (TextView) v.findViewById(R.id.tvJenisKelamin);
        TextView tvTanggalLahir = (TextView) v.findViewById(R.id.tvTanggalLahir);
        TextView tvTelp = (TextView) v.findViewById(R.id.tvTelpPreview);
        TextView tvTelp2 = (TextView) v.findViewById(R.id.tvTelpPreview2);
        TextView tvTelpKantor = (TextView) v.findViewById(R.id.tvTelpKantor);
        TextView tvAlamat = (TextView) v.findViewById(R.id.tvAlamatPreview);
        TextView tvEmail = (TextView) v.findViewById(R.id.tvEmailPreview);
        TextView tvPinBBM = (TextView) v.findViewById(R.id.tvPinBBM);

        radioGenderGroup = (RadioGroup) v.findViewById(R.id.radioGender);

        lnTop.setVisibility(View.GONE);
        lnBottom.setVisibility(View.VISIBLE);

        saveCustomerBaseHeader();

        String typeSubmission = new tCustomerBasedMobileHeaderBL().getDataByBitActive().get_txtSubmissionCode();

        tvCode.setText(String.format(": %s", spnSubmissionCode.getSelectedItem().toString()));
        tvNama.setText(String.format(": %s", etNama.getText().toString()));

        int selectedgender = radioGenderGroup.getCheckedRadioButtonId();
        radioGenderButton = (RadioButton) v.findViewById(selectedgender);
        tvGender.setText(String.format(": %s", radioGenderButton.getText()));

        tvTanggalLahir.setText(String.format(": %s", dtHeader.get_txtTglLahir()));
        tvTelp.setText(String.format(": %s", etTelpon.getText().toString()));
        tvTelp2.setText(String.format(": %s", etTelpon2.getText().toString()));
        tvTelpKantor.setText(String.format(": %s", etTelponKantor.getText().toString()));
        tvAlamat.setText(String.format(": %s", etAlamat.getText().toString()));
        tvEmail.setText(String.format(": %s", etEmail.getText().toString()));
        tvPinBBM.setText(String.format(": %s", etPinBBM.getText().toString()));
    }

    private void viewCustomerBaseFragment() {
        Intent intent = new Intent(getContext(), MainMenu.class);
        intent.putExtra("key_view", "View Customer Base");
        getActivity().finish();
        startActivity(intent);
    }

    private void saveCustomerBase() {
        Boolean status = new tCustomerBasedMobileHeaderBL().save(getContext());

        if (status) {
            new mTypeSubmissionMobileBL().updateLastSelected(dtHeader.get_txtSubmissionCode());
            viewCustomerBaseFragment();
        }
    }

    private void popUpAddProduct(final tCustomerBasedMobileDetailData dataDetail) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.popup_add_product, null);
        final TextView nama = (TextView) promptView.findViewById(R.id.add_nama);
        final Button btnAddProduct = (Button) promptView.findViewById(R.id.btn_add_product);
        final EditText qty = (EditText) promptView.findViewById(R.id.qty);
        final HashMap<String, String> HMProduct = new HashMap<>();
        final HashMap<String, String> HMProductKompetitor = new HashMap<>();
        final TextInputLayout txtInputLayoutQty = (TextInputLayout) promptView.findViewById(R.id.input_layout_qty);

        setTableProduct(dataDetail, promptView);

        final Spinner spnKalbeProduct = (Spinner) promptView.findViewById(R.id.spn_kn_product);
        final Spinner spnCompetProduct = (Spinner) promptView.findViewById(R.id.spn_cp_product);

        List<String> dataProductKalbe = new ArrayList<>();
        final List<String> dataProductKompetitor = new ArrayList<>();
        final List<mProductSPGData> mProductSPGDataList = new mProductSPGBL().GetDataByMasterId(HMSubmision.get(HMSubmision.get(spnSubmissionCode.getSelectedItem())));
        final List<mProductPICData> mProductPICDataList = new mProductPICBL().GetDataByMasterId(HMSubmision.get(HMSubmision.get(spnSubmissionCode.getSelectedItem())));

        if (dataDetail.get_intPIC().equals("1")) {
            if (mProductPICDataList.size() > 0) {
                dataProductKalbe.add("Select Product");
                for (mProductPICData dt : mProductPICDataList) {
                    dataProductKalbe.add(dt.get_txtProductBrandDetailGramName());
                    HMProduct.put(dt.get_txtProductBrandDetailGramName(), dt.get_txtBrandDetailGramCode());
                    HMProduct.put(dt.get_txtBrandDetailGramCode(), dt.get_txtProductDetailCode());
                    HMProduct.put(dt.get_txtProductDetailCode(), dt.get_txtLobName());
                }

            }
        } else {
            dataProductKalbe.add("Select Product");
            if (mProductSPGDataList.size() > 0) {
                for (mProductSPGData dt : mProductSPGDataList) {
                    dataProductKalbe.add(dt.get_txtProductBrandDetailGramName());
                    HMProduct.put(dt.get_txtProductBrandDetailGramName(), dt.get_txtBrandDetailGramCode());
                    HMProduct.put(dt.get_txtBrandDetailGramCode(), dt.get_txtProductDetailCode());
                    HMProduct.put(dt.get_txtProductDetailCode(), dt.get_txtLobName());
                }

            }
        }

        final List<String> lisAwalKompet = new ArrayList<>();
        lisAwalKompet.add("-");

        ArrayAdapter<String> adapterKalbeProduct = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, dataProductKalbe);
        final ArrayAdapter<String> adapterAwalKompet = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, lisAwalKompet);
        spnKalbeProduct.setAdapter(adapterKalbeProduct);
        spnCompetProduct.setAdapter(adapterAwalKompet);
        final int index = spnKalbeProduct.getAdapter().getCount() - 1;
        spnKalbeProduct.setSelection(0);
        spnKalbeProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (spnKalbeProduct.getSelectedItemPosition() > 0) {
                    HMProductKompetitor.clear();
                    dataProductKompetitor.clear();
                    String txtProductDetailCode;
                    if (dataDetail.get_intPIC().equals("1")) {
                        txtProductDetailCode = mProductPICDataList.get(position - 1).get_txtProductDetailCode();
                    } else {
                        txtProductDetailCode = mProductSPGDataList.get(position - 1).get_txtProductDetailCode();
                    }

                    List<mProductCompetitorData> listProductKompetitor = new mProductCompetitorBL().GetListDataByProductKN(txtProductDetailCode);

                    dataProductKompetitor.add("Select Previous Product");
                    if (listProductKompetitor.size() > 0) {
                        //dataProductKompetitor.add("Select Previous Product");
                        for (mProductCompetitorData dt : listProductKompetitor) {
                            dataProductKompetitor.add(dt.get_txtProdukKompetitorID());
                            HMProductKompetitor.put(dt.get_txtProdukKompetitorID(), dt.get_txtProdukKompetitorID());
                        }

                    }

                    ArrayAdapter<String> adapterKompetProduct = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dataProductKompetitor);
                    spnCompetProduct.setAdapter(adapterKompetProduct);
                    //final int index = spnCompetProduct.getAdapter().getCount()-1;
                    spnCompetProduct.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dtListDetailProduct = new tCustomerBasedMobileDetailProductBL().getDataByCustomerDetailId(txtDetailIDCustomerBased);
                boolean val = true;
                for (int i = 0; i < dtListDetailProduct.size(); i++) {
                    if (dtListDetailProduct.get(i).get_txtProductBrandName().equals(spnKalbeProduct.getSelectedItem().toString())) {
                        val = false;
                    }

                }

                if (!spnKalbeProduct.getSelectedItem().equals("Select Product")) {
                    if (val) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(qty.getWindowToken(), 0);
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String date = dateFormat.format(Calendar.getInstance().getTime());
                        String selectedOneKNProduct = spnKalbeProduct.getSelectedItem().toString();
                        String selectedOneCompetitorProduct = spnCompetProduct.getSelectedItem().toString();
                        tUserLoginData dtUser = new tUserLoginBL().getUserActive();
                        String qtyProduct;
                        qtyProduct = qty.getText().toString();
                        new clsMainActivity().removeErrorMessage(txtInputLayoutQty);
                        if (qtyProduct.length() == 0) {
                            new clsMainActivity().setErrorMessage(getContext(), txtInputLayoutQty, qty, "Qty cannot empty");
                            spnKalbeProduct.setSelection(spnKalbeProduct.getSelectedItemPosition());
                            spnCompetProduct.setSelection(spnCompetProduct.getSelectedItemPosition());
                        } else if (Integer.parseInt(qtyProduct) == 0) {
                            new clsMainActivity().setErrorMessage(getContext(), txtInputLayoutQty, qty, "Qty cannot 0");
                            spnKalbeProduct.setSelection(spnKalbeProduct.getSelectedItemPosition());
                            spnCompetProduct.setSelection(spnCompetProduct.getSelectedItemPosition());
                        } else {
                            new clsMainActivity().removeErrorMessage(txtInputLayoutQty);
                            tCustomerBasedMobileDetailProductData data = new tCustomerBasedMobileDetailProductData();
                            data.set_intTrCustomerIdDetailProduct(new clsMainActivity().GenerateGuid());
                            data.set_intTrCustomerIdDetail(dataDetail.get_intTrCustomerIdDetail());
                            data.set_txtProductBrandCode(HMProduct.get(selectedOneKNProduct));// brand name
                            data.set_txtProductBrandName(selectedOneKNProduct);
                            data.set_txtProductBrandQty(qtyProduct);
                            data.set_txtProductBrandCodeCRM(HMProduct.get(HMProduct.get(selectedOneKNProduct)));// brandcode
                            data.set_txtLOB(HMProduct.get(HMProduct.get(HMProduct.get(selectedOneKNProduct))));// brandcodeCRM
                            if (spnCompetProduct.getSelectedItem().equals("Select Previous Product")) {
                                data.set_txtProductCompetitorCode(null);
                                data.set_txtProductCompetitorName(null);
                            } else {
                                data.set_txtProductCompetitorCode(HMProductKompetitor.get(selectedOneCompetitorProduct));
                                data.set_txtProductCompetitorName(selectedOneCompetitorProduct);
                            }

                            data.set_dtInserted(date);
                            data.set_txtInsertedBy(dtUser.get_txtUserId());

                            new tCustomerBasedMobileDetailProductBL().saveData(data);

                            if (spnKalbeProduct.getSelectedItemPosition() > 0) {
                                spnCompetProduct.setAdapter(null);
                                spnKalbeProduct.setSelection(0);
                            }
                            qty.setText("");
                            spnKalbeProduct.setSelection(0);
                            spnCompetProduct.setAdapter(adapterAwalKompet);
                            setTableProduct(dataDetail, promptView);
                            //                setTablePerson();
                        }//validasi product kompetitor
                    } else {
                        new clsMainActivity().showCustomToast(getContext(), "Sorry, you cannot add same product", false);
                    }
                } else {
                    new clsMainActivity().showCustomToast(getContext(), "Please Choose Product first", false);
                }

            }
        });

        if (dataDetail.get_intTrCustomerIdDetail() != null) {
            nama.setText(dataDetail.get_txtNamaDepan());
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        setTablePerson();
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();

    }

    private void popUpAddPerson(final tCustomerBasedMobileDetailData dataDetail) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.popup_add_customerbase, null);

        final EditText nama = (EditText) promptView.findViewById(R.id.etNama);


        InputFilter filter2 = new InputFilter() {
            boolean canEnterSpace = false;

            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {

                if (nama.getText().toString().equals("")) {
                    canEnterSpace = false;
                }

                StringBuilder builder = new StringBuilder();

                for (int i = start; i < end; i++) {
                    char currentChar = source.charAt(i);

                    if (Character.isLetterOrDigit(currentChar) || currentChar == '_') {
                        builder.append(currentChar);
                        canEnterSpace = true;
                    }

                    if (Character.isWhitespace(currentChar) && canEnterSpace) {
                        builder.append(currentChar);
                    }

                }
                return builder.toString();
            }

        };

        InputFilter[] filters = {filter2, new InputFilter.AllCaps()};

        nama.setFilters(filters);

        final EditText usiaKehamilan = (EditText) promptView.findViewById(R.id.usiaKehamilan);
        final TextView tvNama = (TextView) promptView.findViewById(R.id.tvNamaPopUp);
        final TableRow trTanggalLahir = (TableRow) promptView.findViewById(R.id.row_tgl_lahir);
        final TextView tvTanggalLahir = (TextView) promptView.findViewById(R.id.tvTanggalLahir);
        final TextView tvJenisKelamin = (TextView) promptView.findViewById(R.id.tvJenisKelamin);
        final RadioGroup radioGroupGender = (RadioGroup) promptView.findViewById(R.id.radioGender);
        final TextInputLayout textInputLayoutNamaPopup = (TextInputLayout) promptView.findViewById(R.id.input_layout_nama);
        final TextInputLayout textInputLayoutAgePopup = (TextInputLayout) promptView.findViewById(R.id.input_layout_age_person);
        final DatePicker dp = (DatePicker) promptView.findViewById(R.id.dp_tgl_lahir);
        LinearLayout lnPic = (LinearLayout) promptView.findViewById(R.id.ln_pic);
        LinearLayout lnNama = (LinearLayout) promptView.findViewById(R.id.ln_nama);
        LinearLayout lnDp = (LinearLayout) promptView.findViewById(R.id.lnDp);
        LinearLayout lnJenisKelamin = (LinearLayout) promptView.findViewById(R.id.ln_jenis_kelamin);
        final LinearLayout lnHamil = (LinearLayout) promptView.findViewById(R.id.lnUsiaKehamilan);
        dp.setMaxDate(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        dp.updateDate(1900, 0, 1);

        final RadioGroup radioDateTypePerson = (RadioGroup) promptView.findViewById(R.id.radioDateTypePerson);
        final EditText etAgePerson = (EditText) promptView.findViewById(R.id.etAgePerson);

        if (dataDetail.get_intAgeTypeFlag() != null) {

            TextInputLayout tl_age = (TextInputLayout) promptView.findViewById(R.id.input_layout_age_person);

            if (dataDetail.get_intAgeTypeFlag().equals("Date")) {
                radioDateTypePerson.check(R.id.radioDate);

                if (dataDetail.get_txtTglLahir().equals("null")) {
                    dp.setMaxDate(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
                } else {

                    String stringDatedb = dataDetail.get_txtTglLahir();
                    String[] parts = stringDatedb.split("-");

                    int year = Integer.valueOf(parts[0]);
                    int month = Integer.valueOf(parts[1]) - 1;
                    int day = Integer.valueOf(parts[2]);

                    dp.updateDate(year, month, day);
                }
                etAgePerson.setText(dataDetail.get_intAge());
                dp.setVisibility(View.VISIBLE);
                tl_age.setVisibility(View.GONE);
            } else if (dataDetail.get_intAgeTypeFlag().equals("Age")) {
                radioDateTypePerson.check(R.id.radioAge);
                etAgePerson.setText(dataDetail.get_intAge());
                tl_age.setVisibility(View.VISIBLE);
                dp.setVisibility(View.GONE);
            }
        }

        radioDateTypePerson.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TextInputLayout tl_age = (TextInputLayout) promptView.findViewById(R.id.input_layout_age_person);

                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    if (checkedRadioButton.getText().equals("Date of Birth")) {
                        dp.setVisibility(View.VISIBLE);
                        tl_age.setVisibility(View.GONE);

                    } else if (checkedRadioButton.getText().equals("Age")) {
                        tl_age.setVisibility(View.VISIBLE);
                        dp.setVisibility(View.GONE);

                    }
                }
            }
        });

        modelItems = new ArrayList<>();

        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioGender = radioGroupGender.findViewById(checkedId);
                int index = radioGroupGender.indexOfChild(radioGender);

                switch (index) {
                    case 0:
                        lnHamil.setVisibility(View.GONE);
                        usiaKehamilan.setText(null);
                        break;
                    case 1:
                        lnHamil.setVisibility(View.VISIBLE);
                        usiaKehamilan.setText(dataDetail.get_txtUsiaKehamilan());
                        break;
                }
            }
        });

        if (dataDetail.get_intTrCustomerIdDetail() != null) {
            nama.setText(dataDetail.get_txtNamaDepan());
            usiaKehamilan.setText(dataDetail.get_txtUsiaKehamilan());

            int year;
            int month;
            int day;
            String part1 = "";
            String part2 = "";
            String part3 = "";
            String stringDatedb = dataDetail.get_txtTglLahir();
            if (!stringDatedb.equals("null")) {
                String[] parts = stringDatedb.split("-");
                part1 = parts[0]; //year
                part2 = parts[1]; //month
                part3 = parts[2]; //date
            }
            if (dataDetail.get_intPIC() != null && dataDetail.get_intPIC().equals("1")) {
                lnPic.setVisibility(View.VISIBLE);
                lnNama.setVisibility(View.GONE);
                lnDp.setVisibility(View.GONE);
                lnJenisKelamin.setVisibility(View.GONE);

                tvNama.setText(String.format(": %s", dataDetail.get_txtNamaDepan()));

                clsMainActivity clsMainMonth = new clsMainActivity();

                if (!stringDatedb.equals("null")) {
                    String month2 = clsMainMonth.months[Integer.parseInt(part2)];
                    tvTanggalLahir.setText(": " + part3 + " - " + month2 + " - " + part1);
                    tvTanggalLahir.setVisibility(View.VISIBLE);
                } else if (stringDatedb.equals("null")) {
                    lnDp.setVisibility(View.VISIBLE);
//                    dp.setMaxDate(System.currentTimeMillis());
                    trTanggalLahir.setVisibility(View.GONE);
                }

                String gender = dataDetail.get_txtGender().equals("Laki-laki") ? "Male" : "Female";

                tvJenisKelamin.setText(String.format(": %s", gender));
                if (dataDetail.get_txtGender().equals("Perempuan")) {
                    lnHamil.setVisibility(View.VISIBLE);
                    usiaKehamilan.setText(dataDetail.get_txtUsiaKehamilan());
                }

            } else {
                if (dataDetail.get_txtTglLahir().equals("null")) {
                    dp.setMaxDate(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
                } else {
                    year = Integer.valueOf(part1);
                    month = Integer.valueOf(part2) - 1;
                    day = Integer.valueOf(part3);

                    dp.updateDate(year, month, day);
                }
            }

            final RadioButton rbLaki = (RadioButton) promptView.findViewById(R.id.radioMale);
            RadioButton rbPerempuan = (RadioButton) promptView.findViewById(R.id.radioFemale);

            if (dataDetail.get_txtGender().equals("Perempuan")) {
                rbLaki.setChecked(false);
                rbPerempuan.setChecked(true);


            } else {
                rbLaki.setChecked(true);
                rbPerempuan.setChecked(false);

            }

            if (dataDetail.get_intPIC().equals("1")) {
                nama.setEnabled(false);
                rbLaki.setEnabled(false);
                rbPerempuan.setEnabled(false);
            }
        }
        Collections.sort(modelItems, ModelListview.StuRollno);
        dataAdapter = new MyAdapter(getActivity().getApplicationContext(), modelItems);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptView);
        if (dataDetail.get_intPIC() != null && dataDetail.get_intPIC().equals("1")) {
            alertDialogBuilder.setCancelable(false)
                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    })
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
        } else {
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    })
                    .setNegativeButton("Close",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
        }

        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();

        new clsMainActivity().removeErrorMessage(textInputLayoutNamaPopup);

        alertD.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nama.getText().toString().equals("") && nama.getText().length() >= 2) {
                    Boolean status = true;
                    new clsMainActivity().removeErrorMessage(textInputLayoutNamaPopup);
                    new clsMainActivity().removeErrorMessage(textInputLayoutAgePopup);

                    int index = radioDateTypePerson.indexOfChild(promptView.findViewById(radioDateTypePerson.getCheckedRadioButtonId()));

                    if (index == 0) {

                        int day = dp.getDayOfMonth();
                        int month = dp.getMonth() + 1;
                        int year = dp.getYear();

                        String tgl = String.valueOf(day) + "-" + String.valueOf(month) + "-" + String.valueOf(year);

                        if (!tgl.equals("")) {
                            DateFormat oriForm = new SimpleDateFormat("dd - MMMM - yyyy", Locale.ENGLISH);
                            DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = null;

                            day = dp.getDayOfMonth();
                            month = dp.getMonth();
                            year = dp.getYear();

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, month, day);

                            String tglLahir = targetFormat.format(calendar.getTime());

                            if (tglLahir.equals("1900-01-01")) {
                                new clsMainActivity().showCustomToast(getContext(), "Date not valid", false);
                                status = false;
                            } else {
                                try {
                                    date = new SimpleDateFormat("yyyy-MM-dd").parse(tglLahir);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                Calendar c = Calendar.getInstance();
                                int lYear = c.get(Calendar.YEAR);
                                int lMonth = c.get(Calendar.MONTH) + 1;
                                int lDay = c.get(Calendar.DATE);
                                String dateNow = lYear + "-" + lMonth + "-" + lDay;
                                if (dateNow.equals(tglLahir)) {
                                    tglLahir = "1900-01-01";
                                }
                                dataDetail.set_txtTglLahir(tglLahir);

                                Date now = new Date();
                                long timeBetween = 0;
                                if (date != null) {
                                    timeBetween = now.getTime() - date.getTime();
                                }
                                double yearsBetween = timeBetween / 3.156e+10;
                                int age = (int) Math.floor(yearsBetween);

                                dataDetail.set_intAge(String.valueOf(age));
                                dataDetail.set_intAgeTypeFlag("Date");
                            }
                        }
                    } else {
                        if (etAgePerson.getText().toString().length() != 0) {
                            int age = 0;
                            try {
                                age = Integer.parseInt(etAgePerson.getText().toString());
                                if (age > 0) {
                                    dataDetail.set_intAge(String.valueOf(etAgePerson.getText()));
                                    Calendar cala = Calendar.getInstance();
                                    cala.set(cala.get(Calendar.YEAR) - Integer.parseInt(dataDetail.get_intAge()), Calendar.JUNE, 30);

                                    DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    String tglLahir = targetFormat.format(cala.getTime());

                                    dataDetail.set_txtTglLahir(tglLahir);
                                    dataDetail.set_intAgeTypeFlag("Age");
                                } else {
                                    new clsMainActivity().setErrorMessage(getContext(), textInputLayoutAgePopup, etAgePerson, "Age not valid");
                                    status = false;
                                }
                            } catch (Exception ignored) {

                            }
                        } else {
                            new clsMainActivity().setErrorMessage(getContext(), textInputLayoutAgePopup, etAgePerson, "Age not valid");
                            status = false;
                        }
                    }
                    if (usiaKehamilan.getText().length() > 0 && status) {
                        int intUsiaKehamilan = Integer.valueOf(usiaKehamilan.getText().toString());
                        if (intUsiaKehamilan > 40) {
                            status = false;
                            new clsMainActivity().showCustomToast(getContext(), "Minimum Age should be less than 40 weeks", false);
                        }
                    }
                    if (status) {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Calendar cal = Calendar.getInstance();

                        tCustomerBasedMobileDetailData data = dataDetail;

                        if (data.get_intTrCustomerIdDetail() == null) {
                            data = new tCustomerBasedMobileDetailData();
                            data.set_intTrCustomerIdDetail(new clsMainActivity().GenerateGuid());
                            if (dataDetail.get_intPIC() == null) {
                                data.set_intPIC("0");
                            } else {
                                data.set_intPIC("1");
                            }

                            data.set_intNo(String.valueOf(dtListDetail.size() + 1));
                        }
                        int day = dp.getDayOfMonth();
                        int month = dp.getMonth() + 1;
                        int year = dp.getYear();

                        data.set_intTrCustomerId(dtHeader.get_intTrCustomerId());
                        data.set_txtNamaDepan(nama.getText().toString());
                        data.set_txtUsiaKehamilan(usiaKehamilan.getText().toString());
                        data.set_bitActive("1");
                        data.set_dtInserted(dateFormat.format(cal.getTime()));
                        data.set_txtTglLahir(dataDetail.get_txtTglLahir());
                        data.set_intAge(dataDetail.get_intAge());
                        data.set_intAgeTypeFlag(dataDetail.get_intAgeTypeFlag());

                        int selectedId = radioGroupGender.getCheckedRadioButtonId();
                        RadioButton rbGender = (RadioButton) promptView.findViewById(selectedId);

                        String gender = rbGender.getText().toString().equals("Male") ? "Laki-laki" : "Perempuan";

                        data.set_txtGender(gender);
                        dtDetail.add(data);
                        new tCustomerBasedMobileDetailBL().saveData(data);
                        for (int i = 0; i < modelItems.size(); i++) {
                            if (modelItems.get(i).is_selected()) {
                                tCustomerBasedMobileDetailProductData dtProduct = new tCustomerBasedMobileDetailProductData();
                                dtProduct.set_intTrCustomerIdDetailProduct(new clsMainActivity().GenerateGuid());
                                dtProduct.set_intTrCustomerIdDetail(data.get_intTrCustomerIdDetail());
                                dtProduct.set_txtProductBrandCode(modelItems.get(i).get_id());
                                dtProduct.set_txtProductBrandName(modelItems.get(i).get_name());
                                dtProduct.set_bitActive("1");
                                dtProduct.set_txtProductBrandQty(String.valueOf(modelItems.get(i).get_value()));
                                dtProduct.set_dtInserted(dateFormat.format(cal.getTime()));

                                new tCustomerBasedMobileDetailProductBL().saveData(dtProduct);
                            }
                        }
                        alertD.dismiss();
                        setTablePerson();
                    }
                } else {
                    if (nama.getText().toString().equals("")) {
                        new clsMainActivity().showCustomToast(getContext(), "Name cannot empty", false);
                        //new clsMainActivity().setErrorMessage(getContext(), textInputLayoutNamaPopup, nama, "Name cannot empty");
                    } else if (nama.getText().length() <= 2) {
                        new clsMainActivity().showCustomToast(getContext(), "Name must more than 1 characters", false);
                        //new clsMainActivity().setErrorMessage(getContext(), textInputLayoutNamaPopup, nama, "Name must more than 1 characters");
                    }
                }
            }
        });
    }

    private void setTablePerson() {
        sv = (ScrollView) v.findViewById(R.id.scroll);
        sv.setFillViewport(true);
        dtListDetail = new tCustomerBasedMobileDetailBL().getAllDataByHeaderId(dtHeader.get_intTrCustomerId());

        clsSwipeList swplist;

        swipeList.clear();
        Calendar c = Calendar.getInstance();
        int lYear = c.get(Calendar.YEAR);
        int lMonth = c.get(Calendar.MONTH) + 1;
        int lDay = c.get(Calendar.DATE);
        String dateNow = Integer.valueOf(lYear) + "-" + Integer.valueOf(lMonth) + "-" + Integer.valueOf(lDay);

        int totalProduct = 0;
        for (int i = 0; i < dtListDetail.size(); i++) {
            List<tCustomerBasedMobileDetailProductData> dtListProduct = new tCustomerBasedMobileDetailProductBL().getDataByCustomerDetailId(dtListDetail.get(i).get_intTrCustomerIdDetail());

            if (dtListProduct == null) {
                totalProduct = 0;
            } else {
                totalProduct = dtListProduct.size();
            }
            swplist = new clsSwipeList();

            String PIC;

            if (dtListDetail.get(i).get_intPIC().equals("1")) PIC = " (PIC)";
            else PIC = "";

            String gender = dtListDetail.get(i).get_txtGender().equals("Laki-laki") ? "Male" : "Female";

            swplist.set_txtTitle("Name             : " + dtListDetail.get(i).get_txtNamaDepan() + PIC);
            swplist.set_txtDescription("Gender           : " + gender);
            String tgl_lahir = dtListDetail.get(i).get_txtTglLahir();
            if (tgl_lahir.equals(dateNow) || tgl_lahir.equals("null") || tgl_lahir.equals("")) {
                tgl_lahir = "not set";
            } else {
                SimpleDateFormat dp = new SimpleDateFormat("yyyy-MMMM-dd");
                clsMainActivity clsMainMonth = new clsMainActivity();
                String[] parts = tgl_lahir.split("-");
                String part1 = parts[0]; //year
                String part2 = parts[1]; //month
                String part3 = parts[2]; //date

                String month = clsMainMonth.months[Integer.parseInt(part2)];
                tgl_lahir = part3 + " - " + month + " - " + part1;
            }
            swplist.set_txtDescription2("Date of Birth : " + tgl_lahir);
            swplist.set_txtDescription3("Total Product : " + String.valueOf(totalProduct));
            if (gender.equals("Female")) {
                swplist.set_txtDescription3(swplist.get_txtDescription3() + "\nPregnant Period: " + dtListDetail.get(i).get_txtUsiaKehamilan());
            }
            swipeList.add(swplist);
        }


        btnSave.setVisibility(View.GONE);
        if (dtListDetail != null) {
            if (dtListDetail.size() > 0) {
                btnSave.setVisibility(View.VISIBLE);
            }
            int index = 1;
            for (tCustomerBasedMobileDetailData data : dtListDetail) {
                String id = data.get_intTrCustomerIdDetail();
                data.set_intNo(String.valueOf(index));
                new tCustomerBasedMobileDetailBL().updateDataValueById(data, id);
                index++;
            }
        }

        clsMainActivity clsMain = new clsMainActivity();

        PullToRefreshSwipeMenuListView mListView = (PullToRefreshSwipeMenuListView) v.findViewById(R.id.listView);
        AppAdapter mAdapter = clsMain.setList2(getActivity().getApplicationContext(), swipeList);
        mListView.setAdapter(mAdapter);
        mListView.setPullRefreshEnable(false);

//        mListView.setXListViewListener(this);
        mHandler = new Handler();

        HashMap<String, String> mapEdit = new HashMap<String, String>();
        HashMap<String, String> mapDelete = new HashMap<String, String>();
        HashMap<String, String> mapAdd = new HashMap<String, String>();

        mapAdd.put("name", "AddProduct");
        mapAdd.put("bgColor", "#27ae60");

        mapEdit.put("name", "Edit");
        mapEdit.put("bgColor", "#2980b9");

        mapDelete.put("name", "Delete");
        mapDelete.put("bgColor", "#c0392b");

        Map<String, HashMap> mapMenu = new HashMap<>();
        mapMenu.put("0", mapAdd);
        mapMenu.put("1", mapEdit);
        mapMenu.put("2", mapDelete);

        SwipeMenuCreator creator = clsMain.setCreator(getActivity().getApplicationContext(), mapMenu);
        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        addList(getActivity().getApplicationContext(), position);
                        break;
                    case 1:
                        editList(getActivity().getApplicationContext(), position);
                        break;
                    case 2:
                        final int intPosition = position;
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Warning");
                        builder.setMessage("Are Yo Sure Delete This Item?");

                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteList(getActivity().getApplicationContext(), intPosition);
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //spnSubmissionCode.setSelection();
                            }
                        });

                        final AlertDialog alert = builder.create();
                        alert.show();

                }
            }
        });

    }

    private void setTableProduct(final tCustomerBasedMobileDetailData _tCustomerBasedMobileDetailData, final View v) {

        dtListDetailProduct = new tCustomerBasedMobileDetailProductBL().getDataByCustomerDetailId(_tCustomerBasedMobileDetailData.get_intTrCustomerIdDetail());
        txtDetailIDCustomerBased = _tCustomerBasedMobileDetailData.get_intTrCustomerIdDetail();
        clsSwipeList swplist;

        swipeListProduct.clear();

        for (int i = 0; i < dtListDetailProduct.size(); i++) {
            swplist = new clsSwipeList();
            swplist.set_txtId(dtListDetailProduct.get(i).get_intTrCustomerIdDetailProduct());
            swplist.set_txtTitle(dtListDetailProduct.get(i).get_txtProductBrandName());
            if (dtListDetailProduct.get(i).get_txtProductCompetitorName().equals("null")) {
                swplist.set_txtDescription("Not set");
            } else {
                swplist.set_txtDescription(dtListDetailProduct.get(i).get_txtProductCompetitorName());
            }
            swplist.set_intPIC(dtListDetailProduct.get(i).get_txtProductBrandQty());
            swipeListProduct.add(swplist);
        }

        AdapterProduct = clsMainActivity.setListProductCusBased(getActivity().getApplicationContext(), swipeListProduct);

        lvProduct = (ListView) v.findViewById(R.id.listViewProduct);
        lvProduct.setAdapter(AdapterProduct);
        lvProduct.setEmptyView(v.findViewById(R.id.LayoutEmpty));

    }

    public final boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void saveCustomerBaseHeader() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        if (dtHeader.get_intTrCustomerId() == null) {
            dtHeader = new tCustomerBasedMobileHeaderData();
            dtHeader.set_intTrCustomerId(new clsMainActivity().GenerateGuid());
        }

        visitplanAbsenData _viAbsenData = new visitplanAbsenData();
        _viAbsenData = new clsHelperBL().getDataCheckInActive();

        dtHeader.set_txtBranchCode(_viAbsenData.get_txtBranchCode());
        dtHeader.set_txtSumberData(_viAbsenData.get_txtOutletCode());
        dtHeader.set_txtNamaSumberData(_viAbsenData.get_txtOutletName());
        dtHeader.set_txtNamaDepan(etNama.getText().toString());
        dtHeader.set_txtTelp(etTelpon.getText().toString());
        dtHeader.set_txtTelp2(etTelpon2.getText().toString());
        dtHeader.set_txtTelpKantor(etTelponKantor.getText().toString());
        dtHeader.set_txtEmail(etEmail.getText().toString());
        dtHeader.set_txtPINBBM(etPinBBM.getText().toString());
        dtHeader.set_txtALamat(etAlamat.getText().toString());
        dtHeader.set_txtSubmissionId(etCustomerBasedNo.getText().toString());
        dtHeader.set_txtSubmissionCode(HMSubmision.get(spnSubmissionCode.getSelectedItem().toString()));
        dtHeader.set_txtUserId(new tUserLoginBL().getUserLogin().get_TxtEmpId());

        int day = dpHeader.getDayOfMonth();
        int month = dpHeader.getMonth() + 1;
        int year = dpHeader.getYear();

        String tgl = etTglLhr.getText().toString();

        String tglLahir = "1900-01-01";

        int index = radioDateType.indexOfChild(v.findViewById(radioDateType.getCheckedRadioButtonId()));

        if (index == 0) {
            if (!tgl.equals("")) {
                DateFormat oriForm = new SimpleDateFormat("dd - MMMM - yyyy", Locale.ENGLISH);
                DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = oriForm.parse(tgl);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tglLahir = targetFormat.format(date);

                Calendar c = Calendar.getInstance();
                int lYear = c.get(Calendar.YEAR);
                int lMonth = c.get(Calendar.MONTH) + 1;
                int lDay = c.get(Calendar.DATE);
                String dateNow = lYear + "-" + lMonth + "-" + lDay;
                if (dateNow.equals(tglLahir)) {
                    //tglLahir = null;
                    tglLahir = "1900-01-01";
                }
                dtHeader.set_txtTglLahir(tglLahir);

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date now = new Date();
                try {
                    now = format.parse(new tUserLoginBL().getUserLogin().get_dtLastLogin());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long timeBetween = 0;
                if (date != null) {
                    timeBetween = now.getTime() - date.getTime();
                }
                double yearsBetween = timeBetween / 3.156e+10;
                int age = (int) Math.floor(yearsBetween);

                dtHeader.set_intAge(String.valueOf(age));
                dtHeader.set_intAgeTypeFlag("Date");
            }
        } else {
            if ((String.valueOf(etAge.getText()).equals(""))) {
                dtHeader.set_intAge("null");
            } else {
                dtHeader.set_intAge(String.valueOf(etAge.getText()));
            }

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date now = new Date();
            try {
                now = format.parse(new tUserLoginBL().getUserLogin().get_dtLastLogin());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (dtHeader.get_intAge().equals("0")) {
                dtHeader.set_intAge("null");
            }
            if (!dtHeader.get_intAge().equals("null")) {
                Calendar cala = Calendar.getInstance();
                cala.setTime(now);
                cala.set(cala.get(Calendar.YEAR) - Integer.parseInt(dtHeader.get_intAge()), Calendar.JUNE, 30);

                DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
                tglLahir = targetFormat.format(cala.getTime());

                dtHeader.set_txtTglLahir(tglLahir);
            } else {
                //dtHeader.set_txtTglLahir("null");
                dtHeader.set_txtTglLahir("1900-01-01");
            }
            dtHeader.set_intAgeTypeFlag("Age");
        }

        int selectedId = radioGenderGroup.getCheckedRadioButtonId();
        RadioButton rbGender = (RadioButton) v.findViewById(selectedId);

        String genderFinal = rbGender.getText().toString().equals("Male") ? "Laki-laki" : "Perempuan";
        dtHeader.set_txtGender(genderFinal);

        if (cbPIC.isChecked()) {
            tCustomerBasedMobileDetailData data = new tCustomerBasedMobileDetailBL().getAllDataByHeaderIdandintPIC(dtHeader.get_intTrCustomerId());
            dtHeader.set_intPIC("1");

            if (data.get_intTrCustomerId() == null) {
                data = new tCustomerBasedMobileDetailData();
                data.set_intTrCustomerIdDetail(new clsMainActivity().GenerateGuid());
                data.set_txtUsiaKehamilan("");
            }

            if (dtHeader.get_txtGender().equalsIgnoreCase("Laki-Laki")) {
                data.set_txtUsiaKehamilan("");
            }

            data.set_intTrCustomerId(dtHeader.get_intTrCustomerId());
            data.set_txtNamaDepan(dtHeader.get_txtNamaDepan());
            data.set_intNo(String.valueOf(dtDetail.size() + 1));
            data.set_intPIC("1");
            data.set_bitActive("0");
            data.set_dtInserted(dateFormat.format(cal.getTime()));
            data.set_txtGender(genderFinal);
            if (tglLahir == null) {
                data.set_txtTglLahir("1900-01-01");
            } else {
                data.set_txtTglLahir(tglLahir);
            }

            data.set_intAge(dtHeader.get_intAge());
            data.set_intAgeTypeFlag(dtHeader.get_intAgeTypeFlag());

            dtDetail.add(data);

            new tCustomerBasedMobileDetailBL().saveData(data);

        } else {
            dtHeader.set_intPIC("0");
            tCustomerBasedMobileDetailData data = new tCustomerBasedMobileDetailBL().getAllDataByHeaderIdandintPIC(dtHeader.get_intTrCustomerId());

            if (data != null) {
                new tCustomerBasedMobileDetailBL().deleteData(data);
            }
        }
        dtHeader.set_txtTglLahir(tglLahir);
        dtHeader.set_txtDeviceId(_viAbsenData.get_txtDeviceId());
        dtHeader.set_bitActive("0");
        dtHeader.set_dtDate(dateFormat.format(cal.getTime()));
        dtHeader.set_intSubmit("0");
        dtHeader.set_intSync("0");
        new tCustomerBasedMobileHeaderBL().saveData(dtHeader);

        setTablePerson();
    }

    private void onLoad() {
//        mListView.setRefreshTime(RefreshTime.getRefreshTime(getActivity().getApplicationContext()));
//        mListView.stopRefresh();
//
//        mListView.stopLoadMore();

    }

    @Override
    public void onRefresh() {

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

    private class MyAdapter extends BaseAdapter implements Filterable {

        private ArrayList<ModelListview> mOriginalValues; // Original Values
        private ArrayList<ModelListview> mDisplayedValues;    // Values to be displayed

        MyAdapter(Context context, ArrayList<ModelListview> mProductArrayList) {
            this.mOriginalValues = mProductArrayList;
            this.mDisplayedValues = mProductArrayList;
        }

        @Override
        public int getCount() {
            return mDisplayedValues.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
            EditText values;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {

                LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                convertView = vi.inflate(R.layout.custom_listitem, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.textView1);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                holder.values = (EditText) convertView.findViewById(R.id.editText4);

                convertView.setTag(holder);

                final ViewHolder finalHolder1 = holder;
                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        finalHolder1.values.requestFocus();
                        ModelListview _state = (ModelListview) cb.getTag();
                        _state.set_selected(cb.isChecked());
                        //moveCursor();
                        for (int i = 0; i < mOriginalValues.size(); i++) {
                            if (cb.getText().equals(mOriginalValues.get(i).get_name())) {
                                mOriginalValues.get(i).set_selected(cb.isChecked());
                                break;
                            }
                        }
                    }
                });

                holder.values.setSelectAllOnFocus(true);

                final ViewHolder finalHolder = holder;
                final ViewHolder finalHolder2 = holder;
                holder.values.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        EditText et = finalHolder.values;
                        for (int i = 0; i < mOriginalValues.size(); i++) {
                            if (finalHolder.name.getText().equals(mOriginalValues.get(i).get_name())) {
                                if (s.length() == 0) {
                                    mOriginalValues.get(i).set_value(0);
                                    break;

                                } else {
                                    mOriginalValues.get(i).set_value(Integer.parseInt(et.getText().toString()));

                                    if (!s.toString().equals("0")) {
                                        mOriginalValues.get(i).set_selected(true);
                                        finalHolder2.name.setChecked(true);
                                    }
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {


                    }
                });

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ModelListview state = mDisplayedValues.get(position);

            holder.name.setText(state.get_name());
            holder.name.setChecked(state.isSelected());
            holder.values.setText(String.valueOf(state.get_value()));

            holder.name.setTag(state);

            return convertView;
        }

        @Override
        public Filter getFilter() {
            return new Filter() {

                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    mDisplayedValues = (ArrayList<ModelListview>) results.values; // has the filtered values
                    notifyDataSetChanged();  // notifies the data with new filtered values
//                    setListViewHeightBasedOnItems(listView);
                }

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                    ArrayList<ModelListview> FilteredArrList = new ArrayList<>();

                    if (mOriginalValues == null) {
                        mOriginalValues = new ArrayList<>(mDisplayedValues); // saves the original data in mOriginalValues
                    }

                    if (constraint == null || constraint.length() == 0) {

                        // set the Original result to return
                        results.count = mOriginalValues.size();
                        results.values = mOriginalValues;
                    } else {
                        constraint = constraint.toString().toLowerCase();
                        for (int i = 0; i < mOriginalValues.size(); i++) {
                            String data = mOriginalValues.get(i).get_name();
                            if (data.toLowerCase().startsWith(constraint.toString())) {
                                ModelListview dt = new ModelListview();
                                dt.set_id(mOriginalValues.get(i).get_id());
                                dt.set_name(mOriginalValues.get(i).get_name());
                                dt.set_value(mOriginalValues.get(i).get_value());
                                dt.set_selected(mOriginalValues.get(i).isSelected());
                                FilteredArrList.add(dt);
                            }
                        }
                        // set the Filtered result to return
                        results.count = FilteredArrList.size();
                        results.values = FilteredArrList;
                    }
                    return results;
                }
            };
        }
    }

    private void editList(Context ctx, int position) {
        popUpAddPerson(dtListDetail.get(position));
    }

    private void addList(Context ctx, int position) {
        popUpAddProduct(dtListDetail.get(position));
    }

    private void deleteList(Context ctx, int position) {
        tCustomerBasedMobileDetailData dtDetail = dtListDetail.get(position);

        if (dtDetail.get_intPIC().equals("1")) {
            new clsMainActivity().showCustomToast(getContext(), "PIC cannot deleted", false);
        } else {
            new tCustomerBasedMobileDetailProductBL().deleteData(dtDetail.get_intTrCustomerIdDetail());
            new tCustomerBasedMobileDetailBL().deleteData(dtDetail);
            setTablePerson();
        }
    }

    private boolean validateHeader() {

        String notelp = etTelpon.getText().toString();
        String notelp2 = etTelpon2.getText().toString();
        String notelpkantor = etTelponKantor.getText().toString();
        String firstNotelp = "";
        String secondNotelp = "";

        if (notelp.length() > 0) {
            firstNotelp = notelp.substring(0, 1);
        }
        if (notelp2.length() > 0) {
            secondNotelp = notelp2.substring(0, 1);
        }

        String firstNotelpkantor = null;

        if (notelpkantor.length() > 0) {
            firstNotelpkantor = notelpkantor.substring(0, 1);
        }


        boolean validate = true;
        new clsMainActivity().removeErrorMessage(textInputLayoutTglLahir);
        new clsMainActivity().removeErrorMessage(textInputLayoutAge);
        new clsMainActivity().removeErrorMessage(textInputLayoutNama);
        new clsMainActivity().removeErrorMessage(textInputLayoutTelp);
        new clsMainActivity().removeErrorMessage(textInputLayoutTelp2);
        new clsMainActivity().removeErrorMessage(textInputLayoutTelpKantor);
        new clsMainActivity().removeErrorMessage(textInputLayoutEmail);

        if (etNama.getText().toString().equals("")) {
            new clsMainActivity().setErrorMessage(getContext(), textInputLayoutNama, etNama, "Name cannot empty");
            validate = false;
        } else if (etNama.getText().length() <= 1) {
            new clsMainActivity().setErrorMessage(getContext(), textInputLayoutNama, etNama, "Name must more than 1 characters");
            validate = false;
        } else if (etNama.getText().toString().startsWith(" ") || etNama.getText().toString().endsWith(" ")) {
            etNama.setText(etNama.getText().toString().trim());
            if (etNama.getText().length() <= 1) {
                new clsMainActivity().setErrorMessage(getContext(), textInputLayoutNama, etNama, "Name must more than 1 characters");
                validate = false;
            }
        }

        if (cbPIC.isChecked()) {
            int index = radioDateType.indexOfChild(v.findViewById(radioDateType.getCheckedRadioButtonId()));
            if (index == 0 && etTglLhr.getText().toString().equals("")) {
                validate = false;
                new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTglLahir, etTglLhr, "PIC must set PIC Date of Birth");
                etTglLhr.setFocusable(true);
            } else if (index == 0 && etTglLhr.getText().toString().equals("1 - January - 1900")) {
                validate = false;
                new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTglLahir, etTglLhr, "Date not valid");
                etTglLhr.setFocusable(true);
            } else if (index == 1 && etAge.getText().toString().equals("")) {
                validate = false;
                new clsMainActivity().setErrorMessage(getContext(), textInputLayoutAge, etAge, "PIC must set PIC Age");
                etAge.setFocusable(true);
            } else if(index == 1 && !etAge.toString().equals("")){
                try{
                    Integer.parseInt(etAge.getText().toString());
                }
                catch (Exception ex){
                    validate = false;
                    new clsMainActivity().setErrorMessage(getContext(), textInputLayoutAge, etAge, "Age must a number");
                    etAge.setFocusable(true);
                }
            }
        }

        if (etTelpon.getText().toString().equals("")) {
            new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelp, etTelpon, "Phones cannot empty");
            validate = false;
        } else if (!firstNotelp.equals("0")) {
            new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelp, etTelpon, "Telephone number beginning with the digits 0");
            validate = false;
        } else if (firstNotelp.equals("0")) {
            if (notelp.length() < 8) {
                new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelp, etTelpon, "telephone number at least 8 digits");
                validate = false;
            } else if (notelp.length() > 15) {
                new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelp, etTelpon, "telephone number maximum 15 digits");
                validate = false;
            }
        }

        if (!etTelpon2.getText().toString().equals("")) {
            if (!secondNotelp.equals("0")) {
                new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelp2, etTelpon2, "Telephone number beginning with the digits 0");
                validate = false;
            } else if (secondNotelp.equals("0")) {
                if (notelp2.length() < 8) {
                    new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelp2, etTelpon2, "telephone number at least 8 digits");
                    validate = false;
                } else if (notelp2.length() > 15) {
                    new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelp2, etTelpon2, "telephone number maximum 15 digits");
                    validate = false;
                } else if (notelp2.equals(notelp)) {
                    new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelp2, etTelpon2, "Second phone number must different with first phone number");
                    validate = false;
                }
            }
        }

        if (etEmail.getText().length() > 0) {
            if (!isValidEmail(etEmail.getText().toString())) {
                new clsMainActivity().setErrorMessage(getContext(), textInputLayoutEmail, etEmail, "Email not valid");
                validate = false;
            }
        }

        if (firstNotelpkantor != null && !firstNotelpkantor.equals("0")) {
            new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelpKantor, etTelponKantor, "Telephone number beginning with the digits 0");
            validate = false;
        } else if (firstNotelpkantor != null && firstNotelpkantor.equals("0")) {
            if (notelpkantor.length() < 8) {
                new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelpKantor, etTelponKantor, "telephone number at least 8 digits");
                validate = false;
            } else if (notelpkantor.length() > 15) {
                new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelpKantor, etTelponKantor, "telephone number maximum 15 digits");
                validate = false;
            }
        }

        return validate;
    }
}
