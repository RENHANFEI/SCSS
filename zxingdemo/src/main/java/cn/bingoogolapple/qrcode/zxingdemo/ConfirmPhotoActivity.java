package cn.bingoogolapple.qrcode.zxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfirmPhotoActivity extends Activity {

    String uid;
    String lightHead;
    String deviceType;
    String lat;
    String lng;
    String address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_photo);

        Intent myIntent = getIntent();
        uid = myIntent.getStringExtra("uid");
        lightHead = myIntent.getStringExtra("lightHead");
        deviceType = myIntent.getStringExtra("deviceType");
        lat = myIntent.getStringExtra("lat");
        lng = myIntent.getStringExtra("lng");
        address = myIntent.getStringExtra("address");
    }

    public void onClick_confirm(View v) {
        Intent myIntent = new Intent(this, ConfirmUploadActivity.class);

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
