package com.jinchi.order.domain.entity;

/**
 * Created by ZHANGTAO269 on 2019-8-30.
 */
public class GpsData {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String gprs;
    private String sysTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGprs() {
        return gprs;
    }

    public void setGprs(String gprs) {
        this.gprs = gprs;
    }

    public String getSysTime() {
        return sysTime;
    }

    public void setSysTime(String sysTime) {
        this.sysTime = sysTime;
    }
}
