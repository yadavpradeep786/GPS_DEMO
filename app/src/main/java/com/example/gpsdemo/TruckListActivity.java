package com.example.gpsdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.gpsdemo.utils.LastRunningState;
import com.example.gpsdemo.utils.LastWaypoint;
import com.example.gpsdemo.utils.NetworkChangeReceiver;
import com.example.gpsdemo.utils.ShowProgressDialog;
import com.example.gpsdemo.utils.TruckItemObject;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("Registered")
public class TruckListActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefresh;
    RecyclerView mRecyclerView;
    ArrayList<TruckItemObject> dataList = new ArrayList<>();
    Toolbar toolbar_map;
    String imeiNo;
    boolean isConnected;
    TextView text_f_date;
    TextView text_t_date;
    RouteAdapter routeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_list);
        isConnected = NetworkChangeReceiver.isConnected();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        initToolbar();
        mRecyclerView = findViewById(R.id.recycler);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        if (isConnected) {
            mRecyclerView.removeAllViews();
            dataList.clear();
            new GetData().execute(toString());
        } else {
            swipeRefresh.setRefreshing(false);
            Toast.makeText(TruckListActivity.this, "Not connected to internet", Toast.LENGTH_SHORT).show();
        }
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isConnected) {
                    mRecyclerView.removeAllViews();
                    dataList.clear();
                    new GetData().execute(toString());
                } else {
                    swipeRefresh.setRefreshing(false);
                    Toast.makeText(TruckListActivity.this, "Not connected to internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.MyViewHolder> implements View.OnClickListener {

        ArrayList<TruckItemObject> statusList;

        public RouteAdapter(List<TruckItemObject> statusList) {
            this.statusList = (ArrayList<TruckItemObject>) statusList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.truck_list_item, parent, false);

            return new MyViewHolder(itemView);
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView text_vehicle_no, text_updated, text_running_stopped, text_speed, text_fuel, text_date, text_ignition, text_sync, text_power;
            LinearLayout ll_location;

            public MyViewHolder(View view) {
                super(view);
                text_vehicle_no = view.findViewById(R.id.text_vehicle_no);
                text_updated = view.findViewById(R.id.text_updated);
                text_running_stopped = view.findViewById(R.id.text_running_stopped);
                text_speed = view.findViewById(R.id.text_speed);
                ll_location = view.findViewById(R.id.ll_location);
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(MyViewHolder viewHolder, int position) {
            TruckItemObject truckItemObject = statusList.get(position);
            LastWaypoint lastWaypoint = truckItemObject.getLastWaypoint();
            LastRunningState lastRunningState = truckItemObject.getLastRunningState();
            viewHolder.text_vehicle_no.setText(truckItemObject.getTruckNumber());
            viewHolder.text_updated.setText(Long.toString(truckItemObject.getCreateTime()) + "-days ago");
            viewHolder.text_running_stopped.setText("Stop/Running " + Long.toString(lastRunningState.getStopStartTime()) + "-days");
            viewHolder.text_speed.setText(lastWaypoint.getSpeed() + "-k/h");
            viewHolder.ll_location.setOnClickListener(this);
            viewHolder.ll_location.setTag(R.string.lat_out_Key, lastWaypoint.getLat());
            viewHolder.ll_location.setTag(R.string.long_out_Key, lastWaypoint.getLng());
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_location:
                    String lat1 = (String) v.getTag(R.string.lat_out_Key);
                    String lng1 = (String) v.getTag(R.string.long_out_Key);
                    if (!lat1.equals("")) {
                        Intent intent1 = new Intent(TruckListActivity.this, LocationOnMapActivity.class);
                        intent1.putExtra("lat", lat1);
                        intent1.putExtra("long", lng1);
                        startActivity(intent1);
                    } else {
                        Toast.makeText(TruckListActivity.this, "No Location Data!", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return statusList.size();
        }
    }


    @SuppressLint("StaticFieldLeak")
    public class GetData extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            ShowProgressDialog.Show(TruckListActivity.this);
        }

        protected String doInBackground(String... arg0) {

            String responseResult = "0";
            try {
                responseResult = getAddedData();
            } catch (Exception e) {
                responseResult = "0";
                e.printStackTrace();
            }


            return responseResult;

        }

        @Override
        protected void onPostExecute(String result) {
            if (ShowProgressDialog.isShowing())
                ShowProgressDialog.Dismiss();
            try {
                swipeRefresh.setRefreshing(false);
                if (ShowProgressDialog.isShowing())
                    ShowProgressDialog.Dismiss();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
                if (jsonArray.length() > 0) {
                    try {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            Gson gson = new Gson();
                            dataList.add(gson.fromJson(String.valueOf((jsonObject1)), TruckItemObject.class));
                        }
                        displayData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(TruckListActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(TruckListActivity.this, "No Data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void displayData() {
        swipeRefresh.setRefreshing(false);
        if (ShowProgressDialog.isShowing())
            ShowProgressDialog.Dismiss();
        routeAdapter = new RouteAdapter(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(TruckListActivity.this.getApplicationContext());
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(TruckListActivity.this, resId);
//        mRecyclerView.setLayoutAnimation(animation);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRecyclerView.getChildAt(View.ACCESSIBILITY_LIVE_REGION_ASSERTIVE);
        }
        mRecyclerView.setAdapter(routeAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String getAddedData() {
        dataList.clear();
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Your code to run in GUI thread here
                        Toast.makeText(TruckListActivity.this, "Server not responding!", Toast.LENGTH_SHORT).show();
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

    private void initToolbar() {
        toolbar_map = findViewById(R.id.toolbar_map);
        toolbar_map.setTitle(getResources().getString(R.string.route_truck));
        toolbar_map.setTitleTextColor(getResources().getColor(R.color.white));
        LinearLayout ll_location = toolbar_map.findViewById(R.id.ll_location);
        setSupportActionBar(toolbar_map);
        toolbar_map.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar_map.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });
        ll_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TruckListActivity.this, VehicleOnMapActivity.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

}
