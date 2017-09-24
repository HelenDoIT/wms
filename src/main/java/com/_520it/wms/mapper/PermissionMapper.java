package com._520it.wms.mapper;

import com._520it.wms.domain.Permission;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/22.
 */
public interface PermissionMapper {
    void save(Permission p);
    void delete(Long id);
    List<Permission> listAll();
    //分页查询
    Integer queryTotalCount(QueryObject qo);
    List<Permission> queryList(QueryObject qo);
    //查询所有的权限表达式
    List<String> listExpressions();
    //根据role id查询所有权限表达式
    Permission selectByRoleId(Long roleId);
    //根据员工id 查询所有的权限表示
    List<String> selectByEmpId(Long EmpId);
}
