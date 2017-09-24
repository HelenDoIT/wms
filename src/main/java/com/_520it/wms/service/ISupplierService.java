package com._520it.wms.service;

import com._520it.wms.domain.Supplier;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public interface ISupplierService {
    void save(Supplier supplier);
    void delete(Long id);
    void update(Supplier supplier);
    Supplier get(Long id);
    List<Supplier> listAll();
    //分页查询
    PageResult queryPageResult(QueryObject qo);
}
