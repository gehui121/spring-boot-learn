package com.gehui.entity.jpa;

import com.gehui.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/8/13 15:58.
 **/
public interface UserJPA extends JpaRepository<UserEntity,Long> {
    //使用springdatajpa定义根据名字查询方法
    public UserEntity findByUsername(String username);
}
