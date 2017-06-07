package com.kalbenutritionals.app.kalbespgmobile;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;

/**
 * Created by Rian Andrivani on 6/7/2017.
 */

public class FragmentAddKoordinasi extends Fragment implements View.OnClickListener, IXListViewListener {

    private TextView date;
    private TextView tvStatus;
    private Button btnSave;
    EditText keterangan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_koordinasi_add, container, false);

        tvStatus = (TextView)view.findViewById(R.id.tvStatusKoordinasi);
        btnSave = (Button)view.findViewById(R.id.btnSaveKoordinasi);
        keterangan = (EditText)view.findViewById(R.id.editTextKeteranganKoordinasi);
        date = (TextView) view.findViewById(R.id.textViewDateTimeKoordinasi);

        String timeStamp = new SimpleDateFormat("dd/MM/yyyy",
                Locale.getDefault()).format(new Date());
        date.setText("Date " + timeStamp);

        return view;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onClick(View view) {

    }
}
