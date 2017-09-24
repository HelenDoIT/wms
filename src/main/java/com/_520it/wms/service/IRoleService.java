package com._520it.wms.service;

import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/24.
 */
public interface IRoleService {
    void save(Role role);
    void update(Role role);
    void delete(Long id);
    List<Role> listAll();
    Role getPermission(Long roleId);
    PageResult queryPage(QueryObject qo);
    Role getRole(Long id);
    void batchDelete(List<Long> ids);

}
