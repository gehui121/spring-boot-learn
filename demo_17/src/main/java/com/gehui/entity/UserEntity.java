package com.gehui.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2018/8/13 15:10.
 * UserDetails是SpringSecurity验证框架内部提供的用户验证接口
 * （我们下面需要用到UserEntity来完成自定义用户认证功能），
 * 我们需要实现getAuthorities方法内容，将我们定义的角色列表添加到授权的列表内。
 **/
@Entity
@Table(name = "login_user")
public class UserEntity implements Serializable, UserDetails {
    @Id
    @Column(name = "u_id")
    public Long id;
    @Column(name = "u_username")
    private String username;
    @Column(name = "u_password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "ur_user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ur_role_id")
            }
    )
    private List<RoleEntity> roles;

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String password, List<RoleEntity> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }


    //.权限集合
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<RoleEntity> roles = getRoles();
        for (RoleEntity role : roles) {
//            auths.add(new SimpleGrantedAuthority(role.getName()));//根据RoleName来添加角色授权的
            auths.add(new SimpleGrantedAuthority(role.getFlag()));//根据flag来添加角色授权的
        }
        return auths;
    }

    //密码
    @Override
    public String getPassword() {
        return password;
    }

    //用户名
    @Override
    public String getUsername() {
        return username;
    }

    //用户是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //是否锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //用户密码是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账号是否可用（可理解为是否删除）
    @Override
    public boolean isEnabled() {
        return true;
    }
}
