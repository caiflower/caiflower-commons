package com.caiflower.commons.response;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应
 *
 * @author: lijinlong
 * @date: 2022/9/1 15:50
 */
public class PaginationData implements Serializable {

    private static final long serialVersionUID = 8545996863226528798L;

    /**
     * 当前页面
     */
    private long current;

    /**
     * 页面大小
     */
    private long size;

    /**
     * 总数
     */
    private long total;

    /**
     * 数据
     */
    private List<?> data;

    public PaginationData(Page page) {
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.total = page.getTotal();
        this.data = page.getRecords();
    }
}
