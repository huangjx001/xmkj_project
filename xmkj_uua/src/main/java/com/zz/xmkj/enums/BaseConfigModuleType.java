package com.zz.xmkj.enums;

/**
 * 基础配置模块名枚举类
 * 
 * @author 001
 */
public enum BaseConfigModuleType {
    SHORT_MESSAGE("short-message", "短信模块配置");

    private String code;

    private String name;

    private BaseConfigModuleType(String code, String name)
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