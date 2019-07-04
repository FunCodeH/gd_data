package com.gd.data.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: He Wenbo
 * @Date: 2019/6/29
 * @Description:
 */
@Data
public class TOrderDetail {
    String id;
    BigDecimal posTotalAmt;
    String paymentNo;
    Date saleTime;
    String dispNo;
    String vipNo;
    String branchNo;

}
