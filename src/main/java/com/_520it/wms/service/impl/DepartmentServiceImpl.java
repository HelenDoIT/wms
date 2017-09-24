package com._520it.wms.service.impl;

import com._520it.wms.mapper.DepartmentMapper;
import com._520it.wms.domain.Department;
import com._520it.wms.domain.Employee;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
import lombok.Setter;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class DepartmentServiceImpl implements IDepartmentService {
    @Setter
    private DepartmentMapper departmentMapper;
    public void save(Department dept) {
        departmentMapper.save(dept);
    }

    public void delete(Long id) {
        departmentMapper.delete(id);
    }

    public void update(Department dept) {
        departmentMapper.update(dept);
    }

    public Department get(Long id) {
        Department dept = departmentMapper.get(id);
        return dept;
    }

    public List<Department> listAll() {
        List<Department> list = departmentMapper.listAll();
        return list;
    }

    public PageResult queryPageResult(QueryObject qo) {
        Integer totalCount = departmentMapper.queryTotalCount(qo);
        if(totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Employee> data = departmentMapper.queryList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }
}
