package com._520it.wms.web.action;

import com._520it.wms.domain.Depot;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class DepotAction extends BaseAction {
    @Setter
    private IDepotService depotService;
    @Getter
    private Depot depot = new Depot();
    @Getter
    private QueryObject qo = new QueryObject();
    @RequiredPermission("仓库列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        PageResult pageResult = depotService.queryPageResult(qo);
        putContext("pageResult",pageResult);
        return LIST;
    }
    @RequiredPermission("仓库删除")
    public String delete() throws Exception {
        try {
            depotService.delete(depot.getId());
            putJson("仓库删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("仓库删除失败,请重试");
        }
        return  NONE;
    }
    @RequiredPermission("仓库保存更新")
    public String saveOrUpdate() throws Exception {
        try {
            if(depot.getId() != null){
                depotService.update(depot);
                addActionMessage("仓库修改成功");
            }else {
                depotService.save(depot);
                addActionMessage("仓库新增成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败,请重试");
        }
        return SUCCESS;
    }
    @RequiredPermission("仓库编辑")
    public String input() throws Exception {
        if(depot.getId() != null){
            depot =  depotService.get(depot.getId());
        }
        return "input";
    }
}
