package com._520it.wms.service.impl;

import com._520it.wms.domain.Supplier;
import com._520it.wms.mapper.SupplierMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.ISupplierService;
import lombok.Setter;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class SupplierServiceImpl implements ISupplierService {
    @Setter
    private SupplierMapper supplierMapper;
    public void save(Supplier supplier) {
        supplierMapper.insert(supplier);
    }

    public void delete(Long id) {
        supplierMapper.deleteByPrimaryKey(id);
    }

    public void update(Supplier supplier) {
        supplierMapper.updateByPrimaryKey(supplier);
    }

    public Supplier get(Long id) {
        Supplier supplier = supplierMapper.selectByPrimaryKey(id);
        return supplier;
    }

    public List<Supplier> listAll() {
        List<Supplier> list = supplierMapper.selectAll();
        return list;
    }

    public PageResult queryPageResult(QueryObject qo) {
        Integer totalCount = supplierMapper.queryForCount(qo);
        if(totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Supplier> data = supplierMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }
}
