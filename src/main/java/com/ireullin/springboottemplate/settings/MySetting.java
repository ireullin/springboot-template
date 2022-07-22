package com.ireullin.springboottemplate.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;



/**
 * 會直接對應到 application.yaml 裏頭的my-setting
 */
@Slf4j
@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix="my-setting")
public class MySetting  {
    // @Value("${my-setting.owner}") 也可以自己指定
    private String owner;
    private String id;
    private String env;
}
