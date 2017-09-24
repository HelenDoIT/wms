package com._520it.wms.web.action;

import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IChartService;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.util.RequiredPermission;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Helen MM on 2017/9/12.
 */
public class ChartAction extends BaseAction {
    @Setter
    private IChartService chartService;
    @Setter
    private ISupplierService supplierService;
    @Setter
    private IBrandService brandService;
    @Setter
    private IClientService clientService;

    @Getter
    private OrderChartQueryObject oqo = new OrderChartQueryObject();
    @Getter
    private SaleChartQueryObject sqo = new SaleChartQueryObject();

    @RequiredPermission("订货报表")
    public String orderChart() throws Exception {
        List<Map<String, Object>> pageResult = chartService.orderChart(oqo);
        putContext("pageResult",pageResult);
        putContext("suppliers",supplierService.listAll());
        putContext("brands",brandService.listAll());
        putContext("groupTypes", OrderChartQueryObject.GROUP_TYPES);
        return "orderChart";
    }

    @RequiredPermission("销售报表")
    public String saleChart() throws Exception {
        List<Map<String, Object>> pageResult = chartService.saleChart(sqo);
        putContext("pageResult",pageResult);
        putContext("clients",clientService.queryPageResult(sqo).getData());
        putContext("brands",brandService.listAll());
        putContext("groupTypes", SaleChartQueryObject.GROUP_TYPES);
        return "saleChart";
    }

    @RequiredPermission("销售报表柱形图")
    public String saleChartByBar() throws Exception {
        List<Map<String, Object>> maps = chartService.saleChart(sqo);
        //分组类型
        putContext("groupTypes",SaleChartQueryObject.GROUP_TYPES.get(sqo.getGroupType()));
        //分组类型列表
        List<String> groupList = new ArrayList<>();
        //存放柱形图需要的数据格式
        List<BigDecimal> totalAmounts = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            groupList.add(map.get("groupType").toString());
            //SQL 查出的数量 列名(key)为 amount
            totalAmounts.add((BigDecimal) map.get("amount".toString()));
            //totalAmounts.add((BigDecimal)map.get("amount").toString());
        }
        putContext("groupList", JSON.toJSONString(groupList));
        putContext("totalAmounts",JSON.toJSONString(totalAmounts));
        return "saleChartByBar";
    }

    @RequiredPermission("销售报表饼状图")
    public String saleChartByPie() throws Exception {
        List<Map<String, Object>> maps = chartService.saleChart(sqo);
        //分组类型
        putContext("groupTypes",SaleChartQueryObject.GROUP_TYPES.get(sqo.getGroupType()));
        //分组类型列表
        List<String> groupList = new ArrayList<>();
        //存放饼状图需要的数据格式  [{value:335, name:'直接访问'},value:335, name:'直接访问'}]
        List<Map<String, Object>> datas = new ArrayList<>();
        BigDecimal maxAmount =BigDecimal.ZERO;
        for (Map<String, Object> map : maps) {
            String groupType = map.get("groupType").toString();
            groupList.add(groupType);
            Map<String, Object> data = new HashMap<>();
            data.put("name",groupType);
            BigDecimal amount = (BigDecimal) map.get("amount");
            //判断最大值
            if(amount.compareTo(maxAmount)>0){
                maxAmount = amount;
            }
            data.put("value",amount);
            datas.add(data);
        }
        putContext("groupList", JSON.toJSONString(groupList));
        putContext("datas",JSON.toJSONString(datas));
        putContext("maxAmount",maxAmount);
        return "saleChartByPie";
    }

}
