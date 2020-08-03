package com.ruoyi.ruoyiforts.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ruoyiforts.domain.TokenApplyForms;
import com.ruoyi.ruoyiforts.mapper.FortMapper;
import com.ruoyi.ruoyiforts.service.FortService;
import com.ruoyi.urgencyforts.domain.UrgencyTask;
import com.ruoyi.urgencyforts.mapper.UrgencyTaskMapper;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 令牌登记impl层
 * @author mengdehu
 * @since  2019-11-11
 */
@Service
public class FortServiceImpl implements FortService {
    private final static Logger log = LoggerFactory.getLogger(FortServiceImpl.class);
    private static int seq = 0;

    @Autowired
    private FortMapper fortMapper;

    @Autowired
    private UrgencyTaskMapper urgencyTaskMapper;

    /**
     * 令牌申请首页查询
     * @return
     */
    @Override
    public List<HashMap<String, String>> selectApplyAndResult(String employeeId,String ip) {
        return fortMapper.selectApplyAndResult(employeeId,ip);
    }

    /**
     * 联合告警令牌申请历史查询
     * @return
     */
    @Override
    public List<HashMap<String, String>> selectApplyAndIPResult(String systemName) {
        return fortMapper.selectApplyAndIPResult(systemName);
    }

    /**
     * 新增令牌登记申请
     *
     * @param tokenApplyForm 令牌登记申请
     * @return 结果
     */
    @Override
    public boolean insertTokenApplyForm(TokenApplyForms tokenApplyForm)
    {
        boolean result = fortMapper.insertTokenApplyForm(tokenApplyForm) > 0;
        Long tokenId = fortMapper.selectMaxTokenId();
        tokenApplyForm.setTokenId(tokenId);
        fortMapper.insertTokenApplyUpdate(tokenApplyForm);
        return result;
    }

    @Override
    public List<HashMap<String, String>> getCommonLanguage(String singId) {
        return fortMapper.getCommonLanguage(singId);
    }

    @Override
    public List<HashMap<String, String>> getTwoOrgInfomation(String userId) {
        return fortMapper.getTwoOrgInfomation(userId);
    }

    @Override
    public List<HashMap<String, String>> getLikeSystem(String userId, String systemName) {
        return fortMapper.getLikeSystem(userId,systemName);
    }

    @Override
    public HashMap<String, Object> vpnInformation(String userName) {
        return fortMapper.vpnInformation(userName);
    }

    @Override
    public boolean addVpnInfo(String userName, String password, String accountStatus, BigDecimal useNum,
                              String employeeId,String uniqueness) {
        return fortMapper.addVpnInfo(userName,password,accountStatus,useNum,employeeId,uniqueness) > 0;
    }

    @Override
    public boolean updateVpnInfo(String userName, String password, String accountStatus, BigDecimal useNum,BigDecimal id) {
        return fortMapper.updateVpnInfo(userName,password,accountStatus,useNum,id) > 0;
    }

    @Override
    public List<HashMap<String, String>> getAllSystemName(String systemName) {
        return fortMapper.getAllSystemName(systemName);
    }

    @Override
    public String selectMaxCount() {
        return fortMapper.selectMaxCount();
    }

    @Override
    public HashMap selectApprovalInfo(String seNo) {
        return fortMapper.selectApprovalInfo(seNo);
    }

    @Override
    public int updateApprovalInfo(String approvalStatus, String uniquenessCode, String remark,
                                  String approvalName, String applyStatus, String seNo) {
        return fortMapper.updateApprovalInfo(approvalStatus,uniquenessCode,remark,approvalName,applyStatus,seNo);
    }

    @Override
    public boolean selectSearchSystem(String userId) {
        return fortMapper.selectSearchSystem(userId) > 0;
    }

    @Override
    public boolean isLeaderSend(String leaderId) {
        return fortMapper.isLeaderSend(leaderId) > 0;
    }

    @Override
    public String selectPhone(String userId) {
        return fortMapper.selectPhone(userId);
    }

    @Override
    public List<HashMap> getInfoByStaff(String chargeTeam) {
        return fortMapper.getInfoByStaff(chargeTeam);
    }

    @Override
    public HashMap<String, String> selectInfoBySeqno(String seqNo) {
        return fortMapper.selectInfoBySeqno(seqNo);
    }

    @Override
    public ModelMap getSystemInfo(String list) throws Exception {
        ModelMap modelMap = new ModelMap();
        try{
            log.info("list ========>>>"+list);
            String strip = StringUtils.strip(list.replaceAll(" ",""), "[]");
            String[] lists = strip.split(",");
            List<HashMap<String, String>> systemInfo = fortMapper.getSystemInfo(lists);
            modelMap.put("code","0");
            modelMap.put("systemInfo",systemInfo);
            return modelMap;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public ModelMap getEmployeeNo(String systemId, String ip) throws Exception {
        ModelMap modelMap = new ModelMap();
        try{
            Map<String,Object> employeeNo = fortMapper.getEmployeeNo(systemId,ip);
            Map<String,Object> upDownUser = fortMapper.getUpDownUser(systemId);
            modelMap.put("code","0");
            modelMap.put("employeeNo",employeeNo);
            modelMap.put("upDownUser",upDownUser);
            return modelMap;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public ModelMap getIndexName(String systemId, String ip) throws Exception {
        ModelMap modelMap = new ModelMap();
        try{
            Map<String, Object> portNumber = new HashMap<>();
            Map<String,Object> indexName = fortMapper.getIndexName(systemId,ip);
            portNumber = fortMapper.getPortNumber(ip);
            modelMap.put("code","0");
            modelMap.put("indexName",indexName);
            if(portNumber != null){
                String portnumber = portNumber.get("PORTNUMBER").toString().replaceAll(",", "','");
                portNumber.put("PORTNUMBER","'"+portnumber+"'");
                modelMap.put("portNumber",portNumber);
            }else{
                portNumber = new HashMap<>();
                portNumber.put("PORTNUMBER","Nodata");
                modelMap.put("portNumber",portNumber);
            }
            return modelMap;
        }catch (Exception e){
            throw new Exception(e);
        }    }

    @Override
    public ModelMap getDataValue(String systemId, String ip, String dataUser) {
        ModelMap modelMap = new ModelMap();
            Map<String, Object> portNumber = new HashMap<>();
            Map<String,Object> dataValue = fortMapper.getDataValue(systemId,ip,dataUser);
            modelMap.put("code","0");
            modelMap.put("dataValue",dataValue);
            return modelMap;
    }

    @Override
    public int modifyDataValue(String identity, String ip, String dataUser, String dataValue) {
        return fortMapper.modifyDataValue(identity,ip,dataUser,dataValue);
    }

    @Override
    public ModelMap updateTokenIncident(String tokenId, String newIncident,String reason) {
        ModelMap model = new ModelMap();
        int i = fortMapper.updateTokenApplyIncident(tokenId, newIncident);
        if(i == 0){
            model.put("code","1");
            model.put("msg","修改令牌事由失败");
            return model;
        }
        int result = fortMapper.updateTokenIncident(tokenId, newIncident,reason);
        if(result == 0){
            model.put("code","1");
            model.put("msg","修改失败");
        }else{
            model.put("code","0");
            model.put("msg","修改成功");
        }
        return model;
    }

    @Override
    public ModelMap selectTokenIncident(String tokenId) {
        ModelMap model = new ModelMap();
        int result = fortMapper.selectTokenNewIncident(tokenId,"");
        if(result == 0){
            model.put("code","1");
            model.put("msg","数据查询异常，请联系管理员！");
            return model;
        }
        int count = fortMapper.selectTokenNewIncident(tokenId,"1");
        if(count == 0){
            model.put("code","1");
            model.put("msg","事由仅允许修改一次，二次修改请联系管理员！");
        }else{
            model.put("code","0");
            model.put("msg","可以修改");
        }
        return model;
    }

    @Override
    public TokenApplyForms selectApplyToken(String seqNo) {
        List<TokenApplyForms> tokenApplyForms = fortMapper.selectApplyToken(seqNo);
        TokenApplyForms tokenApplyForm = tokenApplyForms.get(0);
        tokenApplyForm.setSeqno(getSeqNo());
        tokenApplyForm.setReserved2(null);
        tokenApplyForm.setReserved3(null);
        tokenApplyForm.setTokenId(null);
        return tokenApplyForm;
    }

    @Override
    public UrgencyTask selectUrgencyTask(String seqNo,String newEndDate,String newOpenDate) {
        List<UrgencyTask> urgencyTasks = urgencyTaskMapper.selectUrgencyTask(seqNo);
        UrgencyTask urgencyTask = urgencyTasks.get(0);
        urgencyTask.setSend(null);
        urgencyTask.setIsApply(null);
        urgencyTask.setSeqNo(getSeqNo());
        String sendContent = urgencyTask.getSendContent();
        String endDate = newEndDate;
        endDate = endDate.replaceAll("-","");
        endDate = endDate.substring(0,14);
        sendContent = sendContent.substring(0,26) +endDate+sendContent.substring(40);
        urgencyTask.setSendContent(sendContent);
        urgencyTask.setEndDate(newEndDate.replaceAll("-",""));
        urgencyTask.setCreateTime(new Date());
        urgencyTask.setOpenDate(newOpenDate.replaceAll("-",""));
        int result = urgencyTaskMapper.insertUrgencyTask(urgencyTask);
        return urgencyTask;
    }

    public static String getSeqNo(){
        synchronized (log){
            seq = seq < 999 ? ++seq : 0;
        }
        DecimalFormat df = new DecimalFormat("000");
        return String.valueOf(System.currentTimeMillis())+df.format(seq);
    }

    @Override
    public int updateTokenApply( String seqNo){
        return fortMapper.updateTokenApply(seqNo);
    }

    @Override
    public int updateUrgencyTaskBySeqNo(String seqNo) {
        return urgencyTaskMapper.updateUrgencyTaskBySeqNo(seqNo);
    }

    @Override
    public ModelMap isApplyFort(String seqNo, String category) {
        ModelMap model = new ModelMap();
        if("1".equals(category)){
            List<TokenApplyForms> tokenApplyForms = fortMapper.selectApplyToken(seqNo);
            TokenApplyForms tokenApplyForm = tokenApplyForms.get(0);
            if("1".equals(tokenApplyForm.getReserved3())){
                model.put("code","1");
                model.put("msg","不显示提交按钮");
            }else{
                model.put("code","0");
                model.put("msg","显示提交按钮");
            }
        }else{
            List<UrgencyTask> urgencyTasks = urgencyTaskMapper.selectUrgencyTask(seqNo);
            UrgencyTask urgencyTask = urgencyTasks.get(0);
            if("1".equals(urgencyTask.getIsApply())){
                model.put("code","1");
                model.put("msg","不显示提交按钮");
            }else{
                model.put("code","0");
                model.put("msg","显示提交按钮");
            }
        }
        return model;
    }

}
