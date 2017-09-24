package com._520it.wms.service.impl;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.Role;
import com._520it.wms.mapper.EmployeeMapper;
import com._520it.wms.mapper.PermissionMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.util.MD5;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class EmployeeServiceImpl implements IEmployeeService {
    @Setter
    private EmployeeMapper employeeMapper;
    @Setter
    private PermissionMapper permissionMapper;
    public void save(Employee e) {
        //保存的时候也进行密码加密
        e.setPassword(MD5.encode(e.getPassword()));
        employeeMapper.save(e);
        //维护关系
        List<Role> roles = e.getRoles();
        for (Role role : roles) {
            employeeMapper.insertRelation(e.getId(),role.getId());
        }
    }

    public void delete(Long id) {
        //先删除关系
        employeeMapper.deleteRelation(id);
        employeeMapper.delete(id);
    }

    public void update(Employee e) {
        //先删除关系, 更新后再重新维护关系
        employeeMapper.deleteRelation(e.getId());
        employeeMapper.update(e);
        List<Role> roles = e.getRoles();
        for (Role role : roles) {
            employeeMapper.insertRelation(e.getId(),role.getId());
        }
    }

    public Employee get(Long id) {
        Employee e = employeeMapper.get(id);
        return e;
    }

    public List<Employee> listAll() {
        List<Employee> list = employeeMapper.listAll();
        return list;
    }

    public PageResult queryPageResult(QueryObject qo) {
        Integer totalCount = employeeMapper.queryTotalCount(qo);
        if(totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Employee> data = employeeMapper.queryList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }

    public void checkLogin(String username, String password) {
        //登录时用户密码加过密在去数据库查
        Employee user = employeeMapper.getLogin(username, MD5.encode(password));
        if(user == null){
            throw new RuntimeException("账号密码不匹配");
        }
        UserContext.setCurrentUser(user);
//        ActionContext.getContext().getSession().put("user_in_session",user);
        //如果正确,把当前登录用户 含有的权限表达式存储到session中
        List<String> exps = permissionMapper.selectByEmpId(user.getId());
        UserContext.setPermissionSet(exps);
//        ActionContext.getContext().getSession().put("expressions_in_session",exps);
    }

    @Override
    public void batchDelete(List<Long> ids) {
        //先批量删除中间关系
        employeeMapper.batchDeleteRelation(ids);
        //批量删除
        employeeMapper.batchDelete(ids);
    }
}
