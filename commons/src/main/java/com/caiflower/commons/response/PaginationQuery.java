package com.caiflower.commons.response;

import java.io.Serializable;

/**
 * 分页查询
 *
 * @author: lijinlong
 * @date: 2022/9/1 15
 */
public class PaginationQuery implements Serializable {

    private static final long serialVersionUID = 8545996863226528798L;

    /**
     * 当前页面
     */
    private long current = 1;
    /**
     * 页面大小
     */
    private long size = 10;
    /**
     * 总数
     */
    private long total;
    /**
     * 关键字
     */
    private String keyword;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCurrent() {
        return current;
    }

    public long getSize() {
        return size;
    }

    public long getTotal() {
        return total;
    }
}
