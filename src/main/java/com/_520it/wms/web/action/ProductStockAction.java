package com._520it.wms.web.action;

import com._520it.wms.domain.ProductStock;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class ProductStockAction extends BaseAction {
    @Setter
    private IProductStockService productStockService;
    @Setter
    private IDepotService depotService;
    @Setter
    private IBrandService brandService;
    @Getter
    private ProductStock productStock = new ProductStock();
    @Getter
    private ProductStockQueryObject qo = new ProductStockQueryObject();

    @RequiredPermission("销售出库单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        QueryObject dqo = new QueryObject();
        dqo.setPageSize(-1);
        PageResult pageResult = productStockService.queryPageResult(qo);
        putContext("pageResult",pageResult);
        putContext("depots",depotService.queryPageResult(dqo).getData());
        putContext("brands",brandService.queryPageResult(dqo).getData());
        return LIST;
    }

}
