package com.decoracks.app.decoracks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UsersController {

    @GetMapping("/index")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    public String user(){
        return "detail_user";
    }
}
