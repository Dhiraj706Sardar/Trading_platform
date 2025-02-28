package com.dtrade.trading.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String home(){
        return "Welcome to the trading platform";
    }
    @GetMapping("/api")
    public String secured(){
        return "This is secured route.";
    }

}
