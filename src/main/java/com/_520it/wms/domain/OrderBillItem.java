package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Helen MM on 2017/9/9.
 */
@Setter@Getter
public class OrderBillItem extends BaseDomain{
    private BigDecimal costPrice;
    private BigDecimal number;
    private BigDecimal amount;
    private  String remark;
    private  Product product;
    private Long billId;//关联的订单id, 不必用对象封装
}
