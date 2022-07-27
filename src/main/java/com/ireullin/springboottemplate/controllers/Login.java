package com.ireullin.springboottemplate.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ireullin.springboottemplate.components.JwtUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "Login")
@Controller
public class Login {

    @Autowired
    private JwtUtils jwtUtils;

    @ApiOperation("登入頁面")
    @GetMapping("/login")
    public String login(HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response){
        params.put("user", "ireullin");
        return "signin";
    }
    
        
    @ApiOperation("驗證頁面")
    @PostMapping("/verify")
    public String verify(HttpServletRequest request, HttpServletResponse response){
        
        String user = request.getParameter("user");
        String password = request.getParameter("password");

        log.trace("verify be called");
        
        if(user.equals("ireullin") && password.equals("1234")){
            var token = jwtUtils.createToken(user);
            response.setHeader("token", token);
            return "redirect:/dashboard";
        }
        else{
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
    public LoginRsp verifyFromJson(@RequestBody LoginReq req, HttpServletResponse response){
        
        log.trace("verify be called");
        
        var user = req.getUser();
        var password = req.getPassword();
        if(user.equals("ireullin") && password.equals("1234")){
            var token = jwtUtils.createToken(user);
            response.setHeader("token", token);
            return new LoginRsp(req.getUser(), "OK");
        }
        else{
            return new LoginRsp(req.getUser(), "failed");
        }
    }
}
