package com.gehui.jpa;

import com.gehui.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/8/7 13:49.
 **/
@Transactional
public interface UserJPA extends
        JpaRepository<UserEntity,Long>,
        JpaSpecificationExecutor<Long>,
        Serializable {
    //查询大于20岁的用户
    @Query(value = "select * from user where age > ?",nativeQuery = true)
    public List<UserEntity> nativeQuery(int age);
    //根据用户名和密码进行删除一条数据
    @Modifying
    @Query(value = "delete from user where name = ? and password = ?",nativeQuery = true)
    public void delete(String name, String password);
}
