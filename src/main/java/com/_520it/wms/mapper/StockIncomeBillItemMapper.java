package com._520it.wms.mapper;

import com._520it.wms.domain.StockIncomeBillItem;

public interface StockIncomeBillItemMapper {

    int insert(StockIncomeBillItem record);

    void deleteByBillId(Long id);

}