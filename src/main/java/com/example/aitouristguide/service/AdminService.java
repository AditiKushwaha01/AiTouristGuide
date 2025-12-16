package com.example.aitouristguide.service;

import com.example.aitouristguide.model.Admin;
import com.example.aitouristguide.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public Admin getAdminByUsername(String username) {
        return adminRepository.findByUsername(username).orElse(null);
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin updateAdmin(Long id, Admin adminDetails) {
        Admin admin = getAdminById(id);
        if (admin == null) return null;

        if (adminDetails.getUsername() != null &&
                !adminDetails.getUsername().equals(admin.getUsername())) {
            admin.setUsername(adminDetails.getUsername());
        }

        if (adminDetails.getEmail() != null &&
                !adminDetails.getEmail().equals(admin.getEmail())) {
            admin.setEmail(adminDetails.getEmail());
        }

        if (adminDetails.getPassword() != null &&
                !adminDetails.getPassword().isEmpty()) {
            admin.setPassword(passwordEncoder.encode(adminDetails.getPassword()));
        }

        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        Admin admin = getAdminById(id);
        if (admin != null) {
            adminRepository.delete(admin);
        }
    }

    public boolean usernameExists(String username) {
        return adminRepository.existsByUsername(username);
    }
}


