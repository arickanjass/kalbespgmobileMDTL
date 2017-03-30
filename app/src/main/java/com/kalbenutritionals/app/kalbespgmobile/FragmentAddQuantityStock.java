package com.kalbenutritionals.app.kalbespgmobile;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import bl.clsMainBL;
import bl.mCounterNumberBL;
import bl.mEmployeeSalesProductBL;
import bl.tAbsenUserBL;
import bl.tSalesProductHeaderBL;
import bl.tSalesProductQuantityDetailBL;
import bl.tSalesProductQuantityHeaderBL;
import bl.tUserLoginBL;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;
import library.salesforce.common.AppAdapter;
import library.salesforce.common.ModelListview;
import library.salesforce.common.clsHelper;
import library.salesforce.common.clsSwipeList;
import library.salesforce.common.mEmployeeSalesProductData;
import library.salesforce.common.tAbsenUserData;
import library.salesforce.common.tSalesProductHeaderData;
import library.salesforce.common.tSalesProductQuantityData;
import library.salesforce.common.tSalesProductQuantityDetailData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumCounterData;
import library.salesforce.dal.tSalesProductQuantityDetailDA;

import static com.kalbenutritionals.app.kalbespgmobile.R.id.imageView;

/**
 * Created by Rian Andrivani on 16/03/2017.
 */

public class FragmentAddQuantityStock extends Fragment implements IXListViewListener{
    View v;

    private ArrayList<ModelListview> modelItems;
    private ArrayList<ModelListview> arrdataPriv;
    ListView listView;
    int selectedId;
    public static ArrayList<ModelListview> arr = new ArrayList<ModelListview>();
    static List<tSalesProductQuantityData> dt;
    static List<tSalesProductQuantityData> data;
    private FloatingActionButton fab;
    private List<String> arrData;
    private EditText edKeterangan, editTextQty;
    private Button preview;
    private String noso;
    private ImageView after1, after2;
    private ImageView before1, before2;
    private Spinner product;
    private Uri uriImage;
    TextView tv_date, txtHDId;
    TextView tv_noso;
    List<tSalesProductQuantityDetailData> dtListDetailProduct;

    private List<clsSwipeList> swipeList = new ArrayList<clsSwipeList>();
    private ArrayList<clsSwipeList> swipeListProduct = new ArrayList<clsSwipeList>();

    private AppAdapter mAdapter;
    private PullToRefreshSwipeMenuListView mListView;
    private Handler mHandler;
    private Map<String, HashMap> mapMenu;
    private HashMap<String, String> HMSubmision = new HashMap<String, String>();

    private static final int CAMERA_REQUEST = 1888;
    private static final int CAMERA_REQUEST2 = 1889;
    private static final int CAMERA_REQUEST3 = 1890;
    private static final int CAMERA_REQUEST4 = 1891;
    private static final String IMAGE_DIRECTORY_NAME = "Image Activity";

    private tSalesProductQuantityData dtQuantityData;

    private static Bitmap photoAfter1, photoAfter2, photoBefore1, photoBefore2;
    private static ByteArrayOutputStream output = new ByteArrayOutputStream();
    private byte[] phtAfter1;
    private static byte[] phtAfter2;
    private static byte[] phtBefore1;
    private static byte[] phtBefore2;

    clsMainActivity _clsMainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_add_quantity_stock, container, false);

        txtHDId = (TextView) v.findViewById(R.id.txtHDId);
        fab = (FloatingActionButton) v.findViewById(R.id.fabQuntity);
        product = (Spinner) v.findViewById(R.id.txtProduct_quantity);
        edKeterangan = (EditText) v.findViewById(R.id.etKeterangan_quantity);
        preview = (Button) v.findViewById(R.id.btnPreviewQuantity);
        tv_date = (TextView) v.findViewById(R.id.txtviewDateQuantity);
        tv_noso = (TextView) v.findViewById(R.id.txtNoQuantity);
        after1 = (ImageView) v.findViewById(R.id.imageAfter1);
        after2 = (ImageView) v.findViewById(R.id.imageAfter2);
        before1 = (ImageView) v.findViewById(R.id.imageBefore1);
        before2 = (ImageView) v.findViewById(R.id.imageBefore2);
        editTextQty = (EditText) v.findViewById(R.id.editTextQty);

        _clsMainActivity = new clsMainActivity();
        dtQuantityData = new tSalesProductQuantityData();
        txtHDId.setText(String.valueOf(new clsMainActivity().GenerateGuid()));

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edKeterangan.getWindowToken(), 0);

        // add no so in Textview txtNoQuantity
//        List<tSalesProductHeaderData> dtta = new tSalesProductHeaderBL().getAllSalesProductHeader();
        List<tSalesProductQuantityData> dtLast = new tSalesProductQuantityHeaderBL().getLastData();
        if (dtLast == null || dtLast.size() == 0) {
            noso = new mCounterNumberBL().getData(enumCounterData.NoDataSO);

        } else {
            noso = new mCounterNumberBL().getData(enumCounterData.NoDataSO);
            List<tSalesProductQuantityData> dataFirstIsExist = new tSalesProductQuantityHeaderBL().getDataByNoSO(noso);
            if (dataFirstIsExist.size() == 1) {
                clsHelper _clsHelper = new clsHelper();
                String oldVersion = dtLast.get(0).get_txtQuantityStock();
                noso = _clsHelper.generateNewId(oldVersion, "-", "5");
            } else {
                noso = new mCounterNumberBL().getData(enumCounterData.NoDataSO);
            }
        }
        tv_noso.setText(noso);

        // add date in txtviewDateQuantity
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy",
                Locale.getDefault()).format(new Date());
        tv_date.setText(timeStamp);

        phtAfter1 = null;
        phtAfter2 = null;
        phtBefore1 = null;
        phtBefore2 = null;

        /*if (dtQuantityData.get_intId() == null){
            byte[] imgAfterFile = dtQuantityData.get_txtAfterImg1();
            if (imgAfterFile != null) {
                Bitmap myBitmap = BitmapFactory.decodeByteArray(imgAfterFile, 0, imgAfterFile.length);
                after1.setImageBitmap(myBitmap);
            }
        }*/

        if (photoAfter1 != null){
            after1.setImageBitmap(photoAfter1);
            photoAfter1.compress(Bitmap.CompressFormat.PNG, 100, output);
            phtAfter1 = output.toByteArray();
        }

        // click image button
        after1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);*/

                captureAfterImage1();
            }
        });

        after2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent cameraIntent2 = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent2, CAMERA_REQUEST2);*/

                captureAfterImage2();
            }
        });

        before1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent cameraIntent3 = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent3, CAMERA_REQUEST3);*/

                captureBeforeImage1();
            }
        });

        before2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent cameraIntent4 = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent4, CAMERA_REQUEST4);*/

                captureBeforeImage2();
            }
        });

        // click Button add product Quantity
        Button btnAddQuantity = (Button) v.findViewById(R.id.btnAddQuantity);
        btnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpAddQuantity(new tSalesProductQuantityDetailData());
            }
        });

        // click button preview
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edKeterangan.getText().toString().equals("")) {
                    _clsMainActivity.showCustomToast(getActivity(), "Please fill Description...", false);
                } /*else if (phtAfter1 == null){
                    _clsMainActivity.showCustomToast(getContext(), "Please take at least 1 photo for after and before", false);
                }*/ else {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Confirm");
                    alertDialog.setMessage("Are you sure?");
                    alertDialog.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            save();
                            viewQuantityFragment();

                            _clsMainActivity.showCustomToast(getActivity(), "Saved", true);
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

        setTableProduct();

        return v;
    }

    private void popUpAddQuantity(final tSalesProductQuantityDetailData dataDetail) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.popup_add_quantity, null);
        final HashMap<String, String> HMProduct = new HashMap<String, String>();
        final EditText editTextQty = (EditText) promptView.findViewById(R.id.editTextQty);
        final Spinner spnKalbeProduct = (Spinner) promptView.findViewById(R.id.spnProductQuantity);
        final DatePicker dp = (DatePicker) promptView.findViewById(R.id.dp_expire_date);

        List<String> dataProductKalbe = new ArrayList<>();
        List<mEmployeeSalesProductData> listDataProductKalbe = new mEmployeeSalesProductBL().GetAllData();

        editTextQty.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                editTextQty.setText("");
            }
        });

        // add product to spinner spnProductQuantity
        if (listDataProductKalbe.size() > 0) {
            for (mEmployeeSalesProductData dt : listDataProductKalbe) {
                dataProductKalbe.add(dt.get_txtProductBrandDetailGramName());
                HMProduct.put(dt.get_txtProductBrandDetailGramName(), dt.get_txtBrandDetailGramCode());
                HMProduct.put(dt.get_txtBrandDetailGramCode(), dt.get_txtBrandDetailGramCode());
                HMProduct.put(dt.get_txtBrandDetailGramCode(), dt.get_decHJD());
            }
        }

        ArrayAdapter<String> adapterKalbeProduct = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, dataProductKalbe);
        spnKalbeProduct.setAdapter(adapterKalbeProduct);

        // set date min today
        dp.setMinDate(System.currentTimeMillis() - 1000);


        // muncul dialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                                alertDialog.setTitle("Confirm");
                                alertDialog.setMessage("Are you sure?");
                                alertDialog.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        clsMainBL _clsMainBL = new clsMainBL();
                                        SQLiteDatabase _db=_clsMainBL.getDb();

                                        String selectedOneKNProduct = spnKalbeProduct.getSelectedItem().toString();
                                        tUserLoginData dtUser = new tUserLoginBL().getUserActive();
                                        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                        Calendar cal = Calendar.getInstance();

                                        int day = dp.getDayOfMonth();
                                        int month = dp.getMonth() + 1;
                                        int year = dp.getYear();
                                        final String expireDate = year + "-" + month + "-" + day;

                                        String qtyProduct = null;
                                        qtyProduct = editTextQty.getText().toString();

                                        if (qtyProduct.length() == 0){
                                            qtyProduct = "0";
                                        }

                                        tSalesProductQuantityDetailData data = new tSalesProductQuantityDetailData();
                                        data.setIntId(_clsMainActivity.GenerateGuid());
                                        data.set_txtNoSo(tv_noso.getText().toString());
                                        data.set_dtDate(dateFormat.format(cal.getTime()));
                                        data.set_txtCodeProduct(HMProduct.get(selectedOneKNProduct));
                                        data.set_txtKeterangan(edKeterangan.getText().toString());
                                        data.setTxtProduct(selectedOneKNProduct);
                                        data.setTxtExpireDate(expireDate);
                                        data.setTxtQuantity(qtyProduct);
                                        data.set_intPrice(HMProduct.get(HMProduct.get(selectedOneKNProduct)));

                                        double prc = Double.valueOf(HMProduct.get(HMProduct.get(selectedOneKNProduct)));
                                        double itm = Double.valueOf(qtyProduct);

                                        data.set_intTotal(_clsMainActivity.convertNumberDec2(prc*itm));
                                        data.set_txtNIK(dtUser.get_txtUserId());

                                        // new tSalesProductQuantityDetailBL().saveData(data);
                                        new tSalesProductQuantityDetailDA(_db).SaveDatatSalesProductQuantityDetailData(_db, data);
                                        editTextQty.setText("");


                                        dialog.dismiss();
                                        setTableProduct();

                                        _clsMainActivity.showCustomToast(getActivity(), "Saved", true);
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
                        })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }

    // put image from camera
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST /*&& resultCode == Activity.RESULT_OK*/) {
            Bitmap bitmap;
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            String uri = uriImage.getPath().toString();

            bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

            previewCaptureAfterImage1(bitmap);
            /*
            Bitmap photoAfter1 = (Bitmap) data.getExtras().get("data");
            Bitmap photo_view = Bitmap.createScaledBitmap(photoAfter1, 150, 150, true);
            photoAfter1.compress(Bitmap.CompressFormat.PNG, 100, output);
            after1.setImageBitmap(photo_view);
            phtAfter1 = output.toByteArray();*/
        } else if (requestCode == CAMERA_REQUEST2 /*&& resultCode == Activity.RESULT_OK*/) {
            Bitmap bitmap;
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            String uri = uriImage.getPath().toString();

            bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

            previewCaptureAfterImage2(bitmap);
        } else if (requestCode == CAMERA_REQUEST3 /*&& resultCode == Activity.RESULT_OK*/) {
            Bitmap bitmap;
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            String uri = uriImage.getPath().toString();

            bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

            previewCaptureBeforeImage1(bitmap);
        } else if (requestCode == CAMERA_REQUEST4 /*&& resultCode == Activity.RESULT_OK*/) {
            Bitmap bitmap;
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            String uri = uriImage.getPath().toString();

            bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

            previewCaptureBeforeImage2(bitmap);
        }
    }

    // preview image After 1
    private void previewCaptureAfterImage1(Bitmap photo){
        tAbsenUserData absenUserData = new tAbsenUserBL().getDataCheckInActive();
//        tUserLoginData loginData = new tUserLoginData();
        tUserLoginData dataUserActive = new tUserLoginBL().getUserActive();
        tSalesProductQuantityDetailData dt = new tSalesProductQuantityDetailData();

        double price = Double.parseDouble(dt.get_intPrice());
        double value = Double.parseDouble(dt.getTxtQuantity());
        double qntyNum = price * value;
        double qntySum = 0;
        qntySum += qntyNum;
        String result = "0";
        result = _clsMainActivity.convertNumberDec2(qntySum);
//        ModelListview modelListview = new ModelListview();
        try {
            Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);
            after1.setVisibility(View.VISIBLE);
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
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
//            photo.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, blob);
//            Bitmap bitmap1 = Bitmap.createScaledBitmap(photo, 150, 150, false);
//            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, blob);
            byte[] pht = output.toByteArray();
            after1.setImageBitmap(photo_view);

            java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            clsMainActivity _clsMainActivity = new clsMainActivity();

            if (dtQuantityData == null){
                dtQuantityData.set_txtAfterImg1(pht);
            } else {
                dtQuantityData.set_intId(txtHDId.getText().toString());
                dtQuantityData.set_txtQuantityStock(tv_noso.getText().toString());
                dtQuantityData.set_dtDate(dateFormat.format(cal.getTime()));
                dtQuantityData.set_OutletCode(absenUserData.get_txtOutletCode());
                dtQuantityData.set_OutletName(absenUserData.get_txtOutletName());
                dtQuantityData.set_txtKeterangan(edKeterangan.getText().toString());
                dtQuantityData.set_UserId(absenUserData.get_txtUserId());
                dtQuantityData.set_txtRoleId(absenUserData.get_txtRoleId());
                dtQuantityData.set_txtBranchCode(absenUserData.get_txtBranchCode());
                dtQuantityData.set_txtBranchName(absenUserData.get_txtBranchName());
                dtQuantityData.set_intSumAmount(String.valueOf(result));
                dtQuantityData.set_intIdAbsenUser(absenUserData.get_intId());
                dtQuantityData.set_txtNIK(dataUserActive.get_TxtEmpId());
                dtQuantityData.set_txtAfterImg1(pht);
            }
            dtQuantityData.set_intSubmit("1");
            dtQuantityData.set_intSync("0");
            List<tSalesProductQuantityData> tSalesProductQuantityDatas = new ArrayList<>();
            tSalesProductQuantityDatas.add(dtQuantityData);
            new tSalesProductQuantityHeaderBL().SaveData(dtQuantityData);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    // preview image after 2
    private void previewCaptureAfterImage2(Bitmap photo){
        tAbsenUserData absenUserData = new tAbsenUserBL().getDataCheckInActive();
        tUserLoginData dataUserActive = new tUserLoginBL().getUserActive();
//        ModelListview modelListview = new ModelListview();
        try {
            Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);
            after2.setVisibility(View.VISIBLE);
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
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
//            photo.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, blob);
//            Bitmap bitmap1 = Bitmap.createScaledBitmap(photo, 150, 150, false);
//            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, blob);
            byte[] pht = output.toByteArray();
            after2.setImageBitmap(photo_view);

            java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            clsMainActivity _clsMainActivity = new clsMainActivity();

            if (dtQuantityData == null){
                dtQuantityData.set_txtAfterImg2(pht);
            } else {
                dtQuantityData.set_intId(txtHDId.getText().toString());
                dtQuantityData.set_txtQuantityStock(tv_noso.getText().toString());
                dtQuantityData.set_dtDate(dateFormat.format(cal.getTime()));
                dtQuantityData.set_OutletCode(absenUserData.get_txtOutletCode());
                dtQuantityData.set_OutletName(absenUserData.get_txtOutletName());
                dtQuantityData.set_txtKeterangan(edKeterangan.getText().toString());
                dtQuantityData.set_UserId(absenUserData.get_txtUserId());
                dtQuantityData.set_txtRoleId(absenUserData.get_txtRoleId());
                dtQuantityData.set_txtBranchCode(absenUserData.get_txtBranchCode());
                dtQuantityData.set_txtBranchName(absenUserData.get_txtBranchName());
                dtQuantityData.set_intIdAbsenUser(absenUserData.get_intId());
                dtQuantityData.set_txtNIK(dataUserActive.get_TxtEmpId());
                dtQuantityData.set_txtAfterImg2(pht);
            }
            dtQuantityData.set_intSubmit("1");
            dtQuantityData.set_intSync("0");
            List<tSalesProductQuantityData> tSalesProductQuantityDatas = new ArrayList<>();
            tSalesProductQuantityDatas.add(dtQuantityData);
            new tSalesProductQuantityHeaderBL().SaveData(dtQuantityData);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    // preview image before 1
    private void previewCaptureBeforeImage1(Bitmap photo){
        tAbsenUserData absenUserData = new tAbsenUserBL().getDataCheckInActive();
        tUserLoginData dataUserActive = new tUserLoginBL().getUserActive();
//        ModelListview modelListview = new ModelListview();
        try {
            Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);
            before1.setVisibility(View.VISIBLE);
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
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
//            photo.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, blob);
//            Bitmap bitmap1 = Bitmap.createScaledBitmap(photo, 150, 150, false);
//            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, blob);
            byte[] pht = output.toByteArray();
            before1.setImageBitmap(photo_view);

            java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            clsMainActivity _clsMainActivity = new clsMainActivity();

            if (dtQuantityData == null){
                dtQuantityData.set_txtBeforeImg1(pht);
            } else {
                dtQuantityData.set_intId(txtHDId.getText().toString());
                dtQuantityData.set_txtQuantityStock(tv_noso.getText().toString());
                dtQuantityData.set_dtDate(dateFormat.format(cal.getTime()));
                dtQuantityData.set_OutletCode(absenUserData.get_txtOutletCode());
                dtQuantityData.set_OutletName(absenUserData.get_txtOutletName());
                dtQuantityData.set_txtKeterangan(edKeterangan.getText().toString());
                dtQuantityData.set_UserId(absenUserData.get_txtUserId());
                dtQuantityData.set_txtRoleId(absenUserData.get_txtRoleId());
                dtQuantityData.set_txtBranchCode(absenUserData.get_txtBranchCode());
                dtQuantityData.set_txtBranchName(absenUserData.get_txtBranchName());
                dtQuantityData.set_intIdAbsenUser(absenUserData.get_intId());
                dtQuantityData.set_txtNIK(dataUserActive.get_TxtEmpId());
                dtQuantityData.set_txtBeforeImg1(pht);
            }
            dtQuantityData.set_intSubmit("1");
            dtQuantityData.set_intSync("0");
            List<tSalesProductQuantityData> tSalesProductQuantityDatas = new ArrayList<>();
            tSalesProductQuantityDatas.add(dtQuantityData);
            new tSalesProductQuantityHeaderBL().SaveData(dtQuantityData);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    // preview image before 2
    private void previewCaptureBeforeImage2(Bitmap photo){
        tAbsenUserData absenUserData = new tAbsenUserBL().getDataCheckInActive();
        tUserLoginData dataUserActive = new tUserLoginBL().getUserActive();
//        ModelListview modelListview = new ModelListview();
        try {
            Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);
            before2.setVisibility(View.VISIBLE);
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
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
//            photo.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, blob);
//            Bitmap bitmap1 = Bitmap.createScaledBitmap(photo, 150, 150, false);
//            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, blob);
            byte[] pht = output.toByteArray();
            before2.setImageBitmap(photo_view);

            java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            clsMainActivity _clsMainActivity = new clsMainActivity();

            if (dtQuantityData == null){
                dtQuantityData.set_txtBeforeImg2(pht);
            } else {
                dtQuantityData.set_intId(txtHDId.getText().toString());
                dtQuantityData.set_txtQuantityStock(tv_noso.getText().toString());
                dtQuantityData.set_dtDate(dateFormat.format(cal.getTime()));
                dtQuantityData.set_OutletCode(absenUserData.get_txtOutletCode());
                dtQuantityData.set_OutletName(absenUserData.get_txtOutletName());
                dtQuantityData.set_txtKeterangan(edKeterangan.getText().toString());
                dtQuantityData.set_UserId(absenUserData.get_txtUserId());
                dtQuantityData.set_txtRoleId(absenUserData.get_txtRoleId());
                dtQuantityData.set_txtBranchCode(absenUserData.get_txtBranchCode());
                dtQuantityData.set_txtBranchName(absenUserData.get_txtBranchName());
                dtQuantityData.set_intIdAbsenUser(absenUserData.get_intId());
                dtQuantityData.set_txtNIK(dataUserActive.get_TxtEmpId());
                dtQuantityData.set_txtBeforeImg2(pht);
            }
            dtQuantityData.set_intSubmit("1");
            dtQuantityData.set_intSync("0");
            List<tSalesProductQuantityData> tSalesProductQuantityDatas = new ArrayList<>();
            tSalesProductQuantityDatas.add(dtQuantityData);
            new tSalesProductQuantityHeaderBL().SaveData(dtQuantityData);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
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

    protected void captureAfterImage1() {
        uriImage = getOutputMediaFileUri();
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void captureAfterImage2() {
        uriImage = getOutputMediaFileUri();
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(cameraIntent, CAMERA_REQUEST2);
    }

    protected void captureBeforeImage1() {
        uriImage = getOutputMediaFileUri();
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(cameraIntent, CAMERA_REQUEST3);
    }

    protected void captureBeforeImage2() {
        uriImage = getOutputMediaFileUri();
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(cameraIntent, CAMERA_REQUEST4);
    }

    private void setTableProduct() {
        ScrollView sv = (ScrollView) v.findViewById(R.id.scroll_quantityStock);
        sv.setFillViewport(true);

        tSalesProductQuantityDetailData _tSalesProductQuantityDetailData = new tSalesProductQuantityDetailData();
        dtListDetailProduct = new tSalesProductQuantityDetailBL().getAllDataByHeaderId(_tSalesProductQuantityDetailData.getIntId());

        clsSwipeList swplist;

        swipeListProduct.clear();
        Calendar c = Calendar.getInstance();
        int lYear = c.get(Calendar.YEAR);
        int lMonth = c.get(Calendar.MONTH) + 1;
        int lDay = c.get(Calendar.DATE);

        int totalProduct = 0;
        for (int i = 0; i < dtListDetailProduct.size(); i++) {
            List<tSalesProductQuantityDetailData> dtListProduct = new tSalesProductQuantityDetailBL().getAllDataByHeaderId(dtListDetailProduct.get(i).getIntId());

            if (dtListProduct == null) {
                totalProduct = 0;
            } else {
                totalProduct = dtListProduct.size();
            }
            swplist = new clsSwipeList();

            swplist.set_txtTitle("Nama Product : " + dtListDetailProduct.get(i).getTxtProduct());
            swplist.set_txtDescription("Total Product : " + String.valueOf(totalProduct));

            String expireDate = dtListDetailProduct.get(i).getTxtExpireDate();
            clsMainActivity clsMainMonth = new clsMainActivity();
            String[] parts = expireDate.split("-");
            String part1 = parts[0]; //year
            String part2 = parts[1]; //month
            String part3 = parts[2]; //date

            String month = clsMainMonth.months[Integer.parseInt(part2)];
            expireDate = part3+" - " + month + " - " + part1;

            swplist.set_intPIC("Tanggal Kadaluarsa : " + expireDate);
            swipeList.add(swplist);
        }

        clsMainActivity clsMain = new clsMainActivity();

        mListView = (PullToRefreshSwipeMenuListView) v.findViewById(R.id.listViewQuantityAdd);
        mAdapter = clsMain.setList(getActivity().getApplicationContext(), swipeList);
        mListView.setAdapter(mAdapter);
        mListView.setPullRefreshEnable(false);

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

        mapMenu = new HashMap<String, HashMap>();
        mapMenu.put("0", mapAdd);
        mapMenu.put("1", mapEdit);
        mapMenu.put("2", mapDelete);
    }

    private void save() {
        tSalesProductQuantityData dt = new tSalesProductQuantityData();
        tAbsenUserData absenUserData = new tAbsenUserBL().getDataCheckInActive();
        ModelListview modelListview = new ModelListview();
        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        clsMainActivity _clsMainActivity = new clsMainActivity();

        dt.set_intId(_clsMainActivity.GenerateGuid());
        dt.set_txtQuantityStock(tv_noso.getText().toString());
        dt.set_dtDate(dateFormat.format(cal.getTime()));
        dt.set_OutletCode(absenUserData.get_txtOutletCode());
        dt.set_OutletName(absenUserData.get_txtBranchName());
        dt.set_txtKeterangan(edKeterangan.getText().toString());
        dt.set_UserId(absenUserData.get_txtUserId());
        dt.set_txtRoleId(absenUserData.get_txtRoleId());
        dt.set_intSubmit("1");
        dt.set_intSync("0");
        dt.set_txtBranchCode(absenUserData.get_txtBranchCode());
        dt.set_txtBranchName(absenUserData.get_txtBranchName());
        dt.set_intIdAbsenUser(absenUserData.get_intId());
        dt.set_txtNIK(modelListview.get_NIK());
//      dt.set_txtAfterImg1(phtAfter1);

//        new tSalesProductQuantityHeaderBL().SaveData(dt);
    }

    public void viewQuantityFragment(){
        Intent myIntent = new Intent(getContext(), MainMenu.class);
        myIntent.putExtra("key_view","View Quantity");
        getActivity().finish();
        startActivity(myIntent);
        return;
    }

    private void saveQuantity() {
        int a = listView.getCount();
        String nik = null;
        List<String> item = new ArrayList<>();
        arrdataPriv = new ArrayList<ModelListview>();
        double qntySum=0;
        double qntyNum;
        double value;
        double price;
        String result = "0";

        final HashMap<String, String> HMProduct = new HashMap<String, String>();
        List<String> dataProductKalbe = new ArrayList<>();
        List<mEmployeeSalesProductData> listDataProductKalbe = new mEmployeeSalesProductBL().GetAllData();

        if (listDataProductKalbe.size() > 0) {
            for (mEmployeeSalesProductData dt : listDataProductKalbe) {
                dataProductKalbe.add(dt.get_txtProductBrandDetailGramName());
                HMProduct.put(dt.get_txtProductBrandDetailGramName(), dt.get_txtBrandDetailGramCode());
                HMProduct.put(dt.get_txtBrandDetailGramCode(), dt.get_txtBrandDetailGramCode());
            }
        }

        for (int i = 0; i < modelItems.size(); i++) {
            if (modelItems.get(i).get_value() > 0) {
                ModelListview data = new ModelListview();
                data.set_id(modelItems.get(i).get_id());
                data.set_name(modelItems.get(i).get_name());
                data.set_value(modelItems.get(i).get_value());
                nik = data.set_NIK(String.valueOf(modelItems.get(i).get_NIK()));
                price = Double.parseDouble(modelItems.get(i).get_price());
                value = Double.parseDouble(String.valueOf(modelItems.get(i).get_value()));
                qntyNum =  price * value;
                qntySum += qntyNum;
                result = new clsMainActivity().convertNumberDec2(qntySum);
                arrdataPriv.add(data);
            }
        }

        tSalesProductQuantityData dt = new tSalesProductQuantityData();

        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        clsMainActivity _clsMainActivity = new clsMainActivity();
        mEmployeeSalesProductData _mEmployeeSalesProductData = new mEmployeeSalesProductData();
        tAbsenUserData absenUserData = new tAbsenUserBL().getDataCheckInActive();

        dt.set_intId(new clsMainActivity().GenerateGuid());
        dt.set_txtQuantityStock(tv_noso.getText().toString());
        dt.set_dtDate(dateFormat.format(cal.getTime()));
        dt.set_OutletCode(absenUserData.get_txtOutletCode());
        dt.set_OutletName(absenUserData.get_txtOutletName());
        dt.set_txtKeterangan(edKeterangan.getText().toString());
        dt.set_intSumItem(String.valueOf(arrdataPriv.size()));
        dt.set_intSumAmount(String.valueOf(result));
        dt.set_UserId(absenUserData.get_txtUserId());
        dt.set_txtRoleId(absenUserData.get_txtRoleId());
        dt.set_intSubmit("1");
        dt.set_intSync("0");
        dt.set_txtBranchCode(absenUserData.get_txtBranchCode());
        dt.set_txtBranchName(absenUserData.get_txtBranchName());
        dt.set_intIdAbsenUser(absenUserData.get_intId());
        dt.set_txtNIK(nik);

//        new tSalesProductQuantityHeaderBL().SaveData(dt);

        clsMainBL _clsMainBL = new clsMainBL();

        String selectedOneKNProduct = product.getSelectedItem().toString();

        SQLiteDatabase _db=_clsMainBL.getDb();
        for (int i = 0; i< modelItems.size(); i++) {
            if (modelItems.get(i).get_value() > 0) {
                double prc = Double.valueOf(modelItems.get(i).get_price());
                double itm = Double.valueOf(modelItems.get(i).get_value());
                tSalesProductQuantityDetailData dtDetail = new tSalesProductQuantityDetailData();
                dtDetail.setIntId(_clsMainActivity.GenerateGuid());
                dtDetail.set_dtDate(dateFormat.format(cal.getTime()));
                dtDetail.set_intPrice(modelItems.get(i).get_price());
                dtDetail.set_txtCodeProduct(HMProduct.get(selectedOneKNProduct));
                dtDetail.set_txtKeterangan(edKeterangan.getText().toString());
                dtDetail.setTxtProduct(selectedOneKNProduct);
                dtDetail.setTxtExpireDate(null);
                dtDetail.setTxtQuantity(null /*String.valueOf(editTextQty.getText())*/);
                dtDetail.set_intTotal(new clsMainActivity().convertNumberDec2(prc*itm));
                dtDetail.set_txtNoSo(tv_noso.getText().toString());
                dtDetail.set_intActive("1");
                dtDetail.set_txtNIK(modelItems.get(i).get_NIK());
                new tSalesProductQuantityDetailDA(_db).SaveDatatSalesProductQuantityDetailData(_db, dtDetail);
            }
        }
        _clsMainActivity.showCustomToast(getActivity(), "Saved", true);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
