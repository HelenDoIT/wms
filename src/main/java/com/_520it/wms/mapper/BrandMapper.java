package com._520it.wms.mapper;

import com._520it.wms.domain.Brand;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Brand brand);

    Brand selectByPrimaryKey(Long id);

    List<Brand> selectAll();

    int updateByPrimaryKey(Brand brand);

    int queryForCount(QueryObject qo);
    List<Brand> queryResult(QueryObject qo);
}