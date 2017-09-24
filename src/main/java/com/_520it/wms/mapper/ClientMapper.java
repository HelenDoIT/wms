package com._520it.wms.mapper;

import com._520it.wms.domain.Client;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface ClientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Client record);

    Client selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Client record);

    int queryForCount(QueryObject qo);
    List<Client> queryResult(QueryObject qo);
}