package com.kalbenutritionals.app.kalbespgmobile;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
//import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
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
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import bl.tDeviceInfoUserBL;
import bl.tUserLoginBL;
import bl.tVisitPlanHeader_MobileBL;
import bl.tVisitPlanRealisasiBL;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.tVisitPlanHeader_MobileData;
import library.spgmobile.common.tVisitPlanRealisasiData;
import library.spgmobile.dal.clsHardCode;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by aan.junianto on 2/01/2018.
 */

public class FragmentAddTaggingOutlet extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

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
    private Uri uriImage;
    private int countActivity;

    clsMainActivity _clsMainActivity = new clsMainActivity();

    View v;

    private GoogleApiClient client;

    private FragmentAddTaggingOutlet dataBiding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_add_tagging_outlet, container, false);

        dataHeader = getArguments();
        idRealisasiHeader = dataHeader.getString(ID_REALISASI);
        dataDetail = new tVisitPlanRealisasiBL().getDataByHeaderId(idRealisasiHeader);

        etBranch = (EditText) v.findViewById(R.id.etBranch);
        etOutlet = (EditText) v.findViewById(R.id.etOutlet);
        etBranch.setText(dataDetail.get_txtBranchCode());
        etBranch.setClickable(false);
        etBranch.setFocusable(false);
//        tvDesc.setText(dataDetail.get_txtDesc());
        etOutlet.setText(dataDetail.get_txtOutletName());
        etOutlet.setFocusable(false);
        etOutlet.setClickable(false);
        txtHDId = (TextView) v.findViewById(R.id.txtHDId);
        btnRefreshMaps = (Button) v.findViewById(R.id.btnRefreshMaps);
        btnCheckIn = (Button) v.findViewById(R.id.buttonCheckIn);
        btnPopupMap = (Button) v.findViewById(R.id.viewMap);
        lblLong = (TextView) v.findViewById(R.id.tvLong);
        lblLang = (TextView) v.findViewById(R.id.tvLat);
        lblAcc = (TextView) v.findViewById(R.id.tvAcc);
//        lblLongOutlet = (TextView) v.findViewById(R.id.tvLongOutlet);
//        lblLatOutlet = (TextView) v.findViewById(R.id.tvlatOutlet);
//        lblLongOutlet.setText(dataDetail.get_txtLongSource());
//        lblLatOutlet.setText(dataDetail.get_txtLatSource());
//        lblDistance = (TextView) v.findViewById(R.id.tvDistance);

        options = new BitmapFactory.Options();
        options.inSampleSize = 2;

//        textInputLayoutDescriptionVisiPlan = (TextInputLayout) v.findViewById(R.id.input_layout_description_visit_plan);
//        etDescReply = (EditText) v.findViewById(R.id.et_desc_reply);

        lblLong.setText("");
        lblLang.setText("");
        lblAcc.setText("");
//        lblDistance.setText("");

        getLocation();

        if (mLastLocation != null) {
            displayLocation(mLastLocation);
        }

//        if (checkPlayServices()) {
//            buildGoogleApiClient();
//        }

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
//                if (valid) {
//                    try {
//                        latitudeOutlet = Double.parseDouble(dataDetail.get_txtLatSource().toString());
//                        longitudeOutlet = Double.parseDouble(dataDetail.get_txtLongSource().toString());
//                    } catch (Exception ex) {
//                        valid = false;
//
//                        new clsMainActivity().showCustomToast(getContext(), "Outlet location not found", false);
//                    }
//                }
                try {
                    if(valid){
                        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

                        final View promptView = layoutInflater.inflate(R.layout.popup_map_absen, null);

                        GoogleMap mMap;
                        mMap = ((MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map)).getMap();

                        if (mMap == null) {
                            mMap = ((MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map)).getMap();
                        }

                        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Your Location");

//                    MarkerOptions markerOutlet = new MarkerOptions().position(new LatLng(latitudeOutlet, longitudeOutlet)).title("Outlet Location");

                        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                        final LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        builder.include(marker.getPosition());
//                    builder.include(markerOutlet.getPosition());

                        mMap.clear();
                        mMap.addMarker(marker);
//                    mMap.addMarker(markerOutlet);

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

//                    LatLng coordinate = new LatLng(latitude, longitude);
//                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate,70));


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

//                    Location locationB = new Location("point B");
//
//                    locationB.setLatitude(latitudeOutlet);
//                    locationB.setLongitude(longitudeOutlet);
//
//                    distance = locationA.distanceTo(locationB);

                        alertD.setTitle(String.valueOf(getAddress(getActivity(),latitude, longitude).get(0)));
                        alertD.show();
                    }
                } catch (Exception ex) {
                    new clsMainActivity().showCustomToast(getContext(), ex.getMessage().toString(), false);
                }

            }
        });

//        // First we need to check availability of play services
//        imgPrevNoImg1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                captureImage1();
//            }
//        });
//
//        imgPrevNoImg2.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                captureImage2();
//            }
//        });

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
                                        Boolean validTagOutlet = true;
                                        double latitude = Double.parseDouble(String.valueOf(lblLang.getText()));
                                        double longitude = Double.parseDouble(String.valueOf(lblLong.getText()));
                                        double acc = Double.parseDouble(String.valueOf(lblAcc.getText()));
                                        if (dataDetail.get_txtLatSource().toString().equals("") || dataDetail.get_txtLatSource().toString().equals("null")) {
//                                                latitudeOutlet = 0.0;
//                                                longitudeOutlet = 0.0;
                                            validTagOutlet = false;
                                        }
                                        if(validTagOutlet){

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
                alertD.setMessage("Are you sure want to Submit?");
                alertD.show();
            }
        });

//        set output text as ALL CAPS and filter
//        etDescReply.setFilters(new InputFilter[]{
//                new InputFilter() {
//                    @Override
//                    public CharSequence filter(CharSequence cs, int start,
//                                               int end, Spanned spanned, int dStart, int dEnd) {
//                        if (cs.equals("")) { // for backspace
//                            return cs;
//                        }
//                        if (cs.toString().matches("[a-zA-Z0-9,.\\- ]+")) {
//                            return cs;
//                        }
//                        return "";
//                    }
//                }, new InputFilter.AllCaps()
//        });

        return v;
    }

    private List<String> getAddress(Context context, double LATITUDE, double LONGITUDE) {

        List<String> addressAll = new ArrayList<>();

        //Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {



                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

//                Log.d(TAG, "getAddress:  address" + address);
//                Log.d(TAG, "getAddress:  city" + city);
//                Log.d(TAG, "getAddress:  state" + state);
//                Log.d(TAG, "getAddress:  postalCode" + postalCode);
//                Log.d(TAG, "getAddress:  knownName" + knownName);

                addressAll.add(address);
                addressAll.add(city);
                addressAll.add(state);
                addressAll.add(country);
                addressAll.add(postalCode);
                addressAll.add(knownName);

            }
        } catch (IOException e) {
            e.printStackTrace();
            addressAll.add(e.toString());
        }
        return addressAll;
    }

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
        DecimalFormat df = new DecimalFormat("#.##");

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();
            double accurate = mLastLocation.getAccuracy();

            lblLong.setText(String.format("%s", longitude));
            lblLang.setText(String.format("%s", latitude));
            lblAcc.setText(String.format("%s", df.format(accurate)));

            try {
//                float distance = countDistance(latitude, longitude);
//                lblDistance.setText(String.format("%s meters", String.valueOf((int) Math.ceil(distance))));
            } catch (Exception ignored) {
                String e = ignored.toString();
            }

            mlongitude = longitude;
            mlatitude = latitude;

        } else {
            lblLong.setText("");
            lblLang.setText("");
            lblAcc.setText("");
//            lblDistance.setText("");
        }

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

    @Override
    public void onLocationChanged(Location location) {
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
