package com.example.thecheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class IntroActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro); //xml , java 소스 연결
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent (getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}