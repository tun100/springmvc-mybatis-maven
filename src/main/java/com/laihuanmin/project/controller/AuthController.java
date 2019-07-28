package com.laihuanmin.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    @RequestMapping(value = "/login")
    public Object login() throws Exception {
        Map map = new HashMap();
        map.put("key", "测试12345");
        return map;
    }
}