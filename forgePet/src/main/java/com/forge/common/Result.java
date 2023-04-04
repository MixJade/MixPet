package com.forge.common;


/**
 * 通用返回结果，封装增删改的响应数据
 */
public record Result(Integer code, String msg) {
    public Result(ResultEnum re) {
        this(re.code, re.msg);
    }

    public static Result choice(String msg, boolean res) {
        if (res) return new Result(1, msg + "成功");
        else return new Result(0, msg + "失败");
    }

    public static Result error(String msg) {
        return new Result(0, msg);
    }

    public static Result success(String msg) {
        return new Result(1, msg);
    }
}
