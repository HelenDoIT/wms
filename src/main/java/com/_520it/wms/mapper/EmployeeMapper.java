package com._520it.wms.mapper;

import com._520it.wms.domain.Employee;
import com._520it.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public interface EmployeeMapper {
    void save(Employee e);
    void delete(Long id);
    void update(Employee e);
    Employee get(Long id);
    List<Employee> listAll();
    //高级查询和分页
    Integer queryTotalCount(QueryObject qo);
    List<Employee> queryList(QueryObject qo);
    //维护员工和角色的关系
    void insertRelation(@Param("eId") Long eId, @Param("rId") Long rId);
    //删除员工和角色的关系
    void deleteRelation(Long eId);
    //返回登录对象
    Employee getLogin(@Param("name") String username,@Param("password") String password);
    //批量删除
    void batchDelete(List<Long> ids);
    //批量删除 中间关系
    void batchDeleteRelation(List<Long> ids);
}
