package com.ireullin.springboottemplate.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "Login")
@Controller
public class Login {

    @ApiOperation("登入頁面")
    @GetMapping("/login")
    public String index(HashMap<String, String> params, HttpSession session){
        params.put("user", "ireullin");

        var msg = session.getAttribute("msg");
        if(msg != null){
            params.put("msg", (String)msg);
        }

        return "signin";
    }
    
        
    @ApiOperation("驗證頁面")
    @PostMapping("/verify")
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

    @Data
    @NoArgsConstructor
    public static class LoginReq{
        private String user;
        private String password;

        // @Data
        // @NoArgsConstructor
        // 以上兩個annotation取代以下function
        // public String getUser(){ return user; }
        // public String getPassword(){ return password; }
        // public void setUser(String user){this.user=user;}
        // public void setPassword(String password){this.password=password;}
    }
    
    public record LoginRsp(String user, String status) {
    }

    @ApiOperation("驗證API")
    @PostMapping("/verify/json")
    @ResponseBody
    public LoginRsp verifyFromJson(@RequestBody LoginReq req, HashMap<String, String> params, HttpSession session){
        
        log.trace("verify be called");
        
        if(req.getUser().equals("ireullin") && req.getPassword().equals("1234")){
            session.setAttribute("user", req.getUser());
            return new LoginRsp(req.getUser(), "OK");
        }
        else{
            return new LoginRsp(req.getUser(), "failed");
        }
    }
}
