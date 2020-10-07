package com.bootdo.system.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootdo.system.dao.CodeStateDao;
import com.bootdo.system.domain.CodeStateDO;
import com.bootdo.system.service.CodeStateService;

/**
 * DictServiceImpl
 *
 * @author system
 * @date 2018.09.26
 */
@Service
public class CodeStateServiceImpl implements CodeStateService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private CodeStateDao codeStateDao;

    @Override
    public CodeStateDO get(Long id) {
        return codeStateDao.get(id);
    }

    @Override
    public List<CodeStateDO> list(Map<String, Object> map) {
        return codeStateDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return codeStateDao.count(map);
    }

    @Override
    @Transactional
	public int save(CodeStateDO dict) {
        return codeStateDao.save(dict);
    }

    @Override
    @Transactional
    public int update(CodeStateDO dict) {
        return codeStateDao.update(dict);
    }

    @Override
    public int remove(String id) {
        return codeStateDao.remove(id);
    }

}
