package com.ruoyi.weeklySchedule.mobilleTerminal.service;

import com.alibaba.fastjson.JSONObject;

public interface ProjectService {

    public JSONObject selectHostTeam(String json) throws Exception;

    public JSONObject selectDemandInfo(String json) throws Exception;

    public JSONObject deleteProjectBySerialNo(String json) throws Exception;

    public JSONObject getHostTeam() throws Exception;
}
