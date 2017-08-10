package com.kalbenutritionals.app.kalbespgmobile;

import android.graphics.Bitmap;
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
import android.widget.AbsListView;

import com.daimajia.slider.library.SliderLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.AppAdapterViewCusBase;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import library.spgmobile.common.clsSwipeList;
import library.spgmobile.common.tActivityData;

/**
 * Created by aan.junianto on 10/08/2017.
 */

public class FragmentViewPlanogram extends Fragment implements IXListViewListener{

    private List<clsSwipeList> swipeList = new ArrayList<clsSwipeList>();
    private AppAdapterViewCusBase mAdapter;

    private PullToRefreshSwipeMenuListView mListView2;

    private Map<String, HashMap> mapMenu;
    private SliderLayout mDemoSlider;

    private List<tActivityData> dt;

    private Bitmap mybitmap1;
    private Bitmap mybitmap2;

    private FloatingActionButton fab;
    Handler mHandler;

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_view_global, container, false);

        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                toolbar.setTitle("Add Actvity SPG");

                FragmentAddActvitySPG fragmentAddActvitySPG = new FragmentAddActvitySPG();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragmentAddActvitySPG);
                fragmentTransaction.commit();
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

    private void loadData() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
