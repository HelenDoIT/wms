package com._520it.wms.service.impl;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.mapper.RoleMapper;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IRoleService;
import com._520it.wms.page.PageResult;
import lombok.Setter;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/24.
 */
public class RoleServiceImpl implements IRoleService {
    @Setter
    private RoleMapper roleMapper;

    public void save(Role role) {
        roleMapper.save(role);
        //维护关系
        List<Permission> permissions = role.getPermissions();
        for (Permission permission : permissions) {
             roleMapper.insertRelation(role.getId(),permission.getId());
        }
        List<SystemMenu> menus = role.getMenus();
        for (SystemMenu menu : menus) {
            roleMapper.insertMenuRelation(role.getId(),menu.getId());
        }
    }

    public void update(Role role) {
        //先删除关系, 后更新, 再维护关系
        roleMapper.deleteRelation(role.getId());
        roleMapper.update(role);
        List<Permission> permissions = role.getPermissions();
        for (Permission p : permissions) {
            System.out.println(p.getId());
            roleMapper.insertRelation(role.getId(),p.getId());
        }
        List<SystemMenu> menus = role.getMenus();
        for (SystemMenu menu : menus) {
            roleMapper.insertMenuRelation(role.getId(),menu.getId());
        }
    }

    public void delete(Long id) {
        //删除关系
        roleMapper.deleteRelation(id);
        roleMapper.deleteMenuRelation(id);
        roleMapper.delete(id);
    }

    public List<Role> listAll() {
        List<Role> roles = roleMapper.listAll();
        return roles;
    }

    public Role getPermission(Long roleId) {
        Role permission = roleMapper.getPermission(roleId);
        return permission;
    }

    public PageResult queryPage(QueryObject qo) {
        Integer totalCount = roleMapper.queryTotalCount(qo);
        if(totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Role> data = roleMapper.queryList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }

    public Role getRole(Long id) {
        Role role = roleMapper.getRole(id);
        return role;
    }

    @Override
    public void batchDelete(List<Long> ids) {
        roleMapper.batchDeleteRelation(ids);
        roleMapper.batchDelete(ids);
    }
}
