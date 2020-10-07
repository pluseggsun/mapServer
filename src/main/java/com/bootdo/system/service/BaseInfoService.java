package com.bootdo.system.service;

import java.util.List;
import java.util.Map;

public interface BaseInfoService {

    List<Map<String,Object>> getImageCode(Map<String, Object> map);

    List<Map<String,Object>> getStatusCode(Map<String, Object> map);
    
    List<Map<String,Object>> getCityInfo(Map<String, Object> map);
}
