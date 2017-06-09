package com.kalbenutritionals.app.kalbespgmobile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bl.tNotificationBL;
import come.example.viewbadger.ShortcutBadger;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;
import library.salesforce.common.clsRowItem;
import library.salesforce.common.clsSwipeList;
import library.salesforce.common.tInformationData;
import library.salesforce.common.tNotificationData;
import service.MyNotificationService;

import static com.kalbenutritionals.app.kalbespgmobile.clsMainActivity.setCreator;
import static com.kalbenutritionals.app.kalbespgmobile.clsMainActivity.setListA;

/**
 * Created by ASUS ZE on 06/09/2016.
 */
public class FragmentNotification extends Fragment implements IXListViewListener{

    View v;

    ListView list;
    private static PullToRefreshSwipeMenuListView mListView;
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

        List<tNotificationData> listNotifikasi = new tNotificationBL().GetAllData();

        dt = new tNotificationBL().GetAllData();

        swipeList.clear();

        arrData=new ArrayList<String>();
        rowItems = new ArrayList<clsRowItem>();
        if(listNotifikasi!=null){

            rowItems = new ArrayList<clsRowItem>();
            for(tNotificationData dt :listNotifikasi ){
                String status = String.valueOf(dt.get_txtStatus());
                if(status.equals("0")){
                    clsRowItem item = new clsRowItem();
                    item.set_imageId(String.valueOf(images2));
                    item.set_title(dt.get_txtTitle());
                    item.set_txtId(dt.get_txtStatus());
                    item.set_desc(dt.get_dtPublishEnd());
                    rowItems.add(item);
                } else if (status.equals("1")){
                    clsRowItem item = new clsRowItem();
                    item.set_imageId(String.valueOf(images1));
                    item.set_title(dt.get_txtTitle());
                    item.set_txtId(dt.get_txtStatus());
                    item.set_desc(dt.get_dtPublishEnd());
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
//                clsSwipeList item = swipeList.get(position);
                switch (index) {
                    case 0:
                        viewList(getActivity().getApplicationContext(), position);
                }
            }
        });

//        play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getActivity(), "tes", Toast.LENGTH_SHORT).show();
//                tNotificationData _tNotificationData = new tNotificationData();
//                _tNotificationData.set_intIndex("1");
//                _tNotificationData.set_guiID("dijdisj89348938493");
//                _tNotificationData.set_txtTitle("tes");
//                _tNotificationData.set_txtStatus("2");
//                _tNotificationData.set_dtPublishEnd("shdshk");
//                _tNotificationData.set_txtDescription("dicoba");
//                _tNotificationData.set_txtLink("com.kalbenutritionals.app.kalbespgmobile.FragmentNotification");
//
//                List<tNotificationData> dtList = new ArrayList<>();
//                dtList.add(_tNotificationData);
//
//                new tNotificationBL().saveData(dtList);
//            }
//        });
//
//        show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent serviceIntent = new Intent(getActivity(), MyNotificationService.class);
//                serviceIntent.putExtra("From", "PUSHDATA");
//                getActivity().startService(serviceIntent);
//            }
//        });

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

        return v;
    }

    private void viewList(Context applicationContext, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        final View promptView = layoutInflater.inflate(R.layout.fragment_notification, null);

        LinearLayout ll_top = (LinearLayout) promptView.findViewById(R.id.linearLayoutTop_ntf);
        LinearLayout ll_btm = (LinearLayout) promptView.findViewById(R.id.linearLayoutBottom_ntf);

        final TextView txtTitle = (TextView) promptView.findViewById(R.id.tv_detail_title);
        final TextView txtDesc = (TextView) promptView.findViewById(R.id.tv_detail_desc);
        final ImageView txtImg = (ImageView) promptView.findViewById(R.id.imageViewDetailN);

        ll_top.setVisibility(View.GONE);
        ll_btm.setVisibility(View.VISIBLE);

//        Intent i= getActivity().getIntent();
//        if(i.hasExtra("id")){

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
                                if(status.equals("0")){
                                    clsRowItem item = new clsRowItem();
                                    item.set_imageId(String.valueOf(images2));
                                    item.set_title(dt.get_txtTitle());
                                    item.set_txtId(dt.get_txtStatus());
                                    item.set_desc(dt.get_dtPublishEnd());
                                    rowItems.add(item);
                                } else if (status.equals("1")){
                                    clsRowItem item = new clsRowItem();
                                    item.set_imageId(String.valueOf(images1));
                                    item.set_title(dt.get_txtTitle());
                                    item.set_txtId(dt.get_txtStatus());
                                    item.set_desc(dt.get_dtPublishEnd());
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
    }

    @Override
    public void onRefresh() {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoad();
            }
        }, 1);
    }

    private void onLoad() {
        mListView.setRefreshTime(RefreshTime.getRefreshTime(getActivity().getApplicationContext()));
        mListView.stopRefresh();

        mListView.stopLoadMore();
    }


    @Override
    public void onLoadMore() {

    }
}
