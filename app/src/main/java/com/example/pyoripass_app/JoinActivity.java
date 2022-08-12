package com.example.pyoripass_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class JoinActivity extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        web = findViewById(R.id.web);

        // 웹페이지 요청할 수 있는 객체
        web.setWebViewClient(new WebViewClient());

        // 이동할 url 지정
        web.loadUrl("http://172.30.1.12:8083/pyoripass/join.do");
    }
}