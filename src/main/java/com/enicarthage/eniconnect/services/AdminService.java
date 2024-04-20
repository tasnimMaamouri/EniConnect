package com.enicarthage.eniconnect.services;

import com.enicarthage.eniconnect.models.Admin;
import com.enicarthage.eniconnect.repositories.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAdmins(){
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin with id " + adminId + " not found."));

        return admin;
    }

    public void createAdmin(Admin admin) {
        Optional<Admin> adminOptionnal = adminRepository.findByEmail(admin.getEmail());
        if(adminOptionnal.isPresent()){
            throw new IllegalArgumentException("admin with this email already exist");
        }
        adminRepository.save(admin);
        System.out.println("admin created successfully");

    }

    public void deleteAdminById(Long adminId) {
        boolean exists = adminRepository.existsById(adminId);
        if(!exists){
            throw new IllegalArgumentException("admin with this id does not exists");
        }
        adminRepository.deleteById(adminId);
        System.out.println("admin deleted successfully");
    }
    @Transactional
    public void updateAdminById(Long adminId, Admin newAdmin) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin with id " + adminId + " not found."));

        if (newAdmin.getFirstname() != null && !newAdmin.getFirstname().isEmpty() && !Objects.equals(newAdmin.getFirstname(), admin.getFirstname())) {
            admin.setFirstname(newAdmin.getFirstname());
        }
        if (newAdmin.getLastname() != null && !newAdmin.getLastname().isEmpty() && !Objects.equals(newAdmin.getLastname(), admin.getLastname())) {
            admin.setLastname(newAdmin.getLastname());
        }
        if (newAdmin.getDateOfBirth() != null && !Objects.equals(newAdmin.getDateOfBirth(), admin.getDateOfBirth())) {
            admin.setDateOfBirth(newAdmin.getDateOfBirth());
        }
        if (newAdmin.getPost() != null && !newAdmin.getPost().isEmpty() && !Objects.equals(newAdmin.getPost(), admin.getPost())) {
            admin.setPost(newAdmin.getPost());
        }
        if (newAdmin.getEmail() != null && !newAdmin.getEmail().isEmpty() && !Objects.equals(newAdmin.getEmail(), admin.getEmail())) {
            admin.setEmail(newAdmin.getEmail());
        }
        if (newAdmin.getPassword() != null && !newAdmin.getPassword().isEmpty() && !Objects.equals(newAdmin.getPassword(), admin.getPassword())) {
            admin.setPassword(newAdmin.getPassword());
        }

        // Save the updated admin entity
        adminRepository.save(admin);

        System.out.println("admin updated successfully");
    }

    //login
    public Optional<Admin> login(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password);
    }
}
