package lib.rocigno.usefulthingslib.CustomViews.Views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import lib.rocigno.usefulthingslib.CustomViews.BottomSheets.CustomBottomSheet;
import lib.rocigno.usefulthingslib.R;
import lib.rocigno.usefulthingslib.Utils.GlideUtil;
import lib.rocigno.usefulthingslib.Utils.ImagesActions;

@SuppressLint("AppCompatCustomView")
public class ButtonAddPhoto extends Button {

    public ButtonAddPhoto(Context context) {
        super(context);
        initAttrs(null);
    }

    public ButtonAddPhoto(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public ButtonAddPhoto(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    int destiny = 0;
    Activity activity;
    int result_camera = 1, result_galery = 2;

    private void initAttrs(AttributeSet attrs){
        if (!this.isInEditMode()) {
            this.activity = (Activity)this.getContext();
        }

        if (attrs != null) {
            TypedArray ta = this.getContext().obtainStyledAttributes(attrs, R.styleable.ButtonAddPhoto);
            this.result_camera = ta.getInteger(R.styleable.ButtonAddPhoto_result_camera, 1);
            this.result_galery = ta.getInteger(R.styleable.ButtonAddPhoto_result_galery, 2);
            this.destiny = ta.getResourceId(R.styleable.ButtonAddPhoto_image_view, 0);
        }

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                (new CustomBottomSheet(activity)).add(R.drawable.ic_add, "Camera", new CustomBottomSheet.onClickAction() {
                    public void onItemSelected() {
                        if (verifiePermissions(activity)) {
                            ImagesActions.showCamera(activity, result_camera);
                        }

                    }
                }).add(R.drawable.ic_close, "Galeria", new CustomBottomSheet.onClickAction() {
                    public void onItemSelected() {
                        if (verifiePermissions(activity)) {
                            ImagesActions.showGalery(activity, result_galery);
                        }

                    }
                }).show();
            }
        });
    }

    public static boolean verifiePermissions(Activity activity) {
        String[] permisions = new String[]{"android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        ArrayList<String> denied = new ArrayList();
        String[] permisionsDenied = permisions;
        int var4 = permisions.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String permision = permisionsDenied[var5];
            if (ContextCompat.checkSelfPermission(activity, permision) == -1) {
                denied.add(permision);
            }
        }

        if (denied.size() > 0) {
            permisionsDenied = new String[denied.size()];
            permisionsDenied = (String[])denied.toArray(permisionsDenied);
            ActivityCompat.requestPermissions(activity, permisionsDenied, 0);
            return false;
        } else {
            return true;
        }
    }

    public void result(int requestCode, int resultCode, Intent data) {
        Uri imageUri = ImagesActions.getImageUri();
        if (resultCode == -1 && requestCode == this.result_camera) {
            this.GuardaImagem(imageUri);
        } else if (resultCode == -1 && requestCode == this.result_galery) {
            Uri selectedImage = this.returnGalery(this.activity, data, 150);
            if (selectedImage != null) {
                this.GuardaImagem(selectedImage);
            }
        }
    }

    private void GuardaImagem(Uri uriBitmap) {
        try {
            GlideUtil.initGlide(activity, uriBitmap, (ImageView) activity.findViewById(destiny));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public Uri returnGalery(Activity activity, Intent data, int MaxDimension) {
        try {
            Uri imageUri = data.getData();

            assert imageUri != null;

            InputStream inputStream = activity.getContentResolver().openInputStream(imageUri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, (Rect) null, options);
            int imageHeight = options.outHeight;
            int imageWidth = options.outWidth;
            if (imageHeight > MaxDimension && imageWidth > MaxDimension) {
                System.gc();
                return imageUri;
            } else {
                Toast.makeText(activity, "Imagem muito pequena, selecione outra.", Toast.LENGTH_SHORT).show();
                return null;
            }
        } catch (FileNotFoundException var9) {
            var9.printStackTrace();
            Toast.makeText(activity, "Você não selecionou uma foto.", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

}
