package com.zz.xmkj.common.enums;


import lombok.Getter;


@Getter
public enum ErrorCode {
    SUCCESS("0000", "success"),

    SERVER_ERROR("9999", "system error"),

    REQUEST_ERROR("400", "请求错误"),

    UNAUTHORIZED("401", "未授权"),

    NOT_ACCESSIBLE("403", "不可访问"),

    METHOD_NOT_ALLOWED("405", "方法不被允许"),

    UNSUPPORTED_MEDIA_TYPE("415", "不支持当前媒体类型"),

    TOKEN_LOSE_EFFICACY("1001", "您的登录令牌已失效"),

    USER_NOT_EXIST("1002", "用户不存在,请先注册!"),

    USER_PASSWORD_ERROR("1003", "密码错误!"),

    USER_IS_EXIST("1004", "用户已经存在!"),

    PARAM_IS_ERROR("1005", "参数缺失,请检查"),

    LIMIT_IS_OUT("1006", "条数超出限制!"),

    INTERVAL_TIME("1007", "间隔时间不足60秒!"),

    AUTH_CODE_ERROR("1008", "验证码错误!"),

    TELPHONE_IS_EXIST("1009", "手机号已经存在!");

    private String code;

    private String message;

    ErrorCode(String code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public static ErrorCode getByCode(String code)
    {

        for (ErrorCode errorCode : ErrorCode.values())
        {
            if (errorCode.getCode().equals(code))
            {
                return errorCode;
            }
        }

        return null;
    }

}