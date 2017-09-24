package com._520it.wms.service;

import com._520it.wms.domain.Employee;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public interface IEmployeeService {
    void save(Employee e);
    void delete(Long id);
    void update(Employee e);
    Employee get(Long id);
    List<Employee> listAll();
    PageResult queryPageResult(QueryObject qo);
    void checkLogin(String username,String password);
    void batchDelete(List<Long> ids);
}
