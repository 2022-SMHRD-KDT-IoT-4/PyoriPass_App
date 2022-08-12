package com.example.pyoripass_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        moveMain(2);
    }

    private void moveMain(int sec) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // new Intent (현재 context, 이동 activity)
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                // Intent 에 명시된 Activity 로 이동
                startActivity(intent);
                finish();
            }
        }, 1000 * sec); // sec 초 정도 딜레이를 준 후 시작
    }
}