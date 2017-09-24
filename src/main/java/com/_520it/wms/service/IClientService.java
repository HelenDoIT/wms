package com._520it.wms.service;

import com._520it.wms.domain.Client;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

/**
 * Created by Helen MM on 2017/8/21.
 */
public interface IClientService {
    void save(Client client);
    void delete(Long id);
    void update(Client client);
    Client get(Long id);
    //分页查询
    PageResult queryPageResult(QueryObject qo);
}
