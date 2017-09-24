package com._520it.wms.service.impl;

import com._520it.wms.domain.Client;
import com._520it.wms.mapper.ClientMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IClientService;
import lombok.Setter;

import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class ClientServiceImpl implements IClientService {
    @Setter
    private ClientMapper clientMapper;

    public void save(Client client) {
        clientMapper.insert(client);
    }

    public void delete(Long id) {
        clientMapper.deleteByPrimaryKey(id);
    }

    public void update(Client client) {
        clientMapper.updateByPrimaryKey(client);
    }

    public Client get(Long id) {
        Client client = clientMapper.selectByPrimaryKey(id);
        return client;
    }

    public PageResult queryPageResult(QueryObject qo) {
        Integer totalCount = clientMapper.queryForCount(qo);
        if(totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Client> data = clientMapper.queryResult(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }
}
