package com.bootdo.system.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.system.domain.CoordinatesDO;


@Mapper
public interface XzylDao {

    /**
     * get
     *
     * @param id
     * @return
     */
    CoordinatesDO get(Long id);

    /**
     * list
     *
     * @param map
     * @return
     */
    List<CoordinatesDO> list(Map<String, Object> map);

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
    int save(CoordinatesDO dict);

    /**
     * update
     *
     * @param dict
     * @return
     */
    int update(CoordinatesDO dict);

    /**
     * remove
     *
     * @param id
     * @return
     */
    int remove(Long id);
}
