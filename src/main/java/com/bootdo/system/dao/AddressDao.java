package com.bootdo.system.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.system.domain.ImageAddressDO;


@Mapper
public interface AddressDao {

    /**
     * get
     *
     * @param id
     * @return
     */
    ImageAddressDO get(Long id);

    /**
     * list
     *
     * @param map
     * @return
     */
    List<ImageAddressDO> list(Map<String, Object> map);

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
    int save(ImageAddressDO dict);

    /**
     * update
     *
     * @param dict
     * @return
     */
    int update(ImageAddressDO dict);

    /**
     * remove
     *
     * @param id
     * @return
     */
    int remove(Long id);

    int removeUpd(Long id);
    
    ImageAddressDO getByMapCoordinates(Long id);

    List<ImageAddressDO> listAll(Map<String, Object> map);

    int countAll(Map<String, Object> map);
    

    List<ImageAddressDO> listMapGoogleAll(Map<String, Object> map);

    List<ImageAddressDO> listMapGoogleById(Map<String, Object> map);
    
}
