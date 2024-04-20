package tn.enicarthage.EniConnect.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EniConnect.entities.Admin;
import tn.enicarthage.EniConnect.services.AdminServiceImp;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminServiceImp adminServiceImp;
    @Autowired
    public AdminController(AdminServiceImp adminServiceImp) {
        this.adminServiceImp = adminServiceImp;
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAdmins() {
        try {
            List<Admin> admins = adminServiceImp.getAdmins();
            return ResponseEntity.ok(admins);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("adminId") Long adminId) {
        try {
            Admin admin = adminServiceImp.getAdminById(adminId);
            return ResponseEntity.ok(admin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createAdmin(@RequestBody Admin admin) {
        try {
            adminServiceImp.createAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "{adminId}")
    public ResponseEntity<Void> deleteAdminById(@PathVariable("adminId") Long adminId) {
        try {
            adminServiceImp.deleteAdminById(adminId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "{adminId}")
    public ResponseEntity<Void> updateAdminById(@PathVariable("adminId") Long adminId, @RequestBody Admin newAdmin) {
        try {
            adminServiceImp.updateAdminById(adminId, newAdmin);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
