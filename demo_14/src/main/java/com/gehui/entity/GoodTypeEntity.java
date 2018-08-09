package com.gehui.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/9 11:05.
 **/
@Entity
@Table(name = "good_type")
public class GoodTypeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tt_id")
    private Long id;
    @Column(name = "tt_name")
    private String name;
    @Column(name = "tt_is_show")
    private int isShow;
    @Column(name = "tt_order")
    private int order;

    public GoodTypeEntity() {
    }

    public GoodTypeEntity(String name) {
        this.name = name;
    }

    public GoodTypeEntity(String name, int isShow, int order) {
        this.name = name;
        this.isShow = isShow;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "GoodTypeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isShow=" + isShow +
                ", order=" + order +
                '}';
    }
}
