package com.bootdo.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bootdo.common.controller.BaseController;

/**
 * LoginController
 *
 * @author system
 * @date 2018.09.26
 */
@Controller
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping({"/", ""})
    String welcome(Model model) {
        return "redirect:/index";
    }

    @GetMapping({"/index"})
    String index(Model model) {
        return "index_v1";
    }
    
//	@GetMapping({"/googlemap"})
//	String googleMap(Model model) {
//		return "system/map/googlemap";
//	}
	
	@GetMapping({"/mapgoogle"})
	String mapGoogle(Model model) {
		return "system/map/mapgoogle";
	}

//    @GetMapping("/login")
//    String login() {
//        return "login";
//    }

//    @PostMapping("/login")
//    @ResponseBody
//    ReturnType ajaxLogin(String username, String password) {
//
//        password = Md5Utils.encrypt(username, password);
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        Subject subject = SecurityUtils.getSubject();
//        try {
//            subject.login(token);
//            return ReturnType.ok();
//        } catch (AuthenticationException e) {
//            return ReturnType.error("用户或密码错误");
//        }
//    }

//    @GetMapping("/logout")
//    String logout() {
//        return "redirect:/login";
//    }
//
    @GetMapping("/main")
    String main() {
        return "main";
    }
//
//    @GetMapping("/test")
//    @ResponseBody
//    String test() {
//        return "1";
//    }

}
