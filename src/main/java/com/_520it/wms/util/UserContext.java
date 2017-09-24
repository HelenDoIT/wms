package com._520it.wms.util;

import com._520it.wms.domain.Employee;
import com.opensymphony.xwork2.ActionContext;

import java.util.List;

/**
 * Created by Helen MM on 2017/9/5.
 */
public class UserContext {
    //把用户信息保存到session中
    public static void setCurrentUser(Employee employee){
        ActionContext.getContext().getSession().put("user_in_session",employee);
    }
    //获取当前登录的用户
    public  static Employee getCurrentUser(){
        return (Employee) ActionContext.getContext().getSession().get("user_in_session");
    }
    //把当前用户的权限保存到session中
    public static void setPermissionSet(List<String> permissionSet){
        ActionContext.getContext().getSession().put("expressions_in_session",permissionSet);
    }
    //获取当前用户权限集合列表
    public  static List<String> getPermissionSet(){
        return (List<String>) ActionContext.getContext().getSession().get("expressions_in_session");
    }
}
