package com._520it.wms.web.action;

import com._520it.wms.domain.Brand;
import com._520it.wms.domain.Product;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IProductService;
import com._520it.wms.util.FileUploadUtil;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class ProductAction extends BaseAction {
    @Setter
    private File pic;
    @Setter
    private String picFileName;
    @Setter
    private IProductService productService;
    @Setter
    private IBrandService brandService;
    @Getter
    private Product product = new Product();
    @Getter
    private ProductQueryObject qo = new ProductQueryObject();

    @RequiredPermission("商品列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        PageResult pageResult = productService.queryPageResult(qo);
        putContext("pageResult",pageResult);
        List<Brand> brands = brandService.listAll();
        putContext("brands",brands);
        return LIST;
    }

    @RequiredPermission("选择商品列表")
    public String selectProductList() throws Exception {
        PageResult pageResult = productService.queryPageResult(qo);
        putContext("pageResult",pageResult);
        return "selectProductList";
    }

    @RequiredPermission("商品删除")
    public String delete() throws Exception {
        try {
            //页面删除要带 imagePath 参数
            FileUploadUtil.deleteFile(product.getImagePath());
            productService.delete(product.getId());
            putJson("商品删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putJson("商品删除失败,请重试");
        }
        return  NONE;
    }


    @RequiredPermission("商品保存更新")
    public String saveOrUpdate() throws Exception {
        try {
            //如果imagePath不为空,而且图片文件不为空, 要删除原来的图片
            if(StringUtils.isNotEmpty(product.getImagePath()) && pic!=null){
                FileUploadUtil.deleteFile(product.getImagePath());
            }
            if(pic!=null){
                String imagePath = FileUploadUtil.uploadFile(pic, picFileName);
                product.setImagePath(imagePath);
            }
            if(product.getId() != null){
                productService.update(product);
                addActionMessage("商品修改成功");
            }else {
                productService.save(product);
                addActionMessage("商品新增成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败,请重试");
        }
        return SUCCESS;
    }
    @RequiredPermission("商品编辑")
    public String input() throws Exception {
        List<Brand> brands = brandService.listAll();
        putContext("brands",brands);
        if(product.getId() != null){
            product =  productService.get(product.getId());
        }
        return "input";
    }
}
