package com.zz.xmkj.common.businessException;

public class RequestException extends RuntimeException
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String code; // 异常状态码

    private String message; // 异常信息

    /**
     * @param code
     *            状态
     * @param message
     *            信息
     * @param descinfo
     *            错误,描述!
     */
    public RequestException(String code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public RequestException()
    {}

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

}