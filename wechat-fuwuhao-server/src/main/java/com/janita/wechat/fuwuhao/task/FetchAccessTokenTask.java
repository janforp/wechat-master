package com.janita.wechat.fuwuhao.task;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import utils.DateTimeUtils;

import java.sql.Timestamp;

/**
 * Created on 2018/6/22
 * @author Janita
 * 定时获取 accessToken
 */
@Component
public class FetchAccessTokenTask {

    private final static Logger logger = LoggerFactory.getLogger(FetchAccessTokenTask.class);

    @Autowired
    private WxMpInMemoryConfigStorage configStorage;

    @Autowired
    private WxMpService wxMpService;

    /**
     * 每 7000s 需要更新一次 accessToken
     */
    @Scheduled(fixedRate = 1000 * 30)
    public void fetchAccessToken() {
        try {
            logger.info("更新之前的 accessToken : " + configStorage.getAccessToken());
            logger.info("更新 accessToken 开始时间 ：" + DateTimeUtils.timestampToDate(new Timestamp(System.currentTimeMillis())));
            String accessToken = wxMpService.getAccessToken();
            configStorage.setAccessToken(accessToken);
            logger.info("更新之后的 accessToken : " + accessToken);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}