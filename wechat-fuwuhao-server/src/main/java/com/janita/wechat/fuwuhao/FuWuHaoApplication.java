package com.janita.wechat.fuwuhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created on 2018/6/21
 * @author Janita
 * 微信服务号后台
 */
@SpringBootApplication
@EnableScheduling
public class FuWuHaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuWuHaoApplication.class, args);
    }
}
