package com.zz.xmkj.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.zz.xmkj.service.MyUserDetailService;


/**
 * 安全配置 @ EnableWebSecurity 启用web安全配置 @ EnableGlobalMethodSecurity 启用全局方法安全注解，就可以在方法上使用注解来对请求进行过滤
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private MyUserDetailService userDetailService;

    /**
     * 全局用户信息
     * 
     * @param auth
     *            认证管理
     * @throws Exception
     *             用户认证异常信息
     */
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth)
        throws Exception
    {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * 认证管理
     * 
     * @return 认证管理对象
     * @throws Exception
     *             认证异常信息
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean()
        throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /**
     * http安全配置
     * 
     * @param http
     *            http安全对象
     * @throws Exception
     *             http安全异常信息
     */
    @Override
    protected void configure(HttpSecurity http)
        throws Exception
    {
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll().antMatchers(
            "/message/sendMs/**", "/userInfo/register/**", "/userInfo/login/**", "/v2/api-docs",
            "/definitions/**", "/configuration/ui", "/swagger-resources/**",
            "/configuration/security", "/swagger-ui.html", "/webjars/**",
            "/swagger-resources/configuration/ui",
            "/swagge‌​r-ui.html").permitAll().anyRequest().authenticated().and().csrf().disable();
    }

}
