package com.gehui.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/9 11:05.
 **/
@Entity
@Table(name = "good_info")
public class GoodEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long id;
    @Column(name = "t_title")
    private String title;
    @Column(name = "t_price")
    private Double price;
    @Column(name = "t_unit")
    private String unit;
    @Column(name = "t_order")
    private int order;
    @OneToOne
    @JoinColumn(name = "t_type_id")
    private GoodTypeEntity type;

    public GoodEntity() {
    }

    public GoodEntity(String title) {
        this.title = title;
    }

    public GoodEntity(String title, Double price, String unit, int order, GoodTypeEntity type) {
        this.title = title;
        this.price = price;
        this.unit = unit;
        this.order = order;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public GoodTypeEntity getType() {
        return type;
    }

    public void setType(GoodTypeEntity type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GoodEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                ", order=" + order +
                ", type=" + type +
                '}';
    }
}
