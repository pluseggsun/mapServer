package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.AsciiUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.ReturnType;
import com.bootdo.system.domain.ImageAddressDO;
import com.bootdo.system.service.XzylService;

@RequestMapping("/sys")
@Controller
public class XzylController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private XzylService xzylService;

	@GetMapping({ "/xzyl" })
	String index(Model model) {
		return "system/map/xzyl";
	}

	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		
		if (params.get("status") != null && StringUtils.isNotEmpty(params.get("status").toString())) {
			String status = params.get("status").toString();
			String[] statusArray = status.split(",");
			params.put("status", statusArray);
		}
		
		Query query = new Query(params);
		List<ImageAddressDO> dictList = xzylService.list(query);
		int total = xzylService.count(query);
		PageUtils pageUtils = new PageUtils(dictList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add() {
		return "system/map/addxzyl";
	}

	@GetMapping("/info/{id}")
	String info(@PathVariable("id") Long id, Model model) {
		ImageAddressDO dict = xzylService.get(id);
		model.addAttribute("dict", dict);
		model.addAttribute("image", dict.getImage());
		return "system/map/infoxzyl";
	}
	
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		ImageAddressDO dict = xzylService.get(id);
		model.addAttribute("dict", dict);
		model.addAttribute("image", dict.getImage());
		return "system/map/editxzyl";
	}

	@GetMapping("/updmap/{id}")
	String updmap(@PathVariable("id") Long id, Model model) {
		ImageAddressDO dict = xzylService.get(id);
		model.addAttribute("address", dict.getCityName() + dict.getTownName() 
			+ AsciiUtil.dbc2sbcCase(dict.getAddress3()) +" 丁目 "+ AsciiUtil.dbc2sbcCase(dict.getAddress4()) + " 番 " +AsciiUtil.dbc2sbcCase(dict.getAddress5()) +"  号  ");
		model.addAttribute("dict", dict);
		model.addAttribute("image", dict.getImage());
		return "system/map/editmapxzyl";
	}

	
	@ResponseBody
	@PostMapping("/save")
	public ReturnType save(ImageAddressDO dict, @RequestParam("file") MultipartFile[] imgFile) {
		if (imgFile.length == 0 ) {
			return ReturnType.error(1, "写真を選択してください。");
		}
		if (xzylService.save(dict,imgFile) > 0) {
			return ReturnType.ok();
		}
		return ReturnType.error();
	}

	
	@ResponseBody
	@RequestMapping("/update")
	public ReturnType update(ImageAddressDO dict, @RequestParam(value = "file", required = false) MultipartFile[] imgFile) {
		xzylService.update(dict,imgFile);
		return ReturnType.ok();
	}
	
	@ResponseBody
	@RequestMapping("/updateaddr")
	public ReturnType updateAddr(ImageAddressDO dict) {
		xzylService.updateAddr(dict);
		return ReturnType.ok();
	}

	
	@PostMapping("/remove")
	@ResponseBody
	public ReturnType remove(Long id) {
		if (xzylService.remove(id) > 0) {
			return ReturnType.ok();
		}
		return ReturnType.error();
	}
	

	@ResponseBody
	@GetMapping("/listall")
	public List<ImageAddressDO>  listMapGoogleAll(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<ImageAddressDO> dictList = xzylService.listMapGoogleAll(query);
		return dictList;
	}
	
	@GetMapping("/detail/{id}")
	String detailById(@PathVariable("id") Long id, Model model) {
		ImageAddressDO dict = xzylService.get(id);
		model.addAttribute("dict", dict);
		model.addAttribute("image", dict.getImage());
		return "system/map/detailxzyl";
	}
	
	@GetMapping("/detail/address")
	String detailByAddress(@RequestParam Map<String, Object> params, Model model) {
		Query query = new Query(params);
		List<ImageAddressDO> dictList = xzylService.listMapGoogleById(query);
		if (dictList.size() > 0) {
//			cityName=武蔵野市, townName=吉祥寺北町
			model.addAttribute("address", dictList.get(0).getCityName() + dictList.get(0).getTownName() 
					+ dictList.get(0).getAddress3() +" 丁目 "+ dictList.get(0).getAddress4() + " 番 " +dictList.get(0).getAddress5() +"  号  ");
			model.addAttribute("postmail", dictList.get(0).getPostmail());
			model.addAttribute("addresscontent", dictList.get(0).getAddresscontent());
			model.addAttribute("addressname", dictList.get(0).getAddressname());
		} else {
			model.addAttribute("address", "_");
			model.addAttribute("postmail", "_");
			model.addAttribute("addresscontent", "_");
			model.addAttribute("addressname", "_");
		}
		model.addAttribute("dictList", dictList);
		return "system/map/detailxzyl";
	}
	
}
