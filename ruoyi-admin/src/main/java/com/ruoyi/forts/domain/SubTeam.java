package com.ruoyi.forts.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 搜索系所属团队名称
 * 
 * @author mengdehu
 * @date 2020-01-03
 */
public class SubTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    /** 领导Id */
    @Excel(name = "领导Id")
    private String leaderId;

    /** 领导名称 */
    @Excel(name = "领导名称")
    private String leaderName;

    /** 部门id */
    @Excel(name = "部门id")
    private String deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "SubTeam{" +
                "id=" + id +
                ", leaderId='" + leaderId + '\'' +
                ", leaderName='" + leaderName + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
