package com.ireullin.springboottemplate.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



/**
 * 會直接對應到 application.yaml 裏頭的my-setting
 */
@Component
@ConfigurationProperties(prefix="my-setting")
public class MySetting  {

    // @Value("${my-setting.owner}") 也可以自己指定
    private String owner;
    
    private String id;
    
    private String env;
    
    public String getOwner() {
        return owner;
    }
    public String getEnv() {
        return env;
    }
    public void setEnv(String env) {
        this.env = env;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
