package com.ruoyi.forts.until;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.forts.domain.TokenApplyForm;
import com.ruoyi.forts.mapper.TokenApplyFormMapper;
import com.ruoyi.forts.mapper.TokenAutoPushDemoMapper;
import com.ruoyi.forts.service.ITokenApplyFormService;
import com.ruoyi.ruoyiforts.until.FortDetailedListUntil;
import com.ruoyi.urgencyforts.domain.UrgencyTask;
import com.ruoyi.urgencyforts.mapper.UrgencyTaskMapper;
import com.ruoyi.urgencyforts.until.CurrentTask;
import com.ruoyi.wsdl.esbSendMessage.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("tokenAutoDelay")
public class TokenAutoDelay {
    protected final static Logger log = LoggerFactory.getLogger(TokenAutoDelay.class);
    private static final String link_launchApp_token = "launchApp://lingpaiapply$$#/lpTokenAutoDelay";



    public static void tokenAutoDelayTask(){
        log.info("自动推送即将到期的令牌数据。。。");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try{
            TokenApplyFormMapper tokenApplyFormMapper = SpringUtils.getBean("tokenApplyFormMapper");
            UrgencyTaskMapper urgencyTaskMapper = SpringUtils.getBean("urgencyTaskMapper");
            List<TokenApplyForm> tokenApplyForms = tokenApplyFormMapper.selectTokenAutoDelayList();
            List<UrgencyTask> urgencyTasks = urgencyTaskMapper.selectTokenAutoDelayList();
            log.info("自动申请令牌数据。。。"+tokenApplyForms.toString());
            log.info("紧急变更申请令牌数据。。。"+urgencyTasks.toString());
            if(tokenApplyForms.size() == 0 && urgencyTasks.size() == 0){
                log.info("没有即将到期的令牌数据。。。");
                return;
            }else{
                if(tokenApplyForms.size() != 0){
                    for (TokenApplyForm tokenApplyForm : tokenApplyForms) {
                        List<String> userList = new ArrayList<>();
                        userList.add(tokenApplyForm.getEmployeeId());
                        /*String post = HttpClientUtil.post(HttpClientUtil.getSoapStr(userList,
                                "令牌到期通知", "您申请的令牌将在" + tokenApplyForm.getEndDate() + "到期！", link_launchApp_token + "?seqNo=" + tokenApplyForm.getSeqno()+"&category=1"));*/
                        Map<String, String> map = FortDetailedListUntil.esbSendMessage(userList,
                                "您申请的令牌将在" + sdf.format(tokenApplyForm.getEndDate()) + "到期！如需延长令牌时间请点击进入申请！",//list集合去重
                                "令牌到期通知", link_launchApp_token + "?seqNo=" + tokenApplyForm.getSeqno() + "&category=1&endDate="+sdf.format(tokenApplyForm.getEndDate())+
                                        "&userId="+tokenApplyForm.getEmployeeId()+"&openDistinct="+tokenApplyForm.getOpenDistinct());
                        log.info("推送内容"+map);
                        if("COMPLETE".equals(map.get("transtatus"))){
                            int result = tokenApplyFormMapper.updateTokenAutoDelay(tokenApplyForm.getSeqno());
                            log.info("更改数据内容"+result);
                            if(result<0){
                                log.info("更改数据内容失败"+tokenApplyForm.getSeqno());
                                return;
                            }
                        }
                    }
                }
                if(urgencyTasks.size() != 0){
                    for (UrgencyTask urgencyTask : urgencyTasks) {
                        List<String> userList = new ArrayList<>();
                        userList.add(urgencyTask.getEmployeeId());
                       /* String post = HttpClientUtil.post(HttpClientUtil.getSoapStr(userList,
                                "令牌到期通知", "您申请的令牌将在" + urgencyTask.getEndDate() + "到期！", link_launchApp_token + "?seqNo=" + urgencyTask.getSeqNo()+"&category=2"));*/
                        Map<String, String> map = FortDetailedListUntil.esbSendMessage(userList,
                                "您申请的令牌将在" + urgencyTask.getEndDate() + "到期！如需延长令牌时间请点击进入申请！",//list集合去重
                                "令牌到期通知", link_launchApp_token + "?seqNo=" + urgencyTask.getSeqNo() +
                                        "&category=2&endDate="+urgencyTask.getEndDate()+
                                        "&userId="+urgencyTask.getEmployeeId()+"&openDistinct=0");

                        if("COMPLETE".equals(map.get("transtatus"))){
                            int result = urgencyTaskMapper.updateTokenAutoDelay(urgencyTask.getSeqNo());
                            log.info("更改数据内容"+result);
                            if(result<0){
                                log.info("更改数据内容失败"+urgencyTask.getSeqNo());
                                return;
                            }
                        }
                    }
                }
                log.info("推送完成。。。");

            }
        }catch (Exception e){
            log.error("令牌自动延期数据推送失败。。。",e);
        }
    }
}
