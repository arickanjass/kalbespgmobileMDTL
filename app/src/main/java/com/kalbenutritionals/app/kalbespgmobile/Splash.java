package com.kalbenutritionals.app.kalbespgmobile;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

import bl.clsHelperBL;
import bl.clsMainBL;
import library.spgmobile.common.clsHelper;
import library.spgmobile.common.clsStatusMenuStart;
import library.spgmobile.dal.enumStatusMenuStart;
import service.MyServiceNative;

public class Splash extends AppCompatActivity {
    long delay = 5000;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private TextView version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.anim_layout);
        version = (TextView) findViewById(R.id.tv_version);

        try {
            version.setText(getPackageManager().getPackageInfo(getPackageName(), 0).versionName + " \u00a9 KN-IT");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    // menjalankan Animasi Logo kalbe
    private void StartAnimations() {

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        TextView iv = (TextView) findViewById(R.id.iv_anim);
        iv.clearAnimation();
        iv.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        anim.reset();
        ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
        iv2.setBackgroundResource(R.mipmap.ic_kalbe_phonegap);
        iv2.clearAnimation();
        iv2.startAnimation(anim);
    }

    //Memunculkan pop up accept permission untuk API > 23
    private boolean checkPermission() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Splash.this);
        builder.setMessage("You need to allow access. . .");
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(Splash.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        && !ActivityCompat.shouldShowRequestPermissionRationale(Splash.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        &&!ActivityCompat.shouldShowRequestPermissionRationale(Splash.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        &&!ActivityCompat.shouldShowRequestPermissionRationale(Splash.this,
                        Manifest.permission.CAMERA)
                        &&!ActivityCompat.shouldShowRequestPermissionRationale(Splash.this,
                        Manifest.permission.READ_PHONE_STATE)){
                    ActivityCompat.requestPermissions(Splash.this,
                            new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE},
                            REQUEST_CODE_ASK_PERMISSIONS);
                    dialog.dismiss();

                }
                ActivityCompat.requestPermissions(Splash.this,
                        new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE},
                        REQUEST_CODE_ASK_PERMISSIONS);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

            return true;
    }

    // mapping menu
    private void checkStatusMenu() {
        Timer runProgress = new Timer();
        TimerTask viewTask = new TimerTask() {

            public void run() {
                Intent myIntent = new Intent(Splash.this, Login.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                clsHelper _clsHelper = new clsHelper();
                _clsHelper.createFolderApp();
                new clsHelperBL().InsertDefaultMconfig();
                try {
                    clsStatusMenuStart _clsStatusMenuStart = new clsMainBL().checkUserActive();
                    if (_clsStatusMenuStart.get_intStatus() == enumStatusMenuStart.FormLogin) {
                        new clsHelperBL().DeleteAllDB();
                        myIntent = new Intent(Splash.this, Login.class);
                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    } else if (_clsStatusMenuStart.get_intStatus() == enumStatusMenuStart.PushDataSPGMobile) {
                        myIntent = new Intent(Splash.this, PushData.class);
                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    } else if (_clsStatusMenuStart.get_intStatus() == enumStatusMenuStart.UserActiveLogin) {
                        myIntent = new Intent(Splash.this, MainMenu.class);
                        startService(new Intent(getApplicationContext(), MyServiceNative.class));
                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
                startActivity(myIntent);
            }
        };
        runProgress.schedule(viewTask, delay);
    }

    //saat on resume mapping version API
    @Override
    protected void onResume() {
        super.onResume();

        int hasWriteExternalStoragePermission = ContextCompat.checkSelfPermission(Splash.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int hasReadExternalStoragePermission = ContextCompat.checkSelfPermission(Splash.this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(Splash.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCameraPermission = ContextCompat.checkSelfPermission(Splash.this,
                Manifest.permission.CAMERA);
        int hasPhonePermission = ContextCompat.checkSelfPermission(Splash.this,
                Manifest.permission.READ_PHONE_STATE);

        if (Build.VERSION.SDK_INT >= 23){
            if (hasWriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
                checkPermission();
            } else if (hasReadExternalStoragePermission != PackageManager.PERMISSION_GRANTED){
                checkPermission();
            } else if (hasAccessFineLocationPermission != PackageManager.PERMISSION_GRANTED){
                checkPermission();
            } else if (hasCameraPermission != PackageManager.PERMISSION_GRANTED){
                checkPermission();
            } else if (hasPhonePermission != PackageManager.PERMISSION_GRANTED){
                checkPermission();
            }  else {
                StartAnimations();
                checkStatusMenu();
            }
        } else {
            StartAnimations();
            checkStatusMenu();
        }
    }
}