package com.kalbenutritionals.app.kalbespgmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.util.List;

import library.salesforce.dal.clsHardCode;

/**
 * Created by XSIS on 20/03/2017.
 */

public class PdfView extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener{

    private static final String TAG = PdfView.class.getSimpleName();
    File logFolder = new File(new clsHardCode().txtPathUserData);
    File file = new File(logFolder, "/sample.pdf");
    PDFView pdfView;
    Toolbar toolbar;

    File pdfName = file;

    Integer pageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        toolbar = (Toolbar) findViewById(R.id.toolbarPdf);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("PDF View");

        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        pdfView = (PDFView)findViewById(R.id.pdfViewtwo);

        display(file);

    }
    private void display(File assetFileName){
        pdfName = assetFileName;
        pdfView.fromFile(file).defaultPage(pageNumber).onPageChange(this).swipeHorizontal(true)
                .scrollHandle(new DefaultScrollHandle(this)).load();
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarkTree(pdfView.getTableOfContents(), "-");
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfName, page + 1, pageCount));
    }

    public void printBookmarkTree(List<PdfDocument.Bookmark> tree, String sep){
        for (PdfDocument.Bookmark b : tree){
            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));
            if (b.hasChildren()){
                printBookmarkTree(b.getChildren(), sep + "-");
            }
        }
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
