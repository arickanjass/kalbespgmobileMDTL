package com.kalbenutritionals.app.kalbespgmobile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.kalbenutritionals.app.kalbespgmobile.MainMenu;
import com.kalbenutritionals.app.kalbespgmobile.R;
import com.kalbenutritionals.app.kalbespgmobile.clsMainActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import bl.clsHelperBL;
import bl.tActivityBL;
import bl.tKategoryPlanogramMobileBL;
import bl.tPlanogramImageBL;
import bl.tPlanogramMobileBL;
import bl.tSalesProductQuantityImageBL;
import bl.tSubTypeActivityBL;
import bl.tUserLoginBL;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tKategoryPlanogramMobileData;
import library.spgmobile.common.tPlanogramImageData;
import library.spgmobile.common.tPlanogramMobileData;
import library.spgmobile.common.tSalesProductQuantityImageData;
import library.spgmobile.common.tSubTypeActivityData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.visitplanAbsenData;
import library.spgmobile.dal.clsHardCode;

/**
 * Created by aan.junianto on 10/08/2017.
 */

public class FragmentAddPlanogram extends Fragment implements View.OnClickListener {

    View v;

    ImageView imageButtonBefore1, imageButtonBefore2, imageButtonAfter1, imageButtonAfter2;
    EditText etDescription;
    TextInputLayout textInputLayoutDescription;
    Spinner spn_category;
    private LinearLayout lnlayoutIsValidPattern;
    private RadioGroup rg_isValidPattern;
    private ArrayList<String> arrCategory = new ArrayList<>();
    private HashMap<String, String> HMcategory = new HashMap<>();

    private Uri uriImage;
    private static final String IMAGE_DIRECTORY_NAME = "Image Planogram";

    private static final int CAMERA_CAPTURE_IMAGE_BEFORE_1_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_IMAGE_BEFORE_2_ACTIVITY_REQUEST_CODE = 200;
    private static final int CAMERA_CAPTURE_IMAGE_AFTER_1_ACTIVITY_REQUEST_CODE = 300;
    private static final int CAMERA_CAPTURE_IMAGE_AFTER_2_ACTIVITY_REQUEST_CODE = 400;

    private tPlanogramMobileData _tPlanogramMobileData;
    private List<tPlanogramImageData> dataimageList;
    private tPlanogramMobileData dtSavetPlanogramMobileData;
    String idTrCustomer = null;
    String param = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_planogram_add, container, false);

        Bundle mBundle = getArguments();
        if (mBundle != null) {
            idTrCustomer = mBundle.getString("id");
            param = mBundle.getString("param");
        }

        imageButtonBefore1 = (ImageView) v.findViewById(R.id.imageButtonBefore1);
        imageButtonBefore2 = (ImageView) v.findViewById(R.id.imageButtonBefore2);
        imageButtonAfter1 = (ImageView) v.findViewById(R.id.imageButtonAfter1);
        imageButtonAfter2 = (ImageView) v.findViewById(R.id.imageButtonAfter2);

        imageButtonBefore1.setOnClickListener(this);
        imageButtonBefore2.setOnClickListener(this);
        imageButtonAfter1.setOnClickListener(this);
        imageButtonAfter2.setOnClickListener(this);

        textInputLayoutDescription = (TextInputLayout) v.findViewById(R.id.input_layout_description);
        etDescription = (EditText) v.findViewById(R.id.etDescription);

        rg_isValidPattern = (RadioGroup) v.findViewById(R.id.rg_isValidPattern);
        RadioButton rb_yes = (RadioButton) v.findViewById(R.id.rb_yes);
        RadioButton rb_no = (RadioButton) v.findViewById(R.id.rb_no);
        rg_isValidPattern.clearCheck();

        lnlayoutIsValidPattern = (LinearLayout) v.findViewById(R.id.lnlayoutIsValidPattern);
        lnlayoutIsValidPattern.setVisibility(View.GONE);

        spn_category = (Spinner) v.findViewById(R.id.spn_category);

        //adding date and outlet di menu add

        TextView tv_date = (TextView) v.findViewById(R.id.tv_date);
        tv_date.setVisibility(View.VISIBLE);

        visitplanAbsenData dtAbsensVisitplan = new clsHelperBL().getDataCheckInActive();
        String outlet = "-";

        if(dtAbsensVisitplan!=null){
            outlet = dtAbsensVisitplan.get_txtOutletName();
            if(dtAbsensVisitplan.get_txtOutletName().toString().equals("null")){
                outlet = "No Outlet";
            }
        }

        // add date & outlet
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy",
                Locale.getDefault()).format(new Date());
        tv_date.setText(outlet + "-" + timeStamp);

        Button btnSave = (Button) v.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        etDescription.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence cs, int start,
                                               int end, Spanned spanned, int dStart, int dEnd) {
                        if (cs.equals("")) { // for backspace
                            return cs;
                        }
                        if (cs.toString().matches("[a-zA-Z0-9,.\\- ]+")) {
                            return cs;
                        }
                        return "";
                    }
                }, new InputFilter.AllCaps()
        });

        _tPlanogramMobileData = new tPlanogramMobileData();

        spn_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String id = HMcategory.get(spn_category.getSelectedItem().toString());

                tKategoryPlanogramMobileData _tKategoryPlanogramMobileData = new tKategoryPlanogramMobileData();

                _tKategoryPlanogramMobileData = new tKategoryPlanogramMobileBL().getDataById(id);

                if(_tKategoryPlanogramMobileData!=null){
                    if(_tKategoryPlanogramMobileData.get_intIsCheckValid().equals("1")){
                        lnlayoutIsValidPattern.setVisibility(View.VISIBLE);
                    } else if (_tKategoryPlanogramMobileData.get_intIsCheckValid().equals("0")){
                        lnlayoutIsValidPattern.setVisibility(View.GONE);
                    } else {
                        lnlayoutIsValidPattern.setVisibility(View.GONE);
                    }

                    _tPlanogramMobileData.set_txtCategoryName(spn_category.getSelectedItem().toString());
                    _tPlanogramMobileData.set_txtIdCategory(HMcategory.get(spn_category.getSelectedItem().toString()));
                } else {
                    lnlayoutIsValidPattern.setVisibility(View.GONE);
                    _tPlanogramMobileData.set_txtCategoryName(null);
                    _tPlanogramMobileData.set_txtIdCategory( null);
                    _tPlanogramMobileData.set_intIsValid(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        m_fillSpinner();

        _tPlanogramMobileData = new tPlanogramMobileData();
//        _tPlanogramMobileData = new tPlanogramMobileBL().getDataSave(dtAbsensVisitplan.get_txtOutletCode());

        boolean validViewEdit = false;

        if(_tPlanogramMobileData.get_txtIdPlanogram()==null) {
            _tPlanogramMobileData.set_txtIdPlanogram(new clsMainActivity().GenerateGuid());
        }

        if(mBundle != null && !idTrCustomer.equals("null")){
            _tPlanogramMobileData = new tPlanogramMobileData();
            _tPlanogramMobileData = new tPlanogramMobileBL().getDataById(idTrCustomer);
            if(_tPlanogramMobileData.get_txtIdPlanogram()!=null){
                validViewEdit = true;
            }
        } else if(_tPlanogramMobileData.get_txtIdPlanogram()!=null&&_tPlanogramMobileData.get_txtNIK()!=null){
            validViewEdit = true;
        }

        if (validViewEdit){
            etDescription.setText(_tPlanogramMobileData.get_txtKeterangan().toString());
            if(_tPlanogramMobileData.get_intIsValid()!=null){
                if(_tPlanogramMobileData.get_intIsValid().equals("1")){
                    rb_yes.setChecked(true);
                }  else if(_tPlanogramMobileData.get_intIsValid().equals("0")) {
                    rb_no.setChecked(true);
                } else {
                    rg_isValidPattern.clearCheck();
                }
            }
            if(_tPlanogramMobileData.get_txtIdCategory()!=null){
                spn_category.setSelection(Integer.valueOf(HMcategory.get(_tPlanogramMobileData.get_txtCategoryName())));
            }
            dataimageList = new ArrayList<>();
            dataimageList = new tPlanogramImageBL().getDataHeaderId(_tPlanogramMobileData.get_txtIdPlanogram());
            Bitmap mybitmap;
            if(dataimageList!=null&&dataimageList.size()>0){
                for(tPlanogramImageData data : dataimageList){
                    if(data.get_txtImage()!=null&&data.get_txtType().equals("BEFORE")&&data.get_intPosition().equals("1")){
                        mybitmap = BitmapFactory.decodeByteArray(data.get_txtImage(), 0, data.get_txtImage().length);
                        Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap, 150, 150, true);
                        imageButtonBefore1.setImageBitmap(bitmap);
                    }
                    if(data.get_txtImage()!=null&&data.get_txtType().equals("BEFORE")&&data.get_intPosition().equals("2")){
                        mybitmap = BitmapFactory.decodeByteArray(data.get_txtImage(), 0, data.get_txtImage().length);
                        Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap, 150, 150, true);
                        imageButtonBefore2.setImageBitmap(bitmap);
                    }
                    if(data.get_txtImage()!=null&&data.get_txtType().equals("AFTER")&&data.get_intPosition().equals("1")){
                        mybitmap = BitmapFactory.decodeByteArray(data.get_txtImage(), 0, data.get_txtImage().length);
                        Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap, 150, 150, true);
                        imageButtonAfter1.setImageBitmap(bitmap);
                    }
                    if(data.get_txtImage()!=null&&data.get_txtType().equals("AFTER")&&data.get_intPosition().equals("2")){
                        mybitmap = BitmapFactory.decodeByteArray(data.get_txtImage(), 0, data.get_txtImage().length);
                        Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap, 150, 150, true);
                        imageButtonAfter2.setImageBitmap(bitmap);
                    }
                }
            }
        }

        rg_isValidPattern.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(lnlayoutIsValidPattern.getVisibility()==View.VISIBLE){
                    if(!(rg_isValidPattern.getCheckedRadioButtonId() ==-1)){

                        int selecterIdValidPattern = rg_isValidPattern.getCheckedRadioButtonId();
                        RadioButton rb_validPattern = (RadioButton) v.findViewById(selecterIdValidPattern);

                        String resultRbValid = null;

                        try{
                            resultRbValid = String.valueOf(rb_validPattern.getText());
                            resultRbValid = resultRbValid.equals("Sesuai") ? "1" : "0";
                        }catch (Exception e){
                            String a = e.toString();
                            resultRbValid = "";
                        }

                        _tPlanogramMobileData.set_intIsValid(resultRbValid);
                    } else {
                        _tPlanogramMobileData.set_intIsValid(null);
                    }
                }
            }
        });

        if(_tPlanogramMobileData.get_intId()!=null&&_tPlanogramMobileData.get_txtIdCategory()!=null){
            spn_category.setSelection(Integer.valueOf(HMcategory.get(_tPlanogramMobileData.get_txtIdCategory()))-1);
        }

//        etDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(etDescription.getText().toString().length()>0){
//                    new clsMainActivity().removeErrorMessage(textInputLayoutDescription);
//                }
//            }
//        });

        return v;
    }

    private void m_fillSpinner() {
        arrCategory.add("Select Category");

        List<tKategoryPlanogramMobileData> _tKategoryPlanogramMobileData = new tKategoryPlanogramMobileBL().getAllData();

        for (tKategoryPlanogramMobileData dt : _tKategoryPlanogramMobileData) {
            arrCategory.add(dt.get_txtName());
            HMcategory.put(dt.get_txtName(), dt.get_intKategoryPlanogram());
        }
        spn_category.setAdapter(new MyAdapter(getActivity(), R.layout.custom_spinner, arrCategory));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButtonBefore1:
                if(spn_category.getSelectedItem().toString().equals("Select Category")){
                    new clsMainActivity().showCustomToast(getContext(), "Please " + spn_category.getSelectedItem().toString() , false);
                }

                else if(lnlayoutIsValidPattern.getVisibility()==View.VISIBLE&&rg_isValidPattern.getCheckedRadioButtonId()==-1){
                    new clsMainActivity().showCustomToast(getContext(), "Please Check Is Valid Pattern are not" , false);
                }

                else if (etDescription.getText().toString().equals("") && etDescription.getText().toString().length() == 0) {
                    new clsMainActivity().setErrorMessage(getContext(), textInputLayoutDescription, etDescription, "Please give Description");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        etDescription.setBackground(null);
                    }
                } else {
                    new clsMainActivity().removeErrorMessage(textInputLayoutDescription);
                    captureImageBefore1();
                }
                break;
            case R.id.imageButtonBefore2:
                if(spn_category.getSelectedItem().toString().equals("Select Category")){
                    new clsMainActivity().showCustomToast(getContext(), "Please " + spn_category.getSelectedItem().toString() , false);
                }

                else if(lnlayoutIsValidPattern.getVisibility()==View.VISIBLE&&rg_isValidPattern.getCheckedRadioButtonId()==-1){
                    new clsMainActivity().showCustomToast(getContext(), "Please Check Is Valid Pattern are not" , false);
                }

                else if (etDescription.getText().toString().equals("") && etDescription.getText().toString().length() == 0) {
                    new clsMainActivity().setErrorMessage(getContext(), textInputLayoutDescription, etDescription, "Please give Description");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        etDescription.setBackground(null);
                    }
                } else {
                    new clsMainActivity().removeErrorMessage(textInputLayoutDescription);
                    captureImageBefore2();
                }
                break;
            case R.id.imageButtonAfter1:
                if(spn_category.getSelectedItem().toString().equals("Select Category")){
                    new clsMainActivity().showCustomToast(getContext(), "Please " + spn_category.getSelectedItem().toString() , false);
                }

                else if(lnlayoutIsValidPattern.getVisibility()==View.VISIBLE&&rg_isValidPattern.getCheckedRadioButtonId()==-1){
                    new clsMainActivity().showCustomToast(getContext(), "Please Check Is Valid Pattern are not" , false);
                }

                else if (etDescription.getText().toString().equals("") && etDescription.getText().toString().length() == 0) {
                    new clsMainActivity().setErrorMessage(getContext(), textInputLayoutDescription, etDescription, "Please give Description");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        etDescription.setBackground(null);
                    }
                } else {
                    new clsMainActivity().removeErrorMessage(textInputLayoutDescription);
                    captureImageAfter1();
                }
                break;
            case R.id.imageButtonAfter2:
                if(spn_category.getSelectedItem().toString().equals("Select Category")){
                    new clsMainActivity().showCustomToast(getContext(), "Please " + spn_category.getSelectedItem().toString() , false);
                }

                else if(lnlayoutIsValidPattern.getVisibility()==View.VISIBLE&&rg_isValidPattern.getCheckedRadioButtonId()==-1){
                    new clsMainActivity().showCustomToast(getContext(), "Please Check Is Valid Pattern are not" , false);
                }

                else if (etDescription.getText().toString().equals("") && etDescription.getText().toString().length() == 0) {
                    new clsMainActivity().setErrorMessage(getContext(), textInputLayoutDescription, etDescription, "Please give Description");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        etDescription.setBackground(null);
                    }
                } else {
                    new clsMainActivity().removeErrorMessage(textInputLayoutDescription);
                    captureImageAfter2();
                }
                break;
            case R.id.btnSave:

                boolean validImageBefore1=false;
                boolean validImageAfter1=false;
                boolean validImageBefore2=false;
                boolean validImageAfter2=false;
                dataimageList = new tPlanogramImageBL().getDataHeaderId(_tPlanogramMobileData.get_txtIdPlanogram());
                if(dataimageList!=null){
                    for(tPlanogramImageData data : dataimageList){
                        if(data.get_txtImage()!=null&&data.get_txtType().equals("BEFORE")&&data.get_intPosition().equals("1")){
                            validImageBefore1=true;
                        }
                        if(data.get_txtImage()!=null&&data.get_txtType().equals("BEFORE")&&data.get_intPosition().equals("2")){
                            validImageBefore2=true;
                        }
                        if(data.get_txtImage()!=null&&data.get_txtType().equals("AFTER")&&data.get_intPosition().equals("1")){
                            validImageAfter1=true;
                        }
                        if(data.get_txtImage()!=null&&data.get_txtType().equals("AFTER")&&data.get_intPosition().equals("2")){
                            validImageAfter2=true;
                        }
                    }
                }

                new clsMainActivity().removeErrorMessage(textInputLayoutDescription);

                if(spn_category.getSelectedItem().toString().equals("Select Category")){
                    new clsMainActivity().showCustomToast(getContext(), "Please " + spn_category.getSelectedItem().toString() , false);
                }

                else if(lnlayoutIsValidPattern.getVisibility()==View.VISIBLE&&rg_isValidPattern.getCheckedRadioButtonId()==-1){
                    new clsMainActivity().showCustomToast(getContext(), "Please Check Is Valid Pattern are not" , false);
                }

                else if (etDescription.getText().toString().equals("") && etDescription.getText().toString().length() == 0) {
                    new clsMainActivity().setErrorMessage(getContext(), textInputLayoutDescription, etDescription, "Please give Description");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        etDescription.setBackground(null);
                    }
                }
                else if(!validImageBefore1&&!validImageBefore2){
                    new clsMainActivity().showCustomToast(getContext(), "Please take at least one Photo Before", false);
                }
                else if(!validImageAfter1&&!validImageAfter2){
                    new clsMainActivity().showCustomToast(getContext(), "Please take at least one Photo After", false);
                } else {

                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Confirm");
                    alertDialog.setMessage("Are you sure?");
                    alertDialog.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            saveFinal();
//                            saveImageBefore1();
//                            saveImageBefore2();
//                            saveImageAfter1();
//                            saveImageAfter2();

                            viewPlanogram();

                            new clsMainActivity().showCustomToast(getContext(), "Submited", true);
                        }
                    });
                    alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });

                    alertDialog.show();
                }
                break;
        }
    }

    private void saveFinal(){
//        _tPlanogramMobileData.set_txtIdPlanogram(String.valueOf(new clsMainActivity().GenerateGuid()));

        visitplanAbsenData dtAbsen = new clsHelperBL().getDataCheckInActive();
        tUserLoginData dtLogin = new tUserLoginBL().getUserLogin();

        int selecterIdValidPattern = rg_isValidPattern.getCheckedRadioButtonId();
        RadioButton rb_validPattern = (RadioButton) v.findViewById(selecterIdValidPattern);

        String resultRbValid = null;

        try{
            resultRbValid = String.valueOf(rb_validPattern.getText());
            resultRbValid = resultRbValid.equals("Sesuai") ? "1" : "0";
        }catch (Exception e){
            String a = e.toString();
            resultRbValid = "";
        }

        _tPlanogramMobileData.set_txtKeterangan(String.valueOf(etDescription.getText()));
        _tPlanogramMobileData.set_OutletCode(dtAbsen.get_txtOutletCode());
        _tPlanogramMobileData.set_OutletName(dtAbsen.get_txtOutletName());
        _tPlanogramMobileData.set_txtBranchCode(dtAbsen.get_txtBranchCode());
        _tPlanogramMobileData.set_txtBranchName(dtAbsen.get_txtBranchName());
        _tPlanogramMobileData.set_txtNIK(dtLogin.get_TxtEmpId());
        _tPlanogramMobileData.set_UserId(dtLogin.get_txtUserId());
        _tPlanogramMobileData.set_txtRoleId(dtLogin.get_txtRoleId());
        _tPlanogramMobileData.set_intIdAbsenUser(dtLogin.get_txtDataId());
        _tPlanogramMobileData.set_txtDeviceId(String.valueOf(dtAbsen.get_txtDeviceId()));
        _tPlanogramMobileData.set_txtCategoryName(spn_category.getSelectedItem().toString());
        _tPlanogramMobileData.set_txtIdCategory(HMcategory.get(spn_category.getSelectedItem().toString()));
        _tPlanogramMobileData.set_intIsValid(resultRbValid);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        _tPlanogramMobileData.set_dtDate(dateFormat.format(cal.getTime()));

        _tPlanogramMobileData.set_txtKeterangan(String.valueOf(etDescription.getText()));
        _tPlanogramMobileData.set_bitActive("0");
        _tPlanogramMobileData.set_intSync("0");
        _tPlanogramMobileData.set_intSubmit("0");

        new tPlanogramMobileBL().saveData(_tPlanogramMobileData);

    }


    private void save(){
//        _tPlanogramMobileData.set_txtIdPlanogram(String.valueOf(new clsMainActivity().GenerateGuid()));

        visitplanAbsenData dtAbsen = new clsHelperBL().getDataCheckInActive();
        tUserLoginData dtLogin = new tUserLoginBL().getUserLogin();

        int selecterIdValidPattern = rg_isValidPattern.getCheckedRadioButtonId();
        RadioButton rb_validPattern = (RadioButton) v.findViewById(selecterIdValidPattern);

        String resultRbValid = null;

        try{
            resultRbValid = String.valueOf(rb_validPattern.getText());
            resultRbValid = resultRbValid.equals("Sesuai") ? "1" : "0";
        }catch (Exception e){
            String a = e.toString();
            resultRbValid = "";
        }

        _tPlanogramMobileData.set_txtKeterangan(String.valueOf(etDescription.getText()));
        _tPlanogramMobileData.set_OutletCode(dtAbsen.get_txtOutletCode());
        _tPlanogramMobileData.set_OutletName(dtAbsen.get_txtOutletName());
        _tPlanogramMobileData.set_txtBranchCode(dtAbsen.get_txtBranchCode());
        _tPlanogramMobileData.set_txtBranchName(dtAbsen.get_txtBranchName());
        _tPlanogramMobileData.set_txtNIK(dtLogin.get_TxtEmpId());
        _tPlanogramMobileData.set_UserId(dtLogin.get_txtUserId());
        _tPlanogramMobileData.set_txtRoleId(dtLogin.get_txtRoleId());
        _tPlanogramMobileData.set_intIdAbsenUser(dtLogin.get_txtDataId());
        _tPlanogramMobileData.set_txtDeviceId(String.valueOf(dtAbsen.get_txtDeviceId()));
        _tPlanogramMobileData.set_txtCategoryName(spn_category.getSelectedItem().toString());
        _tPlanogramMobileData.set_txtIdCategory(HMcategory.get(spn_category.getSelectedItem().toString()));
        _tPlanogramMobileData.set_intIsValid(resultRbValid);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        _tPlanogramMobileData.set_dtDate(dateFormat.format(cal.getTime()));

        _tPlanogramMobileData.set_txtKeterangan(String.valueOf(etDescription.getText()));
        _tPlanogramMobileData.set_bitActive("0");
        _tPlanogramMobileData.set_intSync("0");
        _tPlanogramMobileData.set_intSubmit("0");

        new tPlanogramMobileBL().saveData(_tPlanogramMobileData);

    }

    //start activity intent camera

    private void captureImageBefore1() {
        uriImage = getOutputMediaFileUri();
        Intent intentCamera1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera1.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(intentCamera1, CAMERA_CAPTURE_IMAGE_BEFORE_1_ACTIVITY_REQUEST_CODE);
    }
    private void captureImageBefore2() {
        uriImage = getOutputMediaFileUri();
        Intent intentCamera1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera1.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(intentCamera1, CAMERA_CAPTURE_IMAGE_BEFORE_2_ACTIVITY_REQUEST_CODE);
    }
    private void captureImageAfter1() {
        uriImage = getOutputMediaFileUri();
        Intent intentCamera1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera1.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(intentCamera1, CAMERA_CAPTURE_IMAGE_AFTER_1_ACTIVITY_REQUEST_CODE);
    }
    private void captureImageAfter2() {
        uriImage = getOutputMediaFileUri();
        Intent intentCamera1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera1.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(intentCamera1, CAMERA_CAPTURE_IMAGE_AFTER_2_ACTIVITY_REQUEST_CODE);
    }

    //get location file to save tmp
    private Uri getOutputMediaFileUri() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //use this if Lollipop_Mr1 (API 22) or above
            return FileProvider.getUriForFile(getActivity(), getActivity().getPackageName()+".provider", getOutputMediaFile());
        } else {
            return Uri.fromFile(getOutputMediaFile());
        }
//        return Uri.fromFile(getOutputMediaFile());
    }

    //create path file
    private File getOutputMediaFile() {
        // External sdcard location

        File mediaStorageDir = new File(new clsHardCode().txtFolderPlanogram + File.separator);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "tmp_plngrm" + ".png");
        return mediaFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_IMAGE_BEFORE_1_ACTIVITY_REQUEST_CODE) {
            if (resultCode == -1) {
                try {

                    Bitmap bitmap = null;

                    try {
                        InputStream ims =  getActivity().getContentResolver().openInputStream(uriImage);
                        bitmap = BitmapFactory.decodeStream(ims);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

//                    Bitmap bitmap;
//                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//                    String uri = uriImage.getPath();
//
//                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCapturedImageBefore1(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_BEFORE_2_ACTIVITY_REQUEST_CODE) {
            if (resultCode == -1) {
                try {

                    Bitmap bitmap = null;

                    try {
                        InputStream ims =  getActivity().getContentResolver().openInputStream(uriImage);
                        bitmap = BitmapFactory.decodeStream(ims);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

//                    Bitmap bitmap;
//                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//                    String uri = uriImage.getPath();
//
//                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCapturedImageBefore2(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_BEFORE_2_ACTIVITY_REQUEST_CODE) {
            if (resultCode == -1) {
                try {

                    Bitmap bitmap = null;

                    try {
                        InputStream ims =  getActivity().getContentResolver().openInputStream(uriImage);
                        bitmap = BitmapFactory.decodeStream(ims);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

//                    Bitmap bitmap;
//                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//                    String uri = uriImage.getPath();
//
//                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCapturedImageBefore2(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_AFTER_1_ACTIVITY_REQUEST_CODE) {
            if (resultCode == -1) {
                try {

                    Bitmap bitmap = null;

                    try {
                        InputStream ims =  getActivity().getContentResolver().openInputStream(uriImage);
                        bitmap = BitmapFactory.decodeStream(ims);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

//                    Bitmap bitmap;
//                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//                    String uri = uriImage.getPath();
//
//                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCapturedImageAfter1(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_AFTER_2_ACTIVITY_REQUEST_CODE) {
            if (resultCode == -1) {
                try {

                    Bitmap bitmap = null;

                    try {
                        InputStream ims =  getActivity().getContentResolver().openInputStream(uriImage);
                        bitmap = BitmapFactory.decodeStream(ims);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

//                    Bitmap bitmap;
//                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//                    String uri = uriImage.getPath();
//
//                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCapturedImageAfter2(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            }
        }
    }

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private byte[] imgPhoto = null;

    private void previewCapturedImageBefore1(Bitmap photo) {
        Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);

        try {
            try {
                output = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, output);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Bitmap photo_view = Bitmap.createScaledBitmap(bitmap, 150, 150, true);

            imgPhoto = output.toByteArray();

            imageButtonBefore1.setImageBitmap(photo_view);

            if(_tPlanogramMobileData!=null){
//                _tPlanogramMobileData.set_txtKeterangan(String.valueOf(etDescription.getText()));
                _tPlanogramMobileData.set_txtBeforeImg1(imgPhoto);
                save();
                saveImageBefore1();

            }

//            new tPlanogramMobileBL().saveData(_tPlanogramMobileData);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    private void previewCapturedImageBefore2(Bitmap photo) {
        Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);

        try {
            try {
                output = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, output);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Bitmap photo_view = Bitmap.createScaledBitmap(bitmap, 150, 150, true);

            imgPhoto = output.toByteArray();

            imageButtonBefore2.setImageBitmap(photo_view);

            if(_tPlanogramMobileData!=null){
//                _tPlanogramMobileData.set_txtKeterangan(String.valueOf(etDescription.getText()));
                _tPlanogramMobileData.set_txtBeforeImg2(imgPhoto);
                save();
                saveImageBefore2();
            }

//            new tPlanogramMobileBL().saveData(_tPlanogramMobileData);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    private void previewCapturedImageAfter1(Bitmap photo) {
        Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);

        try {
            try {
                output = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, output);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Bitmap photo_view = Bitmap.createScaledBitmap(bitmap, 150, 150, true);

            imgPhoto = output.toByteArray();

            imageButtonAfter1.setImageBitmap(photo_view);

            if(_tPlanogramMobileData!=null){
//                _tPlanogramMobileData.set_txtKeterangan(String.valueOf(etDescription.getText()));
                _tPlanogramMobileData.set_txtAfterImg1(imgPhoto);
                save();
                saveImageAfter1();
            }

//            new tPlanogramMobileBL().saveData(_tPlanogramMobileData);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    private void previewCapturedImageAfter2(Bitmap photo) {
        Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);

        try {
            try {
                output = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, output);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Bitmap photo_view = Bitmap.createScaledBitmap(bitmap, 150, 150, true);

            imgPhoto = output.toByteArray();

            imageButtonAfter2.setImageBitmap(photo_view);

            if(_tPlanogramMobileData!=null){
//                _tPlanogramMobileData.set_txtKeterangan(String.valueOf(etDescription.getText()));
                _tPlanogramMobileData.set_txtAfterImg2(imgPhoto);
                save();
                saveImageAfter2();
            }

//            new tPlanogramMobileBL().saveData(_tPlanogramMobileData);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void saveImageBefore1() {
        tPlanogramImageData dataImage = new tPlanogramImageData();
        List<tPlanogramImageData> dataImageList = new ArrayList<>();

        dataImageList = new tPlanogramImageBL().getDataHeaderId(_tPlanogramMobileData.get_txtIdPlanogram());
        boolean validUpdate = false;

        if(dataImageList!=null&&dataImageList.size()>0){
            for(tPlanogramImageData data : dataImageList){
                if(data.get_txtType().equals("BEFORE")&&data.get_intPosition().equals("1")){
                    validUpdate = true;
                    dataImage = data;
                }
            }

        }

        if(validUpdate){
            dataImage.set_txtImage(_tPlanogramMobileData.get_txtBeforeImg1());

            List<tPlanogramImageData> dtListImage = new ArrayList<>();
            dtListImage.add(dataImage);
            new tPlanogramImageBL().SaveData(dtListImage);
        } else {
            if (_tPlanogramMobileData.get_txtBeforeImg1() != null) {
                dataImage.set_txtId(new clsMainActivity().GenerateGuid());
                dataImage.set_txtHeaderId(_tPlanogramMobileData.get_txtIdPlanogram());
                dataImage.set_txtImage(_tPlanogramMobileData.get_txtBeforeImg1());
                dataImage.set_intPosition("1");
                dataImage.set_txtType("BEFORE");

                List<tPlanogramImageData> dtListImage = new ArrayList<>();
                dtListImage.add(dataImage);
                new tPlanogramImageBL().SaveData(dtListImage);
            }
        }

    }

    private void saveImageBefore2() {
        tPlanogramImageData dataImage = new tPlanogramImageData();
        List<tPlanogramImageData> dataImageList = new ArrayList<>();

        dataImageList = new tPlanogramImageBL().getDataHeaderId(_tPlanogramMobileData.get_txtIdPlanogram());
        boolean validUpdate = false;

        if(dataImageList!=null&&dataImageList.size()>0){
            for(tPlanogramImageData data : dataImageList){
                if(data.get_txtType().equals("BEFORE")&&data.get_intPosition().equals("2")){
                    validUpdate = true;
                    dataImage = data;
                }
            }

        }

        if(validUpdate){
            dataImage.set_txtImage(_tPlanogramMobileData.get_txtBeforeImg2());

            List<tPlanogramImageData> dtListImage = new ArrayList<>();
            dtListImage.add(dataImage);
            new tPlanogramImageBL().SaveData(dtListImage);
        } else {
            if (_tPlanogramMobileData.get_txtBeforeImg2() != null) {
                dataImage.set_txtId(new clsMainActivity().GenerateGuid());
                dataImage.set_txtHeaderId(_tPlanogramMobileData.get_txtIdPlanogram());
                dataImage.set_txtImage(_tPlanogramMobileData.get_txtBeforeImg2());
                dataImage.set_intPosition("2");
                dataImage.set_txtType("BEFORE");

                List<tPlanogramImageData> dtListImage = new ArrayList<>();
                dtListImage.add(dataImage);
                new tPlanogramImageBL().SaveData(dtListImage);
            }
        }
    }

    private void saveImageAfter1() {
        tPlanogramImageData dataImage = new tPlanogramImageData();
        List<tPlanogramImageData> dataImageList = new ArrayList<>();

        dataImageList = new tPlanogramImageBL().getDataHeaderId(_tPlanogramMobileData.get_txtIdPlanogram());
        boolean validUpdate = false;

        if(dataImageList!=null&&dataImageList.size()>0){
            for(tPlanogramImageData data : dataImageList){
                if(data.get_txtType().equals("AFTER")&&data.get_intPosition().equals("1")){
                    validUpdate = true;
                    dataImage = data;
                }
            }

        }

        if(validUpdate){
            dataImage.set_txtImage(_tPlanogramMobileData.get_txtAfterImg1());

            List<tPlanogramImageData> dtListImage = new ArrayList<>();
            dtListImage.add(dataImage);
            new tPlanogramImageBL().SaveData(dtListImage);
        } else {
            if (_tPlanogramMobileData.get_txtAfterImg1() != null) {
                dataImage.set_txtId(new clsMainActivity().GenerateGuid());
                dataImage.set_txtHeaderId(_tPlanogramMobileData.get_txtIdPlanogram());
                dataImage.set_txtImage(_tPlanogramMobileData.get_txtAfterImg1());
                dataImage.set_intPosition("1");
                dataImage.set_txtType("AFTER");

                List<tPlanogramImageData> dtListImage = new ArrayList<>();
                dtListImage.add(dataImage);
                new tPlanogramImageBL().SaveData(dtListImage);
            }
        }

    }

    private void saveImageAfter2() {
        tPlanogramImageData dataImage = new tPlanogramImageData();
        List<tPlanogramImageData> dataImageList = new ArrayList<>();

        dataImageList = new tPlanogramImageBL().getDataHeaderId(_tPlanogramMobileData.get_txtIdPlanogram());
        boolean validUpdate = false;

        if(dataImageList!=null&&dataImageList.size()>0){
            for(tPlanogramImageData data : dataImageList){
                if(data.get_txtType().equals("AFTER")&&data.get_intPosition().equals("2")){
                    validUpdate = true;
                    dataImage = data;
                }
            }

        }

        if(validUpdate){
            dataImage.set_txtImage(_tPlanogramMobileData.get_txtAfterImg2());

            List<tPlanogramImageData> dtListImage = new ArrayList<>();
            dtListImage.add(dataImage);
            new tPlanogramImageBL().SaveData(dtListImage);
        } else {
            if (_tPlanogramMobileData.get_txtAfterImg2() != null) {
                dataImage.set_txtId(new clsMainActivity().GenerateGuid());
                dataImage.set_txtHeaderId(_tPlanogramMobileData.get_txtIdPlanogram());
                dataImage.set_txtImage(_tPlanogramMobileData.get_txtAfterImg2());
                dataImage.set_intPosition("2");
                dataImage.set_txtType("AFTER");

                List<tPlanogramImageData> dtListImage = new ArrayList<>();
                dtListImage.add(dataImage);
                new tPlanogramImageBL().SaveData(dtListImage);
            }
        }

    }

    public void viewPlanogram() {
        Intent myIntent = new Intent(getContext(), MainMenu.class);
        myIntent.putExtra("key_view", "View Planogram");
        getActivity().finish();
        startActivity(myIntent);
    }
    public class MyAdapter extends ArrayAdapter<String> {

        List<String> arrObject;
        Context context;

        public MyAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            arrObject = new ArrayList<>();
            arrObject = objects;
            this.context = context;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row = inflater.inflate(R.layout.custom_spinner, parent, false);
            TextView label = (TextView) row.findViewById(R.id.tvTitle);
            label.setText(arrObject.get(position));
            TextView sub = (TextView) row.findViewById(R.id.tvDesc);
            sub.setVisibility(View.INVISIBLE);
            sub.setVisibility(View.GONE);
            row.setBackgroundColor(new Color().TRANSPARENT);
            return row;
        }
    }
}
