package com.zz.xmkj.common.data;


import java.io.Serializable;

import com.zz.xmkj.common.enums.ErrorCode;

import lombok.Data;


@Data
public class R<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String ret;

    private T data;

    private String msg;

    public R(T data, ErrorCode errcode)
    {
        this.ret = errcode.getCode();
        this.msg = errcode.getMessage();
        this.data = data;
    }

    public R(ErrorCode errcode)
    {
        this.ret = errcode.getCode();
        this.msg = errcode.getMessage();
    }

}