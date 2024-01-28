package com.travel.io.auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class HomeController {
    @GetMapping("/")
    public String home(){return "Hello, Home!; ";}

    @GetMapping("/secured")
    public String secured(){ return "Hello, Home!; ";}


    
}
