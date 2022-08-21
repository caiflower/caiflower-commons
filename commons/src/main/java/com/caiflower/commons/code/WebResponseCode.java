package com.caiflower.commons.code;

/**
 * web响应码
 *
 * @author lee
 * @date 2022/8/12 22:48
 */
public interface WebResponseCode {

    /**
     * 默认响应码
     */
    int DEFAULT_CODE = 0;
    /**
     * 参数错误
     */
    int PARAMETER_ERROR_CODE = 300;
    /**
     * 认证失败
     */
    int UNAUTHORIZED = 401;
    /**
     * 无权限
     */
    int FORBIDDEN = 403;
    /**
     * 未知异常
     */
    int UNKNOWN_ERROR_CODE = 500;
}
