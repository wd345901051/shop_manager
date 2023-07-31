package cn.tedu.csmall.product.config;

import cn.tedu.csmall.product.filter.JwtAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security的配置类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfiguration() {
        log.info("加载配置类：SecurityConfiguration");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 请求路径白名单
        String[] urls = {
                "/favicon.ico",
                "/doc.html",
                "/**/*.js",
                "/**/*.css",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/admins/login"
        };

        http.cors();

        http.csrf().disable();

        http.authorizeRequests() // 配置请求的授权访问
                // .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(urls) // 匹配某些请求路径
                .permitAll() // 允许直接访问（无论是否经过认证）
                .anyRequest() // 除以上配置过的请求以外的其它所有请求
                .authenticated(); // 经过认证的

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
