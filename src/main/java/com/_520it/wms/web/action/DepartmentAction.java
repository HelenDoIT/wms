package com._520it.wms.web.action;

import com._520it.wms.domain.Department;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class DepartmentAction extends BaseAction {
    @Setter
    private IDepartmentService departmentService;
    @Getter
    private Department department = new Department();
    @Getter
    private QueryObject qo = new QueryObject();
    @RequiredPermission("部门列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        PageResult pageResult = departmentService.queryPageResult(qo);
        putContext("pageResult",pageResult);
        return LIST;
    }
    @RequiredPermission("部门删除")
    public String delete() throws Exception {
        try {
            departmentService.delete(department.getId());
            putJson("部门删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("部门删除失败");
        }
        return  NONE;
    }
    @RequiredPermission("部门保存更新")
    public String saveOrUpdate() throws Exception {
        try {
            int i = 1/0;
            if(department.getId() != null){
                departmentService.update(department);
                addActionMessage("部门更改成功");
            }else {
                departmentService.save(department);
                addActionMessage("部门保存成功");
            }
        } catch (Exception e) {
            addActionError("操作失败,请重试");
            e.printStackTrace();
        }
        return SUCCESS;
    }
    @RequiredPermission("部门编辑")
    public String input() throws Exception {
        try {
            if(department.getId() != null){
                department =  departmentService.get(department.getId());
            }
            int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "input";
    }
}
