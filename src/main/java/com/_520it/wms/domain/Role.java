package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helen MM on 2017/8/20.
 */
@Setter@Getter
public class Role extends BaseDomain{
    private String name;
    private String sn;
    //一定要new出来, 因为页面对role.permission做迭代
    private List<Permission> permissions = new ArrayList<>();

    private List<SystemMenu> menus = new ArrayList<>();
}
