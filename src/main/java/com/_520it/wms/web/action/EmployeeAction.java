package com._520it.wms.web.action;

import com._520it.wms.domain.Department;
import com._520it.wms.domain.Employee;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.EmployeeQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class EmployeeAction extends BaseAction {
    @Setter
    private IEmployeeService employeeService;
    @Setter
    private IDepartmentService departmentService;
    @Setter
    private IRoleService roleService;
    @Getter
    private Employee employee = new Employee();
    @Getter
    private QueryObject qo = new EmployeeQueryObject();
    @Setter@Getter
    private List<Long> ids = new ArrayList<>();

    @RequiredPermission("员工列表")
    @InputConfig(methodName="input")//如果有错误信息, 往指定的方法或视图上跳转
    public String execute() throws Exception {
        try {
            PageResult pageResult = employeeService.queryPageResult(qo);
            putContext("pageResult",pageResult);
            List<Department> depts = departmentService.listAll();
            putContext("depts",depts);
//            int i = 1/0;
        } catch (Exception e) {
            addActionError(e.getMessage());//调用父类的方法把错误信息共享到页面
            e.printStackTrace();
        }
        return LIST;
    }
    @RequiredPermission("员工删除")
    public String delete() throws Exception {
        try {
            employeeService.delete(employee.getId());
            putJson("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("删除失败");
        }
        return  NONE;
    }
    @RequiredPermission("员工保存更新")
    public String saveOrUpdate() throws Exception {
        try {
            //int i = 1/0;
            if(employee.getId() != null){
                employeeService.update(employee);
                addActionMessage("修改成功啦啦啦");
            }else {
                employeeService.save(employee);
                addActionMessage("新增成功啦啦啦");
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            e.printStackTrace();
        }
        return SUCCESS;
    }
    @RequiredPermission("员工编辑")
    public String input() throws Exception {
        try {
            List<Department> depts = departmentService.listAll();
            putContext("depts",depts);
            List<Role> roles = roleService.listAll();
            putContext("roles",roles);
            if(employee.getId() != null){
               employee =  employeeService.get(employee.getId());
                System.out.println(employee);
            }
            //int i = 1/0;
        } catch (Exception e) {
            addActionError(e.getMessage());
            e.printStackTrace();
        }
        return "input";
    }
    @RequiredPermission("员工批量删除")
    public String batchDelete()throws Exception{
        try {
            if(ids.size()>0){
                    employeeService.batchDelete(ids);
                    putJson("批量删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putJson("批量删除失败");
        }
        return NONE;
    }
}
