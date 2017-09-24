package com._520it.wms.web.action;

import com._520it.wms.domain.Permission;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/8/22.
 */
public class PermissionAction extends BaseAction {
    @Setter
    private IPermissionService permissionService;
    @Getter
    private QueryObject qo = new QueryObject();
    @Getter
    private Permission permission = new Permission();

    @RequiredPermission("权限列表")
    public String execute() throws Exception {
        PageResult pageResult = permissionService.queryPageResult(qo);
        putContext("pageResult",pageResult);
        return LIST;
    }
    @RequiredPermission("权限删除")
    public String delete() throws Exception {
        try {
            permissionService.delete(permission.getId());
            putJson("权限删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("权限删除失败,请重试");
        }
        return NONE;
    }
    @RequiredPermission("权限加载")
    public String reload() throws Exception {
        try {
            //int i = 1/0;
            permissionService.reload();
            putJson("权限加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("权限加载失败,请重试");
        }
        return NONE;
    }
}
