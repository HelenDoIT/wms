package com._520it.wms.service.impl;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.domain.SystemMenuVO;
import com._520it.wms.mapper.SystemMenuMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class SystemMenuServiceImpl implements ISystemMenuService {
    @Setter
    private SystemMenuMapper systemMenuMapper;
    public void save(SystemMenu systemMenu) {
        systemMenuMapper.insert(systemMenu);
    }

    public void delete(Long id) {
        systemMenuMapper.deleteByPrimaryKey(id);
    }

    public void update(SystemMenu systemMenu) {
        systemMenuMapper.updateByPrimaryKey(systemMenu);
    }

    public SystemMenu selectByPrimaryKey(Long id) {
        SystemMenu systemMenu = systemMenuMapper.selectByPrimaryKey(id);
        return systemMenu;
    }

    public List<SystemMenu> listAll() {
        List<SystemMenu> list = systemMenuMapper.listAll();
        return list;
    }

    public List<SystemMenu> listChildMenu() {
        List<SystemMenu> list = systemMenuMapper.listChildMenu();
        return list;
    }

    @Override
    public List<SystemMenu> selectAll() {
        List<SystemMenu> list = systemMenuMapper.selectAll();
        return list;
    }

    public PageResult queryPageResult(QueryObject qo) {
        Integer totalCount = systemMenuMapper.queryForCount(qo);
        if(totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<SystemMenu> data = systemMenuMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }

    public List<SystemMenuVO> queryParentMenu(Long parentId) {
        List<SystemMenuVO> list = new ArrayList<>();
        //获取当前菜单的父级对象
        SystemMenu currentMenu = systemMenuMapper.selectByPrimaryKey(parentId);
        while (currentMenu != null){
            //获取当前菜单的父级对象的值并设置给 VO对象
            SystemMenuVO vo = new SystemMenuVO();
            vo.setId(currentMenu.getId());
            vo.setName(currentMenu.getName());
            list.add(vo);
            //如果当前菜单的父级对象不为空则获取父级对象的上一级对象, 赋值给currentMenu
            if(currentMenu.getParent()!=null){
                currentMenu = systemMenuMapper.selectByPrimaryKey(currentMenu.getParent().getId());
            }else {
                break;
            }
            //遍历出来的值顺序是 子目录 > 父目录  倒序回来
            Collections.reverse(list);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> queryMenuByParentSn(String parentSn) {
        //判断是否是管理员,根据parentSn查询即可
        Employee currentUser = UserContext.getCurrentUser();
        if(currentUser.isAdmin()){
            List<Map<String, Object>> list = systemMenuMapper.queryMenuByParentSn(parentSn);
            return list;
        }
        //如果不是管理员, 根据员工id和parentSn查询
        return systemMenuMapper.queryMenuByParentSnAndEmpId(parentSn,currentUser.getId());
    }
}

