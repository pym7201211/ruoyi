package com.ruoyi.urgencyforts.mapper;

import com.ruoyi.urgencyforts.domain.UrgencyTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 紧急变更线程Mapper接口
 * 
 * @author mengdehu
 * @date 2020-03-21
 */
public interface UrgencyTaskMapper 
{
    /**
     * 查询紧急变更线程
     * 
     * @param id 紧急变更线程ID
     * @return 紧急变更线程
     */
    public UrgencyTask selectUrgencyTaskById(Long id);

    /**
     * 查询紧急变更线程列表
     * 
     * @param urgencyTask 紧急变更线程
     * @return 紧急变更线程集合
     */
    public List<UrgencyTask> selectUrgencyTaskList(UrgencyTask urgencyTask);

    /**
     * 新增紧急变更线程
     * 
     * @param urgencyTask 紧急变更线程
     * @return 结果
     */
    public int insertUrgencyTask(UrgencyTask urgencyTask);

    /**
     * 修改紧急变更线程
     * 
     * @param urgencyTask 紧急变更线程
     * @return 结果
     */
    public int updateUrgencyTask(UrgencyTask urgencyTask);

    /**
     * 删除紧急变更线程
     * 
     * @param id 紧急变更线程ID
     * @return 结果
     */
    public int deleteUrgencyTaskById(Long id);

    /**
     * 批量删除紧急变更线程
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUrgencyTaskByIds(String[] ids);

    /**
     * 查询令牌延期数据
     *
     * @return 令牌延期数据
     */
    public List<UrgencyTask> selectTokenAutoDelayList();

    /**
     * 到期令牌推送成功
     *
     * @param seqNo 到期令牌推送成功
     * @return 结果
     */
    public int updateTokenAutoDelay(String seqNo);

    public List<UrgencyTask> selectUrgencyTask(@Param("seqNo") String seqNo);


    public int updateUrgencyTaskBySeqNo(@Param("seqNo") String seqNo);

}
