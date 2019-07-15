package com.gd.data.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gd.data.entity.*;
import com.gd.data.mapper.*;
import com.gd.data.service.UpdateDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: He Wenbo
 * @Date: 2019/6/25
 * @Description:
 */
@Service
public class UpdateDateServiceImpl implements UpdateDateService {

    @Autowired
    private TSysUserMapper tSysUserMapper;

    @Autowired
    private TItemBrandMapper tItemBrandMapper;

    @Autowired
    private TItemClassMapper tItemClassMapper;

    @Autowired
    private TSupInfoMapper tSupInfoMapper;

    @Autowired
    private TDataPaymentMapper tDataPaymentMapper;

    @Autowired
    private TVisaFlowMapper tVisaFlowMapper;

    @Autowired
    private TPosSaleMasterMapper tPosSaleMasterMapper;

    @Autowired
    private TVipFlowMapper tVipFlowMapper;

    @Autowired
    private TVipInfoMapper tVipInfoMapper;

    @Autowired
    private TItemInfoMapper tItemInfoMapper;

    @Autowired
    private TBranchfoMapper tBranchfoMapper;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Date rechargeRecordDate = null;
    private static Date saleDetailDate = null;
    private static Date carPayDate = null;
    private static Date itemInfoDate = null;
    private static Date orderDate = null;

    @Override
    public String queryCashierList() {
        List<TSysUser> tSysUsers = tSysUserMapper.selectCashierList();
        JSONArray cashiers = new JSONArray();
        for (TSysUser user : tSysUsers) {
            JSONArray cashier = new JSONArray();
            cashier.add(user.getUserId());
            cashier.add(user.getUserName());
            cashier.add(user.getBranchNo().length() > 2 ? user.getBranchNo().substring(0,2): user.getBranchNo());
            cashiers.add(cashier);
        }
        return JSONObject.toJSONString(cashiers);
    }

    @Override
    public String queryBrandList() {
        List<TItemBrand> tItemBrands = tItemBrandMapper.selectBrandList();
        JSONArray brands = new JSONArray();
        for (TItemBrand tItemBrand : tItemBrands) {
            JSONArray brand = new JSONArray();
            brand.add(tItemBrand.getItemBrand());
            brand.add(tItemBrand.getBrandName());
            brands.add(brand);
        }
        return JSONObject.toJSONString(brands);
    }

    @Override
    public String queryModeItemCls() {
        List<TItemClass> itemClasses = tItemClassMapper.selectItemCls();
        JSONArray tItemClasses = new JSONArray();
        for (TItemClass tItemClass : itemClasses) {
            JSONArray itemCls = new JSONArray();
            itemCls.add(tItemClass.getItemClass());
            itemCls.add(tItemClass.getClassName());
            if(tItemClass.getItemClass().length() > 2) {
                itemCls.add(tItemClass.getItemClass().substring(0, tItemClass.getItemClass().length()-2));
            }else{
                itemCls.add("");
            }
            tItemClasses.add(itemCls);
        }
        return JSONObject.toJSONString(tItemClasses);
    }

    @Override
    public String queryModeSaleMan() {
        List<TSysUser> tSysUsers= tSysUserMapper.selectSaleManList();
        JSONArray saleMans = new JSONArray();
        for (TSysUser user : tSysUsers) {
            JSONArray saleMan = new JSONArray();
            saleMan.add(user.getUserId());
            saleMan.add(user.getUserName());
            saleMans.add(saleMan);
        }
        return JSONObject.toJSONString(saleMans);
    }

    @Override
    public String querySupcustInfo() {
        List<TSupInfo> tSupInfos= tSupInfoMapper.selectSupcust();
        JSONArray supcusts = new JSONArray();
        for (TSupInfo tSupInfo : tSupInfos) {
            JSONArray supcust = new JSONArray();
            supcust.add(tSupInfo.getSupNo());
            supcust.add(tSupInfo.getSupName());
            supcust.add(tSupInfo.getSupMan());
            supcust.add(tSupInfo.getSupAddress());
            supcusts.add(supcust);
        }
        return JSONObject.toJSONString(supcusts);
    }

    @Override
    public String queryDataPayment() {
        List<TDataPayment> tDataPaymentList= tDataPaymentMapper.selectPayments();
        JSONArray tdataPayments = new JSONArray();
        for (TDataPayment payment : tDataPaymentList) {
            JSONArray tdataPayment = new JSONArray();
            tdataPayment.add(payment.getPaymentNo());
            tdataPayment.add(payment.getPaymentName());
            tdataPayments.add(tdataPayment);
        }
        return JSONObject.toJSONString(tdataPayments);
    }

    @Override
    public String queryVisaFlow() {
        List<TVisaFlow> visaFlows= tVisaFlowMapper.selectVisaFlows(rechargeRecordDate);
        JSONArray tVisaFlows = new JSONArray();
        for (TVisaFlow tVisaFlow : visaFlows) {
            JSONArray visaFlow = new JSONArray();
            visaFlow.add(tVisaFlow.getUpdateFlow().toString());
            visaFlow.add(tVisaFlow.getSubAmt().doubleValue());
            visaFlow.add(simpleDateFormat.format(tVisaFlow.getOperDate()));
            visaFlow.add(tVisaFlow.getBranchNo().length() > 2 ? tVisaFlow.getBranchNo().substring(0,2): tVisaFlow.getBranchNo());
            rechargeRecordDate = tVisaFlow.getOperDate();
            tVisaFlows.add(visaFlow);
        }
        return JSONObject.toJSONString(tVisaFlows);
    }

    @Override
    public String querySaleDetail() {
        List<TPosSale> tPosSales = tPosSaleMasterMapper.selectSaleDetail(saleDetailDate);
        JSONArray posSales = new JSONArray();
        for (TPosSale tPosSale : tPosSales) {
            JSONArray posSale = new JSONArray();
            posSale.add(tPosSale.getId());
            posSale.add("0");
            posSale.add(simpleDateFormat.format(tPosSale.getSaleTime()));
            if(tPosSale.getType().equals("1")) {
                posSale.add(tPosSale.getSaleAmt().doubleValue());
            }else{
                posSale.add(BigDecimal.ZERO.doubleValue());
            }
            posSale.add(tPosSale.getSaleQty().doubleValue());
            posSale.add(tPosSale.getSheetNo());
            posSale.add(tPosSale.getSaleBarcode());
            posSale.add(tPosSale.getSaleMan());
            posSale.add(tPosSale.getJh());
            if(tPosSale.getType().equals("1")) {
                posSale.add(BigDecimal.ZERO.doubleValue());
            }else{
                posSale.add(tPosSale.getGiveAmt().doubleValue());
            }
            posSale.add(tPosSale.getUserId());
            if(tPosSale.getType().equals("1")) {
                posSale.add("A");
            }else{
                posSale.add("C");
            }
            posSale.add(tPosSale.getBranchNo().length() > 2 ? tPosSale.getBranchNo().substring(0,2):tPosSale.getBranchNo());
            saleDetailDate = tPosSale.getSaleTime();
            posSales.add(posSale);
        }
        return JSONObject.toJSONString(posSales);
    }

    @Override
    public String queryCardPaylist() {
        List<TVipFlow> tVipFlowList = tVipFlowMapper.selectCarPayList(carPayDate);
        JSONArray tVipFlows = new JSONArray();
        for (TVipFlow tVipFlow : tVipFlowList) {
            JSONArray vipFlow = new JSONArray();
            vipFlow.add(tVipFlow.getUpdateFlow().toString());
            vipFlow.add(tVipFlow.getVipDispno());
            vipFlow.add(tVipFlow.getSalesAmt());
            vipFlow.add(simpleDateFormat.format(tVipFlow.getOperDate()));
            vipFlow.add(tVipFlow.getBranchNo().length() > 2 ? tVipFlow.getBranchNo().substring(0,2):tVipFlow.getBranchNo());
            carPayDate = tVipFlow.getOperDate();
            tVipFlows.add(vipFlow);
        }
        return JSONObject.toJSONString(tVipFlows);
    }

    @Override
    public String queryVipInfo() {
        List<TVipInfo> tVipInfoList = tVipInfoMapper.selectVipInfo();
        JSONArray tvipInfos = new JSONArray();
        for (TVipInfo tVipInfo : tVipInfoList) {
            JSONArray vipInfo = new JSONArray();
            vipInfo.add(tVipInfo.getVipDispno());
            vipInfo.add(tVipInfo.getVipName());
            vipInfo.add(tVipInfo.getVipMobil1());
            vipInfo.add(simpleDateFormat.format(tVipInfo.getCreateDate()));
            vipInfo.add(tVipInfo.getBranchNo().length() > 2 ? tVipInfo.getBranchNo().substring(0,2):tVipInfo.getBranchNo());
            tvipInfos.add(vipInfo);
        }
        return JSONObject.toJSONString(tvipInfos);
    }

    @Override
    public String queryItemInfo() {
        List<TitemStockInfo> infoList = tItemInfoMapper.selectItemInfo(itemInfoDate);
        JSONArray infos = new JSONArray();
        for (TitemStockInfo stockInfo : infoList) {
            JSONArray info = new JSONArray();
            info.add(stockInfo.getItemBarcode());
            info.add(stockInfo.getItemInPrice().floatValue());
            info.add(stockInfo.getStockQty());
            info.add(stockInfo.getItemName());
            info.add(stockInfo.getItemBrand());
            info.add(stockInfo.getItemClass());
            info.add(stockInfo.getItemNo());
            info.add(stockInfo.getItemSize());
            info.add(stockInfo.getItemUnitNo());
            info.add(simpleDateFormat.format(stockInfo.getModifyDate()));
            info.add(stockInfo.getItemSalePrice().floatValue());
            info.add(stockInfo.getItemVipPrice().floatValue());
            info.add(stockInfo.getItemSupNo());
            info.add(stockInfo.getBranchNo().length() > 2 ? stockInfo.getBranchNo().substring(0,2):stockInfo.getBranchNo());
            info.add(simpleDateFormat.format(stockInfo.getCreateTime()));
            itemInfoDate = stockInfo.getModifyDate();
            infos.add(info);
        }
        return JSONObject.toJSONString(infos);
    }

    @Override
    public String queryItemStock() {
        List<TitemStock> titemStockList = tItemInfoMapper.selectTitemStock();
        JSONArray stocks = new JSONArray();
        for (TitemStock titemStock : titemStockList) {
            JSONArray stock = new JSONArray();
            stock.add(titemStock.getItemBarcode());
            stock.add(titemStock.getRealQty().floatValue());
            stock.add(titemStock.getStockAmt().floatValue());
            stock.add(titemStock.getBranchNo().length() > 2 ? titemStock.getBranchNo().substring(0,2):titemStock.getBranchNo());
            stocks.add(stock);
        }
        return JSONObject.toJSONString(stocks);
    }

    @Override
    public String queryOrderDetail() {
        List<TOrderDetail> tOrderDetailList = tPosSaleMasterMapper.selectOrderDetail(orderDate);
        JSONArray tOrderDetails = new JSONArray();
        for (TOrderDetail detail : tOrderDetailList) {
            JSONArray tOrderDetail = new JSONArray();
            tOrderDetail.add(detail.getId());
            tOrderDetail.add(((detail.getId().substring(0,6))));
            tOrderDetail.add(detail.getId());
            tOrderDetail.add(detail.getPosTotalAmt().doubleValue());
            tOrderDetail.add(detail.getPaymentNo());
            tOrderDetail.add("A");
            tOrderDetail.add(simpleDateFormat.format(detail.getSaleTime()));
            tOrderDetail.add(detail.getPosTotalAmt().doubleValue());
            tOrderDetail.add(detail.getVipNo());
            tOrderDetail.add(detail.getDispNo());
            tOrderDetail.add(detail.getBranchNo().length() > 2 ? detail.getBranchNo().substring(0,2):detail.getBranchNo());
            orderDate = detail.getSaleTime();
            tOrderDetails.add(tOrderDetail);
        }
        return JSONObject.toJSONString(tOrderDetails);
    }

    @Override
    public String queryBranchInfoList() {
        List<TBranchfo> branchfos = tBranchfoMapper.selectBranchInfo();
        JSONArray tBranchfos = new JSONArray();
        for (TBranchfo tBranchfo : branchfos) {
            if(tBranchfo.getBranchNo().length() <= 2) {
                JSONArray branchinfo = new JSONArray();
                branchinfo.add(tBranchfo.getBranchName());
                branchinfo.add(tBranchfo.getBranchMan());
                branchinfo.add(tBranchfo.getBranchAddress());
                branchinfo.add(tBranchfo.getBranchNo());
                tBranchfos.add(branchinfo);
            }
        }
        return JSONObject.toJSONString(tBranchfos);
    }

    public static void main(String[] args) {
        System.out.println(Long.parseLong(("190626000100001".substring(0,6)) + ("190626000100001".substring(10,15))));
//        190626000100001
        String str = "1";
        System.out.println(str.length() > 2 ? str.substring(0,2): str);
    }
}
