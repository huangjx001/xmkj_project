package com.zz.xmkj.enums;

/**
 * 短信模块相关配置枚举类
 * 
 * @author 001
 */
public enum BaseConfigShortMessageModuleKeyType {

    ACCESS_KEY_ID("0", "access-key-id"),

    ACCESS_KEY_SECRET("1", "access-key-secret"),

    TEMPLATE_CODE("2", "template-code"),

    SIGN_NAME("3", "sign-name"),

    SEND_SECONDS("4", "send-seconds"),

    LIMIT_COUNT("5", "limit-count");

    private String code;

    private String name;

    private BaseConfigShortMessageModuleKeyType(String code, String name)
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