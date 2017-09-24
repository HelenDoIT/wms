package com._520it.wms.service.impl;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.mapper.StockIncomeBillItemMapper;
import com._520it.wms.mapper.StockIncomeBillMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.IStockIncomeBillService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * Created by Helen MM on 2017/8/21.
 */
public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
    @Setter
    private StockIncomeBillMapper stockIncomeBillMapper;
    @Setter
    private StockIncomeBillItemMapper stockIncomeBillItemMapper;
    @Setter
    private IProductStockService productStockService;

    public void save(StockIncomeBill bill) {
        //1 设置录入信息
        bill.setInputTime(new Date());
        bill.setInputUser(UserContext.getCurrentUser());
        //2 设置状态为未审核
        bill.setStatus(StockIncomeBill.NORMAL);
        //3 计算总金额和总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for (StockIncomeBillItem item : bill.getItems()) {
            BigDecimal amount = item.getCostPrice().multiply(item.getNumber()).setScale(2, RoundingMode.HALF_UP);
            //4 为明细设置金额小计
            item.setAmount(amount);
            totalAmount=totalAmount.add(amount);
            totalNumber=totalNumber.add(item.getNumber());
        }
        //5 设置总金额与总数量
        bill.setTotalAmount(totalAmount);
        bill.setTotalNumber(totalNumber);
        //6 保存采购订单
        stockIncomeBillMapper.insert(bill);
        //7 为明细订单设置订单id, 并保存明细订单
        for (StockIncomeBillItem item : bill.getItems()) {
            item.setBillId(bill.getId());
            stockIncomeBillItemMapper.insert(item);
        }
    }

    public void delete(Long id) {
        //先删除明细,再删除订单
        stockIncomeBillItemMapper.deleteByBillId(id);
        stockIncomeBillMapper.deleteByPrimaryKey(id);
    }

    public void update(StockIncomeBill bill) {
        //查询当前订单对象, 判断状态为未审核
        StockIncomeBill stockIncomeBill = stockIncomeBillMapper.selectByPrimaryKey(bill.getId());
        if(stockIncomeBill !=  null && stockIncomeBill.getStatus() == StockIncomeBill.NORMAL){
        //先删除明细,再获取页面传来的明细信息进行保存 再更新订单
            stockIncomeBillItemMapper.deleteByBillId(bill.getId());
        // 计算总金额和总数量
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal totalNumber = BigDecimal.ZERO;
            for (StockIncomeBillItem item : bill.getItems()) {
                BigDecimal amount = item.getCostPrice().multiply(item.getNumber()).setScale(2,RoundingMode.HALF_UP);
            // 为明细设置金额小计
                item.setAmount(amount);
                item.setBillId(bill.getId());
                //保存明细对象
                stockIncomeBillItemMapper.insert(item);

                totalAmount = totalAmount.add(amount);
                totalNumber = totalNumber.add(item.getNumber());
            }
        // 设置总金额与总数量
            bill.setTotalAmount(totalAmount);
            bill.setTotalNumber(totalNumber);
            stockIncomeBillMapper.updateByPrimaryKey(bill);
        }
    }

    public StockIncomeBill get(Long id) {
        StockIncomeBill stockIncomeBill = stockIncomeBillMapper.selectByPrimaryKey(id);
        return stockIncomeBill;
    }

    public PageResult queryPageResult(QueryObject qo) {
        Integer totalCount = stockIncomeBillMapper.queryForCount(qo);
        if(totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<StockIncomeBill> data = stockIncomeBillMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }

    @Override
    public void audit(Long id) {
//        1 查询出入库单, 判断是否为未审核
        StockIncomeBill bill = stockIncomeBillMapper.selectByPrimaryKey(id);
        if (bill != null && bill.getStatus() == StockIncomeBill.NORMAL) {
            //2 设置审核新: 审核人,审核时间, 审核状态
            bill.setAuditor(UserContext.getCurrentUser());
            bill.setAuditTime(new Date());
            bill.setStatus(StockIncomeBill.AUDITED);
            //3 更新订单审核信息
            stockIncomeBillMapper.audit(bill);
            //-----------------库存操作--------------
            productStockService.stockIncome(bill);
        }
    }
}
