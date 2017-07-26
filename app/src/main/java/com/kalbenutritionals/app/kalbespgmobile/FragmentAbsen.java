package com.kalbenutritionals.app.kalbespgmobile;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
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
import java.util.HashMap;
import java.util.List;

import bl.mEmployeeAreaBL;
import bl.mEmployeeBranchBL;
import bl.tAbsenUserBL;
import bl.tDeviceInfoUserBL;
import bl.tUserLoginBL;
import library.spgmobile.common.mEmployeeAreaData;
import library.spgmobile.common.mEmployeeBranchData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;

import static android.content.Context.LOCATION_SERVICE;

public class FragmentAbsen extends Fragment implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

    private Location mLastLocation;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;
    private Spinner spnOutlet;
    private Spinner spnBranch;
    private List<String> arrData;
    private double mlongitude = 0;
    private double mlatitude = 0;
    private static final int CAMERA_CAPTURE_IMAGE1_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_IMAGE2_REQUEST_CODE = 130;
    private ImageView imgPrevNoImg1;
    private ImageView imgPrevNoImg2;
    private static final String IMAGE_DIRECTORY_NAME = "Image Sales";
    private HashMap<String, String> HMbranch = new HashMap<>();
    private HashMap<String, String> HMoutlet = new HashMap<>();
    private HashMap<String, String> HMoutletLang = new HashMap<>();
    private HashMap<String, String> HMoutletLat = new HashMap<>();
    private TextView lblLong;
    private TextView lblLang, lblDistance;
    private TextView lblAcc;
    private TextView txtHDId;
    Options options;
    private tAbsenUserData dttAbsenUserData;
    private Button btnRefreshMaps;

    private String nameBranch;
    private String nameOutlet;
    private String outletCode;
    private Uri uriImage;
    private TextView tvLongOutlet;
    private TextView tvLatOutlet;


    clsMainActivity _clsMainActivity = new clsMainActivity();

    View v;

    private class MyAdapter extends ArrayAdapter<String> {
        private List<String> arrayDataAdapyter;

        List<String> getArrayDataAdapyter() {
            return arrayDataAdapyter;
        }

        void setArrayDataAdapyter(List<String> arrayDataAdapyter) {
            this.arrayDataAdapyter = arrayDataAdapyter;
        }

        MyAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            setArrayDataAdapyter(objects);
        }

        @Override
        public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, parent);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, parent);
        }

        View getCustomView(int position, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row = inflater.inflate(R.layout.custom_spinner, parent, false);
            if (arrData.size() > 0) {
                TextView label = (TextView) row.findViewById(R.id.tvTitle);
                label.setText(getArrayDataAdapyter().get(position));
                TextView sub = (TextView) row.findViewById(R.id.tvDesc);
                sub.setVisibility(View.GONE);
                sub.setVisibility(View.GONE);
                label.setTextColor(Color.parseColor("#000000"));
                row.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            return row;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_absen, container, false);

        txtHDId = (TextView) v.findViewById(R.id.txtHDId);
        btnRefreshMaps = (Button) v.findViewById(R.id.btnRefreshMaps);
        Button btnCheckIn = (Button) v.findViewById(R.id.buttonCheckIn);
        Button btnPopupMap = (Button) v.findViewById(R.id.viewMap);
        spnOutlet = (Spinner) v.findViewById(R.id.spnOutlet);
        spnBranch = (Spinner) v.findViewById(R.id.spnType);
        imgPrevNoImg1 = (ImageView) v.findViewById(R.id.imageViewCamera1);
        imgPrevNoImg2 = (ImageView) v.findViewById(R.id.imageViewCamera2);
        lblLong = (TextView) v.findViewById(R.id.tvLong);
        lblLang = (TextView) v.findViewById(R.id.tvLat);
        lblAcc = (TextView) v.findViewById(R.id.tvAcc);
        lblDistance = (TextView) v.findViewById(R.id.tvDis);
        options = new Options();
        options.inSampleSize = 2;
        tAbsenUserBL _tAbsenUserBL = new tAbsenUserBL();
        dttAbsenUserData = _tAbsenUserBL.getDataCheckInActive();
        lblLong.setText("");
        lblLang.setText("");
        lblAcc.setText("");
        lblDistance.setText("");

        btnRefreshMaps.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
                if (mLastLocation != null) {
                    displayLocation(mLastLocation);
                    new clsMainActivity().showCustomToast(getContext(), "Location Updated", true);
                }
            }
        });

        getLocation();

        if (mLastLocation != null) {
            displayLocation(mLastLocation);
        }

        btnPopupMap.setOnClickListener(new OnClickListener() {
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
                        latitudeOutlet = Double.parseDouble(HMoutletLat.get(spnOutlet.getSelectedItem().toString()));
                        longitudeOutlet = Double.parseDouble(HMoutletLang.get(spnOutlet.getSelectedItem().toString()));
                    } catch (Exception ex) {
                        valid = false;

                        new clsMainActivity().showCustomToast(getContext(), "Outlet location not found", false);
                    }
                }

                if (valid) {

                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());

                    View promptView = layoutInflater.inflate(R.layout.popup_map_absen, null);

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

                    float distance = locationA.distanceTo(locationB);

                    alertD.setTitle(String.valueOf((int) Math.ceil(distance)) + " meters");
                    alertD.show();
                }
            }
        });

        List<mEmployeeBranchData> listDataBranch = new mEmployeeBranchBL().GetAllData();
        final List<mEmployeeAreaData> listDataArea = new mEmployeeAreaBL().GetAllData();
        if (checkPlayServices()) {
            buildGoogleApiClient();
        }

        // First we need to check availability of play services
        imgPrevNoImg1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dttAbsenUserData == null) {
                    dttAbsenUserData = new tAbsenUserData();
                }
                captureImage1();
            }
        });

        imgPrevNoImg2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (dttAbsenUserData == null) {
                    dttAbsenUserData = new tAbsenUserData();
                }
                captureImage2();
            }
        });

        arrData = new ArrayList<>();
        if (listDataBranch.size() > 0) {
            for (mEmployeeBranchData dt : listDataBranch) {
                arrData.add(dt.get_txtBranchName());
                HMbranch.put(dt.get_txtBranchName(), dt.get_txtBranchCode());
            }
            ArrayAdapter<String> dataAdapterBranch = new MyAdapter(getContext(), R.layout.custom_spinner, arrData);
            spnBranch.setAdapter(dataAdapterBranch);
        }

        arrData = new ArrayList<>();
        if (listDataArea.size() > 0) {
            for (mEmployeeAreaData dt : listDataArea) {
                arrData.add(dt.get_txtOutletName());
                HMoutlet.put(dt.get_txtOutletName(), dt.get_txtOutletCode());
                HMoutletLang.put(dt.get_txtOutletName(), dt.get_txtLongitude() == null ? "" : dt.get_txtLongitude());
                HMoutletLat.put(dt.get_txtOutletName(), dt.get_txtLatitude() == null ? "" : dt.get_txtLatitude());
            }
            ArrayAdapter<String> dataAdapterOutlet = new MyAdapter(getContext(), R.layout.custom_spinner, arrData);
            spnOutlet.setAdapter(dataAdapterOutlet);
        }

        dttAbsenUserData = _tAbsenUserBL.getDataCheckInActive();

        btnCheckIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                nameOutlet = spnOutlet.getSelectedItem().toString();
                outletCode = HMoutlet.get(nameOutlet);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("Confirmation");
                alertDialogBuilder.setMessage("Check in Data?");
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Boolean pRes = true;
                                        Boolean isLocationDetected = true;
                                        Boolean isLocationOutletDetected = true;
                                        String errorMessage = "Please Photo at least 1 photo";
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

                                        double latitudeOutlet;
                                        double longitudeOutlet;

                                        if (pRes) {
                                            if (lblLang.getText().toString().equals("") && lblLong.getText().toString().equals("")) {
                                                isLocationDetected = false;
                                                _clsMainActivity.showCustomToast(getContext(), "Failed checkin: Location not found, please check your GPS", false);
                                            } else if (lblLang.getText() == null && lblLong.getText() == null) {
                                                isLocationDetected = false;
                                                _clsMainActivity.showCustomToast(getContext(), "Failed checkin: Location not found, please check your GPS", false);
                                            }

                                            if (HMoutletLat.get(spnOutlet.getSelectedItem().toString()).equals("") && HMoutletLang.get(spnOutlet.getSelectedItem().toString()).equals("")) {
                                                isLocationOutletDetected = false;
                                                _clsMainActivity.showCustomToast(getContext(), "Failed checkin: Location outlet not found", false);
                                            } else if (HMoutletLat.get(spnOutlet.getSelectedItem().toString()).equalsIgnoreCase("null") && HMoutletLang.get(spnOutlet.getSelectedItem().toString()).equalsIgnoreCase("null")) {
                                                isLocationOutletDetected = false;
                                                _clsMainActivity.showCustomToast(getContext(), "Failed checkin: Location outlet not found", false);
                                            }

                                            if (isLocationDetected && isLocationOutletDetected) {
                                                double latitude = Double.parseDouble(String.valueOf(lblLang.getText()));
                                                double longitude = Double.parseDouble(String.valueOf(lblLong.getText()));

                                                latitudeOutlet = Double.parseDouble(HMoutletLat.get(spnOutlet.getSelectedItem().toString()));
                                                longitudeOutlet = Double.parseDouble(HMoutletLang.get(spnOutlet.getSelectedItem().toString()));

                                                Location locationA = new Location("point A");

                                                locationA.setLatitude(latitude);
                                                locationA.setLongitude(longitude);

                                                Location locationB = new Location("point B");

                                                locationB.setLatitude(latitudeOutlet);
                                                locationB.setLongitude(longitudeOutlet);

                                                float distance = locationA.distanceTo(locationB);

                                                tUserLoginData checkLocation = new tUserLoginBL().getUserLogin();
                                                boolean dValidDistance = false;
                                                if (checkLocation.get_txtCheckLocation().equals("0")) {
                                                    dValidDistance = true;
                                                } else if ((int) Math.ceil(distance) <= Integer.valueOf(checkLocation.get_txtCheckLocation())) {
                                                    dValidDistance = true;
                                                }
                                                if (!dValidDistance) {
                                                    _clsMainActivity.showCustomToast(getContext(), "Failed checkin: Your location too far from outlet", false);
                                                } else {
                                                    nameBranch = spnBranch.getSelectedItem().toString();
                                                    nameOutlet = spnOutlet.getSelectedItem().toString();
                                                    outletCode = HMoutlet.get(nameOutlet);
                                                    if (dttAbsenUserData == null) {
                                                        dttAbsenUserData = new tAbsenUserData();
                                                    }
                                                    tAbsenUserData datatAbsenUserData = dttAbsenUserData;
                                                    tUserLoginData dataUserActive = new tUserLoginBL().getUserActive();
                                                    String idUserActive = String.valueOf(dataUserActive.get_txtUserId());
                                                    List<tDeviceInfoUserData> dataDeviceInfoUser = new tDeviceInfoUserBL().getData(1);
                                                    String deviceInfo = String.valueOf(dataDeviceInfoUser.get(0).get_txtDeviceId());
                                                    List<tAbsenUserData> absenUserDatas = new ArrayList<>();
                                                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                    Calendar cal = Calendar.getInstance();
                                                    datatAbsenUserData.set_dtDateCheckIn(dateFormat.format(cal.getTime()));
                                                    datatAbsenUserData.set_intId(txtHDId.getText().toString());
                                                    datatAbsenUserData.set_intSubmit("0");
                                                    datatAbsenUserData.set_intSync("0");
                                                    datatAbsenUserData.set_txtAbsen("0");
                                                    datatAbsenUserData.set_txtBranchCode(HMbranch.get(nameBranch));
                                                    datatAbsenUserData.set_txtBranchName(spnBranch.getSelectedItem().toString());
                                                    datatAbsenUserData.set_txtAccuracy(lblAcc.getText().toString());
                                                    datatAbsenUserData.set_txtLatitude(lblLang.getText().toString());
                                                    datatAbsenUserData.set_txtLongitude(lblLong.getText().toString());
                                                    datatAbsenUserData.set_txtOutletCode(outletCode);
                                                    datatAbsenUserData.set_txtOutletName(nameOutlet);
                                                    datatAbsenUserData.set_txtDeviceId(deviceInfo);
                                                    datatAbsenUserData.set_txtUserId(idUserActive);
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
                                                    Intent myIntent = new Intent(getContext(), MainMenu.class);
                                                    getActivity().finish();
                                                    startActivity(myIntent);
                                                }
                                            }
                                        } else {
                                            _clsMainActivity.showCustomToast(getContext(), errorMessage, false);
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

        tvLongOutlet = (TextView) v.findViewById(R.id.tvLongOutlet);
        tvLatOutlet = (TextView) v.findViewById(R.id.tvLatOutlet);

        spnOutlet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    tvLongOutlet.setText(listDataArea.get(i).get_txtLongitude());
                    tvLatOutlet.setText(listDataArea.get(i).get_txtLatitude());

                    if (mlongitude != 0 && mlatitude != 0) {
                        countDistance(mlatitude, mlongitude);
                    }
                } catch (Exception ex) {
                    new clsMainActivity().showCustomToast(getContext(), "Outlet location not found", false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return v;
    }

    public Location getLocation() {
        try {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
                new clsMainActivity().showCustomToast(getContext(), "Please turn on GPS and check your internet connection", false);
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

                if (isGPSEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
                    mLastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                } else {
                    _clsMainActivity.showCustomToast(getContext(), "Please turn on GPS", false);
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

            lblLong.setText(String.format("%s", longitude));
            lblLang.setText(String.format("%s", latitude));
            lblAcc.setText(String.format("%s", accurate));

            try {
                float distance = countDistance(mlatitude, mlongitude);
                lblDistance.setText(String.format("%s meters", String.valueOf((int) Math.ceil(distance))));
            } catch (Exception ignored) {

            }

            mlongitude = longitude;
            mlatitude = latitude;

        }

    }

    private float countDistance(double latitude, double longitude) {
        float distance;

        double latitudeOutlet = Double.parseDouble(HMoutletLat.get(spnOutlet.getSelectedItem().toString()));
        double longitudeOutlet = Double.parseDouble(HMoutletLang.get(spnOutlet.getSelectedItem().toString()));

        Location locationA = new Location("point user");

        locationA.setLatitude(latitude);
        locationA.setLongitude(longitude);

        Location locationB = new Location("point outlet");

        locationB.setLatitude(latitudeOutlet);
        locationB.setLongitude(longitudeOutlet);

        distance = locationA.distanceTo(locationB);

        lblDistance.setText(String.format("%s meters", String.valueOf((int) Math.ceil(distance))));

        return distance;
    }

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

    }

    @SuppressWarnings("deprecation")
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(), PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onResume() {
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
                    String uri = uriImage.getPath();

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
                    String uri = uriImage.getPath();

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
            byte[] pht = new byte[0];
            if (out != null) {
                pht = out.toByteArray();
            }
            imgPrevNoImg1.setImageBitmap(photo_view);
            dttAbsenUserData.set_txtImg1(pht);

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
            byte[] pht = new byte[0];
            if (out != null) {
                pht = out.toByteArray();
            }
            imgPrevNoImg2.setImageBitmap(photo_view);
            dttAbsenUserData.set_txtImg2(pht);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {

    }

    @Override
    public void onConnected(@Nullable Bundle arg0) {

    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();

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
}