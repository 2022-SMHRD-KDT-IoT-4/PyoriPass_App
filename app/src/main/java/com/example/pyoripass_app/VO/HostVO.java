package com.example.pyoripass_app.VO;

import java.io.Serializable;

public class HostVO implements Serializable {

    private String host_id;
    private String host_pw;
    private String host_tel;

    public HostVO(String host_id, String host_pw, String host_tel) {
        this.host_id = host_id;
        this.host_pw = host_pw;
        this.host_tel = host_tel;
    }

    public String getHost_id() {
        return host_id;
    }

    public void setHost_id(String host_id) {
        this.host_id = host_id;
    }

    public String getHost_pw() {
        return host_pw;
    }

    public void setHost_pw(String host_pw) {
        this.host_pw = host_pw;
    }

    public String getHost_tel() {
        return host_tel;
    }

    public void setHost_tel(String host_tel) {
        this.host_tel = host_tel;
    }
}
