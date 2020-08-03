package com.ruoyi.web.service;

import com.ruoyi.forts.domain.Employer;
import com.ruoyi.forts.domain.Org;
import com.ruoyi.forts.domain.SubTeam;
import com.ruoyi.system.domain.SysUser;

import java.util.List;

public interface GeneralService {

    /**
     * 查询所有角色
     *
     * @param
     * @return 结果
     */
    public List<Employer> selectEmployeesAll();
    /**
     * 查询所有组织机构
     *
     * @param
     * @return 结果
     */
    public List<Org> selectOrgAll(String pid,String orgtype);
    /**
     * 根据公司id查询公司名称
     *
     * @param
     * @return 结果
     */
    public Org selectOrgByOrgId(String orgid);
    /**
     * 得到所有团队名称
     *
     * @param
     * @return 结果
     */
    public List<SubTeam> getAllSubTeam();

}
