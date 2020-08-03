package com.ruoyi.forts.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 搜索系统人员对象PERSON
 * 
 * @author mengdehu
 * @date 2020-01-03
 */
public class Org extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    /** 公司Id */
    @Excel(name = "公司Id")
    private String orgId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String orgName;

    /** 公司类别 */
    @Excel(name = "公司类别")
    private String orgtype;

    /** 公司父级 */
    @Excel(name = "公司父级")
    private String orgPid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(String orgtype) {
        this.orgtype = orgtype;
    }

    public String getOrgPid() {
        return orgPid;
    }

    public void setOrgPid(String orgPid) {
        this.orgPid = orgPid;
    }

    @Override
    public String toString() {
        return "Org{" +
                "id=" + id +
                ", orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgtype='" + orgtype + '\'' +
                ", orgPid='" + orgPid + '\'' +
                '}';
    }
}
