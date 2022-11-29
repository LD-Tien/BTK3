package com.ldt.btk3;

import java.io.Serializable;

public class NgonNgu implements Serializable {
    private String tenNN, vidu;

    public NgonNgu() {
    }

    public NgonNgu(String tenNN, String vidu) {
        this.tenNN = tenNN;
        this.vidu = vidu;
    }

    public String getTenNN() {
        return tenNN;
    }

    public void setTenNN(String tenNN) {
        this.tenNN = tenNN;
    }

    public String getVidu() {
        return vidu;
    }

    public void setVidu(String vidu) {
        this.vidu = vidu;
    }
}
