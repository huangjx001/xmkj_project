package com.zz.xmkj.oauth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zz.xmkj.domain.UserInfo;
import com.zz.xmkj.service.UserInfoService;
import com.zz.xmkj.vo.security.IntegrationAuthenticationEntity;


/**
 * 密码登录认证器
 * 
 * @author 001
 */
@Component
@Primary
public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator
{

    private final static String AUTH_TYPE = "password";

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserInfo authenticate(IntegrationAuthenticationEntity entity)
    {
        String name = entity.getAuthParameter("username");
        String pwd = entity.getAuthParameter("password");
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd))
        {
            throw new OAuth2Exception("用户名或密码不能为空");
        }
        QueryWrapper<UserInfo> qw = new QueryWrapper<UserInfo>();
        qw.eq("user_name", name).or().eq("telphone", name.trim());
        UserInfo user = userInfoService.getOne(qw);
        if (bCryptPasswordEncoder.matches(pwd, user.getPassword()))
        {
            return user;
        }
        return null;
    }

    @Override
    public boolean support(IntegrationAuthenticationEntity entity)
    {
        return AUTH_TYPE.equals(entity.getAuthType());
    }
}