package com.example.viewpagedemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


/**
 * Created by Administrator on 2015/9/6 0006.
 */
public class WelcomeAct extends Activity {


    private boolean isFirstIn = false;
    private static final int TIME = 2000;
    private static final int Go_Home = 1000;
    private static final int Go_Guide = 1001;




    private void goHome() {
        Intent i = new Intent(WelcomeAct.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goGuide() {
        Intent i = new Intent(WelcomeAct.this, Guide.class);
        startActivity(i);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        init();
    }

    private void init() {
        SharedPreferences preferences = getSharedPreferences("1", MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("isFirstIn", true);
        if (!isFirstIn) {

            mHandler.sendEmptyMessageDelayed(Go_Home, TIME);


        } else {
            mHandler.sendEmptyMessageDelayed(Go_Guide, TIME);
            Editor editor = preferences.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();
        }

    }

   private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Go_Home:
                    goHome();
                    break;
                case Go_Guide:
                    goGuide();
                    break;


            }
        }
    };
}
