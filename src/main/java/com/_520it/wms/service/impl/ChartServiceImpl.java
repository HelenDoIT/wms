package com._520it.wms.service.impl;

import com._520it.wms.mapper.ChartMapper;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IChartService;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by Helen MM on 2017/9/12.
 */
public class ChartServiceImpl implements IChartService{
    @Setter
    private ChartMapper chartMapper;

    @Override
    public List<Map<String, Object>> orderChart(QueryObject qo) {

        return chartMapper.orderChart(qo);
    }

    @Override
    public List<Map<String, Object>> saleChart(QueryObject qo) {
        return chartMapper.saleChart(qo);
    }
}
