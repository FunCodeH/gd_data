package com.gd.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.gd.data.model.BaseResponse;
import com.gd.data.service.UpdateDateService;
import com.gd.data.utils.HttpTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: He Wenbo
 * @Date: 2019/6/25
 * @Description:
 */
@RestController
@RequestMapping("/gd-data-up/lastUpdateTime/")
public class LastUpdateController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UpdateDateService updateDateService;

    private static final String updateCashierUrl = "http://39.96.78.91/gd-data-up/update/updateModeCashier";


    @RequestMapping("modeCashier")
    public BaseResponse modeCashier() {
        BaseResponse baseResponse = new BaseResponse();
        try{
            String cashierList = updateDateService.queryCashierList();
            logger.info("查询到Cashier结果:{}", JSONObject.toJSONString(cashierList));
//            String result = HttpTools.sendPost(updateCashierUrl, cashierList, token);
//            logger.info("发送Cashier post结果:{}", result);
        }catch (Exception e){
            System.out.println(e);
            baseResponse.setCode(-1);
            baseResponse.setMessage("程序异常");
        }
        return baseResponse;
    }
    @RequestMapping("updateModeItemBrand")
    public BaseResponse updateModeItemBrand() {
        BaseResponse baseResponse = new BaseResponse();
        try{
            String brands = updateDateService.queryBrandList();
            logger.info("查询到Cashier结果:{}", JSONObject.toJSONString(brands));
        }catch (Exception e){
            System.out.println(e);
            baseResponse.setCode(-1);
            baseResponse.setMessage("程序异常");
        }
        return baseResponse;
    }

    @RequestMapping("updateModeItemCls")
    public BaseResponse updateModeItemCls() {
        BaseResponse baseResponse = new BaseResponse();
        try{
            String itemCls = updateDateService.queryModeItemCls();
            logger.info("查询到itemCls结果:{}", JSONObject.toJSONString(itemCls));
        }catch (Exception e){
            System.out.println(e);
            baseResponse.setCode(-1);
            baseResponse.setMessage("程序异常");
        }
        return baseResponse;
    }

}
