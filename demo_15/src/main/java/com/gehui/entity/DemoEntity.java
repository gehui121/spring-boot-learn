package com.gehui.entity;

import com.gehui.validator.FlagValidator;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/9 16:05.
 **/
public class DemoEntity implements Serializable {
    @NotBlank
    @Length(min = 2,max = 10)
    private String name;//非空校验、长度必须在2~10位之间
    @Min(value = 1)
    private int age;//最小是1岁。
    @NotBlank
    @Email
    private String mail;//非空校验、邮箱格式。
    @FlagValidator(values = "1,2,3")
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public DemoEntity() {
    }

    public DemoEntity(@NotBlank @Length(min = 2, max = 10) String name, @Min(value = 1) int age, @NotBlank @Email String mail) {
        this.name = name;
        this.age = age;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
