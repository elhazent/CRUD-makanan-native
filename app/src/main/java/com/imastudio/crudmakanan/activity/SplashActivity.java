package com.imastudio.crudmakanan.activity;

import android.os.Bundle;
import android.os.Handler;

import com.imastudio.crudmakanan.R;
import com.imastudio.crudmakanan.helper.SessionManager;

public class SplashActivity extends SessionManager {

//    todo 1 buat class splash jika mau

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//       myIntent(LoginActivity.class);
//                finish();
                sessionManager.checkLogin();
                finish();
            }
        },4000);
    }
}
