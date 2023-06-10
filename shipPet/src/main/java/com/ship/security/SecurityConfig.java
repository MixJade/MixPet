package com.ship.security;


import com.ship.security.login.LoginFilter;
import com.ship.security.login.RememberMeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SpringSecurity配置类
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true) // 开启角色验证的注解
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   LoginFilter loginFilter,
                                                   RememberMeService rememberMeService) throws Exception {
        // 放行的静态资源
        String[] quiet = {"/", "/index.html", "/favicon.ico", "/reception/**", "/picture/**",
                "/js/**", "/doctorPage/**", "/backstage/**", "/assets/**", "/login01.html"};
        // 放行的后端接口
        String[] rearEnd = {"/login/**", "/doctor/page", "/pet/four",
                "/notice/four", "/common/download", "/common/downChat"};
        http.authorizeHttpRequests((requests) -> requests.requestMatchers(quiet).permitAll() // 放行静态资源
                        .requestMatchers(rearEnd).permitAll() // 放行后端接口
                        .requestMatchers("/cao/toLogin").anonymous() // 登录接口只允许匿名访问
                        .anyRequest().authenticated() // 其他地址的访问均需验证权限
                )
                // 这里很多配置都移到了登录过滤器的配置里面
                .formLogin((form) -> form.loginPage("/login/noLogin") // 设置未登录的重定向路径
                        .permitAll() // 尽管我已经在前面配置了，但还是放行留作象征
                )
                // 自己的登录过滤器
                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .rememberMe(remember -> remember.rememberMeServices(rememberMeService))
                // 没有权限时的重定向路径
                .exceptionHandling(e -> e.accessDeniedPage("/login/noPower"))
                .csrf(CsrfConfigurer::disable) // 关闭跨站攻击防护
                .logout((logout) -> logout.logoutUrl("/cao/logout") // 退出登录的路径，不需要自己的接口
                        .logoutSuccessUrl("/login/bye") // 退出登录成功的重定向路径
                        .permitAll() // 放行路径，理由同上
                );
        return http.build();
    }
}
