package com.janita.wechat.fuwuhao.controller;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/6/21
 * @author Janita
 */
@RestController
@RequestMapping("/wxApi")
public class IndexController {

    /**
     * 验证微信token
     * @param request
     * @return
     */
    @GetMapping("/verifyToken")
    public String verifyToken(HttpServletRequest request) {
        //微信服务器get传递的参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //微信工具类
        WxMpService wxService=new WxMpServiceImpl();

        //注入token的配置参数
        /**
         * 生产环境 建议将WxMpInMemoryConfigStorage持久化
         */
        WxMpInMemoryConfigStorage wxConfigProvider=new WxMpInMemoryConfigStorage();
        //注入token值
        wxConfigProvider.setToken("weixin");

        wxService.setWxMpConfigStorage(wxConfigProvider);

        boolean flag=wxService.checkSignature(timestamp, nonce, signature);

        return echostr;
    }
}
