package com._520it.wms.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com._520it.wms.service.IEmployeeService;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/8/27.
 */
public class LoginAction extends ActionSupport {
    @Setter
    private String username;
    @Setter
    private String password;
    @Setter
    private IEmployeeService employeeService;

    public String execute() throws Exception {
        try {
            employeeService.checkLogin(username,password);
        } catch (Exception e) {
           super.addActionError(e.getMessage());
            return "login";
        }
        return SUCCESS;
    }
}
