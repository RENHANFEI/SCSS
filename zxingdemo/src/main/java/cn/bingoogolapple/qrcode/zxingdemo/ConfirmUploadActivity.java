package cn.bingoogolapple.qrcode.zxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfirmUploadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_upload);
    }

    public void onClick_confirm(View v) {
        Intent myIntent = new Intent(this, UploadResultActivity.class);
        startActivity(myIntent);

    }

    public void onClick_adjust(View v) {
        Intent myIntent = new Intent(this, AdjustLocationActivity.class);
        startActivity(myIntent);
    }

    public void onClick_takePhoto(View v) {
        Intent myIntent = new Intent(this, PhotoActivity.class);
        startActivity(myIntent);
    }

    public void onClick_back(View v) {
        finish();
    }
}
