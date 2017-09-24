package com._520it.wms.service.impl;

import com._520it.wms.domain.Product;
import com._520it.wms.mapper.ProductMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductService;
import lombok.Setter;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class ProductServiceImpl implements IProductService {
    @Setter
    private ProductMapper productMapper;
    public void save(Product product) {
        productMapper.insert(product);
    }

    public void delete(Long id) {
        productMapper.deleteByPrimaryKey(id);
    }

    public void update(Product product) {
        productMapper.updateByPrimaryKey(product);
    }

    public Product get(Long id) {
        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }

    public PageResult queryPageResult(QueryObject qo) {
        Integer totalCount = productMapper.queryForCount(qo);
        if(totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Product> data = productMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }
}
