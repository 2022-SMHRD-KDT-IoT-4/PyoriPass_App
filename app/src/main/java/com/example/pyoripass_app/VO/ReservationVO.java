package com.example.pyoripass_app.VO;

import java.io.Serializable;

public class ReservationVO implements Serializable {

    private String host_id;
    private String pension_name;
    private String room_name;
    private String reservation_num;
    private String checkin_date;
    private String checkout_date;
    private String guest_name;
    private String guest_phone;

    public ReservationVO(String host_id, String pension_name, String room_name, String reservation_num, String checkin_date, String checkout_date, String guest_name, String guest_phone) {
        this.host_id = host_id;
        this.pension_name = pension_name;
        this.room_name = room_name;
        this.reservation_num = reservation_num;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.guest_name = guest_name;
        this.guest_phone = guest_phone;
    }

    public String getHost_id() {
        return host_id;
    }

    public void setHost_id(String host_id) {
        this.host_id = host_id;
    }

    public String getPension_name() {
        return pension_name;
    }

    public void setPension_name(String pension_name) {
        this.pension_name = pension_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getReservation_num() {
        return reservation_num;
    }

    public void setReservation_num(String reservation_num) {
        this.reservation_num = reservation_num;
    }

    public String getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(String checkin_date) {
        this.checkin_date = checkin_date;
    }

    public String getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(String checkout_date) {
        this.checkout_date = checkout_date;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getGuest_phone() {
        return guest_phone;
    }

    public void setGuest_phone(String guest_phone) {
        this.guest_phone = guest_phone;
    }

}
