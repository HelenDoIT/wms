package com._520it.wms.mapper;

import com._520it.wms.domain.ProductStock;
import com._520it.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper {

    int insert(ProductStock record);

    ProductStock selectByProductIdAndDepotId(@Param("proId") Long proId, @Param("depotId") Long depotId);

    int updateByPrimaryKey(ProductStock record);

    int queryForCount(QueryObject qo);

    List<ProductStock> queryForList(QueryObject qo);
}