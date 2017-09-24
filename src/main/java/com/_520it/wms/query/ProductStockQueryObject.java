package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/9/12.
 */
@Setter@Getter
public class ProductStockQueryObject extends QueryObject {
    private String keyword;
    private Long depotId=-1L;
    private Long brandId = -1L;
    private Integer limitNumber = -1;
}
