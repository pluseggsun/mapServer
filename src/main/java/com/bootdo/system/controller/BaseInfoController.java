package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.Query;
import com.bootdo.system.service.BaseInfoService;

@RequestMapping("/base")
@Controller
public class BaseInfoController extends BaseController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BaseInfoService baseInfoService;

	@ResponseBody
	@GetMapping("/getcityinfo")
	public List<Map<String, Object>> getCityInfo(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<Map<String, Object>> list = baseInfoService.getCityInfo(query);
		return list;
	}
	
	@ResponseBody
	@GetMapping("/getimagecode")
	public List<Map<String, Object>> getImageCode(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<Map<String, Object>> list = baseInfoService.getImageCode(query);
		return list;
	}
	
	@ResponseBody
	@GetMapping("/getstatuscode")
	public List<Map<String, Object>> getStatusCode(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<Map<String, Object>> list = baseInfoService.getStatusCode(query);
		return list;
	}
	
}
