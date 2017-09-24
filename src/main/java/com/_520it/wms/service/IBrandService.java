package com._520it.wms.service;

import com._520it.wms.domain.Brand;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public interface IBrandService {
    void save(Brand brand);
    void delete(Long id);
    void update(Brand brand);
    Brand get(Long id);
    List<Brand> listAll();
    //分页查询
    PageResult queryPageResult(QueryObject qo);
}
