package service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

import com.kalbenutritionals.app.kalbespgmobile.MainMenu;
import com.kalbenutritionals.app.kalbespgmobile.R;

import org.json.JSONException;
import org.json.simple.JSONArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import bl.clsHelperBL;
import bl.clsLogReceiverHeader_mobileBL;
import bl.clsMainBL;
import bl.tNotificationBL;
import bl.tUserLoginBL;
import come.example.viewbadger.ShortcutBadger;
import library.spgmobile.common.APIData;
import library.spgmobile.common.clsLogReceiverHeader_mobile;
import library.spgmobile.common.clsPushData;
import library.spgmobile.common.dataJson;
import library.spgmobile.common.mCounterNumberData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tActivityData;
import library.spgmobile.common.tNotificationData;
import library.spgmobile.common.tUserLoginData;
import library.spgmobile.dal.clsHardCode;
import library.spgmobile.dal.enumCounterData;
import library.spgmobile.dal.mCounterNumberDA;
import library.spgmobile.dal.tAbsenUserDA;
import library.spgmobile.dal.tActivityDA;
import library.spgmobile.dal.tUserLoginDA;

public class MyServiceNative extends Service{
	public MyServiceNative() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}


	@Override
	public void onCreate() {
		//Toast.makeText(this, "Welcome Kalbe SPG Mobile", Toast.LENGTH_LONG).show();

	}

	//private static long UPDATE_INTERVAL = 1*36*1000;  //default
	private static long UPDATE_INTERVAL = 1*360*1000;  //default
	//private static long UPDATE_INTERVAL_DELAY = 180000;  //default
	private static long UPDATE_INTERVAL_TESTING = 3000;  //default
	private static Timer timer = new Timer();
	private void _startService()
	{
		long intInverval=0;
		if(new clsMainBL().getLIVE().equals("1")){
			intInverval=UPDATE_INTERVAL;
		}else{
			intInverval=UPDATE_INTERVAL_TESTING;
		}

		if(timer != null)
		{
			timer.cancel();
		}
		timer = new Timer();
			timer.scheduleAtFixedRate(
				new TimerTask() {
					public void run() {
						try {
							doServiceWork();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, 3000,intInverval);
		//Log.i(getClass().getSimpleName(), "FileScannerService Timer started....");
	}

	private void doServiceWork() throws JSONException
	{
		String versionName="";
		try {
			versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (NameNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		clsPushData dtJson= new clsHelperBL().pushData(versionName);
		if(dtJson==null){
			_shutdownService();
		}else{
			try {
				JSONArray JsonArrayResult=new clsHelperBL().callPushDataReturnJson(versionName,dtJson.getDtdataJson().txtJSON().toString(),dtJson.getFileUpload());
				new clsHelperBL().saveDataPush(dtJson.getDtdataJson(),JsonArrayResult);

//				Intent serviceIntent = new Intent(this,MyNotificationService.class);
//				serviceIntent.putExtra("From", "PUSHDATA");
//				startService(serviceIntent);

				startNotification();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		SQLiteDatabase db;
//    	String versionName="";
//    	try {
//			versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//		} catch (NameNotFoundException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		clsHardCode clsdthc = new clsHardCode();
		db = SQLiteDatabase.openOrCreateDatabase(clsdthc.txtDatabaseName,null); // create file database
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		if(_tUserLoginDA.getContactsCount(db)> 0){
			tUserLoginData _tUserLoginData=_tUserLoginDA.getData(db, 1);

			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				mCounterNumberDA _mCounterNumberDA=new mCounterNumberDA(db);
				mCounterNumberData _data =new mCounterNumberData();
				_data.set_intId(enumCounterData.MonitorSchedule.getidCounterData());
				_data.set_txtDeskripsi("value menunjukan waktu terakhir menjalankan services");
				_data.set_txtName("Monitor Service");
				_data.set_txtValue(dateFormat.format(cal.getTime()));
				_mCounterNumberDA.SaveDataMConfig(db, _data);

				//new clsInit().PushData(db,versionName);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			tAbsenUserDA _tAbsenUserDA =new tAbsenUserDA (db);
			tActivityDA _tActivityDA =new tActivityDA (db);

			List<tAbsenUserData> ListOftAbsenUserData=_tAbsenUserDA.getAllDataToPushData(db);
			List<tActivityData> ListOftActivityData=_tActivityDA.getAllDataToPushData(db);
			dataJson dtPush=new dataJson();
			HashMap<String, String> FileUpload=null;
			FileUpload=new HashMap<String, String>();
			if(ListOftAbsenUserData!= null){
				dtPush.setListOftAbsenUserData(ListOftAbsenUserData);
				for (tAbsenUserData dttAbsenUserData : ListOftAbsenUserData) {
					if(dttAbsenUserData.get_txtImg1()!=null){
						FileUpload.put("FUAbsen1"+dttAbsenUserData.get_intId(), dttAbsenUserData.get_txtImg1().toString());
					}
					if(dttAbsenUserData.get_txtImg2()!=null){
						FileUpload.put("FUAbsen2"+dttAbsenUserData.get_intId(), dttAbsenUserData.get_txtImg2().toString());
					}
				}
			}
			if(ListOftActivityData!=null){
				dtPush.setListOftActivityData(ListOftActivityData);
				for (tActivityData dttActivityData : ListOftActivityData) {
					if(dttActivityData.get_txtImg1()!=null){
						FileUpload.put("FUActivity1"+dttActivityData.get_intId(), dttActivityData.get_txtImg1().toString());
					}
					if(dttActivityData.get_txtImg2()!=null){
						FileUpload.put("FUActivity2"+dttActivityData.get_intId(), dttActivityData.get_txtImg2().toString());
					}
				}
			}
		}
		else{
			_shutdownService();
		}

		db.close();
	}

	private void _shutdownService()
	{
		if (timer != null) timer.cancel();
		Log.i(getClass().getSimpleName(), "Timer stopped...");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// For time consuming an long tasks you can launch a new thread here...
		//Toast.makeText(this, "Welcome Kalbe SPG Mobile", Toast.LENGTH_LONG).show();
		_startService();

	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		//Toast.makeText(this, " onStartCommand", Toast.LENGTH_LONG).show();
		_startService();
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		//Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
		_shutdownService();
	}

	public void startNotification(){
		tNotificationBL _tNotificationBL=new tNotificationBL();
		clsLogReceiverHeader_mobileBL _clsLogReceiverHeader_mobileBL= new clsLogReceiverHeader_mobileBL();
		List<tNotificationData> ListData=_tNotificationBL.getAllDataWillAlert("2");
		List<tNotificationData> tmpListData= new ArrayList<tNotificationData>();
		List<clsLogReceiverHeader_mobile> tmpLisDataReceiver = new ArrayList<>();

		clsLogReceiverHeader_mobile data = new clsLogReceiverHeader_mobile();
		tUserLoginData dtLogin = new tUserLoginData();
		dtLogin = new tUserLoginBL().getUserActive();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		if(ListData!=null){
			for (tNotificationData dttNotificationData : ListData) {
				dttNotificationData.set_txtStatus("1");
				tmpListData.add(dttNotificationData);
				String index = String.valueOf(dttNotificationData.get_intIndex());
				String title = String.valueOf(dttNotificationData.get_txtTitle());
				String desc = String.valueOf(dttNotificationData.get_txtDescription());
				String className = String.valueOf(dttNotificationData.get_txtLink());
				Class<?> myClass = null;
				try {
					myClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				Intent i = new Intent(getApplicationContext(), MainMenu.class);
				i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				i.putExtra("key_view", "Notification");
				i.putExtra("id", String.valueOf(dttNotificationData.get_guiID()));
				i.setAction("notif");
				int idn = Integer.parseInt(index);

				PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),idn, i, PendingIntent.FLAG_ONE_SHOT);

				int icon = R.drawable.ic_notif_name;
				String tickerText = dttNotificationData.get_txtTitle();
				long when = System.currentTimeMillis();
				Notification tnotification = null;
				if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
					tnotification = new Notification.Builder(MyServiceNative.this)
							.setContentIntent(pendingIntent)
							.setContentTitle(title)
							.setContentText(desc)
							.setSmallIcon(icon)
							.setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),
									R.mipmap.ic_kalbe_phonegap))
							.setWhen(when)
							.setTicker(tickerText)
							.setPriority(Notification.PRIORITY_HIGH)
							.setAutoCancel(true)
							.setDefaults(Notification.DEFAULT_ALL | Notification.FLAG_SHOW_LIGHTS | Notification.PRIORITY_DEFAULT)
							.build();
				}
				NotificationManager tnotificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
				tnotification.defaults=Notification.DEFAULT_ALL;
				tnotificationManager.notify(idn,tnotification);

				data = new clsLogReceiverHeader_mobile();

				UUID uuid = UUID.randomUUID();
				String randomUUIDString = uuid.toString();

				data.setTxtIdLogReceiver(randomUUIDString);
				data.setTxtStatus("RECEIVED");
				data.setTxtNIK(dtLogin.get_TxtEmpId().toString());
				data.setTxtRoleId(dtLogin.get_txtRoleId().toString());
				data.setTxtUSerName(dtLogin.get_txtUserId().toString());
				data.setDtInserted(String.valueOf(dateFormat.format(cal.getTime())));
				data.setIntActive("1");
				data.setIntSubmit("1");
				data.setIntSync("0");
				data.setTxtGuidLogin(dtLogin.get_txtDataId().toString());
				data.setTxtIdHeaderNotif(dttNotificationData.get_guiID());

				tmpLisDataReceiver.add(data);
			}
			_tNotificationBL.saveData(tmpListData);
			_clsLogReceiverHeader_mobileBL.saveData(tmpLisDataReceiver);
			int totalStatus = new tNotificationBL().getContactsCountStatus();
			ShortcutBadger.applyCount(MyServiceNative.this, totalStatus);
		}
	}
}
