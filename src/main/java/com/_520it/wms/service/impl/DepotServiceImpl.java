package com._520it.wms.service.impl;

import com._520it.wms.domain.Depot;
import com._520it.wms.mapper.DepotMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepotService;
import lombok.Setter;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class DepotServiceImpl implements IDepotService {
    @Setter
    private DepotMapper depotMapper;
    public void save(Depot depot) {
        depotMapper.insert(depot);
    }

    public void delete(Long id) {
        depotMapper.deleteByPrimaryKey(id);
    }

    public void update(Depot depot) {
        depotMapper.updateByPrimaryKey(depot);
    }

    public Depot get(Long id) {
        Depot depot = depotMapper.selectByPrimaryKey(id);
        return depot;
    }

    public List<Depot> listAll() {
        List<Depot> list = depotMapper.selectAll();
        return list;
    }

    public PageResult queryPageResult(QueryObject qo) {
        Integer totalCount = depotMapper.queryForCount(qo);
        if(totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Depot> data = depotMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }
}
