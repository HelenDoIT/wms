package com._520it.wms.web.interceptor;

import com._520it.wms.domain.Employee;
import com._520it.wms.util.UserContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Created by Helen MM on 2017/8/30.
 */
public class LoginInterceptor extends AbstractInterceptor {

    public String intercept(ActionInvocation actionInvocation) throws Exception {
        //从session中获取登录用户
//        Object user = actionInvocation.getInvocationContext().getSession().get("user_in_session");
        Employee currentUser = UserContext.getCurrentUser();
        if(currentUser==null){
            return "login";
        }
        return actionInvocation.invoke();
    }
}
