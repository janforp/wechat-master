package com.janita.wechat.fuwuhao.controller;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * 模板消息字体颜色
     */
    private static final String TEMPLATE_FRONT_COLOR = "red";

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

    /**
     * 发送模板消息
     * @return
     */
    @GetMapping("/sendMsgTemplate")
    public void sendMsgTemplate(WxMpXmlMessage message, HttpServletResponse response, HttpServletRequest request) {
        WxMpTemplateMessage orderPaySuccessTemplate = WxMpTemplateMessage.builder().build();
        orderPaySuccessTemplate.setToUser("oXfZo1NG9c0nL9OlNC1EMuUjcN9M");
        orderPaySuccessTemplate.setTemplateId("dVdfU_VjU1uOdBT4oGDDsYKhy1hQQJYjvbvaI5MZxiU");
        orderPaySuccessTemplate.setUrl("http://www.baidu.com");
        WxMpTemplateData firstData = new WxMpTemplateData("first", "订单支付成功", TEMPLATE_FRONT_COLOR);
        WxMpTemplateData orderMoneySumData = new WxMpTemplateData("orderMoneySum", request.getParameter("orderMoneySum"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData orderProductNameData = new WxMpTemplateData("orderProductName", request.getParameter("orderProductName"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData remarkData = new WxMpTemplateData("Remark", request.getParameter("remark"), TEMPLATE_FRONT_COLOR);
        orderPaySuccessTemplate.addData(firstData)
                .addData(orderMoneySumData)
                .addData(orderProductNameData)
                .addData(remarkData);
        try {
            wxMpService.getTemplateMsgService()
                    .sendTemplateMsg(orderPaySuccessTemplate);
        } catch (WxErrorException e) {
            logger.error(e.getMessage());
        }
    }
}