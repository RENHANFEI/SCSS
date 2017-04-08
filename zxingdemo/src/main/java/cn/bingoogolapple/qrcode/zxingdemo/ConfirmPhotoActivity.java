package cn.bingoogolapple.qrcode.zxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfirmPhotoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_photo);
    }

    public void onClick_confirm(View v) {
        Intent myIntent = new Intent(this, ConfirmUploadActivity.class);
        startActivity(myIntent);

    }

    public void onClick_back(View v) {
        finish();

    }
}
