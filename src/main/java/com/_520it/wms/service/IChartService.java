package com._520it.wms.service;

import com._520it.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Helen MM on 2017/9/12.
 */
public interface IChartService {
    List<Map<String,Object>> orderChart(QueryObject qo);
    List<Map<String,Object>> saleChart(QueryObject qo);
}
