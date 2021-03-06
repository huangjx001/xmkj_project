package com.zz.xmkj.rest;


import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliyuncs.CommonResponse;
import com.zz.xmkj.common.data.R;
import com.zz.xmkj.common.enums.ErrorCode;
import com.zz.xmkj.constant.UuaConstant;
import com.zz.xmkj.enums.SendMessageLimitType;
import com.zz.xmkj.service.MessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@SuppressWarnings({"rawtypes"})
@RestController
@RequestMapping("/message")
@Api(value = "短信相关接口", tags = "短信相关接口")
public class MessageController
{
    @Autowired
    private MessageService messageService;

    @Autowired
    private RedisTemplate redisTemplate;

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    @GetMapping("/sendMs")
    @PreAuthorize("permitAll")
    public R sendMs(@RequestParam("telphone") String telphone)
    {
        String canSendInfo = messageService.isAuthCodeCanSend(telphone);
        if (SendMessageLimitType.CAN_SEND.getCode().equals(canSendInfo))
        {
            String verificationCode = String.valueOf((int)((Math.random() * 9 + 1) * 1000));
            CommonResponse commonRes = messageService.sendMs(verificationCode, telphone);
            redisTemplate.opsForValue().set(UuaConstant.MESSAGE_VERFICATE_CODE + telphone,
                verificationCode, 60 * 5, TimeUnit.SECONDS);
            return new R(commonRes.getData(), ErrorCode.SUCCESS);
        }
        if (SendMessageLimitType.LIMIT_OUT.getCode().equals(canSendInfo))
        {
            return new R(ErrorCode.LIMIT_IS_OUT);
        }
        return new R(ErrorCode.INTERVAL_TIME);
    }

}