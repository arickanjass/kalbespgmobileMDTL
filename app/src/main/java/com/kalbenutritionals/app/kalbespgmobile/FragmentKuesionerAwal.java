package com.kalbenutritionals.app.kalbespgmobile;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bl.mKategoriBL;
import bl.mPertanyaanBL;
import library.salesforce.common.mKategoriData;
import library.salesforce.common.mPertanyaanData;

/**
 * Created by XSIS on 17/03/2017.
 */

public class FragmentKuesionerAwal extends Fragment {
    View v;
    Button btn1, btn2;
    List<mKategoriData> kategoriDataList = new ArrayList<>();
    List<mPertanyaanData> pertanyaanDataList = new ArrayList<>();
    List<String> dataPertanyaan;
    private LinearLayout lnBtn;
    final HashMap<String, String> HMPertanyaan = new HashMap<String, String>();
    List<Button> listButton = new ArrayList<Button>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_awal, container, false);
        lnBtn = (LinearLayout) v.findViewById(R.id.ln_quis_btn);
        kategoriDataList = new mKategoriBL().GetAllData();

        for (int i = 0; i < kategoriDataList.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            Button btn = new Button(getContext());
            btn.setId(Integer.parseInt(kategoriDataList.get(i).get_intCategoryId()));
            final int id_ = btn.getId();
            btn.setText(kategoriDataList.get(i).get_txtCategoryName());
//            btn.setBackgroundColor(Color.rgb(70, 80, 90));
            lnBtn.addView(btn, params);
            btn2 = ((Button) v.findViewById(id_));
            listButton.add(btn2);
            final int finalJ = i;
            listButton.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    final int a = listButton.get(finalJ).getId();
                    final int b = 0;
                    pertanyaanDataList = new mPertanyaanBL().GetDataByCategoriId(a);
                    Bundle bundle = new Bundle();
                    String c = pertanyaanDataList.get(b).get_intQuestionId();
                    int d = Integer.parseInt(c) - 1;
                    bundle.putInt("key_view", d);
                    FragmentKuesioner fragmentKuesioner = new FragmentKuesioner();
                    fragmentKuesioner.setArguments(bundle);
                    FragmentTransaction fragmentTransactionkuesioner = getFragmentManager().beginTransaction();
                    fragmentTransactionkuesioner.replace(R.id.frame, fragmentKuesioner);
                    fragmentTransactionkuesioner.commit();
                }
            });
        }
        return v;
    }
}
