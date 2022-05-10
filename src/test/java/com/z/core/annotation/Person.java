package com.z.core.annotation;

@Table(value = "person")
public class Person {
    @Column("name")
    public String name;
    @Column("user_name")
    public String userName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
