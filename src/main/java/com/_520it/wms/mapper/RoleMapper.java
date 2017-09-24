package com._520it.wms.mapper;

import com._520it.wms.domain.Role;
import com._520it.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/22.
 */
public interface RoleMapper {
    void save(Role role);
    void update(Role role);
    void delete(Long id);
    Role getRole(Long id );
    List<Role> listAll();
    //根据角色id查询该角色拥有的所有权限
    Role getPermission(Long roleId);
    //分页查询
    Integer queryTotalCount(QueryObject qo);
    List<Role> queryList(QueryObject qo);

    //维护中间表关系
    void insertRelation(@Param("roleId") Long roleId, @Param("permId") Long permId);
    //删除中间关系
    void deleteRelation(Long roleId);
    //通过员工id查询对应的角色
    List<Role> getByEmpId(Long eId);
    //批量删除
    void batchDelete(List<Long> ids);
    void batchDeleteRelation(List<Long> ids);

    //维护与菜单列表的关系
    void insertMenuRelation(@Param("roleId") Long roleId, @Param("menuId") Long menuId);
    //删除与菜单列表的关系
    void deleteMenuRelation(Long roleId);

}
