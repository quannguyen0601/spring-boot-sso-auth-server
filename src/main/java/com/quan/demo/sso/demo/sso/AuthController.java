package com.quan.demo.sso.demo.sso;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    @RequestMapping("/sso-login")
    public  String loginPage(ModelAndView modelAndView){
        return "login-page";
    }
}
