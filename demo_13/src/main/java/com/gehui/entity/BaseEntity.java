package com.gehui.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/7 17:44.
 **/
public class BaseEntity implements Serializable {
    private int page = 1;//分页页码，默认为1
    private int size = 5;//每页显示的条数，默认为5
    private String sidx = "id";//排序列名称，默认为ID
    private String sord = "desc";

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }
}
