package com._520it.wms.web.action;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.domain.Supplier;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.OrderBillQueryObject;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class OrderBillAction extends BaseAction {
   
    @Setter
    private IOrderBillService orderBillService;
    @Setter
    private ISupplierService supplierService;
    @Getter
    private OrderBill orderBill = new OrderBill();
    @Getter
    private OrderBillQueryObject qo = new OrderBillQueryObject();

    @RequiredPermission("订单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        PageResult pageResult = orderBillService.queryPageResult(qo);
        putContext("pageResult",pageResult);
        List<Supplier> supplier = supplierService.listAll();
        putContext("supplier",supplier);
        return LIST;
    }

    @RequiredPermission("订单删除")
    public String delete() throws Exception {
        try {
            orderBillService.delete(orderBill.getId());
            putJson("订单删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("订单删除失败,请重试");
        }
        return  NONE;
    }

    @RequiredPermission("订单查看")
    public String view() throws Exception {
        if(orderBill.getId() != null){
            orderBill =  orderBillService.get(orderBill.getId());
        }
        return  "view";
    }

    @RequiredPermission("订单保存更新")
    public String saveOrUpdate() throws Exception {
        try {
            if(orderBill.getId() != null){
                orderBillService.update(orderBill);
                addActionMessage("订单修改成功");
            }else {
                orderBillService.save(orderBill);
                addActionMessage("订单新增成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败,请重试");
        }
        return SUCCESS;
    }
    @RequiredPermission("订单编辑")
    public String input() throws Exception {
        List<Supplier> supplier = supplierService.listAll();
        putContext("supplier",supplier);
        if(orderBill.getId() != null){
            orderBill =  orderBillService.get(orderBill.getId());
        }
        return "input";
    }

    @RequiredPermission("订单审核")
    public String audit() throws Exception {
        try {
            if(orderBill.getId() != null){
                orderBillService.audit(orderBill.getId());
                putJson("审核成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putJson("审核失败");
        }
        return NONE;
    }
}
