package com.kanezi.springsocial2cloud;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping
    public String home() {
        return "index";
    }
}
