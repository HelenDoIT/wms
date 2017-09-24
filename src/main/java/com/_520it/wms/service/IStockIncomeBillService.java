package com._520it.wms.service;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

/**
 * Created by Helen MM on 2017/8/21.
 */
public interface IStockIncomeBillService {
    void save(StockIncomeBill stockIncomeBill);
    void delete(Long id);
    void update(StockIncomeBill stockIncomeBill);
    StockIncomeBill get(Long id);
    //分页查询
    PageResult queryPageResult(QueryObject qo);

    void audit(Long id);
}
