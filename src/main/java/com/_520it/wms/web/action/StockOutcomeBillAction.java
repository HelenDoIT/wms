package com._520it.wms.web.action;

import com._520it.wms.domain.Depot;
import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.query.StockOutcomeBillQueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockOutcomeBillService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class StockOutcomeBillAction extends BaseAction {
    @Setter
    private IStockOutcomeBillService stockOutcomeBillService;
    @Setter
    private IDepotService depotService;
    @Setter
    private IClientService clientService;
    @Getter
    private StockOutcomeBill stockOutcomeBill = new StockOutcomeBill();
    @Getter
    private StockOutcomeBillQueryObject qo = new StockOutcomeBillQueryObject();

    @RequiredPermission("销售出库单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        QueryObject dqo = new QueryObject();
        dqo.setPageSize(-1);
        PageResult pageResult = stockOutcomeBillService.queryPageResult(qo);
        putContext("pageResult",pageResult);
        putContext("depots",depotService.queryPageResult(dqo).getData());
        putContext("clients",clientService.queryPageResult(dqo).getData());
        return LIST;
    }

    @RequiredPermission("销售出库单删除")
    public String delete() throws Exception {
        try {
            stockOutcomeBillService.delete(stockOutcomeBill.getId());
            putJson("销售出库单删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("销售出库单删除失败,请重试");
        }
        return  NONE;
    }

    @RequiredPermission("销售出库单查看")
    public String view() throws Exception {
        if(stockOutcomeBill.getId() != null){
            stockOutcomeBill =  stockOutcomeBillService.get(stockOutcomeBill.getId());
        }
        return  "view";
    }

    @RequiredPermission("销售出库单保存更新")
    public String saveOrUpdate() throws Exception {
        try {
            if(stockOutcomeBill.getId() != null){
                stockOutcomeBillService.update(stockOutcomeBill);
                addActionMessage("销售出库单修改成功");
            }else {
                System.out.println(stockOutcomeBill);
                stockOutcomeBillService.save(stockOutcomeBill);
                addActionMessage("销售出库单新增成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败,请重试");
        }
        return SUCCESS;
    }
    @RequiredPermission("销售出库单编辑")
    public String input() throws Exception {
        List<Depot> depots = depotService.listAll();
        putContext("depots",depots);
        putContext("clients",clientService.queryPageResult(qo).getData());
        if(stockOutcomeBill.getId() != null){
            stockOutcomeBill =  stockOutcomeBillService.get(stockOutcomeBill.getId());
        }
        return "input";
    }

    @RequiredPermission("销售出库单审核")
    public String audit() throws Exception {
        try {
            if(stockOutcomeBill.getId() != null){
                stockOutcomeBillService.audit(stockOutcomeBill.getId());
                putJson("审核成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putJson(e.getMessage());
        }
        return NONE;
    }
}
