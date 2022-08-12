package com.example.pyoripass_app;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pyoripass_app.VO.ReservationVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ResAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<ReservationVO> dataset;
    private LayoutInflater inflater;

    long now = System.currentTimeMillis();

    public ResAdapter(Context context, int layout, ArrayList<ReservationVO> dataset) {
        this.context = context;
        this.layout = layout;
        this.dataset = dataset;

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public Object getItem(int i) {
        return dataset.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(layout, null);

        TextView res_guestName = view.findViewById(R.id.res_guestName);
        TextView res_status = view.findViewById(R.id.res_status);
        TextView res_checkin = view.findViewById(R.id.res_checkin);
        TextView res_checkout = view.findViewById(R.id.res_checkout);
        ImageButton res_phone = view.findViewById(R.id.res_phone);
        TextView res_pension = view.findViewById(R.id.res_pension);
        TextView res_room = view.findViewById(R.id.res_room);

        // 날짜, 시간 분리
        String[] checkin_date = dataset.get(i).getCheckin_date().split(" ");
        String[] checkout_date = dataset.get(i).getCheckout_date().split(" ");

        String[] dayIn = checkin_date[0].split("-");
        String[] timeIn = checkin_date[1].split(":");
        String[] dayOut = checkout_date[0].split("-");
        String[] timeOut = checkout_date[1].split(":");

        int checkin_day = Integer.parseInt(dayIn[0] + dayIn[1] + dayIn[2]);
        int checkin_time = Integer.parseInt(timeIn[0] + timeIn[1]);
        int checkout_day = Integer.parseInt(dayOut[0] + dayOut[1] + dayOut[2]);
        int checkout_time = Integer.parseInt(timeOut[0] + timeOut[1]);

        Log.v("checkin_day", checkin_day + "");
        Log.v("checkin_time", checkin_time + "");
        Log.v("checkout_day", checkout_day + "");
        Log.v("checkout_time", checkout_time + "");

        // 현재 시간 가져오기
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");
        SimpleDateFormat dataFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = dateFormat.format(date);
        String getDay = dataFormat2.format(date);

        String[] getTime2 = getTime.split(":");
        String[] getDay2 = getDay.split("-");
        int day_getTime = Integer.parseInt(getTime2[0] + getTime2[1]);
        int day_getDay = Integer.parseInt(getDay2[0] + getDay2[1] + getDay2[2]);

        Log.v("day_getTime", day_getTime + "");
        Log.v("day_getDay", day_getDay + "");

        if (day_getDay < checkin_day) {
            res_status.setText("예약");
        } else if (day_getDay == checkin_day) {
            if (day_getTime < checkin_time) {
                res_status.setText("예약");
            } else if (checkin_time <= day_getTime) {
                res_status.setText("입실");
            }
        } else if (checkin_day < day_getDay && day_getDay < checkout_day) {
            res_status.setText("입실");
        } else if (day_getDay == checkout_day) {
            if (day_getTime < checkout_time) {
                res_status.setText("입실");
            } else {
                res_status.setText("퇴실");
            }
        } else if (checkout_day < day_getDay) {
            res_status.setText("퇴실");
        }

        res_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), dataset.get(i).getGuest_name() + "에게 전화를 겁니다.", Toast.LENGTH_LONG).show();
                try {
                    // 전화걸기

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + dataset.get(i).getGuest_phone()));
                    view.getContext().startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Log.e("전화걸기", "전화걸기 실패했습니다.", e);
                }
            }
        });

        res_guestName.setText(dataset.get(i).getGuest_name());
        res_checkin.setText(checkin_date[0]);
        res_checkout.setText(checkout_date[0]);
        res_pension.setText(dataset.get(i).getPension_name());
        res_room.setText(dataset.get(i).getRoom_name());

        return view;
    }
}
