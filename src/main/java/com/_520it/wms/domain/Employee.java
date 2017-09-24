package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helen MM on 2017/8/19.
 */
@Setter@Getter
public class Employee extends BaseDomain {
    //private Long id;
    private String name;
    private String password;
    private String email;
    private Integer age;
    private boolean admin;
    private Department dept;
    private List<Role> roles = new ArrayList<>();

    public String toString() {
        return "Employee{" +
                "admin=" + admin +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", id=" + getId() +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
