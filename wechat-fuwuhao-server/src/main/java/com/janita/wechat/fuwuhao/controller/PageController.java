package com.janita.wechat.fuwuhao.controller;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created on 2018/6/22
 * @author Janita
 * 页面跳转
 */
@Controller
@RequestMapping("/wx")
public class PageController {

    private final static Logger logger = LoggerFactory.getLogger(PageController.class);

    @Value("${base.url}")
    private String baseUrl;

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/auth")
    public void auth(HttpServletResponse response) {
        String authCallBackUrl = baseUrl + "wx/authCallBack";
        String state = UUID.randomUUID().toString().replace("-","");

        //scope = snsapi_base 的授权用户是无感知的
        String url = wxMpService.oauth2buildAuthorizationUrl(authCallBackUrl, WxConsts.OAuth2Scope.SNSAPI_BASE, state);
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            logger.error("用户授权重定向失败");
            e.printStackTrace();
        }
    }

    /**
     * 微信网页授权之后
     * 去绑定页面
     * @param request
     * @return
     */
    @GetMapping("/authCallBack")
    public String authCallBack(HttpServletRequest request) {
        String code = request.getParameter("code");
        try {
            WxMpOAuth2AccessToken auth2AccessToken = wxMpService.oauth2getAccessToken(code);
            String openId = auth2AccessToken.getOpenId();
            request.setAttribute("openId", openId);
        } catch (WxErrorException e) {
            logger.error("获取用户 openId 失败");
            e.printStackTrace();
        }
        return "bind";
    }
}
