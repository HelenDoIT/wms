package com._520it.wms.service.impl;

import com._520it.wms.domain.Permission;
import com._520it.wms.mapper.PermissionMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.util.RequiredPermission;
import com._520it.wms.web.action.BaseAction;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by Helen MM on 2017/8/22.
 */
public class PermissionServiceImpl implements IPermissionService,ApplicationContextAware {
    @Setter
    private PermissionMapper permissionMapper;

    private ApplicationContext ctx;

    public void save(Permission p) {
        permissionMapper.save(p);
    }

    public void delete(Long id) {
        permissionMapper.delete(id);
    }

    public List<Permission> listAll() {
        List<Permission> permissions = permissionMapper.listAll();
        return permissions;
    }

    public PageResult queryPageResult(QueryObject qo) {
        Integer totalCount = permissionMapper.queryTotalCount(qo);
        if(totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Permission> data = permissionMapper.queryList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }

    public void reload() {
        //取出数据库中所有的权限表达式
        List<String> exps = permissionMapper.listExpressions();
//        扫描所有继承于BaseAction的类
        Map<String, BaseAction> actionMap = ctx.getBeansOfType(BaseAction.class);
//        迭代每一个Action类,并获取Action类中的每一个方法对象
        for (BaseAction actionObject : actionMap.values()) {
            Method[] methods = actionObject.getClass().getMethods();
//        迭代每一个Action方法, 并判断是否RequirePermission注解标注了
            for (Method method : methods) {
                RequiredPermission rp = method.getAnnotation(RequiredPermission.class);
                String exp=actionObject.getClass().getName()+":"+method.getName();
                if(!exps.contains(exp)){
                    if(rp != null){
    //        若是: 则创建一个Permission对象, 设置属性, 并保存到数据库中
                        Permission p = new Permission();
                        p.setName(rp.value());//权限名称
                        p.setExpression(exp);
                        permissionMapper.save(p);
                    }
                }
            }
        }
    }

    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx=ctx;
    }
}
