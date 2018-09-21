package com.gehui.entity.jpa;

import com.gehui.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2018/9/12 14:35.
 **/
public interface UserJPA extends JpaRepository<UserEntity,String>,JpaSpecificationExecutor<UserEntity> {
}
