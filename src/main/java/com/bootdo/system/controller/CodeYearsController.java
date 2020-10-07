package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.ReturnType;
import com.bootdo.system.domain.CodeYearsDO;
import com.bootdo.system.service.CodeYearsService;

@RequestMapping("/code/years")
@Controller
public class CodeYearsController extends BaseController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CodeYearsService codeYearsService;

	@GetMapping({ "/index" })
	String index(Model model) {
		return "code/years/years";
	}

	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<CodeYearsDO> dictList = codeYearsService.list(query);
		int total = codeYearsService.count(query);
		PageUtils pageUtils = new PageUtils(dictList, total);
		return pageUtils;
	}
	
	
	@GetMapping("/add")
	String add() {
		return "code/years/addyears";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		CodeYearsDO dict = codeYearsService.get(id);
		model.addAttribute("dict", dict);
		return "code/years/edityears";
	}

	
	@ResponseBody
	@PostMapping("/save")
	public ReturnType save(CodeYearsDO dict) {
		if (codeYearsService.save(dict) > 0) {
			return ReturnType.ok();
		}
		return ReturnType.error();
	}

	
	@ResponseBody
	@RequestMapping("/update")
	public ReturnType update(CodeYearsDO dict) {
		codeYearsService.update(dict);
		return ReturnType.ok();
	}

	@PostMapping("/remove")
	@ResponseBody
	public ReturnType remove(String id) {
		if (codeYearsService.remove(id) > 0) {
			return ReturnType.ok();
		}
		return ReturnType.error();
	}
	
}
