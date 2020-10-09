package com.zz.xmkj.config;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import com.zz.xmkj.common.data.R;
import com.zz.xmkj.common.enums.ErrorCode;


@Component
@Aspect
public class AuthTokenAspect
{

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Around("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp)
    {

        R response = new R();
        try
        {
            Object proceed = pjp.proceed();
            if (proceed != null)
            {
                ResponseEntity<OAuth2AccessToken> responseEntity = (ResponseEntity<OAuth2AccessToken>)proceed;
                OAuth2AccessToken body = responseEntity.getBody();
                if (responseEntity.getStatusCode().is2xxSuccessful())
                {
                    response.setRet(ErrorCode.SUCCESS.getCode());
                    response.setMsg(ErrorCode.SUCCESS.getMessage());
                }
                else
                {

                }
                response.setData(body);
            }
            return ResponseEntity.status(200).body(response);
        }
        catch (Throwable e)
        {
            response.setRet(ErrorCode.REQUEST_ERROR.getCode());
            response.setMsg(e.getMessage());
            response.setData(e.toString());
        }
        return ResponseEntity.status(400).body(response);
    }
}
