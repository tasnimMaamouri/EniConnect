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

        try {
            List<Admin> admins = adminService.getAdmins();
            return ResponseEntity.ok(admins);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        }

        @GetMapping("/{GetAdminId}")
        public ResponseEntity<Admin> getAdminById(@PathVariable Long adminId) {

            try {
                Admin admin = adminService.getAdminById(adminId);
                return ResponseEntity.ok(admin);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping("/{CreateAdminId}")
        public ResponseEntity<String> createAdmin(@RequestBody Admin admin) {

            try {
                adminService.createAdmin(admin);
                return ResponseEntity.status(HttpStatus.CREATED).body("Admin created successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        @DeleteMapping("/{DeleteAdminId}")
        public ResponseEntity<String> deleteAdminById(@PathVariable Long adminId) {

            try {
                adminService.deleteAdminById(adminId);
                return ResponseEntity.ok("Admin deleted successfully");
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

    }



