package com.kalbenutritionals.app.kalbespgmobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import addons.adapter.AdapterListVisitplan;
import bl.tVisitPlanRealisasiBL;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import library.spgmobile.common.clsSwipeList;
import library.spgmobile.common.tVisitPlanRealisasiData;

/**
 * Created by Robert on 16/05/2017.
 */

public class FragmentViewVisitplan  extends Fragment implements IXListViewListener {
    View v;
    ListView lvVisitPlan;
    HashMap hmIdRealisasi = new HashMap<String, String>();;
    private AdapterListVisitplan mAdapter;
    private ArrayList<clsSwipeList> swipeListProduct = new ArrayList<clsSwipeList>();
    ImageView icon;
    private String ID_REALISASI = "IdRealisasi";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_view_realisasi_header, container, false);
        lvVisitPlan = (ListView) v.findViewById(R.id.list_view_tRealisasi);

        loadData();
        return v;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
    private void loadData() {
        List<tVisitPlanRealisasiData> dtVisitPlan = new tVisitPlanRealisasiBL().getAllDataByIntSubmit("0");

        lvVisitPlan = (ListView) v.findViewById(R.id.list_view_tRealisasi);
        clsSwipeList swplist;

        swipeListProduct.clear();

        for (int i = 0; i < dtVisitPlan.size(); i++) {
            swplist = new clsSwipeList();
            swplist.set_txtId(dtVisitPlan.get(i).get_txtDataIDRealisasi());
            swplist.set_txtTitle(dtVisitPlan.get(i).get_txtOutletName());
            String desc = dtVisitPlan.get(i).get_txtDesc();
            if(dtVisitPlan.get(i).get_txtDesc().length()>20){
                desc = desc.substring(0,20) + "...";
            }
            swplist.set_txtDescription(desc);
            hmIdRealisasi.put(i,dtVisitPlan.get(i).get_txtDataIDRealisasi());
            if(dtVisitPlan.get(i).get_intSubmit().equals("1")){
                swplist.set_intPIC("Visited");
            }else{
                swplist.set_intPIC("Plan");
            }
            swipeListProduct.add(swplist);
        }

        clsMainActivity clsMain = new clsMainActivity();

        mAdapter = clsMain.setListVisitPlan(getActivity().getApplicationContext(), swipeListProduct);
        tVisitPlanRealisasiData data = new tVisitPlanRealisasiBL().getDataCheckinActive();

        lvVisitPlan.setAdapter(mAdapter);
        lvVisitPlan.setEmptyView(v.findViewById(R.id.LayoutEmpty));
        if (data.get_txtDataIDRealisasi() != null){
            lvVisitPlan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    new clsMainActivity().showCustomToast(getContext(), "to open, please checkout", false);
                }
            });
        }else{
            lvVisitPlan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                new clsMainActivity().showCustomToast(getContext(), "You click id : " + id +" Posisi : "+position, true);
                    Bundle data = new Bundle();
                    data.putString( ID_REALISASI , hmIdRealisasi.get(position).toString());
                    FragmentVisitPlan fragmentVisitPlan = new FragmentVisitPlan();
                    fragmentVisitPlan.setArguments(data);
                    FragmentTransaction fragmentTransaction = getParentFragment().getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragmentVisitPlan);
                    fragmentTransaction.commit();
                }
            });
        }

    }
}
