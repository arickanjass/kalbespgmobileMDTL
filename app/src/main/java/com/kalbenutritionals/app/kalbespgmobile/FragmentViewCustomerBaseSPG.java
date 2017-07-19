package com.kalbenutritionals.app.kalbespgmobile;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import adapter.AppAdapterViewCusBase;
import bl.clsHelperBL;
import bl.mTypeSubmissionMobileBL;
import bl.tCustomerBasedMobileDetailBL;
import bl.tCustomerBasedMobileDetailProductBL;
import bl.tCustomerBasedMobileHeaderBL;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import library.spgmobile.common.clsSwipeList;
import library.spgmobile.common.mTypeSubmissionMobile;
import library.spgmobile.common.tCustomerBasedMobileDetailData;
import library.spgmobile.common.tCustomerBasedMobileDetailProductData;
import library.spgmobile.common.tCustomerBasedMobileHeaderData;
import library.spgmobile.common.visitplanAbsenData;

public class FragmentViewCustomerBaseSPG extends Fragment implements IXListViewListener {

    private List<clsSwipeList> swipeList = new ArrayList<clsSwipeList>();
    private AppAdapterViewCusBase mAdapter;

    // private SwipeMenuListView mListView2;

    private PullToRefreshSwipeMenuListView mListView2;

    private Map<String, HashMap> mapMenu;
    private FloatingActionButton fab;
    Handler mHandler;
    private List<tCustomerBasedMobileHeaderData> dt;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_customerbase_view, container, false);

//        fab = (FloatingActionButton) v.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//                toolbar.setTitle("Add Customer Base SPG");
//
//                FragmentAddCustomerBaseSPG fragmentAddCustomerBaseSPG = new FragmentAddCustomerBaseSPG();
//                Bundle args = new Bundle();
//                args.putString("idTrCustomer", "null");
//                args.putString("param", "null");
//                fragmentAddCustomerBaseSPG.setArguments(args);
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame, fragmentAddCustomerBaseSPG);
//                fragmentTransaction.commit();
//            }
//        });

        final FabSpeedDial fab = (FabSpeedDial) v.findViewById(R.id.fabView);

        fab.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                NavigationView nv = (NavigationView) getActivity().findViewById(R.id.navigation_view);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                switch(menuItem.getItemId()){

                    case R.id.action_add_customerbase:
                        toolbar.setTitle("Add Customer Base SPG");
                        nv.setCheckedItem(2);
                        FragmentAddCustomerBaseSPG fragmentAddCustomerBaseSPG = new FragmentAddCustomerBaseSPG();
                        Bundle args = new Bundle();
                        args.putString("idTrCustomer", "null");
                        args.putString("param", "null");
                        fragmentAddCustomerBaseSPG.setArguments(args);
                        fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragmentAddCustomerBaseSPG);
                        fragmentTransaction.commit();
                        return true;

                    case R.id.action_submitAll:
                        saveDataSubmitAll();
//                        new clsMainActivity().showCustomToast(getContext(), "submit", true);
                        return true;

                    default:
                        return false;
                }
            }
        });

        final PullToRefreshSwipeMenuListView swipeMenuList = (PullToRefreshSwipeMenuListView) v.findViewById(R.id.SwipelistView);
        swipeMenuList.setPullRefreshEnable(true);
        final CoordinatorLayout coordinatorLayout= (CoordinatorLayout) v.findViewById(R.id.main_content);
        coordinatorLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int coord[]={0,0};
                coordinatorLayout.getLocationInWindow(coord);
                int absoluteTop = coord[1];

                if(absoluteTop < 90){
                    fab.hide();
                }
                else{
                    fab.show();
                }

                return false;
            }
        });

        swipeMenuList.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int previousDistanceFromFirstCellToTop;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View firstCell = mListView2.getChildAt(0);
                int distanceFromFirstCellTop = mListView2.getFirstVisiblePosition() * firstCell.getHeight()-firstCell.getTop();


                if(distanceFromFirstCellTop > previousDistanceFromFirstCellToTop){
                    fab.hide();
                }
                if(distanceFromFirstCellTop < previousDistanceFromFirstCellToTop){
                    fab.show();
                }
                previousDistanceFromFirstCellToTop = distanceFromFirstCellTop;
            }
        });
        loadData();
        return v;
    }

    private void saveDataSubmitAll() {

//        tAbsenUserData dtActive = new tAbsenUserBL().getDataCheckInActive();

        visitplanAbsenData _viAbsenData = new visitplanAbsenData();
        _viAbsenData = new clsHelperBL().getDataCheckInActive();

        final List<tCustomerBasedMobileHeaderData> data = new tCustomerBasedMobileHeaderBL().getAllCustomerBasedMobileHeaderByOutletCodeUnsubmit(_viAbsenData.get_txtOutletCode());

        if(data.size()!=0){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure to submit all transaction ? ");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    for(tCustomerBasedMobileHeaderData dt : data){
                        new tCustomerBasedMobileHeaderBL().updateDataSubmit(dt);
                    }
                    new clsMainActivity().showCustomToast(getContext(), "submit successfull", true);
                    loadData();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        } else {
            new clsMainActivity().showCustomToast(getContext(), "There is No data to submit", false);
        }
    }

    private void viewList(Context ctx, final int position) {

        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        final View promptView = layoutInflater.inflate(R.layout.activity_preview_customerbase, null);

        final TextView _tvSubmissionId = (TextView) promptView.findViewById(R.id.tvSubmissionId);
        final TextView _tvNama = (TextView) promptView.findViewById(R.id.tvNama);
        final TextView _tvTelp = (TextView) promptView.findViewById(R.id.tvTelp);
        final TextView _tvTelp2 = (TextView) promptView.findViewById(R.id.tvTelp2);
        final TextView _tvTelpKantor = (TextView) promptView.findViewById(R.id.tvTelpKantor);
        final TextView _tvEmail = (TextView) promptView.findViewById(R.id.tvEmail);
        final TextView _tvPinBbm = (TextView) promptView.findViewById(R.id.tvPinBbm);
        final TextView _tvAlamat = (TextView) promptView.findViewById(R.id.tvAlamat);
        final TextView _tvPicPelanggan = (TextView) promptView.findViewById(R.id.tvPicPelanggan);
        final TextView _tvGender = (TextView) promptView.findViewById(R.id.tvGender);
        final TextView _dob = (TextView) promptView.findViewById(R.id.tvDob);

//        final ListView _lvProduk = (ListView) promptView.findViewById(R.id.lvProduks);


        mTypeSubmissionMobile mtTypeSubmissionMobile = new mTypeSubmissionMobile();
        mtTypeSubmissionMobile = new mTypeSubmissionMobileBL().getDataBySubmissionCode(dt.get(position).get_txtSubmissionCode());

        String gender = dt.get(position).get_txtGender().toString().equals("Laki-laki") ? "Male" : "Female";
        String stringDatedb = dt.get(position).get_txtTglLahir().toString();

//        int year = 0;
//        int month = 0;
//        int day = 0;
//        String part1 = "";
//        String part2 = "";
//        String part3 = "";
//        clsMainActivity clsMainMonth = new clsMainActivity();
//
//        if (stringDatedb.toString().equals("null")||!stringDatedb.toString().equals("")) {
//            String[] parts = stringDatedb.split("-");
//            part1 = parts[0]; //year
//            part2 = parts[1]; //month
//            part3 = parts[2]; //date
//            String month2 = clsMainMonth.months[Integer.parseInt(part2)];
//            _dob.setText(": " + part3 + " - " + month2 + " - " + part1);
//        } else {
//            _dob.setText("Not set");
//        }

        _tvSubmissionId.setText(mtTypeSubmissionMobile.get_txtNamaMasterData());
        _tvNama.setText(dt.get(position).get_txtNamaDepan());
        _tvGender.setText(gender);
        _tvTelp.setText(dt.get(position).get_txtTelp());
        _tvTelp2.setText(dt.get(position).get_txtTelp2());
        _tvTelpKantor.setText(dt.get(position).get_txtTelpKantor());
        _tvEmail.setText(dt.get(position).get_txtEmail());
        _tvPinBbm.setText(dt.get(position).get_txtPINBBM());
        _tvAlamat.setText(dt.get(position).get_txtALamat());
        _tvPicPelanggan.setText(Integer.parseInt(dt.get(position).get_intPIC()) == 1 ? "Yes" : "No");

        String statusTran = "";
        if(dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("1")){
            statusTran = "Sync";
        } else if (dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("0")){
            statusTran = "Submit";
        } else if(dt.get(position).get_intSubmit().equals("0") && dt.get(position).get_intSync().equals("0")){
            statusTran = "Save";
        }

        TableLayout tl = (TableLayout) promptView.findViewById(R.id.tlPerson);
        tl.removeAllViews();

        TableRow row = new TableRow(getContext());
        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        String[] colTextHeader = {"Name", "Product"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));

            tv.setTextColor(Color.WHITE);
            row.addView(tv);
        }
        tl.addView(row);

        final List<tCustomerBasedMobileDetailData> dtListDetail = new tCustomerBasedMobileDetailBL().getAllDataByHeaderId(dt.get(position).get_intTrCustomerId());

        for (int count = 0; count < dtListDetail.size(); count++) {

            List<tCustomerBasedMobileDetailProductData> dtListProduct = new tCustomerBasedMobileDetailProductBL().getDataByCustomerDetailId(dtListDetail.get(count).get_intTrCustomerIdDetail());

            if (dtListProduct != null) {
                row = new TableRow(getContext());
                row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

                String product = "";
                String status = "";
                status = Integer.parseInt(dtListDetail.get(count).get_intPIC()) == 1 ? " (PIC)" : "";
                for (tCustomerBasedMobileDetailProductData dataproduct : dtListProduct) {
                    product = product + "- " + dataproduct.get_txtProductBrandName() + " (" + dataproduct.get_txtProductBrandQty() + ")\n";

                }
                String[] colText = {dtListDetail.get(count).get_txtNamaDepan() + status, product};

                int j = 1;

                for (String text : colText) {
                    TextView tv = new TextView(getContext());

                    if (j % 2 == 0) {
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
                    } else {
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
                    }
                    tv.setTextSize(14);
                    tv.setPadding(10, 10, 10, 10);
                    tv.setText(text);
                    tv.setGravity(Gravity.LEFT);

                    if (count % 2 == 0) {
                        tv.setBackgroundColor(Color.parseColor("#F0F0F0"));
                    } else {
                        tv.setBackgroundColor(Color.GRAY);
                    }

                    tv.setTextColor(Color.BLACK);

                    row.addView(tv);
                    j++;
                }
                tl.addView(row);
            }
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)/*
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tCustomerBasedMobileHeaderData data = new tCustomerBasedMobileHeaderData();
                        data.set_intTrCustomerId(dt.get(position).get_intTrCustomerId());
                        data.set_intSubmit("1");
                        new tCustomerBasedMobileHeaderBL().updateDataSubmit(data);
                        dialog.cancel();
                        loadData();
                    }
                })*/
                .setNeutralButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Warning");
                        builder.setMessage("Are you sure to submit ");

                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                tCustomerBasedMobileHeaderData data = new tCustomerBasedMobileHeaderData();
                                data.set_intTrCustomerId(dt.get(position).get_intTrCustomerId());
                                data.set_intSubmit("1");
                                new tCustomerBasedMobileHeaderBL().updateDataSubmit(data);
                                dialog.cancel();
                                loadData();
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();

        if(!statusTran.equals("")&&statusTran.equals("Save")){
            alertD.getButton(AlertDialog.BUTTON_NEUTRAL).setEnabled(true);
        } else {
            alertD.getButton(AlertDialog.BUTTON_NEUTRAL).setEnabled(false);
        }
    }

    private void loadData() {
//        tAbsenUserData dtActive = new tAbsenUserBL().getDataCheckInActive();

        visitplanAbsenData _viAbsenData = new visitplanAbsenData();
        _viAbsenData = new clsHelperBL().getDataCheckInActive();

        clsSwipeList swplist;
        dt = new tCustomerBasedMobileHeaderBL().getAllCustomerBasedMobileHeaderByOutletCodeForView(_viAbsenData.get_txtOutletCode());

        swipeList.clear();

        String status = "";

        for (int i = 0; i < dt.size(); i++) {
//            String status = dt.get(i).get_intSubmit().equals("1") && dt.get(i).get_intSync().equals("1") ? "Sync" : "Submit";
            if(dt.get(i).get_intSubmit().equals("1") && dt.get(i).get_intSync().equals("1")){
                status = "Sync";
            } else if (dt.get(i).get_intSubmit().equals("1") && dt.get(i).get_intSync().equals("0")){
                status = "Submit";
            } else if(dt.get(i).get_intSubmit().equals("0") && dt.get(i).get_intSync().equals("0")){
                status = "Saved";
            }
            swplist = new clsSwipeList();
            swplist.set_txtTitle("Submission Id : " + dt.get(i).get_txtSubmissionId());
            swplist.set_txtDescription("Name : " + dt.get(i).get_txtNamaDepan());
            swplist.set_txtDescription2(status);
            swipeList.add(swplist);
        }

        clsMainActivity clsMain = new clsMainActivity();
        mListView2 = (PullToRefreshSwipeMenuListView) v.findViewById(R.id.SwipelistView);

        mAdapter = clsMain.setListViewCusBase(getActivity().getApplicationContext(), swipeList);
        mListView2.setAdapter(mAdapter);
        mListView2.setPullRefreshEnable(true);
        mListView2.setPullLoadEnable(true);
        mListView2.setEmptyView( v.findViewById(R.id.LayoutEmpty));
        mListView2.setXListViewListener(this);
        mHandler = new Handler();

//        HashMap<String, String> mapView = new HashMap<String, String>();
//
//        mapView.put("name", "View");
//        mapView.put("bgColor", "#3498db");
//
//        mapMenu = new HashMap<>();
//        mapMenu.put("0", mapView);

        HashMap<String, String> mapView = new HashMap<String, String>();
        HashMap<String, String> mapEdit = new HashMap<String, String>();
        HashMap<String, String> mapDelete = new HashMap<String, String>();

        for (int i = 0; i < dt.size(); i++) {
            if(dt.get(i).get_intSubmit().equals("1") && dt.get(i).get_intSync().equals("1")){
                status = "Sync";
            } else if (dt.get(i).get_intSubmit().equals("1") && dt.get(i).get_intSync().equals("0")){
                status = "Submit";
            } else if(dt.get(i).get_intSubmit().equals("0") && dt.get(i).get_intSync().equals("0")){
                status = "Save";
            }
        }

        mapView.put("name", "View");
        mapView.put("bgColor", "#3498db");

        mapEdit.put("name", "Edit");
        mapEdit.put("bgColor", "#2980b9");

        mapDelete.put("name", "Delete");
        mapDelete.put("bgColor", "#c0392b");

        mapMenu = new HashMap<String, HashMap>();
        mapMenu.put("0", mapView);
        mapMenu.put("1", mapEdit);
        mapMenu.put("2", mapDelete);

        SwipeMenuCreator creator = clsMain.setCreatorForCusBased(getActivity().getApplicationContext(), mapMenu);
        mListView2.setMenuCreator(creator);
        mListView2.setEmptyView(v.findViewById(R.id.LayoutEmpty));
        mListView2.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                clsSwipeList item = swipeList.get(position);
                switch (index) {
                    case 0:
                        viewList(getContext(), position);
                        break;
                    case 1:
                        editList(getActivity().getApplicationContext(), position);
//                        new clsMainActivity().showCustomToast(getContext(), "edit", true);
                        break;
                    case 2:
                        deleteList(getActivity().getApplicationContext(), position);
                }
            }
        });
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        RefreshTime.setRefreshTime(getContext(), " " + df.format(new Date()));
        mListView2.setRefreshTime(RefreshTime.getRefreshTime(getActivity().getApplicationContext()));

    }

    private void deleteList(Context applicationContext, final int position) {
        boolean isDeleteable = false;
        String statusTran = "";
        if(dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("1")){
            statusTran = "Syncronized";
        } else if (dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("0")){
            statusTran = "Submit";
        } else if(dt.get(position).get_intSubmit().equals("0") && dt.get(position).get_intSync().equals("0")){
            statusTran = "Save";
            isDeleteable = true;
        }

        if(isDeleteable){
            final String name = dt.get( position).get_txtNamaDepan().toString();
            List<tCustomerBasedMobileDetailData> detailData = new tCustomerBasedMobileDetailBL().getAllDataByHeaderId(dt.get(position).get_intTrCustomerId());
            List<tCustomerBasedMobileDetailProductData> detailProductData = new ArrayList<>();

            String textCunsomer = detailData.size()==1 ? "Consumer" : "Consumers";

            for(tCustomerBasedMobileDetailData dataDetailProduct : detailData){
                detailProductData = new tCustomerBasedMobileDetailProductBL().getDataByCustomerDetailId(dataDetailProduct.get_intTrCustomerIdDetail());
            }


            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setTitle("Warning");
            builder.setMessage("Are you sure to delete " + name.toUpperCase() + " as PIC ?" +"\n" + "Contain : " + String.valueOf(detailData.size()) + " " + textCunsomer);

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    new tCustomerBasedMobileHeaderBL().deleteTrId(dt.get( position).get_intTrCustomerId());
                    new clsMainActivity().showCustomToast(getContext(), "Transaction data " + name.toUpperCase() + " has been deleted", true);
                    loadData();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        } else {
            String name = dt.get( position).get_txtNamaDepan().toString();
            new clsMainActivity().showCustomToast(getContext(), "Cannot delete data PIC " + name + "\n data was " + statusTran, false);
        }
    }

    private void editList(Context applicationContext, int position) {
        boolean isEditalbe = false;
        String statusTran = "";
        if(dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("1")){
            statusTran = "Syncronized";
        } else if (dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("0")){
            statusTran = "Submit";
        } else if(dt.get(position).get_intSubmit().equals("0") && dt.get(position).get_intSync().equals("0")){
            statusTran = "Save";
            isEditalbe = true;
        }

        if(isEditalbe){
            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            toolbar.setTitle("Add Customer Base SPG");

            FragmentAddCustomerBaseSPG fragmentAddCustomerBaseSPG = new FragmentAddCustomerBaseSPG();
            Bundle args = new Bundle();
            args.putString("idTrCustomer", dt.get( position).get_intTrCustomerId());
            args.putString("param", "edit");
            fragmentAddCustomerBaseSPG.setArguments(args);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragmentAddCustomerBaseSPG);
            fragmentTransaction.commit();
        } else {
            String name = dt.get( position).get_txtNamaDepan().toString();
            new clsMainActivity().showCustomToast(getContext(), "Cannot edit data PIC " + name + "\n data was " + statusTran, false);
        }
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                mListView2.stopRefresh();
                mListView2.stopLoadMore();
            }
        }, 500);
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
}
