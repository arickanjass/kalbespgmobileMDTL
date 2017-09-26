package com.kalbenutritionals.app.kalbespgmobile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import bl.clsHelperBL;
import bl.clsMainBL;
import bl.mMenuBL;
import bl.tAbsenUserBL;
import bl.tAttendanceUserBL;
import bl.tDisplayPictureBL;
import bl.tNotificationBL;
import bl.tUserLoginBL;
import bl.tVisitPlanRealisasiBL;
import come.example.viewbadger.ShortcutBadger;
import de.hdodenhof.circleimageview.CircleImageView;
import library.spgmobile.common.APIData;
import library.spgmobile.common.clsPushData;
import library.spgmobile.common.mMenuData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tAttendanceUserData;
import library.spgmobile.common.tNotificationData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.common.tVisitPlanRealisasiData;
import library.spgmobile.common.visitplanAbsenData;
import library.spgmobile.dal.clsHardCode;
import service.MyServiceNative;
import service.MyTrackingLocationService;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private tAbsenUserBL _tAbsenUserBL;
    private tAbsenUserData dttAbsenUserData;
    private visitplanAbsenData dtAbsensVisitplan;
    private tVisitPlanRealisasiData dtRealisasi;
    private NavigationView navigationView;

    PackageInfo pInfo = null;

    int selectedId;
    Boolean isSubMenu = false;

    clsMainActivity _clsMainActivity = new clsMainActivity();

    String[] listMenu;
    String[] linkMenu;

    String i_view = null;
    Intent intent;

    @Override
    public void onBackPressed() {
        boolean isHome = false;
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment != null && fragment.toString().contains("FragmentInformation") && getSupportFragmentManager().getFragments().size() == 1) {
                isHome = true;
                finish();
            } else if (fragment != null && fragment.toString().contains("FragmentInformation") && getSupportFragmentManager().getFragments().size() > 1) {
                if (fragment.isVisible()) {
                    finish();
                }
            } else if (fragment != null && !fragment.toString().contains("FragmentInformation") && getSupportFragmentManager().getFragments().size() > 1) {
                isHome = false;
            }
        }
        if (!isHome) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

            toolbar.setTitle("Home");
            toolbar.setSubtitle(null);
            FragmentInformation homeFragment = new FragmentInformation();
            FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
            fragmentTransactionHome.replace(R.id.frame, homeFragment);
            fragmentTransactionHome.commit();
            navigationView.getMenu().getItem(0).setChecked(true);
        } else {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedId = 0;

        Intent serviceIntentMyServiceNative = new Intent(getApplicationContext(), MyServiceNative.class);
        if (!isMyServiceRunning(MyServiceNative.class)) {
            getApplicationContext().startService(serviceIntentMyServiceNative);
        }

        Intent serviceIntentMyTrackingLocationService = new Intent(getApplicationContext(), MyTrackingLocationService.class);
        if (!isMyServiceRunning(MyTrackingLocationService.class)) {
            getApplicationContext().startService(serviceIntentMyTrackingLocationService);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_color_theme));
        }

        try {
            pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        toolbar.setSubtitle(null);
        setSupportActionBar(toolbar);

        FragmentInformation homeFragment = new FragmentInformation();
        FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
        fragmentTransactionHome.replace(R.id.frame, homeFragment);
        fragmentTransactionHome.commit();
        selectedId = 99;

        tUserLoginData dt = new tUserLoginBL().getUserActive();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View vwHeader = navigationView.getHeaderView(0);

        CircleImageView ivProfile = (CircleImageView) vwHeader.findViewById(R.id.profile_image);
        TextView tvUsername = (TextView) vwHeader.findViewById(R.id.username);
        TextView tvEmail = (TextView) vwHeader.findViewById(R.id.email);
        tvUsername.setText(String.format("%s%s", _clsMainActivity.greetings(), dt.get_txtName()));
        tvEmail.setText(dt.get_TxtEmail());

        library.spgmobile.common.tDisplayPictureData tDisplayPictureData = new tDisplayPictureBL().getData();

        if (tDisplayPictureData.get_image() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(tDisplayPictureData.get_image(), 0, tDisplayPictureData.get_image().length);
            ivProfile.setImageBitmap(bitmap);
        } else {
            ivProfile.setImageBitmap(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.profile));
        }

        ivProfile.setOnClickListener(this);

        dtAbsensVisitplan = new clsHelperBL().getDataCheckInActive();
        Menu header = navigationView.getMenu();

        dtRealisasi = new tVisitPlanRealisasiBL().getDataCheckinActive();

        Intent intent = getIntent();
        i_view = intent.getStringExtra("key_view");

        String statusAbsen = "0";
        int menuActive;

        if (dtRealisasi.get_txtDataIDRealisasi() == null) {
            header.removeItem(R.id.checkoutVisitplan);
        }

        if (dtAbsensVisitplan != null && dtAbsensVisitplan.getType().equals("visitPlan")) {
            header.removeItem(R.id.logout);
            header.removeItem(R.id.checkout);
            header.removeItem(R.id.checkoutAbsenFPE);
            menuActive = R.id.groupListMenu1;

            statusAbsen = new mMenuBL().getIntParentID();

            if(statusAbsen!=null){
                List<mMenuData> menu = new mMenuBL().getDatabyParentId(statusAbsen);
                listMenu = new String[menu.size()];

                for (int i = 0; i < menu.size(); i++) {
                    listMenu[i] = menu.get(i).get_TxtMenuName();
                }
            }

            if (i_view != null) {
                intent_activity();
            }

        } else if (dtAbsensVisitplan != null && dtAbsensVisitplan.getType().equals("absen")) {

            header.removeItem(R.id.logout);
            header.removeItem(R.id.checkoutVisitplan);
            header.removeItem(R.id.checkoutAbsenFPE);
            menuActive = R.id.groupListMenu1;

            statusAbsen = new mMenuBL().getIntParentID();

            if(statusAbsen!=null){
                List<mMenuData> menu = new mMenuBL().getDatabyParentId(statusAbsen);
                listMenu = new String[menu.size()];

                for (int i = 0; i < menu.size(); i++) {
                    listMenu[i] = menu.get(i).get_TxtMenuName();
                }
            }

            if (i_view != null) {
                intent_activity();
            }
        } else if (dtAbsensVisitplan != null && dtAbsensVisitplan.getType().equals("absenFPE")) {

            header.removeItem(R.id.logout);
            header.removeItem(R.id.checkoutVisitplan);
            header.removeItem(R.id.checkout);
            menuActive = R.id.groupListMenu1;

            statusAbsen = new mMenuBL().getIntParentID();

            if(statusAbsen!=null){
                List<mMenuData> menu = new mMenuBL().getDatabyParentId(statusAbsen);
                listMenu = new String[menu.size()];

                for (int i = 0; i < menu.size(); i++) {
                    listMenu[i] = menu.get(i).get_TxtMenuName();
                }
            }

            if (i_view != null) {
                intent_activity();
            }
        } else {
            menuActive = R.id.groupListMenu;
            header.removeItem(R.id.checkout);
            header.removeItem(R.id.checkoutAbsenFPE);
        }

        List<mMenuData> menu;

        if (dtAbsensVisitplan == null) {
            menu = new mMenuBL().getDatabyParentId("0");
        } else {
            menu = new mMenuBL().getDatabyParentId(statusAbsen);
        }

        linkMenu = new String[menu.size()];
        listMenu = new String[menu.size()];

        for (int i = 0; i < menu.size(); i++) {

            int resId = getResources().getIdentifier(String.valueOf(menu.get(i).get_TxtDescription().toLowerCase()), "drawable", MainMenu.this.getPackageName());
            Drawable icon = MainMenu.this.getResources().getDrawable(resId);

            header.add(menuActive, i, 1, menu.get(i).get_TxtMenuName()).setIcon(icon).setCheckable(true);

            linkMenu[i] = menu.get(i).get_TxtLink();
            listMenu[i] = menu.get(i).get_TxtMenuName();
        }

//        TextView view = (TextView) navigationView.getMenu().findItem(R.id.home).getActionView();
//        view.setText("99");
        if (i_view != null) {
            switch (i_view) {
                case "View Reso":
                    navigationView.getMenu().findItem(0).setChecked(true);
                    break;
                case "View Actvity":
                    navigationView.getMenu().findItem(1).setChecked(true);
                    break;
                case "View Customer Base":
//                navigationView.getMenu().findItem(2).setChecked(true);
                    break;
                case "Notifcation":
//                    navigationView.getMenu().findItem(R.id.information).setChecked(true);
                    break;
                case "View PO":
                    navigationView.getMenu().findItem(R.id.PO).setChecked(true);
                    break;
                case "View Quantity":
                    navigationView.getMenu().findItem(R.id.quantityStock).setChecked(true);
                    break;
            }
        }


//        TextView view = (TextView) navigationView.getMenu().findItem(R.id.information).getActionView();
//        List<tNotificationData> ListData=new tNotificationBL().getAllDataWillAlert("2");
//        view.setText("1");

        String linkAPI = new clsMainBL().getLinkAPI();
        try {
            URL u = new URL(linkAPI);
            linkAPI = u.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        SubMenu subMenuVersion = header.addSubMenu(R.id.groupVersion, 0, 3, "Version");
        try {
            subMenuVersion.add(getPackageManager().getPackageInfo(getPackageName(), 0).versionName + " \u00a9 KN-IT").setIcon(R.drawable.ic_android).setEnabled(false);
            subMenuVersion.add(linkAPI).setIcon(R.drawable.ic_action_link).setEnabled(false);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                Fragment fragment;

                switch (menuItem.getItemId()) {
                    case R.id.logout:
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainMenu.this);
                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        AsyncCallLogOut task = new AsyncCallLogOut();
                                        task.execute();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        final AlertDialog alertD = alertDialogBuilder.create();
                        alertD.setTitle("Confirm");
                        alertD.setMessage("Logout Application?");
                        alertD.show();
                        return true;

                    case R.id.reporting:
                        toolbar.setTitle("Reporting");
                        toolbar.setSubtitle(null);

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

                        FragmentReporting reportingFragment = new FragmentReporting();
                        FragmentTransaction fragmentTransactionReport = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionReport.replace(R.id.frame, reportingFragment);
                        fragmentTransactionReport.commit();
                        selectedId = 100;

                        return true;

                    case R.id.home:
                        toolbar.setTitle("Home");
                        toolbar.setSubtitle(null);

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                        FragmentInformation homeFragment = new FragmentInformation();
                        FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionHome.replace(R.id.frame, homeFragment);
                        fragmentTransactionHome.commit();
                        selectedId = 99;

                        return true;

                    case R.id.quantityStock:
                        toolbar.setTitle("View Quantity Stock");
                        toolbar.setSubtitle(null);

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                        FragmentViewQuantityStock quantityStock = new FragmentViewQuantityStock();
                        FragmentTransaction fragmentTransactionQuantityStock = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionQuantityStock.replace(R.id.frame, quantityStock);
                        fragmentTransactionQuantityStock.commit();
                        selectedId = 99;

                        return true;

//                    case R.id.koordinasiOutlet:
//                        toolbar.setTitle("Koordinasi Outlet");
//
//                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
//
//                        FragmentAddKoordinasi koordinasi = new FragmentAddKoordinasi();
//                        FragmentTransaction FragmentTransactionKoordinasi = getSupportFragmentManager().beginTransaction();
//                        FragmentTransactionKoordinasi.replace(R.id.frame, koordinasi);
//                        FragmentTransactionKoordinasi.commit();
//                        selectedId = 99;
//
//                        return true;


                    case R.id.historyAbsen:
                        toolbar.setTitle("Attendance History");
                        toolbar.setSubtitle(null);

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                        FragmentViewHistoryAbsen fragmentViewHistoryAbsen = new FragmentViewHistoryAbsen();
                        FragmentTransaction fragmentTransactionHistoryAbsen = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionHistoryAbsen.replace(R.id.frame, fragmentViewHistoryAbsen);
                        fragmentTransactionHistoryAbsen.commit();
                        selectedId = 99;

                        return true;

                    case R.id.settings:
                        toolbar.setTitle("Settings");
                        toolbar.setSubtitle(null);

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

                        FragmentSettings fragmentSettings = new FragmentSettings();
                        FragmentTransaction fragmentTransactionSettings = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionSettings.replace(R.id.frame, fragmentSettings);
                        fragmentTransactionSettings.commit();
                        selectedId = 99;

                        return true;

//                    case R.id.PO:
//                        toolbar.setTitle("Purchase Order");
//                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                        FragmentPO fragmentPO = new FragmentPO();
//                        FragmentTransaction fragmentTransactionPO = getSupportFragmentManager().beginTransaction();
//                        fragmentTransactionPO.replace(R.id.frame, fragmentPO);
//                        fragmentTransactionPO.commit();
//                        selectedId = 99;
//                        return true;

//                    case R.id.soalkuesioner:
//                        toolbar.setTitle("Kuesioner / Quiz");
//                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                        FragmentKuesionerAwal fragmentKuesioner = new FragmentKuesionerAwal();
//                        FragmentTransaction fragmentTransactionKuesioner = getSupportFragmentManager().beginTransaction();
//                        fragmentTransactionKuesioner.replace(R.id.frame, fragmentKuesioner);
//                        fragmentTransactionKuesioner.commit();
//                        return true;

//                    case R.id.soalkuesioner:
//                        toolbar.setTitle("Kuesioner / Quiz");
//                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                        FragmentKuesioner fragmentKuesioner = new FragmentKuesioner();
//                        FragmentTransaction fragmentTransactionKuesioner = getSupportFragmentManager().beginTransaction();
//                        fragmentTransactionKuesioner.replace(R.id.frame, fragmentKuesioner);
//                        fragmentTransactionKuesioner.commit();
//                        return true;


                  /*  case R.id.viewVisitPlan:
                        toolbar.setTitle("Visit Plan");
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        FragmentViewVisitplan fragmentVP = new FragmentViewVisitplan();
                        FragmentTransaction fragmentTransactionVP = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionVP.replace(R.id.frame, fragmentVP);
                        fragmentTransactionVP.commit();
                        selectedId = 99;
                        return true;*/
                    case R.id.information:
                        toolbar.setTitle("Information");
                        toolbar.setSubtitle(null);
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                        FragmentNotification fragmentNotification = new FragmentNotification();
                        FragmentTransaction fragmentTransactionNotifcation = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionNotifcation.replace(R.id.frame, fragmentNotification);
                        fragmentTransactionNotifcation.commit();
                        selectedId = 99;

                        return true;
                    case R.id.checkoutVisitplan:
                        AlertDialog.Builder _alertDialogBuilder2 = new AlertDialog.Builder(MainMenu.this);
                        _alertDialogBuilder2
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        tVisitPlanRealisasiData data = new tVisitPlanRealisasiData();
                                        data = dtRealisasi;
                                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Calendar cal = Calendar.getInstance();
                                        data.set_dateCheckout(dateFormat.format(cal.getTime()));
                                        data.set_intPush("0");
                                        new tVisitPlanRealisasiBL().UpdateData(data);
                                        finish();
                                        Intent nextScreen = new Intent(getApplicationContext(), MainMenu.class);
                                        nextScreen.putExtra("keyMainMenu", "main_menu");
                                        finish();
                                        startActivity(nextScreen);
                                        /* _tAbsenUserBL = new tAbsenUserBL();
                                        dttAbsenUserData = new tAbsenUserData();

                                        dttAbsenUserData = _tAbsenUserBL.getDataCheckInActive();
                                        tAbsenUserData datatAbsenUserData = dttAbsenUserData;
                                        List<tAbsenUserData> absenUserDatas = new ArrayList<tAbsenUserData>();

                                        if (dttAbsenUserData != null) {
                                            datatAbsenUserData.set_intId(dttAbsenUserData.get_intId());
                                            datatAbsenUserData.set_dtDateCheckOut(_clsMainActivity.FormatDateDB().toString());
                                            datatAbsenUserData.set_intSubmit("1");
                                            datatAbsenUserData.set_intSync("0");
                                            datatAbsenUserData.set_txtAbsen("0");
                                            absenUserDatas.add(datatAbsenUserData);
                                            new tAbsenUserBL().saveData(absenUserDatas);

                                            finish();
                                            Intent nextScreen = new Intent(getApplicationContext(), MainMenu.class);
                                            nextScreen.putExtra("keyMainMenu", "main_menu");
                                            finish();
                                            startActivity(nextScreen);
                                        }*/
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        final AlertDialog _alertD2 = _alertDialogBuilder2.create();
                        _alertD2.setTitle("Confirm");
                        _alertD2.setMessage("Checkout Data?");
                        _alertD2.show();

                        return true;

                    case R.id.checkout:
                        AlertDialog.Builder _alertDialogBuilder = new AlertDialog.Builder(MainMenu.this);
                        _alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        _tAbsenUserBL = new tAbsenUserBL();
                                        dttAbsenUserData = new tAbsenUserData();

                                        dttAbsenUserData = _tAbsenUserBL.getDataCheckInActive();
                                        tAbsenUserData datatAbsenUserData = dttAbsenUserData;
                                        List<tAbsenUserData> absenUserDatas = new ArrayList<>();

                                        if (dttAbsenUserData != null) {
                                            datatAbsenUserData.set_intId(dttAbsenUserData.get_intId());
                                            datatAbsenUserData.set_dtDateCheckOut(_clsMainActivity.FormatDateDB());
                                            datatAbsenUserData.set_intSubmit("1");
                                            datatAbsenUserData.set_intSync("0");
                                            datatAbsenUserData.set_txtAbsen("0");
                                            absenUserDatas.add(datatAbsenUserData);
                                            new tAbsenUserBL().saveData(absenUserDatas);

                                            finish();
                                            Intent nextScreen = new Intent(getApplicationContext(), MainMenu.class);
                                            nextScreen.putExtra("keyMainMenu", "main_menu");
                                            finish();
                                            startActivity(nextScreen);
                                        }
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        final AlertDialog _alertD = _alertDialogBuilder.create();
                        _alertD.setTitle("Confirm");
                        _alertD.setMessage("Checkout Data?");
                        _alertD.show();

                        return true;
                    case R.id.checkoutAbsenFPE:
                        AlertDialog.Builder _alertDialogBuilder3 = new AlertDialog.Builder(MainMenu.this);
                        _alertDialogBuilder3
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        tAttendanceUserBL _tAttendanceUserBL = new tAttendanceUserBL();
                                        tAttendanceUserData _tAttendanceUserData = new tAttendanceUserData();

                                        _tAttendanceUserData = _tAttendanceUserBL.getDataCheckInActive();

                                        if (_tAttendanceUserData != null) {
                                            _tAttendanceUserData.set_dtDateCheckOut(_clsMainActivity.FormatDateDB());
                                            _tAttendanceUserData.set_intSubmit("1");
                                            _tAttendanceUserData.set_intSync("0");
                                            _tAttendanceUserData.set_txtAbsen("0");
                                            new tAttendanceUserBL().saveData(_tAttendanceUserData);

                                            finish();
                                            Intent nextScreen = new Intent(getApplicationContext(), MainMenu.class);
                                            nextScreen.putExtra("keyMainMenu", "main_menu");
                                            finish();
                                            startActivity(nextScreen);
                                        }
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        final AlertDialog _alertD3 = _alertDialogBuilder3.create();
                        _alertD3.setTitle("Confirm");
                        _alertD3.setMessage("Checkout Data?");
                        _alertD3.show();

                        return true;
                    default:
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                        try {
                            Class<?> fragmentClass = Class.forName(linkMenu[menuItem.getItemId()]);
                            try {
                                if (dtAbsensVisitplan != null) {
                                    toolbar.setTitle("View " + menuItem.getTitle().toString());
                                    toolbar.setSubtitle(null);
                                } else {
                                    toolbar.setTitle(menuItem.getTitle().toString());
                                    toolbar.setSubtitle(null);
                                }

                                fragment = (Fragment) fragmentClass.newInstance();
                                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.frame, fragment);
                                fragmentTransaction.addToBackStack(fragment.getClass().getName());
                                fragmentTransaction.commit();
                                selectedId = menuItem.getItemId();
                                isSubMenu = false;

                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        return true;

                }
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        toolbar.setTitle(item.getTitle());

        Class<?> fragmentClass;
        try {
            Fragment fragment;

            fragmentClass = Class.forName("com.kalbenutritionals.app.kalbespgmobile.Fragment" + String.valueOf(item.getTitle()).replaceAll("\\s+", ""));
            fragment = (Fragment) fragmentClass.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();

            selectedId = id;

            if (!isSubMenu) isSubMenu = true;
            else isSubMenu = false;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        menu.clear();
//        if (listMenu.length <= selectedId) {
//            if (toolbar.getTitle().equals("Reporting")&&!isSubMenu||isSubMenu && dtAbsensVisitplan != null&&listMenu.length>0&&toolbar.getTitle()=="Reporting"){
//                for(String s : listMenu){
//                    if(s.contains("Reso SPG")) {
//                        int a = listMenu.length;
//                        for (int i=0; i<a; i++ ){
//                            if (i==1){
//                                menu.removeItem(i);
//                            } else {
//                                menu.add(0, i, 0, "Add " + listMenu[i]);
//                            }
//                        }
//                    } else {
//                        menu.add(4, 0, 0, "-");
//                        menu.setGroupEnabled(4, false);
//                        break;
//                    }
//                    break;
//                }
//            } else {
//                menu.add(4, 0, 0, "-");
//                menu.setGroupEnabled(4, false);
//            }
//        } else if (!isSubMenu && dtAbsensVisitplan != null) {
//            menu.add(0, selectedId, 0, "Add " + listMenu[selectedId]);
//        } else if (isSubMenu && dtAbsensVisitplan != null) {
//            menu.add(1, selectedId, 0, "View " + listMenu[selectedId]);
//        }
//        else {
//            menu.add(4, 0, 0, "-");
//            menu.setGroupEnabled(4, false);
//        }
//
//        return super.onPrepareOptionsMenu(menu);
//
//    }


    int intProcesscancel = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_image:
                pickImage2();
                break;
        }
    }

    public void pickImage2() {
        CropImage.startPickImageActivity(this);
    }

    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);

            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                Intent intent = new Intent(this, CropDisplayPicture.class);
                String strName = imageUri.toString();
                intent.putExtra("uriPicture", strName);
                startActivity(intent);
                finish();
            }
        } else if (requestCode == 100 || requestCode == 130) {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }

    }

    private class AsyncCallLogOut extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;

            try {
                boolean validPush = false;
                List<tAbsenUserData> listAbsenData = new ArrayList<>();
                tAbsenUserData dtTabsenData = new tAbsenUserBL().getDataCheckInActive();
                if (dtTabsenData != null) {
                    dtTabsenData.set_dtDateCheckOut(_clsMainActivity.FormatDateComplete());
                    dtTabsenData.set_intSubmit("1");
                    dtTabsenData.set_intSync("0");
                    listAbsenData.add(dtTabsenData);
                    new tAbsenUserBL().saveData(listAbsenData);
                }
                String versionName = "";
                try {
                    versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                } catch (PackageManager.NameNotFoundException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                clsPushData dtJson = new clsHelperBL().pushData(versionName);
                JSONArray Jresult = null;
                if (dtJson != null) {
                    try {
                        if (dtJson.getDtdataJson().getListOftAbsenUserData() != null) {
                            List<tAbsenUserData> listAbsen = dtJson.getDtdataJson().getListOftAbsenUserData();
                            if (listAbsen.get(0).get_txtAbsen().equals("0")) {
                                Jresult = new clsHelperBL().callPushDataReturnJson(versionName, dtJson.getDtdataJson().txtJSON().toString(), dtJson.getFileUpload());
                            } else {
                                Jresult = new clsHelperBL().callPushDataReturnJson(versionName, dtJson.getDtdataJson().txtJSON().toString(), null);
                            }
//                            new clsHelperBL().saveDataPush(dtJson.getDtdataJson(), Jresult);
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if(Jresult!=null){
                        APIData dtAPIDATA = new APIData();
                        Iterator i = Jresult.iterator();
                        while (i.hasNext()) {
                            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
                            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
                            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                                validPush = true;
                            } else {
                                validPush = false;
                                break;
                            }
                        }
                    }
                }

                try {
                    pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }

                Json = new tUserLoginBL().Logout(pInfo.versionName);
                if(validPush){
                    Json = new tUserLoginBL().Logout(pInfo.versionName);
                } else if (Jresult==null){
                    Json = new tUserLoginBL().Logout(pInfo.versionName);
                } else {
                    Json = Jresult;
                }
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(MainMenu.this);

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            Toast toast = Toast.makeText(MainMenu.this, "cancel", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 25, 400);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null) {
                for (Object aRoledata : roledata) {
                    JSONObject innerObj = (JSONObject) aRoledata;
                    Long IntResult = (Long) innerObj.get("_pboolValid");
                    String PstrMessage = (String) innerObj.get("_pstrMessage");

                    if (IntResult == 1) {
                        tNotificationBL _tNotificationBL = new tNotificationBL();
                        List<tNotificationData> ListData = _tNotificationBL.getAllDataWillAlert("1");
                        if (ListData != null) {
                            for (tNotificationData dttNotificationData : ListData) {
                                NotificationManager tnotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                tnotificationManager.cancelAll();
                                ShortcutBadger.applyCount(MainMenu.this, 0);
                                System.gc();
                            }
                        }
                        ImagePick.deleteMediaStorageDirQuiz();;
                        stopService(new Intent(getApplicationContext(), MyServiceNative.class));
                        stopService(new Intent(getApplicationContext(), MyTrackingLocationService.class));
                        MyTrackingLocationService service = new MyTrackingLocationService();
                        service.shutdownService();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.cancelAll();
                        new clsHelperBL().DeleteAllDB();
                        finish();
                        Intent nextScreen = new Intent(MainMenu.this, Splash.class);
                        startActivity(nextScreen);
                    } else {
                        _clsMainActivity.showCustomToast(getApplicationContext(), PstrMessage, false);
//                        Toast toast = Toast.makeText(MainMenu.this,
//                                PstrMessage, Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.TOP, 25, 400);
//                        toast.show();
                    }
                }

            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    _clsMainActivity.showCustomToast(getApplicationContext(), "Offline", false);
                }

            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage(new clsHardCode().txtMessLogOut);
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

    private void intent_activity() {
        if (i_view.equals("Notification")) {
            String id = intent.getStringExtra("id");
            Class<?> fragmentClass = null;
            try {
                fragmentClass = Class.forName("com.kalbenutritionals.app.kalbespgmobile.Fragment" + i_view);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            toolbar.setTitle("Information");
            toolbar.setSubtitle(null);
            Fragment fragment = null;
            try {
                fragment = (Fragment) (fragmentClass != null ? fragmentClass.newInstance() : null);
                Bundle bundle = new Bundle();
                bundle.putString("TAG_UUID", id);
                if (fragment != null) {
                    fragment.setArguments(bundle);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
            selectedId = 99;
        } else {
            try {
                Class<?> fragmentClass = Class.forName("com.kalbenutritionals.app.kalbespgmobile.Fragment" + i_view.replaceAll("\\s+", ""));
                try {
                    for (int i = 0; i < listMenu.length; i++) {
                        if (("View " + listMenu[i]).equals(i_view)) {
                            selectedId = i;
                            break;
                        }
                    }
                    toolbar.setTitle(i_view);
                    toolbar.setSubtitle(null);
                    Fragment fragment = (Fragment) fragmentClass.newInstance();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            String view = intent.getStringExtra("key_view");
            if (view != null) {
                if (view.equals("Notification")) {
                    String id = intent.getStringExtra("id");
                    Class<?> fragmentClass = null;
                    try {
                        fragmentClass = Class.forName("com.kalbenutritionals.app.kalbespgmobile.Fragment" + view);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    toolbar.setTitle("Information");
                    toolbar.setSubtitle(null);
                    Fragment fragment = null;
                    try {
                        if (fragmentClass != null) {
                            fragment = (Fragment) fragmentClass.newInstance();
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString("TAG_UUID", id);
                        fragment.setArguments(bundle);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                    selectedId = 99;

                    NotificationManager tnotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    tnotificationManager.cancelAll();
                }
            }
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
