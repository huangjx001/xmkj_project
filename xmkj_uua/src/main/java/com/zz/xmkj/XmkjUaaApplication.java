package com.zz.xmkj;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.zz.xmkj")
@EnableCaching
public class XmkjUaaApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(XmkjUaaApplication.class, args);
    }

}
