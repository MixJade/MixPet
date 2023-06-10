package com.ship.util;

/**
 * 分页统一计算
 *
 * @param begin 分页开始索引
 * @param size  页面大小
 */
public record PageUtil(int begin, int size) {
    /**
     * 分页逻辑封装
     *
     * @param num  页码，从1开始
     * @param size 页面大小
     * @param max  数据长度
     * @return 分页对象
     */
    public static PageUtil pu(int num, int size, int max) {
        int begin = (num - 1) * size;
        if (begin >= max && max != 0) begin = (max / size - 1) * size;
        if (begin < 0) begin = 0;
        if (size < 1) size = 5;
        return new PageUtil(begin, size);
    }
}
