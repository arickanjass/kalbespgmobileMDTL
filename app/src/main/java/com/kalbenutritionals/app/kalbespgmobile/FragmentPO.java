package com.kalbenutritionals.app.kalbespgmobile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import bl.clsHelperBL;
import bl.tPurchaseOrderDetailBL;
import bl.tPurchaseOrderHeaderBL;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;
import library.spgmobile.common.AppAdapter;
import library.spgmobile.common.clsSwipeList;
import library.spgmobile.common.tPurchaseOrderDetailData;
import library.spgmobile.common.tPurchaseOrderHeaderData;
import library.spgmobile.common.tSalesProductDetailData;
import library.spgmobile.common.tSalesProductHeaderData;
import library.spgmobile.common.visitplanAbsenData;

/**
 * Created by XSIS on 21/03/2017.
 */

public class FragmentPO extends Fragment implements IXListViewListener{
    View view;
    private FloatingActionButton fab;
    private Handler mHandler;
    private static List<clsSwipeList> swipeList = new ArrayList<clsSwipeList>();
    private AppAdapter mAdapter;
    private PullToRefreshSwipeMenuListView mListView;
    private static Map<String, HashMap> mapMenu;
    static List<tPurchaseOrderHeaderData> dtPO;
    static List<tPurchaseOrderDetailData> dataPO;
    static List<tSalesProductHeaderData> dt;
    static List<tSalesProductDetailData> data;
    String getData ="key_view";
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_po_view, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.fabPO);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//        getData = getActivity().getIntent().getStringExtra("View PO");
        Intent intent = getActivity().getIntent();
        if (intent.getExtras() != null){
            String keyViewPO = intent.getStringExtra("key_view");
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setTitle("Add Purchase Order");

                FragmentAddPO fragmentAddPO = new FragmentAddPO();
                FragmentTransaction fragmentTransactionAddPO = getFragmentManager().beginTransaction();
                fragmentTransactionAddPO.replace(R.id.frame, fragmentAddPO);
                fragmentTransactionAddPO.commit();
            }
        });
        loadData();
        return view;
    }


    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
               mListView.stopRefresh();
                mListView.stopLoadMore();
            }
        }, 500);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
               onLoad();
            }
        }, 1);
    }

    private void onLoad(){
        mListView.setRefreshTime(RefreshTime.getRefreshTime(getActivity().getApplicationContext()));
        mListView.stopRefresh();
        mListView.stopLoadMore();
    }

    private void viewList(Context ctx, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.activity_preview_po, null);

        final TextView _tvNoSO = (TextView) promptView.findViewById(R.id.tvnoSOtbl);
        final TextView _tvKet = (TextView) promptView.findViewById(R.id.tvkettbl);
        _tvNoSO.setText(": " + dtPO.get(position).get_txtNoOrder());
        _tvKet.setText(": " + dtPO.get(position).get_txtKeterangan());
        final TextView tv_item = (TextView) promptView.findViewById(R.id.tvItemtbl);
        tv_item.setTypeface(null, Typeface.BOLD);
        tv_item.setText(": " + String.valueOf(dtPO.get(position).get_intSumItem()));
        final  TextView tv_amount = (TextView) promptView.findViewById(R.id.tvSumAmount) ;
        tv_amount.setTypeface(null, Typeface.BOLD);
        tv_amount.setText(": " + new clsMainActivity().convertNumberDec(Double.valueOf(dtPO.get(position).get_intSumAmount())));
        final  TextView tv_status = (TextView) promptView.findViewById(R.id.tvStatus);
        tv_status.setTypeface(null, Typeface.BOLD);

        if (dtPO.get(position).get_intSubmit().equals("1")&&dtPO.get(position).get_intSync().equals("0")){
            tv_status.setText(": Submit");
        } else if (dtPO.get(position).get_intSubmit().equals("1")&&dtPO.get(position).get_intSync().equals("1")){
            tv_status.setText(": Sync");
        }

        TableLayout tlb = (TableLayout) promptView.findViewById(R.id.tlProduct);
        tlb.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        TableLayout tl = new TableLayout(getContext());

        String[] colTextHeader = {"Nama", "Qty", "Price", "Amount"};

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

        //data = new tSalesProductDetailBL().GetDataByNoSO(dt.get(position).get_txtNoSo());
        dataPO = new tPurchaseOrderDetailBL().getDataByNoPO(dtPO.get(position).get_txtNoOrder());//kalau udah punya no PO

        double qtySum=0;
        double qtyNum;
        for(tPurchaseOrderDetailData dat : dataPO){
            tr = new TableRow(getContext());
            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

            int leftMargin=0;
            int topMargin=0;
            int rightMargin=0;
            int bottomMargin=0;
            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            tr.setLayoutParams(tableRowParams);

            TextView product = new TextView(getContext());
            product.setTextSize(12);
            product.setWidth(200);
            product.setPadding(10, 10, 10, 10);
            product.setBackgroundColor(Color.parseColor("#f0f0f0"));
            product.setTextColor(Color.BLACK);
            product.setText(dat.get_txtNameProduct());
            tr.addView(product,params);

            TextView qty = new TextView(getContext());
            qty.setTextSize(12);
            qty.setPadding(10, 10, 10, 10);
            qty.setBackgroundColor(Color.parseColor("#f0f0f0"));
            qty.setTextColor(Color.BLACK);
            qty.setGravity(Gravity.RIGHT);
            qty.setText(dat.get_intQty());
            tr.addView(qty,params);

            TextView price = new TextView(getContext());
            price.setTextSize(12);
            price.setPadding(10, 10, 10, 10);
            price.setBackgroundColor(Color.parseColor("#f0f0f0"));
            price.setTextColor(Color.BLACK);
            price.setGravity(Gravity.RIGHT);
            price.setText(new clsMainActivity().convertNumberDec(Double.valueOf(dat.get_intPrice())));
            tr.addView(price,params);

            TextView amount = new TextView(getContext());
            amount.setTextSize(12);
            amount.setWidth(200);
            amount.setPadding(10, 10, 10, 10);
            amount.setBackgroundColor(Color.parseColor("#f0f0f0"));
            amount.setTextColor(Color.BLACK);
            amount.setGravity(Gravity.RIGHT);
            double prc = Double.valueOf(dat.get_intPrice());
            double itm = Double.valueOf(dat.get_intQty());
            qtyNum = prc * itm;
            qtySum += qtyNum;
            amount.setText(new clsMainActivity().convertNumberDec(qtyNum));
            tr.addView(amount,params);

            tl.addView(tr, tableRowParams);
        }

        tlb.addView(tl);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }

    private void loadData(){
//        tAbsenUserData dtActive = new tAbsenUserBL().getDataCheckInActive();
        visitplanAbsenData dtActive = new clsHelperBL().getDataCheckInActive();
        clsSwipeList swplist;
        dtPO = new tPurchaseOrderHeaderBL().getAllPurchaseOrderHeaderByOutletCode(dtActive.get_txtOutletCode());

        swipeList.clear();

        if(dtPO!=null) {
            for (int i = 0; i < dtPO.size(); i++) {
                swplist = new clsSwipeList();
                swplist.set_txtTitle(dtPO.get(i).get_txtNoOrder());
                if (dtPO.get(i).get_intSubmit().equals("1")&&dtPO.get(i).get_intSync().equals("0")){
                    swplist.set_txtDescription("Submit");
                } else if (dtPO.get(i).get_intSubmit().equals("1")&&dtPO.get(i).get_intSync().equals("1")){
                    swplist.set_txtDescription("Sync");
                }

                swipeList.add(swplist);
            }
        }

        clsMainActivity clsMain = new clsMainActivity();

        mListView = (PullToRefreshSwipeMenuListView) view.findViewById(R.id.listViewPO);
        mAdapter = clsMain.setList(getActivity().getApplicationContext(), swipeList);
        mListView.setAdapter(mAdapter);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        mListView.setEmptyView(view.findViewById(R.id.ln_po_view));
        mListView.setXListViewListener(this);
        mHandler = new Handler();

        HashMap<String, String> mapView = new HashMap<String, String>();

        mapView.put("name", "View");
        mapView.put("bgColor", "#3498db");

        mapMenu = new HashMap<String, HashMap>();
        mapMenu.put("0", mapView);

        SwipeMenuCreator creator = clsMain.setCreator(getActivity().getApplicationContext(), mapMenu);
        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                clsSwipeList item = swipeList.get(position);
                switch (index) {
                    case 0:
                        viewList(getActivity().getApplicationContext(), position);
                }
            }
        });

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        RefreshTime.setRefreshTime(getContext(), " " + df.format(new Date()));
        mListView.setRefreshTime(RefreshTime.getRefreshTime(getActivity().getApplicationContext()));
    }
}
