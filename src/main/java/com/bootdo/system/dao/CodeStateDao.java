package com.bootdo.system.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.system.domain.CodeStateDO;


@Mapper
public interface CodeStateDao {

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
