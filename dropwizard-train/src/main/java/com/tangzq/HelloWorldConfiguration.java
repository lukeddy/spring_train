package com.tangzq;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class HelloWorldConfiguration extends Configuration {
    @NotEmpty //参数检查
    @JsonProperty //自动映射配置文件
    private String template;

    @NotEmpty
    @JsonProperty
    private String defaultName;

    public String getTemplate() {
        return template;
    }

    public String getDefaultName() {
        return defaultName;
    }
}
