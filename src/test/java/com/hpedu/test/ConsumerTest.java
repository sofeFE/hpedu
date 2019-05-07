package com.hpedu.test;

import com.hpedu.web.core.video.service.GeneralVideoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ConsumerTest {
    
    @Autowired
    private GeneralVideoService generalVideoService;
    
    @Test
    public void startTimer() {
        Date date = new Date();
        String[] dateArr = new SimpleDateFormat("yyyy-MM-dd#EEEE-HH").format(date).split("#");
        generalVideoService.updateVideoEditDate(dateArr[0] + "-" + dateArr[1].split("-")[1], dateArr[1]);
    }
}
