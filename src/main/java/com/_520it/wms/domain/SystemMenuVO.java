package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/9/6.
 */
//用List<SystemMenuVO> 来封装层级菜单列表对象的 id name
@Getter@Setter
public class SystemMenuVO {
    private Long id;
    private String name;
}
