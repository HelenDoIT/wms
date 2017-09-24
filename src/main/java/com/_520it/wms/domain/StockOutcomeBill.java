package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter@Getter
public class StockOutcomeBill extends BaseAuditDomain {

    private String sn;

    private Date vdate;

    private BigDecimal totalAmount;

    private BigDecimal totalNumber;

    private Depot depot;

    private Client client;

    private List<StockOutcomeBillItem> items = new ArrayList<>();
}