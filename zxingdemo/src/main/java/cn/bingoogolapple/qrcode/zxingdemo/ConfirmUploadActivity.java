package cn.bingoogolapple.qrcode.zxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ConfirmUploadActivity extends Activity {

    String uid;
    String lightHead;
    String deviceType;
    String lat;
    String lng;
    String address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_upload);

        // get extra
        Intent myIntent = getIntent();
        uid = myIntent.getStringExtra("uid");
        lightHead = myIntent.getStringExtra("lightHead");
        deviceType = myIntent.getStringExtra("deviceType");
        lat = myIntent.getStringExtra("lat");
        lng = myIntent.getStringExtra("lng");
        address = myIntent.getStringExtra("address");

        // show upload info
        // set spinner
        Spinner typeSpinner = (Spinner) findViewById(R.id.light_type);
        String[] deviceTypes = {"控制柜", "灯杆"};
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, deviceTypes);
        typeSpinner.setAdapter(adapterType);
        int position = 0;
        if (deviceType.equalsIgnoreCase(deviceTypes[1]))
            position = 1;
        typeSpinner.setSelection(position);

        // set text (scanned info)
        EditText uidView = (EditText) findViewById(R.id.uid);
        EditText lightHeadView = (EditText) findViewById(R.id.light_head);

        uidView.setText(uid);
        lightHeadView.setText(lightHead);

        // set position
        TextView latView = (TextView) findViewById(R.id.lat_view);
        TextView lngView = (TextView) findViewById(R.id.lng_view);
        TextView addView = (TextView) findViewById(R.id.address_view);

        latView.setText("纬度  " + lat);
        lngView.setText("经度  " + lng);
        addView.setText("地址  " + address);
    }

    public void onClick_confirm(View v) {
        Intent myIntent = new Intent(this, UploadResultActivity.class);
        myIntent.putExtra("deviceType", deviceType);
        myIntent.putExtra("uid", uid);
        myIntent.putExtra("lightHead", lightHead);
        myIntent.putExtra("lat", lat);
        myIntent.putExtra("lng", lng);
        myIntent.putExtra("address", address);
        startActivity(myIntent);

    }

    public void onClick_adjust(View v) {
        Intent myIntent = new Intent(this, AdjustLocationActivity.class);
        myIntent.putExtra("deviceType", deviceType);
        myIntent.putExtra("uid", uid);
        myIntent.putExtra("lightHead", lightHead);
        myIntent.putExtra("lat", lat);
        myIntent.putExtra("lng", lng);
        myIntent.putExtra("address", address);
        myIntent.putExtra("source", 1);
        startActivity(myIntent);
    }

    public void onClick_takePhoto(View v) {
        Intent myIntent = new Intent(this, PhotoActivity.class);
        myIntent.putExtra("deviceType", deviceType);
        myIntent.putExtra("uid", uid);
        myIntent.putExtra("lightHead", lightHead);
        myIntent.putExtra("lat", lat);
        myIntent.putExtra("lng", lng);
        myIntent.putExtra("address", address);
        startActivity(myIntent);
    }

    public void onClick_back(View v) {
        finish();
    }
}
