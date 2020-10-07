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
import com.bootdo.system.domain.CodeStateDO;
import com.bootdo.system.service.CodeStateService;

@RequestMapping("/code/state")
@Controller
public class CodeStateController extends BaseController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CodeStateService codeStateService;

	@GetMapping({ "/index" })
	String index(Model model) {
		return "code/state/state";
	}

	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<CodeStateDO> dictList = codeStateService.list(query);
		int total = codeStateService.count(query);
		PageUtils pageUtils = new PageUtils(dictList, total);
		return pageUtils;
	}
	
	
	@GetMapping("/add")
	String add() {
		return "code/state/addstate";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		CodeStateDO dict = codeStateService.get(id);
		model.addAttribute("dict", dict);
		return "code/state/editstate";
	}

	
	@ResponseBody
	@PostMapping("/save")
	public ReturnType save(CodeStateDO dict) {
		if (codeStateService.save(dict) > 0) {
			return ReturnType.ok();
		}
		return ReturnType.error();
	}

	
	@ResponseBody
	@RequestMapping("/update")
	public ReturnType update(CodeStateDO dict) {
		codeStateService.update(dict);
		return ReturnType.ok();
	}

	@ResponseBody
	@PostMapping("/remove") 
	public ReturnType remove(String id) {
		if (codeStateService.remove(id) > 0) {
			return ReturnType.ok();
		}
		return ReturnType.error();
	}
	
}
