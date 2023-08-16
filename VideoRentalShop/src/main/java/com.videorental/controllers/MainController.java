package com.videorental.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private static final String MAIN_VIEW = "main-page-view";

    @GetMapping
    private String menu() {

        return MAIN_VIEW;
    }
}
