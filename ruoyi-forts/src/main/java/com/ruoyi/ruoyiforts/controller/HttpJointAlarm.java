package com.ruoyi.ruoyiforts.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ruoyiforts.service.FortService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/HttpJointAlarm")
public class HttpJointAlarm {

    private final static Logger log = LoggerFactory.getLogger(HttpJointAlarm.class);
    private static final String chartset = "application/json;charset=UTF-8";


    @Autowired
    private FortService fortService;

    /**
     * 远程联合告警请求系统名称
     * @return
     */
    @RequestMapping(value = "/getSystemInfo" ,method = RequestMethod.POST,produces = chartset)
    public ModelMap getSystemInfo(@RequestParam(required = true) String list){
        ModelMap modelMap = null;
        try{
            if (StringUtils.isBlank(list)){
                modelMap.put("code","1");
                modelMap.put("msg","service_id 为必传项参数");
                return modelMap;
            }
            modelMap = fortService.getSystemInfo(list);
        }catch (Exception e){
            log.error("远程联合告警请求系统名称接口异常：",e);
        }
        log.info("getSystemInfo : "+modelMap);
        return modelMap;
    }

    /**
     * 根据系统号查询员工号
     * @param service_id
     * @return
     */
    @RequestMapping(value = "/getEmployeeNo",method = RequestMethod.POST,produces = chartset)
    public ModelMap getEmployeeNo(@RequestParam(required = false) String service_id,@RequestParam(required = false) String ip){
        ModelMap modelMap = new ModelMap();
        try{
            if (StringUtils.isBlank(service_id) && StringUtils.isBlank(ip)){
                modelMap.put("code","1");
                modelMap.put("msg","service_id 为必传项参数");
                return modelMap;
            }
            modelMap = fortService.getEmployeeNo(service_id,ip);
        }catch (Exception e){
            log.error("根据系统号查询员工号接口异常：",e);
        }
        log.info("getEmployeeNo "+modelMap);
        return modelMap;
    }

    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("8263");
        list.add("8162");
        list.add(" 8258");
        System.out.println(list);
        String aa = StringUtils.strip(list.toString().replaceAll(" ",""),"[]");
        List<String> strings = Arrays.asList(aa);
        System.out.println(strings);

    }

    /**
     * 根据系统号查询指标
     * @param service_id
     * @return
     */
    @RequestMapping(value = "/getIndexName",method = RequestMethod.POST,produces = chartset)
    public ModelMap getIndexName(@RequestParam(required = false) String service_id,@RequestParam(required = false) String ip){
        ModelMap modelMap = new ModelMap();
        try{
            if (StringUtils.isBlank(service_id) && StringUtils.isBlank(ip)){
                modelMap.put("code","1");
                modelMap.put("msg","service_id 为必传项参数");
                return modelMap;
            }
            modelMap = fortService.getIndexName(service_id,ip);
        }catch (Exception e){
            log.error("根据系统号查询指标接口异常：",e);
        }
        log.info("getIndexName "+modelMap);
        return modelMap;
    }

    /**
     * 根据系统号查询数据库用户值
     * @param service_id
     * @return
     */
    @RequestMapping(value = "/getDataValue",method = RequestMethod.POST,produces = chartset)
    public ModelMap getDataValue(@RequestParam(required = false) String service_id,@RequestParam(required = false) String ip,@RequestParam(required = false) String data_user){
        ModelMap modelMap = new ModelMap();
        try{
            if (StringUtils.isBlank(service_id) && StringUtils.isBlank(ip) && StringUtils.isBlank(data_user)){
                modelMap.put("code","1");
                modelMap.put("msg","service_id 为必传项参数");
                return modelMap;
            }
            modelMap = fortService.getDataValue(service_id,ip,data_user);
        }catch (Exception e){
            log.error("根据系统号查询数据库用户值：",e);
        }
        log.info("getDataValue "+modelMap);
        return modelMap;
    }
}
