package cn.bingoogolapple.qrcode.zxingdemo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhotoActivity extends FragmentActivity {

    final int TAKE_PICTURE = 1;

    String uid;
    String lightHead;
    String deviceType;
    String lat;
    String lng;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //请求窗口特性：无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //添加窗口特性：全屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        // setContentView(photoPreview);
        setContentView(R.layout.activity_photo);

        // get intent extra
        Intent myIntent = getIntent();
        uid = myIntent.getStringExtra("uid");
        lightHead = myIntent.getStringExtra("lightHead");
        deviceType = myIntent.getStringExtra("deviceType");
        lat = myIntent.getStringExtra("lat");
        lng = myIntent.getStringExtra("lng");
        address = myIntent.getStringExtra("address");

    }

    public void onClick_take(View v) {
        Intent myIntent = new Intent(this, ConfirmPhotoActivity.class);

        myIntent.putExtra("deviceType",deviceType);
        myIntent.putExtra("uid",uid);
        myIntent.putExtra("lightHead",lightHead);
        myIntent.putExtra("lat",lat);
        myIntent.putExtra("lng",lng);
        myIntent.putExtra("address",address);

        startActivity(myIntent);

    }

    public void onClick_back(View v) {
        finish();

    }


}