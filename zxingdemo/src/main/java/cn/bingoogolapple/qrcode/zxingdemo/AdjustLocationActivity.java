package cn.bingoogolapple.qrcode.zxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdjustLocationActivity extends FragmentActivity implements
        OnMapReadyCallback,
        com.google.android.gms.location.LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Marker mCurrentMarker;

    String geoAddress;
    String lat, lng;
    String uid;
    String lightHead;
    String deviceType;

    TextView latView;
    TextView lngView;
    TextView addView;

    int source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_location);

        // initialize map & location listener
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        geoAddress = "";
        lat = "31.3";
        lng = "120.6";
        mCurrentMarker = null;

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        // get extra
        Intent myIntent = getIntent();
        uid = myIntent.getStringExtra("uid");
        lightHead = myIntent.getStringExtra("lightHead");
        deviceType = myIntent.getStringExtra("deviceType");
        lat = myIntent.getStringExtra("lat");
        lng = myIntent.getStringExtra("lng");
        geoAddress = myIntent.getStringExtra("address");
        source = myIntent.getIntExtra("source", 0);


        // set
        latView = (TextView) findViewById(R.id.lat_view);
        lngView = (TextView)findViewById(R.id.lng_view);
        addView = (TextView)findViewById(R.id.address_view);

        latView.setText("纬度  " + lat);
        lngView.setText("经度  " + lng);
        addView.setText("地址  " + geoAddress);
    }

    public void onClick_confirm(View v) {

        if (source == 0)
            gotoPhoto();
        else
            gotoUpload();

    }

    public void gotoPhoto() {
        Intent myIntent = new Intent(this, PhotoActivity.class);
        myIntent.putExtra("deviceType", deviceType);
        myIntent.putExtra("uid", uid);
        myIntent.putExtra("lightHead", lightHead);
        myIntent.putExtra("lat", lat);
        myIntent.putExtra("lng", lng);
        myIntent.putExtra("address", geoAddress);
        startActivity(myIntent);
    }

    public void gotoUpload() {
        Intent myIntent = new Intent(this, ConfirmUploadActivity.class);
        myIntent.putExtra("deviceType", deviceType);
        myIntent.putExtra("uid", uid);
        myIntent.putExtra("lightHead", lightHead);
        myIntent.putExtra("lat", lat);
        myIntent.putExtra("lng", lng);
        myIntent.putExtra("address", geoAddress);
        startActivity(myIntent);
    }


    public void onClick_cancel(View v) {
        finish();

    }


    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    public void onPause() {
        LocationServices.FusedLocationApi.removeLocationUpdates
                (mGoogleApiClient, this);
        super.onPause();
    }

    public void onResume() {
        if (mGoogleApiClient.isConnected()) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                PendingResult<Status> pendingResult =
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                mGoogleApiClient, mLocationRequest, this);
            }
        }
        super.onResume();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            PendingResult<Status> pendingResult =
                    LocationServices.FusedLocationApi.requestLocationUpdates(
                            mGoogleApiClient, mLocationRequest, this);
        }
    }

    Location mCurrentLocation;

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mCurrentLocation = location;
        Geocoder geocoder;
        List<Address> addresses;

        String mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        String curAddress = "获取地理位置中……";

        if (mCurrentLocation != null) {

            lat = String.valueOf(mCurrentLocation.getLatitude());
            lng = String.valueOf(mCurrentLocation.getLongitude());

            // get the location with accuracy and also provider, using
            mCurrentLocation.getAccuracy();
            mCurrentLocation.getProvider();


            geocoder = new Geocoder(this, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(Double.valueOf(lat), Double.valueOf(lng), 1);
                int numAddresses = addresses.size();
                for (int a = 0; a < numAddresses; a++) {
                    int maxAddressLineIndex = addresses.get(a).getMaxAddressLineIndex();
                    for (int b = 0; b < maxAddressLineIndex; b++) {
                        String address = addresses.get(a).getAddressLine(b);
                        String city = addresses.get(a).getLocality();
                        String state = addresses.get(a).getAdminArea();
                        String country = addresses.get(a).getCountryName();
                        String postalCode = addresses.get(a).getPostalCode();
                        String knownName = addresses.get(a).getFeatureName();
                        // Only if available else return NULL
                        // if (knownName != null) geoInfoCtx += knownName + " ";
                        geoAddress = "";
                        if (address != null) geoAddress += address + " ";
                        if (city != null) geoAddress += city + " ";
                        if (state != null) geoAddress += state + " ";
                        if (country != null) geoAddress += country + " ";
                        if (postalCode != null) geoAddress += postalCode;
                        curAddress = address;
                    }
                }


            } catch (IOException e) {
                Log.e("Getting Address: ", "Error : ", e);
            }
        }


        TextView curAddressView = (TextView) findViewById(R.id.cur_address);
        curAddressView.setText(curAddress);

        //Place current location marker
        if (mCurrentMarker != null) {
            mCurrentMarker.remove();
        }

        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

        mCurrentMarker = mMap.addMarker(new MarkerOptions().position(currentLocation)
                .title("Marker in Current Location").snippet("This is Current Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.light_pin)));

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
}
