package com._520it.wms.service.impl;

import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.domain.StockOutcomeBillItem;
import com._520it.wms.mapper.StockOutcomeBillItemMapper;
import com._520it.wms.mapper.StockOutcomeBillMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.IStockOutcomeBillService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {
    @Setter
    private StockOutcomeBillMapper stockOutcomeBillMapper;
    @Setter
    private StockOutcomeBillItemMapper stockOutcomeBillItemMapper;
    @Setter
    private IProductStockService productStockService;
    @Setter
    private IClientService clientService;

    public void save(StockOutcomeBill bill) {
        //1 设置录入信息
        bill.setInputTime(new Date());
        bill.setInputUser(UserContext.getCurrentUser());
        //2 设置状态为未审核
        bill.setStatus(StockOutcomeBill.NORMAL);
        //3 计算总金额和总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        bill.getItems();
        for (StockOutcomeBillItem item : bill.getItems()) {
            BigDecimal amount = item.getSalePrice().multiply(item.getNumber()).setScale(2, RoundingMode.HALF_UP);
            //4 为明细设置金额小计
            item.setAmount(amount);
            totalAmount=totalAmount.add(amount);
            totalNumber=totalNumber.add(item.getNumber());
        }
        //5 设置总金额与总数量
        bill.setTotalAmount(totalAmount);
        bill.setTotalNumber(totalNumber);
        //6 保存采购订单
        stockOutcomeBillMapper.insert(bill);
        //7 为明细订单设置订单id, 并保存明细订单
        for (StockOutcomeBillItem item : bill.getItems()) {
            item.setBillId(bill.getId());
            stockOutcomeBillItemMapper.insert(item);
        }
    }

    public void delete(Long id) {
        //先删除明细,再删除订单
        stockOutcomeBillItemMapper.deleteByBillId(id);
        stockOutcomeBillMapper.deleteByPrimaryKey(id);
    }

    public void update(StockOutcomeBill bill) {
        //查询当前订单对象, 判断状态为未审核
        StockOutcomeBill stockOutcomeBill = stockOutcomeBillMapper.selectByPrimaryKey(bill.getId());
        if(stockOutcomeBill !=  null && stockOutcomeBill.getStatus() == StockOutcomeBill.NORMAL){
        //先删除明细,再获取页面传来的明细信息进行保存 再更新订单
            stockOutcomeBillItemMapper.deleteByBillId(bill.getId());
        // 计算总金额和总数量
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal totalNumber = BigDecimal.ZERO;
            for (StockOutcomeBillItem item : bill.getItems()) {
                BigDecimal amount = item.getSalePrice().multiply(item.getNumber()).setScale(2,RoundingMode.HALF_UP);
            // 为明细设置金额小计
                item.setAmount(amount);
                item.setBillId(bill.getId());
                //保存明细对象
                stockOutcomeBillItemMapper.insert(item);

                totalAmount = totalAmount.add(amount);
                totalNumber = totalNumber.add(item.getNumber());
            }
        // 设置总金额与总数量
            bill.setTotalAmount(totalAmount);
            bill.setTotalNumber(totalNumber);
            stockOutcomeBillMapper.updateByPrimaryKey(bill);
        }
    }

    public StockOutcomeBill get(Long id) {
        StockOutcomeBill stockOutcomeBill = stockOutcomeBillMapper.selectByPrimaryKey(id);
        return stockOutcomeBill;
    }

    public PageResult queryPageResult(QueryObject qo) {
        Integer totalCount = stockOutcomeBillMapper.queryForCount(qo);
        if(totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<StockOutcomeBill> data = stockOutcomeBillMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }

    @Override
    public void audit(Long id) {
        // 1 查询出订单对象, 判断是否为未审核
        StockOutcomeBill bill = stockOutcomeBillMapper.selectByPrimaryKey(id);
        if (bill != null && bill.getStatus() == StockOutcomeBill.NORMAL) {
            //2 设置审核新: 审核人,审核时间, 审核状态
            bill.setAuditor(UserContext.getCurrentUser());
            bill.setAuditTime(new Date());
            bill.setStatus(StockOutcomeBill.AUDITED);
            //3 更新订单审核信息
            stockOutcomeBillMapper.audit(bill);
            //-----------------库存操作--------------
            productStockService.stockOutcome(bill);
        }
    }
}
