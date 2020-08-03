package com.ruoyi.ruoyiforts.mapper;

import com.ruoyi.ruoyiforts.domain.TokenApplyForms;
import com.ruoyi.urgencyforts.domain.UrgencyTask;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.crypto.hash.Hash;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
     * 令牌登记mapper层
     * @author mengdehu
     * @since  2019-11-11
     */
    public interface FortMapper {

    /**
     * 联合告警令牌申请历史查询
     * @return
     */
    public List<HashMap<String,String>> selectApplyAndResult(@Param("employeeId") String employeeId,@Param("ip")String ip);

    /**
     * 令牌首页查询和详情
     * @return
     */
    public List<HashMap<String,String>> selectApplyAndIPResult(@Param("systemName") String systemName);


    /**
     * 新增令牌登记申请
     *
     * @param tokenApplyForm 令牌登记申请
     * @return 结果
     */
    public int insertTokenApplyForm(TokenApplyForms tokenApplyForm);

    /**
     * 令牌事由登记历史
     *
     * @param tokenApplyForm 令牌事由登记历史
     * @return 结果
     */
    public int insertTokenApplyUpdate(TokenApplyForms tokenApplyForm);

    /**
     * 查询令牌最大id
     *
     * @return 结果
     */
    public Long selectMaxTokenId();

        /**
         * 获取常用语
         * @param singId
         * @return
         */
    public List<HashMap<String,String>> getCommonLanguage(@Param("singId") String singId);

        /**
         * 获取上级领导信息
         * @param userId
         * @return
         */
    public List<HashMap<String,String>> getTwoOrgInfomation(@Param("userId") String userId);


        /**
         * 系统名称模糊查询
         * @param userId
         * @param systemName
         * @return
         */
    public List<HashMap<String,String>>
    getLikeSystem(@Param("userId") String userId,@Param("systemName") String systemName);


        /**
         * 远程vpn密码表
         * @param userName
         * @return
         */
        public HashMap<String,Object> vpnInformation(@Param("userName") String userName);

        public int addVpnInfo(@Param("userName") String userName,@Param("password") String password,
                              @Param("accountStatus") String accountStatus,@Param("useNum") BigDecimal useNum,
                              @Param("employeeId")String employeeId,
                              @Param("uniqueness") String uniqueness);

        /**
         * 查询全表的部门
         * @return
         */
        public List<HashMap<String,String>> getAllSystemName(@Param("systemName") String systemName);

        public int updateVpnInfo(@Param("userName") String userName,@Param("password") String password,
                              @Param("accountStatus") String accountStatus,@Param("useNum") BigDecimal useNum,
                                 @Param("id") BigDecimal id);

        /**
         * 查询账户和密码的次数
         * @return
         */
        public String selectMaxCount();

        public HashMap selectApprovalInfo(@Param("seNo") String seNo);

        public int updateApprovalInfo(@Param("approvalStatus") String approvalStatus,
                                      @Param("uniquenessCode") String uniquenessCode,
                                      @Param("remark") String remark,
                                      @Param("approvalName") String approvalName,
                                      @Param("applyStatus") String applyStatus,
                                      @Param("seNo") String seNo);

        public int updateApprovalInfoTask(@Param("approvalStatus") String approvalStatus,
                                      @Param("uniquenessCode") String uniquenessCode,
                                      @Param("remark") String remark,
                                      @Param("approvalName") String approvalName,
                                      @Param("applyStatus") String applyStatus,
                                      @Param("seqNo") String seqNo,
                                      @Param("seNo") String seNo);

        public int selectSearchSystem(@Param("userId") String userId);

        public int isLeaderSend(@Param("leaderId") String leaderId);

        public String selectPhone(@Param("userId") String userId);

        public List<HashMap> getInfoByStaff(@Param("chargeTeam") String chargeTeam);

        public HashMap<String,String> selectInfoBySeqno (@Param("seqNo") String seqNo);

        public List<HashMap<String,String>> getSystemInfo(String[] ids);

        public Map<String,Object> getEmployeeNo(@Param("systemId") String systemId, @Param("ip") String ip);

        public Map<String,Object> getBaseValue(@Param("systemId") String systemId, @Param("ip") String ip);

        public Map<String,Object> getUpDownUser(@Param("systemId") String systemId);

        public Map<String,Object> getIndexName(@Param("systemId") String systemId, @Param("ip") String ip);
        public Map<String,Object> getDataValue(@Param("systemId") String systemId, @Param("ip") String ip, @Param("dataUser") String dataUser);

        public Map<String,Object> getPortNumber( @Param("ip") String ip);
        public int modifyDataValue(@Param("identity") String identity, @Param("ip") String ip,
                                   @Param("dataUser") String dataUser, @Param("dataValue") String dataValue);

    public int selectTokenNewIncident(@Param("tokenId") String tokenId,@Param("status") String status);

    public int updateTokenIncident(@Param("tokenId") String tokenId,@Param("newIncident") String newIncident,@Param("reason") String reason);

    public int updateTokenApplyIncident(@Param("tokenId") String tokenId,@Param("newIncident") String newIncident);

    public List<TokenApplyForms> selectApplyToken(@Param("seqNo") String seqNo);

    public int updateTokenApply(@Param("seqNo") String seqNo);


}
