package com.kalbenutritionals.app.kalbespgmobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import bl.clsHelperBL;
import bl.tActivityBL;
import bl.tPlanogramImageBL;
import bl.tPlanogramMobileBL;
import bl.tSalesProductQuantityImageBL;
import bl.tUserLoginBL;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tPlanogramImageData;
import library.spgmobile.common.tPlanogramMobileData;
import library.spgmobile.common.tSalesProductQuantityImageData;
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

    private Uri uriImage;
    private static final String IMAGE_DIRECTORY_NAME = "Image Planogram";

    private static final int CAMERA_CAPTURE_IMAGE_BEFORE_1_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_IMAGE_BEFORE_2_ACTIVITY_REQUEST_CODE = 200;
    private static final int CAMERA_CAPTURE_IMAGE_AFTER_1_ACTIVITY_REQUEST_CODE = 300;
    private static final int CAMERA_CAPTURE_IMAGE_AFTER_2_ACTIVITY_REQUEST_CODE = 400;

    private tPlanogramMobileData _tPlanogramMobileData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_planogram_add, container, false);

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
                        if (cs.toString().matches("[a-zA-Z0-9.\\- ]+")) {
                            return cs;
                        }
                        return "";
                    }
                }, new InputFilter.AllCaps()
        });

        _tPlanogramMobileData = new tPlanogramMobileData();
//        _tPlanogramMobileData = new tPlanogramMobileBL().getDataByBitActive();
//
//        if(_tPlanogramMobileData.get_txtIdPlanogram()!=null){
//            etDescription.setText(_tPlanogramMobileData.get_txtKeterangan());
//
//            byte[] imgFileB1 = _tPlanogramMobileData.get_txtBeforeImg1();
//            if (imgFileB1 != null) {
//                Bitmap myBitmap = BitmapFactory.decodeByteArray(imgFileB1, 0, imgFileB1.length);
//                Bitmap photo_view1 = Bitmap.createScaledBitmap(myBitmap, 150, 150, true);
//                imageButtonBefore1.setImageBitmap(photo_view1);
//            }
//            byte[] imgFileB2 = _tPlanogramMobileData.get_txtBeforeImg2();
//            if (imgFileB2 != null) {
//                Bitmap myBitmap = BitmapFactory.decodeByteArray(imgFileB2, 0, imgFileB2.length);
//                Bitmap photo_view2 = Bitmap.createScaledBitmap(myBitmap, 150, 150, true);
//                imageButtonBefore2.setImageBitmap(photo_view2);
//            }
//            byte[] imgFileA1 = _tPlanogramMobileData.get_txtAfterImg1();
//            if (imgFileA1 != null) {
//                Bitmap myBitmap = BitmapFactory.decodeByteArray(imgFileA1, 0, imgFileA1.length);
//                Bitmap photo_view3 = Bitmap.createScaledBitmap(myBitmap, 150, 150, true);
//                imageButtonAfter1.setImageBitmap(photo_view3);
//            }
//            byte[] imgFileA2 = _tPlanogramMobileData.get_txtAfterImg2();
//            if (imgFileA2 != null) {
//                Bitmap myBitmap = BitmapFactory.decodeByteArray(imgFileA2, 0, imgFileA2.length);
//                Bitmap photo_view4 = Bitmap.createScaledBitmap(myBitmap, 150, 150, true);
//                imageButtonAfter2.setImageBitmap(photo_view4);
//            }
//
//        } else {
//            _tPlanogramMobileData.set_txtIdPlanogram(String.valueOf(new clsMainActivity().GenerateGuid()));
//
//            visitplanAbsenData dtAbsen = new clsHelperBL().getDataCheckInActive();
//            tUserLoginData dtLogin = new tUserLoginBL().getUserLogin();
//
//            _tPlanogramMobileData.set_bitActive("1");
//            _tPlanogramMobileData.set_intSync("0");
//            _tPlanogramMobileData.set_intSubmit("0");
//            _tPlanogramMobileData.set_txtKeterangan(String.valueOf(etDescription.getText()));
//            _tPlanogramMobileData.set_OutletCode(dtAbsen.get_txtOutletCode());
//            _tPlanogramMobileData.set_OutletName(dtAbsen.get_txtOutletName());
//            _tPlanogramMobileData.set_txtBranchCode(dtAbsen.get_txtBranchCode());
//            _tPlanogramMobileData.set_txtBranchName(dtAbsen.get_txtBranchName());
//            _tPlanogramMobileData.set_txtNIK(dtLogin.get_TxtEmpId());
//
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            Calendar cal = Calendar.getInstance();
//
//            _tPlanogramMobileData.set_dtDate(dateFormat.format(cal.getTime()));
//        }

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButtonBefore1:
                captureImageBefore1();
                break;
            case R.id.imageButtonBefore2:
                captureImageBefore2();
                break;
            case R.id.imageButtonAfter1:
                captureImageAfter1();
                break;
            case R.id.imageButtonAfter2:
                captureImageAfter2();
                break;
            case R.id.btnSave:

                new clsMainActivity().removeErrorMessage(textInputLayoutDescription);
                if (etDescription.getText().toString().equals("") && etDescription.getText().toString().length() == 0) {
                    new clsMainActivity().setErrorMessage(getContext(), textInputLayoutDescription, etDescription, "Please give Description");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        etDescription.setBackground(null);
                    }
                }
//                else if(_tPlanogramMobileData.get_txtBeforeImg1()==null&&_tPlanogramMobileData.get_txtBeforeImg2()==null){
//                    new clsMainActivity().showCustomToast(getContext(), "Please take at least one Photo Before", false);
//                }
                else if(_tPlanogramMobileData.get_txtAfterImg1()==null&&_tPlanogramMobileData.get_txtAfterImg2()==null){
                    new clsMainActivity().showCustomToast(getContext(), "Please take at least one Photo After", false);
                } else {

                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Confirm");
                    alertDialog.setMessage("Are you sure?");
                    alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            _tPlanogramMobileData.set_txtIdPlanogram(String.valueOf(new clsMainActivity().GenerateGuid()));

                            visitplanAbsenData dtAbsen = new clsHelperBL().getDataCheckInActive();
                            tUserLoginData dtLogin = new tUserLoginBL().getUserLogin();

                            _tPlanogramMobileData.set_txtKeterangan(String.valueOf(etDescription.getText()));
                            _tPlanogramMobileData.set_OutletCode(dtAbsen.get_txtOutletCode());
                            _tPlanogramMobileData.set_OutletName(dtAbsen.get_txtOutletName());
                            _tPlanogramMobileData.set_txtBranchCode(dtAbsen.get_txtBranchCode());
                            _tPlanogramMobileData.set_txtBranchName(dtAbsen.get_txtBranchName());
                            _tPlanogramMobileData.set_txtNIK(dtLogin.get_TxtEmpId());
                            _tPlanogramMobileData.set_UserId(dtLogin.get_txtUserId());
                            _tPlanogramMobileData.set_txtRoleId(dtLogin.get_txtRoleId());
                            _tPlanogramMobileData.set_intIdAbsenUser(dtLogin.get_txtDataId());

                            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            Calendar cal = Calendar.getInstance();

                            _tPlanogramMobileData.set_dtDate(dateFormat.format(cal.getTime()));

                            _tPlanogramMobileData.set_txtKeterangan(String.valueOf(etDescription.getText()));
                            _tPlanogramMobileData.set_bitActive("0");
                            _tPlanogramMobileData.set_intSync("0");
                            _tPlanogramMobileData.set_intSubmit("1");

                            new tPlanogramMobileBL().saveData(_tPlanogramMobileData);

                            saveImageBefore1();
                            saveImageBefore2();
                            saveImageAfter1();
                            saveImageAfter2();

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
        return Uri.fromFile(getOutputMediaFile());
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

                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath();

                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

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

                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath();

                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

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

                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath();

                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

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

                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath();

                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

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

                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath();

                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

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
            }

//            new tPlanogramMobileBL().saveData(_tPlanogramMobileData);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void saveImageBefore1() {
        tPlanogramImageData dataImage = new tPlanogramImageData();

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

    private void saveImageBefore2() {
        tPlanogramImageData dataImage = new tPlanogramImageData();

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

    private void saveImageAfter1() {
        tPlanogramImageData dataImage = new tPlanogramImageData();

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

    private void saveImageAfter2() {
        tPlanogramImageData dataImage = new tPlanogramImageData();

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

    public void viewPlanogram() {
        Intent myIntent = new Intent(getContext(), MainMenu.class);
        myIntent.putExtra("key_view", "View Planogram");
        getActivity().finish();
        startActivity(myIntent);
    }
}
