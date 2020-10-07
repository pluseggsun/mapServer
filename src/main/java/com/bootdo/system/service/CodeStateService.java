package com.bootdo.system.service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.domain.CodeStateDO;

public interface CodeStateService {

    /**
     * get
     *
     * @param id
     * @return
     */
    CodeStateDO get(Long id);

    /**
     * list
     *
     * @param map
     * @return
     */
    List<CodeStateDO> list(Map<String, Object> map);

    /**
     * count
     *
     * @param map
     * @return
     */
    int count(Map<String, Object> map);

    /**
     * save
     *
     * @param dict
     * @return
     */
    int save(CodeStateDO dict);

    /**
     * update
     *
     * @param dict
     * @return
     */
    int update(CodeStateDO dict);

    /**
     * remove
     *
     * @param id
     * @return
     */
    int remove(String id);

}
