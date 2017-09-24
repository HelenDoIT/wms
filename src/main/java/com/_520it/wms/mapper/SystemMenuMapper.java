package com._520it.wms.mapper;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SystemMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long id);

    List<SystemMenu> selectAll();

    int updateByPrimaryKey(SystemMenu record);

    int queryForCount(QueryObject qo);

    List<SystemMenu> queryForList(QueryObject qo);
    List<SystemMenu> listAll();

    List<SystemMenu> selectByRoleId(Long roleId);
    List<SystemMenu> listChildMenu();

    List<Map<String,Object>> queryMenuByParentSn(String parentSn);

    List<Map<String,Object>> queryMenuByParentSnAndEmpId(@Param("parentSn") String parentSn, @Param("empId") Long empId);

}