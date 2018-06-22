package com.janita.wechat.fuwuhao.controller;

import com.janita.wechat.common.result.ResultDto;
import com.janita.wechat.common.result.ResultDtoFactory;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WxApiController {

    private final static Logger logger = LoggerFactory.getLogger(WxApiController.class);

    @Autowired
    private WxMpInMemoryConfigStorage wxMpConfigStorage;

    @Autowired
    private WxMpService wxMpService;

    /**
     * 验证微信token
     * 该接口用于与微信服务器交互
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

        //注入token值,这个token的值必须是微信后台的token
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage);

        //检测签名
        boolean flag = wxMpService.checkSignature(timestamp, nonce, signature);
        if (flag) {
            return echostr;
        }else {
            logger.error("签名错误");
            return null;
        }
    }
}
