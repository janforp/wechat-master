package com.janita.wechat.fuwuhao.controller;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/6/24
 * @author Janita
 * 发送模板消息接口
 */
@RestController
@RequestMapping("/msg")
public class TemplateMsgController {

    @Autowired
    private WxMpService wxMpService;

    /**
     * 模板消息字体颜色
     * 32CD32
     */
    private static final String TEMPLATE_FRONT_COLOR = "black";

    /**
     * 逗号
     */
    private static final String COMMA = ",";

    private final static Logger logger = LoggerFactory.getLogger(TemplateMsgController.class);

    /**
     * 发送模板消息
     * 测试
     * @return
     */
    @GetMapping("/sendMsgTemplate")
    public void sendMsgTemplate(HttpServletRequest request) {
        WxMpTemplateMessage orderPaySuccessTemplate = WxMpTemplateMessage.builder().build();
        orderPaySuccessTemplate.setToUser("oXfZo1NG9c0nL9OlNC1EMuUjcN9M");
        orderPaySuccessTemplate.setTemplateId("c9qIzbCmDllYS-35IuM9ebWzZ9CxIDaXa_NcvbhBDv0");
        orderPaySuccessTemplate.setUrl("http://www.baidu.com");
        WxMpTemplateData firstData = new WxMpTemplateData("a", "订单号码：12323445559984", TEMPLATE_FRONT_COLOR);
        WxMpTemplateData orderMoneySumData = new WxMpTemplateData("b", "股票名称：恒生电子", TEMPLATE_FRONT_COLOR);
        WxMpTemplateData orderProductNameData = new WxMpTemplateData("c", "股票代码：6558984", TEMPLATE_FRONT_COLOR);
        WxMpTemplateData price = new WxMpTemplateData("keyword3", "188.9", TEMPLATE_FRONT_COLOR);
        WxMpTemplateData remarkData = new WxMpTemplateData("remark", "触发了T信号触发了T信号触发了T信号", TEMPLATE_FRONT_COLOR);
        orderPaySuccessTemplate.addData(firstData)
                .addData(orderMoneySumData)
                .addData(orderProductNameData)
                .addData(price)
                .addData(remarkData);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(orderPaySuccessTemplate);
        } catch (WxErrorException e) {
            logger.error(e.getMessage());
        }
    }
}