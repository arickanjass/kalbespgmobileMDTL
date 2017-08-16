package com.kalbenutritionals.app.kalbespgmobile;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import bl.mPertanyaanBL;
import bl.tGroupQuestionMappingBL;
import library.spgmobile.common.mPertanyaanData;
import library.spgmobile.common.tGroupQuestionMappingData;

/**
 * Created by Dewi Oktaviani on 17/03/2017.
 */

public class FragmentKuesionerAwal extends Fragment {
    View v;
    Button btn1, btn2;
    List<tGroupQuestionMappingData> groupQuestionMappingDataList = new ArrayList<>();
    List<mPertanyaanData> mPertanyaanDataList = new ArrayList<>();
    List<mPertanyaanData> listPertanyaanbyQId = new ArrayList<>();
    private LinearLayout lnBtn;
    List<Button> listButton = new ArrayList<Button>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_awal, container, false);
        lnBtn = (LinearLayout) v.findViewById(R.id.ln_quis_btn);
        groupQuestionMappingDataList = new tGroupQuestionMappingBL().GetAllData();
        String currentFragment = this.getClass().getName();
        int iterator = 0;
        for (int i = 0; i < groupQuestionMappingDataList.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            Button btn = new Button(getContext());
            btn.setId(Integer.parseInt(groupQuestionMappingDataList.get(i).get_intId()));
            final int id_ = btn.getId();
            btn.setText(groupQuestionMappingDataList.get(i).get_txtGroupQuestion());
//            btn.setBackgroundColor(Color.rgb(70, 80, 90));
            lnBtn.addView(btn, params);
            btn2 = ((Button) v.findViewById(id_));
            listButton.add(btn2);
           listPertanyaanbyQId = new mPertanyaanBL().GetDataByGroupQuestion(id_);
            final int h = iterator ;
            mPertanyaanDataList = new mPertanyaanBL().GetDataBYGroupQuestionCheck(Integer.parseInt(groupQuestionMappingDataList.get(i).get_intId()));
            if (mPertanyaanDataList.size() == 0 && groupQuestionMappingDataList.get(i).get_txtRepeatQuestion().equals("Berulang") ){
                listButton.get(i).setVisibility(View.VISIBLE);
            } else if (mPertanyaanDataList.size() == 0 && groupQuestionMappingDataList.get(i).get_txtRepeatQuestion().equals("Sekali Jawab")){
                listButton.get(i).setVisibility(View.GONE);
            }

            listButton.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ImagePick.deleteMediaStorageDirQuiz();
                    Bundle bundle = new Bundle();
//                        bundle.putInt("key_view", h);
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        bundle.putInt("Key_GroupId", id_);
                        FragmentKuesioner fragmentKuesioner = new FragmentKuesioner();
                        fragmentKuesioner.setArguments(bundle);
                        FragmentTransaction fragmentTransactionkuesioner = getFragmentManager().beginTransaction();
                        fragmentTransactionkuesioner.replace(R.id.frame, fragmentKuesioner);
                        fragmentTransactionkuesioner.commit();
                }
            });
            iterator = iterator + listPertanyaanbyQId.size();
        }
        return v;
    }
}
