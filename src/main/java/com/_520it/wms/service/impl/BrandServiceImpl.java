package com._520it.wms.service.impl;

import com._520it.wms.domain.Brand;
import com._520it.wms.mapper.BrandMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IBrandService;
import lombok.Setter;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class BrandServiceImpl implements IBrandService {
    @Setter
    private BrandMapper brandMapper;
    public void save(Brand brand) {
        brandMapper.insert(brand);
    }

    public void delete(Long id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    public void update(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    public Brand get(Long id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }

    public List<Brand> listAll() {
        List<Brand> list = brandMapper.selectAll();
        return list;
    }

    public PageResult queryPageResult(QueryObject qo) {
        Integer totalCount = brandMapper.queryForCount(qo);
        if(totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Brand> data = brandMapper.queryResult(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }
}
