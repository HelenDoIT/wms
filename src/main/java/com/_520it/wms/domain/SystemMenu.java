package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helen MM on 2017/9/6.
 */
@Setter@Getter
public class SystemMenu extends BaseDomain {
    private String name;
    private String url;
    private String sn;
    private SystemMenu parent;
    private List<SystemMenu> children = new ArrayList<>();

    public String getParentName(){
        if(getParent()!= null){
            return getParent().getName();
        }
        return "根目录";
    }
}
