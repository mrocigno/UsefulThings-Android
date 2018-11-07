package lib.rocigno.usefulthingslib.Utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

public class ImagesActions {
    private static Uri imageUri;

    public ImagesActions() {
    }

    public static Uri getImageUri() {
        return imageUri;
    }

    public static void showCamera(Activity activity, int request) {
        ContentValues values = new ContentValues();
        values.put("title", "New Picture");
        values.put("description", "From your Camera");
        imageUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent takePictureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (takePictureIntent.resolveActivity(activity.getBaseContext().getPackageManager()) != null) {
            takePictureIntent.putExtra("output", imageUri);
            activity.startActivityForResult(takePictureIntent, request);
        }

    }

    public static void showGalery(Activity activity, int request) {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        activity.startActivityForResult(intent, request);
    }
}