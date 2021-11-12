package com.example.gpsdemo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class LocationOnMapActivity extends AppCompatActivity implements LocationListener, View.OnClickListener, OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private static final String TAG = "LocationOnMapActivity";
    Toolbar toolbar_map;

    private GoogleMap mMap;
    Marker mPointMarker;
    Double latitude = 0.00;
    Double longitude = 0.00;
    String addressNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_on_map);
        initToolbar();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        try {
            latitude = Double.valueOf(Objects.requireNonNull(getIntent().getStringExtra("lat")));
            longitude = Double.valueOf(Objects.requireNonNull(getIntent().getStringExtra("long")));
            setUpMap();
            getAddress(latitude, longitude);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    private void setUpMap() {
        if (mMap == null) {
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.bin_location_map)).getMapAsync(this);
            Log.d("Map", "Success MapAsync");
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        setUpMap();
        Log.d("On Resume", "Success");
    }

    @Override
    public void onPause() {
        /* Disable the my-location layer (this causes our LocationSource to be automatically deactivated.) */
        super.onPause();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        addPointMarker();
        mMap.setOnInfoWindowClickListener(this);

    }

    public void getAddress(double LATITUDE, double LONGITUDE) {

        //Set Address
        try {
            Geocoder geocoder = new Geocoder(LocationOnMapActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {

                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                addressNew = address;
                Log.d(TAG, "getAddress:  address" + address);
                Log.d(TAG, "getAddress:  city" + city);
                Log.d(TAG, "getAddress:  state" + state);
                Log.d(TAG, "getAddress:  postalCode" + postalCode);
                Log.d(TAG, "getAddress:  knownName" + knownName);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public void addPointMarker() {
        LatLng START = new LatLng(latitude, longitude);
        BitmapDrawable bitmapdraw = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.icon_map_pin_everythingcivic);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 70, 70, false);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(START);
        mPointMarker = mMap.addMarker(new MarkerOptions().position(START)
                .title(addressNew)
                .visible(true)
//                .snippet(addressNew)
                .flat(true)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                .anchor(0.5f, 1.0f));
        mPointMarker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));
        mMap.setOnMarkerClickListener(onMarkerClickedListener);
    }

    private GoogleMap.OnMarkerClickListener onMarkerClickedListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            if (marker.isInfoWindowShown()) {
                marker.hideInfoWindow();
            } else {
                marker.showInfoWindow();
            }
            return true;
        }
    };

    private void initToolbar() {
        toolbar_map = findViewById(R.id.toolbar_map);
        toolbar_map.setTitle(getResources().getString(R.string.location_on_map));
        toolbar_map.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar_map);
        toolbar_map.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        toolbar_map.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }
}
