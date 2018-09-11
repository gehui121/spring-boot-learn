package com.gehui.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Administrator on 2018/8/22 9:28.
 * 角色name
 **/
@Entity
public class Authority {
    @Id
    @NotNull
    @Size(min = 0, max = 50)
    private String name;

    public Authority() {
    }

    public Authority(@NotNull @Size(min = 0, max = 50) String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "name='" + name + '\'' +
                '}';
    }
}
