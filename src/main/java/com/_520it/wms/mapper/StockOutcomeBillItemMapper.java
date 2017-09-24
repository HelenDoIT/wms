package com._520it.wms.mapper;

import com._520it.wms.domain.StockOutcomeBillItem;

public interface StockOutcomeBillItemMapper {

    int insert(StockOutcomeBillItem record);

    void deleteByBillId(Long id);

}