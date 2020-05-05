package com.zz.xmkj.oauth;


import com.zz.xmkj.vo.security.IntegrationAuthenticationEntity;


/**
 * 定义集成认证-认证器抽象类
 * 
 * @author 001
 */
public abstract class AbstractPreparableIntegrationAuthenticator implements IntegrationAuthenticator
{

    @Override
    public void prepare(IntegrationAuthenticationEntity entity)
    {

    }

    @Override
    public void complete(IntegrationAuthenticationEntity entity)
    {

    }
}