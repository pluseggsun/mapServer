package com.bootdo.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseInfoDao {


    List<Map<String,Object>> getImageCode(Map<String, Object> map);

    List<Map<String,Object>> getStatusCode(Map<String, Object> map);
    
    List<Map<String,Object>> getCityInfo(Map<String, Object> map);
  
}
