package com._520it.wms.service.impl;

import com._520it.wms.domain.*;
import com._520it.wms.mapper.ProductStockMapper;
import com._520it.wms.mapper.SaleAccountMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductStockService;
import lombok.Setter;

import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * Created by Helen MM on 2017/9/12.
 */
public class ProductStockServiceImpl implements IProductStockService{
    @Setter
    private ProductStockMapper productStockMapper;
    @Setter
    private SaleAccountMapper saleAccountMapper;

    @Override
    public void stockIncome(StockIncomeBill bill) {
        //4 根据货品 id 和 仓库 id 获取出一条明细
        for (StockIncomeBillItem item : bill.getItems()) {
            Long productId = item.getProduct().getId();
            Long depotId =  bill.getDepot().getId();
            ProductStock ps = productStockMapper.selectByProductIdAndDepotId(item.getProduct().getId(), bill.getDepot().getId());
            //5 判断获取的库存明细是否为空
            if(ps != null){
                //6 不空则修改明细, 计算库存价格, 累加库存数量,计算库存金额修改库存明细
                ps.setStoreNumber(ps.getStoreNumber().add(item.getNumber()));
                ps.setAmount(ps.getAmount().add(item.getAmount()));
                ps.setPrice(ps.getAmount().divide(ps.getStoreNumber(),2, RoundingMode.HALF_UP));
                ps.setIncomeDate(new Date());
                productStockMapper.updateByPrimaryKey(ps);
            }else{
                //7 空则新建一条库存明细, 保存入库
                ps = new ProductStock();
                ps.setPrice(item.getCostPrice());
                ps.setStoreNumber(item.getNumber());
                ps.setAmount(item.getAmount());
                ps.setIncomeDate(new Date());
                ps.setProduct(item.getProduct());
                ps.setDepot(bill.getDepot());
                productStockMapper.insert(ps);
            }
        }
    }

    @Override
    public void stockOutcome(StockOutcomeBill bill) {
        for (StockOutcomeBillItem item : bill.getItems()) {
            //4 根据货品 id + 仓库id 查询出库存明细对象
            ProductStock ps = productStockMapper.selectByProductIdAndDepotId(item.getProduct().getId(), bill.getDepot().getId());
            if(ps == null){
                //如果明细对象为空 则提示在该仓库中没有该货品
                throw new RuntimeException(bill.getDepot().getName()+"中没有"+item.getProduct().getId()+"号"+item.getProduct().getName()+"商品");
            }else if(ps.getStoreNumber().compareTo(item.getNumber())<0){
                //如果明细中货品数量小于出库数量, 则提示货存不足
                throw new RuntimeException(bill.getDepot().getName()+"中商品"+item.getProduct().getName()+"数量["+ps.getStoreNumber()+"]不足出库");
            }else{
                //否则 减去库存数量, 计算库存金额
                ps.setStoreNumber(ps.getStoreNumber().subtract(item.getNumber()));
                ps.setAmount(ps.getStoreNumber().multiply(ps.getPrice()).setScale(2,RoundingMode.HALF_UP));
                ps.setOutcomeDate(new Date());
                //更新货存信息
                productStockMapper.updateByPrimaryKey(ps) ;

                //记账操作
                SaleAccount sa = new SaleAccount();
                sa.setVdate(bill.getVdate());
                sa.setNumber(item.getNumber());
                sa.setCostPrice(ps.getPrice());
                sa.setCostAmount(sa.getCostPrice().multiply(sa.getCostAmount()).setScale(2,RoundingMode.HALF_UP));
                sa.setSalePrice(item.getSalePrice());
                sa.setSaleAmount(sa.getSalePrice().multiply(sa.getNumber()).setScale(2,RoundingMode.HALF_UP));
                sa.setClientId(bill.getClient().getId());
                sa.setSalemanId(bill.getInputUser().getId());
                sa.setProductId(item.getProduct().getId());
                saleAccountMapper.insert(sa);
            }
        }
    }

    @Override
    public PageResult queryPageResult(QueryObject qo) {
        Integer totalCount = productStockMapper.queryForCount(qo);
        if(totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<ProductStock> data = productStockMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }
}
