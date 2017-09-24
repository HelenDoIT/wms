package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter@Getter
public class StockIncomeBill extends BaseAuditDomain {

    private String sn;

    private Date vdate;

    private BigDecimal totalAmount;

    private BigDecimal totalNumber;

    private Depot depot;
    private List<StockIncomeBillItem> items = new ArrayList<>();
}