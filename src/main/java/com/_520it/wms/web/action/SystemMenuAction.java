package com._520it.wms.web.action;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.domain.SystemMenuVO;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class SystemMenuAction extends BaseAction {
    @Setter
    private ISystemMenuService systemMenuService;
    @Getter
    private SystemMenu systemMenu = new SystemMenu();
    @Getter
    private SystemMenuQueryObject qo = new SystemMenuQueryObject();
    @RequiredPermission("系统菜单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        PageResult pageResult = systemMenuService.queryPageResult(qo);
        putContext("pageResult",pageResult);
        //根据qo.parentId 查询出所有的上级菜单列表, 共享到session
        List<SystemMenuVO> parentMenus = systemMenuService.queryParentMenu(qo.getParentId());
        putContext("parentMenus",parentMenus);
        return LIST;
    }
    @RequiredPermission("系统菜单删除")
    public String delete() throws Exception {
        try {
            systemMenuService.delete(systemMenu.getId());
            putJson("系统菜单删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("系统菜单删除失败,请重试");
        }
        return  NONE;
    }
    @RequiredPermission("系统菜单保存更新")
    public String saveOrUpdate() throws Exception {
        try {
            //通过qo.parentId查询出父级, 并设值到systemMenu对象中
            if(qo.getParentId()!=null){
                SystemMenu parent = systemMenuService.selectByPrimaryKey(qo.getParentId());
                systemMenu.setParent(parent);
            }
            if(systemMenu.getId() != null){
                systemMenuService.update(systemMenu);
                addActionMessage("系统菜单修改成功");
            }else {
                systemMenuService.save(systemMenu);
                addActionMessage("系统菜单新增成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败,请重试");
        }
        return SUCCESS;
    }
    @RequiredPermission("系统菜单编辑")
    public String input() throws Exception {
        //判断qo.parentId的值是否为空, 空则共享根目录到页面,不空则查询出父级名称,并共享到页面
        if(qo.getParentId() == null){
            putContext("parentNameCtx","根目录");
        }else{
            SystemMenu parent = systemMenuService.selectByPrimaryKey(qo.getParentId());
            putContext("parentNameCtx", parent.getName());
        }
        if(systemMenu.getId() != null){
            systemMenu =  systemMenuService.selectByPrimaryKey(systemMenu.getId());
        }
        return "input";
    }
    //通过父级菜单编码查询出所有子菜单, 封装到 List<Map> 中
    public String loadMenuBySn()throws Exception{
        List<Map<String,Object>> list = systemMenuService.queryMenuByParentSn(qo.getParentSn());
        putJson(list);
        return NONE;
    }
}
