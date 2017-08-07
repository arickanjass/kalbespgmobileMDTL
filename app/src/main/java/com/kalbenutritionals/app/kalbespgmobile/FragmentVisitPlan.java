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
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.location.LocationListener;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
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
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import bl.mMenuBL;
import bl.tAbsenUserBL;
import bl.tDeviceInfoUserBL;
import bl.tUserLoginBL;
import bl.tVisitPlanHeader_MobileBL;
import bl.tVisitPlanRealisasiBL;
import library.spgmobile.common.mMenuData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.tVisitPlanHeader_MobileData;
import library.spgmobile.common.tVisitPlanRealisasiData;
import library.spgmobile.dal.clsHardCode;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Robert on 27/04/2017.
 */

public class FragmentVisitPlan extends Fragment implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener{
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
    float distance;
    private String Acc;
    double latitudeOutlet;
    private double mlongitude = 0;
    private double mlatitude = 0;
    private static final int CAMERA_CAPTURE_IMAGE1_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_IMAGE2_REQUEST_CODE = 130;
    private ImageView imgPrevNoImg1;
    private ImageView imgPrevNoImg2;
    private static final String IMAGE_DIRECTORY_NAME = "Image Sales";
    private HashMap<String, String> HMbranch = new HashMap<String, String>();
    private HashMap<String, String> HMoutlet = new HashMap<String, String>();
    private HashMap<String, String> HMoutletLang = new HashMap<String, String>();
    private HashMap<String, String> HMoutletCode = new HashMap<String, String>();
    private HashMap<String, String> HMoutletLat = new HashMap<String, String>();
    private HashMap<String, String> HMIdRealisasi = new HashMap<String, String>();

    private String ID_REALISASI = "IdRealisasi";
    final String finalFile = null;
    private TextView lblLong;
    private TextView lblLang;
    private TextView lblAcc;
    private TextView txtHDId;
    private TextView lblLongOutlet;
    private TextView lblLatOutlet;
    private TextView lblDistance;
    private EditText etDescReply;
    private EditText etBranch;
    private EditText etOutlet;
    private TextView tvDesc;
    private ArrayAdapter<String> dataAdapterBranch;
    private ArrayAdapter<String> dataAdapterOutlet;
    //    private tAbsenUserBL _tAbsenUserBL;
    BitmapFactory.Options options;
    private tAbsenUserData dttAbsenUserData;
    private Button btnRefreshMaps, btnPopupMap;
    private Button btnCheckIn;
    private String MenuID;
    double latitude;
    double longitude;
    double longitudeOutlet;
    private String[] arrdefaultBranch = new String[]{"Branch"};
    private String[] arrdefaultOutlet = new String[]{"Outlet"};

    private static Bitmap photo1, photo2;
    private static ByteArrayOutputStream output = new ByteArrayOutputStream();
    private static byte[] pht1;
    private static byte[] pht2;

    private tVisitPlanRealisasiData dttVisitPlanRealisasiData;
    TextInputLayout textInputLayoutDescriptionVisiPlan;
    private String idRealisasi;
    private String nameBranch;
    private String nameOutlet;
    private String branchCode;
    private String outletCode;
    private String myClass;
    Bundle dataHeader;
    String idRealisasiHeader;
    tVisitPlanRealisasiData dataDetail;
    private Class<?> clazz = null;
    tVisitPlanRealisasiData _tVisitPlanRealisasiData = new tVisitPlanRealisasiData();
    ;
    private Uri uriImage;
    private int countActivity;

    clsMainActivity _clsMainActivity = new clsMainActivity();

    View v;

    private GoogleApiClient client;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_visit_plan, container, false);

        dataHeader = getArguments();
        idRealisasiHeader = dataHeader.getString(ID_REALISASI);
        dataDetail = new tVisitPlanRealisasiBL().getDataByHeaderId(idRealisasiHeader);

        etBranch = (EditText) v.findViewById(R.id.etBranch);
        etOutlet = (EditText) v.findViewById(R.id.etOutlet);
        etBranch.setText(dataDetail.get_txtBranchCode());
        etBranch.setClickable(false);
        etBranch.setFocusable(false);
        tvDesc = (TextView) v.findViewById(R.id.tvDesc);
        tvDesc.setText(dataDetail.get_txtDesc());
        etOutlet.setText(dataDetail.get_txtOutletName());
        etOutlet.setFocusable(false);
        etOutlet.setClickable(false);
        txtHDId = (TextView) v.findViewById(R.id.txtHDId);
        btnRefreshMaps = (Button) v.findViewById(R.id.btnRefreshMaps);
        btnCheckIn = (Button) v.findViewById(R.id.buttonCheckIn);
        btnPopupMap = (Button) v.findViewById(R.id.viewMap);
        imgPrevNoImg1 = (ImageView) v.findViewById(R.id.imageViewCamera1);
        imgPrevNoImg2 = (ImageView) v.findViewById(R.id.imageViewCamera2);
        lblLong = (TextView) v.findViewById(R.id.tvLong);
        lblLang = (TextView) v.findViewById(R.id.tvLat);
        lblAcc = (TextView) v.findViewById(R.id.tvAcc);
        lblLongOutlet = (TextView) v.findViewById(R.id.tvLongOutlet);
        lblLatOutlet = (TextView) v.findViewById(R.id.tvlatOutlet);
        lblLongOutlet.setText(dataDetail.get_txtLongSource());
        lblLatOutlet.setText(dataDetail.get_txtLatSource());
        lblDistance = (TextView) v.findViewById(R.id.tvDistance);

        options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        textInputLayoutDescriptionVisiPlan = (TextInputLayout) v.findViewById(R.id.input_layout_description_visit_plan);
        etDescReply = (EditText) v.findViewById(R.id.et_desc_reply);

        lblLong.setText("");
        lblLang.setText("");
        lblAcc.setText("");
        lblDistance.setText("");

        getLocation();

        if (mLastLocation != null) {
            displayLocation(mLastLocation);
        }

        if (checkPlayServices()) {
            buildGoogleApiClient();
        }

        btnRefreshMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
                if (mLastLocation == null) {
                    displayLocation(mLastLocation);
                }
            }
        });

        pht1 = null;
        pht2 = null;

        btnPopupMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean valid = true;

                double latitude = 0;
                double longitude = 0;
                double latitudeOutlet = 0;
                double longitudeOutlet = 0;

                //Check longlat my location
                try {
                    latitude = Double.parseDouble(String.valueOf(lblLang.getText()));
                    longitude = Double.parseDouble(String.valueOf(lblLong.getText()));
                } catch (Exception ex) {
                    valid = false;
                    new clsMainActivity().showCustomToast(getContext(), "Your location not found", false);
                }

                //Check longlat outlet location
                if (valid) {
                    try {
                        latitudeOutlet = Double.parseDouble(dataDetail.get_txtLatSource().toString());
                        longitudeOutlet = Double.parseDouble(dataDetail.get_txtLongSource().toString());
                    } catch (Exception ex) {
                        valid = false;

                        new clsMainActivity().showCustomToast(getContext(), "Outlet location not found", false);
                    }
                }

                if(valid){
                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());

                    final View promptView = layoutInflater.inflate(R.layout.popup_map_absen, null);

                    GoogleMap mMap;
                    mMap = ((MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map)).getMap();

                    if (mMap == null) {
                        mMap = ((MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map)).getMap();
                    }

                        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Your Location");

                        MarkerOptions markerOutlet = new MarkerOptions().position(new LatLng(latitudeOutlet, longitudeOutlet)).title("Outlet Location");

                        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                        final LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        builder.include(marker.getPosition());
                        builder.include(markerOutlet.getPosition());

                        mMap.clear();
                        mMap.addMarker(marker);
                        mMap.addMarker(markerOutlet);

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
                                                MapFragment f = (MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map);
                                                if (f != null) {
                                                    (getActivity()).getFragmentManager().beginTransaction().remove(f).commit();
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

                        distance = locationA.distanceTo(locationB);

                        alertD.setTitle(String.valueOf((int) Math.ceil(distance)) + " meters");
                        alertD.show();
                }
            }
        });

        // First we need to check availability of play services
        imgPrevNoImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage1();
            }
        });

        imgPrevNoImg2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                captureImage2();
            }
        });

        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameOutlet = dataDetail.get_txtOutletName();
                outletCode = dataDetail.get_txtOutletCode();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Boolean pRes = true;
                                        new clsMainActivity().removeErrorMessage(textInputLayoutDescriptionVisiPlan);
                                        //validasi foto jika kosong semua
                                        if (pht1 == null && pht2 == null) {
                                            new clsMainActivity().removeErrorMessage(textInputLayoutDescriptionVisiPlan);
                                            new clsMainActivity().showCustomToast(getContext(), "Please take at least 1 photo", false);
                                            pRes = false;
                                        }
                                        if (pRes) {
                                            double latitude = Double.parseDouble(String.valueOf(lblLang.getText()));
                                            double longitude = Double.parseDouble(String.valueOf(lblLong.getText()));
                                            if (dataDetail.get_txtLatSource().toString().equals("") || dataDetail.get_txtLatSource().toString().equals("null")) {
                                                latitudeOutlet = 0.0;
                                                longitudeOutlet = 0.0;
                                            } else {
                                                latitudeOutlet = Double.parseDouble(dataDetail.get_txtLatSource().toString());
                                                longitudeOutlet = Double.parseDouble(dataDetail.get_txtLongSource().toString());
                                            }

                                            Location locationA = new Location("point A");

                                            locationA.setLatitude(latitude);
                                            locationA.setLongitude(longitude);

                                            Location locationB = new Location("point B");

                                            locationB.setLatitude(latitudeOutlet);
                                            locationB.setLongitude(longitudeOutlet);

                                            float distance = locationA.distanceTo(locationB);

                                            tUserLoginData checkLocation = new tUserLoginBL().getUserLogin();

                                            if ((int) Math.ceil(distance) > 100 && checkLocation.get_txtCheckLocation().equals("1")) {
                                                _clsMainActivity.showCustomToast(getContext(), "Failed checkin: Your location too far from outlet", false);
                                            } else {
                                                nameBranch = dataDetail.get_txtBranchCode();
                                                nameOutlet = dataDetail.get_txtOutletName();
                                                branchCode = dataDetail.get_txtBranchCode();
                                                outletCode = dataDetail.get_txtOutletCode();
                                                idRealisasi = dataDetail.get_txtDataIDRealisasi();

                                                tUserLoginData dataUserActive = new tUserLoginBL().getUserActive();
                                                String idRoleActive = String.valueOf(dataUserActive.get_txtRoleId());

                                                tUserLoginData dataLogin = new tUserLoginBL().getUserLogin();
                                                String dataTanggalLogin = dataLogin.get_dtLastLogin();

                                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                Calendar cal = Calendar.getInstance();
                                                String descReply = etDescReply.getText().toString();
                                                _tVisitPlanRealisasiData.set_txtDataIDRealisasi(idRealisasi);
                                                _tVisitPlanRealisasiData.set_dtDateRealisasi(dataTanggalLogin);
                                                _tVisitPlanRealisasiData.set_dtDateRealisasiDevice(dateFormat.format(cal.getTime()));
                                                _tVisitPlanRealisasiData.set_txtDescReply(descReply);
                                                _tVisitPlanRealisasiData.set_txtLong(longitude + "");
                                                _tVisitPlanRealisasiData.set_txtLat(latitude + "");
                                                _tVisitPlanRealisasiData.set_intDistance(distance + "");
                                                _tVisitPlanRealisasiData.set_txtRoleId(idRoleActive);
                                                _tVisitPlanRealisasiData.set_intSubmit("1");
                                                _tVisitPlanRealisasiData.set_txtAcc(lblAcc.getText().toString());
                                                List<tDeviceInfoUserData> dataDeviceInfoUser = new tDeviceInfoUserBL().getData(0);
                                                String deviceInfo = String.valueOf(dataDeviceInfoUser.get(0).get_txtDeviceId());
                                                _tVisitPlanRealisasiData.set_deviceId(deviceInfo);
                                                new tVisitPlanRealisasiBL().UpdateData(_tVisitPlanRealisasiData);
                                                imgPrevNoImg1.setClickable(false);
                                                imgPrevNoImg2.setClickable(false);
                                                btnRefreshMaps.setClickable(false);
                                                btnRefreshMaps.setVisibility(View.GONE);

                                                List<tVisitPlanRealisasiData> data = new tVisitPlanRealisasiBL().getAllData();
                                                boolean statusHeader = false;
                                                for (tVisitPlanRealisasiData dt : data) {
                                                    if (dt.get_intSubmit().toString().equals("1")) {
                                                        statusHeader = true;
                                                    } else {
                                                        statusHeader = false;
                                                    }
                                                }
                                                if (statusHeader) {
                                                    tVisitPlanHeader_MobileData dt = new tVisitPlanHeader_MobileData();
                                                    dt.set_intSubmit("1");
                                                    dt.set_intHeaderId(dataDetail.get_intHeaderID());
                                                    new tVisitPlanHeader_MobileBL().UpdateData(dt);
                                                }

                                                _clsMainActivity.showCustomToast(getContext(), "Saved", true);
                                                Intent myIntent = new Intent(getContext(), MainMenu.class);
                                                getActivity().finish();
                                                startActivity(myIntent);
                                            }

                                        } else {
//                                            _clsMainActivity.showCustomToast(getContext(), "Please Photo at least 1 photo..", false);
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
                alertD.setTitle("Confirmation");
                alertD.setMessage("Are you sure want to checkin?");
                alertD.show();
            }
        });

//        set output text as ALL CAPS and filter
        etDescReply.setFilters(new InputFilter[]{
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

//    private void getDistance() {
//        latitude = Double.parseDouble(String.valueOf(lblLang.getText()));
//        longitude = Double.parseDouble(String.valueOf(lblLong.getText()));
//        if (dataDetail.get_txtLatSource().toString().equals("") || dataDetail.get_txtLatSource().toString().equals("null")) {
//            latitudeOutlet = 0.0;
//            longitudeOutlet = 0.0;
//        } else {
//            latitudeOutlet = Double.parseDouble(dataDetail.get_txtLatSource().toString());
//            longitudeOutlet = Double.parseDouble(dataDetail.get_txtLongSource().toString());
//        }
//
//        Location locationA = new Location("point A");
//
//        locationA.setLatitude(latitude);
//        locationA.setLongitude(longitude);
//
//        Location locationB = new Location("point B");
//
//        locationB.setLatitude(latitudeOutlet);
//        locationB.setLongitude(longitudeOutlet);
//
//        distance = locationA.distanceTo(locationB);
//    }

    // get location GPS
    private boolean earlyState = true;
    public Location getLocation() {
        try {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
                mLastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                new clsMainActivity().showCustomToast(getContext(), "Please turn on GPS or check your internet connection", false);
            } else {
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        _clsMainActivity.showCustomToast(getContext(), "Please check application permissions", false);
                    } else {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
                        mLastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                } else {
                    _clsMainActivity.showCustomToast(getContext(), "Please check your connection", false);
                }

                if (isGPSEnabled && mLastLocation==null) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
                    mLastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                } else if(!isGPSEnabled){
                    _clsMainActivity.showCustomToast(getContext(), "Please turn on GPS", false);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(mLastLocation!=null&&!earlyState){
            new clsMainActivity().showCustomToast(getContext(), "Location Updated", true);
        }
        earlyState = false;
        return mLastLocation;
    }

    @SuppressWarnings("deprecation")
    //set text view long lat
    private void displayLocation(Location mLastLocation) {

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();
            double accurate = mLastLocation.getAccuracy();

            lblLong.setText(String.format("%s", longitude));
            lblLang.setText(String.format("%s", latitude));
            lblAcc.setText(String.format("%s", accurate));

            try {
                float distance = countDistance(latitude, longitude);
                lblDistance.setText(String.format("%s meters", String.valueOf((int) Math.ceil(distance))));
            } catch (Exception ignored) {
                String e = ignored.toString();
            }

            mlongitude = longitude;
            mlatitude = latitude;

        } else {
            lblLong.setText("");
            lblLang.setText("");
            lblAcc.setText("");
            lblDistance.setText("");
        }

    }

    // count distance
    private float countDistance(double latitude, double longitude) {
        float distance = 0;

        try {
            if (dataDetail.get_txtLatSource().toString().equals("") || dataDetail.get_txtLatSource().toString().equals("null")) {
                latitudeOutlet = 0.0;
                longitudeOutlet = 0.0;
            } else {
                latitudeOutlet = Double.parseDouble(dataDetail.get_txtLatSource().toString());
                longitudeOutlet = Double.parseDouble(dataDetail.get_txtLongSource().toString());
            }

            Location locationA = new Location("point user");

            locationA.setLatitude(latitude);
            locationA.setLongitude(longitude);

            Location locationB = new Location("point outlet");

            locationB.setLatitude(latitudeOutlet);
            locationB.setLongitude(longitudeOutlet);

            distance = locationA.distanceTo(locationB);
        } catch (Exception ignored) {
            String e = ignored.toString();
        }

        lblDistance.setText(String.format("%s meters", String.valueOf((int) Math.ceil(distance))));

        return distance;
    }

    @SuppressWarnings("deprecation")
    private boolean checkPlayServices() {
        // TODO Auto-generated method stub
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(), PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            return false;
        }
        return true;
    }

    private void buildGoogleApiClient() {
        // TODO Auto-generated method stub
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
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
            Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);
            imgPrevNoImg1.setVisibility(View.VISIBLE);
            ByteArrayOutputStream output = null;
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
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
            pht1 = output.toByteArray();
            imgPrevNoImg1.setImageBitmap(photo_view);
//            _tVisitPlanRealisasiData = new tVisitPlanRealisasiData();
            _tVisitPlanRealisasiData.set_dtPhoto1(pht1);
            _tVisitPlanRealisasiData.set_dtPhoto2(pht2);


        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void previewCapturedImage2(Bitmap photo) {
        try {
//            dttAbsenUserData = _tAbsenUserBL.getDataCheckInActive();
            Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);
            imgPrevNoImg2.setVisibility(View.VISIBLE);
            ByteArrayOutputStream output = null;
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
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
            pht2 = output.toByteArray();
            imgPrevNoImg2.setImageBitmap(photo_view);
//            _tVisitPlanRealisasiData = new tVisitPlanRealisasiData();
            _tVisitPlanRealisasiData.set_dtPhoto1(pht1);
            _tVisitPlanRealisasiData.set_dtPhoto2(pht2);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        displayLocation(mLastLocation);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
