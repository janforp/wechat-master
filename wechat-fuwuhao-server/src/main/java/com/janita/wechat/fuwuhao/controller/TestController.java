package com.janita.wechat.fuwuhao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018/6/21
 * @author Janita
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test/{name}")
    public String test(@PathVariable String name) {
        return name;
    }
}
