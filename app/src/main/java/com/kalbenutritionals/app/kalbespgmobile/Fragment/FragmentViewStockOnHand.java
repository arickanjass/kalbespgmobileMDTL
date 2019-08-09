package com.kalbenutritionals.app.kalbespgmobile.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.kalbenutritionals.app.kalbespgmobile.R;
import com.kalbenutritionals.app.kalbespgmobile.clsMainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import adapter.AppAdapterViewCusBase;
import bl.clsHelperBL;
import bl.tStockInHandDetailBL;
import bl.tStockInHandHeaderBL;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import library.spgmobile.common.clsSwipeList;
import library.spgmobile.common.tStockInHandDetailData;
import library.spgmobile.common.tStockInHandHeaderData;
import library.spgmobile.common.visitplanAbsenData;

/**
 * Created by aan.junianto on 18/08/2017.
 */

public class FragmentViewStockOnHand extends Fragment implements IXListViewListener {
    private static List<clsSwipeList> swipeList = new ArrayList<clsSwipeList>();
    private AppAdapterViewCusBase mAdapter;

    private PullToRefreshSwipeMenuListView mListView2;

    private Handler mHandler;
    private Map<String, HashMap> mapMenu;
    private List<tStockInHandHeaderData> dt;
    private List<tStockInHandDetailData> data;
    private FloatingActionButton fab;

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_view_global_new,container,false);

        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        final FabSpeedDial fabSpeedDial = (FabSpeedDial) v.findViewById(R.id.fabView);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//                toolbar.setTitle("Add Stock On Hand");
//
//                FragmentAddStockInHand fragmentAddResoSPG = new FragmentAddStockInHand();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame, fragmentAddResoSPG);
//                fragmentTransaction.commit();
//            }
//        });
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                NavigationView nv = (NavigationView) getActivity().findViewById(R.id.navigation_view);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                switch(menuItem.getItemId()){

                    case R.id.action_add:
                        toolbar.setTitle("Add Stock On Hand");
//                        nv.setCheckedItem(2);
                        FragmentAddStockInHand fragmentAddStockInHand = new FragmentAddStockInHand();
                        Bundle args = new Bundle();
                        args.putString("id", "null");
                        args.putString("param", "null");
                        fragmentAddStockInHand.setArguments(args);
                        fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragmentAddStockInHand);
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
        swipeMenuList.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int previousDistanceFromFirstCellToTop;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View firstCell = mListView2.getChildAt(0);
                if(firstCell == null){ return; }
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

    private void viewList(Context ctx, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.activity_preview_so, null);

        final TextView _tvNoSO = (TextView) promptView.findViewById(R.id.tvnoSOtbl);
        final TextView _tvKet = (TextView) promptView.findViewById(R.id.tvkettbl);
        _tvNoSO.setText(": " + dt.get(position).get_txtNoSo());
        _tvKet.setText(": " + dt.get(position).get_txtKeterangan());
        final TextView tv_item = (TextView) promptView.findViewById(R.id.tvItemtbl);
        tv_item.setTypeface(null, Typeface.BOLD);
        tv_item.setText(": " + String.valueOf(dt.get(position).get_intSumItem()));
        final  TextView tv_amount = (TextView) promptView.findViewById(R.id.tvSumAmount) ;
        tv_amount.setTypeface(null, Typeface.BOLD);
        tv_amount.setText(": " + new clsMainActivity().convertNumberDec(Double.valueOf(dt.get(position).get_intSumAmount())));
        final  TextView tv_status = (TextView) promptView.findViewById(R.id.tvStatus);
        tv_status.setTypeface(null, Typeface.BOLD);

        final TableRow tr_amount = (TableRow) promptView.findViewById(R.id.tr_amount);
        tr_amount.setVisibility(View.GONE);

        String statusTran = "";
        if(dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("1")){
            statusTran = "Sync";
        } else if (dt.get(position).get_intSubmit().equals("1") && dt.get(position).get_intSync().equals("0")){
            statusTran = "Submit";
        } else if(dt.get(position).get_intSubmit().equals("0") && dt.get(position).get_intSync().equals("0")){
            statusTran = "Save";
        }

        tv_status.setText(statusTran);

        TableLayout tlb = (TableLayout) promptView.findViewById(R.id.tlProduct);
        tlb.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        TableLayout tl = new TableLayout(getContext());

        String[] colTextHeader = {"Name", "Qty"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tr.addView(tv,params);
        }
        tl.addView(tr);

        data = new tStockInHandDetailBL().GetDataByNoSO(dt.get(position).get_txtNoSo());

        double qtySum=0;
        double qtyNum;
        for(tStockInHandDetailData dat : data){
            tr = new TableRow(getContext());
            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT);

            int leftMargin=0;
            int topMargin=0;
            int rightMargin=0;
            int bottomMargin=0;
            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            tr.setLayoutParams(tableRowParams);

            TextView product = new TextView(getContext());
            product.setTextSize(12);
            product.setPadding(10, 10, 10, 10);
            product.setBackgroundColor(Color.parseColor("#f0f0f0"));
            product.setTextColor(Color.BLACK);
            product.setText(dat.get_txtNameProduct());
            product.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            tr.addView(product,params);

            TextView qty = new TextView(getContext());
            qty.setTextSize(12);
            qty.setPadding(10, 10, 10, 10);
            qty.setBackgroundColor(Color.parseColor("#f0f0f0"));
            qty.setTextColor(Color.BLACK);
            qty.setGravity(Gravity.RIGHT);
            qty.setText(dat.get_intQty()+" pcs");
            tr.addView(qty,params);

//            TextView price = new TextView(getContext());
//            price.setTextSize(12);
//            price.setPadding(10, 10, 10, 10);
//            price.setBackgroundColor(Color.parseColor("#f0f0f0"));
//            price.setTextColor(Color.BLACK);
//            price.setGravity(Gravity.RIGHT);
//            price.setText(new clsMainActivity().convertNumberDec(Double.valueOf(dat.get_intPrice())));
//            tr.addView(price,params);
//
//            TextView amount = new TextView(getContext());
//            amount.setTextSize(12);
//            amount.setWidth(200);
//            amount.setPadding(10, 10, 10, 10);
//            amount.setBackgroundColor(Color.parseColor("#f0f0f0"));
//            amount.setTextColor(Color.BLACK);
//            amount.setGravity(Gravity.RIGHT);
//            double prc = Double.valueOf(dat.get_intPrice());
//            double itm = Double.valueOf(dat.get_intQty());
//            qtyNum = prc * itm;
//            qtySum += qtyNum;
//            amount.setText(new clsMainActivity().convertNumberDec(qtyNum));
//            tr.addView(amount,params);

            tl.addView(tr, tableRowParams);
        }

        tlb.addView(tl);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNeutralButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Warning");
                        builder.setMessage("Are you sure to submit ");

                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                tStockInHandHeaderData data = new tStockInHandHeaderData();
                                data.set_intId(dt.get(position).get_intId());
                                data.set_intSubmit("1");
                                new tStockInHandHeaderBL().updateDataSubmit(data);
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

    private void loadData(){

//        tUserLoginData dtLogin = new tUserLoginBL().getUserActive();
        visitplanAbsenData _viAbsenData = new visitplanAbsenData();
        _viAbsenData = new clsHelperBL().getDataCheckInActive();

        clsSwipeList swplist;
        dt = new tStockInHandHeaderBL().getAllSalesProductHeaderByOutletCode(_viAbsenData.get_txtOutletCode());

        swipeList.clear();

        if(dt!=null) {
            for (int i = 0; i < dt.size(); i++) {
                swplist = new clsSwipeList();
                swplist.set_txtTitle("No : " + dt.get(i).get_txtNoSo());
                String desc = dt.get(i).get_txtKeterangan();
                if(desc.length()>20){
                    desc = dt.get(i).get_txtKeterangan().substring(0,20) + "...";
                }
                swplist.set_txtDescription("Description : " + desc);
                if (dt.get(i).get_intSubmit().equals("1")&&dt.get(i).get_intSync().equals("0")){
                    swplist.set_txtDescription2("Submit");
                } else if (dt.get(i).get_intSubmit().equals("1")&&dt.get(i).get_intSync().equals("1")){
                    swplist.set_txtDescription2("Sync");
                }
                else if (dt.get(i).get_intSubmit().equals("0")&&dt.get(i).get_intSync().equals("0")){
                    swplist.set_txtDescription2("Save");
                }

                swipeList.add(swplist);
            }
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

        HashMap<String, String> mapView = new HashMap<String, String>();
        HashMap<String, String> mapEdit = new HashMap<String, String>();
        HashMap<String, String> mapDelete = new HashMap<String, String>();

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
            public void onMenuItemClick(int position, edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu menu, int index) {
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
            final List<tStockInHandDetailData> detailData = new tStockInHandDetailBL().GetDataByNoSO(dt.get(position).get_txtNoSo());

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setTitle("Warning");
            builder.setMessage("Are you sure to delete?" + "\n The operation cannot be undone...");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    new tStockInHandHeaderBL().deleteTrId(dt.get( position).get_intId());
                    if(detailData!=null&&detailData.size()>0){
                        for(tStockInHandDetailData data : detailData){
                            new tStockInHandDetailBL().deleteTrId(data.get_intId());
                        }
                    }
                    new clsMainActivity().showCustomToast(getContext(), "Transaction data has been deleted", true);
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
            new clsMainActivity().showCustomToast(getContext(), "Cannot delete data, data was " + statusTran, false);
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
            toolbar.setTitle("Add Stock On Hand");

            FragmentAddStockInHand fragmentAddStockInHand = new FragmentAddStockInHand();
            Bundle args = new Bundle();
            args.putString("id", dt.get( position).get_txtNoSo());
            args.putString("param", "edit");
            fragmentAddStockInHand.setArguments(args);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragmentAddStockInHand);
            fragmentTransaction.commit();
        } else {
            String name = dt.get( position).get_txtNoSo().toString();
            new clsMainActivity().showCustomToast(getContext(), "Cannot edit, " + "\n data was " + statusTran, false);
        }
    }
    private void saveDataSubmitAll() {

//        tAbsenUserData dtActive = new tAbsenUserBL().getDataCheckInActive();

        visitplanAbsenData _viAbsenData = new visitplanAbsenData();
        _viAbsenData = new clsHelperBL().getDataCheckInActive();

        final List<tStockInHandHeaderData> data = new tStockInHandHeaderBL().getAllHeaderByOutletCodeUnsubmit(_viAbsenData.get_txtOutletCode());

        if(data!=null&&data.size()!=0){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure to submit all transaction ? ");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    for(tStockInHandHeaderData dt : data){
                        new tStockInHandHeaderBL().updateDataSubmit(dt);
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
}
