package com.ship.security.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ship.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.io.IOException;

/**
 * Json的工具，
 * 注意这不是public，只能在包内使用
 */
class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 直接返回JSON
     *
     * @param res 返回值封装
     */
    protected static void write(Result res, HttpServletResponse resp) {
        try {
            // 写入JSON
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(res);
            // 相应数据
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(jsonString);
        } catch (IOException e) {
            log.warn("JSON转化失败");
        }
    }

    /**
     * 判断请求是不是Json
     *
     * @param req remember与Login请求
     * @return 是Json
     */
    protected static boolean isJson(HttpServletRequest req) {
        boolean isAxios = req.getContentType().equals("application/json;charset=UTF-8");
        return isAxios || req.getContentType().equals(MediaType.APPLICATION_JSON_VALUE);
    }
}
