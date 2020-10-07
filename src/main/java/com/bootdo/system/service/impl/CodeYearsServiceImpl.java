package com.bootdo.system.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootdo.system.dao.CodeYearsDao;
import com.bootdo.system.domain.CodeYearsDO;
import com.bootdo.system.service.CodeYearsService;

/**
 * DictServiceImpl
 *
 * @author system
 * @date 2018.09.26
 */
@Service
public class CodeYearsServiceImpl implements CodeYearsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private CodeYearsDao codeYearsDao;

    @Override
    public CodeYearsDO get(Long id) {
        return codeYearsDao.get(id);
    }

    @Override
    public List<CodeYearsDO> list(Map<String, Object> map) {
        return codeYearsDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return codeYearsDao.count(map);
    }

    @Override
    @Transactional
	public int save(CodeYearsDO dict) {
        return codeYearsDao.save(dict);
    }

    @Override
    @Transactional
    public int update(CodeYearsDO dict) {
        return codeYearsDao.update(dict);
    }

    @Override
    public int remove(String id) {
        return codeYearsDao.remove(id);
    }

}
