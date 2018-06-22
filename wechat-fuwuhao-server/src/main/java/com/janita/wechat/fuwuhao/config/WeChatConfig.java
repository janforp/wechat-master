package com.janita.wechat.fuwuhao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created on 2018/6/22
 * @author Janita
 * 微信配置
 */
@Configuration
@ConfigurationProperties(prefix = "wx")
@PropertySource(value={"classpath:config/wechat.properties"})
public class WeChatConfig {

    @Value("${wx.app.id}")
    private String appId;

    @Value("${wx.app.secret}")
    private String appSecret;

    @Value("${wx.token}")
    private String wxToken;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getWxToken() {
        return wxToken;
    }

    public void setWxToken(String wxToken) {
        this.wxToken = wxToken;
    }
}
