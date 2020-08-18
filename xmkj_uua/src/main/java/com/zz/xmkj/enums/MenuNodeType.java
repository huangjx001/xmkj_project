package com.zz.xmkj.enums;

/**
 * 菜单nodetype枚举类
 * 
 * @author 001
 */
public enum MenuNodeType {
    FOLDER("文件夹", "1"), PAGE("页面", "2"), BUTTON("按钮", "3");

    private String name;

    private String code;

    private MenuNodeType(String name, String code)
    {
        this.name = name;
        this.code = code;
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