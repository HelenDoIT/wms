package com._520it.wms.service.impl;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.domain.OrderBillItem;
import com._520it.wms.mapper.OrderBillItemMapper;
import com._520it.wms.mapper.OrderBillMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class OrderBillServiceImpl implements IOrderBillService {
    @Setter
    private OrderBillMapper orderBillMapper;
    @Setter
    private OrderBillItemMapper orderBillItemMapper;

    public void save(OrderBill orderBill) {
        //System.out.println(orderBill);
        //1 设置录入信息
        orderBill.setInputTime(new Date());
        orderBill.setInputUser(UserContext.getCurrentUser());
        //2 设置状态为未审核
        orderBill.setStatus(OrderBill.NORMAL);
        //3 计算总金额和总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for (OrderBillItem item : orderBill.getItems()) {
            BigDecimal amount = item.getCostPrice().multiply(item.getNumber()).setScale(2, RoundingMode.HALF_UP);
            //4 为明细设置金额小计
            item.setAmount(amount);
            totalAmount=totalAmount.add(amount);
            totalNumber=totalNumber.add(item.getNumber());
        }
        //5 设置总金额与总数量
        orderBill.setTotalAmount(totalAmount);
        orderBill.setTotalNumber(totalNumber);
        //6 保存采购订单
        orderBillMapper.insert(orderBill);
        //7 为明细订单设置订单id, 并保存明细订单
        for (OrderBillItem item : orderBill.getItems()) {
            item.setBillId(orderBill.getId());
            orderBillItemMapper.insert(item);
        }
    }

    public void delete(Long id) {
        //先删除明细,再删除订单
        orderBillItemMapper.deleteByBillId(id);
        orderBillMapper.deleteByPrimaryKey(id);
    }

    public void update(OrderBill orderBill) {
        //查询当前订单对象, 判断状态为未审核
        OrderBill orderBill1BD = orderBillMapper.selectByPrimaryKey(orderBill.getId());
        if(orderBill1BD != null && orderBill1BD.getStatus() == OrderBill.NORMAL){
        //线删除明细,再获取页面传来的明细信息进行保存 再更新订单
        orderBillItemMapper.deleteByBillId(orderBill.getId());
        // 计算总金额和总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for (OrderBillItem item : orderBill.getItems()) {
            BigDecimal amount = item.getCostPrice().multiply(item.getNumber()).setScale(2, RoundingMode.HALF_UP);
            // 为明细设置金额小计
            item.setAmount(amount);
            totalAmount=totalAmount.add(amount);
            totalNumber=totalNumber.add(item.getNumber());
        }
        // 设置总金额与总数量
        orderBill.setTotalAmount(totalAmount);
        orderBill.setTotalNumber(totalNumber);
        orderBillMapper.updateByPrimaryKey(orderBill);
        }
    }

    public OrderBill get(Long id) {
        OrderBill orderBill = orderBillMapper.selectByPrimaryKey(id);
        return orderBill;
    }

    public PageResult queryPageResult(QueryObject qo) {
        Integer totalCount = orderBillMapper.queryForCount(qo);
        if(totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<OrderBill> data = orderBillMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }

    @Override
    public void audit(Long id) {
        OrderBill orderBill = orderBillMapper.selectByPrimaryKey(id);
        if(orderBill != null && orderBill.getStatus() == OrderBill.NORMAL){
            orderBill.setAuditor(UserContext.getCurrentUser());
            orderBill.setAuditTime(new Date());
            orderBill.setStatus(OrderBill.AUDITED);
            orderBillMapper.audit(orderBill);
        }
    }
}
