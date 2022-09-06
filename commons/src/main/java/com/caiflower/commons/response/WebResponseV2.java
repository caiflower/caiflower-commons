package com.caiflower.commons.response;

import java.util.HashMap;

/**
 * web端响应 版本V2
 *
 * @author caiflower
 * @date 2022/9/6 20:34
 */
public class WebResponseV2 extends HashMap<String, Object> {

    private WebResponseV2() {

    }

    /**
     * 创建一个WebResponse
     *
     * @return WebResponse
     */
    public static WebResponseV2 generateWebResponse() {
        WebResponseV2 webResponse = new WebResponseV2();
        webResponse.put("success", true);
        return webResponse;
    }

    public WebResponseV2 success(String msg) {
        return success(msg, null);
    }

    public WebResponseV2 success(Object data) {
        return success(null, data);
    }

    /**
     * 成功
     *
     * @param data 数据
     * @param msg  成功消息
     */
    public WebResponseV2 success(String msg, Object data) {
        this.put("success", true);
        if (data != null) {
            this.put("data", data);
        }
        if (msg != null) {
            this.put("msg", msg);
        }
        return this;
    }

    /**
     * 失败
     *
     * @param msg 失败消息
     */
    public WebResponseV2 fail(String msg) {
        this.put("success", false);
        this.put("msg", msg);
        return this;
    }

    public boolean isSuccess() {
        return (boolean) this.get("success") == true;
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
