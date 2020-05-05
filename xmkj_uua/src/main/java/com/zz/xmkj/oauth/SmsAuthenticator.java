package com.zz.xmkj.oauth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zz.xmkj.domain.UserInfo;
import com.zz.xmkj.service.UserInfoService;
import com.zz.xmkj.vo.security.IntegrationAuthenticationEntity;


@Component
public class SmsAuthenticator extends AbstractPreparableIntegrationAuthenticator
{

    private final static String AUTH_TYPE = "sms";

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserInfo authenticate(IntegrationAuthenticationEntity entity)
    {
        String mobile = entity.getAuthParameter("mobile");
        if (StringUtils.isEmpty(mobile))
        {
            throw new OAuth2Exception("手机号不能为空");
        }
        String code = entity.getAuthParameter("code");
        // 测试项目，所以将验证码顶死为：1234
        if (!"1234".equals(code))
        {
            throw new OAuth2Exception("验证码错误或已过期");
        }

        QueryWrapper<UserInfo> qw = new QueryWrapper<UserInfo>();
        qw.eq("telphone", mobile);
        UserInfo userInfo = userInfoService.getOne(qw);
        return userInfo;
    }

    @Override
    public boolean support(IntegrationAuthenticationEntity entity)
    {
        return AUTH_TYPE.equals(entity.getAuthType());
    }
}