package com.gehui.entity;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;


/**
 * Created by Administrator on 2018/8/22 9:01.
 **/
@Entity
public class User implements Serializable {
    @Id
    @Column(updatable = false,nullable = false)
    @Size(min = 0,max = 50)
    private String username;

    @Size(min = 0, max = 500)
    private String password;

    @Email
    @Size(min = 0, max = 100)
    private String email;

    private boolean activated;

    @Size(min = 0, max = 100)
    @Column(name = "activationkey")
    private String activationKey;

    @Column(name = "resetpasswordkey")
    @Size(min = 0, max = 100)
    private String resetPasswordKey;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_auth",
            joinColumns = {
                    @JoinColumn(name = "username")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "name")
            }
    )
    private Set<Authority> authority;

    public User() {
    }

    public User(@Size(min = 0, max = 50) String username, @Size(min = 0, max = 500) String password, @Email @Size(min = 0, max = 100) String email, boolean activated, @Size(min = 0, max = 100) String activationKey, @Size(min = 0, max = 100) String resetPasswordKey, Set<Authority> authority) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.activated = activated;
        this.activationKey = activationKey;
        this.resetPasswordKey = resetPasswordKey;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetPasswordKey() {
        return resetPasswordKey;
    }

    public void setResetPasswordKey(String resetPasswordKey) {
        this.resetPasswordKey = resetPasswordKey;
    }

    public Set<Authority> getAuthority() {
        return authority;
    }

    public void setAuthority(Set<Authority> authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", activated=" + activated +
                ", activationKey='" + activationKey + '\'' +
                ", resetPasswordKey='" + resetPasswordKey + '\'' +
                ", authority=" + authority +
                '}';
    }
}
