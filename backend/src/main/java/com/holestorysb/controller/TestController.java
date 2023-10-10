package com.holestorysb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/protected-resource")
    public String getProtectedResource() {
        return "You have accessed a protected resource!";
    }

    @GetMapping("/api/admin/admin-only-resource")
    public String getAdminStuff() {
        return "You have accessed an admin only resource!";
    }
}
