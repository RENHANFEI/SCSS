package cn.bingoogolapple.qrcode.zxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SuccessScanActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_scan);

        // set spinner
        Spinner typeSpinner = (Spinner) findViewById(R.id.light_type);
        String[] deviceType = {"控制柜", "灯杆"};
//        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, deviceType);
//        typeSpinner.setAdapter(adapterType);
//
//        // set text (scanned info)
//        TextView uidView = (TextView) findViewById(R.id.uid);
//        TextView lightHeadView = (TextView) findViewById(R.id.light_head);


    }

    public void onClick_close(View v) {
        finish();
    }

    public void onClick_adjustLocation(View v) {

        TextView uidView = (TextView) findViewById(R.id.uid);
        TextView lightHeadView = (TextView) findViewById(R.id.light_head);

        String uid = uidView.toString();
        String lightHead = lightHeadView.toString();

        Intent myIntent = new Intent(this, AdjustLocationActivity.class);
        startActivity(myIntent);
    }

    public void onClick_nextStep(View v) {
        Intent myIntent = new Intent(this, PhotoActivity.class);
        startActivity(myIntent);
    }
}
