package com.yym.springboot.restsecurity.config;

import lombok.Data;
import net.bytebuddy.build.ToStringPlugin;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "security.ignore")
@Data
public class SecurityIgnoreUrlProperties {
    private List<String> urls;
}
