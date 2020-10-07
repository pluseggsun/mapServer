package com.bootdo.system.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.utils.AsciiUtil;
import com.bootdo.common.utils.UploadUtils;
import com.bootdo.system.dao.AddressDao;
import com.bootdo.system.dao.ImageDao;
import com.bootdo.system.domain.ImageAddressDO;
import com.bootdo.system.service.XzylService;

/**
 * DictServiceImpl
 *
 * @author system
 * @date 2018.09.26
 */
@Service
public class XzylServiceImpl implements XzylService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ImageDao imageDao;

    @Override
    public ImageAddressDO get(Long id) {
    	ImageAddressDO dict = addressDao.getByMapCoordinates(id);
		toEntryBj(dict);
        return dict;
    }

    @Override
    public List<ImageAddressDO> list(Map<String, Object> map) {
    	toMapQj(map);
        return addressDao.listAll(map);
    }

    @Override
    public int count(Map<String, Object> map) {
    	toMapQj(map);
        return addressDao.countAll(map);
    }

    @Override
    @Transactional
	public int save(ImageAddressDO dict, MultipartFile[] imgFile) {
    	toEntryQj(dict);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("address1", dict.getAddress1());
		map.put("address2", dict.getAddress2());
		map.put("address3", dict.getAddress3());
		map.put("address4", dict.getAddress4());
		map.put("address5", dict.getAddress5());
		List<ImageAddressDO> listImgAddr = addressDao.listAll(map);
		if (!StringUtils.isBlank(dict.getAddress2()) && !StringUtils.isBlank(dict.getAddress3())
				&& !StringUtils.isBlank(dict.getAddress4()) && !StringUtils.isBlank(dict.getAddress5()) && listImgAddr.size() == 0  ) {
			int mapId = addressDao.save(dict);
			dict.setMapid(dict.getAddressno());
		} else {
			if(listImgAddr.size() == 1 && !StringUtils.isBlank(dict.getAddress2()) && !StringUtils.isBlank(dict.getAddress3())
					&& !StringUtils.isBlank(dict.getAddress4()) && !StringUtils.isBlank(dict.getAddress5()) ){
				dict.setMapid(listImgAddr.get(0).getAddressno());
			}
		}
		for (int i = 0; i < imgFile.length; i++) {
			// 拿到文件名
			String filename = imgFile[i].getOriginalFilename();
			// 存放上传图片的文件夹
			File fileDir = UploadUtils.getImgDirFile();
			// 输出文件夹绝对路径 -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
			logger.info(fileDir.getAbsolutePath());

			try {
				// 构建真实的文件路径
				String hzName = filename.substring(filename.lastIndexOf("."), filename.length());
				File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
				/* 上传图片到 -> 绝对路径 */
				imgFile[i].transferTo(newFile);

				BufferedImage sourceImg = ImageIO.read(new FileInputStream(newFile));
				dict.setImageresonew(sourceImg.getWidth() +"*"+sourceImg.getHeight());
				dict.setImageresoold(sourceImg.getWidth() +"*"+sourceImg.getHeight());
			} catch (IOException e) {
				e.printStackTrace();
			}
			dict.setImage(filename);
			imageDao.save(dict);
		}
		return 1;
	}

    @Override
    @Transactional
    public int update(ImageAddressDO dict, MultipartFile[] imgFile) {
    	toEntryQj(dict);
    	/*修改时校验修改的地址是否存在*/
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("address1", dict.getAddress1());
		map.put("address2", dict.getAddress2());
		map.put("address3", dict.getAddress3());
		map.put("address4", dict.getAddress4());
		map.put("address5", dict.getAddress5());
		List<ImageAddressDO> listImgAddr;
		if (!StringUtils.isBlank(dict.getAddress2()) && !StringUtils.isBlank(dict.getAddress3()) && 
				!StringUtils.isBlank(dict.getAddress4()) && !StringUtils.isBlank(dict.getAddress5())) {
			listImgAddr = addressDao.listAll(map);
			if(listImgAddr.size() > 0) {
				addressDao.update(dict);
				dict.setMapid(listImgAddr.get(0).getMapid());
			} else {
				addressDao.save(dict);
				dict.setMapid(dict.getAddressno());
			}
		}
		/*只有地址表做删除*/
		for (int i = 0; i < imgFile.length; i++) {
			// 拿到文件名
			String filename = imgFile[i].getOriginalFilename();
			// 存放上传图片的文件夹
			File fileDir = UploadUtils.getImgDirFile();
			// 输出文件夹绝对路径 -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
			logger.info(fileDir.getAbsolutePath());

			try {
				// 构建真实的文件路径
				File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
				/* 上传图片到 -> 绝对路径 */
				imgFile[i].transferTo(newFile);

				BufferedImage sourceImg = ImageIO.read(new FileInputStream(newFile));
				dict.setImageresonew(sourceImg.getWidth() +"*"+sourceImg.getHeight());
				dict.setImageresoold(sourceImg.getWidth() +"*"+sourceImg.getHeight());
			} catch (IOException e) {
				e.printStackTrace();
			}
			dict.setImage(filename);
			imageDao.update(dict);
		}
		if(imgFile.length == 0) {
			imageDao.update(dict);
		}
//		Map<String, Object> imgMap = new HashMap<String, Object>();
//		imgMap.put("mapid", dict.getAddressno());
//		List<ImageAddressDO> imgList = imageDao.list(imgMap);
//		if (imgList.size() == 0) {
//			addressDao.remove(dict.getAddressno());
//		}
		return 1;
    }

    @Override
    public int remove(Long id) {
        return imageDao.remove(id);
    }
    
    public void toEntryQj(ImageAddressDO dict) {
    	
    	if (!StringUtils.isBlank(dict.getAddress3())) {
    		dict.setAddress3(AsciiUtil.dbc2sbcCase(dict.getAddress3()));
		}
    	if (!StringUtils.isBlank(dict.getAddress4())) {
    		dict.setAddress4(AsciiUtil.dbc2sbcCase(dict.getAddress4()));
		}
    	if (!StringUtils.isBlank(dict.getAddress3())) {
    		dict.setAddress5(AsciiUtil.dbc2sbcCase(dict.getAddress5()));
		}
    }
    
	public void toMapQj(Map<String, Object> dict) {

		if (null != dict.get("address3") && dict.get("address3") != "") {
			dict.put("address3", AsciiUtil.dbc2sbcCase(dict.get("address3").toString().trim()));
		}
		if (null != dict.get("address4") && dict.get("address4") != "") {
			dict.put("address4", AsciiUtil.dbc2sbcCase(dict.get("address4").toString().trim()));
		}
		if (null != dict.get("address5") && dict.get("address5") != "") {
			dict.put("address5", AsciiUtil.dbc2sbcCase(dict.get("address5").toString().trim()));
		}

	}
	
  public void toEntryBj(ImageAddressDO dict) {
    	
    	if (!StringUtils.isBlank(dict.getAddress3())) {
    		dict.setAddress3(AsciiUtil.sbc2dbcCase(dict.getAddress3()));
		}
    	if (!StringUtils.isBlank(dict.getAddress4())) {
    		dict.setAddress4(AsciiUtil.sbc2dbcCase(dict.getAddress4()));
		}
    	if (!StringUtils.isBlank(dict.getAddress3())) {
    		dict.setAddress5(AsciiUtil.sbc2dbcCase(dict.getAddress5()));
		}
    }
    
	public void toMapBj(Map<String, Object> dict) {

		if (null != dict.get("address3") && dict.get("address3") != "") {
			dict.put("address3", AsciiUtil.sbc2dbcCase(dict.get("address3").toString().trim()));
		}
		if (null != dict.get("address4") && dict.get("address4") != "") {
			dict.put("address4", AsciiUtil.sbc2dbcCase(dict.get("address4").toString().trim()));
		}
		if (null != dict.get("address5") && dict.get("address5") != "") {
			dict.put("address5", AsciiUtil.sbc2dbcCase(dict.get("address5").toString().trim()));
		}

	}
	
 	@Override
    @Transactional
    public int updateAddr(ImageAddressDO dict) {
    	toEntryQj(dict);
    	/*修改时校验修改的地址是否存在*/
		ImageAddressDO imgAddr = addressDao.get(dict.getAddressno());
		if(imgAddr != null) {
			addressDao.update(dict);
		}
		return 1;
    }

	@Override
	public List<ImageAddressDO> listMapGoogleAll(Map<String, Object> map) {
    	toMapQj(map);
        return addressDao.listMapGoogleAll(map);
	}

	@Override
	public List<ImageAddressDO> listMapGoogleById(Map<String, Object> map) {
    	toMapQj(map);
        return addressDao.listMapGoogleById(map);
	}

}
