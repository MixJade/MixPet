# O6-关于引用Security之后，无法使用Iframe

网页中的iframe也算是一种跨域，会被Security拦截

这个可以在配置类中关闭，但我建议开启，因为我以后不用Iframe了。

```java
/**
 * SpringSecurity配置类
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true) // 开启角色验证的注解
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain() throws Exception {
        // 。。。乱七八糟的代码
				// 关闭iframe的拦截
                .headers(h ->
                        h.frameOptions(f ->
                                f.sameOrigin() .httpStrictTransportSecurity(HeadersConfigurer.HstsConfig::disable)
                                        .disable()))
    }
}
```