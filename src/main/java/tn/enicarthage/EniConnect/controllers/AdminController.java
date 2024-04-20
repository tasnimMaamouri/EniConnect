package tn.enicarthage.EniConnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EniConnect.entities.Admin;
import tn.enicarthage.EniConnect.services.IAdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

        @Autowired
        private IAdminService adminService;

    @GetMapping
        public ResponseEntity<List<Admin>> getAllAdmins() {
            List<Admin> admins = adminService.getAdmins();
            return ResponseEntity.ok(admins);
        }

        @GetMapping("/{GetAdminId}")
        public ResponseEntity<Admin> getAdminById(@PathVariable Long adminId) {
            Admin admin = adminService.getAdminById(adminId);
            return ResponseEntity.ok(admin);
        }

        @PostMapping("/{CreateAdminId}")
        public ResponseEntity<String> createAdmin(@RequestBody Admin admin) {
            adminService.createAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin created successfully");
        }

        @DeleteMapping("/{DeleteAdminId}")
        public ResponseEntity<String> deleteAdminById(@PathVariable Long adminId) {
            adminService.deleteAdminById(adminId);
            return ResponseEntity.ok("Admin deleted successfully");
        }

    }



