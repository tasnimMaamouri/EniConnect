package tn.enicarthage.EniConnect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EniConnect.entities.Admin;
import tn.enicarthage.EniConnect.repositories.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IAdminServiceImpl implements IAdminService{
    private final AdminRepository adminRepository;
    @Autowired
    public IAdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Admin> getAdmins(){
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin with id " + adminId + " not found."));

        return admin;
    }

    @Override
    public void createAdmin(Admin admin) {
        Optional<Admin> adminOptionnal = adminRepository.findByEmail(admin.getEmail());
        if(adminOptionnal.isPresent()){
            throw new IllegalArgumentException("admin with this email already exist");
        }
        adminRepository.save(admin);

    }

    @Override
    public void deleteAdminById(Long adminId) {
        boolean exists = adminRepository.existsById(adminId);
        if(!exists){
            throw new IllegalArgumentException("admin with this id does not exists");
        }
        adminRepository.deleteById(adminId);
    }


}
