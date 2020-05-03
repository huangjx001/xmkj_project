package com.zz.xmkj;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class XmkjGatewayApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(XmkjGatewayApplication.class, args);
    }

}
