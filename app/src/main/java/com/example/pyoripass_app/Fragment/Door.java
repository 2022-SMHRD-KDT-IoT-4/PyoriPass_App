package com.example.pyoripass_app.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pyoripass_app.R;

public class Door extends Fragment {

    Context context;
    Button btn_open;
    RequestQueue requestQueue;
    StringRequest request;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View door = inflater.inflate(R.layout.fragment_door, container, false);
        btn_open = door.findViewById(R.id.btn_open);
        context = container.getContext();

        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                send();
            }
        });

        return door;
    }

    public void send() {
        requestQueue = Volley.newRequestQueue(context);

        request = new StringRequest(
                Request.Method.POST,
                "http://172.30.1.12:8083/pyoripass/dooropen.do",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
    }
}