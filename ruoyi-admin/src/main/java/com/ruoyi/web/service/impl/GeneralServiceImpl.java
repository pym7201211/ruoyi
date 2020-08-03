package com.ruoyi.web.service.impl;

import com.ruoyi.forts.domain.Employer;
import com.ruoyi.forts.domain.Org;
import com.ruoyi.forts.domain.SubTeam;
import com.ruoyi.web.mapper.GeneralMapper;
import com.ruoyi.web.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralServiceImpl implements GeneralService {

    @Autowired
    private GeneralMapper generalMapper;

    @Override
    public List<Employer> selectEmployeesAll() {
        return generalMapper.selectUserList();
    }

    @Override
    public List<Org> selectOrgAll(String pid,String orgtype) {
        return generalMapper.selectOrgList(pid,orgtype);
    }

    @Override
    public Org selectOrgByOrgId(String orgid) {
        return generalMapper.selectOrgByOrgId(orgid);
    }

    @Override
    public List<SubTeam> getAllSubTeam() {
        return generalMapper.selectAllSubTeam();
    }
}
