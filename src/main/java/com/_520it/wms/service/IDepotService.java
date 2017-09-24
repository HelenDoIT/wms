package com._520it.wms.service;

import com._520it.wms.domain.Depot;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public interface IDepotService {
    void save(Depot depot);
    void delete(Long id);
    void update(Depot depot);
    Depot get(Long id);
    List<Depot> listAll();
    //分页查询
    PageResult queryPageResult(QueryObject qo);
}
