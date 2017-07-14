package com.kalbenutritionals.app.kalbespgmobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import addons.zoomview.CustomZoomView;
import bl.clsFileAttach_mobileBL;
import bl.clsLogReceiverDetail_mobileBL;
import bl.clsLogReceiverHeader_mobileBL;
import bl.tNotificationBL;
import bl.tUserLoginBL;
import come.example.viewbadger.ShortcutBadger;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;
import library.salesforce.common.clsFileAttach_mobile;
import library.salesforce.common.clsLogReceiverDetail_mobile;
import library.salesforce.common.clsLogReceiverHeader_mobile;
import library.salesforce.common.clsRowItem;
import library.salesforce.common.clsSwipeList;
import library.salesforce.common.tInformationData;
import library.salesforce.common.tNotificationData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.dal.clsHardCode;
import service.MyNotificationService;

import static com.kalbenutritionals.app.kalbespgmobile.clsMainActivity.setCreator;
import static com.kalbenutritionals.app.kalbespgmobile.clsMainActivity.setListA;

/**
 * Created by ASUS ZE on 06/09/2016.
 */
public class FragmentNotification extends Fragment implements IXListViewListener{

    View v;

    ListView list, listPopup;
    private static PullToRefreshSwipeMenuListView mListView, mListViewPopup;
    private static List<clsSwipeList> swipeList = new ArrayList<clsSwipeList>();
    List<clsRowItem> rowItems;
    private static AppAdapterNotif mmAdapter;
    private static Handler mHandler;
    private static Map<String, HashMap> mapMenu;

    private ImageView imgBdge;
    private Button play;
    private Button show, push;
    public static final Integer images1 = R.drawable.ic_bbm_notif;
    public static final Integer images2 = R.drawable.ic_bbm_transp;

    private List<String> arrData;
    static List<tNotificationData> dt;
    static List<clsFileAttach_mobile> dtFile;

    String idHeaderNotif=null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_notification,container,false);
//        play = (Button) v.findViewById(R.id.btn_play);
//        show = (Button) v.findViewById(R.id.btn_show);

        Intent i = getActivity().getIntent();
        list = (ListView) v.findViewById(R.id.listViewNotifikasi);
        mListView = (PullToRefreshSwipeMenuListView) v.findViewById(R.id.listView);
        imgBdge = (ImageView) v.findViewById(R.id.iv_icon);
        push = (Button) v.findViewById(R.id.btn_push);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB_MR1) {
                idHeaderNotif = bundle.getString("TAG_UUID", "id");
            }
        }

        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<tNotificationData> dt = new ArrayList<tNotificationData>();

//                Intent intent = new Intent(getActivity(), PdfView.class);
//                startActivity(intent);
                for(int i=0;i<3;i++){
                    tNotificationData data = new tNotificationData();
                    data.set_bitActive("");
                    data.set_dtPublishEnd("");
                    data.set_dtUpdate("");
                    data.set_guiID(String.valueOf(i));
                    data.set_intIndex(String.valueOf(i));
                    data.set_intPriority("1");
                    data.set_intSubmit("1");
                    data.set_intSync("0");
                    data.set_tPublishStart("");
                    data.set_txtStatus("2");
                    data.set_txtTitle("SPG Mobile" + String.valueOf(i) );
                    data.set_txtDescription("Anda mendapat pesan baru" + String.valueOf(i));

                    dt.add(data);
                }



                new tNotificationBL().saveData(dt);
            }
        });

        loadData();

        return v;
    }

    private void viewList(Context applicationContext, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        final View promptView = layoutInflater.inflate(R.layout.fragment_notification_popup, null);

        final TextView txtTitle = (TextView) promptView.findViewById(R.id.tv_detail_title);
        final TextView txtDesc = (TextView) promptView.findViewById(R.id.tv_detail_desc);
        final ImageView txtImg = (ImageView) promptView.findViewById(R.id.imageViewDetailN);
        listPopup = (ListView) promptView.findViewById(R.id.listViewFIle);
        mListViewPopup = (PullToRefreshSwipeMenuListView) promptView.findViewById(R.id.listViewPopup);

        final List<clsFileAttach_mobile> lisclsFileAttach_mobile = new clsFileAttach_mobileBL().getDataByIdHeader(dt.get(position).get_guiID());

        swipeList.clear();

        arrData=new ArrayList<String>();
        rowItems = new ArrayList<clsRowItem>();
        if(lisclsFileAttach_mobile!=null){

            rowItems = new ArrayList<clsRowItem>();
            for(clsFileAttach_mobile dt :lisclsFileAttach_mobile ){
                String status = String.valueOf(dt.get_intStatus());
                if(status.equals("READ")){
                    clsRowItem item = new clsRowItem();
                    item.set_imageId(String.valueOf(images2));
                    item.set_title(dt.get_txtDesc());
                    rowItems.add(item);
                } else if (status.equals("RECEIVED")){
                    clsRowItem item = new clsRowItem();
                    item.set_imageId(String.valueOf(images1));
                    item.set_title(dt.get_txtDesc());
                    rowItems.add(item);
                }

            }

        }

        clsMainActivity clsMain = new clsMainActivity();

        mmAdapter = setListA(getActivity(), rowItems);
        mListViewPopup.setAdapter(mmAdapter);
        mListViewPopup.setPullRefreshEnable(false);
        mListViewPopup.setPullLoadEnable(true);
        mListViewPopup.setXListViewListener(this);
        mHandler = new Handler();
        HashMap<String, String> mapView = new HashMap<String, String>();

        mapView.put("name", "View");
        mapView.put("bgColor", "#3498db");

        mapMenu = new HashMap<String, HashMap>();
        mapMenu.put("0", mapView);

        SwipeMenuCreator creator = setCreator(getActivity(), mapMenu);
        mListViewPopup.setMenuCreator(creator);

        creator = clsMain.setCreator(getActivity().getApplicationContext(), mapMenu);
        mListViewPopup.setMenuCreator(creator);
        mListViewPopup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:

                        clsFileAttach_mobile data = new clsFileAttach_mobile();
                        data = lisclsFileAttach_mobile.get(position);
                        String txtPath = new clsHardCode().txtPathUserData + "FileAttach/";

                        File file = new File(txtPath + "/" + data.get_txtNameFileEncrypt());
                        if(file.exists()){
//                            Toast.makeText(getActivity(), "file exist", Toast.LENGTH_SHORT).show();
                            callShowFile(data);
                        } else {
                            callDownloadData(data);
                        }
                }
            }
        });

            String messageId = dt.get(position).get_guiID();
            List<tNotificationData> tNotifId = new tNotificationBL().getData(messageId);
            if(dt!=null){

                tNotificationData dataStatus = new tNotificationData();

                String img = String.valueOf(dt.get(position).get_txtImage());
                txtTitle.setText(dt.get(position).get_txtTitle());
                txtTitle.setTextColor(Color.BLACK);
                txtDesc.setText(dt.get(position).get_txtDescription());
                txtDesc.setTextColor(Color.BLACK);

                if (img !=""){
                    txtImg.setVisibility(View.GONE);
                    txtImg.getLayoutParams().height = 0;
                } else {
                    txtImg.setImageURI(Uri.parse(img));
                }

                tNotifId.add(dataStatus);
                new tNotificationBL().SaveDataUpdate(tNotifId);
            }

        int totalStatus = new tNotificationBL().getContactsCountStatus();
        ShortcutBadger.applyCount(getActivity(), totalStatus);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        swipeList.clear();

                        List<tNotificationData> listNotifikasi = new tNotificationBL().GetAllData();

                        dt = new tNotificationBL().GetAllData();

                        swipeList.clear();

                        arrData=new ArrayList<String>();
                        rowItems = new ArrayList<clsRowItem>();
                        if(listNotifikasi!=null){

                            rowItems = new ArrayList<clsRowItem>();
                            rowItems = new ArrayList<clsRowItem>();
                            for(tNotificationData dt :listNotifikasi ){

                                String status = String.valueOf(dt.get_txtStatus());

                                String input = dt.get_dtInserted();

                                String outputDtFormat = new clsMainActivity().convertDateToText(input);

                                if(status.equals("0")){
                                    clsRowItem item = new clsRowItem();
                                    item.set_imageId(String.valueOf(images2));
                                    item.set_title(dt.get_txtTitle());
                                    item.set_txtId(dt.get_txtStatus());
                                    item.set_txtDateTime(outputDtFormat);
                                    rowItems.add(item);
                                } else if (status.equals("1")){
                                    clsRowItem item = new clsRowItem();
                                    item.set_imageId(String.valueOf(images1));
                                    item.set_title(dt.get_txtTitle());
                                    item.set_txtId(dt.get_txtStatus());
                                    item.set_txtDateTime(outputDtFormat);
                                    rowItems.add(item);
                                }

                            }

                        }

                        clsMainActivity clsMain = new clsMainActivity();

                        mmAdapter = setListA(getActivity(), rowItems);
                        mListView.setAdapter(mmAdapter);

                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();

        List<clsLogReceiverHeader_mobile> lisData = new ArrayList<>();
        clsLogReceiverHeader_mobile data = new clsLogReceiverHeader_mobile();
        tUserLoginData dtLogin = new tUserLoginData();
        dtLogin = new tUserLoginBL().getUserActive();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        int num = new clsLogReceiverHeader_mobileBL().getContactsCountStatus(dt.get(position));

        if(num==0){
            data.setTxtIdLogReceiver(new clsMainActivity().GenerateGuid());
            data.setTxtStatus("READ");
            data.setTxtNIK(dtLogin.get_TxtEmpId().toString());
            data.setTxtUSerName(dtLogin.get_txtUserId().toString());
            data.setDtInserted(String.valueOf(dateFormat.format(cal.getTime())));
            data.setIntActive("1");
            data.setIntSubmit("1");
            data.setIntSync("0");
            data.setTxtGuidLogin(dtLogin.get_txtDataId().toString());
            data.setTxtIdHeaderNotif(dt.get(position).get_guiID());

            lisData.add(data);

            new clsLogReceiverHeader_mobileBL().saveData(lisData);
        }
    }

    @Override
    public void onRefresh() {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                onLoad();
                loadData();
                mListView.stopRefresh();
                mListView.stopLoadMore();
            }
        }, 500);
    }

    private void onLoad() {
        mListView.setRefreshTime(RefreshTime.getRefreshTime(getActivity().getApplicationContext()));
        mListView.stopRefresh();

        mListView.stopLoadMore();
    }


    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        }, 1);
    }

    public void loadData(){
        List<tNotificationData> listNotifikasi = new tNotificationBL().GetAllData();

        dt = new tNotificationBL().GetAllData();

        swipeList.clear();

        arrData=new ArrayList<String>();
        rowItems = new ArrayList<clsRowItem>();
        if(listNotifikasi!=null){

            rowItems = new ArrayList<clsRowItem>();
            for(tNotificationData dt :listNotifikasi ){
                String status = String.valueOf(dt.get_txtStatus());

                String input = dt.get_dtInserted();

                String outputDtFormat = new clsMainActivity().convertDateToText(input);

                if(status.equals("0")){
                    clsRowItem item = new clsRowItem();
                    item.set_imageId(String.valueOf(images2));
                    item.set_title(dt.get_txtTitle());
                    item.set_txtId(dt.get_txtStatus());
                    item.set_txtDateTime(outputDtFormat);
                    rowItems.add(item);
                } else if (status.equals("1")){
                    clsRowItem item = new clsRowItem();
                    item.set_imageId(String.valueOf(images1));
                    item.set_title(dt.get_txtTitle());
                    item.set_txtId(dt.get_txtStatus());
                    item.set_txtDateTime(outputDtFormat);
                    rowItems.add(item);
                }

            }

        }

        clsMainActivity clsMain = new clsMainActivity();

        mmAdapter = setListA(getActivity(), rowItems);
        mListView.setAdapter(mmAdapter);
        mListView.setPullRefreshEnable(false);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        mHandler = new Handler();
        HashMap<String, String> mapView = new HashMap<String, String>();

        mapView.put("name", "View");
        mapView.put("bgColor", "#3498db");

        mapMenu = new HashMap<String, HashMap>();
        mapMenu.put("0", mapView);

        SwipeMenuCreator creator = setCreator(getActivity(), mapMenu);
        mListView.setMenuCreator(creator);

//        mListView.setOnMenuItemClickListener(new clsMainActivity().mmenuSwipeListener(getActivity(), "LNotifi", mapMenu, rowItems));

        creator = clsMain.setCreator(getActivity().getApplicationContext(), mapMenu);
        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        viewList(getActivity().getApplicationContext(), position);
                }
            }
        });

        if(idHeaderNotif!=null){
            int index = 0;
            List<tNotificationData> _data = new tNotificationBL().GetAllData();
            for (tNotificationData dt : _data){
                if(idHeaderNotif.equals(dt.get_guiID().toString())){
                    viewList(getContext(), index);
                    break;
                }
                index++;
            }
        }


    }

    ProgressDialog mProgressDialog;

    private void callDownloadData(clsFileAttach_mobile data){
        // instantiate it within the onCreate method
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Please Wait For Downloading File....");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(false);

        // execute this when the downloader must be fired
        final DownloadTask downloadTask = new DownloadTask(getActivity());
        downloadTask.execute(data);

        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                downloadTask.cancel(true);
            }
        });
    }

    private class DownloadTask extends AsyncTask<clsFileAttach_mobile, Integer, String> {
        private Context context;
        private PowerManager.WakeLock mWakeLock;
        private clsFileAttach_mobile _clsFileAttach_mobile = new clsFileAttach_mobile();

        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(clsFileAttach_mobile... clsFileAttach_mobiles) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            this._clsFileAttach_mobile = clsFileAttach_mobiles[0];

            try {
                URL url = new URL(clsFileAttach_mobiles[0].get_txtLinkFileAttach());
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                String txtPath = new clsHardCode().txtPathUserData + "FileAttach/";
                File mediaStorageDir = new File(txtPath);
                // Create the storage directory if it does not exist
                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                        return null;
                    }
                }
                output = new FileOutputStream(txtPath + "/" + clsFileAttach_mobiles[0].get_txtNameFileEncrypt());

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }

        int intProcesscancel = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
            mProgressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            mWakeLock.release();
            mProgressDialog.dismiss();
            if (result != null)
                Toast.makeText(context, "Download error: " + result, Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(context, "File downloaded", Toast.LENGTH_SHORT).show();
                _clsFileAttach_mobile.set_intStatus("DOWNLOADED");
                _clsFileAttach_mobile.set_intSubmit("1");
                List<clsFileAttach_mobile> data = new ArrayList<>();
                data.add(_clsFileAttach_mobile);
                new clsFileAttach_mobileBL().saveData(data);
//                new clsMainActivity().saveLogFile(data.get(0), "DOWNLOADED");
                callShowFile(_clsFileAttach_mobile);
            }
        }
    }

    private void callShowFile(clsFileAttach_mobile data) {
        switch (data.get_txtTypeFile()){
            case ".pdf":
//                Toast.makeText(getActivity(), data.get_txtTypeFile(), Toast.LENGTH_SHORT).show();
//                    saveLogFile(data);

                Intent intent = new Intent(getActivity(), PdfView.class);
                intent.putExtra("idFile", data.get_txtIDFile());
                startActivity(intent);
                break;
            case ".jpg":
//                Toast.makeText(getActivity(), data.get_txtTypeFile(), Toast.LENGTH_SHORT).show();
                _dataclsFileAttach_mobile = data;
//                saveLogFile(data);
                decryptFile();
                break;
        }
    }

    private void saveLogFile(clsFileAttach_mobile data) {
        List<clsLogReceiverDetail_mobile> lisData = new ArrayList<>();
        clsLogReceiverDetail_mobile _clsLogReceiverDetail_mobile = new clsLogReceiverDetail_mobile();
        tUserLoginData dtLogin = new tUserLoginData();
        dtLogin = new tUserLoginBL().getUserActive();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        _clsLogReceiverDetail_mobile.setTxtIdLogReceiverDetail(new clsMainActivity().GenerateGuid());
        _clsLogReceiverDetail_mobile.setTxtStatus("READ");
        _clsLogReceiverDetail_mobile.setTxtNIK(dtLogin.get_TxtEmpId().toString());
        _clsLogReceiverDetail_mobile.setTxtUSerName(dtLogin.get_txtUserId().toString());
        _clsLogReceiverDetail_mobile.setDtInserted(String.valueOf(dateFormat.format(cal.getTime())));
        _clsLogReceiverDetail_mobile.setIntActive("1");
        _clsLogReceiverDetail_mobile.setIntSubmit("1");
        _clsLogReceiverDetail_mobile.setIntSync("0");
        _clsLogReceiverDetail_mobile.setTxtIDFile(data.get_txtIDFile());
        _clsLogReceiverDetail_mobile.setTxtGuidLogin(dtLogin.get_txtDataId().toString());
        _clsLogReceiverDetail_mobile.setTxtIdHeaderNotif(data.get_txtIdHeaderNotif());

        lisData.add(_clsLogReceiverDetail_mobile);

        new clsLogReceiverDetail_mobileBL().saveData(lisData);
    }

    private String pathFolder = new clsHardCode().txtPathUserData + "FileAttach/";
    File fileFolder = null;
    private clsFileAttach_mobile _dataclsFileAttach_mobile = new clsFileAttach_mobile();

    private void decryptFile(){
        String key = "kalbenutritionals";
        clsMainActivity _clsMainActivity = new clsMainActivity();
        File file = new File(pathFolder, _dataclsFileAttach_mobile.get_txtNameFileEncrypt());;
        fileFolder = file;

        try {
            if (_dataclsFileAttach_mobile.get_intStatus().equals("DOWNLOADED")){
                if(_dataclsFileAttach_mobile.get_intActive().equals("0")){
                    byte[] array = _clsMainActivity.getFile(_dataclsFileAttach_mobile);
                    byte[] keyInByte = _clsMainActivity.getKeyBytes(key);
                    byte[] arrayFileDecrypt =  _clsMainActivity.decrypt(array, keyInByte, keyInByte);

                    file = _clsMainActivity.saveFile(arrayFileDecrypt, pathFolder, _dataclsFileAttach_mobile.get_txtNameFileEncrypt());

                    Bitmap bitmap = BitmapFactory.decodeByteArray(arrayFileDecrypt, 0, arrayFileDecrypt.length);
                    Bitmap bitmapScale = Bitmap.createScaledBitmap(bitmap, 150, 150, true);

                    if (zoomImage(bitmapScale)){
                        _dataclsFileAttach_mobile.set_intActive("1");
                        _dataclsFileAttach_mobile.set_intStatus("READ");

                        List<clsFileAttach_mobile> dt = new ArrayList<>();
                        dt.add(_dataclsFileAttach_mobile);

                        new clsFileAttach_mobileBL().saveData(dt);
                    }
                } else if (_dataclsFileAttach_mobile.get_intActive().equals("1")){
                    Bitmap bitmap = BitmapFactory.decodeFile(pathFolder+_dataclsFileAttach_mobile.get_txtNameFileEncrypt());
                    Bitmap bitmapScale = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
                    zoomImage(bitmapScale);
                }
            } else if (_dataclsFileAttach_mobile.get_intStatus().equals("READ")) {
                if(_dataclsFileAttach_mobile.get_intActive().equals("0")){
                    byte[] array = _clsMainActivity.getFile(_dataclsFileAttach_mobile);
                    byte[] keyInByte = _clsMainActivity.getKeyBytes(key);
                    byte[] arrayFileDecrypt =  _clsMainActivity.decrypt(array, keyInByte, keyInByte);

                    file = _clsMainActivity.saveFile(arrayFileDecrypt, pathFolder, _dataclsFileAttach_mobile.get_txtNameFileEncrypt());

                    Bitmap bitmap = BitmapFactory.decodeByteArray(arrayFileDecrypt, 0, arrayFileDecrypt.length);
                    Bitmap bitmapScale = Bitmap.createScaledBitmap(bitmap, 150, 150, true);

                    if (zoomImage(bitmapScale)){
                        _dataclsFileAttach_mobile.set_intActive("1");
                        _dataclsFileAttach_mobile.set_intStatus("READ");

                        List<clsFileAttach_mobile> dt = new ArrayList<>();
                        dt.add(_dataclsFileAttach_mobile);

                        new clsFileAttach_mobileBL().saveData(dt);
                    }
                } else if (_dataclsFileAttach_mobile.get_intActive().equals("1")){
                    Bitmap bitmap = BitmapFactory.decodeFile(pathFolder+_dataclsFileAttach_mobile.get_txtNameFileEncrypt());
                    Bitmap bitmapScale = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
                    zoomImage(bitmapScale);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    private void encrypteFile(){
        String key = "kalbenutritionals";
        clsMainActivity _clsMainActivity = new clsMainActivity();
        File file = new File(pathFolder, _dataclsFileAttach_mobile.get_txtNameFileEncrypt());;
        fileFolder = file;

        try {
            if (_dataclsFileAttach_mobile.get_intActive().equals("1")){
                byte[] array = _clsMainActivity.getFile(_dataclsFileAttach_mobile);
                byte[] keyInByte = _clsMainActivity.getKeyBytes(key);
                byte[] arrayFileEncrypt =  _clsMainActivity.encrypt(array, keyInByte, keyInByte);

                file = _clsMainActivity.saveFile(arrayFileEncrypt, pathFolder, _dataclsFileAttach_mobile.get_txtNameFileEncrypt());

                _dataclsFileAttach_mobile.set_intActive("0");;

                List<clsFileAttach_mobile> dt = new ArrayList<>();
                dt.add(_dataclsFileAttach_mobile);

                new clsFileAttach_mobileBL().saveData(dt);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    private boolean zoomImage (Bitmap bitmap){
        new clsMainActivity().saveLogFile(_dataclsFileAttach_mobile, "READ");
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        final View promptView = layoutInflater.inflate(R.layout.custom_zoom_image, null);
        final TextView tv_desc = (TextView) promptView.findViewById(R.id.desc_act);

//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.BOTTOM;
//        params.gravity = Gravity.CENTER;



        CustomZoomView customZoomView ;
        customZoomView = (CustomZoomView)promptView.findViewById(R.id.customImageVIew1);
        customZoomView.setBitmap(bitmap);
//        customZoomView.setLayoutParams(params);

//        tv_desc.setText(description);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        encrypteFile();
                        dialog.cancel();
                    }
                });

        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();

    return true;

    }
}
