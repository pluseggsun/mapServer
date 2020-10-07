package com.bootdo.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.bootdo.system.domain.ImageAddressDO;

public interface XzylService {

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
    int save(ImageAddressDO dict,MultipartFile[] imgFile);

    /**
     * update
     *
     * @param dict
     * @return
     */
    int update(ImageAddressDO dict, MultipartFile[] imgFile);
    
    int updateAddr(ImageAddressDO dict);

    /**
     * remove
     *
     * @param id
     * @return
     */
    int remove(Long id);
    

    List<ImageAddressDO> listMapGoogleAll(Map<String, Object> map);

    List<ImageAddressDO> listMapGoogleById(Map<String, Object> map);
    

}
