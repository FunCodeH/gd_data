package com.gd.data.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TPosSale {
    String id;
    Date saleTime;
    BigDecimal saleAmt;
    BigDecimal saleQty;
    String sheetNo;
    String saleBarcode;
    String saleMan;
    String jh;
    String userId;
}