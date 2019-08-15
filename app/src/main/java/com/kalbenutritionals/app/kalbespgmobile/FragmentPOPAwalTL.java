package com.kalbenutritionals.app.kalbespgmobile;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
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

import bl.mTypePOPStandardBL;
import library.spgmobile.common.mTypePOPStandardData;

/**
 * Created by Dewi Oktaviani on 17/10/2017.
 */

public class FragmentPOPAwalTL extends Fragment {
    View v;
    List<Button> listButton = new ArrayList<Button>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_pop_awal, container, false);
        LinearLayout ln = (LinearLayout) v.findViewById(R.id.ln_pop_awal);
        final List<mTypePOPStandardData> listType = new mTypePOPStandardBL().GetAllData();
        if (listType.size() > 0){
            for (int i = 0; i < listType.size(); i++){
                Button button = new Button(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 0, 10);
                button.setLayoutParams(params);
                button.setId(Integer.parseInt(listType.get(i).get_intId()));
                button.setText(listType.get(i).get_txtType().toUpperCase());
                button.setPadding(15, 10, 15, 10);
                button.setTextSize(14);
                button.setTypeface(button.getTypeface(), Typeface.BOLD);
                button.setTextColor(Color.BLACK);
                button.setBackgroundResource(R.drawable.bg_edtext);
                ln.addView(button);
                final int _id  = button.getId();
                final String name = listType.get(i).get_txtType();
                final String flag = listType.get(i).get_intFlagMandatori();
                final String [] bundleType = {name, flag};
                Button btn = (Button) v.findViewById(_id);
                listButton.add(btn);
                listButton.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        bundle.putStringArray("Key_POPId", bundleType);
//                        bundle.putString("Key_POPId", name);
                        FragmentPOPView fragmentPOPView = new FragmentPOPView();
                        fragmentPOPView.setArguments(bundle);
                        FragmentTransaction fragmentTransactionPOP = getFragmentManager().beginTransaction();
                        fragmentTransactionPOP.replace(R.id.frame, fragmentPOPView);
                        fragmentTransactionPOP.commit();
                    }
                });
            }
        }
        return v;
    }
}
