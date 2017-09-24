package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/9/10.
 */
@Setter @Getter
public class StockOutcomeBillQueryObject extends BaseAuditQueryObject{
    private Long depotId = -1L;
    private Long clientId = -1L;
}
