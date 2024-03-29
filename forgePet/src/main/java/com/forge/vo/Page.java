package com.forge.vo;

import java.util.List;

/**
 * 实现分页所需数据
 *
 * @param data 当前页的数据
 * @param len  所有数据的长度
 */
public record Page<T>(List<T> data, int len) {
}
