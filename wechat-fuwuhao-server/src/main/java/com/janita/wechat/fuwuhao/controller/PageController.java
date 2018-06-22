package com.janita.wechat.fuwuhao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2018/6/22
 * @author Janita
 * 页面跳转
 */
@Controller
@RequestMapping("/wx")
public class PageController {

    @GetMapping("/index.html")
    public String index(){
        return "index";
    }

    /**
     * 去绑定页面
     * @return
     */
    @GetMapping("/bind.html")
    public String bind(){
        return "bind";
    }
}
