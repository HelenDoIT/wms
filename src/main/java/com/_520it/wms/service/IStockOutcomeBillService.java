package com._520it.wms.service;

import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

/**
 * Created by Helen MM on 2017/8/21.
 */
public interface IStockOutcomeBillService {
    void save(StockOutcomeBill stockOutcomeBill);
    void delete(Long id);
    void update(StockOutcomeBill stockOutcomeBill);
    StockOutcomeBill get(Long id);
    //分页查询
    PageResult queryPageResult(QueryObject qo);

    void audit(Long id);
}
