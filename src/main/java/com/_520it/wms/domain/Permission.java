package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/8/19.
 */
@Setter@Getter
public class Permission extends BaseDomain {
    //private Long id;
    private String name;   //权限名称
    private String expression;//权限表达式
}
