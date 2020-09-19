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

    /**
     * 判断能否发送验证码
     * 
     * @param telphone
     * @return
     */
    String isAuthCodeCanSend(String telphone);

    /**
     * 判断手机验证码是否正确
     * 
     * @param telphone
     * @param verficateCode
     * @return
     */
    boolean authCodeCorrect(String telphone, String verficateCode);
}