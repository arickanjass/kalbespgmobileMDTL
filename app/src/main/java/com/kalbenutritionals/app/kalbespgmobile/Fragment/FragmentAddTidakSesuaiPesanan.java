package com.kalbenutritionals.app.kalbespgmobile.Fragment;

import android.app.AlertDialog;
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
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kalbenutritionals.app.kalbespgmobile.MainMenu;
import com.kalbenutritionals.app.kalbespgmobile.R;
import com.kalbenutritionals.app.kalbespgmobile.clsMainActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import bl.KoordinasiOutletBL;
import bl.KoordinasiOutletImageBL;
import bl.clsHelperBL;
import bl.tTidakSesuaiPesananHeaderBL;
import bl.tTidakSesuaiPesananImageBL;
import bl.tUserLoginBL;
import library.spgmobile.common.KoordinasiOutletData;
import library.spgmobile.common.KoordinasiOutletImageData;
import library.spgmobile.common.mCategoryKoordinasiOutletData;
import library.spgmobile.common.tTidakSesuaiPesananHeaderData;
import library.spgmobile.common.tTidakSesuaiPesananImageData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.visitplanAbsenData;
import library.spgmobile.dal.clsHardCode;

/**
 * Created by aan.junianto on 11/10/2017.
 */

public class FragmentAddTidakSesuaiPesanan extends Fragment {
    private TextView date;
    private TextView tvStatus;
    private TextView tvOutlet, txtHDId;
    private Button btnSave;
    private Spinner spnKategori;
    EditText keterangan;
    private ImageView image1, image2;
    private Uri uriImage;

    private static final int CAMERA_REQUEST = 1888;
    private static final int CAMERA_REQUEST2 = 1889;
    private static final String IMAGE_DIRECTORY_NAME = "Image Koordinasi";

    private static Bitmap photoImage1, photoImage2;
    private static ByteArrayOutputStream output = new ByteArrayOutputStream();
    private static byte[] phtImage1;
    private static byte[] phtImage2;
    private static Bitmap mybitmap1;
    private static Bitmap mybitmap2;

    private tTidakSesuaiPesananHeaderData dtKoordinasi;
    private tTidakSesuaiPesananImageData dtKoordinasiImage;
    static List<tTidakSesuaiPesananHeaderData> dt;
    static List<tTidakSesuaiPesananImageData> dataImage;
    private ArrayList<String> arrCategoryKoordinasi = new ArrayList<>();
    private HashMap<String, String> HMsubCategory = new HashMap<>();
    clsMainActivity _clsMainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_koordinasi_add, container, false);

        txtHDId = (TextView) view.findViewById(R.id.txtHDId);
        tvStatus = (TextView)view.findViewById(R.id.tvStatusKoordinasi);
        btnSave = (Button)view.findViewById(R.id.btnSaveKoordinasi);
        keterangan = (EditText)view.findViewById(R.id.editTextKeteranganKoordinasi);
        date = (TextView) view.findViewById(R.id.textViewDateTimeKoordinasi);
        tvOutlet = (TextView) view.findViewById(R.id.textViewOutletName);
        image1 = (ImageView) view.findViewById(R.id.image1);
        image2 = (ImageView) view.findViewById(R.id.image2);
        spnKategori = (Spinner) view.findViewById(R.id.spn_kategoriKoordinasi);

        LinearLayout ll_CategoryKoordinasiOutlet = (LinearLayout) view.findViewById(R.id.ll_CategoryKoordinasiOutlet);

        ll_CategoryKoordinasiOutlet.setVisibility(View.GONE);

        keterangan.setFilters(new InputFilter[]{
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

//        final tAbsenUserData absenUserData = new tAbsenUserBL().getDataCheckInActive();
        final tUserLoginData dataUserActive = new tUserLoginBL().getUserActive();
        final int index = new KoordinasiOutletBL().getContactCount() + 1;
//        txtHDId.setText(String.valueOf(index));
        txtHDId.setText(String.valueOf(new clsMainActivity().GenerateGuid()));

        dtKoordinasi = new tTidakSesuaiPesananHeaderData();
        dtKoordinasiImage = new tTidakSesuaiPesananImageData();
//        tAbsenUserData dtActive = new tAbsenUserBL().getDataCheckInActive();
        final visitplanAbsenData absenUserData = new clsHelperBL().getDataCheckInActive();
        dt = new tTidakSesuaiPesananHeaderBL().getAllDataByOutletCode(absenUserData.get_txtOutletCode());

        // set date
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy",
                Locale.getDefault()).format(new Date());
        date.setText("Date " + timeStamp);

        String txtOutletName = "Outlet Name : " + String.valueOf(absenUserData.get_txtOutletName());
        if(absenUserData.get_txtOutletName().toString().equals("null")){
            tvOutlet.setVisibility(View.GONE);
            View view2 = (View) view.findViewById(R.id.view2);
            view2.setVisibility(View.GONE);
        }
        tvOutlet.setText(txtOutletName);

        tvStatus.setText("Status : Open");

        phtImage1 = null;
        phtImage2 = null;

        if (dtKoordinasi.get_intId() != null){
            byte[] img1File = dtKoordinasiImage.get_txtImage();
            if (img1File != null) {
                Bitmap myBitmap = BitmapFactory.decodeByteArray(img1File, 0, img1File.length);
                image1.setImageBitmap(myBitmap);
            }

            byte[] img2File = dtKoordinasiImage.get_txtImage();
            if (img2File != null) {
                Bitmap myBitmap = BitmapFactory.decodeByteArray(img2File, 0, img2File.length);
                image2.setImageBitmap(myBitmap);
            }
        }

        if (photoImage1 != null){
            image1.setImageBitmap(photoImage1);
            photoImage1.compress(Bitmap.CompressFormat.PNG, 100, output);
            phtImage1 = output.toByteArray();
        }

        if (photoImage2 != null){
            image2.setImageBitmap(photoImage2);
            photoImage2.compress(Bitmap.CompressFormat.PNG, 100, output);
            phtImage2 = output.toByteArray();
        }

        // click image
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage1();
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage2();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (keterangan.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Description cannot empy", Toast.LENGTH_SHORT).show();
                } else if (phtImage1 == null && phtImage2 == null){
                    Toast.makeText(getContext(), "Please Take at Least 1 Photo", Toast.LENGTH_SHORT).show();
                } else {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Confirm");
                    alertDialog.setMessage("Are you sure?");
                    alertDialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tTidakSesuaiPesananHeaderData koordinasiOutletData = new tTidakSesuaiPesananHeaderData();
                            java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            Calendar cal = Calendar.getInstance();

                            koordinasiOutletData.set_intId(txtHDId.getText().toString());
                            koordinasiOutletData.set_dtDate(dateFormat.format(cal.getTime()));
                            koordinasiOutletData.set_txtKeterangan(keterangan.getText().toString());
                            koordinasiOutletData.set_txtUserId(absenUserData.get_txtUserId());
                            koordinasiOutletData.set_txtUsername(dataUserActive.get_txtUserName());
                            koordinasiOutletData.set_txtRoleId(absenUserData.get_txtRoleId());
                            koordinasiOutletData.set_txtOutletCode(absenUserData.get_txtOutletCode());
                            koordinasiOutletData.set_txtOutletName(absenUserData.get_txtOutletName());
                            koordinasiOutletData.set_txtBranchCode(absenUserData.get_txtBranchCode());
                            koordinasiOutletData.set_txtBranchName(absenUserData.get_txtBranchName());
                            koordinasiOutletData.set_txtNIK(dataUserActive.get_TxtEmpId());
//                            koordinasiOutletData.set_intCategoriId(HMsubCategory.get(spnKategori.getSelectedItem().toString()));
//                            koordinasiOutletData.set_txtCategory(spnKategori.getSelectedItem().toString());
                            koordinasiOutletData.set_intSubmit("1");
                            koordinasiOutletData.set_intSync("0");
                            new tTidakSesuaiPesananHeaderBL().SaveData(koordinasiOutletData);

                            savePicture1();
                            savePicture2();

//                            Intent myIntent = new Intent(getContext(), MainMenu.class);
//                            getActivity().finish();
//                            startActivity(myIntent);
                            viewFragment();
                            new clsMainActivity().showCustomToast(getActivity(), "Submit", true);
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
            }
        });

        KoordinasiOutletBL _KoordinasiOutletBL = new KoordinasiOutletBL();
        KoordinasiOutletImageBL _KoordinasiOutletImageBL = new KoordinasiOutletImageBL();
        List<KoordinasiOutletData> listData = _KoordinasiOutletBL.getData("");
        List<KoordinasiOutletImageData> listImage = _KoordinasiOutletImageBL.getDataHeaderId("");

//        if (dt != null) {
//
//
//            date.setText("Date : " + dt.get(0).get_dtDate());
//            tvOutlet.setText("Outlet Name : " + dt.get(0).get_txtOutletName());
//            if(dt.get(0).get_txtOutletName().toString().equals("null")){
//                tvOutlet.setText("Outlet Name : " + "-");
//            }
//            keterangan.setText(dt.get(0).get_txtKeterangan());
//            keterangan.setTextColor(Color.BLACK);
//            keterangan.setEnabled(false);
//            image1.setEnabled(false);
//            image2.setEnabled(false);
//
//            if (!dt.get(0).get_intSync().equals("0")){
//                tvStatus.setText("Status : Sync");
//            } else {
//                tvStatus.setText("Status : Submit");
//            }
//            viewImage();
//            btnSave.setVisibility(View.INVISIBLE);
//        }
//        m_fillSpinner();

        return view;
    }

//    private void m_fillSpinner() {
//
//        List<mCategoryKoordinasiOutletData> _kategori = new KoordinasiOutletBL().GetAllCategoryKoordinasiOutletData();
//        arrCategoryKoordinasi.add("- Select One -");
//        for (mCategoryKoordinasiOutletData dt : _kategori) {
//            arrCategoryKoordinasi.add(dt.get_txtCategory());
//            HMsubCategory.put(dt.get_txtCategory(), dt.get_intId());
//        }
//        spnKategori.setAdapter(new FragmentAddKoordinasi.MyAdapter(getActivity(), R.layout.custom_spinner, arrCategoryKoordinasi));
//    }


    private void viewImage() {
        dataImage = new tTidakSesuaiPesananImageBL().getDataByHeaderId(dt.get(0).get_intId());
//        File folder = new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata");
        File folder = new File(new clsHardCode().txtPathTempData);
        folder.mkdir();

        for (tTidakSesuaiPesananImageData imgDt : dataImage){
            final byte[] imgFile = imgDt.get_txtImage();
            if (imgFile != null) {
                if (imgDt.get_intPosition().equals("1")) {
                    mybitmap1 = BitmapFactory.decodeByteArray(imgFile, 0, imgFile.length);
                    Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap1, 150, 150, true);
                    image1.setImageBitmap(bitmap);

                    File file = null;
                    try {
//                        file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
                        file = File.createTempFile("image-", ".jpg", new File(new clsHardCode().txtPathTempData));
                        FileOutputStream out = new FileOutputStream(file);
                        out.write(imgFile);
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            final byte[] imgFile2 = imgDt.get_txtImage();
            if (imgFile2 != null) {
                if (imgDt.get_intPosition().equals("2")) {
                    mybitmap2 = BitmapFactory.decodeByteArray(imgFile2, 0, imgFile2.length);
                    Bitmap bitmap = Bitmap.createScaledBitmap(mybitmap2, 150, 150, true);
                    image2.setImageBitmap(bitmap);

                    File file = null;
                    try {
//                        file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
                        file = File.createTempFile("image-", ".jpg", new File(new clsHardCode().txtPathTempData));
                        FileOutputStream out = new FileOutputStream(file);
                        out.write(imgFile2);
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // put image from camera
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
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
//                    String uri = uriImage.getPath().toString();
//
//                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCaptureImage1(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            }  else {
                try {
                    photoImage1 = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), data.getData());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == CAMERA_REQUEST2) {
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
//                    String uri = uriImage.getPath().toString();
//
//                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCaptureImage2(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            }  else {
                try {
                    photoImage2 = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), data.getData());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // preview image 1
    private void previewCaptureImage1(Bitmap photo){
        try {
            Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);
            image1.setVisibility(View.VISIBLE);
            output = null;
            try {
                output = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, output);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (output != null){
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Bitmap photo_view = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
            phtImage1 = output.toByteArray();
            image1.setImageBitmap(photo_view);

            if (dtKoordinasiImage == null){
                dtKoordinasiImage.set_txtImage(phtImage1);
            } else {
                dtKoordinasiImage.set_txtImage(phtImage1);
            }
            List<tTidakSesuaiPesananImageData> KoordinasiOutletImageDatas = new ArrayList<>();
            KoordinasiOutletImageDatas.add(dtKoordinasiImage);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    // preview image 2
    private void previewCaptureImage2(Bitmap photo){
        try {
            Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);
            image2.setVisibility(View.VISIBLE);
            output = null;
            try {
                output = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, output);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (output != null){
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Bitmap photo_view = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
            phtImage2 = output.toByteArray();
            image2.setImageBitmap(photo_view);

            if (dtKoordinasiImage == null){
                dtKoordinasiImage.set_txtImage(phtImage2);
            } else {
                dtKoordinasiImage.set_txtImage(phtImage2);
            }
            List<tTidakSesuaiPesananImageData> KoordinasiOutletImageDatas = new ArrayList<>();
            KoordinasiOutletImageDatas.add(dtKoordinasiImage);
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

    protected void captureImage1() {
        uriImage = getOutputMediaFileUri();
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void captureImage2() {
        uriImage = getOutputMediaFileUri();
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(cameraIntent, CAMERA_REQUEST2);
    }

    private void savePicture1() {
        tTidakSesuaiPesananImageData dataImage = new tTidakSesuaiPesananImageData();
        String headerId = txtHDId.getText().toString();

        if (phtImage1 != null){
            dataImage.set_txtId(new clsMainActivity().GenerateGuid());
            dataImage.set_txtHeaderId(txtHDId.getText().toString());
            dataImage.set_txtImage(phtImage1);
            dataImage.set_intPosition("1");

            List<tTidakSesuaiPesananImageData> dtListImage = new ArrayList<>();
            dtListImage.add(dataImage);
            new tTidakSesuaiPesananImageBL().SaveDataImage(dtListImage);
        }
    }

    private void savePicture2() {
        tTidakSesuaiPesananImageData dataImage = new tTidakSesuaiPesananImageData();
        String headerId = txtHDId.getText().toString();

        if (phtImage2 != null){
            dataImage.set_txtId(new clsMainActivity().GenerateGuid());
            dataImage.set_txtHeaderId(txtHDId.getText().toString());
            dataImage.set_txtImage(phtImage2);
            dataImage.set_intPosition("2");

            List<tTidakSesuaiPesananImageData> dtListImage = new ArrayList<>();
            dtListImage.add(dataImage);
            new tTidakSesuaiPesananImageBL().SaveDataImage(dtListImage);
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
    public void viewFragment() {
        Intent myIntent = new Intent(getContext(), MainMenu.class);
        myIntent.putExtra("key_view", "View Tidak Sesuai Pesanan");
        getActivity().finish();
        startActivity(myIntent);
    }
}
