package com.z.bean.VO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserVo {
    private int id;

    @NotNull
    private String username;

    @NotNull
    @Length(min = 6 , message = "密码格式错误")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
