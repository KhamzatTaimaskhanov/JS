package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UsersController {
    @GetMapping("/admin")
    public String authAdminRole() {
        return "admin";
    }

    @GetMapping("/user")
    public String authUserRole() {
        return "user";
    }
}
