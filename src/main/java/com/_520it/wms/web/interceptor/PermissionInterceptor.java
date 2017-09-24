package com._520it.wms.web.interceptor;

import com._520it.wms.util.UserContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com._520it.wms.domain.Employee;
import com._520it.wms.util.RequiredPermission;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Helen MM on 2017/8/30.
 */
public class PermissionInterceptor extends AbstractInterceptor {
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        //获取登录用户
        Employee user = (Employee) actionInvocation.getInvocationContext().getSession().get("user_in_session");
        //判断用户是否为超级管理员, 如果是,放行
        if(user.isAdmin()){
            return actionInvocation.invoke();
        }
        //通过代理获取当前Action的字节码对象
        Class actionClass = actionInvocation.getProxy().getAction().getClass();
        //获取当前请求的方法名称
        String methodName = actionInvocation.getProxy().getMethod();
        //获取当前请求的方法
        Method method = actionClass.getMethod(methodName);
        //判断方法上是否有贴权限注解, 没有, 放行
        RequiredPermission anno = method.getAnnotation(RequiredPermission.class);
        if(anno==null){
            return actionInvocation.invoke();
        }
        //拼当前权限表达式
        String exp=actionClass.getName()+":"+methodName;
        //获取session中当前用户拥有的权限表达式
        List<String> exps = UserContext.getPermissionSet();
        //List<String> exps= (List<String>) actionInvocation.getInvocationContext().getSession().get("expressions_in_session");
        //判断当前权限表达式 是否存在于当前用户在数据库的权限表达式 (通过查询存放到list集合中), 存在则放行
        if(exps.contains(exp)){
            return actionInvocation.invoke();
        }
        return "nopermission";
    }
}
