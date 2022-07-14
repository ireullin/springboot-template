package com.ireullin.springboottemplate.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Login {

    private static Logger log = LoggerFactory.getLogger(Login.class);

    @RequestMapping("/login")
    public String index(HashMap<String, String> params, HttpSession session){
        params.put("user", "ireullin");

        var msg = session.getAttribute("msg");
        if(msg != null){
            params.put("msg", (String)msg);
        }

        return "signin";
    }
    
        
    @PostMapping("/verify")
    // @ResponseBody // 直接回傳結果
    public String verify(@RequestParam("user") String user, @RequestParam("password") String password, HashMap<String, String> params, HttpSession session){
        
        log.trace("verify be called");
        
        if(user.equals("ireullin") && password.equals("1234")){
            session.setAttribute("user", user);
            session.removeAttribute("msg");
            return "redirect:/dashboard";
        }
        else{
            session.setAttribute("msg", "帳密錯誤");
            return "redirect:/login";
        }
    }
}
