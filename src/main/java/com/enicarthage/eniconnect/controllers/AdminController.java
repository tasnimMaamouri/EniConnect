package com.enicarthage.eniconnect.controllers;


import com.enicarthage.eniconnect.models.Admin;
import com.enicarthage.eniconnect.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAdmins() {
        try {
            List<Admin> admins = adminService.getAdmins();
            return ResponseEntity.ok(admins);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("adminId") Long adminId) {
        try {
            Admin admin = adminService.getAdminById(adminId);
            return ResponseEntity.ok(admin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createAdmin(@RequestBody Admin admin) {
        try {
            adminService.createAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "{adminId}")
    public ResponseEntity<Void> deleteAdminById(@PathVariable("adminId") Long adminId) {
        try {
            adminService.deleteAdminById(adminId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "{adminId}")
    public ResponseEntity<Void> updateAdminById(@PathVariable("adminId") Long adminId, @RequestBody Admin newAdmin) {
        try {
            adminService.updateAdminById(adminId, newAdmin);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        try {
            if (adminService.login(email, password).isPresent()) {
                return ResponseEntity.ok("Login successful");
            } else {
                throw new IllegalArgumentException("Invalid email or password.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
