package com.kalbenutritionals.app.kalbespgmobile.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.kalbenutritionals.app.kalbespgmobile.Utils.ImagePick;
import com.kalbenutritionals.app.kalbespgmobile.R;
import com.kalbenutritionals.app.kalbespgmobile.clsMainActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import bl.mCategoryPOPStandardBL;
import bl.mReasonPOPStandardBL;
import bl.tAbsenUserBL;
import bl.tPOPStandardDetailBL;
import bl.tPOPStandardHeaderBL;
import library.spgmobile.common.mCategoryPOPStandardData;
import library.spgmobile.common.mReasonPOPStandardData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tPOPStandardDetailData;
import library.spgmobile.common.tPOPStandardHeaderData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;

/**
 * Created by Dewi Oktaviani on 17/10/2017.
 */

public class FragmentAddPOP extends Fragment {
    View v;
    TextView tvDate, tvOutlet;
    EditText txtDesc;
    Spinner spnCategory, spnReason;
    CheckBox cbPOP;
    ImageView img1, img2;
    Button btnSubmit;
    LinearLayout lnReason, ln_descPOP;
    CardView cvImg;
    private ArrayList<String> arrData;
    private HashMap<String, String> HMsubCategory = new HashMap<>();
    private HashMap<String, String> HMsubReason = new HashMap<>();
    tUserLoginData dataUserActive;
    tAbsenUserData dataOutletCheckIn;
    Toolbar toolbar;
    String [] bundleType;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_add_popstandard, container, false);
        tvDate = (TextView) v.findViewById(R.id.tv_datePOP);
        tvOutlet = (TextView) v.findViewById(R.id.tv_outletPOP);
        spnCategory = (Spinner) v.findViewById(R.id.spnPOP);
        spnReason = (Spinner) v.findViewById(R.id.spnReasonPOP);
        lnReason = (LinearLayout) v.findViewById(R.id.ln_reasonPOP);
        ln_descPOP = (LinearLayout) v.findViewById(R.id.ln_descPOP);
        cvImg = (CardView) v.findViewById(R.id.itemCardViewPOP);
        cbPOP = (CheckBox) v.findViewById(R.id.cbPOP);
        txtDesc = (EditText) v.findViewById(R.id.etKeterangan);
        img1 = (ImageView) v.findViewById(R.id.imagePOP1);
        img2 = (ImageView) v.findViewById(R.id.imagePOP2);
        btnSubmit = (Button) v.findViewById(R.id.btnSubmitPOP);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            bundleType = bundle.getStringArray("Key_POPId");
        }
        toolbar.setSubtitle(bundleType[0]);

        dataUserActive = new tAbsenUserBL().getUserActive();
        dataOutletCheckIn = new tAbsenUserBL().getDataCheckInActive();
        tvOutlet.setText(dataOutletCheckIn.get_txtOutletName());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dataUserActive.get_dtLastLogin());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dateNow = dateFormat.format(date);
        tvDate.setText(dateNow);
        cbPOP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbPOP.isChecked()){
                    lnReason.setVisibility(View.GONE);
                    cvImg.setVisibility(View.VISIBLE);
                    ln_descPOP.setVisibility(View.VISIBLE);
                } else {
                    cvImg.setVisibility(View.GONE);
                    ln_descPOP.setVisibility(View.GONE);
                    lnReason.setVisibility(View.VISIBLE);
                }
            }
        });
        if (cbPOP.isChecked()){
            lnReason.setVisibility(View.GONE);
            cvImg.setVisibility(View.VISIBLE);
            ln_descPOP.setVisibility(View.VISIBLE);
        } else {
            cvImg.setVisibility(View.GONE);
            ln_descPOP.setVisibility(View.GONE);
            lnReason.setVisibility(View.VISIBLE);
        }
        m_fillSpinner();
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImageViewCamera1();
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImageViewCamera2();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate().length()>0){
                    new clsMainActivity().showCustomToast(getContext(), validate(), false);
                } else {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Confirm");
                    alertDialog.setMessage("Are you sure to Save?");
                    alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            save();
                        }
                    });
                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            //save();
               // validate();
            }
        });
        return v;
    }

    private String validate(){
        String msg = "";
        if (spnCategory.getSelectedItem().equals("-- Select Category POP Standard --")){
            msg = "Please Select Category";
           // new clsMainActivity().showCustomToast(getContext(), "Please Select Category", false);
        }else if (cbPOP.isChecked()){
            if (imgPhoto1==null && imgPhoto2==null){
                if (bundleType[1].equals("1")){
                    msg = "Please Photo at least 1 photo";
                } else {
                    msg="";
                }
                //new clsMainActivity().showCustomToast(getContext(), "Please Photo at least 1 photo", false);
            }
        }else if (!cbPOP.isChecked()){
            if (spnReason.getSelectedItem().equals("-- Select Reason --")){
                msg = "Please Select Reason If You Have no " + bundleType[0];
                        //new clsMainActivity().showCustomToast(getContext(), "Please Photo at least 1 photo", false);
            }
        }
        return  msg;
    }
    private void save(){
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
        tPOPStandardHeaderData dt = new tPOPStandardHeaderData();
        dt.set_intId(ImagePick.GenerateGuid());
        dt.set_txtType(bundleType[0]);
        if (cbPOP.isChecked()){
            dt.set_bolHavePOP("1");
            dt.set_txtReason(txtDesc.getText().toString());
        } else {
            dt.set_bolHavePOP("0");
            dt.set_txtReason(spnReason.getSelectedItem().toString());
        }
        dt.set_txtCategory(spnCategory.getSelectedItem().toString());
        dt.set_intRoleId(dataUserActive.get_txtRoleId());
        dt.set_txtUserName(dataUserActive.get_txtUserName());
        dt.set_txtNIK(dataUserActive.get_TxtEmpId());
        dt.set_txtOutletName(dataOutletCheckIn.get_txtOutletName());
        dt.set_txtOutletCode(dataOutletCheckIn.get_txtOutletCode());
        dt.set_txtBranchName(dataOutletCheckIn.get_txtBranchName());
        dt.set_txtBranchCode(dataOutletCheckIn.get_txtBranchCode());
        dt.set_DtDatetime(dateNow + " " + timeNow);
        dt.set_intSync("0");
        dt.set_intSubmit("0");
        new tPOPStandardHeaderBL().SaveData(dt);

        tPOPStandardHeaderData header = new tPOPStandardHeaderBL().GetByLastBeforeSaveDetail();
        tPOPStandardDetailData _data = new tPOPStandardDetailData();
        _data.set_intId(ImagePick.GenerateGuid());
        _data.set_intHeaderId(header.get_intId());
        if (cbPOP.isChecked()){
                _data.set_txtImg1(imgPhoto1);
                _data.set_txtImg2(imgPhoto2);
        }else {
            _data.set_txtImg1(null);
            _data.set_txtImg2(null);
        }
        _data.set_dtDatetime(dateNow + " " + timeNow);
        _data.set_intSubmit("1");
        _data.set_intSync("0");
        new tPOPStandardDetailBL().SaveData(_data);

        header.set_intSubmit("1");
        header.set_intId(header.get_intId());
        new tPOPStandardHeaderBL().SaveData(header);

        new clsMainActivity().showCustomToast(getContext(), "Saved", true);
        Bundle bundle = new Bundle();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        bundle.putStringArray("Key_POPId", bundleType);
        toolbar.setTitle("View POP Standard TL");
        FragmentPOPView fragmentPOPView = new FragmentPOPView();
        fragmentPOPView.setArguments(bundle);
        FragmentTransaction fragmentTransactionPOPView = getFragmentManager().beginTransaction();
        fragmentTransactionPOPView.replace(R.id.frame, fragmentPOPView);
        fragmentTransactionPOPView.commit();
    }
    private void m_fillSpinner() {
        arrData = new ArrayList<>();
        List<mCategoryPOPStandardData> _kategori = new mCategoryPOPStandardBL().GetAllData();
        arrData.add("-- Select Category POP Standard --");
        for (mCategoryPOPStandardData dt : _kategori) {
            arrData.add(dt.get_txtCategoryName());
            HMsubCategory.put(dt.get_txtCategoryName(), dt.get_txtCategoryCode());
        }
        spnCategory.setAdapter(new MyAdapter(getActivity(), R.layout.custom_spinner, arrData));

        arrData = new ArrayList<>();
        List<mReasonPOPStandardData> _Reason = new mReasonPOPStandardBL().GetAllData();
        arrData.add("-- Select Reason --");
        for (mReasonPOPStandardData dt : _Reason) {
            arrData.add(dt.get_txtReason());
            HMsubCategory.put(dt.get_txtReason(), dt.get_intId());
        }
        spnReason.setAdapter(new MyAdapter(getActivity(), R.layout.custom_spinner, arrData));
    }
    //start activity intent camera
    private Uri uriImage;
    private static final int CAMERA_CAPTURE_IMAGE_VIEW_1_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_IMAGE_VIEW_2_ACTIVITY_REQUEST_CODE = 200;
    private void captureImageViewCamera1() {
        uriImage = getOutputMediaFileUri();
        Intent intentCamera1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera1.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(intentCamera1, CAMERA_CAPTURE_IMAGE_VIEW_1_ACTIVITY_REQUEST_CODE);
    }
    private void captureImageViewCamera2() {
        uriImage = getOutputMediaFileUri();
        Intent intentCamera1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera1.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(intentCamera1, CAMERA_CAPTURE_IMAGE_VIEW_2_ACTIVITY_REQUEST_CODE);
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
    private static final String IMAGE_DIRECTORY_NAME = "Image POP Standard";
    private File getOutputMediaFile() {
        // External sdcard location

        File mediaStorageDir = new File(new clsHardCode().txtFolderPOPStandard + File.separator);
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
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "tmp_POPStandard" + ".png");
        return mediaFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_IMAGE_VIEW_1_ACTIVITY_REQUEST_CODE) {
            if (resultCode == -1) {
                try {

//                    Bitmap bitmap;
//                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//                    String uri = uriImage.getPath();
//
//                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    Bitmap bitmap = null;

                    try {
                        InputStream ims =  getActivity().getContentResolver().openInputStream(uriImage);
                        bitmap = BitmapFactory.decodeStream(ims);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    previewImageViewCamera1(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_VIEW_2_ACTIVITY_REQUEST_CODE) {
            if (resultCode == -1) {
                try {

//                    Bitmap bitmap;
//                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//                    String uri = uriImage.getPath();
//
//                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    Bitmap bitmap = null;

                    try {
                        InputStream ims =  getActivity().getContentResolver().openInputStream(uriImage);
                        bitmap = BitmapFactory.decodeStream(ims);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    previewImageViewCamera2(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            }
        }
    }
    //Preview image from camera capture
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private byte[] imgPhoto1 = null;
    private byte[] imgPhoto2 = null;
    private void previewImageViewCamera1(Bitmap photo) {
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

            imgPhoto1 = output.toByteArray();

            img1.setImageBitmap(photo_view);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    private void previewImageViewCamera2(Bitmap photo) {
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

            imgPhoto2 = output.toByteArray();

            img2.setImageBitmap(photo_view);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
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