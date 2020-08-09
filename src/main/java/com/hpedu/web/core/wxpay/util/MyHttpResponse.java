package com.hpedu.web.core.wxpay.util;

import lombok.Data;

import java.io.Serializable;

/**
 * Description: 封装httpClient响应结果
 * 
 * @author JourWon
 * @date Created on 2018年4月19日
 */
@Data
public class MyHttpResponse implements Serializable {

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;


    public MyHttpResponse(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public MyHttpResponse(int code) {
        this.code = code;
    }
}