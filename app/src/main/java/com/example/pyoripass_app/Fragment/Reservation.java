package com.example.pyoripass_app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pyoripass_app.R;
import com.example.pyoripass_app.ResAdapter;
import com.example.pyoripass_app.VO.ReservationVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Reservation extends Fragment {

    Context context;
    TextView res_txt;
    TextView res_status;
    CalendarView calendar;
    ListView listView;

    String id;

    RequestQueue requestQueue;
    StringRequest request;
    JSONArray jsonArr;
    ArrayList<ReservationVO> dataset;
    ResAdapter adapter;

    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View reservation = inflater.inflate(R.layout.fragment_reservation, container, false);
        
        context = container.getContext();
        res_txt = reservation.findViewById(R.id.res_txt);
        calendar = reservation.findViewById(R.id.calendar);
        listView = reservation.findViewById(R.id.listview);
        
        bundle = this.getArguments();
        id = bundle.getString("host_id");
        res_txt.setText(id + "님 펜션의 일정");
        Log.v("id", id);

        getReservation();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                // 선택한 날짜 값 가져오기
                String date = year + "-0" + (month + 1) + "-" + dayOfMonth;
                String[] date2 = date.split("-");

                dataset = new ArrayList<ReservationVO>();
                adapter = new ResAdapter(context, R.layout.reservation_item, dataset);

                try {
                    for (int i = 0; i < jsonArr.length(); i++) {
                        JSONObject calendarList = (JSONObject) jsonArr.get(i);
                        String[] listDate = calendarList.getString("checkin_date").split(" ");
                        Log.v("calendarList", calendarList.toString());
                        Log.v("listDate", listDate[0]);

                        if (date.equals(listDate[0])) {
                            Log.v("listDate2", listDate[0]);

                            String host_id = calendarList.getString("host_id");
                            String pension_name = calendarList.getString("pension_name");
                            String room_name = calendarList.getString("room_name");
                            String reservation_num = calendarList.getString("reservation_num");
                            String checkin_date = calendarList.getString("checkin_date");
                            String checkout_date = calendarList.getString("checkout_date");
                            String guest_name = calendarList.getString("guest_name");
                            String guest_phone = calendarList.getString("guest_phone");

                            dataset.add(new ReservationVO(host_id, pension_name, room_name, reservation_num, checkin_date, checkout_date, guest_name, guest_phone));
                        }
                    }
                    listView.setAdapter(adapter);

                    builder.setTitle((month + 1) + "월 " + dayOfMonth + "일 예약 정보");
                    if (listView.getParent() != null) {
                        ((ViewGroup) listView.getParent()).removeView(listView);
                    }
                    builder.setView(listView);
                    builder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return reservation;
    }

    public void getReservation() {
        requestQueue = Volley.newRequestQueue(context);

        request = new StringRequest(
                Request.Method.POST,
                "http://172.30.1.12:8083/pyoripass/appcallist.do",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("resultValue", response);

                        try {
                            jsonArr = new JSONArray(response);
                            Log.v("jsonArr", jsonArr.toString());
                            Log.v("jsonArr", jsonArr.length() + "");
                        } catch (JSONException e) {
                            e.printStackTrace();
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
                String host_id = id;

                params.put("host_id", host_id);

                return params;
            }
        };
        requestQueue.add(request);
    }
}