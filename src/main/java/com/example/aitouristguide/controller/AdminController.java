package com.example.aitouristguide.controller;

import com.example.aitouristguide.model.Admin;
import com.example.aitouristguide.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @GetMapping("/{username}")
    public Admin getAdmin(@PathVariable String username) {
        return adminService.getAdminByUsername(username);
    }

}
