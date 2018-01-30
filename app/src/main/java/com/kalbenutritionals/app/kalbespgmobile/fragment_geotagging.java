package com.kalbenutritionals.app.kalbespgmobile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

//import com.activeandroid.query.Delete;
//import com.activeandroid.query.Select;
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
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MBranch;
//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MPartnerProfile;
//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MPartnerProfileAlias;
//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_TipeSumberData;
//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_UserJabatan;
//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_ValidationNo;
//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mAllbranch;
//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVersionApp;
//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trUserLogin;
//import callplan.prm.kalbe.callplanlibrary.common.clsSpinner;
//import callplan.prm.kalbe.callplanlibrary.common.clsWarning;
//import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_trUserLoginBL;
//import callplan.prm.kalbe.kalbecallplanmobile.bl.clsMainBL;

import bl.clsHelperBL;
import bl.clsMainBL;
import bl.mEmployeeAreaBL;
import bl.mEmployeeBranchBL;
import bl.mTipeSumberDataBL;
import bl.tUserLoginBL;
import bl.tVisitPlanRealisasiBL;
import library.spgmobile.common.APIData;
import library.spgmobile.common.clsSpinner;
import library.spgmobile.common.mEmployeeAreaData;
import library.spgmobile.common.mEmployeeBranchData;
import library.spgmobile.common.mTipeSumberData;
import library.spgmobile.common.mTypeLeaveMobileData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.tVisitPlanRealisasiData;
import library.spgmobile.dal.clsHardCode;

import static android.content.Context.LOCATION_SERVICE;

public class fragment_geotagging extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, android.location.LocationListener{

    public fragment_geotagging() {
        // Required empty public constructor
    }


    View v;
    clsMainBL _clsMainBL = null;
    clsHardCode _clsWarning = null;
    private String Id;
    @SuppressLint("ValidFragment")
    private String mCurrentPhotoPath;
    private TextInputLayout input_layout_poa_search, input_layout_outlet_search, textDescription, textPreNC, textBobot;
    private LinearLayout lnOutletLocation;
    private LinearLayout llCategotyOutlet;
    private LinearLayout llOutletCode;
    private LinearLayout llInfoOutlet;
    private RelativeLayout rlDatePlan;
    //private Spinner spnNoRealisasi;
    private EditText etRealisasi;
    private CheckBox cbFullDay;
    private Spinner spnBranch;
    private Spinner spnCategory;
    private EditText etPOA;
    private Spinner spnLOB;
    private EditText etOutletName;
    private TextView txtLatOutlet;
    private TextView txtLongOutlet;
    private EditText etDesc;
    private EditText etDatePlan;
    private EditText etBobot;
    private EditText etPreNC;
    private Integer intdistance;
    private RelativeLayout trLocation;
    //private TableRow trLong;
    //private TableRow trLati;
    //private TableRow trAcc;
    //private TableRow trDistance;
    private TextView tvTypeOutlet;
    private Spinner spnOutletAlias;
    private List<String> ListTitle;
    private List<String> ListDesc;
    private TextView lblLong;
    private TextView lblLang;
    private TextView lblAcc;
    private TextView txtHDLati;
    private TextView txtHDLong;
    private TextView txtHDActualDate;
    private TextView txtHDId;
    private TextView tvViewDate;
    private TextView tvDistance;
    private EditText tvAddInformation;
    private Button btnRefreshMaps, btnPopupMap;
    private Button btnCheckIn;
    private Button btnEDetailing;
    String txtProcess;
    private View viewEmpty;
    private LinearLayout llimgview;
    private static final int CAMERA_CAPTURE_IMAGE1_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_IMAGE2_REQUEST_CODE = 130;
    private GoogleMap mMap;
    String txtIdNoRealisasi;
    private Location mLastLocation;
    private RadioGroup rdtypeSearch;
    private RelativeLayout RLPreNC;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private static final String TAG = FragmentVisitPlan.class.getSimpleName();
    private GoogleApiClient mGoogleApiClient;
    private boolean mRequestingLocationUpdates = false;
    private LocationRequest mLocationRequest;
    private EditText etSearch;
    private ListView lvSearch;
    private static int UPDATE_INTERVAL = 3000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters
    private String Long;
    private String Lat;
    private String Acc;
    private EditText etOutletCode;
    private ImageView imgPrevNoImg1;
    private Spinner spnCategoryOutlet;
    private Spinner spnListOutlet;
    private String txtOutletKodeSumberDataID;
    private TableLayout tblData;
    private ImageView imgPrevNoImg2;
    private String IMAGE_DIRECTORY_NAME = "Image Sales";
    final String finalFile = null;
    private GoogleApiClient client;
    List<clsSpinner> listSpinnerBranch = null;
    List<clsSpinner> listSpinnerCategory = null;
//    clsMobile_MPartnerProfile _clsMobile_MPartnerProfile;
    android.support.v7.app.AlertDialog alertD;
//    List<clsMobile_ValidationNo> ListOfclsMobile_ValidationNoe=null;
    ListView listData;
    View viewListEmpty;

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
                new clsMainBL().showToastWarning(getContext(), "Aktifkan Mode GPS!!" );
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
                                0,  this);
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
    private void blankPosition(Boolean val){
        txtLatOutlet.setText("");
        txtLongOutlet.setText("");
        if(!val){
            llInfoOutlet.setVisibility(View.GONE);
        }else{
            llInfoOutlet.setVisibility(View.VISIBLE);
        }
    }
    List<mEmployeeBranchData> ListOfclsMobile_mAllbranch = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragment_geotagging, container, false);
        _clsWarning = new clsHardCode();
        _clsMainBL = new clsMainBL();
        txtHDLati=(TextView)  v.findViewById(R.id.txtHDLati);
        txtHDLong=(TextView)  v.findViewById(R.id.txtHDLong);
        txtHDActualDate=(TextView)  v.findViewById(R.id.txtHDActualDate);
        llimgview=(LinearLayout)  v.findViewById(R.id.llimgview);
        llInfoOutlet=(LinearLayout)  v.findViewById(R.id.llInfoOutlet);
        btnRefreshMaps = (Button) v.findViewById(R.id.btnRefreshMaps);
//        btnEDetailing= (Button) v.findViewById(R.id.btnEDetailing);
        btnPopupMap = (Button) v.findViewById(R.id.viewMap);
        btnCheckIn = (Button) v.findViewById(R.id.buttonCheckIn);
        lblLong = (TextView) v.findViewById(R.id.tvLong);
        lblLang = (TextView) v.findViewById(R.id.tvLat);
        txtLatOutlet = (TextView) v.findViewById(R.id.txtLatOutlet);
        txtLongOutlet = (TextView) v.findViewById(R.id.txtLongOutlet);
//        tvViewDate = (TextView) v.findViewById(R.id.tvViewDate);
        lblAcc = (TextView) v.findViewById(R.id.tvAcc);
        spnCategory= (Spinner) v.findViewById(R.id.spnCategory);
        spnCategoryOutlet= (Spinner) v.findViewById(R.id.spnCategoryOutlet);
        spnListOutlet= (Spinner) v.findViewById(R.id.spnListOutlet);
        spnBranch = (Spinner) v.findViewById(R.id.spnBranch);
        etOutletName= (EditText) v.findViewById(R.id.etOutletName);
        trLocation= (RelativeLayout) v.findViewById(R.id.trLocation);
        llCategotyOutlet=(LinearLayout) v.findViewById(R.id.llCategotyOutlet);
        llOutletCode=(LinearLayout) v.findViewById(R.id.llOutletCode);
        etOutletCode=(EditText)v.findViewById(R.id.etOutletCode);
        lblLong.setText("");
        lblLang.setText("");
        lblAcc.setText("");
        spnBranch.setAdapter(null);

        etOutletCode.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    txtProcess=_clsWarning.txtConstProcessGetDataSumberDataforWebDashboard;
                    spnListOutlet.setAdapter(null);
                    txtOutletKodeSumberDataID="";
                    AsyncCall call= new AsyncCall();
                    call.execute();
                    return true;
                }
                return false;
            }
        });
        etOutletCode.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etOutletCode) {
            public boolean onDrawableClick() {
                txtProcess=_clsWarning.txtConstProcessGetDataSumberDataforWebDashboard;
                spnListOutlet.setAdapter(null);
                txtOutletKodeSumberDataID="";
                AsyncCall call= new AsyncCall();
                call.execute();
                return true;
            }
        });
        etOutletName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if(etOutletName.getText().length()>=3){
                        txtProcess=_clsWarning.txtConstProcessGetDataSumberDataforWebDashboard;
                        spnListOutlet.setAdapter(null);
                        txtOutletKodeSumberDataID="";
                        AsyncCall call= new AsyncCall();
                        call.execute();
                    }else{
                        _clsMainBL.showToastWarning(getContext(),"please input min 3 character");
                    }
                    return true;
                }
                return false;
            }
        });

        etOutletName.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etOutletName) {
            public boolean onDrawableClick() {
                if(etOutletName.getText().length()>=3){
                    txtOutletKodeSumberDataID="";
                    txtProcess=_clsWarning.txtConstProcessGetDataSumberDataforWebDashboard;
                    spnListOutlet.setAdapter(null);
                    AsyncCall call= new AsyncCall();
                    call.execute();
                }else{
                    _clsMainBL.showToastWarning(getContext(),"please input min 3 character");
                    try  {
                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {

                    }
                }
                return true;
            }
        });

        ListOfclsMobile_mAllbranch = new mEmployeeBranchBL().GetAllData();
        if (ListOfclsMobile_mAllbranch.size() > 0) {
            listSpinnerBranch = new ArrayList<clsSpinner>();
            for (mEmployeeBranchData dtclsMobile_mAllbranch : ListOfclsMobile_mAllbranch) {
                clsSpinner _clsSpinner = new clsSpinner(dtclsMobile_mAllbranch.get_txtBranchCode().toString(), dtclsMobile_mAllbranch.get_txtBranchName());
                listSpinnerBranch.add(_clsSpinner);
            }
            ArrayAdapter<clsSpinner> adapter = new ArrayAdapter<clsSpinner>(getContext(), android.R.layout.simple_spinner_item, listSpinnerBranch);
            spnBranch.setAdapter(adapter);
            blankPosition(false);
        }
        blankPosition(false);
        spnListOutlet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                blankPosition(true);
                txtOutletKodeSumberDataID="";
                clsSpinner _clsSpinner = (clsSpinner) parent.getSelectedItem();
                mTipeSumberData _dtclsMobile_TipeSumberData=new mTipeSumberData();
                List<mTipeSumberData> listData= new mTipeSumberDataBL().GetAllDataListByOutletCode(_clsSpinner.getID().toString());
                txtOutletKodeSumberDataID=listData.get(0).getTipeSumberDataID();
                if(listData.size()>0){
                    if(listData.get(0).getTxtLatitude().equals("")){
                        listData.get(0).getTxtLatitude().equals("0");
                    }
                    if(listData.get(0).getTxtLongitude().equals("")){
                        listData.get(0).getTxtLongitude().equals("0");
                    }
                    txtLatOutlet.setText("Latitude : "+listData.get(0).getTxtLatitude());
                    txtLongOutlet.setText("Longitude : "+listData.get(0).getTxtLongitude());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spnCategoryOutlet.setAdapter(null);
                spnListOutlet.setAdapter(null);
                blankPosition(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        RadioButton radio_outlet_code = (RadioButton) v.findViewById(R.id.radio_outlet_code);
        RadioButton radio_outlet_name = (RadioButton) v.findViewById(R.id.radio_outlet_name);
        RadioButton radio_outlet_callPLan = (RadioButton) v.findViewById(R.id.radio_outlet_callPLan);
        //RadioGroup rdtypeSearch = (RadioGroup) v.findViewById(R.id.rdtypeSearch);
        llCategotyOutlet.setVisibility(View.GONE);
        View.OnClickListener first_radio_listener = new View.OnClickListener (){
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                blankPosition(false);
                etOutletCode.setText("");
                spnCategoryOutlet.setAdapter(null);
                spnListOutlet.setAdapter(null);
                etOutletName.setText("");
                //Your Implementaions...
                switch(v.getId()) {
                    case R.id.radio_outlet_code:
                        if (checked)
                            new mTipeSumberDataBL().deleteAllData();
                            llCategotyOutlet.setVisibility(View.GONE);
                            llOutletCode.setVisibility(View.VISIBLE);

                        break;
                    case R.id.radio_outlet_name:
                        if (checked)
                            new mTipeSumberDataBL().deleteAllData();
                            llCategotyOutlet.setVisibility(View.VISIBLE);
                            llOutletCode.setVisibility(View.GONE);
                            txtProcess=_clsWarning.txtConstProcessGetDataTipeSumberDataforWebDashboard;
                            spnCategoryOutlet.setAdapter(null);
                            AsyncCall call= new AsyncCall();
                            call.execute();
                        break;
                    case R.id.radio_outlet_callPLan:
                        if (checked)
                            llOutletCode.setVisibility(View.GONE);
                            llCategotyOutlet.setVisibility(View.GONE);
                            new mTipeSumberDataBL().deleteAllData();
                            List<mEmployeeAreaData> ListDataMpartner=new mEmployeeAreaBL().GetAllData();
//                            List<clsMobile_MPartnerProfileAlias> ListDataMpartnerAlias=new Select().from(clsMobile_MPartnerProfileAlias.class).execute();
                            listSpinnerCategory=new ArrayList<>();
                            for (mEmployeeAreaData dt:ListDataMpartner) {
                                mTipeSumberData dtclsMobile_TipeSumberData=new mTipeSumberData();
                                List<mTipeSumberData> listDataCheck=new mTipeSumberDataBL().GetAllDataListByOutletCode(dt.get_txtOutletCode());
                                if(listDataCheck.size()==0){
                                    dtclsMobile_TipeSumberData.setTxtLatitude(dt.get_txtLatitude());
                                    dtclsMobile_TipeSumberData.setTxtLongitude(dt.get_txtLongitude());
                                    dtclsMobile_TipeSumberData.setTxtAlamat("");
                                    dtclsMobile_TipeSumberData.setTipeSumberDataID(dt.get_txtOutletCode());
                                    dtclsMobile_TipeSumberData.setTxtAcc("");
                                    dtclsMobile_TipeSumberData.setTxtNamaInstitusi(dt.get_txtOutletName());
                                    new mTipeSumberDataBL().saveDataOne(dtclsMobile_TipeSumberData);
                                    clsSpinner _cls=new clsSpinner(dtclsMobile_TipeSumberData.getTipeSumberDataID(),dtclsMobile_TipeSumberData.getTxtNamaInstitusi());
                                    listSpinnerCategory.add(_cls);

                                }
                            }
//                            for (clsMobile_MPartnerProfileAlias dt:ListDataMpartnerAlias) {
//                                clsMobile_TipeSumberData dtclsMobile_TipeSumberData=new clsMobile_TipeSumberData();
//                                List<clsMobile_TipeSumberData> listDataCheck=new Select().from(clsMobile_TipeSumberData.class).where("txtSumberDataID=?",dt.txtPartnerCRM).execute();
//                                if(listDataCheck.size()==0){
//                                    dtclsMobile_TipeSumberData.txtLatitude=dt.txtLatitude;
//                                    dtclsMobile_TipeSumberData.txtLongitude=dt.txtLongitude;
//                                    dtclsMobile_TipeSumberData.txtAlamat=dt.txtAlamat;
//                                    dtclsMobile_TipeSumberData.txtSumberDataID=dt.txtPartnerCRM;
//                                    dtclsMobile_TipeSumberData.txtAcc=dt.txtAcc;
//                                    dtclsMobile_TipeSumberData.txtNamaInstitusi=dt.txtNamaPartner;
//                                    dtclsMobile_TipeSumberData.save();
//                                    clsSpinner _cls=new clsSpinner(dtclsMobile_TipeSumberData.txtSumberDataID,dtclsMobile_TipeSumberData.txtNamaInstitusi);
//                                    listSpinnerCategory.add(_cls);
//
//                                }
//                            }
                            ArrayAdapter<clsSpinner> adapter = new ArrayAdapter<clsSpinner>(getContext(), android.R.layout.simple_spinner_item, listSpinnerCategory);
                            spnListOutlet.setAdapter(adapter);
                            break;
                }
            }
        };
        radio_outlet_code.setOnClickListener(first_radio_listener);
        radio_outlet_name.setOnClickListener(first_radio_listener);
        radio_outlet_callPLan.setOnClickListener(first_radio_listener);
        client = new GoogleApiClient.Builder(getContext()).addApi(AppIndex.API).build();


        displayLocation();
        btnRefreshMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLocation();
                _clsMainBL.showToast(getContext(), "Location Updated");
            }
        });
        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(lblLang.getText().equals("") || lblLong.getText().equals("")|| lblAcc.getText().equals("")){
                    _clsMainBL.showToastWarning(getContext(), "Your Location Not Found");
                }else if(txtOutletKodeSumberDataID.equals("")){
                    _clsMainBL.showToastWarning(getContext(), "Please Select Outlet");
                }else {
                    txtProcess=_clsWarning.txtConstProcessSaveDataSumberDataforWebDashboard;
                    AsyncCall call = new AsyncCall();
                    call.execute();
                }
            }
        });
        btnPopupMap.setVisibility(View.INVISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            btnPopupMap.setVisibility(View.VISIBLE);
        }

        btnPopupMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lblLang.getText().equals("") || lblLong.getText().equals("") || lblAcc.getText().equals("")) {
                    _clsMainBL.showToastWarning(getContext(), "Your Location Not Found");
                } else {

                    try {
                        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                        final View promptView = layoutInflater.inflate(R.layout.popup_map_absen, null);

                        mMap = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                            mMap = ((MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map)).getMap();
                            if (mMap == null) {
                                mMap = ((MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map)).getMap();
                            }

                            double latitude = Double.parseDouble(String.valueOf(lblLang.getText()));
                            double longitude = Double.parseDouble(String.valueOf(lblLong.getText()));
                            double accurate = Double.parseDouble(String.valueOf(lblAcc.getText()));

                            double latitudeOutlet = Double.parseDouble("232.23");
                            double longitudeOutlet = Double.parseDouble("-232.23");
                            if (txtHDLati.getText().toString().equals("")==false && txtHDLong.getText().toString().equals("")==false) {
                                latitudeOutlet = Double.parseDouble(txtHDLati.getText().toString());
                                longitudeOutlet = Double.parseDouble(txtHDLong.getText().toString());
                            }
                            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Your Location");

                            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                            final LatLngBounds.Builder builder = new LatLngBounds.Builder();
                            builder.include(marker.getPosition());
                            LatLngBounds bounds = builder.build();

                            mMap.clear();
                            mMap.addMarker(marker);
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
                            alertD.setTitle("your location");
                            alertD.show();
                        }
                    } catch (Exception e){
                        _clsMainBL.showToastWarning(getContext(), e.getMessage());
                    }
                }
            }
        });
        return v;
    }
    private void SaveDataSumberDataforWebDashboard(org.json.simple.JSONArray JsonRes){
        spnListOutlet.setAdapter(null);
        blankPosition(false);
        if(_clsMainBL.ValidJSON_a(JsonRes)){

            APIData dtAPIDATA = new APIData();
            Iterator i = JsonRes.iterator();
            while (i.hasNext()) {
                org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
                int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));

                if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
                    List<mEmployeeAreaData> listclsMobile_MPartnerProfile=new mEmployeeAreaBL().GetAllDataListByOutletCode(txtOutletKodeSumberDataID);
//                    List<clsMobile_MPartnerProfileAlias> listclsMobile_MPartnerProfileAlias=new Select().from(clsMobile_MPartnerProfileAlias.class).where(new clsMobile_MPartnerProfileAlias().txtConsttxtPartnerCRM+"=?",txtOutletKodeSumberDataID).execute();
                    if(listclsMobile_MPartnerProfile.size()>0){
                        mEmployeeAreaData dt=listclsMobile_MPartnerProfile.get(0);
                        dt.set_txtLatitude(lblLang.getText().toString());
                        dt.set_txtLongitude(lblLong.getText().toString());
//                        dt.txtAcc=lblAcc.getText().toString();
//                        dt.save();
                        new mEmployeeAreaBL().saveData(dt);
                        tVisitPlanRealisasiData data = new tVisitPlanRealisasiData();
                        data.set_txtLatSource(lblLang.getText().toString());
                        data.set_txtLongSource(lblLong.getText().toString());
                        new tVisitPlanRealisasiBL().UpdateDataTagging(data);
                    }
//                    if(listclsMobile_MPartnerProfileAlias.size()>0){
//                        clsMobile_MPartnerProfileAlias dt=listclsMobile_MPartnerProfileAlias.get(0);
//                        dt.txtLatitude=lblLang.getText().toString();
//                        dt.txtLongitude=lblLong.getText().toString();
//                        dt.txtAcc=lblAcc.getText().toString();
//                        dt.save();
//                    }
                    etOutletName.setText("");
                    etOutletCode.setText("");
//                    String txtvalidJsonError=(String.valueOf(JsonRes.get("validJson")));
//                    JSONObject validJsonError=new JSONObject(txtvalidJsonError);
//                    String txtWarn=(String) validJsonError.get("TxtWarn");
                    _clsMainBL.showToast(getContext(),"Sukses Save Geolocation");
                }else{
                    String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrMessage()));
                    _clsMainBL.showToastWarning(getContext(),pstrArgumet);
                }
            }

        }
    }
    List<String> ListSpinTitle;
    List<mTipeSumberData> ListDataclsMobile_TipeSumberData;
    List<String> ListSpinDesc;
    private void GetDataSumberDataforWebDashboard(org.json.simple.JSONArray JsonRes){
        spnListOutlet.setAdapter(null);
        blankPosition(false);
        if(_clsMainBL.ValidJSON_a(JsonRes)){
            APIData dtAPIDATA = new APIData();
            Iterator i = JsonRes.iterator();
            listSpinnerCategory=new ArrayList<>();
            new mTipeSumberDataBL().deleteAllData();
            while (i.hasNext()) {
                org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
                int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));

                if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
                    ListDataclsMobile_TipeSumberData=new ArrayList<>();
                    ListSpinTitle=new ArrayList<String>();
                    ListSpinDesc=new ArrayList<String>();
                    listSpinnerBranch=new ArrayList<>();
                        mTipeSumberData _dtclsMobile_TipeSumberData=new mTipeSumberData();
                        String TxtNamaInstitusi = String.valueOf(innerObj.get("TxtNamaInstitusi"));
                        String TipeSumberData = String.valueOf(innerObj.get("TipeSumberData"));
                        String TxtSumberDataID = String.valueOf(innerObj.get("TxtSumberDataID"));
                        String TxtCabangID = String.valueOf(innerObj.get("TxtCabangID"));
                        String TxtLong = String.valueOf(innerObj.get("TxtLongitude"));
                        String TxtLat = String.valueOf(innerObj.get("TxtLatitude"));
                        String TxtAcc = String.valueOf(innerObj.get("TxtAcc"));
                        String TxtAlamat = String.valueOf(innerObj.get("TxtAlamat"));
                        String TxtNamaPropinsi = String.valueOf(innerObj.get("TxtNamaPropinsi"));
                        String TxtNamaKabKota = String.valueOf(innerObj.get("TxtNamaKabKota"));
                        _dtclsMobile_TipeSumberData.setTxtNamaInstitusi(TxtNamaInstitusi);
                        _dtclsMobile_TipeSumberData.setTipeSumberDataID(TxtSumberDataID);
                        _dtclsMobile_TipeSumberData.setTxtSumberDataID(TipeSumberData);
                        _dtclsMobile_TipeSumberData.setTxtCabangID(TxtCabangID);
                        _dtclsMobile_TipeSumberData.setTxtLongitude(TxtLong);
                        _dtclsMobile_TipeSumberData.setTxtLatitude(TxtLat);
                        _dtclsMobile_TipeSumberData.setTxtAcc(TxtAcc);
                        _dtclsMobile_TipeSumberData.setTxtAlamat(TxtAlamat);
                        _dtclsMobile_TipeSumberData.setTxtNamaPropinsi(TxtNamaPropinsi);
                        ListDataclsMobile_TipeSumberData.add(_dtclsMobile_TipeSumberData);
                        new mTipeSumberDataBL().saveDataOne(_dtclsMobile_TipeSumberData);
                        clsSpinner _cls=new clsSpinner(_dtclsMobile_TipeSumberData.getTipeSumberDataID(),_dtclsMobile_TipeSumberData.getTxtNamaInstitusi());
                        listSpinnerCategory.add(_cls);
                }else{
                    String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrMessage()));
                    _clsMainBL.showToastWarning(getContext(),pstrArgumet);
                }
            }

            if(listSpinnerCategory.size()>0){
                ArrayAdapter<clsSpinner> adapter = new ArrayAdapter<clsSpinner>(getContext(), android.R.layout.simple_spinner_item, listSpinnerCategory);
                spnListOutlet.setAdapter(adapter);
            }
        }
    }
    private void GetDataSumberDataforWebDashboardByOutlet(JSONObject JsonRes){
        if(_clsMainBL.ValidJSON(JsonRes)){
            try {
                String txtvalidJson=(String.valueOf(JsonRes.get("validJson")));
                JSONObject validJson=new JSONObject(txtvalidJson);
                String intresult=(String.valueOf(validJson.get("TxtResult")));
                if(intresult.equals("1")){
//                    new Delete().from(clsMobile_TipeSumberData.class).execute();
                    new mTipeSumberDataBL().deleteAllData();
                    ListDataclsMobile_TipeSumberData=new ArrayList<>();
                    String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                    JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
                    String txtGetDataJsonOutlet = String.valueOf(JsonDataTxtData.get("VwGetDatadSumberDataforWebDashboard"));
                    JSONArray JSONArrayOutlet =new JSONArray(txtGetDataJsonOutlet);
                    ListSpinTitle=new ArrayList<String>();
                    ListSpinDesc=new ArrayList<String>();
                    listSpinnerBranch=new ArrayList<>();
                    listSpinnerCategory=new ArrayList<>();
                    for (int i = 0; i < JSONArrayOutlet.length(); i++) {
                        JSONObject JSONObjectOutlet = JSONArrayOutlet.getJSONObject(i);
                        mTipeSumberData _dtclsMobile_TipeSumberData=new mTipeSumberData();
                        String TxtNamaInstitusi = String.valueOf(JSONObjectOutlet.get("TxtNamaInstitusi"));
                        String TipeSumberData = String.valueOf(JSONObjectOutlet.get("TipeSumberData"));
                        String TxtSumberDataID = String.valueOf(JSONObjectOutlet.get("TxtSumberDataID"));
                        String TxtCabangID = String.valueOf(JSONObjectOutlet.get("TxtCabangID"));
                        String TxtLong = String.valueOf(JSONObjectOutlet.get("TxtLong"));
                        String TxtLat = String.valueOf(JSONObjectOutlet.get("TxtLat"));
                        String TxtAcc = String.valueOf(JSONObjectOutlet.get("TxtAcc"));
                        String TxtAlamat = String.valueOf(JSONObjectOutlet.get("TxtAlamat"));
                        String TxtNamaPropinsi = String.valueOf(JSONObjectOutlet.get("TxtNamaPropinsi"));
                        String TxtNamaKabKota = String.valueOf(JSONObjectOutlet.get("TxtNamaKabKota"));
                        _dtclsMobile_TipeSumberData.setTxtNamaInstitusi(TxtNamaInstitusi);
                        _dtclsMobile_TipeSumberData.setTipeSumberDataID(TipeSumberData);
                        _dtclsMobile_TipeSumberData.setTxtSumberDataID(TxtSumberDataID);
                        _dtclsMobile_TipeSumberData.setTxtCabangID(TxtCabangID);
                        _dtclsMobile_TipeSumberData.setTxtLongitude(TxtLong);
                        _dtclsMobile_TipeSumberData.setTxtLatitude(TxtLat);
                        _dtclsMobile_TipeSumberData.setTxtAcc(TxtAcc);
                        _dtclsMobile_TipeSumberData.setTxtAlamat(TxtAlamat);
                        _dtclsMobile_TipeSumberData.setTxtNamaPropinsi(TxtNamaPropinsi);
//                        _dtclsMobile_TipeSumberData.save();
                        ListDataclsMobile_TipeSumberData.add(_dtclsMobile_TipeSumberData);
                        new mTipeSumberDataBL().saveData(ListDataclsMobile_TipeSumberData);
                        clsSpinner _cls=new clsSpinner(_dtclsMobile_TipeSumberData.txtSumberDataID,_dtclsMobile_TipeSumberData.txtNamaInstitusi);
                        listSpinnerCategory.add(_cls);
                        //ListSpinTitle.add(_dtclsMobile_TipeSumberData.txtNamaInstitusi);
                        //ListSpinDesc.add(_dtclsMobile_TipeSumberData.txtAlamat + " " + _dtclsMobile_TipeSumberData.txtNamaPropinsi);
                    }
                    //ListSpinCategory=ListSpinTitle;
                    ArrayAdapter<clsSpinner> adapter = new ArrayAdapter<clsSpinner>(getContext(), android.R.layout.simple_spinner_item, listSpinnerCategory);
                    spnListOutlet.setAdapter(adapter);
                    //spnCategoryOutlet.setEnabled(true);
                }else{
                    String txtvalidJsonError=(String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJsonError=new JSONObject(txtvalidJsonError);
                    String txtWarn=(String) validJsonError.get("TxtWarn");
                    _clsMainBL.showToastWarning(getContext(),txtWarn);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private void GetDataTipeSumberDataforWebDashboard(org.json.simple.JSONArray JsonRes){
        if(_clsMainBL.ValidJSON_a(JsonRes)){
            APIData dtAPIDATA = new APIData();
            Iterator i = JsonRes.iterator();
            listSpinnerCategory=new ArrayList<>();
            while (i.hasNext()) {
                org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
                int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
                if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
//                        String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
//                        JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
//                        String txtGetDataJsonOutlet = String.valueOf(JsonDataTxtData.get("VwGetDatadSumberDataforWebDashboard"));
//                        JSONArray JSONArrayOutlet =new JSONArray(txtGetDataJsonOutlet);
                    ListSpinTitle=new ArrayList<String>();
                    ListSpinDesc=new ArrayList<String>();
//                            JSONObject JSONObjectOutlet = JSONArrayOutlet.getJSONObject(i);
                        mTipeSumberData _dtclsMobile_TipeSumberData=new mTipeSumberData();
                        String TipeSumberData = String.valueOf(innerObj.get("TipeSumberData"));
                        _dtclsMobile_TipeSumberData.setTxtSumberDataID(TipeSumberData);
                        clsSpinner _cls=new clsSpinner(_dtclsMobile_TipeSumberData.getTxtSumberDataID(),_dtclsMobile_TipeSumberData.getTxtSumberDataID());
                        listSpinnerCategory.add(_cls);
                }else{
                    String pstrArgumet = String.valueOf(innerObj.get(new APIData().getStrMessage()));
                    _clsMainBL.showToastWarning(getContext(),pstrArgumet);
                }

            }
            if(listSpinnerCategory.size()>0){
                ArrayAdapter<clsSpinner> adapter = new ArrayAdapter<clsSpinner>(getContext(), android.R.layout.simple_spinner_item, listSpinnerCategory);
                spnCategoryOutlet.setAdapter(adapter);
            }

        }
    }
    private class   AsyncCall extends AsyncTask<org.json.simple.JSONArray, Void, org.json.simple.JSONArray> {
        @SuppressWarnings("WrongThread")
        protected org.json.simple.JSONArray doInBackground(org.json.simple.JSONArray... params) {
            JSONObject JsonResult=null;
            org.json.simple.JSONArray JsonResult_a=null;
            try {
                JSONArray _JSONArray = null;
                JSONObject JsonParam=new JSONObject();

//                clsMobile_mVersionApp _clsMobile_mVersionApp=new clsMobile_mVersionApp();
//                List<clsMobile_mVersionApp> listOfclsMobile_mVersionApp= new Select().from(clsMobile_mVersionApp.class).where(_clsMobile_mVersionApp.txtConstidVersion+"=?",1).execute();
//                clsMobile_trUserLogin dtclsMobile_trUserLogin= new Mobile_trUserLoginBL().CheckUserLogin();
//                clsMobile_UserJabatan _clsMobile_UserJabatan=new clsMobile_UserJabatan();
//                List<clsMobile_UserJabatan> LisOfjabatan=new Select().from(clsMobile_UserJabatan.class).where(_clsMobile_UserJabatan.txtConstBitPrimary+"=1").execute();
//                List<clsMobile_MBranch> ListOfCabang=new Select().from(clsMobile_MBranch.class).execute();

                tUserLoginData dt = new tUserLoginBL().getUserActive();
                String versionName="";
                try {
                    versionName = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
//                    versionName = new clsMainActivity().getApplicationContext().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
                } catch (PackageManager.NameNotFoundException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }

                List<mEmployeeBranchData> ListOfCabang = new mEmployeeBranchBL().GetAllData();

                try {
                    _JSONArray=new JSONArray();
                    JsonParam.put("TxtGUI_trUserLogin",dt.get_intId());
                    JsonParam.put("TxtUserID",dt.get_txtUserId());
                    JsonParam.put("TxtGUI_mVersionApp",versionName);
                    String txtCabang ="";
                    int IndexCabang=0;
                    if(ListOfCabang.size()>0){
                        for (mEmployeeBranchData dtclsMobile_MBranch :ListOfCabang) {
                            if(IndexCabang>0){
                                txtCabang+=",";
                            }
                            txtCabang+=dtclsMobile_MBranch.get_txtBranchCode();
                            IndexCabang+=1;
                        }
                    }else{
                        txtCabang=dt.get_txtBranchCode();
                    }
                    JsonParam.put("IntCabangID",txtCabang);
                    JsonParam.put("txtCabangID",spnBranch.getSelectedItem().toString());
                    if(spnCategoryOutlet.getSelectedItem() == null){
                        JsonParam.put("txtTipeSumberDataID","");
                    }else{
                        JsonParam.put("txtTipeSumberDataID",spnCategoryOutlet.getSelectedItem().toString());
                    }
                    JsonParam.put("txtSumberDataID",etOutletCode.getText().toString());
                    JsonParam.put("txtNamaInstitusi",etOutletName.getText().toString());
//                    clsMobile_trUserLogin listDataclsMobile_trUserLogin=new Mobile_trUserLoginBL().CheckUserActive();


                    if(txtProcess.equals(_clsWarning.txtConstProcessSaveDataSumberDataforWebDashboard)){
                        JsonParam.put("txtSumberDataId",txtOutletKodeSumberDataID);
                        JsonParam.put("txtUser",dt.get_TxtEmpId());
                        JsonParam.put("txtLongitude",lblLong.getText().toString());
                        JsonParam.put("txtLatitude",lblLang.getText().toString());
                        JsonParam.put("txtAcc",lblAcc.getText().toString());
                        JsonParam.put("Source","ANDROID");
                    }

                    _JSONArray.put(JsonParam);

                    if(txtProcess.equals(_clsWarning.txtConstProcessGetDataSumberDataforWebDashboard)){
//                        JsonResult=_clsMainBL.PushData("GetDataSumberDataforWebDashboard_J",_JSONArray.toString());
//                        JsonResult = new clsHelperBL().callPushDataReturnJsonParamMethodJObject("GetDataSumberDataforWebDashboard_J",versionName,  _JSONArray.toString());
                        JsonResult_a = new clsHelperBL().callPushDataReturnJsonParamMethodJObject("GetDataSumberDataforWebDashboard_J",versionName,  _JSONArray.toString());
                    }
                    else if(txtProcess.equals(_clsWarning.txtConstProcessGetDataTipeSumberDataforWebDashboard)){
//                        JsonResult=_clsMainBL.PushData("GetDataTipeSumberDataforWebDashboard_J",_JSONArray.toString());
//                        JsonResult = new clsHelperBL().callPushDataReturnJsonParamMethodJObject("GetDataTipeSumberDataforWebDashboard_J",versionName,  _JSONArray.toString());
                        JsonResult_a = new clsHelperBL().callPushDataReturnJsonParamMethodJObject("GetDataTipeSumberDataforWebDashboard_J",versionName,  _JSONArray.toString());
                    }else if(txtProcess.equals(_clsWarning.txtConstProcessGetDataSumberDataforWebDashboardByOutlet)){
//                        JsonResult=_clsMainBL.PushData("GetDataSumberDataforWebDashboard_J",_JSONArray.toString());
//                        JsonResult = new clsHelperBL().callPushDataReturnJsonParamMethodJObject("GetDataSumberDataforWebDashboard_J",versionName,  _JSONArray.toString());
                        JsonResult_a = new clsHelperBL().callPushDataReturnJsonParamMethodJObject("GetDataSumberDataforWebDashboard_J",versionName,  _JSONArray.toString());
                    }else if(txtProcess.equals(_clsWarning.txtConstProcessSaveDataSumberDataforWebDashboard)){
//                        JsonResult=_clsMainBL.PushData("SaveDatamGeolocationOutlet",_JSONArray.toString());
//                        JsonResult = new clsHelperBL().callPushDataReturnJsonParamMethodJObject("SaveDatamGeolocationOutlet",versionName,  _JSONArray.toString());
                        JsonResult_a = new clsHelperBL().callPushDataReturnJsonParamMethodJObject("SaveDatamGeolocationOutlet",versionName,  _JSONArray.toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return JsonResult_a;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            _clsMainBL.showToast(getContext(),_clsWarning.txtConstCancelRequest);
        }
        @Override
        protected void onPostExecute(org.json.simple.JSONArray JsonRes) {
            if(_clsMainBL.ValidJSON_a(JsonRes)){
                if(txtProcess.equals(_clsWarning.txtConstProcessGetDataSumberDataforWebDashboard)){
//                    new Delete().from(clsMobile_TipeSumberData.class).execute();
                    new mTipeSumberDataBL().deleteAllData();
                    GetDataSumberDataforWebDashboard(JsonRes);
                }
                else if(txtProcess.equals(_clsWarning.txtConstProcessGetDataTipeSumberDataforWebDashboard)) {
//                    new Delete().from(clsMobile_TipeSumberData.class).execute();
                    new mTipeSumberDataBL().deleteAllData();
                    GetDataTipeSumberDataforWebDashboard(JsonRes);
                }
// else if(txtProcess.equals(_clsWarning.txtConstProcessGetDataSumberDataforWebDashboardByOutlet)) {
////                    new Delete().from(clsMobile_TipeSumberData.class).execute();
//                    new mTipeSumberDataBL().deleteAllData();
//                    GetDataSumberDataforWebDashboard(JsonRes);
//                }
 else if(txtProcess.equals(_clsWarning.txtConstProcessSaveDataSumberDataforWebDashboard)) {
                    SaveDataSumberDataforWebDashboard(JsonRes);
                }
            }else{
                _clsMainBL.showToastWarning(getContext(),new clsHardCode().txtConstErrorConnection);
            }

            try  {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {

            }

            Dialog.dismiss();
        }

        int intProcesscancel = 0;

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(_clsWarning.txtConstGettingAsyncData);
            Dialog.setCancelable(false);
            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intProcesscancel = 1;
                }
            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
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

    private void displayLocation() {
        // TODO Auto-generated method stub
        if(getContext()!=null){
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            //mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            mLastLocation=getLocation();
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
                if((txtHDLong.getText().toString().equals("")==false && txtHDLati.getText().toString().equals("")==false)){
                    if(txtHDLong.getText().toString().equals("No Location")==false && txtHDLati.getText().toString().equals("No Location")==false){
                        double latitudeOutlet = Double.parseDouble("0");
                        double longitudeOutlet = Double.parseDouble("0");
//                        clsMobile_MPartnerProfile _clsMobile_MPartnerProfile=new clsMobile_MPartnerProfile();

                        latitudeOutlet = Double.parseDouble(txtHDLati.getText().toString());
                        longitudeOutlet= Double.parseDouble(txtHDLong.getText().toString());
                        Location locationA = new Location("point A");

                        locationA.setLatitude(latitude);
                        locationA.setLongitude(longitude);
                    }
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        SupportMapFragment mapFragment = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map));

        if (mapFragment != null) {
            FragmentManager fM = getFragmentManager();
            fM.beginTransaction().remove(mapFragment).commit();

        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        SupportMapFragment mapFragment = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map));

        if (mapFragment != null) {
            FragmentManager fM = getFragmentManager();
            fM.beginTransaction().remove(mapFragment).commit();
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
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //initilizeMap();
        //checkPlayServices();
    }

    private Uri getOutputMediaFileUri() {
        //return Uri.fromFile(getOutputMediaFile());
        if (Build.VERSION.SDK_INT >= 24) {
            return FileProvider.getUriForFile(getActivity(),
                    BuildConfig.APPLICATION_ID + ".provider",
                    getOutputMediaFile());
        }else{
            return Uri.fromFile(getOutputMediaFile());
        }
    }

    private File getOutputMediaFile() {
        // External sdcard location

        File mediaStorageDir = new File(IMAGE_DIRECTORY_NAME);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + "/tmp_absen" + timeStamp + ".jpg");
        mCurrentPhotoPath = "file:" + mediaFile.getAbsolutePath();
        return mediaFile;
    }

    public class MyAdapter extends ArrayAdapter<String>
    {
        private List<String> arrayDataAdapyter;
        private List<String> arrayDataAdapyterDesc;
        private Context Ctx;

        public List<String> getArrayDataAdapyterDesc() {
            return arrayDataAdapyterDesc;
        }
        public void setArrayDataAdapyterDesc(List<String> arrayDataAdapyterDesc) {
            this.arrayDataAdapyterDesc = arrayDataAdapyterDesc;
        }

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
        public MyAdapter(Context context, int textViewResourceId, List<String> objects,List<String> objectsDesc)
        {
            super(context, textViewResourceId, objects);
            setCtx(context);
            setArrayDataAdapyter(objects);
            setArrayDataAdapyterDesc(objectsDesc);
            // TODO Auto-generated constructor stub
        }
        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent)
        {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater=getActivity().getLayoutInflater();
            View row=inflater.inflate(R.layout.custom_spinner, parent, false);
            if(getArrayDataAdapyter().size()>0){
                TextView label=(TextView)row.findViewById(R.id.tvTitle);
                label.setText(getArrayDataAdapyter().get(position));
                label.setTextColor(new Color().parseColor("#000000"));
                TextView sub=(TextView)row.findViewById(R.id.tvDesc);
                if(getArrayDataAdapyterDesc().get(position).equals("")){
                    sub.setVisibility(View.INVISIBLE);
                    sub.setVisibility(View.GONE);
                }else{
                    sub.setVisibility(View.VISIBLE);
                }
                sub.setText(getArrayDataAdapyterDesc().get(position));
                sub.setTextColor(new Color().parseColor("#000000"));
                row.setBackgroundColor(new Color().parseColor("#FFFFFF"));
            }
            //sub.setText(mydata2[position]);
            return row;
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
        // TODO Auto-generated method stub
        // Once connected with google api, get the location
        displayLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        // TODO Auto-generated method stub
        mGoogleApiClient.connect();

    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
        mLastLocation = location;
        displayLocation();

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
