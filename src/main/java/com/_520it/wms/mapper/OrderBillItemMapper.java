package com._520it.wms.mapper;

import com._520it.wms.domain.OrderBillItem;

public interface OrderBillItemMapper {

    int insert(OrderBillItem record);
    int deleteByBillId(Long billId);
    OrderBillItem selectByBillId(Long billId);
}