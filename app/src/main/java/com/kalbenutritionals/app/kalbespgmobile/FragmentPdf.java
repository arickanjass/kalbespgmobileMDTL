package com.kalbenutritionals.app.kalbespgmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kalbenutritionals.app.kalbespgmobile.Utils.PdfView;
import com.kalbenutritionals.app.kalbespgmobile.R;

/**
 * Created by XSIS on 20/03/2017.
 */

public class FragmentPdf extends Fragment{
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pdf, container, false);
        Button button = (Button)view.findViewById(R.id.btn_pdf);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PdfView.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
