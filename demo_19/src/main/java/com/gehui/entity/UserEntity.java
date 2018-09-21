package com.gehui.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Administrator on 2018/9/12 13:59.
 **/
@Entity
@Table(name = "user_jwt",schema = "jwt")
public class UserEntity implements Serializable {
    @Id
    @Column(name = "aui_app_id")
    private String appId;

    @Column(name = "aui_app_secret")
    private byte[] appSectet;

    @Column(name = "aui_status")
    private int status;

    @Column(name = "aui_day_request_count")
    private int dayRequestCount;

    @Column(name = "aui_ajax_bind_ip")
    private String ajaxBindIp;

    @Column(name = "aui_mark")
    private String mark;

    public UserEntity(String appId, byte[] appSectet, int status, int dayRequestCount, String ajaxBindIp, String mark) {
        this.appId = appId;
        this.appSectet = appSectet;
        this.status = status;
        this.dayRequestCount = dayRequestCount;
        this.ajaxBindIp = ajaxBindIp;
        this.mark = mark;
    }

    public UserEntity() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public byte[] getAppSectet() {
        return appSectet;
    }

    public void setAppSectet(byte[] appSectet) {
        this.appSectet = appSectet;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDayRequestCount() {
        return dayRequestCount;
    }

    public void setDayRequestCount(int dayRequestCount) {
        this.dayRequestCount = dayRequestCount;
    }

    public String getAjaxBindIp() {
        return ajaxBindIp;
    }

    public void setAjaxBindIp(String ajaxBindIp) {
        this.ajaxBindIp = ajaxBindIp;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "appId='" + appId + '\'' +
                ", appSectet=" + Arrays.toString(appSectet) +
                ", status=" + status +
                ", dayRequestCount=" + dayRequestCount +
                ", ajaxBindIp='" + ajaxBindIp + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}
