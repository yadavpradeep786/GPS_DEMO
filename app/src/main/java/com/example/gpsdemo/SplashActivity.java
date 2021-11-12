package com.example.gpsdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;

public class SplashActivity extends Activity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("loginStatus", Context.MODE_PRIVATE);
                if (sharedPreferences.getString("status", "").equals("1")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            SplashActivity.this.finish();

                        }
                    });
                } else {
                    startApplication();
                }

            }

        }, 2000);
    }

    private void startApplication() {
        Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacksAndMessages(null);
        finish();
    }
}
