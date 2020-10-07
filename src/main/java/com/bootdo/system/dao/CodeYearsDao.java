package com.bootdo.system.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.system.domain.CodeYearsDO;


@Mapper
public interface CodeYearsDao {

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
