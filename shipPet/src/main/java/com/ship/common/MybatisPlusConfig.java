package com.ship.common;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置MP的分页插件
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 设置MySQL的分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mpi = new MybatisPlusInterceptor();
        // 设置MySQL的分页插件
        mpi.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mpi;
    }
}