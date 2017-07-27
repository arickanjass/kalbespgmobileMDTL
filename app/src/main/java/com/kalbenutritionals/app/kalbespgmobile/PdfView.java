package com.kalbenutritionals.app.kalbespgmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import bl.clsFileAttach_mobileBL;
import library.spgmobile.common.clsFileAttach_mobile;
import library.spgmobile.dal.clsHardCode;

/**
 * Created by XSIS on 20/03/2017.
 */

public class PdfView extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener{

    private static final String TAG = PdfView.class.getSimpleName();
    File logFolder = new File(new clsHardCode().txtPathUserData + "FileAttach/");
    String pathFolder = new clsHardCode().txtPathUserData + "FileAttach/";
    File fileFolder = null;
    PDFView pdfView;
    Toolbar toolbar;
    clsFileAttach_mobile data = new clsFileAttach_mobile();

//    File pdfName = file;

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

        Intent intent = getIntent();
        String idFile = intent.getStringExtra("idFile");

        if(idFile!=null){
            data = new clsFileAttach_mobileBL().getData(idFile);
        }

        if(data.get_txtIDFile()!=null){
            decryptFile();
        }
    }

    private void decryptFile(){
        String key = "kalbenutritionals";
        clsMainActivity _clsMainActivity = new clsMainActivity();
        File file = new File(pathFolder, data.get_txtNameFileEncrypt());;
        fileFolder = file;

        try {
            if (data.get_intStatus().equals("DOWNLOADED")){
                if(data.get_intActive().equals("0")){
                    byte[] array = _clsMainActivity.getFile(data);
                    byte[] keyInByte = _clsMainActivity.getKeyBytes(key);
                    byte[] arrayFileDecrypt =  _clsMainActivity.decrypt(array, keyInByte, keyInByte);

                    file = _clsMainActivity.saveFile(arrayFileDecrypt, pathFolder, data.get_txtNameFileEncrypt());

                    if (display(file, data)){
                        data.set_intActive("1");
                        data.set_intStatus("READ");

                        List<clsFileAttach_mobile> dt = new ArrayList<>();
                        dt.add(data);

                        new clsFileAttach_mobileBL().saveData(dt);
                    }
                } else if (data.get_intActive().equals("1")){
                    display(fileFolder, data);
                }
            } else if (data.get_intStatus().equals("READ")) {
                if(data.get_intActive().equals("0")){
                    byte[] array = _clsMainActivity.getFile(data);
                    byte[] keyInByte = _clsMainActivity.getKeyBytes(key);
                    byte[] arrayFileDecrypt =  _clsMainActivity.decrypt(array, keyInByte, keyInByte);

                    file = _clsMainActivity.saveFile(arrayFileDecrypt, pathFolder, data.get_txtNameFileEncrypt());

                    if (display(file, data)){
                        data.set_intActive("1");
                        data.set_intStatus("READ");

                        List<clsFileAttach_mobile> dt = new ArrayList<>();
                        dt.add(data);

                        new clsFileAttach_mobileBL().saveData(dt);
                    }
                } else if (data.get_intActive().equals("1")){
                    display(fileFolder, data);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    private void encrypteFile(){
        String key = "kalbenutritionals";
        clsMainActivity _clsMainActivity = new clsMainActivity();
        File file = new File(pathFolder, data.get_txtNameFileEncrypt());;
        fileFolder = file;

        try {
            if (data.get_intActive().equals("1")){
                byte[] array = _clsMainActivity.getFile(data);
                byte[] keyInByte = _clsMainActivity.getKeyBytes(key);
                byte[] arrayFileEncrypt =  _clsMainActivity.encrypt(array, keyInByte, keyInByte);

                file = _clsMainActivity.saveFile(arrayFileEncrypt, pathFolder, data.get_txtNameFileEncrypt());

                data.set_intActive("0");;

                List<clsFileAttach_mobile> dt = new ArrayList<>();
                dt.add(data);

                new clsFileAttach_mobileBL().saveData(dt);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    private boolean display(File assetFileName, clsFileAttach_mobile data){
        fileFolder = assetFileName;
        pdfView.fromFile(fileFolder).defaultPage(pageNumber).onPageChange(this).swipeHorizontal(true)
                .scrollHandle(new DefaultScrollHandle(this)).load();
        new clsMainActivity().saveLogFile(data, "READ");
        return true;
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarkTree(pdfView.getTableOfContents(), "-");
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", fileFolder, page + 1, pageCount));
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
                encrypteFile();
//                fileFolder.delete();
                Intent parentIntent = NavUtils.getParentActivityIntent(this);
                parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(parentIntent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        encrypteFile();
//        fileFolder.delete();
    }

    @Override
    protected void onPause() {
        super.onPause();
        encrypteFile();
    }

    @Override
    protected void onResume() {
        super.onResume();
        decryptFile();
    }

    @Override
    protected void onStop() {
        super.onStop();
        encrypteFile();
    }
}
