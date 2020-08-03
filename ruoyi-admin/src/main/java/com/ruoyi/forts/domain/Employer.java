package com.ruoyi.forts.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 搜索系统人员对象PERSON
 * 
 * @author mengdehu
 * @date 2020-01-03
 */
public class Employer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    /** 人员id */
    @Excel(name = "人员id")
    private String employerId;

    /** 人员名称 */
    @Excel(name = "人员名称")
    private String employerName;

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", employerId='" + employerId + '\'' +
                ", employerName='" + employerName + '\'' +
                '}';
    }
}
