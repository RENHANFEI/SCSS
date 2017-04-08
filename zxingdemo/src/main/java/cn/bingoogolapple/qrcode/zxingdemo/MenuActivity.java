package cn.bingoogolapple.qrcode.zxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClick_scan(View v) {
        Intent myIntent = new Intent(this, TestScanActivity.class);
        startActivity(myIntent);
    }

    public void onClick_upload(View v) {
        Intent myIntent = new Intent(this, LocalDataActivity.class);
        startActivity(myIntent);
    }
}
