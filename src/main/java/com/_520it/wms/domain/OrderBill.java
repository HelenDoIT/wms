package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Helen MM on 2017/9/9.
 */
@Getter@Setter
public class OrderBill extends BaseAuditDomain {

    private String sn;
    private Date vdate;

    private BigDecimal totalAmount;
    private BigDecimal totalNumber;

    private Supplier supplier;
    private List<OrderBillItem> items = new ArrayList<>();
}
