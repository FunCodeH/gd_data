package com.gd.data.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: He Wenbo
 * @Date: 2019/6/25
 * @Description:
 */
@Data
public class BaseResponse implements Serializable {

    private int code = 0;

    private String message = "";

    private Object data;

}
