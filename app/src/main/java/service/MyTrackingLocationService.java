package service;

import android.Manifest;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.kalbenutritionals.app.kalbespgmobile.clsMainActivity;

import org.json.JSONException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

import bl.tUserLoginBL;
import bl.trackingLocationBL;
import library.spgmobile.common.mCounterNumberData;
import library.spgmobile.common.mDownloadMasterData_mobileData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.trackingLocationData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumCounterData;
import library.spgmobile.dal.mCounterNumberDA;
import library.spgmobile.dal.mDownloadMasterData_mobileDA;
import library.spgmobile.dal.tUserLoginDA;

/**
 * Created by Rian Andrivani on 5/18/2017.
 */

public class MyTrackingLocationService extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    List<tUserLoginData> dtLogin;
    List<mDownloadMasterData_mobileData> dtDownload;

    public MyTrackingLocationService() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver, filter);
        buildGoogleApiClient();
    }

    private GoogleApiClient mGoogleApiClient;
    private void buildGoogleApiClient() {
        // TODO Auto-generated method stub
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();


    }
    private Location mLastLocation;
    Handler mHandler = new Handler();
    private final static int INTERVAL_TESTING = 1000 * 60; //2 minutes
    private final static int INTERVAL_LIVE = 1000 * 60 * 60; //2 minutes
    private static long UPDATE_INTERVAL = 1*360*1000;;  //default
    private static long UPDATE_INTERVAL_TESTING = /*1000 * 60 * 2*/3000; //2 minutes
    private static Timer timer = new Timer();


    private void startService() throws JSONException {
        try {
            doService();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void doService() throws JSONException {
        SQLiteDatabase db;
        String versionName="";
        try {
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        clsHardCode clsdthc = new clsHardCode();
        db = SQLiteDatabase.openOrCreateDatabase(clsdthc.txtDatabaseName,null); // create file database
        tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
        mDownloadMasterData_mobileDA _mDownloadMasterData_mobileDA = new mDownloadMasterData_mobileDA(db);

        if (_mDownloadMasterData_mobileDA.getContactsCount(db)> 0) {
//            tUserLoginData _tUserLoginData=_tUserLoginDA.getData(db, 1);
            mDownloadMasterData_mobileData mDownloadMasterData_mobileData = _mDownloadMasterData_mobileDA.getData(db,1);

            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                mCounterNumberDA _mCounterNumberDA=new mCounterNumberDA(db);
                mCounterNumberData _data =new mCounterNumberData();
                _data.set_intId(enumCounterData.MonitoringLocation.getidCounterData());
                _data.set_txtDeskripsi("value menunjukan waktu terakhir menjalankan services");
                _data.set_txtName("Monitor Service Location");
                _data.set_txtValue(dateFormat.format(cal.getTime()));
                _mCounterNumberDA.SaveDataMConfig(db, _data);

                startRepeatingTask();
//                trackingLocation("doService");
                //new clsInit().PushData(db,versionName);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } else {
            shutdownService();
        }
        db.close();
    }



    public void trackingLocation(String flag) {
        try {
            LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

            // getting GPS status
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
                mLastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                new clsMainActivity().showCustomToast(getApplicationContext(), "Please turn on GPS or check your internet connection", false );
            } else {
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        new clsMainActivity().showCustomToast(getApplicationContext(), "Please check application permissions", false);
                    } else {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, UPDATE_INTERVAL, 0, this);
                        mLastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                } else {
                    new clsMainActivity().showCustomToast(getApplicationContext(), "Please check your connection", false);
                }

                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled&&mLastLocation==null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, UPDATE_INTERVAL, 0, this);
                        mLastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                } else if(!isGPSEnabled){
                    new clsMainActivity().showCustomToast(getApplicationContext(), "Please turn on GPS", false);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(mLastLocation!=null){
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
//            try {
//                addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
//                String namaJalan = addresses.get(0).getAddressLine(0);
//                String kelurahan = addresses.get(0).getAddressLine(1);
//                String kecamatan = addresses.get(0).getAddressLine(2);
//                String kota = addresses.get(0).getAddressLine(3);
//                String provinsi = addresses.get(0).getAddressLine(4);
//                String negara = addresses.get(0).getAddressLine(5);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            saveLocation(mLastLocation, flag, addresses);
        }
    }

    private void saveLocation(Location mLastLocation, String flag, List<Address> addresses) {
        trackingLocationData dataLocation = new trackingLocationData();
        tUserLoginData dataUserActive = new tUserLoginBL().getUserActive();
        final int index = new trackingLocationBL().getContactCount() + 1;

        if (new trackingLocationBL().getContactCount() == 0) {
            if (dataUserActive != null) {
                dataLocation.set_intId(new clsMainActivity().GenerateGuid());
                dataLocation.set_txtLongitude(String.valueOf(mLastLocation.getLongitude()));
                dataLocation.set_txtLatitude(String.valueOf(mLastLocation.getLatitude()));
                dataLocation.set_txtAccuracy(String.valueOf(mLastLocation.getAccuracy()));
                dataLocation.set_txtUserId(dataUserActive.get_txtUserId());
                dataLocation.set_txtUsername(dataUserActive.get_txtUserName());
                dataLocation.set_txtRoleId(dataUserActive.get_txtRoleId());
                dataLocation.set_txtDeviceId(dataUserActive.get_txtDeviceId());
                dataLocation.set_txtBranchCode(flag);
                dataLocation.set_txtOutletCode(String.valueOf(addresses));
                dataLocation.set_txtNIK(dataUserActive.get_TxtEmpId());
                dataLocation.set_intSequence(String.valueOf(index));
                dataLocation.set_intSubmit("1");
                dataLocation.set_intSync("0");

                List<trackingLocationData> dtList = new ArrayList<>();
                dtList.add(dataLocation);
                new trackingLocationBL().SaveDataLocation(dtList);
            } else {
                shutdownService();
            }
        }
        else {
            int data = 0;
            List<trackingLocationData> listtrackingLocationData = new trackingLocationBL().getAllLastData();

            String intSequence = "";
            intSequence = listtrackingLocationData.get(0).get_intSequence();

            if(intSequence.trim().equals("null")||intSequence.trim().equals("")){
                data = 0;
            } else {
                data = Integer.valueOf(intSequence);
            }

            if (dataUserActive != null) {
                dataLocation.set_intId(new clsMainActivity().GenerateGuid());
                dataLocation.set_txtLongitude(String.valueOf(mLastLocation.getLongitude()));
                dataLocation.set_txtLatitude(String.valueOf(mLastLocation.getLatitude()));
                dataLocation.set_txtAccuracy(String.valueOf(mLastLocation.getAccuracy()));
                dataLocation.set_txtUserId(dataUserActive.get_txtUserId());
                dataLocation.set_txtUsername(dataUserActive.get_txtUserName());
                dataLocation.set_txtRoleId(dataUserActive.get_txtRoleId());
                dataLocation.set_txtDeviceId(dataUserActive.get_txtDeviceId());
                dataLocation.set_txtBranchCode(flag);
                dataLocation.set_txtOutletCode(String.valueOf(addresses));
                dataLocation.set_txtNIK(dataUserActive.get_TxtEmpId());
                dataLocation.set_intSequence(String.valueOf(data + 1));
                dataLocation.set_intSubmit("1");
                dataLocation.set_intSync("0");

                List<trackingLocationData> dtList = new ArrayList<>();
                dtList.add(dataLocation);
                new trackingLocationBL().SaveDataLocation(dtList);
            } else {
                shutdownService();
            }
        }
    }

    private static int timers;
    Runnable mHandlerTask = new Runnable()
    {
        @Override
        public void run() {
            trackingLocation("mHandlerTask");
            timers = INTERVAL_TESTING;
            mHandler.postDelayed(mHandlerTask, timers);
        }
    };

    void startRepeatingTask()
    {
            mHandlerTask.run();
    }

    public void shutdownService() {
        mHandler.removeCallbacks(mHandlerTask);
        Log.i(getClass().getSimpleName(), "Timer stopped...");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // For time consuming an long tasks you can launch a new thread here...
        //Toast.makeText(this, "Welcome Kalbe SPG Mobile", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    startService();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, 35000);

    }
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            intent.getFlags();

            String broadcastName = intent.getAction().substring(0);

            if(broadcastName.equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")){
                ConnectivityManager cm = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null
                        && activeNetwork.isConnectedOrConnecting();

                if (isConnected) {
//                    new clsMainActivity().showCustomToast(getApplicationContext(), "on Connection Suspended" + String.valueOf(isConnected), false);
                }

            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        //Toast.makeText(this, " onStartCommand", Toast.LENGTH_LONG).show();

        try {
            startService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    startService();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 35000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        //Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        unregisterReceiver(receiver);
        shutdownService();
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
//        try {
//            addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
//                String namaJalan = addresses.get(0).getAddressLine(0);
//                String kelurahan = addresses.get(0).getAddressLine(1);
//                String kecamatan = addresses.get(0).getAddressLine(2);
//                String kota = addresses.get(0).getAddressLine(3);
//                String provinsi = addresses.get(0).getAddressLine(4);
//                String negara = addresses.get(0).getAddressLine(5);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        saveLocation(mLastLocation, "onLocationChanged", addresses);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
//        new clsMainActivity().showCustomToast(getApplicationContext(), s, false);
    }

    @Override
    public void onProviderEnabled(String s) {
//        new clsMainActivity().showCustomToast(getApplicationContext(), s, false);
        trackingLocation("onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String s) {
//        new clsMainActivity().showCustomToast(getApplicationContext(), s, false);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
//        new clsMainActivity().showCustomToast(getApplicationContext(), "on Connected", false);
    }

    @Override
    public void onConnectionSuspended(int i) {
//        new clsMainActivity().showCustomToast(getApplicationContext(), "on Connection Suspended", false);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        new clsMainActivity().showCustomToast(getApplicationContext(), "on Connection Failed", false);
    }
}
