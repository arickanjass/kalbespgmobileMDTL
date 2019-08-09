package com.kalbenutritionals.app.kalbespgmobile.Fragment;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
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
import bl.tActivityMobileBL;
import bl.tSubTypeActivityBL;
import bl.tUserLoginBL;
import library.spgmobile.common.tActivityMobileData;
import library.spgmobile.common.tSubTypeActivityData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.visitplanAbsenData;
import library.spgmobile.dal.clsHardCode;

public class FragmentAddActivityMD extends Fragment implements View.OnClickListener {
    View v;
    ImageButton imgActivity1, imgActivity2;
    EditText etDescription;
    RadioGroup rdFlag, rg_isValidPattern;
    RadioButton rbKalbe, rbComp;
    Spinner spnTypeActivity;
    TextInputLayout textInputLayoutDescription;

    private static final int CAMERA_CAPTURE_IMAGE1_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_IMAGE2_ACTIVITY_REQUEST_CODE = 130;
    private static final String IMAGE_DIRECTORY_NAME = "Image Activity";

    private Uri uriImage;
    private int countActivity;

    private tActivityMobileData dtActivityMobileData;
    private tActivityMobileBL _tActivityBL;

    private static Bitmap photo1, photo2;
    private static ByteArrayOutputStream output = new ByteArrayOutputStream();
    private static byte[] pht1;
    private static byte[] pht2;

    private ArrayList<String> arrTypeActivity = new ArrayList<>();
    private String selectedTypeActivity;
    private String selectedRbName;
    private HashMap<String, String> HMsubType = new HashMap<>();
    private LinearLayout lnlayoutIsValidPattern;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_activity_md_add,container,false);
        imgActivity1 = (ImageButton) v.findViewById(R.id.imageButton);
        imgActivity1.setOnClickListener(this);

        lnlayoutIsValidPattern = (LinearLayout) v.findViewById(R.id.lnlayoutIsValidPattern);
        lnlayoutIsValidPattern.setVisibility(View.GONE);

        rg_isValidPattern = (RadioGroup) v.findViewById(R.id.rg_isValidPattern);
        rg_isValidPattern.clearCheck();

        imgActivity2 = (ImageButton) v.findViewById(R.id.imageButton2);
        imgActivity2.setOnClickListener(this);

        textInputLayoutDescription = (TextInputLayout) v.findViewById(R.id.input_layout_description);

        Button btnSave = (Button) v.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        etDescription = (EditText) v.findViewById(R.id.etNama);
        rdFlag = (RadioGroup) v.findViewById(R.id.radioFlag);
        rbKalbe = (RadioButton) v.findViewById(R.id.rbKalbe);
        rbComp = (RadioButton) v.findViewById(R.id.rbCompetitor);

        spnTypeActivity = (Spinner) v.findViewById(R.id.spn_typeActivity);

        spnTypeActivity.setVisibility(View.GONE);
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

        TableRow tableRow = (TableRow) v.findViewById(R.id.tr_header_act);
        tableRow.setVisibility(View.GONE);

        dtActivityMobileData = new tActivityMobileBL().getDataByBitActive();

        _tActivityBL = new tActivityMobileBL();

        pht1=null;
        pht2=null;

        arrTypeActivity.add("Select Category");
        selectedRbName = rbKalbe.getText().toString();

        m_fillSpinner();

        rdFlag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(i == R.id.rbKalbe){
                    arrTypeActivity.clear();
                    arrTypeActivity.add("Select Category");
                    selectedRbName = rbKalbe.getText().toString();
                    m_fillSpinner();
                } else if (i == R.id.rbCompetitor){
                    arrTypeActivity.clear();
                    arrTypeActivity.add("Select Category");
                    selectedRbName = rbComp.getText().toString();
                    m_fillSpinner();
                    lnlayoutIsValidPattern.setVisibility(View.GONE);
                }
            }
        });

//        spnTypeActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(spnTypeActivity.getSelectedItem().toString().equals("Pattern Display")){
//                    lnlayoutIsValidPattern.setVisibility(View.VISIBLE);
//                } else if(!spnTypeActivity.getSelectedItem().toString().equals("Pattern Display"))  {
//                    lnlayoutIsValidPattern.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        if(dtActivityMobileData.get_intId() != null){
            byte[] imgFile = dtActivityMobileData.get_txtImg1();
            if(imgFile!=null){
                Bitmap myBitmap = BitmapFactory.decodeByteArray(imgFile, 0 , imgFile.length);
                imgActivity1.setImageBitmap(myBitmap);
            }

            byte[] imgFile2 = dtActivityMobileData.get_txtImg2();
            if(imgFile2!=null){
                Bitmap myBitmap = BitmapFactory.decodeByteArray(imgFile2, 0 , imgFile2.length);
                imgActivity2.setImageBitmap(myBitmap);
            }
        }

        if(photo1 != null){
            imgActivity1.setImageBitmap(photo1);
            photo1.compress(Bitmap.CompressFormat.PNG, 100, output);
            pht1 = output.toByteArray();
        }

        if(photo2 != null){
            imgActivity2.setImageBitmap(photo2);
            photo2.compress(Bitmap.CompressFormat.PNG, 100, output);
            pht2 = output.toByteArray();
        }

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


        return v;
    }

    private void m_fillSpinner() {

        List<tSubTypeActivityData> _tSubTypeActivityData = new tSubTypeActivityBL().getAllDataByTxtType(selectedRbName);

        for (tSubTypeActivityData dt : _tSubTypeActivityData) {
            arrTypeActivity.add(dt.get_txtName());
            HMsubType.put(dt.get_txtName(), dt.get_intSubTypeActivity());
        }
//        spnTypeActivity.setAdapter(new MyAdapter(getActivity(), R.layout.custom_spinner, arrTypeActivity));
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.imageButton:
                captureImage1();
                break;
            case R.id.imageButton2:
                captureImage2();
                break;
            case R.id.btnSave:
                new clsMainActivity().removeErrorMessage(textInputLayoutDescription);

//                if(spnTypeActivity.getSelectedItem().toString().equals("Select Category")||spnTypeActivity.getSelectedItem().toString().equals("Select Category")){
//                    new clsMainActivity().showCustomToast(getContext(), "Please " + spnTypeActivity.getSelectedItem().toString() , false);
//                } else
                if(lnlayoutIsValidPattern.getVisibility()==View.VISIBLE&&rg_isValidPattern.getCheckedRadioButtonId()==-1){
                        new clsMainActivity().showCustomToast(getContext(), "Please Check Is Valid Pattern are not" , false);
                } else if(etDescription.getText().toString().equals("")&&etDescription.getText().toString().length()==0){
                new clsMainActivity().setErrorMessage(getContext(), textInputLayoutDescription, etDescription, "Please give Description");
                } else if(pht1 == null && pht2 == null) {
                    new clsMainActivity().showCustomToast(getContext(), "Please take at least 1 photo", false);
                } else {

                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Confirm");
                    alertDialog.setMessage("Are you sure?");
                    alertDialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            int selectedId = rdFlag.getCheckedRadioButtonId();
                            RadioButton radioFlag = (RadioButton) v.findViewById(selectedId);

                            int selecterIdValidPattern = rg_isValidPattern.getCheckedRadioButtonId();
                            RadioButton rb_validPattern = (RadioButton) v.findViewById(selecterIdValidPattern);

                            String selectionRbValidPattern = null;

                            try{
                                selectionRbValidPattern = String.valueOf(rb_validPattern.getText());
                                selectionRbValidPattern = selectionRbValidPattern.equals("Yes") ? "1" : "0";
                            }catch (Exception e){
                                String a = e.toString();
                                selectionRbValidPattern = "";
                            }

//                            tAbsenUserData dtAbsen = new tAbsenUserBL().getDataCheckInActive();
                            visitplanAbsenData dtAbsen = new clsHelperBL().getDataCheckInActive();
                            tUserLoginData dtLogin = new tUserLoginBL().getUserLogin();
                            dtActivityMobileData.set_intIsValid(String.valueOf(selectionRbValidPattern));
                            dtActivityMobileData.set_intActive("0");
                            dtActivityMobileData.set_intIdSyn("0");
                            dtActivityMobileData.set_txtDesc(String.valueOf(etDescription.getText()));
                            dtActivityMobileData.set_intFlag(String.valueOf(radioFlag.getText()));
//                            dtActivityMobileData.set_intId(String.valueOf(new clsMainActivity().GenerateGuid()));
                            dtActivityMobileData.set_intSubmit("1");
                            dtActivityMobileData.set_txtOutletCode(dtAbsen.get_txtOutletCode());
                            dtActivityMobileData.set_txtOutletName(dtAbsen.get_txtOutletName());
                            if (dtAbsen.get_txtDeviceId() != null){
                                dtActivityMobileData.set_txtDeviceId(dtAbsen.get_txtDeviceId());
                            }
                            dtActivityMobileData.set_txtBranch(dtAbsen.get_txtBranchCode());
                            dtActivityMobileData.set_txtUserId(dtLogin.get_TxtEmpId());
                            dtActivityMobileData.set_txtRoleId(dtLogin.get_txtRoleId());
                            dtActivityMobileData.set_txtImg1(pht1);
                            dtActivityMobileData.set_txtImg2(pht2);
//                            dtActivityMobileData.set_txtTypeActivity(spnTypeActivity.getSelectedItem().toString());
                            dtActivityMobileData.set_txtTypeActivity("");
//                            dtActivityMobileData.set_intSubTypeActivity(HMsubType.get(spnTypeActivity.getSelectedItem().toString()));
                            dtActivityMobileData.set_intSubTypeActivity("8");

                            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            Calendar cal = Calendar.getInstance();

                            dtActivityMobileData.set_dtActivity(dateFormat.format(cal.getTime()));

                            List<tActivityMobileData> dtList = new ArrayList<>();
                            dtList.add(dtActivityMobileData);

                            new tActivityMobileBL().saveData(dtList);

                            new clsMainActivity().showCustomToast(getContext(), "Saved", true);
                            viewActivityFragment();
                            photo1 = null;
                            photo2 = null;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_IMAGE1_ACTIVITY_REQUEST_CODE) {
            if (resultCode == -1){
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
//                    String uri = uriImage.getPath().toString();
//
//                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCapturedImage1(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            }  else {
                try {
                    photo1 = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), data.getData());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE2_ACTIVITY_REQUEST_CODE) {
            if (resultCode == -1){
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
//                    String uri = uriImage.getPath().toString();
//
//                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCapturedImage2(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            }  else {
                try {
                    photo2 = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), data.getData());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }else {
            //Toast.makeText(getActivity().getApplicationContext(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
        }
    }

    private void previewCapturedImage1(Bitmap photo) {

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
            pht1 = output.toByteArray();
            imgActivity1.setImageBitmap(photo_view);

            dtActivityMobileData = new tActivityMobileBL().getDataByBitActive();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();

            if (dtActivityMobileData != null) {
                dtActivityMobileData.set_txtImg1(pht1);
                dtActivityMobileData.set_txtImg2(pht2);
            } else {
                dtActivityMobileData.set_txtImg1(pht1);
                dtActivityMobileData.set_txtImg2(pht2);
            }

            List<tActivityMobileData> dtListActivity = new ArrayList<>();
            dtListActivity.add(dtActivityMobileData);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void previewCapturedImage2(Bitmap photo) {
        Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);
        try {
            try {
                output = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, output); // bmp is your Bitmap instance
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
            pht2 = output.toByteArray();
            imgActivity2.setImageBitmap(photo_view);

            dtActivityMobileData = new tActivityMobileBL().getDataByBitActive();

            if (dtActivityMobileData != null) {
                dtActivityMobileData.set_txtImg1(pht1);
                dtActivityMobileData.set_txtImg2(pht2);
            } else {
                dtActivityMobileData.set_txtImg1(pht1);
                dtActivityMobileData.set_txtImg2(pht2);
            }
            List<tActivityMobileData> dtListActivity = new ArrayList<>();
            dtListActivity.add(dtActivityMobileData);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private Uri getOutputMediaFileUri() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //use this if Lollipop_Mr1 (API 22) or above
            return FileProvider.getUriForFile(getActivity(), getActivity().getPackageName()+".provider", getOutputMediaFile());
        } else {
            return Uri.fromFile(getOutputMediaFile());
        }
//        return Uri.fromFile(getOutputMediaFile());
    }

    private File getOutputMediaFile() {
        // External sdcard location

        File mediaStorageDir = new File(new clsHardCode().txtFolderActivity + File.separator);
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
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "tmp_act"  + ".png");
        return mediaFile;
    }

    public void viewActivityFragment(){
        Intent intent = new Intent(getContext(), MainMenu.class);
        intent.putExtra("key_view", "View Add Display");
        getActivity().finish();
        startActivity(intent);
        return;
    }
    protected void captureImage1() {
        uriImage = getOutputMediaFileUri();
        Intent intentCamera1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera1.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(intentCamera1, CAMERA_CAPTURE_IMAGE1_ACTIVITY_REQUEST_CODE);
    }

    protected void captureImage2() {
        uriImage = getOutputMediaFileUri();
        Intent intentCamera2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera2.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(intentCamera2, CAMERA_CAPTURE_IMAGE2_ACTIVITY_REQUEST_CODE);
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