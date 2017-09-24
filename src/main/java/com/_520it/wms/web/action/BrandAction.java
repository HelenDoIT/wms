package com._520it.wms.web.action;

import com._520it.wms.domain.Brand;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class BrandAction extends BaseAction {
    @Setter
    private IBrandService brandService;
    @Getter
    private Brand brand = new Brand();
    @Getter
    private QueryObject qo = new QueryObject();
    @RequiredPermission("品牌列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        PageResult pageResult = brandService.queryPageResult(qo);
        putContext("pageResult",pageResult);
        return LIST;
    }
    @RequiredPermission("品牌删除")
    public String delete() throws Exception {
        try {
            brandService.delete(brand.getId());
            putJson("品牌删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("品牌删除失败,请重试");
        }
        return  NONE;
    }
    @RequiredPermission("品牌保存更新")
    public String saveOrUpdate() throws Exception {
        try {
            if(brand.getId() != null){
                brandService.update(brand);
                addActionMessage("品牌修改成功");
            }else {
                brandService.save(brand);
                addActionMessage("品牌新增成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败,请重试");
        }
        return SUCCESS;
    }
    @RequiredPermission("品牌编辑")
    public String input() throws Exception {
        if(brand.getId() != null){
            brand =  brandService.get(brand.getId());
        }
        return "input";
    }
}
