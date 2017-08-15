package com.kalbenutritionals.app.kalbespgmobile;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import library.spgmobile.dal.clsHardCode;

/**
 * Created by Dewi Oktaviani on 10/08/2017.
 */

public class ImagePick {
    private static final int DEFAULT_MIN_WIDTH_QUALITY = 400;        // min pixels
    private static final String TAG = "ImagePicker";
    private static final String TEMP_IMAGE_NAME = "tempImage";
    private static Uri uriImage;
    private static final String IMAGE_DIRECTORY_NAME = "Image Quiz";
    public static int minWidthQuality = DEFAULT_MIN_WIDTH_QUALITY;
    private static byte[] phtQuiz;
    private static byte[] txtFileQuiz;
//    static List<byte[]> qzByte = new ArrayList<>();
    public static Intent getPickImageIntent(Context context) {
        Intent chooserIntent = null;

        List<Intent> intentList = new ArrayList<>();
        uriImage = getOutputMediaFileUri();
        Intent pickIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoIntent.putExtra("return-data", true);
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        pickIntent.setType("image/*");
        pickIntent.putExtra("return-data", true);
        pickIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        intentList = addIntentsToList(context, intentList, pickIntent);
        intentList = addIntentsToList(context, intentList, takePhotoIntent);

        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
                    context.getString(R.string.pick_image_intent_text));

            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }

        return chooserIntent;
    }

    private static List<Intent> addIntentsToList(Context context, List<Intent> list, Intent intent) {
        List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent targetedIntent = new Intent(intent);
            targetedIntent.setPackage(packageName);
            list.add(targetedIntent);
            Log.d(TAG, "Intent: " + intent.getAction() + " package: " + packageName);
        }
        return list;
    }

    private static Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private static File getOutputMediaFile() {
        // External sdcard location

        File mediaStorageDir = new File(new clsHardCode().txtFolderQuiz + File.separator);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "tmp_Quiz_" + timeStamp +".jpg");
        return mediaFile;
    }
    private static String getRealPathFromURI(Uri contentUri, Context mContext) {

        String[] proj = { MediaStore.Video.Media.DATA };
//        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        CursorLoader loader = new CursorLoader(mContext, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    private static Uri getOutputMediaFileUpload(String fileName ) {
        // External sdcard location

        File mediaStorageDir = new File(new clsHardCode().txtFolderQuiz + File.separator);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        // Create a media file name
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + fileName);
        return  Uri.fromFile(mediaFile);
    }
    public static byte[] byteQuiz(Bitmap photo){

        try {
            Bitmap bitmap = new clsMainActivity().resizeImageForBlob(photo);
            ByteArrayOutputStream output = null;
            try {
                output = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, output); // bmp is your Bitmap instance
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            phtQuiz = output.toByteArray();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }return phtQuiz;
    }
    public static List<byte[]> byteQuesioner(){
        List<byte[]> qzByte = new ArrayList<>();
        qzByte.add(phtQuiz);
        return qzByte;
    }
    public static byte[] getFile(Uri path, Context mContext) throws FileNotFoundException
    {
        byte[] data = null;
//        byte[] inarry = null;
        String paths = path.toString();
//        AssetManager am = getAssets();
        try {
            InputStream is = mContext.getContentResolver().openInputStream(path); // use recorded file instead of getting file from assets folder.
            int length = is.available();
            data = new byte[length];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while ((bytesRead = is.read(data)) != -1) {
                output.write(data, 0, bytesRead);
            }
            txtFileQuiz = output.toByteArray();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return txtFileQuiz;

    }
    public static String getFileName(Context context, int resultCode, Intent fileReturnedIntent) throws FileNotFoundException {
        InputStream in = null;
        OutputStream out = null;
        Uri uri = fileReturnedIntent.getData();
        String mimeType = context.getContentResolver().getType(uri);
        Cursor returnCursor = context.getContentResolver().query(uri,null, null, null, null);

        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
        returnCursor.moveToFirst();
        String fileName = null;
        if (resultCode == Activity.RESULT_OK){
            byte[] byteFile = getFile(uri, context);
            if (byteFile.length > 0){
                fileName = returnCursor.getString(nameIndex);
            }else {
                fileName = null;
            }
            try {

                in = context.getContentResolver().openInputStream(uri);
                out = new FileOutputStream(getOutputMediaFileUpload(fileName).getPath().toString());

                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//       String lastFile = getOutputMediaFileUpload().getPath().toString() + fileName;
//        String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
        //        String fileName = name.substring(name.lastIndexOf('/')+1, name.length());
//        String fileExtension = urlStr.substring(urlStr.lastIndexOf("."));
        return fileName;
    }
    public static List<byte[]> byteQusionerFile(){
        List<byte[]> qzByte = new ArrayList<>();
        qzByte.add(txtFileQuiz);
        return qzByte;
    }
    public static Bitmap getImageFromResult(Context context, int resultCode, Intent imageReturnedIntent) {
        Log.d(TAG, "getImageFromResult, resultCode: " + resultCode);
        Bitmap bm = null;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//        String uri = uriImage.getPath().toString();

//        bm = BitmapFactory.decodeFile(uri, bitmapOptions);
        File imageFile = getOutputMediaFile();
        if (resultCode == Activity.RESULT_OK) {
            String selectedImage;
            boolean isCamera = (imageReturnedIntent == null ||
                    imageReturnedIntent.getData() == null  ||
                    imageReturnedIntent.getData().toString().contains(imageFile.toString()));
            if (isCamera) {     /** CAMERA **/
                selectedImage = uriImage.getPath().toString();
            } else {            /** ALBUM **/
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = new FileInputStream(getRealPathFromURI(imageReturnedIntent.getData(), context));
                    out = new FileOutputStream(uriImage.getPath().toString());

                    // Transfer bytes from in to out
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    in.close();
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                selectedImage = uriImage.getPath().toString();
            }
            Log.d(TAG, "selectedImage: " + selectedImage);

//            bm = getImageResized(context, selectedImage);
//            int rotation = getRotation(context, selectedImage, isCamera);
//            bm = rotate(bm, rotation);
              bm = BitmapFactory.decodeFile(selectedImage, bitmapOptions);}
        return bm;
    }
    public static String getImageName (){
        String path = uriImage.getPath().toString();
        String fileName = path.substring(path.lastIndexOf('/')+1, path.length());
        return fileName;
    }

    private static File getTempFile(Context context) {
        File imageFile = new File(context.getExternalCacheDir(), TEMP_IMAGE_NAME);
        imageFile.getParentFile().mkdirs();
        return imageFile;
    }

    private static Bitmap decodeBitmap(Context context, Uri theUri, int sampleSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;

        AssetFileDescriptor fileDescriptor = null;
        try {
            fileDescriptor = context.getContentResolver().openAssetFileDescriptor(theUri, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Bitmap actuallyUsableBitmap = BitmapFactory.decodeFileDescriptor(
                fileDescriptor.getFileDescriptor(), null, options);

        Log.d(TAG, options.inSampleSize + " sample method bitmap ... " +
                actuallyUsableBitmap.getWidth() + " " + actuallyUsableBitmap.getHeight());

        return actuallyUsableBitmap;
    }

    /**
     * Resize to avoid using too much memory loading big images (e.g.: 2560*1920)
     **/
    private static Bitmap getImageResized(Context context, Uri selectedImage) {
        Bitmap bm = null;
        int[] sampleSizes = new int[]{5, 3, 2, 1};
        int i = 0;
        do {
            bm = decodeBitmap(context, selectedImage, sampleSizes[i]);
            Log.d(TAG, "resizer: new bitmap width = " + bm.getWidth());
            i++;
        } while (bm.getWidth() < minWidthQuality && i < sampleSizes.length);
        return bm;
    }


    private static int getRotation(Context context, Uri imageUri, boolean isCamera) {
        int rotation;
        if (isCamera) {
            rotation = getRotationFromCamera(context, imageUri);
        } else {
            rotation = getRotationFromGallery(context, imageUri);
        }
        Log.d(TAG, "Image rotation: " + rotation);
        return rotation;
    }

    private static int getRotationFromCamera(Context context, Uri imageFile) {
        int rotate = 0;
        try {

            context.getContentResolver().notifyChange(imageFile, null);
            ExifInterface exif = new ExifInterface(imageFile.getPath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }

    public static int getRotationFromGallery(Context context, Uri imageUri) {
        int result = 0;
        String[] columns = {MediaStore.Images.Media.ORIENTATION};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(imageUri, columns, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int orientationColumnIndex = cursor.getColumnIndex(columns[0]);
                result = cursor.getInt(orientationColumnIndex);
            }
        } catch (Exception e) {
            //Do nothing
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }//End of try-catch block
        return result;
    }


    private static Bitmap rotate(Bitmap bm, int rotation) {
        if (rotation != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(rotation);
            Bitmap bmOut = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
            return bmOut;
        }
        return bm;
    }
}
