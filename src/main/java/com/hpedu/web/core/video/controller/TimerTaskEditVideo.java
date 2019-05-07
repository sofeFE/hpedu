package com.hpedu.web.core.video.controller;

import com.hpedu.util.codeUtil.BaseUtil;
import com.hpedu.web.core.video.service.GeneralVideoService;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component//将这个对象加入Spring容器中
public class TimerTaskEditVideo {
    @Resource
    GeneralVideoService generalVideoService;
    Logger log = BaseUtil.getLogger(TimerTaskEditVideo.class);


    /**
     * 此处的更新 莫名其妙. 
     * 未明白用意.
     */
    @Scheduled(cron = "0 0 0/1 * * ?")//00:00开始执行,每小时执行一次视频更新
    public void startTimer() {
        Date date = new Date();
        String[] dateArr = new SimpleDateFormat("yyyy-MM-dd#EEEE-HH").format(date).split("#");
        try {
            generalVideoService.updateVideoEditDate(dateArr[0] + "-" + dateArr[1].split("-")[1], dateArr[1]);
        } catch (Exception e) {
            log.error("定时更新视频异常：", e.getCause());
        }
    }
}  

