package com.example.test01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends Activity {

    public static final String TITLE = "title";
    public static final String USER_INFO = "userInfo";
    public static final int REQUEST_CODE = 9999;
    private static final String TAG = SplashActivity.class.getSimpleName();


    Handler mHandler = new Handler();

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mTextView = (TextView) findViewById(R.id.title_text_view);
        String title = mTextView.getText().toString();
        mHandler.postDelayed(new Runnable() {
            @Override

            public void run() {
                //jump to mainActivity
                UserInfo userInfo = new UserInfo("XiaoMin", 12);
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra(TITLE, title);
                intent.putExtra(USER_INFO, userInfo);

                startActivityForResult(intent, REQUEST_CODE);
            }
        }, 2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "requestCode: " + requestCode + ", resultCode: " + resultCode);

        if(requestCode == REQUEST_CODE && resultCode == MainActivity.RESULT_CODE){
            if(data != null){
                String present = data.getStringExtra(TITLE);
                mTextView.setText(present);
            }
        }
    }
}
