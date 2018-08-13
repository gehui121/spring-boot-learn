package com.gehui.jpa;

import com.gehui.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/8/10 17:52.
 **/
public interface UserJPA extends JpaRepository<UserEntity,Long> {
}
