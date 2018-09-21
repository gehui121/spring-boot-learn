package com.gehui.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/12 14:55.
 * 编写获取Token方法返回数据
 **/
public class TokenResult implements Serializable {
    //状态
    private boolean flag = true;
    //返回消息内容
    private String msg = "";
    //返回Token值
    private String token = "";

    public TokenResult() {
    }

    public TokenResult(boolean flag, String msg, String token) {
        this.flag = flag;
        this.msg = msg;
        this.token = token;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenResult{" +
                "flag=" + flag +
                ", msg='" + msg + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
