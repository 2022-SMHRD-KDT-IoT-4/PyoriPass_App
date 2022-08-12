package com.example.pyoripass_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.pyoripass_app.Fragment.Door;
import com.example.pyoripass_app.Fragment.Inversion;
import com.example.pyoripass_app.Fragment.Reservation;
import com.example.pyoripass_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView nav;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav = findViewById(R.id.nav);

        // fragment 생성
        Inversion inversion = new Inversion();
        Door door = new Door();
        Reservation reservation = new Reservation();

        // LogActivity 에서 보낸 intent 받기
        Intent intent = getIntent();
        String host_id = intent.getStringExtra("host_id");
        String host_pw = intent.getStringExtra("host_pw");
        String host_tel = intent.getStringExtra("host_tel");

        bundle.putString("host_id", host_id);
        reservation.setArguments(bundle);

        // 기본 화면 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame, reservation).commit();

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.inversion) {
                    // inversion fragment 이동
                    getSupportFragmentManager().beginTransaction().replace(R.id.Frame, inversion).commit();
                } else if (item.getItemId() == R.id.reservation) {

                    // reservation fragment 이동
                    getSupportFragmentManager().beginTransaction().replace(R.id.Frame, reservation).commit();
                } else {
                    // door fragment 이동
                    getSupportFragmentManager().beginTransaction().replace(R.id.Frame, door).commit();
                }

                return true;
            }
        });

    }
}