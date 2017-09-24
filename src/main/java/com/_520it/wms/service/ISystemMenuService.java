package com._520it.wms.service;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.domain.SystemMenuVO;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Helen MM on 2017/8/21.
 */
public interface ISystemMenuService {
    void save(SystemMenu systemMenu);
    void delete(Long id);
    void update(SystemMenu systemMenu);
    SystemMenu selectByPrimaryKey(Long id);
    List<SystemMenu> listAll();
    List<SystemMenu> selectAll();
    //分页查询
    PageResult queryPageResult(QueryObject qo);

    List<SystemMenuVO> queryParentMenu(Long parentId);

    List<Map<String,Object>> queryMenuByParentSn(String parentSn);
}
