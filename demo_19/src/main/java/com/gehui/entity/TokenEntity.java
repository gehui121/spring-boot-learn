package com.gehui.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Administrator on 2018/9/12 14:21.
 **/
@Entity
@Table(name = "token_jwt",schema = "jwt")
public class TokenEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ati_id")
    private long id;

    @Column(name = "ati_app_id")
    private String appId;

    @Column(name = "ati_token")
    private byte[] token;

    @Column(name = "ati_bind_time")
    private String bindTime;

    public TokenEntity(String appId, byte[] token, String bindTime) {
        this.appId = appId;
        this.token = token;
        this.bindTime = bindTime;
    }

    public TokenEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public String getBindTime() {
        return bindTime;
    }

    public void setBindTime(String bindTime) {
        this.bindTime = bindTime;
    }

    @Override
    public String toString() {
        return "TokenEntity{" +
                "id=" + id +
                ", appId='" + appId + '\'' +
                ", token=" + Arrays.toString(token) +
                ", bindTime='" + bindTime + '\'' +
                '}';
    }
}
