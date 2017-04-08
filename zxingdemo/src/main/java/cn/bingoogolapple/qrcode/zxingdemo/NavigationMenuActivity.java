package cn.bingoogolapple.qrcode.zxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NavigationMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
    }

    public void onClick_close(View v){
        finish();
    }

    public void onClick_add(View v) {
        Intent myIntent = new Intent(this, TestScanActivity.class);
        startActivity(myIntent);
    }

    public void onClick_maintenance(View v) {
        Intent myIntent = new Intent(this, MaintenanceActivity.class);
        startActivity(myIntent);
    }

    public void onClick_localData(View v) {
        Intent myIntent = new Intent(this, LocalDataActivity.class);
        startActivity(myIntent);
    }

    public void onClick_report(View v) {
        Intent myIntent = new Intent(this, ReportRepairActivity.class);
        startActivity(myIntent);
    }

    public void onClick_overview(View v) {
//        Intent myIntent = new Intent(this, ***.class);
//        startActivity(myIntent);
    }

    public void onClick_task(View v) {
//        Intent myIntent = new Intent(this, ***.class);
//        startActivity(myIntent);
    }


}
