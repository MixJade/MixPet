package com.ship.security;


import com.ship.security.login.LoginFilter;
import com.ship.security.login.PowerHandler;
import com.ship.security.login.RememberService;
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
                                                   RememberService rememberService) throws Exception {
        String[] quiet = {"/", "/index.html", "/favicon.ico"};
        // 放行的后端接口
        String[] rearEnd = {"/login/**", "/power/**", "/doctor/page", "/pet/four",
                "/foster/pet", "/notice/four", "/common/download", "/common/downChat"};
        PowerHandler powerHandler = new PowerHandler();
        http.authorizeHttpRequests((requests) -> requests.requestMatchers(quiet).permitAll() // 放行静态资源
                        .requestMatchers(rearEnd).permitAll() // 放行后端接口
                        .requestMatchers("/cao/toLogin").anonymous() // 登录接口只允许匿名访问
                        .anyRequest().authenticated() // 其他地址的访问均需验证权限
                )
                // 自己的登录过滤器
                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .rememberMe(remember -> remember.rememberMeServices(rememberService))
                // 没有权限与未登陆
                .exceptionHandling(e -> e.accessDeniedHandler(powerHandler)
                        .authenticationEntryPoint(powerHandler))
                .csrf(CsrfConfigurer::disable) // 关闭跨站攻击防护
                .logout((logout) -> logout.logoutUrl("/cao/logout") // 退出登录的路径
                        .logoutSuccessHandler(powerHandler) // 退出登录成功
                        .permitAll() // 放行路径，理由同上
                );
        return http.build();
    }
}
