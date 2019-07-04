package com.gd.data.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: He Wenbo
 * @Date: 2019/6/28
 * @Description:
 */
@Data
public class TitemStockInfo {
    String itemBarcode;
    BigDecimal itemInPrice;
    String itemName;
    String itemBrand;
    String itemClass;
    String itemNo;
    String itemSize;
    String itemUnitNo;
    Date modifyDate;
    BigDecimal itemSalePrice;
    BigDecimal itemVipPrice;
    String itemSupNo;
    Integer stockQty;
    String branchNo;
    Date createTime;
}
