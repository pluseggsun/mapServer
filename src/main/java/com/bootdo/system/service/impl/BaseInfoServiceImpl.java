package com.bootdo.system.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.system.dao.BaseInfoDao;
import com.bootdo.system.service.BaseInfoService;

/**
 * DictServiceImpl
 *
 * @author system
 * @date 2018.09.26
 */
@Service
public class BaseInfoServiceImpl implements BaseInfoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private BaseInfoDao baseInfoDao;

	@Override
	public List<Map<String, Object>> getImageCode(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return baseInfoDao.getImageCode(map);
	}

	@Override
	public List<Map<String, Object>> getStatusCode(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return baseInfoDao.getStatusCode(map);
	}

	@Override
	public List<Map<String, Object>> getCityInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return baseInfoDao.getCityInfo(map);
	}
	
}
