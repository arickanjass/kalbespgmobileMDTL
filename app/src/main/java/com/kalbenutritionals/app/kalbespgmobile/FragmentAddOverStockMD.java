package com.kalbenutritionals.app.kalbespgmobile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.Spanned;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import bl.clsHelperBL;
import bl.clsMainBL;
import bl.mCounterNumberBL;
import bl.mEmployeeSalesProductBL;
import bl.tOverStockDetailBL;
import bl.tOverStockHeaderBL;
import bl.tSalesProductQuantityDetailBL;
import bl.tSalesProductQuantityHeaderBL;
import bl.tSalesProductQuantityImageBL;
import bl.tUserLoginBL;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import library.spgmobile.common.AppAdapter;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.clsSwipeList;
import library.spgmobile.common.mEmployeeSalesProductData;
import library.spgmobile.common.tOverStockDetailData;
import library.spgmobile.common.tOverStockHeaderData;
import library.spgmobile.common.tSalesProductQuantityDetailData;
import library.spgmobile.common.tSalesProductQuantityHeaderData;
import library.spgmobile.common.tSalesProductQuantityImageData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.visitplanAbsenData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumCounterData;

/**
 * Created by aan.junianto on 15/09/2017.
 */

public class FragmentAddOverStockMD extends Fragment implements IXListViewListener {
    View v;

    //    private ArrayList<ModelListview> modelItems;
//    public static ArrayList<ModelListview> arr = new ArrayList<ModelListview>();
//    private ArrayList<ModelListview> arrdataPriv;
//    ListView listView;
//    int selectedId;
    static List<tOverStockHeaderData> dt;
    static List<tOverStockHeaderData> data;
    //    private FloatingActionButton fab;
//    private List<String> arrData;
    private EditText edKeterangan;
    private ImageView after1, after2;
    private ImageView before1, before2;
    //    private Spinner product;
    private Uri uriImage;
    TextView tv_date, txtHDId;
    TextView tv_noQuantityStock;
    //    List<tSalesProductQuantityHeaderData> dtListDetailProduct;
    List<tOverStockDetailData> dtListProduct;
//    AdapterListProductQuantityDetail AdapterProduct;

    private List<clsSwipeList> swipeList = new ArrayList<>();
//    private ArrayList<clsSwipeList> swipeListProduct = new ArrayList<clsSwipeList>();

//    private HashMap<String, String> HMSubmision = new HashMap<String, String>();

    private static final int CAMERA_REQUEST = 1888;
    private static final int CAMERA_REQUEST2 = 1889;
    private static final int CAMERA_REQUEST3 = 1890;
    private static final int CAMERA_REQUEST4 = 130;
    private static final String IMAGE_DIRECTORY_NAME = "Image Activity";

    private tOverStockHeaderData dtQuantityData;

    private static Bitmap photoAfter1, photoAfter2, photoBefore1, photoBefore2;
    private static ByteArrayOutputStream output = new ByteArrayOutputStream();
    private static byte[] phtAfter1;
    private static byte[] phtAfter2;
    private static byte[] phtBefore1;
    private static byte[] phtBefore2;

    clsMainActivity _clsMainActivity;
    PullToRefreshSwipeMenuListView mListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_add_quantity_stock, container, false);

        txtHDId = (TextView) v.findViewById(R.id.txtHDId);
//        fab = (FloatingActionButton) v.findViewById(R.id.fabQuntity);
//        product = (Spinner) v.findViewById(R.id.txtProduct_quantity);
        edKeterangan = (EditText) v.findViewById(R.id.etKeterangan_quantity);
        Button preview = (Button) v.findViewById(R.id.btnPreviewQuantity);
        tv_date = (TextView) v.findViewById(R.id.txtviewDateQuantity);
        tv_noQuantityStock = (TextView) v.findViewById(R.id.txtNoQuantity);
        after1 = (ImageView) v.findViewById(R.id.imageAfter1);
        after2 = (ImageView) v.findViewById(R.id.imageAfter2);
        before1 = (ImageView) v.findViewById(R.id.imageBefore1);
        before2 = (ImageView) v.findViewById(R.id.imageBefore2);
//        editTextQty = (EditText) v.findViewById(R.id.editTextQty);

        _clsMainActivity = new clsMainActivity();
        dtQuantityData = new tOverStockHeaderData();
        txtHDId.setText(String.valueOf(new clsMainActivity().GenerateGuid()));

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edKeterangan.getWindowToken(), 0);

        // add no Quantity Stock in Textview txtNoQuantity
        List<tOverStockHeaderData> dtLast = new tOverStockHeaderBL().getLastData();
        String noQuantityStock;
        if (dtLast == null || dtLast.size() == 0) {
            noQuantityStock = new mCounterNumberBL().getData(enumCounterData.NoOS);

        } else {
            noQuantityStock = new mCounterNumberBL().getData(enumCounterData.NoOS);
            List<tOverStockHeaderData> dataFirstIsExist = new tOverStockHeaderBL().getDataByNoOverStock(noQuantityStock);
            if (dataFirstIsExist.size() == 1) {
                clsHelper _clsHelper = new clsHelper();
                String oldVersion = dtLast.get(0).get_txtOverStock();
                noQuantityStock = _clsHelper.generateNewId(oldVersion, "-", "6");
            } else {
                noQuantityStock = new mCounterNumberBL().getData(enumCounterData.NoOS);
            }
        }
        tv_noQuantityStock.setText(noQuantityStock);

        // add date in txtviewDateQuantity
//        String timeStamp = new SimpleDateFormat("dd/MM/yyyy",
//                Locale.getDefault()).format(new Date());
//        tv_date.setText(timeStamp);

        //adding date and outlet di menu add
        TextView txtDate = (TextView) v.findViewById(R.id.txtDateQuantity);
        txtDate.setText("Outlet-Date");
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

        phtAfter1 = null;
        phtAfter2 = null;
        phtBefore1 = null;
        phtBefore2 = null;

        if (dtQuantityData.get_intId() != null) {
            byte[] imgAfter1File = dtQuantityData.get_txtAfterImg1();
            if (imgAfter1File != null) {
                Bitmap myBitmap = BitmapFactory.decodeByteArray(imgAfter1File, 0, imgAfter1File.length);
                after1.setImageBitmap(myBitmap);
            }

            byte[] imgAfter2File = dtQuantityData.get_txtAfterImg2();
            if (imgAfter2File != null) {
                Bitmap myBitmap = BitmapFactory.decodeByteArray(imgAfter2File, 0, imgAfter2File.length);
                after2.setImageBitmap(myBitmap);
            }

            byte[] imgBefore1File = dtQuantityData.get_txtBeforeImg1();
            if (imgBefore1File != null) {
                Bitmap myBitmap = BitmapFactory.decodeByteArray(imgBefore1File, 0, imgBefore1File.length);
                before1.setImageBitmap(myBitmap);
            }

            byte[] imgBefore2File = dtQuantityData.get_txtBeforeImg2();
            if (imgBefore2File != null) {
                Bitmap myBitmap = BitmapFactory.decodeByteArray(imgBefore2File, 0, imgBefore2File.length);
                before2.setImageBitmap(myBitmap);
            }
        }

        if (photoAfter1 != null) {
            after1.setImageBitmap(photoAfter1);
            photoAfter1.compress(Bitmap.CompressFormat.PNG, 100, output);
            phtAfter1 = output.toByteArray();
        }

        if (photoAfter2 != null) {
            after2.setImageBitmap(photoAfter2);
            photoAfter2.compress(Bitmap.CompressFormat.PNG, 100, output);
            phtAfter2 = output.toByteArray();
        }

        if (photoBefore1 != null) {
            before1.setImageBitmap(photoBefore1);
            photoBefore1.compress(Bitmap.CompressFormat.PNG, 100, output);
            phtBefore1 = output.toByteArray();
        }

        if (photoBefore2 != null) {
            before2.setImageBitmap(photoBefore2);
            photoBefore2.compress(Bitmap.CompressFormat.PNG, 100, output);
            phtBefore2 = output.toByteArray();
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
                captureAfterImage2();
            }
        });

        before1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureBeforeImage1();
            }
        });

        before2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureBeforeImage2();
            }
        });

        // click Button add product Quantity
        Button btnAddQuantity = (Button) v.findViewById(R.id.btnAddQuantity);
        btnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpAddQuantity(new tOverStockDetailData());
            }
        });

        // click button preview
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (edKeterangan.getText().toString().equals("")) {
//                    _clsMainActivity.showCustomToast(getActivity(), "Please fill Description...", false);
//                } else if (phtAfter1 == null && phtAfter2 == null) {
//                    _clsMainActivity.showCustomToast(getContext(), "Please take at least 1 photo for after", false);
//                } else if (phtBefore1 == null && phtBefore2 == null) {
//                    _clsMainActivity.showCustomToast(getContext(), "Please take at least 1 photo for before", false);
//                } else

                if (dtListProduct.size() == 0) {
                    _clsMainActivity.showCustomToast(getContext(), "Please Add least 1 Product...", false);
                } else {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Confirm");
                    alertDialog.setMessage("Are you sure?");
                    alertDialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            save();
//                            savePicture1();
//                            savePicture2();
//                            savePicture3();
//                            savePicture4();
                            viewQuantityFragment();

                            _clsMainActivity.showCustomToast(getActivity(), "Submit", true);
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

        edKeterangan.setFilters(new InputFilter[]{
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

        TableProduct();
        return v;
    }

    private void popUpAddQuantity(final tOverStockDetailData dataDetail) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.popup_add_quantity, null);
        final HashMap<String, String> HMProduct = new HashMap<String, String>();
        final EditText editTextQty = (EditText) promptView.findViewById(R.id.editTextQty);
        final Spinner spnKalbeProduct = (Spinner) promptView.findViewById(R.id.spnProductQuantity);
        final DatePicker dp = (DatePicker) promptView.findViewById(R.id.dp_expire_date);
        final EditText editTextKeterangan = (EditText) promptView.findViewById(R.id.editTextKeterangan);

        int maxLength = 3;
        editTextQty.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});

//        List<tSalesProductQuantityDetailData> dataProduct = null;

        List<String> dataProductKalbe = new ArrayList<>();
        List<mEmployeeSalesProductData> listDataProductKalbe = new mEmployeeSalesProductBL().getAllDataNotWhere();
//        modelItems = new ArrayList<>();

//        editTextQty.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                editTextQty.setText("");
//            }
//        });

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

        // for edit data in popupAddQuantity
        int index = 0;
        if (dataDetail.getIntId() != null) {

            String item = dataDetail.getTxtProduct();
            for (int i = 0; i < spnKalbeProduct.getAdapter().getCount() - 1; i++) {
                if (spnKalbeProduct.getItemAtPosition(i).equals(item)) {
                    index = i;
                }
            }
            spnKalbeProduct.setSelection(index);
            int year = 0;
            int month = 0;
            int day = 0;
            String stringDatedb = dataDetail.getTxtExpireDate();
            if (!stringDatedb.equals("null")) {
                String[] parts = stringDatedb.split("-");
                year = Integer.parseInt(parts[0]);
                month = Integer.parseInt(parts[1]);
                day = Integer.parseInt(parts[2]);
            }
            dp.updateDate(year, month - 1, day);
            editTextQty.setText(dataDetail.getTxtQuantity());
            editTextKeterangan.setText(dataDetail.get_txtKeterangan());
        }

        // set date min today
//        dp.setMinDate(System.currentTimeMillis() - 1000);

        String qtyProduct = null;
        qtyProduct = editTextQty.getText().toString();

        boolean validQty = true;

        if (qtyProduct.equals("0") || qtyProduct.equals("00") || qtyProduct.equals("000")) {
            validQty = false;
        }

        if(validQty){

        } else{

        }

        // muncul dialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                clsMainBL _clsMainBL = new clsMainBL();
//                                        SQLiteDatabase _db = _clsMainBL.getDb();

                                String qtyProduct = null;
                                qtyProduct = editTextQty.getText().toString();

                                boolean validQty = true;

                                if (qtyProduct.equals("0") || qtyProduct.equals("00") || qtyProduct.equals("000")) {
                                    validQty = true;
                                    qtyProduct = "1";
                                }

                                if(validQty){
                                    String selectedOneKNProduct = spnKalbeProduct.getSelectedItem().toString();
                                    tUserLoginData dtUser = new tUserLoginBL().getUserActive();
                                    java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                    Calendar cal = Calendar.getInstance();

                                    int day = dp.getDayOfMonth();
                                    int month = dp.getMonth() + 1;
                                    int year = dp.getYear();
                                    final String expireDate = year + "-" + month + "-" + day;

                                    String keterangan;
                                    keterangan = editTextKeterangan.getText().toString();

                                    if (keterangan.length() == 0) {
                                        keterangan = "";
                                    }

                                    tOverStockDetailData data = new tOverStockDetailData();
                                    if (dataDetail.getIntId() != null) {
                                        data.setIntId(dataDetail.getIntId());
                                    } else {
                                        data.setIntId(_clsMainActivity.GenerateGuid());
                                    }

                                    data.set_txtOverStock(tv_noQuantityStock.getText().toString());
                                    data.set_dtDate(dateFormat.format(cal.getTime()));
                                    data.set_txtCodeProduct(HMProduct.get(selectedOneKNProduct));
                                    data.set_txtKeterangan(keterangan);
                                    data.setTxtProduct(selectedOneKNProduct);
                                    data.setTxtExpireDate(expireDate);
                                    data.setTxtQuantity(qtyProduct);
                                    data.set_intPrice(HMProduct.get(HMProduct.get(selectedOneKNProduct)));

                                    double prc = Double.valueOf(HMProduct.get(HMProduct.get(selectedOneKNProduct)));
                                    double itm = Double.valueOf(qtyProduct);

                                    data.set_intTotal(_clsMainActivity.convertNumberDec2(prc * itm));
                                    data.set_txtNIK(dtUser.get_txtUserId());

                                    new tOverStockDetailBL().saveData(data);
//                                        new tSalesProductQuantityDetailDA(_db).SaveDatatSalesProductQuantityDetailData(_db, data);

                                    dialog.dismiss();
                                    TableProduct();

                                    _clsMainActivity.showCustomToast(getActivity(), "Saved", true);
                                } else {
                                    _clsMainActivity.showCustomToast(getActivity(), "Quantity Cannot 0", true);
                                }
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            if (resultCode == -1) {
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath();

                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCaptureAfterImage1(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            } else {
                try {
                    photoAfter1 = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /*
            Bitmap photoAfter1 = (Bitmap) data.getExtras().get("data");
            Bitmap photo_view = Bitmap.createScaledBitmap(photoAfter1, 150, 150, true);
            photoAfter1.compress(Bitmap.CompressFormat.PNG, 100, output);
            after1.setImageBitmap(photo_view);
            phtAfter1 = output.toByteArray();*/
        } else if (requestCode == CAMERA_REQUEST2) {
            if (resultCode == -1) {
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath();

                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCaptureAfterImage2(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            } else {
                try {
                    photoAfter2 = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == CAMERA_REQUEST3) {
            if (resultCode == -1) {
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath();

                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCaptureBeforeImage1(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            } else {
                try {
                    photoBefore1 = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == CAMERA_REQUEST4) {
            if (resultCode == -1) {
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath();

                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCaptureBeforeImage2(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 0) {
                new clsMainActivity().showCustomToast(getContext(), "User canceled to capture image", false);
            } else {
                try {
                    photoBefore2 = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // preview image After 1
    private void previewCaptureAfterImage1(Bitmap photo) {
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
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Bitmap photo_view = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
            phtAfter1 = output.toByteArray();
            after1.setImageBitmap(photo_view);

            if (dtQuantityData == null) {
                dtQuantityData.set_txtAfterImg1(phtAfter1);
            } else {
                dtQuantityData.set_txtAfterImg1(phtAfter1);
            }
            List<tOverStockHeaderData> tSalesProductQuantityDatas = new ArrayList<>();
            tSalesProductQuantityDatas.add(dtQuantityData);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    // preview image after 2
    private void previewCaptureAfterImage2(Bitmap photo) {
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
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Bitmap photo_view = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
            phtAfter2 = output.toByteArray();
            after2.setImageBitmap(photo_view);

            if (dtQuantityData == null) {
                dtQuantityData.set_txtAfterImg2(phtAfter2);
            } else {
                dtQuantityData.set_txtAfterImg2(phtAfter2);
            }
            List<tOverStockHeaderData> tSalesProductQuantityDatas = new ArrayList<>();
            tSalesProductQuantityDatas.add(dtQuantityData);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    // preview image before 1
    private void previewCaptureBeforeImage1(Bitmap photo) {
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
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Bitmap photo_view = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
            phtBefore1 = output.toByteArray();
            before1.setImageBitmap(photo_view);

            if (dtQuantityData == null) {
                dtQuantityData.set_txtBeforeImg1(phtBefore1);
            } else {
                dtQuantityData.set_txtBeforeImg1(phtBefore1);
            }
            List<tOverStockHeaderData> tSalesProductQuantityDatas = new ArrayList<>();
            tSalesProductQuantityDatas.add(dtQuantityData);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    // preview image before 2
    private void previewCaptureBeforeImage2(Bitmap photo) {
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
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Bitmap photo_view = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
            phtBefore2 = output.toByteArray();
            before2.setImageBitmap(photo_view);

            if (dtQuantityData == null) {
                dtQuantityData.set_txtBeforeImg2(phtBefore2);
            } else {
                dtQuantityData.set_txtBeforeImg2(phtBefore2);
            }
            List<tOverStockHeaderData> tSalesProductQuantityDatas = new ArrayList<>();
            tSalesProductQuantityDatas.add(dtQuantityData);
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
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "tmp_act" + ".png");
        return mediaFile;
    }

    protected void captureAfterImage1() {
        uriImage = getOutputMediaFileUri();
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void captureAfterImage2() {
        uriImage = getOutputMediaFileUri();
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(cameraIntent, CAMERA_REQUEST2);
    }

    protected void captureBeforeImage1() {
        uriImage = getOutputMediaFileUri();
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(cameraIntent, CAMERA_REQUEST3);
    }

    protected void captureBeforeImage2() {
        uriImage = getOutputMediaFileUri();
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(cameraIntent, CAMERA_REQUEST4);
    }

    private void TableProduct() {
        ScrollView sv = (ScrollView) v.findViewById(R.id.scroll_quantityStock);
        sv.setFillViewport(true);

//        dtListDetailProduct = new tSalesProductQuantityHeaderBL().getDataByNoQuantityStock(tv_noQuantityStock.getText().toString());
        dtListProduct = new tOverStockDetailBL().GetDataByNoOverStock(tv_noQuantityStock.getText().toString());

        clsSwipeList swplist;

        swipeList.clear();

        for (tOverStockDetailData data : dtListProduct) {
//            List<tSalesProductQuantityDetailData> dtListProduct = new tSalesProductQuantityDetailBL().GetDataByNoQuantityStock(dtListDetailProduct.get(i).get_txtQuantityStock());
//            List<tSalesProductQuantityDetailData> dtListProduct = new tSalesProductQuantityDetailBL().GetDataByNoQuantityStock(noSO);

            swplist = new clsSwipeList();

            String ed = new clsMainActivity().giveFormatDate2(data.getTxtExpireDate());


            swplist.set_txtTitle("Product : " + data.getTxtProduct());
            swplist.set_txtDescription("Total Product : " + data.getTxtQuantity() +" pcs");
            swplist.set_txtDescription2("ED : " + ed);
            swplist.set_txtDescription3("");
            swipeList.add(swplist);
        }

        clsMainActivity clsMain = new clsMainActivity();

        mListView = (PullToRefreshSwipeMenuListView) v.findViewById(R.id.listViewQuantity);
        AppAdapter mAdapter = clsMainActivity.setListNearEdTr(getActivity().getApplicationContext(), swipeList);
        mListView.setAdapter(mAdapter);
        mListView.setPullRefreshEnable(false);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
//        mHandler = new Handler();

        HashMap<String, String> mapEdit = new HashMap<String, String>();
        HashMap<String, String> mapDelete = new HashMap<String, String>();

        mapEdit.put("name", "Edit");
        mapEdit.put("bgColor", "#3498db");

        mapDelete.put("name", "Delete");
        mapDelete.put("bgColor", "#FF0000");

        Map<String, HashMap> mapMenu = new HashMap<String, HashMap>();
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

        setListViewHeightBasedOnItems(mListView);
    }

    private void editList(Context ctx, int position) {
        popUpAddQuantity(dtListProduct.get(position));
    }

    private void deleteList(Context ctx, int position) {
        final tOverStockDetailData dtDetail = dtListProduct.get(position);

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Confirm");
        alertDialog.setMessage("Are you sure?");
        alertDialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new tOverStockDetailBL().deleteData(dtDetail);
                TableProduct();
                _clsMainActivity.showCustomToast(getActivity(), dtDetail.getTxtProduct()+" was deleted", true);
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

    private void save() {
        List<mEmployeeSalesProductData> employeeSalesProductDataList = new mEmployeeSalesProductBL().GetAllData();
//        dtQuantityData = new tSalesProductQuantityHeaderData();
//        tAbsenUserData absenUserData = new tAbsenUserBL().getDataCheckInActive();
        tUserLoginData dataUserActive = new tUserLoginBL().getUserActive();
        visitplanAbsenData absenUserData = new clsHelperBL().getDataCheckInActive();
//        modelItems = new ArrayList<ModelListview>();
        String noQtyStock = tv_noQuantityStock.getText().toString();
        List<tOverStockDetailData> productDetail = new tOverStockDetailBL().GetDataByNoOverStock(noQtyStock);

//        arrdataPriv = new ArrayList<ModelListview>();
        double qntySum = 0;
        double qntyNum;
        double value;
        double price;
        String result = "0";
        String resultItem = "0";

        for (int i = 0; i < productDetail.size(); i++) {
            price = Double.parseDouble(String.valueOf(productDetail.get(i).get_intPrice()));
            value = Double.parseDouble(String.valueOf(productDetail.get(i).getTxtQuantity()));
            qntyNum = price * value;
            qntySum += qntyNum;
            result = new clsMainActivity().convertNumberDec2(qntySum);
        }

        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        clsMainActivity _clsMainActivity = new clsMainActivity();

        dtQuantityData.set_intSumItem(String.valueOf(productDetail.size()));
        dtQuantityData.set_intSumAmount(String.valueOf(result));

        dtQuantityData.set_intId(txtHDId.getText().toString());
        dtQuantityData.set_txtOverStock(tv_noQuantityStock.getText().toString());
        dtQuantityData.set_dtDate(dateFormat.format(cal.getTime()));
        dtQuantityData.set_OutletCode(absenUserData.get_txtOutletCode());
        dtQuantityData.set_OutletName(absenUserData.get_txtOutletName());
        dtQuantityData.set_txtKeterangan(edKeterangan.getText().toString());
        dtQuantityData.set_UserId(absenUserData.get_txtUserId());
        dtQuantityData.set_txtRoleId(absenUserData.get_txtRoleId());
        dtQuantityData.set_txtBranchCode(absenUserData.get_txtBranchCode());
        dtQuantityData.set_txtBranchName(absenUserData.get_txtBranchName());
        dtQuantityData.set_intIdAbsenUser(absenUserData.get_intId());
//        dtQuantityData.set_txtAfterImg1(phtAfter1);
//        dtQuantityData.set_txtAfterImg2(phtAfter2);
//        dtQuantityData.set_txtBeforeImg1(phtBefore1);
//        dtQuantityData.set_txtBeforeImg2(phtBefore2);
        dtQuantityData.set_txtNIK(dataUserActive.get_TxtEmpId());
        dtQuantityData.set_intSubmit("1");
        dtQuantityData.set_intSync("0");
        List<tOverStockHeaderData> dtList = new ArrayList<>();
        dtList.add(dtQuantityData);
        new tOverStockHeaderBL().SaveData2(dtList);
    }

    private void savePicture1() {
        tSalesProductQuantityImageData dataImage = new tSalesProductQuantityImageData();
        String headerId = tv_noQuantityStock.getText().toString();

        if (phtAfter1 != null) {
            dataImage.set_txtId(_clsMainActivity.GenerateGuid());
            dataImage.set_txtHeaderId(tv_noQuantityStock.getText().toString());
            dataImage.set_txtImage(phtAfter1);
            dataImage.set_intPosition("1");
            dataImage.set_txtType("After");

            List<tSalesProductQuantityImageData> dtListImage = new ArrayList<>();
            dtListImage.add(dataImage);
            new tSalesProductQuantityImageBL().SaveData(dtListImage);
        }
    }

    private void savePicture2() {
        tSalesProductQuantityImageData dataImage = new tSalesProductQuantityImageData();
        String headerId = tv_noQuantityStock.getText().toString();

        if (phtAfter2 != null) {
            dataImage.set_txtId(_clsMainActivity.GenerateGuid());
            dataImage.set_txtHeaderId(tv_noQuantityStock.getText().toString());
            dataImage.set_txtImage(phtAfter2);
            dataImage.set_intPosition("2");
            dataImage.set_txtType("After");

            List<tSalesProductQuantityImageData> dtListImage = new ArrayList<>();
            dtListImage.add(dataImage);
            new tSalesProductQuantityImageBL().SaveData(dtListImage);
        }
    }

    private void savePicture3() {
        tSalesProductQuantityImageData dataImage = new tSalesProductQuantityImageData();
        String headerId = tv_noQuantityStock.getText().toString();

        if (phtBefore1 != null) {
            dataImage.set_txtId(_clsMainActivity.GenerateGuid());
            dataImage.set_txtHeaderId(tv_noQuantityStock.getText().toString());
            dataImage.set_txtImage(phtBefore1);
            dataImage.set_intPosition("1");
            dataImage.set_txtType("Before");

            List<tSalesProductQuantityImageData> dtListImage = new ArrayList<>();
            dtListImage.add(dataImage);
            new tSalesProductQuantityImageBL().SaveData(dtListImage);
        }
    }

    private void savePicture4() {
        tSalesProductQuantityImageData dataImage = new tSalesProductQuantityImageData();
        String headerId = tv_noQuantityStock.getText().toString();

        if (phtBefore2 != null) {
            dataImage.set_txtId(_clsMainActivity.GenerateGuid());
            dataImage.set_txtHeaderId(tv_noQuantityStock.getText().toString());
            dataImage.set_txtImage(phtBefore2);
            dataImage.set_intPosition("2");
            dataImage.set_txtType("Before");

            List<tSalesProductQuantityImageData> dtListImage = new ArrayList<>();
            dtListImage.add(dataImage);
            new tSalesProductQuantityImageBL().SaveData(dtListImage);
        }
    }

    public void viewQuantityFragment() {
//        Bundle bundle = new Bundle();
//        bundle.putString("key_view", "View_QuantityStock");
//        FragmentAddQuantityStock fragmentAddQuantityStock = new FragmentAddQuantityStock();
//        fragmentAddQuantityStock.setArguments(bundle);
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.frame, new FragmentViewQuantityStock()).commit();

        Intent myIntent = new Intent(getContext(), MainMenu.class);
        myIntent.putExtra("key_view", "View Over Stock MD");
        getActivity().finish();
        startActivity(myIntent);
//        return;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
}
