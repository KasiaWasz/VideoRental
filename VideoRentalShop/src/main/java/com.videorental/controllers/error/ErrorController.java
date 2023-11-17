package com.videorental.controllers.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/access-denied")
@Controller
class ErrorController {

    public static final String V_ERROR = "error";

    @GetMapping
    private String getErrorPage() {

        return V_ERROR;
    }
}
