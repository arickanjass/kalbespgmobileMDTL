package com.kalbenutritionals.app.kalbespgmobile.Utils;

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
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.kalbenutritionals.app.kalbespgmobile.R;

import java.io.BufferedOutputStream;
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
import java.util.UUID;

import library.spgmobile.dal.clsHardCode;

/**
 * Created by Dewi Oktaviani on 10/08/2017.
 */

public class ImagePick {
    private static final int DEFAULT_MIN_WIDTH_QUALITY = 400;        // min pixels
    private static final String TAG = "ImagePicker";
    private static final String TEMP_IMAGE_NAME = "tempImage";
    private static Uri uriImage;
    private static Uri  path, pathFile;
    private static final String IMAGE_DIRECTORY_NAME = "Image Quiz";
    public static int minWidthQuality = DEFAULT_MIN_WIDTH_QUALITY;
    private static byte[] phtQuiz;
    private static byte[] txtFileQuiz;
    private static String fileName;
    static List<byte[]> qzByte = new ArrayList<>();
    public static Intent getPickImageIntent(Context context) {
        Intent chooserIntent = null;

        List<Intent> intentList = new ArrayList<>();
        uriImage = path;
        Intent pickIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoIntent.putExtra("return-data", true);
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        pickIntent.setType("image/*");
        pickIntent.putExtra("return-data", true);
        pickIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
//        intentList = addIntentsToList(context, intentList, pickIntent);
        intentList = addIntentsToList(context, intentList, takePhotoIntent);

        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
                    context.getString(R.string.pick_image_intent_text));

            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }

        return takePhotoIntent;
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
    public static Uri getPathForPickImage(String uri, Context context){
        if (uri.length() > 0){
            path = Uri.parse(uri);
        }else {
            path = getOutputMediaFileUri(context);
        }
        return path;
    }
    public static Uri getPathForPickFile(String uri){
        if (uri.length() > 0){
            pathFile = Uri.parse(uri);
        }else {
            pathFile = getOutputMediaFileUpload(fileName);
        }
        return pathFile;
    }
    public static String showPathFile(){
        String path = null;
        if (pathFile != null){
            path = pathFile.getPath().toString();
        } else {
            path = "";
        }
        return path;
    }
    private static Uri getOutputMediaFileUri(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //use this if Lollipop_Mr1 (API 22) or above
            return FileProvider.getUriForFile(context, context.getPackageName()+".provider", getOutputMediaFile());
        } else {
            return Uri.fromFile(getOutputMediaFile());
        }
//        return Uri.fromFile(getOutputMediaFile());
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
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "tmp_Quiz_" + timeStamp +".png");
        return mediaFile;
    }
    private static String getRealPathFromURI(Uri contentUri, Context mContext) {

        String[] proj = { MediaStore.Images.Media.DATA };
//        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
//        CursorLoader loader = new CursorLoader(mContext, contentUri, proj, null, null, null);
        Cursor returnCursor = mContext.getContentResolver().query(contentUri,null, null, null, null);
        int column_index = returnCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        returnCursor.moveToFirst();
        return returnCursor.getString(column_index);
    }
    public static void deleteMediaStorageDirQuiz (){
        File mediaStorageDir = new File(new clsHardCode().txtFolderQuiz + File.separator);
        if (mediaStorageDir.exists()){
            if (mediaStorageDir.isDirectory()){
                for (File currentFile : mediaStorageDir.listFiles()){
                    currentFile.delete();
                }
            }
            mediaStorageDir.delete();
        }
    }
    public static Uri getOutputMediaFileUpload(String fileName) {
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
        File mediaFile = null;
        if (fileName.length() >0){
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + fileNameWithoutExtension + "_" +timeStamp + fileExtension);

        }
        return  Uri.fromFile(mediaFile);
    }

    private static Bitmap resizeImageForBlob(Bitmap photo){
        int width = photo.getWidth();
        int height = photo.getHeight();

        int maxHeight = 800;
        int maxWidth = 800;

        Bitmap bitmap;

        if(height > width){
            float ratio = (float) height / maxHeight;
            height = maxHeight;
            width = (int)(width / ratio);
        }
        else if(height < width){
            float ratio = (float) width / maxWidth;
            width = maxWidth;
            height = (int)(height / ratio);
        }
        else{
            width = maxWidth;
            height = maxHeight;
        }

        bitmap = Bitmap.createScaledBitmap(photo, width, height, true);

        return bitmap;
    }
    public static byte[] byteQuiz(Bitmap photo){

        try {
            Bitmap bitmap = resizeImageForBlob(photo);
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
    public static String GenerateGuid() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }
    public static byte[] getFile(Uri path, Context mContext) throws FileNotFoundException
    {
        byte[] data = null;

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
        Uri uri = fileReturnedIntent.getData();
        String path = fileReturnedIntent.getData().getPath().toString();
        if (resultCode == Activity.RESULT_OK){
            byte[] byteFile = null;
            try {
                InputStream is = context.getContentResolver().openInputStream(fileReturnedIntent.getData()); // use recorded file instead of getting file from assets folder.
                int length = is.available();
                byteFile = new byte[length];
                int bytesRead;
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                while ((bytesRead = is.read(byteFile)) != -1) {
                    output.write(byteFile, 0, bytesRead);
                }
                txtFileQuiz = output.toByteArray();
            } catch (IOException e) {
                // TODO Auto-generated catch block
               e.printStackTrace();
            }
            if (txtFileQuiz != null){
                if (Build.MANUFACTURER.equals("Xiaomi")){
                    fileName = path.substring(path.lastIndexOf('/')+1, path.length());
                }else {
                    Cursor returnCursor = context.getContentResolver().query(uri,null, null, null, null);

                    int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                    returnCursor.moveToFirst();
                    fileName = returnCursor.getString(nameIndex);
                }

            }else {
                fileName = "";
            }}
        return fileName;
    }
    public static void byteQusionerFile(Context context, Intent fileReturnedIntent){
        InputStream in = null;
        OutputStream out = null;
        Uri uri = fileReturnedIntent.getData();
        try {
                in = context.getContentResolver().openInputStream(uri);
                out = new FileOutputStream(pathFile.getPath().toString());

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
    public static Bitmap getImageFromResult(Context context, int resultCode, Intent imageReturnedIntent) {
        Log.d(TAG, "getImageFromResult, resultCode: " + resultCode);
        Bitmap bm = null;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
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
                    in = context.getContentResolver().openInputStream(imageReturnedIntent.getData());
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
//              bm = BitmapFactory.decodeFile(selectedImage, bitmapOptions);

            try {
                InputStream ims =  context.getContentResolver().openInputStream(uriImage);
                bm = BitmapFactory.decodeStream(ims);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return bm;
    }
    public static String getImagePath(){
        String path = uriImage.getPath().toString();
        return path;
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

    public static Uri getOutputMediaFileUri(Context context, String folderName, String fileName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //use this if Lollipop_Mr1 (API 22) or above
            return FileProvider.getUriForFile(context.getApplicationContext(), context.getApplicationContext().getPackageName()+".provider", getOutputMediaFile(folderName, fileName));
        } else {
            return Uri.fromFile(getOutputMediaFile(folderName, fileName));
        }
    }

    //create path file
    private static File getOutputMediaFile(String folderName, String fileName) {
        // External sdcard location

        File mediaStorageDir = new File(folderName + File.separator);
        mediaStorageDir.mkdir();
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("_yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + fileName);
        return mediaFile;
    }

    public static File decodeByteArraytoTempFile(byte[] fileArray, String pathFolder, String fileExtension) {
        File folder = new File(pathFolder);
        folder.mkdirs();
        File file = null;
        try {
            file = File.createTempFile("file-", fileExtension, new File(pathFolder));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(fileArray);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File decodeByteArraytoFileImage(byte[] imageArray, String pathFolder){
        File folder = new File(pathFolder);
        folder.mkdirs();
        File file = null;
        try {
            file = File.createTempFile("image-", ".jpg", new File(pathFolder));
            FileOutputStream out = new FileOutputStream(file);
            out.write(imageArray);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
