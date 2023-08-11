package com.dcm.spring.druginfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebContoller {

    @GetMapping("/")
    public  String home(){
        return "home";
    }
    @GetMapping("/tmp")
    public  String tmp(){
        return "tmp";
    }
    @GetMapping("/search")
    public  String search(){
        return "search";
    }
}
