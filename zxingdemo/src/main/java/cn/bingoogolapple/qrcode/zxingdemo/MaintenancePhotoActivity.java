package cn.bingoogolapple.qrcode.zxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MaintenancePhotoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_photo);
    }


    public void onClick_next(View v) {
        Intent myIntent = new Intent(this, MaintenanceInfoActivity.class);
        startActivity(myIntent);
    }
}
