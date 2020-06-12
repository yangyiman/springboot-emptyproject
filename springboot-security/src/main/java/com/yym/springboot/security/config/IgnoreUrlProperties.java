package com.yym.springboot.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("security.ignore")
@Data
public class IgnoreUrlProperties {
    List<String> urls;
}
