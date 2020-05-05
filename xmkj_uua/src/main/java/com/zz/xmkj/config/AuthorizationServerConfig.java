package com.zz.xmkj.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.zz.xmkj.service.MyUserDetailService;


/**
 * 〈OAuth2认证服务器〉 认证服务
 * 
 * @author huangjx
 * @since 1.0.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter
{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public TokenStore tokenStore()
    {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)
        throws Exception
    {
        security.allowFormAuthenticationForClients().tokenKeyAccess(
            "permitAll()").checkTokenAccess("isAuthenticated()");
    }

    /**
     * 配置 oauth_client_details【client_id和client_secret等】信息的认证【检查ClientDetails的合法性】服务 设置 认证信息的来源：数据库
     * (可选项：数据库和内存,使用内存一般用来作测试) 自动注入：ClientDetailsService的实现类 JdbcClientDetailsService (检查
     * ClientDetails 对象) 这个方法主要是用于校验注册的第三方客户端的信息，可以存储在数据库中，默认方式是存储在内存中，如下所示，注释掉的代码即为内存中存储的方式
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
        throws Exception
    {
        clients.inMemory().withClient("client").secret(
            bCryptPasswordEncoder.encode("server")).authorizedGrantTypes("password",
                "refresh_token").scopes("all");
    }

    @Bean
    public ClientDetailsService clientDetails()
    {
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
        throws Exception
    {
        endpoints.tokenStore(tokenStore()).userDetailsService(
            userDetailService).authenticationManager(authenticationManager);
        endpoints.tokenServices(defaultTokenServices());
    }

    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p> 自定义的token 认证的token是存到redis里的
     * 
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices()
    {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
        // refresh_token默认30天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }
}