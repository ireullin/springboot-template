package com.ireullin.springboottemplate.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ireullin.springboottemplate.settings.MySetting;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "Dashboard")
@Controller
public class Dashboard {
    
    public record InfoRsp(String port,MySetting setting) {
    }

    // 自動去讀設定檔
    @Autowired 
    private MySetting mySetting;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 從設定檔讀
    @Value("${server.port}")
    private String port;

    @ApiOperation("回傳dashboard")
    @GetMapping("/dashboard")
    public String index(HashMap<String, String> params){
        return "dashboard";
    }

    @ApiOperation("回傳info")
    @GetMapping("/info")
    @ResponseBody // 直接回傳結果
    public InfoRsp info(HashMap<String, String> params, HttpSession session) throws Exception{
        return new InfoRsp(port, mySetting);
    }

    @ApiOperation("查DB")
    @GetMapping("/querydb")
    @ResponseBody // 直接回傳結果
    public List<Map<String,Object>> queryDb(HashMap<String, String> params, HttpSession session) throws Exception{
        return jdbcTemplate.queryForList("select * from account limit 10");
    }
}
