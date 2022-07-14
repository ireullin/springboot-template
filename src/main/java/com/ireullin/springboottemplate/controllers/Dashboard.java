package com.ireullin.springboottemplate.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ireullin.springboottemplate.settings.MySetting;

@Controller
public class Dashboard {
    
    private static Logger log = LoggerFactory.getLogger(Dashboard.class);

    public record InfoRsp(String port,MySetting setting) {
    }

    // 自動去讀設定檔
    @Autowired 
    private MySetting mySetting;

    // 從設定檔讀
    @Value("${server.port}")
    private String port;


    @RequestMapping("/dashboard")
    public String index(HashMap<String, String> params){
        return "dashboard";
    }

    @GetMapping("/info")
    @ResponseBody // 直接回傳結果
    public InfoRsp info(HashMap<String, String> params, HttpSession session){
        return new InfoRsp(port, mySetting);
    }
}
