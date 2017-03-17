package com.kalbenutritionals.app.kalbespgmobile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import bl.mTypeLeaveBL;
import bl.tAbsenUserBL;
import bl.tDeviceInfoUserBL;
import bl.tLeaveMobileBL;
import library.salesforce.common.mTypeLeaveMobileData;
import library.salesforce.common.tDeviceInfoUserData;
import library.salesforce.common.tLeaveMobileData;
import library.salesforce.common.tUserLoginData;

/**
 * Created by ASUS ZE on 08/08/2016.
 */
public class FragmentAddLeave extends Fragment implements View.OnClickListener {


    private TextView date;
    private TextView tvStatus;
    private Spinner spLeave;
    private List<String> arrData;
    private Button btnSaveleave;
    EditText edReason;
    private String typeAlasan;
    private String typeAlasanName;
    ArrayAdapter<String> dataAdapter ;
    private HashMap<String,String> HMLeave1 = new HashMap<String, String>();
    private HashMap<String,String> HMLeave2 = new HashMap<String, String>();
    private List<String> arrLeave;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_leave_add, container, false);

        tvStatus=(TextView)view.findViewById(R.id.tvPinBbm);
        spLeave = (Spinner) view.findViewById(R.id.spnTypeLeave);
        btnSaveleave = (Button) view.findViewById(R.id.btnSaveLeave);
        edReason  = (EditText) view.findViewById(R.id.editTextReason);
        date = (TextView) view.findViewById(R.id.textViewDateTime);

        String timeStamp = new SimpleDateFormat("dd/MM/yyyy",
                Locale.getDefault()).format(new Date());
        date.setText("Date " + timeStamp);

        List<mTypeLeaveMobileData> listDataLeave = new mTypeLeaveBL().GetAllData();
        arrData=new ArrayList<String>();
        if(listDataLeave!=null){
            for(mTypeLeaveMobileData dt :listDataLeave ){
                arrData.add(dt.get_txtTipeLeaveName());
                String idname = String.valueOf(dt.get_txtTipeLeaveName());
                HMLeave1.put(dt.get_txtTipeLeaveName(),dt.get_intTipeLeave());
                HMLeave2.put(dt.get_txtTipeLeaveName(),idname);
            }
            spLeave.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));
        }

        tvStatus.setText("Status : Open");

        btnSaveleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edReason.getText().toString().equals("")){
                    Toast.makeText(getContext(), "please fill reason!!!", Toast.LENGTH_SHORT).show();
                } else {

                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    final View promptView = layoutInflater.inflate(R.layout.confirm_data, null);

                    final TextView _tvConfirm = (TextView) promptView.findViewById(R.id.tvTitle);
                    final TextView _tvDesc = (TextView) promptView.findViewById(R.id.tvDesc);
                    _tvDesc.setVisibility(View.INVISIBLE);
                    _tvConfirm.setText("Are you sure to Leave ?");

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setView(promptView);
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                                String idname = spLeave.getSelectedItem().toString();
                                                //String nameid = spLeave.getSelectedItem().toString();
                                                String idleave = HMLeave1.get(idname);
                                                String nameleave = HMLeave2.get(idname);
                                                //String nameLeave = HMLeave1.get(String.valueOf(value));
                                                tLeaveMobileData dataTypeLeaveMobileData = new tLeaveMobileData();
                                                List<tLeaveMobileData> leaveUserdatas = new ArrayList<tLeaveMobileData>();
                                                List<tDeviceInfoUserData> dataDeviceInfoUser = new tDeviceInfoUserBL().getData(1);
                                                String deviceInfo = String.valueOf(dataDeviceInfoUser.get(0).get_txtDeviceId());
                                                tUserLoginData dataUserActive = new tAbsenUserBL().getUserActive();
                                                String idUserActive = String.valueOf(dataUserActive.get_TxtEmpId());
                                                String idRoleActive = String.valueOf(dataUserActive.get_txtRoleId());
                                                int index = new tLeaveMobileBL().getContactsCount() + 1;
                                                dataTypeLeaveMobileData.set_intLeaveId(String.valueOf(index));
                                                dataTypeLeaveMobileData.set_intLeaveIdSync("0");
                                                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                                Calendar cal = Calendar.getInstance();
                                                dataTypeLeaveMobileData.set_dtLeave(dateFormat.format(cal.getTime()));
                                                dataTypeLeaveMobileData.set_intSubmit("1");
                                                dataTypeLeaveMobileData.set_txtAlasan(String.valueOf(edReason.getText()));
                                                dataTypeLeaveMobileData.set_txtDeviceId(deviceInfo);
                                                dataTypeLeaveMobileData.set_txtTypeAlasan(idleave);
                                                dataTypeLeaveMobileData.set_txtTypeAlasanName(nameleave);
                                                dataTypeLeaveMobileData.set_txtUserId(idUserActive);
                                                dataTypeLeaveMobileData.set_txtRoleId(idRoleActive);
                                                leaveUserdatas.add(dataTypeLeaveMobileData);
                                                new tLeaveMobileBL().saveData(leaveUserdatas);
                                                Intent nextScreen = new Intent(getContext(), MainMenu.class);
                                                startActivity(nextScreen);

                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    final AlertDialog alertD = alertDialogBuilder.create();
                    alertD.show();
                }
            }
        });

        tLeaveMobileBL _tLeaveMobileBL=new tLeaveMobileBL();
        List<tLeaveMobileData> listData=_tLeaveMobileBL.getData("");
        if(listData.size()>0){
            date.setText(listData.get(0).get_dtLeave());
            List<String> arrData=new ArrayList<>();
            arrData.add(0, listData.get(0).get_txtTypeAlasanName());
            spLeave.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrData));

            edReason.setText(listData.get(0).get_txtAlasan());
            edReason.setTextColor(Color.BLACK);
            edReason.setEnabled(false);
            spLeave.setEnabled(false);
            if(!listData.get(0).get_intLeaveIdSync().equals("0")){
                tvStatus.setText("Status : Sync");
            } else {
                tvStatus.setText("Status : Submit");
            }
            btnSaveleave.setVisibility(View.INVISIBLE);
        }

         return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btnSaveLeave :
                if(edReason.getText().equals("")){
                    new  clsMainActivity().showCustomToast(getContext(), "please fill reason!!!", false);
                }

            break;
        }

    }

    public class MyAdapter extends ArrayAdapter<String>
    {
        private List<String> arrayDataAdapyter;
        private Context Ctx;

        public List<String> getArrayDataAdapyter() {
            return arrayDataAdapyter;
        }
        public void setArrayDataAdapyter(List<String> arrayDataAdapyter) {
            this.arrayDataAdapyter = arrayDataAdapyter;
        }
        public Context getCtx() {
            return Ctx;
        }
        public void setCtx(Context ctx) {
            Ctx = ctx;
        }
        public MyAdapter(Context context, int textViewResourceId, List<String> objects)
        {
            super(context, textViewResourceId, objects);
            setCtx(context);
            setArrayDataAdapyter(objects);
            // TODO Auto-generated constructor stub
        }
        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent)
        {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater=getActivity().getLayoutInflater();
            View row=inflater.inflate(R.layout.custom_spinner, parent, false);
            if(getArrayDataAdapyter().size()>0){
                TextView label=(TextView)row.findViewById(R.id.tvTitle);
                //label.setText(arrData.get(position));
                label.setText(getArrayDataAdapyter().get(position));
                label.setTextColor(new Color().parseColor("#000000"));
                TextView sub=(TextView)row.findViewById(R.id.tvDesc);
                sub.setVisibility(View.INVISIBLE);
                sub.setVisibility(View.GONE);
                row.setBackgroundColor(new Color().parseColor("#FFFFFF"));
            }
            //sub.setText(mydata2[position]);
            return row;
        }

    }
}
