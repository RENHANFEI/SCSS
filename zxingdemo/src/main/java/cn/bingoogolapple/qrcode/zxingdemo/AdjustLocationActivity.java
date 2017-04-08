package cn.bingoogolapple.qrcode.zxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdjustLocationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_location);
    }

    public void onClick_confirm(View v) {
        Intent myIntent = new Intent(this, PhotoActivity.class);
        startActivity(myIntent);

    }

    public void onClick_cancel(View v) {
        finish();

    }
}
