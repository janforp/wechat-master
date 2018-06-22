package com.janita.wechat.fuwuhao.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
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
public class WxConfig {

    @Value("${wx.app.id}")
    private String appId;

    @Value("${wx.app.secret}")
    private String appSecret;

    @Value("${wx.token}")
    private String wxToken;

    @Value("${wx.aes.key}")
    private String wxAesKey;

    /**
     * 微信的基本配置持久化
     * @return
     */
    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxConfigProvider=new WxMpInMemoryConfigStorage();
        wxConfigProvider.setAppId(appId);
        wxConfigProvider.setSecret(appSecret);
        wxConfigProvider.setToken(wxToken);
        wxConfigProvider.setAesKey(wxAesKey);
        return wxConfigProvider;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }
}
