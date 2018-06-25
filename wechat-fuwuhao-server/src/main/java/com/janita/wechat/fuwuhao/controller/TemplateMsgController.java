package com.janita.wechat.fuwuhao.controller;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     * @param phoneList
     * @param stockName
     * @param triggerCondition
     * @param triggerPrice
     * @param strategyNo
     * @param kLineType
     * @param maiDanAmount
     * @param weiTuoMethod
     * @param failReason
     * @param reboundAmplitude
     */
    @PostMapping("/sendMsgTemplate")
    public void sendMsgTemplate(@RequestParam(value = "phoneList") String phoneList,
                                @RequestParam(value = "templateType") String templateType,
                                @RequestParam(value = "msgName",required = false,defaultValue = "") String msgName,
                                @RequestParam(value = "stockName",required = false,defaultValue = "") String stockName,
                                @RequestParam(value = "triggerCondition",required = false,defaultValue = "") String triggerCondition,
                                @RequestParam(value = "triggerPrice",required = false,defaultValue = "") String triggerPrice,
                                @RequestParam(value = "strategyNo",required = false,defaultValue = "") String strategyNo,
                                @RequestParam(value = "kLineType",required = false,defaultValue = "") String kLineType,
                                @RequestParam(value = "maiDanAmount",required = false,defaultValue = "") String maiDanAmount,
                                @RequestParam(value = "weiTuoMethod",required = false,defaultValue = "") String weiTuoMethod,
                                @RequestParam(value = "failReason",required = false,defaultValue = "") String failReason,
                                @RequestParam(value = "reboundAmplitude",required = false,defaultValue = "") String reboundAmplitude) {

        if (StringUtils.isEmpty(phoneList)) {
            return;
        }
        if (StringUtils.isEmpty(templateType)) {
            return;
        }
        if (StringUtils.isEmpty(stockName) && StringUtils.isEmpty(triggerCondition) &&
            StringUtils.isEmpty(triggerPrice) && StringUtils.isEmpty(strategyNo) &&
            StringUtils.isEmpty(kLineType) && StringUtils.isEmpty(maiDanAmount) &&
            StringUtils.isEmpty(weiTuoMethod) && StringUtils.isEmpty(failReason) &&
            StringUtils.isEmpty(reboundAmplitude)) {
            return;
        }

        //手机号码列表
        String[] list = phoneList.split(COMMA);
        List<WxMpTemplateData> dataList = new ArrayList<>();
        WxMpTemplateMessage orderPaySuccessTemplate = WxMpTemplateMessage.builder().build();

        //T信号
        if (Objects.equals(templateType, "T")) {
            orderPaySuccessTemplate.setTemplateId("c0yLJtnajwLn_wqlz1abdbv5AFt36_y8OwEJsNKshvU");
        }

        //多空阵线
        if (Objects.equals(templateType, "DUOKONG")) {
            orderPaySuccessTemplate.setTemplateId("c0yLJtnajwLn_wqlz1abdbv5AFt36_y8OwEJsNKshvU");
            orderPaySuccessTemplate.setUrl("http://www.baidu.com");
        }

        if (!StringUtils.isEmpty(msgName)) {
            WxMpTemplateData data = new WxMpTemplateData("msgName", "提示类型：" + msgName, TEMPLATE_FRONT_COLOR);
            dataList.add(data);
        }
        if (!StringUtils.isEmpty(stockName)) {
            WxMpTemplateData data = new WxMpTemplateData("stockName", "股票名称：" + stockName, TEMPLATE_FRONT_COLOR);
            dataList.add(data);
        }
        if (!StringUtils.isEmpty(triggerCondition)) {
            WxMpTemplateData data = new WxMpTemplateData("triggerCondition", "触发条件：" + triggerCondition, TEMPLATE_FRONT_COLOR);
            dataList.add(data);
        }
        if (!StringUtils.isEmpty(triggerPrice)) {
            WxMpTemplateData data = new WxMpTemplateData("triggerPrice", "触发价格：" + triggerPrice, TEMPLATE_FRONT_COLOR);
            dataList.add(data);
        }
        if (!StringUtils.isEmpty(strategyNo)) {
            WxMpTemplateData data = new WxMpTemplateData("strategyNo", "策略编号：" + strategyNo, TEMPLATE_FRONT_COLOR);
            dataList.add(data);
        }
        if (!StringUtils.isEmpty(kLineType)) {
            WxMpTemplateData data = new WxMpTemplateData("kLineType", "K线类型：" + kLineType, TEMPLATE_FRONT_COLOR);
            dataList.add(data);
        }
        if (!StringUtils.isEmpty(maiDanAmount)) {
            WxMpTemplateData data = new WxMpTemplateData("maiDanAmount", "埋单数量：" + maiDanAmount, TEMPLATE_FRONT_COLOR);
            dataList.add(data);
        }
        if (!StringUtils.isEmpty(weiTuoMethod)) {
            WxMpTemplateData data = new WxMpTemplateData("weiTuoMethod", "委托方式：" + weiTuoMethod, TEMPLATE_FRONT_COLOR);
            dataList.add(data);
        }
        if (!StringUtils.isEmpty(failReason)) {
            WxMpTemplateData data = new WxMpTemplateData("failReason", "失败原因：" + failReason, TEMPLATE_FRONT_COLOR);
            dataList.add(data);
        }
        if (!StringUtils.isEmpty(reboundAmplitude)) {
            WxMpTemplateData data = new WxMpTemplateData("reboundAmplitude", "反弹幅度：" + reboundAmplitude, TEMPLATE_FRONT_COLOR);
            dataList.add(data);
        }
        if (!CollectionUtils.isEmpty(dataList)) {
            for (WxMpTemplateData data : dataList) {
                orderPaySuccessTemplate.addData(data);
            }
        }

        for (String openId : list) {
            orderPaySuccessTemplate.setToUser(openId);
            try {
                wxMpService.getTemplateMsgService().sendTemplateMsg(orderPaySuccessTemplate);
            } catch (WxErrorException e) {
                logger.error(e.getMessage());
            }
        }
    }
}