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
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import bl.mCounterNumberBL;
import bl.mEmployeeSalesProductBL;
import bl.tAbsenUserBL;
import bl.tSalesProductHeaderBL;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;
import library.salesforce.common.AppAdapter;
import library.salesforce.common.clsHelper;
import library.salesforce.common.clsSwipeList;
import library.salesforce.common.mEmployeeSalesProductData;
import library.salesforce.common.tAbsenUserData;
import library.salesforce.common.tSalesProductHeaderData;
import library.salesforce.common.tSalesProductQuantityData;
import library.salesforce.dal.enumCounterData;

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
    private EditText edKeterangan;
    private Button preview;
    private String noso;
    TextView tv_date;
    TextView tv_noso;

    clsMainActivity _clsMainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_add_quantity_stock, container, false);

        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        edKeterangan = (EditText) v.findViewById(R.id.etKeterangan_quantity);
        preview = (Button) v.findViewById(R.id.btnPreviewQuantity);
        tv_date = (TextView) v.findViewById(R.id.txtviewDateQuantity);
        tv_noso = (TextView) v.findViewById(R.id.txtNoQuantity);

        _clsMainActivity = new clsMainActivity();

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edKeterangan.getWindowToken(), 0);

        // add no so in Textview txtNoQuantity
        List<tSalesProductHeaderData> dtta = new tSalesProductHeaderBL().getAllSalesProductHeader();
        List<tSalesProductHeaderData> dtLast = new tSalesProductHeaderBL().getLastData();
        if(dtLast==null || dtLast.size()==0) {
            noso = new mCounterNumberBL().getData(enumCounterData.NoDataSO);

        } else {
            noso = new mCounterNumberBL().getData(enumCounterData.NoDataSO);
            List<tSalesProductHeaderData> dataFirstIsExist = new tSalesProductHeaderBL().getDataByNoSO(noso);
            if (dataFirstIsExist.size()==1){
                clsHelper _clsHelper=new clsHelper();
                String oldVersion = dtLast.get(0).get_txtNoSo();
                noso = _clsHelper.generateNewId(oldVersion, "-" , "5");
            } else {
                noso = new mCounterNumberBL().getData(enumCounterData.NoDataSO);
            }
        }
        tv_noso.setText(noso);

        // add date in txtviewDateQuantity
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy",
                Locale.getDefault()).format(new Date());
        tv_date.setText(timeStamp);

        // click Button add product Quantity
        Button btnAddQuantity = (Button)v.findViewById(R.id.btnAddQuantity);
        btnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpAddQuantity();
            }
        });

        // click button preview
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edKeterangan.getText().toString().equals("")) {
                    _clsMainActivity.showCustomToast(getActivity(), "Please fill Description...", false);

                }
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
                .setPositiveButton("Save",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                        alertDialog.setTitle("Confirm");
                        alertDialog.setMessage("Are you sure?");
                        alertDialog.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /*saveReso();
                                viewResoFragment();*/
                            }
                        });
                        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });

                        alertDialog.show();
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }
}
