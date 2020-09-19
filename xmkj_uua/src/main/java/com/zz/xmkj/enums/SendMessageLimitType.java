package com.zz.xmkj.enums;

/**
 * 是否可以发送短信验证信息
 * 
 * @author 001
 */
public enum SendMessageLimitType {
    INTERVAL_TIME("0", "间隔时间不足"), CAN_SEND("1", "可以发送短信"), LIMIT_OUT("2", "短信条数超出限制");

    private String code;

    private String name;

    private SendMessageLimitType(String code, String name)
    {
        this.code = code;
        this.name = name;

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

}