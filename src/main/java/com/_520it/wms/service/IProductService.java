package com._520it.wms.service;

import com._520it.wms.domain.Product;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

/**
 * Created by Helen MM on 2017/9/12.
 */
public interface IProductService {
    void save(Product product);
    void delete(Long id);
    void update(Product product);
    Product get(Long id);
    //分页查询
    PageResult queryPageResult(QueryObject qo);
}
