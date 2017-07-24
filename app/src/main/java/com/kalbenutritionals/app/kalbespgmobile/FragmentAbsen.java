package com.kalbenutritionals.app.kalbespgmobile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

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

import bl.mEmployeeAreaBL;
import bl.mEmployeeBranchBL;
import bl.mMenuBL;
import bl.tAbsenUserBL;
import bl.tDeviceInfoUserBL;
import bl.tUserLoginBL;
import library.spgmobile.common.mEmployeeAreaData;
import library.spgmobile.common.mEmployeeBranchData;
import library.spgmobile.common.mMenuData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;

import static android.content.Context.LOCATION_SERVICE;

public class FragmentAbsen extends Fragment implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    private Location mLastLocation;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private static final String TAG = FragmentAbsen.class.getSimpleName();
    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;
    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;
    private LocationRequest mLocationRequest;
    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 3000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters
    private Spinner spnOutlet;
    private Spinner spnBranch;
    private List<String> arrData;
    private String Long;
    private String Lat;
    private String Acc;
    private static final int CAMERA_CAPTURE_IMAGE1_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_IMAGE2_REQUEST_CODE = 130;
    private ImageView imgPrevNoImg1;
    private ImageView imgPrevNoImg2;
    private static final String IMAGE_DIRECTORY_NAME = "Image Sales";
    private HashMap<String, String> HMbranch = new HashMap<String, String>();
    private HashMap<String, String> HMoutlet = new HashMap<String, String>();
    private HashMap<String, String> HMoutletLang = new HashMap<String, String>();
    private HashMap<String, String> HMoutletLat = new HashMap<String, String>();
    final String finalFile = null;
    private TextView lblLong;
    private TextView lblLang;
    private TextView lblAcc;
    private TextView txtHDId;
    private ArrayAdapter<String> dataAdapterBranch;
    private ArrayAdapter<String> dataAdapterOutlet;
    private tAbsenUserBL _tAbsenUserBL;
    Options options;
    private tAbsenUserData dttAbsenUserData;
    private Button btnRefreshMaps, btnPopupMap;
    private Button btnCheckIn;
    private String MenuID;
    private String[] arrdefaultBranch = new String[]{"Branch"};
    private String[] arrdefaultOutlet = new String[]{"Outlet"};


    private String nameBranch;
    private String nameOutlet;
    private String branchCode;
    private String outletCode;
    private String myClass;
    private Class<?> clazz = null;

    private Uri uriImage;
    private int countActivity;

    clsMainActivity _clsMainActivity = new clsMainActivity();

    View v;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public static Bitmap resizeBitMapImage1(String filePath, int targetWidth, int targetHeight) {
        Bitmap bitMapImage = null;
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            double sampleSize = 0;
            Boolean scaleByHeight = Math.abs(options.outHeight - targetHeight) >= Math.abs(options.outWidth
                    - targetWidth);
            if (options.outHeight * options.outWidth * 2 >= 1638) {
                sampleSize = scaleByHeight ? options.outHeight / targetHeight : options.outWidth / targetWidth;
                sampleSize = (int) Math.pow(2d, Math.floor(Math.log(sampleSize) / Math.log(2d)));
            }
            options.inJustDecodeBounds = false;
            options.inTempStorage = new byte[128];
            while (true) {
                try {
                    options.inSampleSize = (int) sampleSize;
                    bitMapImage = BitmapFactory.decodeFile(filePath, options);
                    break;
                } catch (Exception ex) {
                    try {
                        sampleSize = sampleSize * 2;
                    } catch (Exception ex1) {

                    }
                }
            }
        } catch (Exception ex) {

        }
        return bitMapImage;
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Absen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.kalbenutritionals.app.kalbespgmobile/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public class MyAdapter extends ArrayAdapter<String> {
        private List<String> arrayDataAdapyter;
        private Context Ctx;

        public List<String> getArrayDataAdapyter() {
            return arrayDataAdapyter;
        }

        public void setArrayDataAdapyter(List<String> arrayDataAdapyter) {
            this.arrayDataAdapyter = arrayDataAdapyter;
        }

        public Context getCtx() {
            return Ctx;
        }

        public void setCtx(Context ctx) {
            Ctx = ctx;
        }

        public MyAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            setCtx(context);
            setArrayDataAdapyter(objects);
            // TODO Auto-generated constructor stub
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
            if (arrData.size() > 0) {
                TextView label = (TextView) row.findViewById(R.id.tvTitle);
                //label.setText(arrData.get(position));
                label.setText(getArrayDataAdapyter().get(position));
                TextView sub = (TextView) row.findViewById(R.id.tvDesc);
                sub.setVisibility(View.GONE);
                sub.setVisibility(View.GONE);
                label.setTextColor(new Color().parseColor("#000000"));
                row.setBackgroundColor(new Color().parseColor("#FFFFFF"));
            }
            return row;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_absen, container, false);

        txtHDId = (TextView) v.findViewById(R.id.txtHDId);
        btnRefreshMaps = (Button) v.findViewById(R.id.btnRefreshMaps);
        btnCheckIn = (Button) v.findViewById(R.id.buttonCheckIn);
        btnPopupMap = (Button) v.findViewById(R.id.viewMap);
        spnOutlet = (Spinner) v.findViewById(R.id.spnOutlet);
        spnBranch = (Spinner) v.findViewById(R.id.spnType);
        imgPrevNoImg1 = (ImageView) v.findViewById(R.id.imageViewCamera1);
        imgPrevNoImg2 = (ImageView) v.findViewById(R.id.imageViewCamera2);
        lblLong = (TextView) v.findViewById(R.id.tvLong);
        lblLang = (TextView) v.findViewById(R.id.tvLat);
        lblAcc = (TextView) v.findViewById(R.id.tvAcc);
        options = new Options();
        options.inSampleSize = 2;
        _tAbsenUserBL = new tAbsenUserBL();
        dttAbsenUserData = _tAbsenUserBL.getDataCheckInActive();
        lblLong.setText("");
        lblLang.setText("");
        lblAcc.setText("");
        MenuID = "mnAbsenKBN";

        final mMenuData dtmenuData = new mMenuBL().getMenuDataByMenuName(MenuID);
        btnRefreshMaps.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                displayLocation(mLastLocation);
                getLocation();
                if (mLastLocation!=null){
                    displayLocation(mLastLocation);
                }
                new clsMainActivity().showCustomToast(getContext(), "Location Updated", true);
            }
        });

        getLocation();

        if (mLastLocation!=null){
            displayLocation(mLastLocation);
        }

        btnPopupMap.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                final View promptView = layoutInflater.inflate(R.layout.popup_map_absen, null);
                btnPopupMap.setEnabled(false);
                GoogleMap mMap = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    mMap = ((MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map)).getMap();

                    if (mMap == null) {
                        mMap = ((MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map)).getMap();
                    }

                    double latitude = Double.parseDouble(String.valueOf(lblLang.getText()));
                    double longitude = Double.parseDouble(String.valueOf(lblLong.getText()));
                    double accurate = Double.parseDouble(String.valueOf(lblAcc.getText()));

                    double latitudeOutlet = Double.parseDouble(HMoutletLat.get(spnOutlet.getSelectedItem().toString()));
                    double longitudeOutlet = Double.parseDouble(HMoutletLang.get(spnOutlet.getSelectedItem().toString()));

                    MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Your Location");

                    MarkerOptions markerOutlet = new MarkerOptions().position(new LatLng(latitudeOutlet, longitudeOutlet)).title("Outlet Location");

                    marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                    final LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    builder.include(marker.getPosition());
                    builder.include(markerOutlet.getPosition());
                    LatLngBounds bounds = builder.build();

                    mMap.clear();
                    mMap.addMarker(marker);
                    mMap.addMarker(markerOutlet);
                    //CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(19).build();

                    final GoogleMap finalMMap = mMap;
                    mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

                        @Override
                        public void onCameraChange(CameraPosition arg0) {
                            // Move camera.
                            finalMMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 60));
                            // Remove listener to prevent position reset on camera move.
                            finalMMap.setOnCameraChangeListener(null);
                        }
                    });


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setView(promptView);
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            btnPopupMap.setEnabled(true);
                                            MapFragment f = null;
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                                f = (MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map);
                                            }
                                            if (f != null) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                                    (getActivity()).getFragmentManager().beginTransaction().remove(f).commit();
                                                }
                                            }

                                            dialog.dismiss();
                                        }
                                    });
                    final AlertDialog alertD = alertDialogBuilder.create();

                    Location locationA = new Location("point A");

                    locationA.setLatitude(latitude);
                    locationA.setLongitude(longitude);

                    Location locationB = new Location("point B");

                    locationB.setLatitude(latitudeOutlet);
                    locationB.setLongitude(longitudeOutlet);

                    float distance = locationA.distanceTo(locationB);

                    alertD.setTitle(String.valueOf((int) Math.ceil(distance)) + " meters");
                    alertD.show();
                }

            }
        });

        List<mEmployeeBranchData> listDataBranch = new mEmployeeBranchBL().GetAllData();
        List<mEmployeeAreaData> listDataArea = new mEmployeeAreaBL().GetAllData();
        if (checkPlayServices()) {
            buildGoogleApiClient();
        }

        // First we need to check availability of play services
        imgPrevNoImg1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameBranch = spnBranch.getSelectedItem().toString();
                String nameOutlet = spnOutlet.getSelectedItem().toString();
                String branchCode = HMbranch.get(nameBranch);
                String outletCode = HMoutlet.get(nameOutlet);
                tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
                String idUserActive = String.valueOf(dataUserActive.get_txtUserId());
                String idRoleActive = String.valueOf(dataUserActive.get_txtRoleId());
                List<tDeviceInfoUserData> dataDeviceInfoUser = new tDeviceInfoUserBL().getData(1);
                String deviceInfo = String.valueOf(dataDeviceInfoUser.get(0).get_txtDeviceId());
                List<tAbsenUserData> absenUserDatas = new ArrayList<tAbsenUserData>();
                if (dttAbsenUserData == null) {
                    dttAbsenUserData = new tAbsenUserData();
                }
                dttAbsenUserData.set_intId(txtHDId.getText().toString());
                dttAbsenUserData.set_intSubmit("0");
                dttAbsenUserData.set_intSync("0");
                dttAbsenUserData.set_txtAbsen("0");
                dttAbsenUserData.set_dtDateCheckOut("-");
                dttAbsenUserData.set_txtAccuracy(lblAcc.getText().toString());
                dttAbsenUserData.set_txtBranchCode(branchCode);
                dttAbsenUserData.set_txtBranchName(nameBranch);
                dttAbsenUserData.set_txtLatitude(lblLang.getText().toString());
                dttAbsenUserData.set_txtLongitude(lblLong.getText().toString());
                dttAbsenUserData.set_txtOutletCode(outletCode);
                dttAbsenUserData.set_txtOutletName(nameOutlet);
                dttAbsenUserData.set_txtDeviceId(deviceInfo);
                dttAbsenUserData.set_txtUserId(idUserActive);
                dttAbsenUserData.set_txtRoleId(idRoleActive);
                absenUserDatas.add(dttAbsenUserData);
                new tAbsenUserBL().saveData(absenUserDatas);
                captureImage1();
            }
        });

        imgPrevNoImg2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String nameBranch = spnBranch.getSelectedItem().toString();
                String nameOutlet = spnOutlet.getSelectedItem().toString();
                String branchCode = HMbranch.get(nameBranch);
                String outletCode = HMoutlet.get(nameOutlet);
                if (dttAbsenUserData == null) {
                    dttAbsenUserData = new tAbsenUserData();
                }
                tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
                String idUserActive = String.valueOf(dataUserActive.get_txtUserId());
                String idRoleActive = String.valueOf(dataUserActive.get_txtRoleId());
                List<tDeviceInfoUserData> dataDeviceInfoUser = new tDeviceInfoUserBL().getData(1);
                String deviceInfo = String.valueOf(dataDeviceInfoUser.get(0).get_txtDeviceId());
                List<tAbsenUserData> absenUserDatas = new ArrayList<tAbsenUserData>();
                dttAbsenUserData.set_intId(txtHDId.getText().toString());
                dttAbsenUserData.set_intSubmit("0");
                dttAbsenUserData.set_intSync("0");
                dttAbsenUserData.set_txtAbsen("0");//
                dttAbsenUserData.set_txtAccuracy(lblAcc.getText().toString());
                dttAbsenUserData.set_txtBranchCode(branchCode);
                dttAbsenUserData.set_txtBranchName(nameBranch);
                dttAbsenUserData.set_txtLatitude(lblLang.getText().toString());
                dttAbsenUserData.set_txtLongitude(lblLong.getText().toString());
                dttAbsenUserData.set_txtOutletCode(outletCode);
                dttAbsenUserData.set_txtOutletName(nameOutlet);
                dttAbsenUserData.set_txtDeviceId(deviceInfo);
                dttAbsenUserData.set_txtUserId(idUserActive);
                dttAbsenUserData.set_txtRoleId(idRoleActive);
                absenUserDatas.add(dttAbsenUserData);
                new tAbsenUserBL().saveData(absenUserDatas);
                captureImage2();
            }
        });
        arrData = new ArrayList<String>();
        if (listDataBranch.size() > 0) {
            for (mEmployeeBranchData dt : listDataBranch) {
                arrData.add(dt.get_txtBranchName());
                HMbranch.put(dt.get_txtBranchName(), dt.get_txtBranchCode());
            }
            dataAdapterBranch = new MyAdapter(getContext(), R.layout.custom_spinner, arrData);
            spnBranch.setAdapter(dataAdapterBranch);
        }
        arrData = new ArrayList<String>();
        if (listDataArea.size() > 0) {
            for (mEmployeeAreaData dt : listDataArea) {
                arrData.add(dt.get_txtOutletName());
                HMoutlet.put(dt.get_txtOutletName(), dt.get_txtOutletCode());
                HMoutletLang.put(dt.get_txtOutletName(), dt.get_txtLongitude());
                HMoutletLat.put(dt.get_txtOutletName(), dt.get_txtLatitude());
            }
            dataAdapterOutlet = new MyAdapter(getContext(), R.layout.custom_spinner, arrData);
            spnOutlet.setAdapter(dataAdapterOutlet);
        }

        if (dttAbsenUserData != null) {
            if (dttAbsenUserData.get_intSubmit().equals("1")) {
                spnBranch.setEnabled(false);
                spnOutlet.setEnabled(false);
                imgPrevNoImg1.setClickable(false);
                imgPrevNoImg2.setClickable(false);
            }


            txtHDId.setText(dttAbsenUserData.get_intId());
            int intPosition = new clsMainActivity().getSpinnerPositionByValue(HMbranch, dttAbsenUserData.get_txtBranchCode(), spnBranch);
            spnBranch.setSelection(intPosition);
            intPosition = new clsMainActivity().getSpinnerPositionByValue(HMoutlet, dttAbsenUserData.get_txtOutletCode(), spnOutlet);
            spnOutlet.setSelection(intPosition);
            lblAcc.setText(dttAbsenUserData.get_txtAccuracy());
            lblLang.setText(dttAbsenUserData.get_txtLatitude());
            lblLong.setText(dttAbsenUserData.get_txtLongitude());

            double latitude = Double.valueOf(lblLang.getText().toString());
            double longitude = Double.valueOf(lblLong.getText().toString());
            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Updating Location!");
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            try {
                initilizeMap();
                // Changing map type
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            } catch (Exception e) {
                e.printStackTrace();
            }
            mMap.clear();
            mMap.addMarker(marker);
            if (dttAbsenUserData.get_intSubmit().equals("1")) {

            }
        } else {
            int IdAbsen = _tAbsenUserBL.getContactsCount() + 1;
            txtHDId.setText(String.valueOf(IdAbsen));
//            displayLocation();
        }

        // Checking camera availability
        if (!isDeviceSupportCamera()) {
        }

        dttAbsenUserData = _tAbsenUserBL.getDataCheckInActive();
        if (dttAbsenUserData != null) {
            if (dttAbsenUserData.get_intSubmit().equals("1")) {
            } else {
                // Kalau ga ada harus check in dulu
            }
        } else {
            // Kalau ga ada harus check in dulu
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(getContext()).addApi(AppIndex.API).build();
        btnCheckIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                myClass = "com.kalbenutritionals.app.kalbespgmobile.MainMenu";
                ;
                MenuID = "mnCheckinKBN";
                clazz = null;

                myClass = "com.kalbenutritionals.app.kalbespgmobile.MainMenu";
                MenuID = "mnCheckinKBN";
                nameOutlet = spnOutlet.getSelectedItem().toString();
                outletCode = HMoutlet.get(nameOutlet);
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                final View promptView = layoutInflater.inflate(R.layout.confirm_data, null);

                final TextView _tvConfirm = (TextView) promptView.findViewById(R.id.tvTitle);
                final TextView _tvDesc = (TextView) promptView.findViewById(R.id.tvDesc);
                _tvDesc.setVisibility(View.INVISIBLE);
                _tvConfirm.setText("Check In Data ?");
                _tvConfirm.setTextSize(18);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setView(promptView);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Boolean pRes = true;
                                        if (dttAbsenUserData == null) {

                                            pRes = false;

                                        } else {
                                            nameBranch = spnBranch.getSelectedItem().toString();
                                            if ((dttAbsenUserData.get_txtImg1() == null)
                                                    && (dttAbsenUserData.get_txtImg2() == null)
                                                    || (spnBranch.getSelectedItem().toString().equals("")
                                                    || spnBranch.getSelectedItem().toString().equals("null"))
                                                    || (HMbranch.get(nameBranch).equals("")
                                                    || HMbranch.get(nameBranch).equals("null"))) {

                                                pRes = false;
                                            }
                                        }
                                        if (pRes) {
                                            double latitude = Double.parseDouble(String.valueOf(lblLang.getText()));
                                            double longitude = Double.parseDouble(String.valueOf(lblLong.getText()));
                                            double latitudeOutlet = Double.parseDouble(HMoutletLat.get(spnOutlet.getSelectedItem().toString()));
                                            double longitudeOutlet = Double.parseDouble(HMoutletLang.get(spnOutlet.getSelectedItem().toString()));

                                            Location locationA = new Location("point A");

                                            locationA.setLatitude(latitude);
                                            locationA.setLongitude(longitude);

                                            Location locationB = new Location("point B");

                                            locationB.setLatitude(latitudeOutlet);
                                            locationB.setLongitude(longitudeOutlet);

                                            float distance = locationA.distanceTo(locationB);

                                            tUserLoginData checkLocation = new tUserLoginBL().getUserLogin();

                                            if((int) Math.ceil(distance) > 100 && checkLocation.get_txtCheckLocation().equals("1")){
                                                _clsMainActivity.showCustomToast(getContext(), "Failed checkin: Your location too far from outlet", false);
                                            }
                                            else{
                                                nameBranch = spnBranch.getSelectedItem().toString();
                                                nameOutlet = spnOutlet.getSelectedItem().toString();
                                                branchCode = HMbranch.get(nameBranch);
                                                outletCode = HMoutlet.get(nameOutlet);
                                                if (dttAbsenUserData == null) {
                                                    dttAbsenUserData = new tAbsenUserData();
                                                }
                                                tAbsenUserData datatAbsenUserData = dttAbsenUserData;
                                                tUserLoginData dataUserActive = new tUserLoginBL().getUserActive();
                                                String idUserActive = String.valueOf(dataUserActive.get_txtUserId());
                                                String idRoleActive = String.valueOf(dataUserActive.get_txtRoleId());
                                                List<tDeviceInfoUserData> dataDeviceInfoUser = new tDeviceInfoUserBL().getData(1);
                                                String deviceInfo = String.valueOf(dataDeviceInfoUser.get(0).get_txtDeviceId());
                                                List<tAbsenUserData> absenUserDatas = new ArrayList<tAbsenUserData>();
                                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                Calendar cal = Calendar.getInstance();
                                                datatAbsenUserData.set_dtDateCheckIn(dateFormat.format(cal.getTime()));
                                                datatAbsenUserData.set_intId(txtHDId.getText().toString());
                                                datatAbsenUserData.set_intSubmit("0");
                                                datatAbsenUserData.set_intSync("0");
                                                datatAbsenUserData.set_txtAbsen("0");//
                                                datatAbsenUserData.set_txtBranchCode(HMbranch.get(nameBranch));
                                                datatAbsenUserData.set_txtBranchName(spnBranch.getSelectedItem().toString());
                                                datatAbsenUserData.set_txtAccuracy(lblAcc.getText().toString());
                                                datatAbsenUserData.set_txtLatitude(lblLang.getText().toString());
                                                datatAbsenUserData.set_txtLongitude(lblLong.getText().toString());
                                                datatAbsenUserData.set_txtOutletCode(outletCode);
                                                datatAbsenUserData.set_txtOutletName(nameOutlet);
                                                datatAbsenUserData.set_txtDeviceId(deviceInfo);
                                                datatAbsenUserData.set_txtUserId(idUserActive);
                                                datatAbsenUserData.set_txtRoleId(idRoleActive);
                                                datatAbsenUserData.set_dtDateCheckOut(null);
                                                absenUserDatas.add(datatAbsenUserData);
                                                new tAbsenUserBL().saveData(absenUserDatas);
                                                spnBranch.setEnabled(false);
                                                spnOutlet.setEnabled(false);
                                                imgPrevNoImg1.setClickable(false);
                                                imgPrevNoImg2.setClickable(false);
                                                btnRefreshMaps.setClickable(false);
                                                btnRefreshMaps.setVisibility(View.GONE);


                                                _clsMainActivity.showCustomToast(getContext(), "Saved", true);
                                                try {
                                                    clazz = Class.forName(myClass);
                                                    Intent myIntent = new Intent(getContext(), MainMenu.class);
                                                    myIntent.putExtra(clsParameterPutExtra.MenuID, MenuID);
                                                    myIntent.putExtra(clsParameterPutExtra.BranchCode, branchCode);
                                                    myIntent.putExtra(clsParameterPutExtra.OutletCode, outletCode);
                                                    getActivity().finish();
                                                    startActivity(myIntent);
                                                } catch (ClassNotFoundException e) {
                                                    // TODO Auto-generated catch block
                                                    e.printStackTrace();
                                                }
                                            }

                                        } else {
                                            _clsMainActivity.showCustomToast(getContext(), "Please Photo at least 1 photo..", false);
                                        }
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
            }
//					else{
//						clazz = Class.forName(myClass);
//						Intent myIntent = new Intent(getApplicationContext(), clazz);
//						myIntent.putExtra(clsParameterPutExtra.MenuID, MenuID);
//						myIntent.putExtra(clsParameterPutExtra.BranchCode, branchCode);
//						myIntent.putExtra(clsParameterPutExtra.OutletCode, outletCode);
//						finish();
//						startActivity(myIntent);
//					}

        });

//        displayLocation();

        return v;
    }

    private void gettingLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        Location location = locationManager.getLastKnownLocation(provider);

        if(location!=null){
            onLocationChanged(location);
        }

        locationManager.requestLocationUpdates(provider, 1000, 0, this);
    }

    public Location getLocation() {
        try {
            LocationManager locationManager = (LocationManager) getActivity()
                    .getSystemService(LOCATION_SERVICE);

            // getting GPS status
            boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            boolean canGetLocation=false;
            Location location = null;

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
                new clsMainActivity().showCustomToast(getContext(), "no network provider is enabled", false );
            } else {
                canGetLocation = true;
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            1000,
                            0, this);
                    Log.d("Network", "Network Enabled");
                    if (locationManager != null) {
                        mLastLocation = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (mLastLocation == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                1000,
                                0, this);
                        Log.d("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                double latitude = location.getLatitude();
                                double longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mLastLocation;
    }

    @SuppressWarnings("deprecation")
    private void displayLocation(Location mLastLocation) {

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();
            double accurate = mLastLocation.getAccuracy();
            lblLong.setText(longitude + "");
            lblLang.setText(latitude + "");
            lblAcc.setText(accurate + "");

            Long = String.valueOf(longitude);
            Lat = String.valueOf(latitude);
            Acc = String.valueOf(accurate);
        }

    }

    private void buildGoogleApiClient() {
        // TODO Auto-generated method stub
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

    }

    @SuppressWarnings("deprecation")
    private boolean checkPlayServices() {
        // TODO Auto-generated method stub
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(), PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
            }
            return false;
        }
        return true;
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    private void initilizeMap() {
        // TODO Auto-generated method stub
        if (mMap == null) {

            // check if map is created successfully or not
            if (mMap == null) {

            }
        }

    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Absen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.kalbenutritionals.app.kalbespgmobile/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        initilizeMap();
        checkPlayServices();
    }

    private Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private File getOutputMediaFile() {
        // External sdcard location

        File mediaStorageDir = new File(new clsHardCode().txtFolderAbsen + File.separator);
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
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "tmp_absen" + ".jpg");
        return mediaFile;
    }

    protected void captureImage1() {
        uriImage = getOutputMediaFileUri();
        Intent intentCamera1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera1.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        getActivity().startActivityForResult(intentCamera1, CAMERA_CAPTURE_IMAGE1_REQUEST_CODE);
    }

    protected void captureImage2() {
        uriImage = getOutputMediaFileUri();
        Intent intentCamera2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera2.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        getActivity().startActivityForResult(intentCamera2, CAMERA_CAPTURE_IMAGE2_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE1_REQUEST_CODE) {
            if (resultCode == -1) {
                try {

                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath().toString();

                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCapturedImage1(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (resultCode == 0) {
                _clsMainActivity.showCustomToast(getContext(), "User canceled photo", false);
            } else {
                _clsMainActivity.showCustomToast(getContext(), "Something error", false);
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE2_REQUEST_CODE) {
            if (resultCode == -1) {
                try {

                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath().toString();

                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    previewCapturedImage2(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 0) {
                _clsMainActivity.showCustomToast(getContext(), "User canceled photo", false);
            } else {
                _clsMainActivity.showCustomToast(getContext(), "Something error", false);
            }
        }

    }

    private void previewCapturedImage1(Bitmap photo) {
        try {
//            dttAbsenUserData = _tAbsenUserBL.getDataCheckInActive();
            Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);
            imgPrevNoImg1.setVisibility(View.VISIBLE);
            ByteArrayOutputStream out = null;
            try {
                out = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, out); // bmp is your Bitmap instance
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
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
            byte[] pht = out.toByteArray();
            imgPrevNoImg1.setImageBitmap(photo_view);
            if (dttAbsenUserData != null) {
                dttAbsenUserData.set_txtImg1(pht);
            } else {
                dttAbsenUserData.set_txtImg1(pht);
//                dttAbsenUserData.set_txtImg2(null);
                dttAbsenUserData.set_intId(txtHDId.getText().toString());
            }
            dttAbsenUserData.set_intSubmit("0");
            dttAbsenUserData.set_intSync("0");
            dttAbsenUserData.set_txtAbsen("0");
            List<tAbsenUserData> Listdata = new ArrayList<tAbsenUserData>();
            Listdata.add(dttAbsenUserData);
            _tAbsenUserBL.saveData(Listdata);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void previewCapturedImage2(Bitmap photo) {
        try {
//            dttAbsenUserData = _tAbsenUserBL.getDataCheckInActive();
            Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);
            imgPrevNoImg2.setVisibility(View.VISIBLE);
            ByteArrayOutputStream out = null;
            try {
                out = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, out); // bmp is your Bitmap instance
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
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
            byte[] pht = out.toByteArray();
            imgPrevNoImg2.setImageBitmap(photo_view);
            if (dttAbsenUserData != null) {
                dttAbsenUserData.set_txtImg2(pht);
            } else {
                dttAbsenUserData.set_txtImg1(null);
                dttAbsenUserData.set_txtImg2(pht);
                dttAbsenUserData.set_intId(txtHDId.getText().toString());
            }
            dttAbsenUserData.set_intSubmit("0");
            dttAbsenUserData.set_intSync("0");
            dttAbsenUserData.set_txtAbsen("0");//
            List<tAbsenUserData> Listdata = new ArrayList<tAbsenUserData>();
            Listdata.add(dttAbsenUserData);
            _tAbsenUserBL.saveData(Listdata);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        // TODO Auto-generated method stub
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());

    }

    @Override
    public void onConnected(@Nullable Bundle arg0) {

    }

    @Override
    public void onConnectionSuspended(int arg0) {
        // TODO Auto-generated method stub
        mGoogleApiClient.connect();

    }

    private boolean isDeviceSupportCamera() {
        if (getContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
        mLastLocation = location;
        displayLocation(mLastLocation);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}







