package com.zz.xmkj.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zz.xmkj.service.MessageService;


/**
 * 短信相关接口
 * 
 * @author 001
 */
@Service
public class MessageServiceImpl implements MessageService
{
    @Value("${short-message.access-key-id}")
    private String accessKeyId;

    @Value("${short-message.template-code}")
    private String templateCode;

    @Value("${short-message.access-key-secret}")
    private String accessKeySecret;

    @Value("${short-message.sign-name}")
    private String signName;

    /**
     * 短信发送
     * 
     * @param code
     * @param num
     * @return
     */
    public CommonResponse sendMs(String code, String num)
    {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
            accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        // 必填:待发送手机号
        request.putQueryParameter("PhoneNumbers", num);
        // 必填:短信签名-可在短信控制台中找到
        request.putQueryParameter("SignName", signName);
        // 必填:短信模板-可在短信控制台中找到
        request.putQueryParameter("TemplateCode", templateCode);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try
        {
            CommonResponse response = client.getCommonResponse(request);
            return response;
        }
        catch (ServerException e)
        {
            e.printStackTrace();
        }
        catch (ClientException e)
        {
            e.printStackTrace();
        }
        throw new RuntimeException("ERROR");
    }
}