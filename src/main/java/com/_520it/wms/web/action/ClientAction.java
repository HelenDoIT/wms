package com._520it.wms.web.action;

import com._520it.wms.domain.Client;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class ClientAction extends BaseAction {
    @Setter
    private IClientService clientService;
    @Getter
    private Client client = new Client();
    @Getter
    private QueryObject qo = new QueryObject();

    @RequiredPermission("客户列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        PageResult pageResult = clientService.queryPageResult(qo);
        putContext("pageResult",pageResult);
        return LIST;
    }
    @RequiredPermission("客户删除")
    public String delete() throws Exception {
        try {
            clientService.delete(client.getId());
            putJson("客户删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("客户删除失败,请重试");
        }
        return  NONE;
    }
    @RequiredPermission("客户保存更新")
    public String saveOrUpdate() throws Exception {
        try {
            if(client.getId() != null){
                clientService.update(client);
                addActionMessage("客户修改成功");
            }else {
                clientService.save(client);
                addActionMessage("客户新增成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败,请重试");
        }
        return SUCCESS;
    }
    @RequiredPermission("客户编辑")
    public String input() throws Exception {
        if(client.getId() != null){
            client =  clientService.get(client.getId());
        }
        return "input";
    }
}
