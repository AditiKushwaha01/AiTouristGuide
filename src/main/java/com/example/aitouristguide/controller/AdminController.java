package com.example.aitouristguide.controller;

import com.example.aitouristguide.model.Admin;
import com.example.aitouristguide.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        if (adminService.usernameExists(admin.getUsername())) {
            return ResponseEntity.status(409).build(); // Conflict
        }

        Admin createdAdmin = adminService.createAdmin(admin);
        createdAdmin.setPassword(null); // Hide password in response
        return ResponseEntity.ok(createdAdmin);
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        admins.forEach(admin -> admin.setPassword(null)); // Hide passwords
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Admin> getAdminByUsername(@PathVariable String username) {
        Admin admin = adminService.getAdminByUsername(username);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }
        admin.setPassword(null); // Hide password
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Admin admin = adminService.getAdminById(id);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }
        admin.setPassword(null); // Hide password
        return ResponseEntity.ok(admin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(
            @PathVariable Long id,
            @RequestBody Admin adminDetails) {
        Admin updatedAdmin = adminService.updateAdmin(id, adminDetails);
        if (updatedAdmin == null) {
            return ResponseEntity.notFound().build();
        }
        updatedAdmin.setPassword(null); // Hide password
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        Admin admin = adminService.getAdminById(id);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}