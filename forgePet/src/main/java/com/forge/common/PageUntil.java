package com.forge.common;

/**
 * 分页统一计算
 *
 * @param begin 分页开始索引
 * @param size  页面大小
 */
public record PageUntil(int begin, int size) {
    /**
     * 分页逻辑封装
     *
     * @param num  页码，从1开始
     * @param size 页面大小
     * @param max  数据长度
     * @return 分页对象
     */
    public static PageUntil pu(int num, int size, int max) {
        int begin = (num - 1) * size;
        if (begin >= max && max != 0) begin = (max / size - 1) * size;
        return new PageUntil(begin, size);
    }
}
