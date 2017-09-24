package com._520it.wms.service;

import com._520it.wms.domain.Permission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/22.
 */
public interface IPermissionService {
    void save(Permission p);
    void delete(Long id);
    List<Permission> listAll();
    //分页查询
    PageResult queryPageResult(QueryObject qo);

    void reload();

}
