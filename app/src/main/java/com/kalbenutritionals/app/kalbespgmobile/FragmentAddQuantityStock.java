package com.kalbenutritionals.app.kalbespgmobile;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import bl.mEmployeeSalesProductBL;
import bl.tAbsenUserBL;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;
import library.salesforce.common.AppAdapter;
import library.salesforce.common.clsSwipeList;
import library.salesforce.common.mEmployeeSalesProductData;
import library.salesforce.common.tAbsenUserData;
import library.salesforce.common.tSalesProductQuantityData;

/**
 * Created by Rian Andrivani on 16/03/2017.
 */

public class FragmentAddQuantityStock extends Fragment {
    View v;

    private static List<clsSwipeList> swipeList = new ArrayList<clsSwipeList>();
    private AppAdapter mAdapter;
    private PullToRefreshSwipeMenuListView mListView;
    private Handler mHandler;
    private static Map<String, HashMap> mapMenu;
    static List<tSalesProductQuantityData> dt;
    static List<tSalesProductQuantityData> data;
    private FloatingActionButton fab;
    private List<String> arrData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_add_quantity_stock, container, false);

        fab = (FloatingActionButton) v.findViewById(R.id.fab);

        // click Button +

        Button btnAddQuantity = (Button)v.findViewById(R.id.btnAddQuantity);
        btnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpAddQuantity();

            }
        });

        return v;
    }

    private void popUpAddQuantity(){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.popup_add_quantity, null);
        final HashMap<String, String> HMProduct = new HashMap<String, String>();
        final EditText editTextQty = (EditText) promptView.findViewById(R.id.editTextQty);
        final Spinner spnKalbeProduct = (Spinner) promptView.findViewById(R.id.spnProductQuantity);

        List<String> dataProductKalbe = new ArrayList<>();
        List<mEmployeeSalesProductData> listDataProductKalbe = new mEmployeeSalesProductBL().GetAllData();

        editTextQty.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                editTextQty.setText("");
            }
        });

        // add product to spinner spnProductQuantity
        if (listDataProductKalbe.size() > 0) {
            for (mEmployeeSalesProductData dt : listDataProductKalbe) {
                dataProductKalbe.add(dt.get_txtProductBrandDetailGramName());
                HMProduct.put(dt.get_txtProductBrandDetailGramName(), dt.get_txtBrandDetailGramCode());
                HMProduct.put(dt.get_txtBrandDetailGramCode(), dt.get_txtBrandDetailGramCode());
            }
        }

        ArrayAdapter<String> adapterKalbeProduct = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, dataProductKalbe);
        spnKalbeProduct.setAdapter(adapterKalbeProduct);


        // muncul dialog
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
}
