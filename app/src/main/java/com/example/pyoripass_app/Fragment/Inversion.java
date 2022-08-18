package com.example.pyoripass_app.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.pyoripass_app.R;

public class Inversion extends Fragment {

    WebView inv_web;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inversion, container, false);

        inv_web = view.findViewById(R.id.inv_web);

        inv_web.setWebViewClient(new WebViewClient());
        inv_web.loadUrl("http://172.30.1.12:8083/pyoripass/invasionInformation");

        return view;
    }
}