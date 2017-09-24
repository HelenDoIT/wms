package com._520it.wms.web.action;

import com._520it.wms.domain.Depot;
import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.query.StockIncomeBillQueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockIncomeBillService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class StockIncomeBillAction extends BaseAction {
    @Setter
    private IStockIncomeBillService stockIncomeBillService;
    @Setter
    private IDepotService depotService;
    @Getter
    private StockIncomeBill stockIncomeBill = new StockIncomeBill();
    @Getter
    private StockIncomeBillQueryObject qo = new StockIncomeBillQueryObject();

    @RequiredPermission("采购入库单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        QueryObject dqo = new QueryObject();
        dqo.setPageSize(-1);
        PageResult pageResult = stockIncomeBillService.queryPageResult(qo);
        putContext("pageResult",pageResult);
        putContext("depots",depotService.queryPageResult(dqo).getData());
        return LIST;
    }

    @RequiredPermission("采购入库单删除")
    public String delete() throws Exception {
        try {
            stockIncomeBillService.delete(stockIncomeBill.getId());
            putJson("采购入库单删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("采购入库单删除失败,请重试");
        }
        return  NONE;
    }

    @RequiredPermission("采购入库单查看")
    public String view() throws Exception {
        if(stockIncomeBill.getId() != null){
            stockIncomeBill =  stockIncomeBillService.get(stockIncomeBill.getId());
        }
        return  "view";
    }

    @RequiredPermission("采购入库单保存更新")
    public String saveOrUpdate() throws Exception {
        try {
            if(stockIncomeBill.getId() != null){
                stockIncomeBillService.update(stockIncomeBill);
                addActionMessage("采购入库单修改成功");
            }else {
                System.out.println(stockIncomeBill);
                stockIncomeBillService.save(stockIncomeBill);
                addActionMessage("采购入库单新增成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败,请重试");
        }
        return SUCCESS;
    }
    @RequiredPermission("采购入库单编辑")
    public String input() throws Exception {
        List<Depot> depots = depotService.listAll();
        putContext("depots",depots);
        if(stockIncomeBill.getId() != null){
            stockIncomeBill =  stockIncomeBillService.get(stockIncomeBill.getId());
        }
        return "input";
    }

    @RequiredPermission("采购入库单审核")
    public String audit() throws Exception {
        try {
            if(stockIncomeBill.getId() != null){
                stockIncomeBillService.audit(stockIncomeBill.getId());
                putJson("审核成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putJson("审核失败");
        }
        return NONE;
    }
}
