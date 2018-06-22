package com.janita.wechat.fuwuhao.config;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created on 2018/6/22
 * @author Janita
 * TODO 是不是这样持久化
 */
@Configuration
@PropertySource(value={"classpath:config/wechat.properties"})
public class WxMpInMemoryConfigStorageConfig {

    @Value("${wx.app.id}")
    private String appId;

    @Value("${wx.app.secret}")
    private String appSecret;

    @Value("${wx.token}")
    private String wxToken;

    /**
     * 微信的基本配置持久化
     * @return
     */
    @Bean
    public WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage() {
        WxMpInMemoryConfigStorage wxConfigProvider=new WxMpInMemoryConfigStorage();
        wxConfigProvider.setAppId(appId);
        wxConfigProvider.setSecret(appSecret);
        wxConfigProvider.setToken(wxToken);
        return wxConfigProvider;
    }
}
