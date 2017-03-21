package com.kalbenutritionals.app.kalbespgmobile;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;

/**
 * Created by XSIS on 21/03/2017.
 */

public class FragmentPO extends Fragment implements IXListViewListener{
    View view;
    private FloatingActionButton fab;
    private Handler mHandler;
    private PullToRefreshSwipeMenuListView menuListView;
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customerbase_view, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
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
        return view;
    }


    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
               menuListView.stopRefresh();
                menuListView.stopLoadMore();
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
        menuListView.setRefreshTime(RefreshTime.getRefreshTime(getActivity().getApplicationContext()));
        menuListView.stopRefresh();
        menuListView.stopLoadMore();
    }
}
