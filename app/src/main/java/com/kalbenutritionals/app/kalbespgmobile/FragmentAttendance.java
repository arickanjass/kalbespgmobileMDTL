package com.kalbenutritionals.app.kalbespgmobile;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import bl.clsHelperBL;
import bl.mEmployeeBranchBL;
import bl.tAbsenUserBL;
import bl.tAttendanceUserBL;
import bl.tDeviceInfoUserBL;
import bl.tPlanogramMobileBL;
import bl.tUserLoginBL;
import library.spgmobile.common.mEmployeeBranchData;
import library.spgmobile.common.tAttendanceUserData;
import library.spgmobile.common.tDeviceInfoUserData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.visitplanAbsenData;
import library.spgmobile.dal.clsHardCode;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by aan.junianto on 21/08/2017.
 */

public class FragmentAttendance extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, View.OnClickListener {
    View v;
    private Button btnRefreshMaps, btnViewMap, buttonCheckIn;
    EditText etOutlet;
    TextInputLayout input_layout_et_outlet;
    TextView tvLong, tvLat, tvAcc;
    private ImageView imageViewCamera1;
    private ImageView imageViewCamera2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_attendace, container, false);
        imageViewCamera1 = (ImageView) v.findViewById(R.id.imageViewCamera1);
        imageViewCamera2 = (ImageView) v.findViewById(R.id.imageViewCamera2);

        imageViewCamera1.setOnClickListener(this);
        imageViewCamera2.setOnClickListener(this);

        input_layout_et_outlet = (TextInputLayout) v.findViewById(R.id.input_layout_et_outlet);
        etOutlet = (EditText) v.findViewById(R.id.etOutlet);

        tvLong = (TextView) v.findViewById(R.id.tvLong);
        tvLat = (TextView) v.findViewById(R.id.tvLat);
        tvAcc = (TextView) v.findViewById(R.id.tvAcc);

        tvLong.setText("");
        tvLat.setText("");
        tvAcc.setText("");

        //get location koordinate
        getLocation();

        if (mLastLocation != null) {
            displayLocation(mLastLocation);
        }

        if (checkPlayServices()) {
            buildGoogleApiClient();
        }

        Button btnRefreshMaps = (Button) v.findViewById(R.id.btnRefreshMaps);
        btnRefreshMaps.setOnClickListener(this);

        Button btnViewMap = (Button) v.findViewById(R.id.btnViewMap);
        btnViewMap.setOnClickListener(this);

        Button buttonCheckIn = (Button) v.findViewById(R.id.buttonCheckIn);
        buttonCheckIn.setOnClickListener(this);

        etOutlet.setFilters(new InputFilter[]{
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

        return v;
    }

    // get location GPS
    private boolean earlyState = true;
    private Location mLastLocation;
    clsMainActivity _clsMainActivity = new clsMainActivity();
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

            tvLong.setText(String.format("%s", longitude));
            tvLat.setText(String.format("%s", latitude));
            tvAcc.setText(String.format("%s", df.format(accurate)));

        } else {
            tvLong.setText("");
            tvLat.setText("");
            tvAcc.setText("");
        }

    }

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
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

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;
    private void buildGoogleApiClient() {
        // TODO Auto-generated method stub
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        if(mGoogleApiClient!=null){
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageViewCamera1:
                captureImageViewCamera1();
                break;
            case R.id.imageViewCamera2:
                captureImageViewCamera2();
                break;
            case R.id.btnRefreshMaps:
                getLocation();
                if (mLastLocation == null) {
                    displayLocation(mLastLocation);
                }
                break;
            case R.id.btnViewMap:
                viewPopUpMap();
                break;
            case R.id.buttonCheckIn:
                new clsMainActivity().removeErrorMessage(input_layout_et_outlet);
                if (etOutlet.getText().toString().equals("") && etOutlet.getText().toString().length() == 0) {
                    new clsMainActivity().setErrorMessage(getContext(), input_layout_et_outlet, etOutlet, "Please give Description");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        etOutlet.setBackground(null);
                    }
                } else if(tvLong.getText().toString()==""&&tvAcc.getText().toString()==""){
                    new clsMainActivity().showCustomToast(getContext(), "Failed checkin: Location not found, please check your GPS", false);
                } else if (imgPhoto1==null&&imgPhoto2==null){
                    new clsMainActivity().showCustomToast(getContext(), "Please Photo at least 1 photo", false);
                } else {

                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Confirm");
                    alertDialog.setMessage("Are you sure to Check In?");
                    alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tAttendanceUserData _tAttendanceUserData = new tAttendanceUserData();
                            int IdAbsen = new tAttendanceUserBL().getContactsCount() + 1;
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Calendar cal = Calendar.getInstance();
                            visitplanAbsenData dtAbsen = new clsHelperBL().getDataCheckInActive();
                            List<tDeviceInfoUserData> dataDeviceInfoUser = new tDeviceInfoUserBL().getData(0);
                            String deviceInfo="";
                            if(dataDeviceInfoUser!=null){
                                deviceInfo = String.valueOf(dataDeviceInfoUser.get(0).get_txtDeviceId());
                            }
                            tUserLoginData dtLogin = new tUserLoginBL().getUserLogin();

                            List<mEmployeeBranchData> _mEmployeeBranchData = new mEmployeeBranchBL().GetAllData();

                            _tAttendanceUserData.set_intId(String.valueOf(IdAbsen));
                            _tAttendanceUserData.set_dtDateCheckIn(dateFormat.format(cal.getTime()));
                            _tAttendanceUserData.set_intSubmit("0");
                            _tAttendanceUserData.set_intSync("0");
                            _tAttendanceUserData.set_txtAbsen("0");
                            _tAttendanceUserData.set_txtAccuracy(tvAcc.getText().toString());
                            _tAttendanceUserData.set_txtLatitude(tvLat.getText().toString());
                            _tAttendanceUserData.set_txtLongitude(tvLong.getText().toString());
                            if(_mEmployeeBranchData!=null){
                                _tAttendanceUserData.set_txtBranchCode(_mEmployeeBranchData.get(0).get_txtBranchCode());
                                _tAttendanceUserData.set_txtBranchName(_mEmployeeBranchData.get(0).get_txtBranchName());
                            }
                            _tAttendanceUserData.set_txtOutletCode(dtLogin.get_txtOutletCode());
                            _tAttendanceUserData.set_txtOutletName(dtLogin.get_txtOutletName());
                            _tAttendanceUserData.set_txtDesc(etOutlet.getText().toString());
                            _tAttendanceUserData.set_txtDeviceId(deviceInfo);
                            _tAttendanceUserData.set_txtImg1(imgPhoto1);
                            _tAttendanceUserData.set_txtImg2(imgPhoto2);
                            _tAttendanceUserData.set_txtRoleId(dtLogin.get_txtRoleId());
                            _tAttendanceUserData.set_txtUserId(dtLogin.get_txtUserId());

                            new tAttendanceUserBL().saveData(_tAttendanceUserData);

                            new clsMainActivity().showCustomToast(getContext(), "Submit", true);

                            Intent myIntent = new Intent(getContext(), MainMenu.class);
                            getActivity().finish();
                            startActivity(myIntent);
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

    private void viewPopUpMap() {
        Boolean valid = true;

        double latitude = 0;
        double longitude = 0;
        double latitudeOutlet = 0;
        double longitudeOutlet = 0;

        //Check longlat my location
        try {
            latitude = Double.parseDouble(String.valueOf(tvLat.getText()));
            longitude = Double.parseDouble(String.valueOf(tvLong.getText()));
        } catch (Exception ex) {
            valid = false;
            new clsMainActivity().showCustomToast(getContext(), "Your location not found", false);
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

            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16));

            final LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(marker.getPosition());

            mMap.clear();
            mMap.addMarker(marker);

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

            alertD.setTitle("Maps");
            alertD.show();
        }
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
        return Uri.fromFile(getOutputMediaFile());
    }
    //create path file
    private static final String IMAGE_DIRECTORY_NAME = "Image Attendace";
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
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "tmp_attendance" + ".png");
        return mediaFile;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_IMAGE_VIEW_1_ACTIVITY_REQUEST_CODE) {
            if (resultCode == -1) {
                try {

                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath();

                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

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

                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath();

                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

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

            imageViewCamera1.setImageBitmap(photo_view);

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

            imageViewCamera2.setImageBitmap(photo_view);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
