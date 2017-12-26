package com.kalbenutritionals.app.kalbespgmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import addons.tableview.ReportComparators;
import addons.tableview.ReportTableDataAdapter;
import addons.tableview.SortabeReportTableViewDetail;
import bl.mEmployeeSalesProductBL;
import bl.mKategoriBL;
import bl.mListJawabanBL;
import bl.mPertanyaanBL;
import bl.tAbsenUserBL;
import bl.tGroupQuestionMappingBL;
import bl.tHirarkiBISBL;
import bl.tJawabanUserBL;
import bl.tJawabanUserHeaderBL;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import library.spgmobile.common.ReportTable;
import library.spgmobile.common.mEmployeeSalesProductData;
import library.spgmobile.common.mKategoriData;
import library.spgmobile.common.mListJawabanData;
import library.spgmobile.common.mPertanyaanData;
import library.spgmobile.common.tAbsenUserData;
import library.spgmobile.common.tGroupQuestionMappingData;
import library.spgmobile.common.tHirarkiBIS;
import library.spgmobile.common.tJawabanUserData;
import library.spgmobile.common.tJawabanUserHeaderData;

/**
 * Created by Dewi Oktaviani on 05/09/2017.
 */

public class ReportDetailQuiz extends AppCompatActivity {
    View v;
    private TextView tvGroupQuiz, tvOutlet, tvIterasi, tvSum, tvAverage, tvSumSum, tvAvg;
    Toolbar toolbar;
    String[] intHeaderId;
    private SortabeReportTableViewDetail ReportTableView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_quiz_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbarQuiz);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Report Detail Pfc. Tim");

        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        tvGroupQuiz = (TextView)findViewById(R.id.tv2quiz);
        tvOutlet = (TextView) findViewById(R.id.tv4quiz);
        tvIterasi = (TextView) findViewById(R.id.tv1quiz);
        tvSum = (TextView) findViewById(R.id.tv6quiz);
        tvAverage = (TextView) findViewById(R.id.tv7quiz);
        tvSumSum = (TextView) findViewById(R.id.tv55Quis);
        tvAvg = (TextView) findViewById(R.id.tv66Quis);
        ReportTableView = (SortabeReportTableViewDetail) findViewById(R.id.tableViewQuis);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            intHeaderId = bundle.getStringArray("Key_HeaderId");
        }

        tJawabanUserHeaderData dataHeader = new tJawabanUserHeaderBL().GetDataByHeaderId(intHeaderId[0]);
        List<tGroupQuestionMappingData> dt_group = new tGroupQuestionMappingBL().GetDataById(Integer.parseInt(dataHeader.get_intGroupQuestionId()));
        List<tJawabanUserData> dataAnswer = new tJawabanUserBL().GetDataByHeaderId(dataHeader.get_intHeaderId());
        List<mPertanyaanData> dt_Question  = new mPertanyaanBL().GetDataByQuestionId(dataAnswer.get(0).get_intQuestionId());
        mKategoriData kategoriData = new mKategoriBL().GetCategoryById(dt_Question.get(0).get_intCategoryId());
        if(kategoriData.get_intParentId().equals("1")){
            tvAverage.setVisibility(View.GONE);
            tvSum.setVisibility(View.GONE);
            tvSumSum.setVisibility(View.GONE);
            tvAvg.setVisibility(View.GONE);
        } else {
            tvSum .setText(" : " + dataHeader.get_intSum());
            tvAverage.setText(" : " + dataHeader.get_intAverage());
        }
        tvGroupQuiz.setText(" : "+ dt_group.get(0).get_txtGroupQuestion());
        tvOutlet.setText(" : "+ dataHeader.get_txtOutletName());
        tvIterasi.setText(" : " + intHeaderId[1]);
        generateReport(dataHeader);
    }
    private void generateReport(tJawabanUserHeaderData dataHeader) {
        String[] header;
        SimpleTableHeaderAdapter simpleTableHeaderAdapter;
        List<ReportTable> reportList;
        int i;
            header = new String[6];
            header[1] = "Category";
            header[2] = "No. Soal";
            header[3] = "Question";
            header[4] = "Answer";

            ReportTableView.setColumnCount(header.length);

            simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getApplicationContext(), header);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.table_header_text));
            simpleTableHeaderAdapter.setTextSize(14);
            simpleTableHeaderAdapter.setPaddingBottom(20);
            simpleTableHeaderAdapter.setPaddingTop(20);

            ReportTableView.setColumnComparator(1, ReportComparators.getCategoryComparator());
            ReportTableView.setColumnComparator(2, ReportComparators.getRepeatComparator());
            ReportTableView.setColumnComparator(3, ReportComparators.getQuestionComparator());
            ReportTableView.setColumnComparator(4, ReportComparators.getAnswerComparator());


            ReportTableView.setColumnWeight(1, 2);
            ReportTableView.setColumnWeight(2, 2);
            ReportTableView.setColumnWeight(3, 2);
            ReportTableView.setColumnWeight(4, 2);

            ReportTableView.setHeaderAdapter(simpleTableHeaderAdapter);

            List<tJawabanUserData> dt_Answer = new tJawabanUserBL().GetDataByHeaderId(dataHeader.get_intHeaderId());
            reportList = new ArrayList<>();
            if(dt_Answer != null&&dt_Answer.size()>0){
                for(tJawabanUserData data : dt_Answer ){
                    ReportTable rt = new ReportTable();
                    List<mPertanyaanData> dt_Question  = new mPertanyaanBL().GetDataByQuestionId(data.get_intQuestionId());
                    List<tJawabanUserData> dataAnswer = new tJawabanUserBL().GetDataByQuestionId(data.get_intQuestionId(), dataHeader.get_intHeaderId());
                    mKategoriData kategoriData = new mKategoriBL().GetCategoryById(dt_Question.get(0).get_intCategoryId());
                    if(kategoriData.get_intParentId().equals(2)){
                        tvAverage.setVisibility(View.GONE);
                        tvSum.setVisibility(View.GONE);
                    }
                    rt.set_report_type("Kuesioner Detail");
                    rt.set_RepeatQuiz(dt_Question.get(0).get_intSoalId());
                    rt.set_dummy(data.get_intHeaderId());
                    rt.set_Category(kategoriData.get_txtCategoryName());
                    rt.set_Question(dt_Question.get(0).get_txtQuestionDesc());
                    if (data.get_intTypeQuestionId().equals("1") || data.get_intTypeQuestionId().equals("2") || data.get_intTypeQuestionId().equals("6")){
                        String jawab = null;
                        if (dataAnswer != null && dataAnswer.size()>0){
                            for (tJawabanUserData dt : dataAnswer){
                                mListJawabanData answer = new mListJawabanBL().GetDataById(dt.get_intAnswerId());
                                final HashMap<String, String> HMProduct = new HashMap<String, String>();
                                tAbsenUserData dataOutletCheckIn = new tAbsenUserBL().getDataCheckInActive();
                                List<String> dataJawaban = new ArrayList<>();
                                if (answer.get_txtValue().equals("SPG01")){

                                    List<tHirarkiBIS> listSPG = new tHirarkiBISBL().GetDataByOutlet(dataHeader.get_txtOutletCode());
                                    if (listSPG.size() > 0) {
                                        for (tHirarkiBIS dat : listSPG) {
                                            dataJawaban.add(dat.get_txtNik());
                                            HMProduct.put(dat.get_txtNik(), dat.get_txtName());
                                        }
                                    }
                                    String jawaban = HMProduct.get(data.get_txtValue());
                                    rt.set_Answer(jawaban);
                                } else if (answer.get_txtValue().equals("CUS01")){
                                    List<mEmployeeSalesProductData> listDataProductKalbe = new mEmployeeSalesProductBL().GetAllData();
                                    if (listDataProductKalbe.size() > 0) {
                                        for (mEmployeeSalesProductData dat : listDataProductKalbe) {
                                            dataJawaban.add(dat.get_txtBrandDetailGramCode());
                                            HMProduct.put(dat.get_txtBrandDetailGramCode(), dat.get_txtProductBrandDetailGramName());
                                        }
                                    }
                                    String jawaban = HMProduct.get(data.get_txtValue());
                                    rt.set_Answer(jawaban);
                                }else {
                                    if (jawab != null){
                                        jawab += answer.get_txtKey() + " ,";
                                    }else {
                                        jawab = answer.get_txtKey() + " ,";
                                    }
                                    String jawabFinal = jawab.substring(0, jawab.lastIndexOf(',')).trim();
                                    rt.set_Answer(jawabFinal);
                                }
                            }
                        }
                    } else {
                        rt.set_Answer(data.get_txtValue());
                    }
                    rt.set_type(data.get_intTypeQuestionId());
                    reportList.add(rt);
                }
            } else {
                new clsMainActivity().showCustomToast(getApplicationContext(), "No Data to Show", false);
            }

            ReportTableView.setDataAdapter(new ReportTableDataAdapter(getApplicationContext(), reportList));
        }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent parentIntent = NavUtils.getParentActivityIntent(this);
                parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(parentIntent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
