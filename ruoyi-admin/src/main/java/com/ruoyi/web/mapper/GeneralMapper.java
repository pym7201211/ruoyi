package com.ruoyi.web.mapper;


import com.ruoyi.forts.domain.Employer;
import com.ruoyi.forts.domain.Org;
import com.ruoyi.forts.domain.SubTeam;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface GeneralMapper {
    /**
     * 根据条件分页查询用户列表
     *
     * @param
     * @return 用户信息集合信息
     */
    public List<Employer> selectUserList();
    /**
     * 查询公司列表
     *
     * @param
     * @return 用户信息集合信息
     */
    public List<Org> selectOrgList(@Param("pid") String pid,@Param("orgtype") String orgtype);
    /**
     * 根据公司id查询公司信息
     *
     * @param
     * @return 用户信息集合信息
     */
    public Org selectOrgByOrgId(@Param("orgid") String orgid);
    /**
     * 得到所有团队名称
     *
     * @param
     * @return 结果
     */
    public List<SubTeam> selectAllSubTeam();

}
