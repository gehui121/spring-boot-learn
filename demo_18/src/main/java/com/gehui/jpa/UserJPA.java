package com.gehui.jpa;

import com.gehui.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2018/8/22 9:45.
 **/
public interface UserJPA extends JpaRepository<User,String> {
    //使用了HQL语法来构建的语句，根据用户名不区分大小写进行查询
    @Query("select u from User u where lower(u.username) = lower(username)")
    User findByUsernameCaseInsensitive(@Param("username") String username);
}
