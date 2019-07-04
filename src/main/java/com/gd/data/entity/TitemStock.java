package com.gd.data.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: He Wenbo
 * @Date: 2019/6/29
 * @Description:
 */
@Data
public class TitemStock {
    String itemBarcode;
    BigDecimal realQty;
    BigDecimal stockAmt;
    String branchNo;
}
