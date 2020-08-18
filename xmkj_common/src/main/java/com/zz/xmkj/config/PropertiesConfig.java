package com.zz.xmkj.config;


import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.time.Duration;


@Component
@ConfigurationProperties
public class PropertiesConfig
{

    private final Map<String, Duration> initCaches = new HashMap<String, Duration>();

    public Map<String, Duration> getInitCaches()
    {
        return initCaches;
    }
}