package com.example.projektportfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecureController {

//    @GetMapping("/")
//    public String mainPage(){
//        return "index";
//    }

    @GetMapping("/admin")
    public String adminPage() {
        return "adminview";
    }

}
