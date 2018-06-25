package com.janita.wechat.common.dto;

import java.util.List;

/**
 * Created on 2018/6/24
 * @author Janita
 * 模板消息推送的字段
 */
public class TemplateMsgDto {

    /**
     * 需要发送用户的手机列表
     */
    private List<String> phoneList;

    /**
     * 证券名称
     */
    private String stockName;

    /**
     * T信号触发条件如：B信号 / 多空阵线提醒触发条件如：多头
     */
    private String triggerCondition;

    /**
     * T信号触发价格 / 挂单买入提醒（埋单） 触发 （失败）
     */
    private String triggerPrice;

    /**
     * 策略编号如：32482
     */
    private String strategyNo;

    /**
     * K线类型如：60分钟K线
     */
    private String kLineType;

    /**
     * 埋单数量
     */
    private String maiDanAmount;

    /**
     * 挂单买入提醒（埋单）触发（失败）委托方式如：买一价
     */
    private String weiTuoMethod;

    /**
     * 挂单买入提醒（埋单）触发（失败）失败原因如：股票分红除权
     */
    private String failReason;

    /**
     * 反弹买入提醒（埋单）触发（失败）生效价格：如58.00
     */
    private String effectPrice;

    /**
     * 反弹幅度
     */
    private String reboundAmplitude;

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getTriggerCondition() {
        return triggerCondition;
    }

    public void setTriggerCondition(String triggerCondition) {
        this.triggerCondition = triggerCondition;
    }

    public String getTriggerPrice() {
        return triggerPrice;
    }

    public void setTriggerPrice(String triggerPrice) {
        this.triggerPrice = triggerPrice;
    }

    public String getStrategyNo() {
        return strategyNo;
    }

    public void setStrategyNo(String strategyNo) {
        this.strategyNo = strategyNo;
    }

    public String getkLineType() {
        return kLineType;
    }

    public void setkLineType(String kLineType) {
        this.kLineType = kLineType;
    }

    public String getMaiDanAmount() {
        return maiDanAmount;
    }

    public void setMaiDanAmount(String maiDanAmount) {
        this.maiDanAmount = maiDanAmount;
    }

    public String getWeiTuoMethod() {
        return weiTuoMethod;
    }

    public void setWeiTuoMethod(String weiTuoMethod) {
        this.weiTuoMethod = weiTuoMethod;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getEffectPrice() {
        return effectPrice;
    }

    public void setEffectPrice(String effectPrice) {
        this.effectPrice = effectPrice;
    }

    public String getReboundAmplitude() {
        return reboundAmplitude;
    }

    public void setReboundAmplitude(String reboundAmplitude) {
        this.reboundAmplitude = reboundAmplitude;
    }
}
