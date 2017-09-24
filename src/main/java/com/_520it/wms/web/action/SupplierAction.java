package com._520it.wms.web.action;

import com._520it.wms.domain.Supplier;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.query.SupplierQueryObject;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class SupplierAction extends BaseAction {
    @Setter
    private ISupplierService supplierService;
    @Getter
    private Supplier supplier = new Supplier();
    @Getter
    private QueryObject qo = new SupplierQueryObject();

    @RequiredPermission("供应商列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        PageResult pageResult = supplierService.queryPageResult(qo);
        putContext("pageResult",pageResult);
        return LIST;
    }
    @RequiredPermission("供应商删除")
    public String delete() throws Exception {
        try {
            supplierService.delete(supplier.getId());
            putJson("供应商删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("供应商删除失败,请重试");
        }
        return  NONE;
    }
    @RequiredPermission("供应商保存更新")
    public String saveOrUpdate() throws Exception {
        try {
            if(supplier.getId() != null){
                supplierService.update(supplier);
                addActionMessage("供应商修改成功");
            }else {
                supplierService.save(supplier);
                addActionMessage("供应商新增成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败,请重试");
        }
        return SUCCESS;
    }
    @RequiredPermission("供应商编辑")
    public String input() throws Exception {
        if(supplier.getId() != null){
            supplier =  supplierService.get(supplier.getId());
        }
        return "input";
    }
}
