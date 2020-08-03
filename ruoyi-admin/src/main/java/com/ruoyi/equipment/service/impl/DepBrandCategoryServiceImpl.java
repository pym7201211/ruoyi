package com.ruoyi.equipment.service.impl;

import com.ruoyi.equipment.domain.DepNumberCategory;
import com.ruoyi.equipment.mapper.DepBrandCategoryMapper;
import com.ruoyi.equipment.mapper.DepNumberCategoryMapper;
import com.ruoyi.equipment.service.IDepBrandCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 设备数量Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@Service
public class DepBrandCategoryServiceImpl implements IDepBrandCategoryService
{
    @Autowired
    private DepBrandCategoryMapper depBrandCategoryMapper;

    @Autowired
    private DepNumberCategoryMapper depNumberCategoryMapper;


    /**
     * 查询设备数量
     * 
     * @param identity 设备数量ID
     * @return 设备数量
     */
    @Override
    public DepNumberCategory selectDepBrandCategoryById(String identity)
    {
        return depBrandCategoryMapper.selectDepBrandCategoryById(identity);
    }

    /**
     * 查询设备数量列表
     * 
     * @param depNumberCategory 设备数量
     * @return 设备数量
     */
    @Override
    public List<DepNumberCategory> selectDepBrandCategoryList(DepNumberCategory depNumberCategory)
    {
        return depBrandCategoryMapper.selectDepBrandCategoryList(depNumberCategory);
    }


    /**
     * 查询设备数量历史列表
     *
     * @param id 设备数量
     * @return 设备数量集合
     */
    @Override
    public List<DepNumberCategory> selectDepBrandCategoryListHis(String id) {
        return depBrandCategoryMapper.selectDepBrandCategoryListHis(id);
    }

    /**
     * 新增设备数量
     * 
     * @param depNumberCategory 设备数量
     * @return 结果
     */
    @Override
    public int insertDepBrandCategory(DepNumberCategory depNumberCategory)
    {
        int i = depBrandCategoryMapper.insertDepBrandCategory(depNumberCategory);
        int number = depBrandCategoryMapper.selectNumberCategory(depNumberCategory.getIdentity(), depNumberCategory.getTwoDep());
        if(i>0){
            depBrandCategoryMapper.insertDepBrandCategoryHis(depNumberCategory);
            automaticUpdate(depNumberCategory);
        }
        if(number==0){
            //获取设备概览的id
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String format = sdf.format(new Date());
            Map<String, Object> mapId = new HashMap<>();
            mapId = depNumberCategoryMapper.selectNextId(format);
            if(!mapId.get("ID").toString().equals(" ")){
                depNumberCategory.setId(mapId.get("ID").toString());
            }else{
                depNumberCategory.setId(format+"0001");
            }
            depNumberCategoryMapper.insertDepNumberCategory(depNumberCategory);
            depNumberCategoryMapper.insertDepNumberCategoryHis(depNumberCategory);
        }
        return i;
    }

    /**
     * 查询设备类别
     *
     * @return 结果
     */
    @Override
    public List<DepNumberCategory> selectCategory() {

        List<DepNumberCategory> depNumberCategories = depBrandCategoryMapper.selectCategory();
        for (DepNumberCategory depNumberCategory : depNumberCategories) {
            List<Map<String,Object>> list = new ArrayList<>();
            String[] nameList = depNumberCategory.getName().split(",");
            String[] identityList = depNumberCategory.getIdentity().split(",");
            for(int i = 0;i<nameList.length;i++){
                Map<String,Object> map = new HashMap<>();
                map.put("name",nameList[i]);
                map.put("identity",identityList[i]);
                list.add(map);
            }
            depNumberCategory.setCategoryList(list);
        }
        return depNumberCategories;
    }


    /**
     * 修改设备数量
     * 
     * @param depNumberCategory 设备数量
     * @return 结果
     */
    @Override
    public int updateDepBrandCategory(DepNumberCategory depNumberCategory)
    {
        int i = depBrandCategoryMapper.updateDepBrandCategory(depNumberCategory);
        if(i>0){
            depBrandCategoryMapper.insertDepBrandCategoryHis(depNumberCategory);
            automaticUpdate(depNumberCategory);
        }
        return i;
    }

    /**
     * 删除设备数量对象
     * 
     * @param id 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDepBrandCategoryByIds(DepNumberCategory depNumberCategory,String id)
    {
        //先根据id查出部门，因为删除之后无法查出
        Map<String, Object> mapDep = depBrandCategoryMapper.selectCategoryDep(depNumberCategory.getId());
        int i = depBrandCategoryMapper.deleteDepBrandCategoryByIds(id);
        if(i>0){
            depBrandCategoryMapper.insertDepBrandCategoryHis(depNumberCategory);
            depNumberCategory.setTwoDep(mapDep.get("TWODEP").toString());
            automaticUpdate(depNumberCategory);
        }
        return i;
    }

    /**
     * 删除设备数量信息
     * 
     * @param identity 设备数量ID
     * @return 结果
     */
    @Override
    public int deleteDepBrandCategoryById(String identity)
    {
        return depBrandCategoryMapper.deleteDepBrandCategoryById(identity);
    }

    @Override
    public Map<String, Object> selectNextId(String id) {
        return depBrandCategoryMapper.selectNextId(id);
    }

    public void automaticUpdate(DepNumberCategory depNumberCategory){
        //根据设备编号查出该设备的全新和利旧
        //先根据id查出是哪家分行的
        String twoDep = "";
        if(!"删除".equals(depNumberCategory.getStatus().toString())){
            Map<String, Object> mapDep = depBrandCategoryMapper.selectCategoryDep(depNumberCategory.getId());
            twoDep = mapDep.get("TWODEP").toString();
        }else{
            twoDep = depNumberCategory.getTwoDep();
        }
        String identity = depNumberCategory.getIdentity();
        Map<String, Object> map = new HashMap<>();
         map = depBrandCategoryMapper.selectCategoryNum(identity,twoDep);
        String newSum = "0";
        String oldSum = "0";
        if(map!= null){
            newSum = map.get("NEWSUM").toString();
            oldSum = map.get("OLDSUM").toString();
        }

        depBrandCategoryMapper.updateCategoryTable(identity, newSum, oldSum,twoDep);

    }
}
