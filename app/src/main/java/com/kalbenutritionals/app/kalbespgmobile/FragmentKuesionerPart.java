package com.kalbenutritionals.app.kalbespgmobile;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AndroidException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import bl.mEmployeeSalesProductBL;
import bl.tAbsenUserBL;
import bl.tHirarkiBISBL;
import library.spgmobile.common.jawabanModel;
import library.spgmobile.common.mEmployeeSalesProductData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tHirarkiBIS;
import library.spgmobile.dal.clsHardCode;


@SuppressLint("ValidFragment")
public class FragmentKuesionerPart extends Fragment {

    int noSoal;
    String soal, kategori;
    int typeJawaban;
    String questionId;
    List<jawabanModel> _jawabanModel;
    ListAdapter myAdapter;
    TextView textView, tvFile, tvImg, tvPathFile;
    Calendar calendar;
    EditText dateView;
    private int year,month, day;
    DatePickerDialog dialog;
    private static final int PICK_IMAGE_ID = 1993;
    private static final int PICK_FILE_ID = 98;
    ImageView imageView;
    final HashMap<String, String> HMProduct = new HashMap<String, String>();
    private Uri uriImage;
    private static byte[] phtQuiz;
    clsMainActivity _clsMainActivity = new clsMainActivity();
    private static final String IMAGE_DIRECTORY_NAME = "Image Quiz";
    public FragmentKuesionerPart(String questionId,int noSoal, String soal, int typeJawaban, List<jawabanModel> _jawabanModel) {
        this.questionId = questionId;
        this.noSoal = noSoal;
        this.soal = soal;
        this.typeJawaban = typeJawaban;
        this._jawabanModel = _jawabanModel;
        this.kategori = kategori;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kuesioner_part, container, false);

        LinearLayout llMain = (LinearLayout) v.findViewById(R.id.llMain);
//        textView = (TextView) v.findViewById(R.id.tv_quizzz);
//        textView.setText(kategori);
        TextView txtSoal = (TextView) v.findViewById(R.id.txtSoal);
        txtSoal.setText(soal);

        View vw = null;

        if (typeJawaban == 1) {
            ArrayList<String> spinnerArray = new ArrayList<String>();

            if(_jawabanModel.get(0).getValue().equals("SPG01")){
                tAbsenUserData dataOutletCheckIn = new tAbsenUserBL().getDataCheckInActive();
                List<tHirarkiBIS> bisList = new tHirarkiBISBL().GetDataByOutletSpinner(dataOutletCheckIn.get_txtOutletCode(), questionId);
                List<tHirarkiBIS> listSPG = new tHirarkiBISBL().GetDataByOutlet(dataOutletCheckIn.get_txtOutletCode());
                if (bisList.size()>0 && listSPG.size() > 0){
                    for (tHirarkiBIS dt : listSPG) {
                        for (tHirarkiBIS dat : bisList){
                            if (!dt.get_txtNik().equals(dat.get_txtNik())){
                                spinnerArray.add(dt.get_txtName());
                                HMProduct.put(dt.get_txtName(), dt.get_txtNik());
                            }
                        }
                    }
                } else if (listSPG.size() > 0) {
                    for (tHirarkiBIS dt : listSPG) {
                        spinnerArray.add(dt.get_txtName());
                        HMProduct.put(dt.get_txtName(), dt.get_txtNik());
                    }
                }
            }else if (_jawabanModel.get(0).getValue().equals("CUS01")){
                List<mEmployeeSalesProductData> listDataProductKalbe = new mEmployeeSalesProductBL().GetAllData();
                if (listDataProductKalbe.size() > 0) {
                    for (mEmployeeSalesProductData dt : listDataProductKalbe) {
                        spinnerArray.add(dt.get_txtProductBrandDetailGramName());
                        HMProduct.put(dt.get_txtProductBrandDetailGramName(), dt.get_txtBrandDetailGramCode());
                        HMProduct.put(dt.get_txtBrandDetailGramCode(), dt.get_txtBrandDetailGramCode());
                    }
                }
            } else {
                for (int i = 0; i < _jawabanModel.size(); i++) {
                    spinnerArray.add(_jawabanModel.get(i).getKey());
                }
            }

            if (spinnerArray.size() ==0 ){
                spinnerArray.add("You Have Finished This Section");
                spinnerArray.add("You Have Finished This Section");
            } else {
                spinnerArray.add("Select One");
            }
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
            final EditText etTest = new EditText(getContext());
            etTest.setText("");
            etTest.setHint("Please fill...");
            etTest.setId(noSoal);
            llMain.addView(etTest);
            final EditText editText = (EditText)v.findViewById(etTest.getId());
            InputFilter filter = new InputFilter() {
                boolean canEnterSpace = false;

                public CharSequence filter(CharSequence source, int start, int end,
                                           Spanned dest, int dstart, int dend) {

                    if (editText.getText().toString().equals("")) {
                        canEnterSpace = false;
                    }

                    StringBuilder builder = new StringBuilder();

                    for (int i = start; i < end; i++) {
                        char currentChar = source.charAt(i);

                        if (Character.isLetterOrDigit(currentChar) || currentChar == '_') {
                            builder.append(currentChar);
                            canEnterSpace = true;
                        }

                        if (Character.isWhitespace(currentChar) && canEnterSpace) {
                            builder.append(currentChar);
                        }

                    }
                    if (canEnterSpace)
                        return null;
                    else {
                        if (source instanceof Spanned) {
                            SpannableString sp = new SpannableString(builder);
                            TextUtils.copySpansFrom((Spanned) source, start, end, null, sp, 0);
                            return sp;
                        } else {
                            return builder.toString();
                        }
                    }

                }

            };

            editText.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(490)});

        } else if (typeJawaban == 6) {
            RadioButton[] rb = new RadioButton[_jawabanModel.size()];
            RadioGroup rg = new RadioGroup(getContext()); //create the RadioGroup
            rg.setOrientation(LinearLayout.VERTICAL);
            rg.setId(noSoal);
            for (int i = 0; i < _jawabanModel.size(); i++) {
                rb[i] = new RadioButton(getContext());
                rb[i].setText(_jawabanModel.get(i).getKey());
                rb[i].setId(i * noSoal + 1007);
                rg.addView(rb[i]);
            }
            llMain.addView(rg);
        } else if (typeJawaban == 7){
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setId(noSoal);
            imageView = new ImageView(getContext());
            imageView.setId(linearLayout.getId()*77);
            imageView.setBackgroundResource(R.drawable.profile);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels/4;
            double width = displayMetrics.widthPixels/(2.5);
            int w = (int) width; 
            LinearLayout.LayoutParams layoutParamImg = new LinearLayout.LayoutParams(w,height);
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.topMargin = 20;
            imageView.setLayoutParams(layoutParamImg);
            tvImg  = new TextView(getContext());
            tvImg.setId(linearLayout.getId()*37);
            tvImg.setTop(100);
            tvImg.setBottom(30);
            tvImg.setLayoutParams(layoutParams);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String path = tvImg.getText().toString();
                    ImagePick.getPathForPickImage(path);
                    Intent imagePick = ImagePick.getPickImageIntent(getContext());
                    startActivityForResult(imagePick, PICK_IMAGE_ID);
                }
            });
            linearLayout.addView(imageView);
            linearLayout.addView(tvImg);
            llMain.addView(linearLayout);

        }else if(typeJawaban == 5){
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
        }else if(typeJawaban == 8){
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setId(noSoal);
            tvFile = new TextView(getContext());
            tvFile.setId(linearLayout.getId()*37);
            tvFile.setText("no file choosen");
            tvFile.setGravity(Gravity.CENTER_HORIZONTAL);
            tvFile.setLayoutParams(layoutParams);
            Button button = new Button(getContext());
            button.setId(linearLayout.getId()*88);
            button.setText("Choose File");
            button.setLayoutParams(layoutParams);
            tvPathFile = new TextView(getContext());
            tvPathFile.setId(linearLayout.getId()*63);
            tvPathFile.setLayoutParams(layoutParams);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    pickIntent.addCategory(Intent.CATEGORY_OPENABLE);
//                    pickIntent.setType("application/x-compressed-zip");
                    pickIntent.setType("application/*");
                    String[] mimetypes = {"application/pdf" , "application/msword" , "application/vnd.ms-excel"};
                    pickIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                    startActivityForResult(pickIntent, PICK_FILE_ID);
                }
            });
            linearLayout.addView(tvFile);
            linearLayout.addView(button);
            linearLayout.addView(tvPathFile);
            llMain.addView(linearLayout);
        }
        else if (typeJawaban == 4){
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setId(noSoal);
            dateView = new EditText(getContext());
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels/22;
            int width = displayMetrics.widthPixels/13;
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(width, height);
            layoutParams1.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
            dateView.setId(linearLayout.getId() * 145);
            dateView.setEnabled(false);
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.ic_cal);
            imageView.setId(1045*noSoal);
            imageView.setLayoutParams(layoutParams1);
            dateView.setLayoutParams(layoutParams);
            calendar= Calendar.getInstance();
            year=calendar.get(Calendar.YEAR);
            month=calendar.get(Calendar.MONTH);
            day=calendar.get(Calendar.DAY_OF_MONTH);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateView.setHint(simpleDateFormat.format(calendar.getTime()));
//            showDate(year,month,day);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        //tambah style baru
                        dialog = new DatePickerDialog(getContext(), R.style.style_date_picker_dialog, myDateListener, year, month, day);
                    } else {
                        dialog = new DatePickerDialog(getContext(),  myDateListener, year, month, day);
                    }
                    dialog.setTitle("Select Date");
                    dialog.show();
                }
            });

            linearLayout.addView(dateView);
            linearLayout.addView(imageView);
            llMain.addView(linearLayout);
//            llMain.addView(dateView);
        }

        return v;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
//                    showDate(arg1, arg2, arg3);
                    year = arg1;
                    month = arg2;
                    day = arg3;
                    displayDate();
                    dateView.setEnabled(true);
                }
            };
    private void displayDate(){
        GregorianCalendar c = new GregorianCalendar(year, month, day);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateView.setText(simpleDateFormat.format(c.getTime()));
    }

    private void showDate(int year, int month, int day) {
        dateView.setHint(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case PICK_IMAGE_ID:
                if (resultCode == -1) {
                        try {
                            Bitmap bitmap = ImagePick.getImageFromResult(getContext(), resultCode, data);
                            if (bitmap != null){
                                tvImg.setText(ImagePick.getImagePath());
                                tvImg.setVisibility(View.INVISIBLE);
                                previewCapturedImage1(bitmap);
                            } else {
                                _clsMainActivity.showCustomToast(getContext(), "Something error", false);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (resultCode == 0) {
                        _clsMainActivity.showCustomToast(getContext(), "User canceled photo", false);
//                    imageView.setBackgroundResource(R.drawable.profile);
                    } else {
                        _clsMainActivity.showCustomToast(getContext(), "Something error", false);
//                    imageView.setBackgroundResource(R.drawable.profile);
                    }
                break;
            case PICK_FILE_ID:
                if (resultCode == -1) {
                    try {
                        String fileName = ImagePick.getFileName(getContext(), resultCode, data);
                        if (fileName.length() > 0){
                            ImagePick.getPathForPickFile(tvPathFile.getText().toString());
                            ImagePick.byteQusionerFile(getContext(), data);
                            tvFile.setText(fileName);
                            tvPathFile.setText(ImagePick.showPathFile());
                            tvPathFile.setVisibility(View.INVISIBLE);
                        } else {
                            Toast.makeText(getContext(), "Invalid File...", Toast.LENGTH_SHORT).show();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }else if (resultCode == 0) {
                    _clsMainActivity.showCustomToast(getContext(), "User canceled take file", false);
                } else {
                    _clsMainActivity.showCustomToast(getContext(), "Something error", false);
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void previewCapturedImage1(Bitmap photo) {
            Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);
            Bitmap imgView = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
            imageView.setImageBitmap(imgView);
            imageView.setBackgroundResource(0);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels/4;
            double width = displayMetrics.widthPixels/(2.5);
            int w = (int) width;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(w,height);
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.topMargin = 20;
            imageView.setLayoutParams(layoutParams);
    }


}
