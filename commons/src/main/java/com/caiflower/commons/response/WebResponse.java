package com.caiflower.commons.response;

import com.caiflower.commons.code.WebResponseCode;

import java.util.HashMap;

/**
 * web端公共响应
 *
 * @author: lijinlong
 * @date: 2022/8/12 17:59
 */
@SuppressWarnings("unused")
public class WebResponse extends HashMap<String, Object> {

    private WebResponse() {

    }

    /**
     * 创建一个WebResponse
     *
     * @return WebResponse
     */
    public static WebResponse generateWebResponse() {
        WebResponse webResponse = new WebResponse();
        webResponse.put("code", WebResponseCode.DEFAULT_CODE);
        return webResponse;
    }

    /**
     * 成功
     *
     * @param msg 成功消息
     */
    public WebResponse success(String msg) {
        this.put("code", WebResponseCode.DEFAULT_CODE);
        this.put("msg", msg);
        return this;
    }

    public WebResponse fail(String msg) {
        return fail(msg, WebResponseCode.UNKNOWN_ERROR_CODE);
    }

    /**
     * 失败
     *
     * @param msg  失败消息
     * @param code 码
     */
    public WebResponse fail(String msg, int code) {
        this.put("code", code);
        this.put("msg", msg);
        return this;
    }

    /**
     * 设置code值
     */
    public void setCode(int code) {
        this.put("code", code);
    }

    /**
     * 获取code值
     *
     * @return code
     */
    public int getCode() {
        return (int) this.get("code");
    }

    /**
     * 设置msg值
     *
     * @param msg 信息
     */
    public void setMsg(String msg) {
        this.put("msg", msg);
    }

    /**
     * 获取消息
     *
     * @return 消息
     */
    public String getMsg() {
        return (String) this.get("msg");
    }

    /**
     * 设置数据
     *
     * @param data 数据
     */
    public void setData(Object data) {
        this.put("data", data);
    }

    /**
     * 获取数据
     */
    public Object getData() {
        return this.get("data");
    }

    /**
     * 设置数据
     *
     * @param key   关键字
     * @param value 值
     */
    public void setKey(String key, Object value) {
        if (key == null || "data".equals(key) || "msg".equals(key)) {
            throw new RuntimeException("key most not be data or msg or null");
        }
        this.put(key, value);
    }

    /**
     * 获取数据
     *
     * @param key key
     * @return 数据
     */
    public Object getKey(String key) {
        return this.get(key);
    }

}
