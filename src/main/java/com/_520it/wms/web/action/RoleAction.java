package com._520it.wms.web.action;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helen MM on 2017/8/24.
 */
public class RoleAction extends BaseAction{
    @Setter
    private IRoleService roleService;
    @Setter
    private IPermissionService permissionService;
    @Setter
    private ISystemMenuService systemMenuService;
    @Getter
    private QueryObject qo = new QueryObject();
    @Getter
    private Role role = new Role();
    @Setter@Getter
    List<Long> ids = new ArrayList<>();

    @RequiredPermission("角色列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        PageResult pageResult = roleService.queryPage(qo);
        putContext("pageResult",pageResult);
        return "list";
    }

    @RequiredPermission("角色删除")
    public String delete() throws Exception {
        try {
            roleService.delete(role.getId());
            putJson("角色删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("角色删除失败,请重试");
        }
        return NONE;
    }

    @RequiredPermission("角色保存更新")
    public String saveOrUpdate() throws Exception {
        try {
            //int i = 1/0;
            if(role.getId() != null){
                roleService.update(role);
                addActionMessage("角色更新成功");
            }else{
                roleService.save(role);
                addActionMessage("角色新增成功");
            }
        } catch (Exception e) {
            addActionError("操作失败,请重试");
            e.printStackTrace();
        }
        return SUCCESS;
    }

    @RequiredPermission("角色编辑")
    public String input() throws Exception {
        List<Permission> permissions = permissionService.listAll();
        putContext("permissions",permissions);
        //把菜单列表共享到context中
        //List<SystemMenu> menus = systemMenuService.listChildMenu();
        //putContext("menus",menus);
        if(role.getId()!= null){
            role = roleService.getRole(role.getId());
            System.out.println(role.getPermissions());
        }
        return "input";
    }

    @RequiredPermission("角色批量删除")
    public String batchDelete() throws Exception {
        System.out.println(ids);
        try {
            roleService.batchDelete(ids);
            putJson("批量删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("批量删除失败");
        }
        return NONE;
    }
}

