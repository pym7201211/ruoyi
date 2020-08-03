package com.ruoyi.cloudDesktop.until;
import com.ruoyi.forts.until.TokenAutoDelay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component("accountAutoDuration")
public class AccountAutoDuration {
    protected final static Logger log = LoggerFactory.getLogger(AccountAutoDuration.class);


    public static void accountAutoDurationTask(){
       log.info("账号开始自动统计时长。。。");
    }
}
