package com.example.gpsdemo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.gpsdemo.utils.LastRunningState;
import com.example.gpsdemo.utils.LastWaypoint;
import com.example.gpsdemo.utils.NetworkChangeReceiver;
import com.example.gpsdemo.utils.ShowProgressDialog;
import com.example.gpsdemo.utils.TruckItemObject;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class VehicleOnMapActivity extends AppCompatActivity implements LocationListener, View.OnClickListener, OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private static final String TAG = "LocationOnMapActivity";
    Toolbar toolbar_map;
    private GoogleMap mMap;
    Marker mPointMarker;
    Double latitude = 0.00;
    Double longitude = 0.00;
    final boolean isConnected = NetworkChangeReceiver.isConnected();
    ArrayList<TruckItemObject> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_on_map);
        initToolbar();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setUpMap();
        if (isConnected) {
            new GetData().execute(toString());
        } else {
            Toast.makeText(VehicleOnMapActivity.this, "Not connected to internet", Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("StaticFieldLeak")
    public class GetData extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            ShowProgressDialog.Show(VehicleOnMapActivity.this);
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        protected String doInBackground(String... arg0) {
            String responseResult = "0";
            String url = "https://api.mystral.in/tt/mobile/logistics/searchTrucks?auth-company=PCH&companyId=33&deactivated=false&key=g2qb5jvucg7j8skpu5q7ria0mu&q-expand=true&q-include=lastRunningState,lastWaypoint";
            try {
                String requestMethod = "GET";
                HttpURLConnection con = (HttpURLConnection) ((new URL(url).openConnection()));
                con.addRequestProperty("Accept-Charset", "UTF-8");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestMethod(requestMethod);
                con.setConnectTimeout(60000);
                con.connect();
                int stcode = con.getResponseCode();
                Log.d("urlLogin", url);
                System.out.println("stcode = " + stcode);
                if (stcode == 200) {
                    StringBuilder response = new StringBuilder();
                    InputStream ins = con.getInputStream();
                    InputStreamReader isr = new InputStreamReader(ins);
                    int ch;

                    while ((ch = isr.read()) >= 0) {
                        response.append((char) ch);
                    }
                    final String s = response.toString();
                    System.out.println("response = " + s);
                    return s;
                } else {
                    System.out.println("Response = " + stcode);
                    VehicleOnMapActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Your code to run in GUI thread here
                            Toast.makeText(VehicleOnMapActivity.this, "Server not responding!", Toast.LENGTH_SHORT).show();
                            if (ShowProgressDialog.isShowing())
                                ShowProgressDialog.Dismiss();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                responseResult = "0";
            }
            return responseResult;
        }

        @Override
        protected void onPostExecute(String result) {
            if (ShowProgressDialog.isShowing())
                ShowProgressDialog.Dismiss();
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
                if (jsonArray.length() > 0) {
                    try {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            Gson gson = new Gson();
                            dataList.add(gson.fromJson(String.valueOf(jsonObject1), TruckItemObject.class));
                        }
                        displayData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(VehicleOnMapActivity.this, "No More Data", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
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

    @SuppressLint("PotentialBehaviorOverride")
    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
//        addPointMarker();
        mMap.setOnInfoWindowClickListener(this);

    }

    private void displayData() {
        try {
            for (int i = 0; i < dataList.size(); i++) {
                TruckItemObject truckItemObject = dataList.get(i);
                LastWaypoint lastWaypoint = truckItemObject.getLastWaypoint();
                LastRunningState lastRunningState = truckItemObject.getLastRunningState();
                Handler handler1 = new Handler();
                handler1.postDelayed(() -> {
                    Double lat = Double.valueOf(lastWaypoint.getLat());
                    Double lng = Double.valueOf(lastWaypoint.getLng());
                    String speed = lastWaypoint.getSpeed();
                    Double lat1 = Double.valueOf(lastRunningState.getLat());
                    Double lng1 = Double.valueOf(lastRunningState.getLng());
                    String no = truckItemObject.getTruckNumber();
                    addPointMarker(lat, lng, speed, no);
                    addPointMarkerRunning(lat1, lng1, "RunningState");
                }, 100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addPointMarker(Double lat, Double lng, String speed, String no) {
        LatLng START = new LatLng(lat, lng);
        BitmapDrawable bitmapdraw = null;
        if (speed.equals("0")) {
            bitmapdraw = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.red_map_icon);
        } else {
            bitmapdraw = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.green_map_icon);
        }
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 70, 70, false);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(START);
        mPointMarker = mMap.addMarker(new MarkerOptions().position(START)
                .title(no)
                .visible(true)
//                .snippet(addressNew)
                .flat(true)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                .anchor(0.5f, 1.0f));
        mPointMarker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 11));
        mMap.setOnMarkerClickListener(onMarkerClickedListener);
    }

    public void addPointMarkerRunning(Double lat, Double lng, String no) {
        LatLng START = new LatLng(lat, lng);
        BitmapDrawable bitmapdraw = null;
        bitmapdraw = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.green_map_icon);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 70, 70, false);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(START);
        mPointMarker = mMap.addMarker(new MarkerOptions().position(START)
                .title(no)
                .visible(true)
//                .snippet(addressNew)
                .flat(true)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                .anchor(0.5f, 1.0f));
        mPointMarker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 11));
        mMap.setOnMarkerClickListener(onMarkerClickedListener);
    }

    private final GoogleMap.OnMarkerClickListener onMarkerClickedListener = new GoogleMap.OnMarkerClickListener() {
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
        toolbar_map.setTitle(getResources().getString(R.string.vehicles_map));
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
