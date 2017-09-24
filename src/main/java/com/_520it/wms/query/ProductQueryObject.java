package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/9/7.
 */
@Getter@Setter
public class ProductQueryObject extends QueryObject{
    private String keyword;
    private Long brandId = -1L;
}
