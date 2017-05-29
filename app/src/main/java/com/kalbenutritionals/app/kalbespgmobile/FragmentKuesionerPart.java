package com.kalbenutritionals.app.kalbespgmobile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.jawabanModel;


@SuppressLint("ValidFragment")
public class FragmentKuesionerPart extends Fragment {

    int noSoal;
    String soal, kategori;
    int typeJawaban;
    List<jawabanModel> _jawabanModel;
    ListAdapter myAdapter;
    TextView textView;

    public FragmentKuesionerPart(String kategori, int noSoal, String soal, int typeJawaban, List<jawabanModel> _jawabanModel) {
        this.noSoal = noSoal;
        this.soal = soal;
        this.typeJawaban = typeJawaban;
        this._jawabanModel = _jawabanModel;
        this.kategori = kategori;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kuesioner_part, container, false);

        LinearLayout llMain = (LinearLayout) v.findViewById(R.id.llMain);
        textView = (TextView) v.findViewById(R.id.tv_quizzz);
        textView.setText(kategori);
        TextView txtSoal = (TextView) v.findViewById(R.id.txtSoal);
        txtSoal.setText(soal);

        View vw = null;

        if (typeJawaban == 1) {
            ArrayList<String> spinnerArray = new ArrayList<String>();

            for (int i = 0; i < _jawabanModel.size(); i++) {
                spinnerArray.add(_jawabanModel.get(i).getKey());
            }
            spinnerArray.add("Select One");
            final int listsize = spinnerArray.size() - 1;
            Spinner spinner = new Spinner(getContext());
            spinner.setId(noSoal);
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerArray){
                @Override
                public int getCount() {
                    return listsize;
                }

            };
            spinner.setAdapter(spinnerArrayAdapter);
            spinner.setSelection(listsize);


            llMain.addView(spinner);
        } else if (typeJawaban == 2) {
            ArrayList<String> lvArray = new ArrayList<String>();

            for (int i = 0; i < _jawabanModel.size(); i++) {
                lvArray.add(_jawabanModel.get(i).getKey());
            }
            ListView.LayoutParams layoutParams = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ListView.LayoutParams.MATCH_PARENT);
            ListView listView = new ListView(getContext());
            listView.setLayoutParams(layoutParams);
            listView.setId(noSoal);
            listView.setAdapter(new FragmentKuesionerPart.ListitemAdapter(getContext(), _jawabanModel));
            setListViewHeightBasedOnItems(listView);
            llMain.addView(listView);
        } else if (typeJawaban == 3) {
            EditText etTest = new EditText(getContext());
            etTest.setText("");
            etTest.setHint("Please fill...");
            etTest.setId(noSoal);
            llMain.addView(etTest);
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
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout linearLayout= new LinearLayout(getContext());
            linearLayout.setId(noSoal);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            llMain.addView(linearLayout);

            SeekBar seekBar = new SeekBar(getContext());
            seekBar.setMax(100);
            seekBar.setMinimumWidth(200);
            seekBar.setProgress(0);
            seekBar.setId(linearLayout.getId() * 33);
            linearLayout.addView(seekBar);

            final TextView tv = new TextView(getContext());
            tv.setId(linearLayout.getId() * 200);
            tv.setGravity(Gravity.CENTER);
            tv.setText("Sliding of the blue pointer");
            linearLayout.addView(tv);

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
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
    public class ListitemAdapter extends BaseAdapter {
        public List<jawabanModel> mDisplayedValues;
        public ListitemAdapter(Context context, List<jawabanModel> _jawabanModels) {
            this.mDisplayedValues = _jawabanModels;
        }
        @Override
        public int getCount() {
            return mDisplayedValues.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        private class ViewHolder{
            CheckBox checkBox;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FragmentKuesionerPart.ListitemAdapter.ViewHolder holder = null;

            if (convertView == null){
                LayoutInflater li = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = li.inflate(R.layout.list_item, null);
                holder = new FragmentKuesionerPart.ListitemAdapter.ViewHolder();
                holder.checkBox = (CheckBox) convertView.findViewById(R.id.cbBox);
                convertView.setTag(holder);
            } else {
                holder = (FragmentKuesionerPart.ListitemAdapter.ViewHolder) convertView.getTag();
            }
            final jawabanModel state = mDisplayedValues.get(position);
            holder.checkBox.setText(state.getKey());
            holder.checkBox.setTag(state);
            return convertView;
        }}
}
