package cn.bingoogolapple.qrcode.zxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhotoActivity extends Activity {

    final int TAKE_PICTURE = 1;
    CameraSurfaceView photoPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        photoPreview = new CameraSurfaceView(this);
    }

    public void onClick_take(View v) {
        Intent myIntent = new Intent(this, ConfirmPhotoActivity.class);
        startActivity(myIntent);

    }

    public void onClick_back(View v) {
        finish();

    }


}