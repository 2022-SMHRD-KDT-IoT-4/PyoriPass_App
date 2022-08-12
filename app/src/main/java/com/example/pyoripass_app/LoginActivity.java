package com.example.pyoripass_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText login_id, login_pw;
    Button btn_login, btn_join;
    RequestQueue requestQueue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_id = findViewById(R.id.login_id);
        login_pw = findViewById(R.id.login_pw);
        btn_login = findViewById(R.id.btn_login);
        btn_join = findViewById(R.id.btn_join);

        // 회원가입
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

        // 로그인
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });
    }

    public void send() {
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(this);

        request = new StringRequest(
                Request.Method.POST,
                "http://172.30.1.12:8083/pyoripass/applogin.do",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("resultValue", response);
                        Log.v("resultValue", response.length() + "");

                        if (response.length() > 0) {
                            try {
                                JSONObject json = new JSONObject(response);

                                String host_id = json.getString("host_id");
                                String host_pw = json.getString("host_pw");
                                String host_tel = json.getString("host_tel");

                                Toast.makeText(getApplicationContext(), host_id + "님 안녕하세요.", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("host_id", host_id);
                                intent.putExtra("host_pw", host_pw);
                                intent.putExtra("host_tel", host_tel);
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String host_id = login_id.getText().toString();
                String host_pw = login_pw.getText().toString();

                params.put("host_id", host_id);
                params.put("host_pw", host_pw);

                return params;
            }
        };
        requestQueue.add(request);
    }
}