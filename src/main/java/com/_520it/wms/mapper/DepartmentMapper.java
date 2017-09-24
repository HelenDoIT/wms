package com._520it.wms.mapper;

import com._520it.wms.domain.Department;
import com._520it.wms.domain.Employee;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public interface DepartmentMapper {
    void save(Department dept);
    void delete(Long id);
    void update(Department dept);
    Department get(Long id);
    List<Department> listAll();
    //分页查询
    Integer queryTotalCount(QueryObject qo);
    List<Employee> queryList(QueryObject qo);
}
