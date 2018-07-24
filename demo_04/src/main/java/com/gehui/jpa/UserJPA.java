package com.gehui.jpa;

import com.gehui.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/24 14:29.
 * 整合mysql使用jpa操作CRUD
 **/
public interface UserJPA extends
        JpaRepository<UserEntity,Long>,
        JpaSpecificationExecutor<UserEntity>,
        Serializable {

}
