package com.bootdo.system.service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.domain.CodeYearsDO;

public interface CodeYearsService {

    /**
     * get
     *
     * @param id
     * @return
     */
    CodeYearsDO get(Long id);

    /**
     * list
     *
     * @param map
     * @return
     */
    List<CodeYearsDO> list(Map<String, Object> map);

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
    int save(CodeYearsDO dict);

    /**
     * update
     *
     * @param dict
     * @return
     */
    int update(CodeYearsDO dict);

    /**
     * remove
     *
     * @param id
     * @return
     */
    int remove(String id);

}
