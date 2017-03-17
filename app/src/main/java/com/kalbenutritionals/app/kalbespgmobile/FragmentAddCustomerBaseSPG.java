package com.kalbenutritionals.app.kalbespgmobile;

import android.graphics.Rect;
import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bl.mEmployeeSalesProductBL;
import bl.tAbsenUserBL;
import bl.tCustomerBasedMobileDetailBL;
import bl.tCustomerBasedMobileDetailProductBL;
import bl.tCustomerBasedMobileHeaderBL;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;
import library.salesforce.common.AppAdapter;
import library.salesforce.common.ModelListview;
import library.salesforce.common.clsSwipeList;
import library.salesforce.common.mEmployeeSalesProductData;
import library.salesforce.common.tCustomerBasedMobileDetailData;
import library.salesforce.common.tCustomerBasedMobileDetailProductData;
import library.salesforce.common.tCustomerBasedMobileHeaderData;

public class FragmentAddCustomerBaseSPG extends Fragment implements View.OnClickListener, IXListViewListener {
    private ArrayList<ModelListview> modelItems;
    private List<tCustomerBasedMobileDetailData> dtDetail;
    tCustomerBasedMobileHeaderData dtHeader;
    ListView listView;
    MyAdapter dataAdapter;

    EditText etCustomerBasedNo, etEmail, etNama, etTelpon, etAlamat, etTelponKantor, etPinBBM, etTelpon2;
    TextInputLayout textInputLayoutNama, textInputLayoutTelp, textInputLayoutTelp2, textInputLayoutTelpKantor, textInputLayoutEmail;

    CheckBox cbPIC;
    RadioGroup radioGenderGroup;
    Button btnSave;
    View v;

    List<tCustomerBasedMobileDetailData> dtListDetail;

    private static List<clsSwipeList> swipeList = new ArrayList<clsSwipeList>();
    private AppAdapter mAdapter;
    private PullToRefreshSwipeMenuListView mListView;
    private Handler mHandler;
    private static Map<String, HashMap> mapMenu;

    static List<tCustomerBasedMobileHeaderData> dt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dtDetail = new ArrayList<>();
        v = inflater.inflate(R.layout.fragment_customerbase_add, container, false);
        etCustomerBasedNo = (EditText) v.findViewById(R.id.etCustomerBasedNo);
        etAlamat = (EditText) v.findViewById(R.id.etAlamat);

        textInputLayoutNama = (TextInputLayout) v.findViewById(R.id.input_layout_nama);
        etNama = (EditText) v.findViewById(R.id.etNama);

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
                if (!etTelpon.getText().equals("")) {
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", etTelpon.getText().toString(), null)));
                }
                return true;
            }
        });

        etTelpon2.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etTelpon2) {
            public boolean onDrawableClick() {
                if (!etTelpon2.getText().equals("")) {
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", etTelpon2.getText().toString(), null)));
                }
                return true;
            }
        });

        etCustomerBasedNo.setText(new tCustomerBasedMobileHeaderBL().generateSubmissionId());

        dtHeader = new tCustomerBasedMobileHeaderBL().getDataByBitActive();

        if (dtHeader.get_intTrCustomerId() != null) {
            etCustomerBasedNo.setText(dtHeader.get_txtSubmissionId());
            etAlamat.setText(dtHeader.get_txtALamat());
            etNama.setText(dtHeader.get_txtNamaDepan());
            etTelpon.setText(dtHeader.get_txtTelp());
            etTelpon2.setText(dtHeader.get_txtTelp2());
            etTelponKantor.setText(dtHeader.get_txtTelpKantor());
            etEmail.setText(dtHeader.get_txtEmail());
            etPinBBM.setText(dtHeader.get_txtPINBBM());
            cbPIC.setChecked(dtHeader.get_intPIC().equals("1") ? true : false);

            RadioButton rbLaki = (RadioButton) v.findViewById(R.id.radioMale);
            RadioButton rbPerempuan = (RadioButton) v.findViewById(R.id.radioFemale);

            if (dtHeader.get_txtGender().equals("Perempuan")) {
                rbPerempuan.setChecked(true);
                rbLaki.setChecked(false);
            } else {
                rbLaki.setChecked(true);
                rbPerempuan.setChecked(false);
            }

        }

        Button btnAdd = (Button) v.findViewById(R.id.btnAdd);
        Button btnAddPerson = (Button) v.findViewById(R.id.btnAddPerson);
        btnSave = (Button) v.findViewById(R.id.btnSave);
        Button btnBack = (Button) v.findViewById(R.id.btnBackToHeader);
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

        etAlamat.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence cs, int start,
                                               int end, Spanned spanned, int dStart, int dEnd) {
                        // TODO Auto-generated method stub
                        if (cs.equals("")) { // for backspace
                            return cs;
                        }
                        if (cs.toString().matches("[a-zA-Z0-9. ]+")) {
                            return cs;
                        }
                        return "";
                    }
                }
        });

        return v;
    }

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

                boolean validate = validateHeader();

                if (validate) {
                    if (validate) {
                        if (isValidEmail(etEmail.getText().toString())) {
                            TextView tvCode = (TextView) v.findViewById(R.id.tvCode);
                            TextView tvNama = (TextView) v.findViewById(R.id.tvNamaPreview);
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

                            tvCode.setText("Code : " + new tCustomerBasedMobileHeaderBL().getDataByBitActive().get_txtSubmissionId());
                            tvNama.setText("Nama : " + etNama.getText().toString());
                            tvTelp.setText("Telp : " + etTelpon.getText().toString());
                            tvTelp2.setText("Telp (Optional) : " + etTelpon2.getText().toString());
                            tvTelpKantor.setText("Telp Kantor : " + etTelponKantor.getText().toString());
                            tvAlamat.setText("Alamat : " + etAlamat.getText().toString());
                            tvEmail.setText("Email : " + etEmail.getText().toString());
                            tvPinBBM.setText("Pin BBM : " + etPinBBM.getText().toString());

                        } else {
                            new clsMainActivity().setErrorMessage(getContext(), textInputLayoutEmail, etEmail, "Email not valid");
//                            new clsMainActivity().showCustomToast(getContext(), "Email not valid", false);
                        }
                    } else {
                        new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelp, etTelpon, "No telp harus diawali dengan 0");
//                        new clsMainActivity().showCustomToast(getContext(), "no telp atau no kantor diawali angka 0", false);

                        new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelp2, etTelpon2, "No telp harus diawali dengan 0");
                    }
                } else {
//                    new clsMainActivity().showCustomToast(getContext(), "Nama, telp, or email cannot empty", false);
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

    private void viewCustomerBaseFragment() {
        Intent intent = new Intent(getContext(), MainMenu.class);
        intent.putExtra("key_view", "View Customer Base");
        getActivity().finish();
        startActivity(intent);
        return;
    }

    private void saveCustomerBase() {
        Boolean status = new tCustomerBasedMobileHeaderBL().submit(getContext());

        if (status) {
//            new clsMainActivity().showCustomToast(getContext(), "Saved", true);
//            getActivity().finish();
//            Intent intent = new Intent(getContext(), MainMenu.class);
//            startActivity(intent);
            viewCustomerBaseFragment();
        } else {
//            new clsMainActivity().showCustomToast(getContext(), "Failed to save", false);
        }
    }

    private void popUpAddPerson(final tCustomerBasedMobileDetailData dataDetail) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.popup_add_customerbase, null);

        final EditText nama = (EditText) promptView.findViewById(R.id.etNama);
        final EditText searchProduct = (EditText) promptView.findViewById(R.id.searchProduct);
        final RadioGroup radioGroupGender = (RadioGroup) promptView.findViewById(R.id.radioGender);
        final TextInputLayout textInputLayoutNamaPopup = (TextInputLayout) promptView.findViewById(R.id.input_layout_nama);

        listView = (ListView) promptView.findViewById(R.id.listView2);

        final List<mEmployeeSalesProductData> data = new mEmployeeSalesProductBL().GetAllData();
        modelItems = new ArrayList<>();

        List<tCustomerBasedMobileDetailProductData> dataProduct = null;
        if (dataDetail.get_intTrCustomerIdDetail() != null) {
            nama.setText(dataDetail.get_txtNamaDepan());

            RadioButton rbLaki = (RadioButton) promptView.findViewById(R.id.radioMale);
            RadioButton rbPerempuan = (RadioButton) promptView.findViewById(R.id.radioFemale);

            if (dataDetail.get_txtGender().equals("Perempuan")) {
                rbLaki.setChecked(false);
                rbPerempuan.setChecked(true);
            } else {
                rbLaki.setChecked(true);
                rbPerempuan.setChecked(false);
            }

            dataProduct = new tCustomerBasedMobileDetailProductBL().getDataByCustomerDetailId(dataDetail.get_intTrCustomerIdDetail());

            if (dataDetail.get_intPIC().equals("1")) {
                nama.setEnabled(false);
                rbLaki.setEnabled(false);
                rbPerempuan.setEnabled(false);
            }
        }

        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                ModelListview dt = new ModelListview();

                Boolean valid = false;
                Integer total = 0;

                if (dataProduct != null) {
                    for (int j = 0; j < dataProduct.size(); j++) {
                        if (dataProduct.get(j).get_txtProductBrandCode().equals(data.get(i).get_txtBrandDetailGramCode())) {
                            valid = true;
                            total = Integer.parseInt(dataProduct.get(j).get_txtProductBrandQty());
                            break;
                        }
                    }
                }

                if (valid) {
                    dt.set_id(data.get(i).get_txtBrandDetailGramCode());
                    dt.set_name(data.get(i).get_txtProductBrandDetailGramName());
                    dt.set_value(total);
                    dt.set_selected(true);
                } else {
                    dt.set_id(data.get(i).get_txtBrandDetailGramCode());
                    dt.set_name(data.get(i).get_txtProductBrandDetailGramName());
                    dt.set_value(0);
                    dt.set_selected(false);
                }

                modelItems.add(dt);
            }
        }

        Collections.sort(modelItems, ModelListview.StuRollno);
        dataAdapter = new MyAdapter(getActivity().getApplicationContext(), modelItems);

        listView.setAdapter(dataAdapter);
        listView.setTextFilterEnabled(true);

        searchProduct.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();

        new clsMainActivity().removeErrorMessage(textInputLayoutNamaPopup);

        alertD.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nama.getText().toString().equals("")) {
                    Boolean status = false;
                    for (int i = 0; i < modelItems.size(); i++) {
                        if (modelItems.get(i).is_selected()) {
                            status = true;
                            break;
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
                                data.set_intPIC(dataDetail.get_intPIC());
                            }

                            data.set_intNo(String.valueOf(dtListDetail.size() + 1));
                        }

                        data.set_intTrCustomerId(dtHeader.get_intTrCustomerId());
                        data.set_txtNamaDepan(nama.getText().toString());
                        data.set_bitActive("1");
                        data.set_dtInserted(dateFormat.format(cal.getTime()));

                        int selectedId = radioGroupGender.getCheckedRadioButtonId();
                        RadioButton rbGender = (RadioButton) promptView.findViewById(selectedId);

                        data.set_txtGender(rbGender.getText().toString());

                        dtDetail.add(data);

                        new tCustomerBasedMobileDetailBL().saveData(data);

                        new tCustomerBasedMobileDetailProductBL().deleteData(data.get_intTrCustomerIdDetail());

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
                    } else {
                        new clsMainActivity().removeErrorMessage(textInputLayoutNamaPopup);
                        new clsMainActivity().showCustomToast(getContext(), "Select at least 1 product", false);
                    }
                } else {
                    new clsMainActivity().setErrorMessage(getContext(), textInputLayoutNamaPopup, nama, "Nama harus diisi");
//                    new clsMainActivity().showCustomToast(getContext(), "Nama cannot empty", false);
                }
            }
        });
    }

    private void setTablePerson() {
        ScrollView sv = (ScrollView) v.findViewById(R.id.scroll);
        sv.setFillViewport(true);

        dtListDetail = new tCustomerBasedMobileDetailBL().getAllDataByHeaderId(dtHeader.get_intTrCustomerId());

        clsSwipeList swplist;

        swipeList.clear();

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

            swplist.set_txtTitle("Nama : " + dtListDetail.get(i).get_txtNamaDepan() + PIC);
            swplist.set_txtDescription("Total Product : " + String.valueOf(totalProduct));
            swipeList.add(swplist);
        }

        btnSave.setVisibility(View.GONE);
        if (dtListDetail != null) {
            if(dtListDetail.size() > 0){
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

        mListView = (PullToRefreshSwipeMenuListView) v.findViewById(R.id.listView);
        mAdapter = clsMain.setList(getActivity().getApplicationContext(), swipeList);
        mListView.setAdapter(mAdapter);
        mListView.setPullRefreshEnable(false);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        mHandler = new Handler();

        HashMap<String, String> mapEdit = new HashMap<String, String>();
        HashMap<String, String> mapDelete = new HashMap<String, String>();

        mapEdit.put("name", "Edit");
        mapEdit.put("bgColor", "#3498db");

        mapDelete.put("name", "Delete");
        mapDelete.put("bgColor", "#FF0000");

        mapMenu = new HashMap<String, HashMap>();
        mapMenu.put("0", mapEdit);
        mapMenu.put("1", mapDelete);

        SwipeMenuCreator creator = clsMain.setCreator(getActivity().getApplicationContext(), mapMenu);
        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                clsSwipeList item = swipeList.get(position);
                switch (index) {
                    case 0:
                        editList(getActivity().getApplicationContext(), position);
                        break;
                    case 1:
                        deleteList(getActivity().getApplicationContext(), position);
                }
            }
        });

    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private void saveCustomerBaseHeader() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        if (dtHeader.get_intTrCustomerId() == null) {
            dtHeader = new tCustomerBasedMobileHeaderData();
            dtHeader.set_intTrCustomerId(new clsMainActivity().GenerateGuid());
        }

        dtHeader.set_txtBranchCode(new tAbsenUserBL().getDataCheckInActive().get_txtBranchCode());
        dtHeader.set_txtSumberData(new tAbsenUserBL().getDataCheckInActive().get_txtOutletCode());
        dtHeader.set_txtNamaSumberData(new tAbsenUserBL().getDataCheckInActive().get_txtOutletName());
        dtHeader.set_txtNamaDepan(etNama.getText().toString());
        dtHeader.set_txtTelp(etTelpon.getText().toString());
        dtHeader.set_txtTelp2(etTelpon2.getText().toString());
        dtHeader.set_txtTelpKantor(etTelponKantor.getText().toString());
        dtHeader.set_txtEmail(etEmail.getText().toString());
        dtHeader.set_txtPINBBM(etPinBBM.getText().toString());
        dtHeader.set_txtALamat(etAlamat.getText().toString());
        dtHeader.set_txtUserId(new tAbsenUserBL().getDataCheckInActive().get_txtUserId());
        dtHeader.set_txtRoleId(new tAbsenUserBL().getDataCheckInActive().get_txtRoleId());

        int selectedId = radioGenderGroup.getCheckedRadioButtonId();
        RadioButton rbGender = (RadioButton) v.findViewById(selectedId);

        dtHeader.set_txtGender(rbGender.getText().toString());

        if (cbPIC.isChecked()) {
            tCustomerBasedMobileDetailData data = new tCustomerBasedMobileDetailBL().getAllDataByHeaderIdandintPIC(dtHeader.get_intTrCustomerId());
            dtHeader.set_intPIC("1");

            if (data.get_intTrCustomerId() == null) {
                data = new tCustomerBasedMobileDetailData();
                data.set_intTrCustomerIdDetail(new clsMainActivity().GenerateGuid());
            }

            data.set_intTrCustomerId(dtHeader.get_intTrCustomerId());
            data.set_txtNamaDepan(dtHeader.get_txtNamaDepan());
            data.set_intNo(String.valueOf(dtDetail.size() + 1));
            data.set_intPIC("1");
            data.set_bitActive("1");
            data.set_dtInserted(dateFormat.format(cal.getTime()));
            data.set_txtGender(rbGender.getText().toString());

            dtDetail.add(data);

            new tCustomerBasedMobileDetailBL().saveData(data);

        } else {
            dtHeader.set_intPIC("0");
            tCustomerBasedMobileDetailData data = new tCustomerBasedMobileDetailBL().getAllDataByHeaderIdandintPIC(dtHeader.get_intTrCustomerId());

            if (data != null) {
                new tCustomerBasedMobileDetailBL().deleteData(data);
            }
        }

        dtHeader.set_txtDeviceId(new tAbsenUserBL().getDataCheckInActive().get_txtDeviceId());
        dtHeader.set_bitActive("1");
        dtHeader.set_dtDate(dateFormat.format(cal.getTime()));
        dtHeader.set_intSubmit("0");
        dtHeader.set_intSync("0");
        new tCustomerBasedMobileHeaderBL().saveData(dtHeader);

        setTablePerson();
    }

    private void onLoad() {
        mListView.setRefreshTime(RefreshTime.getRefreshTime(getActivity().getApplicationContext()));
        mListView.stopRefresh();

        mListView.stopLoadMore();

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

    public class MyAdapter extends BaseAdapter implements Filterable {

        private ArrayList<ModelListview> mOriginalValues; // Original Values
        private ArrayList<ModelListview> mDisplayedValues;    // Values to be displayed

        public MyAdapter(Context context, ArrayList<ModelListview> mProductArrayList) {
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
            Filter filter = new Filter() {

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
                    ArrayList<ModelListview> FilteredArrList = new ArrayList<ModelListview>();

                    if (mOriginalValues == null) {
                        mOriginalValues = new ArrayList<ModelListview>(mDisplayedValues); // saves the original data in mOriginalValues
                    }

                    /********
                     *
                     *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                     *  else does the Filtering and returns FilteredArrList(Filtered)
                     *
                     ********/
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
            return filter;
        }
    }

    public void viewCBFragment() {
        Intent intent = new Intent(getContext(), MainMenu.class);
        intent.putExtra("key_view", "View Customer Base");
        getActivity().finish();
        startActivity(intent);
        return;
    }

    private void editList(Context ctx, int position) {
        popUpAddPerson(dtListDetail.get(position));
    }

    private void deleteList(Context ctx, int position) {
        tCustomerBasedMobileDetailData dtDetail = dtListDetail.get(position);

        if (dtDetail.get_intPIC().equals("1")) {
            new clsMainActivity().showCustomToast(getContext(), "PIC tidak bisa dihapus", false);
        } else {
            new tCustomerBasedMobileDetailProductBL().deleteData(dtDetail.get_intTrCustomerIdDetail());
            new tCustomerBasedMobileDetailBL().deleteData(dtDetail);
            setTablePerson();
        }
    }

    private boolean validateHeader(){

        String notelp = etTelpon.getText().toString();
        String notelp2 = etTelpon2.getText().toString();
        String notelpkantor = etTelponKantor.getText().toString();
        String firstNotelp = "";
        String secondNotelp = null;

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
        new clsMainActivity().removeErrorMessage(textInputLayoutNama);
        new clsMainActivity().removeErrorMessage(textInputLayoutTelp);
        new clsMainActivity().removeErrorMessage(textInputLayoutTelp2);
        new clsMainActivity().removeErrorMessage(textInputLayoutTelpKantor);
        new clsMainActivity().removeErrorMessage(textInputLayoutEmail);

        if (etNama.getText().toString().equals("")){
            new clsMainActivity().setErrorMessage(getContext(), textInputLayoutNama, etNama, "Nama wajib diisi");
            validate = false;
        }

        if (etTelpon.getText().toString().equals("")){
            new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelp, etTelpon, "Telpon wajib diisi");
            validate = false;
        }
        else if (!firstNotelp.equals("0")) {
            new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelp, etTelpon, "No telpon diawali dengan angka 0");
            validate = false;
        }

        if (etTelpon2.getText().toString().equals("")){
            validate = true;
        }
        else if (!secondNotelp.equals("0")) {
            new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelp2, etTelpon2, "No telpon diawali dengan angka 0");
            validate = false;
        }

        if (etEmail.getText().toString().equals("")){
            new clsMainActivity().setErrorMessage(getContext(), textInputLayoutEmail, etEmail, "Email wajib diisi");
            validate = false;
        }
        else if(!isValidEmail(etEmail.getText().toString())){
            new clsMainActivity().setErrorMessage(getContext(), textInputLayoutEmail, etEmail, "Email tidak valid");
            validate = false;
        }

        if(firstNotelpkantor != null && !firstNotelpkantor.equals("0")){
            new clsMainActivity().setErrorMessage(getContext(), textInputLayoutTelpKantor, etTelponKantor, "No telpon kantor diawali dengan angka 0");
            validate = false;
        }

        return validate;
    }
}
