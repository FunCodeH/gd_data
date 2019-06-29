package com.gd.data.service;

import com.gd.data.model.Cashier;

import java.util.Date;
import java.util.List;

/**
 * @Auther: wenbo.he
 * @Date: 2019/6/25
 * @Description:
 */
public interface UpdateDateService {
    String  queryCashierList();

    String queryBrandList();

    String queryModeItemCls();

    String queryModeSaleMan();

    String querySupcustInfo();

    String queryDataPayment();

    String queryVisaFlow();

    String querySaleDetail();

    String queryCardPaylist();

    String queryVipInfo();

    String queryItemInfo();

    String queryItemStock();

    String queryOrderDetail();
}
