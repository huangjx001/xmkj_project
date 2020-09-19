package com.zz.xmkj.common.aop;


import com.google.common.collect.Maps;
import com.zz.xmkj.common.businessException.RequestException;
import com.zz.xmkj.common.enums.ErrorCode;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * redis限流aop方式
 * @author 001
 *
 */
@Aspect
@Component
@Slf4j
public class RequestLimitContract
{
    private Map<String, Integer> redisTemplate = Maps.newHashMap();

    @Before("within(@org.springframework.stereotype.Controller *)&& @annotation(limit)")

    public void requestLimit(final JoinPoint joinPoint, RequestLimit limit)
        throws RequestException
    {
        try
        {
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            for (int i = 0; i < args.length; i++ )
            {
                if (args[i] instanceof HttpServletRequest)
                {
                    request = (HttpServletRequest)args[i];
                    break;
                }
            }
            /**
             * 将请求参数转变为request
             */
            if (request == null)
            {
                throw new RequestException(ErrorCode.PARAM_IS_ERROR.getCode(),
                    ErrorCode.PARAM_IS_ERROR.getMessage());
            }
            String ip = request.getLocalAddr();
            String url = request.getRequestURI().toString();
            String key = limit.key();
            if (redisTemplate.get(key) == null || redisTemplate.get(key) == 0)
            {
                redisTemplate.put(key, 1);
            }
            else
            {
                redisTemplate.put(key, redisTemplate.get(key) + 1);
            }
            Integer count = redisTemplate.get(key);
            if (count > 0)
            {
                // 创建一个定时器
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        redisTemplate.remove(key);
                    }
                };
                // 这个定时器设定在time规定的时间之后会执行上面的remove方法，也就是说在这个时间后它可以重新访问
                timer.schedule(timerTask, limit.time());
            }
            if (count > limit.count())
            {
                log.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
                throw new RequestException(ErrorCode.LIMIT_IS_OUT.getCode(),
                    ErrorCode.LIMIT_IS_OUT.getMessage());
            }
        }
        catch (RequestException e)
        {
            throw e;
        }
        catch (Exception e)
        {

        }
    }
}