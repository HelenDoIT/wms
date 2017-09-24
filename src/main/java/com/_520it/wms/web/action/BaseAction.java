package com._520it.wms.web.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class BaseAction extends ActionSupport {
    public static final String LIST = "list";
    public void putContext(String name, Object value){
        ActionContext.getContext().put(name, value);
    }
    public void putJson(Object obj) throws Exception {
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(JSON.toJSONString(obj));
    }
}
