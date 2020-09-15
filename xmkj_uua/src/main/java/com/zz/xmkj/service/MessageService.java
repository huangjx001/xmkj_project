package com.zz.xmkj.service;


import com.aliyuncs.CommonResponse;


/**
 * 短信相关接口
 * 
 * @author 001
 */
public interface MessageService
{
    /**
     * 短信发送
     * 
     * @param code
     * @param num
     * @return
     */
    CommonResponse sendMs(String code, String num);
}