package com.kalbenutritionals.app.kalbespgmobile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.jawabanModel;


@SuppressLint("ValidFragment")
public class FragmentKuesionerPart extends Fragment {

    int noSoal;
    String soal;
    int typeJawaban;
    List<jawabanModel> _jawabanModel;

    public FragmentKuesionerPart(int noSoal, String soal, int typeJawaban, List<jawabanModel> _jawabanModel) {
        this.noSoal = noSoal;
        this.soal = soal;
        this.typeJawaban = typeJawaban;
        this._jawabanModel = _jawabanModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kuesioner_part, container, false);

        LinearLayout llMain = (LinearLayout) v.findViewById(R.id.llMain);
        TextView txtSoal = (TextView) v.findViewById(R.id.txtSoal);
        txtSoal.setText(soal);

        View vw = null;

        if (typeJawaban == 1) {
            EditText etTest = new EditText(getContext());
            etTest.setText("");
            etTest.setId(noSoal);
            llMain.addView(etTest);
        } else if (typeJawaban == 2) {
            for (int i = 0; i < _jawabanModel.size(); i++) {
                CheckBox cb = new CheckBox(getContext());
                cb.setText(_jawabanModel.get(i).getKey());
                cb.setId((noSoal * 100) + i);
                llMain.addView(cb);}
        } else if (typeJawaban == 3) {
            ArrayList<String> spinnerArray = new ArrayList<String>();

            for (int i = 0; i < _jawabanModel.size(); i++) {
                spinnerArray.add(_jawabanModel.get(i).getKey());
            }

            Spinner spinner = new Spinner(getContext());
            spinner.setId(noSoal);
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerArray);
            spinner.setAdapter(spinnerArrayAdapter);

            llMain.addView(spinner);
        } else if (typeJawaban == 4) {
                RadioButton[] rb = new RadioButton[_jawabanModel.size()];
                RadioGroup rg = new RadioGroup(getContext()); //create the RadioGroup
                rg.setOrientation(LinearLayout.VERTICAL);
                rg.setId(noSoal);
                for (int i = 0; i < _jawabanModel.size(); i++) {
                    rb[i] = new RadioButton(getContext());
                    rb[i].setText(_jawabanModel.get(i).getKey());
                    rb[i].setId(i * noSoal + 1000);
                    rg.addView(rb[i]);
                }
                llMain.addView(rg);
        } else if(typeJawaban == 5){
            SeekBar seekBar = new SeekBar(getContext());
            seekBar.setMax(100);
            seekBar.setMinimumWidth(200);
            seekBar.setProgress(50);
            seekBar.setId(noSoal);
            llMain.addView(seekBar);

            final TextView tv = new TextView(getContext());
            tv.setId(noSoal * 200);
            tv.setGravity(Gravity.CENTER);
            tv.setText("50");

            llMain.addView(tv);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    tv.setText(String.valueOf(i));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }

        return v;
    }

}
